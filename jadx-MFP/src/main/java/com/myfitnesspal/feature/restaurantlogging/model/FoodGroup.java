package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;

public class FoodGroup extends ArrayList<MfpFood> implements MfpMenuItemMatchData {
    public static final Creator<FoodGroup> CREATOR = new Creator<FoodGroup>() {
        public FoodGroup createFromParcel(Parcel parcel) {
            return new FoodGroup(parcel);
        }

        public FoodGroup[] newArray(int i) {
            return new FoodGroup[i];
        }
    };
    private int selectedFoodIndex;

    public int describeContents() {
        return 0;
    }

    public FoodGroup() {
    }

    private FoodGroup(Parcel parcel) {
        super(ParcelableUtil.readList(parcel, MfpFood.class));
        this.selectedFoodIndex = parcel.readInt();
    }

    public int getSelectedFoodIndex() {
        return this.selectedFoodIndex;
    }

    public void setSelectedFoodIndex(int i) {
        this.selectedFoodIndex = i;
    }

    public MfpFood getSelectedFood() {
        return (MfpFood) get(this.selectedFoodIndex);
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelableUtil.writeList(parcel, this);
        parcel.writeInt(this.selectedFoodIndex);
    }
}
