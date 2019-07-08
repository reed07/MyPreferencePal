package com.samsung.android.sdk.internal.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadRequest;
import java.util.ArrayList;
import java.util.List;

public final class ReadRequestImpl implements Parcelable, ReadRequest {
    public static final Creator<ReadRequestImpl> CREATOR = new Creator<ReadRequestImpl>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new ReadRequestImpl[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new ReadRequestImpl(parcel);
        }
    };
    private final String a;
    private String b;
    private String c;
    private long d;
    private long e;
    private int f;
    private int g;
    private Filter h;
    private List<Projection> i = null;
    private List<String> j = null;
    private byte k;
    private long l;

    public static class Projection implements Parcelable {
        public static final Creator<Projection> CREATOR = new Creator<Projection>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Projection[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Projection(parcel);
            }
        };
        private String a;
        private String b;

        public int describeContents() {
            return 0;
        }

        public Projection(String str, String str2) {
            if (str == null || str.isEmpty()) {
                throw new IllegalArgumentException("Null or empty property for read request is not allowed");
            }
            this.a = str;
            this.b = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.a);
            parcel.writeString(this.b);
        }

        public String toString() {
            return this.a;
        }

        public void setAlias(String str) {
            this.b = str;
        }

        public String getProperty() {
            return this.a;
        }

        public String getAlias() {
            return this.b;
        }

        public Projection(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readString();
        }
    }

    public final int describeContents() {
        return 0;
    }

    public ReadRequestImpl(String str) {
        this.a = str;
    }

    public ReadRequestImpl(String str, String str2, Filter filter, List<Projection> list, List<String> list2, byte b2, String str3, long j2, long j3, int i2, int i3, long j4) {
        this.a = str;
        this.c = str2;
        this.b = str3;
        this.d = j2;
        this.e = j3;
        this.f = i2;
        this.g = i3;
        this.h = filter;
        this.i = list;
        this.j = list2;
        this.k = b2;
        this.l = j4;
    }

    public ReadRequestImpl(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readLong();
        this.e = parcel.readLong();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = (Filter) parcel.readParcelable(Filter.class.getClassLoader());
        this.i = parcel.createTypedArrayList(Projection.CREATOR);
        this.j = new ArrayList();
        parcel.readStringList(this.j);
        this.k = parcel.readByte();
        this.l = parcel.readLong();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeLong(this.d);
        parcel.writeLong(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeParcelable(this.h, 0);
        parcel.writeTypedList(this.i);
        parcel.writeStringList(this.j);
        parcel.writeByte(this.k);
        parcel.writeLong(this.l);
    }

    public final boolean isEmpty() {
        return this.h == null && TextUtils.isEmpty(this.b);
    }

    public final String getDataType() {
        return this.a;
    }

    public final String getPackageName() {
        return this.c;
    }

    public final long getStartTime() {
        return this.d;
    }

    public final long getEndTime() {
        return this.e;
    }

    public final int getOffset() {
        return this.f;
    }

    public final long getCount() {
        return (long) this.g;
    }

    public final String getSortOrder() {
        return this.b;
    }

    public final Filter getFilter() {
        return this.h;
    }

    public final List<Projection> getProjections() {
        return this.i;
    }

    public final List<String> getDeviceUuids() {
        return this.j;
    }

    public final byte isAliasOnly() {
        return this.k;
    }

    public final long getTimeAfter() {
        return this.l;
    }
}
