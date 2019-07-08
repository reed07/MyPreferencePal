package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher.DataCallback;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class LocalUriFetcher<T> implements DataFetcher<T> {
    private final ContentResolver contentResolver;
    private T data;
    private final Uri uri;

    public void cancel() {
    }

    /* access modifiers changed from: protected */
    public abstract void close(T t) throws IOException;

    /* access modifiers changed from: protected */
    public abstract T loadResource(Uri uri2, ContentResolver contentResolver2) throws FileNotFoundException;

    public LocalUriFetcher(ContentResolver contentResolver2, Uri uri2) {
        this.contentResolver = contentResolver2;
        this.uri = uri2;
    }

    public final void loadData(@NonNull Priority priority, @NonNull DataCallback<? super T> dataCallback) {
        try {
            this.data = loadResource(this.uri, this.contentResolver);
            dataCallback.onDataReady(this.data);
        } catch (FileNotFoundException e) {
            if (Log.isLoggable("LocalUriFetcher", 3)) {
                Log.d("LocalUriFetcher", "Failed to open Uri", e);
            }
            dataCallback.onLoadFailed(e);
        }
    }

    public void cleanup() {
        T t = this.data;
        if (t != null) {
            try {
                close(t);
            } catch (IOException unused) {
            }
        }
    }

    @NonNull
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
