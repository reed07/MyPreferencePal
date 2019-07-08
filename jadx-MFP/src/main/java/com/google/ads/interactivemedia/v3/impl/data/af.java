package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.UiElement;
import com.google.ads.interactivemedia.v3.internal.afx;
import com.google.ads.interactivemedia.v3.internal.xj;

/* compiled from: IMASDK */
public final class af implements UiElement {
    public static final xj<af> GSON_TYPE_ADAPTER = new ag();
    private final String name;

    public af(String str) {
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof af)) {
            return false;
        }
        return this.name.equals(((af) obj).name);
    }

    public final int hashCode() {
        return afx.a(this.name);
    }

    public final String toString() {
        String str = this.name;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 20);
        sb.append("UiElementImpl[name=");
        sb.append(str);
        sb.append("]");
        return sb.toString();
    }
}
