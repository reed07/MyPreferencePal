package com.inmobi.ads;

import android.content.ContentValues;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.commons.core.d.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PlacementDao */
public class bj {
    public static final String[] a = {"id", "placement_id", "tp_key", "last_accessed_ts", AppEventsConstants.EVENT_PARAM_AD_TYPE, "m10_context"};
    private static final String b = "bj";
    private static bj c;
    private static final Object d = new Object();

    public static bj a() {
        bj bjVar = c;
        if (bjVar == null) {
            synchronized (d) {
                bjVar = c;
                if (bjVar == null) {
                    bjVar = new bj();
                    c = bjVar;
                }
            }
        }
        return bjVar;
    }

    private bj() {
        b a2 = b.a();
        a2.a("placement", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL,tp_key TEXT,last_accessed_ts INTEGER NOT NULL,ad_type TEXT NOT NULL,m10_context TEXT NOT NULL,UNIQUE(placement_id,m10_context,tp_key))");
        a2.b();
    }

    public static int a(long j, String str) {
        b a2 = b.a();
        int a3 = a2.a("placement", "ad_type=? AND last_accessed_ts<?", new String[]{str, String.valueOf(System.currentTimeMillis() - (j * 1000))});
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(a3);
        sb.append(" expired pids from cache");
        a2.b();
        return a3;
    }

    public final synchronized int a(List<bi> list, int i) {
        if (list.size() == 0) {
            return 0;
        }
        b a2 = b.a();
        for (int i2 = 0; i2 < list.size(); i2++) {
            bi biVar = (bi) list.get(i2);
            String[] strArr = {String.valueOf(biVar.a), biVar.f.toString(), biVar.b};
            ContentValues contentValues = new ContentValues();
            contentValues.put("placement_id", Long.valueOf(biVar.a));
            contentValues.put("last_accessed_ts", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("tp_key", biVar.b);
            contentValues.put(AppEventsConstants.EVENT_PARAM_AD_TYPE, biVar.e);
            contentValues.put("m10_context", biVar.f.toString());
            a2.a("placement", contentValues, "placement_id = ? AND m10_context = ? AND tp_key=?", strArr);
        }
        int a3 = a2.a("placement") - i;
        if (a3 > 0) {
            List a4 = a2.a("placement", new String[]{"id"}, null, null, null, null, "last_accessed_ts ASC", String.valueOf(a3));
            String[] strArr2 = new String[a4.size()];
            for (int i3 = 0; i3 < a4.size(); i3++) {
                strArr2[i3] = String.valueOf(((ContentValues) a4.get(i3)).getAsInteger("id"));
            }
            String replace = Arrays.toString(strArr2).replace("[", "(").replace("]", ")");
            StringBuilder sb = new StringBuilder("id IN ");
            sb.append(replace);
            a2.a("placement", sb.toString(), null);
        }
        a2.b();
        return a3;
    }

    public static List<bi> a(String str) {
        ArrayList arrayList = new ArrayList();
        b a2 = b.a();
        b bVar = a2;
        List<ContentValues> a3 = bVar.a("placement", a, "ad_type=? ", new String[]{str}, null, null, null, null);
        a2.b();
        for (ContentValues biVar : a3) {
            arrayList.add(new bi(biVar));
        }
        return arrayList;
    }
}
