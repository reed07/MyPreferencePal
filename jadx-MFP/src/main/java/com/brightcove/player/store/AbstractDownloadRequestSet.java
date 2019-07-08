package com.brightcove.player.store;

import android.support.annotation.NonNull;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Nullable;
import io.requery.OneToMany;
import io.requery.OneToOne;
import io.requery.PreInsert;
import io.requery.query.Expression;
import io.requery.query.LogicalCondition;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public abstract class AbstractDownloadRequestSet implements IdentifiableEntity<DownloadRequestSet, Long> {
    long actualSize;
    long bytesDownloaded;
    long createTime;
    @OneToMany
    Set<DownloadRequest> downloadRequests;
    long estimatedSize;
    @Key
    @Generated
    Long key;
    int notificationVisibility;
    @Nullable
    @OneToOne
    OfflineVideo offlineVideo;
    int reasonCode;
    int statusCode;
    @Nullable
    String title;
    long updateTime;

    @PreInsert
    public void onBeforeInsert() {
        this.createTime = System.currentTimeMillis();
    }

    public long getModifiedTime() {
        long j = this.updateTime;
        return j == 0 ? this.createTime : j;
    }

    @NonNull
    public List<Long> getDownloadRequestIds() {
        ArrayList arrayList = new ArrayList();
        for (DownloadRequest key2 : ((DownloadRequestSet) this).getDownloadRequests()) {
            arrayList.add(key2.getKey());
        }
        return arrayList;
    }

    public boolean isMarkedForDeletion() {
        int statusCode2 = ((DownloadRequestSet) this).getStatusCode();
        return statusCode2 == -2 || statusCode2 == -3;
    }

    public LogicalCondition<? extends Expression<Long>, ?> getIdentityCondition(Long l) {
        return (LogicalCondition) DownloadRequestSet.KEY.eq((Object) l);
    }

    public LogicalCondition<? extends Expression<Long>, ?> getIdentityCondition() {
        return getIdentityCondition((Long) getKey());
    }
}
