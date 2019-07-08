package io.requery.android;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.requery.Converter;

@Deprecated
public class ParcelConverter<T extends Parcelable> implements Converter<T, byte[]> {
    private final Creator<T> creator;
    private final Class<T> type;

    public Integer getPersistedSize() {
        return null;
    }

    public Class<T> getMappedType() {
        return this.type;
    }

    public Class<byte[]> getPersistedType() {
        return byte[].class;
    }

    public byte[] convertToPersisted(T t) {
        if (t == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        return obtain.marshall();
    }

    public T convertToMapped(Class<? extends T> cls, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        return (Parcelable) this.creator.createFromParcel(obtain);
    }
}
