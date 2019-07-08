package com.twitter.sdk.android.core;

import okhttp3.Headers;

public class TwitterRateLimit {
    private int remainingRequest;
    private int requestLimit;
    private long resetSeconds;

    TwitterRateLimit(Headers headers) {
        if (headers != null) {
            for (int i = 0; i < headers.size(); i++) {
                if ("x-rate-limit-limit".equals(headers.name(i))) {
                    this.requestLimit = Integer.valueOf(headers.value(i)).intValue();
                } else if ("x-rate-limit-remaining".equals(headers.name(i))) {
                    this.remainingRequest = Integer.valueOf(headers.value(i)).intValue();
                } else if ("x-rate-limit-reset".equals(headers.name(i))) {
                    this.resetSeconds = Long.valueOf(headers.value(i)).longValue();
                }
            }
            return;
        }
        throw new IllegalArgumentException("headers must not be null");
    }
}
