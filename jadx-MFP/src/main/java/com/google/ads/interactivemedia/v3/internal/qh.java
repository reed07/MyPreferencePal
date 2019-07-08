package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: IMASDK */
public final class qh implements fq {
    private static final Pattern a = Pattern.compile("LOCAL:([^,]+)");
    private static final Pattern b = Pattern.compile("MPEGTS:(\\d+)");
    private final String c;
    private final ve d;
    private final ut e = new ut();
    private fs f;
    private byte[] g = new byte[1024];
    private int h;

    public qh(String str, ve veVar) {
        this.c = str;
        this.d = veVar;
    }

    public final void c() {
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        frVar.b(this.g, 0, 6, false);
        this.e.a(this.g, 6);
        if (rd.b(this.e)) {
            return true;
        }
        frVar.b(this.g, 6, 3, false);
        this.e.a(this.g, 9);
        return rd.b(this.e);
    }

    public final void a(fs fsVar) {
        this.f = fsVar;
        fsVar.a(new ga(-9223372036854775807L));
    }

    public final void a(long j, long j2) {
        throw new IllegalStateException();
    }

    public final int a(fr frVar, fx fxVar) throws IOException, InterruptedException {
        int i;
        int d2 = (int) frVar.d();
        int i2 = this.h;
        byte[] bArr = this.g;
        if (i2 == bArr.length) {
            if (d2 != -1) {
                i = d2;
            } else {
                i = bArr.length;
            }
            this.g = Arrays.copyOf(bArr, (i * 3) / 2);
        }
        byte[] bArr2 = this.g;
        int i3 = this.h;
        int a2 = frVar.a(bArr2, i3, bArr2.length - i3);
        if (a2 != -1) {
            this.h += a2;
            if (d2 == -1 || this.h != d2) {
                return 0;
            }
        }
        ut utVar = new ut(this.g);
        rd.a(utVar);
        long j = 0;
        long j2 = 0;
        while (true) {
            String s = utVar.s();
            if (TextUtils.isEmpty(s)) {
                Matcher c2 = rd.c(utVar);
                if (c2 == null) {
                    a(0);
                } else {
                    long a3 = rd.a(c2.group(1));
                    long b2 = this.d.b(ve.e((j + a3) - j2));
                    gc a4 = a(b2 - a3);
                    this.e.a(this.g, this.h);
                    a4.a(this.e, this.h);
                    a4.a(b2, 1, this.h, 0, null);
                }
                return -1;
            } else if (s.startsWith("X-TIMESTAMP-MAP")) {
                Matcher matcher = a.matcher(s);
                if (!matcher.find()) {
                    String str = "X-TIMESTAMP-MAP doesn't contain local timestamp: ";
                    String valueOf = String.valueOf(s);
                    throw new ca(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                }
                Matcher matcher2 = b.matcher(s);
                if (!matcher2.find()) {
                    String str2 = "X-TIMESTAMP-MAP doesn't contain media timestamp: ";
                    String valueOf2 = String.valueOf(s);
                    throw new ca(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
                }
                j2 = rd.a(matcher.group(1));
                j = ve.d(Long.parseLong(matcher2.group(1)));
            }
        }
    }

    private final gc a(long j) {
        gc a2 = this.f.a(0, 3);
        a2.a(bs.a((String) null, MimeTypes.TEXT_VTT, (String) null, -1, 0, this.c, (fa) null, j));
        this.f.a();
        return a2;
    }
}
