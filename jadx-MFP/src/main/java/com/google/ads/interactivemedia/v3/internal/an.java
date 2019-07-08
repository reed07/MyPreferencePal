package com.google.ads.interactivemedia.v3.internal;

import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: IMASDK */
public final class an extends ai {
    public an(al alVar, HashSet<String> hashSet, JSONObject jSONObject, double d) {
        super(alVar, hashSet, jSONObject, d);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void onPostExecute(String str) {
        j a = j.a();
        if (a != null) {
            for (e eVar : a.b()) {
                if (this.a.contains(eVar.f())) {
                    eVar.e().b(str, this.c);
                }
            }
        }
        super.onPostExecute(str);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        return this.b.toString();
    }
}
