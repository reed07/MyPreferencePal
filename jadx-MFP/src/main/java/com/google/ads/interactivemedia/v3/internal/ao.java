package com.google.ads.interactivemedia.v3.internal;

import android.text.TextUtils;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: IMASDK */
public final class ao extends ai {
    public ao(al alVar, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(alVar, hashSet, jSONObject, d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void onPostExecute(String str) {
        if (!TextUtils.isEmpty(str)) {
            j a = j.a();
            if (a != null) {
                for (e eVar : a.b()) {
                    if (this.a.contains(eVar.f())) {
                        eVar.e().a(str, this.c);
                    }
                }
            }
        }
        super.onPostExecute(str);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        if (z.b(this.b, this.d.a())) {
            return null;
        }
        this.d.a(this.b);
        return this.b.toString();
    }
}
