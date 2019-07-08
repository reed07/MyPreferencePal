package com.mopub.volley.toolbox;

import com.facebook.ads.AudienceNetworkActivity;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.Response.Listener;
import com.mopub.volley.VolleyLog;
import java.io.UnsupportedEncodingException;

public abstract class JsonRequest<T> extends Request<T> {
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[]{AudienceNetworkActivity.WEBVIEW_ENCODING});
    private Listener<T> mListener;
    private final Object mLock;
    private final String mRequestBody;

    /* access modifiers changed from: protected */
    public abstract Response<T> parseNetworkResponse(NetworkResponse networkResponse);

    @Deprecated
    public JsonRequest(String str, String str2, Listener<T> listener, ErrorListener errorListener) {
        this(-1, str, str2, listener, errorListener);
    }

    public JsonRequest(int i, String str, String str2, Listener<T> listener, ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mLock = new Object();
        this.mListener = listener;
        this.mRequestBody = str2;
    }

    public void cancel() {
        super.cancel();
        synchronized (this.mLock) {
            this.mListener = null;
        }
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(T t) {
        Listener<T> listener;
        synchronized (this.mLock) {
            listener = this.mListener;
        }
        if (listener != null) {
            listener.onResponse(t);
        }
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Deprecated
    public byte[] getPostBody() {
        return getBody();
    }

    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public byte[] getBody() {
        byte[] bArr = null;
        try {
            if (this.mRequestBody != null) {
                bArr = this.mRequestBody.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING);
            }
            return bArr;
        } catch (UnsupportedEncodingException unused) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", this.mRequestBody, AudienceNetworkActivity.WEBVIEW_ENCODING);
            return null;
        }
    }
}
