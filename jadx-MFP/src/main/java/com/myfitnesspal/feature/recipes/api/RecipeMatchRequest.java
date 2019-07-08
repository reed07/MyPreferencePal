package com.myfitnesspal.feature.recipes.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecipeMatchRequest extends ApiRequest<MfpIngredient> implements Parcelable {
    public static final Creator<RecipeMatchRequest> CREATOR = new Creator<RecipeMatchRequest>() {
        public RecipeMatchRequest createFromParcel(Parcel parcel) {
            return new RecipeMatchRequest(parcel);
        }

        public RecipeMatchRequest[] newArray(int i) {
            return new RecipeMatchRequest[i];
        }
    };
    @Expose
    private String hash;

    public int describeContents() {
        return 0;
    }

    public RecipeMatchRequest() {
    }

    public RecipeMatchRequest(List<String> list) {
        this(list, null);
    }

    public RecipeMatchRequest(List<String> list, String str) {
        this.hash = str;
        setItems(Enumerable.select((Collection<T>) list, (ReturningFunction2<U, T, Integer>) new ReturningFunction2<MfpIngredient, String, Integer>() {
            public MfpIngredient execute(String str, Integer num) {
                return new MfpIngredient(str, num);
            }
        }));
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    protected RecipeMatchRequest(Parcel parcel) {
        ArrayList arrayList;
        if (parcel.readByte() == 1) {
            arrayList = new ArrayList();
            parcel.readList(arrayList, MfpIngredient.class.getClassLoader());
        } else {
            arrayList = null;
        }
        setItems(arrayList);
        this.hash = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        List items = getItems();
        if (items == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(items);
        }
        parcel.writeString(this.hash);
    }
}
