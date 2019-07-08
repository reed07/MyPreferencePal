package com.myfitnesspal.shared.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.myfitnesspal.shared.constants.Constants.Database;
import com.myfitnesspal.shared.db.table.DeletedItemsTable;
import com.myfitnesspal.shared.db.table.DeletedMostUsedFoodsTable;
import com.myfitnesspal.shared.db.table.DiaryNotesTable;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.ExerciseEntryPropertiesTable;
import com.myfitnesspal.shared.db.table.ExercisesTable;
import com.myfitnesspal.shared.db.table.FoodEntriesTable;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.db.table.FoodPermissionsTable;
import com.myfitnesspal.shared.db.table.FoodPortionsTable;
import com.myfitnesspal.shared.db.table.FoodsTable;
import com.myfitnesspal.shared.db.table.GlobalApplicationPreferencesTable;
import com.myfitnesspal.shared.db.table.ImageAssociationsTable;
import com.myfitnesspal.shared.db.table.ImagesTable;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable;
import com.myfitnesspal.shared.db.table.ItemUsageCountsTable;
import com.myfitnesspal.shared.db.table.MealIngredientsTable;
import com.myfitnesspal.shared.db.table.MeasurementTypesTable;
import com.myfitnesspal.shared.db.table.MeasurementsTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTable;
import com.myfitnesspal.shared.db.table.NutrientGoalsTable;
import com.myfitnesspal.shared.db.table.NutritionalValuesTable;
import com.myfitnesspal.shared.db.table.ProfileImagesTable;
import com.myfitnesspal.shared.db.table.RecipeBoxItemsTable;
import com.myfitnesspal.shared.db.table.RecipeIngredientsTable;
import com.myfitnesspal.shared.db.table.RecipePropertiesTable;
import com.myfitnesspal.shared.db.table.RecipesTable;
import com.myfitnesspal.shared.db.table.RemindersTable;
import com.myfitnesspal.shared.db.table.StepsTable;
import com.myfitnesspal.shared.db.table.SyncPointersTable;
import com.myfitnesspal.shared.db.table.TrackedNutrientsTable;
import com.myfitnesspal.shared.db.table.UserApplicationSettingsTable;
import com.myfitnesspal.shared.db.table.UserPropertiesTable;
import com.myfitnesspal.shared.db.table.UsersTableV1;
import com.myfitnesspal.shared.db.table.UsersTableV2;
import com.myfitnesspal.shared.db.table.WaterEntriesTable;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.database.SQLiteDatabaseWrapperFactory;
import com.uacf.core.database.SQLiteDatabaseWrapperOpenHelper;
import com.uacf.core.util.Ln;
import java.io.File;

public class MyFitnessPalSQLiteOpenHelper extends SQLiteDatabaseWrapperOpenHelper {
    public MyFitnessPalSQLiteOpenHelper(Context context) {
        super(context, Database.MAIN_DATABASE_NAME, null, 48);
        File parentFile = context.getDatabasePath(Database.MAIN_DATABASE_NAME).getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        MfpDatabaseTable[] mfpDatabaseTables;
        SQLiteDatabaseWrapper wrap = SQLiteDatabaseWrapperFactory.wrap(sQLiteDatabase);
        try {
            sQLiteDatabase.beginTransaction();
            for (MfpDatabaseTable mfpDatabaseTable : getMfpDatabaseTables(wrap)) {
                mfpDatabaseTable.onCreate();
                mfpDatabaseTable.onUpgrade(1, 48);
            }
            sQLiteDatabase.setVersion(48);
            StringBuilder sb = new StringBuilder();
            sb.append("Database has been set to version: ");
            sb.append(sQLiteDatabase.getVersion());
            Ln.w(sb.toString(), new Object[0]);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        SQLiteDatabaseWrapper wrap = SQLiteDatabaseWrapperFactory.wrap(sQLiteDatabase);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Upgrading from version ");
            sb.append(i);
            sb.append(" to ");
            sb.append(i2);
            sb.append(".");
            Ln.w(sb.toString(), new Object[0]);
            sQLiteDatabase.beginTransaction();
            for (MfpDatabaseTable onUpgrade : getMfpDatabaseTables(wrap)) {
                onUpgrade.onUpgrade(i, 48);
            }
            sQLiteDatabase.setVersion(i2);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Database has been set to version: ");
            sb2.append(sQLiteDatabase.getVersion());
            Ln.w(sb2.toString(), new Object[0]);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private MfpDatabaseTable[] getMfpDatabaseTables(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new MfpDatabaseTable[]{new DeletedItemsTable(sQLiteDatabaseWrapper), new DeletedMostUsedFoodsTable(sQLiteDatabaseWrapper), new DiaryNotesTable(sQLiteDatabaseWrapper), new ExerciseEntriesTable(sQLiteDatabaseWrapper), new ExerciseEntryPropertiesTable(sQLiteDatabaseWrapper), new ExercisesTable(sQLiteDatabaseWrapper), new FoodEntriesTable(sQLiteDatabaseWrapper), new FoodsTable(sQLiteDatabaseWrapper), new FoodNotesTable(sQLiteDatabaseWrapper), new FoodPermissionsTable(sQLiteDatabaseWrapper), new FoodPortionsTable(sQLiteDatabaseWrapper), new GlobalApplicationPreferencesTable(sQLiteDatabaseWrapper), new ProfileImagesTable(sQLiteDatabaseWrapper), new ImagesTable(sQLiteDatabaseWrapper), new ImageAssociationsTable(sQLiteDatabaseWrapper), new InstalledDatasetsTable(sQLiteDatabaseWrapper), new ItemUsageCountsTable(sQLiteDatabaseWrapper), new MealIngredientsTable(sQLiteDatabaseWrapper), new MeasurementsTable(sQLiteDatabaseWrapper), new MeasurementTypesTable(sQLiteDatabaseWrapper), new NutrientGoalsTable(sQLiteDatabaseWrapper), new NutritionalValuesTable(sQLiteDatabaseWrapper), new RecipeBoxItemsTable(sQLiteDatabaseWrapper), new RecipeIngredientsTable(sQLiteDatabaseWrapper), new RecipePropertiesTable(sQLiteDatabaseWrapper), new RecipesTable(sQLiteDatabaseWrapper), new RemindersTable(sQLiteDatabaseWrapper), new StepsTable(sQLiteDatabaseWrapper), new SyncPointersTable(sQLiteDatabaseWrapper), new TrackedNutrientsTable(sQLiteDatabaseWrapper), new UserApplicationSettingsTable(sQLiteDatabaseWrapper), new UserPropertiesTable(sQLiteDatabaseWrapper), new UsersTableV2(sQLiteDatabaseWrapper), new UsersTableV1(sQLiteDatabaseWrapper), new WaterEntriesTable(sQLiteDatabaseWrapper)};
    }
}
