package com.liulishuo.filedownloader.event;

public abstract class IDownloadEvent {
    public Runnable callback = null;
    protected final String id;

    public IDownloadEvent(String str) {
        this.id = str;
    }

    public final String getId() {
        return this.id;
    }
}
