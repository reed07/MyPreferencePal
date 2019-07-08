package com.liulishuo.filedownloader.event;

public class DownloadServiceConnectChangedEvent extends IDownloadEvent {
    private final Class<?> serviceClass;
    private final ConnectStatus status;

    public enum ConnectStatus {
        connected,
        disconnected,
        lost
    }

    public DownloadServiceConnectChangedEvent(ConnectStatus connectStatus, Class<?> cls) {
        super("event.service.connect.changed");
        this.status = connectStatus;
        this.serviceClass = cls;
    }

    public ConnectStatus getStatus() {
        return this.status;
    }
}
