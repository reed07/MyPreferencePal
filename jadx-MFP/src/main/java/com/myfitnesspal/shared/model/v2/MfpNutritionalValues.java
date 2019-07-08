package com.myfitnesspal.shared.model.v2;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.annotations.Expose;

public class MfpNutritionalValues {
    @Expose
    private float calcium;
    @Expose
    private float carbohydrates;
    @Expose
    private float cholesterol;
    @Expose
    private MfpMeasuredValue energy;
    @Expose
    private float fat;
    @Expose
    private float fiber;
    @Expose
    private float iron;
    @Expose
    private float monounsaturatedFat;
    @Expose
    private float polyunsaturatedFat;
    @Expose
    private float potassium;
    @Expose
    private float protein;
    @Expose
    private float saturatedFat;
    @Expose
    private float sodium;
    @Expose
    private float sugar;
    @Expose
    private float transFat;
    @Expose
    private float vitaminA;
    @Expose
    private float vitaminC;

    public MfpNutritionalValues() {
    }

    public MfpNutritionalValues(float[] fArr) {
        setValues(fArr);
    }

    public float getCalcium() {
        return this.calcium;
    }

    public void setCalcium(float f) {
        this.calcium = f;
    }

    public float getCarbohydrates() {
        return this.carbohydrates;
    }

    public void setCarbohydrates(float f) {
        this.carbohydrates = f;
    }

    public float getCholesterol() {
        return this.cholesterol;
    }

    public void setCholesterol(float f) {
        this.cholesterol = f;
    }

    public MfpMeasuredValue getEnergy() {
        return this.energy;
    }

    public void setEnergy(MfpMeasuredValue mfpMeasuredValue) {
        this.energy = mfpMeasuredValue;
    }

    public float getFat() {
        return this.fat;
    }

    public void setFat(float f) {
        this.fat = f;
    }

    public float getFiber() {
        return this.fiber;
    }

    public void setFiber(float f) {
        this.fiber = f;
    }

    public float getIron() {
        return this.iron;
    }

    public void setIron(float f) {
        this.iron = f;
    }

    public float getMonounsaturatedFat() {
        return this.monounsaturatedFat;
    }

    public void setMonounsaturatedFat(float f) {
        this.monounsaturatedFat = f;
    }

    public float getPolyunsaturatedFat() {
        return this.polyunsaturatedFat;
    }

    public void setPolyunsaturatedFat(float f) {
        this.polyunsaturatedFat = f;
    }

    public float getPotassium() {
        return this.potassium;
    }

    public void setPotassium(float f) {
        this.potassium = f;
    }

    public float getProtein() {
        return this.protein;
    }

    public void setProtein(float f) {
        this.protein = f;
    }

    public float getSaturatedFat() {
        return this.saturatedFat;
    }

    public void setSaturatedFat(float f) {
        this.saturatedFat = f;
    }

    public float getSodium() {
        return this.sodium;
    }

    public void setSodium(float f) {
        this.sodium = f;
    }

    public float getSugar() {
        return this.sugar;
    }

    public void setSugar(float f) {
        this.sugar = f;
    }

    public float getTransFat() {
        return this.transFat;
    }

    public void setTransFat(float f) {
        this.transFat = f;
    }

    public float getVitaminA() {
        return this.vitaminA;
    }

    public void setVitaminA(float f) {
        this.vitaminA = f;
    }

    public float getVitaminC() {
        return this.vitaminC;
    }

    public void setVitaminC(float f) {
        this.vitaminC = f;
    }

    public void setValues(float[] fArr) {
        setEnergy(new MfpMeasuredValue("calories", Math.max(fArr[0], BitmapDescriptorFactory.HUE_RED)));
        setFat(Math.max(fArr[1], BitmapDescriptorFactory.HUE_RED));
        setSaturatedFat(Math.max(fArr[2], BitmapDescriptorFactory.HUE_RED));
        setPolyunsaturatedFat(Math.max(fArr[3], BitmapDescriptorFactory.HUE_RED));
        setMonounsaturatedFat(Math.max(fArr[4], BitmapDescriptorFactory.HUE_RED));
        setTransFat(Math.max(fArr[5], BitmapDescriptorFactory.HUE_RED));
        setCholesterol(Math.max(fArr[6], BitmapDescriptorFactory.HUE_RED));
        setSodium(Math.max(fArr[7], BitmapDescriptorFactory.HUE_RED));
        setPotassium(Math.max(fArr[8], BitmapDescriptorFactory.HUE_RED));
        setCarbohydrates(Math.max(fArr[9], BitmapDescriptorFactory.HUE_RED));
        setFiber(Math.max(fArr[10], BitmapDescriptorFactory.HUE_RED));
        setSugar(Math.max(fArr[11], BitmapDescriptorFactory.HUE_RED));
        setProtein(Math.max(fArr[12], BitmapDescriptorFactory.HUE_RED));
        setVitaminA(Math.max(fArr[13], BitmapDescriptorFactory.HUE_RED));
        setVitaminC(Math.max(fArr[14], BitmapDescriptorFactory.HUE_RED));
        setCalcium(Math.max(fArr[15], BitmapDescriptorFactory.HUE_RED));
        setIron(Math.max(fArr[16], BitmapDescriptorFactory.HUE_RED));
    }
}
