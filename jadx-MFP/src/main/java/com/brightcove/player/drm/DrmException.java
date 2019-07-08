package com.brightcove.player.drm;

public class DrmException extends Exception {
    public DrmException(String str, Throwable th) {
        super(str, th);
    }

    public DrmException(Throwable th) {
        super(th);
    }
}
