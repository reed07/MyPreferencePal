package com.amazon.device.ads;

import android.support.annotation.NonNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.json.JSONException;

class DtbHttpClient {
    private HashMap<String, Object> headers;
    private boolean isQueryParamsEnabled = false;
    private String message;
    private HashMap<String, Object> params;
    private String response = null;
    private int responseCode;
    private boolean secure = true;
    private String url;

    private enum HTTPMethod {
        POST,
        GET
    }

    private static class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext;

        /* renamed from: com.amazon.device.ads.DtbHttpClient$MySSLSocketFactory$1 reason: invalid class name */
        class AnonymousClass1 implements X509TrustManager {
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }

        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return this.sslContext.getSocketFactory().createSocket(socket, str, i, z);
        }

        public Socket createSocket() throws IOException {
            return this.sslContext.getSocketFactory().createSocket();
        }
    }

    /* access modifiers changed from: protected */
    public String getResponse() {
        return this.response;
    }

    /* access modifiers changed from: protected */
    public int getResponseCode() {
        return this.responseCode;
    }

    protected DtbHttpClient(String str) {
        this.url = str;
        this.params = new HashMap<>();
        this.headers = new HashMap<>();
    }

    /* access modifiers changed from: protected */
    public void addHeader(String str, String str2) {
        this.headers.put(str, str2);
    }

    /* access modifiers changed from: protected */
    public void setParams(HashMap<String, Object> hashMap) {
        this.params = hashMap;
    }

    public void setUseSecure(boolean z) {
        this.secure = z;
    }

    public void enableQueryParams() {
        this.isQueryParamsEnabled = true;
    }

    /* access modifiers changed from: protected */
    public void executePOST() throws JSONException, IOException {
        URL url2;
        StringBuilder sb = new StringBuilder();
        sb.append(this.secure ? "https://" : "http://");
        sb.append(this.url);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("POST URL:");
        sb3.append(sb2);
        DtbLog.debug(sb3.toString());
        if (this.isQueryParamsEnabled) {
            String queryParams = getQueryParams();
            StringBuilder sb4 = new StringBuilder();
            sb4.append("with query params:[");
            sb4.append(queryParams);
            sb4.append("]");
            DtbLog.debug(sb4.toString());
            StringBuilder sb5 = new StringBuilder();
            sb5.append(sb2);
            sb5.append(queryParams);
            url2 = new URL(sb5.toString());
        } else {
            url2 = new URL(sb2);
        }
        executeRequest(HTTPMethod.POST, url2);
    }

    public boolean isHttpStatusCodeOK() {
        return this.responseCode == 200;
    }

    private String getQueryParams() {
        String str = "";
        if (!this.params.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("?");
            str = sb.toString();
            for (String str2 : this.params.keySet()) {
                if (this.params.get(str2) != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str2);
                    sb2.append("=");
                    sb2.append(DtbCommonUtils.getURLEncodedString(this.params.get(str2).toString()));
                    String sb3 = sb2.toString();
                    if (str.length() > 1) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(str);
                        sb4.append("&");
                        sb4.append(sb3);
                        str = sb4.toString();
                    } else {
                        StringBuilder sb5 = new StringBuilder();
                        sb5.append(str);
                        sb5.append(sb3);
                        str = sb5.toString();
                    }
                }
            }
        }
        return str;
    }

    private String getParamsAsJsonString() throws JSONException {
        return DtbCommonUtils.getParamsAsJsonString(this.params);
    }

    /* access modifiers changed from: protected */
    public void executeGET() throws JSONException, IOException {
        String queryParams = getQueryParams();
        StringBuilder sb = new StringBuilder();
        sb.append(this.secure ? "https://" : "http://");
        sb.append(this.url);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("GET URL:");
        sb3.append(sb2);
        DtbLog.debug(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append("with params: ");
        sb4.append(queryParams);
        DtbLog.debug(sb4.toString());
        StringBuilder sb5 = new StringBuilder();
        sb5.append(sb2);
        sb5.append(queryParams);
        executeRequest(HTTPMethod.GET, new URL(sb5.toString()));
    }

    private void executeRequest(HTTPMethod hTTPMethod, URL url2) throws JSONException, IOException {
        HttpURLConnection createDefaultConnection = createDefaultConnection(url2);
        StringBuilder sb = new StringBuilder();
        for (String str : this.headers.keySet()) {
            String obj = this.headers.get(str) != null ? this.headers.get(str).toString() : "";
            createDefaultConnection.setRequestProperty(str, obj);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(":");
            sb2.append(obj);
            sb2.append(" ");
            sb.append(sb2.toString());
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("with headers:[");
        sb3.append(sb.toString());
        sb3.append("]");
        DtbLog.debug(sb3.toString());
        if (hTTPMethod == HTTPMethod.POST) {
            createDefaultConnection.setDoOutput(true);
            if (!this.isQueryParamsEnabled && !this.params.isEmpty()) {
                createDefaultConnection.setRequestProperty("content-type", "application/json; charset=utf-8");
                String paramsAsJsonString = getParamsAsJsonString();
                StringBuilder sb4 = new StringBuilder();
                sb4.append("with json params:[");
                sb4.append(paramsAsJsonString);
                sb4.append("]");
                DtbLog.debug(sb4.toString());
                OutputStream outputStream = createDefaultConnection.getOutputStream();
                outputStream.write(paramsAsJsonString.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
        try {
            InputStream inputStream = createDefaultConnection.getInputStream();
            if (inputStream == null) {
                createDefaultConnection.disconnect();
                return;
            }
            this.responseCode = createDefaultConnection.getResponseCode();
            this.message = createDefaultConnection.getResponseMessage();
            this.response = convertStreamToString(inputStream);
            inputStream.close();
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Response :");
            sb5.append(this.response);
            DtbLog.debug(sb5.toString());
            createDefaultConnection.disconnect();
        } catch (Exception e) {
            this.response = null;
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Error while connecting to remote server: ");
            sb6.append(createDefaultConnection.getURL().toString());
            sb6.append(" with error:");
            sb6.append(e.getMessage());
            DtbLog.debug(sb6.toString());
        } catch (Throwable th) {
            createDefaultConnection.disconnect();
            throw th;
        }
    }

    private HttpURLConnection createDefaultConnection(URL url2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.setReadTimeout(60000);
        return httpURLConnection;
    }

    @NonNull
    private static String convertStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(readLine);
                    sb2.append("\n");
                    sb.append(sb2.toString());
                }
            } catch (IOException e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Error converting stream to string. Ex=");
                sb3.append(e);
                DtbLog.debug(sb3.toString());
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
                throw th;
            }
            try {
                break;
            } catch (IOException unused2) {
            }
        }
        inputStream.close();
        return sb.toString();
    }
}
