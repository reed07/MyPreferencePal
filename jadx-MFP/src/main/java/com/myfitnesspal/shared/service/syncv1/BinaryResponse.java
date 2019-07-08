package com.myfitnesspal.shared.service.syncv1;

import android.content.Context;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.DiaryNoteDbAdapter;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter;
import com.myfitnesspal.shared.db.adapter.GenericAdapter;
import com.myfitnesspal.shared.db.adapter.MealIngredientsDBAdapter;
import com.myfitnesspal.shared.db.adapter.MeasurementTypesDBAdapter;
import com.myfitnesspal.shared.db.adapter.RecipeBoxItemsDBAdapter;
import com.myfitnesspal.shared.db.adapter.RecipeIngredientsDBAdapter;
import com.myfitnesspal.shared.db.adapter.RecipePropertiesDBAdapter;
import com.myfitnesspal.shared.db.adapter.TrackedNutrientDbAdapter;
import com.myfitnesspal.shared.db.adapter.UserPropertiesDBAdapter;
import com.myfitnesspal.shared.db.adapter.UserV1DBAdapter;
import com.myfitnesspal.shared.db.adapter.WaterEntriesDBAdapter;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodAndExerciseFactory;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class BinaryResponse extends PacketType {
    private static final boolean VERBOSE_LOGGING = false;
    public static final int kBinaryResponseStateBorked = 3;
    public static final int kBinaryResponseStateHeaderRead = 1;
    public static final int kBinaryResponseStateInitial = 0;
    public static final int kBinaryResponseStateNetworkError = 4;
    public static final int kBinaryResponseStateReadingPacket = 2;
    @Inject
    protected AppSettings appSettings;
    @Inject
    protected Context context;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    Exercise decodedExercise;
    ExerciseEntry decodedExerciseEntry;
    Food decodedFood;
    FoodEntry decodedFoodEntry;
    BinaryDecoder decoder = new BinaryDecoder();
    DiaryNoteDbAdapter diaryNoteDbAdapter;
    String errorMessage;
    Exercise exercise;
    ExerciseEntry exerciseEntry;
    int expectedPacketCount;
    FoodDBAdapter foodDbAdapter;
    FoodEntriesDBAdapter foodEntriesDbAdapter;
    FoodEntry foodEntry;
    GenericAdapter genericAdapter;
    int lastProcessedItemType;
    MealIngredientsDBAdapter mealIngredientsDBAdapter;
    MeasurementTypesDBAdapter measurementTypesDBAdapter;
    long packetLength;
    int packetsProcessed;
    RecipeBoxItemsDBAdapter recipeBoxItemsDBAdapter;
    RecipeIngredientsDBAdapter recipeIngredientsDBAdapter;
    RecipePropertiesDBAdapter recipePropertiesDBAdapter;
    @Inject
    protected Lazy<Session> session;
    int state;
    int statusCode;
    @Inject
    protected SyncSettings syncSettings;
    TrackedNutrientDbAdapter trackedNutrientDbAdapter;
    UserPropertiesDBAdapter userPropertiesDBAdapter;
    UserV1DBAdapter userV1DBAdapter;
    WaterEntriesDBAdapter waterEntriesDBAdapter;

    public void processPacketOfType(int i) {
    }

    public BinaryResponse() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void processAvailablePackets() {
        do {
        } while (processData().booleanValue());
    }

    public Boolean processData() {
        boolean z = false;
        try {
            switch (this.state) {
                case 0:
                    if (this.decoder.getTotalBytesLeft() >= 8) {
                        if (this.decoder.decode2ByteInt() == 1235) {
                            this.packetLength = this.decoder.decode4ByteInt();
                            this.decoder.decode2ByteInt();
                            this.state = 1;
                            z = true;
                            break;
                        } else {
                            Ln.d("SYNC: bad magic number, bail", new Object[0]);
                            this.state = 3;
                            return Boolean.valueOf(true);
                        }
                    } else {
                        Ln.d("SYNC: not enough bytes, bail", new Object[0]);
                        return Boolean.valueOf(false);
                    }
                case 1:
                    if (this.decoder.getTotalBytesLeft() >= this.packetLength - 8) {
                        this.state = 2;
                        z = true;
                        break;
                    } else {
                        Ln.d("SYNC: not enough bytes, bail", new Object[0]);
                        return Boolean.valueOf(false);
                    }
                case 2:
                    processPacketOfType(this.decoder.decode2ByteInt());
                    this.packetsProcessed++;
                    this.state = 0;
                    z = true;
                    break;
                case 3:
                    Ln.d("SYNC: borked!", new Object[0]);
                    break;
            }
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("SynchronizationResponse, An Exception occured while attempting to process sync data: ");
            sb.append(e.getMessage());
            Ln.v(sb.toString(), new Object[0]);
        }
        return Boolean.valueOf(z);
    }

    public Boolean isBorked() {
        int i = this.state;
        boolean z = i == 3 || i == 4;
        Ln.d("isBorked: state = %s, return %s", Integer.valueOf(this.state), Boolean.valueOf(z));
        return Boolean.valueOf(z);
    }

    public boolean wasSuccessful() {
        return !isBorked().booleanValue() && this.statusCode == 0;
    }

    public Boolean hasErrorMessage() {
        String str = this.errorMessage;
        return Boolean.valueOf(str != null && str.length() > 0);
    }

    public void decodeNextFood() {
        this.decodedFood = FoodAndExerciseFactory.createFoodFrom(this.decoder, (DbConnectionManager) this.dbConnectionManager.get());
    }

    public void decodeNextFoodEntry() {
        try {
            this.foodEntry = new FoodEntry();
            this.foodEntry.setMasterDatabaseId(this.decoder.decode8ByteInt());
            this.foodEntry.setUid(this.decoder.decodeString());
            decodeNextFood();
            this.foodEntry.setFood(this.decodedFood);
            this.foodEntry.setDate(this.decoder.decodeDate());
            this.foodEntry.setMealName(this.decoder.decodeString());
            this.foodEntry.setQuantity(this.decoder.decodeFloat());
            this.foodEntry.setWeightIndex((int) this.decoder.decode4ByteInt());
            this.foodEntry.setFoodPortion(this.foodEntry.getFood().foodPortionWithIndex(this.foodEntry.getWeightIndex()));
            long decode4ByteInt = this.decoder.decode4ByteInt();
            if (decode4ByteInt > 0) {
                this.foodEntry.setMealFood(this.foodDbAdapter.getMealFoodForMasterId(decode4ByteInt));
            }
            this.decoder.decodeString();
            this.decoder.decodeString();
            String decodeString = this.decoder.decodeString();
            if (!Strings.isEmpty(decodeString)) {
                this.foodEntry.setEntryTime(DateTimeUtils.parse("HH:mm:ss", decodeString));
            }
            String decodeString2 = this.decoder.decodeString();
            if (!Strings.isEmpty(decodeString2)) {
                this.foodEntry.setLoggedAt(DateTimeUtils.parse(DateTimeUtils.FORMAT_ISO8601, decodeString2));
            }
            this.decodedFoodEntry = this.foodEntry;
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("SynchronizationResponse, An Exception occured while attempting to decodeNextFoodEntry(): ");
            sb.append(e.getMessage());
            Ln.v(sb.toString(), new Object[0]);
        }
    }

    public void decodeNextExercise() {
        this.exercise = FoodAndExerciseFactory.createExerciseFrom(this.decoder, (DbConnectionManager) this.dbConnectionManager.get());
        setDecodedExercise(this.exercise);
    }

    public void decodeNextExerciseEntry() {
        try {
            this.exerciseEntry = new ExerciseEntry();
            this.exerciseEntry.setMasterDatabaseId(this.decoder.decode8ByteInt());
            this.exerciseEntry.setUid(this.decoder.decodeString());
            decodeNextExercise();
            this.exerciseEntry.setExercise(this.decodedExercise);
            this.exerciseEntry.setDate(this.decoder.decodeDate());
            this.exerciseEntry.setQuantity((int) this.decoder.decode4ByteInt());
            this.exerciseEntry.setSets((int) this.decoder.decode4ByteInt());
            this.exerciseEntry.setWeight(this.decoder.decodeFloat());
            this.exerciseEntry.setCalories(this.decoder.decodeFloat());
            int decode2ByteInt = this.decoder.decode2ByteInt();
            while (true) {
                int i = decode2ByteInt - 1;
                if (decode2ByteInt > 0) {
                    this.exerciseEntry.setExtraPropertyNamed(this.decoder.decodeString(), this.decoder.decodeString());
                    decode2ByteInt = i;
                } else {
                    setDecodedExerciseEntry(this.exerciseEntry);
                    return;
                }
            }
        } catch (Exception e) {
            Ln.v(e, "SynchronizationResponse, An Exception occured while attempting to decodeNextExerciseEntry()", new Object[0]);
        }
    }

    public void processPendingItemTalliesPacket() {
        int decode2ByteInt = this.decoder.decode2ByteInt();
        while (true) {
            int i = decode2ByteInt - 1;
            if (decode2ByteInt > 0) {
                this.decoder.decodeString();
                this.decoder.decode4ByteInt();
                decode2ByteInt = i;
            } else {
                return;
            }
        }
    }

    public void setDecodedExerciseEntry(ExerciseEntry exerciseEntry2) {
        this.decodedExerciseEntry = exerciseEntry2;
    }

    public void setDecodedExercise(Exercise exercise2) {
        this.decodedExercise = exercise2;
    }
}
