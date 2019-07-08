package com.google.ads.mediation.inmobi;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

class InMobiMemoryCache {
    private static final String TAG = "MemoryCache";
    private final Map<String, Drawable> cache = Collections.synchronizedMap(new LinkedHashMap(10, 1.5f, true));
    private long limit = 1000000;
    private long size = 0;

    InMobiMemoryCache() {
        setLimit(Runtime.getRuntime().maxMemory() / 4);
    }

    private void setLimit(long j) {
        this.limit = j;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("MemoryCache will use up to ");
        sb.append((((double) this.limit) / 1024.0d) / 1024.0d);
        sb.append("MB");
        Log.i(str, sb.toString());
    }

    public Drawable get(String str) {
        try {
            if (!this.cache.containsKey(str)) {
                return null;
            }
            return (Drawable) this.cache.get(str);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void put(String str, Drawable drawable) {
        try {
            if (this.cache.containsKey(str)) {
                this.size -= getSizeInBytes(((BitmapDrawable) this.cache.get(str)).getBitmap());
            }
            this.cache.put(str, drawable);
            this.size += getSizeInBytes(((BitmapDrawable) drawable).getBitmap());
            checkSize();
            Log.d(TAG, "Drawable used from cache");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void checkSize() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("cache size=");
        sb.append(this.size);
        sb.append(" length=");
        sb.append(this.cache.size());
        Log.i(str, sb.toString());
        if (this.size > this.limit) {
            Iterator it = this.cache.entrySet().iterator();
            while (it.hasNext()) {
                this.size -= getSizeInBytes(((BitmapDrawable) ((Entry) it.next()).getValue()).getBitmap());
                it.remove();
                if (this.size <= this.limit) {
                    break;
                }
            }
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Clean cache. New size ");
            sb2.append(this.cache.size());
            Log.i(str2, sb2.toString());
        }
    }

    public void clear() {
        try {
            this.cache.clear();
            this.size = 0;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private long getSizeInBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        return (long) (bitmap.getRowBytes() * bitmap.getHeight());
    }
}
