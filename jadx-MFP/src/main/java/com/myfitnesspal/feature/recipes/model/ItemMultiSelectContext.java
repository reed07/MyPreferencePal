package com.myfitnesspal.feature.recipes.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ItemMultiSelectContext<T extends Parcelable> implements Parcelable {
    public static final Creator<ItemMultiSelectContext> CREATOR = new Creator<ItemMultiSelectContext>() {
        public ItemMultiSelectContext createFromParcel(Parcel parcel) {
            return new ItemMultiSelectContext(parcel);
        }

        public ItemMultiSelectContext[] newArray(int i) {
            return new ItemMultiSelectContext[i];
        }
    };
    private boolean isMultiSelectEnabled;
    private final Set<T> itemSet;

    public int describeContents() {
        return 0;
    }

    public ItemMultiSelectContext() {
        this.itemSet = new HashSet();
    }

    private ItemMultiSelectContext(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, Parcelable.class.getClassLoader());
        this.itemSet = new HashSet(arrayList);
        this.isMultiSelectEnabled = ParcelableUtil.readBoolean(parcel);
    }

    public Set<T> getItemSet() {
        return this.itemSet;
    }

    public boolean isMultiSelectEnabled() {
        return this.isMultiSelectEnabled;
    }

    public void setMultiSelectEnabled(boolean z) {
        this.isMultiSelectEnabled = z;
    }

    public void toggleSelectedState(T t) {
        if (this.itemSet.contains(t)) {
            this.itemSet.remove(t);
        } else {
            this.itemSet.add(t);
        }
    }

    public boolean hasItem(T t) {
        return this.itemSet.contains(t);
    }

    public int selectedCount() {
        return this.itemSet.size();
    }

    public void clear() {
        this.itemSet.clear();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(new ArrayList(this.itemSet));
        ParcelableUtil.writeBoolean(parcel, this.isMultiSelectEnabled);
    }
}
