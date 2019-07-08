package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.IDownloadSpeed.Lookup;
import com.liulishuo.filedownloader.message.MessageSnapshot;

public interface ITaskHunter extends Lookup {

    public interface IMessageHandler {
        IFileDownloadMessenger getMessenger();

        MessageSnapshot prepareErrorMessage(Throwable th);

        boolean updateKeepAhead(MessageSnapshot messageSnapshot);

        boolean updateKeepFlow(MessageSnapshot messageSnapshot);

        boolean updateMoreLikelyCompleted(MessageSnapshot messageSnapshot);

        boolean updateSameFilePathTaskRunning(MessageSnapshot messageSnapshot);
    }

    public interface IStarter {
        void start();
    }

    void free();

    Throwable getErrorCause();

    int getRetryingTimes();

    long getSofarBytes();

    byte getStatus();

    long getTotalBytes();

    void intoLaunchPool();

    boolean isLargeFile();

    boolean pause();
}
