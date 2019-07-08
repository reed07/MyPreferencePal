package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

public class VideoViewabilityTrackerXmlManager {
    public static final String PERCENT_VIEWABLE = "percentViewable";
    public static final String VIEWABLE_PLAYTIME = "viewablePlaytime";
    private final Node mVideoViewabilityNode;

    VideoViewabilityTrackerXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node);
        this.mVideoViewabilityNode = node;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0046  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Integer getViewablePlaytimeMS() {
        /*
            r6 = this;
            org.w3c.dom.Node r0 = r6.mVideoViewabilityNode
            java.lang.String r1 = "viewablePlaytime"
            java.lang.String r0 = com.mopub.mobileads.util.XmlUtils.getAttributeValue(r0, r1)
            r1 = 0
            if (r0 != 0) goto L_0x000d
            return r1
        L_0x000d:
            boolean r2 = com.mopub.common.util.Strings.isAbsoluteTracker(r0)
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0028
            java.lang.Integer r0 = com.mopub.common.util.Strings.parseAbsoluteOffset(r0)     // Catch:{ NumberFormatException -> 0x001a }
            goto L_0x0044
        L_0x001a:
            java.lang.String r2 = "Invalid VAST viewablePlaytime format for \"HH:MM:SS[.mmm]\": %s:"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r3] = r0
            java.lang.String r0 = java.lang.String.format(r2, r4)
            com.mopub.common.logging.MoPubLog.d(r0)
            goto L_0x0043
        L_0x0028:
            float r2 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x0036 }
            r5 = 1148846080(0x447a0000, float:1000.0)
            float r2 = r2 * r5
            int r2 = (int) r2     // Catch:{ NumberFormatException -> 0x0036 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)     // Catch:{ NumberFormatException -> 0x0036 }
            goto L_0x0044
        L_0x0036:
            java.lang.String r2 = "Invalid VAST viewablePlaytime format for \"SS[.mmm]\": %s:"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r4[r3] = r0
            java.lang.String r0 = java.lang.String.format(r2, r4)
            com.mopub.common.logging.MoPubLog.d(r0)
        L_0x0043:
            r0 = r1
        L_0x0044:
            if (r0 == 0) goto L_0x004e
            int r2 = r0.intValue()
            if (r2 >= 0) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            return r0
        L_0x004e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VideoViewabilityTrackerXmlManager.getViewablePlaytimeMS():java.lang.Integer");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Integer getPercentViewable() {
        Integer num;
        String attributeValue = XmlUtils.getAttributeValue(this.mVideoViewabilityNode, PERCENT_VIEWABLE);
        if (attributeValue == null) {
            return null;
        }
        try {
            num = Integer.valueOf((int) Float.parseFloat(attributeValue.replace("%", "")));
        } catch (NumberFormatException unused) {
            MoPubLog.d(String.format("Invalid VAST percentViewable format for \"d{1,3}%%\": %s:", new Object[]{attributeValue}));
            num = null;
        }
        if (num == null || num.intValue() < 0 || num.intValue() > 100) {
            return null;
        }
        return num;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getVideoViewabilityTrackerUrl() {
        return XmlUtils.getNodeValue(this.mVideoViewabilityNode);
    }
}
