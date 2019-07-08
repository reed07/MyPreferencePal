package com.myfitnesspal.feature.externalsync.impl.googlefit.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.GoogleFitNutritionEntry;
import com.uacf.core.database.DatabaseTable;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.database.SQLiteDatabaseWrapperFactory;
import com.uacf.core.database.SQLiteDatabaseWrapperOpenHelper;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple1;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class GoogleFitDbAdapter {
    static final int UPDATED_SYNC_FLAG = 101010;
    static final int UPLOADING_SYNC_FLAG = 4161;
    private SQLiteDatabaseWrapper db = this.dbHelper.getWritableDatabaseWrapper();
    private SQLiteDatabaseWrapperOpenHelper dbHelper;
    /* access modifiers changed from: private */
    public GoogleFitNutritionTable googleFitNutritionTable = new GoogleFitNutritionTable(this.db);
    /* access modifiers changed from: private */
    public GoogleFitUserTable googleFitUserTable = new GoogleFitUserTable(this.db);

    private static class DatabaseHelper extends SQLiteDatabaseWrapperOpenHelper {
        private static final String DATABASE_NAME = "mfpfit.db";
        private static final int VERSION = 4;

        private DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, 4);
        }

        /* access modifiers changed from: private */
        public DatabaseTable[] allTables(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
            return new DatabaseTable[]{new GoogleFitUserTable(sQLiteDatabaseWrapper), new GoogleFitNutritionTable(sQLiteDatabaseWrapper)};
        }

        public void onCreate(final SQLiteDatabase sQLiteDatabase) {
            final SQLiteDatabaseWrapper wrap = SQLiteDatabaseWrapperFactory.wrap(sQLiteDatabase);
            try {
                DatabaseUtil.ensureDatabaseTransaction(wrap, new Function0() {
                    public void execute() {
                        DatabaseTable[] access$300;
                        for (DatabaseTable databaseTable : DatabaseHelper.this.allTables(wrap)) {
                            databaseTable.onCreate();
                            databaseTable.onUpgrade(1, 4);
                        }
                        sQLiteDatabase.setVersion(4);
                        StringBuilder sb = new StringBuilder();
                        sb.append("Database has been set to version: ");
                        sb.append(sQLiteDatabase.getVersion());
                        Ln.w(sb.toString(), new Object[0]);
                    }
                });
            } catch (Exception e) {
                Ln.e(e);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            for (DatabaseTable onUpgrade : allTables(SQLiteDatabaseWrapperFactory.wrap(sQLiteDatabase))) {
                onUpgrade.onUpgrade(i, i2);
            }
        }
    }

    public GoogleFitDbAdapter(Context context) {
        this.dbHelper = new DatabaseHelper(context);
    }

    public Map<Long, Float> getUserWeights(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(Columns.USER_ID.getColumnName());
        sb.append("=?");
        String sb2 = sb.toString();
        String[] strArr = {str};
        StringBuilder sb3 = new StringBuilder();
        sb3.append(Columns.ENTRY_TIME);
        sb3.append(" asc");
        return this.googleFitUserTable.getUserWeights(SQLiteQueryBuilder.buildQueryString(false, GoogleFitUserTable.TABLE_NAME, null, sb2, null, null, sb3.toString(), null), strArr);
    }

    public int insertOrUpdateFitUserWeight(Date date, float f, String str) {
        Tuple1 create = Tuple.create(Integer.valueOf(0));
        if (date != null) {
            SQLiteDatabaseWrapper sQLiteDatabaseWrapper = this.db;
            final Date date2 = date;
            final float f2 = f;
            final String str2 = str;
            final Tuple1 tuple1 = create;
            AnonymousClass1 r1 = new Function0() {
                public void execute() {
                    if (GoogleFitDbAdapter.this.googleFitUserTable.insertOrUpdateFitUserWeight(date2, f2, str2)) {
                        tuple1.setItem1(Integer.valueOf(1));
                    }
                }
            };
            DatabaseUtil.ensureDatabaseTransaction(sQLiteDatabaseWrapper, r1);
        }
        return ((Integer) create.getItem1()).intValue();
    }

    public int insertFitNutrientEntry(final GoogleFitNutritionEntry googleFitNutritionEntry, final String str) {
        final Tuple1 create = Tuple.create(Integer.valueOf(0));
        if (googleFitNutritionEntry != null) {
            DatabaseUtil.ensureDatabaseTransaction(this.db, new Function0() {
                public void execute() {
                    if (GoogleFitDbAdapter.this.googleFitNutritionTable.insertOrUpdateFitNutrientEntry(googleFitNutritionEntry, str)) {
                        create.setItem1(Integer.valueOf(1));
                    }
                }
            });
        }
        return ((Integer) create.getItem1()).intValue();
    }

    public TreeMap<Long, List<GoogleFitNutritionEntry>> fetchNutrientEntry(final String str) {
        final TreeMap<Long, List<GoogleFitNutritionEntry>> treeMap = new TreeMap<>();
        if (Strings.notEmpty(str)) {
            DatabaseUtil.ensureDatabaseTransaction(this.db, new Function0() {
                public void execute() {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Columns.USER_ID.getColumnName());
                    sb.append("=?");
                    if (GoogleFitDbAdapter.this.googleFitNutritionTable.markAsUploading(sb.toString(), str)) {
                        String format = String.format("%s = ? and %s = ?", new Object[]{Columns.USER_ID.getColumnName(), Columns.SYNC_FLAG.getColumnName()});
                        String[] strArr = {str, String.valueOf(GoogleFitDbAdapter.UPLOADING_SYNC_FLAG)};
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(Columns.ENTRY_DATE);
                        sb2.append(" asc");
                        treeMap.putAll(GoogleFitDbAdapter.this.googleFitNutritionTable.fetchNutrientEntry(SQLiteQueryBuilder.buildQueryString(false, "fit_nutrition", null, format, null, null, sb2.toString(), null), strArr));
                    }
                }
            });
        }
        return treeMap;
    }

    public void clearUserData() {
        this.googleFitUserTable.reset();
    }

    public void clearUploadedNutrientData(String str) {
        this.googleFitNutritionTable.deleteUploadedData(String.format("%s = ? and %s = ?", new Object[]{Columns.USER_ID.getColumnName(), Columns.SYNC_FLAG.getColumnName()}), str, Integer.valueOf(UPLOADING_SYNC_FLAG));
    }
}
