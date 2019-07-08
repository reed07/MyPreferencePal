package com.myfitnesspal.shared.db;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.Constants.Database.Recipes.Queries;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import com.myfitnesspal.shared.constants.Constants.Database.Users.Queries.UpdateUserRow;
import com.myfitnesspal.shared.db.adapter.DeletedItemsDBAdapter;
import com.myfitnesspal.shared.db.adapter.DiaryNoteDbAdapter;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter;
import com.myfitnesspal.shared.db.adapter.FoodPortionsDBAdapter;
import com.myfitnesspal.shared.db.adapter.GenericAdapter;
import com.myfitnesspal.shared.db.adapter.InstalledDatasetsDBAdapter;
import com.myfitnesspal.shared.db.adapter.MealIngredientsDBAdapter;
import com.myfitnesspal.shared.db.adapter.MeasurementTypesDBAdapter;
import com.myfitnesspal.shared.db.adapter.NutritionalValuesDBAdapter;
import com.myfitnesspal.shared.db.adapter.RecipeBoxItemsDBAdapter;
import com.myfitnesspal.shared.db.adapter.RecipeIngredientsDBAdapter;
import com.myfitnesspal.shared.db.adapter.RecipePropertiesDBAdapter;
import com.myfitnesspal.shared.db.adapter.TrackedNutrientDbAdapter;
import com.myfitnesspal.shared.db.adapter.UserPropertiesDBAdapter;
import com.myfitnesspal.shared.db.adapter.UserV1DBAdapter;
import com.myfitnesspal.shared.db.adapter.WaterEntriesDBAdapter;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.HashMap;

public class DbConnectionManager {
    private static SQLiteDatabaseWrapper db;
    private static MyFitnessPalSQLiteOpenHelper dbHelper;
    private static HashMap<Integer, SQLiteStatement> preparedStatements = new HashMap<>();
    private static HashMap<Integer, String> queryStatements = new HashMap<>();
    private static SQLiteDatabaseWrapper stockDb;
    private static StockDbSQLiteOpenHelper stockDbHelper;
    private DeletedItemsDBAdapter deletedItemsDbAdapter;
    private DiaryNoteDbAdapter diaryNoteDbAdapter = new DiaryNoteDbAdapter();
    private FoodDBAdapter foodDbAdapter;
    private FoodEntriesDBAdapter foodEntriesDbAdapter;
    private FoodPortionsDBAdapter foodPortionsDBAdapter;
    private GenericAdapter genericDbAdapter;
    private InstalledDatasetsDBAdapter installedDatasetsDbAdapter;
    private MealIngredientsDBAdapter mealIngredientsDbAdapter;
    private MeasurementTypesDBAdapter measurementTypesDbAdapter;
    private NutritionalValuesDBAdapter nutritionalValuesDBAdapter;
    private RecipeBoxItemsDBAdapter recipeBoxItemsDBAdapter;
    private RecipeIngredientsDBAdapter recipeIngredientsDBAdapter;
    private RecipePropertiesDBAdapter recipePropertiesDBAdapter;
    private TrackedNutrientDbAdapter trackedNutrientDbAdapter;
    private UserPropertiesDBAdapter userPropertiesDbAdapter;
    private UserV1DBAdapter userV1DBAdapter;
    private WaterEntriesDBAdapter waterEntriesDBAdapter;

    public DbConnectionManager(Context context, Lazy<AppSettings> lazy, Lazy<ExerciseService> lazy2) {
        Ln.w("initializing Db connection manager...", new Object[0]);
        open(context);
        this.deletedItemsDbAdapter = new DeletedItemsDBAdapter(context);
        this.foodDbAdapter = new FoodDBAdapter(context, this);
        this.recipeIngredientsDBAdapter = new RecipeIngredientsDBAdapter(context, this);
        this.recipePropertiesDBAdapter = new RecipePropertiesDBAdapter(context);
        this.recipeBoxItemsDBAdapter = new RecipeBoxItemsDBAdapter(context, this);
        this.foodEntriesDbAdapter = new FoodEntriesDBAdapter(context, this);
        this.genericDbAdapter = new GenericAdapter(context, lazy2, lazy, this);
        this.installedDatasetsDbAdapter = new InstalledDatasetsDBAdapter(context);
        this.mealIngredientsDbAdapter = new MealIngredientsDBAdapter(context, this);
        this.measurementTypesDbAdapter = new MeasurementTypesDBAdapter(context);
        this.userPropertiesDbAdapter = new UserPropertiesDBAdapter(context);
        this.userV1DBAdapter = new UserV1DBAdapter(context, this);
        this.waterEntriesDBAdapter = new WaterEntriesDBAdapter(context);
        this.trackedNutrientDbAdapter = new TrackedNutrientDbAdapter(context);
        this.nutritionalValuesDBAdapter = new NutritionalValuesDBAdapter(context);
        this.foodPortionsDBAdapter = new FoodPortionsDBAdapter(context);
        createPreparedStatements();
    }

    private static boolean open(Context context) {
        if (dbHelper == null) {
            dbHelper = new MyFitnessPalSQLiteOpenHelper(context);
        }
        try {
            if (!openWritableDatabase()) {
                if (!openReadableDatabase()) {
                    Ln.e("Unable to open the database", new Object[0]);
                    return false;
                }
            }
            return true;
        } catch (SQLiteException e) {
            Ln.e(e, "Unable to open the database", new Object[0]);
            return false;
        }
    }

    private static boolean openWritableDatabase() {
        try {
            db = dbHelper.getWritableDatabaseWrapper();
            return true;
        } catch (SQLiteException e) {
            Ln.e(e, "Unable to open a writable database", new Object[0]);
            return false;
        }
    }

    private static boolean openReadableDatabase() {
        try {
            db = dbHelper.getReadableDatabaseWrapper();
            return true;
        } catch (SQLiteException e) {
            Ln.e(e, "Unable to open a readable database", new Object[0]);
            return false;
        }
    }

    private static boolean openStockDb(Context context, Lazy<AppSettings> lazy) {
        if (stockDbHelper == null) {
            stockDbHelper = new StockDbSQLiteOpenHelper(context, lazy);
        }
        try {
            if (!openWritableStockDatabase()) {
                if (!openReadableStockDatabase()) {
                    Ln.e("Unable to open the stock database", new Object[0]);
                    return false;
                }
            }
            return true;
        } catch (SQLiteException e) {
            Ln.e(e, "Unable to open the stock database", new Object[0]);
            return false;
        }
    }

    private static boolean openWritableStockDatabase() {
        try {
            stockDb = stockDbHelper.getWritableDatabaseWrapper();
            return true;
        } catch (SQLiteException e) {
            Ln.e(e, "Unable to open a writable stock database", new Object[0]);
            return false;
        }
    }

    private static boolean openReadableStockDatabase() {
        try {
            stockDb = stockDbHelper.getReadableDatabaseWrapper();
            return true;
        } catch (SQLiteException e) {
            Ln.e(e, "Unable to open a readable stock database", new Object[0]);
            return false;
        }
    }

    protected static void close() {
        db.close();
    }

    public static SQLiteDatabaseWrapper getDb(Context context) {
        try {
            if (db == null || !db.isOpen()) {
                open(context);
            }
        } catch (Exception e) {
            Ln.e(e);
        }
        return db;
    }

    public static SQLiteDatabaseWrapper getStockDb(Context context, Lazy<AppSettings> lazy) {
        SQLiteDatabaseWrapper sQLiteDatabaseWrapper = stockDb;
        if (sQLiteDatabaseWrapper == null || !sQLiteDatabaseWrapper.isOpen()) {
            openStockDb(context, lazy);
        }
        return stockDb;
    }

    public DeletedItemsDBAdapter deletedItemsDbAdapter() {
        return this.deletedItemsDbAdapter;
    }

    public RecipeIngredientsDBAdapter recipeIngredientsDBAdapter() {
        return this.recipeIngredientsDBAdapter;
    }

    public RecipePropertiesDBAdapter recipePropertiesDBAdapter() {
        return this.recipePropertiesDBAdapter;
    }

    public RecipeBoxItemsDBAdapter recipeBoxItemsDBAdapter() {
        return this.recipeBoxItemsDBAdapter;
    }

    public FoodDBAdapter foodDbAdapter() {
        return this.foodDbAdapter;
    }

    public FoodEntriesDBAdapter foodEntriesDbAdapter() {
        return this.foodEntriesDbAdapter;
    }

    public GenericAdapter genericDbAdapter() {
        return this.genericDbAdapter;
    }

    public InstalledDatasetsDBAdapter installedDatasetsDbAdapter() {
        return this.installedDatasetsDbAdapter;
    }

    public MealIngredientsDBAdapter mealIngredientsDbAdapter() {
        return this.mealIngredientsDbAdapter;
    }

    public MeasurementTypesDBAdapter measurementTypesDbAdapter() {
        return this.measurementTypesDbAdapter;
    }

    public UserPropertiesDBAdapter userPropertiesDbAdapter() {
        return this.userPropertiesDbAdapter;
    }

    public UserV1DBAdapter usersDbAdapter() {
        return this.userV1DBAdapter;
    }

    public WaterEntriesDBAdapter waterEntriesDbAdapter() {
        return this.waterEntriesDBAdapter;
    }

    public DiaryNoteDbAdapter diaryNoteDbAdapter() {
        return this.diaryNoteDbAdapter;
    }

    public NutritionalValuesDBAdapter nutritionalValuesDBAdapter() {
        return this.nutritionalValuesDBAdapter;
    }

    public FoodPortionsDBAdapter foodPortionsDBAdapter() {
        return this.foodPortionsDBAdapter;
    }

    public void createPreparedStatements() {
        preparedStatements.put(Integer.valueOf(1), db.compileStatement("insert into food_entries (user_id, master_id, uid,entry_date, food_id, original_food_id, meal_id, quantity, weight_index, fraction, meal_food_id, entry_time, logged_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(3), db.compileStatement("delete from water_entries where (user_id = ? and entry_date = ?) or master_id = ?"));
        preparedStatements.put(Integer.valueOf(4), db.compileStatement("insert into water_entries (master_id, uid, user_id, entry_date, cups, milliliters) values (?, ?, ?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(5), db.compileStatement(UpdateUserRow.QUERY));
        preparedStatements.put(Integer.valueOf(6), db.compileStatement("update foods set deleted = 1, destroyed = ? where id = ?"));
        preparedStatements.put(Integer.valueOf(14), db.compileStatement("insert into foods (master_id, original_food_id, original_food_master_id, owner_user_id, owner_user_master_id, food_type, deleted, is_public, description, brand, food_info, food_barcode, uid, original_uid, food_verified, food_grams, promoted_from_master_id, promoted_from_uid) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(17), db.compileStatement("update foods set original_food_id = ?, original_food_master_id = ?, original_uid = ? where id = ?"));
        preparedStatements.put(Integer.valueOf(18), db.compileStatement("update foods set original_food_id = ? where original_food_master_id = ?"));
        preparedStatements.put(Integer.valueOf(19), db.compileStatement("update food_entries set original_food_id = ? where food_id = ?"));
        preparedStatements.put(Integer.valueOf(20), db.compileStatement("insert into deleted_items (user_id, item_type, item_master_id, item_uid, is_destroyed, deleted_at) values (?, ?, ?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(22), db.compileStatement("delete from meal_ingredients where meal_food_id = ?"));
        preparedStatements.put(Integer.valueOf(23), db.compileStatement("insert into meal_ingredients (master_id, user_id, meal_food_id, ingredient_food_id, quantity, weight_index, fraction) values (?, ?, ?, ?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(24), db.compileStatement("select id from users where master_id = ?"));
        preparedStatements.put(Integer.valueOf(31), db.compileStatement("delete from diary_notes where user_id = ? and entry_date = ? and note_type = ?"));
        preparedStatements.put(Integer.valueOf(32), db.compileStatement("insert into diary_notes (master_id, uid, user_id, entry_date, note_type, body) values (?, ?, ?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(33), db.compileStatement("delete from diary_notes where user_id = ? and id = ?"));
        preparedStatements.put(Integer.valueOf(39), db.compileStatement("update user_properties set property_value = ?, updated_at = ? where user_id = ? and property_name = ?"));
        preparedStatements.put(Integer.valueOf(40), db.compileStatement("insert into user_properties (user_id, property_name, property_value, updated_at) values (?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(41), db.compileStatement("update user_properties set last_sync_at = ? where user_id = ?"));
        preparedStatements.put(Integer.valueOf(56), db.compileStatement("update foods set original_food_id = id where original_food_id = ?"));
        preparedStatements.put(Integer.valueOf(57), db.compileStatement("update food_entries set original_food_id = food_id where original_food_id = ?"));
        preparedStatements.put(Integer.valueOf(58), db.compileStatement("select count(*) from food_entries where user_id = ? and original_food_id = ?"));
        preparedStatements.put(Integer.valueOf(59), db.compileStatement("delete from food_entries where id = ?"));
        preparedStatements.put(Integer.valueOf(63), db.compileStatement("select id from food_entries where user_id = ? order by id desc limit 1 offset ?"));
        preparedStatements.put(Integer.valueOf(73), db.compileStatement("delete from recipe_ingredients where recipe_food_id = ?"));
        preparedStatements.put(Integer.valueOf(74), db.compileStatement("delete from recipe_ingredients where master_id = ?"));
        preparedStatements.put(Integer.valueOf(75), db.compileStatement("insert into recipe_ingredients (master_id, recipe_food_id, position, ingredient_food_id, quantity, weight_index, fraction) values (?, ?, ?, ?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(76), db.compileStatement("delete from recipe_properties where recipe_food_id = ?"));
        preparedStatements.put(Integer.valueOf(77), db.compileStatement("insert into recipe_properties (recipe_food_id, position, property_type, property_value) values (?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(79), db.compileStatement("update recipe_box_items set food_id = ?, original_food_id = ?, original_food_master_id = ?, food_description = ? where id = ?"));
        preparedStatements.put(Integer.valueOf(80), db.compileStatement("insert into recipe_box_items (user_id, master_id, food_id, original_food_id, original_food_master_id, food_description, food_uid, original_food_uid) values (?, ?, ?, ?, ?, ?, ?, ?)"));
        preparedStatements.put(Integer.valueOf(81), db.compileStatement("update recipe_box_items set food_id = ?, food_description = ? where original_food_master_id = ?"));
        preparedStatements.put(Integer.valueOf(82), db.compileStatement("update foods set food_type = ? where id = ?"));
        preparedStatements.put(Integer.valueOf(83), db.compileStatement("delete from recipe_box_items where id = ?"));
        preparedStatements.put(Integer.valueOf(84), db.compileStatement("delete from recipe_box_items where user_id = ? and master_id = ?"));
        preparedStatements.put(Integer.valueOf(118), db.compileStatement("update users set pincode = ? where id = ?"));
        preparedStatements.put(Integer.valueOf(123), db.compileStatement("delete from tracked_nutrients where master_id = ?"));
        preparedStatements.put(Integer.valueOf(125), db.compileStatement("insert into tracked_nutrients (master_id, uid, user_id, position, nutrient_name_id) values (?, ?, ?, ?, ?)"));
        queryStatements.put(Integer.valueOf(120), "select master_id, uid, position, nutrient_name_id from tracked_nutrients where id = ?");
        queryStatements.put(Integer.valueOf(122), "select id from tracked_nutrients where user_id = ? and master_id = ?");
        queryStatements.put(Integer.valueOf(129), "select id, master_id, food_id, food_description from recipe_box_items where user_id = ? order by id asc limit ? offset ?");
        queryStatements.put(Integer.valueOf(2), "select id, original_food_id from foods where master_id = ?");
        queryStatements.put(Integer.valueOf(16), "select master_id, original_food_master_id, uid, original_uid from foods where id = ?");
        queryStatements.put(Integer.valueOf(34), "select id, master_id, user_id, entry_date, note_type, body from diary_notes where id = ?");
        queryStatements.put(Integer.valueOf(35), "select id, master_id, user_id, entry_date, note_type, body from diary_notes where user_id = ? and entry_date = ?");
        queryStatements.put(Integer.valueOf(38), "select property_value from user_properties where user_id = ? and property_name = ?");
        queryStatements.put(Integer.valueOf(65), "select id from foods where owner_user_id = ? and deleted = 0 and food_type = ? order by description asc limit ? offset ?");
        queryStatements.put(Integer.valueOf(66), "select id from foods where owner_user_id = ? and deleted = 0 and food_type = ? order by description desc limit ? offset ?");
        queryStatements.put(Integer.valueOf(128), "select id from foods where owner_user_id = ? and deleted = 0 and food_type = ? limit ? offset ?");
        queryStatements.put(Integer.valueOf(67), "select distinct(foods.id) from foods left outer join food_entries on foods.id = food_entries.food_id where foods.owner_user_id = ? and foods.deleted = 0 and foods.food_type = ? order by food_entries.entry_date desc limit ? offset ?");
        queryStatements.put(Integer.valueOf(126), "select id from foods where owner_user_id = ? and deleted = 0 and food_type = ? order by id asc limit ? offset ?");
        queryStatements.put(Integer.valueOf(Statements.GetOwnedFoodIdsDateDescending), "select id from foods where owner_user_id = ? and deleted = 0 and food_type = ? order by id desc limit ? offset ?");
        queryStatements.put(Integer.valueOf(78), "select id from recipe_box_items where user_id = ? and master_id = ?");
        queryStatements.put(Integer.valueOf(89), "select id, master_id, ingredient_food_id, quantity, weight_index, fraction from recipe_ingredients where recipe_food_id = ? order by position asc");
        queryStatements.put(Integer.valueOf(90), "select property_type, property_value from recipe_properties where recipe_food_id = ? order by property_type asc, position asc");
        queryStatements.put(Integer.valueOf(136), "select recipe_food_id from recipe_properties where property_type = 'recipe_original_uid' and property_value = ?");
        queryStatements.put(Integer.valueOf(91), String.format(Queries.GET_RECIPE_BOX_ITEMS_ALPHABETICAL_WITH_SORT_FORMAT, new Object[]{"asc"}));
        queryStatements.put(Integer.valueOf(92), String.format(Queries.GET_RECIPE_BOX_ITEMS_ALPHABETICAL_WITH_SORT_FORMAT, new Object[]{"desc"}));
        queryStatements.put(Integer.valueOf(93), "select rbi.id, rbi.master_id, rbi.food_id, rbi.food_description from recipe_box_items rbi join foods f on f.id = rbi.food_id where rbi.user_id = ? and f.deleted = 0 order by rbi.id desc limit ? offset ?");
        queryStatements.put(Integer.valueOf(94), "select id, description from measurement_types where user_id = ? order by position asc");
    }

    public static SQLiteStatement preparedStatement(int i) {
        return (SQLiteStatement) preparedStatements.get(Integer.valueOf(i));
    }

    public static String queryString(int i) {
        return (String) queryStatements.get(Integer.valueOf(i));
    }

    public TrackedNutrientDbAdapter trackedNutrientDbAdapter() {
        return this.trackedNutrientDbAdapter;
    }
}
