package com.myfitnesspal.shared.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.Nonnull;

public class UserV1DBAdapter {
    private static final String DATABASE_TABLE = "users";
    private static final String[] USER_COLUMNS = {"id", "master_id", "username", "third_party_service_id", "third_party_user_id", "third_party_auth_token"};
    private final Context context;
    private final DbConnectionManager dbConnectionManager;
    private SQLiteStatement stmt;

    public UserV1DBAdapter(@Nonnull Context context2, @Nonnull DbConnectionManager dbConnectionManager2) {
        this.context = context2;
        this.dbConnectionManager = dbConnectionManager2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void saveUser(com.myfitnesspal.shared.model.v1.UserV1 r15) {
        /*
            r14 = this;
            r0 = 0
            java.lang.String r1 = r15.getUsername()     // Catch:{ Exception -> 0x0107 }
            java.lang.String r1 = com.uacf.core.util.Strings.trimmed(r1)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r1 = r1.toLowerCase()     // Catch:{ Exception -> 0x0107 }
            boolean r2 = r15.hasMasterDatabaseId()     // Catch:{ Exception -> 0x0107 }
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x002b
            java.lang.String r2 = "lower(username) = ? OR master_id = ?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ Exception -> 0x0107 }
            r5[r4] = r1     // Catch:{ Exception -> 0x0107 }
            long r6 = r15.getMasterDatabaseId()     // Catch:{ Exception -> 0x0107 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r6 = com.uacf.core.util.Strings.toString(r6)     // Catch:{ Exception -> 0x0107 }
            r5[r3] = r6     // Catch:{ Exception -> 0x0107 }
            goto L_0x0031
        L_0x002b:
            java.lang.String r2 = "lower(username) = ?"
            java.lang.String[] r5 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0107 }
            r5[r4] = r1     // Catch:{ Exception -> 0x0107 }
        L_0x0031:
            android.content.Context r6 = r14.context     // Catch:{ Exception -> 0x0107 }
            com.uacf.core.database.SQLiteDatabaseWrapper r6 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r6)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r7 = "users"
            java.lang.String[] r8 = USER_COLUMNS     // Catch:{ Exception -> 0x0107 }
            r11 = 0
            r12 = 0
            r13 = 0
            r9 = r2
            r10 = r5
            android.database.Cursor r6 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ Exception -> 0x0107 }
            android.content.ContentValues r7 = new android.content.ContentValues     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r7.<init>()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r8 = "third_party_service_id"
            int r9 = r15.getThirdPartyServiceId()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r7.put(r8, r9)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r8 = "third_party_user_id"
            java.lang.String r9 = r15.getThirdPartyUserId()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r7.put(r8, r9)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r8 = "third_party_auth_token"
            java.lang.String r9 = r15.getThirdPartyAuthToken()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r7.put(r8, r9)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            boolean r8 = r6.moveToFirst()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            if (r8 == 0) goto L_0x0087
            java.lang.String r0 = "USER: found user %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r3[r4] = r1     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            com.uacf.core.util.Ln.d(r0, r3)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            android.content.Context r0 = r14.context     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            com.uacf.core.database.SQLiteDatabaseWrapper r0 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r0)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r1 = "users"
            r0.update(r1, r7, r2, r5)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            long r0 = r6.getLong(r4)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            goto L_0x00f3
        L_0x0087:
            java.lang.String r2 = "USER: could not find user %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r3[r4] = r1     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            com.uacf.core.util.Ln.d(r2, r3)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r2 = "username"
            r7.put(r2, r1)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            boolean r2 = r15.hasMasterDatabaseId()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            if (r2 == 0) goto L_0x00a8
            java.lang.String r2 = "master_id"
            long r8 = r15.getMasterDatabaseId()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.Long r3 = java.lang.Long.valueOf(r8)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r7.put(r2, r3)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
        L_0x00a8:
            android.content.Context r2 = r14.context     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            com.uacf.core.database.SQLiteDatabaseWrapper r2 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r2)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r3 = "users"
            long r2 = r2.insert(r3, r0, r7)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r7 = 0
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x00d1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r0.<init>()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r5 = "error inserting new user "
            r0.append(r5)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r0.append(r1)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            com.uacf.core.util.Ln.e(r0, r1)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            goto L_0x00e7
        L_0x00d1:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r0.<init>()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r5 = "****Inserted new with username: "
            r0.append(r5)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r0.append(r1)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            com.uacf.core.util.Ln.i(r0, r1)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
        L_0x00e7:
            com.myfitnesspal.shared.model.v1.UserProfile r0 = r15.getProfile()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            java.lang.String[] r0 = r0.getMeasurementTypes()     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r14.createMeasurementTypeDescriptions(r0, r2)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r0 = r2
        L_0x00f3:
            r15.setLocalId(r0)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            r14.saveUserProperties(r15)     // Catch:{ Exception -> 0x0101, all -> 0x00ff }
            if (r6 == 0) goto L_0x0110
            r6.close()
            goto L_0x0110
        L_0x00ff:
            r15 = move-exception
            goto L_0x0111
        L_0x0101:
            r15 = move-exception
            r0 = r6
            goto L_0x0108
        L_0x0104:
            r15 = move-exception
            r6 = r0
            goto L_0x0111
        L_0x0107:
            r15 = move-exception
        L_0x0108:
            com.uacf.core.util.Ln.e(r15)     // Catch:{ all -> 0x0104 }
            if (r0 == 0) goto L_0x0110
            r0.close()
        L_0x0110:
            return
        L_0x0111:
            if (r6 == 0) goto L_0x0116
            r6.close()
        L_0x0116:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.UserV1DBAdapter.saveUser(com.myfitnesspal.shared.model.v1.UserV1):void");
    }

    private void saveUserProperties(UserV1 userV1) {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            userV1.writeAllPropertyKeysTo(arrayList, arrayList2);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.dbConnectionManager.userPropertiesDbAdapter().saveUserProperty((String) arrayList.get(i), (String) arrayList2.get(i), userV1.getLocalId());
            }
        } catch (SQLiteException e) {
            Ln.e(e);
        }
    }

    public void createMeasurementTypeDescriptions(String[] strArr, long j) {
        try {
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                this.dbConnectionManager.measurementTypesDbAdapter().insertMeasurementType(strArr[i], j, i);
            }
        } catch (SQLiteException e) {
            Ln.e(e);
        }
    }

    public UserV1 fetchUserWithUserLocalId(long j) {
        Ln.d("MfpLoginProcedureHelper: fetchUserWithUserLocalId id = %s", Long.valueOf(j));
        return fetchUser("id = ?", Strings.toString(Long.valueOf(j)));
    }

    public UserV1 fetchUserWithUsername(String str) {
        Ln.d("MfpLoginProcedureHelper: fetchUserWithUsername username = %s", str);
        return fetchUser("lower(username) = ?", Strings.toString(str).toLowerCase());
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x011a A[Catch:{ all -> 0x010f, SQLiteException -> 0x011e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v1.UserV1 fetchUser(java.lang.String r20, java.lang.String r21) {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r0 = "MfpLoginProcedureHelper: fetchUser %s, %s"
            r3 = 2
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0116 }
            r5 = 0
            r4[r5] = r20     // Catch:{ all -> 0x0116 }
            r14 = 1
            r4[r14] = r21     // Catch:{ all -> 0x0116 }
            com.uacf.core.util.Ln.d(r0, r4)     // Catch:{ all -> 0x0116 }
            android.content.Context r0 = r1.context     // Catch:{ all -> 0x0116 }
            com.uacf.core.database.SQLiteDatabaseWrapper r6 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r0)     // Catch:{ all -> 0x0116 }
            java.lang.String r7 = "users"
            java.lang.String[] r8 = USER_COLUMNS     // Catch:{ all -> 0x0116 }
            java.lang.String[] r10 = new java.lang.String[r14]     // Catch:{ all -> 0x0116 }
            r10[r5] = r21     // Catch:{ all -> 0x0116 }
            r11 = 0
            r12 = 0
            r13 = 0
            r9 = r20
            android.database.Cursor r4 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x0116 }
            boolean r0 = r4.moveToFirst()     // Catch:{ all -> 0x0114 }
            if (r0 == 0) goto L_0x0069
            long r8 = r4.getLong(r5)     // Catch:{ all -> 0x0114 }
            long r10 = r4.getLong(r14)     // Catch:{ all -> 0x0114 }
            java.lang.String r0 = r4.getString(r3)     // Catch:{ all -> 0x0114 }
            r12 = 3
            int r13 = r4.getInt(r12)     // Catch:{ all -> 0x0114 }
            r15 = 4
            java.lang.String r16 = r4.getString(r15)     // Catch:{ all -> 0x0114 }
            r2 = 5
            java.lang.String r17 = r4.getString(r2)     // Catch:{ all -> 0x0114 }
            java.lang.String r6 = "MfpLoginProcedureHelper: fetchUser lid=%s, mid=%s, uname=%s, tpsi=%s, tpuid=%s, tpat=%s"
            r7 = 6
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ all -> 0x0114 }
            java.lang.Long r18 = java.lang.Long.valueOf(r8)     // Catch:{ all -> 0x0114 }
            r7[r5] = r18     // Catch:{ all -> 0x0114 }
            java.lang.Long r18 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x0114 }
            r7[r14] = r18     // Catch:{ all -> 0x0114 }
            r7[r3] = r0     // Catch:{ all -> 0x0114 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x0114 }
            r7[r12] = r3     // Catch:{ all -> 0x0114 }
            r7[r15] = r16     // Catch:{ all -> 0x0114 }
            r7[r2] = r17     // Catch:{ all -> 0x0114 }
            com.uacf.core.util.Ln.d(r6, r7)     // Catch:{ all -> 0x0114 }
            goto L_0x006e
        L_0x0069:
            r0 = 0
            r8 = 0
            r10 = 0
        L_0x006e:
            if (r4 == 0) goto L_0x0076
            r4.close()     // Catch:{ SQLiteException -> 0x011e }
            r2 = 0
            goto L_0x0078
        L_0x0076:
            r2 = 0
        L_0x0078:
            int r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0085
            java.lang.String r0 = "MfpLoginProcedureHelper: fetchUser local id = 0, bail"
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch:{ SQLiteException -> 0x011e }
            com.uacf.core.util.Ln.d(r0, r2)     // Catch:{ SQLiteException -> 0x011e }
            r2 = 0
            return r2
        L_0x0085:
            com.myfitnesspal.shared.model.v1.UserV1 r2 = new com.myfitnesspal.shared.model.v1.UserV1     // Catch:{ SQLiteException -> 0x011e }
            r2.<init>()     // Catch:{ SQLiteException -> 0x011e }
            r2.resetToDefault()     // Catch:{ SQLiteException -> 0x011e }
            com.myfitnesspal.shared.model.v1.UserProfile r3 = new com.myfitnesspal.shared.model.v1.UserProfile     // Catch:{ SQLiteException -> 0x011e }
            r3.<init>(r8)     // Catch:{ SQLiteException -> 0x011e }
            r3.initAsBlankProfile()     // Catch:{ SQLiteException -> 0x011e }
            com.myfitnesspal.shared.model.v1.UserV1NutrientGoals r4 = new com.myfitnesspal.shared.model.v1.UserV1NutrientGoals     // Catch:{ SQLiteException -> 0x011e }
            r4.<init>()     // Catch:{ SQLiteException -> 0x011e }
            r2.setUsername(r0)     // Catch:{ SQLiteException -> 0x011e }
            r2.setProfile(r3)     // Catch:{ SQLiteException -> 0x011e }
            r2.setGoals(r4)     // Catch:{ SQLiteException -> 0x011e }
            r2.setLocalId(r8)     // Catch:{ SQLiteException -> 0x011e }
            r2.setMasterDatabaseId(r10)     // Catch:{ SQLiteException -> 0x011e }
            java.util.Date r0 = new java.util.Date     // Catch:{ SQLiteException -> 0x011e }
            r0.<init>()     // Catch:{ SQLiteException -> 0x011e }
            r2.setActiveDate(r0)     // Catch:{ SQLiteException -> 0x011e }
            com.myfitnesspal.shared.db.DbConnectionManager r0 = r1.dbConnectionManager     // Catch:{ SQLiteException -> 0x011e }
            com.myfitnesspal.shared.db.adapter.UserPropertiesDBAdapter r0 = r0.userPropertiesDbAdapter()     // Catch:{ SQLiteException -> 0x011e }
            android.database.Cursor r6 = r0.loadUserProperties(r8)     // Catch:{ SQLiteException -> 0x011e }
            boolean r0 = r6.moveToFirst()     // Catch:{ all -> 0x010f }
            if (r0 == 0) goto L_0x00d2
        L_0x00c1:
            java.lang.String r0 = r6.getString(r5)     // Catch:{ all -> 0x010f }
            java.lang.String r7 = r6.getString(r14)     // Catch:{ all -> 0x010f }
            r2.setProperty(r0, r7)     // Catch:{ all -> 0x010f }
            boolean r0 = r6.moveToNext()     // Catch:{ all -> 0x010f }
            if (r0 != 0) goto L_0x00c1
        L_0x00d2:
            r6.close()     // Catch:{ SQLiteException -> 0x011e }
            java.lang.String r0 = "goal_calories_per_day"
            float r0 = r4.get(r0)     // Catch:{ SQLiteException -> 0x011e }
            r3.setGoalCalories(r0)     // Catch:{ SQLiteException -> 0x011e }
            com.myfitnesspal.shared.db.DbConnectionManager r0 = r1.dbConnectionManager     // Catch:{ SQLiteException -> 0x011e }
            com.myfitnesspal.shared.db.adapter.MeasurementTypesDBAdapter r0 = r0.measurementTypesDbAdapter()     // Catch:{ SQLiteException -> 0x011e }
            java.util.ArrayList r0 = r0.getMeasurementTypesForUserId(r8)     // Catch:{ SQLiteException -> 0x011e }
            int r4 = r0.size()     // Catch:{ SQLiteException -> 0x011e }
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x011e }
            r6 = 0
        L_0x00ef:
            int r7 = r0.size()     // Catch:{ SQLiteException -> 0x011e }
            if (r6 >= r7) goto L_0x0104
            java.lang.Object r7 = r0.get(r6)     // Catch:{ SQLiteException -> 0x011e }
            com.myfitnesspal.feature.progress.model.MeasurementTypeItem r7 = (com.myfitnesspal.feature.progress.model.MeasurementTypeItem) r7     // Catch:{ SQLiteException -> 0x011e }
            java.lang.String r7 = r7.getDescription()     // Catch:{ SQLiteException -> 0x011e }
            r4[r6] = r7     // Catch:{ SQLiteException -> 0x011e }
            int r6 = r6 + 1
            goto L_0x00ef
        L_0x0104:
            r3.setMeasurementTypes(r4)     // Catch:{ SQLiteException -> 0x011e }
            java.lang.String r0 = "MfpLoginProcedureHelper: fetchUser done, return %s"
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ SQLiteException -> 0x011e }
            com.uacf.core.util.Ln.d(r0, r3)     // Catch:{ SQLiteException -> 0x011e }
            return r2
        L_0x010f:
            r0 = move-exception
            r6.close()     // Catch:{ SQLiteException -> 0x011e }
            throw r0     // Catch:{ SQLiteException -> 0x011e }
        L_0x0114:
            r0 = move-exception
            goto L_0x0118
        L_0x0116:
            r0 = move-exception
            r4 = 0
        L_0x0118:
            if (r4 == 0) goto L_0x011d
            r4.close()     // Catch:{ SQLiteException -> 0x011e }
        L_0x011d:
            throw r0     // Catch:{ SQLiteException -> 0x011e }
        L_0x011e:
            r0 = move-exception
            com.uacf.core.util.Ln.e(r0)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.UserV1DBAdapter.fetchUser(java.lang.String, java.lang.String):com.myfitnesspal.shared.model.v1.UserV1");
    }

    public void updateUsersRowForUser(UserV1 userV1) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(5);
            if (userV1.hasMasterDatabaseId()) {
                this.stmt.bindLong(1, userV1.getMasterDatabaseId());
            } else {
                this.stmt.bindNull(1);
            }
            this.stmt.bindString(2, Strings.toString(userV1.getUsername()));
            this.stmt.bindLong(3, (long) userV1.getThirdPartyServiceId());
            this.stmt.bindString(4, Strings.toString(userV1.getThirdPartyUserId()));
            this.stmt.bindString(5, Strings.toString(userV1.getThirdPartyAuthToken()));
            this.stmt.bindLong(6, userV1.getLocalId());
            this.stmt.execute();
            this.stmt.clearBindings();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void updateFoodOwnerUserIdsForNewUserLocalId(long j, long j2) {
        try {
            new FoodDBAdapter(this.context, this.dbConnectionManager).updateFoodsOwnerUserIdForOwnerUserMasterId(j, j2);
        } catch (SQLiteException e) {
            Ln.e(e);
        }
    }

    public long lookupUserLocalIdFromMasterId(long j) {
        if (j == 0) {
            return 0;
        }
        try {
            this.stmt = DbConnectionManager.preparedStatement(24);
            this.stmt.bindLong(1, j);
            long simpleQueryForLong = this.stmt.simpleQueryForLong();
            this.stmt.clearBindings();
            return simpleQueryForLong;
        } catch (SQLiteDoneException unused) {
            return -1;
        } catch (Exception e) {
            Ln.e(e);
            return -1;
        }
    }

    public void updateUserLastSyncAt(long j) {
        try {
            String encodeDateAndTime = Database.encodeDateAndTime(new Date());
            ContentValues contentValues = new ContentValues();
            contentValues.put("last_sync_at", encodeDateAndTime);
            DbConnectionManager.getDb(this.context).update("users", contentValues, "id = ?", new String[]{String.valueOf(j)});
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void saveAppUnlockPINCode(String str, User user) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(118);
            this.stmt.bindString(1, str);
            this.stmt.bindLong(2, user.getLocalId());
            this.stmt.execute();
            this.stmt.clearBindings();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public String appUnlockPINCodeForUser(UserV1 userV1) {
        String[] strArr = {String.valueOf(userV1.getLocalId())};
        String str = "";
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery("select pincode from users where id = ?", strArr);
        try {
            if (rawQuery.moveToFirst()) {
                str = rawQuery.getString(0);
            }
            return str == null ? "" : str;
        } finally {
            rawQuery.close();
        }
    }
}
