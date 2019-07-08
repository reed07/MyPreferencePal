package com.brightcove.player.network;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.brightcove.player.offline.R;
import com.brightcove.player.util.Convert;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DownloadStatus implements Parcelable {
    public static final Creator<DownloadStatus> CREATOR = new Creator<DownloadStatus>() {
        public DownloadStatus createFromParcel(Parcel parcel) {
            DownloadStatus downloadStatus = new DownloadStatus();
            downloadStatus.setCode(parcel.readInt());
            downloadStatus.setReason(parcel.readInt());
            downloadStatus.bytesDownloaded = parcel.readLong();
            downloadStatus.actualSize = parcel.readLong();
            downloadStatus.estimatedSize = parcel.readLong();
            downloadStatus.time = parcel.readLong();
            return downloadStatus;
        }

        public DownloadStatus[] newArray(int i) {
            return new DownloadStatus[i];
        }
    };
    public static final int ERROR_CANNOT_RESUME = 1008;
    public static final int ERROR_DEVICE_NOT_FOUND = 1007;
    public static final int ERROR_FILE_ALREADY_EXISTS = 1009;
    public static final int ERROR_FILE_ERROR = 1001;
    public static final int ERROR_HTTP_DATA_ERROR = 1004;
    public static final int ERROR_INSUFFICIENT_SPACE = 1006;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_TOO_MANY_REDIRECTS = 1005;
    public static final int ERROR_UNHANDLED_HTTP_CODE = 1002;
    public static final int ERROR_UNKNOWN = 1000;
    public static final int PAUSED_QUEUED_FOR_WIFI = 3;
    public static final int PAUSED_UNKNOWN = 4;
    public static final int PAUSED_WAITING_FOR_NETWORK = 2;
    public static final int PAUSED_WAITING_TO_RETRY = 1;
    public static final int STATUS_CANCELLING = -2;
    public static final int STATUS_COMPLETE = 8;
    public static final int STATUS_DELETING = -3;
    public static final int STATUS_DOWNLOADING = 2;
    public static final int STATUS_FAILED = 16;
    public static final int STATUS_NOT_QUEUED = 0;
    public static final int STATUS_PAUSED = -4;
    public static final int STATUS_PENDING = 1;
    public static final int STATUS_QUEUEING = -1;
    public static final int STATUS_RETRY = 4;
    long actualSize;
    long bytesDownloaded;
    int code = 0;
    long estimatedSize;
    int reason = 0;
    long time;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReasonCode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StatusCode {
    }

    public int describeContents() {
        return 0;
    }

    public static DownloadStatus createForInvalidDownloadsFile() {
        DownloadStatus downloadStatus = new DownloadStatus();
        downloadStatus.setCode(0);
        downloadStatus.setReason(ERROR_DEVICE_NOT_FOUND);
        return downloadStatus;
    }

    public String toString() {
        return Convert.toJsonString(this);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof DownloadStatus)) {
            return false;
        }
        DownloadStatus downloadStatus = (DownloadStatus) obj;
        if (this.code == downloadStatus.code && this.reason == downloadStatus.reason && this.bytesDownloaded == downloadStatus.bytesDownloaded && this.actualSize == downloadStatus.actualSize && this.estimatedSize == downloadStatus.estimatedSize) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int i = this.code + this.reason;
        long j = this.bytesDownloaded;
        int i2 = i + ((int) (j ^ (j >>> 32)));
        long j2 = this.actualSize;
        return i2 + ((int) (j2 ^ (j2 >>> 32)));
    }

    public boolean isMarkedForDeletion() {
        int i = this.code;
        return i == -2 || i == -3;
    }

    public int getStatusMessage() {
        return toStatusMessage(this.code);
    }

    public static int toStatusMessage(int i) {
        if (i == 4) {
            return R.string.odrm_download_waiting_retry;
        }
        if (i == 8) {
            return R.string.odrm_download_complete;
        }
        if (i == 16) {
            return R.string.odrm_download_failed;
        }
        switch (i) {
            case -4:
                return R.string.odrm_download_paused;
            case -3:
                return R.string.odrm_download_deleting;
            case -2:
                return R.string.odrm_download_cancelling;
            case -1:
                return R.string.odrm_download_queuing;
            default:
                switch (i) {
                    case 1:
                        return R.string.odrm_download_pending;
                    case 2:
                        return R.string.odrm_download_running;
                    default:
                        return R.string.odrm_download_not_queued;
                }
        }
    }

    public int getReasonMessage() {
        return toReasonMessage(this.reason);
    }

    public static int toReasonMessage(int i) {
        switch (i) {
            case 1:
                return R.string.odrm_paused_waiting_to_retry;
            case 2:
                return R.string.odrm_paused_waiting_for_network;
            case 3:
                return R.string.odrm_paused_waiting_for_wifi;
            case 4:
                return R.string.odrm_paused_unknown;
            default:
                switch (i) {
                    case 1000:
                        return R.string.odrm_error_unknown;
                    case 1001:
                        return R.string.odrm_error_file_error;
                    case 1002:
                        return R.string.odrm_error_unhandled_http_code;
                    default:
                        switch (i) {
                            case 1004:
                                return R.string.odrm_error_http_data_error;
                            case ERROR_TOO_MANY_REDIRECTS /*1005*/:
                                return R.string.odrm_error_too_many_redirects;
                            case 1006:
                                return R.string.odrm_error_insufficient_space;
                            case ERROR_DEVICE_NOT_FOUND /*1007*/:
                                return R.string.odrm_error_device_not_found;
                            case ERROR_CANNOT_RESUME /*1008*/:
                                return R.string.odrm_error_cannot_resume;
                            case ERROR_FILE_ALREADY_EXISTS /*1009*/:
                                return R.string.odrm_error_file_already_exists;
                            default:
                                return R.string.odrm_download_pending;
                        }
                }
        }
    }

    public long getTime() {
        return this.time;
    }

    public int getCode() {
        return this.code;
    }

    /* access modifiers changed from: 0000 */
    public void setCode(int i) {
        if (i == 4) {
            this.code = 4;
        } else if (i == 8) {
            this.code = 8;
        } else if (i != 16) {
            switch (i) {
                case 1:
                    this.code = 1;
                    return;
                case 2:
                    this.code = 2;
                    return;
                default:
                    this.code = i;
                    return;
            }
        } else {
            this.code = 16;
        }
    }

    /* access modifiers changed from: 0000 */
    public void setReason(int i) {
        this.reason = i;
    }

    public int getReason() {
        return this.reason;
    }

    public long getBytesDownloaded() {
        return this.bytesDownloaded;
    }

    public long getActualSize() {
        return this.actualSize;
    }

    public long getEstimatedSize() {
        return this.estimatedSize;
    }

    public long getMaxSize() {
        return Math.max(this.actualSize, this.estimatedSize);
    }

    public double getProgress() {
        long maxSize = getMaxSize();
        if (maxSize == 0) {
            return Double.NaN;
        }
        return (((double) this.bytesDownloaded) * 100.0d) / ((double) maxSize);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeInt(this.reason);
        parcel.writeLong(this.bytesDownloaded);
        parcel.writeLong(this.actualSize);
        parcel.writeLong(this.estimatedSize);
        parcel.writeLong(this.time);
    }
}
