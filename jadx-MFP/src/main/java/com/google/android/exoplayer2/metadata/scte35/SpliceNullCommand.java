package com.google.android.exoplayer2.metadata.scte35;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class SpliceNullCommand extends SpliceCommand {
    public static final Creator<SpliceNullCommand> CREATOR = new Creator<SpliceNullCommand>() {
        public SpliceNullCommand createFromParcel(Parcel parcel) {
            return new SpliceNullCommand();
        }

        public SpliceNullCommand[] newArray(int i) {
            return new SpliceNullCommand[i];
        }
    };

    public void writeToParcel(Parcel parcel, int i) {
    }
}
