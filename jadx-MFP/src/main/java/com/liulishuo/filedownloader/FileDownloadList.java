package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileDownloadList {
    private final ArrayList<IRunningTask> mList;

    private static final class HolderClass {
        /* access modifiers changed from: private */
        public static final FileDownloadList INSTANCE = new FileDownloadList();

        private HolderClass() {
        }
    }

    public static FileDownloadList getImpl() {
        return HolderClass.INSTANCE;
    }

    private FileDownloadList() {
        this.mList = new ArrayList<>();
    }

    /* access modifiers changed from: 0000 */
    public int size() {
        return this.mList.size();
    }

    /* access modifiers changed from: 0000 */
    public int count(int i) {
        int i2;
        synchronized (this.mList) {
            Iterator it = this.mList.iterator();
            i2 = 0;
            while (it.hasNext()) {
                if (((IRunningTask) it.next()).is(i)) {
                    i2++;
                }
            }
        }
        return i2;
    }

    public IRunningTask get(int i) {
        synchronized (this.mList) {
            Iterator it = this.mList.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (iRunningTask.is(i)) {
                    return iRunningTask;
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public List<IRunningTask> getReceiveServiceTaskList(int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mList) {
            Iterator it = this.mList.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (iRunningTask.is(i) && !iRunningTask.isOver()) {
                    byte status = iRunningTask.getOrigin().getStatus();
                    if (!(status == 0 || status == 10)) {
                        arrayList.add(iRunningTask);
                    }
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public List<IRunningTask> getDownloadingList(int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mList) {
            Iterator it = this.mList.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (iRunningTask.is(i) && !iRunningTask.isOver()) {
                    arrayList.add(iRunningTask);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public boolean isNotContains(IRunningTask iRunningTask) {
        return this.mList.isEmpty() || !this.mList.contains(iRunningTask);
    }

    /* access modifiers changed from: 0000 */
    public IRunningTask[] copy() {
        IRunningTask[] iRunningTaskArr;
        synchronized (this.mList) {
            iRunningTaskArr = (IRunningTask[]) this.mList.toArray(new IRunningTask[this.mList.size()]);
        }
        return iRunningTaskArr;
    }

    /* access modifiers changed from: 0000 */
    public void divertAndIgnoreDuplicate(List<IRunningTask> list) {
        synchronized (this.mList) {
            Iterator it = this.mList.iterator();
            while (it.hasNext()) {
                IRunningTask iRunningTask = (IRunningTask) it.next();
                if (!list.contains(iRunningTask)) {
                    list.add(iRunningTask);
                }
            }
            this.mList.clear();
        }
    }

    public boolean remove(IRunningTask iRunningTask, MessageSnapshot messageSnapshot) {
        boolean remove;
        byte status = messageSnapshot.getStatus();
        synchronized (this.mList) {
            remove = this.mList.remove(iRunningTask);
        }
        if (FileDownloadLog.NEED_LOG && this.mList.size() == 0) {
            FileDownloadLog.v(this, "remove %s left %d %d", iRunningTask, Byte.valueOf(status), Integer.valueOf(this.mList.size()));
        }
        if (remove) {
            IFileDownloadMessenger messenger = iRunningTask.getMessageHandler().getMessenger();
            switch (status) {
                case -4:
                    messenger.notifyWarn(messageSnapshot);
                    break;
                case -3:
                    messenger.notifyBlockComplete(MessageSnapshotTaker.takeBlockCompleted(messageSnapshot));
                    break;
                case -2:
                    messenger.notifyPaused(messageSnapshot);
                    break;
                case -1:
                    messenger.notifyError(messageSnapshot);
                    break;
            }
        } else {
            FileDownloadLog.e(this, "remove error, not exist: %s %d", iRunningTask, Byte.valueOf(status));
        }
        return remove;
    }

    /* access modifiers changed from: 0000 */
    public void add(IRunningTask iRunningTask) {
        if (!iRunningTask.getOrigin().isAttached()) {
            iRunningTask.setAttachKeyDefault();
        }
        if (iRunningTask.getMessageHandler().getMessenger().notifyBegin()) {
            addUnchecked(iRunningTask);
        }
    }

    /* access modifiers changed from: 0000 */
    public void addUnchecked(IRunningTask iRunningTask) {
        if (!iRunningTask.isMarkedAdded2List()) {
            synchronized (this.mList) {
                if (this.mList.contains(iRunningTask)) {
                    FileDownloadLog.w(this, "already has %s", iRunningTask);
                } else {
                    iRunningTask.markAdded2List();
                    this.mList.add(iRunningTask);
                    if (FileDownloadLog.NEED_LOG) {
                        FileDownloadLog.v(this, "add list in all %s %d %d", iRunningTask, Byte.valueOf(iRunningTask.getOrigin().getStatus()), Integer.valueOf(this.mList.size()));
                    }
                }
            }
        }
    }
}
