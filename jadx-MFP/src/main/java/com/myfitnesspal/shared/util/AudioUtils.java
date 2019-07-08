package com.myfitnesspal.shared.util;

import android.content.Context;
import android.media.AudioManager;
import com.google.android.exoplayer2.util.MimeTypes;

public final class AudioUtils {
    public static boolean deviceInSilentOrVibrateMode(Context context) {
        int ringerMode = ((AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO)).getRingerMode();
        return ringerMode == 0 || ringerMode == 1;
    }
}
