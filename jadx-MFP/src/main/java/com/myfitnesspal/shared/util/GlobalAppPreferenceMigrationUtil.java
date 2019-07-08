package com.myfitnesspal.shared.util;

import android.content.Context;
import android.database.Cursor;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;

public class GlobalAppPreferenceMigrationUtil {
    public static final String APP_INSTALLATION_DATE = "app_installation_date";
    private static final String APP_VERSION = "app_version";
    public static final String CURRENT_LOGGED_IN_USER_NAME = "current_logged_in_username";
    public static final String DEFAULT_SEARCH_TAB = "default_search_tab";
    public static final String DONT_ASK_FOR_REVIEW = "dont_ask_for_review";
    private static final String DONT_SHOW_OFFLINE_NOTIFICATION_FOR_COMPLETE_DIARYDAY = "dont_show_offline_notification_for_complete_diary_day";
    private static final String GLOBAL_PREFERENCES = "globalPreferences";
    public static final String GLOBAL_PREFERENCE_DATABASE_TABLE = "global_app_preferences";
    public static final String LAST_LOGGED_IN_USER = "last_logged_in_user";
    public static final String LAST_LOGIN_DAY_NUMBER = "last_login_day_number";
    public static final String MULTI_ADD_TOGGLE_ON_BY_DEFAULT = "multi_add_toggle_on_by_default";
    private static final String REQUIRES_PIN_CODE_ON_APP_ENTRY = "requires_pin_code_on_app_entry";
    public static final String SHOW_ALL_MEALS = "show_all_meals";
    public static final String SHOW_INVITATION_PROMOTION_VIEW = "show_invitation_promotion_view";
    public static final String SHOW_INVITATION_PROMOTION_VIEW_USER = "show_invitation_promotion_view_";
    private static final String SHOW_NEWS_FEED_ON_HOME_TAB = "show_news_feed_on_home_tab_";
    public static final String USAGE_TYPE = "usage_type";
    private static final String USER_HAS_REVIEWED_APP = "user_has_reviewed_app";
    public static final int kGlobalAppPreferenceTypeGeneral = 1;
    private final Context context;
    private Lazy<GlobalSettingsService> globalSettingsService;
    private Lazy<LocalSettingsService> localSettingsService;
    private Lazy<Session> session;

    public GlobalAppPreferenceMigrationUtil(Context context2, Lazy<LocalSettingsService> lazy, Lazy<GlobalSettingsService> lazy2, Lazy<Session> lazy3) {
        this.context = context2;
        this.localSettingsService = lazy;
        this.globalSettingsService = lazy2;
        this.session = lazy3;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void migrateGlobalPreferencesToLocalSettings() {
        /*
            r21 = this;
            r0 = r21
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r1 = r0.localSettingsService
            java.lang.Object r1 = r1.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r1 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r1
            boolean r1 = r1.isGlobalPrefDBMigrationComplete()
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0185
            java.lang.String r4 = "current_logged_in_username"
            java.lang.String r5 = "last_logged_in_user"
            java.lang.String r6 = "last_login_day_number"
            java.lang.String r7 = "app_installation_date"
            java.lang.String r8 = "dont_ask_for_review"
            java.lang.String r9 = "show_invitation_promotion_view"
            java.lang.String r10 = "multi_add_toggle_on_by_default"
            java.lang.String r11 = "show_all_meals"
            java.lang.String r12 = "default_search_tab"
            java.lang.String[] r1 = new java.lang.String[]{r4, r5, r6, r7, r8, r9, r10, r11, r12}
            android.content.Context r4 = r0.context
            com.uacf.core.database.SQLiteDatabaseWrapper r13 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r4)
            java.lang.String r14 = "global_app_preferences"
            java.lang.String r16 = "usage_type = 1"
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r15 = r1
            android.database.Cursor r4 = r13.query(r14, r15, r16, r17, r18, r19, r20)
            int r5 = r1.length
            r6 = 0
        L_0x0041:
            if (r6 >= r5) goto L_0x0177
            r7 = r1[r6]
            r4.moveToFirst()
            int r8 = r7.hashCode()
            r9 = -1
            switch(r8) {
                case -1983069648: goto L_0x00a2;
                case -742489038: goto L_0x0098;
                case -197740949: goto L_0x008e;
                case -185598660: goto L_0x0083;
                case 179543473: goto L_0x0079;
                case 430081017: goto L_0x006f;
                case 1007721461: goto L_0x0065;
                case 1179221890: goto L_0x005b;
                case 1754453861: goto L_0x0051;
                default: goto L_0x0050;
            }
        L_0x0050:
            goto L_0x00ac
        L_0x0051:
            java.lang.String r8 = "show_invitation_promotion_view"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 5
            goto L_0x00ad
        L_0x005b:
            java.lang.String r8 = "dont_ask_for_review"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 4
            goto L_0x00ad
        L_0x0065:
            java.lang.String r8 = "app_installation_date"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 3
            goto L_0x00ad
        L_0x006f:
            java.lang.String r8 = "current_logged_in_username"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 0
            goto L_0x00ad
        L_0x0079:
            java.lang.String r8 = "last_logged_in_user"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 1
            goto L_0x00ad
        L_0x0083:
            java.lang.String r8 = "default_search_tab"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 8
            goto L_0x00ad
        L_0x008e:
            java.lang.String r8 = "last_login_day_number"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 2
            goto L_0x00ad
        L_0x0098:
            java.lang.String r8 = "multi_add_toggle_on_by_default"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 6
            goto L_0x00ad
        L_0x00a2:
            java.lang.String r8 = "show_all_meals"
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x00ac
            r7 = 7
            goto L_0x00ad
        L_0x00ac:
            r7 = -1
        L_0x00ad:
            switch(r7) {
                case 0: goto L_0x0160;
                case 1: goto L_0x014c;
                case 2: goto L_0x013a;
                case 3: goto L_0x0128;
                case 4: goto L_0x0110;
                case 5: goto L_0x00f8;
                case 6: goto L_0x00df;
                case 7: goto L_0x00c7;
                case 8: goto L_0x00b2;
                default: goto L_0x00b0;
            }
        L_0x00b0:
            goto L_0x0173
        L_0x00b2:
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r7 = r0.localSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r7 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r7
            java.lang.String r8 = "default_search_tab"
            r9 = 6001(0x1771, float:8.409E-42)
            int r8 = getIntValueforColumn(r4, r8, r9)
            r7.setDefaultSearchTab(r8)
            goto L_0x0173
        L_0x00c7:
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r7 = r0.localSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r7 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r7
            java.lang.String r8 = "show_all_meals"
            int r8 = getIntValueforColumn(r4, r8, r2)
            if (r2 != r8) goto L_0x00d9
            r8 = 1
            goto L_0x00da
        L_0x00d9:
            r8 = 0
        L_0x00da:
            r7.setShouldShowAllMeals(r8)
            goto L_0x0173
        L_0x00df:
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r7 = r0.localSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r7 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r7
            java.lang.String r8 = "multi_add_toggle_on_by_default"
            java.lang.String r9 = "false"
            java.lang.String r8 = getStringValueforColumn(r4, r8, r9)
            boolean r8 = com.uacf.core.util.Strings.toBoolean(r8)
            r7.setMultiAddToggleOnByDefault(r8)
            goto L_0x0173
        L_0x00f8:
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r7 = r0.localSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r7 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r7
            java.lang.String r8 = "show_invitation_promotion_view"
            java.lang.String r9 = "true"
            java.lang.String r8 = getStringValueforColumn(r4, r8, r9)
            boolean r8 = com.uacf.core.util.Strings.toBoolean(r8)
            r7.setShowInvitePromotionView(r8)
            goto L_0x0173
        L_0x0110:
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r7 = r0.localSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r7 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r7
            java.lang.String r8 = "dont_ask_for_review"
            java.lang.String r9 = "false"
            java.lang.String r8 = getStringValueforColumn(r4, r8, r9)
            boolean r8 = com.uacf.core.util.Strings.toBoolean(r8)
            r7.setDontAskForReview(r8)
            goto L_0x0173
        L_0x0128:
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r7 = r0.localSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r7 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r7
            java.lang.String r8 = "app_installation_date"
            java.lang.Long r8 = getLongValueforColumn(r4, r8)
            r7.setAppInstallationDate(r8)
            goto L_0x0173
        L_0x013a:
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r7 = r0.localSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r7 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r7
            java.lang.String r8 = "last_logged_in_user"
            int r8 = getIntValueforColumn(r4, r8, r9)
            r7.setLastLoginDayNumber(r8)
            goto L_0x0173
        L_0x014c:
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r7 = r0.globalSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r7 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r7
            java.lang.String r8 = "last_logged_in_user"
            java.lang.String r9 = ""
            java.lang.String r8 = getStringValueforColumn(r4, r8, r9)
            r7.setLastLoggedInUser(r8)
            goto L_0x0173
        L_0x0160:
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r7 = r0.globalSettingsService
            java.lang.Object r7 = r7.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r7 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r7
            java.lang.String r8 = "current_logged_in_username"
            java.lang.String r9 = ""
            java.lang.String r8 = getStringValueforColumn(r4, r8, r9)
            r7.setCurrentLoggedInUserName(r8)
        L_0x0173:
            int r6 = r6 + 1
            goto L_0x0041
        L_0x0177:
            r4.close()
            dagger.Lazy<com.myfitnesspal.shared.service.localsettings.LocalSettingsService> r1 = r0.localSettingsService
            java.lang.Object r1 = r1.get()
            com.myfitnesspal.shared.service.localsettings.LocalSettingsService r1 = (com.myfitnesspal.shared.service.localsettings.LocalSettingsService) r1
            r1.setGlobalPrefDBMigrationComplete()
        L_0x0185:
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r1 = r0.globalSettingsService
            java.lang.Object r1 = r1.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r1 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r1
            boolean r1 = r1.isGlobalPrefMigrationComplete()
            if (r1 != 0) goto L_0x021c
            android.content.Context r1 = r0.context
            java.lang.String r4 = "globalPreferences"
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r4, r3)
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r4 = r0.session
            java.lang.Object r4 = r4.get()
            com.myfitnesspal.shared.service.session.Session r4 = (com.myfitnesspal.shared.service.session.Session) r4
            com.myfitnesspal.shared.model.User r4 = r4.getUser()
            java.lang.String r4 = r4.getUsername()
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r5 = r0.globalSettingsService
            java.lang.Object r5 = r5.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r5 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r5
            java.lang.String r6 = "app_version"
            java.lang.String r7 = ""
            java.lang.String r6 = r1.getString(r6, r7)
            r5.setAppVersion(r6)
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r5 = r0.globalSettingsService
            java.lang.Object r5 = r5.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r5 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r5
            java.lang.String r6 = "user_has_reviewed_app"
            boolean r6 = r1.getBoolean(r6, r3)
            r5.setUserHasReviewedApp(r6)
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r5 = r0.globalSettingsService
            java.lang.Object r5 = r5.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r5 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r5
            java.lang.String r6 = "dont_show_offline_notification_for_complete_diary_day"
            boolean r6 = r1.getBoolean(r6, r3)
            r5.setDontShowOfflineNotificationForCompleteDiaryDay(r6)
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r5 = r0.globalSettingsService
            java.lang.Object r5 = r5.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r5 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r5
            java.lang.String r6 = "requires_pin_code_on_app_entry"
            boolean r3 = r1.getBoolean(r6, r3)
            r5.setRequiresPinCodeOnAppEntry(r3)
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r3 = r0.globalSettingsService
            java.lang.Object r3 = r3.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r3 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r3
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "show_invitation_promotion_view_"
            r5.append(r6)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            boolean r1 = r1.getBoolean(r5, r2)
            r3.setShowInvitationPromotinalScreen(r4, r1)
            dagger.Lazy<com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService> r1 = r0.globalSettingsService
            java.lang.Object r1 = r1.get()
            com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService r1 = (com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService) r1
            r1.setGlobalPrefMigrationComplete()
        L_0x021c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.GlobalAppPreferenceMigrationUtil.migrateGlobalPreferencesToLocalSettings():void");
    }

    private static String getStringValueforColumn(Cursor cursor, String str, String str2) {
        if (cursor == null || cursor.isClosed()) {
            return str2;
        }
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex != -1 ? cursor.getString(columnIndex) : str2;
    }

    private static int getIntValueforColumn(Cursor cursor, String str, int i) {
        if (cursor == null || cursor.isClosed()) {
            return i;
        }
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex != -1 ? cursor.getInt(columnIndex) : i;
    }

    private static Long getLongValueforColumn(Cursor cursor, String str) {
        Long valueOf = Long.valueOf(0);
        if (cursor == null || cursor.isClosed()) {
            return valueOf;
        }
        int columnIndex = cursor.getColumnIndex(str);
        return columnIndex != -1 ? Long.valueOf(cursor.getLong(columnIndex)) : valueOf;
    }
}
