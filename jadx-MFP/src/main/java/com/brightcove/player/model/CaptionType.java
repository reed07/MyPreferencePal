package com.brightcove.player.model;

import com.google.android.exoplayer2.util.MimeTypes;
import com.myfitnesspal.shared.constants.Constants.Analytics;

public enum CaptionType {
    UNKNOWN(Analytics.UNKNOWN),
    TTML("application/text+xml"),
    WEBVTT(MimeTypes.TEXT_VTT);
    
    private final String type;

    private CaptionType(String str) {
        this.type = str;
    }

    public String toString() {
        return this.type;
    }

    public static CaptionType fromString(String str) {
        CaptionType[] values;
        if (str != null) {
            for (CaptionType captionType : values()) {
                if (str.equalsIgnoreCase(captionType.type)) {
                    return captionType;
                }
            }
        }
        return null;
    }
}
