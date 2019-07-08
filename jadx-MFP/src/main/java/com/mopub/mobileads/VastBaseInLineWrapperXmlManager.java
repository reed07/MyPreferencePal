package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

abstract class VastBaseInLineWrapperXmlManager {
    @NonNull
    protected final Node mNode;

    VastBaseInLineWrapperXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node);
        this.mNode = node;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastTracker> getImpressionTrackers() {
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mNode, "Impression");
        ArrayList arrayList = new ArrayList();
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
    public List<VastTracker> getErrorTrackers() {
        ArrayList arrayList = new ArrayList();
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(this.mNode, "Error");
        if (matchingChildNodes == null) {
            return arrayList;
        }
        for (Node nodeValue : matchingChildNodes) {
            String nodeValue2 = XmlUtils.getNodeValue(nodeValue);
            if (!TextUtils.isEmpty(nodeValue2)) {
                arrayList.add(new VastTracker(nodeValue2, true));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastLinearXmlManager> getLinearXmlManagers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mNode, "Creatives");
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "Creative");
        if (matchingChildNodes == null) {
            return arrayList;
        }
        for (Node firstMatchingChildNode2 : matchingChildNodes) {
            Node firstMatchingChildNode3 = XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode2, "Linear");
            if (firstMatchingChildNode3 != null) {
                arrayList.add(new VastLinearXmlManager(firstMatchingChildNode3));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public List<VastCompanionAdXmlManager> getCompanionAdXmlManagers() {
        ArrayList arrayList = new ArrayList();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mNode, "Creatives");
        if (firstMatchingChildNode == null) {
            return arrayList;
        }
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(firstMatchingChildNode, "Creative");
        if (matchingChildNodes == null) {
            return arrayList;
        }
        for (Node firstMatchingChildNode2 : matchingChildNodes) {
            Node firstMatchingChildNode3 = XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode2, "CompanionAds");
            if (firstMatchingChildNode3 != null) {
                List<Node> matchingChildNodes2 = XmlUtils.getMatchingChildNodes(firstMatchingChildNode3, "Companion");
                if (matchingChildNodes2 != null) {
                    for (Node vastCompanionAdXmlManager : matchingChildNodes2) {
                        arrayList.add(new VastCompanionAdXmlManager(vastCompanionAdXmlManager));
                    }
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public VastExtensionParentXmlManager getVastExtensionParentXmlManager() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mNode, "Extensions");
        if (firstMatchingChildNode == null) {
            return null;
        }
        return new VastExtensionParentXmlManager(firstMatchingChildNode);
    }
}
