package io.uacf.inbox.internal.database;

import android.content.Context;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import io.uacf.core.util.ContextUtil;

public final class DatabaseManager {
    private static SQLiteDatabaseWrapper mainDb;
    private static MainDbOpenHelper mainDbOpenHelper;
    private final Context context;

    public DatabaseManager(Context context2) {
        this.context = ContextUtil.getApplicationContextSafe(context2);
    }

    public SQLiteDatabaseWrapper getMainDb() {
        if (mainDbOpenHelper == null) {
            mainDbOpenHelper = new MainDbOpenHelper(this.context);
        }
        if (mainDb == null) {
            mainDb = mainDbOpenHelper.getWritableDatabaseWrapper();
        }
        return mainDb;
    }
}
