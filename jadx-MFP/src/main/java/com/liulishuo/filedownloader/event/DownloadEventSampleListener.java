package com.liulishuo.filedownloader.event;

public class DownloadEventSampleListener extends IDownloadListener {
    private final IEventListener i;

    public interface IEventListener {
        boolean callback(IDownloadEvent iDownloadEvent);
    }

    public boolean callback(IDownloadEvent iDownloadEvent) {
        IEventListener iEventListener = this.i;
        return iEventListener != null && iEventListener.callback(iDownloadEvent);
    }
}
