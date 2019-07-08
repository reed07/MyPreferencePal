package com.brightcove.player.captioning;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Pair;
import java.util.Iterator;
import java.util.List;

public abstract class BrightcoveCaptionFormat {
    public static final String BRIGHTCOVE_SCHEME = "brightcove://";

    public interface Builder {
        BrightcoveCaptionFormat build();

        Builder hasInBandMetadataTrackDispatchType(boolean z);

        Builder isDefault(boolean z);

        Builder language(String str);

        Builder type(String str);
    }

    public abstract boolean hasInBandMetadataTrackDispatchType();

    public abstract boolean isDefault();

    public abstract String language();

    public abstract String type();

    public static BrightcoveCaptionFormat createCaptionFormat(String str, String str2) {
        return builder().type(str).language(str2).hasInBandMetadataTrackDispatchType(false).isDefault(false).build();
    }

    @TargetApi(19)
    public static MediaFormat convertCaptionFormat(BrightcoveCaptionFormat brightcoveCaptionFormat) {
        return MediaFormat.createSubtitleFormat(brightcoveCaptionFormat.type(), brightcoveCaptionFormat.language());
    }

    public static Builder builder() {
        return new Builder();
    }

    public void validate() {
        if (language().length() < 2) {
            throw new IllegalStateException("Language Code incorrect. ");
        }
    }

    @Nullable
    public static Pair<Uri, BrightcoveCaptionFormat> findMatchingLanguageIgnoreMimeType(List<? extends Pair<Uri, BrightcoveCaptionFormat>> list, BrightcoveCaptionFormat brightcoveCaptionFormat) {
        Pair<Uri, BrightcoveCaptionFormat> pair = null;
        if (brightcoveCaptionFormat == null || brightcoveCaptionFormat.language() == null || list == null || list.isEmpty()) {
            return null;
        }
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Pair<Uri, BrightcoveCaptionFormat> pair2 = (Pair) it.next();
            BrightcoveCaptionFormat brightcoveCaptionFormat2 = (BrightcoveCaptionFormat) pair2.second;
            if (brightcoveCaptionFormat2 != null && brightcoveCaptionFormat2.language().equals(brightcoveCaptionFormat.language())) {
                pair = pair2;
                break;
            }
        }
        return pair;
    }
}
