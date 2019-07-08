package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import com.mopub.mobileads.util.XmlUtils;
import org.w3c.dom.Node;

class VastMediaXmlManager {
    @NonNull
    private final Node mMediaNode;

    VastMediaXmlManager(@NonNull Node node) {
        Preconditions.checkNotNull(node, "mediaNode cannot be null");
        this.mMediaNode = node;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Integer getWidth() {
        return XmlUtils.getAttributeValueAsInt(this.mMediaNode, "width");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public Integer getHeight() {
        return XmlUtils.getAttributeValueAsInt(this.mMediaNode, "height");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getType() {
        return XmlUtils.getAttributeValue(this.mMediaNode, "type");
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getMediaUrl() {
        return XmlUtils.getNodeValue(this.mMediaNode);
    }
}
