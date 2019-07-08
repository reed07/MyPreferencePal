package com.amazon.device.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdRequest.Builder;
import java.util.List;
import java.util.Map.Entry;

public class DTBAdUtil {
    public static final DTBAdUtil INSTANCE = new DTBAdUtil();

    private DTBAdUtil() {
    }

    public Builder createPublisherAdRequestBuilder(DTBAdResponse dTBAdResponse) {
        Builder builder = new Builder();
        if (dTBAdResponse.getAdCount() > 0) {
            loadDTBParameters(dTBAdResponse, builder);
        }
        return builder;
    }

    private void loadDTBParameters(DTBAdResponse dTBAdResponse, Builder builder) {
        for (Entry entry : dTBAdResponse.getDefaultDisplayAdsRequestCustomParams().entrySet()) {
            builder.addCustomTargeting((String) entry.getKey(), (List) entry.getValue());
        }
    }
}
