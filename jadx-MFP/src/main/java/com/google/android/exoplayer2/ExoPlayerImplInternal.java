package com.google.android.exoplayer2;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.DefaultMediaClock.PlaybackParameterListener;
import com.google.android.exoplayer2.PlayerMessage.Sender;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSource.MediaPeriodId;
import com.google.android.exoplayer2.source.MediaSource.SourceInfoRefreshListener;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector.InvalidationListener;
import com.google.android.exoplayer2.trackselection.TrackSelectorResult;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.HandlerWrapper;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

final class ExoPlayerImplInternal implements Callback, PlaybackParameterListener, Sender, MediaPeriod.Callback, SourceInfoRefreshListener, InvalidationListener {
    private static final int IDLE_INTERVAL_MS = 1000;
    private static final int MSG_DO_SOME_WORK = 2;
    public static final int MSG_ERROR = 2;
    private static final int MSG_PERIOD_PREPARED = 9;
    public static final int MSG_PLAYBACK_INFO_CHANGED = 0;
    public static final int MSG_PLAYBACK_PARAMETERS_CHANGED = 1;
    private static final int MSG_PLAYBACK_PARAMETERS_CHANGED_INTERNAL = 16;
    private static final int MSG_PREPARE = 0;
    private static final int MSG_REFRESH_SOURCE_INFO = 8;
    private static final int MSG_RELEASE = 7;
    private static final int MSG_SEEK_TO = 3;
    private static final int MSG_SEND_MESSAGE = 14;
    private static final int MSG_SEND_MESSAGE_TO_TARGET_THREAD = 15;
    private static final int MSG_SET_PLAYBACK_PARAMETERS = 4;
    private static final int MSG_SET_PLAY_WHEN_READY = 1;
    private static final int MSG_SET_REPEAT_MODE = 12;
    private static final int MSG_SET_SEEK_PARAMETERS = 5;
    private static final int MSG_SET_SHUFFLE_ENABLED = 13;
    private static final int MSG_SOURCE_CONTINUE_LOADING_REQUESTED = 10;
    private static final int MSG_STOP = 6;
    private static final int MSG_TRACK_SELECTION_INVALIDATED = 11;
    private static final int PREPARING_SOURCE_INTERVAL_MS = 10;
    private static final int RENDERING_INTERVAL_MS = 10;
    private static final String TAG = "ExoPlayerImplInternal";
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private Renderer[] enabledRenderers;
    private final Handler eventHandler;
    private final HandlerWrapper handler;
    private final HandlerThread internalPlaybackThread;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private MediaSource mediaSource;
    private int nextPendingMessageIndex;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private int pendingPrepareCount;
    private final Period period;
    private boolean playWhenReady;
    private PlaybackInfo playbackInfo;
    private final PlaybackInfoUpdate playbackInfoUpdate;
    private final MediaPeriodQueue queue = new MediaPeriodQueue();
    private boolean rebuffering;
    private boolean released;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionUs;
    private final Renderer[] renderers;
    private int repeatMode;
    private final boolean retainBackBufferFromKeyframe;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private final Window window;

    private static final class MediaSourceRefreshInfo {
        public final Object manifest;
        public final MediaSource source;
        public final Timeline timeline;

        public MediaSourceRefreshInfo(MediaSource mediaSource, Timeline timeline2, Object obj) {
            this.source = mediaSource;
            this.timeline = timeline2;
            this.manifest = obj;
        }
    }

    private static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        @Nullable
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage playerMessage) {
            this.message = playerMessage;
        }

        public void setResolvedPosition(int i, long j, Object obj) {
            this.resolvedPeriodIndex = i;
            this.resolvedPeriodTimeUs = j;
            this.resolvedPeriodUid = obj;
        }

        public int compareTo(@NonNull PendingMessageInfo pendingMessageInfo) {
            int i = 1;
            if ((this.resolvedPeriodUid == null) != (pendingMessageInfo.resolvedPeriodUid == null)) {
                if (this.resolvedPeriodUid != null) {
                    i = -1;
                }
                return i;
            } else if (this.resolvedPeriodUid == null) {
                return 0;
            } else {
                int i2 = this.resolvedPeriodIndex - pendingMessageInfo.resolvedPeriodIndex;
                if (i2 != 0) {
                    return i2;
                }
                return Util.compareLong(this.resolvedPeriodTimeUs, pendingMessageInfo.resolvedPeriodTimeUs);
            }
        }
    }

    private static final class PlaybackInfoUpdate {
        /* access modifiers changed from: private */
        public int discontinuityReason;
        private PlaybackInfo lastPlaybackInfo;
        /* access modifiers changed from: private */
        public int operationAcks;
        /* access modifiers changed from: private */
        public boolean positionDiscontinuity;

        private PlaybackInfoUpdate() {
        }

        public boolean hasPendingUpdate(PlaybackInfo playbackInfo) {
            return playbackInfo != this.lastPlaybackInfo || this.operationAcks > 0 || this.positionDiscontinuity;
        }

        public void reset(PlaybackInfo playbackInfo) {
            this.lastPlaybackInfo = playbackInfo;
            this.operationAcks = 0;
            this.positionDiscontinuity = false;
        }

        public void incrementPendingOperationAcks(int i) {
            this.operationAcks += i;
        }

        public void setPositionDiscontinuity(int i) {
            boolean z = true;
            if (!this.positionDiscontinuity || this.discontinuityReason == 4) {
                this.positionDiscontinuity = true;
                this.discontinuityReason = i;
                return;
            }
            if (i != 4) {
                z = false;
            }
            Assertions.checkArgument(z);
        }
    }

    private static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline timeline2, int i, long j) {
            this.timeline = timeline2;
            this.windowIndex = i;
            this.windowPositionUs = j;
        }
    }

    public ExoPlayerImplInternal(Renderer[] rendererArr, TrackSelector trackSelector2, TrackSelectorResult trackSelectorResult, LoadControl loadControl2, BandwidthMeter bandwidthMeter2, boolean z, int i, boolean z2, Handler handler2, Clock clock2) {
        this.renderers = rendererArr;
        this.trackSelector = trackSelector2;
        this.emptyTrackSelectorResult = trackSelectorResult;
        this.loadControl = loadControl2;
        this.bandwidthMeter = bandwidthMeter2;
        this.playWhenReady = z;
        this.repeatMode = i;
        this.shuffleModeEnabled = z2;
        this.eventHandler = handler2;
        this.clock = clock2;
        this.backBufferDurationUs = loadControl2.getBackBufferDurationUs();
        this.retainBackBufferFromKeyframe = loadControl2.retainBackBufferFromKeyframe();
        this.seekParameters = SeekParameters.DEFAULT;
        this.playbackInfo = PlaybackInfo.createDummy(-9223372036854775807L, trackSelectorResult);
        this.playbackInfoUpdate = new PlaybackInfoUpdate();
        this.rendererCapabilities = new RendererCapabilities[rendererArr.length];
        for (int i2 = 0; i2 < rendererArr.length; i2++) {
            rendererArr[i2].setIndex(i2);
            this.rendererCapabilities[i2] = rendererArr[i2].getCapabilities();
        }
        this.mediaClock = new DefaultMediaClock(this, clock2);
        this.pendingMessages = new ArrayList<>();
        this.enabledRenderers = new Renderer[0];
        this.window = new Window();
        this.period = new Period();
        trackSelector2.init(this, bandwidthMeter2);
        this.internalPlaybackThread = new HandlerThread("ExoPlayerImplInternal:Handler", -16);
        this.internalPlaybackThread.start();
        this.handler = clock2.createHandler(this.internalPlaybackThread.getLooper(), this);
    }

    public void prepare(MediaSource mediaSource2, boolean z, boolean z2) {
        this.handler.obtainMessage(0, z ? 1 : 0, z2 ? 1 : 0, mediaSource2).sendToTarget();
    }

    public void setPlayWhenReady(boolean z) {
        this.handler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
    }

    public void setRepeatMode(int i) {
        this.handler.obtainMessage(12, i, 0).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean z) {
        this.handler.obtainMessage(13, z ? 1 : 0, 0).sendToTarget();
    }

    public void seekTo(Timeline timeline, int i, long j) {
        this.handler.obtainMessage(3, new SeekPosition(timeline, i, j)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(4, playbackParameters).sendToTarget();
    }

    public void setSeekParameters(SeekParameters seekParameters2) {
        this.handler.obtainMessage(5, seekParameters2).sendToTarget();
    }

    public void stop(boolean z) {
        this.handler.obtainMessage(6, z ? 1 : 0, 0).sendToTarget();
    }

    public synchronized void sendMessage(PlayerMessage playerMessage) {
        if (this.released) {
            Log.w(TAG, "Ignoring messages sent after release.");
            playerMessage.markAsProcessed(false);
            return;
        }
        this.handler.obtainMessage(14, playerMessage).sendToTarget();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void release() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.released     // Catch:{ all -> 0x0023 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)
            return
        L_0x0007:
            com.google.android.exoplayer2.util.HandlerWrapper r0 = r2.handler     // Catch:{ all -> 0x0023 }
            r1 = 7
            r0.sendEmptyMessage(r1)     // Catch:{ all -> 0x0023 }
            r0 = 0
        L_0x000e:
            boolean r1 = r2.released     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0018
            r2.wait()     // Catch:{ InterruptedException -> 0x0016 }
            goto L_0x000e
        L_0x0016:
            r0 = 1
            goto L_0x000e
        L_0x0018:
            if (r0 == 0) goto L_0x0021
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0023 }
            r0.interrupt()     // Catch:{ all -> 0x0023 }
        L_0x0021:
            monitor-exit(r2)
            return
        L_0x0023:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.release():void");
    }

    public Looper getPlaybackLooper() {
        return this.internalPlaybackThread.getLooper();
    }

    public void onSourceInfoRefreshed(MediaSource mediaSource2, Timeline timeline, Object obj) {
        this.handler.obtainMessage(8, new MediaSourceRefreshInfo(mediaSource2, timeline, obj)).sendToTarget();
    }

    public void onPrepared(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(9, mediaPeriod).sendToTarget();
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        this.handler.obtainMessage(10, mediaPeriod).sendToTarget();
    }

    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(11);
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        this.handler.obtainMessage(16, playbackParameters).sendToTarget();
    }

    public boolean handleMessage(Message message) {
        try {
            switch (message.what) {
                case 0:
                    prepareInternal((MediaSource) message.obj, message.arg1 != 0, message.arg2 != 0);
                    break;
                case 1:
                    setPlayWhenReadyInternal(message.arg1 != 0);
                    break;
                case 2:
                    doSomeWork();
                    break;
                case 3:
                    seekToInternal((SeekPosition) message.obj);
                    break;
                case 4:
                    setPlaybackParametersInternal((PlaybackParameters) message.obj);
                    break;
                case 5:
                    setSeekParametersInternal((SeekParameters) message.obj);
                    break;
                case 6:
                    stopInternal(message.arg1 != 0, true);
                    break;
                case 7:
                    releaseInternal();
                    return true;
                case 8:
                    handleSourceInfoRefreshed((MediaSourceRefreshInfo) message.obj);
                    break;
                case 9:
                    handlePeriodPrepared((MediaPeriod) message.obj);
                    break;
                case 10:
                    handleContinueLoadingRequested((MediaPeriod) message.obj);
                    break;
                case 11:
                    reselectTracksInternal();
                    break;
                case 12:
                    setRepeatModeInternal(message.arg1);
                    break;
                case 13:
                    setShuffleModeEnabledInternal(message.arg1 != 0);
                    break;
                case 14:
                    sendMessageInternal((PlayerMessage) message.obj);
                    break;
                case 15:
                    sendMessageToTargetThread((PlayerMessage) message.obj);
                    break;
                case 16:
                    handlePlaybackParameters((PlaybackParameters) message.obj);
                    break;
                default:
                    return false;
            }
            maybeNotifyPlaybackInfoChanged();
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Playback error.", e);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, e).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (IOException e2) {
            Log.e(TAG, "Source error.", e2);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForSource(e2)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        } catch (RuntimeException e3) {
            Log.e(TAG, "Internal runtime error.", e3);
            stopInternal(false, false);
            this.eventHandler.obtainMessage(2, ExoPlaybackException.createForUnexpected(e3)).sendToTarget();
            maybeNotifyPlaybackInfoChanged();
        }
        return true;
    }

    private void setState(int i) {
        if (this.playbackInfo.playbackState != i) {
            this.playbackInfo = this.playbackInfo.copyWithPlaybackState(i);
        }
    }

    private void setIsLoading(boolean z) {
        if (this.playbackInfo.isLoading != z) {
            this.playbackInfo = this.playbackInfo.copyWithIsLoading(z);
        }
    }

    private void maybeNotifyPlaybackInfoChanged() {
        if (this.playbackInfoUpdate.hasPendingUpdate(this.playbackInfo)) {
            this.eventHandler.obtainMessage(0, this.playbackInfoUpdate.operationAcks, this.playbackInfoUpdate.positionDiscontinuity ? this.playbackInfoUpdate.discontinuityReason : -1, this.playbackInfo).sendToTarget();
            this.playbackInfoUpdate.reset(this.playbackInfo);
        }
    }

    private void prepareInternal(MediaSource mediaSource2, boolean z, boolean z2) {
        this.pendingPrepareCount++;
        resetInternal(true, z, z2);
        this.loadControl.onPrepared();
        this.mediaSource = mediaSource2;
        setState(2);
        mediaSource2.prepareSource(this, this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
    }

    private void setPlayWhenReadyInternal(boolean z) throws ExoPlaybackException {
        this.rebuffering = false;
        this.playWhenReady = z;
        if (!z) {
            stopRenderers();
            updatePlaybackPositions();
        } else if (this.playbackInfo.playbackState == 3) {
            startRenderers();
            this.handler.sendEmptyMessage(2);
        } else if (this.playbackInfo.playbackState == 2) {
            this.handler.sendEmptyMessage(2);
        }
    }

    private void setRepeatModeInternal(int i) throws ExoPlaybackException {
        this.repeatMode = i;
        if (!this.queue.updateRepeatMode(i)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void setShuffleModeEnabledInternal(boolean z) throws ExoPlaybackException {
        this.shuffleModeEnabled = z;
        if (!this.queue.updateShuffleModeEnabled(z)) {
            seekToCurrentPosition(true);
        }
        handleLoadingMediaPeriodChanged(false);
    }

    private void seekToCurrentPosition(boolean z) throws ExoPlaybackException {
        MediaPeriodId mediaPeriodId = this.queue.getPlayingPeriod().info.id;
        long seekToPeriodPosition = seekToPeriodPosition(mediaPeriodId, this.playbackInfo.positionUs, true);
        if (seekToPeriodPosition != this.playbackInfo.positionUs) {
            PlaybackInfo playbackInfo2 = this.playbackInfo;
            this.playbackInfo = playbackInfo2.copyWithNewPosition(mediaPeriodId, seekToPeriodPosition, playbackInfo2.contentPositionUs, getTotalBufferedDurationUs());
            if (z) {
                this.playbackInfoUpdate.setPositionDiscontinuity(4);
            }
        }
    }

    private void startRenderers() throws ExoPlaybackException {
        this.rebuffering = false;
        this.mediaClock.start();
        for (Renderer start : this.enabledRenderers) {
            start.start();
        }
    }

    private void stopRenderers() throws ExoPlaybackException {
        this.mediaClock.stop();
        for (Renderer ensureStopped : this.enabledRenderers) {
            ensureStopped(ensureStopped);
        }
    }

    private void updatePlaybackPositions() throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            long readDiscontinuity = playingPeriod.mediaPeriod.readDiscontinuity();
            if (readDiscontinuity != -9223372036854775807L) {
                resetRendererPosition(readDiscontinuity);
                if (readDiscontinuity != this.playbackInfo.positionUs) {
                    PlaybackInfo playbackInfo2 = this.playbackInfo;
                    this.playbackInfo = playbackInfo2.copyWithNewPosition(playbackInfo2.periodId, readDiscontinuity, this.playbackInfo.contentPositionUs, getTotalBufferedDurationUs());
                    this.playbackInfoUpdate.setPositionDiscontinuity(4);
                }
            } else {
                this.rendererPositionUs = this.mediaClock.syncAndGetPositionUs();
                long periodTime = playingPeriod.toPeriodTime(this.rendererPositionUs);
                maybeTriggerPendingMessages(this.playbackInfo.positionUs, periodTime);
                this.playbackInfo.positionUs = periodTime;
            }
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            this.playbackInfo.bufferedPositionUs = loadingPeriod.getBufferedPositionUs();
            this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        }
    }

    private void doSomeWork() throws ExoPlaybackException, IOException {
        Renderer[] rendererArr;
        long uptimeMillis = this.clock.uptimeMillis();
        updatePeriods();
        if (!this.queue.hasPlayingPeriod()) {
            maybeThrowPeriodPrepareError();
            scheduleNextWork(uptimeMillis, 10);
            return;
        }
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
        playingPeriod.mediaPeriod.discardBuffer(this.playbackInfo.positionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
        boolean z = true;
        boolean z2 = true;
        for (Renderer renderer : this.enabledRenderers) {
            renderer.render(this.rendererPositionUs, elapsedRealtime);
            z2 = z2 && renderer.isEnded();
            boolean z3 = renderer.isReady() || renderer.isEnded() || rendererWaitingForNextStream(renderer);
            if (!z3) {
                renderer.maybeThrowStreamError();
            }
            z = z && z3;
        }
        if (!z) {
            maybeThrowPeriodPrepareError();
        }
        long j = playingPeriod.info.durationUs;
        if (z2 && ((j == -9223372036854775807L || j <= this.playbackInfo.positionUs) && playingPeriod.info.isFinal)) {
            setState(4);
            stopRenderers();
        } else if (this.playbackInfo.playbackState == 2 && shouldTransitionToReadyState(z)) {
            setState(3);
            if (this.playWhenReady) {
                startRenderers();
            }
        } else if (this.playbackInfo.playbackState == 3 && (this.enabledRenderers.length != 0 ? !z : !isTimelineReady())) {
            this.rebuffering = this.playWhenReady;
            setState(2);
            stopRenderers();
        }
        if (this.playbackInfo.playbackState == 2) {
            for (Renderer maybeThrowStreamError : this.enabledRenderers) {
                maybeThrowStreamError.maybeThrowStreamError();
            }
        }
        if ((this.playWhenReady && this.playbackInfo.playbackState == 3) || this.playbackInfo.playbackState == 2) {
            scheduleNextWork(uptimeMillis, 10);
        } else if (this.enabledRenderers.length == 0 || this.playbackInfo.playbackState == 4) {
            this.handler.removeMessages(2);
        } else {
            scheduleNextWork(uptimeMillis, 1000);
        }
        TraceUtil.endSection();
    }

    private void scheduleNextWork(long j, long j2) {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessageAtTime(2, j + j2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal.SeekPosition r23) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r22 = this;
            r1 = r22
            r0 = r23
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r2 = r1.playbackInfoUpdate
            r3 = 1
            r2.incrementPendingOperationAcks(r3)
            android.util.Pair r2 = r1.resolveSeekPosition(r0, r3)
            r4 = 0
            r6 = 0
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r2 != 0) goto L_0x0028
            com.google.android.exoplayer2.PlaybackInfo r2 = r1.playbackInfo
            boolean r9 = r1.shuffleModeEnabled
            com.google.android.exoplayer2.Timeline$Window r10 = r1.window
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r2 = r2.getDummyFirstMediaPeriodId(r9, r10)
            r15 = r2
            r12 = r7
            r18 = r12
            r2 = 1
            goto L_0x0058
        L_0x0028:
            java.lang.Object r9 = r2.first
            java.lang.Object r10 = r2.second
            java.lang.Long r10 = (java.lang.Long) r10
            long r10 = r10.longValue()
            com.google.android.exoplayer2.MediaPeriodQueue r12 = r1.queue
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r9 = r12.resolveMediaPeriodIdForAds(r9, r10)
            boolean r12 = r9.isAd()
            if (r12 == 0) goto L_0x0044
            r12 = r4
            r15 = r9
            r18 = r10
            r2 = 1
            goto L_0x0058
        L_0x0044:
            java.lang.Object r2 = r2.second
            java.lang.Long r2 = (java.lang.Long) r2
            long r12 = r2.longValue()
            long r14 = r0.windowPositionUs
            int r2 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0054
            r2 = 1
            goto L_0x0055
        L_0x0054:
            r2 = 0
        L_0x0055:
            r15 = r9
            r18 = r10
        L_0x0058:
            r9 = 2
            com.google.android.exoplayer2.source.MediaSource r10 = r1.mediaSource     // Catch:{ all -> 0x00df }
            if (r10 == 0) goto L_0x00c7
            int r10 = r1.pendingPrepareCount     // Catch:{ all -> 0x00df }
            if (r10 <= 0) goto L_0x0062
            goto L_0x00c7
        L_0x0062:
            int r0 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x006e
            r0 = 4
            r1.setState(r0)     // Catch:{ all -> 0x00df }
            r1.resetInternal(r6, r3, r6)     // Catch:{ all -> 0x00df }
            goto L_0x00c9
        L_0x006e:
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x00df }
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r0 = r0.periodId     // Catch:{ all -> 0x00df }
            boolean r0 = r15.equals(r0)     // Catch:{ all -> 0x00df }
            if (r0 == 0) goto L_0x00b8
            com.google.android.exoplayer2.MediaPeriodQueue r0 = r1.queue     // Catch:{ all -> 0x00df }
            com.google.android.exoplayer2.MediaPeriodHolder r0 = r0.getPlayingPeriod()     // Catch:{ all -> 0x00df }
            if (r0 == 0) goto L_0x008d
            int r7 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x008d
            com.google.android.exoplayer2.source.MediaPeriod r0 = r0.mediaPeriod     // Catch:{ all -> 0x00df }
            com.google.android.exoplayer2.SeekParameters r4 = r1.seekParameters     // Catch:{ all -> 0x00df }
            long r4 = r0.getAdjustedSeekPositionUs(r12, r4)     // Catch:{ all -> 0x00df }
            goto L_0x008e
        L_0x008d:
            r4 = r12
        L_0x008e:
            long r7 = com.google.android.exoplayer2.C.usToMs(r4)     // Catch:{ all -> 0x00df }
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x00df }
            long r10 = r0.positionUs     // Catch:{ all -> 0x00df }
            long r10 = com.google.android.exoplayer2.C.usToMs(r10)     // Catch:{ all -> 0x00df }
            int r0 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r0 != 0) goto L_0x00b9
            com.google.android.exoplayer2.PlaybackInfo r0 = r1.playbackInfo     // Catch:{ all -> 0x00df }
            long r3 = r0.positionUs     // Catch:{ all -> 0x00df }
            com.google.android.exoplayer2.PlaybackInfo r14 = r1.playbackInfo
            long r20 = r22.getTotalBufferedDurationUs()
            r16 = r3
            com.google.android.exoplayer2.PlaybackInfo r0 = r14.copyWithNewPosition(r15, r16, r18, r20)
            r1.playbackInfo = r0
            if (r2 == 0) goto L_0x00b7
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r0 = r1.playbackInfoUpdate
            r0.setPositionDiscontinuity(r9)
        L_0x00b7:
            return
        L_0x00b8:
            r4 = r12
        L_0x00b9:
            long r4 = r1.seekToPeriodPosition(r15, r4)     // Catch:{ all -> 0x00df }
            int r0 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00c2
            goto L_0x00c3
        L_0x00c2:
            r3 = 0
        L_0x00c3:
            r2 = r2 | r3
            r16 = r4
            goto L_0x00cb
        L_0x00c7:
            r1.pendingInitialSeekPosition = r0     // Catch:{ all -> 0x00df }
        L_0x00c9:
            r16 = r12
        L_0x00cb:
            com.google.android.exoplayer2.PlaybackInfo r14 = r1.playbackInfo
            long r20 = r22.getTotalBufferedDurationUs()
            com.google.android.exoplayer2.PlaybackInfo r0 = r14.copyWithNewPosition(r15, r16, r18, r20)
            r1.playbackInfo = r0
            if (r2 == 0) goto L_0x00de
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r0 = r1.playbackInfoUpdate
            r0.setPositionDiscontinuity(r9)
        L_0x00de:
            return
        L_0x00df:
            r0 = move-exception
            com.google.android.exoplayer2.PlaybackInfo r14 = r1.playbackInfo
            long r20 = r22.getTotalBufferedDurationUs()
            r16 = r12
            com.google.android.exoplayer2.PlaybackInfo r3 = r14.copyWithNewPosition(r15, r16, r18, r20)
            r1.playbackInfo = r3
            if (r2 == 0) goto L_0x00f5
            com.google.android.exoplayer2.ExoPlayerImplInternal$PlaybackInfoUpdate r2 = r1.playbackInfoUpdate
            r2.setPositionDiscontinuity(r9)
        L_0x00f5:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ExoPlayerImplInternal.seekToInternal(com.google.android.exoplayer2.ExoPlayerImplInternal$SeekPosition):void");
    }

    private long seekToPeriodPosition(MediaPeriodId mediaPeriodId, long j) throws ExoPlaybackException {
        return seekToPeriodPosition(mediaPeriodId, j, this.queue.getPlayingPeriod() != this.queue.getReadingPeriod());
    }

    private long seekToPeriodPosition(MediaPeriodId mediaPeriodId, long j, boolean z) throws ExoPlaybackException {
        stopRenderers();
        this.rebuffering = false;
        setState(2);
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        MediaPeriodHolder mediaPeriodHolder = playingPeriod;
        while (true) {
            if (mediaPeriodHolder != null) {
                if (mediaPeriodId.equals(mediaPeriodHolder.info.id) && mediaPeriodHolder.prepared) {
                    this.queue.removeAfter(mediaPeriodHolder);
                    break;
                }
                mediaPeriodHolder = this.queue.advancePlayingPeriod();
            } else {
                break;
            }
        }
        if (playingPeriod != mediaPeriodHolder || z) {
            for (Renderer disableRenderer : this.enabledRenderers) {
                disableRenderer(disableRenderer);
            }
            this.enabledRenderers = new Renderer[0];
            playingPeriod = null;
        }
        if (mediaPeriodHolder != null) {
            updatePlayingPeriodRenderers(playingPeriod);
            if (mediaPeriodHolder.hasEnabledTracks) {
                long seekToUs = mediaPeriodHolder.mediaPeriod.seekToUs(j);
                mediaPeriodHolder.mediaPeriod.discardBuffer(seekToUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
                j = seekToUs;
            }
            resetRendererPosition(j);
            maybeContinueLoading();
        } else {
            this.queue.clear(true);
            this.playbackInfo = this.playbackInfo.copyWithTrackInfo(TrackGroupArray.EMPTY, this.emptyTrackSelectorResult);
            resetRendererPosition(j);
        }
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return j;
    }

    private void resetRendererPosition(long j) throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            j = this.queue.getPlayingPeriod().toRendererTime(j);
        }
        this.rendererPositionUs = j;
        this.mediaClock.resetPosition(this.rendererPositionUs);
        for (Renderer resetPosition : this.enabledRenderers) {
            resetPosition.resetPosition(this.rendererPositionUs);
        }
    }

    private void setPlaybackParametersInternal(PlaybackParameters playbackParameters) {
        this.mediaClock.setPlaybackParameters(playbackParameters);
    }

    private void setSeekParametersInternal(SeekParameters seekParameters2) {
        this.seekParameters = seekParameters2;
    }

    private void stopInternal(boolean z, boolean z2) {
        resetInternal(true, z, z);
        this.playbackInfoUpdate.incrementPendingOperationAcks(this.pendingPrepareCount + (z2 ? 1 : 0));
        this.pendingPrepareCount = 0;
        this.loadControl.onStopped();
        setState(1);
    }

    private void releaseInternal() {
        resetInternal(true, true, true);
        this.loadControl.onReleased();
        setState(1);
        this.internalPlaybackThread.quit();
        synchronized (this) {
            this.released = true;
            notifyAll();
        }
    }

    private void resetInternal(boolean z, boolean z2, boolean z3) {
        long j;
        this.handler.removeMessages(2);
        this.rebuffering = false;
        this.mediaClock.stop();
        this.rendererPositionUs = 0;
        for (Renderer disableRenderer : this.enabledRenderers) {
            try {
                disableRenderer(disableRenderer);
            } catch (ExoPlaybackException | RuntimeException e) {
                Log.e(TAG, "Stop failed.", e);
            }
        }
        this.enabledRenderers = new Renderer[0];
        this.queue.clear(!z2);
        setIsLoading(false);
        if (z2) {
            this.pendingInitialSeekPosition = null;
        }
        if (z3) {
            this.queue.setTimeline(Timeline.EMPTY);
            Iterator it = this.pendingMessages.iterator();
            while (it.hasNext()) {
                ((PendingMessageInfo) it.next()).message.markAsProcessed(false);
            }
            this.pendingMessages.clear();
            this.nextPendingMessageIndex = 0;
        }
        MediaPeriodId dummyFirstMediaPeriodId = z2 ? this.playbackInfo.getDummyFirstMediaPeriodId(this.shuffleModeEnabled, this.window) : this.playbackInfo.periodId;
        long j2 = -9223372036854775807L;
        if (z2) {
            j = -9223372036854775807L;
        } else {
            j = this.playbackInfo.positionUs;
        }
        if (!z2) {
            j2 = this.playbackInfo.contentPositionUs;
        }
        PlaybackInfo playbackInfo2 = new PlaybackInfo(z3 ? Timeline.EMPTY : this.playbackInfo.timeline, z3 ? null : this.playbackInfo.manifest, dummyFirstMediaPeriodId, j, j2, this.playbackInfo.playbackState, false, z3 ? TrackGroupArray.EMPTY : this.playbackInfo.trackGroups, z3 ? this.emptyTrackSelectorResult : this.playbackInfo.trackSelectorResult, dummyFirstMediaPeriodId, j, 0, j);
        this.playbackInfo = playbackInfo2;
        if (z) {
            MediaSource mediaSource2 = this.mediaSource;
            if (mediaSource2 != null) {
                mediaSource2.releaseSource(this);
                this.mediaSource = null;
            }
        }
    }

    private void sendMessageInternal(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getPositionMs() == -9223372036854775807L) {
            sendMessageToTarget(playerMessage);
        } else if (this.mediaSource == null || this.pendingPrepareCount > 0) {
            this.pendingMessages.add(new PendingMessageInfo(playerMessage));
        } else {
            PendingMessageInfo pendingMessageInfo = new PendingMessageInfo(playerMessage);
            if (resolvePendingMessagePosition(pendingMessageInfo)) {
                this.pendingMessages.add(pendingMessageInfo);
                Collections.sort(this.pendingMessages);
                return;
            }
            playerMessage.markAsProcessed(false);
        }
    }

    private void sendMessageToTarget(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (playerMessage.getHandler().getLooper() == this.handler.getLooper()) {
            deliverMessage(playerMessage);
            if (this.playbackInfo.playbackState == 3 || this.playbackInfo.playbackState == 2) {
                this.handler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        this.handler.obtainMessage(15, playerMessage).sendToTarget();
    }

    private void sendMessageToTargetThread(PlayerMessage playerMessage) {
        playerMessage.getHandler().post(new Runnable(playerMessage) {
            private final /* synthetic */ PlayerMessage f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ExoPlayerImplInternal.lambda$sendMessageToTargetThread$0(ExoPlayerImplInternal.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ void lambda$sendMessageToTargetThread$0(ExoPlayerImplInternal exoPlayerImplInternal, PlayerMessage playerMessage) {
        try {
            exoPlayerImplInternal.deliverMessage(playerMessage);
        } catch (ExoPlaybackException e) {
            Log.e(TAG, "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private void deliverMessage(PlayerMessage playerMessage) throws ExoPlaybackException {
        if (!playerMessage.isCanceled()) {
            try {
                playerMessage.getTarget().handleMessage(playerMessage.getType(), playerMessage.getPayload());
            } finally {
                playerMessage.markAsProcessed(true);
            }
        }
    }

    private void resolvePendingMessagePositions() {
        for (int size = this.pendingMessages.size() - 1; size >= 0; size--) {
            if (!resolvePendingMessagePosition((PendingMessageInfo) this.pendingMessages.get(size))) {
                ((PendingMessageInfo) this.pendingMessages.get(size)).message.markAsProcessed(false);
                this.pendingMessages.remove(size);
            }
        }
        Collections.sort(this.pendingMessages);
    }

    private boolean resolvePendingMessagePosition(PendingMessageInfo pendingMessageInfo) {
        if (pendingMessageInfo.resolvedPeriodUid == null) {
            Pair resolveSeekPosition = resolveSeekPosition(new SeekPosition(pendingMessageInfo.message.getTimeline(), pendingMessageInfo.message.getWindowIndex(), C.msToUs(pendingMessageInfo.message.getPositionMs())), false);
            if (resolveSeekPosition == null) {
                return false;
            }
            pendingMessageInfo.setResolvedPosition(this.playbackInfo.timeline.getIndexOfPeriod(resolveSeekPosition.first), ((Long) resolveSeekPosition.second).longValue(), resolveSeekPosition.first);
        } else {
            int indexOfPeriod = this.playbackInfo.timeline.getIndexOfPeriod(pendingMessageInfo.resolvedPeriodUid);
            if (indexOfPeriod == -1) {
                return false;
            }
            pendingMessageInfo.resolvedPeriodIndex = indexOfPeriod;
        }
        return true;
    }

    private void maybeTriggerPendingMessages(long j, long j2) throws ExoPlaybackException {
        if (!this.pendingMessages.isEmpty() && !this.playbackInfo.periodId.isAd()) {
            if (this.playbackInfo.startPositionUs == j) {
                j--;
            }
            int indexOfPeriod = this.playbackInfo.timeline.getIndexOfPeriod(this.playbackInfo.periodId.periodUid);
            int i = this.nextPendingMessageIndex;
            PendingMessageInfo pendingMessageInfo = i > 0 ? (PendingMessageInfo) this.pendingMessages.get(i - 1) : null;
            while (pendingMessageInfo != null && (pendingMessageInfo.resolvedPeriodIndex > indexOfPeriod || (pendingMessageInfo.resolvedPeriodIndex == indexOfPeriod && pendingMessageInfo.resolvedPeriodTimeUs > j))) {
                this.nextPendingMessageIndex--;
                int i2 = this.nextPendingMessageIndex;
                pendingMessageInfo = i2 > 0 ? (PendingMessageInfo) this.pendingMessages.get(i2 - 1) : null;
            }
            PendingMessageInfo pendingMessageInfo2 = this.nextPendingMessageIndex < this.pendingMessages.size() ? (PendingMessageInfo) this.pendingMessages.get(this.nextPendingMessageIndex) : null;
            while (pendingMessageInfo2 != null && pendingMessageInfo2.resolvedPeriodUid != null && (pendingMessageInfo2.resolvedPeriodIndex < indexOfPeriod || (pendingMessageInfo2.resolvedPeriodIndex == indexOfPeriod && pendingMessageInfo2.resolvedPeriodTimeUs <= j))) {
                this.nextPendingMessageIndex++;
                pendingMessageInfo2 = this.nextPendingMessageIndex < this.pendingMessages.size() ? (PendingMessageInfo) this.pendingMessages.get(this.nextPendingMessageIndex) : null;
            }
            while (pendingMessageInfo2 != null && pendingMessageInfo2.resolvedPeriodUid != null && pendingMessageInfo2.resolvedPeriodIndex == indexOfPeriod && pendingMessageInfo2.resolvedPeriodTimeUs > j && pendingMessageInfo2.resolvedPeriodTimeUs <= j2) {
                sendMessageToTarget(pendingMessageInfo2.message);
                if (pendingMessageInfo2.message.getDeleteAfterDelivery() || pendingMessageInfo2.message.isCanceled()) {
                    this.pendingMessages.remove(this.nextPendingMessageIndex);
                } else {
                    this.nextPendingMessageIndex++;
                }
                pendingMessageInfo2 = this.nextPendingMessageIndex < this.pendingMessages.size() ? (PendingMessageInfo) this.pendingMessages.get(this.nextPendingMessageIndex) : null;
            }
        }
    }

    private void ensureStopped(Renderer renderer) throws ExoPlaybackException {
        if (renderer.getState() == 2) {
            renderer.stop();
        }
    }

    private void disableRenderer(Renderer renderer) throws ExoPlaybackException {
        this.mediaClock.onRendererDisabled(renderer);
        ensureStopped(renderer);
        renderer.disable();
    }

    private void reselectTracksInternal() throws ExoPlaybackException {
        if (this.queue.hasPlayingPeriod()) {
            float f = this.mediaClock.getPlaybackParameters().speed;
            MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
            MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
            boolean z = true;
            while (playingPeriod != null && playingPeriod.prepared) {
                if (playingPeriod.selectTracks(f)) {
                    if (z) {
                        MediaPeriodHolder playingPeriod2 = this.queue.getPlayingPeriod();
                        boolean[] zArr = new boolean[this.renderers.length];
                        long applyTrackSelection = playingPeriod2.applyTrackSelection(this.playbackInfo.positionUs, this.queue.removeAfter(playingPeriod2), zArr);
                        if (!(this.playbackInfo.playbackState == 4 || applyTrackSelection == this.playbackInfo.positionUs)) {
                            PlaybackInfo playbackInfo2 = this.playbackInfo;
                            this.playbackInfo = playbackInfo2.copyWithNewPosition(playbackInfo2.periodId, applyTrackSelection, this.playbackInfo.contentPositionUs, getTotalBufferedDurationUs());
                            this.playbackInfoUpdate.setPositionDiscontinuity(4);
                            resetRendererPosition(applyTrackSelection);
                        }
                        boolean[] zArr2 = new boolean[this.renderers.length];
                        int i = 0;
                        int i2 = 0;
                        while (true) {
                            Renderer[] rendererArr = this.renderers;
                            if (i >= rendererArr.length) {
                                break;
                            }
                            Renderer renderer = rendererArr[i];
                            zArr2[i] = renderer.getState() != 0;
                            SampleStream sampleStream = playingPeriod2.sampleStreams[i];
                            if (sampleStream != null) {
                                i2++;
                            }
                            if (zArr2[i]) {
                                if (sampleStream != renderer.getStream()) {
                                    disableRenderer(renderer);
                                } else if (zArr[i]) {
                                    renderer.resetPosition(this.rendererPositionUs);
                                }
                            }
                            i++;
                        }
                        this.playbackInfo = this.playbackInfo.copyWithTrackInfo(playingPeriod2.trackGroups, playingPeriod2.trackSelectorResult);
                        enableRenderers(zArr2, i2);
                    } else {
                        this.queue.removeAfter(playingPeriod);
                        if (playingPeriod.prepared) {
                            playingPeriod.applyTrackSelection(Math.max(playingPeriod.info.startPositionUs, playingPeriod.toPeriodTime(this.rendererPositionUs)), false);
                        }
                    }
                    handleLoadingMediaPeriodChanged(true);
                    if (this.playbackInfo.playbackState != 4) {
                        maybeContinueLoading();
                        updatePlaybackPositions();
                        this.handler.sendEmptyMessage(2);
                    }
                    return;
                }
                if (playingPeriod == readingPeriod) {
                    z = false;
                }
                playingPeriod = playingPeriod.next;
            }
        }
    }

    private void updateTrackSelectionPlaybackSpeed(float f) {
        TrackSelection[] all;
        for (MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod(); frontPeriod != null; frontPeriod = frontPeriod.next) {
            if (frontPeriod.trackSelectorResult != null) {
                for (TrackSelection trackSelection : frontPeriod.trackSelectorResult.selections.getAll()) {
                    if (trackSelection != null) {
                        trackSelection.onPlaybackSpeed(f);
                    }
                }
            }
        }
    }

    private boolean shouldTransitionToReadyState(boolean z) {
        if (this.enabledRenderers.length == 0) {
            return isTimelineReady();
        }
        boolean z2 = false;
        if (!z) {
            return false;
        }
        if (!this.playbackInfo.isLoading) {
            return true;
        }
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if ((loadingPeriod.isFullyBuffered() && loadingPeriod.info.isFinal) || this.loadControl.shouldStartPlayback(getTotalBufferedDurationUs(), this.mediaClock.getPlaybackParameters().speed, this.rebuffering)) {
            z2 = true;
        }
        return z2;
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        long j = playingPeriod.info.durationUs;
        return j == -9223372036854775807L || this.playbackInfo.positionUs < j || (playingPeriod.next != null && (playingPeriod.next.prepared || playingPeriod.next.info.id.isAd()));
    }

    private void maybeThrowSourceInfoRefreshError() throws IOException {
        if (this.queue.getLoadingPeriod() != null) {
            Renderer[] rendererArr = this.enabledRenderers;
            int length = rendererArr.length;
            int i = 0;
            while (i < length) {
                if (rendererArr[i].hasReadStreamToEnd()) {
                    i++;
                } else {
                    return;
                }
            }
        }
        this.mediaSource.maybeThrowSourceInfoRefreshError();
    }

    private void maybeThrowPeriodPrepareError() throws IOException {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        if (loadingPeriod != null && !loadingPeriod.prepared && (readingPeriod == null || readingPeriod.next == loadingPeriod)) {
            Renderer[] rendererArr = this.enabledRenderers;
            int length = rendererArr.length;
            int i = 0;
            while (i < length) {
                if (rendererArr[i].hasReadStreamToEnd()) {
                    i++;
                } else {
                    return;
                }
            }
            loadingPeriod.mediaPeriod.maybeThrowPrepareError();
        }
    }

    private void handleSourceInfoRefreshed(MediaSourceRefreshInfo mediaSourceRefreshInfo) throws ExoPlaybackException {
        MediaSourceRefreshInfo mediaSourceRefreshInfo2 = mediaSourceRefreshInfo;
        if (mediaSourceRefreshInfo2.source == this.mediaSource) {
            Timeline timeline = this.playbackInfo.timeline;
            Timeline timeline2 = mediaSourceRefreshInfo2.timeline;
            Object obj = mediaSourceRefreshInfo2.manifest;
            this.queue.setTimeline(timeline2);
            this.playbackInfo = this.playbackInfo.copyWithTimeline(timeline2, obj);
            resolvePendingMessagePositions();
            int i = this.pendingPrepareCount;
            long j = 0;
            if (i > 0) {
                this.playbackInfoUpdate.incrementPendingOperationAcks(i);
                this.pendingPrepareCount = 0;
                SeekPosition seekPosition = this.pendingInitialSeekPosition;
                if (seekPosition != null) {
                    try {
                        Pair resolveSeekPosition = resolveSeekPosition(seekPosition, true);
                        this.pendingInitialSeekPosition = null;
                        if (resolveSeekPosition == null) {
                            handleSourceInfoRefreshEndedPlayback();
                        } else {
                            Object obj2 = resolveSeekPosition.first;
                            long longValue = ((Long) resolveSeekPosition.second).longValue();
                            MediaPeriodId resolveMediaPeriodIdForAds = this.queue.resolveMediaPeriodIdForAds(obj2, longValue);
                            this.playbackInfo = this.playbackInfo.resetToNewPosition(resolveMediaPeriodIdForAds, resolveMediaPeriodIdForAds.isAd() ? 0 : longValue, longValue);
                        }
                    } catch (IllegalSeekPositionException e) {
                        IllegalSeekPositionException illegalSeekPositionException = e;
                        this.playbackInfo = this.playbackInfo.resetToNewPosition(this.playbackInfo.getDummyFirstMediaPeriodId(this.shuffleModeEnabled, this.window), -9223372036854775807L, -9223372036854775807L);
                        throw illegalSeekPositionException;
                    }
                } else if (this.playbackInfo.startPositionUs == -9223372036854775807L) {
                    if (timeline2.isEmpty()) {
                        handleSourceInfoRefreshEndedPlayback();
                    } else {
                        Pair periodPosition = getPeriodPosition(timeline2, timeline2.getFirstWindowIndex(this.shuffleModeEnabled), -9223372036854775807L);
                        Object obj3 = periodPosition.first;
                        long longValue2 = ((Long) periodPosition.second).longValue();
                        MediaPeriodId resolveMediaPeriodIdForAds2 = this.queue.resolveMediaPeriodIdForAds(obj3, longValue2);
                        this.playbackInfo = this.playbackInfo.resetToNewPosition(resolveMediaPeriodIdForAds2, resolveMediaPeriodIdForAds2.isAd() ? 0 : longValue2, longValue2);
                    }
                }
            } else if (timeline.isEmpty()) {
                if (!timeline2.isEmpty()) {
                    Pair periodPosition2 = getPeriodPosition(timeline2, timeline2.getFirstWindowIndex(this.shuffleModeEnabled), -9223372036854775807L);
                    Object obj4 = periodPosition2.first;
                    long longValue3 = ((Long) periodPosition2.second).longValue();
                    MediaPeriodId resolveMediaPeriodIdForAds3 = this.queue.resolveMediaPeriodIdForAds(obj4, longValue3);
                    this.playbackInfo = this.playbackInfo.resetToNewPosition(resolveMediaPeriodIdForAds3, resolveMediaPeriodIdForAds3.isAd() ? 0 : longValue3, longValue3);
                }
            } else {
                MediaPeriodHolder frontPeriod = this.queue.getFrontPeriod();
                long j2 = this.playbackInfo.contentPositionUs;
                Object obj5 = frontPeriod == null ? this.playbackInfo.periodId.periodUid : frontPeriod.uid;
                if (timeline2.getIndexOfPeriod(obj5) == -1) {
                    Object resolveSubsequentPeriod = resolveSubsequentPeriod(obj5, timeline, timeline2);
                    if (resolveSubsequentPeriod == null) {
                        handleSourceInfoRefreshEndedPlayback();
                        return;
                    }
                    Pair periodPosition3 = getPeriodPosition(timeline2, timeline2.getPeriodByUid(resolveSubsequentPeriod, this.period).windowIndex, -9223372036854775807L);
                    Object obj6 = periodPosition3.first;
                    long longValue4 = ((Long) periodPosition3.second).longValue();
                    MediaPeriodId resolveMediaPeriodIdForAds4 = this.queue.resolveMediaPeriodIdForAds(obj6, longValue4);
                    if (frontPeriod != null) {
                        while (frontPeriod.next != null) {
                            frontPeriod = frontPeriod.next;
                            if (frontPeriod.info.id.equals(resolveMediaPeriodIdForAds4)) {
                                frontPeriod.info = this.queue.getUpdatedMediaPeriodInfo(frontPeriod.info);
                            }
                        }
                    }
                    if (!resolveMediaPeriodIdForAds4.isAd()) {
                        j = longValue4;
                    }
                    this.playbackInfo = this.playbackInfo.copyWithNewPosition(resolveMediaPeriodIdForAds4, seekToPeriodPosition(resolveMediaPeriodIdForAds4, j), longValue4, getTotalBufferedDurationUs());
                    return;
                }
                MediaPeriodId mediaPeriodId = this.playbackInfo.periodId;
                if (mediaPeriodId.isAd()) {
                    MediaPeriodId resolveMediaPeriodIdForAds5 = this.queue.resolveMediaPeriodIdForAds(obj5, j2);
                    if (!resolveMediaPeriodIdForAds5.equals(mediaPeriodId)) {
                        if (!resolveMediaPeriodIdForAds5.isAd()) {
                            j = j2;
                        }
                        this.playbackInfo = this.playbackInfo.copyWithNewPosition(resolveMediaPeriodIdForAds5, seekToPeriodPosition(resolveMediaPeriodIdForAds5, j), j2, getTotalBufferedDurationUs());
                        return;
                    }
                }
                if (!this.queue.updateQueuedPeriods(mediaPeriodId, this.rendererPositionUs)) {
                    seekToCurrentPosition(false);
                }
                handleLoadingMediaPeriodChanged(false);
            }
        }
    }

    private void handleSourceInfoRefreshEndedPlayback() {
        setState(4);
        resetInternal(false, true, false);
    }

    @Nullable
    private Object resolveSubsequentPeriod(Object obj, Timeline timeline, Timeline timeline2) {
        int indexOfPeriod = timeline.getIndexOfPeriod(obj);
        int periodCount = timeline.getPeriodCount();
        int i = indexOfPeriod;
        int i2 = -1;
        for (int i3 = 0; i3 < periodCount && i2 == -1; i3++) {
            i = timeline.getNextPeriodIndex(i, this.period, this.window, this.repeatMode, this.shuffleModeEnabled);
            if (i == -1) {
                break;
            }
            i2 = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i));
        }
        if (i2 == -1) {
            return null;
        }
        return timeline2.getUidOfPeriod(i2);
    }

    private Pair<Object, Long> resolveSeekPosition(SeekPosition seekPosition, boolean z) {
        Timeline timeline = this.playbackInfo.timeline;
        Timeline timeline2 = seekPosition.timeline;
        if (timeline.isEmpty()) {
            return null;
        }
        if (timeline2.isEmpty()) {
            timeline2 = timeline;
        }
        try {
            Pair<Object, Long> periodPosition = timeline2.getPeriodPosition(this.window, this.period, seekPosition.windowIndex, seekPosition.windowPositionUs);
            if (timeline == timeline2) {
                return periodPosition;
            }
            int indexOfPeriod = timeline.getIndexOfPeriod(periodPosition.first);
            if (indexOfPeriod != -1) {
                return periodPosition;
            }
            if (!z || resolveSubsequentPeriod(periodPosition.first, timeline2, timeline) == null) {
                return null;
            }
            return getPeriodPosition(timeline, timeline.getPeriod(indexOfPeriod, this.period).windowIndex, -9223372036854775807L);
        } catch (IndexOutOfBoundsException unused) {
            throw new IllegalSeekPositionException(timeline, seekPosition.windowIndex, seekPosition.windowPositionUs);
        }
    }

    private Pair<Object, Long> getPeriodPosition(Timeline timeline, int i, long j) {
        return timeline.getPeriodPosition(this.window, this.period, i, j);
    }

    private void updatePeriods() throws ExoPlaybackException, IOException {
        MediaSource mediaSource2 = this.mediaSource;
        if (mediaSource2 != null) {
            if (this.pendingPrepareCount > 0) {
                mediaSource2.maybeThrowSourceInfoRefreshError();
                return;
            }
            maybeUpdateLoadingPeriod();
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            int i = 0;
            if (loadingPeriod == null || loadingPeriod.isFullyBuffered()) {
                setIsLoading(false);
            } else if (!this.playbackInfo.isLoading) {
                maybeContinueLoading();
            }
            if (this.queue.hasPlayingPeriod()) {
                MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
                MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
                boolean z = false;
                while (this.playWhenReady && playingPeriod != readingPeriod && this.rendererPositionUs >= playingPeriod.next.getStartPositionRendererTime()) {
                    if (z) {
                        maybeNotifyPlaybackInfoChanged();
                    }
                    int i2 = playingPeriod.info.isLastInTimelinePeriod ? 0 : 3;
                    MediaPeriodHolder advancePlayingPeriod = this.queue.advancePlayingPeriod();
                    updatePlayingPeriodRenderers(playingPeriod);
                    this.playbackInfo = this.playbackInfo.copyWithNewPosition(advancePlayingPeriod.info.id, advancePlayingPeriod.info.startPositionUs, advancePlayingPeriod.info.contentPositionUs, getTotalBufferedDurationUs());
                    this.playbackInfoUpdate.setPositionDiscontinuity(i2);
                    updatePlaybackPositions();
                    playingPeriod = advancePlayingPeriod;
                    z = true;
                }
                if (readingPeriod.info.isFinal) {
                    while (true) {
                        Renderer[] rendererArr = this.renderers;
                        if (i < rendererArr.length) {
                            Renderer renderer = rendererArr[i];
                            SampleStream sampleStream = readingPeriod.sampleStreams[i];
                            if (sampleStream != null && renderer.getStream() == sampleStream && renderer.hasReadStreamToEnd()) {
                                renderer.setCurrentStreamFinal();
                            }
                            i++;
                        } else {
                            return;
                        }
                    }
                } else if (readingPeriod.next != null) {
                    int i3 = 0;
                    while (true) {
                        Renderer[] rendererArr2 = this.renderers;
                        if (i3 < rendererArr2.length) {
                            Renderer renderer2 = rendererArr2[i3];
                            SampleStream sampleStream2 = readingPeriod.sampleStreams[i3];
                            if (renderer2.getStream() == sampleStream2 && (sampleStream2 == null || renderer2.hasReadStreamToEnd())) {
                                i3++;
                            }
                        } else if (!readingPeriod.next.prepared) {
                            maybeThrowPeriodPrepareError();
                            return;
                        } else {
                            TrackSelectorResult trackSelectorResult = readingPeriod.trackSelectorResult;
                            MediaPeriodHolder advanceReadingPeriod = this.queue.advanceReadingPeriod();
                            TrackSelectorResult trackSelectorResult2 = advanceReadingPeriod.trackSelectorResult;
                            boolean z2 = advanceReadingPeriod.mediaPeriod.readDiscontinuity() != -9223372036854775807L;
                            int i4 = 0;
                            while (true) {
                                Renderer[] rendererArr3 = this.renderers;
                                if (i4 < rendererArr3.length) {
                                    Renderer renderer3 = rendererArr3[i4];
                                    if (trackSelectorResult.isRendererEnabled(i4)) {
                                        if (z2) {
                                            renderer3.setCurrentStreamFinal();
                                        } else if (!renderer3.isCurrentStreamFinal()) {
                                            TrackSelection trackSelection = trackSelectorResult2.selections.get(i4);
                                            boolean isRendererEnabled = trackSelectorResult2.isRendererEnabled(i4);
                                            boolean z3 = this.rendererCapabilities[i4].getTrackType() == 6;
                                            RendererConfiguration rendererConfiguration = trackSelectorResult.rendererConfigurations[i4];
                                            RendererConfiguration rendererConfiguration2 = trackSelectorResult2.rendererConfigurations[i4];
                                            if (!isRendererEnabled || !rendererConfiguration2.equals(rendererConfiguration) || z3) {
                                                renderer3.setCurrentStreamFinal();
                                            } else {
                                                renderer3.replaceStream(getFormats(trackSelection), advanceReadingPeriod.sampleStreams[i4], advanceReadingPeriod.getRendererOffset());
                                            }
                                        }
                                    }
                                    i4++;
                                } else {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void maybeUpdateLoadingPeriod() throws IOException {
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        if (this.queue.shouldLoadNextMediaPeriod()) {
            MediaPeriodInfo nextMediaPeriodInfo = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo);
            if (nextMediaPeriodInfo == null) {
                maybeThrowSourceInfoRefreshError();
                return;
            }
            this.queue.enqueueNextMediaPeriod(this.rendererCapabilities, this.trackSelector, this.loadControl.getAllocator(), this.mediaSource, nextMediaPeriodInfo).prepare(this, nextMediaPeriodInfo.startPositionUs);
            setIsLoading(true);
            handleLoadingMediaPeriodChanged(false);
        }
    }

    private void handlePeriodPrepared(MediaPeriod mediaPeriod) throws ExoPlaybackException {
        if (this.queue.isLoading(mediaPeriod)) {
            MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
            loadingPeriod.handlePrepared(this.mediaClock.getPlaybackParameters().speed);
            updateLoadControlTrackSelection(loadingPeriod.trackGroups, loadingPeriod.trackSelectorResult);
            if (!this.queue.hasPlayingPeriod()) {
                resetRendererPosition(this.queue.advancePlayingPeriod().info.startPositionUs);
                updatePlayingPeriodRenderers(null);
            }
            maybeContinueLoading();
        }
    }

    private void handleContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.queue.isLoading(mediaPeriod)) {
            this.queue.reevaluateBuffer(this.rendererPositionUs);
            maybeContinueLoading();
        }
    }

    private void handlePlaybackParameters(PlaybackParameters playbackParameters) throws ExoPlaybackException {
        Renderer[] rendererArr;
        this.eventHandler.obtainMessage(1, playbackParameters).sendToTarget();
        updateTrackSelectionPlaybackSpeed(playbackParameters.speed);
        for (Renderer renderer : this.renderers) {
            if (renderer != null) {
                renderer.setOperatingRate(playbackParameters.speed);
            }
        }
    }

    private void maybeContinueLoading() {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        long nextLoadPositionUs = loadingPeriod.getNextLoadPositionUs();
        if (nextLoadPositionUs == Long.MIN_VALUE) {
            setIsLoading(false);
            return;
        }
        boolean shouldContinueLoading = this.loadControl.shouldContinueLoading(getTotalBufferedDurationUs(nextLoadPositionUs), this.mediaClock.getPlaybackParameters().speed);
        setIsLoading(shouldContinueLoading);
        if (shouldContinueLoading) {
            loadingPeriod.continueLoading(this.rendererPositionUs);
        }
    }

    private void updatePlayingPeriodRenderers(@Nullable MediaPeriodHolder mediaPeriodHolder) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        if (playingPeriod != null && mediaPeriodHolder != playingPeriod) {
            boolean[] zArr = new boolean[this.renderers.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                Renderer[] rendererArr = this.renderers;
                if (i < rendererArr.length) {
                    Renderer renderer = rendererArr[i];
                    zArr[i] = renderer.getState() != 0;
                    if (playingPeriod.trackSelectorResult.isRendererEnabled(i)) {
                        i2++;
                    }
                    if (zArr[i] && (!playingPeriod.trackSelectorResult.isRendererEnabled(i) || (renderer.isCurrentStreamFinal() && renderer.getStream() == mediaPeriodHolder.sampleStreams[i]))) {
                        disableRenderer(renderer);
                    }
                    i++;
                } else {
                    this.playbackInfo = this.playbackInfo.copyWithTrackInfo(playingPeriod.trackGroups, playingPeriod.trackSelectorResult);
                    enableRenderers(zArr, i2);
                    return;
                }
            }
        }
    }

    private void enableRenderers(boolean[] zArr, int i) throws ExoPlaybackException {
        this.enabledRenderers = new Renderer[i];
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        int i2 = 0;
        for (int i3 = 0; i3 < this.renderers.length; i3++) {
            if (playingPeriod.trackSelectorResult.isRendererEnabled(i3)) {
                int i4 = i2 + 1;
                enableRenderer(i3, zArr[i3], i2);
                i2 = i4;
            }
        }
    }

    private void enableRenderer(int i, boolean z, int i2) throws ExoPlaybackException {
        MediaPeriodHolder playingPeriod = this.queue.getPlayingPeriod();
        Renderer renderer = this.renderers[i];
        this.enabledRenderers[i2] = renderer;
        if (renderer.getState() == 0) {
            RendererConfiguration rendererConfiguration = playingPeriod.trackSelectorResult.rendererConfigurations[i];
            Format[] formats = getFormats(playingPeriod.trackSelectorResult.selections.get(i));
            boolean z2 = this.playWhenReady && this.playbackInfo.playbackState == 3;
            renderer.enable(rendererConfiguration, formats, playingPeriod.sampleStreams[i], this.rendererPositionUs, !z && z2, playingPeriod.getRendererOffset());
            this.mediaClock.onRendererEnabled(renderer);
            if (z2) {
                renderer.start();
            }
        }
    }

    private boolean rendererWaitingForNextStream(Renderer renderer) {
        MediaPeriodHolder readingPeriod = this.queue.getReadingPeriod();
        return readingPeriod.next != null && readingPeriod.next.prepared && renderer.hasReadStreamToEnd();
    }

    private void handleLoadingMediaPeriodChanged(boolean z) {
        long j;
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        MediaPeriodId mediaPeriodId = loadingPeriod == null ? this.playbackInfo.periodId : loadingPeriod.info.id;
        boolean z2 = !this.playbackInfo.loadingMediaPeriodId.equals(mediaPeriodId);
        if (z2) {
            this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(mediaPeriodId);
        }
        PlaybackInfo playbackInfo2 = this.playbackInfo;
        if (loadingPeriod == null) {
            j = playbackInfo2.positionUs;
        } else {
            j = loadingPeriod.getBufferedPositionUs();
        }
        playbackInfo2.bufferedPositionUs = j;
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if ((z2 || z) && loadingPeriod != null && loadingPeriod.prepared) {
            updateLoadControlTrackSelection(loadingPeriod.trackGroups, loadingPeriod.trackSelectorResult);
        }
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private long getTotalBufferedDurationUs(long j) {
        MediaPeriodHolder loadingPeriod = this.queue.getLoadingPeriod();
        if (loadingPeriod == null) {
            return 0;
        }
        return j - loadingPeriod.toPeriodTime(this.rendererPositionUs);
    }

    private void updateLoadControlTrackSelection(TrackGroupArray trackGroupArray, TrackSelectorResult trackSelectorResult) {
        this.loadControl.onTracksSelected(this.renderers, trackGroupArray, trackSelectorResult.selections);
    }

    private static Format[] getFormats(TrackSelection trackSelection) {
        int length = trackSelection != null ? trackSelection.length() : 0;
        Format[] formatArr = new Format[length];
        for (int i = 0; i < length; i++) {
            formatArr[i] = trackSelection.getFormat(i);
        }
        return formatArr;
    }
}
