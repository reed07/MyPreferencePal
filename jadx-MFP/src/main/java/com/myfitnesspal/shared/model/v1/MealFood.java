package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.FoodEditorItem;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

public class MealFood extends Food implements FoodEditorItem {
    public static final Creator<MealFood> CREATOR = new Creator<MealFood>() {
        public MealFood createFromParcel(Parcel parcel) {
            return new MealFood(parcel);
        }

        public MealFood[] newArray(int i) {
            return new MealFood[i];
        }
    };
    private List<MealIngredient> ingredients;

    private static class MealIngredientComparator implements Comparator<MealIngredient> {
        private MealIngredientComparator() {
        }

        public int compare(MealIngredient mealIngredient, MealIngredient mealIngredient2) {
            return Long.compare(mealIngredient.getIngredientFoodId(), mealIngredient2.getIngredientFoodId());
        }
    }

    public int describeContents() {
        return 0;
    }

    public boolean isMeal() {
        return true;
    }

    public boolean isNormalFood() {
        return false;
    }

    public int itemType() {
        return 3;
    }

    public void loadIngredientsIfNeeded(@Nonnull DbConnectionManager dbConnectionManager) {
        if (this.ingredients == null) {
            setIngredients(dbConnectionManager.mealIngredientsDbAdapter().mealIngredientsForMealFood(this));
        }
    }

    public MealFood() {
    }

    private MealFood(Parcel parcel) {
        super(parcel);
        this.ingredients = ParcelableUtil.readList(parcel, MealIngredient.class);
    }

    public void addIngredient(MealIngredient mealIngredient) {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList();
        }
        this.ingredients.add(mealIngredient);
    }

    public void setIngredients(List<MealIngredient> list) {
        this.ingredients = list;
    }

    public List<MealIngredient> getIngredients() {
        return CollectionUtils.notEmpty((Collection<?>) this.ingredients) ? this.ingredients : new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        ParcelableUtil.writeList(parcel, this.ingredients);
    }

    public boolean isAutoGeneratedMeal() {
        return this.localId == 0 && this.masterDatabaseId == 0;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        List<MealIngredient> list = this.ingredients;
        return hashCode + (list != null ? ((ArrayList) list).hashCode() : 0);
    }

    public boolean equals(Object obj) {
        return equalsWithIds(obj);
    }

    private boolean equalsWithIds(Object obj) {
        return super.equals(obj) && equalsIgnoreId(obj);
    }

    public boolean equalsIgnoreId(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MealFood)) {
            return false;
        }
        MealFood mealFood = (MealFood) obj;
        if (super.equalsIgnoreId(obj)) {
            List<MealIngredient> list = this.ingredients;
            if (list != null) {
            }
        }
        z = false;
        return z;
    }

    public boolean ingredientsAreSameAs(List<MealIngredient> list) {
        List ingredients2 = getIngredients();
        boolean z = true;
        if (list == null) {
            if (ingredients2 != null) {
                z = false;
            }
            return z;
        } else if (CollectionUtils.size((Collection<?>) list) != CollectionUtils.size((Collection<?>) ingredients2)) {
            return false;
        } else {
            ArrayList arrayList = new ArrayList(ingredients2);
            ArrayList arrayList2 = new ArrayList(list);
            MealIngredientComparator mealIngredientComparator = new MealIngredientComparator();
            Collections.sort(arrayList2, mealIngredientComparator);
            Collections.sort(arrayList, mealIngredientComparator);
            Iterator it = arrayList2.iterator();
            Iterator it2 = arrayList.iterator();
            while (it.hasNext() && it2.hasNext()) {
                if (!((MealIngredient) it.next()).equalsIgnoreId(it2.next())) {
                    return false;
                }
            }
            return true;
        }
    }
}
