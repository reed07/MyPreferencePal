package com.samsung.android.sdk.internal.healthdata;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.samsung.android.sdk.healthdata.HealthDataResolver.AggregateRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.AggregateRequest.AggregateFunction;
import com.samsung.android.sdk.healthdata.HealthDataResolver.AggregateRequest.TimeGroupUnit;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import java.util.ArrayList;
import java.util.List;

public final class AggregateRequestImpl implements Parcelable, AggregateRequest {
    public static final Creator<AggregateRequestImpl> CREATOR = new Creator<AggregateRequestImpl>() {
        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new AggregateRequestImpl[i];
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new AggregateRequestImpl(parcel, 0);
        }
    };
    private final String a;
    private final String b;
    private final List<AggregatePair> c;
    private final List<Group> d;
    private final TimeGroup e;
    private final Filter f;
    private final List<String> g;
    private final String h;
    private final long i;
    private final long j;

    public static class AggregatePair implements Parcelable {
        public static final Creator<AggregatePair> CREATOR = new Creator<AggregatePair>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new AggregatePair[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new AggregatePair(parcel);
            }
        };
        private int a;
        private String b;
        private String c;

        public int describeContents() {
            return 0;
        }

        public AggregatePair(AggregateFunction aggregateFunction, String str, String str2) {
            if (aggregateFunction == null || str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
                throw new IllegalArgumentException("Insufficient arguments for aggregate function");
            }
            this.a = aggregateFunction.getValue();
            this.b = str;
            this.c = str2;
        }

        public int getAggNum() {
            return this.a;
        }

        public String getField() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeString(this.b);
            parcel.writeString(this.c);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(AggregateFunction.from(this.a).toSqlLiteral());
            sb.append('(');
            sb.append(this.b);
            sb.append(')');
            return sb.toString();
        }

        public String getAlias() {
            return this.c;
        }

        public AggregatePair(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readString();
            this.c = parcel.readString();
        }
    }

    public static class Group implements Parcelable {
        public static final Creator<Group> CREATOR = new Creator<Group>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Group[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new Group(parcel);
            }
        };
        private String a;
        private String b;

        public int describeContents() {
            return 0;
        }

        public Group(String str, String str2) {
            if (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) {
                throw new IllegalArgumentException("Insufficient arguments for group");
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

        public String getProperty() {
            return this.a;
        }

        public String getAlias() {
            return this.b;
        }

        public Group(Parcel parcel) {
            this.a = parcel.readString();
            this.b = parcel.readString();
        }
    }

    public static class TimeGroup implements Parcelable {
        public static final Creator<TimeGroup> CREATOR = new Creator<TimeGroup>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new TimeGroup[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new TimeGroup(parcel);
            }
        };
        private int a;
        private int b;
        private String c;
        private String d;
        private String e;

        public int describeContents() {
            return 0;
        }

        public TimeGroup(TimeGroupUnit timeGroupUnit, int i, String str, String str2, String str3) throws IllegalArgumentException {
            if (timeGroupUnit == null) {
                throw new IllegalArgumentException("Invalid timeUnit");
            } else if (i != 0) {
                switch (timeGroupUnit) {
                    case MINUTELY:
                        if (60 % i != 0) {
                            throw new IllegalArgumentException("Invalid amount");
                        }
                        break;
                    case HOURLY:
                        if (24 % i != 0) {
                            throw new IllegalArgumentException("Invalid amount");
                        }
                        break;
                    case DAILY:
                    case WEEKLY:
                        if (i != 1) {
                            throw new IllegalArgumentException("Invalid amount");
                        }
                        break;
                    case MONTHLY:
                        if (!(i == 1 || i == 3 || i == 6)) {
                            throw new IllegalArgumentException("Invalid amount");
                        }
                    default:
                        throw new IllegalArgumentException("Invalid timeUnit");
                }
                if (str == null || str.isEmpty() || str3 == null || str3.isEmpty()) {
                    throw new IllegalArgumentException("Insufficient arguments for time group");
                }
                this.a = timeGroupUnit.getValue();
                this.b = i;
                this.c = str;
                this.d = str2;
                this.e = str3;
            } else {
                throw new IllegalArgumentException("Invalid amount");
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }

        public String toString() {
            return TimeGroupUnit.from(this.a).toSqlLiteral(this.c, this.d, this.b);
        }

        public int getTimeUnit() {
            return this.a;
        }

        public int getAmount() {
            return this.b;
        }

        public String getTimeProperty() {
            return this.c;
        }

        public String getOffsetProperty() {
            return this.d;
        }

        public String getAlias() {
            return this.e;
        }

        public TimeGroup(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
        }
    }

    public final int describeContents() {
        return 0;
    }

    /* synthetic */ AggregateRequestImpl(Parcel parcel, byte b2) {
        this(parcel);
    }

    public AggregateRequestImpl(String str, String str2, List<AggregatePair> list, List<Group> list2, TimeGroup timeGroup, Filter filter, List<String> list3, String str3, long j2, long j3) {
        this.a = str;
        this.b = str2;
        this.c = list;
        this.d = list2;
        this.e = timeGroup;
        this.f = filter;
        this.g = list3;
        this.h = str3;
        this.i = j2;
        this.j = j3;
    }

    private AggregateRequestImpl(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.createTypedArrayList(AggregatePair.CREATOR);
        this.d = parcel.createTypedArrayList(Group.CREATOR);
        this.e = (TimeGroup) parcel.readParcelable(TimeGroup.class.getClassLoader());
        this.f = (Filter) parcel.readParcelable(Filter.class.getClassLoader());
        this.g = new ArrayList();
        parcel.readStringList(this.g);
        this.h = parcel.readString();
        this.i = parcel.readLong();
        this.j = parcel.readLong();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeTypedList(this.c);
        parcel.writeTypedList(this.d);
        parcel.writeParcelable(this.e, 0);
        parcel.writeParcelable(this.f, 0);
        parcel.writeStringList(this.g);
        parcel.writeString(this.h);
        parcel.writeLong(this.i);
        parcel.writeLong(this.j);
    }

    public final boolean isEmpty() {
        return this.f == null;
    }

    public final String getDataType() {
        return this.a;
    }

    public final String getPackageName() {
        return this.b;
    }

    public final List<AggregatePair> getAggregatePair() {
        return this.c;
    }

    public final List<Group> getGroups() {
        return this.d;
    }

    public final TimeGroup getTimeGroup() {
        return this.e;
    }

    public final long getStartTime() {
        return this.i;
    }

    public final long getEndTime() {
        return this.j;
    }

    public final String getSortOrder() {
        return this.h;
    }

    public final Filter getFilter() {
        return this.f;
    }

    public final List<String> getDeviceUuids() {
        return this.g;
    }
}
