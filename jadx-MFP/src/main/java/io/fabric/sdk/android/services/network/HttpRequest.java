package io.fabric.sdk.android.services.network;

import com.google.common.net.HttpHeaders;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import kotlin.text.Typography;

public class HttpRequest {
    private static ConnectionFactory CONNECTION_FACTORY = ConnectionFactory.DEFAULT;
    private static final String[] EMPTY_STRINGS = new String[0];
    /* access modifiers changed from: private */
    public int bufferSize = 8192;
    private HttpURLConnection connection = null;
    private String httpProxyHost;
    private int httpProxyPort;
    private boolean ignoreCloseExceptions = true;
    private boolean multipart;
    private RequestOutputStream output;
    private final String requestMethod;
    private boolean uncompress = false;
    public final URL url;

    public static class Base64 {
        private static final byte[] _STANDARD_ALPHABET = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, Framer.EXIT_FRAME_PREFIX, 121, 122, 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52, 53, 54, 55, 56, 57, 43, 47};

        private Base64() {
        }
    }

    protected static abstract class CloseOperation<V> extends Operation<V> {
        private final Closeable closeable;
        private final boolean ignoreCloseExceptions;

        protected CloseOperation(Closeable closeable2, boolean z) {
            this.closeable = closeable2;
            this.ignoreCloseExceptions = z;
        }

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

        protected HttpRequestException(IOException iOException) {
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
        private final CharsetEncoder encoder;

        public RequestOutputStream(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.encoder = Charset.forName(HttpRequest.getValidCharset(str)).newEncoder();
        }

        public RequestOutputStream write(String str) throws IOException {
            ByteBuffer encode = this.encoder.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    /* access modifiers changed from: private */
    public static String getValidCharset(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    private static StringBuilder addPathSeparator(String str, StringBuilder sb) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            sb.append('/');
        }
        return sb;
    }

    private static StringBuilder addParamPrefix(String str, StringBuilder sb) {
        int indexOf = str.indexOf(63);
        int length = sb.length() - 1;
        if (indexOf == -1) {
            sb.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            sb.append(Typography.amp);
        }
        return sb;
    }

    public static String encode(CharSequence charSequence) throws HttpRequestException {
        String str;
        try {
            URL url2 = new URL(charSequence.toString());
            String host = url2.getHost();
            int port = url2.getPort();
            if (port != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(host);
                sb.append(':');
                sb.append(Integer.toString(port));
                str = sb.toString();
            } else {
                str = host;
            }
            try {
                URI uri = new URI(url2.getProtocol(), str, url2.getPath(), url2.getQuery(), null);
                String aSCIIString = uri.toASCIIString();
                int indexOf = aSCIIString.indexOf(63);
                if (indexOf <= 0) {
                    return aSCIIString;
                }
                int i = indexOf + 1;
                if (i >= aSCIIString.length()) {
                    return aSCIIString;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(aSCIIString.substring(0, i));
                sb2.append(aSCIIString.substring(i).replace("+", "%2B"));
                return sb2.toString();
            } catch (URISyntaxException e) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e);
                throw new HttpRequestException(iOException);
            }
        } catch (IOException e2) {
            throw new HttpRequestException(e2);
        }
    }

    public static String append(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder sb = new StringBuilder(charSequence2);
        addPathSeparator(charSequence2, sb);
        addParamPrefix(charSequence2, sb);
        Iterator it = map.entrySet().iterator();
        Entry entry = (Entry) it.next();
        sb.append(entry.getKey().toString());
        sb.append('=');
        Object value = entry.getValue();
        if (value != null) {
            sb.append(value);
        }
        while (it.hasNext()) {
            sb.append(Typography.amp);
            Entry entry2 = (Entry) it.next();
            sb.append(entry2.getKey().toString());
            sb.append('=');
            Object value2 = entry2.getValue();
            if (value2 != null) {
                sb.append(value2);
            }
        }
        return sb.toString();
    }

    public static HttpRequest get(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, HttpConstants.METHOD_GET);
    }

    public static HttpRequest get(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String append = append(charSequence, map);
        if (z) {
            append = encode(append);
        }
        return get(append);
    }

    public static HttpRequest post(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, HttpConstants.METHOD_POST);
    }

    public static HttpRequest post(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String append = append(charSequence, map);
        if (z) {
            append = encode(append);
        }
        return post(append);
    }

    public static HttpRequest put(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, HttpConstants.METHOD_PUT);
    }

    public static HttpRequest delete(CharSequence charSequence) throws HttpRequestException {
        return new HttpRequest(charSequence, HttpConstants.METHOD_DELETE);
    }

    public HttpRequest(CharSequence charSequence, String str) throws HttpRequestException {
        try {
            this.url = new URL(charSequence.toString());
            this.requestMethod = str;
        } catch (MalformedURLException e) {
            throw new HttpRequestException(e);
        }
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

    public int code() throws HttpRequestException {
        try {
            closeOutput();
            return getConnection().getResponseCode();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public boolean ok() throws HttpRequestException {
        return 200 == code();
    }

    /* access modifiers changed from: protected */
    public ByteArrayOutputStream byteStream() {
        int contentLength = contentLength();
        if (contentLength > 0) {
            return new ByteArrayOutputStream(contentLength);
        }
        return new ByteArrayOutputStream();
    }

    public String body(String str) throws HttpRequestException {
        ByteArrayOutputStream byteStream = byteStream();
        try {
            copy(buffer(), byteStream);
            return byteStream.toString(getValidCharset(str));
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public String body() throws HttpRequestException {
        return body(charset());
    }

    public BufferedInputStream buffer() throws HttpRequestException {
        return new BufferedInputStream(stream(), this.bufferSize);
    }

    public InputStream stream() throws HttpRequestException {
        InputStream inputStream;
        if (code() < 400) {
            try {
                inputStream = getConnection().getInputStream();
            } catch (IOException e) {
                throw new HttpRequestException(e);
            }
        } else {
            inputStream = getConnection().getErrorStream();
            if (inputStream == null) {
                try {
                    inputStream = getConnection().getInputStream();
                } catch (IOException e2) {
                    throw new HttpRequestException(e2);
                }
            }
        }
        if (!this.uncompress || !"gzip".equals(contentEncoding())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e3) {
            throw new HttpRequestException(e3);
        }
    }

    public HttpRequest connectTimeout(int i) {
        getConnection().setConnectTimeout(i);
        return this;
    }

    public HttpRequest header(String str, String str2) {
        getConnection().setRequestProperty(str, str2);
        return this;
    }

    public HttpRequest header(Entry<String, String> entry) {
        return header((String) entry.getKey(), (String) entry.getValue());
    }

    public String header(String str) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderField(str);
    }

    public int intHeader(String str) throws HttpRequestException {
        return intHeader(str, -1);
    }

    public int intHeader(String str, int i) throws HttpRequestException {
        closeOutputQuietly();
        return getConnection().getHeaderFieldInt(str, i);
    }

    public String parameter(String str, String str2) {
        return getParam(header(str), str2);
    }

    /* access modifiers changed from: protected */
    public String getParam(String str, String str2) {
        int i;
        if (str == null || str.length() == 0) {
            return null;
        }
        int length = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = length;
        }
        while (indexOf < i) {
            int indexOf3 = str.indexOf(61, indexOf);
            if (indexOf3 != -1 && indexOf3 < i && str2.equals(str.substring(indexOf, indexOf3).trim())) {
                String trim = str.substring(indexOf3 + 1, i).trim();
                int length2 = trim.length();
                if (length2 != 0) {
                    if (length2 > 2 && '\"' == trim.charAt(0)) {
                        int i2 = length2 - 1;
                        if ('\"' == trim.charAt(i2)) {
                            return trim.substring(1, i2);
                        }
                    }
                    return trim;
                }
            }
            indexOf = i + 1;
            i = str.indexOf(59, indexOf);
            if (i == -1) {
                i = length;
            }
        }
        return null;
    }

    public String charset() {
        return parameter("Content-Type", "charset");
    }

    public HttpRequest useCaches(boolean z) {
        getConnection().setUseCaches(z);
        return this;
    }

    public String contentEncoding() {
        return header(HttpHeaders.CONTENT_ENCODING);
    }

    public HttpRequest contentType(String str) {
        return contentType(str, null);
    }

    public HttpRequest contentType(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return header("Content-Type", str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("; charset=");
        sb.append(str2);
        return header("Content-Type", sb.toString());
    }

    public int contentLength() {
        return intHeader("Content-Length");
    }

    /* access modifiers changed from: protected */
    public HttpRequest copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        final InputStream inputStream2 = inputStream;
        final OutputStream outputStream2 = outputStream;
        AnonymousClass6 r0 = new CloseOperation<HttpRequest>(inputStream, this.ignoreCloseExceptions) {
            public HttpRequest run() throws IOException {
                byte[] bArr = new byte[HttpRequest.this.bufferSize];
                while (true) {
                    int read = inputStream2.read(bArr);
                    if (read == -1) {
                        return HttpRequest.this;
                    }
                    outputStream2.write(bArr, 0, read);
                }
            }
        };
        return (HttpRequest) r0.call();
    }

    /* access modifiers changed from: protected */
    public HttpRequest closeOutput() throws IOException {
        RequestOutputStream requestOutputStream = this.output;
        if (requestOutputStream == null) {
            return this;
        }
        if (this.multipart) {
            requestOutputStream.write("\r\n--00content0boundary00--\r\n");
        }
        if (this.ignoreCloseExceptions) {
            try {
                this.output.close();
            } catch (IOException unused) {
            }
        } else {
            this.output.close();
        }
        this.output = null;
        return this;
    }

    /* access modifiers changed from: protected */
    public HttpRequest closeOutputQuietly() throws HttpRequestException {
        try {
            return closeOutput();
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    /* access modifiers changed from: protected */
    public HttpRequest openOutput() throws IOException {
        if (this.output != null) {
            return this;
        }
        getConnection().setDoOutput(true);
        this.output = new RequestOutputStream(getConnection().getOutputStream(), getParam(getConnection().getRequestProperty("Content-Type"), "charset"), this.bufferSize);
        return this;
    }

    /* access modifiers changed from: protected */
    public HttpRequest startPart() throws IOException {
        if (!this.multipart) {
            this.multipart = true;
            contentType("multipart/form-data; boundary=00content0boundary00").openOutput();
            this.output.write("--00content0boundary00\r\n");
        } else {
            this.output.write("\r\n--00content0boundary00\r\n");
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public HttpRequest writePartHeader(String str, String str2, String str3) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"");
        sb.append(str);
        if (str2 != null) {
            sb.append("\"; filename=\"");
            sb.append(str2);
        }
        sb.append(Typography.quote);
        partHeader(HttpHeaders.CONTENT_DISPOSITION, sb.toString());
        if (str3 != null) {
            partHeader("Content-Type", str3);
        }
        return send("\r\n");
    }

    public HttpRequest part(String str, String str2) {
        return part(str, (String) null, str2);
    }

    public HttpRequest part(String str, String str2, String str3) throws HttpRequestException {
        return part(str, str2, (String) null, str3);
    }

    public HttpRequest part(String str, String str2, String str3, String str4) throws HttpRequestException {
        try {
            startPart();
            writePartHeader(str, str2, str3);
            this.output.write(str4);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest part(String str, Number number) throws HttpRequestException {
        return part(str, (String) null, number);
    }

    public HttpRequest part(String str, String str2, Number number) throws HttpRequestException {
        return part(str, str2, number != null ? number.toString() : null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0024 A[SYNTHETIC, Splitter:B:19:0x0024] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.fabric.sdk.android.services.network.HttpRequest part(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.io.File r7) throws io.fabric.sdk.android.services.network.HttpRequest.HttpRequestException {
        /*
            r3 = this;
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x001b }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x001b }
            r2.<init>(r7)     // Catch:{ IOException -> 0x001b }
            r1.<init>(r2)     // Catch:{ IOException -> 0x001b }
            io.fabric.sdk.android.services.network.HttpRequest r4 = r3.part(r4, r5, r6, r1)     // Catch:{ IOException -> 0x0016, all -> 0x0013 }
            r1.close()     // Catch:{ IOException -> 0x0012 }
        L_0x0012:
            return r4
        L_0x0013:
            r4 = move-exception
            r0 = r1
            goto L_0x0022
        L_0x0016:
            r4 = move-exception
            r0 = r1
            goto L_0x001c
        L_0x0019:
            r4 = move-exception
            goto L_0x0022
        L_0x001b:
            r4 = move-exception
        L_0x001c:
            io.fabric.sdk.android.services.network.HttpRequest$HttpRequestException r5 = new io.fabric.sdk.android.services.network.HttpRequest$HttpRequestException     // Catch:{ all -> 0x0019 }
            r5.<init>(r4)     // Catch:{ all -> 0x0019 }
            throw r5     // Catch:{ all -> 0x0019 }
        L_0x0022:
            if (r0 == 0) goto L_0x0027
            r0.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.fabric.sdk.android.services.network.HttpRequest.part(java.lang.String, java.lang.String, java.lang.String, java.io.File):io.fabric.sdk.android.services.network.HttpRequest");
    }

    public HttpRequest part(String str, String str2, String str3, InputStream inputStream) throws HttpRequestException {
        try {
            startPart();
            writePartHeader(str, str2, str3);
            copy(inputStream, this.output);
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public HttpRequest partHeader(String str, String str2) throws HttpRequestException {
        return send(str).send(": ").send(str2).send("\r\n");
    }

    public HttpRequest send(CharSequence charSequence) throws HttpRequestException {
        try {
            openOutput();
            this.output.write(charSequence.toString());
            return this;
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }

    public URL url() {
        return getConnection().getURL();
    }

    public String method() {
        return getConnection().getRequestMethod();
    }
}
