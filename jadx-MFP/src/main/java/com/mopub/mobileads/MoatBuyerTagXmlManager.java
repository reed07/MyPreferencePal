package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.w3c.dom.Node;

class MoatBuyerTagXmlManager {
    private final List<Node> mMoatVerificationNodes;

    MoatBuyerTagXmlManager(@NonNull List<Node> list) {
        Preconditions.checkNotNull(list);
        this.mMoatVerificationNodes = list;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Set<String> getImpressionPixelsXml() {
        HashSet hashSet = new HashSet();
        for (Node node : this.mMoatVerificationNodes) {
            if (node != null) {
                String viewableImpressionXml = getViewableImpressionXml(XmlUtils.getFirstMatchingChildNode(node, "ViewableImpression"));
                if (viewableImpressionXml != null) {
                    hashSet.add(viewableImpressionXml);
                }
            }
        }
        return hashSet;
    }

    @Nullable
    private String getViewableImpressionXml(@Nullable Node node) {
        if (node == null || !node.hasAttributes()) {
            return null;
        }
        return String.format(Locale.US, "<ViewableImpression id=\"%s\"><![CDATA[%s]]</ViewableImpression>", new Object[]{XmlUtils.getAttributeValue(node, "id"), XmlUtils.getNodeValue(node)});
    }
}
