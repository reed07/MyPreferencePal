package com.liulishuo.filedownloader;

import android.content.Context;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler.FileDownloadServiceSharedConnection;
import com.liulishuo.filedownloader.util.FileDownloadProperties;

public class FileDownloadServiceProxy implements IFileDownloadServiceProxy {
    private final IFileDownloadServiceProxy handler;

    private static final class HolderClass {
        /* access modifiers changed from: private */
        public static final FileDownloadServiceProxy INSTANCE = new FileDownloadServiceProxy();

        private HolderClass() {
        }
    }

    public static FileDownloadServiceProxy getImpl() {
        return HolderClass.INSTANCE;
    }

    public static FileDownloadServiceSharedConnection getConnectionListener() {
        if (getImpl().handler instanceof FileDownloadServiceSharedTransmit) {
            return (FileDownloadServiceSharedConnection) getImpl().handler;
        }
        return null;
    }

    private FileDownloadServiceProxy() {
        this.handler = FileDownloadProperties.getImpl().processNonSeparate ? new FileDownloadServiceSharedTransmit() : new FileDownloadServiceUIGuard();
    }

    public boolean start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        return this.handler.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
    }

    public boolean pause(int i) {
        return this.handler.pause(i);
    }

    public byte getStatus(int i) {
        return this.handler.getStatus(i);
    }

    public void pauseAllTasks() {
        this.handler.pauseAllTasks();
    }

    public boolean isConnected() {
        return this.handler.isConnected();
    }

    public void bindStartByContext(Context context) {
        this.handler.bindStartByContext(context);
    }

    public void bindStartByContext(Context context, Runnable runnable) {
        this.handler.bindStartByContext(context, runnable);
    }

    public boolean clearTaskData(int i) {
        return this.handler.clearTaskData(i);
    }
}
