package com.myfitnesspal.shared.service.syncv1;

import android.content.Context;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.constants.SyncConstants;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.model.v1.DatabaseObjectReference;
import com.myfitnesspal.shared.model.v1.DiaryNote;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v1.Measurement;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v1.RecipeIngredient;
import com.myfitnesspal.shared.model.v1.TrackedNutrient;
import com.myfitnesspal.shared.model.v1.UserImage;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v1.WaterEntry;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.model.v15.SyncPointer;
import com.myfitnesspal.shared.util.ApiUtil;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple4;
import com.uacf.core.util.VersionUtils;
import dagger.Lazy;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Named;

public class SynchronizationRequest extends PacketType {
    public static final int INCREMENTAL_SYNC_TYPE_BACKGROUND = 0;
    public static final int INCREMENTAL_SYNC_TYPE_FOREGROUND = 1;
    private static final boolean VERBOSE_LOGGING = false;
    @Inject
    ApiDeviceTokenProvider apiDeviceTokenProvider;
    private int apiVersion;
    @Inject
    Lazy<AppSettings> appSettings;
    private Context context;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    @Inject
    BinaryEncoder encoder;
    @Inject
    Lazy<LoginModel> loginModel;
    private int packetCount = 0;
    private long packetStartPosition;
    private long requestLengthInBytes;
    @Inject
    Lazy<SignUpModel> signUpModel;
    private LegacySyncManager syncManager;
    @Inject
    Lazy<SyncPointerService> syncPointerService;
    @Inject
    @Named("deviceUUIDBytes")
    byte[] uuidBytes;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SyncFlag {
    }

    public SynchronizationRequest(Context context2, LegacySyncManager legacySyncManager) {
        this.context = context2;
        this.syncManager = legacySyncManager;
        MyFitnessPalApp.getInstance().component().inject(this);
        this.apiVersion = ApiUtil.getBinaryApiVersion((AppSettings) this.appSettings.get());
    }

    public BinaryEncoder getEncoder() {
        return this.encoder;
    }

    public int getPacketCount() {
        return this.packetCount;
    }

    public long getRequestLengthInBytes() {
        return this.requestLengthInBytes;
    }

    public void finishPacket() {
        long currentPosition = (long) this.encoder.currentPosition();
        long j = this.packetStartPosition;
        this.encoder.patchAtPosition((int) (j + 2), (int) (currentPosition - j));
        this.packetCount++;
        this.requestLengthInBytes = (long) this.encoder.getBuffer().capacity();
    }

    public void addSynchronizationRequestPacketForUser(int i) {
        beginPacketOfType(1);
        this.encoder.write2ByteInt(this.apiVersion);
        this.encoder.write4ByteInt((long) VersionUtils.getAppVersionCode(this.context));
        this.encoder.write2ByteInt(2);
        this.encoder.write2ByteInt(this.syncManager.syncRequestFlags());
        this.encoder.writeUUID(this.uuidBytes);
        writeCurrentDeviceToken();
        writeLastSyncPointersAndExclusions();
        this.encoder.write2ByteInt(i);
        finishPacket();
    }

    private void writeLastSyncPointersAndExclusions() {
        List<SyncPointer> lastSyncPointers = ((SyncPointerService) this.syncPointerService.get()).getLastSyncPointers();
        this.encoder.write2ByteInt(lastSyncPointers.size());
        for (SyncPointer syncPointer : lastSyncPointers) {
            this.encoder.writeString(syncPointer.getKey());
            this.encoder.writeString(syncPointer.getLastSyncTime());
            this.encoder.write8ByteInt(syncPointer.getCutoffId());
        }
    }

    private void writeCurrentDeviceToken() {
        this.encoder.writeString(this.apiDeviceTokenProvider.get());
    }

    public void beginPacketOfType(int i) {
        this.packetStartPosition = (long) this.encoder.currentPosition();
        Ln.d("beginPacketOfType: current pos = %s, type = %s", Long.valueOf(this.packetStartPosition), Integer.valueOf(i));
        this.encoder.write2ByteInt(SyncConstants.MAGIC_NUMBER);
        this.encoder.write4ByteInt(0);
        this.encoder.write2ByteInt(1);
        this.encoder.write2ByteInt(i);
    }

    public void addUserPropertyUpdatePacket(Map<String, String> map) {
        beginPacketOfType(13);
        this.encoder.write2ByteInt(map.size());
        for (String str : map.keySet()) {
            this.encoder.writeString(str);
            this.encoder.writeString((String) map.get(str));
        }
        finishPacket();
    }

    public void addSetMeasurementTypesPacket(String[] strArr) {
        beginPacketOfType(9);
        this.encoder.write2ByteInt(strArr.length);
        for (String str : strArr) {
            this.encoder.write4ByteInt(0);
            this.encoder.writeString(null);
            this.encoder.writeString(str);
        }
        finishPacket();
    }

    public void addSynchronizationRequestPacketForUserRegistration() {
        beginPacketOfType(1);
        this.encoder.write2ByteInt(this.apiVersion);
        this.encoder.write4ByteInt((long) VersionUtils.getAppVersionCode(this.context));
        this.encoder.write2ByteInt(3);
        this.encoder.write2ByteInt(this.syncManager.syncRequestFlags());
        this.encoder.writeUUID(this.uuidBytes);
        writeCurrentDeviceToken();
        this.encoder.write2ByteInt(0);
        this.encoder.write2ByteInt(1);
        finishPacket();
    }

    public void addUserRegistrationRequestPacketForUser(UserV1 userV1) {
        beginPacketOfType(14);
        String stringOrDefaultIfEmpty = Strings.toStringOrDefaultIfEmpty(userV1.getUsername(), SyncConstants.LOCAL_USERNAME);
        String stringOrDefaultIfEmpty2 = Strings.toStringOrDefaultIfEmpty(((SignUpModel) this.signUpModel.get()).getPassword(), "123456");
        this.encoder.writeString(stringOrDefaultIfEmpty);
        this.encoder.writeString(stringOrDefaultIfEmpty2);
        FacebookData facebookData = ((LoginModel) this.loginModel.get()).getFacebookData();
        if (facebookData.isValid()) {
            this.encoder.writeString(facebookData.getEmail());
            this.encoder.write2ByteInt(1);
            this.encoder.writeString(facebookData.getUserId());
            this.encoder.writeString(facebookData.getAccessToken());
        } else {
            this.encoder.writeString(userV1.getEmail());
            this.encoder.write2ByteInt(0);
            this.encoder.writeString(null);
            this.encoder.writeString(null);
        }
        this.encoder.writeString(((SignUpModel) this.signUpModel.get()).getConsentsMatrixVersion());
        this.encoder.write2ByteInt(((SignUpModel) this.signUpModel.get()).getNumberOfConsents());
        List<String> acceptedConsentsId = ((SignUpModel) this.signUpModel.get()).getAcceptedConsentsId();
        if (CollectionUtils.notEmpty((Collection<?>) acceptedConsentsId)) {
            for (String writeString : acceptedConsentsId) {
                this.encoder.writeString(writeString);
            }
        }
        finishPacket();
    }

    public void addSynchronizationRequestPacketForImportUsername() {
        beginPacketOfType(1);
        this.encoder.write2ByteInt(this.apiVersion);
        this.encoder.write4ByteInt((long) VersionUtils.getAppVersionCode(this.context));
        this.encoder.write2ByteInt(2);
        this.encoder.write2ByteInt(this.syncManager.syncRequestFlags());
        this.encoder.writeUUID(this.uuidBytes);
        writeCurrentDeviceToken();
        writeLastSyncPointersAndExclusions();
        this.encoder.write2ByteInt(1);
        finishPacket();
    }

    public void addSetRecipePropertiesPacketForRecipeFood(RecipeFood recipeFood) {
        beginPacketOfType(24);
        this.encoder.write4ByteInt(recipeFood.localId);
        this.encoder.write4ByteInt(recipeFood.masterDatabaseId);
        this.encoder.writeString(recipeFood.getOriginalUid());
        this.encoder.writeString(recipeFood.getUid());
        this.encoder.write4ByteInt((long) CollectionUtils.size((Collection<?>) recipeFood.getIngredients()));
        this.encoder.write4ByteInt((long) recipeFood.totalPropertyCount());
        Iterator it = recipeFood.getIngredients().iterator();
        while (it.hasNext()) {
            RecipeIngredient recipeIngredient = (RecipeIngredient) it.next();
            this.encoder.write4ByteInt(recipeIngredient.localId);
            this.encoder.writeString(recipeIngredient.getUid());
            this.encoder.write4ByteInt(recipeIngredient.getIngredientFoodId());
            this.encoder.write4ByteInt(((Long) ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().lookupFoodMasterAndUidsFromLocalId(recipeIngredient.getIngredientFoodId()).getItem1()).longValue());
            this.encoder.writeString(recipeIngredient.getFood().getUid());
            this.encoder.writeString(recipeIngredient.getFood().getOriginalUid());
            this.encoder.write4ByteInt((long) recipeIngredient.getWeightIndex());
            this.encoder.writeFloat(recipeIngredient.getQuantity());
            this.encoder.write2ByteInt(recipeIngredient.isFraction() ? 1 : 0);
        }
        Map properties = recipeFood.getProperties();
        for (String str : properties.keySet()) {
            for (String str2 : (List) properties.get(str)) {
                this.encoder.writeString(str);
                this.encoder.writeString(str2);
            }
        }
        finishPacket();
    }

    public void addAddRecipeBoxItemPacketForRecipeFood(RecipeFood recipeFood, long j) {
        beginPacketOfType(25);
        this.encoder.write4ByteInt(recipeFood.localId);
        this.encoder.write4ByteInt(recipeFood.masterDatabaseId);
        this.encoder.writeString(recipeFood.getUid());
        this.encoder.writeString(recipeFood.getOriginalUid());
        this.encoder.write4ByteInt(j);
        finishPacket();
    }

    public void addFoodPacket(Food food) {
        food.updateOriginalFoodIdsIfNeeded((DbConnectionManager) this.dbConnectionManager.get());
        beginPacketOfType(3);
        this.encoder.write4ByteInt(food.getLocalId());
        this.encoder.writeString(food.getUid());
        this.encoder.write4ByteInt(food.getOriginalId());
        this.encoder.write4ByteInt(food.getOriginalMasterId());
        this.encoder.writeString(food.getOriginalUid());
        this.encoder.write8ByteInt(food.getPromotedFromMasterId());
        this.encoder.writeString(food.getPromotedFromUid());
        this.encoder.writeString(food.getDescription());
        this.encoder.writeString(food.getBrand());
        this.encoder.writeString(food.getBarcode());
        this.encoder.write4ByteInt(food.isPublic() | (food.isDeleted() ? (char) 2 : 0) ? 1 : 0);
        for (int i = 0; i < 20; i++) {
            this.encoder.writeFloat(food.getNutritionalValues().valueForNutrientIndex(i));
        }
        this.encoder.writeFloat(food.getGrams());
        this.encoder.write2ByteInt(food.serverFoodType());
        int length = food.getFoodPortions().length;
        this.encoder.write2ByteInt(length);
        for (int i2 = 0; i2 < length; i2++) {
            FoodPortion foodPortionWithIndex = food.foodPortionWithIndex(i2);
            this.encoder.writeFloat(foodPortionWithIndex.getAmount());
            this.encoder.writeFloat(foodPortionWithIndex.getGramWeight());
            this.encoder.writeString(foodPortionWithIndex.getDescription());
            this.encoder.write2ByteInt(foodPortionWithIndex.getIsFraction() ? 1 : 0);
        }
        finishPacket();
    }

    public void addExercisePacket(Exercise exercise) {
        beginPacketOfType(4);
        this.encoder.write4ByteInt(exercise.getLocalId());
        this.encoder.writeString("");
        this.encoder.write4ByteInt(0);
        this.encoder.write4ByteInt(0);
        this.encoder.writeString("");
        this.encoder.write2ByteInt(exercise.getExerciseType());
        this.encoder.writeString(exercise.getDescription());
        this.encoder.write4ByteInt(exercise.isPublic() | (exercise.isDeleted() ? (char) 2 : 0) ? 1 : 0);
        this.encoder.writeFloat(exercise.getMets());
        finishPacket();
    }

    public void addSetMealIngredientsPacketForMealFood(MealFood mealFood) {
        FoodDBAdapter foodDBAdapter = new FoodDBAdapter(this.context, (DbConnectionManager) this.dbConnectionManager.get());
        beginPacketOfType(11);
        this.encoder.write4ByteInt(mealFood.getLocalId());
        this.encoder.write4ByteInt(mealFood.getMasterDatabaseId());
        this.encoder.writeString(mealFood.getOriginalUid());
        this.encoder.writeString(mealFood.getUid());
        this.encoder.write4ByteInt((long) mealFood.getIngredients().size());
        for (MealIngredient mealIngredient : mealFood.getIngredients()) {
            Tuple4 lookupFoodMasterAndUidsFromLocalId = foodDBAdapter.lookupFoodMasterAndUidsFromLocalId(mealIngredient.getIngredientFoodId());
            long longValue = ((Long) lookupFoodMasterAndUidsFromLocalId.getItem1()).longValue();
            this.encoder.write4ByteInt(mealIngredient.getLocalId());
            this.encoder.writeString(mealIngredient.getIngredientFoodUid());
            this.encoder.write4ByteInt(mealIngredient.getIngredientFoodId());
            this.encoder.write4ByteInt(longValue);
            this.encoder.writeString((String) lookupFoodMasterAndUidsFromLocalId.getItem4());
            this.encoder.writeString((String) lookupFoodMasterAndUidsFromLocalId.getItem3());
            this.encoder.write4ByteInt((long) mealIngredient.getWeightIndex());
            this.encoder.writeFloat(mealIngredient.getQuantity());
            this.encoder.write2ByteInt(mealIngredient.isFraction() ? 1 : 0);
        }
        finishPacket();
    }

    public void addSetMeasurementPacket(Measurement measurement) {
        beginPacketOfType(10);
        this.encoder.write8ByteInt(measurement.getLocalId());
        this.encoder.writeString(measurement.getUid());
        this.encoder.writeString(measurement.getMeasurementTypeName());
        this.encoder.writeString(measurement.getSourceClientId());
        this.encoder.writeDate(measurement.getEntryDate().getTime());
        this.encoder.writeFloat(measurement.getValue().floatValue());
        finishPacket();
    }

    public void addFoodEntryPacket(FoodEntry foodEntry) {
        Food food = foodEntry.getFood();
        if (!food.hasMasterDatabaseId()) {
            Ln.w("no master database ID in addFoodEntryPacket", new Object[0]);
        }
        beginPacketOfType(7);
        this.encoder.write8ByteInt(foodEntry.getLocalId());
        this.encoder.writeString(foodEntry.getUid());
        this.encoder.write4ByteInt(food.getLocalId());
        this.encoder.write4ByteInt(food.getMasterDatabaseId());
        this.encoder.writeString(food.getUid());
        this.encoder.writeString(food.getOriginalUid());
        this.encoder.writeDate(foodEntry.getDate());
        this.encoder.writeString(foodEntry.getMealName());
        this.encoder.writeFloat(foodEntry.getQuantity());
        this.encoder.write4ByteInt((long) foodEntry.getWeightIndex());
        MealFood mealFood = foodEntry.getMealFood();
        long j = 0;
        this.encoder.write4ByteInt(mealFood != null ? mealFood.getLocalId() : 0);
        BinaryEncoder binaryEncoder = this.encoder;
        if (mealFood != null) {
            j = mealFood.getMasterDatabaseId();
        }
        binaryEncoder.write4ByteInt(j);
        this.encoder.writeString("");
        this.encoder.writeString("");
        this.encoder.writeString(foodEntry.getEntryTime() != null ? DateTimeUtils.format("HH:mm:ss", foodEntry.getEntryTime()) : "");
        this.encoder.writeString(foodEntry.getLoggedAt() != null ? DateTimeUtils.format(DateTimeUtils.FORMAT_ISO8601, TimeZone.getDefault(), foodEntry.getLoggedAt()) : "");
        finishPacket();
    }

    public void addExerciseEntryPacket(ExerciseEntry exerciseEntry) {
        Exercise exercise = exerciseEntry.getExercise();
        if (!exercise.hasMasterDatabaseId()) {
            Ln.w("no master database ID in addExerciseEntryPacket", new Object[0]);
        }
        beginPacketOfType(8);
        this.encoder.write8ByteInt(exerciseEntry.getLocalId());
        this.encoder.writeString(exerciseEntry.getUid());
        this.encoder.write4ByteInt(exercise.getLocalId());
        this.encoder.write4ByteInt(exercise.getMasterDatabaseId());
        this.encoder.writeString(exercise.getUid());
        this.encoder.writeString(exercise.getOriginalUid());
        this.encoder.writeDate(exerciseEntry.getDate());
        this.encoder.write4ByteInt((long) exerciseEntry.getQuantity());
        this.encoder.write4ByteInt((long) exerciseEntry.getSets());
        this.encoder.writeFloat(exerciseEntry.getWeight());
        this.encoder.writeFloat(exerciseEntry.getCalories());
        new StringBuilder();
        Map extraProperties = exerciseEntry.getExtraProperties();
        int size = CollectionUtils.size(extraProperties);
        this.encoder.write2ByteInt(size);
        if (size > 0) {
            for (String str : extraProperties.keySet()) {
                String str2 = (String) extraProperties.get(str);
                this.encoder.writeString(str);
                this.encoder.writeString(str2);
            }
        }
        finishPacket();
    }

    public void addSetWaterEntryPacket(WaterEntry waterEntry) {
        beginPacketOfType(16);
        this.encoder.write8ByteInt(waterEntry.getLocalId());
        this.encoder.writeString(waterEntry.getUid());
        this.encoder.writeDate(waterEntry.getEntryDate());
        this.encoder.writeFloat(waterEntry.getMilliliters());
        finishPacket();
    }

    public void addDeleteItemPacket(DatabaseObjectReference databaseObjectReference) {
        addDeleteItemPacket(databaseObjectReference.getItemType(), databaseObjectReference.getMasterDatabaseId(), databaseObjectReference.getUid(), databaseObjectReference.isDestroyed());
    }

    public void addDeleteItemPacket(int i, long j, String str, boolean z) {
        beginPacketOfType(17);
        this.encoder.write2ByteInt(i);
        this.encoder.write8ByteInt(j);
        this.encoder.writeString(str);
        this.encoder.write2ByteInt(z ? 2 : 1);
        finishPacket();
    }

    public void addSetDiaryNotePacket(DiaryNote diaryNote) {
        beginPacketOfType(23);
        this.encoder.write8ByteInt(diaryNote.getLocalId());
        this.encoder.writeString(diaryNote.getUid());
        this.encoder.writeDate(diaryNote.getEntryDate());
        this.encoder.write2ByteInt(diaryNote.getNoteType());
        this.encoder.writeString(diaryNote.getBody());
        finishPacket();
    }

    public void addAddUserImagePacket(UserImage userImage) {
        byte[] imageData = userImage.getImageData();
        beginPacketOfType(29);
        this.encoder.write8ByteInt(userImage.localId);
        this.encoder.write8ByteInt(userImage.masterDatabaseId);
        this.encoder.writeString(userImage.getUid());
        this.encoder.write8ByteInt(userImage.getCreatorUserId());
        this.encoder.writeString(userImage.getCreatorUid());
        this.encoder.write2ByteInt(1);
        this.encoder.write8ByteInt(0);
        this.encoder.write8ByteInt(0);
        this.encoder.write4ByteInt(userImage.isVisible() ? 1 : 0);
        this.encoder.write2ByteInt(userImage.getPosition());
        this.encoder.write2ByteInt(userImage.getWidth());
        this.encoder.write2ByteInt(userImage.getHeight());
        this.encoder.writeString(userImage.getFileType());
        this.encoder.writeString("");
        this.encoder.writeString("");
        this.encoder.writeString("");
        this.encoder.writeTimestamp(userImage.getCreatedAt());
        this.encoder.writeTimestamp(userImage.getUpdatedAt());
        if (imageData != null) {
            this.encoder.write4ByteInt((long) imageData.length);
            this.encoder.writeRawBytes(imageData, imageData.length);
        } else {
            this.encoder.write4ByteInt(0);
        }
        finishPacket();
    }

    public void AddReminderPacket(ReminderObject reminderObject) {
        beginPacketOfType(35);
        this.encoder.write8ByteInt(reminderObject.getLocalId());
        this.encoder.write8ByteInt(reminderObject.getMasterId());
        this.encoder.writeString(reminderObject.getUid());
        HashMap hashMap = new HashMap();
        hashMap.put("interval_in_days", String.format("%d", new Object[]{Integer.valueOf(reminderObject.getIntervalInDays())}));
        hashMap.put("wall_clock_time", reminderObject.getWallClockTime());
        hashMap.put("reminder_type", reminderObject.reminderTypeNameForCode(reminderObject.getReminderType()));
        if (Strings.notEmpty(reminderObject.getMealName())) {
            hashMap.put("meal_name", reminderObject.getMealName());
        }
        if (Strings.notEmpty(reminderObject.getFrequency())) {
            hashMap.put("frequency", reminderObject.getFrequency());
        }
        if (Strings.notEmpty(reminderObject.getDayOfWeek())) {
            hashMap.put("day_of_week", reminderObject.getDayOfWeek());
        }
        if (Strings.notEmpty((Object) Integer.valueOf(reminderObject.getDayOfMonth()))) {
            hashMap.put("day_of_month", String.format("%d", new Object[]{Integer.valueOf(reminderObject.getDayOfMonth())}));
        }
        this.encoder.write2ByteInt(hashMap.size());
        for (String str : hashMap.keySet()) {
            this.encoder.writeString(str);
            this.encoder.writeString((String) hashMap.get(str));
        }
        this.encoder.write2ByteInt(reminderObject.getFlags());
        finishPacket();
    }

    public void addRequestPendingItemTalliesPacket() {
        beginPacketOfType(130);
        finishPacket();
    }

    public void addAddTrackedNutrientPacket(TrackedNutrient trackedNutrient) {
        beginPacketOfType(36);
        this.encoder.write8ByteInt(trackedNutrient.localId);
        this.encoder.write8ByteInt(trackedNutrient.masterDatabaseId);
        this.encoder.writeString(trackedNutrient.getUid());
        this.encoder.write8ByteInt(trackedNutrient.getNutrientNameId());
        this.encoder.write2ByteInt(trackedNutrient.getPosition());
        finishPacket();
    }

    public void addSetFoodPermissionsPacket(FoodPermission foodPermission) {
        beginPacketOfType(43);
        foodPermission.writeData(this.encoder);
        Food fetchFoodById = ((DbConnectionManager) this.dbConnectionManager.get()).foodDbAdapter().fetchFoodById(foodPermission.getFoodLocalId());
        if (!(fetchFoodById == null || fetchFoodById.getMasterDatabaseId() == 0)) {
            foodPermission.setFoodMasterId(fetchFoodById.getMasterDatabaseId());
        }
        finishPacket();
    }

    public void addSetFoodNotesPacket(FoodNotes foodNotes) {
        beginPacketOfType(44);
        foodNotes.writeData(this.encoder);
        finishPacket();
    }
}
