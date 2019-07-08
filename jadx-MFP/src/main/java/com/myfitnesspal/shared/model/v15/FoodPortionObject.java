package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class FoodPortionObject implements BinaryApiSerializable {
    public static final BinaryCreator<FoodPortionObject> BINARY_CREATOR = new BinaryCreator<FoodPortionObject>() {
        public FoodPortionObject create(BinaryDecoder binaryDecoder) {
            FoodPortionObject foodPortionObject = new FoodPortionObject();
            foodPortionObject.readData(binaryDecoder);
            return foodPortionObject;
        }
    };
    public static final Double DEFAULT_NUTRITION_MULTIPLIER = Double.valueOf(1.0d);
    private float amount;
    private String description;
    private float gramWeight;
    private boolean isFraction;
    private Double nutritionMultiplier = DEFAULT_NUTRITION_MULTIPLIER;

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    public float getGramWeight() {
        return this.gramWeight;
    }

    public void setGramWeight(float f) {
        this.gramWeight = f;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public boolean isFraction() {
        return this.isFraction;
    }

    public void setFraction(boolean z) {
        this.isFraction = z;
    }

    public Double getNutritionMultiplier() {
        return this.nutritionMultiplier;
    }

    public void setNutritionMultiplier(double d) {
        this.nutritionMultiplier = Double.valueOf(d);
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeFloat(this.amount);
        binaryEncoder.writeFloat(this.gramWeight);
        binaryEncoder.writeString(this.description);
        binaryEncoder.writeBoolean(this.isFraction);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.amount = binaryDecoder.decodeFloat();
        this.gramWeight = binaryDecoder.decodeFloat();
        this.description = binaryDecoder.decodeString();
        this.isFraction = binaryDecoder.decodeBoolean();
    }
}
