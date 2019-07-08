package com.integralads.avid.library.inmobi.registration;

public interface AvidAdSessionRegistryListener {
    void registryHasActiveSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry);

    void registryHasSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry);
}
