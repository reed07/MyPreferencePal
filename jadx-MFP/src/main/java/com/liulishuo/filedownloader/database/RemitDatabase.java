package com.liulishuo.filedownloader.database;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.liulishuo.filedownloader.database.FileDownloadDatabase.Maintainer;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadHelper.DatabaseCustomMaker;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class RemitDatabase implements FileDownloadDatabase {
    private final NoDatabaseImpl cachedDatabase = new NoDatabaseImpl();
    /* access modifiers changed from: private */
    public final List<Integer> freeToDBIdList = new ArrayList();
    private Handler handler;
    /* access modifiers changed from: private */
    public AtomicInteger handlingId = new AtomicInteger();
    private final long minInterval = FileDownloadProperties.getImpl().downloadMinProgressTime;
    /* access modifiers changed from: private */
    public volatile Thread parkThread;
    private final SqliteDatabaseImpl realDatabase = new SqliteDatabaseImpl();

    public static class Maker implements DatabaseCustomMaker {
        public FileDownloadDatabase customMake() {
            return new RemitDatabase();
        }
    }

    public RemitDatabase() {
        HandlerThread handlerThread = new HandlerThread(FileDownloadUtils.getThreadPoolName("RemitHandoverToDB"));
        handlerThread.start();
        this.handler = new Handler(handlerThread.getLooper(), new Callback() {
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    if (RemitDatabase.this.parkThread != null) {
                        LockSupport.unpark(RemitDatabase.this.parkThread);
                        RemitDatabase.this.parkThread = null;
                    }
                    return false;
                }
                try {
                    RemitDatabase.this.handlingId.set(i);
                    RemitDatabase.this.syncCacheToDB(i);
                    RemitDatabase.this.freeToDBIdList.add(Integer.valueOf(i));
                    return false;
                } finally {
                    RemitDatabase.this.handlingId.set(0);
                    if (RemitDatabase.this.parkThread != null) {
                        LockSupport.unpark(RemitDatabase.this.parkThread);
                        RemitDatabase.this.parkThread = null;
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void syncCacheToDB(int i) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "sync cache to db %d", Integer.valueOf(i));
        }
        this.realDatabase.update(this.cachedDatabase.find(i));
        List<ConnectionModel> findConnectionModel = this.cachedDatabase.findConnectionModel(i);
        this.realDatabase.removeConnections(i);
        for (ConnectionModel insertConnectionModel : findConnectionModel) {
            this.realDatabase.insertConnectionModel(insertConnectionModel);
        }
    }

    private boolean isNoNeedUpdateToRealDB(int i) {
        return !this.freeToDBIdList.contains(Integer.valueOf(i));
    }

    public void onTaskStart(int i) {
        this.handler.sendEmptyMessageDelayed(i, this.minInterval);
    }

    public FileDownloadModel find(int i) {
        return this.cachedDatabase.find(i);
    }

    public List<ConnectionModel> findConnectionModel(int i) {
        return this.cachedDatabase.findConnectionModel(i);
    }

    public void removeConnections(int i) {
        this.cachedDatabase.removeConnections(i);
        if (!isNoNeedUpdateToRealDB(i)) {
            this.realDatabase.removeConnections(i);
        }
    }

    public void insertConnectionModel(ConnectionModel connectionModel) {
        this.cachedDatabase.insertConnectionModel(connectionModel);
        if (!isNoNeedUpdateToRealDB(connectionModel.getId())) {
            this.realDatabase.insertConnectionModel(connectionModel);
        }
    }

    public void updateConnectionModel(int i, int i2, long j) {
        this.cachedDatabase.updateConnectionModel(i, i2, j);
        if (!isNoNeedUpdateToRealDB(i)) {
            this.realDatabase.updateConnectionModel(i, i2, j);
        }
    }

    public void updateProgress(int i, long j) {
        this.cachedDatabase.updateProgress(i, j);
        if (!isNoNeedUpdateToRealDB(i)) {
            this.realDatabase.updateProgress(i, j);
        }
    }

    public void updateConnectionCount(int i, int i2) {
        this.cachedDatabase.updateConnectionCount(i, i2);
        if (!isNoNeedUpdateToRealDB(i)) {
            this.realDatabase.updateConnectionCount(i, i2);
        }
    }

    public void update(FileDownloadModel fileDownloadModel) {
        this.cachedDatabase.update(fileDownloadModel);
        if (!isNoNeedUpdateToRealDB(fileDownloadModel.getId())) {
            this.realDatabase.update(fileDownloadModel);
        }
    }

    public boolean remove(int i) {
        this.realDatabase.remove(i);
        return this.cachedDatabase.remove(i);
    }

    public void clear() {
        this.cachedDatabase.clear();
        this.realDatabase.clear();
    }

    public void updateOldEtagOverdue(int i, String str, long j, long j2, int i2) {
        this.cachedDatabase.updateOldEtagOverdue(i, str, j, j2, i2);
        if (!isNoNeedUpdateToRealDB(i)) {
            this.realDatabase.updateOldEtagOverdue(i, str, j, j2, i2);
        }
    }

    public void updateConnected(int i, long j, String str, String str2) {
        this.cachedDatabase.updateConnected(i, j, str, str2);
        if (!isNoNeedUpdateToRealDB(i)) {
            this.realDatabase.updateConnected(i, j, str, str2);
        }
    }

    public void updatePending(int i) {
        this.cachedDatabase.updatePending(i);
        if (!isNoNeedUpdateToRealDB(i)) {
            this.realDatabase.updatePending(i);
        }
    }

    public void updateRetry(int i, Throwable th) {
        this.cachedDatabase.updateRetry(i, th);
        if (!isNoNeedUpdateToRealDB(i)) {
            this.realDatabase.updateRetry(i, th);
        }
    }

    private void ensureCacheToDB(int i) {
        this.handler.removeMessages(i);
        if (this.handlingId.get() == i) {
            this.parkThread = Thread.currentThread();
            this.handler.sendEmptyMessage(0);
            LockSupport.park();
            return;
        }
        syncCacheToDB(i);
    }

    public void updateError(int i, Throwable th, long j) {
        this.cachedDatabase.updateError(i, th, j);
        if (isNoNeedUpdateToRealDB(i)) {
            ensureCacheToDB(i);
        }
        this.realDatabase.updateError(i, th, j);
        this.freeToDBIdList.remove(Integer.valueOf(i));
    }

    public void updateCompleted(int i, long j) {
        this.cachedDatabase.updateCompleted(i, j);
        if (isNoNeedUpdateToRealDB(i)) {
            this.handler.removeMessages(i);
            if (this.handlingId.get() == i) {
                this.parkThread = Thread.currentThread();
                this.handler.sendEmptyMessage(0);
                LockSupport.park();
                this.realDatabase.updateCompleted(i, j);
            }
        } else {
            this.realDatabase.updateCompleted(i, j);
        }
        this.freeToDBIdList.remove(Integer.valueOf(i));
    }

    public void updatePause(int i, long j) {
        this.cachedDatabase.updatePause(i, j);
        if (isNoNeedUpdateToRealDB(i)) {
            ensureCacheToDB(i);
        }
        this.realDatabase.updatePause(i, j);
        this.freeToDBIdList.remove(Integer.valueOf(i));
    }

    public Maintainer maintainer() {
        return this.realDatabase.maintainer(this.cachedDatabase.downloaderModelMap, this.cachedDatabase.connectionModelListMap);
    }
}
