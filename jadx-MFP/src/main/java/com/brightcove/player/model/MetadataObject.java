package com.brightcove.player.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.brightcove.player.util.ErrorUtil;
import java.io.Serializable;
import java.util.Map;

public abstract class MetadataObject implements Serializable {
    public static final String TAG = "MetadataObject";
    protected Map<String, Object> properties;

    public MetadataObject(Map<String, Object> map) {
        if (map != null) {
            this.properties = map;
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.PROPERTIES_REQUIRED));
    }

    @NonNull
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    public String getStringProperty(String str) {
        return getStringProperty(this.properties, str);
    }

    @Nullable
    public static String getStringProperty(@NonNull Map<String, Object> map, @NonNull String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        Object obj = map.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public Integer getIntegerProperty(String str) {
        return getIntegerProperty(this.properties, str);
    }

    public static Integer getIntegerProperty(Map<String, Object> map, String str) {
        if (map.containsKey(str)) {
            try {
                Object obj = map.get(str);
                if (obj == null) {
                    return null;
                }
                if (obj instanceof Integer) {
                    return (Integer) obj;
                }
                return Integer.valueOf(Integer.parseInt(obj.toString()));
            } catch (NumberFormatException unused) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error converting value of <");
                sb.append(map.get(str));
                sb.append("> for key '");
                sb.append(str);
                sb.append("'");
                Log.e(str2, sb.toString());
                return null;
            }
        } else {
            String str3 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not find property with name '");
            sb2.append(str);
            sb2.append("'");
            Log.w(str3, sb2.toString());
            return null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append("{properties: ");
        sb.append(this.properties.size());
        sb.append('}');
        return sb.toString();
    }
}
