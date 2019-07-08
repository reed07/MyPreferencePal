package com.liulishuo.filedownloader.services;

import android.app.Notification;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService.Stub;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow.MessageReceiver;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.lang.ref.WeakReference;

public class FDServiceSeparateHandler extends Stub implements MessageReceiver, IFileDownloadServiceHandler {
    private final RemoteCallbackList<IFileDownloadIPCCallback> callbackList = new RemoteCallbackList<>();
    private final FileDownloadManager downloadManager;
    private final WeakReference<FileDownloadService> wService;

    public IBinder onBind(Intent intent) {
        return this;
    }

    public void onStartCommand(Intent intent, int i, int i2) {
    }

    private synchronized int callback(MessageSnapshot messageSnapshot) {
        int beginBroadcast;
        RemoteCallbackList<IFileDownloadIPCCallback> remoteCallbackList;
        beginBroadcast = this.callbackList.beginBroadcast();
        int i = 0;
        while (i < beginBroadcast) {
            try {
                ((IFileDownloadIPCCallback) this.callbackList.getBroadcastItem(i)).callback(messageSnapshot);
                i++;
            } catch (RemoteException e) {
                try {
                    FileDownloadLog.e(this, e, "callback error", new Object[0]);
                    remoteCallbackList = this.callbackList;
                } catch (Throwable th) {
                    this.callbackList.finishBroadcast();
                    throw th;
                }
            }
        }
        remoteCallbackList = this.callbackList;
        remoteCallbackList.finishBroadcast();
        return beginBroadcast;
    }

    FDServiceSeparateHandler(WeakReference<FileDownloadService> weakReference, FileDownloadManager fileDownloadManager) {
        this.wService = weakReference;
        this.downloadManager = fileDownloadManager;
        MessageSnapshotFlow.getImpl().setReceiver(this);
    }

    public void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
        this.callbackList.register(iFileDownloadIPCCallback);
    }

    public void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
        this.callbackList.unregister(iFileDownloadIPCCallback);
    }

    public boolean checkDownloading(String str, String str2) throws RemoteException {
        return this.downloadManager.isDownloading(str, str2);
    }

    public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException {
        this.downloadManager.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean pause(int i) throws RemoteException {
        return this.downloadManager.pause(i);
    }

    public void pauseAllTasks() throws RemoteException {
        this.downloadManager.pauseAll();
    }

    public boolean setMaxNetworkThreadCount(int i) throws RemoteException {
        return this.downloadManager.setMaxNetworkThreadCount(i);
    }

    public long getSofar(int i) throws RemoteException {
        return this.downloadManager.getSoFar(i);
    }

    public long getTotal(int i) throws RemoteException {
        return this.downloadManager.getTotal(i);
    }

    public byte getStatus(int i) throws RemoteException {
        return this.downloadManager.getStatus(i);
    }

    public boolean isIdle() throws RemoteException {
        return this.downloadManager.isIdle();
    }

    public void startForeground(int i, Notification notification) throws RemoteException {
        WeakReference<FileDownloadService> weakReference = this.wService;
        if (weakReference != null && weakReference.get() != null) {
            ((FileDownloadService) this.wService.get()).startForeground(i, notification);
        }
    }

    public void stopForeground(boolean z) throws RemoteException {
        WeakReference<FileDownloadService> weakReference = this.wService;
        if (weakReference != null && weakReference.get() != null) {
            ((FileDownloadService) this.wService.get()).stopForeground(z);
        }
    }

    public boolean clearTaskData(int i) throws RemoteException {
        return this.downloadManager.clearTaskData(i);
    }

    public void clearAllTaskData() throws RemoteException {
        this.downloadManager.clearAllTaskData();
    }

    public void onDestroy() {
        MessageSnapshotFlow.getImpl().setReceiver(null);
    }

    public void receive(MessageSnapshot messageSnapshot) {
        callback(messageSnapshot);
    }
}
