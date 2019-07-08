package com.myfitnesspal.feature.images.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.brightcove.player.event.EventType;
import com.myfitnesspal.feature.images.service.api.CreateImagePostBody;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants;
import com.myfitnesspal.shared.db.SqlOp;
import com.myfitnesspal.shared.db.table.ImageAssociationsTable;
import com.myfitnesspal.shared.db.table.ImagesTable;
import com.myfitnesspal.shared.db.table.ImagesTable.Columns;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.model.v2.MfpImage.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpImage.Status;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.provider.sdk.model.SyncItem;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Provider;

public class ImageServiceImpl extends SimpleAsyncServiceBase implements ImageService {
    private static final String HEIGHT_URL_PARAM = "height";
    private static final String INVALID_UID = "<INVALID_UID>";
    private static final String USER_ID_AND_IMAGE_UID_PREDICATE = "user_id=? AND uid=?";
    private static final String WIDTH_URL_PARAM = "width";
    private final Provider<MfpV2Api> api;
    private final Context context;
    private final SQLiteDatabaseWrapper db;
    private final ImagesTable imagesTable;
    private final Lazy<Session> session;
    private final Lazy<ApiUrlProvider> urlProvider;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 4;
    }

    public String getSyncResourceName() {
        return "image";
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "ImageServiceImpl";
    }

    public void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException {
    }

    public ImageServiceImpl(Context context2, Lazy<Session> lazy, SQLiteDatabaseWrapper sQLiteDatabaseWrapper, Lazy<ApiUrlProvider> lazy2, Provider<MfpV2Api> provider) {
        this.context = context2;
        this.session = lazy;
        this.imagesTable = new ImagesTable(sQLiteDatabaseWrapper);
        this.urlProvider = lazy2;
        this.api = provider;
        this.db = sQLiteDatabaseWrapper;
    }

    public Class<?> getSyncItemClass() {
        return MfpImage.class;
    }

    public String getStableImageUrlById(String str) {
        if (Strings.isEmpty(str)) {
            return null;
        }
        return Uri.parse(((ApiUrlProvider) this.urlProvider.get()).getApiV2BaseUrl()).buildUpon().appendEncodedPath(String.format(Constants.Uri.IMAGE_DOWNLOAD_BY_ID, new Object[]{str}).substring(1)).build().toString();
    }

    public String getStableImageUrlById(String str, int i, int i2) {
        return Uri.parse(((ApiUrlProvider) this.urlProvider.get()).getApiV2BaseUrl()).buildUpon().appendEncodedPath(String.format(Constants.Uri.IMAGE_DOWNLOAD_BY_ID, new Object[]{str}).substring(1)).appendQueryParameter("width", String.valueOf(i)).appendQueryParameter("height", String.valueOf(i2)).build().toString();
    }

    public long createLocalImage(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.LOCAL_FILEPATH, str2);
        contentValues.put("user_id", getUserId());
        return this.imagesTable.insertData(contentValues);
    }

    public MfpImage createImage(long j, String str, String str2, int i, int i2) throws ApiException {
        MfpImage mfpImage = (MfpImage) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(new CreateImagePostBody(str2, str))).post(Constants.Uri.IMAGES, new Object[0])).getItem();
        if (i > 0 && i2 > 0) {
            mfpImage.setWidth(i);
            mfpImage.setHeight(i2);
        }
        mfpImage.setLocalId(j);
        updateOrInsertImageLocally(mfpImage);
        return mfpImage;
    }

    public void deleteImage(MfpImage mfpImage) throws ApiException {
        if (Strings.notEmpty(mfpImage.getId())) {
            ((MfpV2Api) this.api.get()).delete(String.format(Constants.Uri.DELETE_IMAGES, new Object[]{mfpImage.getId()}));
        }
        this.imagesTable.deleteData("id=? OR uid=?", Long.valueOf(mfpImage.getLocalId()), mfpImage.getId());
    }

    public void consumeSyncItems(List<SyncItem<MfpImage>> list) {
        int i = 0;
        int i2 = 0;
        for (SyncItem syncItem : list) {
            if (syncItem.getAction() != null) {
                switch (syncItem.getAction()) {
                    case Delete:
                        deleteImageLocally(((MfpImage) syncItem.getItem()).getId());
                        i2++;
                        break;
                    case Create:
                    case Update:
                        updateOrInsertImageLocally((MfpImage) syncItem.getItem());
                        i++;
                        break;
                }
            } else {
                Ln.e("SYNCV2: ImageServiceImpl#consumeSyncItems action is null!", new Object[0]);
            }
        }
        Ln.d("ImageSync: ImageService.consumeSyncItems updated=%d, deleted=%d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    public List<MfpImage> getPendingDeletions() {
        return MfpImage.listFromCursor(this.imagesTable.queryData(true, new String[]{EventType.ANY}, "user_id=? AND sync_flags=?", getUserId(), Integer.valueOf(1)));
    }

    public boolean updateSyncFlag(MfpImage mfpImage, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MfpDatabaseTableV2.Columns.SYNC_FLAGS, Integer.valueOf(i));
        if (this.imagesTable.updateData(contentValues, USER_ID_AND_IMAGE_UID_PREDICATE, getUserId(), mfpImage.getId()) > 0) {
            return true;
        }
        return false;
    }

    public List<MfpImage> getImagesForResource(String str, String str2, long j) {
        if (Strings.isEmpty(str2)) {
            str2 = INVALID_UID;
        }
        String join = Strings.join(" AND ", (T[]) new String[]{SqlOp.or(SqlOp.eq(SqlOp.col(ImagesTable.TABLE_NAME, "uid"), SqlOp.col(ImageAssociationsTable.TABLE_NAME, "image_uid")), SqlOp.eq(SqlOp.col(ImagesTable.TABLE_NAME, "id"), SqlOp.col(ImageAssociationsTable.TABLE_NAME, "image_id"))), SqlOp.eq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, "resource_type"), "?"), SqlOp.or(SqlOp.eq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, "resource_uid"), "?"), SqlOp.eq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, "resource_id"), "?")), SqlOp.and(SqlOp.neq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, MfpDatabaseTableV2.Columns.SYNC_FLAGS), String.valueOf(3)), SqlOp.neq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, MfpDatabaseTableV2.Columns.SYNC_FLAGS), String.valueOf(5)))});
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT images.* FROM images,image_associations WHERE ");
        sb.append(join);
        sb.append(";");
        Cursor rawQuery = this.db.rawQuery(sb.toString(), new String[]{str, str2, String.valueOf(j)});
        if (rawQuery == null) {
            return new ArrayList();
        }
        try {
            return MfpImage.listFromCursor(rawQuery);
        } finally {
            rawQuery.close();
        }
    }

    private boolean deleteImageLocally(String str) {
        checkDeleteTempUploadFile(str);
        return this.imagesTable.deleteData(USER_ID_AND_IMAGE_UID_PREDICATE, getUserId(), str) != 0;
    }

    private boolean updateOrInsertImageLocally(MfpImage mfpImage) {
        if (Status.AVAILABLE.equals(mfpImage.getStatus())) {
            checkDeleteTempUploadFile(mfpImage.getId());
        }
        ContentValues contentValuesForUpdate = ImagesTable.getContentValuesForUpdate(mfpImage);
        boolean z = false;
        String format = String.format("(%s=? OR %s=?) AND %s=?", new Object[]{"id", "uid", "user_id"});
        if (this.imagesTable.updateData(contentValuesForUpdate, format, Long.valueOf(mfpImage.getLocalId()), mfpImage.getId(), getUserId()) > 0) {
            return true;
        }
        contentValuesForUpdate.put(MfpDatabaseTableV2.Columns.SYNC_FLAGS, Integer.valueOf(4));
        if (this.imagesTable.insertData(contentValuesForUpdate) != -1) {
            z = true;
        }
        return z;
    }

    private MfpImage findByUid(String str) {
        return MfpImage.fromCursor(this.imagesTable.queryData(true, new String[]{EventType.ANY}, USER_ID_AND_IMAGE_UID_PREDICATE, getUserId(), str));
    }

    private void checkDeleteTempUploadFile(String str) {
        MfpImage findByUid = findByUid(str);
        if (findByUid != null && Strings.notEmpty(findByUid.getLocalFilepath())) {
            ImageUploadUtil.checkDeleteTempImage(this.context, findByUid.getLocalFilepath());
            ContentValues contentValues = new ContentValues();
            contentValues.put(Columns.LOCAL_FILEPATH, "");
            this.imagesTable.updateData(contentValues, USER_ID_AND_IMAGE_UID_PREDICATE, getUserId(), findByUid.getId());
        }
    }

    private String getUserId() {
        return ((Session) this.session.get()).getUser().getUserId();
    }
}
