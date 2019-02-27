package moe.anitrack.server;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.base.MoreObjects;

public class RoutesTest {

    @Test
    public void ensureControllersAreProperlyDefined() {
        Set<String> controllerFragments = findAllControllerFragments();
        assertThat(controllerFragments).doesNotContain((String) null);

        final Set<Class<?>> restControllers = new Reflections("moe.anitrack.server").getTypesAnnotatedWith(RequestMapping.class);
        assertThat(restControllers).hasSameSizeAs(controllerFragments);
        restControllers
                .stream()
                .map(clazz -> clazz.getDeclaredAnnotation(RequestMapping.class))
                .map(RequestMapping::value)
                .forEach(fragment -> assertThat(controllerFragments).contains(fragment));
    }

    @Test
    public void ensureControllerMethodsAreProperlyDefined() {
        Set<String> methodFragments = findAllMethodFragments();
        assertThat(methodFragments).doesNotContain((String) null);

        final Reflections methodsLookup = new Reflections("moe.anitrack.server", new MethodAnnotationsScanner());
        final Set<Method> gets = methodsLookup.getMethodsAnnotatedWith(GetMapping.class);
        final Set<Method> posts = methodsLookup.getMethodsAnnotatedWith(PostMapping.class);

        final Set<Method> endpointMethods = Stream.of(gets, posts).flatMap(Set::stream).collect(Collectors.toSet());
        assertThat(endpointMethods).hasSameSizeAs(methodFragments);

        endpointMethods.stream().map(method -> {
            final Optional<String[]> getMappings = ofNullable(method.getDeclaredAnnotation(GetMapping.class)).map(GetMapping::value);
            final Optional<String[]> postMappings = ofNullable(method.getDeclaredAnnotation(PostMapping.class)).map(PostMapping::value);
            return MoreObjects.firstNonNull(getMappings.orElse(null), postMappings.orElse(null));
        }).forEach(endpoint -> assertThat(methodFragments).contains(endpoint));
    }

    private static Set<String> findAllControllerFragments() {
        return readPublicStaticStringFieldsFromClass(Routes.class);
    }

    private static Set<String> findAllMethodFragments() {
        final Class[] controllerSubclasses = Routes.class.getDeclaredClasses();
        return Arrays.stream(controllerSubclasses)
                     .map(RoutesTest::readPublicStaticStringFieldsFromClass)
                     .flatMap(Set::stream)
                     .collect(Collectors.toCollection(HashSet::new));
    }

    private static Set<String> readPublicStaticStringFieldsFromClass(final Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                     .filter(field(Modifier::isPublic))
                     .filter(field(Modifier::isStatic))
                     .filter(field(Modifier::isFinal))
                     .map(RoutesTest::readStaticStringField)
                     .collect(Collectors.toCollection(HashSet::new));
    }

    private static Predicate<Field> field(Predicate<Integer> modifierPredicate) {
        return f -> modifierPredicate.test(f.getModifiers());
    }

    private static String readStaticStringField(final Field field) {
        try {
            return (String) field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
