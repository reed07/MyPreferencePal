package com.bumptech.glide.load.engine.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.cache.MemoryCache.ResourceRemovedListener;

public class MemoryCacheAdapter implements MemoryCache {
    private ResourceRemovedListener listener;

    public void clearMemory() {
    }

    @Nullable
    public Resource<?> remove(@NonNull Key key) {
        return null;
    }

    public void setSizeMultiplier(float f) {
    }

    public void trimMemory(int i) {
    }

    @Nullable
    public Resource<?> put(@NonNull Key key, @Nullable Resource<?> resource) {
        if (resource != null) {
            this.listener.onResourceRemoved(resource);
        }
        return null;
    }

    public void setResourceRemovedListener(@NonNull ResourceRemovedListener resourceRemovedListener) {
        this.listener = resourceRemovedListener;
    }
}
