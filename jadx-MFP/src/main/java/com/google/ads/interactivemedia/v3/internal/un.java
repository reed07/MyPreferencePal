package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import com.google.android.exoplayer2.util.MimeTypes;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.util.ArrayList;

/* compiled from: IMASDK */
public final class un {
    private static final ArrayList<uo> a = new ArrayList<>();

    public static String a(int i) {
        if (i == 35) {
            return MimeTypes.VIDEO_H265;
        }
        if (i != 64) {
            if (i == 163) {
                return MimeTypes.VIDEO_VC1;
            }
            if (i == 177) {
                return MimeTypes.VIDEO_VP9;
            }
            switch (i) {
                case 32:
                    return MimeTypes.VIDEO_MP4V;
                case 33:
                    return MimeTypes.VIDEO_H264;
                default:
                    switch (i) {
                        case 96:
                        case 97:
                        case 98:
                        case 99:
                        case 100:
                        case 101:
                            return MimeTypes.VIDEO_MPEG2;
                        case 102:
                        case 103:
                        case 104:
                            break;
                        case 105:
                        case 107:
                            return MimeTypes.AUDIO_MPEG;
                        case 106:
                            return MimeTypes.VIDEO_MPEG;
                        default:
                            switch (i) {
                                case 165:
                                    return MimeTypes.AUDIO_AC3;
                                case 166:
                                    return MimeTypes.AUDIO_E_AC3;
                                default:
                                    switch (i) {
                                        case 169:
                                        case 172:
                                            return MimeTypes.AUDIO_DTS;
                                        case RequestCodes.CHALLENGES /*170*/:
                                        case RequestCodes.CHALLENGE_DETAIL /*171*/:
                                            return MimeTypes.AUDIO_DTS_HD;
                                        case 173:
                                            return MimeTypes.AUDIO_OPUS;
                                        default:
                                            return null;
                                    }
                            }
                    }
            }
        }
        return MimeTypes.AUDIO_AAC;
    }

    public static boolean a(String str) {
        return MimeTypes.BASE_TYPE_AUDIO.equals(j(str));
    }

    public static boolean b(String str) {
        return "video".equals(j(str));
    }

    public static boolean c(String str) {
        return "text".equals(j(str));
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        for (String f : vf.j(str)) {
            String f2 = f(f);
            if (f2 != null && b(f2)) {
                return f2;
            }
        }
        return null;
    }

    public static String e(String str) {
        if (str == null) {
            return null;
        }
        for (String f : vf.j(str)) {
            String f2 = f(f);
            if (f2 != null && a(f2)) {
                return f2;
            }
        }
        return null;
    }

    public static String f(String str) {
        String str2 = null;
        if (str == null) {
            return null;
        }
        String d = vf.d(str.trim());
        if (d.startsWith("avc1") || d.startsWith("avc3")) {
            return MimeTypes.VIDEO_H264;
        }
        if (d.startsWith("hev1") || d.startsWith("hvc1")) {
            return MimeTypes.VIDEO_H265;
        }
        if (d.startsWith("dvav") || d.startsWith("dva1") || d.startsWith("dvhe") || d.startsWith("dvh1")) {
            return "video/dolby-vision";
        }
        if (d.startsWith("av01")) {
            return "video/av01";
        }
        if (d.startsWith("vp9") || d.startsWith("vp09")) {
            return MimeTypes.VIDEO_VP9;
        }
        if (d.startsWith("vp8") || d.startsWith("vp08")) {
            return MimeTypes.VIDEO_VP8;
        }
        if (d.startsWith("mp4a")) {
            if (d.startsWith("mp4a.")) {
                String substring = d.substring(5);
                if (substring.length() >= 2) {
                    try {
                        str2 = a(Integer.parseInt(vf.e(substring.substring(0, 2)), 16));
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            return str2 == null ? MimeTypes.AUDIO_AAC : str2;
        } else if (d.startsWith("ac-3") || d.startsWith("dac3")) {
            return MimeTypes.AUDIO_AC3;
        } else {
            if (d.startsWith("ec-3") || d.startsWith("dec3")) {
                return MimeTypes.AUDIO_E_AC3;
            }
            if (d.startsWith("ec+3")) {
                return MimeTypes.AUDIO_E_AC3_JOC;
            }
            if (d.startsWith("dtsc") || d.startsWith("dtse")) {
                return MimeTypes.AUDIO_DTS;
            }
            if (d.startsWith("dtsh") || d.startsWith("dtsl")) {
                return MimeTypes.AUDIO_DTS_HD;
            }
            if (d.startsWith("opus")) {
                return MimeTypes.AUDIO_OPUS;
            }
            if (d.startsWith("vorbis")) {
                return MimeTypes.AUDIO_VORBIS;
            }
            if (d.startsWith("flac")) {
                return MimeTypes.AUDIO_FLAC;
            }
            int size = a.size();
            for (int i = 0; i < size; i++) {
                uo uoVar = (uo) a.get(i);
                if (d.startsWith(uoVar.b)) {
                    return uoVar.a;
                }
            }
            return null;
        }
    }

    public static int g(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (a(str)) {
            return 1;
        }
        if (b(str)) {
            return 2;
        }
        if (c(str) || MimeTypes.APPLICATION_CEA608.equals(str) || MimeTypes.APPLICATION_CEA708.equals(str) || MimeTypes.APPLICATION_MP4CEA608.equals(str) || MimeTypes.APPLICATION_SUBRIP.equals(str) || MimeTypes.APPLICATION_TTML.equals(str) || MimeTypes.APPLICATION_TX3G.equals(str) || MimeTypes.APPLICATION_MP4VTT.equals(str) || MimeTypes.APPLICATION_RAWCC.equals(str) || MimeTypes.APPLICATION_VOBSUB.equals(str) || MimeTypes.APPLICATION_PGS.equals(str) || MimeTypes.APPLICATION_DVBSUBS.equals(str)) {
            return 3;
        }
        if (MimeTypes.APPLICATION_ID3.equals(str) || MimeTypes.APPLICATION_EMSG.equals(str) || MimeTypes.APPLICATION_SCTE35.equals(str)) {
            return 4;
        }
        if (MimeTypes.APPLICATION_CAMERA_MOTION.equals(str)) {
            return 5;
        }
        int size = a.size();
        for (int i = 0; i < size; i++) {
            uo uoVar = (uo) a.get(i);
            if (str.equals(uoVar.a)) {
                return uoVar.c;
            }
        }
        return -1;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int h(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = 5
            r2 = 0
            switch(r0) {
                case -2123537834: goto L_0x003c;
                case -1095064472: goto L_0x0032;
                case 187078296: goto L_0x0028;
                case 1504578661: goto L_0x001e;
                case 1505942594: goto L_0x0014;
                case 1556697186: goto L_0x000a;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x0046
        L_0x000a:
            java.lang.String r0 = "audio/true-hd"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 5
            goto L_0x0047
        L_0x0014:
            java.lang.String r0 = "audio/vnd.dts.hd"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 4
            goto L_0x0047
        L_0x001e:
            java.lang.String r0 = "audio/eac3"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 1
            goto L_0x0047
        L_0x0028:
            java.lang.String r0 = "audio/ac3"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 0
            goto L_0x0047
        L_0x0032:
            java.lang.String r0 = "audio/vnd.dts"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 3
            goto L_0x0047
        L_0x003c:
            java.lang.String r0 = "audio/eac3-joc"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0046
            r3 = 2
            goto L_0x0047
        L_0x0046:
            r3 = -1
        L_0x0047:
            switch(r3) {
                case 0: goto L_0x0055;
                case 1: goto L_0x0053;
                case 2: goto L_0x0053;
                case 3: goto L_0x0051;
                case 4: goto L_0x004e;
                case 5: goto L_0x004b;
                default: goto L_0x004a;
            }
        L_0x004a:
            return r2
        L_0x004b:
            r3 = 14
            return r3
        L_0x004e:
            r3 = 8
            return r3
        L_0x0051:
            r3 = 7
            return r3
        L_0x0053:
            r3 = 6
            return r3
        L_0x0055:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.un.h(java.lang.String):int");
    }

    public static int i(String str) {
        return g(f(str));
    }

    private static String j(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf == -1) {
            return null;
        }
        return str.substring(0, indexOf);
    }
}
