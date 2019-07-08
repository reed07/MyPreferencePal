package com.brightcove.player.model;

import com.brightcove.player.util.ErrorUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SourceCollection extends SourceAwareMetadataObject {
    private Set<Source> sources;

    public SourceCollection(Map<String, Object> map) {
        super(map);
    }

    public SourceCollection(Map<String, Object> map, Set<Source> set) {
        super(map);
        if (set != null) {
            this.sources = set;
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.SOURCES_REQUIRED));
    }

    public SourceCollection(Set<Source> set, DeliveryType deliveryType) {
        super(new HashMap());
        if (set != null) {
            this.sources = set;
            initializeDeliveryType(deliveryType);
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.SOURCES_REQUIRED));
    }

    public SourceCollection(Source source, DeliveryType deliveryType) {
        super(new HashMap());
        if (source != null) {
            this.sources = new HashSet();
            this.sources.add(source);
            initializeDeliveryType(deliveryType);
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.SOURCE_REQUIRED));
    }

    public Set<Source> getSources() {
        return this.sources;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SourceCollection{");
        sb.append(" deliveryType:");
        sb.append(getDeliveryType().toString());
        sb.append(" sources:");
        Set<Source> set = this.sources;
        sb.append(set == null ? 0 : set.size());
        sb.append("} ");
        return sb.toString();
    }
}
