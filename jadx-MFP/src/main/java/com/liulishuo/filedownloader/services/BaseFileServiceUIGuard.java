package com.liulishuo.filedownloader.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.liulishuo.filedownloader.FileDownloadEventPool;
import com.liulishuo.filedownloader.IFileDownloadServiceProxy;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent;
import com.liulishuo.filedownloader.event.DownloadServiceConnectChangedEvent.ConnectStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class BaseFileServiceUIGuard<CALLBACK extends Binder, INTERFACE extends IInterface> implements ServiceConnection, IFileDownloadServiceProxy {
    private final List<Context> bindContexts = new ArrayList();
    private final CALLBACK callback;
    private final ArrayList<Runnable> connectedRunnableList = new ArrayList<>();
    private volatile INTERFACE service;
    private final Class<?> serviceClass;
    private final HashMap<String, Object> uiCacheMap = new HashMap<>();

    /* access modifiers changed from: protected */
    public abstract INTERFACE asInterface(IBinder iBinder);

    /* access modifiers changed from: protected */
    public abstract CALLBACK createCallback();

    /* access modifiers changed from: protected */
    public abstract void registerCallback(INTERFACE interfaceR, CALLBACK callback2) throws RemoteException;

    /* access modifiers changed from: protected */
    public abstract void unregisterCallback(INTERFACE interfaceR, CALLBACK callback2) throws RemoteException;

    /* access modifiers changed from: protected */
    public INTERFACE getService() {
        return this.service;
    }

    protected BaseFileServiceUIGuard(Class<?> cls) {
        this.serviceClass = cls;
        this.callback = createCallback();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.service = asInterface(iBinder);
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "onServiceConnected %s %s", componentName, this.service);
        }
        try {
            registerCallback(this.service, this.callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        List<Runnable> list = (List) this.connectedRunnableList.clone();
        this.connectedRunnableList.clear();
        for (Runnable run : list) {
            run.run();
        }
        FileDownloadEventPool.getImpl().asyncPublishInNewThread(new DownloadServiceConnectChangedEvent(ConnectStatus.connected, this.serviceClass));
    }

    public void onServiceDisconnected(ComponentName componentName) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "onServiceDisconnected %s %s", componentName, this.service);
        }
        releaseConnect(true);
    }

    private void releaseConnect(boolean z) {
        if (!z && this.service != null) {
            try {
                unregisterCallback(this.service, this.callback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "release connect resources %s", this.service);
        }
        this.service = null;
        FileDownloadEventPool.getImpl().asyncPublishInNewThread(new DownloadServiceConnectChangedEvent(z ? ConnectStatus.lost : ConnectStatus.disconnected, this.serviceClass));
    }

    public void bindStartByContext(Context context) {
        bindStartByContext(context, null);
    }

    public void bindStartByContext(Context context, Runnable runnable) {
        if (!FileDownloadUtils.isDownloaderProcess(context)) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "bindStartByContext %s", context.getClass().getSimpleName());
            }
            Intent intent = new Intent(context, this.serviceClass);
            if (runnable != null && !this.connectedRunnableList.contains(runnable)) {
                this.connectedRunnableList.add(runnable);
            }
            if (!this.bindContexts.contains(context)) {
                this.bindContexts.add(context);
            }
            context.bindService(intent, this, 1);
            context.startService(intent);
            return;
        }
        throw new IllegalStateException("Fatal-Exception: You can't bind the FileDownloadService in :filedownloader process.\n It's the invalid operation and is likely to cause unexpected problems.\n Maybe you want to use non-separate process mode for FileDownloader, More detail about non-separate mode, please move to wiki manually: https://github.com/lingochamp/FileDownloader/wiki/filedownloader.properties");
    }

    public boolean isConnected() {
        return getService() != null;
    }
}
