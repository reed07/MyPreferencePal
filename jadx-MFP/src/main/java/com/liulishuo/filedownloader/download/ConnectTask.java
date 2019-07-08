package com.liulishuo.filedownloader.download;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.connection.RedirectHandler;
import com.liulishuo.filedownloader.download.ConnectionProfile.ConnectionProfileBuild;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConnectTask {
    final int downloadId;
    private String etag;
    final FileDownloadHeader header;
    private ConnectionProfile profile;
    private List<String> redirectedUrlList;
    private Map<String, List<String>> requestHeader;
    final String url;

    static class Builder {
        private ConnectionProfile connectionProfile;
        private Integer downloadId;
        private String etag;
        private FileDownloadHeader header;
        private String url;

        Builder() {
        }

        public Builder setDownloadId(int i) {
            this.downloadId = Integer.valueOf(i);
            return this;
        }

        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }

        public Builder setEtag(String str) {
            this.etag = str;
            return this;
        }

        public Builder setHeader(FileDownloadHeader fileDownloadHeader) {
            this.header = fileDownloadHeader;
            return this;
        }

        public Builder setConnectionProfile(ConnectionProfile connectionProfile2) {
            this.connectionProfile = connectionProfile2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public ConnectTask build() {
            Integer num = this.downloadId;
            if (num != null) {
                ConnectionProfile connectionProfile2 = this.connectionProfile;
                if (!(connectionProfile2 == null || this.url == null)) {
                    ConnectTask connectTask = new ConnectTask(connectionProfile2, num.intValue(), this.url, this.etag, this.header);
                    return connectTask;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    private ConnectTask(ConnectionProfile connectionProfile, int i, String str, String str2, FileDownloadHeader fileDownloadHeader) {
        this.downloadId = i;
        this.url = str;
        this.etag = str2;
        this.header = fileDownloadHeader;
        this.profile = connectionProfile;
    }

    /* access modifiers changed from: 0000 */
    public void updateConnectionProfile(long j) {
        if (j == this.profile.currentOffset) {
            FileDownloadLog.w(this, "no data download, no need to update", new Object[0]);
            return;
        }
        long j2 = j;
        this.profile = ConnectionProfileBuild.buildConnectionProfile(this.profile.startOffset, j2, this.profile.endOffset, this.profile.contentLength - (j - this.profile.currentOffset));
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.i(this, "after update profile:%s", this.profile);
        }
    }

    /* access modifiers changed from: 0000 */
    public FileDownloadConnection connect() throws IOException, IllegalAccessException {
        FileDownloadConnection createConnection = CustomComponentHolder.getImpl().createConnection(this.url);
        addUserRequiredHeader(createConnection);
        addRangeHeader(createConnection);
        fixNeededHeader(createConnection);
        this.requestHeader = createConnection.getRequestHeaderFields();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "<---- %s request header %s", Integer.valueOf(this.downloadId), this.requestHeader);
        }
        createConnection.execute();
        this.redirectedUrlList = new ArrayList();
        FileDownloadConnection process = RedirectHandler.process(this.requestHeader, createConnection, this.redirectedUrlList);
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "----> %s response header %s", Integer.valueOf(this.downloadId), process.getResponseHeaderFields());
        }
        return process;
    }

    private void addUserRequiredHeader(FileDownloadConnection fileDownloadConnection) {
        FileDownloadHeader fileDownloadHeader = this.header;
        if (fileDownloadHeader != null) {
            HashMap headers = fileDownloadHeader.getHeaders();
            if (headers != null) {
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.v(this, "%d add outside header: %s", Integer.valueOf(this.downloadId), headers);
                }
                for (Entry entry : headers.entrySet()) {
                    String str = (String) entry.getKey();
                    List<String> list = (List) entry.getValue();
                    if (list != null) {
                        for (String addHeader : list) {
                            fileDownloadConnection.addHeader(str, addHeader);
                        }
                    }
                }
            }
        }
    }

    private void addRangeHeader(FileDownloadConnection fileDownloadConnection) throws ProtocolException {
        if (!fileDownloadConnection.dispatchAddResumeOffset(this.etag, this.profile.startOffset)) {
            if (!TextUtils.isEmpty(this.etag)) {
                fileDownloadConnection.addHeader(HttpHeaders.IF_MATCH, this.etag);
            }
            this.profile.processProfile(fileDownloadConnection);
        }
    }

    private void fixNeededHeader(FileDownloadConnection fileDownloadConnection) {
        FileDownloadHeader fileDownloadHeader = this.header;
        if (fileDownloadHeader == null || fileDownloadHeader.getHeaders().get("User-Agent") == null) {
            fileDownloadConnection.addHeader("User-Agent", FileDownloadUtils.defaultUserAgent());
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isRangeNotFromBeginning() {
        return this.profile.currentOffset > 0;
    }

    /* access modifiers changed from: 0000 */
    public String getFinalRedirectedUrl() {
        List<String> list = this.redirectedUrlList;
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<String> list2 = this.redirectedUrlList;
        return (String) list2.get(list2.size() - 1);
    }

    public Map<String, List<String>> getRequestHeader() {
        return this.requestHeader;
    }

    public ConnectionProfile getProfile() {
        return this.profile;
    }
}
