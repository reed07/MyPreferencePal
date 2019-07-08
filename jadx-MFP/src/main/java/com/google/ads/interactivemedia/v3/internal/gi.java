package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;
import com.google.ads.interactivemedia.v3.internal.fa.a;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.FirebaseError;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

/* compiled from: IMASDK */
public final class gi implements fq {
    private static final byte[] a = {Framer.STDOUT_FRAME_PREFIX, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, Framer.STDIN_FRAME_PREFIX, Framer.STDIN_FRAME_PREFIX, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] b = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    /* access modifiers changed from: private */
    public static final byte[] c = vf.c("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] d = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] e = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    /* access modifiers changed from: private */
    public static final UUID f = new UUID(72057594037932032L, -9223371306706625679L);
    private boolean A;
    private int B;
    private long C;
    private boolean D;
    private long E;
    private long F;
    private long G;
    private ul H;
    private ul I;
    private boolean J;
    private int K;
    private long L;
    private long M;
    private int N;
    private int O;
    private int[] P;
    private int Q;
    private int R;
    private int S;
    private int T;
    private boolean U;
    private boolean V;
    private boolean W;
    private boolean X;
    private byte Y;
    private int Z;
    private int aa;
    private int ab;
    private boolean ac;
    private boolean ad;
    private fs ae;
    private final gh g;
    private final gm h;
    private final SparseArray<gj> i;
    private final boolean j;
    private final ut k;
    private final ut l;
    private final ut m;
    private final ut n;
    private final ut o;
    private final ut p;
    private final ut q;
    private final ut r;
    private final ut s;
    private ByteBuffer t;
    private long u;
    private long v;
    private long w;
    private long x;
    private long y;
    private gj z;

    public gi() {
        this(0);
    }

    protected static int a(int i2) {
        switch (i2) {
            case PacketTypes.RetrieveDiaryDayForOtherUser /*131*/:
            case 136:
            case 155:
            case 159:
            case 176:
            case RequestCodes.SHARE_PROGRESS /*179*/:
            case RequestCodes.FACEBOOK_LOGIN /*186*/:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 30321:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case FirebaseError.ERROR_WEAK_PASSWORD /*17026*/:
            case 21358:
            case 2274716:
                return 3;
            case 160:
            case RequestCodes.JOIN_CHALLENGE /*174*/:
            case RequestCodes.FOOD_EDITOR /*183*/:
            case RequestCodes.PROGRESS_STATUS_UPDATE /*187*/:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
            case 30323:
            case 30324:
            case 30325:
                return 5;
            default:
                return 0;
        }
    }

    protected static boolean b(int i2) {
        return i2 == 357149030 || i2 == 524531317 || i2 == 475249515 || i2 == 374648427;
    }

    public final void c() {
    }

    public gi(int i2) {
        this(new gh(), i2);
    }

    private gi(gh ghVar, int i2) {
        this.v = -1;
        this.w = -9223372036854775807L;
        this.x = -9223372036854775807L;
        this.y = -9223372036854775807L;
        this.E = -1;
        this.F = -1;
        this.G = -9223372036854775807L;
        this.g = ghVar;
        this.g.a(new gg(this, 0));
        boolean z2 = true;
        if ((i2 & 1) != 0) {
            z2 = false;
        }
        this.j = z2;
        this.h = new gm();
        this.i = new SparseArray<>();
        this.m = new ut(4);
        this.n = new ut(ByteBuffer.allocate(4).putInt(-1).array());
        this.o = new ut(4);
        this.k = new ut(up.a);
        this.l = new ut(4);
        this.p = new ut();
        this.q = new ut();
        this.r = new ut(8);
        this.s = new ut();
    }

    public final boolean a(fr frVar) throws IOException, InterruptedException {
        return new gl().a(frVar);
    }

    public final void a(fs fsVar) {
        this.ae = fsVar;
    }

    public final void a(long j2, long j3) {
        this.G = -9223372036854775807L;
        this.K = 0;
        this.g.a();
        this.h.a();
        d();
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            gj gjVar = (gj) this.i.valueAt(i2);
            if (gjVar.Q != null) {
                gjVar.Q.a();
            }
        }
    }

    public final int a(fr frVar, fx fxVar) throws IOException, InterruptedException {
        boolean z2;
        this.ac = false;
        boolean z3 = true;
        while (z3 && !this.ac) {
            z3 = this.g.a(frVar);
            if (z3) {
                long c2 = frVar.c();
                if (this.D) {
                    this.F = c2;
                    fxVar.a = this.E;
                    this.D = false;
                    z2 = true;
                } else {
                    if (this.A) {
                        long j2 = this.F;
                        if (j2 != -1) {
                            fxVar.a = j2;
                            this.F = -1;
                            z2 = true;
                        }
                    }
                    z2 = false;
                }
                if (z2) {
                    return 1;
                }
            }
        }
        if (z3) {
            return 0;
        }
        for (int i2 = 0; i2 < this.i.size(); i2++) {
            gj gjVar = (gj) this.i.valueAt(i2);
            if (gjVar.Q != null) {
                gjVar.Q.a(gjVar);
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public final void a(int i2, long j2, long j3) throws ca {
        if (i2 == 160) {
            this.ad = false;
        } else if (i2 == 174) {
            this.z = new gj(0);
        } else if (i2 == 187) {
            this.J = false;
        } else if (i2 == 19899) {
            this.B = -1;
            this.C = -1;
        } else if (i2 != 20533) {
            if (i2 == 21968) {
                this.z.v = true;
            } else if (i2 == 25152) {
            } else {
                if (i2 == 408125543) {
                    long j4 = this.v;
                    if (j4 == -1 || j4 == j2) {
                        this.v = j2;
                        this.u = j3;
                        return;
                    }
                    throw new ca("Multiple Segment elements not supported");
                } else if (i2 == 475249515) {
                    this.H = new ul();
                    this.I = new ul();
                } else if (i2 == 524531317 && !this.A) {
                    if (!this.j || this.E == -1) {
                        this.ae.a(new ga(this.y));
                        this.A = true;
                        return;
                    }
                    this.D = true;
                }
            }
        } else {
            this.z.f = true;
        }
    }

    /* access modifiers changed from: protected */
    public final void c(int i2) throws ca {
        fy fyVar;
        int i3;
        int i4 = 0;
        if (i2 != 160) {
            if (i2 != 174) {
                if (i2 == 19899) {
                    int i5 = this.B;
                    if (i5 != -1) {
                        long j2 = this.C;
                        if (j2 != -1) {
                            if (i5 == 475249515) {
                                this.E = j2;
                                return;
                            }
                        }
                    }
                    throw new ca("Mandatory element SeekID or SeekPosition not found");
                } else if (i2 != 25152) {
                    if (i2 != 28032) {
                        if (i2 == 357149030) {
                            if (this.w == -9223372036854775807L) {
                                this.w = 1000000;
                            }
                            long j3 = this.x;
                            if (j3 != -9223372036854775807L) {
                                this.y = a(j3);
                                return;
                            }
                        } else if (i2 != 374648427) {
                            if (i2 == 475249515 && !this.A) {
                                fs fsVar = this.ae;
                                if (!(this.v == -1 || this.y == -9223372036854775807L)) {
                                    ul ulVar = this.H;
                                    if (!(ulVar == null || ulVar.a() == 0)) {
                                        ul ulVar2 = this.I;
                                        if (ulVar2 != null && ulVar2.a() == this.H.a()) {
                                            int a2 = this.H.a();
                                            int[] iArr = new int[a2];
                                            long[] jArr = new long[a2];
                                            long[] jArr2 = new long[a2];
                                            long[] jArr3 = new long[a2];
                                            for (int i6 = 0; i6 < a2; i6++) {
                                                jArr3[i6] = this.H.a(i6);
                                                jArr[i6] = this.v + this.I.a(i6);
                                            }
                                            while (true) {
                                                i3 = a2 - 1;
                                                if (i4 >= i3) {
                                                    break;
                                                }
                                                int i7 = i4 + 1;
                                                iArr[i4] = (int) (jArr[i7] - jArr[i4]);
                                                jArr2[i4] = jArr3[i7] - jArr3[i4];
                                                i4 = i7;
                                            }
                                            iArr[i3] = (int) ((this.v + this.u) - jArr[i3]);
                                            jArr2[i3] = this.y - jArr3[i3];
                                            this.H = null;
                                            this.I = null;
                                            fyVar = new fn(iArr, jArr, jArr2, jArr3);
                                            fsVar.a(fyVar);
                                            this.A = true;
                                            return;
                                        }
                                    }
                                }
                                this.H = null;
                                this.I = null;
                                fyVar = new ga(this.y);
                                fsVar.a(fyVar);
                                this.A = true;
                                return;
                            }
                        } else if (this.i.size() != 0) {
                            this.ae.a();
                        } else {
                            throw new ca("No valid tracks were found");
                        }
                    } else if (this.z.f && this.z.g != null) {
                        throw new ca("Combining encryption and compression is not supported");
                    }
                } else if (this.z.f) {
                    if (this.z.h != null) {
                        this.z.j = new fa(new a(at.a, MimeTypes.VIDEO_WEBM, this.z.h.b));
                        return;
                    }
                    throw new ca("Encrypted Track found but ContentEncKeyID was not found");
                }
                return;
            }
            String str = this.z.b;
            if ("V_VP8".equals(str) || "V_VP9".equals(str) || "V_AV1".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_TEXT/ASS".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str)) {
                i4 = 1;
            }
            if (i4 != 0) {
                gj gjVar = this.z;
                gjVar.a(this.ae, gjVar.c);
                this.i.put(this.z.c, this.z);
            }
            this.z = null;
        } else if (this.K == 2) {
            if (!this.ad) {
                this.S |= 1;
            }
            a((gj) this.i.get(this.Q), this.L);
            this.K = 0;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(int i2, long j2) throws ca {
        boolean z2 = false;
        switch (i2) {
            case PacketTypes.RetrieveDiaryDayForOtherUser /*131*/:
                this.z.d = (int) j2;
                return;
            case 136:
                gj gjVar = this.z;
                if (j2 == 1) {
                    z2 = true;
                }
                gjVar.S = z2;
                return;
            case 155:
                this.M = a(j2);
                return;
            case 159:
                this.z.L = (int) j2;
                return;
            case 176:
                this.z.k = (int) j2;
                return;
            case RequestCodes.SHARE_PROGRESS /*179*/:
                this.H.a(a(j2));
                return;
            case RequestCodes.FACEBOOK_LOGIN /*186*/:
                this.z.l = (int) j2;
                return;
            case 215:
                this.z.c = (int) j2;
                return;
            case 231:
                this.G = a(j2);
                return;
            case 241:
                if (!this.J) {
                    this.I.a(j2);
                    this.J = true;
                    return;
                }
                break;
            case 251:
                this.ad = true;
                return;
            case 16980:
                if (j2 != 3) {
                    StringBuilder sb = new StringBuilder(50);
                    sb.append("ContentCompAlgo ");
                    sb.append(j2);
                    sb.append(" not supported");
                    throw new ca(sb.toString());
                }
                break;
            case 17029:
                if (j2 < 1 || j2 > 2) {
                    StringBuilder sb2 = new StringBuilder(53);
                    sb2.append("DocTypeReadVersion ");
                    sb2.append(j2);
                    sb2.append(" not supported");
                    throw new ca(sb2.toString());
                }
            case 17143:
                if (j2 != 1) {
                    StringBuilder sb3 = new StringBuilder(50);
                    sb3.append("EBMLReadVersion ");
                    sb3.append(j2);
                    sb3.append(" not supported");
                    throw new ca(sb3.toString());
                }
                break;
            case 18401:
                if (j2 != 5) {
                    StringBuilder sb4 = new StringBuilder(49);
                    sb4.append("ContentEncAlgo ");
                    sb4.append(j2);
                    sb4.append(" not supported");
                    throw new ca(sb4.toString());
                }
                break;
            case 18408:
                if (j2 != 1) {
                    StringBuilder sb5 = new StringBuilder(56);
                    sb5.append("AESSettingsCipherMode ");
                    sb5.append(j2);
                    sb5.append(" not supported");
                    throw new ca(sb5.toString());
                }
                break;
            case 20529:
                if (j2 != 0) {
                    StringBuilder sb6 = new StringBuilder(55);
                    sb6.append("ContentEncodingOrder ");
                    sb6.append(j2);
                    sb6.append(" not supported");
                    throw new ca(sb6.toString());
                }
                break;
            case 20530:
                if (j2 != 1) {
                    StringBuilder sb7 = new StringBuilder(55);
                    sb7.append("ContentEncodingScope ");
                    sb7.append(j2);
                    sb7.append(" not supported");
                    throw new ca(sb7.toString());
                }
                break;
            case 21420:
                this.C = j2 + this.v;
                return;
            case 21432:
                int i3 = (int) j2;
                if (i3 == 3) {
                    this.z.u = 1;
                    return;
                } else if (i3 != 15) {
                    switch (i3) {
                        case 0:
                            this.z.u = 0;
                            return;
                        case 1:
                            this.z.u = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.z.u = 3;
                    return;
                }
            case 21680:
                this.z.m = (int) j2;
                return;
            case 21682:
                this.z.o = (int) j2;
                return;
            case 21690:
                this.z.n = (int) j2;
                return;
            case 21930:
                gj gjVar2 = this.z;
                if (j2 == 1) {
                    z2 = true;
                }
                gjVar2.R = z2;
                return;
            case 21945:
                switch ((int) j2) {
                    case 1:
                        this.z.y = 2;
                        return;
                    case 2:
                        this.z.y = 1;
                        return;
                    default:
                        return;
                }
            case 21946:
                int i4 = (int) j2;
                if (i4 != 1) {
                    if (i4 == 16) {
                        this.z.x = 6;
                        return;
                    } else if (i4 != 18) {
                        switch (i4) {
                            case 6:
                            case 7:
                                break;
                            default:
                                return;
                        }
                    } else {
                        this.z.x = 7;
                        return;
                    }
                }
                this.z.x = 3;
                return;
            case 21947:
                gj gjVar3 = this.z;
                gjVar3.v = true;
                int i5 = (int) j2;
                if (i5 == 1) {
                    gjVar3.w = 1;
                    return;
                } else if (i5 != 9) {
                    switch (i5) {
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            gjVar3.w = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    gjVar3.w = 6;
                    return;
                }
            case 21948:
                this.z.z = (int) j2;
                return;
            case 21949:
                this.z.A = (int) j2;
                return;
            case 22186:
                this.z.O = j2;
                return;
            case 22203:
                this.z.P = j2;
                return;
            case 25188:
                this.z.M = (int) j2;
                return;
            case 30321:
                switch ((int) j2) {
                    case 0:
                        this.z.p = 0;
                        return;
                    case 1:
                        this.z.p = 1;
                        return;
                    case 2:
                        this.z.p = 2;
                        return;
                    case 3:
                        this.z.p = 3;
                        return;
                }
            case 2352003:
                this.z.e = (int) j2;
                return;
            case 2807729:
                this.w = j2;
                return;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(int i2, double d2) throws ca {
        if (i2 == 181) {
            this.z.N = (int) d2;
        } else if (i2 != 17545) {
            switch (i2) {
                case 21969:
                    this.z.B = (float) d2;
                    return;
                case 21970:
                    this.z.C = (float) d2;
                    return;
                case 21971:
                    this.z.D = (float) d2;
                    return;
                case 21972:
                    this.z.E = (float) d2;
                    return;
                case 21973:
                    this.z.F = (float) d2;
                    return;
                case 21974:
                    this.z.G = (float) d2;
                    return;
                case 21975:
                    this.z.H = (float) d2;
                    return;
                case 21976:
                    this.z.I = (float) d2;
                    return;
                case 21977:
                    this.z.J = (float) d2;
                    return;
                case 21978:
                    this.z.K = (float) d2;
                    return;
                default:
                    switch (i2) {
                        case 30323:
                            this.z.q = (float) d2;
                            return;
                        case 30324:
                            this.z.r = (float) d2;
                            return;
                        case 30325:
                            this.z.s = (float) d2;
                            break;
                    }
                    return;
            }
        } else {
            this.x = (long) d2;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(int i2, String str) throws ca {
        if (i2 != 134) {
            if (i2 != 17026) {
                if (i2 == 21358) {
                    this.z.a = str;
                    return;
                } else if (i2 == 2274716) {
                    this.z.V = str;
                }
            } else if (!"webm".equals(str) && !"matroska".equals(str)) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
                sb.append("DocType ");
                sb.append(str);
                sb.append(" not supported");
                throw new ca(sb.toString());
            }
            return;
        }
        this.z.b = str;
    }

    /* JADX INFO: used method not loaded: com.google.ads.interactivemedia.v3.internal.ca.<init>(java.lang.String):null, types can be incorrect */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0202, code lost:
        throw new com.google.ads.interactivemedia.v3.internal.ca("EBML lacing sample size out of range.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(int r20, int r21, com.google.ads.interactivemedia.v3.internal.fr r22) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = 161(0xa1, float:2.26E-43)
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 4
            r7 = 0
            r8 = 1
            if (r1 == r4) goto L_0x0091
            if (r1 == r5) goto L_0x0091
            r4 = 16981(0x4255, float:2.3795E-41)
            if (r1 == r4) goto L_0x0085
            r4 = 18402(0x47e2, float:2.5787E-41)
            if (r1 == r4) goto L_0x0076
            r4 = 21419(0x53ab, float:3.0014E-41)
            if (r1 == r4) goto L_0x0058
            r4 = 25506(0x63a2, float:3.5742E-41)
            if (r1 == r4) goto L_0x004c
            r4 = 30322(0x7672, float:4.249E-41)
            if (r1 != r4) goto L_0x0033
            com.google.ads.interactivemedia.v3.internal.gj r1 = r0.z
            byte[] r4 = new byte[r2]
            r1.t = r4
            byte[] r1 = r1.t
            r3.b(r1, r7, r2)
            return
        L_0x0033:
            com.google.ads.interactivemedia.v3.internal.ca r2 = new com.google.ads.interactivemedia.v3.internal.ca
            r3 = 26
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Unexpected id: "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1)
            throw r2
        L_0x004c:
            com.google.ads.interactivemedia.v3.internal.gj r1 = r0.z
            byte[] r4 = new byte[r2]
            r1.i = r4
            byte[] r1 = r1.i
            r3.b(r1, r7, r2)
            return
        L_0x0058:
            com.google.ads.interactivemedia.v3.internal.ut r1 = r0.o
            byte[] r1 = r1.a
            java.util.Arrays.fill(r1, r7)
            com.google.ads.interactivemedia.v3.internal.ut r1 = r0.o
            byte[] r1 = r1.a
            int r6 = r6 - r2
            r3.b(r1, r6, r2)
            com.google.ads.interactivemedia.v3.internal.ut r1 = r0.o
            r1.c(r7)
            com.google.ads.interactivemedia.v3.internal.ut r1 = r0.o
            long r1 = r1.j()
            int r2 = (int) r1
            r0.B = r2
            return
        L_0x0076:
            byte[] r1 = new byte[r2]
            r3.b(r1, r7, r2)
            com.google.ads.interactivemedia.v3.internal.gj r2 = r0.z
            com.google.ads.interactivemedia.v3.internal.gd r3 = new com.google.ads.interactivemedia.v3.internal.gd
            r3.<init>(r8, r1, r7, r7)
            r2.h = r3
            return
        L_0x0085:
            com.google.ads.interactivemedia.v3.internal.gj r1 = r0.z
            byte[] r4 = new byte[r2]
            r1.g = r4
            byte[] r1 = r1.g
            r3.b(r1, r7, r2)
            return
        L_0x0091:
            int r4 = r0.K
            r9 = 8
            if (r4 != 0) goto L_0x00b6
            com.google.ads.interactivemedia.v3.internal.gm r4 = r0.h
            long r10 = r4.a(r3, r7, r8, r9)
            int r4 = (int) r10
            r0.Q = r4
            com.google.ads.interactivemedia.v3.internal.gm r4 = r0.h
            int r4 = r4.b()
            r0.R = r4
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0.M = r10
            r0.K = r8
            com.google.ads.interactivemedia.v3.internal.ut r4 = r0.m
            r4.a()
        L_0x00b6:
            android.util.SparseArray<com.google.ads.interactivemedia.v3.internal.gj> r4 = r0.i
            int r10 = r0.Q
            java.lang.Object r4 = r4.get(r10)
            com.google.ads.interactivemedia.v3.internal.gj r4 = (com.google.ads.interactivemedia.v3.internal.gj) r4
            if (r4 != 0) goto L_0x00cc
            int r1 = r0.R
            int r1 = r2 - r1
            r3.b(r1)
            r0.K = r7
            return
        L_0x00cc:
            int r10 = r0.K
            if (r10 != r8) goto L_0x028b
            r10 = 3
            r0.a(r3, r10)
            com.google.ads.interactivemedia.v3.internal.ut r11 = r0.m
            byte[] r11 = r11.a
            r12 = 2
            byte r11 = r11[r12]
            r11 = r11 & 6
            int r11 = r11 >> r8
            r13 = 255(0xff, float:3.57E-43)
            if (r11 != 0) goto L_0x00f6
            r0.O = r8
            int[] r6 = r0.P
            int[] r6 = a(r6, r8)
            r0.P = r6
            int[] r6 = r0.P
            int r11 = r0.R
            int r2 = r2 - r11
            int r2 = r2 - r10
            r6[r7] = r2
            goto L_0x0216
        L_0x00f6:
            if (r1 != r5) goto L_0x0283
            r0.a(r3, r6)
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.m
            byte[] r14 = r14.a
            byte r14 = r14[r10]
            r14 = r14 & r13
            int r14 = r14 + r8
            r0.O = r14
            int[] r14 = r0.P
            int r15 = r0.O
            int[] r14 = a(r14, r15)
            r0.P = r14
            if (r11 != r12) goto L_0x011f
            int r10 = r0.R
            int r2 = r2 - r10
            int r2 = r2 - r6
            int r6 = r0.O
            int r2 = r2 / r6
            int[] r10 = r0.P
            java.util.Arrays.fill(r10, r7, r6, r2)
            goto L_0x0216
        L_0x011f:
            if (r11 != r8) goto L_0x0157
            r6 = 0
            r10 = 4
            r11 = 0
        L_0x0124:
            int r14 = r0.O
            int r15 = r14 + -1
            if (r6 >= r15) goto L_0x014b
            int[] r14 = r0.P
            r14[r6] = r7
        L_0x012e:
            int r10 = r10 + r8
            r0.a(r3, r10)
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.m
            byte[] r14 = r14.a
            int r15 = r10 + -1
            byte r14 = r14[r15]
            r14 = r14 & r13
            int[] r15 = r0.P
            r16 = r15[r6]
            int r16 = r16 + r14
            r15[r6] = r16
            if (r14 == r13) goto L_0x012e
            r14 = r15[r6]
            int r11 = r11 + r14
            int r6 = r6 + 1
            goto L_0x0124
        L_0x014b:
            int[] r6 = r0.P
            int r14 = r14 - r8
            int r15 = r0.R
            int r2 = r2 - r15
            int r2 = r2 - r10
            int r2 = r2 - r11
            r6[r14] = r2
            goto L_0x0216
        L_0x0157:
            if (r11 != r10) goto L_0x026a
            r6 = 0
            r10 = 4
            r11 = 0
        L_0x015c:
            int r14 = r0.O
            int r15 = r14 + -1
            if (r6 >= r15) goto L_0x020b
            int[] r14 = r0.P
            r14[r6] = r7
            int r10 = r10 + 1
            r0.a(r3, r10)
            com.google.ads.interactivemedia.v3.internal.ut r14 = r0.m
            byte[] r14 = r14.a
            int r15 = r10 + -1
            byte r14 = r14[r15]
            if (r14 == 0) goto L_0x0203
            r16 = 0
            r14 = 0
        L_0x0178:
            if (r14 >= r9) goto L_0x01ce
            int r18 = 7 - r14
            int r5 = r8 << r18
            com.google.ads.interactivemedia.v3.internal.ut r12 = r0.m
            byte[] r12 = r12.a
            byte r12 = r12[r15]
            r12 = r12 & r5
            if (r12 == 0) goto L_0x01c4
            int r10 = r10 + r14
            r0.a(r3, r10)
            com.google.ads.interactivemedia.v3.internal.ut r12 = r0.m
            byte[] r12 = r12.a
            int r16 = r15 + 1
            byte r12 = r12[r15]
            r12 = r12 & r13
            int r5 = ~r5
            r5 = r5 & r12
            long r7 = (long) r5
            r5 = r16
            r16 = r7
        L_0x019b:
            if (r5 >= r10) goto L_0x01b1
            long r7 = r16 << r9
            com.google.ads.interactivemedia.v3.internal.ut r15 = r0.m
            byte[] r15 = r15.a
            int r16 = r5 + 1
            byte r5 = r15[r5]
            r5 = r5 & r13
            long r12 = (long) r5
            long r7 = r7 | r12
            r5 = r16
            r13 = 255(0xff, float:3.57E-43)
            r16 = r7
            goto L_0x019b
        L_0x01b1:
            if (r6 <= 0) goto L_0x01c1
            int r14 = r14 * 7
            int r14 = r14 + 6
            r7 = 1
            long r12 = r7 << r14
            long r12 = r12 - r7
            long r16 = r16 - r12
            r7 = r16
            goto L_0x01d0
        L_0x01c1:
            r7 = r16
            goto L_0x01d0
        L_0x01c4:
            int r14 = r14 + 1
            r5 = 163(0xa3, float:2.28E-43)
            r7 = 0
            r8 = 1
            r12 = 2
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x0178
        L_0x01ce:
            r7 = r16
        L_0x01d0:
            r12 = -2147483648(0xffffffff80000000, double:NaN)
            int r5 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r5 < 0) goto L_0x01fb
            r12 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r5 > 0) goto L_0x01fb
            int r5 = (int) r7
            int[] r7 = r0.P
            if (r6 != 0) goto L_0x01e4
            goto L_0x01e9
        L_0x01e4:
            int r8 = r6 + -1
            r8 = r7[r8]
            int r5 = r5 + r8
        L_0x01e9:
            r7[r6] = r5
            int[] r5 = r0.P
            r5 = r5[r6]
            int r11 = r11 + r5
            int r6 = r6 + 1
            r5 = 163(0xa3, float:2.28E-43)
            r7 = 0
            r8 = 1
            r12 = 2
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x015c
        L_0x01fb:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "EBML lacing sample size out of range."
            r1.<init>(r2)
            throw r1
        L_0x0203:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "No valid varint length mask found"
            r1.<init>(r2)
            throw r1
        L_0x020b:
            int[] r5 = r0.P
            r6 = 1
            int r14 = r14 - r6
            int r6 = r0.R
            int r2 = r2 - r6
            int r2 = r2 - r10
            int r2 = r2 - r11
            r5[r14] = r2
        L_0x0216:
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            byte[] r2 = r2.a
            r5 = 0
            byte r2 = r2[r5]
            int r2 = r2 << r9
            com.google.ads.interactivemedia.v3.internal.ut r5 = r0.m
            byte[] r5 = r5.a
            r6 = 1
            byte r5 = r5[r6]
            r6 = 255(0xff, float:3.57E-43)
            r5 = r5 & r6
            r2 = r2 | r5
            long r5 = r0.G
            long r7 = (long) r2
            long r7 = r0.a(r7)
            long r5 = r5 + r7
            r0.L = r5
            com.google.ads.interactivemedia.v3.internal.ut r2 = r0.m
            byte[] r2 = r2.a
            r5 = 2
            byte r2 = r2[r5]
            r2 = r2 & r9
            if (r2 != r9) goto L_0x023f
            r2 = 1
            goto L_0x0240
        L_0x023f:
            r2 = 0
        L_0x0240:
            int r6 = r4.d
            if (r6 == r5) goto L_0x0256
            r6 = 163(0xa3, float:2.28E-43)
            if (r1 != r6) goto L_0x0254
            com.google.ads.interactivemedia.v3.internal.ut r6 = r0.m
            byte[] r6 = r6.a
            byte r6 = r6[r5]
            r5 = 128(0x80, float:1.794E-43)
            r6 = r6 & r5
            if (r6 != r5) goto L_0x0254
            goto L_0x0256
        L_0x0254:
            r5 = 0
            goto L_0x0257
        L_0x0256:
            r5 = 1
        L_0x0257:
            if (r2 == 0) goto L_0x025c
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x025d
        L_0x025c:
            r7 = 0
        L_0x025d:
            r2 = r5 | r7
            r0.S = r2
            r2 = 2
            r0.K = r2
            r2 = 0
            r0.N = r2
            r2 = 163(0xa3, float:2.28E-43)
            goto L_0x028d
        L_0x026a:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            r2 = 36
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Unexpected lacing value: "
            r3.append(r2)
            r3.append(r11)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0283:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Lacing only supported in SimpleBlocks."
            r1.<init>(r2)
            throw r1
        L_0x028b:
            r2 = 163(0xa3, float:2.28E-43)
        L_0x028d:
            if (r1 != r2) goto L_0x02b6
        L_0x028f:
            int r1 = r0.N
            int r2 = r0.O
            if (r1 >= r2) goto L_0x02b2
            int[] r2 = r0.P
            r1 = r2[r1]
            r0.a(r3, r4, r1)
            long r1 = r0.L
            int r5 = r0.N
            int r6 = r4.e
            int r5 = r5 * r6
            int r5 = r5 / 1000
            long r5 = (long) r5
            long r1 = r1 + r5
            r0.a(r4, r1)
            int r1 = r0.N
            r2 = 1
            int r1 = r1 + r2
            r0.N = r1
            goto L_0x028f
        L_0x02b2:
            r1 = 0
            r0.K = r1
            return
        L_0x02b6:
            r1 = 0
            int[] r2 = r0.P
            r1 = r2[r1]
            r0.a(r3, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.gi.a(int, int, com.google.ads.interactivemedia.v3.internal.fr):void");
    }

    private final void a(gj gjVar, long j2) {
        gj gjVar2 = gjVar;
        if (gjVar2.Q != null) {
            gjVar2.Q.a(gjVar2, j2);
        } else {
            long j3 = j2;
            if ("S_TEXT/UTF8".equals(gjVar2.b)) {
                a(gjVar, "%02d:%02d:%02d,%03d", 19, 1000, b);
            } else if ("S_TEXT/ASS".equals(gjVar2.b)) {
                a(gjVar, "%01d:%02d:%02d:%02d", 21, 10000, e);
            }
            gjVar2.T.a(j2, this.S, this.ab, 0, gjVar2.h);
        }
        this.ac = true;
        d();
    }

    private final void d() {
        this.T = 0;
        this.ab = 0;
        this.aa = 0;
        this.U = false;
        this.V = false;
        this.X = false;
        this.Z = 0;
        this.Y = 0;
        this.W = false;
        this.p.a();
    }

    private final void a(fr frVar, int i2) throws IOException, InterruptedException {
        if (this.m.c() < i2) {
            if (this.m.a.length < i2) {
                ut utVar = this.m;
                utVar.a(Arrays.copyOf(utVar.a, Math.max(this.m.a.length << 1, i2)), this.m.c());
            }
            frVar.b(this.m.a, this.m.c(), i2 - this.m.c());
            this.m.b(i2);
        }
    }

    private final void a(fr frVar, gj gjVar, int i2) throws IOException, InterruptedException {
        int i3;
        if ("S_TEXT/UTF8".equals(gjVar.b)) {
            a(frVar, a, i2);
        } else if ("S_TEXT/ASS".equals(gjVar.b)) {
            a(frVar, d, i2);
        } else {
            gc gcVar = gjVar.T;
            boolean z2 = true;
            if (!this.U) {
                if (gjVar.f) {
                    this.S &= -1073741825;
                    int i4 = 128;
                    if (!this.V) {
                        frVar.b(this.m.a, 0, 1);
                        this.T++;
                        if ((this.m.a[0] & 128) != 128) {
                            this.Y = this.m.a[0];
                            this.V = true;
                        } else {
                            throw new ca("Extension bit is set in signal byte");
                        }
                    }
                    if ((this.Y & 1) == 1) {
                        boolean z3 = (this.Y & 2) == 2;
                        this.S |= 1073741824;
                        if (!this.W) {
                            frVar.b(this.r.a, 0, 8);
                            this.T += 8;
                            this.W = true;
                            byte[] bArr = this.m.a;
                            if (!z3) {
                                i4 = 0;
                            }
                            bArr[0] = (byte) (i4 | 8);
                            this.m.c(0);
                            gcVar.a(this.m, 1);
                            this.ab++;
                            this.r.c(0);
                            gcVar.a(this.r, 8);
                            this.ab += 8;
                        }
                        if (z3) {
                            if (!this.X) {
                                frVar.b(this.m.a, 0, 1);
                                this.T++;
                                this.m.c(0);
                                this.Z = this.m.e();
                                this.X = true;
                            }
                            int i5 = this.Z << 2;
                            this.m.a(i5);
                            frVar.b(this.m.a, 0, i5);
                            this.T += i5;
                            short s2 = (short) ((this.Z / 2) + 1);
                            int i6 = (s2 * 6) + 2;
                            ByteBuffer byteBuffer = this.t;
                            if (byteBuffer == null || byteBuffer.capacity() < i6) {
                                this.t = ByteBuffer.allocate(i6);
                            }
                            this.t.position(0);
                            this.t.putShort(s2);
                            int i7 = 0;
                            int i8 = 0;
                            while (true) {
                                i3 = this.Z;
                                if (i7 >= i3) {
                                    break;
                                }
                                int p2 = this.m.p();
                                if (i7 % 2 == 0) {
                                    this.t.putShort((short) (p2 - i8));
                                } else {
                                    this.t.putInt(p2 - i8);
                                }
                                i7++;
                                i8 = p2;
                            }
                            int i9 = (i2 - this.T) - i8;
                            if (i3 % 2 == 1) {
                                this.t.putInt(i9);
                            } else {
                                this.t.putShort((short) i9);
                                this.t.putInt(0);
                            }
                            this.s.a(this.t.array(), i6);
                            gcVar.a(this.s, i6);
                            this.ab += i6;
                        }
                    }
                } else if (gjVar.g != null) {
                    this.p.a(gjVar.g, gjVar.g.length);
                }
                this.U = true;
            }
            int c2 = i2 + this.p.c();
            if (!"V_MPEG4/ISO/AVC".equals(gjVar.b) && !"V_MPEGH/ISO/HEVC".equals(gjVar.b)) {
                if (gjVar.Q != null) {
                    if (this.p.c() != 0) {
                        z2 = false;
                    }
                    qi.c(z2);
                    gjVar.Q.a(frVar, this.S, c2);
                }
                while (true) {
                    int i10 = this.T;
                    if (i10 >= c2) {
                        break;
                    }
                    a(frVar, gcVar, c2 - i10);
                }
            } else {
                byte[] bArr2 = this.l.a;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i11 = gjVar.U;
                int i12 = 4 - gjVar.U;
                while (this.T < c2) {
                    int i13 = this.aa;
                    if (i13 == 0) {
                        int min = Math.min(i11, this.p.b());
                        frVar.b(bArr2, i12 + min, i11 - min);
                        if (min > 0) {
                            this.p.a(bArr2, i12, min);
                        }
                        this.T += i11;
                        this.l.c(0);
                        this.aa = this.l.p();
                        this.k.c(0);
                        gcVar.a(this.k, 4);
                        this.ab += 4;
                    } else {
                        this.aa = i13 - a(frVar, gcVar, i13);
                    }
                }
            }
            if ("A_VORBIS".equals(gjVar.b)) {
                this.n.c(0);
                gcVar.a(this.n, 4);
                this.ab += 4;
            }
        }
    }

    private final void a(fr frVar, byte[] bArr, int i2) throws IOException, InterruptedException {
        int length = bArr.length + i2;
        if (this.q.a.length < length) {
            this.q.a = Arrays.copyOf(bArr, length + i2);
        } else {
            System.arraycopy(bArr, 0, this.q.a, 0, bArr.length);
        }
        frVar.b(this.q.a, bArr.length, i2);
        this.q.a(length);
    }

    private final void a(gj gjVar, String str, int i2, long j2, byte[] bArr) {
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4 = this.q.a;
        long j3 = this.M;
        if (j3 == -9223372036854775807L) {
            bArr3 = bArr;
            bArr2 = bArr3;
        } else {
            int i3 = (int) (j3 / 3600000000L);
            long j4 = j3 - (((long) (i3 * 3600)) * 1000000);
            int i4 = (int) (j4 / 60000000);
            long j5 = j4 - (((long) (i4 * 60)) * 1000000);
            int i5 = (int) (j5 / 1000000);
            int i6 = (int) ((j5 - (((long) i5) * 1000000)) / j2);
            Object[] objArr = {Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6)};
            String str2 = str;
            bArr2 = vf.c(String.format(Locale.US, str, objArr));
            bArr3 = bArr;
        }
        System.arraycopy(bArr2, 0, bArr4, i2, bArr3.length);
        gc gcVar = gjVar.T;
        ut utVar = this.q;
        gcVar.a(utVar, utVar.c());
        this.ab += this.q.c();
    }

    private final int a(fr frVar, gc gcVar, int i2) throws IOException, InterruptedException {
        int i3;
        int b2 = this.p.b();
        if (b2 > 0) {
            i3 = Math.min(i2, b2);
            gcVar.a(this.p, i3);
        } else {
            i3 = gcVar.a(frVar, i2, false);
        }
        this.T += i3;
        this.ab += i3;
        return i3;
    }

    private final long a(long j2) throws ca {
        long j3 = this.w;
        if (j3 != -9223372036854775807L) {
            return vf.c(j2, j3, 1000);
        }
        throw new ca("Can't scale timecode prior to timecodeScale being set.");
    }

    private static int[] a(int[] iArr, int i2) {
        if (iArr == null) {
            return new int[i2];
        }
        if (iArr.length >= i2) {
            return iArr;
        }
        return new int[Math.max(iArr.length << 1, i2)];
    }
}
