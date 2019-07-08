package io.uacf.inbox.internal.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mopub.common.AdType;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.ContentValuesMapper.Mappable;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.util.Strings;

public class Notification implements Mappable {
    /* access modifiers changed from: private */
    @Expose
    public NotificationAnalyticData analyticData;
    /* access modifiers changed from: private */
    @SerializedName("notification_category")
    @Expose
    public String category;
    /* access modifiers changed from: private */
    @Expose
    public String collapseKey;
    /* access modifiers changed from: private */
    @Expose
    public NotificationContent content;
    /* access modifiers changed from: private */
    @Expose
    public GsonMappableIso8601Date createdAt;
    /* access modifiers changed from: private */
    public boolean deleted;
    /* access modifiers changed from: private */
    @Expose
    public String engagementId;
    /* access modifiers changed from: private */
    @Expose
    public GsonMappableIso8601Date expiresAt;
    /* access modifiers changed from: private */
    @Expose
    public boolean markedAsExpired;
    /* access modifiers changed from: private */
    @Expose
    public boolean priority;
    /* access modifiers changed from: private */
    @Expose
    public String state;
    /* access modifiers changed from: private */
    public int syncFlags;

    public static final class Builder {
        private NotificationAnalyticData analyticData;
        private String category;
        private String collapseKey;
        private NotificationContent content;
        private GsonMappableIso8601Date createdAt;
        private boolean deleted;
        private String engagementId;
        private GsonMappableIso8601Date expirationDate;
        private boolean markedAsExpired;
        private boolean priority;
        private String state;
        private int syncFlags;

        public Builder() {
        }

        public Builder(Notification notification) {
            if (notification != null) {
                this.engagementId = notification.engagementId;
                this.createdAt = notification.createdAt;
                this.state = notification.state;
                this.category = notification.category;
                this.collapseKey = notification.collapseKey;
                this.analyticData = notification.analyticData;
                this.content = notification.content;
                this.expirationDate = notification.expiresAt;
                this.markedAsExpired = notification.markedAsExpired;
                this.syncFlags = notification.syncFlags;
                this.deleted = notification.deleted;
                this.priority = notification.priority;
            }
        }

        public Builder withMarkedAsExpired(boolean z) {
            this.markedAsExpired = z;
            return this;
        }

        public Builder withSyncFlags(int i) {
            this.syncFlags = i;
            return this;
        }

        public Builder withDeleted(boolean z) {
            this.deleted = z;
            return this;
        }

        public Notification build() {
            Notification notification = new Notification();
            notification.engagementId = this.engagementId;
            GsonMappableIso8601Date gsonMappableIso8601Date = this.createdAt;
            if (gsonMappableIso8601Date == null) {
                gsonMappableIso8601Date = GsonMappableIso8601Date.newInstance(System.currentTimeMillis());
            }
            notification.createdAt = gsonMappableIso8601Date;
            notification.state = this.state;
            notification.category = this.category;
            notification.collapseKey = this.collapseKey;
            notification.analyticData = this.analyticData;
            notification.content = this.content;
            notification.expiresAt = this.expirationDate;
            notification.markedAsExpired = this.markedAsExpired;
            notification.syncFlags = this.syncFlags;
            notification.deleted = this.deleted;
            notification.priority = this.priority;
            return notification;
        }
    }

    public Notification() {
    }

    public Notification(CursorMapper cursorMapper) {
        this.engagementId = cursorMapper.getString(Attributes.ENGAGEMENT_ID);
        this.collapseKey = cursorMapper.getString("collapse_key");
        long j = cursorMapper.getLong("created_at");
        if (j > 0) {
            this.createdAt = GsonMappableIso8601Date.newInstance(j);
        }
        this.deleted = cursorMapper.getBoolean("deleted").booleanValue();
        this.state = cursorMapper.getString("state");
        this.category = cursorMapper.getString(Attributes.CATEGORY);
        this.syncFlags = cursorMapper.getInt(Columns.SYNC_FLAGS);
        long j2 = cursorMapper.getLong("expires_at");
        if (j2 > 0) {
            this.expiresAt = GsonMappableIso8601Date.newInstance(j2);
        }
        this.markedAsExpired = cursorMapper.getBoolean("marked_as_expired").booleanValue();
        this.priority = cursorMapper.getBoolean(InstalledDatasetsTable.Columns.PRIORITY).booleanValue();
        Notification notification = (Notification) new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).withType(Notification.class).tryMapFrom(cursorMapper.getString(AdType.STATIC_NATIVE));
        if (notification != null) {
            this.analyticData = notification.getAnalyticData();
            this.content = notification.getContent();
        }
    }

    public String getEngagementId() {
        return this.engagementId;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public String getCollapseKey() {
        return this.collapseKey;
    }

    public GsonMappableIso8601Date getCreatedAt() {
        return this.createdAt;
    }

    public String getState() {
        return this.state;
    }

    public String getCategory() {
        return this.category;
    }

    public NotificationAnalyticData getAnalyticData() {
        return this.analyticData;
    }

    public NotificationContent getContent() {
        return this.content;
    }

    public GsonMappableIso8601Date getExpiresAt() {
        return this.expiresAt;
    }

    public boolean getMarkedAsExpired() {
        return this.markedAsExpired;
    }

    public int getSyncFlags() {
        return this.syncFlags;
    }

    public boolean isExpired() {
        GsonMappableIso8601Date gsonMappableIso8601Date = this.expiresAt;
        return gsonMappableIso8601Date != null && gsonMappableIso8601Date.getTime() <= System.currentTimeMillis();
    }

    public boolean getPriority() {
        return this.priority;
    }

    public void toContentValues(ContentValuesMapper contentValuesMapper) {
        ContentValuesMapper put = contentValuesMapper.put(Attributes.ENGAGEMENT_ID, Strings.toString(getEngagementId())).put("collapse_key", getCollapseKey());
        String str = "created_at";
        GsonMappableIso8601Date gsonMappableIso8601Date = this.createdAt;
        long j = 0;
        ContentValuesMapper put2 = put.put(str, Long.valueOf(gsonMappableIso8601Date != null ? gsonMappableIso8601Date.getTime() : 0)).put("deleted", Boolean.valueOf(isDeleted())).put("state", Strings.toString(getState())).put(Attributes.CATEGORY, getCategory()).put(Columns.SYNC_FLAGS, Integer.valueOf(getSyncFlags()));
        String str2 = "expires_at";
        GsonMappableIso8601Date gsonMappableIso8601Date2 = this.expiresAt;
        if (gsonMappableIso8601Date2 != null) {
            j = gsonMappableIso8601Date2.getTime();
        }
        put2.put(str2, Long.valueOf(j)).put("marked_as_expired", Boolean.valueOf(this.markedAsExpired)).put(InstalledDatasetsTable.Columns.PRIORITY, Boolean.valueOf(this.priority)).put(AdType.STATIC_NATIVE, new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).reverseMap((Object) this));
    }
}
