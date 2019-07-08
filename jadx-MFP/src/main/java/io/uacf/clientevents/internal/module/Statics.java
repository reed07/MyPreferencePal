package io.uacf.clientevents.internal.module;

import android.content.Context;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import io.uacf.clientevents.internal.database.DatabaseManager;
import io.uacf.core.api.UacfApiEnvironmentProvider;

public final class Statics {
    private static UacfApiEnvironmentProvider ApiEnvironmentProvider;
    private static DatabaseManager DbManager;
    private static boolean initialized;

    public static void init(Context context, UacfApiEnvironmentProvider uacfApiEnvironmentProvider) {
        if (!initialized) {
            ApiEnvironmentProvider = uacfApiEnvironmentProvider;
            DbManager = new DatabaseManager(context);
            initialized = true;
        }
    }

    public static SQLiteDatabaseWrapper getMainDb() {
        verifyInitialized();
        return DbManager.getMainDb();
    }

    private static void verifyInitialized() {
        if (!initialized) {
            throw new IllegalStateException("Someone must call Statics#init() before calling any methods");
        }
    }
}
