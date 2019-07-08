package com.facebook.login;

import com.facebook.internal.NativeProtocol;

public enum DefaultAudience {
    NONE(null),
    ONLY_ME(NativeProtocol.AUDIENCE_ME),
    FRIENDS("friends"),
    EVERYONE(NativeProtocol.AUDIENCE_EVERYONE);
    
    private final String nativeProtocolAudience;

    private DefaultAudience(String str) {
        this.nativeProtocolAudience = str;
    }

    public String getNativeProtocolAudience() {
        return this.nativeProtocolAudience;
    }
}
