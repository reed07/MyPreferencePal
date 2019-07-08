package com.brightcove.player.store;

import android.net.Uri;
import com.brightcove.player.network.IDownloadManager.IRequest;
import io.requery.Column;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.ManyToOne;
import io.requery.Nullable;
import io.requery.PreInsert;
import io.requery.query.Expression;
import io.requery.query.LogicalCondition;
import java.util.Map;

@Entity
public abstract class AbstractDownloadRequest implements IRequest, IdentifiableEntity<DownloadRequest, Long> {
    long actualSize;
    boolean allowScanningByMediaScanner;
    boolean allowedOverBluetooth;
    boolean allowedOverMetered;
    boolean allowedOverMobile;
    boolean allowedOverRoaming;
    boolean allowedOverWifi;
    long bytesDownloaded;
    long createTime;
    @Nullable
    String description;
    @Nullable
    @Column
    Long downloadId;
    long estimatedSize;
    @Nullable
    Map<String, String> headers;
    @Key
    @Generated
    Long key;
    @Nullable
    Uri localUri;
    @Nullable
    String mimeType;
    int notificationVisibility;
    int reasonCode;
    @Column
    Uri remoteUri;
    @ManyToOne
    @Nullable
    DownloadRequestSet requestSet;
    int statusCode;
    @Nullable
    String title;
    long updateTime;
    boolean visibleInDownloadsUi;

    @PreInsert
    public void onBeforeInsert() {
        this.createTime = System.currentTimeMillis();
    }

    public long getModifiedTime() {
        long j = this.updateTime;
        return j == 0 ? this.createTime : j;
    }

    public LogicalCondition<? extends Expression<Long>, ?> getIdentityCondition(Long l) {
        return (LogicalCondition) DownloadRequest.KEY.eq((Object) l);
    }

    public LogicalCondition<? extends Expression<Long>, ?> getIdentityCondition() {
        return getIdentityCondition((Long) getKey());
    }
}
