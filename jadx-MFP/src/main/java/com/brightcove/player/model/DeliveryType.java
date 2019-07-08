package com.brightcove.player.model;

import com.google.android.exoplayer2.util.MimeTypes;
import com.myfitnesspal.shared.constants.Constants.Analytics;

public enum DeliveryType {
    UNKNOWN(Analytics.UNKNOWN),
    MP4(MimeTypes.VIDEO_MP4),
    HLS("application/vnd.apple.mpegurl"),
    FLV("video/x-flv"),
    WVM("video/wvm"),
    DASH("video/mpeg-dash");
    
    private final String type;

    private DeliveryType(String str) {
        this.type = str;
    }

    public String toString() {
        return this.type;
    }

    public static DeliveryType getDeliveryTypeByName(String str) {
        DeliveryType deliveryType = UNKNOWN;
        if (str == null) {
            return deliveryType;
        }
        if (MP4.type.equals(str) || MP4.name().equals(str)) {
            return MP4;
        }
        if (HLS.type.equals(str) || HLS.name().equals(str)) {
            return HLS;
        }
        if (FLV.type.equals(str) || FLV.name().equals(str)) {
            return FLV;
        }
        if (WVM.type.equals(str) || WVM.name().equals(str)) {
            return WVM;
        }
        if (DASH.type.equals(str) || DASH.name().equals(str)) {
            return DASH;
        }
        return deliveryType;
    }
}
