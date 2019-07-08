package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.validation.Validator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideServingSizeValidatorFactory implements Factory<Validator> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideServingSizeValidatorFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public Validator get() {
        return provideInstance(this.module);
    }

    public static Validator provideInstance(ApplicationModule applicationModule) {
        return proxyProvideServingSizeValidator(applicationModule);
    }

    public static ApplicationModule_ProvideServingSizeValidatorFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideServingSizeValidatorFactory(applicationModule);
    }

    public static Validator proxyProvideServingSizeValidator(ApplicationModule applicationModule) {
        return (Validator) Preconditions.checkNotNull(applicationModule.provideServingSizeValidator(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
