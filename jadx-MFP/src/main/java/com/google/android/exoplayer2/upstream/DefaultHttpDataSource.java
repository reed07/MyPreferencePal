package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidContentTypeException;
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException;
import com.google.android.exoplayer2.upstream.HttpDataSource.RequestProperties;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Predicate;
import com.google.android.exoplayer2.util.Util;
import com.google.common.net.HttpHeaders;
import com.mopub.common.Constants;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class DefaultHttpDataSource extends BaseDataSource implements HttpDataSource {
    private static final Pattern CONTENT_RANGE_HEADER = Pattern.compile("^bytes (\\d+)-(\\d+)/(\\d+)$");
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 8000;
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 8000;
    private static final int HTTP_STATUS_PERMANENT_REDIRECT = 308;
    private static final int HTTP_STATUS_TEMPORARY_REDIRECT = 307;
    private static final long MAX_BYTES_TO_DRAIN = 2048;
    private static final int MAX_REDIRECTS = 20;
    private static final String TAG = "DefaultHttpDataSource";
    private static final AtomicReference<byte[]> skipBufferReference = new AtomicReference<>();
    private final boolean allowCrossProtocolRedirects;
    private long bytesRead;
    private long bytesSkipped;
    private long bytesToRead;
    private long bytesToSkip;
    private final int connectTimeoutMillis;
    @Nullable
    private HttpURLConnection connection;
    @Nullable
    private final Predicate<String> contentTypePredicate;
    @Nullable
    private DataSpec dataSpec;
    @Nullable
    private final RequestProperties defaultRequestProperties;
    @Nullable
    private InputStream inputStream;
    private boolean opened;
    private final int readTimeoutMillis;
    private final RequestProperties requestProperties;
    private final String userAgent;

    public DefaultHttpDataSource(String str, @Nullable Predicate<String> predicate) {
        this(str, predicate, 8000, 8000);
    }

    public DefaultHttpDataSource(String str, @Nullable Predicate<String> predicate, int i, int i2) {
        this(str, predicate, i, i2, false, null);
    }

    public DefaultHttpDataSource(String str, @Nullable Predicate<String> predicate, int i, int i2, boolean z, @Nullable RequestProperties requestProperties2) {
        super(true);
        this.userAgent = Assertions.checkNotEmpty(str);
        this.contentTypePredicate = predicate;
        this.requestProperties = new RequestProperties();
        this.connectTimeoutMillis = i;
        this.readTimeoutMillis = i2;
        this.allowCrossProtocolRedirects = z;
        this.defaultRequestProperties = requestProperties2;
    }

    @Deprecated
    public DefaultHttpDataSource(String str, @Nullable Predicate<String> predicate, @Nullable TransferListener transferListener) {
        this(str, predicate, transferListener, 8000, 8000);
    }

    @Deprecated
    public DefaultHttpDataSource(String str, @Nullable Predicate<String> predicate, @Nullable TransferListener transferListener, int i, int i2) {
        this(str, predicate, transferListener, i, i2, false, null);
    }

    @Deprecated
    public DefaultHttpDataSource(String str, @Nullable Predicate<String> predicate, @Nullable TransferListener transferListener, int i, int i2, boolean z, @Nullable RequestProperties requestProperties2) {
        this(str, predicate, i, i2, z, requestProperties2);
        if (transferListener != null) {
            addTransferListener(transferListener);
        }
    }

    @Nullable
    public Uri getUri() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection == null) {
            return null;
        }
        return Uri.parse(httpURLConnection.getURL().toString());
    }

    public Map<String, List<String>> getResponseHeaders() {
        HttpURLConnection httpURLConnection = this.connection;
        return httpURLConnection == null ? Collections.emptyMap() : httpURLConnection.getHeaderFields();
    }

    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        this.requestProperties.set(str, str2);
    }

    public void clearRequestProperty(String str) {
        Assertions.checkNotNull(str);
        this.requestProperties.remove(str);
    }

    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    public long open(DataSpec dataSpec2) throws HttpDataSourceException {
        this.dataSpec = dataSpec2;
        long j = 0;
        this.bytesRead = 0;
        this.bytesSkipped = 0;
        transferInitializing(dataSpec2);
        try {
            this.connection = makeConnection(dataSpec2);
            try {
                int responseCode = this.connection.getResponseCode();
                String responseMessage = this.connection.getResponseMessage();
                if (responseCode < 200 || responseCode > 299) {
                    Map headerFields = this.connection.getHeaderFields();
                    closeConnectionQuietly();
                    InvalidResponseCodeException invalidResponseCodeException = new InvalidResponseCodeException(responseCode, responseMessage, headerFields, dataSpec2);
                    if (responseCode == 416) {
                        invalidResponseCodeException.initCause(new DataSourceException(0));
                    }
                    throw invalidResponseCodeException;
                }
                String contentType = this.connection.getContentType();
                Predicate<String> predicate = this.contentTypePredicate;
                if (predicate == null || predicate.evaluate(contentType)) {
                    if (responseCode == 200 && dataSpec2.position != 0) {
                        j = dataSpec2.position;
                    }
                    this.bytesToSkip = j;
                    if (!dataSpec2.isFlagSet(1)) {
                        long j2 = -1;
                        if (dataSpec2.length != -1) {
                            this.bytesToRead = dataSpec2.length;
                        } else {
                            long contentLength = getContentLength(this.connection);
                            if (contentLength != -1) {
                                j2 = contentLength - this.bytesToSkip;
                            }
                            this.bytesToRead = j2;
                        }
                    } else {
                        this.bytesToRead = dataSpec2.length;
                    }
                    try {
                        this.inputStream = this.connection.getInputStream();
                        this.opened = true;
                        transferStarted(dataSpec2);
                        return this.bytesToRead;
                    } catch (IOException e) {
                        closeConnectionQuietly();
                        throw new HttpDataSourceException(e, dataSpec2, 1);
                    }
                } else {
                    closeConnectionQuietly();
                    throw new InvalidContentTypeException(contentType, dataSpec2);
                }
            } catch (IOException e2) {
                closeConnectionQuietly();
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to connect to ");
                sb.append(dataSpec2.uri.toString());
                throw new HttpDataSourceException(sb.toString(), e2, dataSpec2, 1);
            }
        } catch (IOException e3) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to connect to ");
            sb2.append(dataSpec2.uri.toString());
            throw new HttpDataSourceException(sb2.toString(), e3, dataSpec2, 1);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws HttpDataSourceException {
        try {
            skipInternal();
            return readInternal(bArr, i, i2);
        } catch (IOException e) {
            throw new HttpDataSourceException(e, this.dataSpec, 2);
        }
    }

    public void close() throws HttpDataSourceException {
        try {
            if (this.inputStream != null) {
                maybeTerminateInputStream(this.connection, bytesRemaining());
                this.inputStream.close();
            }
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
        } catch (IOException e) {
            throw new HttpDataSourceException(e, this.dataSpec, 3);
        } catch (Throwable th) {
            this.inputStream = null;
            closeConnectionQuietly();
            if (this.opened) {
                this.opened = false;
                transferEnded();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final HttpURLConnection getConnection() {
        return this.connection;
    }

    /* access modifiers changed from: protected */
    public final long bytesSkipped() {
        return this.bytesSkipped;
    }

    /* access modifiers changed from: protected */
    public final long bytesRead() {
        return this.bytesRead;
    }

    /* access modifiers changed from: protected */
    public final long bytesRemaining() {
        long j = this.bytesToRead;
        return j == -1 ? j : j - this.bytesRead;
    }

    private HttpURLConnection makeConnection(DataSpec dataSpec2) throws IOException {
        HttpURLConnection makeConnection;
        DataSpec dataSpec3 = dataSpec2;
        URL url = new URL(dataSpec3.uri.toString());
        int i = dataSpec3.httpMethod;
        byte[] bArr = dataSpec3.httpBody;
        long j = dataSpec3.position;
        long j2 = dataSpec3.length;
        boolean isFlagSet = dataSpec3.isFlagSet(1);
        if (!this.allowCrossProtocolRedirects) {
            return makeConnection(url, i, bArr, j, j2, isFlagSet, true);
        }
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i2 <= 20) {
                int i4 = i3;
                long j3 = j2;
                makeConnection = makeConnection(url, i, bArr, j, j2, isFlagSet, false);
                int responseCode = makeConnection.getResponseCode();
                String headerField = makeConnection.getHeaderField(HttpHeaders.LOCATION);
                if ((i == 1 || i == 3) && (responseCode == 300 || responseCode == 301 || responseCode == 302 || responseCode == 303 || responseCode == HTTP_STATUS_TEMPORARY_REDIRECT || responseCode == HTTP_STATUS_PERMANENT_REDIRECT)) {
                    makeConnection.disconnect();
                    url = handleRedirect(url, headerField);
                } else if (i != 2 || (responseCode != 300 && responseCode != 301 && responseCode != 302 && responseCode != 303)) {
                    return makeConnection;
                } else {
                    makeConnection.disconnect();
                    url = handleRedirect(url, headerField);
                    bArr = null;
                    i = 1;
                }
                i2 = i4;
                j2 = j3;
            } else {
                int i5 = i3;
                StringBuilder sb = new StringBuilder();
                sb.append("Too many redirects: ");
                sb.append(i5);
                throw new NoRouteToHostException(sb.toString());
            }
        }
        return makeConnection;
    }

    private HttpURLConnection makeConnection(URL url, int i, byte[] bArr, long j, long j2, boolean z, boolean z2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.connectTimeoutMillis);
        httpURLConnection.setReadTimeout(this.readTimeoutMillis);
        RequestProperties requestProperties2 = this.defaultRequestProperties;
        if (requestProperties2 != null) {
            for (Entry entry : requestProperties2.getSnapshot().entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
        }
        for (Entry entry2 : this.requestProperties.getSnapshot().entrySet()) {
            httpURLConnection.setRequestProperty((String) entry2.getKey(), (String) entry2.getValue());
        }
        if (!(j == 0 && j2 == -1)) {
            StringBuilder sb = new StringBuilder();
            sb.append("bytes=");
            sb.append(j);
            sb.append("-");
            String sb2 = sb.toString();
            if (j2 != -1) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb2);
                sb3.append((j + j2) - 1);
                sb2 = sb3.toString();
            }
            httpURLConnection.setRequestProperty(HttpHeaders.RANGE, sb2);
        }
        httpURLConnection.setRequestProperty("User-Agent", this.userAgent);
        if (!z) {
            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "identity");
        }
        httpURLConnection.setInstanceFollowRedirects(z2);
        httpURLConnection.setDoOutput(bArr != null);
        httpURLConnection.setRequestMethod(DataSpec.getStringForHttpMethod(i));
        if (bArr != null) {
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    private static URL handleRedirect(URL url, String str) throws IOException {
        if (str != null) {
            URL url2 = new URL(url, str);
            String protocol = url2.getProtocol();
            if (Constants.HTTPS.equals(protocol) || Constants.HTTP.equals(protocol)) {
                return url2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unsupported protocol redirect: ");
            sb.append(protocol);
            throw new ProtocolException(sb.toString());
        }
        throw new ProtocolException("Null location redirect");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long getContentLength(java.net.HttpURLConnection r8) {
        /*
            java.lang.String r0 = "Content-Length"
            java.lang.String r0 = r8.getHeaderField(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x002c
            long r1 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x0011 }
            goto L_0x002e
        L_0x0011:
            java.lang.String r1 = "DefaultHttpDataSource"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected Content-Length ["
            r2.append(r3)
            r2.append(r0)
            java.lang.String r3 = "]"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.google.android.exoplayer2.util.Log.e(r1, r2)
        L_0x002c:
            r1 = -1
        L_0x002e:
            java.lang.String r3 = "Content-Range"
            java.lang.String r8 = r8.getHeaderField(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r8)
            if (r3 != 0) goto L_0x00ac
            java.util.regex.Pattern r3 = CONTENT_RANGE_HEADER
            java.util.regex.Matcher r3 = r3.matcher(r8)
            boolean r4 = r3.find()
            if (r4 == 0) goto L_0x00ac
            r4 = 2
            java.lang.String r4 = r3.group(r4)     // Catch:{ NumberFormatException -> 0x0091 }
            long r4 = java.lang.Long.parseLong(r4)     // Catch:{ NumberFormatException -> 0x0091 }
            r6 = 1
            java.lang.String r3 = r3.group(r6)     // Catch:{ NumberFormatException -> 0x0091 }
            long r6 = java.lang.Long.parseLong(r3)     // Catch:{ NumberFormatException -> 0x0091 }
            long r4 = r4 - r6
            r6 = 1
            long r4 = r4 + r6
            r6 = 0
            int r3 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r3 >= 0) goto L_0x0064
            r1 = r4
            goto L_0x00ac
        L_0x0064:
            int r3 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r3 == 0) goto L_0x00ac
            java.lang.String r3 = "DefaultHttpDataSource"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0091 }
            r6.<init>()     // Catch:{ NumberFormatException -> 0x0091 }
            java.lang.String r7 = "Inconsistent headers ["
            r6.append(r7)     // Catch:{ NumberFormatException -> 0x0091 }
            r6.append(r0)     // Catch:{ NumberFormatException -> 0x0091 }
            java.lang.String r0 = "] ["
            r6.append(r0)     // Catch:{ NumberFormatException -> 0x0091 }
            r6.append(r8)     // Catch:{ NumberFormatException -> 0x0091 }
            java.lang.String r0 = "]"
            r6.append(r0)     // Catch:{ NumberFormatException -> 0x0091 }
            java.lang.String r0 = r6.toString()     // Catch:{ NumberFormatException -> 0x0091 }
            com.google.android.exoplayer2.util.Log.w(r3, r0)     // Catch:{ NumberFormatException -> 0x0091 }
            long r0 = java.lang.Math.max(r1, r4)     // Catch:{ NumberFormatException -> 0x0091 }
            r1 = r0
            goto L_0x00ac
        L_0x0091:
            java.lang.String r0 = "DefaultHttpDataSource"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected Content-Range ["
            r3.append(r4)
            r3.append(r8)
            java.lang.String r8 = "]"
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            com.google.android.exoplayer2.util.Log.e(r0, r8)
        L_0x00ac:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.DefaultHttpDataSource.getContentLength(java.net.HttpURLConnection):long");
    }

    private void skipInternal() throws IOException {
        if (this.bytesSkipped != this.bytesToSkip) {
            byte[] bArr = (byte[]) skipBufferReference.getAndSet(null);
            if (bArr == null) {
                bArr = new byte[4096];
            }
            while (true) {
                long j = this.bytesSkipped;
                long j2 = this.bytesToSkip;
                if (j != j2) {
                    int read = this.inputStream.read(bArr, 0, (int) Math.min(j2 - j, (long) bArr.length));
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedIOException();
                    } else if (read != -1) {
                        this.bytesSkipped += (long) read;
                        bytesTransferred(read);
                    } else {
                        throw new EOFException();
                    }
                } else {
                    skipBufferReference.set(bArr);
                    return;
                }
            }
        }
    }

    private int readInternal(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesToRead;
        if (j != -1) {
            long j2 = j - this.bytesRead;
            if (j2 == 0) {
                return -1;
            }
            i2 = (int) Math.min((long) i2, j2);
        }
        int read = this.inputStream.read(bArr, i, i2);
        if (read != -1) {
            this.bytesRead += (long) read;
            bytesTransferred(read);
            return read;
        } else if (this.bytesToRead == -1) {
            return -1;
        } else {
            throw new EOFException();
        }
    }

    private static void maybeTerminateInputStream(HttpURLConnection httpURLConnection, long j) {
        if (Util.SDK_INT == 19 || Util.SDK_INT == 20) {
            try {
                InputStream inputStream2 = httpURLConnection.getInputStream();
                if (j == -1) {
                    if (inputStream2.read() == -1) {
                        return;
                    }
                } else if (j <= MAX_BYTES_TO_DRAIN) {
                    return;
                }
                String name = inputStream2.getClass().getName();
                if ("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream".equals(name) || "com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream".equals(name)) {
                    Method declaredMethod = inputStream2.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[0]);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(inputStream2, new Object[0]);
                }
            } catch (Exception unused) {
            }
        }
    }

    private void closeConnectionQuietly() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
                Log.e(TAG, "Unexpected error while disconnecting", e);
            }
            this.connection = null;
        }
    }
}
