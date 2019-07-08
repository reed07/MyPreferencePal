package com.inmobi.commons.core.a;

import android.content.ContentValues;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.measurement.AppMeasurement;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.b.b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: CrashDao */
public class c extends b {
    private static final String a = "c";

    public c() {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        a2.a(AppMeasurement.CRASH_ORIGIN, "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventId TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL, ts TEXT NOT NULL)");
        a2.b();
    }

    public static void a(d dVar) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        String str = AppMeasurement.CRASH_ORIGIN;
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", dVar.b);
        contentValues.put("componentType", dVar.d);
        contentValues.put("eventType", dVar.c);
        contentValues.put("payload", dVar.a());
        contentValues.put("ts", String.valueOf(dVar.e));
        a2.a(str, contentValues);
        a2.b();
    }

    public static List<d> a(int i) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        List<ContentValues> a3 = a2.a(AppMeasurement.CRASH_ORIGIN, null, null, null, null, null, "ts ASC", String.valueOf(i));
        ArrayList arrayList = new ArrayList();
        a2.b();
        for (ContentValues a4 : a3) {
            arrayList.add(d.a(a4));
        }
        return arrayList;
    }

    public final boolean a(long j, String str) {
        List a2 = a(1);
        if (a2.size() <= 0 || TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - ((d) a2.get(0)).e) <= j) {
            return false;
        }
        return true;
    }

    public final int a(String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        int a3 = a2.a(AppMeasurement.CRASH_ORIGIN);
        a2.b();
        return a3;
    }

    public final int b(long j, String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        int a3 = a2.a(AppMeasurement.CRASH_ORIGIN, "ts<?", new String[]{String.valueOf(System.currentTimeMillis() - (j * 1000))});
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(a3);
        sb.append(" expired events from crash DB");
        a2.b();
        return a3;
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
            String str = AppMeasurement.CRASH_ORIGIN;
            StringBuilder sb = new StringBuilder("id IN (");
            sb.append(stringBuffer);
            sb.append(")");
            a2.a(str, sb.toString(), null);
            a2.b();
        }
    }

    public static void a() {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        List a3 = a2.a(AppMeasurement.CRASH_ORIGIN, null, null, null, null, null, "ts ASC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        if (!a3.isEmpty()) {
            String asString = ((ContentValues) a3.get(0)).getAsString("id");
            String str = AppMeasurement.CRASH_ORIGIN;
            StringBuilder sb = new StringBuilder("id IN (");
            sb.append(asString);
            sb.append(")");
            a2.a(str, sb.toString(), null);
        }
        a2.b();
    }

    public final void c(long j, String str) {
        if (a.a()) {
            com.inmobi.commons.core.d.c.b("batch_processing_info").a("crash_last_batch_process", j);
        }
    }

    public final long b(String str) {
        if (a.a()) {
            return com.inmobi.commons.core.d.c.b("batch_processing_info").b("crash_last_batch_process", -1);
        }
        return -1;
    }
}
