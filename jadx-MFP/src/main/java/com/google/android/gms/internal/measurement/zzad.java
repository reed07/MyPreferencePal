package com.google.android.gms.internal.measurement;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@ShowFirstParty
public final class zzad extends zzi<zzad> {
    private ProductAction zzrt;
    private final Map<String, List<Product>> zzru = new HashMap();
    private final List<Promotion> zzrv = new ArrayList();
    private final List<Product> zzrw = new ArrayList();

    public final ProductAction zzax() {
        return this.zzrt;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zzrw.isEmpty()) {
            hashMap.put("products", this.zzrw);
        }
        if (!this.zzrv.isEmpty()) {
            hashMap.put("promotions", this.zzrv);
        }
        if (!this.zzru.isEmpty()) {
            hashMap.put("impressions", this.zzru);
        }
        hashMap.put("productAction", this.zzrt);
        return zza((Object) hashMap);
    }

    public final List<Product> zzay() {
        return Collections.unmodifiableList(this.zzrw);
    }

    public final Map<String, List<Product>> zzaz() {
        return this.zzru;
    }

    public final List<Promotion> zzba() {
        return Collections.unmodifiableList(this.zzrv);
    }

    public final /* synthetic */ void zzb(zzi zzi) {
        zzad zzad = (zzad) zzi;
        zzad.zzrw.addAll(this.zzrw);
        zzad.zzrv.addAll(this.zzrv);
        for (Entry entry : this.zzru.entrySet()) {
            String str = (String) entry.getKey();
            for (Product product : (List) entry.getValue()) {
                if (product != null) {
                    String str2 = str == null ? "" : str;
                    if (!zzad.zzru.containsKey(str2)) {
                        zzad.zzru.put(str2, new ArrayList());
                    }
                    ((List) zzad.zzru.get(str2)).add(product);
                }
            }
        }
        ProductAction productAction = this.zzrt;
        if (productAction != null) {
            zzad.zzrt = productAction;
        }
    }
}
