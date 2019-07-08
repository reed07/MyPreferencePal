package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MfpIngredient implements Parcelable {
    public static final Creator<MfpIngredient> CREATOR = new Creator<MfpIngredient>() {
        public MfpIngredient createFromParcel(Parcel parcel) {
            return new MfpIngredient(parcel);
        }

        public MfpIngredient[] newArray(int i) {
            return new MfpIngredient[i];
        }
    };
    @Expose
    private Double confidence = Double.valueOf(0.0d);
    @Expose
    private MfpFood food;
    @Expose
    private Integer localId;
    private String matchType;
    @Expose
    private MfpIngredientMeasurement measurement;
    @Expose
    private MfpNormalizedData normalizedData;
    @Expose
    private MfpNutritionalContents nutritionalContent;
    @Expose
    private int quantity = 1;
    @Expose
    private String rawText;
    @Expose
    private MfpServingSize servingSize;
    @Expose
    private Double servings = Double.valueOf(1.0d);
    @Expose
    private String text;
    @Expose
    private List<MfpWeightOption> weightOptions = new ArrayList();

    public static final class MatchTypes {
        public static final String NONE = null;
        public static final String SYSTEM_MATCHED = "system_matched";
        public static final String USER_ADDED = "user_added";
        public static final String USER_MATCHED = "user_matched";
        public static final String USER_MODIFIED = "user_modified";
    }

    public int describeContents() {
        return 0;
    }

    public MfpIngredient() {
    }

    public MfpIngredient(String str, Integer num) {
        this.text = str;
        this.localId = num;
    }

    public MfpIngredient(String str, MfpFood mfpFood) {
        this.rawText = str;
        this.food = mfpFood;
        this.servings = Double.valueOf(1.0d);
        this.servingSize = (MfpServingSize) mfpFood.getServingSizes().get(0);
    }

    public String getText() {
        if (Strings.notEmpty(this.text)) {
            return this.text.trim();
        }
        return null;
    }

    public void setText(String str) {
        this.text = str;
    }

    public Integer getLocalId() {
        return this.localId;
    }

    public void setLocalId(Integer num) {
        this.localId = num;
    }

    public MfpFood getFood() {
        return this.food;
    }

    public MfpFood getSanitizedFood() {
        ensureServingInformation();
        List servingSizes = this.food.getServingSizes();
        if (servingSizes == null || servingSizes.isEmpty()) {
            this.food.setServingSizes(Arrays.asList(new MfpServingSize[]{this.servingSize}));
        }
        return this.food;
    }

    public void setFood(MfpFood mfpFood) {
        this.food = mfpFood;
    }

    public String getRawText() {
        return this.rawText;
    }

    public void setRawText(String str) {
        this.rawText = str;
    }

    public MfpIngredientMeasurement getMeasurement() {
        return this.measurement;
    }

    public void setMeasurement(MfpIngredientMeasurement mfpIngredientMeasurement) {
        this.measurement = mfpIngredientMeasurement;
    }

    public MfpNormalizedData getNormalizedData() {
        return this.normalizedData;
    }

    public void setNormalizedData(MfpNormalizedData mfpNormalizedData) {
        this.normalizedData = mfpNormalizedData;
    }

    public MfpNutritionalContents getNutritionalContents() {
        return this.nutritionalContent;
    }

    public void setNutritionalContent(MfpNutritionalContents mfpNutritionalContents) {
        this.nutritionalContent = mfpNutritionalContents;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public Double getServings() {
        ensureServingInformation();
        return this.servings;
    }

    public void setServings(Double d) {
        this.servings = d;
        ensureServingInformation();
    }

    public MfpServingSize getServingSize() {
        ensureServingInformation();
        return this.servingSize;
    }

    public void setServingSize(MfpServingSize mfpServingSize) {
        this.servingSize = mfpServingSize;
        ensureServingInformation();
    }

    public String getMatchType() {
        return this.matchType;
    }

    public void setMatchType(String str) {
        this.matchType = str;
    }

    private void ensureServingInformation() {
        if (this.servingSize == null) {
            if (CollectionUtils.size((Collection<?>) this.weightOptions) == 0) {
                List servingSizes = this.food.getServingSizes();
                if (CollectionUtils.notEmpty((Collection<?>) servingSizes)) {
                    this.servingSize = (MfpServingSize) servingSizes.get(0);
                }
            } else {
                MfpWeightOption mfpWeightOption = (MfpWeightOption) getWeightOptions().get(0);
                this.servingSize = mfpWeightOption.getServingSize();
                this.servings = mfpWeightOption.getServings();
            }
        }
        if (this.servings == null) {
            this.servings = Double.valueOf(1.0d);
        }
        ensureFoodServingInfo();
    }

    private void ensureFoodServingInfo() {
        if (CollectionUtils.isEmpty((Collection<?>) this.food.getServingSizes())) {
            this.food.setServingSizes(Collections.singletonList(this.servingSize));
            this.food.setSelectedServingAmount(this.servings.floatValue());
        }
    }

    public double getCaloriesValue() {
        if (getServingSize() == null || getFood() == null || getFood().getNutritionalContents() == null || getFood().getNutritionalContents().getEnergy() == null) {
            return 0.0d;
        }
        return getServings().doubleValue() * getServingSize().getNutritionMultiplier().doubleValue() * ((double) getFood().getNutritionalContents().getEnergy().getCaloriesValue());
    }

    protected MfpIngredient(Parcel parcel) {
        this.text = parcel.readString();
        Double d = null;
        this.localId = parcel.readByte() == 0 ? null : Integer.valueOf(parcel.readInt());
        this.food = (MfpFood) parcel.readValue(MfpFood.class.getClassLoader());
        this.rawText = parcel.readString();
        this.normalizedData = (MfpNormalizedData) parcel.readValue(MfpNormalizedData.class.getClassLoader());
        this.measurement = (MfpIngredientMeasurement) parcel.readValue(MfpIngredientMeasurement.class.getClassLoader());
        this.nutritionalContent = (MfpNutritionalContents) parcel.readValue(MfpNutritionalContents.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.weightOptions = new ArrayList();
            parcel.readList(this.weightOptions, MfpWeightOption.class.getClassLoader());
        } else {
            this.weightOptions = null;
        }
        this.confidence = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.quantity = parcel.readInt();
        if (parcel.readByte() != 0) {
            d = Double.valueOf(parcel.readDouble());
        }
        this.servings = d;
        this.servingSize = (MfpServingSize) parcel.readValue(MfpServingSize.class.getClassLoader());
        this.matchType = parcel.readString();
    }

    public List<MfpWeightOption> getWeightOptions() {
        return this.weightOptions;
    }

    public void setWeightOptions(List<MfpWeightOption> list) {
        this.weightOptions = list;
    }

    public Double getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Double d) {
        this.confidence = d;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MfpIngredient mfpIngredient = (MfpIngredient) obj;
        if (this.quantity != mfpIngredient.quantity) {
            return false;
        }
        String str = this.text;
        if (str == null ? mfpIngredient.text != null : !str.equals(mfpIngredient.text)) {
            return false;
        }
        Integer num = this.localId;
        if (num == null ? mfpIngredient.localId != null : !num.equals(mfpIngredient.localId)) {
            return false;
        }
        MfpFood mfpFood = this.food;
        if (mfpFood == null ? mfpIngredient.food != null : !mfpFood.equals(mfpIngredient.food)) {
            return false;
        }
        String str2 = this.rawText;
        if (str2 == null ? mfpIngredient.rawText != null : !str2.equals(mfpIngredient.rawText)) {
            return false;
        }
        MfpNormalizedData mfpNormalizedData = this.normalizedData;
        if (mfpNormalizedData == null ? mfpIngredient.normalizedData != null : !mfpNormalizedData.equals(mfpIngredient.normalizedData)) {
            return false;
        }
        MfpIngredientMeasurement mfpIngredientMeasurement = this.measurement;
        if (mfpIngredientMeasurement == null ? mfpIngredient.measurement != null : !mfpIngredientMeasurement.equals(mfpIngredient.measurement)) {
            return false;
        }
        MfpNutritionalContents mfpNutritionalContents = this.nutritionalContent;
        if (mfpNutritionalContents == null ? mfpIngredient.nutritionalContent != null : !mfpNutritionalContents.equals(mfpIngredient.nutritionalContent)) {
            return false;
        }
        List<MfpWeightOption> list = this.weightOptions;
        if (list == null ? mfpIngredient.weightOptions != null : !list.equals(mfpIngredient.weightOptions)) {
            return false;
        }
        Double d = this.confidence;
        if (d == null ? mfpIngredient.confidence != null : !d.equals(mfpIngredient.confidence)) {
            return false;
        }
        Double d2 = this.servings;
        if (d2 == null ? mfpIngredient.servings != null : !d2.equals(mfpIngredient.servings)) {
            return false;
        }
        MfpServingSize mfpServingSize = this.servingSize;
        if (mfpServingSize == null ? mfpIngredient.servingSize != null : !mfpServingSize.equals(mfpIngredient.servingSize)) {
            return false;
        }
        String str3 = this.matchType;
        if (str3 != null) {
            z = str3.equals(mfpIngredient.matchType);
        } else if (mfpIngredient.matchType != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.text;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Integer num = this.localId;
        int hashCode2 = (hashCode + (num != null ? num.hashCode() : 0)) * 31;
        MfpFood mfpFood = this.food;
        int hashCode3 = (hashCode2 + (mfpFood != null ? mfpFood.hashCode() : 0)) * 31;
        String str2 = this.rawText;
        int hashCode4 = (hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        MfpNormalizedData mfpNormalizedData = this.normalizedData;
        int hashCode5 = (hashCode4 + (mfpNormalizedData != null ? mfpNormalizedData.hashCode() : 0)) * 31;
        MfpIngredientMeasurement mfpIngredientMeasurement = this.measurement;
        int hashCode6 = (hashCode5 + (mfpIngredientMeasurement != null ? mfpIngredientMeasurement.hashCode() : 0)) * 31;
        MfpNutritionalContents mfpNutritionalContents = this.nutritionalContent;
        int hashCode7 = (hashCode6 + (mfpNutritionalContents != null ? mfpNutritionalContents.hashCode() : 0)) * 31;
        List<MfpWeightOption> list = this.weightOptions;
        int hashCode8 = (hashCode7 + (list != null ? list.hashCode() : 0)) * 31;
        Double d = this.confidence;
        int hashCode9 = (((hashCode8 + (d != null ? d.hashCode() : 0)) * 31) + this.quantity) * 31;
        Double d2 = this.servings;
        int hashCode10 = (hashCode9 + (d2 != null ? d2.hashCode() : 0)) * 31;
        MfpServingSize mfpServingSize = this.servingSize;
        int hashCode11 = (hashCode10 + (mfpServingSize != null ? mfpServingSize.hashCode() : 0)) * 31;
        String str3 = this.matchType;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode11 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
        if (this.localId == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeInt(this.localId.intValue());
        }
        parcel.writeValue(this.food);
        parcel.writeString(this.rawText);
        parcel.writeValue(this.normalizedData);
        parcel.writeValue(this.measurement);
        parcel.writeValue(this.nutritionalContent);
        if (this.weightOptions == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.weightOptions);
        }
        if (this.confidence == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.confidence.doubleValue());
        }
        parcel.writeInt(this.quantity);
        if (this.servings == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.servings.doubleValue());
        }
        parcel.writeValue(this.servingSize);
        parcel.writeString(this.matchType);
    }

    public static MfpIngredient fromFood(MfpFood mfpFood, MfpServingSize mfpServingSize, double d) {
        MfpIngredient mfpIngredient = new MfpIngredient();
        mfpIngredient.setFood(mfpFood);
        mfpIngredient.setRawText(mfpFood.getDescription());
        mfpIngredient.setServings(Double.valueOf(d));
        mfpIngredient.setServingSize(mfpServingSize);
        MfpWeightOption mfpWeightOption = new MfpWeightOption();
        mfpWeightOption.setConfidence(Double.valueOf(1.0d));
        mfpWeightOption.setServings(Double.valueOf(d));
        mfpWeightOption.setServingSize(mfpServingSize);
        mfpIngredient.setWeightOptions(Arrays.asList(new MfpWeightOption[]{mfpWeightOption}));
        return mfpIngredient;
    }

    public static MfpIngredient fromFood(MfpFood mfpFood) {
        return fromFood(mfpFood, mfpFood.getSelectedServingSize(), (double) mfpFood.getSelectedServingAmount());
    }

    public static MfpIngredient getFakeIngredient() {
        MfpIngredient mfpIngredient = new MfpIngredient();
        mfpIngredient.setText("0.4 lb(s) salmon fillets");
        mfpIngredient.setLocalId(Integer.valueOf(1));
        MfpNormalizedData mfpNormalizedData = new MfpNormalizedData();
        mfpNormalizedData.setIngredient("salmon fillets");
        mfpNormalizedData.setQuantity(Double.valueOf(0.4d));
        mfpNormalizedData.setWeight("lb(s)");
        mfpIngredient.setNormalizedData(mfpNormalizedData);
        MfpFood mfpFood = new MfpFood();
        mfpFood.setId("27931790232893");
        mfpFood.setBrandName("Great Value");
        mfpFood.setDescription("Battered Fish Fillets");
        mfpFood.setVersion("27931790232893");
        MfpNutritionalContents mfpNutritionalContents = new MfpNutritionalContents();
        mfpNutritionalContents.setCalories(Double.valueOf(190.0d));
        mfpNutritionalContents.setFat(Double.valueOf(14.0d));
        mfpNutritionalContents.setSaturatedFat(Double.valueOf(4.5d));
        mfpNutritionalContents.setPolyunsaturatedFat(Double.valueOf(6.0d));
        mfpNutritionalContents.setMonounsaturatedFat(Double.valueOf(2.5d));
        mfpNutritionalContents.setTransFat(Double.valueOf(0.0d));
        mfpNutritionalContents.setCholesterol(Double.valueOf(15.0d));
        mfpNutritionalContents.setSodium(Double.valueOf(480.0d));
        mfpNutritionalContents.setPotassium(Double.valueOf(95.0d));
        mfpNutritionalContents.setCarbohydrates(Double.valueOf(11.0d));
        mfpNutritionalContents.setFiber(Double.valueOf(1.0d));
        mfpNutritionalContents.setSugar(Double.valueOf(2.0d));
        mfpNutritionalContents.setProtein(Double.valueOf(6.0d));
        mfpNutritionalContents.setVitaminA(Double.valueOf(0.0d));
        mfpNutritionalContents.setVitaminC(Double.valueOf(0.0d));
        mfpNutritionalContents.setCalcium(Double.valueOf(2.0d));
        mfpNutritionalContents.setIron(Double.valueOf(2.0d));
        mfpFood.setNutritionalContents(mfpNutritionalContents);
        mfpIngredient.setFood(mfpFood);
        ArrayList arrayList = new ArrayList();
        MfpWeightOption mfpWeightOption = new MfpWeightOption();
        mfpWeightOption.setServings(Double.valueOf(0.004000000189989805d));
        MfpServingSize mfpServingSize = new MfpServingSize();
        mfpServingSize.setValue(Double.valueOf(1.0d));
        mfpServingSize.setUnit("container (8 fillets ea.)");
        mfpServingSize.setNutritionMultiplier(Double.valueOf(8.0d));
        mfpWeightOption.setServingSize(mfpServingSize);
        mfpWeightOption.setConfidence(Double.valueOf(1.0d));
        arrayList.add(mfpWeightOption);
        mfpIngredient.setWeightOptions(arrayList);
        mfpIngredient.setConfidence(Double.valueOf(1.0d));
        return mfpIngredient;
    }
}
