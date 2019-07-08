package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import com.liulishuo.filedownloader.message.MessageSnapshot.IWarnMessageSnapshot;

public abstract class LargeMessageSnapshot extends MessageSnapshot {

    public static class CompletedFlowDirectlySnapshot extends CompletedSnapshot implements IFlowDirectly {
        CompletedFlowDirectlySnapshot(int i, boolean z, long j) {
            super(i, z, j);
        }
    }

    public static class CompletedSnapshot extends LargeMessageSnapshot {
        private final boolean reusedDownloadedFile;
        private final long totalBytes;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return -3;
        }

        CompletedSnapshot(int i, boolean z, long j) {
            super(i);
            this.reusedDownloadedFile = z;
            this.totalBytes = j;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.reusedDownloadedFile ? (byte) 1 : 0);
            parcel.writeLong(this.totalBytes);
        }

        CompletedSnapshot(Parcel parcel) {
            super(parcel);
            this.reusedDownloadedFile = parcel.readByte() != 0;
            this.totalBytes = parcel.readLong();
        }

        public long getLargeTotalBytes() {
            return this.totalBytes;
        }

        public boolean isReusedDownloadedFile() {
            return this.reusedDownloadedFile;
        }
    }

    public static class ConnectedMessageSnapshot extends LargeMessageSnapshot {
        private final String etag;
        private final String fileName;
        private final boolean resuming;
        private final long totalBytes;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return 2;
        }

        ConnectedMessageSnapshot(int i, boolean z, long j, String str, String str2) {
            super(i);
            this.resuming = z;
            this.totalBytes = j;
            this.etag = str;
            this.fileName = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.resuming ? (byte) 1 : 0);
            parcel.writeLong(this.totalBytes);
            parcel.writeString(this.etag);
            parcel.writeString(this.fileName);
        }

        ConnectedMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.resuming = parcel.readByte() != 0;
            this.totalBytes = parcel.readLong();
            this.etag = parcel.readString();
            this.fileName = parcel.readString();
        }

        public String getFileName() {
            return this.fileName;
        }

        public boolean isResuming() {
            return this.resuming;
        }

        public long getLargeTotalBytes() {
            return this.totalBytes;
        }

        public String getEtag() {
            return this.etag;
        }
    }

    public static class ErrorMessageSnapshot extends LargeMessageSnapshot {
        private final long sofarBytes;
        private final Throwable throwable;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return -1;
        }

        ErrorMessageSnapshot(int i, long j, Throwable th) {
            super(i);
            this.sofarBytes = j;
            this.throwable = th;
        }

        public long getLargeSofarBytes() {
            return this.sofarBytes;
        }

        public Throwable getThrowable() {
            return this.throwable;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.sofarBytes);
            parcel.writeSerializable(this.throwable);
        }

        ErrorMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.sofarBytes = parcel.readLong();
            this.throwable = (Throwable) parcel.readSerializable();
        }
    }

    public static class PausedSnapshot extends PendingMessageSnapshot {
        public byte getStatus() {
            return -2;
        }

        PausedSnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }
    }

    public static class PendingMessageSnapshot extends LargeMessageSnapshot {
        private final long sofarBytes;
        private final long totalBytes;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return 1;
        }

        PendingMessageSnapshot(PendingMessageSnapshot pendingMessageSnapshot) {
            this(pendingMessageSnapshot.getId(), pendingMessageSnapshot.getLargeSofarBytes(), pendingMessageSnapshot.getLargeTotalBytes());
        }

        PendingMessageSnapshot(int i, long j, long j2) {
            super(i);
            this.sofarBytes = j;
            this.totalBytes = j2;
        }

        public long getLargeSofarBytes() {
            return this.sofarBytes;
        }

        public long getLargeTotalBytes() {
            return this.totalBytes;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.sofarBytes);
            parcel.writeLong(this.totalBytes);
        }

        PendingMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.sofarBytes = parcel.readLong();
            this.totalBytes = parcel.readLong();
        }
    }

    public static class ProgressMessageSnapshot extends LargeMessageSnapshot {
        private final long sofarBytes;

        public int describeContents() {
            return 0;
        }

        public byte getStatus() {
            return 3;
        }

        ProgressMessageSnapshot(int i, long j) {
            super(i);
            this.sofarBytes = j;
        }

        public long getLargeSofarBytes() {
            return this.sofarBytes;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.sofarBytes);
        }

        ProgressMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.sofarBytes = parcel.readLong();
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

        RetryMessageSnapshot(int i, long j, Throwable th, int i2) {
            super(i, j, th);
            this.retryingTimes = i2;
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
        WarnFlowDirectlySnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }
    }

    public static class WarnMessageSnapshot extends PendingMessageSnapshot implements IWarnMessageSnapshot {
        public byte getStatus() {
            return -4;
        }

        WarnMessageSnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }

        WarnMessageSnapshot(Parcel parcel) {
            super(parcel);
        }

        public MessageSnapshot turnToPending() {
            return new PendingMessageSnapshot((PendingMessageSnapshot) this);
        }
    }

    LargeMessageSnapshot(int i) {
        super(i);
        this.isLargeFile = true;
    }

    LargeMessageSnapshot(Parcel parcel) {
        super(parcel);
    }

    public int getSmallSofarBytes() {
        if (getLargeSofarBytes() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) getLargeSofarBytes();
    }

    public int getSmallTotalBytes() {
        if (getLargeTotalBytes() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) getLargeTotalBytes();
    }
}
