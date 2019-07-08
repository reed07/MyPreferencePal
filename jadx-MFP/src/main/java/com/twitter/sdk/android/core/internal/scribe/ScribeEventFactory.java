package com.twitter.sdk.android.core.internal.scribe;

import java.util.List;

public class ScribeEventFactory {
    public static ScribeEvent newScribeEvent(EventNamespace eventNamespace, String str, long j, String str2, String str3, List<ScribeItem> list) {
        String str4 = eventNamespace.client;
        if (((str4.hashCode() == 114757 && str4.equals("tfw")) ? (char) 0 : 65535) != 0) {
            SyndicatedSdkImpressionEvent syndicatedSdkImpressionEvent = new SyndicatedSdkImpressionEvent(eventNamespace, j, str2, str3, list);
            return syndicatedSdkImpressionEvent;
        }
        SyndicationClientEvent syndicationClientEvent = new SyndicationClientEvent(eventNamespace, str, j, str2, str3, list);
        return syndicationClientEvent;
    }
}
