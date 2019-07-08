package com.google.ads.interactivemedia.v3.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCodec.OnFrameRenderedListener;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.Scopes;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import com.myfitnesspal.shared.constants.Constants.Exercise.Cardio;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import java.util.List;

/* compiled from: IMASDK */
public class vo extends jg {
    private static final int[] c = {1920, 1600, Cardio.MINUTES_PERFORMED_DAILY_LIMIT, 1280, 960, 854, AmazonAdTask.DEFAULT_AD_WIDTH, 540, AmazonAdTask.DEFAULT_AD_HEIGHT};
    private static boolean d;
    private static boolean e;
    private int A;
    private float B;
    private int C;
    private int D;
    private int E;
    private float F;
    private int G;
    private int H;
    private int I;
    private float J;
    private boolean K;
    private int L;
    private long M;
    private long N;
    private int O;
    private vp P;
    b b;
    private final Context f;
    private final vq g;
    private final vv h;
    private final long i;
    private final int j;
    private final boolean k;
    private final long[] l;
    private final long[] m;
    private a n;
    private boolean o;
    private Surface p;
    private Surface q;
    private int r;
    private boolean s;
    private long t;
    private long u;
    private long v;
    private int w;
    private int x;
    private int y;
    private long z;

    /* compiled from: IMASDK */
    public static final class a {
        public final int a;
        public final int b;
        public final int c;

        public a(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    @TargetApi(23)
    /* compiled from: IMASDK */
    final class b implements OnFrameRenderedListener {
        private b(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }

        public final void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
            if (this == vo.this.b) {
                vo.this.e(j);
            }
        }

        /* synthetic */ b(vo voVar, MediaCodec mediaCodec, byte b) {
            this(mediaCodec);
        }
    }

    public vo(Context context, ji jiVar, long j2, Handler handler, vu vuVar, int i2) {
        this(context, jiVar, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS, null, false, handler, vuVar, 50);
    }

    private static boolean f(long j2) {
        return j2 < -30000;
    }

    private vo(Context context, ji jiVar, long j2, fe<fg> feVar, boolean z2, Handler handler, vu vuVar, int i2) {
        super(2, jiVar, null, false, 30.0f);
        this.i = j2;
        this.j = i2;
        this.f = context.getApplicationContext();
        this.g = new vq(this.f);
        this.h = new vv(handler, vuVar);
        this.k = "NVIDIA".equals(vf.c);
        this.l = new long[10];
        this.m = new long[10];
        this.N = -9223372036854775807L;
        this.M = -9223372036854775807L;
        this.u = -9223372036854775807L;
        this.C = -1;
        this.D = -1;
        this.F = -1.0f;
        this.B = -1.0f;
        this.r = 1;
        C();
    }

    /* access modifiers changed from: protected */
    public final int a(ji jiVar, fe<fg> feVar, bs bsVar) throws jm {
        boolean z2;
        int i2 = 0;
        if (!un.b(bsVar.h)) {
            return 0;
        }
        fa faVar = bsVar.k;
        if (faVar != null) {
            z2 = false;
            for (int i3 = 0; i3 < faVar.b; i3++) {
                z2 |= faVar.a(i3).b;
            }
        } else {
            z2 = false;
        }
        List a2 = jiVar.a(bsVar.h, z2);
        if (a2.isEmpty()) {
            if (!z2 || jiVar.a(bsVar.h, false).isEmpty()) {
                return 1;
            }
            return 2;
        } else if (!cj.a(feVar, faVar)) {
            return 2;
        } else {
            jf jfVar = (jf) a2.get(0);
            boolean a3 = jfVar.a(bsVar);
            int i4 = jfVar.b(bsVar) ? 16 : 8;
            if (jfVar.c) {
                i2 = 32;
            }
            return (a3 ? 4 : 3) | i4 | i2;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(boolean z2) throws aw {
        super.a(z2);
        int i2 = this.L;
        this.L = y().b;
        this.K = this.L != 0;
        if (this.L != i2) {
            J();
        }
        this.h.a(this.a);
        this.g.a();
    }

    /* access modifiers changed from: protected */
    public final void a(bs[] bsVarArr, long j2) throws aw {
        if (this.N == -9223372036854775807L) {
            this.N = j2;
        } else {
            int i2 = this.O;
            long[] jArr = this.l;
            if (i2 == jArr.length) {
                long j3 = jArr[i2 - 1];
                StringBuilder sb = new StringBuilder(65);
                sb.append("Too many stream changes, so dropping offset: ");
                sb.append(j3);
                Log.w("MediaCodecVideoRenderer", sb.toString());
            } else {
                this.O = i2 + 1;
            }
            long[] jArr2 = this.l;
            int i3 = this.O;
            jArr2[i3 - 1] = j2;
            this.m[i3 - 1] = this.M;
        }
        super.a(bsVarArr, j2);
    }

    /* access modifiers changed from: protected */
    public final void a(long j2, boolean z2) throws aw {
        super.a(j2, z2);
        e();
        this.t = -9223372036854775807L;
        this.x = 0;
        this.M = -9223372036854775807L;
        int i2 = this.O;
        if (i2 != 0) {
            this.N = this.l[i2 - 1];
            this.O = 0;
        }
        if (z2) {
            d();
        } else {
            this.u = -9223372036854775807L;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r9.K != false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        if (r9.p == r0) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean n() {
        /*
            r9 = this;
            boolean r0 = super.n()
            r1 = 1
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 == 0) goto L_0x0025
            boolean r0 = r9.s
            if (r0 != 0) goto L_0x0022
            android.view.Surface r0 = r9.q
            if (r0 == 0) goto L_0x0018
            android.view.Surface r4 = r9.p
            if (r4 == r0) goto L_0x0022
        L_0x0018:
            android.media.MediaCodec r0 = r9.H()
            if (r0 == 0) goto L_0x0022
            boolean r0 = r9.K
            if (r0 == 0) goto L_0x0025
        L_0x0022:
            r9.u = r2
            return r1
        L_0x0025:
            long r4 = r9.u
            r0 = 0
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x002d
            return r0
        L_0x002d:
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r6 = r9.u
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0038
            return r1
        L_0x0038:
            r9.u = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.vo.n():boolean");
    }

    /* access modifiers changed from: protected */
    public final void t() {
        super.t();
        this.w = 0;
        this.v = SystemClock.elapsedRealtime();
        this.z = SystemClock.elapsedRealtime() * 1000;
    }

    /* access modifiers changed from: protected */
    public final void u() {
        this.u = -9223372036854775807L;
        N();
        super.u();
    }

    /* access modifiers changed from: protected */
    public final void v() {
        this.M = -9223372036854775807L;
        this.N = -9223372036854775807L;
        this.O = 0;
        C();
        e();
        this.g.b();
        this.b = null;
        try {
            super.v();
        } finally {
            this.h.b(this.a);
        }
    }

    /* access modifiers changed from: protected */
    public final void w() {
        try {
            super.w();
        } finally {
            Surface surface = this.q;
            if (surface != null) {
                if (this.p == surface) {
                    this.p = null;
                }
                this.q.release();
                this.q = null;
            }
        }
    }

    public final void a(int i2, Object obj) throws aw {
        if (i2 == 1) {
            Surface surface = (Surface) obj;
            if (surface == null) {
                Surface surface2 = this.q;
                if (surface2 != null) {
                    surface = surface2;
                } else {
                    jf I2 = I();
                    if (I2 != null && b(I2)) {
                        this.q = vl.a(this.f, I2.d);
                        surface = this.q;
                    }
                }
            }
            if (this.p != surface) {
                this.p = surface;
                int f2 = f();
                MediaCodec H2 = H();
                if (H2 != null) {
                    if (vf.a < 23 || surface == null || this.o) {
                        J();
                        F();
                    } else {
                        H2.setOutputSurface(surface);
                    }
                }
                if (surface == null || surface == this.q) {
                    C();
                    e();
                } else {
                    M();
                    e();
                    if (f2 == 2) {
                        d();
                    }
                }
            } else if (!(surface == null || surface == this.q)) {
                M();
                if (this.s) {
                    this.h.a(this.p);
                }
            }
        } else if (i2 == 4) {
            this.r = ((Integer) obj).intValue();
            MediaCodec H3 = H();
            if (H3 != null) {
                H3.setVideoScalingMode(this.r);
            }
        } else if (i2 == 6) {
            this.P = (vp) obj;
        } else {
            super.a(i2, obj);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean a(jf jfVar) {
        return this.p != null || b(jfVar);
    }

    /* access modifiers changed from: protected */
    public final boolean G() {
        return this.K;
    }

    /* access modifiers changed from: protected */
    public final void a(jf jfVar, MediaCodec mediaCodec, bs bsVar, MediaCrypto mediaCrypto, float f2) throws jm {
        a aVar;
        boolean z2;
        Point point;
        jf jfVar2 = jfVar;
        MediaCodec mediaCodec2 = mediaCodec;
        bs bsVar2 = bsVar;
        float f3 = f2;
        bs[] x2 = x();
        int i2 = bsVar2.m;
        int i3 = bsVar2.n;
        int a2 = a(jfVar2, bsVar2);
        if (x2.length == 1) {
            if (a2 != -1) {
                int a3 = a(jfVar2, bsVar2.h, bsVar2.m, bsVar2.n);
                if (a3 != -1) {
                    a2 = Math.min((int) (((float) a2) * 1.5f), a3);
                }
            }
            aVar = new a(i2, i3, a2);
        } else {
            int i4 = i3;
            int i5 = a2;
            boolean z3 = false;
            int i6 = i2;
            for (bs bsVar3 : x2) {
                if (jfVar2.a(bsVar2, bsVar3, false)) {
                    z3 |= bsVar3.m == -1 || bsVar3.n == -1;
                    i6 = Math.max(i6, bsVar3.m);
                    int max = Math.max(i4, bsVar3.n);
                    i5 = Math.max(i5, a(jfVar2, bsVar3));
                    i4 = max;
                }
            }
            if (z3) {
                StringBuilder sb = new StringBuilder(66);
                sb.append("Resolutions unknown. Codec max resolution: ");
                sb.append(i6);
                sb.append(AvidJSONUtil.KEY_X);
                sb.append(i4);
                Log.w("MediaCodecVideoRenderer", sb.toString());
                boolean z4 = bsVar2.n > bsVar2.m;
                int i7 = z4 ? bsVar2.n : bsVar2.m;
                int i8 = z4 ? bsVar2.m : bsVar2.n;
                float f4 = ((float) i8) / ((float) i7);
                int[] iArr = c;
                int length = iArr.length;
                int i9 = 0;
                while (true) {
                    if (i9 >= length) {
                        point = null;
                        break;
                    }
                    int i10 = iArr[i9];
                    int[] iArr2 = iArr;
                    int i11 = (int) (((float) i10) * f4);
                    if (i10 <= i7 || i11 <= i8) {
                        point = null;
                    } else {
                        int i12 = i7;
                        int i13 = i8;
                        if (vf.a >= 21) {
                            int i14 = z4 ? i11 : i10;
                            if (z4) {
                                i11 = i10;
                            }
                            Point a4 = jfVar2.a(i14, i11);
                            Point point2 = a4;
                            if (jfVar2.a(a4.x, a4.y, (double) bsVar2.o)) {
                                point = point2;
                                break;
                            }
                        } else {
                            int a5 = vf.a(i10, 16) << 4;
                            int a6 = vf.a(i11, 16) << 4;
                            if (a5 * a6 <= jl.b()) {
                                int i15 = z4 ? a6 : a5;
                                if (z4) {
                                    a6 = a5;
                                }
                                point = new Point(i15, a6);
                            }
                        }
                        i9++;
                        iArr = iArr2;
                        i7 = i12;
                        i8 = i13;
                    }
                }
                point = null;
                if (point != null) {
                    i6 = Math.max(i6, point.x);
                    i4 = Math.max(i4, point.y);
                    i5 = Math.max(i5, a(jfVar2, bsVar2.h, i6, i4));
                    StringBuilder sb2 = new StringBuilder(57);
                    sb2.append("Codec max resolution adjusted to: ");
                    sb2.append(i6);
                    sb2.append(AvidJSONUtil.KEY_X);
                    sb2.append(i4);
                    Log.w("MediaCodecVideoRenderer", sb2.toString());
                }
            }
            aVar = new a(i6, i4, i5);
        }
        this.n = aVar;
        a aVar2 = this.n;
        boolean z5 = this.k;
        int i16 = this.L;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", bsVar2.h);
        mediaFormat.setInteger("width", bsVar2.m);
        mediaFormat.setInteger("height", bsVar2.n);
        ho.a(mediaFormat, bsVar2.j);
        ho.a(mediaFormat, "frame-rate", bsVar2.o);
        ho.a(mediaFormat, "rotation-degrees", bsVar2.p);
        ho.a(mediaFormat, bsVar2.r);
        if ("video/dolby-vision".equals(bsVar2.h)) {
            Pair a7 = jl.a(bsVar2.e);
            if (a7 != null) {
                ho.a(mediaFormat, Scopes.PROFILE, ((Integer) a7.first).intValue());
            }
        }
        mediaFormat.setInteger("max-width", aVar2.a);
        mediaFormat.setInteger("max-height", aVar2.b);
        ho.a(mediaFormat, "max-input-size", aVar2.c);
        if (vf.a >= 23) {
            mediaFormat.setInteger(Columns.PRIORITY, 0);
            if (f3 != -1.0f) {
                mediaFormat.setFloat("operating-rate", f3);
            }
        }
        if (z5) {
            z2 = true;
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        } else {
            z2 = true;
        }
        if (i16 != 0) {
            mediaFormat.setFeatureEnabled("tunneled-playback", z2);
            mediaFormat.setInteger("audio-session-id", i16);
        }
        if (this.p == null) {
            qi.c(b(jfVar));
            if (this.q == null) {
                this.q = vl.a(this.f, jfVar2.d);
            }
            this.p = this.q;
        }
        mediaCodec2.configure(mediaFormat, this.p, mediaCrypto, 0);
        if (vf.a >= 23 && this.K) {
            this.b = new b(this, mediaCodec2, 0);
        }
    }

    /* access modifiers changed from: protected */
    public final int a(jf jfVar, bs bsVar, bs bsVar2) {
        if (!jfVar.a(bsVar, bsVar2, true) || bsVar2.m > this.n.a || bsVar2.n > this.n.b || a(jfVar, bsVar2) > this.n.c) {
            return 0;
        }
        return bsVar.b(bsVar2) ? 3 : 2;
    }

    /* access modifiers changed from: protected */
    public final void J() {
        try {
            super.J();
        } finally {
            this.y = 0;
        }
    }

    /* access modifiers changed from: protected */
    public final boolean L() {
        try {
            return super.L();
        } finally {
            this.y = 0;
        }
    }

    /* access modifiers changed from: protected */
    public final float a(float f2, bs[] bsVarArr) {
        float f3 = -1.0f;
        for (bs bsVar : bsVarArr) {
            float f4 = bsVar.o;
            if (f4 != -1.0f) {
                f3 = Math.max(f3, f4);
            }
        }
        if (f3 == -1.0f) {
            return -1.0f;
        }
        return f3 * f2;
    }

    /* access modifiers changed from: protected */
    public final void a(String str, long j2, long j3) {
        this.h.a(str, j2, j3);
        this.o = a(str);
    }

    /* access modifiers changed from: protected */
    public final void b(bs bsVar) throws aw {
        super.b(bsVar);
        this.h.a(bsVar);
        this.B = bsVar.q;
        this.A = bsVar.p;
    }

    /* access modifiers changed from: protected */
    public final void a(ex exVar) {
        this.y++;
        this.M = Math.max(exVar.c, this.M);
        if (vf.a < 23 && this.K) {
            e(exVar.c);
        }
    }

    /* access modifiers changed from: protected */
    public final void a(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int i2;
        int i3;
        boolean z2 = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z2) {
            i2 = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            i2 = mediaFormat.getInteger("width");
        }
        if (z2) {
            i3 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            i3 = mediaFormat.getInteger("height");
        }
        a(mediaCodec, i2, i3);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0066, code lost:
        if ((f(r10) && r12 - r6.z > 100000) != false) goto L_0x011e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(long r22, long r24, android.media.MediaCodec r26, java.nio.ByteBuffer r27, int r28, int r29, long r30, boolean r32, com.google.ads.interactivemedia.v3.internal.bs r33) throws com.google.ads.interactivemedia.v3.internal.aw {
        /*
            r21 = this;
            r6 = r21
            r0 = r22
            r7 = r26
            r8 = r28
            r2 = r30
            long r4 = r6.t
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r11 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x0017
            r6.t = r0
        L_0x0017:
            long r4 = r6.N
            long r4 = r2 - r4
            r9 = 1
            if (r32 == 0) goto L_0x0022
            r6.a(r7, r8)
            return r9
        L_0x0022:
            long r10 = r2 - r0
            android.view.Surface r12 = r6.p
            android.view.Surface r13 = r6.q
            r14 = 0
            if (r12 != r13) goto L_0x0036
            boolean r0 = f(r10)
            if (r0 == 0) goto L_0x0035
            r6.a(r7, r8)
            return r9
        L_0x0035:
            return r14
        L_0x0036:
            long r12 = android.os.SystemClock.elapsedRealtime()
            r15 = 1000(0x3e8, double:4.94E-321)
            long r12 = r12 * r15
            int r14 = r21.f()
            r9 = 2
            if (r14 != r9) goto L_0x0047
            r9 = 1
            goto L_0x0048
        L_0x0047:
            r9 = 0
        L_0x0048:
            boolean r14 = r6.s
            r15 = 21
            if (r14 == 0) goto L_0x011c
            if (r9 == 0) goto L_0x006a
            r17 = r4
            long r4 = r6.z
            long r4 = r12 - r4
            boolean r14 = f(r10)
            if (r14 == 0) goto L_0x0065
            r19 = 100000(0x186a0, double:4.94066E-319)
            int r14 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r14 <= 0) goto L_0x0065
            r4 = 1
            goto L_0x0066
        L_0x0065:
            r4 = 0
        L_0x0066:
            if (r4 == 0) goto L_0x006c
            goto L_0x011e
        L_0x006a:
            r17 = r4
        L_0x006c:
            if (r9 == 0) goto L_0x011a
            long r4 = r6.t
            int r9 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r9 != 0) goto L_0x0076
            goto L_0x011a
        L_0x0076:
            long r12 = r12 - r24
            long r10 = r10 - r12
            long r4 = java.lang.System.nanoTime()
            r12 = 1000(0x3e8, double:4.94E-321)
            long r10 = r10 * r12
            long r10 = r10 + r4
            com.google.ads.interactivemedia.v3.internal.vq r9 = r6.g
            long r9 = r9.a(r2, r10)
            long r2 = r9 - r4
            long r2 = r2 / r12
            r4 = -500000(0xfffffffffff85ee0, double:NaN)
            int r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r11 >= 0) goto L_0x0094
            r4 = 1
            goto L_0x0095
        L_0x0094:
            r4 = 0
        L_0x0095:
            if (r4 == 0) goto L_0x00b7
            int r0 = r21.b(r22)
            if (r0 != 0) goto L_0x009f
            r0 = 0
            goto L_0x00b1
        L_0x009f:
            com.google.ads.interactivemedia.v3.internal.ew r1 = r6.a
            int r4 = r1.i
            r5 = 1
            int r4 = r4 + r5
            r1.i = r4
            int r1 = r6.y
            int r1 = r1 + r0
            r6.b(r1)
            r21.K()
            r0 = 1
        L_0x00b1:
            if (r0 == 0) goto L_0x00b5
            r0 = 0
            return r0
        L_0x00b5:
            r0 = 0
            goto L_0x00b8
        L_0x00b7:
            r0 = 0
        L_0x00b8:
            boolean r1 = f(r2)
            if (r1 == 0) goto L_0x00ce
            java.lang.String r1 = "dropVideoBuffer"
            com.google.ads.interactivemedia.v3.internal.qi.b(r1)
            r7.releaseOutputBuffer(r8, r0)
            com.google.ads.interactivemedia.v3.internal.qi.e()
            r0 = 1
            r6.b(r0)
            return r0
        L_0x00ce:
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.a
            if (r0 < r15) goto L_0x00ea
            r0 = 50000(0xc350, double:2.47033E-319)
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x00e8
            r0 = r21
            r1 = r17
            r3 = r9
            r5 = r33
            r0.a(r1, r3, r5)
            r6.a(r7, r8, r9)
            r0 = 1
            return r0
        L_0x00e8:
            r0 = 0
            goto L_0x0119
        L_0x00ea:
            r0 = 30000(0x7530, double:1.4822E-319)
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x0118
            r0 = 11000(0x2af8, double:5.4347E-320)
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 <= 0) goto L_0x0109
            r0 = 10000(0x2710, double:4.9407E-320)
            long r2 = r2 - r0
            r0 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r0
            java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x0100 }
            goto L_0x0109
        L_0x0100:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
            r0 = 0
            return r0
        L_0x0109:
            r0 = r21
            r1 = r17
            r3 = r9
            r5 = r33
            r0.a(r1, r3, r5)
            r6.b(r7, r8)
            r0 = 1
            return r0
        L_0x0118:
            r0 = 0
        L_0x0119:
            return r0
        L_0x011a:
            r0 = 0
            return r0
        L_0x011c:
            r17 = r4
        L_0x011e:
            long r9 = java.lang.System.nanoTime()
            r0 = r21
            r1 = r17
            r3 = r9
            r5 = r33
            r0.a(r1, r3, r5)
            int r0 = com.google.ads.interactivemedia.v3.internal.vf.a
            if (r0 < r15) goto L_0x0135
            r6.a(r7, r8, r9)
            r0 = 1
            goto L_0x0139
        L_0x0135:
            r6.b(r7, r8)
            r0 = 1
        L_0x0139:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.vo.a(long, long, android.media.MediaCodec, java.nio.ByteBuffer, int, int, long, boolean, com.google.ads.interactivemedia.v3.internal.bs):boolean");
    }

    private final void a(MediaCodec mediaCodec, int i2, int i3) {
        this.C = i2;
        this.D = i3;
        this.F = this.B;
        if (vf.a >= 21) {
            int i4 = this.A;
            if (i4 == 90 || i4 == 270) {
                int i5 = this.C;
                this.C = this.D;
                this.D = i5;
                this.F = 1.0f / this.F;
            }
        } else {
            this.E = this.A;
        }
        mediaCodec.setVideoScalingMode(this.r);
    }

    private final void a(long j2, long j3, bs bsVar) {
        vp vpVar = this.P;
        if (vpVar != null) {
            vpVar.a();
        }
    }

    /* access modifiers changed from: protected */
    public final void e(long j2) {
        bs d2 = d(j2);
        if (d2 != null) {
            a(H(), d2.m, d2.n);
        }
        D();
        B();
        c(j2);
    }

    /* access modifiers changed from: protected */
    public final void c(long j2) {
        this.y--;
        while (true) {
            int i2 = this.O;
            if (i2 != 0 && j2 >= this.m[0]) {
                long[] jArr = this.l;
                this.N = jArr[0];
                this.O = i2 - 1;
                System.arraycopy(jArr, 1, jArr, 0, this.O);
                long[] jArr2 = this.m;
                System.arraycopy(jArr2, 1, jArr2, 0, this.O);
            } else {
                return;
            }
        }
    }

    private final void a(MediaCodec mediaCodec, int i2) {
        qi.b("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i2, false);
        qi.e();
        this.a.f++;
    }

    private final void b(int i2) {
        this.a.g += i2;
        this.w += i2;
        this.x += i2;
        this.a.h = Math.max(this.x, this.a.h);
        int i3 = this.j;
        if (i3 > 0 && this.w >= i3) {
            N();
        }
    }

    private final void b(MediaCodec mediaCodec, int i2) {
        D();
        qi.b("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i2, true);
        qi.e();
        this.z = SystemClock.elapsedRealtime() * 1000;
        this.a.e++;
        this.x = 0;
        B();
    }

    @TargetApi(21)
    private final void a(MediaCodec mediaCodec, int i2, long j2) {
        D();
        qi.b("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i2, j2);
        qi.e();
        this.z = SystemClock.elapsedRealtime() * 1000;
        this.a.e++;
        this.x = 0;
        B();
    }

    private final boolean b(jf jfVar) {
        return vf.a >= 23 && !this.K && !a(jfVar.a) && (!jfVar.d || vl.a(this.f));
    }

    private final void d() {
        this.u = this.i > 0 ? SystemClock.elapsedRealtime() + this.i : -9223372036854775807L;
    }

    private final void e() {
        this.s = false;
        if (vf.a >= 23 && this.K) {
            MediaCodec H2 = H();
            if (H2 != null) {
                this.b = new b(this, H2, 0);
            }
        }
    }

    private final void B() {
        if (!this.s) {
            this.s = true;
            this.h.a(this.p);
        }
    }

    private final void C() {
        this.G = -1;
        this.H = -1;
        this.J = -1.0f;
        this.I = -1;
    }

    private final void D() {
        if (this.C != -1 || this.D != -1) {
            if (this.G != this.C || this.H != this.D || this.I != this.E || this.J != this.F) {
                this.h.a(this.C, this.D, this.E, this.F);
                this.G = this.C;
                this.H = this.D;
                this.I = this.E;
                this.J = this.F;
            }
        }
    }

    private final void M() {
        if (this.G != -1 || this.H != -1) {
            this.h.a(this.G, this.H, this.I, this.J);
        }
    }

    private final void N() {
        if (this.w > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.h.a(this.w, elapsedRealtime - this.v);
            this.w = 0;
            this.v = elapsedRealtime;
        }
    }

    private static int a(jf jfVar, bs bsVar) {
        if (bsVar.i == -1) {
            return a(jfVar, bsVar.h, bsVar.m, bsVar.n);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < bsVar.j.size(); i3++) {
            i2 += ((byte[]) bsVar.j.get(i3)).length;
        }
        return bsVar.i + i2;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(com.google.ads.interactivemedia.v3.internal.jf r5, java.lang.String r6, int r7, int r8) {
        /*
            r0 = -1
            if (r7 == r0) goto L_0x00a9
            if (r8 != r0) goto L_0x0007
            goto L_0x00a9
        L_0x0007:
            int r1 = r6.hashCode()
            r2 = 3
            r3 = 4
            r4 = 2
            switch(r1) {
                case -1664118616: goto L_0x0049;
                case -1662541442: goto L_0x003e;
                case 1187890754: goto L_0x0033;
                case 1331836730: goto L_0x0028;
                case 1599127256: goto L_0x001d;
                case 1599127257: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x0054
        L_0x0012:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 5
            goto L_0x0055
        L_0x001d:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 3
            goto L_0x0055
        L_0x0028:
            java.lang.String r1 = "video/avc"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 2
            goto L_0x0055
        L_0x0033:
            java.lang.String r1 = "video/mp4v-es"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 1
            goto L_0x0055
        L_0x003e:
            java.lang.String r1 = "video/hevc"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 4
            goto L_0x0055
        L_0x0049:
            java.lang.String r1 = "video/3gpp"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0054
            r6 = 0
            goto L_0x0055
        L_0x0054:
            r6 = -1
        L_0x0055:
            switch(r6) {
                case 0: goto L_0x00a0;
                case 1: goto L_0x00a0;
                case 2: goto L_0x0060;
                case 3: goto L_0x005c;
                case 4: goto L_0x0059;
                case 5: goto L_0x0059;
                default: goto L_0x0058;
            }
        L_0x0058:
            return r0
        L_0x0059:
            int r7 = r7 * r8
            goto L_0x00a3
        L_0x005c:
            int r7 = r7 * r8
            r3 = 2
            goto L_0x00a3
        L_0x0060:
            java.lang.String r6 = "BRAVIA 4K 2015"
            java.lang.String r1 = com.google.ads.interactivemedia.v3.internal.vf.d
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x009f
            java.lang.String r6 = "Amazon"
            java.lang.String r1 = com.google.ads.interactivemedia.v3.internal.vf.c
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x008d
            java.lang.String r6 = "KFSOWI"
            java.lang.String r1 = com.google.ads.interactivemedia.v3.internal.vf.d
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x009f
            java.lang.String r6 = "AFTS"
            java.lang.String r1 = com.google.ads.interactivemedia.v3.internal.vf.d
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x008d
            boolean r5 = r5.d
            if (r5 == 0) goto L_0x008d
            goto L_0x009f
        L_0x008d:
            r5 = 16
            int r6 = com.google.ads.interactivemedia.v3.internal.vf.a(r7, r5)
            int r5 = com.google.ads.interactivemedia.v3.internal.vf.a(r8, r5)
            int r6 = r6 * r5
            int r5 = r6 << 4
            int r7 = r5 << 4
            r3 = 2
            goto L_0x00a3
        L_0x009f:
            return r0
        L_0x00a0:
            int r7 = r7 * r8
            r3 = 2
        L_0x00a3:
            int r7 = r7 * 3
            int r3 = r3 * 2
            int r7 = r7 / r3
            return r7
        L_0x00a9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.vo.a(com.google.ads.interactivemedia.v3.internal.jf, java.lang.String, int, int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:392:0x0604, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x0605, code lost:
        switch(r1) {
            case 0: goto L_0x0609;
            case 1: goto L_0x0609;
            case 2: goto L_0x0609;
            case 3: goto L_0x0609;
            case 4: goto L_0x0609;
            case 5: goto L_0x0609;
            case 6: goto L_0x0609;
            case 7: goto L_0x0609;
            case 8: goto L_0x0609;
            case 9: goto L_0x0609;
            case 10: goto L_0x0609;
            case 11: goto L_0x0609;
            case 12: goto L_0x0609;
            case 13: goto L_0x0609;
            case 14: goto L_0x0609;
            case 15: goto L_0x0609;
            case 16: goto L_0x0609;
            case 17: goto L_0x0609;
            case 18: goto L_0x0609;
            case 19: goto L_0x0609;
            case 20: goto L_0x0609;
            case 21: goto L_0x0609;
            case 22: goto L_0x0609;
            case 23: goto L_0x0609;
            case 24: goto L_0x0609;
            case 25: goto L_0x0609;
            case 26: goto L_0x0609;
            case 27: goto L_0x0609;
            case 28: goto L_0x0609;
            case 29: goto L_0x0609;
            case 30: goto L_0x0609;
            case 31: goto L_0x0609;
            case 32: goto L_0x0609;
            case 33: goto L_0x0609;
            case 34: goto L_0x0609;
            case 35: goto L_0x0609;
            case 36: goto L_0x0609;
            case 37: goto L_0x0609;
            case 38: goto L_0x0609;
            case 39: goto L_0x0609;
            case 40: goto L_0x0609;
            case 41: goto L_0x0609;
            case 42: goto L_0x0609;
            case 43: goto L_0x0609;
            case 44: goto L_0x0609;
            case 45: goto L_0x0609;
            case 46: goto L_0x0609;
            case 47: goto L_0x0609;
            case 48: goto L_0x0609;
            case 49: goto L_0x0609;
            case 50: goto L_0x0609;
            case 51: goto L_0x0609;
            case 52: goto L_0x0609;
            case 53: goto L_0x0609;
            case 54: goto L_0x0609;
            case 55: goto L_0x0609;
            case 56: goto L_0x0609;
            case 57: goto L_0x0609;
            case 58: goto L_0x0609;
            case 59: goto L_0x0609;
            case 60: goto L_0x0609;
            case 61: goto L_0x0609;
            case 62: goto L_0x0609;
            case 63: goto L_0x0609;
            case 64: goto L_0x0609;
            case 65: goto L_0x0609;
            case 66: goto L_0x0609;
            case 67: goto L_0x0609;
            case 68: goto L_0x0609;
            case 69: goto L_0x0609;
            case 70: goto L_0x0609;
            case 71: goto L_0x0609;
            case 72: goto L_0x0609;
            case 73: goto L_0x0609;
            case 74: goto L_0x0609;
            case 75: goto L_0x0609;
            case 76: goto L_0x0609;
            case 77: goto L_0x0609;
            case 78: goto L_0x0609;
            case 79: goto L_0x0609;
            case 80: goto L_0x0609;
            case 81: goto L_0x0609;
            case 82: goto L_0x0609;
            case 83: goto L_0x0609;
            case 84: goto L_0x0609;
            case 85: goto L_0x0609;
            case 86: goto L_0x0609;
            case 87: goto L_0x0609;
            case 88: goto L_0x0609;
            case 89: goto L_0x0609;
            case 90: goto L_0x0609;
            case 91: goto L_0x0609;
            case 92: goto L_0x0609;
            case 93: goto L_0x0609;
            case 94: goto L_0x0609;
            case 95: goto L_0x0609;
            case 96: goto L_0x0609;
            case 97: goto L_0x0609;
            case 98: goto L_0x0609;
            case 99: goto L_0x0609;
            case 100: goto L_0x0609;
            case 101: goto L_0x0609;
            case 102: goto L_0x0609;
            case 103: goto L_0x0609;
            case 104: goto L_0x0609;
            case 105: goto L_0x0609;
            case 106: goto L_0x0609;
            case 107: goto L_0x0609;
            case 108: goto L_0x0609;
            case 109: goto L_0x0609;
            case 110: goto L_0x0609;
            case 111: goto L_0x0609;
            case 112: goto L_0x0609;
            case 113: goto L_0x0609;
            case 114: goto L_0x0609;
            case 115: goto L_0x0609;
            case 116: goto L_0x0609;
            case 117: goto L_0x0609;
            case 118: goto L_0x0609;
            case 119: goto L_0x0609;
            case 120: goto L_0x0609;
            case com.myfitnesspal.shared.service.syncv1.PacketTypes.RetrieveFriendList :int: goto L_0x0609;
            case 122: goto L_0x0609;
            case 123: goto L_0x0609;
            default: goto L_0x0608;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:395:0x0609, code lost:
        e = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x060b, code lost:
        r1 = com.google.ads.interactivemedia.v3.internal.vf.d;
        r2 = r1.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x0614, code lost:
        if (r2 == 2006354) goto L_0x0626;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:399:0x0619, code lost:
        if (r2 == 2006367) goto L_0x061c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:402:0x0622, code lost:
        if (r1.equals("AFTN") == false) goto L_0x062f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:403:0x0624, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:405:0x062c, code lost:
        if (r1.equals("AFTA") == false) goto L_0x062f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:407:0x062f, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x0630, code lost:
        switch(r0) {
            case 0: goto L_0x0634;
            case 1: goto L_0x0634;
            default: goto L_0x0633;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:410:0x0634, code lost:
        e = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(java.lang.String r6) {
        /*
            java.lang.String r0 = "OMX.google"
            boolean r6 = r6.startsWith(r0)
            r0 = 0
            if (r6 == 0) goto L_0x000a
            return r0
        L_0x000a:
            java.lang.Class<com.google.ads.interactivemedia.v3.internal.vo> r6 = com.google.ads.interactivemedia.v3.internal.vo.class
            monitor-enter(r6)
            boolean r1 = d     // Catch:{ all -> 0x063c }
            if (r1 != 0) goto L_0x0638
            int r1 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ all -> 0x063c }
            r2 = 27
            r3 = 1
            if (r1 > r2) goto L_0x0030
            java.lang.String r1 = "dangal"
            java.lang.String r4 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ all -> 0x063c }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x063c }
            if (r1 != 0) goto L_0x002c
            java.lang.String r1 = "HWEML"
            java.lang.String r4 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ all -> 0x063c }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0030
        L_0x002c:
            e = r3     // Catch:{ all -> 0x063c }
            goto L_0x0636
        L_0x0030:
            int r1 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ all -> 0x063c }
            if (r1 >= r2) goto L_0x0636
            java.lang.String r1 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ all -> 0x063c }
            int r4 = r1.hashCode()     // Catch:{ all -> 0x063c }
            r5 = -1
            switch(r4) {
                case -2144781245: goto L_0x05f9;
                case -2144781185: goto L_0x05ee;
                case -2144781160: goto L_0x05e3;
                case -2097309513: goto L_0x05d8;
                case -2022874474: goto L_0x05cd;
                case -1978993182: goto L_0x05c2;
                case -1978990237: goto L_0x05b7;
                case -1936688988: goto L_0x05ac;
                case -1936688066: goto L_0x05a1;
                case -1936688065: goto L_0x0595;
                case -1931988508: goto L_0x0589;
                case -1696512866: goto L_0x057d;
                case -1680025915: goto L_0x0571;
                case -1615810839: goto L_0x0565;
                case -1554255044: goto L_0x0558;
                case -1481772737: goto L_0x054c;
                case -1481772730: goto L_0x0540;
                case -1481772729: goto L_0x0534;
                case -1320080169: goto L_0x0528;
                case -1217592143: goto L_0x051c;
                case -1180384755: goto L_0x0510;
                case -1139198265: goto L_0x0504;
                case -1052835013: goto L_0x04f8;
                case -993250464: goto L_0x04ed;
                case -965403638: goto L_0x04e1;
                case -958336948: goto L_0x04d5;
                case -879245230: goto L_0x04c9;
                case -842500323: goto L_0x04bd;
                case -821392978: goto L_0x04b2;
                case -797483286: goto L_0x04a6;
                case -794946968: goto L_0x0499;
                case -788334647: goto L_0x048c;
                case -782144577: goto L_0x0480;
                case -575125681: goto L_0x0474;
                case -521118391: goto L_0x0468;
                case -430914369: goto L_0x045c;
                case -290434366: goto L_0x0450;
                case -282781963: goto L_0x0444;
                case -277133239: goto L_0x0438;
                case -173639913: goto L_0x042c;
                case -56598463: goto L_0x041f;
                case 2126: goto L_0x0413;
                case 2564: goto L_0x0407;
                case 2715: goto L_0x03fb;
                case 2719: goto L_0x03ef;
                case 3483: goto L_0x03e3;
                case 73405: goto L_0x03d7;
                case 75739: goto L_0x03cb;
                case 76779: goto L_0x03bf;
                case 78669: goto L_0x03b3;
                case 79305: goto L_0x03a7;
                case 80618: goto L_0x039b;
                case 88274: goto L_0x038f;
                case 98846: goto L_0x0383;
                case 98848: goto L_0x0377;
                case 99329: goto L_0x036b;
                case 101481: goto L_0x035f;
                case 1513190: goto L_0x0354;
                case 1514184: goto L_0x0349;
                case 1514185: goto L_0x033e;
                case 2436959: goto L_0x0332;
                case 2463773: goto L_0x0326;
                case 2464648: goto L_0x031a;
                case 2689555: goto L_0x030e;
                case 3154429: goto L_0x0302;
                case 3284551: goto L_0x02f6;
                case 3351335: goto L_0x02ea;
                case 3386211: goto L_0x02de;
                case 41325051: goto L_0x02d2;
                case 55178625: goto L_0x02c6;
                case 61542055: goto L_0x02bb;
                case 65355429: goto L_0x02af;
                case 66214468: goto L_0x02a3;
                case 66214470: goto L_0x0297;
                case 66214473: goto L_0x028b;
                case 66215429: goto L_0x027f;
                case 66215431: goto L_0x0273;
                case 66215433: goto L_0x0267;
                case 66216390: goto L_0x025b;
                case 76402249: goto L_0x024f;
                case 76404105: goto L_0x0243;
                case 76404911: goto L_0x0237;
                case 80963634: goto L_0x022b;
                case 82882791: goto L_0x021f;
                case 98715550: goto L_0x0213;
                case 102844228: goto L_0x0207;
                case 165221241: goto L_0x01fc;
                case 182191441: goto L_0x01f0;
                case 245388979: goto L_0x01e4;
                case 287431619: goto L_0x01d8;
                case 307593612: goto L_0x01cc;
                case 308517133: goto L_0x01c0;
                case 316215098: goto L_0x01b4;
                case 316215116: goto L_0x01a8;
                case 316246811: goto L_0x019c;
                case 316246818: goto L_0x0190;
                case 407160593: goto L_0x0184;
                case 507412548: goto L_0x0178;
                case 793982701: goto L_0x016c;
                case 794038622: goto L_0x0160;
                case 794040393: goto L_0x0154;
                case 835649806: goto L_0x0148;
                case 917340916: goto L_0x013d;
                case 958008161: goto L_0x0131;
                case 1060579533: goto L_0x0125;
                case 1150207623: goto L_0x0119;
                case 1176899427: goto L_0x010d;
                case 1280332038: goto L_0x0101;
                case 1306947716: goto L_0x00f5;
                case 1349174697: goto L_0x00e9;
                case 1522194893: goto L_0x00dc;
                case 1691543273: goto L_0x00d0;
                case 1709443163: goto L_0x00c4;
                case 1865889110: goto L_0x00b8;
                case 1906253259: goto L_0x00ac;
                case 1977196784: goto L_0x00a0;
                case 2006372676: goto L_0x0094;
                case 2029784656: goto L_0x0088;
                case 2030379515: goto L_0x007c;
                case 2033393791: goto L_0x0070;
                case 2047190025: goto L_0x0064;
                case 2047252157: goto L_0x0058;
                case 2048319463: goto L_0x004c;
                case 2048855701: goto L_0x0040;
                default: goto L_0x003e;
            }     // Catch:{ all -> 0x063c }
        L_0x003e:
            goto L_0x0604
        L_0x0040:
            java.lang.String r2 = "HWWAS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 54
            goto L_0x0605
        L_0x004c:
            java.lang.String r2 = "HWVNS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 53
            goto L_0x0605
        L_0x0058:
            java.lang.String r4 = "ELUGA_Prim"
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 27
            goto L_0x0605
        L_0x0064:
            java.lang.String r2 = "ELUGA_Note"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 26
            goto L_0x0605
        L_0x0070:
            java.lang.String r2 = "ASUS_X00AD_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 11
            goto L_0x0605
        L_0x007c:
            java.lang.String r2 = "HWCAM-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 52
            goto L_0x0605
        L_0x0088:
            java.lang.String r2 = "HWBLN-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 51
            goto L_0x0605
        L_0x0094:
            java.lang.String r2 = "BRAVIA_ATV3_4K"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 15
            goto L_0x0605
        L_0x00a0:
            java.lang.String r2 = "Infinix-X572"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 57
            goto L_0x0605
        L_0x00ac:
            java.lang.String r2 = "PB2-670M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 85
            goto L_0x0605
        L_0x00b8:
            java.lang.String r2 = "santoni"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 101(0x65, float:1.42E-43)
            goto L_0x0605
        L_0x00c4:
            java.lang.String r2 = "iball8735_9806"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 56
            goto L_0x0605
        L_0x00d0:
            java.lang.String r2 = "CPH1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 19
            goto L_0x0605
        L_0x00dc:
            java.lang.String r2 = "woods_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 117(0x75, float:1.64E-43)
            goto L_0x0605
        L_0x00e9:
            java.lang.String r2 = "htc_e56ml_dtul"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 49
            goto L_0x0605
        L_0x00f5:
            java.lang.String r2 = "EverStar_S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 29
            goto L_0x0605
        L_0x0101:
            java.lang.String r2 = "hwALE-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 50
            goto L_0x0605
        L_0x010d:
            java.lang.String r2 = "itel_S41"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 59
            goto L_0x0605
        L_0x0119:
            java.lang.String r2 = "LS-5017"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 65
            goto L_0x0605
        L_0x0125:
            java.lang.String r2 = "panell_d"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 81
            goto L_0x0605
        L_0x0131:
            java.lang.String r2 = "j2xlteins"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 60
            goto L_0x0605
        L_0x013d:
            java.lang.String r2 = "A7000plus"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 7
            goto L_0x0605
        L_0x0148:
            java.lang.String r2 = "manning"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 67
            goto L_0x0605
        L_0x0154:
            java.lang.String r2 = "GIONEE_WBL7519"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 47
            goto L_0x0605
        L_0x0160:
            java.lang.String r2 = "GIONEE_WBL7365"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 46
            goto L_0x0605
        L_0x016c:
            java.lang.String r2 = "GIONEE_WBL5708"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 45
            goto L_0x0605
        L_0x0178:
            java.lang.String r2 = "QM16XE_U"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 99
            goto L_0x0605
        L_0x0184:
            java.lang.String r2 = "Pixi5-10_4G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 91
            goto L_0x0605
        L_0x0190:
            java.lang.String r2 = "TB3-850M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 109(0x6d, float:1.53E-43)
            goto L_0x0605
        L_0x019c:
            java.lang.String r2 = "TB3-850F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 108(0x6c, float:1.51E-43)
            goto L_0x0605
        L_0x01a8:
            java.lang.String r2 = "TB3-730X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x0605
        L_0x01b4:
            java.lang.String r2 = "TB3-730F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 106(0x6a, float:1.49E-43)
            goto L_0x0605
        L_0x01c0:
            java.lang.String r2 = "A7020a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 9
            goto L_0x0605
        L_0x01cc:
            java.lang.String r2 = "A7010a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 8
            goto L_0x0605
        L_0x01d8:
            java.lang.String r2 = "griffin"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 48
            goto L_0x0605
        L_0x01e4:
            java.lang.String r2 = "marino_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 68
            goto L_0x0605
        L_0x01f0:
            java.lang.String r2 = "CPY83_I00"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 20
            goto L_0x0605
        L_0x01fc:
            java.lang.String r2 = "A2016a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 5
            goto L_0x0605
        L_0x0207:
            java.lang.String r2 = "le_x6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 64
            goto L_0x0605
        L_0x0213:
            java.lang.String r2 = "i9031"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 55
            goto L_0x0605
        L_0x021f:
            java.lang.String r2 = "X3_HK"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 119(0x77, float:1.67E-43)
            goto L_0x0605
        L_0x022b:
            java.lang.String r2 = "V23GB"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 112(0x70, float:1.57E-43)
            goto L_0x0605
        L_0x0237:
            java.lang.String r2 = "Q4310"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 97
            goto L_0x0605
        L_0x0243:
            java.lang.String r2 = "Q4260"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 95
            goto L_0x0605
        L_0x024f:
            java.lang.String r2 = "PRO7S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 93
            goto L_0x0605
        L_0x025b:
            java.lang.String r2 = "F3311"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 36
            goto L_0x0605
        L_0x0267:
            java.lang.String r2 = "F3215"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 35
            goto L_0x0605
        L_0x0273:
            java.lang.String r2 = "F3213"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 34
            goto L_0x0605
        L_0x027f:
            java.lang.String r2 = "F3211"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 33
            goto L_0x0605
        L_0x028b:
            java.lang.String r2 = "F3116"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 32
            goto L_0x0605
        L_0x0297:
            java.lang.String r2 = "F3113"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 31
            goto L_0x0605
        L_0x02a3:
            java.lang.String r2 = "F3111"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 30
            goto L_0x0605
        L_0x02af:
            java.lang.String r2 = "E5643"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 24
            goto L_0x0605
        L_0x02bb:
            java.lang.String r2 = "A1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 4
            goto L_0x0605
        L_0x02c6:
            java.lang.String r2 = "Aura_Note_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 12
            goto L_0x0605
        L_0x02d2:
            java.lang.String r2 = "MEIZU_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 69
            goto L_0x0605
        L_0x02de:
            java.lang.String r2 = "p212"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 78
            goto L_0x0605
        L_0x02ea:
            java.lang.String r2 = "mido"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 71
            goto L_0x0605
        L_0x02f6:
            java.lang.String r2 = "kate"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 63
            goto L_0x0605
        L_0x0302:
            java.lang.String r2 = "fugu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 38
            goto L_0x0605
        L_0x030e:
            java.lang.String r2 = "XE2X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x0605
        L_0x031a:
            java.lang.String r2 = "Q427"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 96
            goto L_0x0605
        L_0x0326:
            java.lang.String r2 = "Q350"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 94
            goto L_0x0605
        L_0x0332:
            java.lang.String r2 = "P681"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 79
            goto L_0x0605
        L_0x033e:
            java.lang.String r2 = "1714"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 2
            goto L_0x0605
        L_0x0349:
            java.lang.String r2 = "1713"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 1
            goto L_0x0605
        L_0x0354:
            java.lang.String r2 = "1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 0
            goto L_0x0605
        L_0x035f:
            java.lang.String r2 = "flo"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 37
            goto L_0x0605
        L_0x036b:
            java.lang.String r2 = "deb"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 23
            goto L_0x0605
        L_0x0377:
            java.lang.String r2 = "cv3"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 22
            goto L_0x0605
        L_0x0383:
            java.lang.String r2 = "cv1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 21
            goto L_0x0605
        L_0x038f:
            java.lang.String r2 = "Z80"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 123(0x7b, float:1.72E-43)
            goto L_0x0605
        L_0x039b:
            java.lang.String r2 = "QX1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 100
            goto L_0x0605
        L_0x03a7:
            java.lang.String r2 = "PLE"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 92
            goto L_0x0605
        L_0x03b3:
            java.lang.String r2 = "P85"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 80
            goto L_0x0605
        L_0x03bf:
            java.lang.String r2 = "MX6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 72
            goto L_0x0605
        L_0x03cb:
            java.lang.String r2 = "M5c"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 66
            goto L_0x0605
        L_0x03d7:
            java.lang.String r2 = "JGZ"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 61
            goto L_0x0605
        L_0x03e3:
            java.lang.String r2 = "mh"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 70
            goto L_0x0605
        L_0x03ef:
            java.lang.String r2 = "V5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 113(0x71, float:1.58E-43)
            goto L_0x0605
        L_0x03fb:
            java.lang.String r2 = "V1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 111(0x6f, float:1.56E-43)
            goto L_0x0605
        L_0x0407:
            java.lang.String r2 = "Q5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 98
            goto L_0x0605
        L_0x0413:
            java.lang.String r2 = "C1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 16
            goto L_0x0605
        L_0x041f:
            java.lang.String r2 = "woods_fn"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 118(0x76, float:1.65E-43)
            goto L_0x0605
        L_0x042c:
            java.lang.String r2 = "ELUGA_A3_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 25
            goto L_0x0605
        L_0x0438:
            java.lang.String r2 = "Z12_PRO"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 122(0x7a, float:1.71E-43)
            goto L_0x0605
        L_0x0444:
            java.lang.String r2 = "BLACK-1X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 13
            goto L_0x0605
        L_0x0450:
            java.lang.String r2 = "taido_row"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x0605
        L_0x045c:
            java.lang.String r2 = "Pixi4-7_3G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 90
            goto L_0x0605
        L_0x0468:
            java.lang.String r2 = "GIONEE_GBL7360"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 41
            goto L_0x0605
        L_0x0474:
            java.lang.String r2 = "GiONEE_CBL7513"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 39
            goto L_0x0605
        L_0x0480:
            java.lang.String r2 = "OnePlus5T"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 77
            goto L_0x0605
        L_0x048c:
            java.lang.String r2 = "whyred"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 116(0x74, float:1.63E-43)
            goto L_0x0605
        L_0x0499:
            java.lang.String r2 = "watson"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 115(0x73, float:1.61E-43)
            goto L_0x0605
        L_0x04a6:
            java.lang.String r2 = "SVP-DTV15"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x0605
        L_0x04b2:
            java.lang.String r2 = "A7000-a"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 6
            goto L_0x0605
        L_0x04bd:
            java.lang.String r2 = "nicklaus_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 74
            goto L_0x0605
        L_0x04c9:
            java.lang.String r2 = "tcl_eu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 110(0x6e, float:1.54E-43)
            goto L_0x0605
        L_0x04d5:
            java.lang.String r2 = "ELUGA_Ray_X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 28
            goto L_0x0605
        L_0x04e1:
            java.lang.String r2 = "s905x018"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x0605
        L_0x04ed:
            java.lang.String r2 = "A10-70F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 3
            goto L_0x0605
        L_0x04f8:
            java.lang.String r2 = "namath"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 73
            goto L_0x0605
        L_0x0504:
            java.lang.String r2 = "Slate_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 102(0x66, float:1.43E-43)
            goto L_0x0605
        L_0x0510:
            java.lang.String r2 = "iris60"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 58
            goto L_0x0605
        L_0x051c:
            java.lang.String r2 = "BRAVIA_ATV2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 14
            goto L_0x0605
        L_0x0528:
            java.lang.String r2 = "GiONEE_GBL7319"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 40
            goto L_0x0605
        L_0x0534:
            java.lang.String r2 = "panell_dt"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 84
            goto L_0x0605
        L_0x0540:
            java.lang.String r2 = "panell_ds"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 83
            goto L_0x0605
        L_0x054c:
            java.lang.String r2 = "panell_dl"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 82
            goto L_0x0605
        L_0x0558:
            java.lang.String r2 = "vernee_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 114(0x72, float:1.6E-43)
            goto L_0x0605
        L_0x0565:
            java.lang.String r2 = "Phantom6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 89
            goto L_0x0605
        L_0x0571:
            java.lang.String r2 = "ComioS1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 17
            goto L_0x0605
        L_0x057d:
            java.lang.String r2 = "XT1663"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 121(0x79, float:1.7E-43)
            goto L_0x0605
        L_0x0589:
            java.lang.String r2 = "AquaPowerM"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 10
            goto L_0x0605
        L_0x0595:
            java.lang.String r2 = "PGN611"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 88
            goto L_0x0605
        L_0x05a1:
            java.lang.String r2 = "PGN610"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 87
            goto L_0x0605
        L_0x05ac:
            java.lang.String r2 = "PGN528"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 86
            goto L_0x0605
        L_0x05b7:
            java.lang.String r2 = "NX573J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 76
            goto L_0x0605
        L_0x05c2:
            java.lang.String r2 = "NX541J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 75
            goto L_0x0605
        L_0x05cd:
            java.lang.String r2 = "CP8676_I02"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 18
            goto L_0x0605
        L_0x05d8:
            java.lang.String r2 = "K50a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 62
            goto L_0x0605
        L_0x05e3:
            java.lang.String r2 = "GIONEE_SWW1631"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 44
            goto L_0x0605
        L_0x05ee:
            java.lang.String r2 = "GIONEE_SWW1627"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 43
            goto L_0x0605
        L_0x05f9:
            java.lang.String r2 = "GIONEE_SWW1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x0604
            r1 = 42
            goto L_0x0605
        L_0x0604:
            r1 = -1
        L_0x0605:
            switch(r1) {
                case 0: goto L_0x0609;
                case 1: goto L_0x0609;
                case 2: goto L_0x0609;
                case 3: goto L_0x0609;
                case 4: goto L_0x0609;
                case 5: goto L_0x0609;
                case 6: goto L_0x0609;
                case 7: goto L_0x0609;
                case 8: goto L_0x0609;
                case 9: goto L_0x0609;
                case 10: goto L_0x0609;
                case 11: goto L_0x0609;
                case 12: goto L_0x0609;
                case 13: goto L_0x0609;
                case 14: goto L_0x0609;
                case 15: goto L_0x0609;
                case 16: goto L_0x0609;
                case 17: goto L_0x0609;
                case 18: goto L_0x0609;
                case 19: goto L_0x0609;
                case 20: goto L_0x0609;
                case 21: goto L_0x0609;
                case 22: goto L_0x0609;
                case 23: goto L_0x0609;
                case 24: goto L_0x0609;
                case 25: goto L_0x0609;
                case 26: goto L_0x0609;
                case 27: goto L_0x0609;
                case 28: goto L_0x0609;
                case 29: goto L_0x0609;
                case 30: goto L_0x0609;
                case 31: goto L_0x0609;
                case 32: goto L_0x0609;
                case 33: goto L_0x0609;
                case 34: goto L_0x0609;
                case 35: goto L_0x0609;
                case 36: goto L_0x0609;
                case 37: goto L_0x0609;
                case 38: goto L_0x0609;
                case 39: goto L_0x0609;
                case 40: goto L_0x0609;
                case 41: goto L_0x0609;
                case 42: goto L_0x0609;
                case 43: goto L_0x0609;
                case 44: goto L_0x0609;
                case 45: goto L_0x0609;
                case 46: goto L_0x0609;
                case 47: goto L_0x0609;
                case 48: goto L_0x0609;
                case 49: goto L_0x0609;
                case 50: goto L_0x0609;
                case 51: goto L_0x0609;
                case 52: goto L_0x0609;
                case 53: goto L_0x0609;
                case 54: goto L_0x0609;
                case 55: goto L_0x0609;
                case 56: goto L_0x0609;
                case 57: goto L_0x0609;
                case 58: goto L_0x0609;
                case 59: goto L_0x0609;
                case 60: goto L_0x0609;
                case 61: goto L_0x0609;
                case 62: goto L_0x0609;
                case 63: goto L_0x0609;
                case 64: goto L_0x0609;
                case 65: goto L_0x0609;
                case 66: goto L_0x0609;
                case 67: goto L_0x0609;
                case 68: goto L_0x0609;
                case 69: goto L_0x0609;
                case 70: goto L_0x0609;
                case 71: goto L_0x0609;
                case 72: goto L_0x0609;
                case 73: goto L_0x0609;
                case 74: goto L_0x0609;
                case 75: goto L_0x0609;
                case 76: goto L_0x0609;
                case 77: goto L_0x0609;
                case 78: goto L_0x0609;
                case 79: goto L_0x0609;
                case 80: goto L_0x0609;
                case 81: goto L_0x0609;
                case 82: goto L_0x0609;
                case 83: goto L_0x0609;
                case 84: goto L_0x0609;
                case 85: goto L_0x0609;
                case 86: goto L_0x0609;
                case 87: goto L_0x0609;
                case 88: goto L_0x0609;
                case 89: goto L_0x0609;
                case 90: goto L_0x0609;
                case 91: goto L_0x0609;
                case 92: goto L_0x0609;
                case 93: goto L_0x0609;
                case 94: goto L_0x0609;
                case 95: goto L_0x0609;
                case 96: goto L_0x0609;
                case 97: goto L_0x0609;
                case 98: goto L_0x0609;
                case 99: goto L_0x0609;
                case 100: goto L_0x0609;
                case 101: goto L_0x0609;
                case 102: goto L_0x0609;
                case 103: goto L_0x0609;
                case 104: goto L_0x0609;
                case 105: goto L_0x0609;
                case 106: goto L_0x0609;
                case 107: goto L_0x0609;
                case 108: goto L_0x0609;
                case 109: goto L_0x0609;
                case 110: goto L_0x0609;
                case 111: goto L_0x0609;
                case 112: goto L_0x0609;
                case 113: goto L_0x0609;
                case 114: goto L_0x0609;
                case 115: goto L_0x0609;
                case 116: goto L_0x0609;
                case 117: goto L_0x0609;
                case 118: goto L_0x0609;
                case 119: goto L_0x0609;
                case 120: goto L_0x0609;
                case 121: goto L_0x0609;
                case 122: goto L_0x0609;
                case 123: goto L_0x0609;
                default: goto L_0x0608;
            }     // Catch:{ all -> 0x063c }
        L_0x0608:
            goto L_0x060b
        L_0x0609:
            e = r3     // Catch:{ all -> 0x063c }
        L_0x060b:
            java.lang.String r1 = com.google.ads.interactivemedia.v3.internal.vf.d     // Catch:{ all -> 0x063c }
            int r2 = r1.hashCode()     // Catch:{ all -> 0x063c }
            r4 = 2006354(0x1e9d52, float:2.811501E-39)
            if (r2 == r4) goto L_0x0626
            r0 = 2006367(0x1e9d5f, float:2.811519E-39)
            if (r2 == r0) goto L_0x061c
            goto L_0x062f
        L_0x061c:
            java.lang.String r0 = "AFTN"
            boolean r0 = r1.equals(r0)     // Catch:{ all -> 0x063c }
            if (r0 == 0) goto L_0x062f
            r0 = 1
            goto L_0x0630
        L_0x0626:
            java.lang.String r2 = "AFTA"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x063c }
            if (r1 == 0) goto L_0x062f
            goto L_0x0630
        L_0x062f:
            r0 = -1
        L_0x0630:
            switch(r0) {
                case 0: goto L_0x0634;
                case 1: goto L_0x0634;
                default: goto L_0x0633;
            }     // Catch:{ all -> 0x063c }
        L_0x0633:
            goto L_0x0636
        L_0x0634:
            e = r3     // Catch:{ all -> 0x063c }
        L_0x0636:
            d = r3     // Catch:{ all -> 0x063c }
        L_0x0638:
            monitor-exit(r6)     // Catch:{ all -> 0x063c }
            boolean r6 = e
            return r6
        L_0x063c:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x063c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.vo.a(java.lang.String):boolean");
    }
}
