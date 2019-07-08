package com.brightcove.player.store;

import io.requery.PreInsert;
import io.requery.PreUpdate;
import io.requery.Superclass;

@Superclass
public abstract class BaseEntity {
    /* access modifiers changed from: protected */
    public long createTime;
    /* access modifiers changed from: protected */
    public long updateTime;

    @PreInsert
    public void onBeforeInsert() {
        this.createTime = System.currentTimeMillis();
    }

    @PreUpdate
    public void onBeforeUpdate() {
        this.updateTime = System.currentTimeMillis();
    }

    public long getModifiedTime() {
        long j = this.updateTime;
        return j == 0 ? this.createTime : j;
    }
}
