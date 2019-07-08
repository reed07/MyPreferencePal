package com.myfitnesspal.shared.model.v15;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.LogicUtils;
import com.uacf.core.util.Strings;
import java.util.List;

public abstract class FoodObject extends BaseObject {
    private String barcode;
    private String brand;
    private String description;
    private int flags;
    private FoodPermission foodPermission;
    private List<FoodPortionObject> foodPortions;
    private float grams;
    private float[] nutritionalValues;
    private long originalLocalId;
    private long originalMasterId;
    private String originalUid;
    private long ownerLocalUserId;
    private long ownerMasterUserId;
    private long promotedFromMasterId;
    private String promotedFromUid;
    private int type;
    private boolean verified;

    public static final class Flags {
        public static final int DELETED = 2;
        public static final int DONT_FORCE_CREATION = 4;
        public static final int PUBLIC = 1;
    }

    public static final class Types {
        public static final int FOOD = 0;
        public static final int MEAL = 1;
        public static final int RECIPE = 2;
    }

    public long getOriginalLocalId() {
        return this.originalLocalId;
    }

    public void setOriginalLocalId(long j) {
        this.originalLocalId = j;
    }

    public long getOwnerLocalUserId() {
        return this.ownerLocalUserId;
    }

    public void setOwnerLocalUserId(long j) {
        this.ownerLocalUserId = j;
    }

    public long getOriginalMasterId() {
        return this.originalMasterId;
    }

    public long getOwnerMasterUserId() {
        return this.ownerMasterUserId;
    }

    public String getOriginalUid() {
        return this.originalUid;
    }

    public void setOriginalUid(String str) {
        this.originalUid = str;
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

    public void setOwnerMasterUserId(long j) {
        this.ownerMasterUserId = j;
    }

    public void setOriginalMasterId(long j) {
        this.originalMasterId = j;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
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

    public boolean hasBrand() {
        return Strings.notEmpty(this.brand);
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public boolean isDeleted() {
        return LogicUtils.checkFlags((long) this.flags, 2);
    }

    public void setIsDeleted(boolean z) {
        this.flags = (int) (z ? LogicUtils.setFlags((long) this.flags, 2) : LogicUtils.clearFlags((long) this.flags, 2));
    }

    public boolean isPublic() {
        return LogicUtils.checkFlags((long) this.flags, 1);
    }

    public void setIsPublic(boolean z) {
        this.flags = (int) (z ? LogicUtils.setFlags((long) this.flags, 1) : LogicUtils.clearFlags((long) this.flags, 1));
    }

    public float[] getNutritionalValues() {
        return this.nutritionalValues;
    }

    public void setNutritionalValues(float[] fArr) {
        this.nutritionalValues = fArr;
    }

    public float getGrams() {
        return this.grams;
    }

    public void setGrams(float f) {
        this.grams = f;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public boolean isRecipe() {
        return this.type == 2;
    }

    public List<FoodPortionObject> getFoodPortions() {
        return this.foodPortions;
    }

    public void setFoodPortions(List<FoodPortionObject> list) {
        this.foodPortions = list;
    }

    public boolean isVerified() {
        return this.verified;
    }

    public void setVerified(boolean z) {
        this.verified = z;
    }

    public FoodPermission getFoodPermission() {
        return this.foodPermission;
    }

    public void setFoodPermission(FoodPermission foodPermission2) {
        this.foodPermission = foodPermission2;
    }

    public float nutrientMultiplierForFoodPortion(FoodPortionObject foodPortionObject) {
        if (foodPortionObject != null && Double.compare(foodPortionObject.getNutritionMultiplier().doubleValue(), FoodPortion.DEFAULT_NUTRITION_MULTIPLIER.doubleValue()) != 0) {
            return foodPortionObject.getNutritionMultiplier().floatValue();
        }
        float f = 1.0f;
        if (this.grams <= BitmapDescriptorFactory.HUE_RED) {
            return 1.0f;
        }
        if (foodPortionObject != null) {
            f = foodPortionObject.getGramWeight() / this.grams;
        }
        return f;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write4ByteInt(this.originalMasterId);
        binaryEncoder.writeString(this.originalUid);
        binaryEncoder.write8ByteInt(this.promotedFromMasterId);
        binaryEncoder.writeString(this.promotedFromUid);
        binaryEncoder.writeString(this.description);
        binaryEncoder.writeString(this.brand);
        binaryEncoder.writeString(this.barcode);
        binaryEncoder.write4ByteInt((long) this.flags);
        for (int i = 0; i < 20; i++) {
            binaryEncoder.writeFloat(this.nutritionalValues[i]);
        }
        binaryEncoder.writeFloat(this.grams);
        binaryEncoder.write2ByteInt(this.type);
        binaryEncoder.writeList(this.foodPortions);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.originalMasterId = binaryDecoder.decode4ByteInt();
        this.originalUid = binaryDecoder.decodeString();
        this.promotedFromMasterId = binaryDecoder.decode8ByteInt();
        this.promotedFromUid = binaryDecoder.decodeString();
        this.description = binaryDecoder.decodeString();
        this.brand = binaryDecoder.decodeString();
        this.barcode = binaryDecoder.decodeString();
        this.flags = (int) binaryDecoder.decode4ByteInt();
        this.nutritionalValues = new float[20];
        for (int i = 0; i < 20; i++) {
            this.nutritionalValues[i] = binaryDecoder.decodeFloat();
        }
        this.grams = binaryDecoder.decodeFloat();
        this.type = binaryDecoder.decode2ByteInt();
        this.foodPortions = binaryDecoder.decodeListWithTwoByteSize(FoodPortionObject.BINARY_CREATOR);
    }
}
