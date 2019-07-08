package com.google.ads.interactivemedia.v3.internal;

import android.os.SystemClock;
import android.util.Log;

/* compiled from: IMASDK */
public class eb {
    final /* synthetic */ ef a;

    public void a(long j, long j2, long j3, long j4) {
        long b = this.a.p();
        long c = this.a.q();
        StringBuilder sb = new StringBuilder(182);
        sb.append("Spurious audio timestamp (frame position mismatch): ");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(", ");
        sb.append(j3);
        sb.append(", ");
        sb.append(j4);
        sb.append(", ");
        sb.append(b);
        sb.append(", ");
        sb.append(c);
        Log.w("AudioTrack", sb.toString());
    }

    public void b(long j, long j2, long j3, long j4) {
        long b = this.a.p();
        long c = this.a.q();
        StringBuilder sb = new StringBuilder(180);
        sb.append("Spurious audio timestamp (system clock mismatch): ");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(", ");
        sb.append(j3);
        sb.append(", ");
        sb.append(j4);
        sb.append(", ");
        sb.append(b);
        sb.append(", ");
        sb.append(c);
        Log.w("AudioTrack", sb.toString());
    }

    public void a(long j) {
        StringBuilder sb = new StringBuilder(61);
        sb.append("Ignoring impossibly large audio latency: ");
        sb.append(j);
        Log.w("AudioTrack", sb.toString());
    }

    public void a(int i, long j) {
        if (this.a.m != null) {
            this.a.m.a(i, j, SystemClock.elapsedRealtime() - this.a.S);
        }
    }

    private eb(ef efVar) {
        this.a = efVar;
    }

    /* synthetic */ eb(ef efVar, byte b) {
        this(efVar);
    }
}
