package com.myfitnesspal.shared.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.myfitnesspal.shared.model.Size;
import com.uacf.core.util.FileUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.Sanselan;
import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
import org.apache.sanselan.formats.jpeg.exifRewrite.ExifRewriter;
import org.apache.sanselan.formats.tiff.TiffImageMetadata;
import org.apache.sanselan.formats.tiff.write.TiffOutputSet;

public final class BitmapUtil {
    private static final String CONTENT_URI_PREFIX = "content://";
    public static final String TEMP_FILENAME_PREFIX = "mfp-temp-image-uploads";

    private static void recycleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static Size getImageSize(Context context, String str) throws IOException {
        Bitmap bitmap;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            bitmap = loadBitmap(context, str, options);
            try {
                if (options.outWidth <= 0 || options.outHeight <= 0) {
                    recycleBitmap(bitmap);
                    throw new IOException();
                }
                Size size = new Size(options.outWidth, options.outHeight);
                recycleBitmap(bitmap);
                return size;
            } catch (Throwable th) {
                th = th;
                recycleBitmap(bitmap);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bitmap = null;
            recycleBitmap(bitmap);
            throw th;
        }
    }

    public static String getMimeType(Context context, String str) throws IOException {
        Bitmap bitmap;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            bitmap = loadBitmap(context, str, options);
            try {
                String str2 = options.outMimeType;
                recycleBitmap(bitmap);
                return str2;
            } catch (Throwable th) {
                th = th;
                recycleBitmap(bitmap);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bitmap = null;
            recycleBitmap(bitmap);
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0064 A[SYNTHETIC, Splitter:B:26:0x0064] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String resizeImage(android.content.Context r5, java.lang.String r6, int r7) throws java.io.IOException {
        /*
            android.graphics.BitmapFactory$Options r0 = new android.graphics.BitmapFactory$Options
            r0.<init>()
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
            r0.inPreferredConfig = r1
            r1 = 0
            android.graphics.Bitmap r0 = loadBitmap(r5, r6, r0)     // Catch:{ all -> 0x007a }
            if (r0 == 0) goto L_0x0074
            int r2 = r0.getWidth()     // Catch:{ all -> 0x0071 }
            int r3 = r0.getHeight()     // Catch:{ all -> 0x0071 }
            if (r2 <= r3) goto L_0x0027
            if (r2 <= r7) goto L_0x0027
            float r6 = (float) r7     // Catch:{ all -> 0x0071 }
            float r2 = (float) r2     // Catch:{ all -> 0x0071 }
            float r6 = r6 / r2
            float r2 = (float) r3     // Catch:{ all -> 0x0071 }
            float r2 = r2 * r6
            int r6 = (int) r2     // Catch:{ all -> 0x0071 }
            r4 = r7
            r7 = r6
            r6 = r4
            goto L_0x0030
        L_0x0027:
            if (r3 <= r7) goto L_0x006a
            float r6 = (float) r7     // Catch:{ all -> 0x0071 }
            float r3 = (float) r3     // Catch:{ all -> 0x0071 }
            float r6 = r6 / r3
            float r2 = (float) r2     // Catch:{ all -> 0x0071 }
            float r2 = r2 * r6
            int r6 = (int) r2     // Catch:{ all -> 0x0071 }
        L_0x0030:
            r2 = 1
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createScaledBitmap(r0, r6, r7, r2)     // Catch:{ all -> 0x0071 }
            recycleBitmap(r0)     // Catch:{ all -> 0x0068 }
            java.lang.String r7 = "mfp-temp-image-uploads"
            java.lang.String r2 = ".jpg"
            java.io.File r5 = r5.getCacheDir()     // Catch:{ all -> 0x0068 }
            java.io.File r5 = java.io.File.createTempFile(r7, r2, r5)     // Catch:{ all -> 0x0068 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ all -> 0x0060 }
            r7.<init>(r5)     // Catch:{ all -> 0x0060 }
            android.graphics.Bitmap$CompressFormat r1 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x005e }
            r2 = 95
            r6.compress(r1, r2, r7)     // Catch:{ all -> 0x005e }
            r7.close()     // Catch:{ all -> 0x0068 }
            java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ all -> 0x0068 }
            recycleBitmap(r0)
            recycleBitmap(r6)
            return r5
        L_0x005e:
            r5 = move-exception
            goto L_0x0062
        L_0x0060:
            r5 = move-exception
            r7 = r1
        L_0x0062:
            if (r7 == 0) goto L_0x0067
            r7.close()     // Catch:{ all -> 0x0068 }
        L_0x0067:
            throw r5     // Catch:{ all -> 0x0068 }
        L_0x0068:
            r5 = move-exception
            goto L_0x007d
        L_0x006a:
            recycleBitmap(r0)
            recycleBitmap(r1)
            return r6
        L_0x0071:
            r5 = move-exception
            r6 = r1
            goto L_0x007d
        L_0x0074:
            java.io.FileNotFoundException r5 = new java.io.FileNotFoundException     // Catch:{ all -> 0x0071 }
            r5.<init>()     // Catch:{ all -> 0x0071 }
            throw r5     // Catch:{ all -> 0x0071 }
        L_0x007a:
            r5 = move-exception
            r6 = r1
            r0 = r6
        L_0x007d:
            recycleBitmap(r0)
            recycleBitmap(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.BitmapUtil.resizeImage(android.content.Context, java.lang.String, int):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        if (r7 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if (r7 != null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0044, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0047, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.String getFilenameForContentUri(android.content.Context r7, java.lang.String r8) {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
            android.net.Uri r2 = android.net.Uri.parse(r8)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
            java.lang.String r7 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r7}     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0036, all -> 0x0033 }
            if (r7 == 0) goto L_0x0030
            boolean r1 = r7.moveToFirst()     // Catch:{ Exception -> 0x0037 }
            if (r1 == 0) goto L_0x0030
            java.lang.String r1 = "_data"
            int r1 = r7.getColumnIndex(r1)     // Catch:{ Exception -> 0x0037 }
            if (r1 < 0) goto L_0x0030
            java.lang.String r8 = r7.getString(r1)     // Catch:{ Exception -> 0x0037 }
            if (r7 == 0) goto L_0x002f
            r7.close()
        L_0x002f:
            return r8
        L_0x0030:
            if (r7 == 0) goto L_0x0047
            goto L_0x0044
        L_0x0033:
            r8 = move-exception
            r7 = r0
            goto L_0x0049
        L_0x0036:
            r7 = r0
        L_0x0037:
            java.lang.String r1 = "failed to resolve filesystem URI for content URI %s"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0048 }
            r3 = 0
            r2[r3] = r8     // Catch:{ all -> 0x0048 }
            com.uacf.core.util.Ln.e(r1, r2)     // Catch:{ all -> 0x0048 }
            if (r7 == 0) goto L_0x0047
        L_0x0044:
            r7.close()
        L_0x0047:
            return r0
        L_0x0048:
            r8 = move-exception
        L_0x0049:
            if (r7 == 0) goto L_0x004e
            r7.close()
        L_0x004e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.util.BitmapUtil.getFilenameForContentUri(android.content.Context, java.lang.String):java.lang.String");
    }

    public static final void copyJpegExifData(Context context, String str, String str2) throws IOException {
        if (str.startsWith(CONTENT_URI_PREFIX)) {
            str = getFilenameForContentUri(context, str);
        }
        File file = null;
        try {
            File file2 = new File(str);
            File file3 = new File(str2);
            if (file2.exists() && file3.exists()) {
                TiffOutputSet readJpegExif = readJpegExif(file2);
                if (readJpegExif != null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(file3.getAbsolutePath());
                    sb.append(".tmp");
                    File file4 = new File(sb.toString());
                    try {
                        new ExifRewriter().updateExifMetadataLossless(file3, (OutputStream) new BufferedOutputStream(new FileOutputStream(file4)), readJpegExif);
                        if (file4.exists() && file3.delete()) {
                            file4.renameTo(file3);
                        }
                    } catch (Exception e) {
                        e = e;
                        file = file4;
                        file.delete();
                        throw new IOException(e);
                    }
                }
            }
        } catch (Exception e2) {
            e = e2;
            if (file != null && file.exists()) {
                file.delete();
            }
            throw new IOException(e);
        }
    }

    private static TiffOutputSet readJpegExif(File file) throws IOException, ImageReadException, ImageWriteException {
        JpegImageMetadata jpegImageMetadata = (JpegImageMetadata) Sanselan.getMetadata(file);
        if (jpegImageMetadata != null) {
            TiffImageMetadata exif = jpegImageMetadata.getExif();
            if (exif != null) {
                return exif.getOutputSet();
            }
        }
        return null;
    }

    private static Bitmap loadBitmap(Context context, String str, Options options) throws IOException {
        InputStream loadFromLocalFileOrContentUri = FileUtils.loadFromLocalFileOrContentUri(context, str);
        if (loadFromLocalFileOrContentUri != null) {
            try {
                return BitmapFactory.decodeStream(loadFromLocalFileOrContentUri, null, options);
            } finally {
                loadFromLocalFileOrContentUri.close();
            }
        } else {
            throw new IOException("failed to load bitmap");
        }
    }
}
