package com.liulishuo.filedownloader.util;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.BaseDownloadTask.FinishListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileDownloadSerialQueue {
    final SerialFinishCallback finishCallback;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread = new HandlerThread(FileDownloadUtils.getThreadPoolName("SerialDownloadManager"));
    /* access modifiers changed from: private */
    public final BlockingQueue<BaseDownloadTask> mTasks = new LinkedBlockingQueue();
    private final Object operationLock = new Object();
    volatile boolean paused = false;
    private final List<BaseDownloadTask> pausedList = new ArrayList();
    volatile BaseDownloadTask workingTask;

    private static class SerialFinishCallback implements FinishListener {
        private final WeakReference<FileDownloadSerialQueue> mQueueWeakReference;

        SerialFinishCallback(WeakReference<FileDownloadSerialQueue> weakReference) {
            this.mQueueWeakReference = weakReference;
        }

        public synchronized void over(BaseDownloadTask baseDownloadTask) {
            baseDownloadTask.removeFinishListener(this);
            if (this.mQueueWeakReference != null) {
                FileDownloadSerialQueue fileDownloadSerialQueue = (FileDownloadSerialQueue) this.mQueueWeakReference.get();
                if (fileDownloadSerialQueue != null) {
                    fileDownloadSerialQueue.workingTask = null;
                    if (!fileDownloadSerialQueue.paused) {
                        fileDownloadSerialQueue.sendNext();
                    }
                }
            }
        }
    }

    private class SerialLoop implements Callback {
        private SerialLoop() {
        }

        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                try {
                    if (!FileDownloadSerialQueue.this.paused) {
                        FileDownloadSerialQueue.this.workingTask = (BaseDownloadTask) FileDownloadSerialQueue.this.mTasks.take();
                        FileDownloadSerialQueue.this.workingTask.addFinishListener(FileDownloadSerialQueue.this.finishCallback).start();
                    }
                } catch (InterruptedException unused) {
                }
            }
            return false;
        }
    }

    public FileDownloadSerialQueue() {
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper(), new SerialLoop());
        this.finishCallback = new SerialFinishCallback(new WeakReference(this));
        sendNext();
    }

    /* access modifiers changed from: private */
    public void sendNext() {
        this.mHandler.sendEmptyMessage(1);
    }
}
