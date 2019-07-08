package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;

public final class zzpt {
    public static boolean zzab(String str) {
        return MimeTypes.BASE_TYPE_AUDIO.equals(zzaf(str));
    }

    public static boolean zzac(String str) {
        return "video".equals(zzaf(str));
    }

    public static boolean zzad(String str) {
        return "text".equals(zzaf(str));
    }

    public static String zzae(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("avc1") || trim.startsWith("avc3")) {
            return MimeTypes.VIDEO_H264;
        }
        if (trim.startsWith("hev1") || trim.startsWith("hvc1")) {
            return MimeTypes.VIDEO_H265;
        }
        if (trim.startsWith("vp9")) {
            return MimeTypes.VIDEO_VP9;
        }
        if (trim.startsWith("vp8")) {
            return MimeTypes.VIDEO_VP8;
        }
        if (trim.startsWith("mp4a")) {
            return MimeTypes.AUDIO_AAC;
        }
        if (trim.startsWith("ac-3") || trim.startsWith("dac3")) {
            return MimeTypes.AUDIO_AC3;
        }
        if (trim.startsWith("ec-3") || trim.startsWith("dec3")) {
            return MimeTypes.AUDIO_E_AC3;
        }
        if (trim.startsWith("dtsc") || trim.startsWith("dtse")) {
            return MimeTypes.AUDIO_DTS;
        }
        if (trim.startsWith("dtsh") || trim.startsWith("dtsl")) {
            return MimeTypes.AUDIO_DTS_HD;
        }
        if (trim.startsWith("opus")) {
            return MimeTypes.AUDIO_OPUS;
        }
        if (trim.startsWith("vorbis")) {
            return MimeTypes.AUDIO_VORBIS;
        }
        return null;
    }

    private static String zzaf(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(47);
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        String str2 = "Invalid mime type: ";
        String valueOf = String.valueOf(str);
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
