package com.liulishuo.filedownloader.download;

import com.google.common.net.HttpHeaders;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.net.ProtocolException;

public class ConnectionProfile {
    final long contentLength;
    final long currentOffset;
    final long endOffset;
    private final boolean isForceNoRange;
    private final boolean isTrialConnect;
    final long startOffset;

    public static class ConnectionProfileBuild {
        public static ConnectionProfile buildTrialConnectionProfile() {
            return new ConnectionProfile();
        }

        public static ConnectionProfile buildTrialConnectionProfileNoRange() {
            ConnectionProfile connectionProfile = new ConnectionProfile(0, 0, 0, 0, true);
            return connectionProfile;
        }

        public static ConnectionProfile buildBeginToEndConnectionProfile(long j) {
            ConnectionProfile connectionProfile = new ConnectionProfile(0, 0, -1, j);
            return connectionProfile;
        }

        public static ConnectionProfile buildToEndConnectionProfile(long j, long j2, long j3) {
            ConnectionProfile connectionProfile = new ConnectionProfile(j, j2, -1, j3);
            return connectionProfile;
        }

        public static ConnectionProfile buildConnectionProfile(long j, long j2, long j3, long j4) {
            ConnectionProfile connectionProfile = new ConnectionProfile(j, j2, j3, j4);
            return connectionProfile;
        }
    }

    private ConnectionProfile() {
        this.startOffset = 0;
        this.currentOffset = 0;
        this.endOffset = 0;
        this.contentLength = 0;
        this.isForceNoRange = false;
        this.isTrialConnect = true;
    }

    private ConnectionProfile(long j, long j2, long j3, long j4) {
        this(j, j2, j3, j4, false);
    }

    private ConnectionProfile(long j, long j2, long j3, long j4, boolean z) {
        if (!(j == 0 && j3 == 0) && z) {
            throw new IllegalArgumentException();
        }
        this.startOffset = j;
        this.currentOffset = j2;
        this.endOffset = j3;
        this.contentLength = j4;
        this.isForceNoRange = z;
        this.isTrialConnect = false;
    }

    public void processProfile(FileDownloadConnection fileDownloadConnection) throws ProtocolException {
        String str;
        if (!this.isForceNoRange) {
            if (this.isTrialConnect && FileDownloadProperties.getImpl().trialConnectionHeadMethod) {
                fileDownloadConnection.setRequestMethod("HEAD");
            }
            if (this.endOffset == -1) {
                str = FileDownloadUtils.formatString("bytes=%d-", Long.valueOf(this.currentOffset));
            } else {
                str = FileDownloadUtils.formatString("bytes=%d-%d", Long.valueOf(this.currentOffset), Long.valueOf(this.endOffset));
            }
            fileDownloadConnection.addHeader(HttpHeaders.RANGE, str);
        }
    }

    public String toString() {
        return FileDownloadUtils.formatString("range[%d, %d) current offset[%d]", Long.valueOf(this.startOffset), Long.valueOf(this.endOffset), Long.valueOf(this.currentOffset));
    }
}
