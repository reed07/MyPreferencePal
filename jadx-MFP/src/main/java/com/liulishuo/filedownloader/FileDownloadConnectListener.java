package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.event.IDownloadEvent;
import com.liulishuo.filedownloader.event.IDownloadListener;

public abstract class FileDownloadConnectListener extends IDownloadListener {
    private ConnectStatus mConnectStatus;

    public abstract void connected();

    public abstract void disconnected();

    public boolean callback(IDownloadEvent iDownloadEvent) {
        if (iDownloadEvent instanceof DownloadServiceConnectChangedEvent) {
            this.mConnectStatus = ((DownloadServiceConnectChangedEvent) iDownloadEvent).getStatus();
            if (this.mConnectStatus == ConnectStatus.connected) {
                connected();
            } else {
                disconnected();
            }
        }
        return false;
    }

    public ConnectStatus getConnectStatus() {
        return this.mConnectStatus;
    }
}
