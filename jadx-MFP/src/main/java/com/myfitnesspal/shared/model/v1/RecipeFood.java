package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class RecipeFood extends Food {
    public static final Creator<RecipeFood> CREATOR = new Creator<RecipeFood>() {
        public RecipeFood createFromParcel(Parcel parcel) {
            return new RecipeFood(parcel);
        }

        public RecipeFood[] newArray(int i) {
            return new RecipeFood[i];
        }
    };
    public static final String PROPERTY_SERVINGS = "servings";
    private static final int VALUE_FOR_NULL_PROPERTIES = -1;
    private ArrayList<RecipeIngredient> ingredients;
    private Map<String, List<String>> properties;

    public boolean isNormalFood() {
        return false;
    }

    public boolean isRecipe() {
        return true;
    }

    public int itemType() {
        return 11;
    }

    public RecipeFood() {
    }

    private RecipeFood(Parcel parcel) {
        super(parcel);
        this.ingredients = ParcelableUtil.readList(parcel, RecipeIngredient.class);
        this.properties = readPropertiesFromParcel(parcel);
    }

    public void setValue(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        this.properties.put(str2, arrayList);
    }

    public ArrayList<RecipeIngredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(ArrayList<RecipeIngredient> arrayList) {
        this.ingredients = arrayList;
    }

    public void setProperties(Map<String, List<String>> map) {
        this.properties = map;
    }

    public int totalPropertyCount() {
        int i = 0;
        for (String str : this.properties.keySet()) {
            i += ((List) this.properties.get(str)).size();
        }
        return i;
    }

    public float servings() {
        return NumberUtils.tryParseFloat(valueForProperty("servings"));
    }

    public void setServings(float f) {
        setValue(String.format(Locale.US, "%.2f", new Object[]{Float.valueOf(f)}), "servings");
    }

    private String valueForProperty(String str) {
        List valuesForProperty = valuesForProperty(str);
        if (valuesForProperty == null || valuesForProperty.size() <= 0) {
            return null;
        }
        return (String) valuesForProperty.get(0);
    }

    private List<String> valuesForProperty(String str) {
        return (List) this.properties.get(str);
    }

    public void addIngredient(RecipeIngredient recipeIngredient) {
        this.ingredients.add(recipeIngredient);
        recalculateNutritionalValues();
    }

    private void recalculateNutritionalValues() {
        NutritionalValues nutritionalValues = getNutritionalValues();
        nutritionalValues.resetToZero();
        Iterator it = this.ingredients.iterator();
        while (it.hasNext()) {
            RecipeIngredient recipeIngredient = (RecipeIngredient) it.next();
            Food food = recipeIngredient.getFood();
            nutritionalValues.addNutritionalValues(food.getNutritionalValues(), food.nutrientMultiplierForFoodPortion(recipeIngredient.getFoodPortion()) * recipeIngredient.getQuantity());
        }
    }

    public boolean isSharing() {
        String valueForProperty = valueForProperty("sharing");
        if (valueForProperty != null) {
            return valueForProperty.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        return false;
    }

    public void loadIngredientsAndPropertiesIfNeeded(@NonNull DbConnectionManager dbConnectionManager) {
        if (this.ingredients == null || this.properties == null) {
            setIngredients(dbConnectionManager.recipeIngredientsDBAdapter().recipeIngredientsForRecipeFood(this));
            setProperties(dbConnectionManager.recipePropertiesDBAdapter().recipePropertiesForRecipeFood(this));
        }
    }

    public Map<String, List<String>> getProperties() {
        return this.properties;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        ParcelableUtil.writeList(parcel, this.ingredients);
        writePropertiesToParcel(parcel);
    }

    private Map<String, List<String>> readPropertiesFromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt == -1) {
            return null;
        }
        HashMap hashMap = new HashMap(readInt);
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), ParcelableUtil.readStringList(parcel));
        }
        return hashMap;
    }

    private void writePropertiesToParcel(Parcel parcel) {
        Map<String, List<String>> map = this.properties;
        if (map == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(map.size());
        for (String str : this.properties.keySet()) {
            parcel.writeString(str);
            ParcelableUtil.writeStringList(parcel, (List) this.properties.get(str));
        }
    }
}
