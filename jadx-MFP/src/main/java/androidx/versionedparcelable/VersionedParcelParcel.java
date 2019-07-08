package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseIntArray;

@RestrictTo
class VersionedParcelParcel extends VersionedParcel {
    private int mCurrentField;
    private final int mEnd;
    private int mNextRead;
    private final int mOffset;
    private final Parcel mParcel;
    private final SparseIntArray mPositionLookup;
    private final String mPrefix;

    VersionedParcelParcel(Parcel parcel) {
        this(parcel, parcel.dataPosition(), parcel.dataSize(), "");
    }

    VersionedParcelParcel(Parcel parcel, int i, int i2, String str) {
        this.mPositionLookup = new SparseIntArray();
        this.mCurrentField = -1;
        this.mNextRead = 0;
        this.mParcel = parcel;
        this.mOffset = i;
        this.mEnd = i2;
        this.mNextRead = this.mOffset;
        this.mPrefix = str;
    }

    private int readUntilField(int i) {
        int readInt;
        do {
            int i2 = this.mNextRead;
            if (i2 >= this.mEnd) {
                return -1;
            }
            this.mParcel.setDataPosition(i2);
            int readInt2 = this.mParcel.readInt();
            readInt = this.mParcel.readInt();
            this.mNextRead += readInt2;
        } while (readInt != i);
        return this.mParcel.dataPosition();
    }

    public boolean readField(int i) {
        int readUntilField = readUntilField(i);
        if (readUntilField == -1) {
            return false;
        }
        this.mParcel.setDataPosition(readUntilField);
        return true;
    }

    public void setOutputField(int i) {
        closeField();
        this.mCurrentField = i;
        this.mPositionLookup.put(i, this.mParcel.dataPosition());
        writeInt(0);
        writeInt(i);
    }

    public void closeField() {
        int i = this.mCurrentField;
        if (i >= 0) {
            int i2 = this.mPositionLookup.get(i);
            int dataPosition = this.mParcel.dataPosition();
            int i3 = dataPosition - i2;
            this.mParcel.setDataPosition(i2);
            this.mParcel.writeInt(i3);
            this.mParcel.setDataPosition(dataPosition);
        }
    }

    /* access modifiers changed from: protected */
    public VersionedParcel createSubParcel() {
        Parcel parcel = this.mParcel;
        int dataPosition = parcel.dataPosition();
        int i = this.mNextRead;
        if (i == this.mOffset) {
            i = this.mEnd;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mPrefix);
        sb.append("  ");
        return new VersionedParcelParcel(parcel, dataPosition, i, sb.toString());
    }

    public void writeByteArray(byte[] bArr) {
        if (bArr != null) {
            this.mParcel.writeInt(bArr.length);
            this.mParcel.writeByteArray(bArr);
            return;
        }
        this.mParcel.writeInt(-1);
    }

    public void writeInt(int i) {
        this.mParcel.writeInt(i);
    }

    public void writeString(String str) {
        this.mParcel.writeString(str);
    }

    public void writeParcelable(Parcelable parcelable) {
        this.mParcel.writeParcelable(parcelable, 0);
    }

    public int readInt() {
        return this.mParcel.readInt();
    }

    public String readString() {
        return this.mParcel.readString();
    }

    public byte[] readByteArray() {
        int readInt = this.mParcel.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        this.mParcel.readByteArray(bArr);
        return bArr;
    }

    public <T extends Parcelable> T readParcelable() {
        return this.mParcel.readParcelable(getClass().getClassLoader());
    }
}
