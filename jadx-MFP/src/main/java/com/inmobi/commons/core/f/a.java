package com.inmobi.commons.core.f;

import android.content.ContentValues;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.commons.core.b.b;
import com.inmobi.commons.core.d.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: TRCDao */
public class a extends b {
    private static final String a = "a";

    public a() {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        a2.a("trc", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, eventId TEXT NOT NULL, adMarkup TEXT NOT NULL, eventName TEXT NOT NULL, imPlid INTEGER NOT NULL, requestId TEXT NOT NULL, eventType TEXT NOT NULL, dNettypeRaw TEXT NOT NULL, ts TEXT NOT NULL, adtype TEXT NOT NULL, timestamp TEXT NOT NULL)");
        a2.b();
    }

    public static void a(b bVar) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", bVar.b);
        contentValues.put("adMarkup", bVar.c);
        contentValues.put("eventName", bVar.d);
        contentValues.put("imPlid", Long.valueOf(bVar.e));
        contentValues.put("requestId", bVar.f);
        contentValues.put("eventType", bVar.g);
        contentValues.put("dNettypeRaw", bVar.h);
        contentValues.put("ts", String.valueOf(bVar.i));
        contentValues.put("adtype", bVar.j);
        contentValues.put("timestamp", String.valueOf(bVar.k));
        a2.a("trc", contentValues);
        a2.b();
    }

    public static List<b> a(int i, String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        com.inmobi.commons.core.d.b bVar = a2;
        List<ContentValues> a3 = bVar.a("trc", null, "adtype=?", new String[]{str}, null, null, "timestamp ASC", String.valueOf(i));
        a2.b();
        ArrayList arrayList = new ArrayList();
        for (ContentValues a4 : a3) {
            arrayList.add(b.a(a4));
        }
        return arrayList;
    }

    public final boolean a(long j, String str) {
        List a2 = a(1, str);
        if (a2.size() <= 0 || TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - ((b) a2.get(0)).i) <= j) {
            return false;
        }
        return true;
    }

    public static boolean c(String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        int b = a2.b("trc", "adtype=?", new String[]{str});
        a2.b();
        if (b > 0) {
            return true;
        }
        return false;
    }

    public static void d(String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        com.inmobi.commons.core.d.b bVar = a2;
        List a3 = bVar.a("trc", null, "adtype=?", new String[]{str}, null, null, "timestamp ASC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        if (!a3.isEmpty()) {
            String asString = ((ContentValues) a3.get(0)).getAsString("id");
            StringBuilder sb = new StringBuilder("id IN (");
            sb.append(asString);
            sb.append(")");
            a2.a("trc", sb.toString(), null);
        }
        a2.b();
    }

    public final void a(List<Integer> list) {
        if (!list.isEmpty()) {
            com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
            StringBuffer stringBuffer = new StringBuffer("");
            for (int i = 0; i < list.size() - 1; i++) {
                stringBuffer.append(list.get(i));
                stringBuffer.append(",");
            }
            stringBuffer.append(String.valueOf(list.get(list.size() - 1)));
            StringBuilder sb = new StringBuilder("id IN (");
            sb.append(stringBuffer);
            sb.append(")");
            a2.a("trc", sb.toString(), null);
            a2.b();
        }
    }

    public final int a(String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        int b = a2.b("trc", "adtype=?", new String[]{str});
        a2.b();
        return b;
    }

    public final int b(long j, String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        int a3 = a2.a("trc", "ts<? and adtype=?", new String[]{String.valueOf(System.currentTimeMillis() - (j * 1000)), str});
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(a3);
        sb.append(" expired events from trc DB");
        a2.b();
        return a3;
    }

    public final String b(int i) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        com.inmobi.commons.core.d.b bVar = a2;
        List a3 = bVar.a("trc", null, "id=?", new String[]{String.valueOf(i)}, null, null, "timestamp ASC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        a2.b();
        if (!a3.isEmpty()) {
            return b.a((ContentValues) a3.get(0)).j;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String e(java.lang.String r2) {
        /*
            int r0 = r2.hashCode()
            r1 = -1396342996(0xffffffffacc57f2c, float:-5.6131957E-12)
            if (r0 == r1) goto L_0x0028
            r1 = -1052618729(0xffffffffc1425017, float:-12.144553)
            if (r0 == r1) goto L_0x001e
            r1 = 104431(0x197ef, float:1.46339E-40)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "int"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0032
            r2 = 1
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "native"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0032
            r2 = 2
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "banner"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0032
            r2 = 0
            goto L_0x0033
        L_0x0032:
            r2 = -1
        L_0x0033:
            switch(r2) {
                case 0: goto L_0x003c;
                case 1: goto L_0x0039;
                default: goto L_0x0036;
            }
        L_0x0036:
            java.lang.String r2 = "trc_last_native_batch_process"
            goto L_0x003e
        L_0x0039:
            java.lang.String r2 = "trc_last_int_batch_process"
            goto L_0x003e
        L_0x003c:
            java.lang.String r2 = "trc_last_banner_batch_process"
        L_0x003e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.commons.core.f.a.e(java.lang.String):java.lang.String");
    }

    public final void c(long j, String str) {
        String e = e(str);
        if (com.inmobi.commons.a.a.a()) {
            c.b("batch_processing_info").a(e, j);
        }
    }

    public final long b(String str) {
        String e = e(str);
        if (com.inmobi.commons.a.a.a()) {
            return c.b("batch_processing_info").b(e, -1);
        }
        return -1;
    }
}
