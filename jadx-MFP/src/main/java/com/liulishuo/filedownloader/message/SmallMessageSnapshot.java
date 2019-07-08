package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import com.liulishuo.filedownloader.message.MessageSnapshot.IWarnMessageSnapshot;

public abstract class SmallMessageSnapshot extends MessageSnapshot {

    public static class CompletedFlowDirectlySnapshot extends CompletedSnapshot implements IFlowDirectly {
        CompletedFlowDirectlySnapshot(int i, boolean z, int i2) {
            super(i, z, i2);
        }
    }

    public static class CompletedSnapshot extends SmallMessageSnapshot {
        private final boolean reusedDownloadedFile;
        private final int totalBytes;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return -3;
        }

        CompletedSnapshot(int i, boolean z, int i2) {
            super(i);
            this.reusedDownloadedFile = z;
            this.totalBytes = i2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.reusedDownloadedFile ? (byte) 1 : 0);
            parcel.writeInt(this.totalBytes);
        }

        CompletedSnapshot(Parcel parcel) {
            super(parcel);
            this.reusedDownloadedFile = parcel.readByte() != 0;
            this.totalBytes = parcel.readInt();
        }

        public int getSmallTotalBytes() {
            return this.totalBytes;
        }

        public boolean isReusedDownloadedFile() {
            return this.reusedDownloadedFile;
        }
    }

    public static class ConnectedMessageSnapshot extends SmallMessageSnapshot {
        private final String etag;
        private final String fileName;
        private final boolean resuming;
        private final int totalBytes;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return 2;
        }

        ConnectedMessageSnapshot(int i, boolean z, int i2, String str, String str2) {
            super(i);
            this.resuming = z;
            this.totalBytes = i2;
            this.etag = str;
            this.fileName = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.resuming ? (byte) 1 : 0);
            parcel.writeInt(this.totalBytes);
            parcel.writeString(this.etag);
            parcel.writeString(this.fileName);
        }

        ConnectedMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.resuming = parcel.readByte() != 0;
            this.totalBytes = parcel.readInt();
            this.etag = parcel.readString();
            this.fileName = parcel.readString();
        }

        public String getFileName() {
            return this.fileName;
        }

        public boolean isResuming() {
            return this.resuming;
        }

        public int getSmallTotalBytes() {
            return this.totalBytes;
        }

        public String getEtag() {
            return this.etag;
        }
    }

    public static class ErrorMessageSnapshot extends SmallMessageSnapshot {
        private final int sofarBytes;
        private final Throwable throwable;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return -1;
        }

        ErrorMessageSnapshot(int i, int i2, Throwable th) {
            super(i);
            this.sofarBytes = i2;
            this.throwable = th;
        }

        public int getSmallSofarBytes() {
            return this.sofarBytes;
        }

        public Throwable getThrowable() {
            return this.throwable;
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.sofarBytes);
            parcel.writeSerializable(this.throwable);
        }

        ErrorMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.sofarBytes = parcel.readInt();
            this.throwable = (Throwable) parcel.readSerializable();
        }
    }

    public static class PausedSnapshot extends PendingMessageSnapshot {
        public byte getStatus() {
            return -2;
        }

        PausedSnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }
    }

    public static class PendingMessageSnapshot extends SmallMessageSnapshot {
        private final int sofarBytes;
        private final int totalBytes;

        public byte getStatus() {
            return 1;
        }

        PendingMessageSnapshot(PendingMessageSnapshot pendingMessageSnapshot) {
            this(pendingMessageSnapshot.getId(), pendingMessageSnapshot.getSmallSofarBytes(), pendingMessageSnapshot.getSmallTotalBytes());
        }

        PendingMessageSnapshot(int i, int i2, int i3) {
            super(i);
            this.sofarBytes = i2;
            this.totalBytes = i3;
        }

        PendingMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.sofarBytes = parcel.readInt();
            this.totalBytes = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.sofarBytes);
            parcel.writeInt(this.totalBytes);
        }

        public int getSmallSofarBytes() {
            return this.sofarBytes;
        }

        public int getSmallTotalBytes() {
            return this.totalBytes;
        }
    }

    public static class ProgressMessageSnapshot extends SmallMessageSnapshot {
        private final int sofarBytes;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return 3;
        }

        ProgressMessageSnapshot(int i, int i2) {
            super(i);
            this.sofarBytes = i2;
        }

        public int getSmallSofarBytes() {
            return this.sofarBytes;
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.sofarBytes);
        }

        ProgressMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.sofarBytes = parcel.readInt();
        }
    }

    public static class RetryMessageSnapshot extends ErrorMessageSnapshot {
        private final int retryingTimes;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return 5;
        }

        RetryMessageSnapshot(int i, int i2, Throwable th, int i3) {
            super(i, i2, th);
            this.retryingTimes = i3;
        }

        public int getRetryingTimes() {
            return this.retryingTimes;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.retryingTimes);
        }

        RetryMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.retryingTimes = parcel.readInt();
        }
    }

    public static class WarnFlowDirectlySnapshot extends WarnMessageSnapshot implements IFlowDirectly {
        WarnFlowDirectlySnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }
    }

    public static class WarnMessageSnapshot extends PendingMessageSnapshot implements IWarnMessageSnapshot {
        public byte getStatus() {
            return -4;
        }

        WarnMessageSnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        WarnMessageSnapshot(Parcel parcel) {
            super(parcel);
        }

        public MessageSnapshot turnToPending() {
            return new PendingMessageSnapshot((PendingMessageSnapshot) this);
        }
    }

    SmallMessageSnapshot(int i) {
        super(i);
        this.isLargeFile = false;
    }

    SmallMessageSnapshot(Parcel parcel) {
        super(parcel);
    }

    public long getLargeTotalBytes() {
        return (long) getSmallTotalBytes();
    }

    public long getLargeSofarBytes() {
        return (long) getSmallSofarBytes();
    }
}
