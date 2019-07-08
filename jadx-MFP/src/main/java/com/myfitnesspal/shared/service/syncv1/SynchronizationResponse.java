package com.myfitnesspal.shared.service.syncv1;

import android.content.ContentValues;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.DeletedMostUsedFoodsTable;
import com.myfitnesspal.shared.db.table.DiaryNotesTable;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.ExercisesTable;
import com.myfitnesspal.shared.db.table.FoodEntriesTable;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.db.table.FoodPermissionsTable;
import com.myfitnesspal.shared.db.table.FoodsTable;
import com.myfitnesspal.shared.db.table.MealIngredientsTable;
import com.myfitnesspal.shared.db.table.MeasurementsTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.myfitnesspal.shared.db.table.ProfileImagesTable;
import com.myfitnesspal.shared.db.table.RecipeBoxItemsTable;
import com.myfitnesspal.shared.db.table.RecipeIngredientsTable;
import com.myfitnesspal.shared.db.table.RecipesTable;
import com.myfitnesspal.shared.db.table.RemindersTable;
import com.myfitnesspal.shared.db.table.WaterEntriesTable;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v1.Measurement;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v1.RecipeIngredient;
import com.myfitnesspal.shared.model.v1.TrackedNutrient;
import com.myfitnesspal.shared.model.v1.UserImage;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v1.WaterEntry;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.model.v15.StepsEntryObject;
import com.myfitnesspal.shared.model.v15.SyncPointer;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.UserV1Utils;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class SynchronizationResponse extends BinaryResponse {
    private static final boolean VERBOSE_LOGGING = false;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    int currentPacketType = 0;
    private UserV1 currentUser;
    ArrayList<DatabaseObject> databaseObjects;
    @Inject
    DbConnectionManager dbConnectionManager;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<ExerciseService> exerciseService;
    @Inject
    Lazy<ExternalNutritionService> externalNutritionService;
    @Inject
    Lazy<FoodNotesTable> foodNotesTable;
    @Inject
    Lazy<FoodPermissionsService> foodPermissionsService;
    @Inject
    Lazy<FoodService> foodService;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    private int lastProcessedType = -1;
    @Inject
    Lazy<LoginModel> loginModel;
    @Inject
    Lazy<MeasurementsService> measurementsService;
    Boolean moreDataToSync;
    private Date now = new Date();
    String optionalExtraMessage;
    int previousPacketType = 0;
    @Inject
    Lazy<RecipesTable> recipesTable;
    @Inject
    Lazy<RemindersService> remindersService;
    public byte[] response;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<StepService> stepsService;
    private LegacySyncManager syncManager;
    int totalItemsInTransaction = 0;
    int totalItemsProcessed = 0;
    int uncommittedObjectsInTransaction;
    @Inject
    Lazy<UserImageService> userImageService;

    public SynchronizationResponse(UserV1 userV1, LegacySyncManager legacySyncManager) {
        MyFitnessPalApp.getInstance().component().inject(this);
        this.syncManager = legacySyncManager;
        this.currentUser = userV1;
        this.packetsProcessed = 0;
        this.expectedPacketCount = 0;
        this.totalItemsProcessed = 0;
        this.totalItemsInTransaction = 0;
        this.lastProcessedItemType = 0;
        this.currentPacketType = 0;
        this.previousPacketType = 0;
        this.uncommittedObjectsInTransaction = 0;
        this.state = 0;
        this.statusCode = 0;
        this.moreDataToSync = Boolean.valueOf(false);
        setErrorMessage("");
        initializeDBAdapters();
    }

    private void initializeDBAdapters() {
        this.userV1DBAdapter = this.dbConnectionManager.usersDbAdapter();
        this.foodEntriesDbAdapter = this.dbConnectionManager.foodEntriesDbAdapter();
        this.foodDbAdapter = this.dbConnectionManager.foodDbAdapter();
        this.recipeIngredientsDBAdapter = this.dbConnectionManager.recipeIngredientsDBAdapter();
        this.recipePropertiesDBAdapter = this.dbConnectionManager.recipePropertiesDBAdapter();
        this.recipeBoxItemsDBAdapter = this.dbConnectionManager.recipeBoxItemsDBAdapter();
        this.diaryNoteDbAdapter = this.dbConnectionManager.diaryNoteDbAdapter();
        this.genericAdapter = this.dbConnectionManager.genericDbAdapter();
        this.measurementTypesDBAdapter = this.dbConnectionManager.measurementTypesDbAdapter();
        this.waterEntriesDBAdapter = this.dbConnectionManager.waterEntriesDbAdapter();
        this.mealIngredientsDBAdapter = this.dbConnectionManager.mealIngredientsDbAdapter();
        this.userPropertiesDBAdapter = this.dbConnectionManager.userPropertiesDbAdapter();
        this.trackedNutrientDbAdapter = this.dbConnectionManager.trackedNutrientDbAdapter();
    }

    public void processPacketOfType(int i) {
        try {
            this.databaseObjects = new ArrayList<>();
            if (this.lastProcessedType != i) {
                StringBuilder sb = new StringBuilder();
                sb.append("SYNC: processing packets of type: ");
                sb.append(i);
                Ln.d(sb.toString(), new Object[0]);
                this.lastProcessedType = i;
            }
            if (i == 29) {
                processAddImagePacket();
            } else if (i == 32) {
                processAddReminderPacket();
            } else if (i != 129) {
                switch (i) {
                    case 1:
                    case 7:
                    case 8:
                    case 14:
                    case 15:
                        break;
                    case 2:
                        processSynchronizationResultsPacket();
                        break;
                    case 3:
                        processAddFoodPacket();
                        break;
                    case 4:
                        processAddExercisePacket();
                        break;
                    case 5:
                        processAddFoodEntryPacket();
                        break;
                    case 6:
                        processAddExerciseEntryPacket();
                        break;
                    case 9:
                        processSetMeasurementTypesPacket();
                        break;
                    case 10:
                        processSetMeasurementValuePacket();
                        break;
                    case 11:
                        processSetMealIngredientsPacket();
                        break;
                    case 12:
                        processMasterIdAssignmentPacket();
                        break;
                    case 13:
                        processUserPropertyUpdatePacket();
                        break;
                    case 16:
                        processSetWaterEntryPacket();
                        break;
                    case 17:
                        processDeleteItemPacket();
                        break;
                    default:
                        switch (i) {
                            case 19:
                                throw new UacfNotImplementedException();
                            case 20:
                                processFailedItemCreationPacket();
                                break;
                            case 21:
                                processAddDeletedMostUsedFoodPacket();
                                break;
                            default:
                                switch (i) {
                                    case 23:
                                        processSetDiaryNotePacket();
                                        break;
                                    case 24:
                                        processSetRecipePropertiesPacket();
                                        break;
                                    case 25:
                                        processAddRecipeBoxItemPacket();
                                        break;
                                    default:
                                        switch (i) {
                                            case 34:
                                                processVersionCheckResponsePacket();
                                                break;
                                            case 35:
                                                processAddReminderNewPacket();
                                                break;
                                            case 36:
                                                processAddTrackedNutrientPacket();
                                                break;
                                            case 37:
                                                processAddABTest();
                                                break;
                                            case 38:
                                                processConfiguration();
                                                break;
                                            case 39:
                                                processThirdPartyAccountInformation();
                                                break;
                                            default:
                                                switch (i) {
                                                    case 42:
                                                        processAddStepsEntry();
                                                        break;
                                                    case 43:
                                                        processSetFoodPermission();
                                                        break;
                                                    case 44:
                                                        processSetFoodNotes();
                                                        break;
                                                    default:
                                                        StringBuilder sb2 = new StringBuilder();
                                                        sb2.append("PACKET: unknown packet type ");
                                                        sb2.append(i);
                                                        sb2.append(" received");
                                                        Ln.e(sb2.toString(), new Object[0]);
                                                        this.state = 3;
                                                        break;
                                                }
                                        }
                                }
                        }
                }
            } else {
                processPendingItemTalliesPacket();
            }
            this.previousPacketType = this.currentPacketType;
            this.totalItemsInTransaction++;
        } catch (Exception e) {
            Ln.e("failed to parse packet of type: %s. failure: ", Integer.valueOf(i), e.getMessage());
            Ln.e(e);
        }
    }

    private void processAddTrackedNutrientPacket() {
        TrackedNutrient trackedNutrient = new TrackedNutrient();
        trackedNutrient.setLocalId(this.decoder.decode8ByteInt());
        trackedNutrient.setMasterDatabaseId(this.decoder.decode8ByteInt());
        trackedNutrient.setUid(this.decoder.decodeString());
        trackedNutrient.setNutrientNameId(this.decoder.decode8ByteInt());
        trackedNutrient.setPosition(this.decoder.decode2ByteInt());
        this.trackedNutrientDbAdapter.insertTrackedNutrient(trackedNutrient);
    }

    private void processAddReminderPacket() {
        this.decoder.decode8ByteInt();
        this.decoder.decode8ByteInt();
        this.decoder.decode2ByteInt();
        this.decoder.decode2ByteInt();
        this.decoder.decodeString();
        this.decoder.decode4ByteInt();
    }

    private void processAddReminderNewPacket() {
        ReminderObject reminderObject = new ReminderObject();
        reminderObject.setLocalId(this.decoder.decode8ByteInt());
        reminderObject.setMasterId(this.decoder.decode8ByteInt());
        reminderObject.setUid(this.decoder.decodeString());
        reminderObject.setIntervalInDays(1);
        reminderObject.setReminderType(1);
        int decode2ByteInt = this.decoder.decode2ByteInt();
        while (true) {
            int i = decode2ByteInt - 1;
            if (decode2ByteInt > 0) {
                String decodeString = this.decoder.decodeString();
                String decodeString2 = this.decoder.decodeString();
                if (Strings.equalsIgnoreCase(decodeString, "interval_in_days")) {
                    reminderObject.setIntervalInDays(NumberUtils.tryParseInt(decodeString2));
                } else if (Strings.equalsIgnoreCase(decodeString, "meal_name")) {
                    reminderObject.setMealName(Strings.toString(decodeString2));
                    int mealIdForNameForReminders = ((Session) this.session.get()).getUser().getMealNames().mealIdForNameForReminders(decodeString2);
                    reminderObject.setMealId(mealIdForNameForReminders != -1 ? Strings.toString(Integer.valueOf(mealIdForNameForReminders)) : "");
                } else if (Strings.equalsIgnoreCase(decodeString, "wall_clock_time")) {
                    reminderObject.setWallClockTime(decodeString2);
                } else if (Strings.equalsIgnoreCase(decodeString, "reminder_type")) {
                    reminderObject.setReminderType(reminderObject.reminderTypeCodeForName(decodeString2));
                } else if (Strings.equalsIgnoreCase(decodeString, "frequency")) {
                    reminderObject.setFrequency(Strings.toString(decodeString2));
                } else if (Strings.equalsIgnoreCase(decodeString, "day_of_month")) {
                    reminderObject.setDayOfMonth(NumberUtils.tryParseInt(decodeString2));
                } else if (Strings.equalsIgnoreCase(decodeString, "day_of_week")) {
                    reminderObject.setDayOfWeek(Strings.toString(decodeString2));
                }
                decode2ByteInt = i;
            } else {
                reminderObject.setFlags(this.decoder.decode2ByteInt());
                ((RemindersService) this.remindersService.get()).insertFromSync(reminderObject);
                return;
            }
        }
    }

    private void processAddImagePacket() {
        long decode8ByteInt = this.decoder.decode8ByteInt();
        long decode8ByteInt2 = this.decoder.decode8ByteInt();
        String decodeString = this.decoder.decodeString();
        this.decoder.decode8ByteInt();
        String decodeString2 = this.decoder.decodeString();
        boolean z = true;
        if (this.decoder.decode2ByteInt() == 1) {
            UserImage userImage = new UserImage();
            userImage.setLocalId(decode8ByteInt);
            userImage.setMasterDatabaseId(decode8ByteInt2);
            userImage.setUid(decodeString);
            userImage.setCreatorUserId(this.currentUser.localId);
            userImage.setCreatorUid(decodeString2);
            this.decoder.decode8ByteInt();
            this.decoder.decode8ByteInt();
            if ((((int) this.decoder.decode4ByteInt()) & 1) == 0) {
                z = false;
            }
            userImage.setVisible(z);
            userImage.setPosition(this.decoder.decode2ByteInt());
            userImage.setWidth(this.decoder.decode2ByteInt());
            userImage.setHeight(this.decoder.decode2ByteInt());
            userImage.setFileType(this.decoder.decodeString());
            userImage.setFilename(this.decoder.decodeString());
            userImage.setThumbnailURL(this.decoder.decodeString());
            userImage.setFullImageURL(this.decoder.decodeString());
            userImage.setCreatedAt(this.decoder.decodeTimestamp());
            userImage.setUpdatedAt(this.decoder.decodeTimestamp());
            this.decoder.skipDecoderBytes((int) this.decoder.decode4ByteInt());
            ((UserImageService) this.userImageService.get()).insertOrUpdateUserImage(userImage);
            return;
        }
        this.decoder.skipDecoderBytes(26);
        this.decoder.decodeString();
        this.decoder.decodeString();
        this.decoder.decodeString();
        this.decoder.decodeString();
        this.decoder.decodeTimestamp();
        this.decoder.decodeTimestamp();
        this.decoder.skipDecoderBytes((int) this.decoder.decode4ByteInt());
    }

    private void processVersionCheckResponsePacket() {
        int decode4ByteInt = (int) this.decoder.decode4ByteInt();
        while (true) {
            int i = decode4ByteInt - 1;
            if (decode4ByteInt > 0) {
                this.decoder.decodeString();
                this.decoder.decodeString();
                decode4ByteInt = i;
            } else {
                return;
            }
        }
    }

    private void processAddRecipeBoxItemPacket() {
        this.decoder.decode4ByteInt();
        long decode4ByteInt = this.decoder.decode4ByteInt();
        String decodeString = this.decoder.decodeString();
        String decodeString2 = this.decoder.decodeString();
        long decode4ByteInt2 = this.decoder.decode4ByteInt();
        long lookupFoodLocalIdFromMasterId = this.foodDbAdapter.lookupFoodLocalIdFromMasterId(decode4ByteInt);
        if (lookupFoodLocalIdFromMasterId > 0) {
            this.foodDbAdapter.updateFoodTypeToRecipeIfNeeded(lookupFoodLocalIdFromMasterId, this.context);
            RecipeFood recipeFood = (RecipeFood) this.foodDbAdapter.fetchFoodById(lookupFoodLocalIdFromMasterId);
            if (recipeFood != null) {
                recipeFood.setUid(decodeString);
                recipeFood.setOriginalUid(decodeString2);
                this.recipeBoxItemsDBAdapter.insertOrUpdateRecipeBoxItemWithMasterId(decode4ByteInt2, recipeFood);
            }
        }
    }

    private void processSetRecipePropertiesPacket() {
        this.decoder.decode4ByteInt();
        long lookupFoodLocalIdFromMasterId = this.foodDbAdapter.lookupFoodLocalIdFromMasterId(this.decoder.decode4ByteInt());
        String decodeString = this.decoder.decodeString();
        String decodeString2 = this.decoder.decodeString();
        int decode4ByteInt = (int) this.decoder.decode4ByteInt();
        int decode4ByteInt2 = (int) this.decoder.decode4ByteInt();
        ArrayList arrayList = new ArrayList(decode4ByteInt);
        while (true) {
            int i = decode4ByteInt - 1;
            if (decode4ByteInt <= 0) {
                break;
            }
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setMasterDatabaseId(this.decoder.decode4ByteInt());
            recipeIngredient.setUid(this.decoder.decodeString());
            recipeIngredient.setRecipeFoodId(lookupFoodLocalIdFromMasterId);
            long lookupFoodLocalIdFromMasterId2 = this.foodDbAdapter.lookupFoodLocalIdFromMasterId(this.decoder.decode4ByteInt());
            recipeIngredient.setIngredientFoodUid(this.decoder.decodeString());
            recipeIngredient.setIngredientFoodOriginalUid(this.decoder.decodeString());
            int decode4ByteInt3 = (int) this.decoder.decode4ByteInt();
            recipeIngredient.setQuantity(this.decoder.decodeFloat());
            recipeIngredient.setIsFraction(this.decoder.decode2ByteInt() > 0);
            recipeIngredient.setWeightIndex(decode4ByteInt3);
            recipeIngredient.setIngredientFoodId(lookupFoodLocalIdFromMasterId2);
            arrayList.add(recipeIngredient);
            decode4ByteInt = i;
        }
        this.recipeIngredientsDBAdapter.eraseRecipeIngredientsForRecipeFoodId(lookupFoodLocalIdFromMasterId);
        this.recipeIngredientsDBAdapter.insertRecipeIngredients(arrayList);
        HashMap hashMap = new HashMap(decode4ByteInt2);
        while (true) {
            int i2 = decode4ByteInt2 - 1;
            if (decode4ByteInt2 > 0) {
                processSingleRecipeProperty(hashMap, this.decoder.decodeString(), this.decoder.decodeString());
                decode4ByteInt2 = i2;
            } else {
                processSingleRecipeProperty(hashMap, Extras.RECIPE_UID, decodeString2);
                processSingleRecipeProperty(hashMap, Extras.RECIPE_ORIGINAL_UID, decodeString);
                this.recipePropertiesDBAdapter.eraseRecipePropertiesForRecipeFoodId(lookupFoodLocalIdFromMasterId);
                this.recipePropertiesDBAdapter.insertRecipeProperties(lookupFoodLocalIdFromMasterId, hashMap);
                return;
            }
        }
    }

    private void processSingleRecipeProperty(Map<String, List<String>> map, String str, String str2) {
        List list = (List) map.get(str);
        if (list == null) {
            list = new ArrayList();
            map.put(str, list);
        }
        list.add(str2);
    }

    private void processSetDiaryNotePacket() {
        DiaryNote diaryNote = new DiaryNote();
        diaryNote.setMasterDatabaseId(this.decoder.decode8ByteInt());
        diaryNote.setUid(this.decoder.decodeString());
        diaryNote.setEntryDate(this.decoder.decodeDate());
        diaryNote.setNoteType(this.decoder.decode2ByteInt());
        diaryNote.setBody(this.decoder.decodeString());
        this.diaryNoteDbAdapter.insertOrUpdateDiaryNote(diaryNote);
    }

    private void processAddDeletedMostUsedFoodPacket() {
        long decode8ByteInt = this.decoder.decode8ByteInt();
        String decodeString = this.decoder.decodeString();
        long decode4ByteInt = this.decoder.decode4ByteInt();
        this.decoder.decodeString();
        ((FoodService) this.foodService.get()).hideFood(decode8ByteInt, decodeString, 0, 0, decode4ByteInt, this.decoder.decodeString(), (long) this.currentUser.getMealNames().mealIdForName(this.decoder.decodeString()));
    }

    private void processFailedItemCreationPacket() {
        int decode2ByteInt = this.decoder.decode2ByteInt();
        long decode4ByteInt = this.decoder.decode4ByteInt();
        int decode2ByteInt2 = this.decoder.decode2ByteInt();
        String decodeString = this.decoder.decodeString();
        StringBuilder sb = new StringBuilder();
        sb.append("Failed item creation; type=");
        sb.append(decode2ByteInt);
        sb.append(", id=");
        sb.append((int) decode4ByteInt);
        sb.append(", reasonCode=");
        sb.append(decode2ByteInt2);
        sb.append(", reason=");
        sb.append(decodeString);
        Ln.i(sb.toString(), new Object[0]);
        long j = 0;
        switch (decode2ByteInt) {
            case 1:
                this.genericAdapter.completelyEraseFoodId(decode4ByteInt);
                return;
            case 2:
                ((ExerciseService) this.exerciseService.get()).markExerciseAsDeletedLocally(decode4ByteInt);
                ((DiaryService) this.diaryService.get()).eraseAllEntriesForExerciseId(decode4ByteInt);
                return;
            case 4:
                this.foodEntry = this.foodEntriesDbAdapter.fetchFoodEntryById(decode4ByteInt);
                if (this.foodEntry != null) {
                    Food food = this.foodEntry.getFood();
                    boolean z = food != null;
                    AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
                    String str = Events.FOOD_ENTRY_FAILED_CREATION;
                    String[] strArr = new String[16];
                    strArr[0] = "food_id";
                    strArr[1] = Strings.toString(Long.valueOf(z ? food.getLocalId() : 0));
                    strArr[2] = "food_master_id";
                    if (z) {
                        j = food.getMasterDatabaseId();
                    }
                    strArr[3] = Strings.toString(Long.valueOf(j));
                    strArr[4] = "food_description";
                    strArr[5] = z ? Strings.toString(food.getDescription()) : "";
                    strArr[6] = Attributes.FOOD_ENTRY_ID;
                    strArr[7] = Strings.toString(Long.valueOf(this.foodEntry.localId));
                    strArr[8] = Attributes.FOOD_ENTRY_MASTER_ID;
                    strArr[9] = Strings.toString(Long.valueOf(this.foodEntry.masterDatabaseId));
                    strArr[10] = Attributes.REASON_MESSAGE;
                    strArr[11] = Strings.toString(decodeString);
                    strArr[12] = Attributes.REASON_CODE;
                    strArr[13] = Strings.toString(Integer.valueOf(decode2ByteInt2));
                    strArr[14] = "user_id";
                    strArr[15] = Strings.toString(((Session) this.session.get()).getUser().getUserId());
                    analyticsService2.reportEvent(str, MapUtil.createMap(strArr));
                    if (decode2ByteInt2 != 256 || !this.foodEntry.hasMasterDatabaseId()) {
                        this.foodEntriesDbAdapter.deleteFoodEntry(this.foodEntry, this.externalNutritionService);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                this.exerciseEntry = ((DiaryService) this.diaryService.get()).getV1ExerciseEntry(decode4ByteInt);
                if (this.exerciseEntry != null) {
                    ((DiaryService) this.diaryService.get()).deleteExerciseEntryLocally(this.exerciseEntry);
                    return;
                }
                return;
            case 10:
                this.diaryNoteDbAdapter.eraseDiaryNoteWithLocalId(decode4ByteInt);
                return;
            case 12:
                this.recipeBoxItemsDBAdapter.deleteRecipeBoxItemWithLocalId(decode4ByteInt);
                return;
            case 26:
                FoodPermission fromLocalId = ((FoodPermissionsService) this.foodPermissionsService.get()).getFromLocalId(decode4ByteInt);
                if (fromLocalId != null) {
                    Food fetchFoodById = this.foodDbAdapter.fetchFoodById(fromLocalId.getFoodLocalId());
                    if (fetchFoodById == null || !(fetchFoodById == null || fetchFoodById.getMasterDatabaseId() == 0)) {
                        ((FoodPermissionsService) this.foodPermissionsService.get()).deletePermission(decode4ByteInt, ((Session) this.session.get()).getUser().getLocalId());
                        return;
                    }
                    return;
                }
                return;
            case 27:
                ((FoodNotesTable) this.foodNotesTable.get()).deleteByLocalId(decode4ByteInt);
                return;
            default:
                return;
        }
    }

    private void processMasterIdAssignmentPacket() {
        int decode2ByteInt = this.decoder.decode2ByteInt();
        long decode8ByteInt = this.decoder.decode8ByteInt();
        updateMasterDatabaseId(this.decoder.decode8ByteInt(), decode2ByteInt, decode8ByteInt, this.decoder.decodeString(), this.decoder.decodeString());
        notifyExternalSyncWithInsert(decode2ByteInt, decode8ByteInt);
    }

    private void processDeleteItemPacket() {
        int decode2ByteInt = this.decoder.decode2ByteInt();
        long decode8ByteInt = this.decoder.decode8ByteInt();
        this.decoder.decodeString();
        boolean z = this.decoder.decode2ByteInt() == 2;
        switch (decode2ByteInt) {
            case 1:
                long lookupFoodLocalIdFromMasterId = this.foodDbAdapter.lookupFoodLocalIdFromMasterId(decode8ByteInt);
                if (lookupFoodLocalIdFromMasterId > 0) {
                    this.foodDbAdapter.markFoodLocalIdAsDeleted(lookupFoodLocalIdFromMasterId);
                    break;
                }
                break;
            case 2:
                this.exercise = ((ExerciseService) this.exerciseService.get()).getV1ExerciseLocallyFromMasterId(decode8ByteInt);
                if (this.exercise != null && !this.exercise.isDeleted()) {
                    ((ExerciseService) this.exerciseService.get()).deleteExerciseLocally(this.exercise, z);
                    break;
                }
            case 4:
                this.genericAdapter.eraseItemOfType(decode2ByteInt, decode8ByteInt, this.currentUser.getLocalId());
                break;
            case 5:
                this.genericAdapter.eraseItemOfType(decode2ByteInt, decode8ByteInt, this.currentUser.getLocalId());
                break;
            case 8:
                ((MeasurementsService) this.measurementsService.get()).localDeleteByMasterId(decode8ByteInt);
                break;
            case 12:
                long recipeFoodIdForMasterId = this.recipeBoxItemsDBAdapter.getRecipeFoodIdForMasterId(decode8ByteInt);
                this.recipeBoxItemsDBAdapter.deleteRecipeBoxItemWithMasterId(decode8ByteInt);
                ((RecipesTable) this.recipesTable.get()).deleteRecipeForFoodId(recipeFoodIdForMasterId);
                break;
            case 15:
                long imageLocalIdForMasterId = ((UserImageService) this.userImageService.get()).getImageLocalIdForMasterId(decode8ByteInt, 1);
                if (imageLocalIdForMasterId != 0) {
                    ((UserImageService) this.userImageService.get()).deleteImageWithLocalId(imageLocalIdForMasterId);
                    break;
                }
                break;
            case 20:
                ((RemindersService) this.remindersService.get()).deleteReminderWithMasterId(decode8ByteInt);
                break;
            case 21:
                this.trackedNutrientDbAdapter.eraseTrackedNutrientWithMasterId(decode8ByteInt);
                break;
            case 22:
                Ln.d("NEWFACEBOOK: delete, master ID = %s, stored = %s", Long.valueOf(decode8ByteInt), Long.valueOf(this.syncSettings.getMasterIdOfMostRecentThirdPartyAccountAddOrDelete()));
                if (decode8ByteInt > this.syncSettings.getMasterIdOfMostRecentThirdPartyAccountAddOrDelete()) {
                    Ln.d("NEWFACEBOOK: delete info!", new Object[0]);
                    ((LoginModel) this.loginModel.get()).clearFacebookData();
                    this.syncSettings.setMasterIdOfMostRecentThirdPartyAccountAddOrDelete(decode8ByteInt);
                    break;
                }
                break;
        }
        this.totalItemsProcessed++;
    }

    private void processSetMeasurementValuePacket() {
        Measurement measurement = new Measurement();
        measurement.setMasterDatabaseId(this.decoder.decode8ByteInt());
        measurement.setUid(this.decoder.decodeString());
        measurement.setMeasurementTypeName(this.decoder.decodeString());
        measurement.setSourceClientId(this.decoder.decodeString());
        Calendar instance = Calendar.getInstance();
        instance.setTime(this.decoder.decodeDate());
        measurement.setEntryDate(instance);
        measurement.setValue(Float.valueOf(this.decoder.decodeFloat()));
        nowProcessingItemType(8);
        ((MeasurementsService) this.measurementsService.get()).insertOrUpdateMeasurement(measurement);
        this.totalItemsProcessed++;
    }

    private void processSetWaterEntryPacket() {
        nowProcessingItemType(7);
        WaterEntry waterEntry = new WaterEntry();
        waterEntry.setMasterDatabaseId(this.decoder.decode8ByteInt());
        waterEntry.setUid(this.decoder.decodeString());
        waterEntry.setEntryDate(this.decoder.decodeDate());
        waterEntry.setMilliliters(this.decoder.decodeFloat());
        this.waterEntriesDBAdapter.insertOrUpdateWaterEntry(waterEntry);
        this.totalItemsProcessed++;
    }

    private void processAddExerciseEntryPacket() {
        nowProcessingItemType(5);
        decodeNextExerciseEntry();
        ((DiaryService) this.diaryService.get()).insertOrUpdateV1ExerciseEntryLocally(this.decodedExerciseEntry);
        this.totalItemsProcessed++;
    }

    private void processSetMealIngredientsPacket() {
        nowProcessingItemType(9);
        this.decoder.decode4ByteInt();
        long decode4ByteInt = this.decoder.decode4ByteInt();
        this.decoder.decodeString();
        this.decoder.decodeString();
        int decode4ByteInt2 = (int) this.decoder.decode4ByteInt();
        long lookupFoodLocalIdFromMasterId = this.foodDbAdapter.lookupFoodLocalIdFromMasterId(decode4ByteInt);
        ArrayList<MealIngredient> arrayList = new ArrayList<>(decode4ByteInt2);
        while (true) {
            int i = decode4ByteInt2 - 1;
            boolean z = true;
            if (decode4ByteInt2 <= 0) {
                break;
            }
            MealIngredient mealIngredient = new MealIngredient();
            mealIngredient.setMasterDatabaseId(this.decoder.decode4ByteInt());
            mealIngredient.setUid(this.decoder.decodeString());
            this.decoder.decode4ByteInt();
            mealIngredient.setCreatorUserId(this.currentUser.getLocalId());
            mealIngredient.setMealFoodId(lookupFoodLocalIdFromMasterId);
            long decode4ByteInt3 = this.decoder.decode4ByteInt();
            this.decoder.decodeString();
            this.decoder.decodeString();
            long lookupFoodLocalIdFromMasterId2 = this.foodDbAdapter.lookupFoodLocalIdFromMasterId(decode4ByteInt3);
            int decode4ByteInt4 = (int) this.decoder.decode4ByteInt();
            mealIngredient.setQuantity(this.decoder.decodeFloat());
            if (this.decoder.decode2ByteInt() <= 0) {
                z = false;
            }
            mealIngredient.setIsFraction(z);
            mealIngredient.setWeightIndex(decode4ByteInt4);
            mealIngredient.setIngredientFoodId(lookupFoodLocalIdFromMasterId2);
            arrayList.add(mealIngredient);
            decode4ByteInt2 = i;
        }
        for (MealIngredient mealIngredient2 : arrayList) {
            if (lookupFoodLocalIdFromMasterId > 0 && mealIngredient2.getIngredientFoodId() > 0) {
                this.mealIngredientsDBAdapter.insertMealIngredient(mealIngredient2);
            }
        }
        this.totalItemsProcessed++;
    }

    private void processAddFoodPacket() {
        nowProcessingItemType(1);
        decodeNextFood();
        this.foodDbAdapter.insertFoodIfMissing(this.decodedFood, this.dbConnectionManager);
        this.totalItemsProcessed++;
    }

    private void processAddFoodEntryPacket() {
        nowProcessingItemType(4);
        decodeNextFoodEntry();
        this.foodEntriesDbAdapter.insertFoodEntry(this.decodedFoodEntry, this.dbConnectionManager);
        notifyExternalSyncWithInsert(4, this.decodedFoodEntry.localId);
        this.totalItemsProcessed++;
    }

    private void processAddExercisePacket() {
        nowProcessingItemType(2);
        decodeNextExercise();
        ((ExerciseService) this.exerciseService.get()).insertOrUpdateV1ExerciseLocally(this.decodedExercise);
        this.totalItemsProcessed++;
    }

    public void nowProcessingItemType(int i) {
        if (this.lastProcessedItemType != i) {
            this.lastProcessedItemType = i;
            this.syncManager.postProgress();
        }
    }

    private void processSetMeasurementTypesPacket() {
        ArrayList arrayList = new ArrayList(5);
        ArrayList arrayList2 = new ArrayList(5);
        ArrayList arrayList3 = new ArrayList(5);
        int decode2ByteInt = this.decoder.decode2ByteInt();
        while (true) {
            int i = decode2ByteInt - 1;
            if (decode2ByteInt > 0) {
                arrayList.add(Long.valueOf(this.decoder.decode4ByteInt()));
                arrayList2.add(this.decoder.decodeString());
                arrayList3.add(this.decoder.decodeString());
                decode2ByteInt = i;
            } else {
                this.measurementTypesDBAdapter.updateMeasurementTypeDescriptions((String[]) arrayList3.toArray(new String[arrayList3.size()]), (Long[]) arrayList.toArray(new Long[arrayList.size()]), arrayList2, this.currentUser.getLocalId());
                this.measurementTypesDBAdapter.loadMeasurementTypesForUser(this.currentUser);
                return;
            }
        }
    }

    private void processUserPropertyUpdatePacket() {
        int decode2ByteInt = this.decoder.decode2ByteInt();
        while (true) {
            int i = decode2ByteInt - 1;
            if (decode2ByteInt > 0) {
                this.currentUser.setProperty(this.decoder.decodeString(), this.decoder.decodeString());
                decode2ByteInt = i;
            } else {
                this.userPropertiesDBAdapter.saveUserProperties(this.currentUser, (LoginModel) this.loginModel.get());
                this.userPropertiesDBAdapter.updateUserPropertiesLastSyncAtTimestamps(this.currentUser.getLocalId());
                this.totalItemsProcessed++;
                return;
            }
        }
    }

    private void processAddABTest() {
        this.decoder.decodeString();
        this.decoder.decodeString();
        this.decoder.decode2ByteInt();
        int decode2ByteInt = this.decoder.decode2ByteInt();
        for (int i = 0; i < decode2ByteInt; i++) {
            this.decoder.decodeString();
            this.decoder.decodeString();
        }
    }

    private void processConfiguration() {
        int decode2ByteInt = this.decoder.decode2ByteInt();
        for (int i = 0; i < decode2ByteInt; i++) {
            this.decoder.decodeString();
            this.decoder.decodeString();
        }
    }

    private void processThirdPartyAccountInformation() {
        long decode8ByteInt = this.decoder.decode8ByteInt();
        this.decoder.decodeString();
        this.decoder.decode2ByteInt();
        String decodeString = this.decoder.decodeString();
        this.decoder.decodeBoolean();
        if (decode8ByteInt > this.syncSettings.getMasterIdOfMostRecentThirdPartyAccountAddOrDelete()) {
            ((LoginModel) this.loginModel.get()).setFacebookUserId(decodeString);
            this.userV1DBAdapter.updateUsersRowForUser(this.currentUser);
            this.syncSettings.setMasterIdOfMostRecentThirdPartyAccountAddOrDelete(decode8ByteInt);
        }
    }

    private void processAddStepsEntry() {
        StepsEntryObject stepsEntryObject = new StepsEntryObject();
        stepsEntryObject.readData(this.decoder);
        ((StepService) this.stepsService.get()).save(stepsEntryObject);
    }

    private void processSetFoodNotes() {
        FoodNotes foodNotes = new FoodNotes();
        foodNotes.setUserId(this.currentUser.getLocalId());
        foodNotes.readData(this.decoder);
        ((FoodNotesTable) this.foodNotesTable.get()).replace(foodNotes);
    }

    private void processSetFoodPermission() {
        FoodPermission foodPermission = new FoodPermission();
        foodPermission.setUserId(this.currentUser.getLocalId());
        foodPermission.readData(this.decoder);
        ((FoodPermissionsService) this.foodPermissionsService.get()).updatePermissionFromServer(foodPermission);
    }

    private void processSynchronizationResultsPacket() {
        Ln.d("processSynchronizationResults", new Object[0]);
        this.statusCode = this.decoder.decode2ByteInt();
        setErrorMessage(this.decoder.decodeString());
        setOptionalExtraMessage(this.decoder.decodeString());
        long decode4ByteInt = this.decoder.decode4ByteInt();
        String decodeString = this.decoder.decodeString();
        int decode2ByteInt = this.decoder.decode2ByteInt();
        int decode2ByteInt2 = this.decoder.decode2ByteInt();
        setExpectedPacketCount((int) this.decoder.decode4ByteInt());
        if (this.statusCode != 0 || !this.currentUser.hasMasterDatabaseId() || this.currentUser.masterDatabaseId == decode4ByteInt || decode4ByteInt == 0) {
            ArrayList arrayList = new ArrayList();
            while (true) {
                int i = decode2ByteInt2 - 1;
                if (decode2ByteInt2 <= 0) {
                    break;
                }
                SyncPointer syncPointer = new SyncPointer();
                syncPointer.readData(this.decoder);
                arrayList.add(syncPointer);
                decode2ByteInt2 = i;
            }
            if (this.statusCode == 0) {
                Ln.d("status OK, mode = %s", this.syncManager.getMode());
                switch (this.syncManager.getMode()) {
                    case Import:
                        Ln.d("sync mode import", new Object[0]);
                        createImportedUserWithMasterId(decode4ByteInt, decodeString);
                        break;
                    case Registration:
                        Ln.d("sync mode registration", new Object[0]);
                        if (Strings.notEmpty(decodeString)) {
                            this.currentUser.setUsername(decodeString);
                        }
                        this.currentUser.setMasterDatabaseId(decode4ByteInt);
                        this.userV1DBAdapter.updateUsersRowForUser(this.currentUser);
                        this.genericAdapter.updateOwnerUserMasterIdsForNewUserMasterId(this.currentUser.getMasterDatabaseId(), this.currentUser.getLocalId());
                        break;
                }
                this.syncManager.setLastSyncPointers(arrayList);
                this.moreDataToSync = Boolean.valueOf((decode2ByteInt & 1) > 0);
                if ((decode2ByteInt & 2) > 0) {
                    String[] split = this.optionalExtraMessage.split("\\|");
                    if (split.length == 5) {
                        HashMap hashMap = new HashMap(5);
                        hashMap.put(GooglePlayConstants.BILLING_JSON_FIELD_TOKEN, split[0]);
                        hashMap.put("title", split[1]);
                        hashMap.put("body", split[2]);
                        hashMap.put("url", split[3]);
                        hashMap.put("style", split[4]);
                        ((GlobalSettingsService) this.globalSettingsService.get()).setUpgradeDetails(hashMap);
                    }
                }
            } else if (this.statusCode == 2) {
                Ln.d("authentication failed", new Object[0]);
            }
            return;
        }
        UserV1 userV1 = this.currentUser;
        StringBuilder sb = new StringBuilder();
        sb.append(this.currentUser.getUsername());
        sb.append(" (deleted)");
        userV1.setUsername(sb.toString());
        this.userV1DBAdapter.updateUsersRowForUser(this.currentUser);
        this.statusCode = 5;
        this.state = 3;
    }

    private void createImportedUserWithMasterId(long j, String str) {
        this.currentUser = UserV1Utils.loadUserOnCurrentThread(str, this.dbConnectionManager);
        if (this.currentUser == null) {
            this.currentUser = new UserV1();
        }
        this.currentUser.setMasterDatabaseId(j);
        this.currentUser.setUsername(str);
        this.userV1DBAdapter.saveUser(this.currentUser);
        this.userV1DBAdapter.updateFoodOwnerUserIdsForNewUserLocalId(this.currentUser.getLocalId(), this.currentUser.getMasterDatabaseId());
        ((ExerciseService) this.exerciseService.get()).updateOwnedExerciseOwnerLocalIdFromOwnerMasterId();
        ((Session) this.session.get()).getUser().setUserV1(this.currentUser);
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
        if (str.length() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Response Error message: ");
            sb.append(str);
            Ln.v(sb.toString(), new Object[0]);
        }
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setOptionalExtraMessage(String str) {
        this.optionalExtraMessage = str;
    }

    public int getState() {
        return this.state;
    }

    public void setExpectedPacketCount(int i) {
        this.expectedPacketCount = i;
    }

    public void setState(int i) {
        this.state = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public Boolean moreDataToSync() {
        return this.moreDataToSync;
    }

    public int getLastProcessedItemType() {
        return this.lastProcessedItemType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        if (r2 != null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        if (r2 == null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isDeleted(long r5, com.myfitnesspal.shared.db.table.MfpDatabaseTable r7) {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            r2 = 0
            java.lang.String[] r3 = new java.lang.String[r0]     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            r3[r1] = r5     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            java.lang.String r5 = "sync_flags"
            java.lang.String[] r5 = new java.lang.String[]{r5}     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            java.lang.String r6 = "id=?"
            android.database.Cursor r2 = r7.queryData(r5, r6, r3)     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            int r5 = r2.getCount()     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            if (r5 <= 0) goto L_0x0030
            boolean r5 = r2.moveToFirst()     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            if (r5 == 0) goto L_0x0030
            int r5 = r2.getInt(r1)     // Catch:{ Exception -> 0x003a, all -> 0x0033 }
            r6 = 3
            if (r5 != r6) goto L_0x0030
            if (r2 == 0) goto L_0x002f
            r2.close()
        L_0x002f:
            return r0
        L_0x0030:
            if (r2 == 0) goto L_0x0040
            goto L_0x003d
        L_0x0033:
            r5 = move-exception
            if (r2 == 0) goto L_0x0039
            r2.close()
        L_0x0039:
            throw r5
        L_0x003a:
            if (r2 == 0) goto L_0x0040
        L_0x003d:
            r2.close()
        L_0x0040:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.syncv1.SynchronizationResponse.isDeleted(long, com.myfitnesspal.shared.db.table.MfpDatabaseTable):boolean");
    }

    private void notifyExternalSyncWithInsert(int i, long j) {
        if (((Session) this.session.get()).getUser().isLoggedIn() && ((ExternalNutritionService) this.externalNutritionService.get()).enabled() && i == 4 && j >= 0) {
            FoodEntry fetchFoodEntryById = this.foodEntriesDbAdapter.fetchFoodEntryById(j);
            if (fetchFoodEntryById != null && Strings.notEmpty(fetchFoodEntryById.getUid()) && Math.abs(DateTimeUtils.getNumberOfDaysBetween(this.now, fetchFoodEntryById.getDate())) <= 2) {
                ((ExternalNutritionService) this.externalNutritionService.get()).onFoodEntryInserted(fetchFoodEntryById, ((Session) this.session.get()).getUser().getUserId());
            }
        }
    }

    private void updateMasterDatabaseId(long j, int i, long j2, String str, String str2) {
        MfpDatabaseTable mfpDatabaseTable;
        boolean z;
        boolean z2;
        int i2 = i;
        long j3 = j2;
        SQLiteDatabaseWrapper db = DbConnectionManager.getDb(this.context);
        switch (i2) {
            case 1:
            case 3:
                mfpDatabaseTable = new FoodsTable(db);
                z2 = true;
                z = false;
                break;
            case 2:
                mfpDatabaseTable = new ExercisesTable(db);
                z2 = true;
                z = true;
                break;
            case 4:
                mfpDatabaseTable = new FoodEntriesTable(db);
                z2 = false;
                z = true;
                break;
            case 5:
                mfpDatabaseTable = new ExerciseEntriesTable(db);
                z2 = false;
                z = true;
                break;
            case 7:
                mfpDatabaseTable = new WaterEntriesTable(db);
                z2 = false;
                z = true;
                break;
            case 8:
                mfpDatabaseTable = new MeasurementsTable(db);
                z2 = false;
                z = true;
                break;
            case 9:
                mfpDatabaseTable = new MealIngredientsTable(db);
                z2 = false;
                z = false;
                break;
            case 10:
                mfpDatabaseTable = new DiaryNotesTable(db);
                z2 = false;
                z = false;
                break;
            case 12:
                mfpDatabaseTable = new RecipeBoxItemsTable(db);
                z2 = false;
                z = true;
                break;
            case 13:
                mfpDatabaseTable = new RecipeIngredientsTable(db);
                z2 = false;
                z = false;
                break;
            case 15:
                mfpDatabaseTable = new ProfileImagesTable(db);
                z2 = false;
                z = false;
                break;
            case 20:
                mfpDatabaseTable = new RemindersTable(db);
                z2 = false;
                z = false;
                break;
            case 25:
                mfpDatabaseTable = new DeletedMostUsedFoodsTable(db);
                z2 = true;
                z = false;
                break;
            case 26:
                mfpDatabaseTable = new FoodPermissionsTable(db);
                z2 = false;
                z = false;
                break;
            case 27:
                mfpDatabaseTable = (MfpDatabaseTable) this.foodNotesTable.get();
                z2 = false;
                z = false;
                break;
            default:
                return;
        }
        if (j <= 0 || !isDeleted(j3, mfpDatabaseTable)) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("master_id", Long.valueOf(j));
            String str3 = null;
            if (i2 != 26) {
                contentValues.put("uid", Strings.notEmpty(str) ? str : null);
            }
            if (z2) {
                String str4 = "original_uid";
                if (Strings.notEmpty(str2)) {
                    str3 = str2;
                }
                contentValues.put(str4, str3);
            }
            if (z) {
                contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(0));
            }
            mfpDatabaseTable.updateData(contentValues, "id = ?", Long.valueOf(j2));
            if (i2 == 1 || i2 == 3) {
                Food fetchFoodById = this.dbConnectionManager.foodDbAdapter().fetchFoodById(j3);
                if (fetchFoodById != null) {
                    fetchFoodById.updateOriginalFoodIdsIfNeeded(this.dbConnectionManager);
                }
            }
        } else {
            this.dbConnectionManager.deletedItemsDbAdapter().recordDeletedItemForUserId(((Session) this.session.get()).getUser().getLocalId(), i, j, false);
            mfpDatabaseTable.deleteData("id=?", String.valueOf(j2));
        }
    }
}
