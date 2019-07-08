package com.liulishuo.filedownloader.exception;

import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FileDownloadHttpException extends IOException {
    private final int mCode;
    private final Map<String, List<String>> mRequestHeaderMap;
    private final Map<String, List<String>> mResponseHeaderMap;

    public FileDownloadHttpException(int i, Map<String, List<String>> map, Map<String, List<String>> map2) {
        super(FileDownloadUtils.formatString("response code error: %d, \n request headers: %s \n response headers: %s", Integer.valueOf(i), map, map2));
        this.mCode = i;
        this.mRequestHeaderMap = cloneSerializableMap(map);
        this.mResponseHeaderMap = cloneSerializableMap(map);
    }

    public int getCode() {
        return this.mCode;
    }

    private static Map<String, List<String>> cloneSerializableMap(Map<String, List<String>> map) {
        HashMap hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put((String) entry.getKey(), new ArrayList((Collection) entry.getValue()));
        }
        return hashMap;
    }
}
