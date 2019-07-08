package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class dx extends Exception {
    public dx(int i) {
        StringBuilder sb = new StringBuilder(36);
        sb.append("AudioTrack write failed: ");
        sb.append(i);
        super(sb.toString());
    }
}
