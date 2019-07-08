package com.myfitnesspal.shared.validation;

import com.uacf.core.util.Strings;
import java.util.regex.Pattern;
import javax.inject.Inject;

public class RegexValidator implements Validator {
    private Pattern pattern;

    @Inject
    public RegexValidator(String str) {
        this.pattern = Pattern.compile(str);
    }

    public boolean validate(String str) {
        if (Strings.isEmpty(str)) {
            return false;
        }
        return this.pattern.matcher(str).matches();
    }
}
