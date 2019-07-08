package com.myfitnesspal.feature.images.service;

import android.content.ContentValues;
import com.brightcove.player.event.EventType;
import com.myfitnesspal.feature.images.service.api.AssociateImagePostBody;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.constants.SyncResourceName;
import com.myfitnesspal.shared.db.table.ImageAssociationsTable;
import com.myfitnesspal.shared.db.table.ImagesTable;
import com.myfitnesspal.shared.db.table.MeasurementsTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.myfitnesspal.shared.db.table.UsersTableV1;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.model.v2.MfpImageAssociation;
import com.myfitnesspal.shared.model.v2.MfpImageAssociation.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.provider.sdk.model.SyncItem;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.inject.Provider;

public class ImageAssociationServiceImpl implements ImageAssociationService {
    private static final String NO_EXISTING_ASSOCIATION = "image-association/002";
    private static final List<NormalizedTable> TABLES_WITH_ASSOCIATIONS;
    private final Provider<MfpV2Api> api;
    private final SQLiteDatabaseWrapper db;
    private final ImageAssociationsTable imageAssociationsTable;
    private final Lazy<ImageService> imageService;
    private final ImagesTable imagesTable;
    private final Lazy<Session> session;

    private static class NormalizedTable {
        private final String localIdColumn;
        private final String localUserIdColumn;
        private final String name;
        private final String resourceType;
        private final String uidColumn;

        public NormalizedTable(String str, String str2, String str3, String str4, String str5) {
            this.name = str;
            this.uidColumn = str2;
            this.localIdColumn = str3;
            this.localUserIdColumn = str4;
            this.resourceType = str5;
        }

        public String name() {
            return this.name;
        }

        public String uid() {
            return col(this.uidColumn);
        }

        public String localId() {
            return col(this.localIdColumn);
        }

        public String localUserId() {
            return col(this.localUserIdColumn);
        }

        public String getResourceType() {
            return this.resourceType;
        }

        private String col(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            sb.append(".");
            sb.append(str);
            return sb.toString();
        }
    }

    public String getSyncResourceName() {
        return SyncResourceName.IMAGE_ASSOCIATION;
    }

    public void publishUnsyncedItems(Function2<Integer, Integer> function2) throws UacfScheduleException {
    }

    static {
        NormalizedTable normalizedTable = new NormalizedTable(MeasurementsTable.TABLE_NAME, "uid", "id", "user_id", "measurement");
        NormalizedTable normalizedTable2 = new NormalizedTable("foods", "uid", "id", "owner_user_id", "food_entry");
        TABLES_WITH_ASSOCIATIONS = Collections.unmodifiableList(Arrays.asList(new NormalizedTable[]{normalizedTable, normalizedTable2}));
    }

    public ImageAssociationServiceImpl(Lazy<Session> lazy, Lazy<ImageService> lazy2, SQLiteDatabaseWrapper sQLiteDatabaseWrapper, Provider<MfpV2Api> provider) {
        this.session = lazy;
        this.imageService = lazy2;
        this.db = sQLiteDatabaseWrapper;
        this.imagesTable = new ImagesTable(sQLiteDatabaseWrapper);
        this.imageAssociationsTable = new ImageAssociationsTable(sQLiteDatabaseWrapper);
        this.api = provider;
    }

    public Class<?> getSyncItemClass() {
        return MfpImageAssociation.class;
    }

    public boolean associateWithExistingImage(long j, String str, String str2, long j2, String str3) {
        ContentValues contentValues = new ContentValues();
        StringBuilder sb = new StringBuilder();
        sb.append("android-");
        sb.append(UUID.randomUUID());
        contentValues.put("uid", sb.toString());
        contentValues.put("resource_type", str2);
        contentValues.put("resource_id", Long.valueOf(j2));
        contentValues.put("resource_uid", str3);
        contentValues.put("user_id", getUserId());
        contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(2));
        contentValues.put("image_id", Long.valueOf(j));
        contentValues.put("image_uid", str);
        return this.imageAssociationsTable.insertData(contentValues) != 0;
    }

    public boolean associate(String str, String str2, long j, String str3) {
        this.db.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ImagesTable.Columns.LOCAL_FILEPATH, str);
            contentValues.put("user_id", getUserId());
            long insertData = this.imagesTable.insertData(contentValues);
            if (insertData != -1) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("image_id", Long.valueOf(insertData));
                StringBuilder sb = new StringBuilder();
                sb.append("android-");
                sb.append(UUID.randomUUID());
                contentValues2.put("uid", sb.toString());
                contentValues2.put("resource_type", str2);
                contentValues2.put("resource_id", Long.valueOf(j));
                contentValues2.put("resource_uid", str3);
                contentValues2.put("user_id", getUserId());
                contentValues2.put(Columns.SYNC_FLAGS, Integer.valueOf(2));
                if (this.imageAssociationsTable.insertData(contentValues2) != -1) {
                    this.db.setTransactionSuccessful();
                    return true;
                }
            }
            this.db.endTransaction();
            return false;
        } finally {
            this.db.endTransaction();
        }
    }

    public boolean updateSyncFlag(String str, int i) {
        String format = String.format("%s=? AND %s=?", new Object[]{"user_id", "uid"});
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(i));
        if (this.imageAssociationsTable.updateData(contentValues, format, getUserId(), str) > 0) {
            return true;
        }
        return false;
    }

    private int getSyncFlag(String str) {
        String format = String.format("%s=? AND %s=?", new Object[]{"user_id", "uid"});
        return CursorUtils.readIntAndClose(this.imageAssociationsTable.queryData(true, new String[]{Columns.SYNC_FLAGS}, format, getUserId(), str), -1);
    }

    public boolean update(long j, MfpImageAssociation mfpImageAssociation) {
        return this.imageAssociationsTable.updateData(ImageAssociationsTable.getContentValuesForUpdate(mfpImageAssociation), "id=?", Long.valueOf(j)) == 1;
    }

    public boolean updateResourceIds(long j, long j2, String str) {
        int i;
        if (j2 == -1 || j == -1) {
            i = 0;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("resource_id", Long.valueOf(j2));
            if (Strings.notEmpty(str)) {
                contentValues.put("resource_uid", str);
            }
            i = this.imageAssociationsTable.updateData(contentValues, "resource_id=?", Long.valueOf(j));
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    public int copyAssociationsToResource(String str, long j, String str2, String str3, long j2, String str4) {
        ImageAssociationServiceImpl imageAssociationServiceImpl;
        String str5 = str;
        long j3 = j;
        String str6 = str2;
        if (Strings.equals(str, str3)) {
            if (!Strings.notEmpty(str4)) {
                String str7 = str4;
            } else if (Strings.equals(str6, str4)) {
                return 0;
            }
            if (j3 > 0 && j3 == j2) {
                return 0;
            }
            imageAssociationServiceImpl = this;
        } else {
            String str8 = str4;
            imageAssociationServiceImpl = this;
        }
        int i = 0;
        for (MfpImage mfpImage : ((ImageService) imageAssociationServiceImpl.imageService.get()).getImagesForResource(str, str6, j3)) {
            if (associateWithExistingImage(mfpImage.getLocalId(), mfpImage.getId(), str3, j2, str4)) {
                i++;
            }
        }
        return i;
    }

    public boolean updateImageUidForAssociationsWithImageLocalId(long j, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("image_uid", str);
        return this.imageAssociationsTable.updateData(contentValues, "image_id=?", Long.valueOf(j)) > 0;
    }

    public MfpImageAssociation remoteAssociate(String str, long j, String str2, String str3, String str4) throws ApiException {
        AssociateImagePostBody associateImagePostBody = new AssociateImagePostBody(str, ((Session) this.session.get()).getUser().getUserId(), str2, str3, str4);
        ApiResponse apiResponse = (ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.api.get()).withJsonBody(new ApiRequest(associateImagePostBody))).withOutputType(API_RESPONSE_MAPPER.class)).post(Uri.POST_IMAGE_ASSOCIATION, new Object[0]);
        if (update(j, (MfpImageAssociation) apiResponse.getItem())) {
            return (MfpImageAssociation) apiResponse.getItem();
        }
        throw new ApiException("local db update failed", -1);
    }

    public void remoteDisassociate(MfpImageAssociation mfpImageAssociation) throws ApiException {
        String id = mfpImageAssociation.getId();
        if (Strings.isEmpty(id)) {
            Ln.d("no remote association for localId=%d", Long.valueOf(mfpImageAssociation.getLocalId()));
        }
        try {
            ((MfpV2Api) this.api.get()).delete(String.format(Uri.DELETE_IMAGE_ASSOCIATION, new Object[]{mfpImageAssociation.getId()}));
        } catch (ApiException e) {
            if (!NO_EXISTING_ASSOCIATION.equals(e.getApiError().getError())) {
                throw e;
            }
        }
        this.imageAssociationsTable.deleteData("uid=?", id);
    }

    public boolean markForRemoteDisassociation(long j, String str, String str2) {
        String format = String.format("(%s=? OR %s=?) AND (%s=?)", new Object[]{"resource_id", "resource_uid", "resource_type"});
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(3));
        if (this.imageAssociationsTable.updateData(contentValues, format, Long.valueOf(j), str, str2) > 0) {
            return true;
        }
        return false;
    }

    public boolean markForRemoteDisassociation(MfpImageAssociation mfpImageAssociation) {
        String format = String.format("%s=? OR %s=?", new Object[]{"id", "uid"});
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(3));
        if (this.imageAssociationsTable.updateData(contentValues, format, Long.valueOf(mfpImageAssociation.getLocalId()), mfpImageAssociation.getId()) > 0) {
            return true;
        }
        return false;
    }

    public List<MfpImageAssociation> findAssociationsForResource(long j, String str) {
        return MfpImageAssociation.listFromCursor(this.imageAssociationsTable.queryDataOrderBy(true, new String[]{EventType.ANY}, "resource_id=? OR resource_uid=?", "id ASC", Long.valueOf(j), str));
    }

    public List<MfpImageAssociation> findAssociationsForImage(long j, String str) {
        return MfpImageAssociation.listFromCursor(this.imageAssociationsTable.queryDataOrderBy(true, new String[]{EventType.ANY}, "image_id=? OR image_uid=?", "id ASC", Long.valueOf(j), str));
    }

    public List<MfpImageAssociation> getPendingAssociations() {
        ArrayList arrayList = new ArrayList();
        for (NormalizedTable pendingAssociations : TABLES_WITH_ASSOCIATIONS) {
            getPendingAssociations(arrayList, pendingAssociations);
        }
        return arrayList;
    }

    public List<MfpImageAssociation> getPendingDisassociations() {
        String[] strArr = {EventType.ANY};
        StringBuilder sb = new StringBuilder();
        sb.append("user_id=? AND sync_flags=? AND ");
        sb.append(String.format("ifnull(%s, '') != ''", new Object[]{"image_uid"}));
        String sb2 = sb.toString();
        return MfpImageAssociation.listFromCursor(this.imageAssociationsTable.queryData(true, strArr, sb2, getUserId(), Integer.valueOf(3)));
    }

    public void consumeSyncItems(List<SyncItem<MfpImageAssociation>> list) {
        int i = 0;
        int i2 = 0;
        for (SyncItem syncItem : list) {
            switch (syncItem.getAction()) {
                case Delete:
                    deleteAssociationLocally(syncItem.getId());
                    i2++;
                    break;
                case Create:
                case Update:
                    String id = ((MfpImageAssociation) syncItem.getItem()).getId();
                    int syncFlag = getSyncFlag(id);
                    if (syncFlag == 3 || syncFlag == 5) {
                        updateSyncFlag(id, 3);
                    } else {
                        updateOrInsertAssociationLocally((MfpImageAssociation) syncItem.getItem());
                        updateSyncFlag(((MfpImageAssociation) syncItem.getItem()).getId(), 4);
                    }
                    i++;
                    break;
            }
        }
        Ln.d("ImageSync: ImageAssociationService.consumeSyncItems: updated=%d, deleted=%d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    public int removeLocalDisassociations() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(1));
        String format = String.format("%s IN(SELECT %s FROM %s WHERE COALESCE(%s, '')='' AND %s=?)", new Object[]{"id", "image_id", ImageAssociationsTable.TABLE_NAME, "uid", Columns.SYNC_FLAGS});
        int updateData = this.imagesTable.updateData(contentValues, format, Integer.valueOf(3)) + 0;
        String format2 = String.format("COALESCE(%s, '') ='' AND %s=?", new Object[]{"uid", Columns.SYNC_FLAGS});
        return updateData + this.imageAssociationsTable.deleteData(format2, Integer.valueOf(3));
    }

    private boolean deleteAssociationLocally(String str) {
        return this.imageAssociationsTable.deleteData("uid=? AND user_id=?", str, getUserId()) != 0;
    }

    private boolean updateOrInsertAssociationLocally(MfpImageAssociation mfpImageAssociation) {
        ContentValues contentValuesForUpdate = ImageAssociationsTable.getContentValuesForUpdate(mfpImageAssociation);
        if (Strings.notEmpty(mfpImageAssociation.getImageId())) {
            contentValuesForUpdate.put(Columns.SYNC_FLAGS, Integer.valueOf(4));
        }
        boolean z = true;
        if (this.imageAssociationsTable.updateData(contentValuesForUpdate, "uid=? AND user_id=?", mfpImageAssociation.getId(), getUserId()) > 0) {
            return true;
        }
        if (this.imageAssociationsTable.insertData(contentValuesForUpdate) == -1) {
            z = false;
        }
        return z;
    }

    private void getPendingAssociations(List<MfpImageAssociation> list, NormalizedTable normalizedTable) {
        StringBuilder sb = new StringBuilder();
        sb.append(normalizedTable.uid());
        sb.append(" as resource_uid");
        String join = Strings.join(",", (T[]) new String[]{"image_associations.id", "image_associations.uid", "image_associations.resource_id", "image_associations.resource_type", "image_associations.sync_flags", sb.toString(), "images.id as image_id", "images.uid as image_uid", "images.user_id"});
        String join2 = Strings.join(",", (T[]) new String[]{normalizedTable.name(), "user", UsersTableV1.TABLE_NAME, ImagesTable.TABLE_NAME, ImageAssociationsTable.TABLE_NAME});
        StringBuilder sb2 = new StringBuilder();
        sb2.append(normalizedTable.localUserId());
        sb2.append("=users.id");
        StringBuilder sb3 = new StringBuilder();
        sb3.append("(");
        sb3.append(normalizedTable.uid());
        sb3.append("=image_associations.resource_uid OR ");
        sb3.append(normalizedTable.localId());
        sb3.append("=image_associations.resource_id)");
        StringBuilder sb4 = new StringBuilder();
        sb4.append("ifnull(");
        sb4.append(normalizedTable.uid());
        sb4.append(", '') != ''");
        String join3 = Strings.join(" AND ", (T[]) new String[]{"image_associations.resource_type=?", "LOWER(user.username)=?", "user.id=images.user_id", "LOWER(user.username)=LOWER(users.username)", sb2.toString(), "(images.uid=image_associations.image_uid OR images.id=image_associations.image_id)", sb3.toString(), "ifnull(images.uid, '') != ''", sb4.toString(), "image_associations.sync_flags=?"});
        String[] strArr = {normalizedTable.getResourceType(), ((Session) this.session.get()).getUser().getUsername().toLowerCase(), String.valueOf(2)};
        StringBuilder sb5 = new StringBuilder();
        sb5.append("SELECT DISTINCT ");
        sb5.append(join);
        sb5.append(" FROM ");
        sb5.append(join2);
        sb5.append(" WHERE ");
        sb5.append(join3);
        sb5.append(";");
        MfpImageAssociation.listFromCursor(list, this.db.rawQuery(sb5.toString(), strArr));
    }

    private String getUserId() {
        return ((Session) this.session.get()).getUser().getUserId();
    }
}
