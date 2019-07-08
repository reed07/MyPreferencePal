package com.liulishuo.filedownloader.database;

import android.util.SparseArray;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadHelper.DatabaseCustomMaker;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoDatabaseImpl implements FileDownloadDatabase {
    final SparseArray<List<ConnectionModel>> connectionModelListMap = new SparseArray<>();
    final SparseArray<FileDownloadModel> downloaderModelMap = new SparseArray<>();

    class Maintainer implements com.liulishuo.filedownloader.database.FileDownloadDatabase.Maintainer {
        public void changeFileDownloadModelId(int i, FileDownloadModel fileDownloadModel) {
        }

        public void onFinishMaintain() {
        }

        public void onRefreshedValidData(FileDownloadModel fileDownloadModel) {
        }

        public void onRemovedInvalidData(FileDownloadModel fileDownloadModel) {
        }

        Maintainer() {
        }

        public Iterator<FileDownloadModel> iterator() {
            return new MaintainerIterator();
        }
    }

    class MaintainerIterator implements Iterator<FileDownloadModel> {
        public boolean hasNext() {
            return false;
        }

        public FileDownloadModel next() {
            return null;
        }

        public void remove() {
        }

        MaintainerIterator() {
        }
    }

    public static class Maker implements DatabaseCustomMaker {
        public FileDownloadDatabase customMake() {
            return new NoDatabaseImpl();
        }
    }

    public void onTaskStart(int i) {
    }

    public void updateConnected(int i, long j, String str, String str2) {
    }

    public void updateConnectionCount(int i, int i2) {
    }

    public void updateError(int i, Throwable th, long j) {
    }

    public void updateOldEtagOverdue(int i, String str, long j, long j2, int i2) {
    }

    public void updatePause(int i, long j) {
    }

    public void updatePending(int i) {
    }

    public void updateProgress(int i, long j) {
    }

    public void updateRetry(int i, Throwable th) {
    }

    public FileDownloadModel find(int i) {
        return (FileDownloadModel) this.downloaderModelMap.get(i);
    }

    public List<ConnectionModel> findConnectionModel(int i) {
        ArrayList arrayList = new ArrayList();
        List list = (List) this.connectionModelListMap.get(i);
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public void removeConnections(int i) {
        this.connectionModelListMap.remove(i);
    }

    public void insertConnectionModel(ConnectionModel connectionModel) {
        int id = connectionModel.getId();
        List list = (List) this.connectionModelListMap.get(id);
        if (list == null) {
            list = new ArrayList();
            this.connectionModelListMap.put(id, list);
        }
        list.add(connectionModel);
    }

    public void updateConnectionModel(int i, int i2, long j) {
        List<ConnectionModel> list = (List) this.connectionModelListMap.get(i);
        if (list != null) {
            for (ConnectionModel connectionModel : list) {
                if (connectionModel.getIndex() == i2) {
                    connectionModel.setCurrentOffset(j);
                    return;
                }
            }
        }
    }

    public void insert(FileDownloadModel fileDownloadModel) {
        this.downloaderModelMap.put(fileDownloadModel.getId(), fileDownloadModel);
    }

    public void update(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            FileDownloadLog.w(this, "update but model == null!", new Object[0]);
            return;
        }
        if (find(fileDownloadModel.getId()) != null) {
            this.downloaderModelMap.remove(fileDownloadModel.getId());
            this.downloaderModelMap.put(fileDownloadModel.getId(), fileDownloadModel);
        } else {
            insert(fileDownloadModel);
        }
    }

    public boolean remove(int i) {
        this.downloaderModelMap.remove(i);
        return true;
    }

    public void clear() {
        this.downloaderModelMap.clear();
    }

    public void updateCompleted(int i, long j) {
        remove(i);
    }

    public com.liulishuo.filedownloader.database.FileDownloadDatabase.Maintainer maintainer() {
        return new Maintainer();
    }
}
