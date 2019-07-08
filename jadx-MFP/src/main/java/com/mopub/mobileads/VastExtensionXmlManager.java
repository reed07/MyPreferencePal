package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.w3c.dom.Node;

public class VastExtensionXmlManager {
    public static final String AD_VERIFICATIONS = "AdVerifications";
    public static final String AVID = "AVID";
    public static final String ID = "id";
    public static final String MOAT = "Moat";
    public static final String TYPE = "type";
    public static final String VENDOR = "vendor";
    public static final String VERIFICATION = "Verification";
    public static final String VIDEO_VIEWABILITY_TRACKER = "MoPubViewabilityTracker";
    private final Node mExtensionNode;

    public VastExtensionXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node);
        this.mExtensionNode = node;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public VideoViewabilityTracker getVideoViewabilityTracker() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mExtensionNode, VIDEO_VIEWABILITY_TRACKER);
        if (firstMatchingChildNode == null) {
            return null;
        }
        VideoViewabilityTrackerXmlManager videoViewabilityTrackerXmlManager = new VideoViewabilityTrackerXmlManager(firstMatchingChildNode);
        Integer viewablePlaytimeMS = videoViewabilityTrackerXmlManager.getViewablePlaytimeMS();
        Integer percentViewable = videoViewabilityTrackerXmlManager.getPercentViewable();
        String videoViewabilityTrackerUrl = videoViewabilityTrackerXmlManager.getVideoViewabilityTrackerUrl();
        if (viewablePlaytimeMS == null || percentViewable == null || TextUtils.isEmpty(videoViewabilityTrackerUrl)) {
            return null;
        }
        return new VideoViewabilityTracker(viewablePlaytimeMS.intValue(), percentViewable.intValue(), videoViewabilityTrackerUrl);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Set<String> getAvidJavaScriptResources() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mExtensionNode, AVID);
        if (firstMatchingChildNode == null) {
            return null;
        }
        return new AvidBuyerTagXmlManager(firstMatchingChildNode).getJavaScriptResources();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Set<String> getMoatImpressionPixels() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mExtensionNode, AD_VERIFICATIONS);
        if (firstMatchingChildNode == null) {
            return null;
        }
        List matchingChildNodes = XmlUtils.getMatchingChildNodes(firstMatchingChildNode, VERIFICATION, VENDOR, Collections.singletonList(MOAT));
        if (matchingChildNodes == null || matchingChildNodes.isEmpty()) {
            return null;
        }
        return new MoatBuyerTagXmlManager(matchingChildNodes).getImpressionPixelsXml();
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getType() {
        return XmlUtils.getAttributeValue(this.mExtensionNode, "type");
    }
}
