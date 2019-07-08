package com.liulishuo.filedownloader.database;

import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.List;

public interface FileDownloadDatabase {

    public interface Maintainer extends Iterable<FileDownloadModel> {
        void changeFileDownloadModelId(int i, FileDownloadModel fileDownloadModel);

        void onFinishMaintain();

        void onRefreshedValidData(FileDownloadModel fileDownloadModel);

        void onRemovedInvalidData(FileDownloadModel fileDownloadModel);
    }

    void clear();

    FileDownloadModel find(int i);

    List<ConnectionModel> findConnectionModel(int i);

    void insertConnectionModel(ConnectionModel connectionModel);

    Maintainer maintainer();

    void onTaskStart(int i);

    boolean remove(int i);

    void removeConnections(int i);

    void update(FileDownloadModel fileDownloadModel);

    void updateCompleted(int i, long j);

    void updateConnected(int i, long j, String str, String str2);

    void updateConnectionCount(int i, int i2);

    void updateConnectionModel(int i, int i2, long j);

    void updateError(int i, Throwable th, long j);

    void updateOldEtagOverdue(int i, String str, long j, long j2, int i2);

    void updatePause(int i, long j);

    void updatePending(int i);

    void updateProgress(int i, long j);

    void updateRetry(int i, Throwable th);
}
