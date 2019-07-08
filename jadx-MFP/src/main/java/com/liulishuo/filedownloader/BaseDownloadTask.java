package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.ITaskHunter.IMessageHandler;

public interface BaseDownloadTask {

    public interface FinishListener {
        void over(BaseDownloadTask baseDownloadTask);
    }

    public interface IRunningTask {
        void free();

        int getAttachKey();

        IMessageHandler getMessageHandler();

        BaseDownloadTask getOrigin();

        Object getPauseLock();

        boolean is(int i);

        boolean isContainFinishListener();

        boolean isMarkedAdded2List();

        boolean isOver();

        void markAdded2List();

        void setAttachKeyDefault();

        void startTaskByRescue();
    }

    public interface InQueueTask {
        int enqueue();
    }

    public interface LifeCycleCallback {
        void onBegin();

        void onIng();

        void onOver();
    }

    BaseDownloadTask addFinishListener(FinishListener finishListener);

    BaseDownloadTask addHeader(String str, String str2);

    InQueueTask asInQueueTask();

    int getAutoRetryTimes();

    int getCallbackProgressMinInterval();

    int getCallbackProgressTimes();

    Throwable getErrorCause();

    String getFilename();

    int getId();

    long getLargeFileSoFarBytes();

    long getLargeFileTotalBytes();

    FileDownloadListener getListener();

    String getPath();

    int getRetryingTimes();

    int getSmallFileSoFarBytes();

    int getSmallFileTotalBytes();

    byte getStatus();

    Object getTag();

    String getTargetFilePath();

    String getUrl();

    boolean isAttached();

    boolean isForceReDownload();

    boolean isLargeFile();

    boolean isPathAsDirectory();

    boolean isSyncCallback();

    boolean isWifiRequired();

    boolean pause();

    boolean removeFinishListener(FinishListener finishListener);

    BaseDownloadTask setCallbackProgressTimes(int i);

    BaseDownloadTask setListener(FileDownloadListener fileDownloadListener);

    BaseDownloadTask setPath(String str);

    BaseDownloadTask setSyncCallback(boolean z);

    BaseDownloadTask setWifiRequired(boolean z);

    int start();
}
