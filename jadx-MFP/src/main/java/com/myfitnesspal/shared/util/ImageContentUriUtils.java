package com.myfitnesspal.shared.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.MediaStore.Images.Media;
import com.uacf.core.util.Strings;

public class ImageContentUriUtils {
    private static final String DOCUMENTS_AUTHORITY = "com.android.providers.media.documents";
    private static final String DOCUMENTS_PROVIDER_IMAGES = "image";
    private static final String DOWNLOADS_AUTHORITY = "com.android.providers.downloads.documents";
    private static final String DOWNLOADS_DATA_COLUMN = "_data";
    private static final Uri DOWNLOADS_QUERY_URI = Uri.parse("content://downloads/public_downloads");
    private static final String FILE_SCHEME = "file";
    private static final String IMAGE_FILENAME_COLUMN = "_data";
    private static final String MEDIA_AUTHORITY = "media";

    public static Uri getImageFilename(Context context, Uri uri) {
        String authority = uri.getAuthority();
        if ("media".equals(authority)) {
            return getImageFilenameFromMediaUri(context, uri);
        }
        if (DOCUMENTS_AUTHORITY.equals(authority)) {
            return getImageFilenameFromDocumentUri(context, uri);
        }
        if (DOWNLOADS_AUTHORITY.equals(authority)) {
            return getImageFilenameFromDownloadsUri(context, uri);
        }
        return null;
    }

    public static Uri getImageFilenameFromMediaUri(Context context, Uri uri) {
        return extractLocalFilename(context, uri, "_data");
    }

    public static Uri getImageFilenameFromDocumentUri(Context context, Uri uri) {
        String[] split = uri.getLastPathSegment().split(":");
        if (split.length != 2 || !"image".equals(split[0])) {
            return null;
        }
        Uri uri2 = Media.EXTERNAL_CONTENT_URI;
        Uri uri3 = Media.INTERNAL_CONTENT_URI;
        Uri imageFilenameFromMediaUri = getImageFilenameFromMediaUri(context, uri2.buildUpon().appendPath(split[1]).build());
        if (imageFilenameFromMediaUri == null) {
            imageFilenameFromMediaUri = getImageFilenameFromMediaUri(context, uri3.buildUpon().appendPath(split[1]).build());
        }
        return imageFilenameFromMediaUri;
    }

    public static Uri getImageFilenameFromDownloadsUri(Context context, Uri uri) {
        String lastPathSegment = uri.getLastPathSegment();
        if (Strings.notEmpty(lastPathSegment)) {
            return extractLocalFilename(context, DOWNLOADS_QUERY_URI.buildUpon().appendPath(lastPathSegment).build(), "_data");
        }
        return null;
    }

    private static Uri extractLocalFilename(Context context, Uri uri, String str) {
        Cursor query = context.getContentResolver().query(uri, new String[]{str}, null, null, null);
        if (query != null) {
            try {
                query.moveToFirst();
                return new Builder().scheme(FILE_SCHEME).path(query.getString(query.getColumnIndexOrThrow(str))).build();
            } catch (Exception unused) {
            } finally {
                query.close();
            }
        }
        return null;
    }
}
