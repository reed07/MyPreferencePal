package com.myfitnesspal.shared.db.table;

import android.database.Cursor;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class NutrientGoalsTable extends MfpDatabaseTableImpl {
    private static final String IDX_GOALS_BY_DATE = "nutrient_goals_by_date_index";
    private static final int REMOVE_VALID_FROM_CONSTRAINT_VERSION = 30;
    public static final String TABLE_NAME = "nutrient_goals";

    public enum Columns {
        ID("_id"),
        USER_ID("user_id"),
        VALID_FROM("valid_from"),
        VALID_TO("valid_to"),
        DATA("data"),
        DEFAULT_GROUP_ID("default_group_id"),
        DEFAULT_GOAL("default_goal"),
        SYNC_FLAG(com.myfitnesspal.shared.db.table.UserApplicationSettingsTable.Columns.SYNC_FLAG);
        
        private String name;

        private Columns(String str) {
            this.name = str;
        }

        public String getColumnName() {
            return this.name;
        }

        public String getColumnStringValue(Cursor cursor) {
            return cursor.getString(cursor.getColumnIndexOrThrow(getColumnName()));
        }

        public int getColumnIntValue(Cursor cursor) {
            return cursor.getInt(cursor.getColumnIndexOrThrow(getColumnName()));
        }
    }

    public void onCreate() {
    }

    @Inject
    public NutrientGoalsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(27, i, i2)) {
            StringBuilder sb = new StringBuilder();
            sb.append(Columns.ID.getColumnName());
            sb.append(" integer primary key autoincrement");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(Columns.USER_ID.getColumnName());
            sb2.append(" text not null");
            StringBuilder sb3 = new StringBuilder();
            sb3.append(Columns.VALID_FROM.getColumnName());
            sb3.append(" text unique not null");
            StringBuilder sb4 = new StringBuilder();
            sb4.append(Columns.VALID_TO.getColumnName());
            sb4.append(" text not null");
            StringBuilder sb5 = new StringBuilder();
            sb5.append(Columns.DATA.getColumnName());
            sb5.append(" text not null");
            StringBuilder sb6 = new StringBuilder();
            sb6.append(Columns.DEFAULT_GROUP_ID.getColumnName());
            sb6.append(" integer not null");
            StringBuilder sb7 = new StringBuilder();
            sb7.append(Columns.DEFAULT_GOAL.getColumnName());
            sb7.append(" text not null");
            StringBuilder sb8 = new StringBuilder();
            sb8.append(Columns.SYNC_FLAG.getColumnName());
            sb8.append(" integer not null");
            createTable(sb.toString(), sb2.toString(), sb3.toString(), sb4.toString(), sb5.toString(), sb6.toString(), sb7.toString(), sb8.toString());
            createIndex(IDX_GOALS_BY_DATE, Columns.USER_ID.getColumnName(), Columns.VALID_FROM.getColumnName());
        }
        if (shouldRunUpgrade(30, i, i2)) {
            DatabaseUtil.ensureDatabaseTransaction(this.database, new Function0() {
                public void execute() {
                    NutrientGoalsTable.this.renameTable("tmp_nutrient_goals");
                    NutrientGoalsTable nutrientGoalsTable = NutrientGoalsTable.this;
                    StringBuilder sb = new StringBuilder();
                    sb.append(Columns.ID.getColumnName());
                    sb.append(" integer primary key autoincrement");
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(Columns.USER_ID.getColumnName());
                    sb2.append(" text not null");
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(Columns.VALID_FROM.getColumnName());
                    sb3.append(" text not null");
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(Columns.VALID_TO.getColumnName());
                    sb4.append(" text not null");
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append(Columns.DATA.getColumnName());
                    sb5.append(" text not null");
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(Columns.DEFAULT_GROUP_ID.getColumnName());
                    sb6.append(" integer not null");
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(Columns.DEFAULT_GOAL.getColumnName());
                    sb7.append(" text not null");
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(Columns.SYNC_FLAG.getColumnName());
                    sb8.append(" integer not null");
                    nutrientGoalsTable.createTable(sb.toString(), sb2.toString(), sb3.toString(), sb4.toString(), sb5.toString(), sb6.toString(), sb7.toString(), sb8.toString());
                    String join = Strings.join(", ", (T[]) new String[]{Columns.ID.getColumnName(), Columns.USER_ID.getColumnName(), Columns.VALID_FROM.getColumnName(), Columns.VALID_TO.getColumnName(), Columns.DATA.getColumnName(), Columns.DEFAULT_GROUP_ID.getColumnName(), Columns.DEFAULT_GOAL.getColumnName(), Columns.SYNC_FLAG.getColumnName()});
                    NutrientGoalsTable.this.insertDataFromOtherTable(join, join, "tmp_nutrient_goals");
                    NutrientGoalsTable.this.dropTable("tmp_nutrient_goals");
                }
            });
        }
    }
}
