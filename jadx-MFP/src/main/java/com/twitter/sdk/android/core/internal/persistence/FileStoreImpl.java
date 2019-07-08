package com.twitter.sdk.android.core.internal.persistence;

import android.content.Context;
import com.twitter.sdk.android.core.Twitter;
import java.io.File;

public class FileStoreImpl implements FileStore {
    private final Context context;

    public FileStoreImpl(Context context2) {
        if (context2 != null) {
            this.context = context2;
            return;
        }
        throw new IllegalArgumentException("Context must not be null");
    }

    public File getFilesDir() {
        return prepare(this.context.getFilesDir());
    }

    /* access modifiers changed from: 0000 */
    public File prepare(File file) {
        if (file == null) {
            Twitter.getLogger().d("Twitter", "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            Twitter.getLogger().w("Twitter", "Couldn't create file");
        }
        return null;
    }
}
