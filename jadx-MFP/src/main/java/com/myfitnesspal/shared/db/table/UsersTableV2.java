package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpAccount;
import com.myfitnesspal.shared.model.v2.MfpAppPreferences;
import com.myfitnesspal.shared.model.v2.MfpGoalDisplay;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.model.v2.MfpLocationPreferences;
import com.myfitnesspal.shared.model.v2.MfpNotificationPreferences;
import com.myfitnesspal.shared.model.v2.MfpPrivacyPreferences;
import com.myfitnesspal.shared.model.v2.MfpProfile.LIST_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpSocialPreferences;
import com.myfitnesspal.shared.model.v2.MfpStepSource;
import com.myfitnesspal.shared.model.v2.MfpSystemData;
import com.myfitnesspal.shared.model.v2.MfpUnitPreferences;
import com.myfitnesspal.shared.model.v2.UserV2;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Ln;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class UsersTableV2 extends MfpDatabaseTableImpl {
    public static final String TABLE_NAME = "user";
    private ApiJsonMapper jsonMapper = new ApiJsonMapper();

    public enum Columns {
        ID("_id"),
        USER_ID("id"),
        USERNAME("username"),
        EMAIL("email"),
        PROFILES(Http.PROFILES),
        ACCOUNT("account"),
        UNIT_PREFERENCES(Http.UNIT_PREFERENCES),
        GOAL_PREFERENCES(Http.GOAL_PREFERENCES),
        LOCATION_PREFERENCES(Http.LOCATION_PREFERENCES),
        STEP_SOURCES(Http.STEPS_SOURCES),
        SOCIAL_PREFERENCES(Http.SOCIAL_PREFERENCES),
        SYSTEM_DATA(Http.SYSTEM_DATA),
        PRIVACY_PREFERENCES(Http.PRIVACY_PREFERENCES),
        NOTIFICATION_PREFERENCES(Http.NOTIFICATION_PREFERENCES),
        APP_PREFERENCES(Http.APP_PREFERENCES),
        GOAL_DISPLAYS(Http.GOAL_DISPLAYS);
        
        private String name;

        private Columns(String str) {
            this.name = str;
        }

        public String getColumnName() {
            return this.name;
        }

        /* access modifiers changed from: 0000 */
        public String getColumnValue(Cursor cursor) {
            return cursor.getString(cursor.getColumnIndexOrThrow(getColumnName()));
        }
    }

    public void onCreate() {
    }

    public String[] getColumns() {
        ArrayList arrayList = new ArrayList();
        for (Columns columnName : Columns.values()) {
            arrayList.add(columnName.getColumnName());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Inject
    public UsersTableV2(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "user");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(27, i, i2)) {
            StringBuilder sb = new StringBuilder();
            sb.append(Columns.ID.getColumnName());
            sb.append(" integer primary key autoincrement");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Columns.USER_ID.getColumnName());
            sb2.append(" text unique not null");
            StringBuilder sb3 = new StringBuilder();
            sb3.append(Columns.USERNAME.getColumnName());
            sb3.append(" text unique not null");
            StringBuilder sb4 = new StringBuilder();
            sb4.append(Columns.EMAIL.getColumnName());
            sb4.append(" text not null");
            StringBuilder sb5 = new StringBuilder();
            sb5.append(Columns.UNIT_PREFERENCES.getColumnName());
            sb5.append(" text not null");
            StringBuilder sb6 = new StringBuilder();
            sb6.append(Columns.PROFILES.getColumnName());
            sb6.append(" text not null");
            StringBuilder sb7 = new StringBuilder();
            sb7.append(Columns.ACCOUNT.getColumnName());
            sb7.append(" text not null");
            StringBuilder sb8 = new StringBuilder();
            sb8.append(Columns.PRIVACY_PREFERENCES.getColumnName());
            sb8.append(" text not null");
            StringBuilder sb9 = new StringBuilder();
            sb9.append(Columns.GOAL_PREFERENCES.getColumnName());
            sb9.append(" text not null");
            StringBuilder sb10 = new StringBuilder();
            sb10.append(Columns.LOCATION_PREFERENCES.getColumnName());
            sb10.append(" text not null");
            StringBuilder sb11 = new StringBuilder();
            sb11.append(Columns.STEP_SOURCES.getColumnName());
            sb11.append(" text not null");
            StringBuilder sb12 = new StringBuilder();
            sb12.append(Columns.SOCIAL_PREFERENCES.getColumnName());
            sb12.append(" text not null");
            StringBuilder sb13 = new StringBuilder();
            sb13.append(Columns.SYSTEM_DATA.getColumnName());
            sb13.append(" text not null");
            StringBuilder sb14 = new StringBuilder();
            sb14.append(Columns.NOTIFICATION_PREFERENCES.getColumnName());
            sb14.append(" text not null");
            StringBuilder sb15 = new StringBuilder();
            sb15.append(Columns.APP_PREFERENCES.getColumnName());
            sb15.append(" text not null");
            StringBuilder sb16 = new StringBuilder();
            sb16.append(Columns.GOAL_DISPLAYS.getColumnName());
            sb16.append(" text not null");
            createTable(sb.toString(), sb2.toString(), sb3.toString(), sb4.toString(), sb5.toString(), sb6.toString(), sb7.toString(), sb8.toString(), sb9.toString(), sb10.toString(), sb11.toString(), sb12.toString(), sb13.toString(), sb14.toString(), sb15.toString(), sb16.toString());
        }
    }

    public boolean setUser(UserV2 userV2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.USER_ID.getColumnName(), userV2.getId());
        contentValues.put(Columns.USERNAME.getColumnName(), userV2.getUsername());
        contentValues.put(Columns.EMAIL.getColumnName(), userV2.getEmail());
        contentValues.put(Columns.UNIT_PREFERENCES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getUnitPreferences()));
        contentValues.put(Columns.PROFILES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getProfiles()));
        contentValues.put(Columns.ACCOUNT.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getAccount()));
        contentValues.put(Columns.PRIVACY_PREFERENCES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getPrivacyPreferences()));
        contentValues.put(Columns.GOAL_PREFERENCES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getGoalPreferences()));
        contentValues.put(Columns.LOCATION_PREFERENCES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getLocationPreferences()));
        contentValues.put(Columns.STEP_SOURCES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getStepSources()));
        contentValues.put(Columns.SOCIAL_PREFERENCES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getSocialPreferences()));
        contentValues.put(Columns.SYSTEM_DATA.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getSystemData()));
        contentValues.put(Columns.NOTIFICATION_PREFERENCES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getNotificationPreferences()));
        contentValues.put(Columns.APP_PREFERENCES.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getAppPreferences()));
        contentValues.put(Columns.GOAL_DISPLAYS.getColumnName(), this.jsonMapper.reverseMap((Object) userV2.getGoalDisplays()));
        return insertData(contentValues) != 0;
    }

    /* JADX INFO: finally extract failed */
    public UserV2 getUser() {
        UserV2 userV2 = new UserV2();
        Cursor queryData = queryData(null);
        try {
            if (queryData.moveToFirst()) {
                userV2.setId(Columns.USER_ID.getColumnValue(queryData));
                userV2.setUsername(Columns.USERNAME.getColumnValue(queryData));
                userV2.setEmail(Columns.EMAIL.getColumnValue(queryData));
                userV2.setUnitPreferences((MfpUnitPreferences) this.jsonMapper.mapFrom(Columns.UNIT_PREFERENCES.getColumnValue(queryData), MfpUnitPreferences.class));
                userV2.setProfiles((List) this.jsonMapper.mapFrom(Columns.PROFILES.getColumnValue(queryData), LIST_MAPPER.class));
                userV2.setAccount((MfpAccount) this.jsonMapper.mapFrom(Columns.ACCOUNT.getColumnValue(queryData), MfpAccount.class));
                userV2.setPrivacyPreferences((MfpPrivacyPreferences) this.jsonMapper.mapFrom(Columns.PRIVACY_PREFERENCES.getColumnValue(queryData), MfpPrivacyPreferences.class));
                userV2.setGoalPreferences((MfpGoalPreferences) this.jsonMapper.mapFrom(Columns.GOAL_PREFERENCES.getColumnValue(queryData), MfpGoalPreferences.class));
                userV2.setLocationPreferences((MfpLocationPreferences) this.jsonMapper.mapFrom(Columns.LOCATION_PREFERENCES.getColumnValue(queryData), MfpLocationPreferences.class));
                userV2.setStepSources((List) this.jsonMapper.mapFrom(Columns.STEP_SOURCES.getColumnValue(queryData), MfpStepSource.LIST_MAPPER.class));
                userV2.setSocialPreferences((MfpSocialPreferences) this.jsonMapper.mapFrom(Columns.SOCIAL_PREFERENCES.getColumnValue(queryData), MfpSocialPreferences.class));
                userV2.setSystemData((MfpSystemData) this.jsonMapper.mapFrom(Columns.SYSTEM_DATA.getColumnValue(queryData), MfpSystemData.class));
                userV2.setNotificationPreferences((MfpNotificationPreferences) this.jsonMapper.mapFrom(Columns.NOTIFICATION_PREFERENCES.getColumnValue(queryData), MfpNotificationPreferences.class));
                userV2.setAppPreferences((MfpAppPreferences) this.jsonMapper.mapFrom(Columns.APP_PREFERENCES.getColumnValue(queryData), MfpAppPreferences.class));
                userV2.setGoalDisplays((List) this.jsonMapper.mapFrom(Columns.GOAL_DISPLAYS.getColumnValue(queryData), MfpGoalDisplay.LIST_MAPPER.class));
            }
            queryData.close();
            return userV2;
        } catch (Exception e) {
            Ln.e(e);
            queryData.close();
            return null;
        } catch (Throwable th) {
            queryData.close();
            throw th;
        }
    }

    public void reset() {
        deleteData();
    }
}
