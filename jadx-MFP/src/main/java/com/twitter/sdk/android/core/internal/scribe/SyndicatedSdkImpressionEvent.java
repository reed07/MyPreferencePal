package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SyndicatedSdkImpressionEvent extends ScribeEvent {
    @SerializedName("device_id_created_at")
    public final long deviceIdCreatedAt = 0;
    @SerializedName("external_ids")
    public final ExternalIds externalIds;
    @SerializedName("language")
    public final String language;

    public class ExternalIds {
        @SerializedName("AD_ID")
        public final String adId;

        public ExternalIds(String str) {
            this.adId = str;
        }
    }

    public SyndicatedSdkImpressionEvent(EventNamespace eventNamespace, long j, String str, String str2, List<ScribeItem> list) {
        super("syndicated_sdk_impression", eventNamespace, j, list);
        this.language = str;
        this.externalIds = new ExternalIds(str2);
    }
}
