package io.opencensus.internal;

import java.util.ServiceConfigurationError;

public final class Provider {
    private Provider() {
    }

    public static <T> T createInstance(Class<?> cls, Class<T> cls2) {
        try {
            return cls.asSubclass(cls2).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Provider ");
            sb.append(cls.getName());
            sb.append(" could not be instantiated.");
            throw new ServiceConfigurationError(sb.toString(), e);
        }
    }
}
