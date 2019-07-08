package com.liulishuo.filedownloader.services;

import android.content.Intent;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadHelper;

public class FileDownloadBroadcastHandler {
    public static void sendCompletedBroadcast(FileDownloadModel fileDownloadModel) {
        if (fileDownloadModel == null) {
            throw new IllegalArgumentException();
        } else if (fileDownloadModel.getStatus() == -3) {
            Intent intent = new Intent("filedownloader.intent.action.completed");
            intent.putExtra("model", fileDownloadModel);
            FileDownloadHelper.getAppContext().sendBroadcast(intent);
        } else {
            throw new IllegalStateException();
        }
    }
}
