package com.brightcove.player.store;

import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.CanSetDownloadIdentifier;
import io.requery.Column;
import io.requery.Convert;
import io.requery.Entity;
import io.requery.ForeignKey;
import io.requery.Key;
import io.requery.Nullable;
import io.requery.OneToOne;
import io.requery.PreInsert;
import io.requery.PreUpdate;
import io.requery.query.Expression;
import io.requery.query.LogicalCondition;
import java.io.File;
import java.util.UUID;

@Entity
public abstract class AbstractOfflineVideo implements IdentifiableEntity<OfflineVideo, UUID> {
    @Nullable
    @Convert
    File downloadDirectory;
    @ForeignKey
    @OneToOne
    DownloadRequestSet downloadRequestSet;
    @Key
    UUID key;
    @Convert
    Video video;
    @Column
    String videoId;

    /* access modifiers changed from: 0000 */
    @PreInsert
    public void onBeforeInsert() {
        if (this.key == null) {
            this.key = UUID.randomUUID();
        }
        onBeforeUpdate();
    }

    /* access modifiers changed from: 0000 */
    @PreUpdate
    @CanSetDownloadIdentifier
    public void onBeforeUpdate() {
        Video video2 = this.video;
        if (video2 != null && video2.getDownloadId() == null) {
            this.video.setDownloadId(this.key);
        }
    }

    public LogicalCondition<? extends Expression<UUID>, ?> getIdentityCondition(UUID uuid) {
        return (LogicalCondition) OfflineVideo.KEY.eq((Object) uuid);
    }

    public LogicalCondition<? extends Expression<UUID>, ?> getIdentityCondition() {
        return getIdentityCondition((UUID) getKey());
    }
}
