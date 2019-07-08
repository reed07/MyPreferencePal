package com.liulishuo.filedownloader;

import android.os.IBinder;
import android.os.RemoteException;
import com.liulishuo.filedownloader.i.IFileDownloadIPCCallback.Stub;
import com.liulishuo.filedownloader.i.IFileDownloadIPCService;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.BaseFileServiceUIGuard;
import com.liulishuo.filedownloader.services.FileDownloadService.SeparateProcessService;
import com.liulishuo.filedownloader.util.DownloadServiceNotConnectedHelper;

class FileDownloadServiceUIGuard extends BaseFileServiceUIGuard<FileDownloadServiceCallback, IFileDownloadIPCService> {

    protected static class FileDownloadServiceCallback extends Stub {
        protected FileDownloadServiceCallback() {
        }

        public void callback(MessageSnapshot messageSnapshot) throws RemoteException {
            MessageSnapshotFlow.getImpl().inflow(messageSnapshot);
        }
    }

    FileDownloadServiceUIGuard() {
        super(SeparateProcessService.class);
    }

    /* access modifiers changed from: protected */
    public FileDownloadServiceCallback createCallback() {
        return new FileDownloadServiceCallback();
    }

    /* access modifiers changed from: protected */
    public IFileDownloadIPCService asInterface(IBinder iBinder) {
        return IFileDownloadIPCService.Stub.asInterface(iBinder);
    }

    /* access modifiers changed from: protected */
    public void registerCallback(IFileDownloadIPCService iFileDownloadIPCService, FileDownloadServiceCallback fileDownloadServiceCallback) throws RemoteException {
        iFileDownloadIPCService.registerCallback(fileDownloadServiceCallback);
    }

    /* access modifiers changed from: protected */
    public void unregisterCallback(IFileDownloadIPCService iFileDownloadIPCService, FileDownloadServiceCallback fileDownloadServiceCallback) throws RemoteException {
        iFileDownloadIPCService.unregisterCallback(fileDownloadServiceCallback);
    }

    public boolean start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.start(str, str2, z);
        }
        try {
            ((IFileDownloadIPCService) getService()).start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean pause(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.pause(i);
        }
        try {
            return ((IFileDownloadIPCService) getService()).pause(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public byte getStatus(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.getStatus(i);
        }
        byte b = 0;
        try {
            b = ((IFileDownloadIPCService) getService()).getStatus(i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return b;
    }

    public void pauseAllTasks() {
        if (!isConnected()) {
            DownloadServiceNotConnectedHelper.pauseAllTasks();
            return;
        }
        try {
            ((IFileDownloadIPCService) getService()).pauseAllTasks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean clearTaskData(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.clearTaskData(i);
        }
        try {
            return ((IFileDownloadIPCService) getService()).clearTaskData(i);
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }
}
