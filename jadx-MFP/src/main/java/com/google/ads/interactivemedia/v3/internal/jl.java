package com.google.ads.interactivemedia.v3.internal;

import android.annotation.SuppressLint;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseIntArray;
import com.brightcove.player.C;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SuppressLint({"InlinedApi"})
/* compiled from: IMASDK */
public final class jl {
    private static final Pattern a = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap<a, List<jf>> b = new HashMap<>();
    private static final SparseIntArray c;
    private static final SparseIntArray d;
    private static final Map<String, Integer> e;
    private static final Map<String, Integer> f;
    private static final Map<String, Integer> g;
    private static final SparseIntArray h;
    private static int i = -1;

    /* compiled from: IMASDK */
    static final class a {
        public final String a;
        public final boolean b;

        public a(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        public final int hashCode() {
            String str = this.a;
            return (((str == null ? 0 : str.hashCode()) + 31) * 31) + (this.b ? 1231 : 1237);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != a.class) {
                return false;
            }
            a aVar = (a) obj;
            return TextUtils.equals(this.a, aVar.a) && this.b == aVar.b;
        }
    }

    public static jf a() throws jm {
        jf b2 = b(MimeTypes.AUDIO_RAW, false);
        if (b2 == null) {
            return null;
        }
        return jf.a(b2.a);
    }

    private static jf b(String str, boolean z) throws jm {
        List a2 = a(str, false);
        if (a2.isEmpty()) {
            return null;
        }
        return (jf) a2.get(0);
    }

    public static synchronized List<jf> a(String str, boolean z) throws jm {
        synchronized (jl.class) {
            a aVar = new a(str, z);
            List<jf> list = (List) b.get(aVar);
            if (list != null) {
                return list;
            }
            jn jpVar = vf.a >= 21 ? new jp(z) : new jo(0);
            ArrayList a2 = a(aVar, jpVar, str);
            if (z && a2.isEmpty() && 21 <= vf.a && vf.a <= 23) {
                jpVar = new jo(0);
                a2 = a(aVar, jpVar, str);
                if (!a2.isEmpty()) {
                    String str2 = ((jf) a2.get(0)).a;
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 63 + String.valueOf(str2).length());
                    sb.append("MediaCodecList API didn't list secure decoder for: ");
                    sb.append(str);
                    sb.append(". Assuming: ");
                    sb.append(str2);
                    Log.w("MediaCodecUtil", sb.toString());
                }
            }
            if (MimeTypes.AUDIO_E_AC3_JOC.equals(str)) {
                a2.addAll(a(new a(MimeTypes.AUDIO_E_AC3, aVar.b), jpVar, str));
            }
            if (MimeTypes.AUDIO_RAW.equals(str)) {
                Collections.sort(a2, new jr(0));
            } else if (vf.a < 21 && a2.size() > 1) {
                String str3 = ((jf) a2.get(0)).a;
                if ("OMX.SEC.mp3.dec".equals(str3) || "OMX.SEC.MP3.Decoder".equals(str3) || "OMX.brcm.audio.mp3.decoder".equals(str3)) {
                    Collections.sort(a2, new jq(0));
                }
            }
            List<jf> unmodifiableList = Collections.unmodifiableList(a2);
            b.put(aVar, unmodifiableList);
            return unmodifiableList;
        }
    }

    public static int b() throws jm {
        int i2;
        if (i == -1) {
            int i3 = 0;
            jf b2 = b(MimeTypes.VIDEO_H264, false);
            if (b2 != null) {
                CodecProfileLevel[] a2 = b2.a();
                int length = a2.length;
                int i4 = 0;
                while (i3 < length) {
                    switch (a2[i3].level) {
                        case 1:
                        case 2:
                            i2 = 25344;
                            break;
                        case 8:
                        case 16:
                        case 32:
                            i2 = 101376;
                            break;
                        case 64:
                            i2 = 202752;
                            break;
                        case 128:
                        case 256:
                            i2 = 414720;
                            break;
                        case 512:
                            i2 = 921600;
                            break;
                        case 1024:
                            i2 = 1310720;
                            break;
                        case 2048:
                        case 4096:
                            i2 = 2097152;
                            break;
                        case 8192:
                            i2 = 2228224;
                            break;
                        case C.DASH_ROLE_CAPTION_FLAG /*16384*/:
                            i2 = 5652480;
                            break;
                        case 32768:
                        case 65536:
                            i2 = 9437184;
                            break;
                        default:
                            i2 = -1;
                            break;
                    }
                    i4 = Math.max(i2, i4);
                    i3++;
                }
                i3 = Math.max(i4, vf.a >= 21 ? 345600 : 172800);
            }
            i = i3;
        }
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
        if (r3.equals("avc1") != false) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair<java.lang.Integer, java.lang.Integer> a(java.lang.String r10) {
        /*
            r0 = 0
            if (r10 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "\\."
            java.lang.String[] r1 = r10.split(r1)
            r2 = 0
            r3 = r1[r2]
            r4 = -1
            int r5 = r3.hashCode()
            r6 = 4
            r7 = 2
            r8 = 3
            r9 = 1
            switch(r5) {
                case 3006243: goto L_0x0056;
                case 3006244: goto L_0x004c;
                case 3095771: goto L_0x0042;
                case 3095823: goto L_0x0038;
                case 3199032: goto L_0x002e;
                case 3214780: goto L_0x0024;
                case 3356560: goto L_0x001a;
                default: goto L_0x0019;
            }
        L_0x0019:
            goto L_0x005f
        L_0x001a:
            java.lang.String r2 = "mp4a"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x005f
            r2 = 6
            goto L_0x0060
        L_0x0024:
            java.lang.String r2 = "hvc1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x005f
            r2 = 3
            goto L_0x0060
        L_0x002e:
            java.lang.String r2 = "hev1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x005f
            r2 = 2
            goto L_0x0060
        L_0x0038:
            java.lang.String r2 = "dvhe"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x005f
            r2 = 4
            goto L_0x0060
        L_0x0042:
            java.lang.String r2 = "dvh1"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x005f
            r2 = 5
            goto L_0x0060
        L_0x004c:
            java.lang.String r2 = "avc2"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x005f
            r2 = 1
            goto L_0x0060
        L_0x0056:
            java.lang.String r5 = "avc1"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x005f
            goto L_0x0060
        L_0x005f:
            r2 = -1
        L_0x0060:
            switch(r2) {
                case 0: goto L_0x01b7;
                case 1: goto L_0x01b7;
                case 2: goto L_0x010a;
                case 3: goto L_0x010a;
                case 4: goto L_0x0069;
                case 5: goto L_0x0069;
                case 6: goto L_0x0064;
                default: goto L_0x0063;
            }
        L_0x0063:
            return r0
        L_0x0064:
            android.util.Pair r10 = b(r10, r1)
            return r10
        L_0x0069:
            int r2 = r1.length
            if (r2 >= r8) goto L_0x0088
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = "Ignoring malformed Dolby Vision codec string: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r3 = r10.length()
            if (r3 == 0) goto L_0x007f
            java.lang.String r10 = r2.concat(r10)
            goto L_0x0084
        L_0x007f:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
        L_0x0084:
            android.util.Log.w(r1, r10)
            return r0
        L_0x0088:
            java.util.regex.Pattern r2 = a
            r3 = r1[r9]
            java.util.regex.Matcher r2 = r2.matcher(r3)
            boolean r3 = r2.matches()
            if (r3 != 0) goto L_0x00b2
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = "Ignoring malformed Dolby Vision codec string: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r3 = r10.length()
            if (r3 == 0) goto L_0x00a9
            java.lang.String r10 = r2.concat(r10)
            goto L_0x00ae
        L_0x00a9:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
        L_0x00ae:
            android.util.Log.w(r1, r10)
            return r0
        L_0x00b2:
            java.lang.String r10 = r2.group(r9)
            java.util.Map<java.lang.String, java.lang.Integer> r2 = f
            java.lang.Object r2 = r2.get(r10)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 != 0) goto L_0x00dc
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = "Unknown Dolby Vision profile string: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r3 = r10.length()
            if (r3 == 0) goto L_0x00d3
            java.lang.String r10 = r2.concat(r10)
            goto L_0x00d8
        L_0x00d3:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
        L_0x00d8:
            android.util.Log.w(r1, r10)
            return r0
        L_0x00dc:
            r10 = r1[r7]
            java.util.Map<java.lang.String, java.lang.Integer> r1 = g
            java.lang.Object r1 = r1.get(r10)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 != 0) goto L_0x0104
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = "Unknown Dolby Vision level string: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r3 = r10.length()
            if (r3 == 0) goto L_0x00fb
            java.lang.String r10 = r2.concat(r10)
            goto L_0x0100
        L_0x00fb:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
        L_0x0100:
            android.util.Log.w(r1, r10)
            return r0
        L_0x0104:
            android.util.Pair r10 = new android.util.Pair
            r10.<init>(r2, r1)
            return r10
        L_0x010a:
            int r2 = r1.length
            if (r2 >= r6) goto L_0x0129
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = "Ignoring malformed HEVC codec string: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r3 = r10.length()
            if (r3 == 0) goto L_0x0120
            java.lang.String r10 = r2.concat(r10)
            goto L_0x0125
        L_0x0120:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
        L_0x0125:
            android.util.Log.w(r1, r10)
            return r0
        L_0x0129:
            java.util.regex.Pattern r2 = a
            r3 = r1[r9]
            java.util.regex.Matcher r2 = r2.matcher(r3)
            boolean r3 = r2.matches()
            if (r3 != 0) goto L_0x0153
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = "Ignoring malformed HEVC codec string: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r3 = r10.length()
            if (r3 == 0) goto L_0x014a
            java.lang.String r10 = r2.concat(r10)
            goto L_0x014f
        L_0x014a:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
        L_0x014f:
            android.util.Log.w(r1, r10)
            return r0
        L_0x0153:
            java.lang.String r10 = r2.group(r9)
            java.lang.String r2 = "1"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x0161
            r7 = 1
            goto L_0x0169
        L_0x0161:
            java.lang.String r2 = "2"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L_0x019b
        L_0x0169:
            r10 = r1[r8]
            java.util.Map<java.lang.String, java.lang.Integer> r1 = e
            java.lang.Object r1 = r1.get(r10)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 != 0) goto L_0x0191
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = "Unknown HEVC level string: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r3 = r10.length()
            if (r3 == 0) goto L_0x0188
            java.lang.String r10 = r2.concat(r10)
            goto L_0x018d
        L_0x0188:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
        L_0x018d:
            android.util.Log.w(r1, r10)
            return r0
        L_0x0191:
            android.util.Pair r10 = new android.util.Pair
            java.lang.Integer r0 = java.lang.Integer.valueOf(r7)
            r10.<init>(r0, r1)
            return r10
        L_0x019b:
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = "Unknown HEVC profile string: "
            java.lang.String r10 = java.lang.String.valueOf(r10)
            int r3 = r10.length()
            if (r3 == 0) goto L_0x01ae
            java.lang.String r10 = r2.concat(r10)
            goto L_0x01b3
        L_0x01ae:
            java.lang.String r10 = new java.lang.String
            r10.<init>(r2)
        L_0x01b3:
            android.util.Log.w(r1, r10)
            return r0
        L_0x01b7:
            android.util.Pair r10 = a(r10, r1)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.jl.a(java.lang.String):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:135:0x024a A[Catch:{ Exception -> 0x02e8, Exception -> 0x0357 }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0288 A[SYNTHETIC, Splitter:B:153:0x0288] */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x0352 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<com.google.ads.interactivemedia.v3.internal.jf> a(com.google.ads.interactivemedia.v3.internal.jl.a r17, com.google.ads.interactivemedia.v3.internal.jn r18, java.lang.String r19) throws com.google.ads.interactivemedia.v3.internal.jm {
        /*
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = 0
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x0357 }
            r5.<init>()     // Catch:{ Exception -> 0x0357 }
            java.lang.String r6 = r1.a     // Catch:{ Exception -> 0x0357 }
            int r7 = r18.a()     // Catch:{ Exception -> 0x0357 }
            boolean r8 = r18.b()     // Catch:{ Exception -> 0x0357 }
            r9 = 0
        L_0x0017:
            if (r9 >= r7) goto L_0x0356
            android.media.MediaCodecInfo r0 = r2.a(r9)     // Catch:{ Exception -> 0x0357 }
            java.lang.String r10 = r0.getName()     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r0.isEncoder()     // Catch:{ Exception -> 0x0357 }
            r12 = 1
            if (r11 != 0) goto L_0x0247
            if (r8 != 0) goto L_0x0034
            java.lang.String r11 = ".secure"
            boolean r11 = r10.endsWith(r11)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0034
            goto L_0x0247
        L_0x0034:
            int r11 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x0357 }
            r13 = 21
            if (r11 >= r13) goto L_0x006d
            java.lang.String r11 = "CIPAACDecoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x006a
            java.lang.String r11 = "CIPMP3Decoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x006a
            java.lang.String r11 = "CIPVorbisDecoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x006a
            java.lang.String r11 = "CIPAMRNBDecoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x006a
            java.lang.String r11 = "AACDecoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x006a
            java.lang.String r11 = "MP3Decoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x006d
        L_0x006a:
            r11 = 0
            goto L_0x0248
        L_0x006d:
            int r11 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x0357 }
            r13 = 18
            if (r11 >= r13) goto L_0x009c
            java.lang.String r11 = "OMX.MTK.AUDIO.DECODER.AAC"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x009c
            java.lang.String r11 = "a70"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0099
            java.lang.String r11 = "Xiaomi"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.c     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x009c
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r13 = "HM"
            boolean r11 = r11.startsWith(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x009c
        L_0x0099:
            r11 = 0
            goto L_0x0248
        L_0x009c:
            int r11 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x0357 }
            r13 = 16
            if (r11 != r13) goto L_0x0125
            java.lang.String r11 = "OMX.qcom.audio.decoder.mp3"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0125
            java.lang.String r11 = "dlxu"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "protou"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "ville"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "villeplus"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "villec2"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r14 = "gee"
            boolean r11 = r11.startsWith(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "C6602"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "C6603"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "C6606"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "C6616"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "L36h"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0122
            java.lang.String r11 = "SO-02E"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0125
        L_0x0122:
            r11 = 0
            goto L_0x0248
        L_0x0125:
            int r11 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x0357 }
            if (r11 != r13) goto L_0x015c
            java.lang.String r11 = "OMX.qcom.audio.decoder.aac"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x015c
            java.lang.String r11 = "C1504"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0159
            java.lang.String r11 = "C1505"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0159
            java.lang.String r11 = "C1604"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0159
            java.lang.String r11 = "C1605"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x015c
        L_0x0159:
            r11 = 0
            goto L_0x0248
        L_0x015c:
            int r11 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x0357 }
            r13 = 24
            if (r11 >= r13) goto L_0x01cf
            java.lang.String r11 = "OMX.SEC.aac.dec"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0172
            java.lang.String r11 = "OMX.Exynos.AAC.Decoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x01cf
        L_0x0172:
            java.lang.String r11 = "samsung"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.c     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x01cf
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r13 = "zeroflte"
            boolean r11 = r11.startsWith(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x01cc
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r13 = "zerolte"
            boolean r11 = r11.startsWith(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x01cc
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r13 = "zenlte"
            boolean r11 = r11.startsWith(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x01cc
            java.lang.String r11 = "SC-05G"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x01cc
            java.lang.String r11 = "marinelteatt"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x01cc
            java.lang.String r11 = "404SC"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x01cc
            java.lang.String r11 = "SC-04G"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x01cc
            java.lang.String r11 = "SCV31"
            java.lang.String r13 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x01cf
        L_0x01cc:
            r11 = 0
            goto L_0x0248
        L_0x01cf:
            int r11 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x0357 }
            r13 = 19
            if (r11 > r13) goto L_0x021b
            java.lang.String r11 = "OMX.SEC.vp8.dec"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x021b
            java.lang.String r11 = "samsung"
            java.lang.String r14 = com.google.ads.interactivemedia.v3.internal.vf.c     // Catch:{ Exception -> 0x0357 }
            boolean r11 = r11.equals(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x021b
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r14 = "d2"
            boolean r11 = r11.startsWith(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0219
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r14 = "serrano"
            boolean r11 = r11.startsWith(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0219
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r14 = "jflte"
            boolean r11 = r11.startsWith(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0219
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r14 = "santos"
            boolean r11 = r11.startsWith(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x0219
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r14 = "t0"
            boolean r11 = r11.startsWith(r14)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x021b
        L_0x0219:
            r11 = 0
            goto L_0x0248
        L_0x021b:
            int r11 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x0357 }
            if (r11 > r13) goto L_0x0233
            java.lang.String r11 = com.google.ads.interactivemedia.v3.internal.vf.b     // Catch:{ Exception -> 0x0357 }
            java.lang.String r13 = "jflte"
            boolean r11 = r11.startsWith(r13)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0233
            java.lang.String r11 = "OMX.qcom.video.decoder.vp8"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0233
            r11 = 0
            goto L_0x0248
        L_0x0233:
            java.lang.String r11 = "audio/eac3-joc"
            boolean r11 = r11.equals(r3)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0245
            java.lang.String r11 = "OMX.MTK.AUDIO.DECODER.DSPAC3"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0245
            r11 = 0
            goto L_0x0248
        L_0x0245:
            r11 = 1
            goto L_0x0248
        L_0x0247:
            r11 = 0
        L_0x0248:
            if (r11 == 0) goto L_0x0285
            java.lang.String r11 = "video/dolby-vision"
            boolean r11 = r3.equals(r11)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0270
            java.lang.String r11 = "OMX.MS.HEVCDV.Decoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x025d
            java.lang.String r11 = "video/hevcdv"
            goto L_0x0286
        L_0x025d:
            java.lang.String r11 = "OMX.RTK.video.decoder"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 != 0) goto L_0x026d
            java.lang.String r11 = "OMX.realtek.video.decoder.tunneled"
            boolean r11 = r11.equals(r10)     // Catch:{ Exception -> 0x0357 }
            if (r11 == 0) goto L_0x0270
        L_0x026d:
            java.lang.String r11 = "video/dv_hevc"
            goto L_0x0286
        L_0x0270:
            java.lang.String[] r11 = r0.getSupportedTypes()     // Catch:{ Exception -> 0x0357 }
            int r13 = r11.length     // Catch:{ Exception -> 0x0357 }
            r14 = 0
        L_0x0276:
            if (r14 >= r13) goto L_0x0285
            r15 = r11[r14]     // Catch:{ Exception -> 0x0357 }
            boolean r16 = r15.equalsIgnoreCase(r3)     // Catch:{ Exception -> 0x0357 }
            if (r16 == 0) goto L_0x0282
            r11 = r15
            goto L_0x0286
        L_0x0282:
            int r14 = r14 + 1
            goto L_0x0276
        L_0x0285:
            r11 = 0
        L_0x0286:
            if (r11 == 0) goto L_0x0352
            android.media.MediaCodecInfo$CodecCapabilities r0 = r0.getCapabilitiesForType(r11)     // Catch:{ Exception -> 0x02e8 }
            boolean r13 = r2.a(r6, r0)     // Catch:{ Exception -> 0x02e8 }
            int r14 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x02e8 }
            r15 = 22
            if (r14 > r15) goto L_0x02bc
            java.lang.String r14 = "ODROID-XU3"
            java.lang.String r15 = com.google.ads.interactivemedia.v3.internal.vf.d     // Catch:{ Exception -> 0x02e8 }
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x02e8 }
            if (r14 != 0) goto L_0x02aa
            java.lang.String r14 = "Nexus 10"
            java.lang.String r15 = com.google.ads.interactivemedia.v3.internal.vf.d     // Catch:{ Exception -> 0x02e8 }
            boolean r14 = r14.equals(r15)     // Catch:{ Exception -> 0x02e8 }
            if (r14 == 0) goto L_0x02bc
        L_0x02aa:
            java.lang.String r14 = "OMX.Exynos.AVC.Decoder"
            boolean r14 = r14.equals(r10)     // Catch:{ Exception -> 0x02e8 }
            if (r14 != 0) goto L_0x02ba
            java.lang.String r14 = "OMX.Exynos.AVC.Decoder.secure"
            boolean r14 = r14.equals(r10)     // Catch:{ Exception -> 0x02e8 }
            if (r14 == 0) goto L_0x02bc
        L_0x02ba:
            r14 = 1
            goto L_0x02bd
        L_0x02bc:
            r14 = 0
        L_0x02bd:
            if (r8 == 0) goto L_0x02c3
            boolean r15 = r1.b     // Catch:{ Exception -> 0x02e8 }
            if (r15 == r13) goto L_0x02c9
        L_0x02c3:
            if (r8 != 0) goto L_0x02d2
            boolean r15 = r1.b     // Catch:{ Exception -> 0x02e8 }
            if (r15 != 0) goto L_0x02d2
        L_0x02c9:
            com.google.ads.interactivemedia.v3.internal.jf r0 = com.google.ads.interactivemedia.v3.internal.jf.a(r10, r6, r0, r14, r4)     // Catch:{ Exception -> 0x02e8 }
            r5.add(r0)     // Catch:{ Exception -> 0x02e8 }
            goto L_0x0352
        L_0x02d2:
            if (r8 != 0) goto L_0x0352
            if (r13 == 0) goto L_0x0352
            java.lang.String r13 = java.lang.String.valueOf(r10)     // Catch:{ Exception -> 0x02e8 }
            java.lang.String r15 = ".secure"
            java.lang.String r13 = r13.concat(r15)     // Catch:{ Exception -> 0x02e8 }
            com.google.ads.interactivemedia.v3.internal.jf r0 = com.google.ads.interactivemedia.v3.internal.jf.a(r13, r6, r0, r14, r12)     // Catch:{ Exception -> 0x02e8 }
            r5.add(r0)     // Catch:{ Exception -> 0x02e8 }
            return r5
        L_0x02e8:
            r0 = move-exception
            int r12 = com.google.ads.interactivemedia.v3.internal.vf.a     // Catch:{ Exception -> 0x0357 }
            r13 = 23
            if (r12 > r13) goto L_0x031b
            boolean r12 = r5.isEmpty()     // Catch:{ Exception -> 0x0357 }
            if (r12 != 0) goto L_0x031b
            java.lang.String r0 = "MediaCodecUtil"
            java.lang.String r11 = java.lang.String.valueOf(r10)     // Catch:{ Exception -> 0x0357 }
            int r11 = r11.length()     // Catch:{ Exception -> 0x0357 }
            int r11 = r11 + 46
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0357 }
            r12.<init>(r11)     // Catch:{ Exception -> 0x0357 }
            java.lang.String r11 = "Skipping codec "
            r12.append(r11)     // Catch:{ Exception -> 0x0357 }
            r12.append(r10)     // Catch:{ Exception -> 0x0357 }
            java.lang.String r10 = " (failed to query capabilities)"
            r12.append(r10)     // Catch:{ Exception -> 0x0357 }
            java.lang.String r10 = r12.toString()     // Catch:{ Exception -> 0x0357 }
            android.util.Log.e(r0, r10)     // Catch:{ Exception -> 0x0357 }
            goto L_0x0352
        L_0x031b:
            java.lang.String r1 = "MediaCodecUtil"
            java.lang.String r2 = java.lang.String.valueOf(r10)     // Catch:{ Exception -> 0x0357 }
            int r2 = r2.length()     // Catch:{ Exception -> 0x0357 }
            int r2 = r2 + 25
            java.lang.String r3 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0357 }
            int r3 = r3.length()     // Catch:{ Exception -> 0x0357 }
            int r2 = r2 + r3
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0357 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0357 }
            java.lang.String r2 = "Failed to query codec "
            r3.append(r2)     // Catch:{ Exception -> 0x0357 }
            r3.append(r10)     // Catch:{ Exception -> 0x0357 }
            java.lang.String r2 = " ("
            r3.append(r2)     // Catch:{ Exception -> 0x0357 }
            r3.append(r11)     // Catch:{ Exception -> 0x0357 }
            java.lang.String r2 = ")"
            r3.append(r2)     // Catch:{ Exception -> 0x0357 }
            java.lang.String r2 = r3.toString()     // Catch:{ Exception -> 0x0357 }
            android.util.Log.e(r1, r2)     // Catch:{ Exception -> 0x0357 }
            throw r0     // Catch:{ Exception -> 0x0357 }
        L_0x0352:
            int r9 = r9 + 1
            goto L_0x0017
        L_0x0356:
            return r5
        L_0x0357:
            r0 = move-exception
            com.google.ads.interactivemedia.v3.internal.jm r1 = new com.google.ads.interactivemedia.v3.internal.jm
            r1.<init>(r0, r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.jl.a(com.google.ads.interactivemedia.v3.internal.jl$a, com.google.ads.interactivemedia.v3.internal.jn, java.lang.String):java.util.ArrayList");
    }

    private static Pair<Integer, Integer> a(String str, String[] strArr) {
        int i2;
        int i3;
        if (strArr.length < 2) {
            String str2 = "MediaCodecUtil";
            String str3 = "Ignoring malformed AVC codec string: ";
            String valueOf = String.valueOf(str);
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
        try {
            if (strArr[1].length() == 6) {
                int parseInt = Integer.parseInt(strArr[1].substring(0, 2), 16);
                i2 = Integer.parseInt(strArr[1].substring(4), 16);
                i3 = parseInt;
            } else if (strArr.length >= 3) {
                i3 = Integer.parseInt(strArr[1]);
                i2 = Integer.parseInt(strArr[2]);
            } else {
                String str4 = "MediaCodecUtil";
                String str5 = "Ignoring malformed AVC codec string: ";
                String valueOf2 = String.valueOf(str);
                Log.w(str4, valueOf2.length() != 0 ? str5.concat(valueOf2) : new String(str5));
                return null;
            }
            int i4 = c.get(i3, -1);
            if (i4 == -1) {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Unknown AVC profile: ");
                sb.append(i3);
                Log.w("MediaCodecUtil", sb.toString());
                return null;
            }
            int i5 = d.get(i2, -1);
            if (i5 != -1) {
                return new Pair<>(Integer.valueOf(i4), Integer.valueOf(i5));
            }
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Unknown AVC level: ");
            sb2.append(i2);
            Log.w("MediaCodecUtil", sb2.toString());
            return null;
        } catch (NumberFormatException unused) {
            String str6 = "MediaCodecUtil";
            String str7 = "Ignoring malformed AVC codec string: ";
            String valueOf3 = String.valueOf(str);
            Log.w(str6, valueOf3.length() != 0 ? str7.concat(valueOf3) : new String(str7));
            return null;
        }
    }

    private static Pair<Integer, Integer> b(String str, String[] strArr) {
        if (strArr.length != 3) {
            String str2 = "MediaCodecUtil";
            String str3 = "Ignoring malformed MP4A codec string: ";
            String valueOf = String.valueOf(str);
            Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
        try {
            if (MimeTypes.AUDIO_AAC.equals(un.a(Integer.parseInt(strArr[1], 16)))) {
                int i2 = h.get(Integer.parseInt(strArr[2]), -1);
                if (i2 != -1) {
                    return new Pair<>(Integer.valueOf(i2), Integer.valueOf(0));
                }
            }
        } catch (NumberFormatException unused) {
            String str4 = "MediaCodecUtil";
            String str5 = "Ignoring malformed MP4A codec string: ";
            String valueOf2 = String.valueOf(str);
            Log.w(str4, valueOf2.length() != 0 ? str5.concat(valueOf2) : new String(str5));
        }
        return null;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        c = sparseIntArray;
        sparseIntArray.put(66, 1);
        c.put(77, 2);
        c.put(88, 4);
        c.put(100, 8);
        c.put(110, 16);
        c.put(122, 32);
        c.put(244, 64);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        d = sparseIntArray2;
        sparseIntArray2.put(10, 1);
        d.put(11, 4);
        d.put(12, 8);
        d.put(13, 16);
        d.put(20, 32);
        d.put(21, 64);
        d.put(22, 128);
        d.put(30, 256);
        d.put(31, 512);
        d.put(32, 1024);
        d.put(40, 2048);
        d.put(41, 4096);
        d.put(42, 8192);
        d.put(50, C.DASH_ROLE_CAPTION_FLAG);
        d.put(51, 32768);
        d.put(52, 65536);
        HashMap hashMap = new HashMap();
        e = hashMap;
        hashMap.put("L30", Integer.valueOf(1));
        e.put("L60", Integer.valueOf(4));
        e.put("L63", Integer.valueOf(16));
        e.put("L90", Integer.valueOf(64));
        e.put("L93", Integer.valueOf(256));
        e.put("L120", Integer.valueOf(1024));
        e.put("L123", Integer.valueOf(4096));
        e.put("L150", Integer.valueOf(C.DASH_ROLE_CAPTION_FLAG));
        e.put("L153", Integer.valueOf(65536));
        e.put("L156", Integer.valueOf(C.DASH_ROLE_SUB_FLAG));
        e.put("L180", Integer.valueOf(ExtractorMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES));
        e.put("L183", Integer.valueOf(4194304));
        e.put("L186", Integer.valueOf(com.google.android.exoplayer2.C.DEFAULT_MUXED_BUFFER_SIZE));
        e.put("H30", Integer.valueOf(2));
        e.put("H60", Integer.valueOf(8));
        e.put("H63", Integer.valueOf(32));
        e.put("H90", Integer.valueOf(128));
        e.put("H93", Integer.valueOf(512));
        e.put("H120", Integer.valueOf(2048));
        e.put("H123", Integer.valueOf(8192));
        e.put("H150", Integer.valueOf(32768));
        e.put("H153", Integer.valueOf(131072));
        e.put("H156", Integer.valueOf(524288));
        e.put("H180", Integer.valueOf(2097152));
        e.put("H183", Integer.valueOf(8388608));
        e.put("H186", Integer.valueOf(33554432));
        HashMap hashMap2 = new HashMap();
        f = hashMap2;
        hashMap2.put("00", Integer.valueOf(1));
        f.put("01", Integer.valueOf(2));
        f.put("02", Integer.valueOf(4));
        f.put("03", Integer.valueOf(8));
        f.put("04", Integer.valueOf(16));
        f.put("05", Integer.valueOf(32));
        f.put("06", Integer.valueOf(64));
        f.put("07", Integer.valueOf(128));
        f.put("08", Integer.valueOf(256));
        f.put("09", Integer.valueOf(512));
        HashMap hashMap3 = new HashMap();
        g = hashMap3;
        hashMap3.put("01", Integer.valueOf(1));
        g.put("02", Integer.valueOf(2));
        g.put("03", Integer.valueOf(4));
        g.put("04", Integer.valueOf(8));
        g.put("05", Integer.valueOf(16));
        g.put("06", Integer.valueOf(32));
        g.put("07", Integer.valueOf(64));
        g.put("08", Integer.valueOf(128));
        g.put("09", Integer.valueOf(256));
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        h = sparseIntArray3;
        sparseIntArray3.put(1, 1);
        h.put(2, 2);
        h.put(3, 3);
        h.put(4, 4);
        h.put(5, 5);
        h.put(6, 6);
        h.put(17, 17);
        h.put(20, 20);
        h.put(23, 23);
        h.put(29, 29);
        h.put(39, 39);
        h.put(42, 42);
    }
}
