package com.myfitnesspal.shared.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class RichTextAttribute implements Parcelable {
    public static final Creator<RichTextAttribute> CREATOR = new Creator<RichTextAttribute>() {
        public RichTextAttribute createFromParcel(Parcel parcel) {
            return new RichTextAttribute(parcel);
        }

        public RichTextAttribute[] newArray(int i) {
            return new RichTextAttribute[i];
        }
    };
    public RichAttributeType attributeType;
    public String attributeValue;
    public int length;
    public int startOffset;

    public int describeContents() {
        return 0;
    }

    public RichTextAttribute() {
    }

    public RichTextAttribute(Parcel parcel) {
        this.startOffset = parcel.readInt();
        this.length = parcel.readInt();
        this.attributeValue = parcel.readString();
        this.attributeType = RichAttributeType.getType(parcel.readInt());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.startOffset);
        parcel.writeInt(this.length);
        parcel.writeString(this.attributeValue);
        parcel.writeInt(this.attributeType.getValue());
    }
}
