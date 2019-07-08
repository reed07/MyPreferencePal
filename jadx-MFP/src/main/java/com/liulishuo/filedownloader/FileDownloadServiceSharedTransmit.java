package com.liulishuo.filedownloader;

import android.content.Context;
import android.content.Intent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler;
import com.liulishuo.filedownloader.services.FDServiceSharedHandler.FileDownloadServiceSharedConnection;
import com.liulishuo.filedownloader.services.FileDownloadService.SharedMainProcessService;
import com.liulishuo.filedownloader.util.DownloadServiceNotConnectedHelper;
import java.util.ArrayList;
import java.util.List;

class FileDownloadServiceSharedTransmit implements IFileDownloadServiceProxy, FileDownloadServiceSharedConnection {
    private static final Class<?> SERVICE_CLASS = SharedMainProcessService.class;
    private final ArrayList<Runnable> connectedRunnableList = new ArrayList<>();
    private FDServiceSharedHandler handler;

    FileDownloadServiceSharedTransmit() {
    }

    public boolean start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.start(str, str2, z);
        }
        this.handler.start(str, str2, z, i, i2, i3, z2, fileDownloadHeader, z3);
        return true;
    }

    public boolean pause(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.pause(i);
        }
        return this.handler.pause(i);
    }

    public byte getStatus(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.getStatus(i);
        }
        return this.handler.getStatus(i);
    }

    public void pauseAllTasks() {
        if (!isConnected()) {
            DownloadServiceNotConnectedHelper.pauseAllTasks();
        } else {
            this.handler.pauseAllTasks();
        }
    }

    public boolean isConnected() {
        return this.handler != null;
    }

    public void bindStartByContext(Context context) {
        bindStartByContext(context, null);
    }

    public void bindStartByContext(Context context, Runnable runnable) {
        if (runnable != null && !this.connectedRunnableList.contains(runnable)) {
            this.connectedRunnableList.add(runnable);
        }
        context.startService(new Intent(context, SERVICE_CLASS));
    }

    public boolean clearTaskData(int i) {
        if (!isConnected()) {
            return DownloadServiceNotConnectedHelper.clearTaskData(i);
        }
        return this.handler.clearTaskData(i);
    }

    public void onConnected(FDServiceSharedHandler fDServiceSharedHandler) {
        this.handler = fDServiceSharedHandler;
        List<Runnable> list = (List) this.connectedRunnableList.clone();
        this.connectedRunnableList.clear();
        for (Runnable run : list) {
            run.run();
        }
        FileDownloadEventPool.getImpl().asyncPublishInNewThread(new DownloadServiceConnectChangedEvent(ConnectStatus.connected, SERVICE_CLASS));
    }

    public void onDisconnected() {
        this.handler = null;
        FileDownloadEventPool.getImpl().asyncPublishInNewThread(new DownloadServiceConnectChangedEvent(ConnectStatus.disconnected, SERVICE_CLASS));
    }
}
