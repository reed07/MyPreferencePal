package com.liulishuo.filedownloader.connection;

import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCountAdapter;

public class DefaultConnectionCountAdapter implements ConnectionCountAdapter {
    public int determineConnectionCount(int i, String str, String str2, long j) {
        if (j < 1048576) {
            return 1;
        }
        if (j < 5242880) {
            return 2;
        }
        if (j < 52428800) {
            return 3;
        }
        return j < 104857600 ? 4 : 5;
    }
}
