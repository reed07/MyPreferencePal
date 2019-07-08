package com.myfitnesspal.shared.model.v2;

import android.database.Cursor;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.List;

public class MfpImageAssociation {
    @Expose
    String createdAt;
    @Expose
    String id;
    @Expose
    String imageId;
    private long localId = -1;
    private long localImageId = -1;
    private long localResourceId = -1;
    @Expose
    String resourceId;
    @Expose
    String resourceType;
    @Expose
    String resourceTypeV2;
    private int syncFlags;
    @Expose
    String updatedAt;
    @Expose
    String userId;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpImageAssociation> {
    }

    public MfpImageAssociation() {
    }

    public MfpImageAssociation(CursorMapper cursorMapper) {
        this.localId = cursorMapper.getLong("id");
        this.localImageId = cursorMapper.getLong("image_id");
        this.localResourceId = cursorMapper.getLong("resource_id");
        this.id = cursorMapper.getString("uid");
        this.imageId = cursorMapper.getString("image_uid");
        this.userId = cursorMapper.getString("user_id");
        this.resourceId = cursorMapper.getString("resource_uid");
        this.resourceType = cursorMapper.getString("resource_type");
        this.createdAt = cursorMapper.getString("created_at");
        this.updatedAt = cursorMapper.getString("updated_at");
        this.syncFlags = cursorMapper.getInt(Columns.SYNC_FLAGS);
    }

    public long getLocalId() {
        return this.localId;
    }

    public void setId(String str) {
        this.id = str;
    }

    public long getLocalImageId() {
        return this.localImageId;
    }

    public long getLocalResourceId() {
        return this.localResourceId;
    }

    public void setLocalId(int i) {
        this.localId = (long) i;
    }

    public String getId() {
        return this.id;
    }

    public String getResourceType() {
        return Strings.notEmpty(this.resourceTypeV2) ? this.resourceTypeV2 : this.resourceType;
    }

    public String getResourceId() {
        return this.resourceId;
    }

    public String getImageId() {
        return this.imageId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public int getSyncFlags() {
        return this.syncFlags;
    }

    public static List<MfpImageAssociation> listFromCursor(Cursor cursor) {
        return listFromCursor(new ArrayList(), cursor, -1);
    }

    public static List<MfpImageAssociation> listFromCursor(Cursor cursor, int i) {
        return listFromCursor(new ArrayList(), cursor, i);
    }

    public static List<MfpImageAssociation> listFromCursor(List<MfpImageAssociation> list, Cursor cursor) {
        return listFromCursor(list, cursor, -1);
    }

    public static List<MfpImageAssociation> listFromCursor(List<MfpImageAssociation> list, Cursor cursor, int i) {
        if (list == null || cursor == null || i == 0) {
            return list;
        }
        int i2 = 0;
        CursorMapper cursorMapper = new CursorMapper(cursor);
        while (cursorMapper.moveToNext()) {
            try {
                list.add(new MfpImageAssociation(cursorMapper));
                if (i > 0) {
                    i2++;
                    if (i2 >= i) {
                        break;
                    }
                }
            } finally {
                cursorMapper.close();
            }
        }
        return list;
    }
}
