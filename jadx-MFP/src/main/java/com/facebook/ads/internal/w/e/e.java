package com.facebook.ads.internal.w.e;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.v.a.n;
import com.facebook.ads.internal.v.a.p;
import com.facebook.ads.internal.w.b.k;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class e extends AsyncTask<String, Void, f> {
    private static final String a = "e";
    private static final Set<String> b = new HashSet();
    private Context c;
    private Map<String, String> d;
    private Map<String, String> e;
    private n f;
    private a g;

    public interface a {
        void a();

        void a(f fVar);
    }

    static {
        b.add("#");
        b.add("null");
    }

    public e(Context context) {
        this(context, null, null);
    }

    public e(Context context, Map<String, String> map) {
        this(context, map, null);
    }

    public e(Context context, Map<String, String> map, Map<String, String> map2) {
        this.c = context;
        HashMap hashMap = null;
        this.d = map != null ? new HashMap<>(map) : null;
        if (map2 != null) {
            hashMap = new HashMap(map2);
        }
        this.e = hashMap;
    }

    private String a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return str;
        }
        String str4 = str.contains("?") ? "&" : "?";
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str4);
        sb.append(str2);
        sb.append("=");
        sb.append(URLEncoder.encode(str3));
        return sb.toString();
    }

    private boolean a(String str) {
        n a2;
        com.facebook.ads.internal.v.a.a a3 = d.a(this.c);
        boolean z = false;
        try {
            if (this.e != null) {
                if (this.e.size() != 0) {
                    p pVar = new p();
                    pVar.a(this.e);
                    a2 = a3.b(str, pVar);
                    this.f = a2;
                    if (this.f != null && this.f.a() == 200) {
                        z = true;
                    }
                    return z;
                }
            }
            a2 = a3.a(str, (p) null);
            this.f = a2;
            z = true;
            return z;
        } catch (Exception e2) {
            String str2 = a;
            StringBuilder sb = new StringBuilder();
            sb.append("Error opening url: ");
            sb.append(str);
            Log.e(str2, sb.toString(), e2);
            return false;
        }
    }

    private String b(String str) {
        try {
            return a(str, "analog", k.a(com.facebook.ads.internal.l.a.a()));
        } catch (Exception unused) {
            return str;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public f doInBackground(String... strArr) {
        String str = strArr[0];
        if (!TextUtils.isEmpty(str) && !b.contains(str)) {
            String b2 = b(str);
            Map<String, String> map = this.d;
            if (map != null && !map.isEmpty()) {
                for (Entry entry : this.d.entrySet()) {
                    b2 = a(b2, (String) entry.getKey(), (String) entry.getValue());
                }
            }
            int i = 1;
            while (true) {
                int i2 = i + 1;
                if (i > 2) {
                    break;
                } else if (a(b2)) {
                    return new f(this.f);
                } else {
                    i = i2;
                }
            }
        }
        return null;
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(f fVar) {
        a aVar = this.g;
        if (aVar != null) {
            aVar.a(fVar);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        a aVar = this.g;
        if (aVar != null) {
            aVar.a();
        }
    }
}
