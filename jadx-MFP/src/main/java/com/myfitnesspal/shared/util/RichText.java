package com.myfitnesspal.shared.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;

public class RichText implements Parcelable {
    public static final Creator<RichText> CREATOR = new Creator<RichText>() {
        public RichText createFromParcel(Parcel parcel) {
            return new RichText(parcel);
        }

        public RichText[] newArray(int i) {
            return new RichText[i];
        }
    };
    public ArrayList<RichTextAttribute> attributes;
    public String baseString;
    public int textColor;

    public int describeContents() {
        return 0;
    }

    public RichText() {
    }

    public RichText(Parcel parcel) {
        this.baseString = parcel.readString();
        this.textColor = parcel.readInt();
        this.attributes = new ArrayList<>();
        parcel.readTypedList(this.attributes, RichTextAttribute.CREATOR);
    }

    public RichText initWithString(String str) {
        this.baseString = str;
        this.attributes = new ArrayList<>();
        return this;
    }

    public static RichText richTextWithString(String str) {
        return new RichText().initWithString(str);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.baseString);
        parcel.writeInt(this.textColor);
        parcel.writeTypedList(this.attributes);
    }
}
