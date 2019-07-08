package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.Ad;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.google.ads.interactivemedia.v3.api.AdEvent.AdEventType;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: IMASDK */
public final class acd implements AdEvent {
    private AdEventType a;
    private Ad b;
    private Map<String, String> c;

    acd(AdEventType adEventType, Ad ad, Map<String, String> map) {
        this.a = adEventType;
        this.b = ad;
        this.c = map;
    }

    public final AdEventType getType() {
        return this.a;
    }

    public final Ad getAd() {
        return this.b;
    }

    public final Map<String, String> getAdData() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof acd)) {
            return false;
        }
        acd acd = (acd) obj;
        return this.a == acd.a && afx.a((Object) this.b, (Object) acd.b) && afx.a((Object) this.c, (Object) acd.c);
    }

    public final int hashCode() {
        return afx.a(this.a, this.b, this.c);
    }

    public final String toString() {
        String str;
        String str2;
        String valueOf = String.valueOf(String.format("AdEvent[type=%s, ad=%s", new Object[]{this.a, this.b}));
        Map<String, String> map = this.c;
        if (map == null) {
            str = "]";
        } else {
            String str3 = ", adData=%s]";
            Object[] objArr = new Object[1];
            if (map == null) {
                str2 = "";
            } else {
                StringBuilder sb = new StringBuilder("{");
                Iterator it = this.c.entrySet().iterator();
                while (it.hasNext()) {
                    Entry entry = (Entry) it.next();
                    sb.append((String) entry.getKey());
                    sb.append(": ");
                    sb.append((String) entry.getValue());
                    if (it.hasNext()) {
                        sb.append(", ");
                    }
                }
                sb.append("}");
                str2 = sb.toString();
            }
            objArr[0] = str2;
            str = String.format(str3, objArr);
        }
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }
}
