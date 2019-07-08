package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v1.Food;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import java.util.Collection;
import java.util.List;

public class MfpNutritionalContents implements Parcelable {
    public static final Creator<MfpNutritionalContents> CREATOR = new Creator<MfpNutritionalContents>() {
        public MfpNutritionalContents createFromParcel(Parcel parcel) {
            return new MfpNutritionalContents(parcel);
        }

        public MfpNutritionalContents[] newArray(int i) {
            return new MfpNutritionalContents[i];
        }
    };
    public static Double DEFAULT_VALUE = Double.valueOf(-1.0d);
    public static final MfpEnergy ZERO_ENERGY = new MfpEnergy(Double.valueOf(0.0d), "calories");
    @Expose
    private Double addedSugars;
    @Expose
    private Double calcium;
    @Expose
    private Double carbohydrates;
    @Expose
    private Double cholesterol;
    @Expose
    private MfpEnergy energy = ZERO_ENERGY;
    @Expose
    private Double fat;
    @Expose
    private Double fiber;
    @Expose
    private Double iron;
    @Expose
    private Double monounsaturatedFat;
    @Expose
    private Double polyunsaturatedFat;
    @Expose
    private Double potassium;
    @Expose
    private Double protein;
    @Expose
    private Double saturatedFat;
    @Expose
    private Double sodium;
    @Expose
    private Double sugar;
    @Expose
    private Double sugarAlcohols;
    @Expose
    private Double transFat;
    @Expose
    private Double vitaminA;
    @Expose
    private Double vitaminC;
    @Expose
    private Double vitaminD;

    public int describeContents() {
        return 0;
    }

    public MfpNutritionalContents() {
        Double d = DEFAULT_VALUE;
        this.fat = d;
        this.saturatedFat = d;
        this.polyunsaturatedFat = d;
        this.monounsaturatedFat = d;
        this.transFat = d;
        this.cholesterol = d;
        this.sodium = d;
        this.potassium = d;
        this.carbohydrates = d;
        this.fiber = d;
        this.sugar = d;
        this.addedSugars = d;
        this.sugarAlcohols = d;
        this.protein = d;
        this.vitaminA = d;
        this.vitaminC = d;
        this.vitaminD = d;
        this.calcium = d;
        this.iron = d;
    }

    public MfpEnergy getEnergy() {
        return this.energy;
    }

    public void setEnergy(MfpEnergy mfpEnergy) {
        if (mfpEnergy == null) {
            mfpEnergy = ZERO_ENERGY;
        }
        this.energy = mfpEnergy;
    }

    public Double getCalories() {
        MfpEnergy mfpEnergy = this.energy;
        return Double.valueOf(mfpEnergy != null ? (double) mfpEnergy.getCaloriesValue() : 0.0d);
    }

    public void setCalories(Double d) {
        this.energy = new MfpEnergy(d, "calories");
    }

    public Double getFat() {
        return this.fat;
    }

    public void setFat(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.fat = d;
    }

    public Double getSaturatedFat() {
        return this.saturatedFat;
    }

    public void setSaturatedFat(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.saturatedFat = d;
    }

    public Double getPolyunsaturatedFat() {
        return this.polyunsaturatedFat;
    }

    public void setPolyunsaturatedFat(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.polyunsaturatedFat = d;
    }

    public Double getMonounsaturatedFat() {
        return this.monounsaturatedFat;
    }

    public void setMonounsaturatedFat(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.monounsaturatedFat = d;
    }

    public Double getTransFat() {
        return this.transFat;
    }

    public void setTransFat(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.transFat = d;
    }

    public Double getCholesterol() {
        return this.cholesterol;
    }

    public void setCholesterol(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.cholesterol = d;
    }

    public Double getSodium() {
        return this.sodium;
    }

    public void setSodium(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.sodium = d;
    }

    public Double getPotassium() {
        return this.potassium;
    }

    public void setPotassium(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.potassium = d;
    }

    public Double getCarbohydrates() {
        return this.carbohydrates;
    }

    public void setCarbohydrates(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.carbohydrates = d;
    }

    public Double getFiber() {
        return this.fiber;
    }

    public void setFiber(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.fiber = d;
    }

    public Double getSugar() {
        return this.sugar;
    }

    public void setSugar(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.sugar = d;
    }

    public Double getProtein() {
        return this.protein;
    }

    public void setProtein(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.protein = d;
    }

    public Double getVitaminA() {
        return this.vitaminA;
    }

    public void setVitaminA(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.vitaminA = d;
    }

    public Double getVitaminC() {
        return this.vitaminC;
    }

    public void setVitaminC(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.vitaminC = d;
    }

    public Double getCalcium() {
        return this.calcium;
    }

    public void setCalcium(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.calcium = d;
    }

    public Double getIron() {
        return this.iron;
    }

    public void setIron(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.iron = d;
    }

    public Double getAddedSugars() {
        return this.addedSugars;
    }

    public void setAddedSugars(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.addedSugars = d;
    }

    public Double getVitaminD() {
        return this.vitaminD;
    }

    public void setVitaminD(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.vitaminD = d;
    }

    public Double getSugarAlcohols() {
        return this.sugarAlcohols;
    }

    public void setSugarAlcohols(Double d) {
        if (d.doubleValue() < 0.0d) {
            d = DEFAULT_VALUE;
        }
        this.sugarAlcohols = d;
    }

    protected MfpNutritionalContents(Parcel parcel) {
        Double d = DEFAULT_VALUE;
        this.fat = d;
        this.saturatedFat = d;
        this.polyunsaturatedFat = d;
        this.monounsaturatedFat = d;
        this.transFat = d;
        this.cholesterol = d;
        this.sodium = d;
        this.potassium = d;
        this.carbohydrates = d;
        this.fiber = d;
        this.sugar = d;
        this.addedSugars = d;
        this.sugarAlcohols = d;
        this.protein = d;
        this.vitaminA = d;
        this.vitaminC = d;
        this.vitaminD = d;
        this.calcium = d;
        this.iron = d;
        this.energy = (MfpEnergy) parcel.readValue(MfpEnergy.class.getClassLoader());
        Double d2 = null;
        this.fat = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.saturatedFat = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.polyunsaturatedFat = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.monounsaturatedFat = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.transFat = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.cholesterol = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.sodium = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.potassium = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.carbohydrates = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.fiber = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.sugar = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.addedSugars = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.sugarAlcohols = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.protein = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.vitaminA = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.vitaminC = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.vitaminD = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        this.calcium = parcel.readByte() == 0 ? null : Double.valueOf(parcel.readDouble());
        if (parcel.readByte() != 0) {
            d2 = Double.valueOf(parcel.readDouble());
        }
        this.iron = d2;
    }

    public static MfpNutritionalContents fromIngredientList(List<MfpIngredient> list) {
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        double d9;
        double d10;
        double d11;
        double d12;
        String str = "calories";
        double doubleValue = DEFAULT_VALUE.doubleValue();
        double doubleValue2 = DEFAULT_VALUE.doubleValue();
        double doubleValue3 = DEFAULT_VALUE.doubleValue();
        double doubleValue4 = DEFAULT_VALUE.doubleValue();
        double doubleValue5 = DEFAULT_VALUE.doubleValue();
        double doubleValue6 = DEFAULT_VALUE.doubleValue();
        double doubleValue7 = DEFAULT_VALUE.doubleValue();
        double doubleValue8 = DEFAULT_VALUE.doubleValue();
        double doubleValue9 = DEFAULT_VALUE.doubleValue();
        double doubleValue10 = DEFAULT_VALUE.doubleValue();
        double doubleValue11 = DEFAULT_VALUE.doubleValue();
        double doubleValue12 = DEFAULT_VALUE.doubleValue();
        double doubleValue13 = DEFAULT_VALUE.doubleValue();
        double doubleValue14 = DEFAULT_VALUE.doubleValue();
        double doubleValue15 = DEFAULT_VALUE.doubleValue();
        double doubleValue16 = DEFAULT_VALUE.doubleValue();
        double doubleValue17 = DEFAULT_VALUE.doubleValue();
        double doubleValue18 = DEFAULT_VALUE.doubleValue();
        double doubleValue19 = DEFAULT_VALUE.doubleValue();
        double doubleValue20 = DEFAULT_VALUE.doubleValue();
        if (list.size() > 0) {
            double d13 = doubleValue;
            str = ((MfpIngredient) list.get(0)).getFood().getNutritionalContents().getEnergy().getUnit();
            d2 = doubleValue5;
            double d14 = doubleValue9;
            d = doubleValue10;
            d3 = doubleValue11;
            d7 = doubleValue4;
            double d15 = 0.0d;
            d11 = d13;
            d4 = doubleValue8;
            d12 = doubleValue12;
            d6 = doubleValue6;
            d10 = doubleValue2;
            d9 = doubleValue18;
            d8 = doubleValue3;
            d5 = doubleValue7;
            for (MfpIngredient mfpIngredient : list) {
                MfpFood food = mfpIngredient.getFood();
                double doubleValue21 = mfpIngredient.getServings().doubleValue();
                MfpNutritionalContents nutritionalContents = mfpIngredient.getFood().getNutritionalContents();
                if (mfpIngredient.getServingSize() != null) {
                    d15 = mfpIngredient.getServingSize().getNutritionMultiplier().doubleValue();
                }
                if (d15 == 0.0d) {
                    d15 = getNutritiontalMultiplierFromFood(food);
                }
                double d16 = d15 * doubleValue21;
                MfpEnergy energy2 = nutritionalContents.getEnergy();
                double calculatedValue = setCalculatedValue(d11, d16, energy2 != null ? NumberUtils.getDoubleValue(energy2.getValue()) : 0.0d);
                double calculatedValue2 = setCalculatedValue(d10, d16, nutritionalContents.getFat().doubleValue());
                double calculatedValue3 = setCalculatedValue(d8, d16, nutritionalContents.getSaturatedFat().doubleValue());
                double d17 = d16;
                double calculatedValue4 = setCalculatedValue(d7, d17, nutritionalContents.getPolyunsaturatedFat().doubleValue());
                double calculatedValue5 = setCalculatedValue(d2, d16, nutritionalContents.getMonounsaturatedFat().doubleValue());
                double d18 = d16;
                d6 = setCalculatedValue(d6, d18, nutritionalContents.getTransFat().doubleValue());
                d5 = setCalculatedValue(d5, d18, nutritionalContents.getCholesterol().doubleValue());
                d4 = setCalculatedValue(d4, d18, nutritionalContents.getSodium().doubleValue());
                d14 = setCalculatedValue(d14, d18, nutritionalContents.getPotassium().doubleValue());
                d = setCalculatedValue(d, d18, nutritionalContents.getCarbohydrates().doubleValue());
                d3 = setCalculatedValue(d3, d16, nutritionalContents.getFiber().doubleValue());
                d12 = setCalculatedValue(d12, d16, nutritionalContents.getSugar().doubleValue());
                double d19 = d16;
                doubleValue13 = setCalculatedValue(doubleValue13, d19, nutritionalContents.getAddedSugars().doubleValue());
                doubleValue14 = setCalculatedValue(doubleValue14, d19, nutritionalContents.getSugarAlcohols().doubleValue());
                doubleValue15 = setCalculatedValue(doubleValue15, d19, nutritionalContents.getProtein().doubleValue());
                doubleValue16 = setCalculatedValue(doubleValue16, d19, nutritionalContents.getVitaminA().doubleValue());
                doubleValue17 = setCalculatedValue(doubleValue17, d16, nutritionalContents.getVitaminC().doubleValue());
                d9 = setCalculatedValue(d9, d16, nutritionalContents.getVitaminD().doubleValue());
                doubleValue19 = setCalculatedValue(doubleValue19, d17, nutritionalContents.getCalcium().doubleValue());
                doubleValue20 = setCalculatedValue(doubleValue20, d16, nutritionalContents.getIron().doubleValue());
                d15 = d16;
                d11 = calculatedValue;
                d10 = calculatedValue2;
                d8 = calculatedValue3;
                d7 = calculatedValue4;
                d2 = calculatedValue5;
            }
            doubleValue9 = d14;
        } else {
            d2 = doubleValue5;
            d = doubleValue10;
            d3 = doubleValue11;
            d7 = doubleValue4;
            d11 = doubleValue;
            d4 = doubleValue8;
            d12 = doubleValue12;
            d6 = doubleValue6;
            d10 = doubleValue2;
            d9 = doubleValue18;
            d8 = doubleValue3;
            d5 = doubleValue7;
        }
        MfpNutritionalContents mfpNutritionalContents = new MfpNutritionalContents();
        mfpNutritionalContents.setEnergy(new MfpEnergy(Double.valueOf(d11), str));
        mfpNutritionalContents.setFat(Double.valueOf(d10));
        mfpNutritionalContents.setSaturatedFat(Double.valueOf(d8));
        mfpNutritionalContents.setPolyunsaturatedFat(Double.valueOf(d7));
        mfpNutritionalContents.setMonounsaturatedFat(Double.valueOf(d2));
        mfpNutritionalContents.setTransFat(Double.valueOf(d6));
        mfpNutritionalContents.setCholesterol(Double.valueOf(d5));
        mfpNutritionalContents.setSodium(Double.valueOf(d4));
        mfpNutritionalContents.setPotassium(Double.valueOf(doubleValue9));
        mfpNutritionalContents.setCarbohydrates(Double.valueOf(d));
        mfpNutritionalContents.setFiber(Double.valueOf(d3));
        mfpNutritionalContents.setSugar(Double.valueOf(d12));
        mfpNutritionalContents.setAddedSugars(Double.valueOf(doubleValue13));
        mfpNutritionalContents.setSugar(Double.valueOf(doubleValue14));
        mfpNutritionalContents.setProtein(Double.valueOf(doubleValue15));
        mfpNutritionalContents.setVitaminA(Double.valueOf(doubleValue16));
        mfpNutritionalContents.setVitaminC(Double.valueOf(doubleValue17));
        mfpNutritionalContents.setVitaminD(Double.valueOf(d9));
        mfpNutritionalContents.setCalcium(Double.valueOf(doubleValue19));
        mfpNutritionalContents.setIron(Double.valueOf(doubleValue20));
        return mfpNutritionalContents;
    }

    private static double getNutritiontalMultiplierFromFood(MfpFood mfpFood) {
        List servingSizes = mfpFood.getServingSizes();
        if (CollectionUtils.notEmpty((Collection<?>) servingSizes)) {
            MfpServingSize mfpServingSize = (MfpServingSize) servingSizes.get(0);
            if (mfpServingSize != null) {
                return mfpServingSize.getNutritionMultiplier().doubleValue();
            }
        }
        return 0.0d;
    }

    public static MfpNutritionalContents fromFood(Food food) {
        return fromNutritionalValuesArray(food.getNutritionalValues().getValues());
    }

    public static MfpNutritionalContents fromNutritionalValuesArray(float[] fArr) {
        MfpNutritionalContents mfpNutritionalContents = new MfpNutritionalContents();
        if (fArr != null && fArr.length == 20) {
            mfpNutritionalContents.setCalories(Double.valueOf((double) fArr[0]));
            mfpNutritionalContents.setFat(Double.valueOf((double) fArr[1]));
            mfpNutritionalContents.setSaturatedFat(Double.valueOf((double) fArr[2]));
            mfpNutritionalContents.setPolyunsaturatedFat(Double.valueOf((double) fArr[3]));
            mfpNutritionalContents.setMonounsaturatedFat(Double.valueOf((double) fArr[4]));
            mfpNutritionalContents.setTransFat(Double.valueOf((double) fArr[5]));
            mfpNutritionalContents.setCholesterol(Double.valueOf((double) fArr[6]));
            mfpNutritionalContents.setSodium(Double.valueOf((double) fArr[7]));
            mfpNutritionalContents.setPotassium(Double.valueOf((double) fArr[8]));
            mfpNutritionalContents.setCarbohydrates(Double.valueOf((double) fArr[9]));
            mfpNutritionalContents.setFiber(Double.valueOf((double) fArr[10]));
            mfpNutritionalContents.setSugar(Double.valueOf((double) fArr[11]));
            mfpNutritionalContents.setAddedSugars(Double.valueOf((double) fArr[17]));
            mfpNutritionalContents.setSugarAlcohols(Double.valueOf((double) fArr[19]));
            mfpNutritionalContents.setProtein(Double.valueOf((double) fArr[12]));
            mfpNutritionalContents.setVitaminA(Double.valueOf((double) fArr[13]));
            mfpNutritionalContents.setVitaminC(Double.valueOf((double) fArr[14]));
            mfpNutritionalContents.setVitaminD(Double.valueOf((double) fArr[18]));
            mfpNutritionalContents.setCalcium(Double.valueOf((double) fArr[15]));
            mfpNutritionalContents.setIron(Double.valueOf((double) fArr[16]));
        }
        return mfpNutritionalContents;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MfpNutritionalContents mfpNutritionalContents = (MfpNutritionalContents) obj;
        MfpEnergy mfpEnergy = this.energy;
        if (mfpEnergy == null ? mfpNutritionalContents.energy != null : !mfpEnergy.equals(mfpNutritionalContents.energy)) {
            return false;
        }
        Double d = this.fat;
        if (d == null ? mfpNutritionalContents.fat != null : !d.equals(mfpNutritionalContents.fat)) {
            return false;
        }
        Double d2 = this.saturatedFat;
        if (d2 == null ? mfpNutritionalContents.saturatedFat != null : !d2.equals(mfpNutritionalContents.saturatedFat)) {
            return false;
        }
        Double d3 = this.polyunsaturatedFat;
        if (d3 == null ? mfpNutritionalContents.polyunsaturatedFat != null : !d3.equals(mfpNutritionalContents.polyunsaturatedFat)) {
            return false;
        }
        Double d4 = this.monounsaturatedFat;
        if (d4 == null ? mfpNutritionalContents.monounsaturatedFat != null : !d4.equals(mfpNutritionalContents.monounsaturatedFat)) {
            return false;
        }
        Double d5 = this.transFat;
        if (d5 == null ? mfpNutritionalContents.transFat != null : !d5.equals(mfpNutritionalContents.transFat)) {
            return false;
        }
        Double d6 = this.cholesterol;
        if (d6 == null ? mfpNutritionalContents.cholesterol != null : !d6.equals(mfpNutritionalContents.cholesterol)) {
            return false;
        }
        Double d7 = this.sodium;
        if (d7 == null ? mfpNutritionalContents.sodium != null : !d7.equals(mfpNutritionalContents.sodium)) {
            return false;
        }
        Double d8 = this.potassium;
        if (d8 == null ? mfpNutritionalContents.potassium != null : !d8.equals(mfpNutritionalContents.potassium)) {
            return false;
        }
        Double d9 = this.carbohydrates;
        if (d9 == null ? mfpNutritionalContents.carbohydrates != null : !d9.equals(mfpNutritionalContents.carbohydrates)) {
            return false;
        }
        Double d10 = this.fiber;
        if (d10 == null ? mfpNutritionalContents.fiber != null : !d10.equals(mfpNutritionalContents.fiber)) {
            return false;
        }
        Double d11 = this.sugar;
        if (d11 == null ? mfpNutritionalContents.sugar != null : !d11.equals(mfpNutritionalContents.sugar)) {
            return false;
        }
        Double d12 = this.addedSugars;
        if (d12 == null ? mfpNutritionalContents.addedSugars != null : !d12.equals(mfpNutritionalContents.addedSugars)) {
            return false;
        }
        Double d13 = this.sugarAlcohols;
        if (d13 == null ? mfpNutritionalContents.sugarAlcohols != null : !d13.equals(mfpNutritionalContents.sugarAlcohols)) {
            return false;
        }
        Double d14 = this.protein;
        if (d14 == null ? mfpNutritionalContents.protein != null : !d14.equals(mfpNutritionalContents.protein)) {
            return false;
        }
        Double d15 = this.vitaminA;
        if (d15 == null ? mfpNutritionalContents.vitaminA != null : !d15.equals(mfpNutritionalContents.vitaminA)) {
            return false;
        }
        Double d16 = this.vitaminC;
        if (d16 == null ? mfpNutritionalContents.vitaminC != null : !d16.equals(mfpNutritionalContents.vitaminC)) {
            return false;
        }
        Double d17 = this.vitaminD;
        if (d17 == null ? mfpNutritionalContents.vitaminD != null : !d17.equals(mfpNutritionalContents.vitaminD)) {
            return false;
        }
        Double d18 = this.calcium;
        if (d18 == null ? mfpNutritionalContents.calcium != null : !d18.equals(mfpNutritionalContents.calcium)) {
            return false;
        }
        Double d19 = this.iron;
        if (d19 != null) {
            z = d19.equals(mfpNutritionalContents.iron);
        } else if (mfpNutritionalContents.iron != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        MfpEnergy mfpEnergy = this.energy;
        int i = 0;
        int hashCode = (mfpEnergy != null ? mfpEnergy.hashCode() : 0) * 31;
        Double d = this.fat;
        int hashCode2 = (hashCode + (d != null ? d.hashCode() : 0)) * 31;
        Double d2 = this.saturatedFat;
        int hashCode3 = (hashCode2 + (d2 != null ? d2.hashCode() : 0)) * 31;
        Double d3 = this.polyunsaturatedFat;
        int hashCode4 = (hashCode3 + (d3 != null ? d3.hashCode() : 0)) * 31;
        Double d4 = this.monounsaturatedFat;
        int hashCode5 = (hashCode4 + (d4 != null ? d4.hashCode() : 0)) * 31;
        Double d5 = this.transFat;
        int hashCode6 = (hashCode5 + (d5 != null ? d5.hashCode() : 0)) * 31;
        Double d6 = this.cholesterol;
        int hashCode7 = (hashCode6 + (d6 != null ? d6.hashCode() : 0)) * 31;
        Double d7 = this.sodium;
        int hashCode8 = (hashCode7 + (d7 != null ? d7.hashCode() : 0)) * 31;
        Double d8 = this.potassium;
        int hashCode9 = (hashCode8 + (d8 != null ? d8.hashCode() : 0)) * 31;
        Double d9 = this.carbohydrates;
        int hashCode10 = (hashCode9 + (d9 != null ? d9.hashCode() : 0)) * 31;
        Double d10 = this.fiber;
        int hashCode11 = (hashCode10 + (d10 != null ? d10.hashCode() : 0)) * 31;
        Double d11 = this.sugar;
        int hashCode12 = (hashCode11 + (d11 != null ? d11.hashCode() : 0)) * 31;
        Double d12 = this.addedSugars;
        int hashCode13 = (hashCode12 + (d12 != null ? d12.hashCode() : 0)) * 31;
        Double d13 = this.sugarAlcohols;
        int hashCode14 = (hashCode13 + (d13 != null ? d13.hashCode() : 0)) * 31;
        Double d14 = this.protein;
        int hashCode15 = (hashCode14 + (d14 != null ? d14.hashCode() : 0)) * 31;
        Double d15 = this.vitaminA;
        int hashCode16 = (hashCode15 + (d15 != null ? d15.hashCode() : 0)) * 31;
        Double d16 = this.vitaminC;
        int hashCode17 = (hashCode16 + (d16 != null ? d16.hashCode() : 0)) * 31;
        Double d17 = this.vitaminD;
        int hashCode18 = (hashCode17 + (d17 != null ? d17.hashCode() : 0)) * 31;
        Double d18 = this.calcium;
        int hashCode19 = (hashCode18 + (d18 != null ? d18.hashCode() : 0)) * 31;
        Double d19 = this.iron;
        if (d19 != null) {
            i = d19.hashCode();
        }
        return hashCode19 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.energy);
        if (this.fat == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.fat.doubleValue());
        }
        if (this.saturatedFat == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.saturatedFat.doubleValue());
        }
        if (this.polyunsaturatedFat == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.polyunsaturatedFat.doubleValue());
        }
        if (this.monounsaturatedFat == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.monounsaturatedFat.doubleValue());
        }
        if (this.transFat == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.transFat.doubleValue());
        }
        if (this.cholesterol == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.cholesterol.doubleValue());
        }
        if (this.sodium == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.sodium.doubleValue());
        }
        if (this.potassium == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.potassium.doubleValue());
        }
        if (this.carbohydrates == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.carbohydrates.doubleValue());
        }
        if (this.fiber == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.fiber.doubleValue());
        }
        if (this.sugar == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.sugar.doubleValue());
        }
        if (this.addedSugars == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.addedSugars.doubleValue());
        }
        if (this.sugarAlcohols == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.sugarAlcohols.doubleValue());
        }
        if (this.protein == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.protein.doubleValue());
        }
        if (this.vitaminA == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.vitaminA.doubleValue());
        }
        if (this.vitaminC == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.vitaminC.doubleValue());
        }
        if (this.vitaminD == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.vitaminD.doubleValue());
        }
        if (this.calcium == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeDouble(this.calcium.doubleValue());
        }
        if (this.iron == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeDouble(this.iron.doubleValue());
    }

    private static double setCalculatedValue(double d, double d2, double d3) {
        if (d3 == DEFAULT_VALUE.doubleValue()) {
            return d;
        }
        double d4 = d2 * d3;
        if (d != DEFAULT_VALUE.doubleValue()) {
            d4 += d;
        }
        return d4;
    }
}
