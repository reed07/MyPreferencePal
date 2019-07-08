package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzis {
    public int height;
    public int number;
    public int type;
    public int width;
    /* access modifiers changed from: private */
    public String zzaaa;
    public String zzajs;
    public int zzajt;
    public boolean zzaju;
    public byte[] zzajv;
    public zzij zzajw;
    public byte[] zzajx;
    public int zzajy;
    public int zzajz;
    public int zzaka;
    public boolean zzakb;
    public int zzakc;
    public int zzakd;
    public int zzake;
    public int zzakf;
    public int zzakg;
    public float zzakh;
    public float zzaki;
    public float zzakj;
    public float zzakk;
    public float zzakl;
    public float zzakm;
    public float zzakn;
    public float zzako;
    public float zzakp;
    public float zzakq;
    public int zzakr;
    public long zzaks;
    public long zzakt;
    public boolean zzaku;
    public boolean zzakv;
    public zzii zzakw;
    public int zzakx;
    public zzhp zzzm;
    public int zzzq;
    public byte[] zzzr;
    public int zzzt;
    public int zzzu;

    private zzis() {
        this.width = -1;
        this.height = -1;
        this.zzajy = -1;
        this.zzajz = -1;
        this.zzaka = 0;
        this.zzzr = null;
        this.zzzq = -1;
        this.zzakb = false;
        this.zzakc = -1;
        this.zzakd = -1;
        this.zzake = -1;
        this.zzakf = 1000;
        this.zzakg = 200;
        this.zzakh = -1.0f;
        this.zzaki = -1.0f;
        this.zzakj = -1.0f;
        this.zzakk = -1.0f;
        this.zzakl = -1.0f;
        this.zzakm = -1.0f;
        this.zzakn = -1.0f;
        this.zzako = -1.0f;
        this.zzakp = -1.0f;
        this.zzakq = -1.0f;
        this.zzzt = 1;
        this.zzakr = -1;
        this.zzzu = 8000;
        this.zzaks = 0;
        this.zzakt = 0;
        this.zzakv = true;
        this.zzaaa = "eng";
    }

    /* JADX WARNING: Removed duplicated region for block: B:162:0x0422  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x04e7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzib r28, int r29) throws com.google.android.gms.internal.ads.zzfx {
        /*
            r27 = this;
            r0 = r27
            java.lang.String r1 = r0.zzajs
            int r2 = r1.hashCode()
            r3 = 25
            r4 = 4
            r5 = 8
            r6 = 1
            r7 = 2
            r8 = 0
            r9 = 3
            r10 = -1
            switch(r2) {
                case -2095576542: goto L_0x014c;
                case -2095575984: goto L_0x0142;
                case -1985379776: goto L_0x0137;
                case -1784763192: goto L_0x012c;
                case -1730367663: goto L_0x0121;
                case -1482641358: goto L_0x0116;
                case -1482641357: goto L_0x010b;
                case -1373388978: goto L_0x0100;
                case -933872740: goto L_0x00f5;
                case -538363189: goto L_0x00ea;
                case -538363109: goto L_0x00df;
                case -425012669: goto L_0x00d3;
                case -356037306: goto L_0x00c7;
                case 62923557: goto L_0x00bb;
                case 62923603: goto L_0x00af;
                case 62927045: goto L_0x00a3;
                case 82338133: goto L_0x0098;
                case 82338134: goto L_0x008d;
                case 99146302: goto L_0x0081;
                case 444813526: goto L_0x0075;
                case 542569478: goto L_0x0069;
                case 725957860: goto L_0x005d;
                case 855502857: goto L_0x0052;
                case 1422270023: goto L_0x0046;
                case 1809237540: goto L_0x003b;
                case 1950749482: goto L_0x002f;
                case 1950789798: goto L_0x0023;
                case 1951062397: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x0156
        L_0x0017:
            java.lang.String r2 = "A_OPUS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 11
            goto L_0x0157
        L_0x0023:
            java.lang.String r2 = "A_FLAC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 21
            goto L_0x0157
        L_0x002f:
            java.lang.String r2 = "A_EAC3"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 16
            goto L_0x0157
        L_0x003b:
            java.lang.String r2 = "V_MPEG2"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 2
            goto L_0x0157
        L_0x0046:
            java.lang.String r2 = "S_TEXT/UTF8"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 24
            goto L_0x0157
        L_0x0052:
            java.lang.String r2 = "V_MPEGH/ISO/HEVC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 7
            goto L_0x0157
        L_0x005d:
            java.lang.String r2 = "A_PCM/INT/LIT"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 23
            goto L_0x0157
        L_0x0069:
            java.lang.String r2 = "A_DTS/EXPRESS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 19
            goto L_0x0157
        L_0x0075:
            java.lang.String r2 = "V_THEORA"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 9
            goto L_0x0157
        L_0x0081:
            java.lang.String r2 = "S_HDMV/PGS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 26
            goto L_0x0157
        L_0x008d:
            java.lang.String r2 = "V_VP9"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 1
            goto L_0x0157
        L_0x0098:
            java.lang.String r2 = "V_VP8"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 0
            goto L_0x0157
        L_0x00a3:
            java.lang.String r2 = "A_DTS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 18
            goto L_0x0157
        L_0x00af:
            java.lang.String r2 = "A_AC3"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 15
            goto L_0x0157
        L_0x00bb:
            java.lang.String r2 = "A_AAC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 12
            goto L_0x0157
        L_0x00c7:
            java.lang.String r2 = "A_DTS/LOSSLESS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 20
            goto L_0x0157
        L_0x00d3:
            java.lang.String r2 = "S_VOBSUB"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 25
            goto L_0x0157
        L_0x00df:
            java.lang.String r2 = "V_MPEG4/ISO/AVC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 6
            goto L_0x0157
        L_0x00ea:
            java.lang.String r2 = "V_MPEG4/ISO/ASP"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 4
            goto L_0x0157
        L_0x00f5:
            java.lang.String r2 = "S_DVBSUB"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 27
            goto L_0x0157
        L_0x0100:
            java.lang.String r2 = "V_MS/VFW/FOURCC"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 8
            goto L_0x0157
        L_0x010b:
            java.lang.String r2 = "A_MPEG/L3"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 14
            goto L_0x0157
        L_0x0116:
            java.lang.String r2 = "A_MPEG/L2"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 13
            goto L_0x0157
        L_0x0121:
            java.lang.String r2 = "A_VORBIS"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 10
            goto L_0x0157
        L_0x012c:
            java.lang.String r2 = "A_TRUEHD"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 17
            goto L_0x0157
        L_0x0137:
            java.lang.String r2 = "A_MS/ACM"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 22
            goto L_0x0157
        L_0x0142:
            java.lang.String r2 = "V_MPEG4/ISO/SP"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 3
            goto L_0x0157
        L_0x014c:
            java.lang.String r2 = "V_MPEG4/ISO/AP"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0156
            r1 = 5
            goto L_0x0157
        L_0x0156:
            r1 = -1
        L_0x0157:
            r2 = 4096(0x1000, float:5.74E-42)
            r11 = 0
            switch(r1) {
                case 0: goto L_0x03b0;
                case 1: goto L_0x03a7;
                case 2: goto L_0x039e;
                case 3: goto L_0x038c;
                case 4: goto L_0x038c;
                case 5: goto L_0x038c;
                case 6: goto L_0x0372;
                case 7: goto L_0x0358;
                case 8: goto L_0x0330;
                case 9: goto L_0x0326;
                case 10: goto L_0x0314;
                case 11: goto L_0x02cc;
                case 12: goto L_0x02bd;
                case 13: goto L_0x02b3;
                case 14: goto L_0x02a9;
                case 15: goto L_0x029f;
                case 16: goto L_0x0295;
                case 17: goto L_0x028b;
                case 18: goto L_0x0281;
                case 19: goto L_0x0281;
                case 20: goto L_0x0277;
                case 21: goto L_0x0268;
                case 22: goto L_0x01ef;
                case 23: goto L_0x01a9;
                case 24: goto L_0x019f;
                case 25: goto L_0x0190;
                case 26: goto L_0x0186;
                case 27: goto L_0x0165;
                default: goto L_0x015d;
            }
        L_0x015d:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Unrecognized codec identifier."
            r1.<init>(r2)
            throw r1
        L_0x0165:
            java.lang.String r1 = "application/dvbsubs"
            byte[] r2 = new byte[r4]
            byte[] r4 = r0.zzajx
            byte r5 = r4[r8]
            r2[r8] = r5
            byte r5 = r4[r6]
            r2[r6] = r5
            byte r5 = r4[r7]
            r2[r7] = r5
            byte r4 = r4[r9]
            r2[r9] = r4
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x0186:
            java.lang.String r1 = "application/pgs"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x0190:
            java.lang.String r1 = "application/vobsub"
            byte[] r2 = r0.zzajx
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x019f:
            java.lang.String r1 = "application/x-subrip"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x01a9:
            java.lang.String r1 = "audio/raw"
            int r2 = r0.zzakr
            int r2 = com.google.android.gms.internal.ads.zzqe.zzbp(r2)
            if (r2 != 0) goto L_0x01e7
            java.lang.String r1 = "audio/x-unknown"
            java.lang.String r2 = "MatroskaExtractor"
            int r4 = r0.zzakr
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
            goto L_0x03b8
        L_0x01e7:
            r13 = r1
            r19 = r2
            r2 = r11
            r16 = -1
            goto L_0x03b8
        L_0x01ef:
            java.lang.String r1 = "audio/raw"
            com.google.android.gms.internal.ads.zzpx r2 = new com.google.android.gms.internal.ads.zzpx
            byte[] r4 = r0.zzajx
            r2.<init>(r4)
            boolean r2 = zzb(r2)
            if (r2 == 0) goto L_0x0242
            int r2 = r0.zzakr
            int r2 = com.google.android.gms.internal.ads.zzqe.zzbp(r2)
            if (r2 != 0) goto L_0x023a
            java.lang.String r1 = "audio/x-unknown"
            java.lang.String r2 = "MatroskaExtractor"
            int r4 = r0.zzakr
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
            goto L_0x03b8
        L_0x023a:
            r13 = r1
            r19 = r2
            r2 = r11
            r16 = -1
            goto L_0x03b8
        L_0x0242:
            java.lang.String r1 = "audio/x-unknown"
            java.lang.String r2 = "MatroskaExtractor"
            java.lang.String r4 = "Non-PCM MS/ACM is unsupported. Setting mimeType to "
            java.lang.String r5 = java.lang.String.valueOf(r1)
            int r12 = r5.length()
            if (r12 == 0) goto L_0x0257
            java.lang.String r4 = r4.concat(r5)
            goto L_0x025d
        L_0x0257:
            java.lang.String r5 = new java.lang.String
            r5.<init>(r4)
            r4 = r5
        L_0x025d:
            android.util.Log.w(r2, r4)
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x0268:
            java.lang.String r1 = "audio/x-flac"
            byte[] r2 = r0.zzajx
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x0277:
            java.lang.String r1 = "audio/vnd.dts.hd"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x0281:
            java.lang.String r1 = "audio/vnd.dts"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x028b:
            java.lang.String r1 = "audio/true-hd"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x0295:
            java.lang.String r1 = "audio/eac3"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x029f:
            java.lang.String r1 = "audio/ac3"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x02a9:
            java.lang.String r1 = "audio/mpeg"
            r13 = r1
            r2 = r11
            r16 = 4096(0x1000, float:5.74E-42)
            r19 = -1
            goto L_0x03b8
        L_0x02b3:
            java.lang.String r1 = "audio/mpeg-L2"
            r13 = r1
            r2 = r11
            r16 = 4096(0x1000, float:5.74E-42)
            r19 = -1
            goto L_0x03b8
        L_0x02bd:
            java.lang.String r1 = "audio/mp4a-latm"
            byte[] r2 = r0.zzajx
            java.util.List r2 = java.util.Collections.singletonList(r2)
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x02cc:
            java.lang.String r1 = "audio/opus"
            r2 = 5760(0x1680, float:8.071E-42)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>(r9)
            byte[] r12 = r0.zzajx
            r4.add(r12)
            java.nio.ByteBuffer r12 = java.nio.ByteBuffer.allocate(r5)
            java.nio.ByteOrder r13 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteBuffer r12 = r12.order(r13)
            long r13 = r0.zzaks
            java.nio.ByteBuffer r12 = r12.putLong(r13)
            byte[] r12 = r12.array()
            r4.add(r12)
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.allocate(r5)
            java.nio.ByteOrder r12 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteBuffer r5 = r5.order(r12)
            long r12 = r0.zzakt
            java.nio.ByteBuffer r5 = r5.putLong(r12)
            byte[] r5 = r5.array()
            r4.add(r5)
            r13 = r1
            r2 = r4
            r16 = 5760(0x1680, float:8.071E-42)
            r19 = -1
            goto L_0x03b8
        L_0x0314:
            java.lang.String r1 = "audio/vorbis"
            r2 = 8192(0x2000, float:1.14794E-41)
            byte[] r4 = r0.zzajx
            java.util.List r4 = zzd(r4)
            r13 = r1
            r2 = r4
            r16 = 8192(0x2000, float:1.14794E-41)
            r19 = -1
            goto L_0x03b8
        L_0x0326:
            java.lang.String r1 = "video/x-unknown"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x0330:
            com.google.android.gms.internal.ads.zzpx r1 = new com.google.android.gms.internal.ads.zzpx
            byte[] r2 = r0.zzajx
            r1.<init>(r2)
            java.util.List r1 = zza(r1)
            if (r1 == 0) goto L_0x0347
            java.lang.String r2 = "video/wvc1"
            r13 = r2
            r16 = -1
            r19 = -1
            r2 = r1
            goto L_0x03b8
        L_0x0347:
            java.lang.String r2 = "MatroskaExtractor"
            java.lang.String r4 = "Unsupported FourCC. Setting mimeType to video/x-unknown"
            android.util.Log.w(r2, r4)
            java.lang.String r2 = "video/x-unknown"
            r13 = r2
            r16 = -1
            r19 = -1
            r2 = r1
            goto L_0x03b8
        L_0x0358:
            java.lang.String r1 = "video/hevc"
            com.google.android.gms.internal.ads.zzpx r2 = new com.google.android.gms.internal.ads.zzpx
            byte[] r4 = r0.zzajx
            r2.<init>(r4)
            com.google.android.gms.internal.ads.zzqn r2 = com.google.android.gms.internal.ads.zzqn.zzi(r2)
            java.util.List<byte[]> r4 = r2.zzzl
            int r2 = r2.zzakx
            r0.zzakx = r2
            r13 = r1
            r2 = r4
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x0372:
            java.lang.String r1 = "video/avc"
            com.google.android.gms.internal.ads.zzpx r2 = new com.google.android.gms.internal.ads.zzpx
            byte[] r4 = r0.zzajx
            r2.<init>(r4)
            com.google.android.gms.internal.ads.zzqh r2 = com.google.android.gms.internal.ads.zzqh.zzg(r2)
            java.util.List<byte[]> r4 = r2.zzzl
            int r2 = r2.zzakx
            r0.zzakx = r2
            r13 = r1
            r2 = r4
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x038c:
            java.lang.String r1 = "video/mp4v-es"
            byte[] r2 = r0.zzajx
            if (r2 != 0) goto L_0x0394
            r2 = r11
            goto L_0x0398
        L_0x0394:
            java.util.List r2 = java.util.Collections.singletonList(r2)
        L_0x0398:
            r13 = r1
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x039e:
            java.lang.String r1 = "video/mpeg2"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x03a7:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
            goto L_0x03b8
        L_0x03b0:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            r13 = r1
            r2 = r11
            r16 = -1
            r19 = -1
        L_0x03b8:
            boolean r1 = r0.zzakv
            r1 = r1 | r8
            boolean r4 = r0.zzaku
            if (r4 == 0) goto L_0x03c1
            r4 = 2
            goto L_0x03c2
        L_0x03c1:
            r4 = 0
        L_0x03c2:
            r1 = r1 | r4
            boolean r4 = com.google.android.gms.internal.ads.zzpt.zzab(r13)
            if (r4 == 0) goto L_0x03ea
            java.lang.String r12 = java.lang.Integer.toString(r29)
            r14 = 0
            r15 = -1
            int r3 = r0.zzzt
            int r4 = r0.zzzu
            com.google.android.gms.internal.ads.zzhp r5 = r0.zzzm
            java.lang.String r7 = r0.zzaaa
            r17 = r3
            r18 = r4
            r20 = r2
            r21 = r5
            r22 = r1
            r23 = r7
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            r9 = 1
            goto L_0x0561
        L_0x03ea:
            boolean r4 = com.google.android.gms.internal.ads.zzpt.zzac(r13)
            if (r4 == 0) goto L_0x050f
            int r1 = r0.zzaka
            if (r1 != 0) goto L_0x0404
            int r1 = r0.zzajy
            if (r1 != r10) goto L_0x03fa
            int r1 = r0.width
        L_0x03fa:
            r0.zzajy = r1
            int r1 = r0.zzajz
            if (r1 != r10) goto L_0x0402
            int r1 = r0.height
        L_0x0402:
            r0.zzajz = r1
        L_0x0404:
            int r1 = r0.zzajy
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r1 == r10) goto L_0x041c
            int r5 = r0.zzajz
            if (r5 == r10) goto L_0x041c
            int r6 = r0.height
            int r6 = r6 * r1
            float r1 = (float) r6
            int r6 = r0.width
            int r6 = r6 * r5
            float r5 = (float) r6
            float r1 = r1 / r5
            r22 = r1
            goto L_0x041e
        L_0x041c:
            r22 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x041e:
            boolean r1 = r0.zzakb
            if (r1 == 0) goto L_0x04e7
            float r1 = r0.zzakh
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzaki
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzakj
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzakk
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzakl
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzakm
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzakn
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzako
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzakp
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x04d9
            float r1 = r0.zzakq
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 != 0) goto L_0x045f
            goto L_0x04d9
        L_0x045f:
            byte[] r11 = new byte[r3]
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r11)
            r1.put(r8)
            float r3 = r0.zzakh
            r4 = 1195593728(0x47435000, float:50000.0)
            float r3 = r3 * r4
            r5 = 1056964608(0x3f000000, float:0.5)
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzaki
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzakj
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzakk
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzakl
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzakm
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzakn
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzako
            float r3 = r3 * r4
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzakp
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            float r3 = r0.zzakq
            float r3 = r3 + r5
            int r3 = (int) r3
            short r3 = (short) r3
            r1.putShort(r3)
            int r3 = r0.zzakf
            short r3 = (short) r3
            r1.putShort(r3)
            int r3 = r0.zzakg
            short r3 = (short) r3
            r1.putShort(r3)
        L_0x04d9:
            com.google.android.gms.internal.ads.zzqi r1 = new com.google.android.gms.internal.ads.zzqi
            int r3 = r0.zzakc
            int r4 = r0.zzake
            int r5 = r0.zzakd
            r1.<init>(r3, r4, r5, r11)
            r25 = r1
            goto L_0x04e9
        L_0x04e7:
            r25 = r11
        L_0x04e9:
            java.lang.String r12 = java.lang.Integer.toString(r29)
            r14 = 0
            r15 = -1
            int r1 = r0.width
            int r3 = r0.height
            r19 = -1082130432(0xffffffffbf800000, float:-1.0)
            r21 = -1
            byte[] r4 = r0.zzzr
            int r5 = r0.zzzq
            com.google.android.gms.internal.ads.zzhp r6 = r0.zzzm
            r17 = r1
            r18 = r3
            r20 = r2
            r23 = r4
            r24 = r5
            r26 = r6
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            r9 = 2
            goto L_0x0561
        L_0x050f:
            java.lang.String r3 = "application/x-subrip"
            boolean r3 = r3.equals(r13)
            if (r3 == 0) goto L_0x052c
            java.lang.String r12 = java.lang.Integer.toString(r29)
            r14 = 0
            r15 = -1
            java.lang.String r2 = r0.zzaaa
            com.google.android.gms.internal.ads.zzhp r3 = r0.zzzm
            r16 = r1
            r17 = r2
            r18 = r3
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r12, r13, r14, r15, r16, r17, r18)
            goto L_0x0561
        L_0x052c:
            java.lang.String r1 = "application/vobsub"
            boolean r1 = r1.equals(r13)
            if (r1 != 0) goto L_0x054d
            java.lang.String r1 = "application/pgs"
            boolean r1 = r1.equals(r13)
            if (r1 != 0) goto L_0x054d
            java.lang.String r1 = "application/dvbsubs"
            boolean r1 = r1.equals(r13)
            if (r1 == 0) goto L_0x0545
            goto L_0x054d
        L_0x0545:
            com.google.android.gms.internal.ads.zzfx r1 = new com.google.android.gms.internal.ads.zzfx
            java.lang.String r2 = "Unexpected MIME type."
            r1.<init>(r2)
            throw r1
        L_0x054d:
            java.lang.String r12 = java.lang.Integer.toString(r29)
            r14 = 0
            r15 = -1
            java.lang.String r1 = r0.zzaaa
            com.google.android.gms.internal.ads.zzhp r3 = r0.zzzm
            r16 = r2
            r17 = r1
            r18 = r3
            com.google.android.gms.internal.ads.zzfs r1 = com.google.android.gms.internal.ads.zzfs.zza(r12, r13, r14, r15, r16, r17, r18)
        L_0x0561:
            int r2 = r0.number
            r3 = r28
            com.google.android.gms.internal.ads.zzii r2 = r3.zzb(r2, r9)
            r0.zzakw = r2
            com.google.android.gms.internal.ads.zzii r2 = r0.zzakw
            r2.zzf(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzis.zza(com.google.android.gms.internal.ads.zzib, int):void");
    }

    private static List<byte[]> zza(zzpx zzpx) throws zzfx {
        try {
            zzpx.zzbl(16);
            if (zzpx.zzhe() != 826496599) {
                return null;
            }
            byte[] bArr = zzpx.data;
            for (int position = zzpx.getPosition() + 20; position < bArr.length - 4; position++) {
                if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                    return Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length));
                }
            }
            throw new zzfx("Failed to find FourCC VC1 initialization data");
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzfx("Error parsing FourCC VC1 codec private");
        }
    }

    private static List<byte[]> zzd(byte[] bArr) throws zzfx {
        try {
            if (bArr[0] == 2) {
                int i = 1;
                int i2 = 0;
                while (bArr[i] == -1) {
                    i2 += 255;
                    i++;
                }
                int i3 = i + 1;
                int i4 = i2 + bArr[i];
                int i5 = 0;
                while (bArr[i3] == -1) {
                    i5 += 255;
                    i3++;
                }
                int i6 = i3 + 1;
                int i7 = i5 + bArr[i3];
                if (bArr[i6] == 1) {
                    byte[] bArr2 = new byte[i4];
                    System.arraycopy(bArr, i6, bArr2, 0, i4);
                    int i8 = i6 + i4;
                    if (bArr[i8] == 3) {
                        int i9 = i8 + i7;
                        if (bArr[i9] == 5) {
                            byte[] bArr3 = new byte[(bArr.length - i9)];
                            System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                            ArrayList arrayList = new ArrayList(2);
                            arrayList.add(bArr2);
                            arrayList.add(bArr3);
                            return arrayList;
                        }
                        throw new zzfx("Error parsing vorbis codec private");
                    }
                    throw new zzfx("Error parsing vorbis codec private");
                }
                throw new zzfx("Error parsing vorbis codec private");
            }
            throw new zzfx("Error parsing vorbis codec private");
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzfx("Error parsing vorbis codec private");
        }
    }

    private static boolean zzb(zzpx zzpx) throws zzfx {
        try {
            int zzhc = zzpx.zzhc();
            if (zzhc == 1) {
                return true;
            }
            if (zzhc != 65534) {
                return false;
            }
            zzpx.setPosition(24);
            return zzpx.readLong() == zzip.zzaht.getMostSignificantBits() && zzpx.readLong() == zzip.zzaht.getLeastSignificantBits();
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzfx("Error parsing MS/ACM codec private");
        }
    }

    /* synthetic */ zzis(zziq zziq) {
        this();
    }
}
