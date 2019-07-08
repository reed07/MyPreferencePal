package com.bumptech.glide.load.engine.cache;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import java.io.File;

public interface DiskCache {

    public interface Factory {
        @Nullable
        DiskCache build();
    }

    public interface Writer {
        boolean write(@NonNull File file);
    }

    @Nullable
    File get(Key key);

    void put(Key key, Writer writer);
}
