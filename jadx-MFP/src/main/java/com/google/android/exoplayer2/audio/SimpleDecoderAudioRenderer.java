package com.google.android.exoplayer2.audio;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.BaseRenderer;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.audio.AudioRendererEventListener.EventDispatcher;
import com.google.android.exoplayer2.audio.AudioSink.ConfigurationException;
import com.google.android.exoplayer2.audio.AudioSink.InitializationException;
import com.google.android.exoplayer2.audio.AudioSink.Listener;
import com.google.android.exoplayer2.audio.AudioSink.WriteException;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.SimpleDecoder;
import com.google.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.ExoMediaCrypto;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MediaClock;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;

public abstract class SimpleDecoderAudioRenderer extends BaseRenderer implements MediaClock {
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    private boolean allowFirstBufferPositionDiscontinuity;
    /* access modifiers changed from: private */
    public boolean allowPositionDiscontinuity;
    private final AudioSink audioSink;
    private boolean audioTrackNeedsConfigure;
    private long currentPositionUs;
    private SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException> decoder;
    private DecoderCounters decoderCounters;
    private boolean decoderReceivedBuffers;
    private int decoderReinitializationState;
    private DrmSession<ExoMediaCrypto> drmSession;
    private final DrmSessionManager<ExoMediaCrypto> drmSessionManager;
    private int encoderDelay;
    private int encoderPadding;
    /* access modifiers changed from: private */
    public final EventDispatcher eventDispatcher;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private final FormatHolder formatHolder;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private SimpleOutputBuffer outputBuffer;
    private boolean outputStreamEnded;
    private DrmSession<ExoMediaCrypto> pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private boolean waitingForKeys;

    private final class AudioSinkListener implements Listener {
        private AudioSinkListener() {
        }

        public void onAudioSessionId(int i) {
            SimpleDecoderAudioRenderer.this.eventDispatcher.audioSessionId(i);
            SimpleDecoderAudioRenderer.this.onAudioSessionId(i);
        }

        public void onPositionDiscontinuity() {
            SimpleDecoderAudioRenderer.this.onAudioTrackPositionDiscontinuity();
            SimpleDecoderAudioRenderer.this.allowPositionDiscontinuity = true;
        }

        public void onUnderrun(int i, long j, long j2) {
            SimpleDecoderAudioRenderer.this.eventDispatcher.audioTrackUnderrun(i, j, j2);
            SimpleDecoderAudioRenderer.this.onAudioTrackUnderrun(i, j, j2);
        }
    }

    /* access modifiers changed from: protected */
    public abstract SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException> createDecoder(Format format, ExoMediaCrypto exoMediaCrypto) throws AudioDecoderException;

    public MediaClock getMediaClock() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void onAudioSessionId(int i) {
    }

    /* access modifiers changed from: protected */
    public void onAudioTrackPositionDiscontinuity() {
    }

    /* access modifiers changed from: protected */
    public void onAudioTrackUnderrun(int i, long j, long j2) {
    }

    /* access modifiers changed from: protected */
    public abstract int supportsFormatInternal(DrmSessionManager<ExoMediaCrypto> drmSessionManager2, Format format);

    public SimpleDecoderAudioRenderer() {
        this((Handler) null, (AudioRendererEventListener) null, new AudioProcessor[0]);
    }

    public SimpleDecoderAudioRenderer(@Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, AudioProcessor... audioProcessorArr) {
        this(handler, audioRendererEventListener, null, null, false, audioProcessorArr);
    }

    public SimpleDecoderAudioRenderer(@Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, @Nullable AudioCapabilities audioCapabilities) {
        this(handler, audioRendererEventListener, audioCapabilities, null, false, new AudioProcessor[0]);
    }

    public SimpleDecoderAudioRenderer(@Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, @Nullable AudioCapabilities audioCapabilities, @Nullable DrmSessionManager<ExoMediaCrypto> drmSessionManager2, boolean z, AudioProcessor... audioProcessorArr) {
        this(handler, audioRendererEventListener, drmSessionManager2, z, new DefaultAudioSink(audioCapabilities, audioProcessorArr));
    }

    public SimpleDecoderAudioRenderer(@Nullable Handler handler, @Nullable AudioRendererEventListener audioRendererEventListener, @Nullable DrmSessionManager<ExoMediaCrypto> drmSessionManager2, boolean z, AudioSink audioSink2) {
        super(1);
        this.drmSessionManager = drmSessionManager2;
        this.playClearSamplesWithoutKeys = z;
        this.eventDispatcher = new EventDispatcher(handler, audioRendererEventListener);
        this.audioSink = audioSink2;
        audioSink2.setListener(new AudioSinkListener());
        this.formatHolder = new FormatHolder();
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.decoderReinitializationState = 0;
        this.audioTrackNeedsConfigure = true;
    }

    public final int supportsFormat(Format format) {
        int i = 0;
        if (!MimeTypes.isAudio(format.sampleMimeType)) {
            return 0;
        }
        int supportsFormatInternal = supportsFormatInternal(this.drmSessionManager, format);
        if (supportsFormatInternal <= 2) {
            return supportsFormatInternal;
        }
        if (Util.SDK_INT >= 21) {
            i = 32;
        }
        return supportsFormatInternal | i | 8;
    }

    /* access modifiers changed from: protected */
    public final boolean supportsOutput(int i, int i2) {
        return this.audioSink.supportsOutput(i, i2);
    }

    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            try {
                this.audioSink.playToEndOfStream();
            } catch (WriteException e) {
                throw ExoPlaybackException.createForRenderer(e, getIndex());
            }
        } else {
            if (this.inputFormat == null) {
                this.flagsOnlyBuffer.clear();
                int readSource = readSource(this.formatHolder, this.flagsOnlyBuffer, true);
                if (readSource == -5) {
                    onInputFormatChanged(this.formatHolder.format);
                } else if (readSource == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    processEndOfStream();
                    return;
                } else {
                    return;
                }
            }
            maybeInitDecoder();
            if (this.decoder != null) {
                try {
                    TraceUtil.beginSection("drainAndFeed");
                    while (drainOutputBuffer()) {
                    }
                    while (feedInputBuffer()) {
                    }
                    TraceUtil.endSection();
                    this.decoderCounters.ensureUpdated();
                } catch (AudioDecoderException | ConfigurationException | InitializationException | WriteException e2) {
                    throw ExoPlaybackException.createForRenderer(e2, getIndex());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Format getOutputFormat() {
        return Format.createAudioSampleFormat(null, MimeTypes.AUDIO_RAW, null, -1, -1, this.inputFormat.channelCount, this.inputFormat.sampleRate, 2, null, null, 0, null);
    }

    private boolean drainOutputBuffer() throws ExoPlaybackException, AudioDecoderException, ConfigurationException, InitializationException, WriteException {
        if (this.outputBuffer == null) {
            this.outputBuffer = (SimpleOutputBuffer) this.decoder.dequeueOutputBuffer();
            SimpleOutputBuffer simpleOutputBuffer = this.outputBuffer;
            if (simpleOutputBuffer == null) {
                return false;
            }
            if (simpleOutputBuffer.skippedOutputBufferCount > 0) {
                this.decoderCounters.skippedOutputBufferCount += this.outputBuffer.skippedOutputBufferCount;
                this.audioSink.handleDiscontinuity();
            }
        }
        if (this.outputBuffer.isEndOfStream()) {
            if (this.decoderReinitializationState == 2) {
                releaseDecoder();
                maybeInitDecoder();
                this.audioTrackNeedsConfigure = true;
            } else {
                this.outputBuffer.release();
                this.outputBuffer = null;
                processEndOfStream();
            }
            return false;
        }
        if (this.audioTrackNeedsConfigure) {
            Format outputFormat = getOutputFormat();
            this.audioSink.configure(outputFormat.pcmEncoding, outputFormat.channelCount, outputFormat.sampleRate, 0, null, this.encoderDelay, this.encoderPadding);
            this.audioTrackNeedsConfigure = false;
        }
        if (!this.audioSink.handleBuffer(this.outputBuffer.data, this.outputBuffer.timeUs)) {
            return false;
        }
        this.decoderCounters.renderedOutputBufferCount++;
        this.outputBuffer.release();
        this.outputBuffer = null;
        return true;
    }

    private boolean feedInputBuffer() throws AudioDecoderException, ExoPlaybackException {
        int i;
        SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException> simpleDecoder = this.decoder;
        if (simpleDecoder == null || this.decoderReinitializationState == 2 || this.inputStreamEnded) {
            return false;
        }
        if (this.inputBuffer == null) {
            this.inputBuffer = simpleDecoder.dequeueInputBuffer();
            if (this.inputBuffer == null) {
                return false;
            }
        }
        if (this.decoderReinitializationState == 1) {
            this.inputBuffer.setFlags(4);
            this.decoder.queueInputBuffer(this.inputBuffer);
            this.inputBuffer = null;
            this.decoderReinitializationState = 2;
            return false;
        }
        if (this.waitingForKeys) {
            i = -4;
        } else {
            i = readSource(this.formatHolder, this.inputBuffer, false);
        }
        if (i == -3) {
            return false;
        }
        if (i == -5) {
            onInputFormatChanged(this.formatHolder.format);
            return true;
        } else if (this.inputBuffer.isEndOfStream()) {
            this.inputStreamEnded = true;
            this.decoder.queueInputBuffer(this.inputBuffer);
            this.inputBuffer = null;
            return false;
        } else {
            this.waitingForKeys = shouldWaitForKeys(this.inputBuffer.isEncrypted());
            if (this.waitingForKeys) {
                return false;
            }
            this.inputBuffer.flip();
            onQueueInputBuffer(this.inputBuffer);
            this.decoder.queueInputBuffer(this.inputBuffer);
            this.decoderReceivedBuffers = true;
            this.decoderCounters.inputBufferCount++;
            this.inputBuffer = null;
            return true;
        }
    }

    private boolean shouldWaitForKeys(boolean z) throws ExoPlaybackException {
        if (this.drmSession == null || (!z && this.playClearSamplesWithoutKeys)) {
            return false;
        }
        int state = this.drmSession.getState();
        boolean z2 = true;
        if (state != 1) {
            if (state == 4) {
                z2 = false;
            }
            return z2;
        }
        throw ExoPlaybackException.createForRenderer(this.drmSession.getError(), getIndex());
    }

    private void processEndOfStream() throws ExoPlaybackException {
        this.outputStreamEnded = true;
        try {
            this.audioSink.playToEndOfStream();
        } catch (WriteException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    private void flushDecoder() throws ExoPlaybackException {
        this.waitingForKeys = false;
        if (this.decoderReinitializationState != 0) {
            releaseDecoder();
            maybeInitDecoder();
            return;
        }
        this.inputBuffer = null;
        SimpleOutputBuffer simpleOutputBuffer = this.outputBuffer;
        if (simpleOutputBuffer != null) {
            simpleOutputBuffer.release();
            this.outputBuffer = null;
        }
        this.decoder.flush();
        this.decoderReceivedBuffers = false;
    }

    public boolean isEnded() {
        return this.outputStreamEnded && this.audioSink.isEnded();
    }

    public boolean isReady() {
        return this.audioSink.hasPendingData() || (this.inputFormat != null && !this.waitingForKeys && (isSourceReady() || this.outputBuffer != null));
    }

    public long getPositionUs() {
        if (getState() == 2) {
            updateCurrentPosition();
        }
        return this.currentPositionUs;
    }

    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        return this.audioSink.setPlaybackParameters(playbackParameters);
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.audioSink.getPlaybackParameters();
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
        this.eventDispatcher.enabled(this.decoderCounters);
        int i = getConfiguration().tunnelingAudioSessionId;
        if (i != 0) {
            this.audioSink.enableTunnelingV21(i);
        } else {
            this.audioSink.disableTunneling();
        }
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.audioSink.reset();
        this.currentPositionUs = j;
        this.allowFirstBufferPositionDiscontinuity = true;
        this.allowPositionDiscontinuity = true;
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        if (this.decoder != null) {
            flushDecoder();
        }
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        this.audioSink.play();
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        updateCurrentPosition();
        this.audioSink.pause();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.inputFormat = null;
        this.audioTrackNeedsConfigure = true;
        this.waitingForKeys = false;
        try {
            releaseDecoder();
            this.audioSink.release();
            try {
                if (this.drmSession != null) {
                    this.drmSessionManager.releaseSession(this.drmSession);
                }
                try {
                    if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                        this.drmSessionManager.releaseSession(this.pendingDrmSession);
                    }
                } finally {
                    this.drmSession = null;
                    this.pendingDrmSession = null;
                    this.decoderCounters.ensureUpdated();
                    this.eventDispatcher.disabled(this.decoderCounters);
                }
            } catch (Throwable th) {
                if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
                throw th;
            } finally {
                this.drmSession = null;
                this.pendingDrmSession = null;
                this.decoderCounters.ensureUpdated();
                this.eventDispatcher.disabled(this.decoderCounters);
            }
        } catch (Throwable th2) {
            try {
                if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                    this.drmSessionManager.releaseSession(this.pendingDrmSession);
                }
                throw th2;
            } finally {
                this.drmSession = null;
                this.pendingDrmSession = null;
                this.decoderCounters.ensureUpdated();
                this.eventDispatcher.disabled(this.decoderCounters);
            }
        } finally {
            this.drmSession = null;
            this.pendingDrmSession = null;
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (i != 5) {
            switch (i) {
                case 2:
                    this.audioSink.setVolume(((Float) obj).floatValue());
                    return;
                case 3:
                    this.audioSink.setAudioAttributes((AudioAttributes) obj);
                    return;
                default:
                    super.handleMessage(i, obj);
                    return;
            }
        } else {
            this.audioSink.setAuxEffectInfo((AuxEffectInfo) obj);
        }
    }

    private void maybeInitDecoder() throws ExoPlaybackException {
        if (this.decoder == null) {
            this.drmSession = this.pendingDrmSession;
            ExoMediaCrypto exoMediaCrypto = null;
            DrmSession<ExoMediaCrypto> drmSession2 = this.drmSession;
            if (drmSession2 != null) {
                exoMediaCrypto = drmSession2.getMediaCrypto();
                if (exoMediaCrypto == null && this.drmSession.getError() == null) {
                    return;
                }
            }
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                TraceUtil.beginSection("createAudioDecoder");
                this.decoder = createDecoder(this.inputFormat, exoMediaCrypto);
                TraceUtil.endSection();
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                this.eventDispatcher.decoderInitialized(this.decoder.getName(), elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
                this.decoderCounters.decoderInitCount++;
            } catch (AudioDecoderException e) {
                throw ExoPlaybackException.createForRenderer(e, getIndex());
            }
        }
    }

    private void releaseDecoder() {
        SimpleDecoder<DecoderInputBuffer, ? extends SimpleOutputBuffer, ? extends AudioDecoderException> simpleDecoder = this.decoder;
        if (simpleDecoder != null) {
            this.inputBuffer = null;
            this.outputBuffer = null;
            simpleDecoder.release();
            this.decoder = null;
            this.decoderCounters.decoderReleaseCount++;
            this.decoderReinitializationState = 0;
            this.decoderReceivedBuffers = false;
        }
    }

    private void onInputFormatChanged(Format format) throws ExoPlaybackException {
        Format format2 = this.inputFormat;
        this.inputFormat = format;
        if (!Util.areEqual(this.inputFormat.drmInitData, format2 == null ? null : format2.drmInitData)) {
            if (this.inputFormat.drmInitData != null) {
                DrmSessionManager<ExoMediaCrypto> drmSessionManager2 = this.drmSessionManager;
                if (drmSessionManager2 != null) {
                    this.pendingDrmSession = drmSessionManager2.acquireSession(Looper.myLooper(), this.inputFormat.drmInitData);
                    DrmSession<ExoMediaCrypto> drmSession2 = this.pendingDrmSession;
                    if (drmSession2 == this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession2);
                    }
                } else {
                    throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
                }
            } else {
                this.pendingDrmSession = null;
            }
        }
        if (this.decoderReceivedBuffers) {
            this.decoderReinitializationState = 1;
        } else {
            releaseDecoder();
            maybeInitDecoder();
            this.audioTrackNeedsConfigure = true;
        }
        this.encoderDelay = format.encoderDelay;
        this.encoderPadding = format.encoderPadding;
        this.eventDispatcher.inputFormatChanged(format);
    }

    private void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (this.allowFirstBufferPositionDiscontinuity && !decoderInputBuffer.isDecodeOnly()) {
            if (Math.abs(decoderInputBuffer.timeUs - this.currentPositionUs) > 500000) {
                this.currentPositionUs = decoderInputBuffer.timeUs;
            }
            this.allowFirstBufferPositionDiscontinuity = false;
        }
    }

    private void updateCurrentPosition() {
        long currentPositionUs2 = this.audioSink.getCurrentPositionUs(isEnded());
        if (currentPositionUs2 != Long.MIN_VALUE) {
            if (!this.allowPositionDiscontinuity) {
                currentPositionUs2 = Math.max(this.currentPositionUs, currentPositionUs2);
            }
            this.currentPositionUs = currentPositionUs2;
            this.allowPositionDiscontinuity = false;
        }
    }
}
