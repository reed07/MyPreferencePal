package com.myfitnesspal.shared.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteQueryBuilder;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.NutrientGoalsTable;
import com.myfitnesspal.shared.db.table.NutrientGoalsTable.Columns;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.ItemSyncState;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple1;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.Nonnull;

public class NutrientGoalsServiceDbAdapter {
    private static final String API_DATE_FORMAT = Format.newIso8601DateFormat().toPattern();
    private SQLiteDatabaseWrapper db;
    private NutrientGoalsTable nutrientGoalsTable = new NutrientGoalsTable(this.db);
    private final Lazy<Session> session;

    public NutrientGoalsServiceDbAdapter(Context context, @Nonnull Lazy<Session> lazy) {
        this.db = DbConnectionManager.getDb(context);
        this.session = lazy;
    }

    public boolean insertOrUpdateNutrientGoals(MfpNutrientGoal mfpNutrientGoal) {
        StringBuilder sb = new StringBuilder();
        sb.append(Columns.VALID_FROM.getColumnName());
        sb.append(" = ? AND ");
        sb.append(Columns.USER_ID.getColumnName());
        sb.append(" = ?");
        String sb2 = sb.toString();
        String userId = ((Session) this.session.get()).getUser().getUserId();
        String[] strArr = new String[2];
        boolean z = false;
        strArr[0] = mfpNutrientGoal.getValidFrom();
        strArr[1] = Strings.notEmpty(mfpNutrientGoal.getUserId()) ? mfpNutrientGoal.getUserId() : userId;
        ApiJsonMapper apiJsonMapper = new ApiJsonMapper();
        ContentValues contentValues = new ContentValues();
        String columnName = Columns.USER_ID.getColumnName();
        if (Strings.notEmpty(mfpNutrientGoal.getUserId())) {
            userId = mfpNutrientGoal.getUserId();
        }
        contentValues.put(columnName, userId);
        contentValues.put(Columns.VALID_FROM.getColumnName(), mfpNutrientGoal.getValidFrom());
        contentValues.put(Columns.VALID_TO.getColumnName(), mfpNutrientGoal.getValidTo());
        contentValues.put(Columns.DATA.getColumnName(), apiJsonMapper.reverseMap((Object) mfpNutrientGoal.getDailyGoals()));
        contentValues.put(Columns.DEFAULT_GROUP_ID.getColumnName(), Integer.valueOf(mfpNutrientGoal.getDefaultGroupId()));
        contentValues.put(Columns.DEFAULT_GOAL.getColumnName(), apiJsonMapper.reverseMap((Object) mfpNutrientGoal.getDefaultGoal()));
        contentValues.put(Columns.SYNC_FLAG.getColumnName(), Integer.valueOf(ItemSyncState.Updated.getId()));
        if (this.nutrientGoalsTable.updateData(contentValues, sb2, (Object[]) strArr) != 0) {
            return true;
        }
        contentValues.put(Columns.SYNC_FLAG.getColumnName(), Integer.valueOf(ItemSyncState.Created.getId()));
        if (this.nutrientGoalsTable.insertData(contentValues) > 0) {
            z = true;
        }
        return z;
    }

    public int setNutrientGoal(final MfpNutrientGoal mfpNutrientGoal) {
        final Tuple1 create = Tuple.create(Integer.valueOf(0));
        if (mfpNutrientGoal != null) {
            DatabaseUtil.ensureDatabaseTransaction(this.db, new Function0() {
                public void execute() {
                    if (NutrientGoalsServiceDbAdapter.this.insertOrUpdateNutrientGoals(mfpNutrientGoal)) {
                        create.setItem1(Integer.valueOf(1));
                    }
                }
            });
        }
        return ((Integer) create.getItem1()).intValue();
    }

    public int setNutrientGoals(final List<MfpNutrientGoal> list) {
        final Tuple1 create = Tuple.create(Integer.valueOf(0));
        if (list != null) {
            DatabaseUtil.ensureDatabaseTransaction(this.db, new Function0() {
                public void execute() {
                    int i = 0;
                    for (MfpNutrientGoal insertOrUpdateNutrientGoals : list) {
                        if (NutrientGoalsServiceDbAdapter.this.insertOrUpdateNutrientGoals(insertOrUpdateNutrientGoals)) {
                            i++;
                        }
                    }
                    create.setItem1(Integer.valueOf(i));
                }
            });
        }
        return ((Integer) create.getItem1()).intValue();
    }

    public MfpNutrientGoal getNutrientGoal(Date date) {
        if (DateTimeUtils.isDateInFuture(date)) {
            date = Calendar.getInstance().getTime();
        }
        String format = DateTimeUtils.format(API_DATE_FORMAT, date);
        StringBuilder sb = new StringBuilder();
        sb.append(Columns.USER_ID.getColumnName());
        sb.append("=? AND ");
        sb.append(Columns.VALID_FROM);
        sb.append(" <=? AND ?<= ");
        sb.append(Columns.VALID_TO);
        String sb2 = sb.toString();
        String[] strArr = {Strings.toString(((Session) this.session.get()).getUser().getUserId()), format, format};
        StringBuilder sb3 = new StringBuilder();
        sb3.append(Columns.VALID_FROM);
        sb3.append(" DESC");
        List nutrientGoals = getNutrientGoals(SQLiteQueryBuilder.buildQueryString(false, NutrientGoalsTable.TABLE_NAME, null, sb2, null, null, sb3.toString(), null), strArr);
        if (CollectionUtils.notEmpty((Collection<?>) nutrientGoals)) {
            return (MfpNutrientGoal) nutrientGoals.get(0);
        }
        return null;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0012 */
    /* JADX WARNING: Removed duplicated region for block: B:2:0x0012 A[LOOP:0: B:2:0x0012->B:12:0x0012, LOOP_START, SYNTHETIC, Splitter:B:2:0x0012] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.myfitnesspal.shared.model.v2.MfpNutrientGoal> getNutrientGoals(java.lang.String r5, java.lang.String[] r6) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.myfitnesspal.shared.db.table.NutrientGoalsTable r1 = r4.nutrientGoalsTable
            android.database.Cursor r5 = r1.rawQuery(r5, r6)
            com.myfitnesspal.shared.model.mapper.ApiJsonMapper r6 = new com.myfitnesspal.shared.model.mapper.ApiJsonMapper
            r6.<init>()
            if (r5 == 0) goto L_0x0081
        L_0x0012:
            boolean r1 = r5.moveToNext()     // Catch:{ all -> 0x007c }
            if (r1 == 0) goto L_0x0078
            com.myfitnesspal.shared.model.v2.MfpNutrientGoal r1 = new com.myfitnesspal.shared.model.v2.MfpNutrientGoal     // Catch:{ IOException -> 0x0012 }
            r1.<init>()     // Catch:{ IOException -> 0x0012 }
            com.myfitnesspal.shared.db.table.NutrientGoalsTable$Columns r2 = com.myfitnesspal.shared.db.table.NutrientGoalsTable.Columns.USER_ID     // Catch:{ IOException -> 0x0012 }
            java.lang.String r2 = r2.getColumnStringValue(r5)     // Catch:{ IOException -> 0x0012 }
            r1.setUserId(r2)     // Catch:{ IOException -> 0x0012 }
            com.myfitnesspal.shared.db.table.NutrientGoalsTable$Columns r2 = com.myfitnesspal.shared.db.table.NutrientGoalsTable.Columns.VALID_FROM     // Catch:{ IOException -> 0x0012 }
            java.lang.String r2 = r2.getColumnStringValue(r5)     // Catch:{ IOException -> 0x0012 }
            r1.setValidFrom(r2)     // Catch:{ IOException -> 0x0012 }
            com.myfitnesspal.shared.db.table.NutrientGoalsTable$Columns r2 = com.myfitnesspal.shared.db.table.NutrientGoalsTable.Columns.VALID_TO     // Catch:{ IOException -> 0x0012 }
            java.lang.String r2 = r2.getColumnStringValue(r5)     // Catch:{ IOException -> 0x0012 }
            r1.setValidTo(r2)     // Catch:{ IOException -> 0x0012 }
            com.myfitnesspal.shared.db.table.NutrientGoalsTable$Columns r2 = com.myfitnesspal.shared.db.table.NutrientGoalsTable.Columns.DATA     // Catch:{ IOException -> 0x0012 }
            java.lang.String r2 = r2.getColumnStringValue(r5)     // Catch:{ IOException -> 0x0012 }
            java.lang.Class<com.myfitnesspal.shared.model.v2.MfpDailyGoal$LIST_MAPPER> r3 = com.myfitnesspal.shared.model.v2.MfpDailyGoal.LIST_MAPPER.class
            java.lang.Object r2 = r6.mapFrom(r2, r3)     // Catch:{ IOException -> 0x0012 }
            java.util.List r2 = (java.util.List) r2     // Catch:{ IOException -> 0x0012 }
            r1.setDailyGoals(r2)     // Catch:{ IOException -> 0x0012 }
            com.myfitnesspal.shared.db.table.NutrientGoalsTable$Columns r2 = com.myfitnesspal.shared.db.table.NutrientGoalsTable.Columns.SYNC_FLAG     // Catch:{ IOException -> 0x0012 }
            int r2 = r2.getColumnIntValue(r5)     // Catch:{ IOException -> 0x0012 }
            r1.setSyncFlag(r2)     // Catch:{ IOException -> 0x0012 }
            com.myfitnesspal.shared.db.table.NutrientGoalsTable$Columns r2 = com.myfitnesspal.shared.db.table.NutrientGoalsTable.Columns.DEFAULT_GOAL     // Catch:{ IOException -> 0x0012 }
            java.lang.String r2 = r2.getColumnStringValue(r5)     // Catch:{ IOException -> 0x0012 }
            java.lang.Class<com.myfitnesspal.shared.model.v2.MfpDailyGoal> r3 = com.myfitnesspal.shared.model.v2.MfpDailyGoal.class
            java.lang.Object r2 = r6.mapFrom(r2, r3)     // Catch:{ IOException -> 0x0012 }
            com.myfitnesspal.shared.model.v2.MfpDailyGoal r2 = (com.myfitnesspal.shared.model.v2.MfpDailyGoal) r2     // Catch:{ IOException -> 0x0012 }
            r1.setDefaultGoal(r2)     // Catch:{ IOException -> 0x0012 }
            com.myfitnesspal.shared.db.table.NutrientGoalsTable$Columns r2 = com.myfitnesspal.shared.db.table.NutrientGoalsTable.Columns.DEFAULT_GROUP_ID     // Catch:{ IOException -> 0x0012 }
            int r2 = r2.getColumnIntValue(r5)     // Catch:{ IOException -> 0x0012 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ IOException -> 0x0012 }
            int r2 = r2.intValue()     // Catch:{ IOException -> 0x0012 }
            r1.setDefaultGroupId(r2)     // Catch:{ IOException -> 0x0012 }
            r0.add(r1)     // Catch:{ IOException -> 0x0012 }
            goto L_0x0012
        L_0x0078:
            r5.close()
            goto L_0x0081
        L_0x007c:
            r6 = move-exception
            r5.close()
            throw r6
        L_0x0081:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.NutrientGoalsServiceDbAdapter.getNutrientGoals(java.lang.String, java.lang.String[]):java.util.List");
    }
}
