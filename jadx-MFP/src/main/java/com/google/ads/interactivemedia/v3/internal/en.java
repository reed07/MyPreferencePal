package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public final class en extends jg implements um {
    private final Context b;
    /* access modifiers changed from: private */
    public final dm c;
    private final dt d;
    private final long[] e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private MediaFormat j;
    private int k;
    private int l;
    private int m;
    private int n;
    private long o;
    private boolean p;
    /* access modifiers changed from: private */
    public boolean q;
    private long r;
    private int s;

    public en(Context context, ji jiVar, Handler handler, dl dlVar) {
        this(context, jiVar, null, false, handler, dlVar);
    }

    protected static void B() {
    }

    protected static void C() {
    }

    protected static void D() {
    }

    public final um c() {
        return this;
    }

    private en(Context context, ji jiVar, fe<fg> feVar, boolean z, Handler handler, dl dlVar) {
        this(context, jiVar, null, false, handler, dlVar, null, new dj[0]);
    }

    private en(Context context, ji jiVar, fe<fg> feVar, boolean z, Handler handler, dl dlVar, de deVar, dj... djVarArr) {
        this(context, jiVar, feVar, z, handler, dlVar, new ef(null, djVarArr));
    }

    private en(Context context, ji jiVar, fe<fg> feVar, boolean z, Handler handler, dl dlVar, dt dtVar) {
        super(1, jiVar, feVar, z, 44100.0f);
        this.b = context.getApplicationContext();
        this.d = dtVar;
        this.r = -9223372036854775807L;
        this.e = new long[10];
        this.c = new dm(handler, dlVar);
        dtVar.a(new dw(this, 0));
    }

    /* access modifiers changed from: protected */
    public final int a(ji jiVar, fe<fg> feVar, bs bsVar) throws jm {
        boolean z;
        String str = bsVar.h;
        if (!un.a(str)) {
            return 0;
        }
        int i2 = vf.a >= 21 ? 32 : 0;
        boolean a = cj.a(feVar, bsVar.k);
        int i3 = 4;
        int i4 = 8;
        if (a && a(bsVar.s, str) && jiVar.a() != null) {
            return i2 | 8 | 4;
        }
        if ((MimeTypes.AUDIO_RAW.equals(str) && !this.d.a(bsVar.s, bsVar.u)) || !this.d.a(bsVar.s, 2)) {
            return 1;
        }
        fa faVar = bsVar.k;
        if (faVar != null) {
            z = false;
            for (int i5 = 0; i5 < faVar.b; i5++) {
                z |= faVar.a(i5).b;
            }
        } else {
            z = false;
        }
        List a2 = jiVar.a(bsVar.h, z);
        if (a2.isEmpty()) {
            if (!z || jiVar.a(bsVar.h, false).isEmpty()) {
                return 1;
            }
            return 2;
        } else if (!a) {
            return 2;
        } else {
            jf jfVar = (jf) a2.get(0);
            boolean a3 = jfVar.a(bsVar);
            if (a3 && jfVar.b(bsVar)) {
                i4 = 16;
            }
            if (!a3) {
                i3 = 3;
            }
            return i4 | i2 | i3;
        }
    }

    /* access modifiers changed from: protected */
    public final List<jf> a(ji jiVar, bs bsVar, boolean z) throws jm {
        if (a(bsVar.s, bsVar.h)) {
            jf a = jiVar.a();
            if (a != null) {
                return Collections.singletonList(a);
            }
        }
        return super.a(jiVar, bsVar, z);
    }

    private final boolean a(int i2, String str) {
        return this.d.a(i2, un.h(str));
    }

    /* access modifiers changed from: protected */
    public final void a(jf jfVar, MediaCodec mediaCodec, bs bsVar, MediaCrypto mediaCrypto, float f2) {
        bs[] x = x();
        int a = a(jfVar, bsVar);
        boolean z = true;
        if (x.length != 1) {
            int i2 = a;
            for (bs bsVar2 : x) {
                if (jfVar.a(bsVar, bsVar2, false)) {
                    i2 = Math.max(i2, a(jfVar, bsVar2));
                }
            }
            a = i2;
        }
        this.f = a;
        this.h = vf.a < 24 && "OMX.SEC.aac.dec".equals(jfVar.a) && "samsung".equals(vf.c) && (vf.b.startsWith("zeroflte") || vf.b.startsWith("herolte") || vf.b.startsWith("heroqlte"));
        String str = jfVar.a;
        if (vf.a >= 21 || !"OMX.SEC.mp3.dec".equals(str) || !"samsung".equals(vf.c) || (!vf.b.startsWith("baffin") && !vf.b.startsWith("grand") && !vf.b.startsWith("fortuna") && !vf.b.startsWith("gprimelte") && !vf.b.startsWith("j2y18lte") && !vf.b.startsWith("ms01"))) {
            z = false;
        }
        this.i = z;
        this.g = jfVar.e;
        String str2 = this.g ? MimeTypes.AUDIO_RAW : jfVar.b;
        int i3 = this.f;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str2);
        mediaFormat.setInteger("channel-count", bsVar.s);
        mediaFormat.setInteger("sample-rate", bsVar.t);
        ho.a(mediaFormat, bsVar.j);
        ho.a(mediaFormat, "max-input-size", i3);
        if (vf.a >= 23) {
            mediaFormat.setInteger(Columns.PRIORITY, 0);
            if (f2 != -1.0f) {
                mediaFormat.setFloat("operating-rate", f2);
            }
        }
        mediaCodec.configure(mediaFormat, null, mediaCrypto, 0);
        if (this.g) {
            this.j = mediaFormat;
            this.j.setString("mime", bsVar.h);
            return;
        }
        this.j = null;
    }

    /* access modifiers changed from: protected */
    public final int a(jf jfVar, bs bsVar, bs bsVar2) {
        if (a(jfVar, bsVar2) > this.f || bsVar.v != 0 || bsVar.w != 0 || bsVar2.v != 0 || bsVar2.w != 0) {
            return 0;
        }
        if (jfVar.a(bsVar, bsVar2, true)) {
            return 3;
        }
        return vf.a((Object) bsVar.h, (Object) bsVar2.h) && bsVar.s == bsVar2.s && bsVar.t == bsVar2.t && bsVar.b(bsVar2) ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public final float a(float f2, bs[] bsVarArr) {
        int i2 = -1;
        for (bs bsVar : bsVarArr) {
            int i3 = bsVar.t;
            if (i3 != -1) {
                i2 = Math.max(i2, i3);
            }
        }
        if (i2 == -1) {
            return -1.0f;
        }
        return ((float) i2) * f2;
    }

    /* access modifiers changed from: protected */
    public final void a(String str, long j2, long j3) {
        this.c.a(str, j2, j3);
    }

    /* access modifiers changed from: protected */
    public final void b(bs bsVar) throws aw {
        super.b(bsVar);
        this.c.a(bsVar);
        this.k = MimeTypes.AUDIO_RAW.equals(bsVar.h) ? bsVar.u : 2;
        this.l = bsVar.s;
        this.m = bsVar.v;
        this.n = bsVar.w;
    }

    /* access modifiers changed from: protected */
    public final void a(MediaCodec mediaCodec, MediaFormat mediaFormat) throws aw {
        int i2;
        int[] iArr;
        MediaFormat mediaFormat2 = this.j;
        if (mediaFormat2 != null) {
            int h2 = un.h(mediaFormat2.getString("mime"));
            mediaFormat = this.j;
            i2 = h2;
        } else {
            i2 = this.k;
        }
        int integer = mediaFormat.getInteger("channel-count");
        int integer2 = mediaFormat.getInteger("sample-rate");
        if (this.h && integer == 6) {
            int i3 = this.l;
            if (i3 < 6) {
                int[] iArr2 = new int[i3];
                for (int i4 = 0; i4 < this.l; i4++) {
                    iArr2[i4] = i4;
                }
                iArr = iArr2;
                this.d.a(i2, integer, integer2, 0, iArr, this.m, this.n);
            }
        }
        iArr = null;
        try {
            this.d.a(i2, integer, integer2, 0, iArr, this.m, this.n);
        } catch (du e2) {
            throw aw.a(e2, z());
        }
    }

    /* access modifiers changed from: protected */
    public final void a(boolean z) throws aw {
        super.a(z);
        this.c.a(this.a);
        int i2 = y().b;
        if (i2 != 0) {
            this.d.a(i2);
        } else {
            this.d.g();
        }
    }

    /* access modifiers changed from: protected */
    public final void a(bs[] bsVarArr, long j2) throws aw {
        super.a(bsVarArr, j2);
        if (this.r != -9223372036854775807L) {
            int i2 = this.s;
            long[] jArr = this.e;
            if (i2 == jArr.length) {
                long j3 = jArr[i2 - 1];
                StringBuilder sb = new StringBuilder(67);
                sb.append("Too many stream changes, so dropping change at ");
                sb.append(j3);
                Log.w("MediaCodecAudioRenderer", sb.toString());
            } else {
                this.s = i2 + 1;
            }
            this.e[this.s - 1] = this.r;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(long j2, boolean z) throws aw {
        super.a(j2, z);
        this.d.i();
        this.o = j2;
        this.p = true;
        this.q = true;
        this.r = -9223372036854775807L;
        this.s = 0;
    }

    /* access modifiers changed from: protected */
    public final void t() {
        super.t();
        this.d.a();
    }

    /* access modifiers changed from: protected */
    public final void u() {
        M();
        this.d.h();
        super.u();
    }

    /* access modifiers changed from: protected */
    public final void v() {
        try {
            this.r = -9223372036854775807L;
            this.s = 0;
            this.d.i();
            try {
                super.v();
            } finally {
                this.c.b(this.a);
            }
        } catch (Throwable th) {
            super.v();
            throw th;
        } finally {
            this.c.b(this.a);
        }
    }

    /* access modifiers changed from: protected */
    public final void w() {
        try {
            super.w();
        } finally {
            this.d.j();
        }
    }

    public final boolean o() {
        return super.o() && this.d.d();
    }

    public final boolean n() {
        return this.d.e() || super.n();
    }

    public final long d() {
        if (f() == 2) {
            M();
        }
        return this.o;
    }

    public final cc a(cc ccVar) {
        return this.d.a(ccVar);
    }

    public final cc e() {
        return this.d.f();
    }

    /* access modifiers changed from: protected */
    public final void a(ex exVar) {
        if (this.p && !exVar.b()) {
            if (Math.abs(exVar.c - this.o) > 500000) {
                this.o = exVar.c;
            }
            this.p = false;
        }
        this.r = Math.max(exVar.c, this.r);
    }

    /* access modifiers changed from: protected */
    public final void c(long j2) {
        while (this.s != 0 && j2 >= this.e[0]) {
            this.d.b();
            this.s--;
            long[] jArr = this.e;
            System.arraycopy(jArr, 1, jArr, 0, this.s);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r1 != -9223372036854775807L) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(long r1, long r3, android.media.MediaCodec r5, java.nio.ByteBuffer r6, int r7, int r8, long r9, boolean r11, com.google.ads.interactivemedia.v3.internal.bs r12) throws com.google.ads.interactivemedia.v3.internal.aw {
        /*
            r0 = this;
            boolean r1 = r0.i
            if (r1 == 0) goto L_0x001a
            r1 = 0
            int r3 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x001a
            r1 = r8 & 4
            if (r1 == 0) goto L_0x001a
            long r1 = r0.r
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r12 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r12 == 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r1 = r9
        L_0x001b:
            boolean r3 = r0.g
            r4 = 0
            r9 = 1
            if (r3 == 0) goto L_0x0029
            r3 = r8 & 2
            if (r3 == 0) goto L_0x0029
            r5.releaseOutputBuffer(r7, r4)
            return r9
        L_0x0029:
            if (r11 == 0) goto L_0x003b
            r5.releaseOutputBuffer(r7, r4)
            com.google.ads.interactivemedia.v3.internal.ew r1 = r0.a
            int r2 = r1.f
            int r2 = r2 + r9
            r1.f = r2
            com.google.ads.interactivemedia.v3.internal.dt r1 = r0.d
            r1.b()
            return r9
        L_0x003b:
            com.google.ads.interactivemedia.v3.internal.dt r3 = r0.d     // Catch:{ dv -> 0x0051, dx -> 0x004f }
            boolean r1 = r3.a(r6, r1)     // Catch:{ dv -> 0x0051, dx -> 0x004f }
            if (r1 == 0) goto L_0x004e
            r5.releaseOutputBuffer(r7, r4)     // Catch:{ dv -> 0x0051, dx -> 0x004f }
            com.google.ads.interactivemedia.v3.internal.ew r1 = r0.a     // Catch:{ dv -> 0x0051, dx -> 0x004f }
            int r2 = r1.e     // Catch:{ dv -> 0x0051, dx -> 0x004f }
            int r2 = r2 + r9
            r1.e = r2     // Catch:{ dv -> 0x0051, dx -> 0x004f }
            return r9
        L_0x004e:
            return r4
        L_0x004f:
            r1 = move-exception
            goto L_0x0052
        L_0x0051:
            r1 = move-exception
        L_0x0052:
            int r2 = r0.z()
            com.google.ads.interactivemedia.v3.internal.aw r1 = com.google.ads.interactivemedia.v3.internal.aw.a(r1, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.en.a(long, long, android.media.MediaCodec, java.nio.ByteBuffer, int, int, long, boolean, com.google.ads.interactivemedia.v3.internal.bs):boolean");
    }

    /* access modifiers changed from: protected */
    public final void E() throws aw {
        try {
            this.d.c();
        } catch (dx e2) {
            throw aw.a(e2, z());
        }
    }

    public final void a(int i2, Object obj) throws aw {
        if (i2 != 5) {
            switch (i2) {
                case 2:
                    this.d.a(((Float) obj).floatValue());
                    return;
                case 3:
                    this.d.a((dc) obj);
                    return;
                default:
                    super.a(i2, obj);
                    return;
            }
        } else {
            this.d.a((ec) obj);
        }
    }

    private final int a(jf jfVar, bs bsVar) {
        if (!"OMX.google.raw.decoder".equals(jfVar.a) || vf.a >= 24 || (vf.a == 23 && vf.c(this.b))) {
            return bsVar.i;
        }
        return -1;
    }

    private final void M() {
        long a = this.d.a(o());
        if (a != Long.MIN_VALUE) {
            if (!this.q) {
                a = Math.max(this.o, a);
            }
            this.o = a;
            this.q = false;
        }
    }
}
