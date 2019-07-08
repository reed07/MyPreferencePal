package com.liulishuo.filedownloader.connection;

import com.liulishuo.filedownloader.util.FileDownloadHelper.ConnectionCreator;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class FileDownloadUrlConnection implements FileDownloadConnection {
    protected URLConnection mConnection;

    public static class Configuration {
        /* access modifiers changed from: private */
        public Integer connectTimeout;
        /* access modifiers changed from: private */
        public Proxy proxy;
        /* access modifiers changed from: private */
        public Integer readTimeout;
    }

    public static class Creator implements ConnectionCreator {
        private final Configuration mConfiguration;

        public Creator() {
            this(null);
        }

        public Creator(Configuration configuration) {
            this.mConfiguration = configuration;
        }

        public FileDownloadConnection create(String str) throws IOException {
            return new FileDownloadUrlConnection(str, this.mConfiguration);
        }
    }

    public boolean dispatchAddResumeOffset(String str, long j) {
        return false;
    }

    public FileDownloadUrlConnection(String str, Configuration configuration) throws IOException {
        this(new URL(str), configuration);
    }

    public FileDownloadUrlConnection(URL url, Configuration configuration) throws IOException {
        if (configuration == null || configuration.proxy == null) {
            this.mConnection = url.openConnection();
        } else {
            this.mConnection = url.openConnection(configuration.proxy);
        }
        if (configuration != null) {
            if (configuration.readTimeout != null) {
                this.mConnection.setReadTimeout(configuration.readTimeout.intValue());
            }
            if (configuration.connectTimeout != null) {
                this.mConnection.setConnectTimeout(configuration.connectTimeout.intValue());
            }
        }
    }

    public void addHeader(String str, String str2) {
        this.mConnection.addRequestProperty(str, str2);
    }

    public InputStream getInputStream() throws IOException {
        return this.mConnection.getInputStream();
    }

    public Map<String, List<String>> getRequestHeaderFields() {
        return this.mConnection.getRequestProperties();
    }

    public Map<String, List<String>> getResponseHeaderFields() {
        return this.mConnection.getHeaderFields();
    }

    public String getResponseHeaderField(String str) {
        return this.mConnection.getHeaderField(str);
    }

    public boolean setRequestMethod(String str) throws ProtocolException {
        URLConnection uRLConnection = this.mConnection;
        if (!(uRLConnection instanceof HttpURLConnection)) {
            return false;
        }
        ((HttpURLConnection) uRLConnection).setRequestMethod(str);
        return true;
    }

    public void execute() throws IOException {
        this.mConnection.connect();
    }

    public int getResponseCode() throws IOException {
        URLConnection uRLConnection = this.mConnection;
        if (uRLConnection instanceof HttpURLConnection) {
            return ((HttpURLConnection) uRLConnection).getResponseCode();
        }
        return 0;
    }

    public void ending() {
        try {
            this.mConnection.getInputStream().close();
        } catch (IOException unused) {
        }
    }
}
