package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.FoodDBAdapter;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple4;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

public class Food extends FoodOrExercise implements Parcelable {
    public static final Creator<Food> CREATOR = new Creator<Food>() {
        public Food createFromParcel(Parcel parcel) {
            return new Food(parcel);
        }

        public Food[] newArray(int i) {
            return new Food[i];
        }
    };
    public static int FOOD_INFO_V1 = 1;
    private static long QUICK_ADDED_CALORIES_MASTER_ID = 2330067;
    @Expose
    private String barcode;
    @Expose
    private String brand;
    @Expose
    private String cachedCompleteDescription;
    private FoodPermission foodPermission;
    @Expose
    private FoodPortion[] foodPortions;
    @Expose
    private int foodType;
    @Expose
    private float grams;
    @Expose
    private NutritionalValues nutritionalValues;
    @Expose
    private long promotedFromMasterId;
    @Expose
    private String promotedFromUid;
    @Expose
    private int sortOrder;
    @Expose
    private boolean verified;

    public static int itemTypeFromServerFoodType(int i) {
        switch (i) {
            case 0:
            case 3:
                return 1;
            case 1:
                return 3;
            case 2:
                return 11;
            default:
                return 1;
        }
    }

    public boolean isFood() {
        return true;
    }

    public boolean isMeal() {
        return false;
    }

    public boolean isNormalFood() {
        return true;
    }

    public boolean isRecipe() {
        return false;
    }

    public int itemType() {
        return 1;
    }

    public Food() {
    }

    public Food(Food food) {
        this.localId = food.localId;
        this.masterDatabaseId = food.masterDatabaseId;
        this.originalId = food.originalId;
        this.originalMasterId = food.originalMasterId;
        this.promotedFromMasterId = food.promotedFromMasterId;
        this.promotedFromUid = food.promotedFromUid;
        this.ownerUserId = food.ownerUserId;
        this.ownerUserMasterId = food.ownerUserMasterId;
        this.sortPriority = food.sortPriority;
        this.isDeleted = food.isDeleted;
        this.isPublic = food.isPublic;
        this.description = food.description;
        this.foodType = food.foodType;
        this.nutritionalValues = (NutritionalValues) food.nutritionalValues.clone();
        this.grams = food.grams;
        String str = food.brand;
        if (str == null) {
            str = "";
        }
        this.brand = str;
        this.foodPortions = (FoodPortion[]) food.foodPortions.clone();
        String str2 = food.cachedCompleteDescription;
        if (str2 == null) {
            str2 = "";
        }
        this.cachedCompleteDescription = str2;
        this.sortOrder = food.sortOrder;
    }

    protected Food(Parcel parcel) {
        super(parcel);
        this.foodType = parcel.readInt();
        this.nutritionalValues = (NutritionalValues) parcel.readParcelable(NutritionalValues.class.getClassLoader());
        this.grams = parcel.readFloat();
        this.brand = parcel.readString();
        this.barcode = parcel.readString();
        this.foodPortions = (FoodPortion[]) parcel.createTypedArray(FoodPortion.CREATOR);
        this.cachedCompleteDescription = parcel.readString();
        this.sortOrder = parcel.readInt();
        this.verified = ParcelableUtil.readBoolean(parcel);
        this.promotedFromMasterId = parcel.readLong();
        this.promotedFromUid = parcel.readString();
        this.foodPermission = (FoodPermission) parcel.readParcelable(FoodPermission.class.getClassLoader());
    }

    public boolean isMealEntries() {
        return super.isMealEntries();
    }

    public boolean isFoodEntry() {
        return super.isFoodEntry();
    }

    public boolean isExerciseEntry() {
        return super.isExerciseEntry();
    }

    public static Food initAsDummy(String str) {
        Food food = new Food();
        food.foodType = -1;
        food.description = str;
        return food;
    }

    public int getFoodType() {
        return this.foodType;
    }

    public final String getCachedCompleteDescription() {
        return this.cachedCompleteDescription;
    }

    public void setCachedCompleteDescription(String str) {
        this.cachedCompleteDescription = str;
    }

    public void setFoodType(int i) {
        this.foodType = i;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String str) {
        this.barcode = str;
    }

    public float getGrams() {
        return this.grams;
    }

    public void setGrams(float f) {
        this.grams = f;
    }

    public FoodPortion[] getFoodPortions() {
        return this.foodPortions;
    }

    public void setFoodPortions(FoodPortion[] foodPortionArr) {
        this.foodPortions = foodPortionArr;
    }

    public NutritionalValues getNutritionalValues() {
        return this.nutritionalValues;
    }

    public float getCaloriesValueFromNutritionalValues(float f, float f2) {
        NutritionalValues nutritionalValues2 = this.nutritionalValues;
        return nutritionalValues2 != null ? nutritionalValues2.getCaloriesValue(f) : f2;
    }

    public float getCaloriesFromNutritionalValues(float f, float f2) {
        NutritionalValues nutritionalValues2 = this.nutritionalValues;
        if (nutritionalValues2 != null) {
            f2 = nutritionalValues2.calories();
        }
        return f * f2;
    }

    public void setNutritionalValues(NutritionalValues nutritionalValues2) {
        this.nutritionalValues = nutritionalValues2;
    }

    public int getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(int i) {
        this.sortOrder = i;
    }

    public long getPromotedFromMasterId() {
        return this.promotedFromMasterId;
    }

    public void setPromotedFromMasterId(long j) {
        this.promotedFromMasterId = j;
    }

    public String getPromotedFromUid() {
        return this.promotedFromUid;
    }

    public void setPromotedFromUid(String str) {
        this.promotedFromUid = str;
    }

    public FoodPermission getFoodPermission() {
        return this.foodPermission;
    }

    public void setFoodPermission(FoodPermission foodPermission2) {
        this.foodPermission = foodPermission2;
    }

    public long encryptionKey() {
        return getLocalId() > 0 ? getLocalId() : getMasterDatabaseId();
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean z) {
        this.verified = z;
    }

    public String brandAndDescription() {
        if (!hasBrand()) {
            return getDescription();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getDescription());
        sb.append(" (");
        sb.append(this.brand);
        sb.append(")");
        return sb.toString();
    }

    public FoodPortion containerPortion() {
        FoodPortion[] foodPortionArr;
        for (FoodPortion foodPortion : this.foodPortions) {
            if (foodPortion.getDescription().startsWith("container")) {
                return foodPortion;
            }
        }
        return null;
    }

    public float servingsPerContainer() {
        FoodPortion containerPortion = containerPortion();
        if (containerPortion == null || NumberUtils.isEffectivelyZero((double) containerPortion.getGramWeight())) {
            return 1.0f;
        }
        return containerPortion.getGramWeight();
    }

    public FoodPortion defaultPortion() {
        FoodPortion[] foodPortionArr = this.foodPortions;
        if (foodPortionArr == null || foodPortionArr.length == 0) {
            return null;
        }
        return foodPortionWithIndex(0);
    }

    public boolean hasBrand() {
        String str = this.brand;
        return str != null && str.length() > 0;
    }

    public float nutrientMultiplierForFoodPortion(FoodPortion foodPortion) {
        if (foodPortion != null && Double.compare(foodPortion.getNutritionMultiplier().doubleValue(), FoodPortion.DEFAULT_NUTRITION_MULTIPLIER.doubleValue()) != 0) {
            return foodPortion.getNutritionMultiplier().floatValue();
        }
        float f = 1.0f;
        if (this.grams <= BitmapDescriptorFactory.HUE_RED) {
            return 1.0f;
        }
        if (foodPortion != null) {
            f = foodPortion.getGramWeight() / this.grams;
        }
        return f;
    }

    public FoodPortion foodPortionWithIndex(int i) {
        if (i > 0) {
            try {
                if (i < this.foodPortions.length) {
                    return this.foodPortions[i];
                }
            } catch (Exception e) {
                Ln.e(e);
                return null;
            }
        }
        if (i != 0 || this.foodPortions.length <= 0) {
            return defaultPortion();
        }
        return this.foodPortions[0];
    }

    public String summaryLine1() {
        return getDescription();
    }

    public int compareTo(Food food) {
        int sortPriority = food.getSortPriority();
        if (getSortPriority() > sortPriority) {
            return -1;
        }
        if (getSortPriority() < sortPriority) {
            return 1;
        }
        int i = food.sortOrder;
        int i2 = this.sortOrder;
        if (i2 > i) {
            return -1;
        }
        if (i2 < i) {
            return 1;
        }
        return 0;
    }

    public void lookupSortOrderByUsageCount(long j, DbConnectionManager dbConnectionManager) {
        try {
            this.sortOrder = dbConnectionManager.foodEntriesDbAdapter().fetchOverallUsageCountForOriginalFoodLocalId(getOriginalId(), j);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void updateOriginalFoodIdsIfNeeded(DbConnectionManager dbConnectionManager) {
        try {
            updateOriginalFoodIdsIfNeededUnsafe(dbConnectionManager);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void updateOriginalFoodIdsIfNeededUnsafe(DbConnectionManager dbConnectionManager) {
        boolean z = false;
        boolean z2 = this.originalMasterId.longValue() != 0;
        boolean hasOriginalUid = hasOriginalUid();
        FoodDBAdapter foodDbAdapter = dbConnectionManager.foodDbAdapter();
        if (this.originalId.longValue() <= 0 || (z2 && hasOriginalUid)) {
            if (this.originalMasterId.longValue() > 0 && this.originalId.longValue() == 0) {
                if (this.originalMasterId.longValue() != this.masterDatabaseId) {
                    long lookupFoodLocalIdFromMasterId = foodDbAdapter.lookupFoodLocalIdFromMasterId(this.originalMasterId.longValue());
                    if (lookupFoodLocalIdFromMasterId > 0) {
                        this.originalId = Long.valueOf(lookupFoodLocalIdFromMasterId);
                    } else {
                        this.originalId = Long.valueOf(this.localId);
                    }
                    z = true;
                } else if (this.originalId.longValue() != this.localId) {
                    this.originalId = Long.valueOf(this.localId);
                    z = true;
                }
            }
        } else if (this.originalId.longValue() == this.localId) {
            if (this.originalMasterId.longValue() != this.masterDatabaseId) {
                this.originalMasterId = Long.valueOf(this.masterDatabaseId);
                z = true;
            }
            if (!Strings.equals(this.originalUid, this.uid)) {
                this.originalUid = this.uid;
                z = true;
            }
        } else {
            Tuple4 lookupFoodMasterAndUidsFromLocalId = foodDbAdapter.lookupFoodMasterAndUidsFromLocalId(this.originalId.longValue());
            long longValue = ((Long) lookupFoodMasterAndUidsFromLocalId.getItem1()).longValue();
            if (longValue > 0) {
                this.originalMasterId = Long.valueOf(longValue);
                z = true;
            }
            String str = (String) lookupFoodMasterAndUidsFromLocalId.getItem3();
            if (Strings.notEmpty(str)) {
                this.originalUid = str;
                z = true;
            }
        }
        if (z) {
            foodDbAdapter.updateOriginalIdsForFoodId(this.localId, this.originalId.longValue(), this.originalMasterId.longValue(), this.originalUid);
        }
    }

    public static Food createCustomFoodWithDescription(String str, String str2, String str3, float f, NutritionalValues nutritionalValues2, Food food, String str4, Session session, DbConnectionManager dbConnectionManager) {
        int i;
        float f2 = f;
        try {
            Boolean valueOf = Boolean.valueOf(false);
            User user = session.getUser();
            Food food2 = new Food();
            food2.setOriginalId(0);
            food2.setOwnerUserId(user.getLocalId());
            food2.setOwnerUserMasterId(user.getMasterDatabaseId());
            String str5 = str;
            food2.setDescription(str);
            food2.setBrand(str2);
            food2.setGrams(1.0f);
            ArrayList arrayList = new ArrayList(3);
            String analyzeServingSizeDescription = FoodPortion.analyzeServingSizeDescription(str3);
            Float analyzeServingSizeAmount = FoodPortion.analyzeServingSizeAmount(str3);
            Boolean analyzeServingSizeIsFraction = FoodPortion.analyzeServingSizeIsFraction(str3, analyzeServingSizeAmount, valueOf);
            FoodPortion foodPortion = new FoodPortion();
            foodPortion.setWeightIndex(0);
            foodPortion.setGramWeight(1.0f);
            foodPortion.setAmount(analyzeServingSizeAmount.floatValue());
            foodPortion.setDescription(analyzeServingSizeDescription);
            foodPortion.setIsFraction(analyzeServingSizeIsFraction.booleanValue());
            arrayList.add(foodPortion);
            double floatValue = (double) analyzeServingSizeAmount.floatValue();
            float f3 = BitmapDescriptorFactory.HUE_RED;
            if (floatValue != 1.0d) {
                FoodPortion foodPortion2 = new FoodPortion();
                foodPortion2.setWeightIndex(1);
                foodPortion2.setGramWeight(analyzeServingSizeAmount.floatValue() == BitmapDescriptorFactory.HUE_RED ? 1.0f : 1.0f / analyzeServingSizeAmount.floatValue());
                foodPortion2.setAmount(1.0f);
                foodPortion2.setDescription(analyzeServingSizeDescription);
                foodPortion2.setIsFraction(true);
                arrayList.add(foodPortion2);
                i = 2;
            } else {
                i = 1;
            }
            if (((double) f2) != 1.0d) {
                FoodPortion foodPortion3 = new FoodPortion();
                foodPortion3.setWeightIndex(i);
                foodPortion3.setGramWeight(f2);
                foodPortion3.setAmount(1.0f);
                if (((double) analyzeServingSizeAmount.floatValue()) > 0.0d) {
                    f3 = f2 * analyzeServingSizeAmount.floatValue();
                }
                String initStringWithFormattedFloat = Strings.initStringWithFormattedFloat(f3, 2);
                StringBuilder sb = new StringBuilder();
                sb.append("container (");
                sb.append(initStringWithFormattedFloat);
                sb.append(" ");
                sb.append(analyzeServingSizeDescription);
                sb.append(")");
                foodPortion3.setDescription(sb.toString());
                foodPortion3.setIsFraction(true);
                arrayList.add(foodPortion3);
            }
            food2.setFoodPortions((FoodPortion[]) arrayList.toArray(new FoodPortion[arrayList.size()]));
            food2.setNutritionalValues(nutritionalValues2);
            if (Strings.notEmpty(str4)) {
                food2.setIsPublic(true);
                food2.setBarcode(str4);
            }
            dbConnectionManager.foodDbAdapter().insertFood(food2, food, dbConnectionManager);
            return food2;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public static Food quickAddedCaloriesFood(DbConnectionManager dbConnectionManager) {
        try {
            long lookupFoodLocalIdFromMasterId = dbConnectionManager.foodDbAdapter().lookupFoodLocalIdFromMasterId(QUICK_ADDED_CALORIES_MASTER_ID);
            if (lookupFoodLocalIdFromMasterId > 0) {
                return dbConnectionManager.foodDbAdapter().fetchFoodById(lookupFoodLocalIdFromMasterId);
            }
            return null;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public static void createQuickAddedCaloriesFoodIfNeeded(DbConnectionManager dbConnectionManager) {
        try {
            if (quickAddedCaloriesFood(dbConnectionManager) == null) {
                Food food = new Food();
                food.setFoodType(1);
                NutritionalValues nutritionalValues2 = new NutritionalValues();
                nutritionalValues2.initAsBlank();
                nutritionalValues2.setNutrientIndex(0, 1.0f);
                food.setNutritionalValues(nutritionalValues2);
                FoodPortion foodPortion = new FoodPortion();
                foodPortion.setWeightIndex(0);
                foodPortion.setAmount(1.0f);
                foodPortion.setGramWeight(1.0f);
                foodPortion.setIsFraction(false);
                foodPortion.setDescription("calories");
                food.setFoodPortions(new FoodPortion[1]);
                food.getFoodPortions()[0] = foodPortion;
                food.setGrams(1.0f);
                food.setIsPublic(true);
                food.setIsDeleted(true);
                food.setDescription(MealTypeName.LEGACY_QUICK_ADDED_CALORIES);
                food.setMasterDatabaseId(QUICK_ADDED_CALORIES_MASTER_ID);
                food.setOriginalMasterId(QUICK_ADDED_CALORIES_MASTER_ID);
                food.setOwnerUserId(0);
                food.setOwnerUserMasterId(0);
                dbConnectionManager.foodDbAdapter().insertFood(food, dbConnectionManager);
            }
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public static Food createQuickAddedMacroFood(User user, QuickAddFood quickAddFood, DbConnectionManager dbConnectionManager) {
        try {
            FoodPortion foodPortion = new FoodPortion();
            foodPortion.setWeightIndex(0);
            foodPortion.setGramWeight(1.0f);
            foodPortion.setAmount(1.0f);
            foodPortion.setDescription(Extras.PREMIUM_SERVINGS);
            Food food = new Food();
            food.setOriginalId(0);
            food.setOwnerUserId(user.getLocalId());
            food.setOwnerUserMasterId(user.getMasterDatabaseId());
            food.setDescription(MealTypeName.QUICK_ADD);
            food.setGrams(1.0f);
            food.setFoodType(1);
            food.setIsDeleted(true);
            food.setFoodPortions(new FoodPortion[]{foodPortion});
            NutritionalValues nutritionalValues2 = new NutritionalValues();
            nutritionalValues2.initAsBlank();
            nutritionalValues2.setMacros(quickAddFood.getCalories(), quickAddFood.getCarbohydrate(), quickAddFood.getProtein(), quickAddFood.getFat());
            food.setNutritionalValues(nutritionalValues2);
            dbConnectionManager.foodDbAdapter().insertFood(food, dbConnectionManager);
            return food;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public boolean isLegacyQuickAddedCalories() {
        return Strings.equalsIgnoreCase(getDescription(), MealTypeName.LEGACY_QUICK_ADDED_CALORIES);
    }

    public boolean isQuickAddFood() {
        return Strings.equalsIgnoreCase(getDescription(), MealTypeName.QUICK_ADD);
    }

    public boolean isQuickAddOfAnySort() {
        return isLegacyQuickAddedCalories() || isQuickAddFood();
    }

    public FoodEntry createDefaultFoodEntry(Session session) {
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setDate(session.getUser().getActiveDate());
        foodEntry.setFood(this);
        foodEntry.setQuantity(1.0f);
        foodEntry.setFoodPortion(defaultPortion());
        foodEntry.setWeightIndex(defaultPortion().getWeightIndex());
        foodEntry.setIsFraction(true);
        return foodEntry;
    }

    public int serverFoodType() {
        int itemType = itemType();
        if (itemType == 1) {
            return 0;
        }
        if (itemType != 3) {
            return itemType != 11 ? 0 : 2;
        }
        return 1;
    }

    public FoodEntry toFoodEntry(String str, Date date, int i, float f) {
        FoodEntry foodEntry = new FoodEntry();
        foodEntry.setFood(this);
        FoodPortion foodPortion = this.foodPortions[i];
        foodEntry.setFoodPortion(foodPortion);
        foodEntry.setWeightIndex(foodPortion.getWeightIndex());
        foodEntry.setQuantity(f);
        foodEntry.setMealName(str);
        foodEntry.setDate(date);
        foodEntry.setIsFraction(foodPortion.getIsFraction());
        foodEntry.clearCachedData();
        return foodEntry;
    }

    public int foodPortionIndexForPortion(@Nullable FoodPortion foodPortion) {
        if (foodPortion == null) {
            return 0;
        }
        FoodPortion[] foodPortions2 = getFoodPortions();
        for (int i = 0; i < foodPortions2.length; i++) {
            if (foodPortion.equals(foodPortions2[i])) {
                return i;
            }
        }
        return 0;
    }

    public static synchronized void unpackWithDecoder(Food food, byte[] bArr, int i) {
        synchronized (Food.class) {
            BinaryDecoder binaryDecoder = new BinaryDecoder();
            binaryDecoder.appendDataBuffer(bArr);
            binaryDecoder.startDecryptingWithKey(food.encryptionKey());
            NutritionalValues nutritionalValues2 = new NutritionalValues();
            nutritionalValues2.initAsBlank();
            for (int i2 = 0; i2 < 20; i2++) {
                nutritionalValues2.setNutrientIndex(i2, binaryDecoder.decodeFloat());
            }
            food.nutritionalValues = nutritionalValues2;
            food.grams = binaryDecoder.decodeFloat();
            int decode2ByteInt = binaryDecoder.decode2ByteInt();
            if (decode2ByteInt < 0) {
                decode2ByteInt = 0;
            }
            FoodPortion[] foodPortionArr = new FoodPortion[decode2ByteInt];
            for (int i3 = 0; i3 < decode2ByteInt; i3++) {
                FoodPortion foodPortion = new FoodPortion();
                foodPortion.setWeightIndex(i3);
                foodPortion.setAmount(binaryDecoder.decodeFloat());
                foodPortion.setGramWeight(binaryDecoder.decodeFloat());
                foodPortion.setDescription(binaryDecoder.decodeString());
                foodPortion.setIsFraction(binaryDecoder.decode2ByteInt() != 0);
                if (i != FOOD_INFO_V1) {
                    foodPortion.setNutritionMultiplier(Double.valueOf(binaryDecoder.decodeDouble()));
                }
                foodPortionArr[i3] = foodPortion;
            }
            food.foodPortions = foodPortionArr;
            binaryDecoder.stopDecrypting();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.foodType);
        parcel.writeParcelable(this.nutritionalValues, i);
        parcel.writeFloat(this.grams);
        parcel.writeString(this.brand);
        parcel.writeString(this.barcode);
        parcel.writeTypedArray(this.foodPortions, i);
        parcel.writeString(this.cachedCompleteDescription);
        parcel.writeInt(this.sortOrder);
        ParcelableUtil.writeBoolean(parcel, this.verified);
        parcel.writeLong(this.promotedFromMasterId);
        parcel.writeString(this.promotedFromUid);
        parcel.writeParcelable(this.foodPermission, i);
    }

    public int hashCode() {
        int hashCode = ((super.hashCode() * 31) + this.foodType) * 31;
        NutritionalValues nutritionalValues2 = this.nutritionalValues;
        int hashCode2 = (((((((((((((((hashCode + (nutritionalValues2 != null ? nutritionalValues2.hashCode() : 0)) * 31) + Float.floatToIntBits(this.grams)) * 31) + Strings.toString(this.brand).hashCode()) * 31) + Strings.toString(this.barcode).hashCode()) * 31) + Arrays.hashCode(this.foodPortions)) * 31) + Strings.toString(this.cachedCompleteDescription).hashCode()) * 31) + this.sortOrder) * 31) + (this.verified ? 1 : 0)) * 31;
        long j = this.promotedFromMasterId;
        return ((hashCode2 + ((int) (j ^ (j >>> 32)))) * 31) + Strings.toString(this.promotedFromUid).hashCode();
    }

    public boolean equals(Object obj) {
        return equalsWithIds(obj);
    }

    private boolean equalsWithIds(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Food)) {
            return false;
        }
        Food food = (Food) obj;
        if (!super.equals(obj) || !equalsIgnoreId(obj) || this.promotedFromMasterId != food.promotedFromMasterId || !Strings.equals(this.promotedFromUid, food.promotedFromUid)) {
            z = false;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        if (r4.verified == r1.verified) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equalsIgnoreId(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.myfitnesspal.shared.model.v1.Food
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            r1 = r5
            com.myfitnesspal.shared.model.v1.Food r1 = (com.myfitnesspal.shared.model.v1.Food) r1
            boolean r5 = super.equalsIgnoreId(r5)
            if (r5 == 0) goto L_0x0069
            int r5 = r4.foodType
            int r3 = r1.foodType
            if (r5 != r3) goto L_0x0069
            com.myfitnesspal.shared.model.v1.NutritionalValues r5 = r4.nutritionalValues
            if (r5 != 0) goto L_0x0022
            com.myfitnesspal.shared.model.v1.NutritionalValues r5 = r1.nutritionalValues
            if (r5 != 0) goto L_0x0069
            goto L_0x002a
        L_0x0022:
            com.myfitnesspal.shared.model.v1.NutritionalValues r3 = r1.nutritionalValues
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x0069
        L_0x002a:
            float r5 = r4.grams
            float r3 = r1.grams
            int r5 = java.lang.Float.compare(r5, r3)
            if (r5 != 0) goto L_0x0069
            java.lang.String r5 = r4.brand
            java.lang.String r3 = r1.brand
            boolean r5 = com.uacf.core.util.Strings.equals(r5, r3)
            if (r5 == 0) goto L_0x0069
            java.lang.String r5 = r4.barcode
            java.lang.String r3 = r1.barcode
            boolean r5 = com.uacf.core.util.Strings.equals(r5, r3)
            if (r5 == 0) goto L_0x0069
            com.myfitnesspal.shared.model.v1.FoodPortion[] r5 = r4.foodPortions
            com.myfitnesspal.shared.model.v1.FoodPortion[] r3 = r1.foodPortions
            boolean r5 = java.util.Arrays.equals(r5, r3)
            if (r5 == 0) goto L_0x0069
            java.lang.String r5 = r4.cachedCompleteDescription
            java.lang.String r3 = r1.cachedCompleteDescription
            boolean r5 = com.uacf.core.util.Strings.equals(r5, r3)
            if (r5 == 0) goto L_0x0069
            int r5 = r4.sortOrder
            int r3 = r1.sortOrder
            if (r5 != r3) goto L_0x0069
            boolean r5 = r4.verified
            boolean r1 = r1.verified
            if (r5 != r1) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r0 = 0
        L_0x006a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.model.v1.Food.equalsIgnoreId(java.lang.Object):boolean");
    }
}
