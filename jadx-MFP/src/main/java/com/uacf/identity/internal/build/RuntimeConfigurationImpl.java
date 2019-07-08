package com.uacf.identity.internal.build;

public class RuntimeConfigurationImpl implements RuntimeConfiguration {
    public boolean allowSso() {
        return true;
    }

    public boolean isDebug() {
        return false;
    }

    public boolean shouldValidateCaller() {
        return true;
    }
}
