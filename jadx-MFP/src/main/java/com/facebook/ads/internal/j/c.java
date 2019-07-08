package com.facebook.ads.internal.j;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.WorkerThread;
import com.mopub.common.Constants;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

public class c extends g {
    public static final b a = new b(0, Extras.EVENT_ID, "TEXT PRIMARY KEY");
    public static final b b = new b(1, "token_id", "TEXT REFERENCES tokens ON UPDATE CASCADE ON DELETE RESTRICT");
    public static final b c = new b(2, Columns.PRIORITY, "INTEGER");
    public static final b d = new b(3, "type", "TEXT");
    public static final b e = new b(4, TimestampAnalyticsHelper.ATTR_TIME, "REAL");
    public static final b f = new b(5, "session_time", "REAL");
    public static final b g = new b(6, Attributes.SESSION_ID, "TEXT");
    public static final b h = new b(7, "data", "TEXT");
    public static final b i = new b(8, "attempt", "INTEGER");
    public static final b[] j = {a, b, c, d, e, f, g, h, i};
    private static final String l = a(Constants.VIDEO_TRACKING_EVENTS_KEY, j);

    public c(d dVar) {
        super(dVar);
    }

    public String a() {
        return Constants.VIDEO_TRACKING_EVENTS_KEY;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public String a(String str, int i2, String str2, double d2, double d3, String str3, Map<String, String> map) {
        String uuid = UUID.randomUUID().toString();
        ContentValues contentValues = new ContentValues(9);
        contentValues.put(a.b, uuid);
        contentValues.put(b.b, str);
        contentValues.put(c.b, Integer.valueOf(i2));
        contentValues.put(d.b, str2);
        contentValues.put(e.b, Double.valueOf(d2));
        contentValues.put(f.b, Double.valueOf(d3));
        contentValues.put(g.b, str3);
        contentValues.put(h.b, map != null ? new JSONObject(map).toString() : null);
        contentValues.put(i.b, Integer.valueOf(0));
        f().insertOrThrow(Constants.VIDEO_TRACKING_EVENTS_KEY, null, contentValues);
        return uuid;
    }

    /* access modifiers changed from: 0000 */
    public boolean a(String str) {
        SQLiteDatabase f2 = f();
        String str2 = Constants.VIDEO_TRACKING_EVENTS_KEY;
        StringBuilder sb = new StringBuilder();
        sb.append(a.b);
        sb.append(" = ?");
        return f2.delete(str2, sb.toString(), new String[]{str}) > 0;
    }

    public b[] b() {
        return j;
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public Cursor c() {
        return f().rawQuery("SELECT count(*) FROM events", null);
    }

    /* access modifiers changed from: 0000 */
    @WorkerThread
    public Cursor d() {
        return f().rawQuery(l, null);
    }
}
