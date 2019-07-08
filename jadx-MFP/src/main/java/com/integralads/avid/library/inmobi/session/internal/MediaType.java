package com.integralads.avid.library.inmobi.session.internal;

public enum MediaType {
    DISPLAY("display"),
    VIDEO("video");
    
    private final String value;

    private MediaType(String str) {
        this.value = str;
    }

    public final String toString() {
        return this.value;
    }
}
