package com.myfitnesspal.feature.recipes.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import java.util.ArrayList;
import java.util.List;

public class RecipeParseResult implements Parcelable {
    public static final Creator<RecipeParseResult> CREATOR = new Creator<RecipeParseResult>() {
        public RecipeParseResult createFromParcel(Parcel parcel) {
            return new RecipeParseResult(parcel);
        }

        public RecipeParseResult[] newArray(int i) {
            return new RecipeParseResult[i];
        }
    };
    @Expose
    private String hash;
    @Expose
    private List<String> ingredients = new ArrayList();
    @Expose
    private String name;
    @Expose
    private String pictureUrl;
    private List<MfpIngredientItem> scannedIngredients = new ArrayList();
    @Expose
    private double servings;
    @Expose
    private String url;

    public static class API_RESPONSE_MAPPER extends ApiResponse<RecipeParseResult> {
    }

    public int describeContents() {
        return 0;
    }

    public RecipeParseResult() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String str) {
        this.pictureUrl = str;
    }

    public double getServings() {
        return this.servings;
    }

    public void setServings(double d) {
        this.servings = d;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<String> list) {
        this.ingredients = list;
    }

    public List<MfpIngredientItem> getScannedIngredients() {
        return this.scannedIngredients;
    }

    public void setScannedIngredients(List<MfpIngredientItem> list) {
        this.scannedIngredients = list;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    protected RecipeParseResult(Parcel parcel) {
        this.name = parcel.readString();
        this.url = parcel.readString();
        this.pictureUrl = parcel.readString();
        this.servings = parcel.readDouble();
        if (parcel.readByte() == 1) {
            this.ingredients = new ArrayList();
            parcel.readList(this.ingredients, String.class.getClassLoader());
        } else {
            this.ingredients = null;
        }
        this.hash = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.url);
        parcel.writeString(this.pictureUrl);
        parcel.writeDouble(this.servings);
        if (this.ingredients == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.ingredients);
        }
        parcel.writeString(this.hash);
    }
}
