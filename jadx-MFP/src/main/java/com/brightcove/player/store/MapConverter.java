package com.brightcove.player.store;

import android.text.TextUtils;
import com.brightcove.player.util.Convert;
import com.brightcove.player.util.Convert.Lazy;
import io.requery.Converter;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public class MapConverter<K extends Serializable, V extends Serializable> implements Converter<Map<K, V>, String> {
    public Integer getPersistedSize() {
        return null;
    }

    public Class<Map<K, V>> getMappedType() {
        return Collections.emptyMap().getClass();
    }

    public Class<String> getPersistedType() {
        return String.class;
    }

    public String convertToPersisted(Map<K, V> map) {
        if (map == null) {
            return null;
        }
        return Convert.toJsonString(map);
    }

    public Map<K, V> convertToMapped(Class<? extends Map<K, V>> cls, String str) {
        if (TextUtils.isEmpty(str)) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap((Map) Lazy.UTC_GSON.fromJson(str, cls));
    }
}
