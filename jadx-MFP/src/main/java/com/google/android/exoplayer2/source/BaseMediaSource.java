package com.google.android.exoplayer2.source;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource.CC;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource.SourceInfoRefreshListener;
import com.google.android.exoplayer2.source.MediaSourceEventListener.EventDispatcher;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import java.util.ArrayList;
import java.util.Iterator;

public abstract class BaseMediaSource implements MediaSource {
    private final EventDispatcher eventDispatcher = new EventDispatcher();
    @Nullable
    private Looper looper;
    @Nullable
    private Object manifest;
    private final ArrayList<SourceInfoRefreshListener> sourceInfoListeners = new ArrayList<>(1);
    @Nullable
    private Timeline timeline;

    @Nullable
    public /* synthetic */ Object getTag() {
        return CC.$default$getTag(this);
    }

    /* access modifiers changed from: protected */
    public abstract void prepareSourceInternal(@Nullable TransferListener transferListener);

    /* access modifiers changed from: protected */
    public abstract void releaseSourceInternal();

    /* access modifiers changed from: protected */
    public final void refreshSourceInfo(Timeline timeline2, @Nullable Object obj) {
        this.timeline = timeline2;
        this.manifest = obj;
        Iterator it = this.sourceInfoListeners.iterator();
        while (it.hasNext()) {
            ((SourceInfoRefreshListener) it.next()).onSourceInfoRefreshed(this, timeline2, obj);
        }
    }

    /* access modifiers changed from: protected */
    public final EventDispatcher createEventDispatcher(@Nullable MediaPeriodId mediaPeriodId) {
        return this.eventDispatcher.withParameters(0, mediaPeriodId, 0);
    }

    /* access modifiers changed from: protected */
    public final EventDispatcher createEventDispatcher(MediaPeriodId mediaPeriodId, long j) {
        Assertions.checkArgument(mediaPeriodId != null);
        return this.eventDispatcher.withParameters(0, mediaPeriodId, j);
    }

    /* access modifiers changed from: protected */
    public final EventDispatcher createEventDispatcher(int i, @Nullable MediaPeriodId mediaPeriodId, long j) {
        return this.eventDispatcher.withParameters(i, mediaPeriodId, j);
    }

    public final void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this.eventDispatcher.addEventListener(handler, mediaSourceEventListener);
    }

    public final void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
        this.eventDispatcher.removeEventListener(mediaSourceEventListener);
    }

    public final void prepareSource(SourceInfoRefreshListener sourceInfoRefreshListener, @Nullable TransferListener transferListener) {
        Looper myLooper = Looper.myLooper();
        Looper looper2 = this.looper;
        Assertions.checkArgument(looper2 == null || looper2 == myLooper);
        this.sourceInfoListeners.add(sourceInfoRefreshListener);
        if (this.looper == null) {
            this.looper = myLooper;
            prepareSourceInternal(transferListener);
            return;
        }
        Timeline timeline2 = this.timeline;
        if (timeline2 != null) {
            sourceInfoRefreshListener.onSourceInfoRefreshed(this, timeline2, this.manifest);
        }
    }

    public final void releaseSource(SourceInfoRefreshListener sourceInfoRefreshListener) {
        this.sourceInfoListeners.remove(sourceInfoRefreshListener);
        if (this.sourceInfoListeners.isEmpty()) {
            this.looper = null;
            this.timeline = null;
            this.manifest = null;
            releaseSourceInternal();
        }
    }
}
