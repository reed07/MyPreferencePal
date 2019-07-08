package com.uacf.core.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class ParcelableUtil {
    public static <T> T clone(Parcelable parcelable, Creator<T> creator) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        T createFromParcel = creator.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    public static void writeMapKeyedByString(Parcel parcel, Map<String, Parcelable> map) {
        Set<String> keySet = map.keySet();
        parcel.writeInt(keySet.size());
        for (String str : keySet) {
            parcel.writeString(str);
            parcel.writeParcelable((Parcelable) map.get(str), 0);
        }
    }

    public static <T extends Parcelable> Map<String, T> readMapKeyedByString(Parcel parcel, Class<T> cls) {
        HashMap hashMap = new HashMap();
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            hashMap.put(parcel.readString(), parcel.readParcelable(cls.getClassLoader()));
        }
        return hashMap;
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() == 1;
    }

    public static void writeDate(Parcel parcel, Date date) {
        writeBoolean(parcel, date != null);
        if (date != null) {
            parcel.writeLong(date.getTime());
        }
    }

    public static Date readDate(Parcel parcel) {
        if (readBoolean(parcel)) {
            return new Date(parcel.readLong());
        }
        return null;
    }

    public static void writeStringToStringMap(Parcel parcel, Map<String, String> map) {
        parcel.writeInt(CollectionUtils.size(map));
        if (!CollectionUtils.isEmpty(map)) {
            for (Entry entry : map.entrySet()) {
                parcel.writeString((String) entry.getKey());
                parcel.writeString((String) entry.getValue());
            }
        }
    }

    public static Map<String, String> createStringToStringMap(Parcel parcel) {
        int readInt = parcel.readInt();
        HashMap hashMap = new HashMap(readInt);
        putStringToStringValuesInMap(parcel, hashMap, readInt);
        return hashMap;
    }

    public static Map<String, String> readStringToStringMap(Parcel parcel) {
        HashMap hashMap = new HashMap();
        readStringToStringMap(parcel, hashMap);
        return hashMap;
    }

    public static void readStringToStringMap(Parcel parcel, Map<String, String> map) {
        putStringToStringValuesInMap(parcel, map, parcel.readInt());
    }

    private static void putStringToStringValuesInMap(Parcel parcel, Map<String, String> map, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            map.put(parcel.readString(), parcel.readString());
        }
    }

    public static <T extends Parcelable> void writeList(Parcel parcel, List<T> list) {
        if (list == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeList(list);
    }

    public static <T extends Parcelable> ArrayList<T> readList(Parcel parcel, Class<T> cls) {
        ArrayList<T> arrayList = new ArrayList<>();
        if (parcel.readByte() == 1) {
            parcel.readList(arrayList, cls.getClassLoader());
        }
        return arrayList;
    }

    public static ArrayList<String> readStringList(Parcel parcel) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (parcel.readByte() == 1) {
            parcel.readStringList(arrayList);
        }
        return arrayList;
    }

    public static <T extends Parcelable> void writeStringList(Parcel parcel, List<String> list) {
        if (list == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeStringList(list);
    }

    public static Double readDoubleObject(Parcel parcel) {
        if (parcel.readByte() == 0) {
            return null;
        }
        return Double.valueOf(parcel.readDouble());
    }

    public static void writeDoubleObject(Parcel parcel, Double d) {
        if (d == null) {
            parcel.writeByte(0);
            return;
        }
        parcel.writeByte(1);
        parcel.writeDouble(d.doubleValue());
    }

    public static <T extends Parcelable> Parcel createParcelAndWriteObject(T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        return obtain;
    }
}
