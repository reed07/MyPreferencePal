package com.myfitnesspal.feature.nutrition.model;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.ArrayList;
import java.util.List;

public enum Nutrient {
    Energy(0, Key.Energy),
    Carbohydrates(9, com.myfitnesspal.shared.constants.Constants.Goals.Nutrient.CARBOHYDRATES),
    Protein(12, "protein"),
    Fat(1, "fat"),
    SaturatedFat(2, "saturated_fat"),
    PolyUnsaturatedFat(3, com.myfitnesspal.shared.constants.Constants.Goals.Nutrient.POLYUNSATURATED_FAT),
    MonoUnsaturatedFat(4, com.myfitnesspal.shared.constants.Constants.Goals.Nutrient.MONOUNSATURATED_FAT),
    TransFat(5, "trans_fat"),
    Fiber(10, com.myfitnesspal.shared.constants.Constants.Goals.Nutrient.FIBER),
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
        throw new IllegalArgumentException(IpcUtil.KEY_CODE);
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

    public String getNutrientNameString(Context context) {
        int i;
        switch (this) {
            case PolyUnsaturatedFat:
                i = R.string.polyunsaturated_fat;
                break;
            case Potassium:
                i = R.string.potassium;
                break;
            case VitaminA:
                i = R.string.vitamin_a;
                break;
            case VitaminC:
                i = R.string.vitamin_c;
                break;
            case Calcium:
                i = R.string.calcium;
                break;
            case Iron:
                i = R.string.iron;
                break;
            case Protein:
                i = R.string.protein;
                break;
            case Fat:
                i = R.string.fat;
                break;
            case SaturatedFat:
                i = R.string.saturated_fat;
                break;
            case MonoUnsaturatedFat:
                i = R.string.monounsaturated_fat;
                break;
            case TransFat:
                i = R.string.trans_fat;
                break;
            case Carbohydrates:
                i = R.string.macro_carbs;
                break;
            case Fiber:
                i = R.string.fiber;
                break;
            case Sugar:
                i = R.string.macro_sugar;
                break;
            case Sodium:
                i = R.string.sodium_nutrient;
                break;
            case Cholesterol:
                i = R.string.cholesterol_nutrient;
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("unknown nutrient specified ");
                sb.append(getApiKey());
                throw new IllegalArgumentException(sb.toString());
        }
        return context.getString(i);
    }

    public String getAbbreviatedUnitString(Context context) {
        switch (this) {
            case PolyUnsaturatedFat:
            case Protein:
            case Fat:
            case SaturatedFat:
            case MonoUnsaturatedFat:
            case TransFat:
            case Carbohydrates:
            case Fiber:
            case Sugar:
                return context.getString(R.string.gram_abbreviation);
            case Potassium:
            case Sodium:
            case Cholesterol:
                return context.getString(R.string.milligram_abbreviation);
            case VitaminA:
            case VitaminC:
            case Calcium:
            case Iron:
                return context.getString(R.string.percent_daily_value_abbreviation);
            default:
                throw new IllegalArgumentException("unexpected nutritionalValue");
        }
    }
}
