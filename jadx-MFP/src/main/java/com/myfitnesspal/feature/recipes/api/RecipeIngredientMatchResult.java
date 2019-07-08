package com.myfitnesspal.feature.recipes.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import java.util.ArrayList;
import java.util.List;

public class RecipeIngredientMatchResult extends ApiResponse<MfpIngredientItem> implements Parcelable {
    public static final Creator<RecipeIngredientMatchResult> CREATOR = new Creator<RecipeIngredientMatchResult>() {
        public RecipeIngredientMatchResult createFromParcel(Parcel parcel) {
            return new RecipeIngredientMatchResult(parcel);
        }

        public RecipeIngredientMatchResult[] newArray(int i) {
            return new RecipeIngredientMatchResult[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public RecipeIngredientMatchResult() {
    }

    protected RecipeIngredientMatchResult(Parcel parcel) {
        ArrayList arrayList;
        if (parcel.readByte() == 1) {
            arrayList = new ArrayList();
            parcel.readList(arrayList, MfpIngredientItem.class.getClassLoader());
        } else {
            arrayList = null;
        }
        setItems(arrayList);
    }

    public void writeToParcel(Parcel parcel, int i) {
        List items = getItems();
        if (items == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeList(items);
    }
}
