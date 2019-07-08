package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.AdError.AdErrorCode;
import com.google.ads.interactivemedia.v3.api.AdError.AdErrorType;
import com.google.ads.interactivemedia.v3.impl.data.CompanionData;
import java.util.Map;

/* compiled from: IMASDK */
public interface adw {
    void a(AdErrorType adErrorType, int i, String str);

    void a(AdErrorType adErrorType, AdErrorCode adErrorCode, String str);

    void a(adv adv);

    void a(Map<String, CompanionData> map);
}
