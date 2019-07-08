package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.CryptoException;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IMASDK */
public abstract class jg extends cj {
    private static final byte[] b = vf.i("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private int A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    private boolean H;
    private boolean I;
    private boolean J;
    private ByteBuffer[] K;
    private ByteBuffer[] L;
    private long M;
    private int N;
    private int O;
    private ByteBuffer P;
    private boolean Q;
    private boolean R;
    private int S = 0;
    private int T = 0;
    private int U = 0;
    private boolean V;
    private boolean W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    protected ew a;
    private boolean aa;
    private boolean ab;
    private final ji c;
    private final fe<fg> d;
    private final boolean e;
    private final float f;
    private final ex g = new ex(0);
    private final ex h = new ex(0);
    private final bu i = new bu();
    private final vd<bs> j = new vd<>();
    private final ArrayList<Long> k = new ArrayList<>();
    private final BufferInfo l = new BufferInfo();
    private bs m;
    private bs n;
    private fd<fg> o;
    private fd<fg> p;
    private MediaCrypto q;
    private boolean r;
    private long s = -9223372036854775807L;
    private float t = 1.0f;
    private MediaCodec u;
    private bs v;
    private float w = -1.0f;
    private ArrayDeque<jf> x;
    private jh y;
    private jf z;

    public jg(int i2, ji jiVar, fe<fg> feVar, boolean z2, float f2) {
        super(i2);
        this.c = (ji) qi.a(jiVar);
        this.d = feVar;
        this.e = z2;
        this.f = f2;
    }

    /* access modifiers changed from: protected */
    public void E() throws aw {
    }

    /* access modifiers changed from: protected */
    public boolean G() {
        return false;
    }

    /* access modifiers changed from: protected */
    public float a(float f2, bs[] bsVarArr) {
        return -1.0f;
    }

    /* access modifiers changed from: protected */
    public int a(jf jfVar, bs bsVar, bs bsVar2) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public abstract int a(ji jiVar, fe<fg> feVar, bs bsVar) throws jm;

    /* access modifiers changed from: protected */
    public void a(MediaCodec mediaCodec, MediaFormat mediaFormat) throws aw {
    }

    /* access modifiers changed from: protected */
    public void a(ex exVar) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(jf jfVar, MediaCodec mediaCodec, bs bsVar, MediaCrypto mediaCrypto, float f2) throws jm;

    /* access modifiers changed from: protected */
    public void a(String str, long j2, long j3) {
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(long j2, long j3, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i2, int i3, long j4, boolean z2, bs bsVar) throws aw;

    /* access modifiers changed from: protected */
    public boolean a(jf jfVar) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void c(long j2) {
    }

    public final int s() {
        return 8;
    }

    /* access modifiers changed from: protected */
    public void t() {
    }

    /* access modifiers changed from: protected */
    public void u() {
    }

    public final int a(bs bsVar) throws aw {
        try {
            return a(this.c, this.d, bsVar);
        } catch (jm e2) {
            throw aw.a(e2, z());
        }
    }

    /* access modifiers changed from: protected */
    public List<jf> a(ji jiVar, bs bsVar, boolean z2) throws jm {
        return jiVar.a(bsVar.h, z2);
    }

    /* access modifiers changed from: protected */
    public final void F() throws aw {
        boolean z2;
        jf jfVar;
        if (this.u == null && this.m != null) {
            b(this.p);
            String str = this.m.h;
            fd<fg> fdVar = this.o;
            if (fdVar != null) {
                if (this.q == null) {
                    fg c2 = fdVar.c();
                    if (c2 != null) {
                        try {
                            this.q = new MediaCrypto(c2.a, c2.b);
                            this.r = !c2.c && this.q.requiresSecureDecoderComponent(str);
                        } catch (MediaCryptoException e2) {
                            throw aw.a(e2, z());
                        }
                    } else if (this.o.b() == null) {
                        return;
                    }
                }
                if ("Amazon".equals(vf.c) && ("AFTM".equals(vf.d) || "AFTB".equals(vf.d))) {
                    int a2 = this.o.a();
                    if (a2 == 1) {
                        throw aw.a(this.o.b(), z());
                    } else if (a2 != 4) {
                        return;
                    }
                }
            }
            try {
                MediaCrypto mediaCrypto = this.q;
                z2 = this.r;
                if (this.x == null) {
                    List a3 = a(this.c, this.m, z2);
                    if (a3.isEmpty() && z2) {
                        a3 = a(this.c, this.m, false);
                        if (!a3.isEmpty()) {
                            String str2 = this.m.h;
                            String valueOf = String.valueOf(a3);
                            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 99 + String.valueOf(valueOf).length());
                            sb.append("Drm session requires secure decoder for ");
                            sb.append(str2);
                            sb.append(", but no secure decoder available. Trying to proceed with ");
                            sb.append(valueOf);
                            sb.append(".");
                            Log.w("MediaCodecRenderer", sb.toString());
                        }
                    }
                    this.x = new ArrayDeque<>(a3);
                    this.y = null;
                }
                if (!this.x.isEmpty()) {
                    while (true) {
                        if (this.u != null) {
                            this.x = null;
                            break;
                        }
                        jfVar = (jf) this.x.peekFirst();
                        if (!a(jfVar)) {
                            break;
                        }
                        a(jfVar, mediaCrypto);
                    }
                    return;
                }
                throw new jh(this.m, (Throwable) null, z2, -49999);
            } catch (Exception e3) {
                String str3 = "MediaCodecRenderer";
                String valueOf2 = String.valueOf(jfVar);
                StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 30);
                sb2.append("Failed to initialize decoder: ");
                sb2.append(valueOf2);
                uk.a(str3, sb2.toString(), e3);
                this.x.removeFirst();
                jh jhVar = new jh(this.m, (Throwable) e3, z2, jfVar.a);
                if (this.y == null) {
                    this.y = jhVar;
                } else {
                    this.y = this.y.a(jhVar);
                }
                if (this.x.isEmpty()) {
                    throw this.y;
                }
            } catch (jm e4) {
                throw new jh(this.m, (Throwable) e4, z2, -49998);
            } catch (jh e5) {
                throw aw.a(e5, z());
            }
        }
    }

    /* access modifiers changed from: protected */
    public final bs d(long j2) {
        bs bsVar = (bs) this.j.a(j2);
        if (bsVar != null) {
            this.n = bsVar;
        }
        return bsVar;
    }

    /* access modifiers changed from: protected */
    public final MediaCodec H() {
        return this.u;
    }

    /* access modifiers changed from: protected */
    public final jf I() {
        return this.z;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z2) throws aw {
        this.a = new ew();
    }

    /* access modifiers changed from: protected */
    public void a(long j2, boolean z2) throws aw {
        this.X = false;
        this.Y = false;
        K();
        this.j.a();
    }

    public final void a(float f2) throws aw {
        this.t = f2;
        if (this.u != null && this.U != 3 && f() != 0) {
            M();
        }
    }

    /* access modifiers changed from: protected */
    public void v() {
        this.m = null;
        if (this.p == null && this.o == null) {
            L();
        } else {
            w();
        }
    }

    /* access modifiers changed from: protected */
    public void w() {
        try {
            J();
        } finally {
            a(null);
        }
    }

    /* access modifiers changed from: protected */
    public void J() {
        this.x = null;
        this.z = null;
        this.v = null;
        B();
        C();
        d();
        this.Z = false;
        this.M = -9223372036854775807L;
        this.k.clear();
        try {
            if (this.u != null) {
                this.a.b++;
                this.u.stop();
                this.u.release();
            }
            this.u = null;
            try {
                if (this.q != null) {
                    this.q.release();
                }
            } finally {
                this.q = null;
                this.r = false;
                b(null);
            }
        } catch (Throwable th) {
            this.u = null;
            try {
                if (this.q != null) {
                    this.q.release();
                }
                throw th;
            } finally {
                this.q = null;
                this.r = false;
                b(null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(long r6, long r8) throws com.google.ads.interactivemedia.v3.internal.aw {
        /*
            r5 = this;
            boolean r0 = r5.Y
            if (r0 == 0) goto L_0x0008
            r5.E()
            return
        L_0x0008:
            com.google.ads.interactivemedia.v3.internal.bs r0 = r5.m
            r1 = 1
            if (r0 != 0) goto L_0x0014
            boolean r0 = r5.b(r1)
            if (r0 != 0) goto L_0x0014
            return
        L_0x0014:
            r5.F()
            android.media.MediaCodec r0 = r5.u
            r2 = 0
            if (r0 == 0) goto L_0x0051
            long r3 = android.os.SystemClock.elapsedRealtime()
            java.lang.String r0 = "drainAndFeed"
            com.google.ads.interactivemedia.v3.internal.qi.b(r0)
        L_0x0025:
            boolean r0 = r5.b(r6, r8)
            if (r0 != 0) goto L_0x0025
        L_0x002b:
            boolean r6 = r5.D()
            if (r6 == 0) goto L_0x004d
            long r6 = r5.s
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x004a
            long r6 = android.os.SystemClock.elapsedRealtime()
            long r6 = r6 - r3
            long r8 = r5.s
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 >= 0) goto L_0x0048
            goto L_0x004a
        L_0x0048:
            r6 = 0
            goto L_0x004b
        L_0x004a:
            r6 = 1
        L_0x004b:
            if (r6 != 0) goto L_0x002b
        L_0x004d:
            com.google.ads.interactivemedia.v3.internal.qi.e()
            goto L_0x005f
        L_0x0051:
            com.google.ads.interactivemedia.v3.internal.ew r8 = r5.a
            int r9 = r8.d
            int r6 = r5.b(r6)
            int r9 = r9 + r6
            r8.d = r9
            r5.b(r2)
        L_0x005f:
            com.google.ads.interactivemedia.v3.internal.ew r6 = r5.a
            r6.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.jg.a(long, long):void");
    }

    /* access modifiers changed from: protected */
    public final boolean K() throws aw {
        boolean L2 = L();
        if (L2) {
            F();
        }
        return L2;
    }

    /* access modifiers changed from: protected */
    public boolean L() {
        if (this.u == null) {
            return false;
        }
        if (this.U == 3 || this.D || (this.E && this.W)) {
            J();
            return true;
        }
        this.u.flush();
        B();
        C();
        this.M = -9223372036854775807L;
        this.W = false;
        this.V = false;
        this.aa = true;
        this.H = false;
        this.I = false;
        this.Q = false;
        this.Z = false;
        this.k.clear();
        this.T = 0;
        this.U = 0;
        this.S = this.R ? 1 : 0;
        return false;
    }

    private final boolean b(boolean z2) throws aw {
        this.h.a();
        int a2 = a(this.i, this.h, z2);
        if (a2 == -5) {
            b(this.i.a);
            return true;
        }
        if (a2 == -4 && this.h.c()) {
            this.X = true;
            P();
        }
        return false;
    }

    private final void a(jf jfVar, MediaCrypto mediaCrypto) throws Exception {
        float f2;
        String str = jfVar.a;
        if (vf.a < 23) {
            f2 = -1.0f;
        } else {
            f2 = a(this.t, x());
        }
        if (f2 <= this.f) {
            f2 = -1.0f;
        }
        MediaCodec mediaCodec = null;
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            String str2 = "createCodec:";
            String valueOf = String.valueOf(str);
            qi.b(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            MediaCodec createByCodecName = MediaCodec.createByCodecName(str);
            qi.e();
            qi.b("configureCodec");
            a(jfVar, createByCodecName, this.m, mediaCrypto, f2);
            qi.e();
            qi.b("startCodec");
            createByCodecName.start();
            qi.e();
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            if (vf.a < 21) {
                this.K = createByCodecName.getInputBuffers();
                this.L = createByCodecName.getOutputBuffers();
            }
            this.u = createByCodecName;
            this.z = jfVar;
            this.w = f2;
            this.v = this.m;
            int i2 = (vf.a > 25 || !"OMX.Exynos.avc.dec.secure".equals(str) || (!vf.d.startsWith("SM-T585") && !vf.d.startsWith("SM-A510") && !vf.d.startsWith("SM-A520") && !vf.d.startsWith("SM-J700"))) ? (vf.a >= 24 || (!"OMX.Nvidia.h264.decode".equals(str) && !"OMX.Nvidia.h264.decode.secure".equals(str)) || (!"flounder".equals(vf.b) && !"flounder_lte".equals(vf.b) && !"grouper".equals(vf.b) && !"tilapia".equals(vf.b))) ? 0 : 1 : 2;
            this.A = i2;
            this.B = vf.d.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(str);
            this.C = vf.a < 21 && this.v.j.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
            this.D = vf.a < 18 || (vf.a == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (vf.a == 19 && vf.d.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str)));
            this.E = (vf.a <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (vf.a <= 19 && (("hb2000".equals(vf.b) || "stvm8".equals(vf.b)) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str))));
            this.F = vf.a == 21 && "OMX.google.aac.decoder".equals(str);
            this.G = vf.a <= 18 && this.v.s == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str);
            String str3 = jfVar.a;
            this.J = ((vf.a <= 17 && ("OMX.rk.video_decoder.avc".equals(str3) || "OMX.allwinner.video.decoder.avc".equals(str3))) || ("Amazon".equals(vf.c) && "AFTS".equals(vf.d) && jfVar.d)) || G();
            B();
            C();
            this.M = f() == 2 ? SystemClock.elapsedRealtime() + 1000 : -9223372036854775807L;
            this.R = false;
            this.S = 0;
            this.W = false;
            this.V = false;
            this.T = 0;
            this.U = 0;
            this.H = false;
            this.I = false;
            this.Q = false;
            this.aa = true;
            this.a.a++;
            a(str, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
        } catch (Exception e2) {
            if (mediaCodec != null) {
                d();
                mediaCodec.release();
            }
            throw e2;
        }
    }

    private final void d() {
        if (vf.a < 21) {
            this.K = null;
            this.L = null;
        }
    }

    private final boolean e() {
        return this.O >= 0;
    }

    private final void B() {
        this.N = -1;
        this.g.b = null;
    }

    private final void C() {
        this.O = -1;
        this.P = null;
    }

    private final void a(fd<fg> fdVar) {
        fd<fg> fdVar2 = this.p;
        this.p = fdVar;
        c(fdVar2);
    }

    private final void b(fd<fg> fdVar) {
        fd<fg> fdVar2 = this.o;
        this.o = fdVar;
        c(fdVar2);
    }

    private final void c(fd<fg> fdVar) {
        if (fdVar != null && fdVar != this.p && fdVar != this.o) {
            this.d.c();
        }
    }

    private final boolean D() throws aw {
        int i2;
        int i3;
        boolean z2;
        ByteBuffer byteBuffer;
        MediaCodec mediaCodec = this.u;
        if (mediaCodec == null || this.T == 2 || this.X) {
            return false;
        }
        if (this.N < 0) {
            this.N = mediaCodec.dequeueInputBuffer(0);
            int i4 = this.N;
            if (i4 < 0) {
                return false;
            }
            ex exVar = this.g;
            if (vf.a >= 21) {
                byteBuffer = this.u.getInputBuffer(i4);
            } else {
                byteBuffer = this.K[i4];
            }
            exVar.b = byteBuffer;
            this.g.a();
        }
        if (this.T == 1) {
            if (!this.J) {
                this.W = true;
                this.u.queueInputBuffer(this.N, 0, 0, 0, 4);
                B();
            }
            this.T = 2;
            return false;
        } else if (this.H) {
            this.H = false;
            this.g.b.put(b);
            this.u.queueInputBuffer(this.N, 0, b.length, 0, 0);
            B();
            this.V = true;
            return true;
        } else {
            if (this.Z) {
                i3 = -4;
                i2 = 0;
            } else {
                if (this.S == 1) {
                    for (int i5 = 0; i5 < this.v.j.size(); i5++) {
                        this.g.b.put((byte[]) this.v.j.get(i5));
                    }
                    this.S = 2;
                }
                i2 = this.g.b.position();
                i3 = a(this.i, this.g, false);
            }
            if (i3 == -3) {
                return false;
            }
            if (i3 == -5) {
                if (this.S == 2) {
                    this.g.a();
                    this.S = 1;
                }
                b(this.i.a);
                return true;
            } else if (this.g.c()) {
                if (this.S == 2) {
                    this.g.a();
                    this.S = 1;
                }
                this.X = true;
                if (!this.V) {
                    P();
                    return false;
                }
                try {
                    if (!this.J) {
                        this.W = true;
                        this.u.queueInputBuffer(this.N, 0, 0, 0, 4);
                        B();
                    }
                    return false;
                } catch (CryptoException e2) {
                    throw aw.a(e2, z());
                }
            } else if (!this.aa || this.g.d()) {
                this.aa = false;
                boolean f2 = this.g.f();
                if (this.o == null || (!f2 && this.e)) {
                    z2 = false;
                } else {
                    int a2 = this.o.a();
                    if (a2 != 1) {
                        z2 = a2 != 4;
                    } else {
                        throw aw.a(this.o.b(), z());
                    }
                }
                this.Z = z2;
                if (this.Z) {
                    return false;
                }
                if (this.C && !f2) {
                    up.a(this.g.b);
                    if (this.g.b.position() == 0) {
                        return true;
                    }
                    this.C = false;
                }
                try {
                    long j2 = this.g.c;
                    if (this.g.b()) {
                        this.k.add(Long.valueOf(j2));
                    }
                    if (this.ab) {
                        this.j.a(j2, this.m);
                        this.ab = false;
                    }
                    this.g.g();
                    a(this.g);
                    if (f2) {
                        CryptoInfo a3 = this.g.a.a();
                        if (i2 != 0) {
                            if (a3.numBytesOfClearData == null) {
                                a3.numBytesOfClearData = new int[1];
                            }
                            int[] iArr = a3.numBytesOfClearData;
                            iArr[0] = iArr[0] + i2;
                        }
                        this.u.queueSecureInputBuffer(this.N, 0, a3, j2, 0);
                    } else {
                        this.u.queueInputBuffer(this.N, 0, this.g.b.limit(), j2, 0);
                    }
                    B();
                    this.V = true;
                    this.S = 0;
                    this.a.c++;
                    return true;
                } catch (CryptoException e3) {
                    throw aw.a(e3, z());
                }
            } else {
                this.g.a();
                if (this.S == 2) {
                    this.S = 1;
                }
                return true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(bs bsVar) throws aw {
        Object obj;
        bs bsVar2 = this.m;
        this.m = bsVar;
        boolean z2 = true;
        this.ab = true;
        fa faVar = bsVar.k;
        if (bsVar2 == null) {
            obj = null;
        } else {
            obj = bsVar2.k;
        }
        if (!vf.a((Object) faVar, obj)) {
            if (bsVar.k != null) {
                fe<fg> feVar = this.d;
                if (feVar != null) {
                    Looper.myLooper();
                    fa faVar2 = bsVar.k;
                    fd<fg> b2 = feVar.b();
                    if (b2 == this.p || b2 == this.o) {
                        this.d.c();
                    }
                    a(b2);
                } else {
                    throw aw.a(new IllegalStateException("Media requires a DrmSessionManager"), z());
                }
            } else {
                a(null);
            }
        }
        if (this.u == null) {
            F();
        } else if ((this.p != null || this.o == null) && ((this.p == null || this.o != null) && ((this.p == null || this.z.d) && (vf.a >= 23 || this.p == this.o)))) {
            switch (a(this.z, this.v, bsVar)) {
                case 0:
                    O();
                    return;
                case 1:
                    this.v = bsVar;
                    M();
                    if (this.p != this.o) {
                        N();
                        return;
                    }
                    if (this.V) {
                        this.T = 1;
                        this.U = 1;
                    }
                    return;
                case 2:
                    if (this.B) {
                        O();
                        return;
                    }
                    this.R = true;
                    this.S = 1;
                    int i2 = this.A;
                    if (!(i2 == 2 || (i2 == 1 && bsVar.m == this.v.m && bsVar.n == this.v.n))) {
                        z2 = false;
                    }
                    this.H = z2;
                    this.v = bsVar;
                    M();
                    if (this.p != this.o) {
                        N();
                        return;
                    }
                    break;
                case 3:
                    this.v = bsVar;
                    M();
                    if (this.p != this.o) {
                        N();
                        return;
                    }
                    break;
                default:
                    throw new IllegalStateException();
            }
        } else {
            O();
        }
    }

    public boolean o() {
        return this.Y;
    }

    public boolean n() {
        return this.m != null && !this.Z && (A() || e() || (this.M != -9223372036854775807L && SystemClock.elapsedRealtime() < this.M));
    }

    private final void M() throws aw {
        if (vf.a >= 23) {
            float a2 = a(this.t, x());
            float f2 = this.w;
            if (f2 != a2) {
                if (a2 == -1.0f) {
                    O();
                } else if (f2 != -1.0f || a2 > this.f) {
                    Bundle bundle = new Bundle();
                    bundle.putFloat("operating-rate", a2);
                    this.u.setParameters(bundle);
                    this.w = a2;
                }
            }
        }
    }

    private final void N() throws aw {
        if (vf.a < 23) {
            O();
        } else if (this.V) {
            this.T = 1;
            this.U = 2;
        } else {
            R();
        }
    }

    private final void O() throws aw {
        if (this.V) {
            this.T = 1;
            this.U = 3;
            return;
        }
        Q();
    }

    private final boolean b(long j2, long j3) throws aw {
        boolean z2;
        int i2;
        ByteBuffer byteBuffer;
        boolean z3;
        if (!e()) {
            if (!this.F || !this.W) {
                i2 = this.u.dequeueOutputBuffer(this.l, 0);
            } else {
                try {
                    i2 = this.u.dequeueOutputBuffer(this.l, 0);
                } catch (IllegalStateException unused) {
                    P();
                    if (this.Y) {
                        J();
                    }
                    return false;
                }
            }
            if (i2 < 0) {
                if (i2 == -2) {
                    MediaFormat outputFormat = this.u.getOutputFormat();
                    if (this.A != 0 && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
                        this.I = true;
                    } else {
                        if (this.G) {
                            outputFormat.setInteger("channel-count", 1);
                        }
                        a(this.u, outputFormat);
                    }
                    return true;
                } else if (i2 == -3) {
                    if (vf.a < 21) {
                        this.L = this.u.getOutputBuffers();
                    }
                    return true;
                } else {
                    if (this.J && (this.X || this.T == 2)) {
                        P();
                    }
                    return false;
                }
            } else if (this.I) {
                this.I = false;
                this.u.releaseOutputBuffer(i2, false);
                return true;
            } else if (this.l.size != 0 || (this.l.flags & 4) == 0) {
                this.O = i2;
                if (vf.a >= 21) {
                    byteBuffer = this.u.getOutputBuffer(i2);
                } else {
                    byteBuffer = this.L[i2];
                }
                this.P = byteBuffer;
                ByteBuffer byteBuffer2 = this.P;
                if (byteBuffer2 != null) {
                    byteBuffer2.position(this.l.offset);
                    this.P.limit(this.l.offset + this.l.size);
                }
                long j4 = this.l.presentationTimeUs;
                int size = this.k.size();
                int i3 = 0;
                while (true) {
                    if (i3 >= size) {
                        z3 = false;
                        break;
                    } else if (((Long) this.k.get(i3)).longValue() == j4) {
                        this.k.remove(i3);
                        z3 = true;
                        break;
                    } else {
                        i3++;
                    }
                }
                this.Q = z3;
                d(this.l.presentationTimeUs);
            } else {
                P();
                return false;
            }
        }
        if (!this.F || !this.W) {
            z2 = a(j2, j3, this.u, this.P, this.O, this.l.flags, this.l.presentationTimeUs, this.Q, this.n);
        } else {
            try {
                z2 = a(j2, j3, this.u, this.P, this.O, this.l.flags, this.l.presentationTimeUs, this.Q, this.n);
            } catch (IllegalStateException unused2) {
                P();
                if (this.Y) {
                    J();
                }
                return false;
            }
        }
        if (z2) {
            c(this.l.presentationTimeUs);
            boolean z4 = (this.l.flags & 4) != 0;
            C();
            if (!z4) {
                return true;
            }
            P();
        }
        return false;
    }

    private final void P() throws aw {
        switch (this.U) {
            case 1:
                K();
                return;
            case 2:
                R();
                return;
            case 3:
                Q();
                return;
            default:
                this.Y = true;
                E();
                return;
        }
    }

    private final void Q() throws aw {
        J();
        F();
    }

    @TargetApi(23)
    private final void R() throws aw {
        fg c2 = this.p.c();
        if (c2 == null) {
            Q();
        } else if (at.e.equals(c2.a)) {
            Q();
        } else if (!K()) {
            try {
                this.q.setMediaDrmSession(c2.b);
                b(this.p);
                this.T = 0;
                this.U = 0;
            } catch (MediaCryptoException e2) {
                throw aw.a(e2, z());
            }
        }
    }
}
