package moe.anitrack.base.util.form;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class FormEmailValidator implements Predicate<String> {

    private static final Pattern MINIMAL_EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    @Override
    public boolean test(String s) {
        return MINIMAL_EMAIL_PATTERN.matcher(s).matches();
    }

}
