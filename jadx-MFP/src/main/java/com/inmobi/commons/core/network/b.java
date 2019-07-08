package com.inmobi.commons.core.network;

import com.brightcove.player.event.AbstractEvent;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.utilities.d;
import com.myfitnesspal.shared.constants.HttpConstants;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NetworkConnection */
class b {
    protected static final String a = "com.inmobi.commons.core.network.b";
    protected c b;
    protected HttpURLConnection c;

    public b(c cVar) {
        this.b = cVar;
    }

    public d a() {
        d dVar;
        this.b.a();
        if (this.b.x != 1) {
            d dVar2 = new d();
            dVar2.b = new NetworkError(ErrorCode.GDPR_COMPLIANCE_ENFORCED, "Network Request dropped as current request is not GDPR compliant.");
            return dVar2;
        }
        if (d.a()) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.b.e()).openConnection();
                a(httpURLConnection);
                this.c = httpURLConnection;
                if (!this.b.t) {
                    this.c.setInstanceFollowRedirects(false);
                }
                if (HttpConstants.METHOD_POST.equals(this.b.p)) {
                    String f = this.b.f();
                    this.c.setRequestProperty("Content-Length", Integer.toString(f.length()));
                    this.c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    BufferedWriter bufferedWriter = null;
                    try {
                        BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(this.c.getOutputStream()));
                        try {
                            bufferedWriter2.write(f);
                            d.a((Closeable) bufferedWriter2);
                        } catch (Throwable th) {
                            th = th;
                            bufferedWriter = bufferedWriter2;
                            d.a((Closeable) bufferedWriter);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        d.a((Closeable) bufferedWriter);
                        throw th;
                    }
                }
                dVar = b();
            } catch (IOException e) {
                d dVar3 = new d();
                dVar3.b = new NetworkError(ErrorCode.NETWORK_IO_ERROR, e.getLocalizedMessage());
                dVar = dVar3;
            } catch (Exception e2) {
                d dVar4 = new d();
                dVar4.b = new NetworkError(ErrorCode.UNKNOWN_ERROR, e2.getLocalizedMessage());
                try {
                    HashMap hashMap = new HashMap();
                    hashMap.put("type", "GenericException");
                    StringBuilder sb = new StringBuilder();
                    sb.append(e2.getMessage());
                    hashMap.put("message", sb.toString());
                    com.inmobi.commons.core.e.b.a();
                    com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", hashMap);
                } catch (Exception unused) {
                    StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                    sb2.append(e2.getMessage());
                    sb2.append(")");
                }
                dVar = dVar4;
            }
        } else {
            dVar = new d();
            dVar.b = new NetworkError(ErrorCode.NETWORK_UNAVAILABLE_ERROR, "Network not reachable currently. Please try again.");
        }
        return dVar;
    }

    private void a(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(this.b.r);
        httpURLConnection.setReadTimeout(this.b.s);
        httpURLConnection.setUseCaches(false);
        Map d = this.b.d();
        if (d != null) {
            for (String str : d.keySet()) {
                httpURLConnection.setRequestProperty(str, (String) d.get(str));
            }
        }
        String str2 = this.b.p;
        httpURLConnection.setRequestMethod(str2);
        if (!HttpConstants.METHOD_GET.equals(str2)) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
    }

    /* access modifiers changed from: protected */
    public d b() {
        d dVar = new d();
        try {
            int responseCode = this.c.getResponseCode();
            StringBuilder sb = new StringBuilder();
            sb.append(this.b.q);
            sb.append("Response code: ");
            sb.append(responseCode);
            if (responseCode == 200) {
                a(dVar, false);
            } else {
                ErrorCode fromValue = ErrorCode.fromValue(responseCode);
                if (fromValue == ErrorCode.BAD_REQUEST) {
                    a(dVar, true);
                    dVar.b = new NetworkError(fromValue, a(dVar.b()));
                } else {
                    if (fromValue == null) {
                        fromValue = ErrorCode.UNKNOWN_ERROR;
                    }
                    StringBuilder sb2 = new StringBuilder("HTTP:");
                    sb2.append(responseCode);
                    dVar.b = new NetworkError(fromValue, sb2.toString());
                    dVar.d = this.c.getHeaderFields();
                }
            }
            this.c.disconnect();
        } catch (SocketTimeoutException unused) {
            dVar.b = new NetworkError(ErrorCode.HTTP_GATEWAY_TIMEOUT, ErrorCode.HTTP_GATEWAY_TIMEOUT.toString());
        } catch (IOException unused2) {
            dVar.b = new NetworkError(ErrorCode.NETWORK_IO_ERROR, ErrorCode.NETWORK_IO_ERROR.toString());
        } catch (OutOfMemoryError unused3) {
            dVar.b = new NetworkError(ErrorCode.OUT_OF_MEMORY_ERROR, ErrorCode.OUT_OF_MEMORY_ERROR.toString());
        } catch (Exception e) {
            dVar.b = new NetworkError(ErrorCode.UNKNOWN_ERROR, ErrorCode.UNKNOWN_ERROR.toString());
            try {
                HashMap hashMap = new HashMap();
                hashMap.put("type", "GenericException");
                StringBuilder sb3 = new StringBuilder();
                sb3.append(e.getMessage());
                hashMap.put("message", sb3.toString());
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", hashMap);
            } catch (Exception unused4) {
                StringBuilder sb4 = new StringBuilder("Error in submitting telemetry event : (");
                sb4.append(e.getMessage());
                sb4.append(")");
            }
        } catch (Throwable th) {
            this.c.disconnect();
            throw th;
        }
        return dVar;
    }

    private void a(d dVar, boolean z) throws IOException {
        if (!(this.b.v != -1) || ((long) this.c.getContentLength()) <= this.b.v) {
            byte[] a2 = d.a(z ? this.c.getErrorStream() : this.c.getInputStream());
            if (a2.length != 0) {
                if (this.b.b()) {
                    a2 = this.b.a(a2);
                    if (a2 == null) {
                        dVar.b = new NetworkError(ErrorCode.INVALID_ENCRYPTED_RESPONSE_RECEIVED, "Unable to decrypt the server response.");
                    }
                }
                if (a2 != null && this.b.w) {
                    a2 = d.a(a2);
                    if (a2 == null) {
                        dVar.b = new NetworkError(ErrorCode.GZIP_DECOMPRESSION_FAILED, "Failed to uncompress gzip response");
                    }
                }
                if (a2 != null) {
                    dVar.b(a2);
                }
            }
            dVar.d = this.c.getHeaderFields();
            return;
        }
        dVar.b = new NetworkError(ErrorCode.RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT, "Response size greater than specified max response size");
    }

    private static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(AbstractEvent.ERROR_MESSAGE)) {
                return jSONObject.getString(AbstractEvent.ERROR_MESSAGE);
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }
}
