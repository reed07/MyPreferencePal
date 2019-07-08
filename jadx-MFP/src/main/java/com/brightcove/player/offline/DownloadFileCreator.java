package com.brightcove.player.offline;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;

public interface DownloadFileCreator {
    @Nullable
    File getDownloadsFolder(@NonNull Context context);
}
