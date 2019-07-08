package com.brightcove.player.offline;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;

public class ExternalFileCreator implements DownloadFileCreator {
    @Nullable
    public File getDownloadsFolder(@NonNull Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
    }
}
