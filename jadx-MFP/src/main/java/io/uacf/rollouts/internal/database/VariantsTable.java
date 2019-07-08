package io.uacf.rollouts.internal.database;

import android.content.ContentValues;
import android.database.Cursor;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.DatabaseTableImpl;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Strings;
import io.uacf.rollouts.internal.model.Variant;
import java.util.ArrayList;
import java.util.List;

public class VariantsTable extends DatabaseTableImpl {

    public static final class Columns {
    }

    public void onUpgrade(int i, int i2) {
    }

    public VariantsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "variants");
    }

    public void onCreate() {
        createTable("_id INTEGER PRIMARY KEY AUTOINCREMENT", "user_id TEXT NOT NULL", "rollout_name TEXT NOT NULL", "rollout_version INTEGER NOT NULL DEFAULT -1", "track_event BOOLEAN DEFAULT FALSE", "variant_index INTEGER NOT NULL DEFAULT -1", "variant_name TEXT");
    }

    public void overwrite(List<Variant> list, String str) {
        deleteData();
        for (Variant variant : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("rollout_name", Strings.toString(variant.getRolloutName()));
            contentValues.put("user_id", Strings.toString(str));
            contentValues.put("variant_name", Strings.toString(variant.getVariantName()));
            contentValues.put("track_event", variant.isVariantTracked());
            contentValues.put("rollout_version", variant.getRolloutVersion());
            contentValues.put("variant_index", variant.getVariantIndex());
            insertData(contentValues);
        }
    }

    public List<Variant> returnVariants() {
        return parseEventsAndCloseCursor(super.rawQuery(String.format("SELECT * FROM %s", new Object[]{"variants"}), new Object[0]));
    }

    private List<Variant> parseEventsAndCloseCursor(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        CursorMapper cursorMapper = new CursorMapper(cursor);
        while (cursor.moveToNext()) {
            try {
                String string = cursorMapper.getString("rollout_name");
                Variant variant = new Variant(cursorMapper.getBoolean("track_event"), Integer.valueOf(cursorMapper.getInt("rollout_version")), string, Integer.valueOf(cursorMapper.getInt("variant_index")), cursorMapper.getString("variant_name"));
                arrayList.add(variant);
            } finally {
                CursorUtils.close(cursor);
            }
        }
        return arrayList;
    }
}
