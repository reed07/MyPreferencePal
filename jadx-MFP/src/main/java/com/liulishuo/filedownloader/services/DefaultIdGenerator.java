package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.util.FileDownloadHelper.IdGenerator;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class DefaultIdGenerator implements IdGenerator {
    public int transOldId(int i, String str, String str2, boolean z) {
        return generateId(str, str2, z);
    }

    public int generateId(String str, String str2, boolean z) {
        if (z) {
            return FileDownloadUtils.md5(FileDownloadUtils.formatString("%sp%s@dir", str, str2)).hashCode();
        }
        return FileDownloadUtils.md5(FileDownloadUtils.formatString("%sp%s", str, str2)).hashCode();
    }
}
