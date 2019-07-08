package com.brightcove.player.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import com.brightcove.player.util.ErrorUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class HttpService {
    public static final int DEFAULT_CONNECT_TIMEOUT = 10000;
    public static final int DEFAULT_READ_TIMEOUT = 10000;
    public static final String TAG = "HttpService";
    private int connectTimeout = 10000;
    private int readTimeout = 10000;

    private enum Method {
        GET,
        POST
    }

    public HttpService() {
    }

    public HttpService(int i, int i2) {
        this.connectTimeout = i;
        this.readTimeout = i2;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public static URI buildURIWithQueryParameters(String str, Map<String, Object> map) throws URISyntaxException, UnsupportedEncodingException {
        if (str != null) {
            StringBuilder sb = new StringBuilder(str);
            if (map == null || map.size() == 0) {
                return new URI(sb.toString());
            }
            if (str.contains("?")) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (!(entry == null || entry.getKey() == null || entry.getValue() == null)) {
                    sb.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
                    if (it.hasNext()) {
                        sb.append("&");
                    }
                }
            }
            return new URI(sb.toString());
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.BASE_URL_REQUIRED));
    }

    public String doGetRequest(URI uri) throws IOException {
        return doGetRequest(uri, new HashMap());
    }

    public String doGetRequest(URI uri, Map<String, String> map) throws IOException {
        return doRequest(Method.GET, uri, map);
    }

    public String doPostRequest(URI uri) throws IOException {
        return doPostRequest(uri, new HashMap());
    }

    public String doPostRequest(URI uri, Map<String, String> map) throws IOException {
        return doRequest(Method.POST, uri, map);
    }

    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r6v6 */
    /* JADX WARNING: type inference failed for: r6v10 */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r6v15 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0107 A[Catch:{ all -> 0x00fe }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0122 A[SYNTHETIC, Splitter:B:45:0x0122] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String doRequest(com.brightcove.player.network.HttpService.Method r5, java.net.URI r6, java.util.Map<java.lang.String, java.lang.String> r7) throws java.io.IOException {
        /*
            r4 = this;
            if (r6 == 0) goto L_0x013b
            if (r7 == 0) goto L_0x012f
            java.net.URL r6 = r6.toURL()
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "issuing "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = " request: "
            r1.append(r2)
            java.lang.String r2 = r6.toString()
            r1.append(r2)
            java.lang.String r2 = ", headers: "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
            r0 = 0
            java.net.URLConnection r6 = r6.openConnection()     // Catch:{ IOException -> 0x0101 }
            int r1 = r4.connectTimeout     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r6.setConnectTimeout(r1)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            int r1 = r4.readTimeout     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r6.setReadTimeout(r1)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            boolean r1 = r6 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            if (r1 == 0) goto L_0x0091
            r1 = r6
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            com.brightcove.player.network.HttpService$Method r2 = com.brightcove.player.network.HttpService.Method.POST     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            boolean r5 = r5.equals(r2)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            if (r5 == 0) goto L_0x0055
            java.lang.String r5 = "POST"
            r1.setRequestMethod(r5)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
        L_0x0055:
            java.util.Set r5 = r7.entrySet()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
        L_0x005d:
            boolean r7 = r5.hasNext()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            if (r7 == 0) goto L_0x0091
            java.lang.Object r7 = r5.next()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.Object r2 = r7.getKey()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            boolean r2 = com.brightcove.player.util.StringUtil.isEmpty(r2)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            if (r2 != 0) goto L_0x005d
            java.lang.Object r2 = r7.getValue()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            boolean r2 = com.brightcove.player.util.StringUtil.isEmpty(r2)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            if (r2 != 0) goto L_0x005d
            java.lang.Object r2 = r7.getKey()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.Object r7 = r7.getValue()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r1.setRequestProperty(r2, r7)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            goto L_0x005d
        L_0x0091:
            java.io.InputStream r5 = r6.getInputStream()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r0 = inputStreamToString(r5)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r5 = TAG     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r7.<init>()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r1 = "response: "
            r7.append(r1)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r7.append(r0)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            android.util.Log.d(r5, r7)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            boolean r5 = r6 instanceof java.net.HttpURLConnection     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            if (r5 == 0) goto L_0x00ea
            r5 = r6
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r7 = TAG     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r1.<init>()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r2 = "code: "
            r1.append(r2)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            int r2 = r5.getResponseCode()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r1.append(r2)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            android.util.Log.d(r7, r1)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r7 = TAG     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r1.<init>()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r2 = "message: "
            r1.append(r2)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r5 = r5.getResponseMessage()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            r1.append(r5)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            java.lang.String r5 = r1.toString()     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
            android.util.Log.d(r7, r5)     // Catch:{ IOException -> 0x00f9, all -> 0x00f7 }
        L_0x00ea:
            if (r6 == 0) goto L_0x00f5
            boolean r5 = r6 instanceof java.net.HttpURLConnection
            if (r5 == 0) goto L_0x00f5
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6
            r6.disconnect()
        L_0x00f5:
            r5 = r0
            goto L_0x0121
        L_0x00f7:
            r5 = move-exception
            goto L_0x0123
        L_0x00f9:
            r5 = move-exception
            r3 = r0
            r0 = r6
            r6 = r3
            goto L_0x0103
        L_0x00fe:
            r5 = move-exception
            r6 = r0
            goto L_0x0123
        L_0x0101:
            r5 = move-exception
            r6 = r0
        L_0x0103:
            boolean r7 = r0 instanceof java.net.HttpURLConnection     // Catch:{ all -> 0x00fe }
            if (r7 == 0) goto L_0x0122
            r5 = r0
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ all -> 0x00fe }
            java.io.InputStream r5 = r5.getErrorStream()     // Catch:{ all -> 0x00fe }
            if (r5 == 0) goto L_0x0115
            java.lang.String r5 = inputStreamToString(r5)     // Catch:{ all -> 0x00fe }
            goto L_0x0116
        L_0x0115:
            r5 = r6
        L_0x0116:
            if (r0 == 0) goto L_0x0121
            boolean r6 = r0 instanceof java.net.HttpURLConnection
            if (r6 == 0) goto L_0x0121
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0
            r0.disconnect()
        L_0x0121:
            return r5
        L_0x0122:
            throw r5     // Catch:{ all -> 0x00fe }
        L_0x0123:
            if (r6 == 0) goto L_0x012e
            boolean r7 = r6 instanceof java.net.HttpURLConnection
            if (r7 == 0) goto L_0x012e
            java.net.HttpURLConnection r6 = (java.net.HttpURLConnection) r6
            r6.disconnect()
        L_0x012e:
            throw r5
        L_0x012f:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "headersRequired"
            java.lang.String r6 = com.brightcove.player.util.ErrorUtil.getMessage(r6)
            r5.<init>(r6)
            throw r5
        L_0x013b:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "uriRequired"
            java.lang.String r6 = com.brightcove.player.util.ErrorUtil.getMessage(r6)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.network.HttpService.doRequest(com.brightcove.player.network.HttpService$Method, java.net.URI, java.util.Map):java.lang.String");
    }

    public Bitmap doImageGetRequest(URI uri) throws IOException {
        if (uri != null) {
            URL url = uri.toURL();
            URLConnection uRLConnection = null;
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("issuing GET request: ");
            sb.append(url.toString());
            Log.d(str, sb.toString());
            try {
                uRLConnection = url.openConnection();
                uRLConnection.setConnectTimeout(this.connectTimeout);
                uRLConnection.setReadTimeout(this.readTimeout);
                Bitmap decodeStream = BitmapFactory.decodeStream(uRLConnection.getInputStream());
                String str2 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("doImageGetRequest: total memory = ");
                sb2.append(Runtime.getRuntime().totalMemory());
                Log.v(str2, sb2.toString());
                return decodeStream;
            } finally {
                if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
                    uRLConnection.disconnect();
                }
            }
        } else {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.URI_REQUIRED));
        }
    }

    public Bitmap doImageGetRequest(URI uri, int i, int i2) throws IOException {
        if (uri != null) {
            return decodeSampledBitmap(uri.toURL(), i, i2);
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.URI_REQUIRED));
    }

    private static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("calculateInSampleSize: height = ");
        sb.append(i3);
        sb.append(", width = ");
        sb.append(i4);
        sb.append(", maxWidth = ");
        sb.append(i);
        sb.append(", maxHeight = ");
        sb.append(i2);
        Log.v(str, sb.toString());
        int i5 = i4 * i2;
        int i6 = i * i3;
        if (i5 > i6) {
            i2 = i6 / i4;
        } else if (i5 < i6) {
            i = i5 / i3;
        }
        String str2 = TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("calculateInSampleSize: maxWidth = ");
        sb2.append(i);
        sb2.append(", maxHeight = ");
        sb2.append(i2);
        Log.v(str2, sb2.toString());
        int i7 = 1;
        if (i3 > i2 || i4 > i) {
            int i8 = i3 / 2;
            int i9 = i4 / 2;
            while (i8 / i7 > i2 && i9 / i7 > i) {
                i7 *= 2;
            }
        }
        return i7;
    }

    private Bitmap decodeSampledBitmap(URL url, int i, int i2) throws IOException {
        URLConnection uRLConnection;
        Options options = new Options();
        try {
            uRLConnection = url.openConnection();
            try {
                uRLConnection.setConnectTimeout(this.connectTimeout);
                uRLConnection.setReadTimeout(this.readTimeout);
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(uRLConnection.getInputStream(), null, options);
                options.inSampleSize = calculateInSampleSize(options, i, i2);
                if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
                    ((HttpURLConnection) uRLConnection).disconnect();
                }
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("decodeSampledBitmap: total memory = ");
                sb.append(Runtime.getRuntime().totalMemory());
                Log.v(str, sb.toString());
                try {
                    uRLConnection = url.openConnection();
                    uRLConnection.setConnectTimeout(this.connectTimeout);
                    uRLConnection.setReadTimeout(this.readTimeout);
                    options.inJustDecodeBounds = false;
                    Bitmap decodeStream = BitmapFactory.decodeStream(uRLConnection.getInputStream(), null, options);
                    String str2 = TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("decodeSampledBitmap: total memory = ");
                    sb2.append(Runtime.getRuntime().totalMemory());
                    Log.v(str2, sb2.toString());
                    return decodeStream;
                } finally {
                    if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
                        ((HttpURLConnection) uRLConnection).disconnect();
                    }
                }
            } catch (Throwable th) {
                th = th;
                ((HttpURLConnection) uRLConnection).disconnect();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            uRLConnection = null;
            if (uRLConnection != null && (uRLConnection instanceof HttpURLConnection)) {
                ((HttpURLConnection) uRLConnection).disconnect();
            }
            throw th;
        }
    }

    public JSONObject doJSONGetRequest(URI uri) throws IOException, JSONException {
        return doJSONGetRequest(uri, new HashMap());
    }

    public JSONObject doJSONGetRequest(URI uri, Map<String, String> map) throws IOException, JSONException {
        return doJSONRequest(Method.GET, uri, map);
    }

    public JSONObject doJSONPostRequest(URI uri) throws IOException, JSONException {
        return doJSONPostRequest(uri, new HashMap());
    }

    public JSONObject doJSONPostRequest(URI uri, Map<String, String> map) throws IOException, JSONException {
        return doJSONRequest(Method.POST, uri, map);
    }

    private JSONObject doJSONRequest(Method method, URI uri, Map<String, String> map) throws IOException, JSONException {
        String doRequest = doRequest(method, uri, map);
        if (doRequest == null || doRequest.trim().isEmpty() || doRequest.equals("null")) {
            return null;
        }
        if (Log.isLoggable(TAG, 3)) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("JSON response:\n");
            sb.append(doRequest);
            Log.d(str, sb.toString());
        }
        if (doRequest.startsWith("[") && doRequest.endsWith("]")) {
            doRequest = doRequest.substring(1, doRequest.length() - 1);
        }
        return parseToJSONObject(doRequest);
    }

    public static String inputStreamToString(InputStream inputStream) throws IOException {
        return readerToString(new InputStreamReader(inputStream));
    }

    /* JADX INFO: finally extract failed */
    public static String readerToString(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(reader);
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
        }
    }

    public static JSONObject parseToJSONObject(String str) throws JSONException {
        if (str == null || str.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.JSON_REQUIRED));
        }
        Object nextValue = new JSONTokener(str).nextValue();
        if (nextValue instanceof JSONObject) {
            return (JSONObject) nextValue;
        }
        if (nextValue instanceof JSONArray) {
            throw new JSONException(ErrorUtil.getMessage(ErrorUtil.JSON_ARRAY));
        }
        throw new JSONException(String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.UNEXPECTED_TYPE), new Object[]{nextValue.getClass().getName()}));
    }
}
