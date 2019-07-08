package com.myfitnesspal.shared.util;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import com.myfitnesspal.shared.db.table.ProfileImagesTable.Columns;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/myfitnesspal/shared/util/FileUploadUtil;", "", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FileUploadUtil.kt */
public final class FileUploadUtil {
    public static final Companion Companion = new Companion(null);
    private static final String FILE_PROVIDER_SHARE_CACHE_AUTHORITY = "com.myfitnesspal.android.fileprovidersharecache";
    private static final String TEMP_FILE_RELATIVE_PATH = "/share/";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0018\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/myfitnesspal/shared/util/FileUploadUtil$Companion;", "", "()V", "FILE_PROVIDER_SHARE_CACHE_AUTHORITY", "", "TEMP_FILE_RELATIVE_PATH", "createFile", "Ljava/io/File;", "dirname", "filename", "getCacheShareUriForFile", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "getTemporaryImageFilepath", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FileUploadUtil.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Uri getTemporaryImageFilepath(@NotNull Context context, @NotNull String str) throws IOException {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(str, Columns.FILENAME);
            StringBuilder sb = new StringBuilder();
            File filesDir = context.getFilesDir();
            Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
            sb.append(filesDir.getAbsolutePath());
            sb.append(File.separator);
            Uri fromFile = Uri.fromFile(createFile(sb.toString(), str));
            Intrinsics.checkExpressionValueIsNotNull(fromFile, "Uri.fromFile(file)");
            return fromFile;
        }

        @JvmStatic
        @NotNull
        public final Uri getCacheShareUriForFile(@NotNull Context context, @NotNull String str) throws IOException {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(str, Columns.FILENAME);
            StringBuilder sb = new StringBuilder();
            sb.append(context.getExternalCacheDir().toString());
            sb.append(FileUploadUtil.TEMP_FILE_RELATIVE_PATH);
            Uri uriForFile = FileProvider.getUriForFile(context, FileUploadUtil.FILE_PROVIDER_SHARE_CACHE_AUTHORITY, createFile(sb.toString(), str));
            Intrinsics.checkExpressionValueIsNotNull(uriForFile, "getUriForFile(context, F…RE_CACHE_AUTHORITY, file)");
            return uriForFile;
        }

        @NotNull
        public final File createFile(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "dirname");
            Intrinsics.checkParameterIsNotNull(str2, Columns.FILENAME);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            File file = new File(sb.toString());
            file.getParentFile().mkdirs();
            file.createNewFile();
            if (file.exists()) {
                return file;
            }
            throw new IOException("unable to create empty file");
        }
    }

    @JvmStatic
    @NotNull
    public static final Uri getCacheShareUriForFile(@NotNull Context context, @NotNull String str) throws IOException {
        return Companion.getCacheShareUriForFile(context, str);
    }

    @JvmStatic
    @NotNull
    public static final Uri getTemporaryImageFilepath(@NotNull Context context, @NotNull String str) throws IOException {
        return Companion.getTemporaryImageFilepath(context, str);
    }
}
