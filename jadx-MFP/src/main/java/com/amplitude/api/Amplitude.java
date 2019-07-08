package com.amplitude.api;

import java.util.HashMap;
import java.util.Map;

public class Amplitude {
    static final Map<String, AmplitudeClient> instances = new HashMap();

    public static AmplitudeClient getInstance() {
        return getInstance(null);
    }

    public static synchronized AmplitudeClient getInstance(String str) {
        AmplitudeClient amplitudeClient;
        synchronized (Amplitude.class) {
            String normalizeInstanceName = Utils.normalizeInstanceName(str);
            amplitudeClient = (AmplitudeClient) instances.get(normalizeInstanceName);
            if (amplitudeClient == null) {
                amplitudeClient = new AmplitudeClient(normalizeInstanceName);
                instances.put(normalizeInstanceName, amplitudeClient);
            }
        }
        return amplitudeClient;
    }
}
