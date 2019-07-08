package com.google.ads.interactivemedia.v3.internal;

import android.media.MediaCodec.CodecException;

/* compiled from: IMASDK */
public final class jh extends Exception {
    private final String a;
    private final boolean b;
    private final String c;
    private final String d;

    public jh(bs bsVar, Throwable th, boolean z, int i) {
        String valueOf = String.valueOf(bsVar);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
        sb.append("Decoder init failed: [");
        sb.append(i);
        sb.append("], ");
        sb.append(valueOf);
        String sb2 = sb.toString();
        String str = bsVar.h;
        String str2 = i < 0 ? "neg_" : "";
        int abs = Math.abs(i);
        StringBuilder sb3 = new StringBuilder(String.valueOf(str2).length() + 64);
        sb3.append("com.google.android.exoplayer.MediaCodecTrackRenderer_");
        sb3.append(str2);
        sb3.append(abs);
        this(sb2, th, str, z, null, sb3.toString(), null);
    }

    public jh(bs bsVar, Throwable th, boolean z, String str) {
        String valueOf = String.valueOf(bsVar);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23 + String.valueOf(valueOf).length());
        sb.append("Decoder init failed: ");
        sb.append(str);
        sb.append(", ");
        sb.append(valueOf);
        this(sb.toString(), th, bsVar.h, z, str, (vf.a < 21 || !(th instanceof CodecException)) ? null : ((CodecException) th).getDiagnosticInfo(), null);
    }

    private jh(String str, Throwable th, String str2, boolean z, String str3, String str4, jh jhVar) {
        super(str, th);
        this.a = str2;
        this.b = z;
        this.c = str3;
        this.d = str4;
    }

    /* access modifiers changed from: private */
    public final jh a(jh jhVar) {
        jh jhVar2 = new jh(getMessage(), getCause(), this.a, this.b, this.c, this.d, jhVar);
        return jhVar2;
    }
}
