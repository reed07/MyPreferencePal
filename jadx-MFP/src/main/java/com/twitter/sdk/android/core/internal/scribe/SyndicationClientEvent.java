package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SyndicationClientEvent extends ScribeEvent {
    @SerializedName("event_info")
    public final String eventInfo;
    @SerializedName("external_ids")
    public final ExternalIds externalIds;
    @SerializedName("language")
    public final String language;

    public class ExternalIds {
        @SerializedName("6")
        public final String adId;

        public ExternalIds(String str) {
            this.adId = str;
        }
    }

    public SyndicationClientEvent(EventNamespace eventNamespace, String str, long j, String str2, String str3, List<ScribeItem> list) {
        super("tfw_client_event", eventNamespace, j, list);
        this.language = str2;
        this.eventInfo = str;
        this.externalIds = new ExternalIds(str3);
    }
}
