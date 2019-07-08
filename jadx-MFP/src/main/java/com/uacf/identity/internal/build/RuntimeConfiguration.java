package com.uacf.identity.internal.build;

public interface RuntimeConfiguration {
    boolean allowSso();

    boolean isDebug();

    boolean shouldValidateCaller();
}
