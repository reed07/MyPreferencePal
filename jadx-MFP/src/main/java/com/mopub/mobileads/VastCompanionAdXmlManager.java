package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.w3c.dom.Node;

class VastCompanionAdXmlManager {
    @NonNull
    private final Node mCompanionNode;
    @NonNull
    private final VastResourceXmlManager mResourceXmlManager;

    VastCompanionAdXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node, "companionNode cannot be null");
        this.mCompanionNode = node;
        this.mResourceXmlManager = new VastResourceXmlManager(node);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Integer getWidth() {
        return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "width");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Integer getHeight() {
        return XmlUtils.getAttributeValueAsInt(this.mCompanionNode, "height");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getAdSlotId() {
        return XmlUtils.getAttributeValue(this.mCompanionNode, "adSlotID");
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public VastResourceXmlManager getResourceXmlManager() {
        return this.mResourceXmlManager;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getClickThroughUrl() {
        return XmlUtils.getNodeValue(XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, "CompanionClickThrough"));
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getClickTrackers() {
        ArrayList arrayList = new ArrayList();
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mCompanionNode, "CompanionClickTracking");
        if (matchingChildNodes == null) {
            return arrayList;
        }
        for (Node nodeValue : matchingChildNodes) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (!TextUtils.isEmpty(nodeValue2)) {
                arrayList.add(new VastTracker(nodeValue2));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getCompanionCreativeViewTrackers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mCompanionNode, "TrackingEvents");
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        for (Node nodeValue : XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "Tracking", "event", Collections.singletonList("creativeView"))) {
            arrayList.add(new VastTracker(XmlUtils.getNodeValue(nodeValue)));
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasResources() {
        return !TextUtils.isEmpty(this.mResourceXmlManager.getStaticResource()) || !TextUtils.isEmpty(this.mResourceXmlManager.getHTMLResource()) || !TextUtils.isEmpty(this.mResourceXmlManager.getIFrameResource());
    }
}
