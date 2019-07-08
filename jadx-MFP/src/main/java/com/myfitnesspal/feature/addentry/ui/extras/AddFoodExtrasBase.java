package com.myfitnesspal.feature.addentry.ui.extras;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase;
import java.util.Date;

public class AddFoodExtrasBase<T extends AddFoodExtrasBase<T>> implements Parcelable {
    public static final Creator<AddFoodExtrasBase> CREATOR = new Creator<AddFoodExtrasBase>() {
        public AddFoodExtrasBase createFromParcel(Parcel parcel) {
            return new AddFoodExtrasBase(parcel);
        }

        public AddFoodExtrasBase[] newArray(int i) {
            return new AddFoodExtrasBase[i];
        }
    };
    private String barcode;
    private Date date;
    private boolean isMealFoodCreationFlow;
    private String mealName;
    private int position;
    private String query;
    private String requestId;
    private int searchVersion;
    private float servings;
    private String source;
    private String title;

    public int describeContents() {
        return 0;
    }

    public AddFoodExtrasBase() {
        this.servings = 1.0f;
        this.position = Integer.MIN_VALUE;
        this.source = "unknown";
        this.searchVersion = 1;
    }

    protected AddFoodExtrasBase(Parcel parcel) {
        this.servings = 1.0f;
        this.position = Integer.MIN_VALUE;
        this.source = "unknown";
        boolean z = true;
        this.searchVersion = 1;
        this.date = (Date) parcel.readSerializable();
        this.mealName = parcel.readString();
        this.title = parcel.readString();
        this.query = parcel.readString();
        this.servings = parcel.readFloat();
        this.position = parcel.readInt();
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.isMealFoodCreationFlow = z;
        this.source = parcel.readString();
        this.requestId = parcel.readString();
        this.searchVersion = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.date);
        parcel.writeString(this.mealName);
        parcel.writeString(this.title);
        parcel.writeString(this.query);
        parcel.writeFloat(this.servings);
        parcel.writeInt(this.position);
        parcel.writeByte(this.isMealFoodCreationFlow ? (byte) 1 : 0);
        parcel.writeString(this.source);
        parcel.writeString(this.requestId);
        parcel.writeInt(this.searchVersion);
    }

    public Date getDate() {
        return this.date;
    }

    public T setDate(Date date2) {
        this.date = date2;
        return this;
    }

    public String getMealName() {
        return this.mealName;
    }

    public T setMealName(String str) {
        this.mealName = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public T setTitle(String str) {
        this.title = str;
        return this;
    }

    public String getQuery() {
        return this.query;
    }

    public T setQuery(String str) {
        this.query = str;
        return this;
    }

    public float getServings() {
        return this.servings;
    }

    public T setServings(float f) {
        this.servings = f;
        return this;
    }

    public int getPosition() {
        return this.position;
    }

    public T setPosition(int i) {
        this.position = i;
        return this;
    }

    public boolean isMealFoodCreationFlow() {
        return this.isMealFoodCreationFlow;
    }

    public T setMealFoodCreationFlow(boolean z) {
        this.isMealFoodCreationFlow = z;
        return this;
    }

    public String getSource() {
        return this.source;
    }

    public T setSource(String str) {
        this.source = str;
        return this;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public AddFoodExtrasBase<T> setRequestId(String str) {
        this.requestId = str;
        return this;
    }

    public int getSearchVersion() {
        return this.searchVersion;
    }

    public AddFoodExtrasBase<T> setSearchVersion(int i) {
        this.searchVersion = i;
        return this;
    }
}
