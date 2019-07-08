package com.myfitnesspal.feature.images.service;

import android.content.ContentValues;
import android.content.Context;
import com.myfitnesspal.feature.images.service.ImageUploadService.ImageType;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.db.table.ImagesTable;
import com.myfitnesspal.shared.db.table.ImagesTable.Columns;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.BitmapUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class ImageUploadServiceImpl implements ImageUploadService {
    private static final String CONTENT_MD5_HEADER = "Content-MD5";
    private static final String DEFAULT_UPLOAD_MIME_TYPE = "application/octet-stream";
    private final Lazy<AnalyticsService> analyticsService;
    private final Context context;
    private final Lazy<ImageService> imageService;
    private ImagesTable imagesTable;
    private final Lazy<Session> session;

    public ImageUploadServiceImpl(Context context2, Lazy<Session> lazy, Lazy<ImageService> lazy2, SQLiteDatabaseWrapper sQLiteDatabaseWrapper, Lazy<AnalyticsService> lazy3) {
        this.context = context2.getApplicationContext();
        this.session = lazy;
        this.imageService = lazy2;
        this.analyticsService = lazy3;
        this.imagesTable = new ImagesTable(sQLiteDatabaseWrapper);
    }

    public MfpImage uploadImage(long j, String str, int i, ImageType imageType) throws IOException {
        String resizeImage = BitmapUtil.resizeImage(this.context, str, i);
        if (!Strings.isEmpty(resizeImage)) {
            if (!Strings.equals(str, resizeImage)) {
                try {
                    BitmapUtil.copyJpegExifData(this.context, str, resizeImage);
                } catch (IOException unused) {
                    Ln.e("failed to copy JPEG exif data! ignoring...", new Object[0]);
                }
            }
            boolean z = resizeImage.contains(BitmapUtil.TEMP_FILENAME_PREFIX) && !resizeImage.equals(str);
            try {
                MfpImage uploadImage = uploadImage(j, resizeImage, imageType);
                if (z && !new File(resizeImage).delete()) {
                    Ln.e("failed to delete resized image at path %s", resizeImage);
                }
                return uploadImage;
            } catch (Throwable th) {
                if (z && !new File(resizeImage).delete()) {
                    Ln.e("failed to delete resized image at path %s", resizeImage);
                }
                throw th;
            }
        } else {
            throw new IOException();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|10|11|(3:13|14|(5:16|17|18|19|(4:21|22|23|24)(3:27|28|29)))|37|38) */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        r14 = DEFAULT_UPLOAD_MIME_TYPE;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001a */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0052 A[SYNTHETIC, Splitter:B:13:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b8 A[SYNTHETIC, Splitter:B:34:0x00b8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v2.MfpImage uploadImage(long r16, java.lang.String r18, com.myfitnesspal.feature.images.service.ImageUploadService.ImageType r19) throws java.io.IOException {
        /*
            r15 = this;
            r1 = r15
            r0 = r18
            r2 = r19
            android.content.Context r3 = r1.context
            java.lang.String r3 = com.uacf.core.util.FileUtils.computeChecksum(r3, r0)
            r11 = 1
            r12 = 0
            r13 = 0
            com.myfitnesspal.shared.model.Size r4 = new com.myfitnesspal.shared.model.Size     // Catch:{ all -> 0x00cf }
            r5 = -1
            r4.<init>(r5, r5)     // Catch:{ all -> 0x00cf }
            android.content.Context r5 = r1.context     // Catch:{ Exception -> 0x001a }
            com.myfitnesspal.shared.model.Size r4 = com.myfitnesspal.shared.util.BitmapUtil.getImageSize(r5, r0)     // Catch:{ Exception -> 0x001a }
        L_0x001a:
            android.content.Context r5 = r1.context     // Catch:{ Exception -> 0x0022 }
            java.lang.String r5 = com.myfitnesspal.shared.util.BitmapUtil.getMimeType(r5, r0)     // Catch:{ Exception -> 0x0022 }
            r14 = r5
            goto L_0x0025
        L_0x0022:
            java.lang.String r5 = "application/octet-stream"
            r14 = r5
        L_0x0025:
            r15.reportUploadImageStarted(r2)     // Catch:{ all -> 0x00cf }
            dagger.Lazy<com.myfitnesspal.feature.images.service.ImageService> r5 = r1.imageService     // Catch:{ all -> 0x00cf }
            java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x00cf }
            com.myfitnesspal.feature.images.service.ImageService r5 = (com.myfitnesspal.feature.images.service.ImageService) r5     // Catch:{ all -> 0x00cf }
            dagger.Lazy<com.myfitnesspal.shared.service.session.Session> r6 = r1.session     // Catch:{ all -> 0x00cf }
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x00cf }
            com.myfitnesspal.shared.service.session.Session r6 = (com.myfitnesspal.shared.service.session.Session) r6     // Catch:{ all -> 0x00cf }
            com.myfitnesspal.shared.model.User r6 = r6.getUser()     // Catch:{ all -> 0x00cf }
            java.lang.String r7 = r6.getUserId()     // Catch:{ all -> 0x00cf }
            int r9 = r4.getWidth()     // Catch:{ all -> 0x00cf }
            int r10 = r4.getHeight()     // Catch:{ all -> 0x00cf }
            r4 = r5
            r5 = r16
            r8 = r3
            com.myfitnesspal.shared.model.v2.MfpImage r4 = r4.createImage(r5, r7, r8, r9, r10)     // Catch:{ all -> 0x00cf }
            if (r4 == 0) goto L_0x00bc
            java.lang.String r5 = r4.getUploadLocation()     // Catch:{ all -> 0x00cc }
            boolean r5 = com.uacf.core.util.Strings.isEmpty(r5)     // Catch:{ all -> 0x00cc }
            if (r5 != 0) goto L_0x00bc
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ all -> 0x00b4 }
            android.content.Context r6 = r1.context     // Catch:{ all -> 0x00b4 }
            java.io.InputStream r0 = com.uacf.core.util.FileUtils.loadFromLocalFileOrContentUri(r6, r0)     // Catch:{ all -> 0x00b4 }
            r5.<init>(r0)     // Catch:{ all -> 0x00b4 }
            okhttp3.RequestBody r0 = com.myfitnesspal.feature.images.service.api.InputStreamPostBody.create(r14, r5)     // Catch:{ all -> 0x00b2 }
            okhttp3.Request$Builder r6 = new okhttp3.Request$Builder     // Catch:{ all -> 0x00b2 }
            r6.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r7 = r4.getUploadLocation()     // Catch:{ all -> 0x00b2 }
            okhttp3.Request$Builder r6 = r6.url(r7)     // Catch:{ all -> 0x00b2 }
            java.lang.String r7 = "Content-MD5"
            okhttp3.Request$Builder r3 = r6.addHeader(r7, r3)     // Catch:{ all -> 0x00b2 }
            okhttp3.Request$Builder r0 = r3.put(r0)     // Catch:{ all -> 0x00b2 }
            okhttp3.Request r0 = r0.build()     // Catch:{ all -> 0x00b2 }
            okhttp3.OkHttpClient r3 = new okhttp3.OkHttpClient     // Catch:{ all -> 0x00b2 }
            r3.<init>()     // Catch:{ all -> 0x00b2 }
            okhttp3.Call r0 = r3.newCall(r0)     // Catch:{ all -> 0x00b2 }
            okhttp3.Response r0 = r0.execute()     // Catch:{ all -> 0x00b2 }
            boolean r3 = r0.isSuccessful()     // Catch:{ all -> 0x00b2 }
            if (r3 == 0) goto L_0x00a4
            r5.close()     // Catch:{ all -> 0x00a0 }
            r15.reportUploadImageFailedOrSuccess(r2, r11)
            return r4
        L_0x00a0:
            r0 = move-exception
            r12 = r4
            r13 = 1
            goto L_0x00d0
        L_0x00a4:
            com.myfitnesspal.shared.api.ApiException r3 = new com.myfitnesspal.shared.api.ApiException     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = r0.message()     // Catch:{ all -> 0x00b2 }
            int r0 = r0.code()     // Catch:{ all -> 0x00b2 }
            r3.<init>(r6, r0)     // Catch:{ all -> 0x00b2 }
            throw r3     // Catch:{ all -> 0x00b2 }
        L_0x00b2:
            r0 = move-exception
            goto L_0x00b6
        L_0x00b4:
            r0 = move-exception
            r5 = r12
        L_0x00b6:
            if (r5 == 0) goto L_0x00bb
            r5.close()     // Catch:{ all -> 0x00cc }
        L_0x00bb:
            throw r0     // Catch:{ all -> 0x00cc }
        L_0x00bc:
            java.lang.String r0 = "unexpected image creation result -- image null or url empty!"
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ all -> 0x00cc }
            com.uacf.core.util.Ln.e(r0, r3)     // Catch:{ all -> 0x00cc }
            com.myfitnesspal.shared.api.ApiException r0 = new com.myfitnesspal.shared.api.ApiException     // Catch:{ all -> 0x00cc }
            java.lang.String r3 = "image null or url empty, but created successfully??"
            r0.<init>(r3, r13)     // Catch:{ all -> 0x00cc }
            throw r0     // Catch:{ all -> 0x00cc }
        L_0x00cc:
            r0 = move-exception
            r12 = r4
            goto L_0x00d0
        L_0x00cf:
            r0 = move-exception
        L_0x00d0:
            r15.reportUploadImageFailedOrSuccess(r2, r13)
            if (r13 != 0) goto L_0x00e2
            if (r12 == 0) goto L_0x00e2
            dagger.Lazy<com.myfitnesspal.feature.images.service.ImageService> r2 = r1.imageService
            java.lang.Object r2 = r2.get()
            com.myfitnesspal.feature.images.service.ImageService r2 = (com.myfitnesspal.feature.images.service.ImageService) r2
            r2.updateSyncFlag(r12, r11)
        L_0x00e2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.images.service.ImageUploadServiceImpl.uploadImage(long, java.lang.String, com.myfitnesspal.feature.images.service.ImageUploadService$ImageType):com.myfitnesspal.shared.model.v2.MfpImage");
    }

    private void reportUploadImageFailedOrSuccess(ImageType imageType, boolean z) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(z ? Events.IMAGE_UPLOAD_SUCCESS : Events.IMAGE_UPLOAD_FAILED, MapUtil.createMap("image_type", imageType.getAnalyticsValue()));
    }

    private void reportUploadImageStarted(ImageType imageType) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.IMAGE_UPLOAD_START, MapUtil.createMap("image_type", imageType.getAnalyticsValue()));
    }

    public boolean markUploadSucceeded(long j, MfpImage mfpImage, int i) {
        return this.imagesTable.updateData(ImagesTable.getContentValuesForUpdate(mfpImage, i), "id=?", Long.valueOf(j)) == 1;
    }

    public boolean markUploadFailed(long j, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.LAST_UPLOAD_ATTEMPT, Long.valueOf(new Date().getTime()));
        contentValues.put(MfpDatabaseTableV2.Columns.SYNC_FLAGS, Integer.valueOf(i));
        return this.imagesTable.updateData(contentValues, "id=?", Long.valueOf(j)) == 1;
    }

    public MfpImage getNextUpload() {
        String format = String.format(Locale.ENGLISH, "SELECT * FROM %s WHERE %s=? AND %s=? ORDER BY %s ASC LIMIT 1", new Object[]{this.imagesTable.getTableName(), MfpDatabaseTableV2.Columns.SYNC_FLAGS, "user_id", Columns.LAST_UPLOAD_ATTEMPT});
        return MfpImage.fromCursor(this.imagesTable.rawQuery(format, Integer.valueOf(0), getUserId()));
    }

    private String getUserId() {
        return ((Session) this.session.get()).getUser().getUserId();
    }
}
