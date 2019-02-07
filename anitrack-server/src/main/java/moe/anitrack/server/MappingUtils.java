package moe.anitrack.server;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

public final class MappingUtils {

    private MappingUtils() {
    }

    public static String toString(final RequestMappingInfo mappingInfo, final HandlerMethod handlerMethod) {
        final List<RequestMethod> httpMethods = new ArrayList<>(mappingInfo.getMethodsCondition().getMethods());
        final String path = mappingInfo.getPatternsCondition().getPatterns().stream().findFirst().orElseThrow();
        final Method handlerJavaMethod = handlerMethod.getMethod();
        final String correspondingMethod = handlerJavaMethod.getDeclaringClass().getSimpleName() + "#" + handlerJavaMethod.getName();
        return httpMethods.toString() + " " + path + " -> " + correspondingMethod;
    }

}
