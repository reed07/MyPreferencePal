package com.myfitnesspal.shared.validation;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class RegexValidator_Factory implements Factory<RegexValidator> {
    private final Provider<String> regexProvider;

    public RegexValidator_Factory(Provider<String> provider) {
        this.regexProvider = provider;
    }

    public RegexValidator get() {
        return provideInstance(this.regexProvider);
    }

    public static RegexValidator provideInstance(Provider<String> provider) {
        return new RegexValidator((String) provider.get());
    }

    public static RegexValidator_Factory create(Provider<String> provider) {
        return new RegexValidator_Factory(provider);
    }

    public static RegexValidator newRegexValidator(String str) {
        return new RegexValidator(str);
    }
}
