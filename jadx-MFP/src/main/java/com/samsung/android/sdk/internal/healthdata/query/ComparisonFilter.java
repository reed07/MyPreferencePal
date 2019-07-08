package com.samsung.android.sdk.internal.healthdata.query;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter.ParcelType;

public class ComparisonFilter extends Filter {
    private Operator a;
    private String b;
    private Number c;

    public enum Operator implements Parcelable {
        ;
        
        public static final Creator<Operator> CREATOR = null;

        public int describeContents() {
            return 0;
        }

        public abstract String toQueryString();

        static {
            CREATOR = new Creator<Operator>() {
                public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                    return new Operator[i];
                }

                public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return Operator.values()[parcel.readInt()];
                }
            };
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public ComparisonFilter(Operator operator, String str, Number number) {
        this.mType = ParcelType.COMPARABLE;
        this.a = operator;
        this.b = str;
        this.c = number;
    }

    public ComparisonFilter(Parcel parcel) {
        readFromParcel(parcel);
    }

    /* access modifiers changed from: protected */
    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.a = (Operator) parcel.readParcelable(Operator.class.getClassLoader());
        this.b = parcel.readString();
        this.c = (Number) parcel.readSerializable();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, 0);
        parcel.writeString(this.b);
        parcel.writeSerializable(this.c);
    }

    public Operator getOperator() {
        return this.a;
    }

    public String getField() {
        return this.b;
    }

    public Number getValue() {
        return this.c;
    }
}
