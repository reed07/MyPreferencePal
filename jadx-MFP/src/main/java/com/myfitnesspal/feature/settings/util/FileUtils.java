package com.myfitnesspal.feature.settings.util;

import android.net.Uri;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\b¨\u0006\f"}, d2 = {"Lcom/myfitnesspal/feature/settings/util/FileUtils;", "", "()V", "getFile", "Ljava/io/File;", "uri", "Landroid/net/Uri;", "getPath", "", "isLocal", "", "url", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FileUtils.kt */
public final class FileUtils {
    public static final FileUtils INSTANCE = new FileUtils();

    private FileUtils() {
    }

    public final boolean isLocal(@Nullable String str) {
        return str != null && !StringsKt.startsWith$default(str, "http://", false, 2, null) && !StringsKt.startsWith$default(str, "https://", false, 2, null);
    }

    @Nullable
    public final String getPath(@NotNull Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, ShareConstants.MEDIA_URI);
        if (StringsKt.equals("file", uri.getScheme(), true)) {
            return uri.getPath();
        }
        return null;
    }

    @Nullable
    public final File getFile(@Nullable Uri uri) {
        if (uri != null) {
            String path = getPath(uri);
            if (path != null && isLocal(path)) {
                return new File(path);
            }
        }
        return null;
    }
}
