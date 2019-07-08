package com.facebook.ads.internal.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.w.e.g;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends h {
    private static final String e = "f";
    private final Uri f;
    private final Map<String, String> g;
    private final boolean h;

    public f(Context context, c cVar, String str, Uri uri, Map<String, String> map, m mVar, boolean z) {
        super(context, cVar, str, mVar);
        this.f = uri;
        this.g = map;
        this.h = z;
    }

    private Intent a(g gVar) {
        if (TextUtils.isEmpty(gVar.a()) || !e.a(this.a, gVar.a())) {
            return null;
        }
        String c = gVar.c();
        if (!TextUtils.isEmpty(c) && (c.startsWith("tel:") || c.startsWith("telprompt:"))) {
            return new Intent("android.intent.action.CALL", Uri.parse(c));
        }
        PackageManager packageManager = this.a.getPackageManager();
        if (TextUtils.isEmpty(gVar.b()) && TextUtils.isEmpty(c)) {
            return packageManager.getLaunchIntentForPackage(gVar.a());
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        if (!TextUtils.isEmpty(gVar.a()) && !TextUtils.isEmpty(gVar.b())) {
            intent.setComponent(new ComponentName(gVar.a(), gVar.b()));
        }
        if (!TextUtils.isEmpty(gVar.c())) {
            intent.setData(Uri.parse(gVar.c()));
        }
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (intent.getComponent() == null) {
            Iterator it = queryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo resolveInfo = (ResolveInfo) it.next();
                if (resolveInfo.activityInfo.packageName.equals(gVar.a())) {
                    intent.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    break;
                }
            }
        }
        if (queryIntentActivities.isEmpty() || intent.getComponent() == null) {
            return null;
        }
        return intent;
    }

    private List<g> f() {
        String queryParameter = this.f.getQueryParameter("appsite_data");
        if (TextUtils.isEmpty(queryParameter) || "[]".equals(queryParameter)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(queryParameter).optJSONArray("android");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    g a = g.a(optJSONArray.optJSONObject(i));
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            }
        } catch (JSONException e2) {
            Log.w(e, "Error parsing appsite_data", e2);
        }
        return arrayList;
    }

    private boolean g() {
        List<Intent> d = d();
        if (d == null) {
            return false;
        }
        for (Intent startActivity : d) {
            try {
                this.a.startActivity(startActivity);
                return true;
            } catch (Exception e2) {
                Log.d(e, "Failed to open app intent, falling back", e2);
            }
        }
        return false;
    }

    private boolean h() {
        g gVar = new g();
        try {
            g.a(gVar, this.a, c(), this.c);
            return true;
        } catch (Exception e2) {
            String str = e;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to open market url: ");
            sb.append(this.f.toString());
            Log.d(str, sb.toString(), e2);
            String queryParameter = this.f.getQueryParameter("store_url_web_fallback");
            if (queryParameter != null && queryParameter.length() > 0) {
                g.a(gVar, this.a, Uri.parse(queryParameter), this.c);
            }
            return false;
        }
    }

    @Nullable
    public a b() {
        String str = "opened_deeplink";
        a aVar = null;
        if (!g()) {
            try {
                str = h() ? "opened_store_url" : "opened_store_fallback_url";
            } catch (Exception unused) {
                Log.d(e, "Failed to open all options including fallback url, can't open anything");
                aVar = a.CANNOT_OPEN;
            }
        }
        this.g.put(str, String.valueOf(true));
        return aVar;
    }

    /* access modifiers changed from: protected */
    public Uri c() {
        String queryParameter = this.f.getQueryParameter("store_url");
        if (!TextUtils.isEmpty(queryParameter)) {
            return Uri.parse(queryParameter);
        }
        return Uri.parse(String.format(Locale.US, "market://details?id=%s", new Object[]{this.f.getQueryParameter("store_id")}));
    }

    /* access modifiers changed from: protected */
    public List<Intent> d() {
        List<g> f2 = f();
        ArrayList arrayList = new ArrayList();
        if (f2 != null) {
            for (g a : f2) {
                Intent a2 = a(a);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public void e() {
        a aVar;
        if (!this.h) {
            aVar = b();
        } else {
            this.g.put("opened_store_url", String.valueOf(true));
            aVar = null;
        }
        a(this.g, aVar);
    }
}
