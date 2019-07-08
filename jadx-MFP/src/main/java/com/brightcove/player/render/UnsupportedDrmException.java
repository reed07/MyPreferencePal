package com.brightcove.player.render;

public final class UnsupportedDrmException extends Exception {
    public static final int REASON_NO_DRM = 0;
    public static final int REASON_UNKNOWN = 2;
    public static final int REASON_UNSUPPORTED_SCHEME = 1;
    public final int reason;

    public UnsupportedDrmException(int i) {
        this.reason = i;
    }

    public UnsupportedDrmException(int i, Exception exc) {
        super(exc);
        this.reason = i;
    }
}
