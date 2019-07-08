package com.google.ads.interactivemedia.v3.internal;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
final class gj {
    public int A;
    public float B;
    public float C;
    public float D;
    public float E;
    public float F;
    public float G;
    public float H;
    public float I;
    public float J;
    public float K;
    public int L;
    public int M;
    public int N;
    public long O;
    public long P;
    public gk Q;
    public boolean R;
    public boolean S;
    public gc T;
    public int U;
    /* access modifiers changed from: private */
    public String V;
    public String a;
    public String b;
    public int c;
    public int d;
    public int e;
    public boolean f;
    public byte[] g;
    public gd h;
    public byte[] i;
    public fa j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public float q;
    public float r;
    public float s;
    public byte[] t;
    public int u;
    public boolean v;
    public int w;
    public int x;
    public int y;
    public int z;

    private gj() {
        this.k = -1;
        this.l = -1;
        this.m = -1;
        this.n = -1;
        this.o = 0;
        this.p = -1;
        this.q = BitmapDescriptorFactory.HUE_RED;
        this.r = BitmapDescriptorFactory.HUE_RED;
        this.s = BitmapDescriptorFactory.HUE_RED;
        this.t = null;
        this.u = -1;
        this.v = false;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = 1000;
        this.A = 200;
        this.B = -1.0f;
        this.C = -1.0f;
        this.D = -1.0f;
        this.E = -1.0f;
        this.F = -1.0f;
        this.G = -1.0f;
        this.H = -1.0f;
        this.I = -1.0f;
        this.J = -1.0f;
        this.K = -1.0f;
        this.L = 1;
        this.M = -1;
        this.N = 8000;
        this.O = 0;
        this.P = 0;
        this.S = true;
        this.V = "eng";
    }

    /* JADX WARNING: Removed duplicated region for block: B:167:0x044f  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0514  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0520  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0522  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x0567  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x056a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.ads.interactivemedia.v3.internal.fs r28, int r29) throws com.google.ads.interactivemedia.v3.internal.ca {
        /*
            r27 = this;
            r0 = r27
            java.lang.String r1 = r0.b
            int r2 = r1.hashCode()
            r3 = 25
            r4 = 4
            r5 = 8
            r6 = 1
            r7 = 2
            r8 = 3
            r9 = 0
            r10 = -1
            switch(r2) {
                case -2095576542: goto L_0x0164;
                case -2095575984: goto L_0x015a;
                case -1985379776: goto L_0x014f;
                case -1784763192: goto L_0x0144;
                case -1730367663: goto L_0x0139;
                case -1482641358: goto L_0x012e;
                case -1482641357: goto L_0x0123;
                case -1373388978: goto L_0x0118;
                case -933872740: goto L_0x010d;
                case -538363189: goto L_0x0102;
                case -538363109: goto L_0x00f7;
                case -425012669: goto L_0x00eb;
                case -356037306: goto L_0x00df;
                case 62923557: goto L_0x00d3;
                case 62923603: goto L_0x00c7;
                case 62927045: goto L_0x00bb;
                case 82318131: goto L_0x00b0;
                case 82338133: goto L_0x00a5;
                case 82338134: goto L_0x009a;
                case 99146302: goto L_0x008e;
                case 444813526: goto L_0x0082;
                case 542569478: goto L_0x0076;
                case 725957860: goto L_0x006a;
                case 738597099: goto L_0x005e;
                case 855502857: goto L_0x0052;
                case 1422270023: goto L_0x0046;
                case 1809237540: goto L_0x003b;
                case 1950749482: goto L_0x002f;
                case 1950789798: goto L_0x0023;
                case 1951062397: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x016e
        L_0x0017:
            java.lang.String r2 = "A_OPUS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 12
            goto L_0x016f
        L_0x0023:
            java.lang.String r2 = "A_FLAC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 22
            goto L_0x016f
        L_0x002f:
            java.lang.String r2 = "A_EAC3"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 17
            goto L_0x016f
        L_0x003b:
            java.lang.String r2 = "V_MPEG2"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 3
            goto L_0x016f
        L_0x0046:
            java.lang.String r2 = "S_TEXT/UTF8"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 25
            goto L_0x016f
        L_0x0052:
            java.lang.String r2 = "V_MPEGH/ISO/HEVC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 8
            goto L_0x016f
        L_0x005e:
            java.lang.String r2 = "S_TEXT/ASS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 26
            goto L_0x016f
        L_0x006a:
            java.lang.String r2 = "A_PCM/INT/LIT"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 24
            goto L_0x016f
        L_0x0076:
            java.lang.String r2 = "A_DTS/EXPRESS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 20
            goto L_0x016f
        L_0x0082:
            java.lang.String r2 = "V_THEORA"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 10
            goto L_0x016f
        L_0x008e:
            java.lang.String r2 = "S_HDMV/PGS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 28
            goto L_0x016f
        L_0x009a:
            java.lang.String r2 = "V_VP9"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 1
            goto L_0x016f
        L_0x00a5:
            java.lang.String r2 = "V_VP8"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 0
            goto L_0x016f
        L_0x00b0:
            java.lang.String r2 = "V_AV1"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 2
            goto L_0x016f
        L_0x00bb:
            java.lang.String r2 = "A_DTS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 19
            goto L_0x016f
        L_0x00c7:
            java.lang.String r2 = "A_AC3"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 16
            goto L_0x016f
        L_0x00d3:
            java.lang.String r2 = "A_AAC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 13
            goto L_0x016f
        L_0x00df:
            java.lang.String r2 = "A_DTS/LOSSLESS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 21
            goto L_0x016f
        L_0x00eb:
            java.lang.String r2 = "S_VOBSUB"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 27
            goto L_0x016f
        L_0x00f7:
            java.lang.String r2 = "V_MPEG4/ISO/AVC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 7
            goto L_0x016f
        L_0x0102:
            java.lang.String r2 = "V_MPEG4/ISO/ASP"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 5
            goto L_0x016f
        L_0x010d:
            java.lang.String r2 = "S_DVBSUB"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 29
            goto L_0x016f
        L_0x0118:
            java.lang.String r2 = "V_MS/VFW/FOURCC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 9
            goto L_0x016f
        L_0x0123:
            java.lang.String r2 = "A_MPEG/L3"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 15
            goto L_0x016f
        L_0x012e:
            java.lang.String r2 = "A_MPEG/L2"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 14
            goto L_0x016f
        L_0x0139:
            java.lang.String r2 = "A_VORBIS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 11
            goto L_0x016f
        L_0x0144:
            java.lang.String r2 = "A_TRUEHD"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 18
            goto L_0x016f
        L_0x014f:
            java.lang.String r2 = "A_MS/ACM"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 23
            goto L_0x016f
        L_0x015a:
            java.lang.String r2 = "V_MPEG4/ISO/SP"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 4
            goto L_0x016f
        L_0x0164:
            java.lang.String r2 = "V_MPEG4/ISO/AP"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x016e
            r1 = 6
            goto L_0x016f
        L_0x016e:
            r1 = -1
        L_0x016f:
            r2 = 4096(0x1000, float:5.74E-42)
            r11 = 0
            switch(r1) {
                case 0: goto L_0x03dc;
                case 1: goto L_0x03d2;
                case 2: goto L_0x03c8;
                case 3: goto L_0x03be;
                case 4: goto L_0x03ab;
                case 5: goto L_0x03ab;
                case 6: goto L_0x03ab;
                case 7: goto L_0x0390;
                case 8: goto L_0x0375;
                case 9: goto L_0x035a;
                case 10: goto L_0x034f;
                case 11: goto L_0x033d;
                case 12: goto L_0x02f5;
                case 13: goto L_0x02e6;
                case 14: goto L_0x02dc;
                case 15: goto L_0x02d2;
                case 16: goto L_0x02c8;
                case 17: goto L_0x02be;
                case 18: goto L_0x02ad;
                case 19: goto L_0x02a3;
                case 20: goto L_0x02a3;
                case 21: goto L_0x0299;
                case 22: goto L_0x028a;
                case 23: goto L_0x0211;
                case 24: goto L_0x01cb;
                case 25: goto L_0x01c1;
                case 26: goto L_0x01b7;
                case 27: goto L_0x01a8;
                case 28: goto L_0x019e;
                case 29: goto L_0x017d;
                default: goto L_0x0175;
            }
        L_0x0175:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Unrecognized codec identifier."
            r1.<init>(r2)
            throw r1
        L_0x017d:
            java.lang.String r1 = "application/dvbsubs"
            byte[] r2 = new byte[r4]
            byte[] r4 = r0.i
            byte r5 = r4[r9]
            r2[r9] = r5
            byte r5 = r4[r6]
            r2[r6] = r5
            byte r5 = r4[r7]
            r2[r7] = r5
            byte r4 = r4[r8]
            r2[r8] = r4
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x019e:
            java.lang.String r1 = "application/pgs"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x01a8:
            java.lang.String r1 = "application/vobsub"
            byte[] r2 = r0.i
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x01b7:
            java.lang.String r1 = "text/x-ssa"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x01c1:
            java.lang.String r1 = "application/x-subrip"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x01cb:
            java.lang.String r1 = "audio/raw"
            int r2 = r0.M
            int r2 = com.google.ads.interactivemedia.v3.internal.vf.b(r2)
            if (r2 != 0) goto L_0x0209
            java.lang.String r1 = "audio/x-unknown"
            java.lang.String r2 = "MatroskaExtractor"
            int r4 = r0.M
            java.lang.String r5 = java.lang.String.valueOf(r1)
            int r5 = r5.length()
            int r5 = r5 + 60
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r5)
            java.lang.String r5 = "Unsupported PCM bit depth: "
            r12.append(r5)
            r12.append(r4)
            java.lang.String r4 = ". Setting mimeType to "
            r12.append(r4)
            r12.append(r1)
            java.lang.String r4 = r12.toString()
            android.util.Log.w(r2, r4)
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x0209:
            r13 = r1
            r19 = r2
            r2 = r11
            r16 = -1
            goto L_0x03e5
        L_0x0211:
            java.lang.String r1 = "audio/raw"
            com.google.ads.interactivemedia.v3.internal.ut r2 = new com.google.ads.interactivemedia.v3.internal.ut
            byte[] r4 = r0.i
            r2.<init>(r4)
            boolean r2 = b(r2)
            if (r2 == 0) goto L_0x0264
            int r2 = r0.M
            int r2 = com.google.ads.interactivemedia.v3.internal.vf.b(r2)
            if (r2 != 0) goto L_0x025c
            java.lang.String r1 = "audio/x-unknown"
            java.lang.String r2 = "MatroskaExtractor"
            int r4 = r0.M
            java.lang.String r5 = java.lang.String.valueOf(r1)
            int r5 = r5.length()
            int r5 = r5 + 60
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r5)
            java.lang.String r5 = "Unsupported PCM bit depth: "
            r12.append(r5)
            r12.append(r4)
            java.lang.String r4 = ". Setting mimeType to "
            r12.append(r4)
            r12.append(r1)
            java.lang.String r4 = r12.toString()
            android.util.Log.w(r2, r4)
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x025c:
            r13 = r1
            r19 = r2
            r2 = r11
            r16 = -1
            goto L_0x03e5
        L_0x0264:
            java.lang.String r1 = "audio/x-unknown"
            java.lang.String r2 = "MatroskaExtractor"
            java.lang.String r4 = "Non-PCM MS/ACM is unsupported. Setting mimeType to "
            java.lang.String r5 = java.lang.String.valueOf(r1)
            int r12 = r5.length()
            if (r12 == 0) goto L_0x0279
            java.lang.String r4 = r4.concat(r5)
            goto L_0x027f
        L_0x0279:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r4)
            r4 = r5
        L_0x027f:
            android.util.Log.w(r2, r4)
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x028a:
            java.lang.String r1 = "audio/flac"
            byte[] r2 = r0.i
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x0299:
            java.lang.String r1 = "audio/vnd.dts.hd"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x02a3:
            java.lang.String r1 = "audio/vnd.dts"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x02ad:
            java.lang.String r1 = "audio/true-hd"
            com.google.ads.interactivemedia.v3.internal.gk r2 = new com.google.ads.interactivemedia.v3.internal.gk
            r2.<init>()
            r0.Q = r2
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x02be:
            java.lang.String r1 = "audio/eac3"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x02c8:
            java.lang.String r1 = "audio/ac3"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x02d2:
            java.lang.String r1 = "audio/mpeg"
            r13 = r1
            r2 = r11
            r16 = 4096(0x1000, float:5.74E-42)
            r19 = -1
            goto L_0x03e5
        L_0x02dc:
            java.lang.String r1 = "audio/mpeg-L2"
            r13 = r1
            r2 = r11
            r16 = 4096(0x1000, float:5.74E-42)
            r19 = -1
            goto L_0x03e5
        L_0x02e6:
            java.lang.String r1 = "audio/mp4a-latm"
            byte[] r2 = r0.i
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x02f5:
            java.lang.String r1 = "audio/opus"
            r2 = 5760(0x1680, float:8.071E-42)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r8)
            byte[] r12 = r0.i
            r4.add(r12)
            java.nio.ByteBuffer r12 = java.nio.ByteBuffer.allocate(r5)
            java.nio.ByteOrder r13 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteBuffer r12 = r12.order(r13)
            long r13 = r0.O
            java.nio.ByteBuffer r12 = r12.putLong(r13)
            byte[] r12 = r12.array()
            r4.add(r12)
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r5)
            java.nio.ByteOrder r12 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteBuffer r5 = r5.order(r12)
            long r12 = r0.P
            java.nio.ByteBuffer r5 = r5.putLong(r12)
            byte[] r5 = r5.array()
            r4.add(r5)
            r13 = r1
            r2 = r4
            r16 = 5760(0x1680, float:8.071E-42)
            r19 = -1
            goto L_0x03e5
        L_0x033d:
            java.lang.String r1 = "audio/vorbis"
            r2 = 8192(0x2000, float:1.14794E-41)
            byte[] r4 = r0.i
            java.util.List r4 = a(r4)
            r13 = r1
            r2 = r4
            r16 = 8192(0x2000, float:1.14794E-41)
            r19 = -1
            goto L_0x03e5
        L_0x034f:
            java.lang.String r1 = "video/x-unknown"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x035a:
            com.google.ads.interactivemedia.v3.internal.ut r1 = new com.google.ads.interactivemedia.v3.internal.ut
            byte[] r2 = r0.i
            r1.<init>(r2)
            android.util.Pair r1 = a(r1)
            java.lang.Object r2 = r1.first
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.second
            java.util.List r1 = (java.util.List) r1
            r13 = r2
            r16 = -1
            r19 = -1
            r2 = r1
            goto L_0x03e5
        L_0x0375:
            java.lang.String r1 = "video/hevc"
            com.google.ads.interactivemedia.v3.internal.ut r2 = new com.google.ads.interactivemedia.v3.internal.ut
            byte[] r4 = r0.i
            r2.<init>(r4)
            com.google.ads.interactivemedia.v3.internal.vn r2 = com.google.ads.interactivemedia.v3.internal.vn.a(r2)
            java.util.List<byte[]> r4 = r2.a
            int r2 = r2.b
            r0.U = r2
            r13 = r1
            r2 = r4
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x0390:
            java.lang.String r1 = "video/avc"
            com.google.ads.interactivemedia.v3.internal.ut r2 = new com.google.ads.interactivemedia.v3.internal.ut
            byte[] r4 = r0.i
            r2.<init>(r4)
            com.google.ads.interactivemedia.v3.internal.vh r2 = com.google.ads.interactivemedia.v3.internal.vh.a(r2)
            java.util.List<byte[]> r4 = r2.a
            int r2 = r2.b
            r0.U = r2
            r13 = r1
            r2 = r4
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x03ab:
            java.lang.String r1 = "video/mp4v-es"
            byte[] r2 = r0.i
            if (r2 != 0) goto L_0x03b4
            r2 = r11
            goto L_0x03b8
        L_0x03b4:
            java.util.List r2 = java.util.Collections.singletonList(r2)
        L_0x03b8:
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x03be:
            java.lang.String r1 = "video/mpeg2"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x03c8:
            java.lang.String r1 = "video/av01"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x03d2:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03e5
        L_0x03dc:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
        L_0x03e5:
            boolean r1 = r0.S
            r1 = r1 | r9
            boolean r4 = r0.R
            if (r4 == 0) goto L_0x03ee
            r4 = 2
            goto L_0x03ef
        L_0x03ee:
            r4 = 0
        L_0x03ef:
            r1 = r1 | r4
            boolean r4 = com.google.ads.interactivemedia.v3.internal.un.a(r13)
            if (r4 == 0) goto L_0x0417
            java.lang.String r12 = java.lang.Integer.toString(r29)
            r14 = 0
            r15 = -1
            int r3 = r0.L
            int r4 = r0.N
            com.google.ads.interactivemedia.v3.internal.fa r5 = r0.j
            java.lang.String r7 = r0.V
            r17 = r3
            r18 = r4
            r20 = r2
            r21 = r5
            r22 = r1
            r23 = r7
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            r8 = 1
            goto L_0x064d
        L_0x0417:
            boolean r4 = com.google.ads.interactivemedia.v3.internal.un.b(r13)
            if (r4 == 0) goto L_0x05c9
            int r1 = r0.o
            if (r1 != 0) goto L_0x0431
            int r1 = r0.m
            if (r1 != r10) goto L_0x0427
            int r1 = r0.k
        L_0x0427:
            r0.m = r1
            int r1 = r0.n
            if (r1 != r10) goto L_0x042f
            int r1 = r0.l
        L_0x042f:
            r0.n = r1
        L_0x0431:
            int r1 = r0.m
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 == r10) goto L_0x0449
            int r5 = r0.n
            if (r5 == r10) goto L_0x0449
            int r6 = r0.l
            int r6 = r6 * r1
            float r1 = (float) r6
            int r6 = r0.k
            int r6 = r6 * r5
            float r5 = (float) r6
            float r1 = r1 / r5
            r22 = r1
            goto L_0x044b
        L_0x0449:
            r22 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x044b:
            boolean r1 = r0.v
            if (r1 == 0) goto L_0x0514
            float r1 = r0.B
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.C
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.D
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.E
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.F
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.G
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.H
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.I
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.J
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0506
            float r1 = r0.K
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 != 0) goto L_0x048c
            goto L_0x0506
        L_0x048c:
            byte[] r11 = new byte[r3]
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r11)
            r1.put(r9)
            float r3 = r0.B
            r4 = 1195593728(0x47435000, float:50000.0)
            float r3 = r3 * r4
            r5 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.C
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.D
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.E
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.F
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.G
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.H
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.I
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.J
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.K
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            int r3 = r0.z
            short r3 = (short) r3
            r1.putShort(r3)
            int r3 = r0.A
            short r3 = (short) r3
            r1.putShort(r3)
        L_0x0506:
            com.google.ads.interactivemedia.v3.internal.vi r1 = new com.google.ads.interactivemedia.v3.internal.vi
            int r3 = r0.w
            int r4 = r0.y
            int r5 = r0.x
            r1.<init>(r3, r4, r5, r11)
            r25 = r1
            goto L_0x0516
        L_0x0514:
            r25 = r11
        L_0x0516:
            java.lang.String r1 = "htc_video_rotA-000"
            java.lang.String r3 = r0.a
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0522
            r1 = 0
            goto L_0x054a
        L_0x0522:
            java.lang.String r1 = "htc_video_rotA-090"
            java.lang.String r3 = r0.a
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x052f
            r1 = 90
            goto L_0x054a
        L_0x052f:
            java.lang.String r1 = "htc_video_rotA-180"
            java.lang.String r3 = r0.a
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x053c
            r1 = 180(0xb4, float:2.52E-43)
            goto L_0x054a
        L_0x053c:
            java.lang.String r1 = "htc_video_rotA-270"
            java.lang.String r3 = r0.a
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0549
            r1 = 270(0x10e, float:3.78E-43)
            goto L_0x054a
        L_0x0549:
            r1 = -1
        L_0x054a:
            int r3 = r0.p
            if (r3 != 0) goto L_0x05a2
            float r3 = r0.q
            r4 = 0
            int r3 = java.lang.Float.compare(r3, r4)
            if (r3 != 0) goto L_0x05a2
            float r3 = r0.r
            int r3 = java.lang.Float.compare(r3, r4)
            if (r3 != 0) goto L_0x05a2
            float r3 = r0.s
            int r3 = java.lang.Float.compare(r3, r4)
            if (r3 != 0) goto L_0x056a
            r21 = 0
            goto L_0x05a4
        L_0x056a:
            float r3 = r0.r
            r4 = 1119092736(0x42b40000, float:90.0)
            int r3 = java.lang.Float.compare(r3, r4)
            if (r3 != 0) goto L_0x0579
            r9 = 90
            r21 = 90
            goto L_0x05a4
        L_0x0579:
            float r3 = r0.r
            r4 = -1020002304(0xffffffffc3340000, float:-180.0)
            int r3 = java.lang.Float.compare(r3, r4)
            if (r3 == 0) goto L_0x059d
            float r3 = r0.r
            r4 = 1127481344(0x43340000, float:180.0)
            int r3 = java.lang.Float.compare(r3, r4)
            if (r3 != 0) goto L_0x058e
            goto L_0x059d
        L_0x058e:
            float r3 = r0.r
            r4 = -1028390912(0xffffffffc2b40000, float:-90.0)
            int r3 = java.lang.Float.compare(r3, r4)
            if (r3 != 0) goto L_0x05a2
            r9 = 270(0x10e, float:3.78E-43)
            r21 = 270(0x10e, float:3.78E-43)
            goto L_0x05a4
        L_0x059d:
            r9 = 180(0xb4, float:2.52E-43)
            r21 = 180(0xb4, float:2.52E-43)
            goto L_0x05a4
        L_0x05a2:
            r21 = r1
        L_0x05a4:
            java.lang.String r12 = java.lang.Integer.toString(r29)
            r14 = 0
            r15 = -1
            int r1 = r0.k
            int r3 = r0.l
            r19 = -1082130432(0xffffffffbf800000, float:-1.0)
            byte[] r4 = r0.t
            int r5 = r0.u
            com.google.ads.interactivemedia.v3.internal.fa r6 = r0.j
            r17 = r1
            r18 = r3
            r20 = r2
            r23 = r4
            r24 = r5
            r26 = r6
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            r8 = 2
            goto L_0x064d
        L_0x05c9:
            java.lang.String r3 = "application/x-subrip"
            boolean r3 = r3.equals(r13)
            if (r3 == 0) goto L_0x05df
            java.lang.String r2 = java.lang.Integer.toString(r29)
            java.lang.String r3 = r0.V
            com.google.ads.interactivemedia.v3.internal.fa r4 = r0.j
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r2, r13, r1, r3, r4)
            goto L_0x064d
        L_0x05df:
            java.lang.String r3 = "text/x-ssa"
            boolean r3 = r3.equals(r13)
            if (r3 == 0) goto L_0x0616
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>(r7)
            byte[] r3 = com.google.ads.interactivemedia.v3.internal.gi.c
            r2.add(r3)
            byte[] r3 = r0.i
            r2.add(r3)
            java.lang.String r12 = java.lang.Integer.toString(r29)
            r14 = 0
            r15 = -1
            java.lang.String r3 = r0.V
            r18 = -1
            com.google.ads.interactivemedia.v3.internal.fa r4 = r0.j
            r20 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r16 = r1
            r17 = r3
            r19 = r4
            r22 = r2
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r12, r13, r14, r15, r16, r17, r18, r19, r20, r22)
            goto L_0x064d
        L_0x0616:
            java.lang.String r3 = "application/vobsub"
            boolean r3 = r3.equals(r13)
            if (r3 != 0) goto L_0x0637
            java.lang.String r3 = "application/pgs"
            boolean r3 = r3.equals(r13)
            if (r3 != 0) goto L_0x0637
            java.lang.String r3 = "application/dvbsubs"
            boolean r3 = r3.equals(r13)
            if (r3 == 0) goto L_0x062f
            goto L_0x0637
        L_0x062f:
            com.google.ads.interactivemedia.v3.internal.ca r1 = new com.google.ads.interactivemedia.v3.internal.ca
            java.lang.String r2 = "Unexpected MIME type."
            r1.<init>(r2)
            throw r1
        L_0x0637:
            java.lang.String r12 = java.lang.Integer.toString(r29)
            r14 = 0
            r15 = -1
            java.lang.String r3 = r0.V
            com.google.ads.interactivemedia.v3.internal.fa r4 = r0.j
            r16 = r1
            r17 = r2
            r18 = r3
            r19 = r4
            com.google.ads.interactivemedia.v3.internal.bs r1 = com.google.ads.interactivemedia.v3.internal.bs.a(r12, r13, r14, r15, r16, r17, r18, r19)
        L_0x064d:
            int r2 = r0.c
            r3 = r28
            com.google.ads.interactivemedia.v3.internal.gc r2 = r3.a(r2, r8)
            r0.T = r2
            com.google.ads.interactivemedia.v3.internal.gc r2 = r0.T
            r2.a(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.gj.a(com.google.ads.interactivemedia.v3.internal.fs, int):void");
    }

    private static Pair<String, List<byte[]>> a(ut utVar) throws ca {
        try {
            utVar.d(16);
            long k2 = utVar.k();
            if (k2 == 1482049860) {
                return new Pair<>("video/divx", null);
            }
            if (k2 == 859189832) {
                return new Pair<>(MimeTypes.VIDEO_H263, null);
            }
            if (k2 == 826496599) {
                byte[] bArr = utVar.a;
                for (int d2 = utVar.d() + 20; d2 < bArr.length - 4; d2++) {
                    if (bArr[d2] == 0 && bArr[d2 + 1] == 0 && bArr[d2 + 2] == 1 && bArr[d2 + 3] == 15) {
                        return new Pair<>(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(bArr, d2, bArr.length)));
                    }
                }
                throw new ca("Failed to find FourCC VC1 initialization data");
            }
            Log.w("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
            return new Pair<>(MimeTypes.VIDEO_UNKNOWN, null);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ca("Error parsing FourCC private data");
        }
    }

    private static List<byte[]> a(byte[] bArr) throws ca {
        try {
            if (bArr[0] == 2) {
                int i2 = 1;
                int i3 = 0;
                while (bArr[i2] == -1) {
                    i3 += 255;
                    i2++;
                }
                int i4 = i2 + 1;
                int i5 = i3 + bArr[i2];
                int i6 = 0;
                while (bArr[i4] == -1) {
                    i6 += 255;
                    i4++;
                }
                int i7 = i4 + 1;
                int i8 = i6 + bArr[i4];
                if (bArr[i7] == 1) {
                    byte[] bArr2 = new byte[i5];
                    System.arraycopy(bArr, i7, bArr2, 0, i5);
                    int i9 = i7 + i5;
                    if (bArr[i9] == 3) {
                        int i10 = i9 + i8;
                        if (bArr[i10] == 5) {
                            byte[] bArr3 = new byte[(bArr.length - i10)];
                            System.arraycopy(bArr, i10, bArr3, 0, bArr.length - i10);
                            ArrayList arrayList = new ArrayList(2);
                            arrayList.add(bArr2);
                            arrayList.add(bArr3);
                            return arrayList;
                        }
                        throw new ca("Error parsing vorbis codec private");
                    }
                    throw new ca("Error parsing vorbis codec private");
                }
                throw new ca("Error parsing vorbis codec private");
            }
            throw new ca("Error parsing vorbis codec private");
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ca("Error parsing vorbis codec private");
        }
    }

    private static boolean b(ut utVar) throws ca {
        try {
            int g2 = utVar.g();
            if (g2 == 1) {
                return true;
            }
            if (g2 != 65534) {
                return false;
            }
            utVar.c(24);
            return utVar.m() == gi.f.getMostSignificantBits() && utVar.m() == gi.f.getLeastSignificantBits();
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ca("Error parsing MS/ACM codec private");
        }
    }

    /* synthetic */ gj(byte b2) {
        this();
    }
}
