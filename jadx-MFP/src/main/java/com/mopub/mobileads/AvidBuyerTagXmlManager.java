package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.w3c.dom.Node;

class AvidBuyerTagXmlManager {
    private final Node mAvidNode;

    AvidBuyerTagXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node);
        this.mAvidNode = node;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Set<String> getJavaScriptResources() {
        HashSet hashSet = new HashSet();
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mAvidNode, VastExtensionXmlManager.AD_VERIFICATIONS);
        if (firstMatchingChildNode == null) {
            return hashSet;
        }
        List<Node> matchingChildNodes = XmlUtils.getMatchingChildNodes(firstMatchingChildNode, VastExtensionXmlManager.VERIFICATION);
        if (matchingChildNodes == null) {
            return hashSet;
        }
        for (Node firstMatchingChildNode2 : matchingChildNodes) {
            Node firstMatchingChildNode3 = XmlUtils.getFirstMatchingChildNode(firstMatchingChildNode2, "JavaScriptResource");
            if (firstMatchingChildNode3 != null) {
                hashSet.add(XmlUtils.getNodeValue(firstMatchingChildNode3));
            }
        }
        return hashSet;
    }
}
