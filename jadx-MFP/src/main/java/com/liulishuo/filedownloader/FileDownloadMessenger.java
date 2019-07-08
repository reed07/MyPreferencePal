package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.BaseDownloadTask.LifeCycleCallback;
import com.liulishuo.filedownloader.ITaskHunter.IMessageHandler;
import com.liulishuo.filedownloader.message.BlockCompleteMessage;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class FileDownloadMessenger implements IFileDownloadMessenger {
    private boolean mIsDiscard = false;
    private LifeCycleCallback mLifeCycleCallback;
    private IRunningTask mTask;
    private Queue<MessageSnapshot> parcelQueue;

    FileDownloadMessenger(IRunningTask iRunningTask, LifeCycleCallback lifeCycleCallback) {
        init(iRunningTask, lifeCycleCallback);
    }

    private void init(IRunningTask iRunningTask, LifeCycleCallback lifeCycleCallback) {
        this.mTask = iRunningTask;
        this.mLifeCycleCallback = lifeCycleCallback;
        this.parcelQueue = new LinkedBlockingQueue();
    }

    public boolean notifyBegin() {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify begin %s", this.mTask);
        }
        if (this.mTask == null) {
            FileDownloadLog.w(this, "can't begin the task, the holder fo the messenger is nil, %d", Integer.valueOf(this.parcelQueue.size()));
            return false;
        }
        this.mLifeCycleCallback.onBegin();
        return true;
    }

    public void notifyPending(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify pending %s", this.mTask);
        }
        this.mLifeCycleCallback.onIng();
        process(messageSnapshot);
    }

    public void notifyStarted(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify started %s", this.mTask);
        }
        this.mLifeCycleCallback.onIng();
        process(messageSnapshot);
    }

    public void notifyConnected(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify connected %s", this.mTask);
        }
        this.mLifeCycleCallback.onIng();
        process(messageSnapshot);
    }

    public void notifyProgress(MessageSnapshot messageSnapshot) {
        BaseDownloadTask origin = this.mTask.getOrigin();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify progress %s %d %d", origin, Long.valueOf(origin.getLargeFileSoFarBytes()), Long.valueOf(origin.getLargeFileTotalBytes()));
        }
        if (origin.getCallbackProgressTimes() <= 0) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "notify progress but client not request notify %s", this.mTask);
            }
            return;
        }
        this.mLifeCycleCallback.onIng();
        process(messageSnapshot);
    }

    public void notifyBlockComplete(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify block completed %s %s", this.mTask, Thread.currentThread().getName());
        }
        this.mLifeCycleCallback.onIng();
        process(messageSnapshot);
    }

    public void notifyRetry(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            BaseDownloadTask origin = this.mTask.getOrigin();
            FileDownloadLog.d(this, "notify retry %s %d %d %s", this.mTask, Integer.valueOf(origin.getAutoRetryTimes()), Integer.valueOf(origin.getRetryingTimes()), origin.getErrorCause());
        }
        this.mLifeCycleCallback.onIng();
        process(messageSnapshot);
    }

    public void notifyWarn(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify warn %s", this.mTask);
        }
        this.mLifeCycleCallback.onOver();
        process(messageSnapshot);
    }

    public void notifyError(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            IRunningTask iRunningTask = this.mTask;
            FileDownloadLog.d(this, "notify error %s %s", iRunningTask, iRunningTask.getOrigin().getErrorCause());
        }
        this.mLifeCycleCallback.onOver();
        process(messageSnapshot);
    }

    public void notifyPaused(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify paused %s", this.mTask);
        }
        this.mLifeCycleCallback.onOver();
        process(messageSnapshot);
    }

    public void notifyCompleted(MessageSnapshot messageSnapshot) {
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "notify completed %s", this.mTask);
        }
        this.mLifeCycleCallback.onOver();
        process(messageSnapshot);
    }

    private void process(MessageSnapshot messageSnapshot) {
        IRunningTask iRunningTask = this.mTask;
        if (iRunningTask == null) {
            if (FileDownloadLog.NEED_LOG) {
                FileDownloadLog.d(this, "occur this case, it would be the host task of this messenger has been over(paused/warn/completed/error) on the other thread before receiving the snapshot(id[%d], status[%d])", Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus()));
            }
            return;
        }
        if (this.mIsDiscard || iRunningTask.getOrigin().getListener() == null) {
            if ((FileDownloadMonitor.isValid() || this.mTask.isContainFinishListener()) && messageSnapshot.getStatus() == 4) {
                this.mLifeCycleCallback.onOver();
            }
            inspectAndHandleOverStatus(messageSnapshot.getStatus());
        } else {
            this.parcelQueue.offer(messageSnapshot);
            FileDownloadMessageStation.getImpl().requestEnqueue(this);
        }
    }

    private void inspectAndHandleOverStatus(int i) {
        if (FileDownloadStatus.isOver(i)) {
            if (!this.parcelQueue.isEmpty()) {
                MessageSnapshot messageSnapshot = (MessageSnapshot) this.parcelQueue.peek();
                FileDownloadLog.w(this, "the messenger[%s](with id[%d]) has already accomplished all his job, but there still are some messages in parcel queue[%d] queue-top-status[%d]", this, Integer.valueOf(messageSnapshot.getId()), Integer.valueOf(this.parcelQueue.size()), Byte.valueOf(messageSnapshot.getStatus()));
            }
            this.mTask = null;
        }
    }

    public void handoverMessage() {
        if (!this.mIsDiscard) {
            MessageSnapshot messageSnapshot = (MessageSnapshot) this.parcelQueue.poll();
            byte status = messageSnapshot.getStatus();
            IRunningTask iRunningTask = this.mTask;
            if (iRunningTask != null) {
                BaseDownloadTask origin = iRunningTask.getOrigin();
                FileDownloadListener listener = origin.getListener();
                IMessageHandler messageHandler = iRunningTask.getMessageHandler();
                inspectAndHandleOverStatus(status);
                if (listener != null && !listener.isInvalid()) {
                    if (status != 4) {
                        FileDownloadLargeFileListener fileDownloadLargeFileListener = null;
                        if (listener instanceof FileDownloadLargeFileListener) {
                            fileDownloadLargeFileListener = (FileDownloadLargeFileListener) listener;
                        }
                        switch (status) {
                            case -4:
                                listener.warn(origin);
                                break;
                            case -3:
                                listener.completed(origin);
                                break;
                            case -2:
                                if (fileDownloadLargeFileListener == null) {
                                    listener.paused(origin, messageSnapshot.getSmallSofarBytes(), messageSnapshot.getSmallTotalBytes());
                                    break;
                                } else {
                                    fileDownloadLargeFileListener.paused(origin, messageSnapshot.getLargeSofarBytes(), messageSnapshot.getLargeTotalBytes());
                                    break;
                                }
                            case -1:
                                listener.error(origin, messageSnapshot.getThrowable());
                                break;
                            case 1:
                                if (fileDownloadLargeFileListener == null) {
                                    listener.pending(origin, messageSnapshot.getSmallSofarBytes(), messageSnapshot.getSmallTotalBytes());
                                    break;
                                } else {
                                    fileDownloadLargeFileListener.pending(origin, messageSnapshot.getLargeSofarBytes(), messageSnapshot.getLargeTotalBytes());
                                    break;
                                }
                            case 2:
                                if (fileDownloadLargeFileListener == null) {
                                    listener.connected(origin, messageSnapshot.getEtag(), messageSnapshot.isResuming(), origin.getSmallFileSoFarBytes(), messageSnapshot.getSmallTotalBytes());
                                    break;
                                } else {
                                    fileDownloadLargeFileListener.connected(origin, messageSnapshot.getEtag(), messageSnapshot.isResuming(), origin.getLargeFileSoFarBytes(), messageSnapshot.getLargeTotalBytes());
                                    break;
                                }
                            case 3:
                                if (fileDownloadLargeFileListener == null) {
                                    listener.progress(origin, messageSnapshot.getSmallSofarBytes(), origin.getSmallFileTotalBytes());
                                    break;
                                } else {
                                    fileDownloadLargeFileListener.progress(origin, messageSnapshot.getLargeSofarBytes(), origin.getLargeFileTotalBytes());
                                    break;
                                }
                            case 5:
                                if (fileDownloadLargeFileListener == null) {
                                    listener.retry(origin, messageSnapshot.getThrowable(), messageSnapshot.getRetryingTimes(), messageSnapshot.getSmallSofarBytes());
                                    break;
                                } else {
                                    fileDownloadLargeFileListener.retry(origin, messageSnapshot.getThrowable(), messageSnapshot.getRetryingTimes(), messageSnapshot.getLargeSofarBytes());
                                    break;
                                }
                            case 6:
                                listener.started(origin);
                                break;
                        }
                    } else {
                        try {
                            listener.blockComplete(origin);
                            notifyCompleted(((BlockCompleteMessage) messageSnapshot).transmitToCompleted());
                        } catch (Throwable th) {
                            notifyError(messageHandler.prepareErrorMessage(th));
                        }
                    }
                    return;
                }
                return;
            }
            throw new IllegalArgumentException(FileDownloadUtils.formatString("can't handover the message, no master to receive this message(status[%d]) size[%d]", Integer.valueOf(status), Integer.valueOf(this.parcelQueue.size())));
        }
    }

    public boolean handoverDirectly() {
        return this.mTask.getOrigin().isSyncCallback();
    }

    public boolean isBlockingCompleted() {
        return ((MessageSnapshot) this.parcelQueue.peek()).getStatus() == 4;
    }

    public String toString() {
        int i;
        String str = "%d:%s";
        Object[] objArr = new Object[2];
        IRunningTask iRunningTask = this.mTask;
        if (iRunningTask == null) {
            i = -1;
        } else {
            i = iRunningTask.getOrigin().getId();
        }
        objArr[0] = Integer.valueOf(i);
        objArr[1] = super.toString();
        return FileDownloadUtils.formatString(str, objArr);
    }
}
