package io.uacf.inbox.internal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.uacf.core.database.DatabaseTable;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.database.SQLiteDatabaseWrapperFactory;
import com.uacf.core.database.SQLiteDatabaseWrapperOpenHelper;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainDbOpenHelper extends SQLiteDatabaseWrapperOpenHelper {
    public MainDbOpenHelper(Context context) {
        super(context, "notification-inbox.db", null, 3);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Enumerable.forEach((Collection<T>) getTables(SQLiteDatabaseWrapperFactory.wrap(sQLiteDatabase)), (Function1<T>) new Function1<DatabaseTable>() {
            public void execute(DatabaseTable databaseTable) {
                databaseTable.onCreate();
            }
        });
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, final int i, final int i2) {
        Enumerable.forEach((Collection<T>) getTables(SQLiteDatabaseWrapperFactory.wrap(sQLiteDatabase)), (Function1<T>) new Function1<DatabaseTable>() {
            public void execute(DatabaseTable databaseTable) {
                databaseTable.onUpgrade(i, i2);
            }
        });
    }

    private List<DatabaseTable> getTables(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return Arrays.asList(new DatabaseTable[]{new NotificationTable(sQLiteDatabaseWrapper)});
    }
}
