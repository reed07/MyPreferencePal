package com.myfitnesspal.feature.images.util;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import com.myfitnesspal.shared.provider.FileProviderPrivateExternal;
import com.myfitnesspal.shared.provider.FileProviderShareCache;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.Strings;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class ImageUploadUtil {
    private static final String FILE_PROVIDER_PRIVATE_EXTERNAL = "com.myfitnesspal.android.fileproviderprivateexternal";
    private static final String FILE_PROVIDER_SHARE_CACHE_AUTHORITY = "com.myfitnesspal.android.fileprovidersharecache";
    private static final String PRIVATE = "private";
    private static final String TEMP_IMAGE_RELATIVE_PATH = "/share/";
    private static final String UPLOAD_IMAGE_RELATIVE_PATH = "/upload/";

    public static Uri getTemporaryImageFilepath(Context context, String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalCacheDir());
        sb.append(TEMP_IMAGE_RELATIVE_PATH);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(str);
        File file = new File(sb3.toString());
        file.getParentFile().mkdirs();
        file.createNewFile();
        if (file.exists()) {
            return Uri.fromFile(file);
        }
        throw new IOException("unable to create empty file");
    }

    public static Uri getCacheShareUriForFile(Context context, String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalCacheDir());
        sb.append(TEMP_IMAGE_RELATIVE_PATH);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(str);
        File file = new File(sb3.toString());
        file.getParentFile().mkdirs();
        file.createNewFile();
        if (file.exists()) {
            return FileProviderShareCache.getUriForFile(context, FILE_PROVIDER_SHARE_CACHE_AUTHORITY, file);
        }
        throw new IOException("unable to create empty file");
    }

    public static Uri getPrivateExternalUriForFile(Context context, String str) throws IOException {
        if (!Strings.isEmpty(str)) {
            return FileProviderPrivateExternal.getUriForFile(context, FILE_PROVIDER_PRIVATE_EXTERNAL, new File(new File(Environment.getExternalStorageDirectory(), PRIVATE), str));
        }
        throw new IOException("unable to create empty file");
    }

    private static String getCacheFileDirectory(Context context, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(context.getExternalCacheDir());
        sb.append(str);
        String sb2 = sb.toString();
        File file = new File(sb2);
        file.mkdirs();
        if (file.exists()) {
            return sb2;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(context.getCacheDir());
        sb3.append(str);
        String sb4 = sb3.toString();
        new File(sb4).mkdirs();
        return sb4;
    }

    public static String copyFileToUploadDirectory(Context context, String str) throws IOException {
        return FileUtils.copyFile(context, str, getUploadDirectory(context), UUID.randomUUID().toString());
    }

    private static String getUploadDirectory(Context context) {
        return getCacheFileDirectory(context, UPLOAD_IMAGE_RELATIVE_PATH);
    }

    public static void checkDeleteTempImage(Context context, String str) {
        if (str.startsWith(getUploadDirectory(context))) {
            new File(str).delete();
        }
    }
}
