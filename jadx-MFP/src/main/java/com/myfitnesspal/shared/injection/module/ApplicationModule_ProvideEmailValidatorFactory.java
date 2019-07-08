package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.validation.Validator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideEmailValidatorFactory implements Factory<Validator> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideEmailValidatorFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public Validator get() {
        return provideInstance(this.module);
    }

    public static Validator provideInstance(ApplicationModule applicationModule) {
        return proxyProvideEmailValidator(applicationModule);
    }

    public static ApplicationModule_ProvideEmailValidatorFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideEmailValidatorFactory(applicationModule);
    }

    public static Validator proxyProvideEmailValidator(ApplicationModule applicationModule) {
        return (Validator) Preconditions.checkNotNull(applicationModule.provideEmailValidator(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
