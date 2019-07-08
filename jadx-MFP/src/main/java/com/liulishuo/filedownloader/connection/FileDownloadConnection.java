package com.liulishuo.filedownloader.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;

public interface FileDownloadConnection {
    void addHeader(String str, String str2);

    boolean dispatchAddResumeOffset(String str, long j);

    void ending();

    void execute() throws IOException;

    InputStream getInputStream() throws IOException;

    Map<String, List<String>> getRequestHeaderFields();

    int getResponseCode() throws IOException;

    String getResponseHeaderField(String str);

    Map<String, List<String>> getResponseHeaderFields();

    boolean setRequestMethod(String str) throws ProtocolException;
}
