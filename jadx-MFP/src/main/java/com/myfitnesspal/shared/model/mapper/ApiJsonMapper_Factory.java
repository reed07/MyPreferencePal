package com.myfitnesspal.shared.model.mapper;

import dagger.internal.Factory;

public final class ApiJsonMapper_Factory implements Factory<ApiJsonMapper> {
    private static final ApiJsonMapper_Factory INSTANCE = new ApiJsonMapper_Factory();

    public ApiJsonMapper get() {
        return provideInstance();
    }

    public static ApiJsonMapper provideInstance() {
        return new ApiJsonMapper();
    }

    public static ApiJsonMapper_Factory create() {
        return INSTANCE;
    }

    public static ApiJsonMapper newApiJsonMapper() {
        return new ApiJsonMapper();
    }
}
