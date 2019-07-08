package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.sqlite.CursorWrapper;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@KeepName
@KeepForSdk
@Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    @KeepForSdk
    public static final Creator<DataHolder> CREATOR = new zac();
    private static final Builder zalx = new zab(new String[0], null);
    private boolean mClosed;
    @VersionField(id = 1000)
    private final int zale;
    @Field(getter = "getColumns", id = 1)
    private final String[] zalp;
    private Bundle zalq;
    @Field(getter = "getWindows", id = 2)
    private final CursorWindow[] zalr;
    @Field(getter = "getStatusCode", id = 3)
    private final int zals;
    @Field(getter = "getMetadata", id = 4)
    private final Bundle zalt;
    private int[] zalu;
    private int zalv;
    private boolean zalw;

    @KeepForSdk
    public static class Builder {
        /* access modifiers changed from: private */
        public final String[] zalp;
        /* access modifiers changed from: private */
        public final ArrayList<HashMap<String, Object>> zaly;
        private final String zalz;
        private final HashMap<Object, Integer> zama;
        private boolean zamb;
        private String zamc;

        private Builder(String[] strArr, String str) {
            this.zalp = (String[]) Preconditions.checkNotNull(strArr);
            this.zaly = new ArrayList<>();
            this.zalz = str;
            this.zama = new HashMap<>();
            this.zamb = false;
            this.zamc = null;
        }

        public Builder zaa(HashMap<String, Object> hashMap) {
            int i;
            Asserts.checkNotNull(hashMap);
            String str = this.zalz;
            if (str == null) {
                i = -1;
            } else {
                Object obj = hashMap.get(str);
                if (obj == null) {
                    i = -1;
                } else {
                    Integer num = (Integer) this.zama.get(obj);
                    if (num == null) {
                        this.zama.put(obj, Integer.valueOf(this.zaly.size()));
                        i = -1;
                    } else {
                        i = num.intValue();
                    }
                }
            }
            if (i == -1) {
                this.zaly.add(hashMap);
            } else {
                this.zaly.remove(i);
                this.zaly.add(i, hashMap);
            }
            this.zamb = false;
            return this;
        }

        @KeepForSdk
        public Builder withRow(ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Entry entry : contentValues.valueSet()) {
                hashMap.put((String) entry.getKey(), entry.getValue());
            }
            return zaa(hashMap);
        }

        @KeepForSdk
        public DataHolder build(int i) {
            return new DataHolder(this, i, (Bundle) null, (zab) null);
        }

        @KeepForSdk
        public DataHolder build(int i, Bundle bundle) {
            DataHolder dataHolder = new DataHolder(this, i, bundle, -1, (zab) null);
            return dataHolder;
        }

        /* synthetic */ Builder(String[] strArr, String str, zab zab) {
            this(strArr, null);
        }
    }

    public static class zaa extends RuntimeException {
        public zaa(String str) {
            super(str);
        }
    }

    @Constructor
    DataHolder(@Param(id = 1000) int i, @Param(id = 1) String[] strArr, @Param(id = 2) CursorWindow[] cursorWindowArr, @Param(id = 3) int i2, @Param(id = 4) Bundle bundle) {
        this.mClosed = false;
        this.zalw = true;
        this.zale = i;
        this.zalp = strArr;
        this.zalr = cursorWindowArr;
        this.zals = i2;
        this.zalt = bundle;
    }

    @KeepForSdk
    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zalw = true;
        this.zale = 1;
        this.zalp = (String[]) Preconditions.checkNotNull(strArr);
        this.zalr = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zals = i;
        this.zalt = bundle;
        zaca();
    }

    private DataHolder(CursorWrapper cursorWrapper, int i, Bundle bundle) {
        this(cursorWrapper.getColumnNames(), zaa(cursorWrapper), i, bundle);
    }

    @KeepForSdk
    public DataHolder(Cursor cursor, int i, Bundle bundle) {
        this(new CursorWrapper(cursor), i, bundle);
    }

    private DataHolder(Builder builder, int i, Bundle bundle) {
        this(builder.zalp, zaa(builder, -1), i, (Bundle) null);
    }

    private DataHolder(Builder builder, int i, Bundle bundle, int i2) {
        this(builder.zalp, zaa(builder, -1), i, bundle);
    }

    public final void zaca() {
        this.zalq = new Bundle();
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = this.zalp;
            if (i2 >= strArr.length) {
                break;
            }
            this.zalq.putInt(strArr[i2], i2);
            i2++;
        }
        this.zalu = new int[this.zalr.length];
        int i3 = 0;
        while (true) {
            CursorWindow[] cursorWindowArr = this.zalr;
            if (i < cursorWindowArr.length) {
                this.zalu[i] = i3;
                i3 += this.zalr[i].getNumRows() - (i3 - cursorWindowArr[i].getStartPosition());
                i++;
            } else {
                this.zalv = i3;
                return;
            }
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zalp, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zalr, i, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zale);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }

    @KeepForSdk
    public final int getStatusCode() {
        return this.zals;
    }

    @KeepForSdk
    public final Bundle getMetadata() {
        return this.zalt;
    }

    /* JADX INFO: finally extract failed */
    private static CursorWindow[] zaa(CursorWrapper cursorWrapper) {
        int i;
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                i = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                i = window.getNumRows();
            }
            while (i < count && cursorWrapper.moveToPosition(i)) {
                CursorWindow window2 = cursorWrapper.getWindow();
                if (window2 != null) {
                    window2.acquireReference();
                    cursorWrapper.setWindow(null);
                } else {
                    window2 = new CursorWindow(false);
                    window2.setStartPosition(i);
                    cursorWrapper.fillWindow(i, window2);
                }
                if (window2.getNumRows() == 0) {
                    break;
                }
                arrayList.add(window2);
                i = window2.getStartPosition() + window2.getNumRows();
            }
            cursorWrapper.close();
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }

    private static CursorWindow[] zaa(Builder builder, int i) {
        List list;
        if (builder.zalp.length == 0) {
            return new CursorWindow[0];
        }
        if (i < 0 || i >= builder.zaly.size()) {
            list = builder.zaly;
        } else {
            list = builder.zaly.subList(0, i);
        }
        int size = list.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(builder.zalp.length);
        CursorWindow cursorWindow2 = cursorWindow;
        int i2 = 0;
        boolean z = false;
        while (i2 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    StringBuilder sb = new StringBuilder(72);
                    sb.append("Allocating additional cursor window for large data set (row ");
                    sb.append(i2);
                    sb.append(")");
                    Log.d("DataHolder", sb.toString());
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zalp.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) list.get(i2);
                boolean z2 = true;
                for (int i3 = 0; i3 < builder.zalp.length && z2; i3++) {
                    String str = builder.zalp[i3];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z2 = cursorWindow2.putNull(i2, i3);
                    } else if (obj instanceof String) {
                        z2 = cursorWindow2.putString((String) obj, i2, i3);
                    } else if (obj instanceof Long) {
                        z2 = cursorWindow2.putLong(((Long) obj).longValue(), i2, i3);
                    } else if (obj instanceof Integer) {
                        z2 = cursorWindow2.putLong((long) ((Integer) obj).intValue(), i2, i3);
                    } else if (obj instanceof Boolean) {
                        z2 = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i2, i3);
                    } else if (obj instanceof byte[]) {
                        z2 = cursorWindow2.putBlob((byte[]) obj, i2, i3);
                    } else if (obj instanceof Double) {
                        z2 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i2, i3);
                    } else if (obj instanceof Float) {
                        z2 = cursorWindow2.putDouble((double) ((Float) obj).floatValue(), i2, i3);
                    } else {
                        String valueOf = String.valueOf(obj);
                        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(valueOf).length());
                        sb2.append("Unsupported object for column ");
                        sb2.append(str);
                        sb2.append(": ");
                        sb2.append(valueOf);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                }
                if (z2) {
                    z = false;
                } else if (!z) {
                    StringBuilder sb3 = new StringBuilder(74);
                    sb3.append("Couldn't populate window data for row ");
                    sb3.append(i2);
                    sb3.append(" - allocating new window.");
                    Log.d("DataHolder", sb3.toString());
                    cursorWindow2.freeLastRow();
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zalp.length);
                    arrayList.add(cursorWindow2);
                    i2--;
                    z = true;
                } else {
                    throw new zaa("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                }
                i2++;
            } catch (RuntimeException e) {
                int size2 = arrayList.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    ((CursorWindow) arrayList.get(i4)).close();
                }
                throw e;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    private final void zaa(String str, int i) {
        Bundle bundle = this.zalq;
        if (bundle == null || !bundle.containsKey(str)) {
            String str2 = "No such column: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.zalv) {
            throw new CursorIndexOutOfBoundsException(i, this.zalv);
        }
    }

    @KeepForSdk
    public final boolean hasColumn(String str) {
        return this.zalq.containsKey(str);
    }

    @KeepForSdk
    public final long getLong(String str, int i, int i2) {
        zaa(str, i);
        return this.zalr[i2].getLong(i, this.zalq.getInt(str));
    }

    @KeepForSdk
    public final int getInteger(String str, int i, int i2) {
        zaa(str, i);
        return this.zalr[i2].getInt(i, this.zalq.getInt(str));
    }

    @KeepForSdk
    public final String getString(String str, int i, int i2) {
        zaa(str, i);
        return this.zalr[i2].getString(i, this.zalq.getInt(str));
    }

    @KeepForSdk
    public final boolean getBoolean(String str, int i, int i2) {
        zaa(str, i);
        return Long.valueOf(this.zalr[i2].getLong(i, this.zalq.getInt(str))).longValue() == 1;
    }

    public final float zaa(String str, int i, int i2) {
        zaa(str, i);
        return this.zalr[i2].getFloat(i, this.zalq.getInt(str));
    }

    public final double zab(String str, int i, int i2) {
        zaa(str, i);
        return this.zalr[i2].getDouble(i, this.zalq.getInt(str));
    }

    @KeepForSdk
    public final byte[] getByteArray(String str, int i, int i2) {
        zaa(str, i);
        return this.zalr[i2].getBlob(i, this.zalq.getInt(str));
    }

    public final void zaa(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zaa(str, i);
        this.zalr[i2].copyStringToBuffer(i, this.zalq.getInt(str), charArrayBuffer);
    }

    @KeepForSdk
    public final boolean hasNull(String str, int i, int i2) {
        zaa(str, i);
        return this.zalr[i2].isNull(i, this.zalq.getInt(str));
    }

    @KeepForSdk
    public final int getCount() {
        return this.zalv;
    }

    @KeepForSdk
    public final int getWindowIndex(int i) {
        int i2 = 0;
        Preconditions.checkState(i >= 0 && i < this.zalv);
        while (true) {
            int[] iArr = this.zalu;
            if (i2 >= iArr.length) {
                break;
            } else if (i < iArr[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.zalu.length ? i2 - 1 : i2;
    }

    @KeepForSdk
    public final boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    @KeepForSdk
    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.zalr) {
                    close.close();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            if (this.zalw && this.zalr.length > 0 && !isClosed()) {
                close();
                String obj = toString();
                StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + RequestCodes.PROGRESS_PHOTOS_GALLERY);
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(obj);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    @KeepForSdk
    public static Builder builder(String[] strArr) {
        return new Builder(strArr, null, null);
    }

    @KeepForSdk
    public static DataHolder empty(int i) {
        return new DataHolder(zalx, i, (Bundle) null);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, zab zab) {
        this(builder, i, (Bundle) null);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, int i2, zab zab) {
        this(builder, i, bundle, -1);
    }
}
