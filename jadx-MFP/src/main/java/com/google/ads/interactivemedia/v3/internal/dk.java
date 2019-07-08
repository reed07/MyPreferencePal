package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class dk extends Exception {
    public dk(int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder(78);
        sb.append("Unhandled format: ");
        sb.append(i);
        sb.append(" Hz, ");
        sb.append(i2);
        sb.append(" channels in encoding ");
        sb.append(i3);
        super(sb.toString());
    }
}
