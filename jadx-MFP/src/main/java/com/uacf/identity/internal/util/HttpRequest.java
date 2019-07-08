package com.uacf.identity.internal.util;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.concurrent.Callable;

public class HttpRequest {
    private static ConnectionFactory CONNECTION_FACTORY = ConnectionFactory.DEFAULT;
    private static final String[] EMPTY_STRINGS = new String[0];
    private HttpURLConnection connection;
    private String httpProxyHost;
    private int httpProxyPort;
    private final String requestMethod;
    private final URL url;

    public static class Base64 {
        private static final byte[] _STANDARD_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, Framer.EXIT_FRAME_PREFIX, 121, 122, 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52, 53, 54, 55, 56, 57, 43, 47};

        private Base64() {
        }
    }

    protected static abstract class CloseOperation<V> extends Operation<V> {
        private final Closeable closeable;
        private final boolean ignoreCloseExceptions;

        /* access modifiers changed from: protected */
        public void done() throws IOException {
            Closeable closeable2 = this.closeable;
            if (closeable2 instanceof Flushable) {
                ((Flushable) closeable2).flush();
            }
            if (this.ignoreCloseExceptions) {
                try {
                    this.closeable.close();
                } catch (IOException unused) {
                }
            } else {
                this.closeable.close();
            }
        }
    }

    public interface ConnectionFactory {
        public static final ConnectionFactory DEFAULT = new ConnectionFactory() {
            public HttpURLConnection create(URL url) throws IOException {
                return (HttpURLConnection) url.openConnection();
            }

            public HttpURLConnection create(URL url, Proxy proxy) throws IOException {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        };

        HttpURLConnection create(URL url) throws IOException;

        HttpURLConnection create(URL url, Proxy proxy) throws IOException;
    }

    protected static abstract class FlushOperation<V> extends Operation<V> {
        private final Flushable flushable;

        /* access modifiers changed from: protected */
        public void done() throws IOException {
            this.flushable.flush();
        }
    }

    public static class HttpRequestException extends RuntimeException {
        private static final long serialVersionUID = -1170466989781746231L;

        public HttpRequestException(IOException iOException) {
            super(iOException);
        }

        public IOException getCause() {
            return (IOException) super.getCause();
        }
    }

    protected static abstract class Operation<V> implements Callable<V> {
        /* access modifiers changed from: protected */
        public abstract void done() throws IOException;

        /* access modifiers changed from: protected */
        public abstract V run() throws HttpRequestException, IOException;

        protected Operation() {
        }

        public V call() throws HttpRequestException {
            boolean z = true;
            try {
                V run = run();
                try {
                    done();
                    return run;
                } catch (IOException e) {
                    throw new HttpRequestException(e);
                }
            } catch (HttpRequestException e2) {
                throw e2;
            } catch (IOException e3) {
                throw new HttpRequestException(e3);
            } catch (Throwable th) {
                th = th;
                done();
                throw th;
            }
        }
    }

    public static class RequestOutputStream extends BufferedOutputStream {
    }

    public interface UploadProgress {
        public static final UploadProgress DEFAULT = new UploadProgress() {
        };
    }

    private Proxy createProxy() {
        return new Proxy(Type.HTTP, new InetSocketAddress(this.httpProxyHost, this.httpProxyPort));
    }

    private HttpURLConnection createConnection() {
        HttpURLConnection httpURLConnection;
        try {
            if (this.httpProxyHost != null) {
                httpURLConnection = CONNECTION_FACTORY.create(this.url, createProxy());
            } else {
                httpURLConnection = CONNECTION_FACTORY.create(this.url);
            }
            httpURLConnection.setRequestMethod(this.requestMethod);
            return httpURLConnection;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(method());
        sb.append(' ');
        sb.append(url());
        return sb.toString();
    }

    public HttpURLConnection getConnection() {
        if (this.connection == null) {
            this.connection = createConnection();
        }
        return this.connection;
    }

    public URL url() {
        return getConnection().getURL();
    }

    public String method() {
        return getConnection().getRequestMethod();
    }
}
