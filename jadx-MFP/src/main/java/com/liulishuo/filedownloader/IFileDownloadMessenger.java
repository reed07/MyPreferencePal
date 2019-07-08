package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.message.MessageSnapshot;

interface IFileDownloadMessenger {
    boolean handoverDirectly();

    void handoverMessage();

    boolean isBlockingCompleted();

    boolean notifyBegin();

    void notifyBlockComplete(MessageSnapshot messageSnapshot);

    void notifyConnected(MessageSnapshot messageSnapshot);

    void notifyError(MessageSnapshot messageSnapshot);

    void notifyPaused(MessageSnapshot messageSnapshot);

    void notifyPending(MessageSnapshot messageSnapshot);

    void notifyProgress(MessageSnapshot messageSnapshot);

    void notifyRetry(MessageSnapshot messageSnapshot);

    void notifyStarted(MessageSnapshot messageSnapshot);

    void notifyWarn(MessageSnapshot messageSnapshot);
}
