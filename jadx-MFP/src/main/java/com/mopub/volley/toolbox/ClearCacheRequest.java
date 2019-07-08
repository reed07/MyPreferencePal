package com.mopub.volley.toolbox;

import android.os.Handler;
import android.os.Looper;
import com.mopub.volley.Cache;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.Request.Priority;
import com.mopub.volley.Response;

public class ClearCacheRequest extends Request<Object> {
    private final Cache mCache;
    private final Runnable mCallback;

    /* access modifiers changed from: protected */
    public void deliverResponse(Object obj) {
    }

    /* access modifiers changed from: protected */
    public Response<Object> parseNetworkResponse(NetworkResponse networkResponse) {
        return null;
    }

    public ClearCacheRequest(Cache cache, Runnable runnable) {
        super(0, null, null);
        this.mCache = cache;
        this.mCallback = runnable;
    }

    public boolean isCanceled() {
        this.mCache.clear();
        if (this.mCallback != null) {
            new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.mCallback);
        }
        return true;
    }

    public Priority getPriority() {
        return Priority.IMMEDIATE;
    }
}
