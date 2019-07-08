package com.liulishuo.filedownloader;

import android.content.Context;
import com.liulishuo.filedownloader.model.FileDownloadHeader;

public interface IFileDownloadServiceProxy {
    void bindStartByContext(Context context);

    void bindStartByContext(Context context, Runnable runnable);

    boolean clearTaskData(int i);

    byte getStatus(int i);

    boolean isConnected();

    boolean pause(int i);

    void pauseAllTasks();

    boolean start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3);
}
