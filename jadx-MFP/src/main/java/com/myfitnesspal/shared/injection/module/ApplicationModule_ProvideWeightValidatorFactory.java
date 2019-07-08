package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.shared.validation.Validator;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ApplicationModule_ProvideWeightValidatorFactory implements Factory<Validator> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideWeightValidatorFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public Validator get() {
        return provideInstance(this.module);
    }

    public static Validator provideInstance(ApplicationModule applicationModule) {
        return proxyProvideWeightValidator(applicationModule);
    }

    public static ApplicationModule_ProvideWeightValidatorFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideWeightValidatorFactory(applicationModule);
    }

    public static Validator proxyProvideWeightValidator(ApplicationModule applicationModule) {
        return (Validator) Preconditions.checkNotNull(applicationModule.provideWeightValidator(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
