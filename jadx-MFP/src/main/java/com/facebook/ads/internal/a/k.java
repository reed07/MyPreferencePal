package com.facebook.ads.internal.a;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.s.f;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import java.util.Map;

class k extends b {
    private static final String d = "k";
    private final Uri e;
    private final Map<String, String> f;

    k(Context context, c cVar, String str, Uri uri, Map<String, String> map) {
        super(context, cVar, str);
        this.e = uri;
        this.f = map;
    }

    public void a() {
        f fVar = f.IMMEDIATE;
        String queryParameter = this.e.getQueryParameter(Columns.PRIORITY);
        if (!TextUtils.isEmpty(queryParameter)) {
            try {
                fVar = f.values()[Integer.valueOf(queryParameter).intValue()];
            } catch (Exception unused) {
            }
        }
        this.b.a(this.c, this.f, this.e.getQueryParameter("type"), fVar);
    }
}
