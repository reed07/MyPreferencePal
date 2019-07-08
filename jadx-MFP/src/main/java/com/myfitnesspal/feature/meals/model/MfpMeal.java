package com.myfitnesspal.feature.meals.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MfpMeal implements Parcelable {
    public static final Creator<MfpMeal> CREATOR = new Creator<MfpMeal>() {
        public MfpMeal createFromParcel(Parcel parcel) {
            return (MfpMeal) MfpMeal.MAPPER.withType(MfpMeal.class).tryMapFrom(parcel.readString());
        }

        public MfpMeal[] newArray(int i) {
            return new MfpMeal[i];
        }
    };
    /* access modifiers changed from: private */
    public static ApiJsonMapper MAPPER = new ApiJsonMapper();
    @Expose
    private String description;
    @Expose
    private String id;
    @Expose
    private MfpMealImage image;
    @Expose
    private List<MfpMealIngredient> ingredients;
    @SerializedName("public")
    @Expose
    private boolean isPublic;
    @Expose
    private MfpLegacyIds legacyIds;
    @Expose
    private MfpFoodNotes notes;
    @Expose
    private MfpNutritionalContents nutritionalContents;
    @Expose
    private Double score;
    @Expose
    private List<MfpServingSize> servingSizes;
    @Expose
    private MfpMealOwner user;
    @Expose
    private String userId;
    @Expose
    private String version;

    private static class MfpFoodNotes {
        /* access modifiers changed from: private */
        @Expose
        public String content;
        @Expose
        private String contentType;

        private MfpFoodNotes() {
        }
    }

    private static class MfpLegacyIds {
        /* access modifiers changed from: private */
        @Expose
        public long id;
        /* access modifiers changed from: private */
        @Expose
        public long userId;
        /* access modifiers changed from: private */
        @Expose
        public long version;

        private MfpLegacyIds() {
        }
    }

    private static class MfpMealImage {
        /* access modifiers changed from: private */
        @Expose
        public int height;
        /* access modifiers changed from: private */
        @Expose
        public String id;
        /* access modifiers changed from: private */
        @Expose
        public int width;

        private MfpMealImage() {
        }
    }

    private static class MfpMealOwner {
        /* access modifiers changed from: private */
        @Expose
        public String profilePictureUrl;
        /* access modifiers changed from: private */
        @Expose
        public String username;

        private MfpMealOwner() {
        }
    }

    public int describeContents() {
        return 0;
    }

    public MfpNutritionalContents getNutritionalContents() {
        return this.nutritionalContents;
    }

    public String getImageUid() {
        MfpMealImage mfpMealImage = this.image;
        return mfpMealImage != null ? mfpMealImage.id : "";
    }

    public int getImageWidth() {
        MfpMealImage mfpMealImage = this.image;
        if (mfpMealImage != null) {
            return mfpMealImage.width;
        }
        return 0;
    }

    public int getImageHeight() {
        MfpMealImage mfpMealImage = this.image;
        if (mfpMealImage != null) {
            return mfpMealImage.height;
        }
        return 0;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isPublic() {
        return this.isPublic;
    }

    public String getUserUid() {
        return this.userId;
    }

    public long getUserId() {
        MfpLegacyIds mfpLegacyIds = this.legacyIds;
        if (mfpLegacyIds != null) {
            return mfpLegacyIds.userId;
        }
        return 0;
    }

    public String getUsername() {
        MfpMealOwner mfpMealOwner = this.user;
        return mfpMealOwner == null ? "" : mfpMealOwner.username;
    }

    public String getUserProfileImage() {
        MfpMealOwner mfpMealOwner = this.user;
        if (mfpMealOwner == null) {
            return null;
        }
        return mfpMealOwner.profilePictureUrl;
    }

    public String getUid() {
        return this.id;
    }

    public long getOriginalMasterId() {
        MfpLegacyIds mfpLegacyIds = this.legacyIds;
        if (mfpLegacyIds != null) {
            return mfpLegacyIds.id;
        }
        return 0;
    }

    public String getVersion() {
        return this.version;
    }

    public long getMasterId() {
        MfpLegacyIds mfpLegacyIds = this.legacyIds;
        if (mfpLegacyIds != null) {
            return mfpLegacyIds.version;
        }
        return 0;
    }

    public List<MfpServingSize> getServingSizes() {
        return new ArrayList(this.servingSizes);
    }

    public List<MfpMealIngredient> getIngredients() {
        ArrayList arrayList = new ArrayList();
        for (MfpMealIngredient mfpMealIngredient : this.ingredients) {
            if (mfpMealIngredient.getFood() != null) {
                arrayList.add(mfpMealIngredient);
            }
        }
        return arrayList;
    }

    public Double getScore() {
        return this.score;
    }

    public String getNotes() {
        MfpFoodNotes mfpFoodNotes = this.notes;
        return mfpFoodNotes != null ? Strings.toString(mfpFoodNotes.content) : "";
    }

    public MealFood toMealFood(final User user2) {
        MealFood mealFood = new MealFood();
        mealFood.setDescription(this.description);
        mealFood.setMasterDatabaseId(this.legacyIds.version);
        mealFood.setOriginalMasterId(this.legacyIds.id);
        mealFood.setUid(this.version);
        mealFood.setOriginalUid(this.id);
        mealFood.setIsPublic(this.isPublic);
        mealFood.setFoodType(3);
        mealFood.setGrams(1.0f);
        List select = Enumerable.select((Collection<T>) getServingSizes(), (ReturningFunction1<U, T>) new ReturningFunction1<FoodPortion, MfpServingSize>() {
            public FoodPortion execute(MfpServingSize mfpServingSize) {
                FoodPortion foodPortion = new FoodPortion();
                foodPortion.setGramWeight(mfpServingSize.getNutritionMultiplier().floatValue());
                foodPortion.setDescription(mfpServingSize.getUnit());
                foodPortion.setAmount(mfpServingSize.getValue().floatValue());
                return foodPortion;
            }
        });
        mealFood.setFoodPortions((FoodPortion[]) select.toArray(new FoodPortion[select.size()]));
        mealFood.setNutritionalValues(NutritionalValues.fromNutritionalContents(this.nutritionalContents, 1.0f));
        mealFood.setIngredients(Enumerable.select((Collection<T>) getIngredients(), (ReturningFunction1<U, T>) new ReturningFunction1<MealIngredient, MfpMealIngredient>() {
            public MealIngredient execute(MfpMealIngredient mfpMealIngredient) {
                return mfpMealIngredient.toMealIngredient(user2);
            }
        }));
        return mealFood;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(MAPPER.reverseMap((Object) this));
    }
}
