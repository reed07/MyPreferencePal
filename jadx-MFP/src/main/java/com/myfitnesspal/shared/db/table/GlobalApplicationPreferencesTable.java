package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import com.brightcove.player.event.AbstractEvent;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.util.GlobalAppPreferenceMigrationUtil;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.UUID;
import javax.inject.Inject;

public class GlobalApplicationPreferencesTable extends MfpDatabaseTableImpl {
    private static final String GLOBAL_PREFERENCES = "globalPreferences";
    private static final String TABLE_NAME = "global_app_preferences";
    private static final String WHERE_CLAUSE = "usage_type = ?";
    private static SharedPreferences globalPreferences;

    public static final class Columns {
        static final String ACCOUNT_CREATION_OPTIONS_SEEN = "account_creation_options_seen";
        static final String APP_INSTALLATION_DATE = "app_installation_date";
        static final String CURRENT_LOGGED_IN_USERNAME = "current_logged_in_username";
        static final String CURRENT_LOGGED_IN_USER_ID = "current_logged_in_user_id";
        static final String CURRENT_LOGGED_IN_USER_PASSWORD = "current_logged_in_user_password";
        static final String DEFAULT_SEARCH_TAB = "default_search_tab";
        static final String DONT_ASK_FOR_REVIEW = "dont_ask_for_review";
        static final String FORCE_OFFLINE_MODE = "force_offline_mode";
        static final String GCM_REGISTRATION_ID = "gcm_registration_id";
        static final String IMPORT_IN_PROGRESS = "import_in_progress";
        static final String IS_OFFLINE_SEARCH_ENABLED = "is_offline_search_enabled";
        static final String LAST_LOGGED_IN_USER = "last_logged_in_user";
        static final String LAST_LOGIN_DAY_NUMBER = "last_login_day_number";
        static final String MFP_SERVER_CERTIFICATE_IS_TRUSTED_BY_THIS_DEVICE = "mfp_server_certificate_is_trusted_by_this_device";
        static final String MULTI_ADD_TOGGLE_ON_BY_DEFAULT = "multi_add_toggle_on_by_default";
        static final String SHOW_ALL_MEALS = "show_all_meals";
        static final String SHOW_INVITATION_PROMOTION_VIEW = "show_invitation_promotion_view";
        static final String TERMS_OF_USE_ACCEPTED = "terms_of_use_accepted";
        static final String UPGRADE_NOTIFICATION_SEEN = "upgrade_notification_seen";
        static final String USAGE_TYPE = "usage_type";
        static final String UUID = "uuid";
    }

    public static final class UsageTypes {
        static final int GENERAL = 1;
    }

    @Inject
    public GlobalApplicationPreferencesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "global_app_preferences");
    }

    public void onCreate() {
        boolean z;
        createTable("usage_type integer primary key", "terms_of_use_accepted text", "current_logged_in_user_id integer", "current_logged_in_username text", "current_logged_in_user_password text", "uuid text", "import_in_progress text", "last_logged_in_user text", "account_creation_options_seen text", "upgrade_notification_seen integer", "last_login_day_number integer", "force_offline_mode integer");
        SharedPreferences globalPreferences2 = getGlobalPreferences();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GlobalAppPreferenceMigrationUtil.USAGE_TYPE, Integer.valueOf(1));
        insertData(contentValues);
        contentValues.clear();
        if (globalPreferences2 == null || !globalPreferences2.contains("terms_of_use_accepted")) {
            z = false;
        } else {
            try {
                z = globalPreferences2.getBoolean("terms_of_use_accepted", false);
            } catch (ClassCastException unused) {
                z = Boolean.valueOf(globalPreferences2.getString("terms_of_use_accepted", "false")).booleanValue();
            }
        }
        contentValues.put("terms_of_use_accepted", Strings.toString(Boolean.valueOf(z)));
        long j = 0;
        if (globalPreferences2 != null) {
            j = globalPreferences2.getLong("current_logged_in_user_id", 0);
        }
        contentValues.put("current_logged_in_user_id", Strings.toString(Long.valueOf(j)));
        String str = null;
        contentValues.put(GlobalAppPreferenceMigrationUtil.CURRENT_LOGGED_IN_USER_NAME, Strings.toString(globalPreferences2 != null ? globalPreferences2.getString("username", null) : null));
        contentValues.put("current_logged_in_user_password", "");
        String string = globalPreferences2 != null ? globalPreferences2.getString(AbstractEvent.UUID, null) : null;
        String str2 = AbstractEvent.UUID;
        if (Strings.isEmpty(string)) {
            string = UUID.randomUUID().toString();
        }
        contentValues.put(str2, string);
        contentValues.put("import_in_progress", Strings.toString(Boolean.valueOf(globalPreferences2 != null && globalPreferences2.getBoolean("initial_import_in_progress", false))));
        if (globalPreferences2 != null) {
            str = globalPreferences2.getString("logged_in_as_username", null);
        }
        contentValues.put(GlobalAppPreferenceMigrationUtil.LAST_LOGGED_IN_USER, Strings.toString(str));
        contentValues.put("account_creation_options_seen", Strings.toString(Boolean.valueOf(globalPreferences2 != null && globalPreferences2.getBoolean("account_creation_options_seen", false))));
        contentValues.put("upgrade_notification_seen", Integer.valueOf((globalPreferences2 == null || !globalPreferences2.getBoolean("upgrade_notification_seen_v3", false)) ? 0 : 1));
        contentValues.put(GlobalAppPreferenceMigrationUtil.LAST_LOGIN_DAY_NUMBER, Integer.valueOf(0));
        contentValues.put("force_offline_mode", Boolean.valueOf(false));
        updateData(contentValues, WHERE_CLAUSE, Integer.valueOf(1));
        if (globalPreferences2 != null) {
            globalPreferences2.edit().remove("logged_in_as_username").remove("username").remove("password").remove("initial_import_in_progress").remove("upgrade_notification_seen_v3").remove("terms_of_use_accepted").putString("terms_of_use_accepted", Strings.toString(Boolean.valueOf(z))).apply();
        }
    }

    public void onUpgrade(int i, int i2) {
        ContentValues contentValues = new ContentValues();
        if (shouldRunUpgrade(6, i, i2)) {
            contentValues.put("terms_of_use_accepted", Strings.toString(Boolean.valueOf(false)));
            updateData(contentValues, WHERE_CLAUSE, Integer.valueOf(1));
            getGlobalPreferences().edit().putString("terms_of_use_accepted", Strings.toString(Boolean.valueOf(false))).apply();
        }
        if (shouldRunUpgrade(9, i, i2)) {
            addColumn(GlobalAppPreferenceMigrationUtil.APP_INSTALLATION_DATE, "long");
            addColumn(GlobalAppPreferenceMigrationUtil.DONT_ASK_FOR_REVIEW, "text");
        }
        if (shouldRunUpgrade(13, i, i2)) {
            addColumn(GlobalAppPreferenceMigrationUtil.SHOW_INVITATION_PROMOTION_VIEW, "text");
        }
        if (shouldRunUpgrade(14, i, i2)) {
            addColumn("is_offline_search_enabled", "text");
            addColumn("gcm_registration_id", "text");
            addColumn(GlobalAppPreferenceMigrationUtil.MULTI_ADD_TOGGLE_ON_BY_DEFAULT, "text");
            contentValues.clear();
            contentValues.put("is_offline_search_enabled", "true");
            updateData(contentValues, WHERE_CLAUSE, Integer.valueOf(1));
        }
        if (shouldRunUpgrade(16, i, i2)) {
            addColumn("mfp_server_certificate_is_trusted_by_this_device", "text default 'false'");
            addColumn(GlobalAppPreferenceMigrationUtil.SHOW_ALL_MEALS, "integer default 1");
        }
        if (shouldRunUpgrade(17, i, i2)) {
            addColumn(GlobalAppPreferenceMigrationUtil.DEFAULT_SEARCH_TAB, "integer default 6001");
        }
        if (shouldRunUpgrade(28, i, i2)) {
            DatabaseUtil.ensureDatabaseTransaction(this.database, new Function0() {
                public final void execute() {
                    GlobalApplicationPreferencesTable.lambda$onUpgrade$0(GlobalApplicationPreferencesTable.this);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$onUpgrade$0(GlobalApplicationPreferencesTable globalApplicationPreferencesTable) throws RuntimeException {
        GlobalApplicationPreferencesTable globalApplicationPreferencesTable2 = globalApplicationPreferencesTable;
        globalApplicationPreferencesTable2.renameTable("tmp_global_app_preferences");
        globalApplicationPreferencesTable2.createTable("usage_type integer primary key", "terms_of_use_accepted text", "current_logged_in_user_id integer", "current_logged_in_username text", "uuid text", "import_in_progress text", "last_logged_in_user text", "account_creation_options_seen text", "upgrade_notification_seen integer", "last_login_day_number integer", "force_offline_mode integer", "app_installation_date long", "dont_ask_for_review text", "show_invitation_promotion_view text", "is_offline_search_enabled text", "gcm_registration_id text", "multi_add_toggle_on_by_default text", "mfp_server_certificate_is_trusted_by_this_device text default 'false'", "show_all_meals integer default 0", "default_search_tab integer default 6001");
        String join = Strings.join(", ", (T[]) new String[]{GlobalAppPreferenceMigrationUtil.USAGE_TYPE, "terms_of_use_accepted", "current_logged_in_user_id", GlobalAppPreferenceMigrationUtil.CURRENT_LOGGED_IN_USER_NAME, AbstractEvent.UUID, "import_in_progress", GlobalAppPreferenceMigrationUtil.LAST_LOGGED_IN_USER, "account_creation_options_seen", "upgrade_notification_seen", GlobalAppPreferenceMigrationUtil.LAST_LOGIN_DAY_NUMBER, "force_offline_mode", GlobalAppPreferenceMigrationUtil.APP_INSTALLATION_DATE, GlobalAppPreferenceMigrationUtil.DONT_ASK_FOR_REVIEW, GlobalAppPreferenceMigrationUtil.SHOW_INVITATION_PROMOTION_VIEW, "is_offline_search_enabled", "gcm_registration_id", GlobalAppPreferenceMigrationUtil.MULTI_ADD_TOGGLE_ON_BY_DEFAULT, "mfp_server_certificate_is_trusted_by_this_device", GlobalAppPreferenceMigrationUtil.SHOW_ALL_MEALS, GlobalAppPreferenceMigrationUtil.DEFAULT_SEARCH_TAB});
        globalApplicationPreferencesTable2.insertDataFromOtherTable(join, join, "tmp_global_app_preferences");
        globalApplicationPreferencesTable2.dropTable("tmp_global_app_preferences");
    }

    public UUID getUUID() {
        String stringPreference = getStringPreference(AbstractEvent.UUID);
        if (Strings.notEmpty(stringPreference)) {
            try {
                return UUID.fromString(stringPreference);
            } catch (IllegalArgumentException e) {
                Ln.e(e);
            }
        }
        return null;
    }

    private synchronized String getStringPreference(String str) {
        String str2;
        str2 = null;
        Cursor cursor = getCursor(str);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    str2 = cursor.getString(cursor.getColumnIndex(str));
                }
            } catch (Exception e) {
                Ln.e(e);
            } catch (Throwable th) {
                cursor.close();
                throw th;
            }
            cursor.close();
        }
        return str2;
    }

    private synchronized Cursor getCursor(String... strArr) {
        try {
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
        return queryData(strArr, WHERE_CLAUSE, Integer.valueOf(1));
    }

    private static SharedPreferences getGlobalPreferences() {
        try {
            if (globalPreferences == null) {
                globalPreferences = MyFitnessPalApp.getInstance().getSharedPreferences(GLOBAL_PREFERENCES, 0);
            }
        } catch (Exception e) {
            Ln.e(e);
        }
        return globalPreferences;
    }
}
