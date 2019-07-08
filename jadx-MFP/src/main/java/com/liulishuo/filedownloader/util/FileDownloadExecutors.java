package com.liulishuo.filedownloader.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class FileDownloadExecutors {

    static class FileDownloadThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group = Thread.currentThread().getThreadGroup();
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        FileDownloadThreadFactory(String str) {
            this.namePrefix = FileDownloadUtils.getThreadPoolName(str);
        }

        public Thread newThread(Runnable runnable) {
            ThreadGroup threadGroup = this.group;
            StringBuilder sb = new StringBuilder();
            sb.append(this.namePrefix);
            sb.append(this.threadNumber.getAndIncrement());
            Thread thread = new Thread(threadGroup, runnable, sb.toString(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public static ThreadPoolExecutor newFixedThreadPool(String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 15, TimeUnit.SECONDS, new SynchronousQueue(), new FileDownloadThreadFactory(str));
        return threadPoolExecutor;
    }

    public static ThreadPoolExecutor newDefaultThreadPool(int i, String str) {
        return newDefaultThreadPool(i, new LinkedBlockingQueue(), str);
    }

    public static ThreadPoolExecutor newDefaultThreadPool(int i, LinkedBlockingQueue<Runnable> linkedBlockingQueue, String str) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i, 15, TimeUnit.SECONDS, linkedBlockingQueue, new FileDownloadThreadFactory(str));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }
}
