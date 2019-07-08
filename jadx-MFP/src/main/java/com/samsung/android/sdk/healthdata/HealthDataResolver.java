package com.samsung.android.sdk.healthdata;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import com.samsung.android.sdk.healthdata.HealthResultHolder.BaseResult;
import com.samsung.android.sdk.internal.database.BulkCursorDescriptor;
import com.samsung.android.sdk.internal.database.BulkCursorToCursorAdaptor;
import com.samsung.android.sdk.internal.database.IBulkCursor;
import com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl;
import com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl.AggregatePair;
import com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl.Group;
import com.samsung.android.sdk.internal.healthdata.AggregateRequestImpl.TimeGroup;
import com.samsung.android.sdk.internal.healthdata.DeleteRequestImpl;
import com.samsung.android.sdk.internal.healthdata.ErrorUtil;
import com.samsung.android.sdk.internal.healthdata.HealthResultHolderImpl;
import com.samsung.android.sdk.internal.healthdata.HealthResultReceiver.ForwardAsync;
import com.samsung.android.sdk.internal.healthdata.InsertRequestImpl;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import com.samsung.android.sdk.internal.healthdata.ReadRequestImpl;
import com.samsung.android.sdk.internal.healthdata.ReadRequestImpl.Projection;
import com.samsung.android.sdk.internal.healthdata.UpdateRequestImpl;
import com.samsung.android.sdk.internal.healthdata.query.AndFilter;
import com.samsung.android.sdk.internal.healthdata.query.ComparisonFilter;
import com.samsung.android.sdk.internal.healthdata.query.ComparisonFilter.Operator;
import com.samsung.android.sdk.internal.healthdata.query.NotFilter;
import com.samsung.android.sdk.internal.healthdata.query.NumberArrayFilter;
import com.samsung.android.sdk.internal.healthdata.query.OrFilter;
import com.samsung.android.sdk.internal.healthdata.query.StringArrayFilter;
import com.samsung.android.sdk.internal.healthdata.query.StringFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HealthDataResolver {
    private final HealthDataStore a;
    private final Handler b;

    public interface AggregateRequest {

        public enum AggregateFunction {
            ;
            
            private final int a;

            public abstract String toSqlLiteral();

            private AggregateFunction(int i) {
                this.a = i;
            }

            public int getValue() {
                return this.a;
            }

            public static AggregateFunction from(int i) {
                if (i >= 0 && i <= 4) {
                    return values()[i];
                }
                StringBuilder sb = new StringBuilder("Invalid range : ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
        }

        public static class Builder {
            private final List<AggregatePair> a = new ArrayList();
            private String b = null;
            private final List<Group> c = new ArrayList();
            private String d = null;
            private TimeGroup e;
            private String f = null;
            private String g;
            private String h;
            private Filter i;
            private List<String> j;
            private String k;
            private SortOrder l;
            private long m = -1;
            private long n = -1;

            public Builder setDataType(String str) {
                this.g = str;
                return this;
            }

            public Builder setFilter(Filter filter) {
                this.i = filter;
                return this;
            }

            public Builder setPackageName(String str) {
                this.h = str;
                return this;
            }

            public Builder setSourceDevices(List<String> list) {
                this.j = list;
                return this;
            }

            public Builder setTimeGroup(TimeGroupUnit timeGroupUnit, int i2, String str, String str2) {
                try {
                    TimeGroup timeGroup = new TimeGroup(timeGroupUnit, i2, str, null, str2);
                    this.e = timeGroup;
                } catch (IllegalArgumentException e2) {
                    this.f = e2.getMessage();
                }
                return this;
            }

            public Builder setTimeGroup(TimeGroupUnit timeGroupUnit, int i2, String str, String str2, String str3) {
                try {
                    TimeGroup timeGroup = new TimeGroup(timeGroupUnit, i2, str, str2, str3);
                    this.e = timeGroup;
                } catch (IllegalArgumentException e2) {
                    this.f = e2.getMessage();
                }
                return this;
            }

            public Builder addGroup(String str, String str2) {
                try {
                    this.c.add(new Group(str, str2));
                } catch (IllegalArgumentException e2) {
                    this.d = e2.getMessage();
                }
                return this;
            }

            public Builder addFunction(AggregateFunction aggregateFunction, String str, String str2) {
                try {
                    this.a.add(new AggregatePair(aggregateFunction, str, str2));
                } catch (IllegalArgumentException e2) {
                    this.b = e2.getMessage();
                }
                return this;
            }

            public Builder setSort(String str, SortOrder sortOrder) {
                this.k = str;
                this.l = sortOrder;
                return this;
            }

            public AggregateRequest build() {
                String str;
                String str2 = this.g;
                if (str2 == null || "".equals(str2)) {
                    throw new IllegalStateException("No data type or invalid data type is specified");
                } else if (this.a.size() > 0) {
                    String str3 = this.b;
                    if (str3 == null) {
                        String str4 = this.f;
                        if (str4 == null) {
                            String str5 = this.d;
                            if (str5 == null) {
                                List<String> list = this.j;
                                if (list != null) {
                                    for (String str6 : list) {
                                        if (str6 == null) {
                                            throw new IllegalStateException("Null device uuid");
                                        }
                                    }
                                }
                                String str7 = this.k;
                                if (str7 == null) {
                                    str = null;
                                } else if (this.l != null) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(this.k);
                                    sb.append(" ");
                                    sb.append(this.l.toSqlLiteral());
                                    str = sb.toString();
                                } else {
                                    str = str7;
                                }
                                AggregateRequestImpl aggregateRequestImpl = new AggregateRequestImpl(this.g, this.h, this.a, this.c, this.e, this.i, this.j, str, this.m, this.n);
                                return aggregateRequestImpl;
                            }
                            throw new IllegalStateException(str5);
                        }
                        throw new IllegalStateException(str4);
                    }
                    throw new IllegalStateException(str3);
                } else {
                    throw new IllegalStateException("No aggregate function is included");
                }
            }
        }

        public enum TimeGroupUnit {
            ;
            
            private final int a;

            public abstract String toSqlLiteral(String str, String str2, int i);

            private TimeGroupUnit(int i) {
                this.a = i;
            }

            public int getValue() {
                return this.a;
            }

            public static TimeGroupUnit from(int i) {
                if (i >= 0 && i <= MONTHLY.getValue()) {
                    return values()[i];
                }
                StringBuilder sb = new StringBuilder("Invalid range : ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
        }
    }

    public static class AggregateResult extends BaseResult {
        public static final Creator<AggregateResult> CREATOR = new Creator<AggregateResult>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new AggregateResult[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new AggregateResult(parcel, 0);
            }
        };
        private final BulkCursorDescriptor a;
        private final String b;
        private boolean c;

        /* synthetic */ AggregateResult(Parcel parcel, byte b2) {
            this(parcel);
        }

        public AggregateResult(String str, int i, int i2) {
            super(i, i2);
            this.b = str;
            this.a = null;
        }

        public AggregateResult(String str, int i, BulkCursorDescriptor bulkCursorDescriptor) {
            super(i, bulkCursorDescriptor != null ? bulkCursorDescriptor.count : 0);
            this.b = str;
            this.a = bulkCursorDescriptor;
        }

        public AggregateResult(String str, BulkCursorDescriptor bulkCursorDescriptor) {
            super(bulkCursorDescriptor != null ? 1 : 4, bulkCursorDescriptor != null ? bulkCursorDescriptor.count : 0);
            this.b = str;
            this.a = bulkCursorDescriptor;
        }

        private AggregateResult(Parcel parcel) {
            super(parcel);
            this.b = parcel.readString();
            this.a = (BulkCursorDescriptor) parcel.readParcelable(BulkCursorDescriptor.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.b);
            parcel.writeParcelable(this.a, i);
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            BulkCursorDescriptor bulkCursorDescriptor = this.a;
            if (bulkCursorDescriptor != null && !this.c) {
                IBulkCursor iBulkCursor = bulkCursorDescriptor.cursor;
                if (iBulkCursor != null) {
                    iBulkCursor.close();
                }
            }
            super.finalize();
        }

        public Cursor getResultCursor() {
            if (this.a == null) {
                return null;
            }
            BulkCursorToCursorAdaptor bulkCursorToCursorAdaptor = new BulkCursorToCursorAdaptor();
            bulkCursorToCursorAdaptor.initialize(this.a);
            this.c = true;
            return bulkCursorToCursorAdaptor;
        }

        public String getDataType() {
            return this.b;
        }
    }

    public interface DeleteRequest {

        public static class Builder {
            private String a;
            private Filter b;
            private List<String> c;

            public Builder setDataType(String str) {
                this.a = str;
                return this;
            }

            public Builder setFilter(Filter filter) {
                this.b = filter;
                return this;
            }

            public Builder setSourceDevices(List<String> list) {
                this.c = list;
                return this;
            }

            public DeleteRequest build() {
                String str = this.a;
                if (str == null || "".equals(str)) {
                    throw new IllegalStateException("No data type or invalid data type is specified");
                }
                List<String> list = this.c;
                if (list != null) {
                    for (String str2 : list) {
                        if (str2 == null) {
                            throw new IllegalStateException("Null device uuid");
                        }
                    }
                }
                return new DeleteRequestImpl(this.a, this.b, this.c);
            }
        }
    }

    public static abstract class Filter implements Parcelable {
        public static final Creator<Filter> CREATOR = new Creator<Filter>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new Filter[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                ParcelType parcelType = (ParcelType) parcel.readParcelable(ParcelType.class.getClassLoader());
                parcel.setDataPosition(parcel.dataPosition());
                return parcelType.a(parcel);
            }
        };
        protected List<Filter> mFilters = new ArrayList();
        protected ParcelType mType;

        public enum ParcelType implements Parcelable {
            ;
            
            public static final Creator<ParcelType> CREATOR = null;

            /* access modifiers changed from: 0000 */
            public abstract Filter a(Parcel parcel);

            public int describeContents() {
                return 0;
            }

            static {
                CREATOR = new Creator<ParcelType>() {
                    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                        return new ParcelType[i];
                    }

                    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                        return ParcelType.values()[parcel.readInt()];
                    }
                };
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(ordinal());
            }
        }

        public int describeContents() {
            return 0;
        }

        protected Filter() {
        }

        protected Filter(Parcel parcel) {
            readFromParcel(parcel);
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mType, 0);
        }

        /* access modifiers changed from: protected */
        public void readFromParcel(Parcel parcel) {
            this.mType = (ParcelType) parcel.readParcelable(ParcelType.class.getClassLoader());
        }

        protected static void checkFilter(Filter filter) {
            if (filter == null) {
                throw new IllegalArgumentException("Invalid filter instance");
            }
        }

        public List<Filter> getFilters() {
            return this.mFilters;
        }

        public static Filter and(Filter filter, Filter... filterArr) {
            if (filter != null && filterArr != null) {
                return new AndFilter(filter, filterArr);
            }
            throw new IllegalArgumentException("Filter arguments for and method should not be null");
        }

        public static Filter or(Filter filter, Filter... filterArr) {
            if (filter != null && filterArr != null) {
                return new OrFilter(filter, filterArr);
            }
            throw new IllegalArgumentException("Filter arguments for or method should not be null");
        }

        public static Filter not(Filter filter) {
            return new NotFilter(filter);
        }

        public static <T> Filter eq(String str, T t) {
            if (str == null) {
                throw new IllegalArgumentException("Invalid property or value");
            } else if (t == null) {
                return new ComparisonFilter(Operator.EQ, str, null);
            } else {
                if (t instanceof Number) {
                    return new ComparisonFilter(Operator.EQ, str, (Number) t);
                }
                if (t instanceof String) {
                    return new StringFilter(str, (String) t);
                }
                throw new IllegalArgumentException("Invalid type of value");
            }
        }

        public static <T extends Comparable<T>> Filter lessThan(String str, T t) {
            if (str != null && (t instanceof Number)) {
                return new ComparisonFilter(Operator.LESS_THAN, str, (Number) t);
            }
            throw new IllegalArgumentException("Invalid property or value");
        }

        public static <T extends Comparable<T>> Filter lessThanEquals(String str, T t) {
            if (str != null && (t instanceof Number)) {
                return new ComparisonFilter(Operator.LESS_THAN_EQUALS, str, (Number) t);
            }
            throw new IllegalArgumentException("Invalid property or value");
        }

        public static <T extends Comparable<T>> Filter greaterThan(String str, T t) {
            if (str != null && (t instanceof Number)) {
                return new ComparisonFilter(Operator.GREATER_THAN, str, (Number) t);
            }
            throw new IllegalArgumentException("Invalid property or value");
        }

        public static <T extends Comparable<T>> Filter greaterThanEquals(String str, T t) {
            if (str != null && (t instanceof Number)) {
                return new ComparisonFilter(Operator.GREATER_THAN_EQUALS, str, (Number) t);
            }
            throw new IllegalArgumentException("Invalid property or value");
        }

        public static <T> Filter in(String str, T[] tArr) {
            if (TextUtils.isEmpty(str) || tArr == null || tArr.length == 0) {
                throw new IllegalArgumentException("Invalid property or values");
            } else if (tArr instanceof Number[]) {
                return new NumberArrayFilter(str, (Number[]) tArr);
            } else {
                if (tArr instanceof String[]) {
                    return new StringArrayFilter(str, (String[]) tArr);
                }
                throw new IllegalArgumentException("Invalid type of value");
            }
        }
    }

    public interface InsertRequest {

        public static class Builder {
            private String a;

            public Builder setDataType(String str) {
                this.a = str;
                return this;
            }

            public InsertRequest build() {
                String str = this.a;
                if (str != null && !"".equals(str)) {
                    return new InsertRequestImpl(this.a);
                }
                throw new IllegalStateException("No data type or invalid data type is specified");
            }
        }

        void addHealthData(HealthData healthData);

        void addHealthData(List<HealthData> list);
    }

    public interface ReadRequest {

        public static class Builder {
            private String a;
            private String b;
            private Filter c;
            private String d;
            private SortOrder e;
            private List<String> f;
            private long g = -1;
            private long h = -1;
            private int i = 0;
            private int j = -1;
            private int k = 0;
            private String[] l;
            private final List<Projection> m = new ArrayList();
            private String n = null;
            private String o = null;
            private long p = -1;
            private boolean q = false;
            private boolean r = false;

            public Builder setDataType(String str) {
                this.a = str;
                return this;
            }

            public Builder setFilter(Filter filter) {
                this.c = filter;
                return this;
            }

            public Builder setSourceDevices(List<String> list) {
                this.f = list;
                return this;
            }

            public Builder setProperties(String[] strArr) {
                if (strArr != null) {
                    this.l = new String[strArr.length];
                    int i2 = 0;
                    while (true) {
                        if (i2 >= strArr.length) {
                            break;
                        }
                        String str = strArr[i2];
                        if (str == null || str.isEmpty()) {
                            this.n = "Null or empty property for read request is not allowed";
                        } else {
                            this.l[i2] = str;
                            i2++;
                        }
                    }
                } else {
                    this.l = null;
                }
                return this;
            }

            public Builder setPropertyAlias(String str, String str2) {
                try {
                    this.m.add(new Projection(str, str2));
                } catch (IllegalArgumentException e2) {
                    this.o = e2.getMessage();
                }
                return this;
            }

            public Builder setPackageName(String str) {
                this.b = str;
                return this;
            }

            public Builder setSort(String str, SortOrder sortOrder) {
                this.d = str;
                this.e = sortOrder;
                return this;
            }

            public Builder setResultCount(int i2, int i3) {
                this.i = i2;
                this.j = i3;
                this.k = 1;
                return this;
            }

            public Builder setTimeAfter(long j2) {
                this.p = j2;
                this.q = true;
                return this;
            }

            public Builder setTimeBefore(long j2) {
                this.h = j2;
                this.r = true;
                return this;
            }

            public ReadRequest build() {
                String str;
                String[] strArr;
                if (this.q && this.p < 0) {
                    throw new IllegalStateException("Illegal setTimeAfter value is specified");
                } else if (!this.r || this.h >= 0) {
                    String str2 = this.a;
                    if (str2 == null || "".equals(str2)) {
                        throw new IllegalStateException("No data type or invalid data type is specified");
                    }
                    String str3 = this.o;
                    if (str3 == null) {
                        String str4 = this.n;
                        if (str4 == null) {
                            for (Projection projection : this.m) {
                                if (projection.getAlias() != null) {
                                    if (projection.getAlias().isEmpty()) {
                                    }
                                }
                                throw new IllegalStateException("Null or empty alias for read request is not allowed");
                            }
                            List<String> list = this.f;
                            if (list != null) {
                                for (String str5 : list) {
                                    if (str5 == null) {
                                        throw new IllegalStateException("Null device uuid");
                                    }
                                }
                            }
                            String str6 = this.d;
                            if (str6 == null) {
                                str = null;
                            } else if (this.e != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(this.d);
                                sb.append(" ");
                                sb.append(this.e.toSqlLiteral());
                                str = sb.toString();
                            } else {
                                str = str6;
                            }
                            if (this.k != 1) {
                                this.j = 0;
                            } else if (this.j <= 0) {
                                throw new IllegalStateException("Wrong count (zero or negative number)");
                            } else if (this.i < 0) {
                                throw new IllegalStateException("Wrong offset (negative number)");
                            }
                            int size = this.m.size();
                            String[] strArr2 = this.l;
                            if (strArr2 == null || strArr2.length == 0) {
                                ReadRequestImpl readRequestImpl = new ReadRequestImpl(this.a, this.b, this.c, size > 0 ? this.m : null, this.f, 1, str, this.g, this.h, this.i, this.j, this.p);
                                return readRequestImpl;
                            }
                            ArrayList arrayList = new ArrayList(strArr2.length);
                            int i2 = size;
                            for (String str7 : this.l) {
                                int i3 = 0;
                                while (i3 < i2) {
                                    String property = ((Projection) this.m.get(i3)).getProperty();
                                    if (str7 != null && str7.equalsIgnoreCase(property)) {
                                        break;
                                    }
                                    i3++;
                                }
                                if (i3 < i2) {
                                    arrayList.add(this.m.remove(i3));
                                    i2--;
                                } else {
                                    arrayList.add(new Projection(str7, null));
                                }
                            }
                            if (i2 == 0 || this.m.size() <= 0) {
                                ReadRequestImpl readRequestImpl2 = new ReadRequestImpl(this.a, this.b, this.c, arrayList, this.f, 0, str, this.g, this.h, this.i, this.j, this.p);
                                return readRequestImpl2;
                            }
                            throw new IllegalStateException("Not matched aliases");
                        }
                        throw new IllegalStateException(str4);
                    }
                    throw new IllegalStateException(str3);
                } else {
                    throw new IllegalStateException("Illegal setTimeBefore value is specified");
                }
            }
        }
    }

    public static class ReadResult extends BaseResult {
        public static final Creator<ReadResult> CREATOR = new Creator<ReadResult>() {
            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new ReadResult[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new ReadResult(parcel, 0);
            }
        };
        private final BulkCursorDescriptor a;
        private final String b;
        private boolean c;

        /* synthetic */ ReadResult(Parcel parcel, byte b2) {
            this(parcel);
        }

        public ReadResult(String str, int i, int i2) {
            super(i, i2);
            this.b = str;
            this.a = null;
        }

        public ReadResult(String str, int i, BulkCursorDescriptor bulkCursorDescriptor) {
            super(i, bulkCursorDescriptor != null ? bulkCursorDescriptor.count : 0);
            this.b = str;
            this.a = bulkCursorDescriptor;
        }

        public ReadResult(String str, BulkCursorDescriptor bulkCursorDescriptor) {
            super(1, bulkCursorDescriptor != null ? bulkCursorDescriptor.count : 0);
            this.b = str;
            this.a = bulkCursorDescriptor;
        }

        private ReadResult(Parcel parcel) {
            super(parcel);
            this.b = parcel.readString();
            this.a = (BulkCursorDescriptor) parcel.readParcelable(BulkCursorDescriptor.class.getClassLoader());
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.b);
            parcel.writeParcelable(this.a, 0);
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            BulkCursorDescriptor bulkCursorDescriptor = this.a;
            if (bulkCursorDescriptor != null && !this.c) {
                IBulkCursor iBulkCursor = bulkCursorDescriptor.cursor;
                if (iBulkCursor != null) {
                    iBulkCursor.close();
                }
            }
            super.finalize();
        }

        public Cursor getResultCursor() {
            if (this.a == null) {
                return null;
            }
            BulkCursorToCursorAdaptor bulkCursorToCursorAdaptor = new BulkCursorToCursorAdaptor();
            bulkCursorToCursorAdaptor.initialize(this.a);
            this.c = true;
            return bulkCursorToCursorAdaptor;
        }

        public String getDataType() {
            return this.b;
        }
    }

    public enum SortOrder {
        ;

        public abstract String toSqlLiteral();
    }

    public interface UpdateRequest {

        public static class Builder {
            private String a;
            private HealthData b;
            private Filter c;
            private List<String> d;

            public Builder setDataType(String str) {
                this.a = str;
                return this;
            }

            public Builder setHealthData(HealthData healthData) {
                this.b = healthData;
                return this;
            }

            public Builder setFilter(Filter filter) {
                this.c = filter;
                return this;
            }

            public Builder setSourceDevices(List<String> list) {
                this.d = list;
                return this;
            }

            public UpdateRequest build() {
                String str = this.a;
                if (str == null || "".equals(str) || this.b == null) {
                    throw new IllegalStateException("at least valid one of data type or health data object is not specified");
                }
                List<String> list = this.d;
                if (list != null) {
                    for (String str2 : list) {
                        if (str2 == null) {
                            throw new IllegalStateException("Null device uuid");
                        }
                    }
                }
                return new UpdateRequestImpl(this.a, this.b, this.c, this.d);
            }
        }
    }

    public HealthDataResolver(HealthDataStore healthDataStore, Handler handler) {
        this.a = healthDataStore;
        this.b = handler;
    }

    private IDataResolver a() {
        try {
            IDataResolver iDataResolver = HealthDataStore.getInterface(this.a).getIDataResolver();
            if (iDataResolver != null) {
                return iDataResolver;
            }
            throw new IllegalStateException("IDataResolver is null");
        } catch (RemoteException e) {
            throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
        }
    }

    public HealthResultHolder<BaseResult> insert(InsertRequest insertRequest) {
        if (insertRequest instanceof InsertRequestImpl) {
            IDataResolver a2 = a();
            Looper b2 = b();
            InsertRequestImpl insertRequestImpl = (InsertRequestImpl) insertRequest;
            if (insertRequestImpl.isEmpty()) {
                return HealthResultHolderImpl.createAndSetResult(new BaseResult(1, 0), b2);
            }
            for (HealthData healthData : insertRequestImpl.getItems()) {
                Iterator it = healthData.getBlobKeySet().iterator();
                while (true) {
                    if (it.hasNext()) {
                        String str = (String) it.next();
                        byte[] blob = healthData.getBlob(str);
                        if (blob != null && blob.length > 1024000) {
                            StringBuilder sb = new StringBuilder("Blob data size is bigger than 1000KB : ");
                            sb.append(str);
                            sb.append(" is ");
                            sb.append(blob.length);
                            sb.append(" Bytes");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    }
                }
            }
            try {
                for (HealthData healthData2 : insertRequestImpl.getItems()) {
                    for (String sendBlob : healthData2.getBlobKeySet()) {
                        IpcUtil.sendBlob(healthData2, sendBlob);
                    }
                }
                ForwardAsync forwardAsync = new ForwardAsync();
                HealthResultHolder<BaseResult> asyncResultHolder = IpcUtil.getAsyncResultHolder(forwardAsync, b2);
                a2.insertData2(this.a.a().getPackageName(), forwardAsync, insertRequestImpl);
                return asyncResultHolder;
            } catch (TransactionTooLargeException e) {
                throw new IllegalArgumentException(e.toString());
            } catch (RemoteException e2) {
                throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e2));
            }
        } else {
            throw new IllegalArgumentException("Invalid request instance");
        }
    }

    public HealthResultHolder<BaseResult> update(UpdateRequest updateRequest) {
        if (updateRequest instanceof UpdateRequestImpl) {
            IDataResolver a2 = a();
            Looper b2 = b();
            UpdateRequestImpl updateRequestImpl = (UpdateRequestImpl) updateRequest;
            HealthData dataObject = updateRequestImpl.getDataObject();
            for (String str : dataObject.getBlobKeySet()) {
                byte[] blob = dataObject.getBlob(str);
                if (blob != null && blob.length > 1024000) {
                    StringBuilder sb = new StringBuilder("Blob data size is bigger than 1000KB : ");
                    sb.append(str);
                    sb.append(" is ");
                    sb.append(blob.length);
                    sb.append(" Bytes");
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            try {
                for (String sendBlob : dataObject.getBlobKeySet()) {
                    IpcUtil.sendBlob(dataObject, sendBlob);
                }
                ForwardAsync forwardAsync = new ForwardAsync();
                HealthResultHolder<BaseResult> asyncResultHolder = IpcUtil.getAsyncResultHolder(forwardAsync, b2);
                a2.updateData2(this.a.a().getPackageName(), forwardAsync, updateRequestImpl);
                return asyncResultHolder;
            } catch (TransactionTooLargeException e) {
                throw new IllegalArgumentException(e.toString());
            } catch (RemoteException e2) {
                throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e2));
            }
        } else {
            throw new IllegalArgumentException("Invalid request instance");
        }
    }

    public HealthResultHolder<BaseResult> delete(DeleteRequest deleteRequest) {
        if (deleteRequest instanceof DeleteRequestImpl) {
            IDataResolver a2 = a();
            Looper b2 = b();
            DeleteRequestImpl deleteRequestImpl = (DeleteRequestImpl) deleteRequest;
            try {
                ForwardAsync forwardAsync = new ForwardAsync();
                HealthResultHolder<BaseResult> asyncResultHolder = IpcUtil.getAsyncResultHolder(forwardAsync, b2);
                a2.deleteData2(this.a.a().getPackageName(), forwardAsync, deleteRequestImpl);
                return asyncResultHolder;
            } catch (RemoteException e) {
                throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
            }
        } else {
            throw new IllegalArgumentException("Invalid request instance");
        }
    }

    public HealthResultHolder<ReadResult> read(ReadRequest readRequest) {
        if (readRequest instanceof ReadRequestImpl) {
            IDataResolver a2 = a();
            Looper b2 = b();
            ReadRequestImpl readRequestImpl = (ReadRequestImpl) readRequest;
            try {
                ForwardAsync forwardAsync = new ForwardAsync();
                HealthResultHolder<ReadResult> asyncResultHolder = IpcUtil.getAsyncResultHolder(forwardAsync, b2);
                a2.readData2(this.a.a().getPackageName(), forwardAsync, readRequestImpl);
                return asyncResultHolder;
            } catch (RemoteException e) {
                throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
            }
        } else {
            throw new IllegalArgumentException("Invalid request instance");
        }
    }

    public HealthResultHolder<AggregateResult> aggregate(AggregateRequest aggregateRequest) {
        if (aggregateRequest instanceof AggregateRequestImpl) {
            IDataResolver a2 = a();
            Looper b2 = b();
            AggregateRequestImpl aggregateRequestImpl = (AggregateRequestImpl) aggregateRequest;
            try {
                ForwardAsync forwardAsync = new ForwardAsync();
                HealthResultHolder<AggregateResult> asyncResultHolder = IpcUtil.getAsyncResultHolder(forwardAsync, b2);
                a2.aggregateData2(this.a.a().getPackageName(), forwardAsync, aggregateRequestImpl);
                return asyncResultHolder;
            } catch (RemoteException e) {
                throw new IllegalStateException(ErrorUtil.getRemoteExceptionMessage(e));
            }
        } else {
            throw new IllegalArgumentException("Invalid aggregate request");
        }
    }

    private Looper b() {
        Handler handler = this.b;
        Looper looper = handler != null ? handler.getLooper() : Looper.myLooper();
        if (looper != null) {
            return looper;
        }
        throw new IllegalStateException("This thread has no looper");
    }
}
