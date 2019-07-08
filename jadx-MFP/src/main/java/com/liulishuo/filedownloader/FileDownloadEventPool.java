package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.event.DownloadEventPoolImpl;

public class FileDownloadEventPool extends DownloadEventPoolImpl {

    private static class HolderClass {
        /* access modifiers changed from: private */
        public static final FileDownloadEventPool INSTANCE = new FileDownloadEventPool();

        private HolderClass() {
        }
    }

    private FileDownloadEventPool() {
    }

    public static FileDownloadEventPool getImpl() {
        return HolderClass.INSTANCE;
    }
}
