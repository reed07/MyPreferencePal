package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
public class ahd extends IOException {
    private static final long serialVersionUID = -6947486886997889499L;

    ahd(int i, int i2) {
        StringBuilder sb = new StringBuilder(108);
        sb.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
        sb.append(i);
        sb.append(" limit ");
        sb.append(i2);
        sb.append(").");
        super(sb.toString());
    }

    public ahd() {
    }

    public ahd(String str) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 60);
        sb.append("Unable to bind a sample queue to TrackGroup with mime type ");
        sb.append(str);
        sb.append(".");
        super(sb.toString());
    }

    public ahd(IOException iOException) {
        super(iOException);
    }
}
