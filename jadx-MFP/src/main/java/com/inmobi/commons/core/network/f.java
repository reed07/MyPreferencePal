package com.inmobi.commons.core.network;

import com.inmobi.commons.core.e.b;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

/* compiled from: VastBitRateNetworkConnection */
public final class f extends b {
    public final /* bridge */ /* synthetic */ d a() {
        return super.a();
    }

    public f(c cVar) {
        super(cVar);
    }

    /* access modifiers changed from: protected */
    public final d b() {
        d dVar = new d();
        try {
            int responseCode = this.c.getResponseCode();
            StringBuilder sb = new StringBuilder();
            sb.append(this.b.q);
            sb.append("Response code: ");
            sb.append(responseCode);
            dVar.c = this.c.getContentLength();
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
                StringBuilder sb2 = new StringBuilder();
                sb2.append(e.getMessage());
                hashMap.put("message", sb2.toString());
                b.a();
                b.a("root", "ExceptionCaught", hashMap);
            } catch (Exception unused4) {
                StringBuilder sb3 = new StringBuilder("Error in submitting telemetry event : (");
                sb3.append(e.getMessage());
                sb3.append(")");
            }
        } catch (Throwable th) {
            this.c.disconnect();
            throw th;
        }
        return dVar;
    }
}
