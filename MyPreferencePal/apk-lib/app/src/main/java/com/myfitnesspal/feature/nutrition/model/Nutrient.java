package com.myfitnesspal.feature.nutrition.model;

import android.content.Context;

import com.btothefifth.patched.R;

import java.util.ArrayList;
import java.util.List;

import lanchon.dexpatcher.annotation.DexAction;
import lanchon.dexpatcher.annotation.DexEdit;
import lanchon.dexpatcher.annotation.DexIgnore;

@DexIgnore
public enum Nutrient {
    Energy(0, "calories"),
    Carbohydrates(9, "carbohydrates"),
    Protein(12, "protein"),
    Fat(1, "fat"),
    SaturatedFat(2, "saturated_fat"),
    PolyUnsaturatedFat(3, "polyunsaturedfat"),
    MonoUnsaturatedFat(4, "monounsaturatedfat"),
    TransFat(5, "trans_fat"),
    Fiber(10, "fiber"),
    Sugar(11, "sugar"),
    Cholesterol(6, "cholesterol"),
    Sodium(7, "sodium"),
    Potassium(8, "potassium"),
    VitaminA(13, "vitamin_a"),
    VitaminC(14, "vitamin_c"),
    Calcium(15, "calcium"),
    Iron(16, "iron");

    private String apiKey;
    private int nutrientIndex;

    private Nutrient(int i, String str) {
        this.nutrientIndex = i;
        this.apiKey = str;
    }

    public int getNutrientIndex() {
        return this.nutrientIndex;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public static Nutrient forNutritionalIndex(int i) {
        Nutrient[] values;
        for (Nutrient nutrient : values()) {
            if (nutrient.getNutrientIndex() == i) {
                return nutrient;
            }
        }
        throw new IllegalArgumentException("index");
    }

    public static Nutrient fromApiKey(String str) {
        Nutrient[] values;
        for (Nutrient nutrient : values()) {
            if (str.equals(nutrient.getApiKey())) {
                return nutrient;
            }
        }
        throw new IllegalArgumentException();
    }

    public static List<Nutrient> fromApiKeys(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (String fromApiKey : list) {
                arrayList.add(fromApiKey(fromApiKey));
            }
        }
        return arrayList;
    }

    public static String[] toApiKeys(List<Nutrient> list) {
        String[] strArr = new String[list.size()];
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                strArr[i] = ((Nutrient) list.get(i)).getApiKey();
            }
        }
        return strArr;
    }

    @DexIgnore
    public String getNutrientNameString(Context context) {
       return "";
    }

    @DexIgnore
    public String getAbbreviatedUnitString(Context context) {
        return "";
    }
}
