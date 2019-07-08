package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.event.EventType;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Strings;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Node;

class VastLinearXmlManager {
    public static final String ICON = "Icon";
    public static final String ICONS = "Icons";
    @NonNull
    private final Node mLinearNode;

    VastLinearXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node);
        this.mLinearNode = node;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastFractionalProgressTracker> getFractionalProgressTrackers() {
        ArrayList arrayList = new ArrayList();
        addQuartileTrackerWithFraction(arrayList, getVideoTrackersByAttribute("firstQuartile"), 0.25f);
        addQuartileTrackerWithFraction(arrayList, getVideoTrackersByAttribute("midpoint"), 0.5f);
        addQuartileTrackerWithFraction(arrayList, getVideoTrackersByAttribute("thirdQuartile"), 0.75f);
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, "TrackingEvents");
        if (firstMatchingChildNode != null) {
            for (Node node : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "Tracking", "event", Collections.singletonList("progress"))) {
                String attributeValue = XmlUtils.getAttributeValue(node, VastIconXmlManager.OFFSET);
                if (attributeValue != null) {
                    String trim = attributeValue.trim();
                    if (Strings.isPercentageTracker(trim)) {
                        String nodeValue = XmlUtils.getNodeValue(node);
                        try {
                            float parseFloat = Float.parseFloat(trim.replace("%", "")) / 100.0f;
                            if (parseFloat >= BitmapDescriptorFactory.HUE_RED) {
                                arrayList.add(new VastFractionalProgressTracker(nodeValue, parseFloat));
                            }
                        } catch (NumberFormatException unused) {
                            MoPubLog.d(String.format("Failed to parse VAST progress tracker %s", new Object[]{trim}));
                        }
                    }
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastAbsoluteProgressTracker> getAbsoluteProgressTrackers() {
        ArrayList arrayList = new ArrayList();
        for (String vastAbsoluteProgressTracker : getVideoTrackersByAttribute(TtmlNode.START)) {
            arrayList.add(new VastAbsoluteProgressTracker(vastAbsoluteProgressTracker, 0));
        }
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, "TrackingEvents");
        if (firstMatchingChildNode != null) {
            for (Node node : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "Tracking", "event", Collections.singletonList("progress"))) {
                String attributeValue = XmlUtils.getAttributeValue(node, VastIconXmlManager.OFFSET);
                if (attributeValue != null) {
                    String trim = attributeValue.trim();
                    if (Strings.isAbsoluteTracker(trim)) {
                        String nodeValue = XmlUtils.getNodeValue(node);
                        try {
                            Integer parseAbsoluteOffset = Strings.parseAbsoluteOffset(trim);
                            if (parseAbsoluteOffset != null && parseAbsoluteOffset.intValue() >= 0) {
                                arrayList.add(new VastAbsoluteProgressTracker(nodeValue, parseAbsoluteOffset.intValue()));
                            }
                        } catch (NumberFormatException unused) {
                            MoPubLog.d(String.format("Failed to parse VAST progress tracker %s", new Object[]{trim}));
                        }
                    }
                }
            }
            for (Node nodeValue2 : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "Tracking", "event", Collections.singletonList("creativeView"))) {
                String nodeValue3 = XmlUtils.getNodeValue(nodeValue2);
                if (nodeValue3 != null) {
                    arrayList.add(new VastAbsoluteProgressTracker(nodeValue3, 0));
                }
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getVideoCompleteTrackers() {
        return getVideoTrackersByAttributeAsVastTrackers("complete");
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getPauseTrackers() {
        List<String> videoTrackersByAttribute = getVideoTrackersByAttribute(EventType.PAUSE);
        ArrayList arrayList = new ArrayList();
        for (String vastTracker : videoTrackersByAttribute) {
            arrayList.add(new VastTracker(vastTracker, true));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getResumeTrackers() {
        List<String> videoTrackersByAttribute = getVideoTrackersByAttribute("resume");
        ArrayList arrayList = new ArrayList();
        for (String vastTracker : videoTrackersByAttribute) {
            arrayList.add(new VastTracker(vastTracker, true));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getVideoCloseTrackers() {
        List<VastTracker> videoTrackersByAttributeAsVastTrackers = getVideoTrackersByAttributeAsVastTrackers("close");
        videoTrackersByAttributeAsVastTrackers.addAll(getVideoTrackersByAttributeAsVastTrackers("closeLinear"));
        return videoTrackersByAttributeAsVastTrackers;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getVideoSkipTrackers() {
        return getVideoTrackersByAttributeAsVastTrackers("skip");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getClickThroughUrl() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, "VideoClicks");
        if (firstMatchingChildNode == null) {
            return null;
        }
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode, "ClickThrough"));
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getClickTrackers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, "VideoClicks");
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node nodeValue : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "ClickTracking")) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (nodeValue2 != null) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getSkipOffset() {
        String attributeValue = XmlUtils.getAttributeValue(this.mLinearNode, "skipoffset");
        if (attributeValue != null && !attributeValue.trim().isEmpty()) {
            return attributeValue.trim();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastMediaXmlManager> getMediaXmlManagers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, "MediaFiles");
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node vastMediaXmlManager : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "MediaFile")) {
            arrayList.add(new VastMediaXmlManager(vastMediaXmlManager));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastIconXmlManager> getIconXmlManagers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, ICONS);
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node vastIconXmlManager : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, ICON)) {
            arrayList.add(new VastIconXmlManager(vastIconXmlManager));
        }
        return arrayList;
    }

    @NonNull
    private List<VastTracker> getVideoTrackersByAttributeAsVastTrackers(@NonNull String str) {
        List<String> videoTrackersByAttribute = getVideoTrackersByAttribute(str);
        ArrayList arrayList = new ArrayList(videoTrackersByAttribute.size());
        for (String vastTracker : videoTrackersByAttribute) {
            arrayList.add(new VastTracker(vastTracker));
        }
        return arrayList;
    }

    @NonNull
    private List<String> getVideoTrackersByAttribute(@NonNull String str) {
        Preconditions.checkNotNull(str);
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mLinearNode, "TrackingEvents");
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node nodeValue : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "Tracking", "event", Collections.singletonList(str))) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (nodeValue2 != null) {
                arrayList.add(nodeValue2);
            }
        }
        return arrayList;
    }

    private void addQuartileTrackerWithFraction(@NonNull List<VastFractionalProgressTracker> list, @NonNull List<String> list2, float f) {
        Preconditions.checkNotNull(list, "trackers cannot be null");
        Preconditions.checkNotNull(list2, "urls cannot be null");
        for (String vastFractionalProgressTracker : list2) {
            list.add(new VastFractionalProgressTracker(vastFractionalProgressTracker, f));
        }
    }
}
