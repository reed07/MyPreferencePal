package com.myfitnesspal.feature.progress.ui.viewmodel;

import android.content.Context;
import android.database.Cursor;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageServiceUtil;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GalleryImageViewModel {
    private static final DateFormat DISPLAY_FORMATTER = DateFormat.getDateInstance(1, Locale.getDefault());
    private String congratsMessage;
    final String dbDate;
    final String displayDate;
    final boolean hasImageAssociation;
    final int height;
    final long imageAssociationLocalId;
    final String imageId;
    final String imageLocaleFilepath;
    private ImageType imageType;
    final boolean imageUploadCompleted;
    private Date rawDate;
    final long resourceId;
    final String resourceType;
    final String resourceUid;
    final double resourceValue;
    final int width;

    public interface Fields {
        public static final String DISPLAY_DATE = "display_date";
        public static final String IMAGE_ASSOCIATION_LOCAL_ID = "image_association_local_id";
        public static final String IMAGE_HEIGHT = "image_height";
        public static final String IMAGE_LOCAL_FILEPATH = "image_local_filepath";
        public static final String IMAGE_SYNC_FLAGS = "image_sync_flags";
        public static final String IMAGE_UID = "image_uid";
        public static final String IMAGE_WIDTH = "image_width";
        public static final String RESOURCE_ID = "resource_id";
        public static final String RESOURCE_TYPE = "resource_type";
        public static final String RESOURCE_UID = "resource_uid";
        public static final String RESOURCE_VALUE = "resource_value";
    }

    public enum ImageType {
        MEASUREMENT_IMAGE,
        CONGRATS_IMAGE
    }

    public GalleryImageViewModel(CursorMapper cursorMapper) {
        this.imageId = cursorMapper.getString("image_uid");
        this.imageAssociationLocalId = cursorMapper.getLong(Fields.IMAGE_ASSOCIATION_LOCAL_ID);
        this.imageLocaleFilepath = cursorMapper.getString(Fields.IMAGE_LOCAL_FILEPATH);
        this.width = cursorMapper.getInt(Fields.IMAGE_WIDTH);
        this.height = cursorMapper.getInt(Fields.IMAGE_HEIGHT);
        this.resourceType = cursorMapper.getString("resource_type");
        this.resourceValue = cursorMapper.getDouble(Fields.RESOURCE_VALUE);
        this.dbDate = cursorMapper.getString(Fields.DISPLAY_DATE);
        this.hasImageAssociation = Strings.notEmpty(this.imageId) || Strings.notEmpty(this.imageLocaleFilepath);
        this.rawDate = DateTimeUtils.parseDb(this.dbDate);
        this.resourceId = cursorMapper.getLong("resource_id");
        this.resourceUid = cursorMapper.getString("resource_uid");
        this.imageUploadCompleted = imageUploadCompleted(this.imageId, cursorMapper.getInt(Fields.IMAGE_SYNC_FLAGS));
        if (this.rawDate == null) {
            this.rawDate = new Date();
        }
        this.displayDate = DISPLAY_FORMATTER.format(this.rawDate);
        this.imageType = ImageType.MEASUREMENT_IMAGE;
    }

    public static GalleryImageViewModel createGalleryImageViewModelForCongratsImage(String str, String str2, String str3) {
        return new GalleryImageViewModel(str, str2, str3);
    }

    private GalleryImageViewModel(String str, String str2, String str3) {
        this.imageId = null;
        this.imageAssociationLocalId = -1;
        this.imageLocaleFilepath = str;
        this.width = -1;
        this.height = -1;
        this.resourceType = null;
        this.resourceValue = -1.0d;
        this.rawDate = new Date();
        this.dbDate = DateTimeUtils.formatDb(this.rawDate);
        this.hasImageAssociation = true;
        this.resourceId = -1;
        this.resourceUid = null;
        this.imageUploadCompleted = false;
        this.displayDate = str3;
        this.imageType = ImageType.CONGRATS_IMAGE;
        this.congratsMessage = str2;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getImageId() {
        return this.imageId;
    }

    public String getFormattedDate() {
        return this.displayDate;
    }

    public String getDatabaseDate() {
        return this.dbDate;
    }

    public double getResourceValue() {
        return this.resourceValue;
    }

    public boolean hasImageAssociation() {
        return this.hasImageAssociation;
    }

    public Date getRawDate() {
        return this.rawDate;
    }

    public long getResourceId() {
        return this.resourceId;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public String getResourceUid() {
        return this.resourceUid;
    }

    public String getThumbnailUri(Context context, Lazy<ImageService> lazy) {
        if (!this.imageUploadCompleted) {
            return this.imageLocaleFilepath;
        }
        return ImageServiceUtil.getImageThumbnailUri(context, (ImageService) lazy.get(), this.imageId);
    }

    private static boolean imageUploadCompleted(String str, int i) {
        return !Strings.isEmpty(str) && i == 4;
    }

    public String getFullSizedUri(Lazy<ImageService> lazy) {
        if (!this.imageUploadCompleted) {
            return this.imageLocaleFilepath;
        }
        return ((ImageService) lazy.get()).getStableImageUrlById(this.imageId);
    }

    public long getImageAssociationLocalId() {
        return this.imageAssociationLocalId;
    }

    public ImageType getImageType() {
        return this.imageType;
    }

    public String getCongratsMessage() {
        return this.congratsMessage;
    }

    /* JADX INFO: finally extract failed */
    public static List<GalleryImageViewModel> parseList(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        try {
            CursorMapper cursorMapper = new CursorMapper(cursor);
            while (cursorMapper.moveToNext()) {
                arrayList.add(new GalleryImageViewModel(cursorMapper));
            }
            if (cursor != null) {
                cursor.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
