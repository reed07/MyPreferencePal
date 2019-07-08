package com.myfitnesspal.shared.model.v1;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class UserV1PropertyBag {
    private Map<String, Float> properties = new HashMap();

    /* access modifiers changed from: protected */
    public abstract Set<String> getPropertyKeyWhitelist();

    public boolean contains(String str) {
        return this.properties.get(str) != null;
    }

    public float get(String str) {
        Float f = (Float) this.properties.get(str);
        return f != null ? f.floatValue() : BitmapDescriptorFactory.HUE_RED;
    }

    public void readKeysAndValues(List<String> list, List<String> list2) {
        for (String str : getPropertyKeyWhitelist()) {
            if (contains(str)) {
                list.add(str);
                list2.add(String.valueOf(get(str)));
            }
        }
    }

    public void writeKeyAndValue(String str, String str2) {
        if (getPropertyKeyWhitelist().contains(str) && Strings.notEmpty(str2)) {
            try {
                set(str, Float.valueOf(str2).floatValue());
            } catch (NumberFormatException e) {
                Ln.e(e, "UserV1PropertyBag.writeKeyAndValue failed. key: %s, value: %s", str, str2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void set(String str, float f) {
        this.properties.put(str, Float.valueOf(f));
    }
}
