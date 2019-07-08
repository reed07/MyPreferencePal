package io.uacf.clientevents.internal.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.LongSparseArray;
import com.google.gson.FieldNamingPolicy;
import com.mopub.common.Constants;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.uacf.core.database.DatabaseTableImpl;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.clientevents.sdk.UacfClientEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientEventsTable extends DatabaseTableImpl {
    protected GsonObjectMapper mapper = new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).withType(UacfClientEvent.class);

    public interface Columns {
    }

    public ClientEventsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, Constants.VIDEO_TRACKING_EVENTS_KEY);
    }

    public void onCreate() {
        createTable("_id integer primary key autoincrement", "uacf_id text", "user_id text", "domain text", "event_json text not null", "event_json_version integer");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(2, i, i2)) {
            addColumn(Basic.UACF_ID, "TEXT");
            addColumn("user_id", "TEXT");
            addColumn("domain", "TEXT");
            createIndex("idx_id", "_id");
            createIndex("idx_uacf_id", Basic.UACF_ID);
            DatabaseUtil.ensureDatabaseTransaction(getDatabase(), new Function0() {
                public void execute() {
                    Cursor cursor;
                    GsonObjectMapper withType = new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).withType(UacfClientEvent.class);
                    try {
                        cursor = ClientEventsTable.this.queryData(null, null, new Object[0]);
                        try {
                            int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_id");
                            int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("event_json");
                            ContentValues contentValues = new ContentValues();
                            while (cursor.moveToNext()) {
                                long j = cursor.getLong(columnIndexOrThrow);
                                String string = cursor.getString(columnIndexOrThrow2);
                                UacfClientEvent uacfClientEvent = Strings.notEmpty(string) ? (UacfClientEvent) withType.tryMapFrom(string) : null;
                                if (uacfClientEvent != null && Strings.notEmpty(uacfClientEvent.getUacfId())) {
                                    contentValues.put(Basic.UACF_ID, uacfClientEvent.getUacfId());
                                    contentValues.put("user_id", uacfClientEvent.getUserId());
                                    contentValues.put("domain", uacfClientEvent.getDomain());
                                    ClientEventsTable.this.updateData(contentValues, "_id = ?", Long.valueOf(j));
                                }
                            }
                            CursorUtils.close(cursor);
                        } catch (Throwable th) {
                            th = th;
                            CursorUtils.close(cursor);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = null;
                        CursorUtils.close(cursor);
                        throw th;
                    }
                }
            });
        }
    }

    public void insert(UacfClientEvent uacfClientEvent) {
        if (uacfClientEvent != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Basic.UACF_ID, uacfClientEvent.getUacfId());
            contentValues.put("user_id", uacfClientEvent.getUserId());
            contentValues.put("domain", uacfClientEvent.getDomain());
            contentValues.put("event_json", this.mapper.reverseMap((Object) uacfClientEvent));
            contentValues.put("event_json_version", Integer.valueOf(1));
            insertData(contentValues);
            return;
        }
        throw new IllegalArgumentException("Argument 'event' must not be null");
    }

    public void remove(LongSparseArray<UacfClientEvent> longSparseArray) {
        if (longSparseArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < longSparseArray.size(); i++) {
                arrayList.add(Long.valueOf(longSparseArray.keyAt(i)));
            }
            removeIds(arrayList);
        }
    }

    public LongSparseArray<UacfClientEvent> next(int i) {
        Cursor cursor;
        String str;
        String str2 = null;
        try {
            cursor = super.rawQuery(String.format("SELECT %s FROM %s ORDER BY %s LIMIT 1", new Object[]{Basic.UACF_ID, Constants.VIDEO_TRACKING_EVENTS_KEY, Basic.UACF_ID}), new Object[0]);
            try {
                int count = cursor.getCount();
                if (cursor.moveToFirst()) {
                    str2 = CursorUtils.getString(cursor, Basic.UACF_ID);
                }
                CursorUtils.close(cursor);
                LongSparseArray<UacfClientEvent> longSparseArray = new LongSparseArray<>();
                if (count <= 0) {
                    return longSparseArray;
                }
                String str3 = "SELECT * FROM %s WHERE %s %s LIMIT %s";
                Object[] objArr = new Object[4];
                objArr[0] = Constants.VIDEO_TRACKING_EVENTS_KEY;
                objArr[1] = Basic.UACF_ID;
                if (str2 == null) {
                    str = "IS NULL";
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("= '");
                    sb.append(str2);
                    sb.append("'");
                    str = sb.toString();
                }
                objArr[2] = str;
                objArr[3] = Integer.valueOf(i);
                return parseEventsAndCloseCursor(super.rawQuery(String.format(str3, objArr), new Object[0]));
            } catch (Throwable th) {
                th = th;
                CursorUtils.close(cursor);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            CursorUtils.close(cursor);
            throw th;
        }
    }

    private LongSparseArray<UacfClientEvent> parseEventsAndCloseCursor(Cursor cursor) {
        LongSparseArray<UacfClientEvent> longSparseArray = new LongSparseArray<>();
        ArrayList arrayList = new ArrayList();
        try {
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow("_id");
            int columnIndexOrThrow2 = cursor.getColumnIndexOrThrow("event_json");
            GsonObjectMapper withType = new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).withType(UacfClientEvent.class);
            while (cursor.moveToNext()) {
                long j = cursor.getLong(columnIndexOrThrow);
                String string = cursor.getString(columnIndexOrThrow2);
                UacfClientEvent uacfClientEvent = Strings.notEmpty(string) ? (UacfClientEvent) withType.tryMapFrom(string) : null;
                if (uacfClientEvent != null) {
                    longSparseArray.append(j, uacfClientEvent);
                } else {
                    Ln.e("ANALYTICS: event is corrupt, skip it", new Object[0]);
                    arrayList.add(Long.valueOf(cursor.getLong(columnIndexOrThrow)));
                }
            }
            return longSparseArray;
        } finally {
            cursor.close();
            removeIds(arrayList);
        }
    }

    private void removeIds(List<Long> list) {
        if (CollectionUtils.size((Collection<?>) list) > 0) {
            deleteData(String.format("%s IN (%s)", new Object[]{"_id", Strings.join(",", (Collection<T>) list)}));
        }
    }
}
