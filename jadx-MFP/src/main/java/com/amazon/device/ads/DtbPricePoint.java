package com.amazon.device.ads;

import com.integralads.avid.library.mopub.utils.AvidJSONUtil;

class DtbPricePoint {
    private final DTBAdSize adSize;
    private final String pricePoint;

    DtbPricePoint(String str, String str2, String str3, AdType adType) {
        this.pricePoint = str;
        String[] split = str2.split(AvidJSONUtil.KEY_X);
        if (split.length == 2) {
            this.adSize = new DTBAdSize(DtbCommonUtils.parseInt(split[0], 0), DtbCommonUtils.parseInt(split[1], 0), adType, str3);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("AdSize ");
        sb.append(str2);
        sb.append(" is not valid");
        throw new IllegalArgumentException(sb.toString());
    }

    public String getPricePoint() {
        return this.pricePoint;
    }

    public DTBAdSize getAdSize() {
        return this.adSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DtbPricePoint [pricePoint=");
        sb.append(this.pricePoint);
        sb.append(", adSize=");
        sb.append(this.adSize);
        sb.append("]");
        return sb.toString();
    }
}
