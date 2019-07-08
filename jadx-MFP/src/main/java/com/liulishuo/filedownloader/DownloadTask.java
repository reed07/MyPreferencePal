package com.liulishuo.filedownloader;

import android.text.TextUtils;
import com.liulishuo.filedownloader.BaseDownloadTask.FinishListener;
import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.BaseDownloadTask.InQueueTask;
import com.liulishuo.filedownloader.ITaskHunter.IMessageHandler;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;
import java.util.ArrayList;

public class DownloadTask implements BaseDownloadTask, IRunningTask, ICaptureTask {
    private final Object headerCreateLock = new Object();
    volatile int mAttachKey = 0;
    private int mAutoRetryTimes = 0;
    private int mCallbackProgressMinIntervalMillis = 10;
    private int mCallbackProgressTimes = 100;
    private String mFilename;
    private ArrayList<FinishListener> mFinishListenerList;
    private FileDownloadHeader mHeader;
    private final ITaskHunter mHunter;
    private int mId;
    private boolean mIsForceReDownload = false;
    /* access modifiers changed from: private */
    public boolean mIsInQueueTask = false;
    private volatile boolean mIsMarkedAdded2List = false;
    private boolean mIsWifiRequired = false;
    private FileDownloadListener mListener;
    private final IMessageHandler mMessageHandler;
    private String mPath;
    private boolean mPathAsDirectory;
    private final Object mPauseLock;
    private boolean mSyncCallback = false;
    private Object mTag;
    private final String mUrl;

    private static final class InQueueTaskImpl implements InQueueTask {
        private final DownloadTask mTask;

        private InQueueTaskImpl(DownloadTask downloadTask) {
            this.mTask = downloadTask;
            this.mTask.mIsInQueueTask = true;
        }

        public int enqueue() {
            int id = this.mTask.getId();
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "add the task[%d] to the queue", Integer.valueOf(id));
            }
            FileDownloadList.getImpl().addUnchecked(this.mTask);
            return id;
        }
    }

    public BaseDownloadTask getOrigin() {
        return this;
    }

    public IRunningTask getRunningTask() {
        return this;
    }

    DownloadTask(String str) {
        this.mUrl = str;
        this.mPauseLock = new Object();
        DownloadTaskHunter downloadTaskHunter = new DownloadTaskHunter(this, this.mPauseLock);
        this.mHunter = downloadTaskHunter;
        this.mMessageHandler = downloadTaskHunter;
    }

    public BaseDownloadTask setPath(String str) {
        return setPath(str, false);
    }

    public BaseDownloadTask setPath(String str, boolean z) {
        this.mPath = str;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "setPath %s", str);
        }
        this.mPathAsDirectory = z;
        if (z) {
            this.mFilename = null;
        } else {
            this.mFilename = new File(str).getName();
        }
        return this;
    }

    public BaseDownloadTask setListener(FileDownloadListener fileDownloadListener) {
        this.mListener = fileDownloadListener;
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "setListener %s", fileDownloadListener);
        }
        return this;
    }

    public BaseDownloadTask setCallbackProgressTimes(int i) {
        this.mCallbackProgressTimes = i;
        return this;
    }

    public BaseDownloadTask addFinishListener(FinishListener finishListener) {
        if (this.mFinishListenerList == null) {
            this.mFinishListenerList = new ArrayList<>();
        }
        if (!this.mFinishListenerList.contains(finishListener)) {
            this.mFinishListenerList.add(finishListener);
        }
        return this;
    }

    public boolean removeFinishListener(FinishListener finishListener) {
        ArrayList<FinishListener> arrayList = this.mFinishListenerList;
        return arrayList != null && arrayList.remove(finishListener);
    }

    public BaseDownloadTask addHeader(String str, String str2) {
        checkAndCreateHeader();
        this.mHeader.add(str, str2);
        return this;
    }

    public BaseDownloadTask setSyncCallback(boolean z) {
        this.mSyncCallback = z;
        return this;
    }

    public BaseDownloadTask setWifiRequired(boolean z) {
        this.mIsWifiRequired = z;
        return this;
    }

    public InQueueTask asInQueueTask() {
        return new InQueueTaskImpl();
    }

    public boolean isUsing() {
        return this.mHunter.getStatus() != 0;
    }

    public boolean isRunning() {
        if (FileDownloader.getImpl().getLostConnectedHandler().isInWaitingList(this)) {
            return true;
        }
        return FileDownloadStatus.isIng(getStatus());
    }

    public boolean isAttached() {
        return this.mAttachKey != 0;
    }

    public int start() {
        if (!this.mIsInQueueTask) {
            return startTaskUnchecked();
        }
        throw new IllegalStateException("If you start the task manually, it means this task doesn't belong to a queue, so you must not invoke BaseDownloadTask#ready() or InQueueTask#enqueue() before you start() this method. For detail: If this task doesn't belong to a queue, what is just an isolated task, you just need to invoke BaseDownloadTask#start() to start this task, that's all. In other words, If this task doesn't belong to a queue, you must not invoke BaseDownloadTask#ready() method or InQueueTask#enqueue() method before invoke BaseDownloadTask#start(), If you do that and if there is the same listener object to start a queue in another thread, this task may be assembled by the queue, in that case, when you invoke BaseDownloadTask#start() manually to start this task or this task is started by the queue, there is an exception buried in there, because this task object is started two times without declare BaseDownloadTask#reuse() : 1. you invoke BaseDownloadTask#start() manually;  2. the queue start this task automatically.");
    }

    private int startTaskUnchecked() {
        if (!isUsing()) {
            if (!isAttached()) {
                setAttachKeyDefault();
            }
            this.mHunter.intoLaunchPool();
            return getId();
        } else if (isRunning()) {
            throw new IllegalStateException(FileDownloadUtils.formatString("This task is running %d, if you want to start the same task, please create a new one by FileDownloader.create", Integer.valueOf(getId())));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("This task is dirty to restart, If you want to reuse this task, please invoke #reuse method manually and retry to restart again.");
            sb.append(this.mHunter.toString());
            throw new IllegalStateException(sb.toString());
        }
    }

    public boolean pause() {
        boolean pause;
        synchronized (this.mPauseLock) {
            pause = this.mHunter.pause();
        }
        return pause;
    }

    public int getId() {
        int i = this.mId;
        if (i != 0) {
            return i;
        }
        if (TextUtils.isEmpty(this.mPath) || TextUtils.isEmpty(this.mUrl)) {
            return 0;
        }
        int generateId = FileDownloadUtils.generateId(this.mUrl, this.mPath, this.mPathAsDirectory);
        this.mId = generateId;
        return generateId;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public int getCallbackProgressTimes() {
        return this.mCallbackProgressTimes;
    }

    public int getCallbackProgressMinInterval() {
        return this.mCallbackProgressMinIntervalMillis;
    }

    public String getPath() {
        return this.mPath;
    }

    public boolean isPathAsDirectory() {
        return this.mPathAsDirectory;
    }

    public String getFilename() {
        return this.mFilename;
    }

    public String getTargetFilePath() {
        return FileDownloadUtils.getTargetFilePath(getPath(), isPathAsDirectory(), getFilename());
    }

    public FileDownloadListener getListener() {
        return this.mListener;
    }

    public int getSmallFileSoFarBytes() {
        if (this.mHunter.getSofarBytes() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.mHunter.getSofarBytes();
    }

    public long getLargeFileSoFarBytes() {
        return this.mHunter.getSofarBytes();
    }

    public int getSmallFileTotalBytes() {
        if (this.mHunter.getTotalBytes() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) this.mHunter.getTotalBytes();
    }

    public long getLargeFileTotalBytes() {
        return this.mHunter.getTotalBytes();
    }

    public byte getStatus() {
        return this.mHunter.getStatus();
    }

    public boolean isForceReDownload() {
        return this.mIsForceReDownload;
    }

    public Throwable getErrorCause() {
        return this.mHunter.getErrorCause();
    }

    public Object getTag() {
        return this.mTag;
    }

    public int getAutoRetryTimes() {
        return this.mAutoRetryTimes;
    }

    public int getRetryingTimes() {
        return this.mHunter.getRetryingTimes();
    }

    public boolean isSyncCallback() {
        return this.mSyncCallback;
    }

    public boolean isLargeFile() {
        return this.mHunter.isLargeFile();
    }

    public boolean isWifiRequired() {
        return this.mIsWifiRequired;
    }

    private void checkAndCreateHeader() {
        if (this.mHeader == null) {
            synchronized (this.headerCreateLock) {
                if (this.mHeader == null) {
                    this.mHeader = new FileDownloadHeader();
                }
            }
        }
    }

    public FileDownloadHeader getHeader() {
        return this.mHeader;
    }

    public void markAdded2List() {
        this.mIsMarkedAdded2List = true;
    }

    public void free() {
        this.mHunter.free();
        if (FileDownloadList.getImpl().isNotContains(this)) {
            this.mIsMarkedAdded2List = false;
        }
    }

    public void startTaskByRescue() {
        startTaskUnchecked();
    }

    public Object getPauseLock() {
        return this.mPauseLock;
    }

    public boolean isContainFinishListener() {
        ArrayList<FinishListener> arrayList = this.mFinishListenerList;
        return arrayList != null && arrayList.size() > 0;
    }

    public boolean isMarkedAdded2List() {
        return this.mIsMarkedAdded2List;
    }

    public void setFileName(String str) {
        this.mFilename = str;
    }

    public ArrayList<FinishListener> getFinishListenerList() {
        return this.mFinishListenerList;
    }

    public IMessageHandler getMessageHandler() {
        return this.mMessageHandler;
    }

    public boolean is(int i) {
        return getId() == i;
    }

    public boolean isOver() {
        return FileDownloadStatus.isOver(getStatus());
    }

    public int getAttachKey() {
        return this.mAttachKey;
    }

    public void setAttachKeyDefault() {
        int i;
        if (getListener() != null) {
            i = getListener().hashCode();
        } else {
            i = hashCode();
        }
        this.mAttachKey = i;
    }

    public String toString() {
        return FileDownloadUtils.formatString("%d@%s", Integer.valueOf(getId()), super.toString());
    }
}
