package io.fabric.sdk.android.services.persistence;

import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.io.File;

public class FileStoreImpl implements FileStore {
    private final String contentPath;
    private final Context context;
    private final String legacySupport;

    public FileStoreImpl(Kit kit) {
        if (kit.getContext() != null) {
            this.context = kit.getContext();
            this.contentPath = kit.getPath();
            StringBuilder sb = new StringBuilder();
            sb.append("Android/");
            sb.append(this.context.getPackageName());
            this.legacySupport = sb.toString();
            return;
        }
        throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
    }

    public File getFilesDir() {
        return prepare(this.context.getFilesDir());
    }

    /* access modifiers changed from: 0000 */
    public File prepare(File file) {
        if (file == null) {
            Fabric.getLogger().d("Fabric", "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            Fabric.getLogger().w("Fabric", "Couldn't create file");
        }
        return null;
    }
}
