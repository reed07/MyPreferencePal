package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;

public class d {
    private static AdAdapter a;

    public AdAdapter a(AdPlacementType adPlacementType) {
        AdAdapter adAdapter = a;
        if (adAdapter != null) {
            return adAdapter;
        }
        switch (adPlacementType) {
            case BANNER:
                return new e();
            case INTERSTITIAL:
                return new g();
            case NATIVE:
                return new i();
            case NATIVE_BANNER:
                return new j();
            case INSTREAM:
                return new f();
            case REWARDED_VIDEO:
                return new k();
            default:
                return null;
        }
    }
}
