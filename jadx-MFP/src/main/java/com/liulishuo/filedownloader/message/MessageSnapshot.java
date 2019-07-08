package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.CompletedSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.ConnectedMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.ErrorMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.PendingMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.ProgressMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.RetryMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.WarnMessageSnapshot;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public abstract class MessageSnapshot implements Parcelable, IMessageSnapshot {
    public static final Creator<MessageSnapshot> CREATOR = new Creator<MessageSnapshot>() {
        public MessageSnapshot createFromParcel(Parcel parcel) {
            MessageSnapshot messageSnapshot;
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            byte readByte = parcel.readByte();
            switch (readByte) {
                case -4:
                    if (!z) {
                        messageSnapshot = new WarnMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.WarnMessageSnapshot(parcel);
                        break;
                    }
                case -3:
                    if (!z) {
                        messageSnapshot = new CompletedSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.CompletedSnapshot(parcel);
                        break;
                    }
                case -1:
                    if (!z) {
                        messageSnapshot = new ErrorMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ErrorMessageSnapshot(parcel);
                        break;
                    }
                case 1:
                    if (!z) {
                        messageSnapshot = new PendingMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.PendingMessageSnapshot(parcel);
                        break;
                    }
                case 2:
                    if (!z) {
                        messageSnapshot = new ConnectedMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ConnectedMessageSnapshot(parcel);
                        break;
                    }
                case 3:
                    if (!z) {
                        messageSnapshot = new ProgressMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ProgressMessageSnapshot(parcel);
                        break;
                    }
                case 5:
                    if (!z) {
                        messageSnapshot = new RetryMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.RetryMessageSnapshot(parcel);
                        break;
                    }
                case 6:
                    messageSnapshot = new StartedMessageSnapshot(parcel);
                    break;
                default:
                    messageSnapshot = null;
                    break;
            }
            if (messageSnapshot != null) {
                messageSnapshot.isLargeFile = z;
                return messageSnapshot;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Can't restore the snapshot because unknown status: ");
            sb.append(readByte);
            throw new IllegalStateException(sb.toString());
        }

        public MessageSnapshot[] newArray(int i) {
            return new MessageSnapshot[i];
        }
    };
    private final int id;
    protected boolean isLargeFile;

    public interface IWarnMessageSnapshot {
        MessageSnapshot turnToPending();
    }

    public static class NoFieldException extends IllegalStateException {
        NoFieldException(String str, MessageSnapshot messageSnapshot) {
            super(FileDownloadUtils.formatString("There isn't a field for '%s' in this message %d %d %s", str, Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus()), messageSnapshot.getClass().getName()));
        }
    }

    public static class StartedMessageSnapshot extends MessageSnapshot {
        public byte getStatus() {
            return 6;
        }

        StartedMessageSnapshot(int i) {
            super(i);
        }

        StartedMessageSnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    public int describeContents() {
        return 0;
    }

    MessageSnapshot(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public Throwable getThrowable() {
        throw new NoFieldException("getThrowable", this);
    }

    public int getRetryingTimes() {
        throw new NoFieldException("getRetryingTimes", this);
    }

    public boolean isResuming() {
        throw new NoFieldException("isResuming", this);
    }

    public String getEtag() {
        throw new NoFieldException("getEtag", this);
    }

    public long getLargeSofarBytes() {
        throw new NoFieldException("getLargeSofarBytes", this);
    }

    public long getLargeTotalBytes() {
        throw new NoFieldException("getLargeTotalBytes", this);
    }

    public int getSmallSofarBytes() {
        throw new NoFieldException("getSmallSofarBytes", this);
    }

    public int getSmallTotalBytes() {
        throw new NoFieldException("getSmallTotalBytes", this);
    }

    public boolean isReusedDownloadedFile() {
        throw new NoFieldException("isReusedDownloadedFile", this);
    }

    public String getFileName() {
        throw new NoFieldException("getFileName", this);
    }

    public boolean isLargeFile() {
        return this.isLargeFile;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isLargeFile ? (byte) 1 : 0);
        parcel.writeByte(getStatus());
        parcel.writeInt(this.id);
    }

    MessageSnapshot(Parcel parcel) {
        this.id = parcel.readInt();
    }
}
