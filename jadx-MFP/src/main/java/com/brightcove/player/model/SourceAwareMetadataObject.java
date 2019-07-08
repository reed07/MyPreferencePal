package com.brightcove.player.model;

import com.brightcove.player.util.ErrorUtil;
import java.util.Map;

public abstract class SourceAwareMetadataObject extends MetadataObject {

    public static final class Fields {
        public static final String DELIVERY_TYPE = "deliveryType";
    }

    public SourceAwareMetadataObject(Map<String, Object> map) {
        super(map);
    }

    /* access modifiers changed from: protected */
    public void initializeDeliveryType(DeliveryType deliveryType) {
        if (deliveryType != null) {
            this.properties.put(Fields.DELIVERY_TYPE, deliveryType);
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.DELIVERY_TYPE_REQUIRED));
    }

    public DeliveryType getDeliveryType() {
        Object obj = this.properties.get(Fields.DELIVERY_TYPE);
        if (obj == null || !(obj instanceof DeliveryType)) {
            return DeliveryType.UNKNOWN;
        }
        return (DeliveryType) obj;
    }
}
