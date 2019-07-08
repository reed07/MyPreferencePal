package com.brightcove.player.drm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.brightcove.player.model.Video.Fields;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BrightcoveMediaDrmCallback {
    protected static final String BRIGHTCOVE_BASE_URL = "https://wvlic.brightcove.com/proxy/";
    public static final String DEFAULT_URL = "defaultUrl";
    protected static final Map<String, String> REQUEST_HEADERS = Collections.singletonMap("Content-Type", "application/octet-stream");
    private static final String TAG = "BrightcoveMediaDrmCallback";
    protected final String defaultUrl;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<String, String> optionalHeaders;

    protected BrightcoveMediaDrmCallback(@Nullable String str) {
        this.defaultUrl = str;
    }

    @Nullable
    public Map<String, String> getOptionalHeaders() {
        this.lock.readLock().lock();
        try {
            return this.optionalHeaders;
        } finally {
            this.lock.readLock().unlock();
        }
    }

    public void setOptionalHeaders(@Nullable Map<String, String> map) {
        this.lock.writeLock().lock();
        try {
            this.optionalHeaders = map;
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void setVideoHeaders(@NonNull Map<String, Object> map) {
        setLicenseRequestHeaders(map);
    }

    /* access modifiers changed from: protected */
    public void setLicenseRequestHeaders(@NonNull Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        try {
            Object obj = map.get(Fields.WIDEVINE_HEADERS);
            if (obj != null && (obj instanceof Map)) {
                Map map2 = (Map) obj;
                StringBuilder sb = new StringBuilder();
                sb.append("Adding headers for license request: ");
                sb.append(map2);
                Log.i("DRM", sb.toString());
                for (Entry entry : map2.entrySet()) {
                    hashMap.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "Failed to find license request headers.", e);
        }
        setOptionalHeaders(hashMap);
    }
}
