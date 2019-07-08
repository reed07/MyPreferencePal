package com.brightcove.player.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.ErrorUtil;
import com.brightcove.player.util.FileUtil.StrictMode;
import java.util.HashMap;
import java.util.Map;

public class Source extends SourceAwareMetadataObject {
    public static final String EXT_X_VERSION_4 = "4";
    public static final String EXT_X_VERSION_5 = "5";

    public static final class Fields {
        public static final String BIT_RATE = "bitRate";
        public static final String DURATION = "duration";
        public static final String EXT_X_VERSION = "ext_x_version";
        public static final String HEADERS = "headers";
        public static final String KEY_SYSTEMS = "key_systems";
        public static final String LICENSE_URL = "license_url";
        public static final String URL = "url";
        public static final String VMAP = "vmap";
        public static final String WIDEVINE_KEY_SYSTEM = "com.widevine.alpha";
    }

    public Source(Map<String, Object> map) {
        super(map);
    }

    public Source(String str) {
        super(new HashMap());
        initializeUrl(str);
    }

    public Source(String str, DeliveryType deliveryType) {
        this(str);
        initializeDeliveryType(deliveryType);
    }

    private void initializeUrl(String str) {
        if (str != null) {
            this.properties.put("url", str);
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.URL_REQUIRED));
    }

    public boolean isLocal() {
        return StrictMode.isFile(Convert.toString(this.properties.get("url"), null));
    }

    public String getUrl() {
        return (String) this.properties.get("url");
    }

    public Integer getBitRate() {
        Object obj = this.properties.get(Fields.BIT_RATE);
        if (obj == null || !(obj instanceof Integer)) {
            return Integer.valueOf(-1);
        }
        return (Integer) obj;
    }

    public boolean hasKeySystem(@NonNull String str) {
        Map map = (Map) this.properties.get(Fields.KEY_SYSTEMS);
        return map != null && map.containsKey(str);
    }

    @Nullable
    public static String getSourceUrl(@Nullable Source source) {
        if (source == null) {
            return null;
        }
        return source.getUrl();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Source{");
        sb.append("deliveryType: ");
        sb.append(getDeliveryType().toString());
        sb.append(", url: ");
        sb.append(getUrl() == null ? "N/A" : getUrl());
        sb.append("}");
        return sb.toString();
    }
}
