package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatchData;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;
import java.util.List;

public class MfpRecipe extends MfpVersionedDatabaseObjectV2 implements MfpMenuItemMatchData {
    public static final Creator<MfpRecipe> CREATOR = new Creator<MfpRecipe>() {
        public MfpRecipe createFromParcel(Parcel parcel) {
            return new MfpRecipe(parcel);
        }

        public MfpRecipe[] newArray(int i) {
            return new MfpRecipe[i];
        }
    };
    @Expose
    private List<String> courses;
    @Expose
    private String directions;
    @Expose
    private String hash;
    @Expose
    private List<MfpIngredient> ingredients;
    @Expose
    private String introduction;
    @SerializedName("food")
    @Expose
    private MfpFood mfpFood;
    @Expose
    private String name;
    @Expose
    private MfpNutritionalContents nutritionalContents;
    @Expose
    private int prepTimeMinutes;
    @Expose
    private Double servings;
    @Expose
    private List<String> sourceImageUrls;
    @Expose
    private String sourceUrl;
    @Expose
    private String userId;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpRecipe> {
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getSyncResourceName() {
        return "recipe";
    }

    /* access modifiers changed from: protected */
    public void readV1Information(CursorMapper cursorMapper) {
    }

    /* access modifiers changed from: protected */
    public void writeV1Information(ContentValuesMapper contentValuesMapper) {
    }

    public MfpRecipe() {
        this.courses = new ArrayList();
        this.ingredients = new ArrayList();
        this.courses.add("Main Dishes");
    }

    private MfpRecipe(Parcel parcel) {
        super(parcel);
        this.courses = new ArrayList();
        this.ingredients = new ArrayList();
        this.userId = parcel.readString();
        this.prepTimeMinutes = parcel.readInt();
        this.name = parcel.readString();
        this.servings = ParcelableUtil.readDoubleObject(parcel);
        this.sourceUrl = parcel.readString();
        this.introduction = parcel.readString();
        this.directions = parcel.readString();
        this.courses = ParcelableUtil.readStringList(parcel);
        this.ingredients = ParcelableUtil.readList(parcel, MfpIngredient.class);
        this.hash = parcel.readString();
        this.sourceImageUrls = ParcelableUtil.readStringList(parcel);
        this.nutritionalContents = (MfpNutritionalContents) parcel.readParcelable(MfpNutritionalContents.class.getClassLoader());
        this.mfpFood = (MfpFood) parcel.readParcelable(MfpFood.class.getClassLoader());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Double getServings() {
        return this.servings;
    }

    public void setServings(Double d) {
        this.servings = d;
    }

    public String getSourceUrl() {
        return this.sourceUrl;
    }

    public void setSourceUrl(String str) {
        this.sourceUrl = str;
    }

    public String getIntroduction() {
        return this.introduction;
    }

    public void setIntroduction(String str) {
        this.introduction = str;
    }

    public String getDirections() {
        return this.directions;
    }

    public void setDirections(String str) {
        this.directions = str;
    }

    public List<String> getCourses() {
        return this.courses;
    }

    public void setCourses(List<String> list) {
        this.courses = list;
    }

    public List<MfpIngredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<MfpIngredient> list) {
        this.ingredients = list;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public int getPrepTimeMinutes() {
        return this.prepTimeMinutes;
    }

    public void setPrepTimeMinutes(int i) {
        this.prepTimeMinutes = i;
    }

    public List<String> getSourceImageUrls() {
        return this.sourceImageUrls;
    }

    public void setSourceImageUrls(List<String> list) {
        this.sourceImageUrls = list;
    }

    public MfpNutritionalContents getNutritionalContents() {
        return this.nutritionalContents;
    }

    public void setNutritionalContents(MfpNutritionalContents mfpNutritionalContents) {
        this.nutritionalContents = mfpNutritionalContents;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public MfpFood getMfpFood() {
        return this.mfpFood;
    }

    public void setMfpFood(MfpFood mfpFood2) {
        this.mfpFood = mfpFood2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.userId);
        parcel.writeInt(this.prepTimeMinutes);
        parcel.writeString(this.name);
        ParcelableUtil.writeDoubleObject(parcel, this.servings);
        parcel.writeString(this.sourceUrl);
        parcel.writeString(this.introduction);
        parcel.writeString(this.directions);
        ParcelableUtil.writeStringList(parcel, this.courses);
        ParcelableUtil.writeList(parcel, this.ingredients);
        parcel.writeString(this.hash);
        ParcelableUtil.writeStringList(parcel, this.sourceImageUrls);
        parcel.writeParcelable(this.nutritionalContents, i);
        parcel.writeParcelable(this.mfpFood, i);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        MfpRecipe mfpRecipe = (MfpRecipe) obj;
        if (this.prepTimeMinutes != mfpRecipe.prepTimeMinutes) {
            return false;
        }
        String str = this.userId;
        if (str == null ? mfpRecipe.userId != null : !str.equals(mfpRecipe.userId)) {
            return false;
        }
        List<String> list = this.sourceImageUrls;
        if (list == null ? mfpRecipe.sourceImageUrls != null : !list.equals(mfpRecipe.sourceImageUrls)) {
            return false;
        }
        MfpNutritionalContents mfpNutritionalContents = this.nutritionalContents;
        if (mfpNutritionalContents == null ? mfpRecipe.nutritionalContents != null : !mfpNutritionalContents.equals(mfpRecipe.nutritionalContents)) {
            return false;
        }
        String str2 = this.name;
        if (str2 == null ? mfpRecipe.name != null : !str2.equals(mfpRecipe.name)) {
            return false;
        }
        Double d = this.servings;
        if (d == null ? mfpRecipe.servings != null : !d.equals(mfpRecipe.servings)) {
            return false;
        }
        String str3 = this.sourceUrl;
        if (str3 == null ? mfpRecipe.sourceUrl != null : !str3.equals(mfpRecipe.sourceUrl)) {
            return false;
        }
        String str4 = this.introduction;
        if (str4 == null ? mfpRecipe.introduction != null : !str4.equals(mfpRecipe.introduction)) {
            return false;
        }
        String str5 = this.directions;
        if (str5 == null ? mfpRecipe.directions != null : !str5.equals(mfpRecipe.directions)) {
            return false;
        }
        List<String> list2 = this.courses;
        if (list2 == null ? mfpRecipe.courses != null : !list2.equals(mfpRecipe.courses)) {
            return false;
        }
        List<MfpIngredient> list3 = this.ingredients;
        if (list3 == null ? mfpRecipe.ingredients != null : !list3.equals(mfpRecipe.ingredients)) {
            return false;
        }
        String str6 = this.hash;
        if (str6 == null ? mfpRecipe.hash != null : !str6.equals(mfpRecipe.hash)) {
            return false;
        }
        MfpFood mfpFood2 = this.mfpFood;
        if (mfpFood2 != null) {
            z = mfpFood2.equals(mfpRecipe.mfpFood);
        } else if (mfpRecipe.mfpFood != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.userId;
        int i = 0;
        int hashCode2 = (((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.prepTimeMinutes) * 31;
        List<String> list = this.sourceImageUrls;
        int hashCode3 = (hashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        MfpNutritionalContents mfpNutritionalContents = this.nutritionalContents;
        int hashCode4 = (hashCode3 + (mfpNutritionalContents != null ? mfpNutritionalContents.hashCode() : 0)) * 31;
        String str2 = this.name;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Double d = this.servings;
        int hashCode6 = (hashCode5 + (d != null ? d.hashCode() : 0)) * 31;
        String str3 = this.sourceUrl;
        int hashCode7 = (hashCode6 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.introduction;
        int hashCode8 = (hashCode7 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.directions;
        int hashCode9 = (hashCode8 + (str5 != null ? str5.hashCode() : 0)) * 31;
        List<String> list2 = this.courses;
        int hashCode10 = (hashCode9 + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<MfpIngredient> list3 = this.ingredients;
        int hashCode11 = (hashCode10 + (list3 != null ? list3.hashCode() : 0)) * 31;
        String str6 = this.hash;
        int hashCode12 = (hashCode11 + (str6 != null ? str6.hashCode() : 0)) * 31;
        MfpFood mfpFood2 = this.mfpFood;
        if (mfpFood2 != null) {
            i = mfpFood2.hashCode();
        }
        return hashCode12 + i;
    }
}
