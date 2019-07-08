package com.liulishuo.filedownloader.util;

public class DownloadServiceNotConnectedHelper {
    public static boolean start(String str, String str2, boolean z) {
        log("request start the task([%s], [%s], [%B]) in the download service", str, str2, Boolean.valueOf(z));
        return false;
    }

    public static boolean pause(int i) {
        log("request pause the task[%d] in the download service", Integer.valueOf(i));
        return false;
    }

    public static byte getStatus(int i) {
        log("request get the status for the task[%d] in the download service", Integer.valueOf(i));
        return 0;
    }

    public static void pauseAllTasks() {
        log("request pause all tasks in the download service", new Object[0]);
    }

    public static boolean clearTaskData(int i) {
        log("request clear the task[%d] data in the database", Integer.valueOf(i));
        return false;
    }

    private static void log(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(", but the download service isn't connected yet.");
        sb.append("\nYou can use FileDownloader#isServiceConnected() to check whether the service has been connected, \nbesides you can use following functions easier to control your code invoke after the service has been connected: \n1. FileDownloader#bindService(Runnable)\n2. FileDownloader#insureServiceBind()\n3. FileDownloader#insureServiceBindAsync()");
        FileDownloadLog.w(DownloadServiceNotConnectedHelper.class, sb.toString(), objArr);
    }
}
