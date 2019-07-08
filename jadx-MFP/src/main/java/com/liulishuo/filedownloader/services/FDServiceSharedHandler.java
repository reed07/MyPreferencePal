package com.liulishuo.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import com.liulishuo.filedownloader.FileDownloadServiceProxy;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService.Stub;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import java.lang.ref.WeakReference;

public class FDServiceSharedHandler extends Stub implements IFileDownloadServiceHandler {
    private final FileDownloadManager downloadManager;
    private final WeakReference<FileDownloadService> wService;

    public interface FileDownloadServiceSharedConnection {
        void onConnected(FDServiceSharedHandler fDServiceSharedHandler);

        void onDisconnected();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) {
    }

    public void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) {
    }

    FDServiceSharedHandler(WeakReference<FileDownloadService> weakReference, FileDownloadManager fileDownloadManager) {
        this.wService = weakReference;
        this.downloadManager = fileDownloadManager;
    }

    public boolean checkDownloading(String str, String str2) {
        return this.downloadManager.isDownloading(str, str2);
    }

    public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        this.downloadManager.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean pause(int i) {
        return this.downloadManager.pause(i);
    }

    public void pauseAllTasks() {
        this.downloadManager.pauseAll();
    }

    public boolean setMaxNetworkThreadCount(int i) {
        return this.downloadManager.setMaxNetworkThreadCount(i);
    }

    public long getSofar(int i) {
        return this.downloadManager.getSoFar(i);
    }

    public long getTotal(int i) {
        return this.downloadManager.getTotal(i);
    }

    public byte getStatus(int i) {
        return this.downloadManager.getStatus(i);
    }

    public boolean isIdle() {
        return this.downloadManager.isIdle();
    }

    public void startForeground(int i, Notification notification) {
        WeakReference<FileDownloadService> weakReference = this.wService;
        if (weakReference != null && weakReference.get() != null) {
            ((FileDownloadService) this.wService.get()).startForeground(i, notification);
        }
    }

    public void stopForeground(boolean z) {
        WeakReference<FileDownloadService> weakReference = this.wService;
        if (weakReference != null && weakReference.get() != null) {
            ((FileDownloadService) this.wService.get()).stopForeground(z);
        }
    }

    public boolean clearTaskData(int i) {
        return this.downloadManager.clearTaskData(i);
    }

    public void clearAllTaskData() {
        this.downloadManager.clearAllTaskData();
    }

    public void onStartCommand(Intent intent, int i, int i2) {
        FileDownloadServiceProxy.getConnectionListener().onConnected(this);
    }

    public void onDestroy() {
        FileDownloadServiceProxy.getConnectionListener().onDisconnected();
    }
}
