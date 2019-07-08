package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.Pair;
import com.google.ads.interactivemedia.v3.internal.js.a;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import com.myfitnesspal.shared.service.syncv1.packets.request.AssociateBarcodeWithFoodRequestPacket.ResultCodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
final class gx {
    private static final int a = vf.h("vide");
    private static final int b = vf.h("soun");
    private static final int c = vf.h("text");
    private static final int d = vf.h("sbtl");
    private static final int e = vf.h("subt");
    private static final int f = vf.h("clcp");
    private static final int g = vf.h("meta");
    private static final int h = vf.h("mdta");
    private static final byte[] i = vf.c("OpusHead");

    public static hr a(gv gvVar, gw gwVar, long j, fa faVar, boolean z, boolean z2) throws ca {
        boolean z3;
        long j2;
        long j3;
        gw gwVar2;
        long[] jArr;
        long[] jArr2;
        hr hrVar;
        Pair pair;
        int i2;
        gz gzVar;
        boolean z4;
        Pair pair2;
        int i3;
        int i4;
        hc hcVar;
        int i5;
        long j4;
        fa faVar2;
        gz gzVar2;
        fa faVar3;
        byte[] bArr;
        fa faVar4;
        int i6;
        int i7;
        int i8;
        int i9;
        fa faVar5;
        List list;
        hc hcVar2;
        boolean z5;
        int i10;
        int i11;
        int i12;
        int i13;
        fa faVar6;
        List list2;
        long j5;
        String str;
        gv gvVar2 = gvVar;
        fa faVar7 = faVar;
        gv e2 = gvVar2.e(gu.R);
        int a2 = a(e2.d(gu.ad).be);
        int i14 = a2 == b ? 1 : a2 == a ? 2 : (a2 == c || a2 == d || a2 == e || a2 == f) ? 3 : a2 == g ? 4 : -1;
        if (i14 == -1) {
            return null;
        }
        ut utVar = gvVar2.d(gu.Z).be;
        utVar.c(8);
        int a3 = gu.a(utVar.l());
        utVar.d(a3 == 0 ? 8 : 16);
        int l = utVar.l();
        utVar.d(4);
        int d2 = utVar.d();
        int i15 = a3 == 0 ? 4 : 8;
        int i16 = 0;
        while (true) {
            if (i16 >= i15) {
                z3 = true;
                break;
            } else if (utVar.a[d2 + i16] != -1) {
                z3 = false;
                break;
            } else {
                i16++;
            }
        }
        long j6 = -9223372036854775807L;
        if (z3) {
            utVar.d(i15);
            j2 = -9223372036854775807L;
        } else {
            long j7 = a3 == 0 ? utVar.j() : utVar.q();
            j2 = j7 == 0 ? -9223372036854775807L : j7;
        }
        utVar.d(16);
        int l2 = utVar.l();
        int l3 = utVar.l();
        utVar.d(4);
        int l4 = utVar.l();
        int l5 = utVar.l();
        int i17 = (l2 == 0 && l3 == 65536 && l4 == -65536 && l5 == 0) ? 90 : (l2 == 0 && l3 == -65536 && l4 == 65536 && l5 == 0) ? 270 : (l2 == -65536 && l3 == 0 && l4 == 0 && l5 == -65536) ? 180 : 0;
        hc hcVar3 = new hc(l, j2, i17);
        if (j == -9223372036854775807L) {
            gwVar2 = gwVar;
            j3 = hcVar3.b;
        } else {
            gwVar2 = gwVar;
            j3 = j;
        }
        ut utVar2 = gwVar2.be;
        utVar2.c(8);
        utVar2.d(gu.a(utVar2.l()) == 0 ? 8 : 16);
        long j8 = utVar2.j();
        if (j3 != -9223372036854775807L) {
            j6 = vf.c(j3, 1000000, j8);
        }
        gv e3 = e2.e(gu.S).e(gu.T);
        ut utVar3 = e2.d(gu.ac).be;
        utVar3.c(8);
        int a4 = gu.a(utVar3.l());
        utVar3.d(a4 == 0 ? 8 : 16);
        long j9 = utVar3.j();
        utVar3.d(a4 == 0 ? 4 : 8);
        int f2 = utVar3.f();
        char c2 = (char) (((f2 >> 10) & 31) + 96);
        char c3 = (char) (((f2 >> 5) & 31) + 96);
        char c4 = (char) ((f2 & 31) + 96);
        StringBuilder sb = new StringBuilder(3);
        sb.append(c2);
        sb.append(c3);
        sb.append(c4);
        Pair create = Pair.create(Long.valueOf(j9), sb.toString());
        ut utVar4 = e3.d(gu.ae).be;
        int b2 = hcVar3.a;
        int c5 = hcVar3.c;
        String str2 = (String) create.second;
        utVar4.c(12);
        int l6 = utVar4.l();
        gz gzVar3 = new gz(l6);
        int i18 = 0;
        while (i18 < l6) {
            int d3 = utVar4.d();
            int l7 = utVar4.l();
            if (l7 > 0) {
                gzVar = gzVar3;
                i2 = l6;
                z4 = true;
            } else {
                gzVar = gzVar3;
                i2 = l6;
                z4 = false;
            }
            qi.a(z4, (Object) "childAtomSize should be positive");
            int l8 = utVar4.l();
            if (l8 == gu.b || l8 == gu.c || l8 == gu.ak || l8 == gu.av || l8 == gu.e || l8 == gu.f || l8 == gu.s || l8 == gu.h || l8 == gu.i || l8 == gu.k || l8 == gu.m || l8 == gu.n || l8 == gu.o || l8 == gu.p) {
                pair2 = create;
                hcVar = hcVar3;
                i3 = i18;
                j4 = j8;
                i4 = c5;
                i5 = i14;
                gzVar2 = gzVar;
                utVar4.c(d3 + 8 + 8);
                utVar4.d(16);
                int f3 = utVar4.f();
                int f4 = utVar4.f();
                utVar4.d(50);
                int d4 = utVar4.d();
                if (l8 == gu.ak) {
                    Pair a5 = a(utVar4, d3, l7);
                    if (a5 != null) {
                        l8 = ((Integer) a5.first).intValue();
                        faVar2 = faVar;
                        if (faVar2 == null) {
                            faVar4 = null;
                        } else {
                            faVar4 = faVar2.a(((hs) a5.second).b);
                        }
                        gzVar2.a[i3] = (hs) a5.second;
                    } else {
                        faVar2 = faVar;
                        faVar4 = faVar2;
                    }
                    utVar4.c(d4);
                    faVar3 = faVar4;
                } else {
                    faVar2 = faVar;
                    faVar3 = faVar2;
                }
                boolean z6 = false;
                String str3 = null;
                String str4 = null;
                List list3 = null;
                float f5 = 1.0f;
                byte[] bArr2 = null;
                int i19 = -1;
                while (true) {
                    if (d4 - d3 < l7) {
                        utVar4.c(d4);
                        int d5 = utVar4.d();
                        int l9 = utVar4.l();
                        if (l9 != 0 || utVar4.d() - d3 != l7) {
                            qi.a(l9 > 0, (Object) "childAtomSize should be positive");
                            int l10 = utVar4.l();
                            if (l10 == gu.d) {
                                qi.c(str3 == null);
                                String str5 = MimeTypes.VIDEO_H264;
                                utVar4.c(d5 + 8);
                                vh a6 = vh.a(utVar4);
                                List<byte[]> list4 = a6.a;
                                gzVar2.c = a6.b;
                                if (!z6) {
                                    f5 = a6.c;
                                }
                                str3 = str5;
                                list3 = list4;
                            } else if (l10 == gu.g) {
                                qi.c(str3 == null);
                                String str6 = MimeTypes.VIDEO_H265;
                                utVar4.c(d5 + 8);
                                vn a7 = vn.a(utVar4);
                                List<byte[]> list5 = a7.a;
                                gzVar2.c = a7.b;
                                str3 = str6;
                                list3 = list5;
                            } else if (l10 == gu.q || l10 == gu.r) {
                                vk a8 = vk.a(utVar4);
                                if (a8 != null && a8.a == 5) {
                                    str3 = "video/dolby-vision";
                                    str4 = a8.b;
                                }
                            } else if (l10 == gu.j) {
                                qi.c(str3 == null);
                                str3 = l8 == gu.h ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
                            } else if (l10 == gu.l) {
                                qi.c(str3 == null);
                                str3 = "video/av01";
                            } else if (l10 == gu.t) {
                                qi.c(str3 == null);
                                str3 = MimeTypes.VIDEO_H263;
                            } else if (l10 == gu.U) {
                                qi.c(str3 == null);
                                Pair a9 = a(utVar4, d5);
                                String str7 = (String) a9.first;
                                list3 = Collections.singletonList((byte[]) a9.second);
                                str3 = str7;
                            } else if (l10 == gu.at) {
                                utVar4.c(d5 + 8);
                                f5 = ((float) utVar4.p()) / ((float) utVar4.p());
                                z6 = true;
                            } else if (l10 == gu.aT) {
                                int i20 = d5 + 8;
                                while (true) {
                                    if (i20 - d5 < l9) {
                                        utVar4.c(i20);
                                        int l11 = utVar4.l();
                                        if (utVar4.l() == gu.aU) {
                                            bArr = Arrays.copyOfRange(utVar4.a, i20, l11 + i20);
                                        } else {
                                            i20 += l11;
                                        }
                                    } else {
                                        bArr = null;
                                    }
                                }
                                bArr2 = bArr;
                            } else if (l10 == gu.aS) {
                                int e4 = utVar4.e();
                                utVar4.d(3);
                                if (e4 == 0) {
                                    switch (utVar4.e()) {
                                        case 0:
                                            i19 = 0;
                                            break;
                                        case 1:
                                            i19 = 1;
                                            break;
                                        case 2:
                                            i19 = 2;
                                            break;
                                        case 3:
                                            i19 = 3;
                                            break;
                                    }
                                }
                            }
                            d4 += l9;
                        }
                    }
                }
                if (str3 != null) {
                    gzVar2.b = bs.a(Integer.toString(b2), str3, str4, -1, -1, f3, f4, -1.0f, list3, i4, f5, bArr2, i19, (vi) null, faVar3);
                }
            } else if (l8 == gu.v || l8 == gu.al || l8 == gu.A || l8 == gu.C || l8 == gu.E || l8 == gu.H || l8 == gu.F || l8 == gu.G || l8 == gu.aI || l8 == gu.aJ || l8 == gu.y || l8 == gu.z || l8 == gu.w || l8 == gu.aW || l8 == gu.aX || l8 == gu.aY || l8 == gu.aZ || l8 == gu.bb) {
                j4 = j8;
                gzVar2 = gzVar;
                utVar4.c(d3 + 8 + 8);
                if (z2) {
                    i6 = utVar4.f();
                    utVar4.d(6);
                } else {
                    utVar4.d(8);
                    i6 = 0;
                }
                if (i6 == 0 || i6 == 1) {
                    pair2 = create;
                    int f6 = utVar4.f();
                    utVar4.d(6);
                    int n = utVar4.n();
                    int i21 = f6;
                    if (i6 == 1) {
                        utVar4.d(16);
                    }
                    i7 = n;
                    i8 = i21;
                } else if (i6 == 2) {
                    utVar4.d(16);
                    pair2 = create;
                    i7 = (int) Math.round(Double.longBitsToDouble(utVar4.m()));
                    i8 = utVar4.p();
                    utVar4.d(20);
                } else {
                    pair2 = create;
                    faVar2 = faVar7;
                    hcVar = hcVar3;
                    i3 = i18;
                    i4 = c5;
                    i5 = i14;
                }
                int d6 = utVar4.d();
                int i22 = i8;
                if (l8 == gu.al) {
                    Pair a10 = a(utVar4, d3, l7);
                    if (a10 != null) {
                        l8 = ((Integer) a10.first).intValue();
                        if (faVar7 == null) {
                            i9 = i7;
                            faVar6 = null;
                        } else {
                            i9 = i7;
                            faVar6 = faVar7.a(((hs) a10.second).b);
                        }
                        gzVar2.a[i18] = (hs) a10.second;
                    } else {
                        i9 = i7;
                        faVar6 = faVar7;
                    }
                    utVar4.c(d6);
                    faVar5 = faVar6;
                } else {
                    i9 = i7;
                    faVar5 = faVar7;
                }
                String str8 = l8 == gu.A ? MimeTypes.AUDIO_AC3 : l8 == gu.C ? MimeTypes.AUDIO_E_AC3 : l8 == gu.E ? MimeTypes.AUDIO_DTS : (l8 == gu.F || l8 == gu.G) ? MimeTypes.AUDIO_DTS_HD : l8 == gu.H ? MimeTypes.AUDIO_DTS_EXPRESS : l8 == gu.aI ? MimeTypes.AUDIO_AMR_NB : l8 == gu.aJ ? MimeTypes.AUDIO_AMR_WB : (l8 == gu.y || l8 == gu.z) ? MimeTypes.AUDIO_RAW : l8 == gu.w ? MimeTypes.AUDIO_MPEG : l8 == gu.aW ? MimeTypes.AUDIO_ALAC : l8 == gu.aX ? MimeTypes.AUDIO_ALAW : l8 == gu.aY ? MimeTypes.AUDIO_MLAW : l8 == gu.aZ ? MimeTypes.AUDIO_OPUS : l8 == gu.bb ? MimeTypes.AUDIO_FLAC : null;
                i5 = i14;
                int i23 = i9;
                byte[] bArr3 = null;
                while (d6 - d3 < l7) {
                    utVar4.c(d6);
                    int l12 = utVar4.l();
                    if (l12 > 0) {
                        hcVar2 = hcVar3;
                        z5 = true;
                    } else {
                        hcVar2 = hcVar3;
                        z5 = false;
                    }
                    qi.a(z5, (Object) "childAtomSize should be positive");
                    int l13 = utVar4.l();
                    if (l13 == gu.U || (z2 && l13 == gu.x)) {
                        i10 = i18;
                        i11 = c5;
                        if (l13 == gu.U) {
                            i13 = d6;
                            i12 = -1;
                        } else {
                            i13 = utVar4.d();
                            while (true) {
                                if (i13 - d6 < l12) {
                                    utVar4.c(i13);
                                    int l14 = utVar4.l();
                                    qi.a(l14 > 0, (Object) "childAtomSize should be positive");
                                    if (utVar4.l() == gu.U) {
                                        i12 = -1;
                                    } else {
                                        i13 += l14;
                                    }
                                } else {
                                    i13 = -1;
                                    i12 = -1;
                                }
                            }
                        }
                        if (i13 != i12) {
                            Pair a11 = a(utVar4, i13);
                            str8 = (String) a11.first;
                            bArr3 = (byte[]) a11.second;
                            if (MimeTypes.AUDIO_AAC.equals(str8)) {
                                Pair a12 = ub.a(bArr3);
                                i23 = ((Integer) a12.first).intValue();
                                i22 = ((Integer) a12.second).intValue();
                            }
                        }
                    } else if (l13 == gu.B) {
                        utVar4.c(d6 + 8);
                        gzVar2.b = da.a(utVar4, Integer.toString(b2), str2, faVar5);
                        i10 = i18;
                        i11 = c5;
                    } else if (l13 == gu.D) {
                        utVar4.c(d6 + 8);
                        gzVar2.b = da.b(utVar4, Integer.toString(b2), str2, faVar5);
                        i10 = i18;
                        i11 = c5;
                    } else if (l13 == gu.I) {
                        gzVar2.b = bs.a(Integer.toString(b2), str8, null, -1, -1, i22, i23, null, faVar5, 0, str2);
                        i10 = i18;
                        i11 = c5;
                    } else if (l13 == gu.aW) {
                        byte[] bArr4 = new byte[l12];
                        utVar4.c(d6);
                        utVar4.a(bArr4, 0, l12);
                        bArr3 = bArr4;
                        i10 = i18;
                        i11 = c5;
                    } else if (l13 == gu.ba) {
                        int i24 = l12 - 8;
                        byte[] bArr5 = i;
                        i11 = c5;
                        byte[] bArr6 = new byte[(bArr5.length + i24)];
                        i10 = i18;
                        System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
                        utVar4.c(d6 + 8);
                        utVar4.a(bArr6, i.length, i24);
                        bArr3 = bArr6;
                    } else {
                        i10 = i18;
                        i11 = c5;
                        if (l12 == gu.bc) {
                            int i25 = l12 - 12;
                            byte[] bArr7 = new byte[i25];
                            utVar4.c(d6 + 12);
                            utVar4.a(bArr7, 0, i25);
                            bArr3 = bArr7;
                        }
                    }
                    d6 += l12;
                    hcVar3 = hcVar2;
                    c5 = i11;
                    i18 = i10;
                    gv gvVar3 = gvVar;
                    fa faVar8 = faVar;
                }
                hcVar = hcVar3;
                i3 = i18;
                i4 = c5;
                if (gzVar2.b == null && str8 != null) {
                    int i26 = MimeTypes.AUDIO_RAW.equals(str8) ? 2 : -1;
                    String num = Integer.toString(b2);
                    if (bArr3 == null) {
                        list = null;
                    } else {
                        list = Collections.singletonList(bArr3);
                    }
                    gzVar2.b = bs.a(num, str8, (String) null, -1, -1, i22, i23, i26, list, faVar5, 0, str2);
                }
                faVar2 = faVar;
            } else if (l8 == gu.au || l8 == gu.aE || l8 == gu.aF || l8 == gu.aG || l8 == gu.aH) {
                j4 = j8;
                gzVar2 = gzVar;
                utVar4.c(d3 + 8 + 8);
                if (l8 == gu.au) {
                    str = MimeTypes.APPLICATION_TTML;
                    j5 = Long.MAX_VALUE;
                    list2 = null;
                } else if (l8 == gu.aE) {
                    int i27 = (l7 - 8) - 8;
                    byte[] bArr8 = new byte[i27];
                    String str9 = MimeTypes.APPLICATION_TX3G;
                    utVar4.a(bArr8, 0, i27);
                    str = str9;
                    list2 = Collections.singletonList(bArr8);
                    j5 = Long.MAX_VALUE;
                } else if (l8 == gu.aF) {
                    str = MimeTypes.APPLICATION_MP4VTT;
                    j5 = Long.MAX_VALUE;
                    list2 = null;
                } else if (l8 == gu.aG) {
                    str = MimeTypes.APPLICATION_TTML;
                    j5 = 0;
                    list2 = null;
                } else if (l8 == gu.aH) {
                    String str10 = MimeTypes.APPLICATION_MP4CEA608;
                    gzVar2.d = 1;
                    str = str10;
                    j5 = Long.MAX_VALUE;
                    list2 = null;
                } else {
                    throw new IllegalStateException();
                }
                gzVar2.b = bs.a(Integer.toString(b2), str, (String) null, -1, 0, str2, -1, (fa) null, j5, list2);
                faVar2 = faVar7;
                pair2 = create;
                hcVar = hcVar3;
                i3 = i18;
                i4 = c5;
                i5 = i14;
            } else if (l8 == gu.aV) {
                j4 = j8;
                gzVar2 = gzVar;
                gzVar2.b = bs.a(Integer.toString(b2), MimeTypes.APPLICATION_CAMERA_MOTION, (String) null, -1, (fa) null);
                faVar2 = faVar7;
                pair2 = create;
                hcVar = hcVar3;
                i3 = i18;
                i4 = c5;
                i5 = i14;
            } else {
                j4 = j8;
                gzVar2 = gzVar;
                faVar2 = faVar7;
                pair2 = create;
                hcVar = hcVar3;
                i3 = i18;
                i4 = c5;
                i5 = i14;
            }
            utVar4.c(d3 + l7);
            i18 = i3 + 1;
            l6 = i2;
            create = pair2;
            gzVar3 = gzVar2;
            faVar7 = faVar2;
            j8 = j4;
            i14 = i5;
            hcVar3 = hcVar;
            c5 = i4;
            gv gvVar4 = gvVar;
        }
        Pair pair3 = create;
        hc hcVar4 = hcVar3;
        long j10 = j8;
        int i28 = i14;
        gz gzVar4 = gzVar3;
        if (!z) {
            gv e5 = gvVar.e(gu.aa);
            if (e5 != null) {
                gw d7 = e5.d(gu.ab);
                if (d7 != null) {
                    ut utVar5 = d7.be;
                    utVar5.c(8);
                    int a13 = gu.a(utVar5.l());
                    int p = utVar5.p();
                    long[] jArr3 = new long[p];
                    long[] jArr4 = new long[p];
                    int i29 = 0;
                    while (i29 < p) {
                        jArr3[i29] = a13 == 1 ? utVar5.q() : utVar5.j();
                        jArr4[i29] = a13 == 1 ? utVar5.m() : (long) utVar5.l();
                        if (utVar5.h() == 1) {
                            utVar5.d(2);
                            i29++;
                        } else {
                            throw new IllegalArgumentException("Unsupported media rate.");
                        }
                    }
                    pair = Pair.create(jArr3, jArr4);
                    hrVar = null;
                    jArr2 = (long[]) pair.first;
                    jArr = (long[]) pair.second;
                }
            }
            hrVar = null;
            pair = Pair.create(null, null);
            jArr2 = (long[]) pair.first;
            jArr = (long[]) pair.second;
        } else {
            hrVar = null;
            jArr2 = null;
            jArr = null;
        }
        if (gzVar4.b == null) {
            return hrVar;
        }
        hr hrVar2 = new hr(hcVar4.a, i28, ((Long) pair3.first).longValue(), j10, j6, gzVar4.b, gzVar4.d, gzVar4.a, gzVar4.c, jArr2, jArr);
        return hrVar2;
    }

    public static hu a(hr hrVar, gv gvVar, fu fuVar) throws ca {
        gy gyVar;
        boolean z;
        int i2;
        int i3;
        long j;
        int[] iArr;
        long[] jArr;
        int i4;
        int[] iArr2;
        long[] jArr2;
        int i5;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5;
        int i6;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z3;
        int i11;
        int i12;
        int i13;
        int i14;
        gy gyVar2;
        hr hrVar2 = hrVar;
        gv gvVar2 = gvVar;
        fu fuVar2 = fuVar;
        gw d2 = gvVar2.d(gu.aA);
        if (d2 != null) {
            gyVar = new ha(d2);
        } else {
            gw d3 = gvVar2.d(gu.aB);
            if (d3 != null) {
                gyVar = new hb(d3);
            } else {
                throw new ca("Track has no sample table size information");
            }
        }
        int a2 = gyVar.a();
        if (a2 == 0) {
            hu huVar = new hu(hrVar, new long[0], new int[0], 0, new long[0], new int[0], -9223372036854775807L);
            return huVar;
        }
        gw d4 = gvVar2.d(gu.aC);
        if (d4 == null) {
            d4 = gvVar2.d(gu.aD);
            z = true;
        } else {
            z = false;
        }
        ut utVar = d4.be;
        ut utVar2 = gvVar2.d(gu.az).be;
        ut utVar3 = gvVar2.d(gu.aw).be;
        gw d5 = gvVar2.d(gu.ax);
        ut utVar4 = d5 != null ? d5.be : null;
        gw d6 = gvVar2.d(gu.ay);
        ut utVar5 = d6 != null ? d6.be : null;
        he heVar = new he(utVar2, utVar, z);
        utVar3.c(12);
        int p = utVar3.p() - 1;
        int p2 = utVar3.p();
        int p3 = utVar3.p();
        if (utVar5 != null) {
            utVar5.c(12);
            i2 = utVar5.p();
        } else {
            i2 = 0;
        }
        int i15 = -1;
        if (utVar4 != null) {
            utVar4.c(12);
            i3 = utVar4.p();
            if (i3 > 0) {
                i15 = utVar4.p() - 1;
            } else {
                utVar4 = null;
            }
        } else {
            i3 = 0;
        }
        if (!(gyVar.c() && MimeTypes.AUDIO_RAW.equals(hrVar2.f.h) && p == 0 && i2 == 0 && i3 == 0)) {
            long[] jArr3 = new long[a2];
            int[] iArr6 = new int[a2];
            long[] jArr4 = new long[a2];
            int i16 = i3;
            iArr = new int[a2];
            int i17 = p;
            ut utVar6 = utVar3;
            int i18 = p3;
            int i19 = i15;
            long j2 = 0;
            long j3 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = i2;
            int i26 = i16;
            int i27 = i25;
            while (true) {
                if (i21 >= a2) {
                    i7 = i17;
                    i8 = a2;
                    i9 = i22;
                    i10 = i24;
                    break;
                }
                long j4 = j3;
                boolean z4 = true;
                while (i22 == 0) {
                    z4 = heVar.a();
                    if (!z4) {
                        break;
                    }
                    int i28 = i17;
                    int i29 = a2;
                    j4 = heVar.d;
                    i22 = heVar.c;
                    a2 = i29;
                    i17 = i28;
                }
                i7 = i17;
                int i30 = a2;
                if (!z4) {
                    Log.w("AtomParsers", "Unexpected end of chunk data");
                    jArr3 = Arrays.copyOf(jArr3, i21);
                    iArr6 = Arrays.copyOf(iArr6, i21);
                    jArr4 = Arrays.copyOf(jArr4, i21);
                    iArr = Arrays.copyOf(iArr, i21);
                    i8 = i21;
                    i9 = i22;
                    i10 = i24;
                    break;
                }
                if (utVar5 != null) {
                    int i31 = i27;
                    while (i23 == 0 && i31 > 0) {
                        i23 = utVar5.p();
                        i24 = utVar5.l();
                        i31--;
                    }
                    i23--;
                    i13 = i31;
                    i14 = i24;
                } else {
                    i13 = i27;
                    i14 = i24;
                }
                jArr3[i21] = j4;
                iArr6[i21] = gyVar.b();
                i27 = i13;
                if (iArr6[i21] > i20) {
                    i20 = iArr6[i21];
                    gyVar2 = gyVar;
                } else {
                    gyVar2 = gyVar;
                }
                jArr4[i21] = j2 + ((long) i14);
                iArr[i21] = utVar4 == null ? 1 : 0;
                if (i21 == i19) {
                    iArr[i21] = 1;
                    i26--;
                    if (i26 > 0) {
                        i19 = utVar4.p() - 1;
                    }
                }
                j2 += (long) i18;
                p2--;
                if (p2 == 0 && i7 > 0) {
                    int p4 = utVar6.p();
                    i18 = utVar6.l();
                    i7--;
                    p2 = p4;
                }
                i22--;
                i21++;
                i24 = i14;
                i17 = i7;
                gyVar = gyVar2;
                a2 = i30;
                j3 = j4 + ((long) iArr6[i21]);
            }
            j = j2 + ((long) i10);
            int i32 = i27;
            while (true) {
                if (i32 <= 0) {
                    z3 = true;
                    break;
                } else if (utVar5.p() != 0) {
                    z3 = false;
                    break;
                } else {
                    utVar5.l();
                    i32--;
                }
            }
            if (i26 == 0 && p2 == 0 && i9 == 0 && i7 == 0) {
                i12 = i23;
                if (i12 == 0 && z3) {
                    i11 = i20;
                    hrVar2 = hrVar;
                    jArr2 = jArr3;
                    jArr = jArr4;
                    i4 = i11;
                    iArr2 = iArr6;
                    i5 = i8;
                }
            } else {
                i12 = i23;
            }
            String str = "AtomParsers";
            i11 = i20;
            hrVar2 = hrVar;
            int i33 = hrVar2.a;
            String str2 = !z3 ? ", ctts invalid" : "";
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + ResultCodes.ERROR_OTHER);
            sb.append("Inconsistent stbl box for track ");
            sb.append(i33);
            sb.append(": remainingSynchronizationSamples ");
            sb.append(i26);
            sb.append(", remainingSamplesAtTimestampDelta ");
            sb.append(p2);
            sb.append(", remainingSamplesInChunk ");
            sb.append(i9);
            sb.append(", remainingTimestampDeltaChanges ");
            sb.append(i7);
            sb.append(", remainingSamplesAtTimestampOffset ");
            sb.append(i12);
            sb.append(str2);
            Log.w(str, sb.toString());
            jArr2 = jArr3;
            jArr = jArr4;
            i4 = i11;
            iArr2 = iArr6;
            i5 = i8;
        } else {
            int i34 = a2;
            long[] jArr5 = new long[heVar.a];
            int[] iArr7 = new int[heVar.a];
            while (heVar.a()) {
                jArr5[heVar.b] = heVar.d;
                iArr7[heVar.b] = heVar.c;
            }
            int b2 = vf.b(hrVar2.f.u, hrVar2.f.s);
            long j5 = (long) p3;
            int i35 = 8192 / b2;
            int i36 = 0;
            for (int a3 : iArr7) {
                i36 += vf.a(a3, i35);
            }
            long[] jArr6 = new long[i36];
            int[] iArr8 = new int[i36];
            long[] jArr7 = new long[i36];
            int[] iArr9 = new int[i36];
            int i37 = 0;
            int i38 = 0;
            int i39 = 0;
            int i40 = 0;
            while (i37 < iArr7.length) {
                int i41 = iArr7[i37];
                long j6 = jArr5[i37];
                int i42 = i39;
                int i43 = i38;
                int i44 = i40;
                while (i41 > 0) {
                    int min = Math.min(i35, i41);
                    jArr6[i42] = j6;
                    iArr8[i42] = b2 * min;
                    long[] jArr8 = jArr5;
                    i44 = Math.max(i44, iArr8[i42]);
                    int[] iArr10 = iArr7;
                    int i45 = b2;
                    jArr7[i42] = ((long) i43) * j5;
                    iArr9[i42] = 1;
                    j6 += (long) iArr8[i42];
                    i43 += min;
                    i41 -= min;
                    i42++;
                    b2 = i45;
                    iArr7 = iArr10;
                    jArr5 = jArr8;
                }
                long[] jArr9 = jArr5;
                int[] iArr11 = iArr7;
                int i46 = b2;
                i37++;
                i40 = i44;
                i38 = i43;
                i39 = i42;
                jArr5 = jArr9;
            }
            hf hfVar = new hf(jArr6, iArr8, i40, jArr7, iArr9, j5 * ((long) i38), 0);
            jArr2 = hfVar.a;
            iArr2 = hfVar.b;
            i4 = hfVar.c;
            jArr = hfVar.d;
            iArr = hfVar.e;
            j = hfVar.f;
            i5 = i34;
        }
        long c2 = vf.c(j, 1000000, hrVar2.c);
        if (hrVar2.h == null || fuVar.a()) {
            long[] jArr10 = jArr2;
            int[] iArr12 = iArr2;
            int i47 = i4;
            int[] iArr13 = iArr;
            vf.a(jArr, 1000000, hrVar2.c);
            hu huVar2 = new hu(hrVar, jArr10, iArr12, i47, jArr, iArr13, c2);
            return huVar2;
        }
        if (hrVar2.h.length == 1 && hrVar2.b == 1 && jArr.length >= 2) {
            long j7 = hrVar2.i[0];
            long c3 = vf.c(hrVar2.h[0], hrVar2.c, hrVar2.d) + j7;
            int length = jArr.length - 1;
            if (jArr[0] <= j7 && j7 < jArr[vf.a(3, 0, length)] && jArr[vf.a(jArr.length - 3, 0, length)] < c3 && c3 <= j) {
                long j8 = j - c3;
                long c4 = vf.c(j7 - jArr[0], (long) hrVar2.f.t, hrVar2.c);
                long c5 = vf.c(j8, (long) hrVar2.f.t, hrVar2.c);
                if (!(c4 == 0 && c5 == 0) && c4 <= 2147483647L && c5 <= 2147483647L) {
                    int i48 = (int) c4;
                    fu fuVar3 = fuVar;
                    fuVar3.a = i48;
                    fuVar3.b = (int) c5;
                    vf.a(jArr, 1000000, hrVar2.c);
                    hu huVar3 = new hu(hrVar, jArr2, iArr2, i4, jArr, iArr, vf.c(hrVar2.h[0], 1000000, hrVar2.d));
                    return huVar3;
                }
            }
        }
        if (hrVar2.h.length == 1 && hrVar2.h[0] == 0) {
            long j9 = hrVar2.i[0];
            for (int i49 = 0; i49 < jArr.length; i49++) {
                jArr[i49] = vf.c(jArr[i49] - j9, 1000000, hrVar2.c);
            }
            hu huVar4 = new hu(hrVar, jArr2, iArr2, i4, jArr, iArr, vf.c(j - j9, 1000000, hrVar2.c));
            return huVar4;
        }
        boolean z5 = hrVar2.b == 1;
        int[] iArr14 = new int[hrVar2.h.length];
        int[] iArr15 = new int[hrVar2.h.length];
        int i50 = 0;
        boolean z6 = false;
        int i51 = 0;
        int i52 = 0;
        while (i50 < hrVar2.h.length) {
            int[] iArr16 = iArr2;
            int i53 = i4;
            long j10 = hrVar2.i[i50];
            if (j10 != -1) {
                i6 = i53;
                boolean z7 = z6;
                int i54 = i51;
                long c6 = vf.c(hrVar2.h[i50], hrVar2.c, hrVar2.d);
                iArr14[i50] = vf.b(jArr, j10, true, true);
                iArr15[i50] = vf.b(jArr, j10 + c6, z5, false);
                while (iArr14[i50] < iArr15[i50] && (iArr[iArr14[i50]] & 1) == 0) {
                    iArr14[i50] = iArr14[i50] + 1;
                }
                i51 = i54 + (iArr15[i50] - iArr14[i50]);
                z2 = z7 | (i52 != iArr14[i50]);
                i52 = iArr15[i50];
            } else {
                z2 = z6;
                int i55 = i51;
                i6 = i53;
            }
            i50++;
            iArr2 = iArr16;
            i4 = i6;
            z6 = z2;
        }
        int[] iArr17 = iArr2;
        int i56 = i4;
        boolean z8 = z6;
        int i57 = 0;
        boolean z9 = true;
        if (i51 == i5) {
            z9 = false;
        }
        boolean z10 = z8 | z9;
        long[] jArr11 = z10 ? new long[i51] : jArr2;
        int[] iArr18 = z10 ? new int[i51] : iArr17;
        int i58 = z10 ? 0 : i56;
        int[] iArr19 = z10 ? new int[i51] : iArr;
        long[] jArr12 = new long[i51];
        long j11 = 0;
        int i59 = 0;
        while (i57 < hrVar2.h.length) {
            long j12 = hrVar2.i[i57];
            int i60 = iArr14[i57];
            int i61 = iArr15[i57];
            if (z10) {
                iArr4 = iArr14;
                int i62 = i61 - i60;
                System.arraycopy(jArr2, i60, jArr11, i59, i62);
                iArr3 = iArr15;
                iArr5 = iArr17;
                System.arraycopy(iArr5, i60, iArr18, i59, i62);
                System.arraycopy(iArr, i60, iArr19, i59, i62);
            } else {
                iArr4 = iArr14;
                iArr3 = iArr15;
                iArr5 = iArr17;
            }
            int i63 = i59;
            int i64 = i58;
            int i65 = i60;
            while (i65 < i61) {
                long[] jArr13 = jArr2;
                long[] jArr14 = jArr11;
                int[] iArr20 = iArr;
                int i66 = i64;
                int i67 = i61;
                jArr12[i63] = vf.c(j11, 1000000, hrVar2.d) + vf.c(jArr[i65] - j12, 1000000, hrVar2.c);
                i64 = (!z10 || iArr18[i63] <= i66) ? i66 : iArr5[i65];
                i63++;
                i65++;
                jArr2 = jArr13;
                jArr11 = jArr14;
                iArr = iArr20;
                i61 = i67;
            }
            long[] jArr15 = jArr11;
            j11 += hrVar2.h[i57];
            i57++;
            jArr2 = jArr2;
            i58 = i64;
            i59 = i63;
            iArr17 = iArr5;
            iArr14 = iArr4;
            iArr15 = iArr3;
            iArr = iArr;
        }
        hu huVar5 = new hu(hrVar, jArr11, iArr18, i58, jArr12, iArr19, vf.c(j11, 1000000, hrVar2.d));
        return huVar5;
    }

    public static js a(gw gwVar, boolean z) {
        if (z) {
            return null;
        }
        ut utVar = gwVar.be;
        utVar.c(8);
        while (utVar.b() >= 8) {
            int d2 = utVar.d();
            int l = utVar.l();
            if (utVar.l() == gu.aL) {
                utVar.c(d2);
                int i2 = d2 + l;
                utVar.d(12);
                while (true) {
                    if (utVar.d() >= i2) {
                        break;
                    }
                    int d3 = utVar.d();
                    int l2 = utVar.l();
                    if (utVar.l() == gu.aN) {
                        utVar.c(d3);
                        int i3 = d3 + l2;
                        utVar.d(8);
                        ArrayList arrayList = new ArrayList();
                        while (utVar.d() < i3) {
                            a a2 = hl.a(utVar);
                            if (a2 != null) {
                                arrayList.add(a2);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            return new js((List<? extends a>) arrayList);
                        }
                    } else {
                        utVar.c(d3 + l2);
                    }
                }
                return null;
            }
            utVar.c(d2 + l);
        }
        return null;
    }

    public static js a(gv gvVar) {
        gw d2 = gvVar.d(gu.ad);
        gw d3 = gvVar.d(gu.aM);
        gw d4 = gvVar.d(gu.aN);
        if (d2 == null || d3 == null || d4 == null || a(d2.be) != h) {
            return null;
        }
        ut utVar = d3.be;
        utVar.c(12);
        int l = utVar.l();
        String[] strArr = new String[l];
        for (int i2 = 0; i2 < l; i2++) {
            int l2 = utVar.l();
            utVar.d(4);
            strArr[i2] = utVar.e(l2 - 8);
        }
        ut utVar2 = d4.be;
        utVar2.c(8);
        ArrayList arrayList = new ArrayList();
        while (utVar2.b() > 8) {
            int d5 = utVar2.d();
            int l3 = utVar2.l();
            int l4 = utVar2.l() - 1;
            if (l4 < 0 || l4 >= strArr.length) {
                StringBuilder sb = new StringBuilder(52);
                sb.append("Skipped metadata with unknown key index: ");
                sb.append(l4);
                Log.w("AtomParsers", sb.toString());
            } else {
                hj a2 = hl.a(utVar2, d5 + l3, strArr[l4]);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            utVar2.c(d5 + l3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new js((List<? extends a>) arrayList);
    }

    private static int a(ut utVar) {
        utVar.c(16);
        return utVar.l();
    }

    private static Pair<String, byte[]> a(ut utVar, int i2) {
        utVar.c(i2 + 8 + 4);
        utVar.d(1);
        b(utVar);
        utVar.d(2);
        int e2 = utVar.e();
        if ((e2 & 128) != 0) {
            utVar.d(2);
        }
        if ((e2 & 64) != 0) {
            utVar.d(utVar.f());
        }
        if ((e2 & 32) != 0) {
            utVar.d(2);
        }
        utVar.d(1);
        b(utVar);
        String a2 = un.a(utVar.e());
        if (MimeTypes.AUDIO_MPEG.equals(a2) || MimeTypes.AUDIO_DTS.equals(a2) || MimeTypes.AUDIO_DTS_HD.equals(a2)) {
            return Pair.create(a2, null);
        }
        utVar.d(12);
        utVar.d(1);
        int b2 = b(utVar);
        byte[] bArr = new byte[b2];
        utVar.a(bArr, 0, b2);
        return Pair.create(a2, bArr);
    }

    private static Pair<Integer, hs> a(ut utVar, int i2, int i3) {
        Pair<Integer, hs> pair;
        Object obj;
        hs hsVar;
        int i4;
        int i5;
        byte[] bArr;
        ut utVar2 = utVar;
        int d2 = utVar.d();
        while (d2 - i2 < i3) {
            utVar2.c(d2);
            int l = utVar.l();
            boolean z = true;
            qi.a(l > 0, (Object) "childAtomSize should be positive");
            if (utVar.l() == gu.ag) {
                int i6 = d2 + 8;
                int i7 = -1;
                int i8 = 0;
                String str = null;
                Object obj2 = null;
                while (i6 - d2 < l) {
                    utVar2.c(i6);
                    int l2 = utVar.l();
                    int l3 = utVar.l();
                    if (l3 == gu.am) {
                        obj2 = Integer.valueOf(utVar.l());
                    } else if (l3 == gu.ah) {
                        utVar2.d(4);
                        str = utVar2.e(4);
                    } else if (l3 == gu.ai) {
                        i7 = i6;
                        i8 = l2;
                    }
                    i6 += l2;
                }
                if (C.CENC_TYPE_cenc.equals(str) || C.CENC_TYPE_cbc1.equals(str) || C.CENC_TYPE_cens.equals(str) || C.CENC_TYPE_cbcs.equals(str)) {
                    qi.a(obj2 != null, (Object) "frma atom is mandatory");
                    qi.a(i7 != -1, (Object) "schi atom is mandatory");
                    int i9 = i7 + 8;
                    while (true) {
                        if (i9 - i7 >= i8) {
                            obj = obj2;
                            hsVar = null;
                            break;
                        }
                        utVar2.c(i9);
                        int l4 = utVar.l();
                        if (utVar.l() == gu.aj) {
                            int a2 = gu.a(utVar.l());
                            utVar2.d(1);
                            if (a2 == 0) {
                                utVar2.d(1);
                                i5 = 0;
                                i4 = 0;
                            } else {
                                int e2 = utVar.e();
                                i5 = e2 & 15;
                                i4 = (e2 & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                            }
                            boolean z2 = utVar.e() == 1;
                            int e3 = utVar.e();
                            byte[] bArr2 = new byte[16];
                            utVar2.a(bArr2, 0, 16);
                            if (!z2 || e3 != 0) {
                                bArr = null;
                            } else {
                                int e4 = utVar.e();
                                byte[] bArr3 = new byte[e4];
                                utVar2.a(bArr3, 0, e4);
                                bArr = bArr3;
                            }
                            obj = obj2;
                            hsVar = new hs(z2, str, e3, bArr2, i4, i5, bArr);
                        } else {
                            Object obj3 = obj2;
                            i9 += l4;
                        }
                    }
                    if (hsVar == null) {
                        z = false;
                    }
                    qi.a(z, (Object) "tenc atom is mandatory");
                    pair = Pair.create(obj, hsVar);
                } else {
                    pair = null;
                }
                if (pair != null) {
                    return pair;
                }
            }
            d2 += l;
        }
        return null;
    }

    private static int b(ut utVar) {
        int e2 = utVar.e();
        int i2 = e2 & Statements.GetOwnedFoodIdsDateDescending;
        while ((e2 & 128) == 128) {
            e2 = utVar.e();
            i2 = (i2 << 7) | (e2 & Statements.GetOwnedFoodIdsDateDescending);
        }
        return i2;
    }
}
