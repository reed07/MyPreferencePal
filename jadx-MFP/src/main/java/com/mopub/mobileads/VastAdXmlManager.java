package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

class VastAdXmlManager {
    @NonNull
    private final Node mAdNode;

    VastAdXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node);
        this.mAdNode = node;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public VastInLineXmlManager getInLineXmlManager() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mAdNode, "InLine");
        if (firstMatchingChildNode != null) {
            return new VastInLineXmlManager(firstMatchingChildNode);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public VastWrapperXmlManager getWrapperXmlManager() {
        Node firstMatchingChildNode = XmlUtils.getFirstMatchingChildNode(this.mAdNode, "Wrapper");
        if (firstMatchingChildNode != null) {
            return new VastWrapperXmlManager(firstMatchingChildNode);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getSequence() {
        return XmlUtils.getAttributeValue(this.mAdNode, "sequence");
    }
}
