package com.inmobi.commons.core.e;

import android.content.ContentValues;
import com.facebook.appevents.AppEventsConstants;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.b.b;
import com.inmobi.commons.core.d.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: TelemetryDao */
public class e extends b {
    private static final String a = "e";

    public e() {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        a2.a("telemetry", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, componentType TEXT NOT NULL, eventId TEXT NOT NULL, eventType TEXT NOT NULL, payload TEXT NOT NULL, ts TEXT NOT NULL)");
        a2.b();
    }

    public static void a(f fVar) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventId", fVar.b);
        contentValues.put("componentType", fVar.d);
        contentValues.put("eventType", fVar.c);
        contentValues.put("payload", fVar.a());
        contentValues.put("ts", String.valueOf(fVar.e));
        a2.a("telemetry", contentValues);
        a2.b();
    }

    public static List<f> a(int i) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        List<ContentValues> a3 = a2.a("telemetry", null, null, null, null, null, "ts ASC", String.valueOf(i));
        ArrayList arrayList = new ArrayList();
        a2.b();
        for (ContentValues a4 : a3) {
            arrayList.add(f.a(a4));
        }
        return arrayList;
    }

    public final boolean a(long j, String str) {
        List a2 = a(1);
        if (a2.size() <= 0 || TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - ((f) a2.get(0)).e) <= j) {
            return false;
        }
        return true;
    }

    public final int a(String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        int a3 = a2.a("telemetry");
        a2.b();
        return a3;
    }

    public final int b(long j, String str) {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        int a3 = a2.a("telemetry", "ts<?", new String[]{String.valueOf(System.currentTimeMillis() - (j * 1000))});
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(a3);
        sb.append(" expired events from telemetry DB");
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
            StringBuilder sb = new StringBuilder("id IN (");
            sb.append(stringBuffer);
            sb.append(")");
            a2.a("telemetry", sb.toString(), null);
            a2.b();
        }
    }

    public static void a() {
        com.inmobi.commons.core.d.b a2 = com.inmobi.commons.core.d.b.a();
        List a3 = a2.a("telemetry", null, null, null, null, null, "ts ASC", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        if (!a3.isEmpty()) {
            String asString = ((ContentValues) a3.get(0)).getAsString("id");
            StringBuilder sb = new StringBuilder("id IN (");
            sb.append(asString);
            sb.append(")");
            a2.a("telemetry", sb.toString(), null);
        }
        a2.b();
    }

    public final void c(long j, String str) {
        if (a.a()) {
            c.b("batch_processing_info").a("telemetry_last_batch_process", j);
        }
    }

    public final long b(String str) {
        if (a.a()) {
            return c.b("batch_processing_info").b("telemetry_last_batch_process", -1);
        }
        return -1;
    }
}
