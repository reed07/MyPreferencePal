package com.google.android.exoplayer2.video;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCodec.OnFrameRenderedListener;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;
import com.google.android.exoplayer2.mediacodec.MediaFormatUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import com.myfitnesspal.shared.constants.Constants.Exercise.Cardio;
import com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns;
import java.nio.ByteBuffer;
import java.util.List;

@TargetApi(16)
public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final int MAX_PENDING_OUTPUT_STREAM_OFFSET_COUNT = 10;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, Cardio.MINUTES_PERFORMED_DAILY_LIMIT, 1280, 960, 854, AmazonAdTask.DEFAULT_AD_WIDTH, 540, AmazonAdTask.DEFAULT_AD_HEIGHT};
    private static final String TAG = "MediaCodecVideoRenderer";
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private Surface dummySurface;
    private final EventDispatcher eventDispatcher;
    @Nullable
    private VideoFrameMetadataListener frameMetadataListener;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private long lastInputTimeUs;
    private long lastRenderTimeUs;
    private final int maxDroppedFramesToNotify;
    private long outputStreamOffsetUs;
    private int pendingOutputStreamOffsetCount;
    private final long[] pendingOutputStreamOffsetsUs;
    private final long[] pendingOutputStreamSwitchTimesUs;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private int reportedHeight;
    private float reportedPixelWidthHeightRatio;
    private int reportedUnappliedRotationDegrees;
    private int reportedWidth;
    private int scalingMode;
    private Surface surface;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;

    protected static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    @TargetApi(23)
    private final class OnFrameRenderedListenerV23 implements OnFrameRenderedListener {
        private OnFrameRenderedListenerV23(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }

        public void onFrameRendered(@NonNull MediaCodec mediaCodec, long j, long j2) {
            if (this == MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener) {
                MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(j);
            }
        }
    }

    private static boolean isBufferLate(long j) {
        return j < -30000;
    }

    private static boolean isBufferVeryLate(long j) {
        return j < -500000;
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector) {
        this(context2, mediaCodecSelector, 0);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j) {
        this(context2, mediaCodecSelector, j, null, null, -1);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, mediaCodecSelector, j, null, false, handler, videoRendererEventListener, i);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, @Nullable Handler handler, @Nullable VideoRendererEventListener videoRendererEventListener, int i) {
        super(2, mediaCodecSelector, drmSessionManager, z, 30.0f);
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        this.context = context2.getApplicationContext();
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(this.context);
        this.eventDispatcher = new EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.pendingOutputStreamOffsetsUs = new long[10];
        this.pendingOutputStreamSwitchTimesUs = new long[10];
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastInputTimeUs = -9223372036854775807L;
        this.joiningDeadlineMs = -9223372036854775807L;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        clearReportedVideoSize();
    }

    /* access modifiers changed from: protected */
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format) throws DecoderQueryException {
        boolean z;
        int i = 0;
        if (!MimeTypes.isVideo(format.sampleMimeType)) {
            return 0;
        }
        DrmInitData drmInitData = format.drmInitData;
        if (drmInitData != null) {
            z = false;
            for (int i2 = 0; i2 < drmInitData.schemeDataCount; i2++) {
                z |= drmInitData.get(i2).requiresSecureDecryption;
            }
        } else {
            z = false;
        }
        List decoderInfos = mediaCodecSelector.getDecoderInfos(format.sampleMimeType, z);
        int i3 = 2;
        if (decoderInfos.isEmpty()) {
            if (!z || mediaCodecSelector.getDecoderInfos(format.sampleMimeType, false).isEmpty()) {
                i3 = 1;
            }
            return i3;
        } else if (!supportsFormatDrm(drmSessionManager, drmInitData)) {
            return 2;
        } else {
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) decoderInfos.get(0);
            boolean isFormatSupported = mediaCodecInfo.isFormatSupported(format);
            int i4 = mediaCodecInfo.isSeamlessAdaptationSupported(format) ? 16 : 8;
            if (mediaCodecInfo.tunneling) {
                i = 32;
            }
            return (isFormatSupported ? 4 : 3) | i4 | i;
        }
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z) throws ExoPlaybackException {
        super.onEnabled(z);
        this.tunnelingAudioSessionId = getConfiguration().tunnelingAudioSessionId;
        this.tunneling = this.tunnelingAudioSessionId != 0;
        this.eventDispatcher.enabled(this.decoderCounters);
        this.frameReleaseTimeHelper.enable();
    }

    /* access modifiers changed from: protected */
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        if (this.outputStreamOffsetUs == -9223372036854775807L) {
            this.outputStreamOffsetUs = j;
        } else {
            int i = this.pendingOutputStreamOffsetCount;
            if (i == this.pendingOutputStreamOffsetsUs.length) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Too many stream changes, so dropping offset: ");
                sb.append(this.pendingOutputStreamOffsetsUs[this.pendingOutputStreamOffsetCount - 1]);
                Log.w(str, sb.toString());
            } else {
                this.pendingOutputStreamOffsetCount = i + 1;
            }
            long[] jArr = this.pendingOutputStreamOffsetsUs;
            int i2 = this.pendingOutputStreamOffsetCount;
            jArr[i2 - 1] = j;
            this.pendingOutputStreamSwitchTimesUs[i2 - 1] = this.lastInputTimeUs;
        }
        super.onStreamChanged(formatArr, j);
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        clearRenderedFirstFrame();
        this.initialPositionUs = -9223372036854775807L;
        this.consecutiveDroppedFrameCount = 0;
        this.lastInputTimeUs = -9223372036854775807L;
        int i = this.pendingOutputStreamOffsetCount;
        if (i != 0) {
            this.outputStreamOffsetUs = this.pendingOutputStreamOffsetsUs[i - 1];
            this.pendingOutputStreamOffsetCount = 0;
        }
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = -9223372036854775807L;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r9.tunneling != false) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        if (r9.surface == r0) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isReady() {
        /*
            r9 = this;
            boolean r0 = super.isReady()
            r1 = 1
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r0 == 0) goto L_0x0025
            boolean r0 = r9.renderedFirstFrame
            if (r0 != 0) goto L_0x0022
            android.view.Surface r0 = r9.dummySurface
            if (r0 == 0) goto L_0x0018
            android.view.Surface r4 = r9.surface
            if (r4 == r0) goto L_0x0022
        L_0x0018:
            android.media.MediaCodec r0 = r9.getCodec()
            if (r0 == 0) goto L_0x0022
            boolean r0 = r9.tunneling
            if (r0 == 0) goto L_0x0025
        L_0x0022:
            r9.joiningDeadlineMs = r2
            return r1
        L_0x0025:
            long r4 = r9.joiningDeadlineMs
            r0 = 0
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 != 0) goto L_0x002d
            return r0
        L_0x002d:
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r6 = r9.joiningDeadlineMs
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0038
            return r1
        L_0x0038:
            r9.joiningDeadlineMs = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.isReady():boolean");
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        this.joiningDeadlineMs = -9223372036854775807L;
        maybeNotifyDroppedFrames();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.outputStreamOffsetUs = -9223372036854775807L;
        this.lastInputTimeUs = -9223372036854775807L;
        this.pendingOutputStreamOffsetCount = 0;
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.frameReleaseTimeHelper.disable();
        this.tunnelingOnFrameRenderedListener = null;
        this.tunneling = false;
        try {
            super.onDisabled();
        } finally {
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    public void handleMessage(int i, @Nullable Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
        } else if (i == 4) {
            this.scalingMode = ((Integer) obj).intValue();
            MediaCodec codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
            }
        } else if (i == 6) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        } else {
            super.handleMessage(i, obj);
        }
    }

    private void setSurface(Surface surface2) throws ExoPlaybackException {
        if (surface2 == null) {
            Surface surface3 = this.dummySurface;
            if (surface3 != null) {
                surface2 = surface3;
            } else {
                MediaCodecInfo codecInfo = getCodecInfo();
                if (codecInfo != null && shouldUseDummySurface(codecInfo)) {
                    this.dummySurface = DummySurface.newInstanceV17(this.context, codecInfo.secure);
                    surface2 = this.dummySurface;
                }
            }
        }
        if (this.surface != surface2) {
            this.surface = surface2;
            int state = getState();
            if (state == 1 || state == 2) {
                MediaCodec codec = getCodec();
                if (Util.SDK_INT < 23 || codec == null || surface2 == null || this.codecNeedsSetOutputSurfaceWorkaround) {
                    releaseCodec();
                    maybeInitCodec();
                } else {
                    setOutputSurfaceV23(codec, surface2);
                }
            }
            if (surface2 == null || surface2 == this.dummySurface) {
                clearReportedVideoSize();
                clearRenderedFirstFrame();
                return;
            }
            maybeRenotifyVideoSizeChanged();
            clearRenderedFirstFrame();
            if (state == 2) {
                setJoiningDeadlineMs();
            }
        } else if (surface2 != null && surface2 != this.dummySurface) {
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUseDummySurface(mediaCodecInfo);
    }

    /* access modifiers changed from: protected */
    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling;
    }

    /* access modifiers changed from: protected */
    public void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto, float f) throws DecoderQueryException {
        this.codecMaxValues = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        MediaFormat mediaFormat = getMediaFormat(format, this.codecMaxValues, f, this.deviceNeedsNoPostProcessWorkaround, this.tunnelingAudioSessionId);
        if (this.surface == null) {
            Assertions.checkState(shouldUseDummySurface(mediaCodecInfo));
            if (this.dummySurface == null) {
                this.dummySurface = DummySurface.newInstanceV17(this.context, mediaCodecInfo.secure);
            }
            this.surface = this.dummySurface;
        }
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
        if (Util.SDK_INT >= 23 && this.tunneling) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(mediaCodec);
        }
    }

    /* access modifiers changed from: protected */
    public int canKeepCodec(MediaCodec mediaCodec, MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        int i = 1;
        if (!mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, true) || format2.width > this.codecMaxValues.width || format2.height > this.codecMaxValues.height || getMaxInputSize(mediaCodecInfo, format2) > this.codecMaxValues.inputSize) {
            return 0;
        }
        if (!format.initializationDataEquals(format2)) {
            i = 3;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void releaseCodec() {
        try {
            super.releaseCodec();
        } finally {
            this.buffersInCodecCount = 0;
            Surface surface2 = this.dummySurface;
            if (surface2 != null) {
                if (this.surface == surface2) {
                    this.surface = null;
                }
                this.dummySurface.release();
                this.dummySurface = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void flushCodec() throws ExoPlaybackException {
        super.flushCodec();
        this.buffersInCodecCount = 0;
    }

    /* access modifiers changed from: protected */
    public float getCodecOperatingRate(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.frameRate;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
    }

    /* access modifiers changed from: protected */
    public void onInputFormatChanged(Format format) throws ExoPlaybackException {
        super.onInputFormatChanged(format);
        this.eventDispatcher.inputFormatChanged(format);
        this.pendingPixelWidthHeightRatio = format.pixelWidthHeightRatio;
        this.pendingRotationDegrees = format.rotationDegrees;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        this.buffersInCodecCount++;
        this.lastInputTimeUs = Math.max(decoderInputBuffer.timeUs, this.lastInputTimeUs);
        if (Util.SDK_INT < 23 && this.tunneling) {
            onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
        }
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int i;
        int i2;
        boolean z = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
        if (z) {
            i = (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1;
        } else {
            i = mediaFormat.getInteger("width");
        }
        if (z) {
            i2 = (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1;
        } else {
            i2 = mediaFormat.getInteger("height");
        }
        processOutputFormat(mediaCodec, i, i2);
    }

    /* access modifiers changed from: protected */
    public boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z, Format format) throws ExoPlaybackException {
        boolean z2;
        long j4 = j;
        long j5 = j2;
        MediaCodec mediaCodec2 = mediaCodec;
        int i3 = i;
        long j6 = j3;
        if (this.initialPositionUs == -9223372036854775807L) {
            this.initialPositionUs = j4;
        }
        long j7 = j6 - this.outputStreamOffsetUs;
        if (z) {
            skipOutputBuffer(mediaCodec2, i3, j7);
            return true;
        }
        long j8 = j6 - j4;
        if (this.surface != this.dummySurface) {
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            boolean z3 = getState() == 2;
            if (!this.renderedFirstFrame || (z3 && shouldForceRenderOutputBuffer(j8, elapsedRealtime - this.lastRenderTimeUs))) {
                long nanoTime = System.nanoTime();
                notifyFrameMetadataListener(j7, nanoTime, format);
                if (Util.SDK_INT >= 21) {
                    renderOutputBufferV21(mediaCodec, i, j7, nanoTime);
                    z2 = true;
                } else {
                    renderOutputBuffer(mediaCodec2, i3, j7);
                    z2 = true;
                }
                return z2;
            } else if (!z3 || j4 == this.initialPositionUs) {
                return false;
            } else {
                long j9 = j8 - (elapsedRealtime - j5);
                long nanoTime2 = System.nanoTime();
                long adjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(j6, (j9 * 1000) + nanoTime2);
                long j10 = (adjustReleaseTime - nanoTime2) / 1000;
                if (shouldDropBuffersToKeyframe(j10, j5) && maybeDropBuffersToKeyframe(mediaCodec, i, j7, j)) {
                    return false;
                }
                if (shouldDropOutputBuffer(j10, j5)) {
                    dropOutputBuffer(mediaCodec2, i3, j7);
                    return true;
                }
                if (Util.SDK_INT >= 21) {
                    if (j10 < 50000) {
                        notifyFrameMetadataListener(j7, adjustReleaseTime, format);
                        renderOutputBufferV21(mediaCodec, i, j7, adjustReleaseTime);
                        return true;
                    }
                } else if (j10 < 30000) {
                    if (j10 > 11000) {
                        try {
                            Thread.sleep((j10 - 10000) / 1000);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                            return false;
                        }
                    }
                    notifyFrameMetadataListener(j7, adjustReleaseTime, format);
                    renderOutputBuffer(mediaCodec2, i3, j7);
                    return true;
                }
                return false;
            }
        } else if (!isBufferLate(j8)) {
            return false;
        } else {
            skipOutputBuffer(mediaCodec2, i3, j7);
            return true;
        }
    }

    private void processOutputFormat(MediaCodec mediaCodec, int i, int i2) {
        this.currentWidth = i;
        this.currentHeight = i2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if (Util.SDK_INT >= 21) {
            int i3 = this.pendingRotationDegrees;
            if (i3 == 90 || i3 == 270) {
                int i4 = this.currentWidth;
                this.currentWidth = this.currentHeight;
                this.currentHeight = i4;
                this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
            }
        } else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }
        mediaCodec.setVideoScalingMode(this.scalingMode);
    }

    private void notifyFrameMetadataListener(long j, long j2, Format format) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format);
        }
    }

    /* access modifiers changed from: protected */
    public long getOutputStreamOffsetUs() {
        return this.outputStreamOffsetUs;
    }

    /* access modifiers changed from: protected */
    public void onProcessedTunneledBuffer(long j) {
        Format updateOutputFormatForTime = updateOutputFormatForTime(j);
        if (updateOutputFormatForTime != null) {
            processOutputFormat(getCodec(), updateOutputFormatForTime.width, updateOutputFormatForTime.height);
        }
        maybeNotifyVideoSizeChanged();
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onProcessedOutputBuffer(long j) {
        this.buffersInCodecCount--;
        while (true) {
            int i = this.pendingOutputStreamOffsetCount;
            if (i != 0 && j >= this.pendingOutputStreamSwitchTimesUs[0]) {
                long[] jArr = this.pendingOutputStreamOffsetsUs;
                this.outputStreamOffsetUs = jArr[0];
                this.pendingOutputStreamOffsetCount = i - 1;
                System.arraycopy(jArr, 1, jArr, 0, this.pendingOutputStreamOffsetCount);
                long[] jArr2 = this.pendingOutputStreamSwitchTimesUs;
                System.arraycopy(jArr2, 1, jArr2, 0, this.pendingOutputStreamOffsetCount);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropOutputBuffer(long j, long j2) {
        return isBufferLate(j);
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2) {
        return isBufferVeryLate(j);
    }

    /* access modifiers changed from: protected */
    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return isBufferLate(j) && j2 > 100000;
    }

    /* access modifiers changed from: protected */
    public void skipOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    /* access modifiers changed from: protected */
    public void dropOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(1);
    }

    /* access modifiers changed from: protected */
    public boolean maybeDropBuffersToKeyframe(MediaCodec mediaCodec, int i, long j, long j2) throws ExoPlaybackException {
        int skipSource = skipSource(j2);
        if (skipSource == 0) {
            return false;
        }
        this.decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(this.buffersInCodecCount + skipSource);
        flushCodec();
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateDroppedBufferCounters(int i) {
        this.decoderCounters.droppedBufferCount += i;
        this.droppedFrames += i;
        this.consecutiveDroppedFrameCount += i;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        int i2 = this.maxDroppedFramesToNotify;
        if (i2 > 0 && this.droppedFrames >= i2) {
            maybeNotifyDroppedFrames();
        }
    }

    /* access modifiers changed from: protected */
    public void renderOutputBuffer(MediaCodec mediaCodec, int i, long j) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public void renderOutputBufferV21(MediaCodec mediaCodec, int i, long j, long j2) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.lastRenderTimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    private boolean shouldUseDummySurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || DummySurface.isSecureSupported(this.context));
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : -9223372036854775807L;
    }

    private void clearRenderedFirstFrame() {
        this.renderedFirstFrame = false;
        if (Util.SDK_INT >= 23 && this.tunneling) {
            MediaCodec codec = getCodec();
            if (codec != null) {
                this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void maybeNotifyRenderedFirstFrame() {
        if (!this.renderedFirstFrame) {
            this.renderedFirstFrame = true;
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void clearReportedVideoSize() {
        this.reportedWidth = -1;
        this.reportedHeight = -1;
        this.reportedPixelWidthHeightRatio = -1.0f;
        this.reportedUnappliedRotationDegrees = -1;
    }

    private void maybeNotifyVideoSizeChanged() {
        if (this.currentWidth != -1 || this.currentHeight != -1) {
            if (this.reportedWidth != this.currentWidth || this.reportedHeight != this.currentHeight || this.reportedUnappliedRotationDegrees != this.currentUnappliedRotationDegrees || this.reportedPixelWidthHeightRatio != this.currentPixelWidthHeightRatio) {
                this.eventDispatcher.videoSizeChanged(this.currentWidth, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
                this.reportedWidth = this.currentWidth;
                this.reportedHeight = this.currentHeight;
                this.reportedUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
                this.reportedPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
            }
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        if (this.reportedWidth != -1 || this.reportedHeight != -1) {
            this.eventDispatcher.videoSizeChanged(this.reportedWidth, this.reportedHeight, this.reportedUnappliedRotationDegrees, this.reportedPixelWidthHeightRatio);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    @TargetApi(23)
    private static void setOutputSurfaceV23(MediaCodec mediaCodec, Surface surface2) {
        mediaCodec.setOutputSurface(surface2);
    }

    @TargetApi(21)
    private static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"InlinedApi"})
    public MediaFormat getMediaFormat(Format format, CodecMaxValues codecMaxValues2, float f, boolean z, int i) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", format.sampleMimeType);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        mediaFormat.setInteger("max-width", codecMaxValues2.width);
        mediaFormat.setInteger("max-height", codecMaxValues2.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues2.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger(Columns.PRIORITY, 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(mediaFormat, i);
        }
        return mediaFormat;
    }

    /* access modifiers changed from: protected */
    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) throws DecoderQueryException {
        int i = format.width;
        int i2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (maxInputSize != -1) {
                int codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height);
                if (codecMaxInputSize != -1) {
                    maxInputSize = Math.min((int) (((float) maxInputSize) * 1.5f), codecMaxInputSize);
                }
            }
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        int i3 = i2;
        int i4 = maxInputSize;
        boolean z = false;
        int i5 = i;
        for (Format format2 : formatArr) {
            if (mediaCodecInfo.isSeamlessAdaptationSupported(format, format2, false)) {
                z |= format2.width == -1 || format2.height == -1;
                i5 = Math.max(i5, format2.width);
                i3 = Math.max(i3, format2.height);
                i4 = Math.max(i4, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Resolutions unknown. Codec max resolution: ");
            sb.append(i5);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(i3);
            Log.w(str, sb.toString());
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                i5 = Math.max(i5, codecMaxSize.x);
                i3 = Math.max(i3, codecMaxSize.y);
                i4 = Math.max(i4, getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, i5, i3));
                String str2 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Codec max resolution adjusted to: ");
                sb2.append(i5);
                sb2.append(AvidJSONUtil.KEY_X);
                sb2.append(i3);
                Log.w(str2, sb2.toString());
            }
        }
        return new CodecMaxValues(i5, i3, i4);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) throws DecoderQueryException {
        int[] iArr;
        boolean z = format.height > format.width;
        int i = z ? format.height : format.width;
        int i2 = z ? format.width : format.height;
        float f = ((float) i2) / ((float) i);
        for (int i3 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i4 = (int) (((float) i3) * f);
            if (i3 <= i || i4 <= i2) {
                return null;
            }
            if (Util.SDK_INT >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i5, i3);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) format.frameRate)) {
                    return alignVideoSizeV21;
                }
            } else {
                int ceilDivide = Util.ceilDivide(i3, 16) * 16;
                int ceilDivide2 = Util.ceilDivide(i4, 16) * 16;
                if (ceilDivide * ceilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                    int i6 = z ? ceilDivide2 : ceilDivide;
                    if (z) {
                        ceilDivide2 = ceilDivide;
                    }
                    return new Point(i6, ceilDivide2);
                }
            }
        }
        return null;
    }

    private static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format.sampleMimeType, format.width, format.height);
        }
        int i = 0;
        for (int i2 = 0; i2 < format.initializationData.size(); i2++) {
            i += ((byte[]) format.initializationData.get(i2)).length;
        }
        return format.maxInputSize + i;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int getCodecMaxInputSize(com.google.android.exoplayer2.mediacodec.MediaCodecInfo r5, java.lang.String r6, int r7, int r8) {
        /*
            r0 = -1
            if (r7 == r0) goto L_0x00a3
            if (r8 != r0) goto L_0x0007
            goto L_0x00a3
        L_0x0007:
            int r1 = r6.hashCode()
            r2 = 3
            r3 = 4
            r4 = 2
            switch(r1) {
                case -1664118616: goto L_0x0044;
                case -1662541442: goto L_0x003a;
                case 1187890754: goto L_0x0030;
                case 1331836730: goto L_0x0026;
                case 1599127256: goto L_0x001c;
                case 1599127257: goto L_0x0012;
                default: goto L_0x0011;
            }
        L_0x0011:
            goto L_0x004e
        L_0x0012:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x004e
            r6 = 5
            goto L_0x004f
        L_0x001c:
            java.lang.String r1 = "video/x-vnd.on2.vp8"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x004e
            r6 = 3
            goto L_0x004f
        L_0x0026:
            java.lang.String r1 = "video/avc"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x004e
            r6 = 2
            goto L_0x004f
        L_0x0030:
            java.lang.String r1 = "video/mp4v-es"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x004e
            r6 = 1
            goto L_0x004f
        L_0x003a:
            java.lang.String r1 = "video/hevc"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x004e
            r6 = 4
            goto L_0x004f
        L_0x0044:
            java.lang.String r1 = "video/3gpp"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x004e
            r6 = 0
            goto L_0x004f
        L_0x004e:
            r6 = -1
        L_0x004f:
            switch(r6) {
                case 0: goto L_0x009a;
                case 1: goto L_0x009a;
                case 2: goto L_0x005a;
                case 3: goto L_0x0056;
                case 4: goto L_0x0053;
                case 5: goto L_0x0053;
                default: goto L_0x0052;
            }
        L_0x0052:
            return r0
        L_0x0053:
            int r7 = r7 * r8
            goto L_0x009d
        L_0x0056:
            int r7 = r7 * r8
            r3 = 2
            goto L_0x009d
        L_0x005a:
            java.lang.String r6 = "BRAVIA 4K 2015"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x0099
            java.lang.String r6 = "Amazon"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MANUFACTURER
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0087
            java.lang.String r6 = "KFSOWI"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r6 = r6.equals(r1)
            if (r6 != 0) goto L_0x0099
            java.lang.String r6 = "AFTS"
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0087
            boolean r5 = r5.secure
            if (r5 == 0) goto L_0x0087
            goto L_0x0099
        L_0x0087:
            r5 = 16
            int r6 = com.google.android.exoplayer2.util.Util.ceilDivide(r7, r5)
            int r7 = com.google.android.exoplayer2.util.Util.ceilDivide(r8, r5)
            int r6 = r6 * r7
            int r6 = r6 * 16
            int r7 = r6 * 16
            r3 = 2
            goto L_0x009d
        L_0x0099:
            return r0
        L_0x009a:
            int r7 = r7 * r8
            r3 = 2
        L_0x009d:
            int r7 = r7 * 3
            int r3 = r3 * 2
            int r7 = r7 / r3
            return r7
        L_0x00a3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.getCodecMaxInputSize(com.google.android.exoplayer2.mediacodec.MediaCodecInfo, java.lang.String, int, int):int");
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:393:0x0601, code lost:
        r1 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:394:0x0602, code lost:
        switch(r1) {
            case 0: goto L_0x0606;
            case 1: goto L_0x0606;
            case 2: goto L_0x0606;
            case 3: goto L_0x0606;
            case 4: goto L_0x0606;
            case 5: goto L_0x0606;
            case 6: goto L_0x0606;
            case 7: goto L_0x0606;
            case 8: goto L_0x0606;
            case 9: goto L_0x0606;
            case 10: goto L_0x0606;
            case 11: goto L_0x0606;
            case 12: goto L_0x0606;
            case 13: goto L_0x0606;
            case 14: goto L_0x0606;
            case 15: goto L_0x0606;
            case 16: goto L_0x0606;
            case 17: goto L_0x0606;
            case 18: goto L_0x0606;
            case 19: goto L_0x0606;
            case 20: goto L_0x0606;
            case 21: goto L_0x0606;
            case 22: goto L_0x0606;
            case 23: goto L_0x0606;
            case 24: goto L_0x0606;
            case 25: goto L_0x0606;
            case 26: goto L_0x0606;
            case 27: goto L_0x0606;
            case 28: goto L_0x0606;
            case 29: goto L_0x0606;
            case 30: goto L_0x0606;
            case 31: goto L_0x0606;
            case 32: goto L_0x0606;
            case 33: goto L_0x0606;
            case 34: goto L_0x0606;
            case 35: goto L_0x0606;
            case 36: goto L_0x0606;
            case 37: goto L_0x0606;
            case 38: goto L_0x0606;
            case 39: goto L_0x0606;
            case 40: goto L_0x0606;
            case 41: goto L_0x0606;
            case 42: goto L_0x0606;
            case 43: goto L_0x0606;
            case 44: goto L_0x0606;
            case 45: goto L_0x0606;
            case 46: goto L_0x0606;
            case 47: goto L_0x0606;
            case 48: goto L_0x0606;
            case 49: goto L_0x0606;
            case 50: goto L_0x0606;
            case 51: goto L_0x0606;
            case 52: goto L_0x0606;
            case 53: goto L_0x0606;
            case 54: goto L_0x0606;
            case 55: goto L_0x0606;
            case 56: goto L_0x0606;
            case 57: goto L_0x0606;
            case 58: goto L_0x0606;
            case 59: goto L_0x0606;
            case 60: goto L_0x0606;
            case 61: goto L_0x0606;
            case 62: goto L_0x0606;
            case 63: goto L_0x0606;
            case 64: goto L_0x0606;
            case 65: goto L_0x0606;
            case 66: goto L_0x0606;
            case 67: goto L_0x0606;
            case 68: goto L_0x0606;
            case 69: goto L_0x0606;
            case 70: goto L_0x0606;
            case 71: goto L_0x0606;
            case 72: goto L_0x0606;
            case 73: goto L_0x0606;
            case 74: goto L_0x0606;
            case 75: goto L_0x0606;
            case 76: goto L_0x0606;
            case 77: goto L_0x0606;
            case 78: goto L_0x0606;
            case 79: goto L_0x0606;
            case 80: goto L_0x0606;
            case 81: goto L_0x0606;
            case 82: goto L_0x0606;
            case 83: goto L_0x0606;
            case 84: goto L_0x0606;
            case 85: goto L_0x0606;
            case 86: goto L_0x0606;
            case 87: goto L_0x0606;
            case 88: goto L_0x0606;
            case 89: goto L_0x0606;
            case 90: goto L_0x0606;
            case 91: goto L_0x0606;
            case 92: goto L_0x0606;
            case 93: goto L_0x0606;
            case 94: goto L_0x0606;
            case 95: goto L_0x0606;
            case 96: goto L_0x0606;
            case 97: goto L_0x0606;
            case 98: goto L_0x0606;
            case 99: goto L_0x0606;
            case 100: goto L_0x0606;
            case 101: goto L_0x0606;
            case 102: goto L_0x0606;
            case 103: goto L_0x0606;
            case 104: goto L_0x0606;
            case 105: goto L_0x0606;
            case 106: goto L_0x0606;
            case 107: goto L_0x0606;
            case 108: goto L_0x0606;
            case 109: goto L_0x0606;
            case 110: goto L_0x0606;
            case 111: goto L_0x0606;
            case 112: goto L_0x0606;
            case 113: goto L_0x0606;
            case 114: goto L_0x0606;
            case 115: goto L_0x0606;
            case 116: goto L_0x0606;
            case 117: goto L_0x0606;
            case 118: goto L_0x0606;
            case 119: goto L_0x0606;
            case 120: goto L_0x0606;
            case com.myfitnesspal.shared.service.syncv1.PacketTypes.RetrieveFriendList :int: goto L_0x0606;
            case 122: goto L_0x0606;
            case 123: goto L_0x0606;
            default: goto L_0x0605;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:396:0x0606, code lost:
        deviceNeedsSetOutputSurfaceWorkaround = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:397:0x0608, code lost:
        r1 = com.google.android.exoplayer2.util.Util.MODEL;
        r2 = r1.hashCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:398:0x0611, code lost:
        if (r2 == 2006354) goto L_0x0623;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:400:0x0616, code lost:
        if (r2 == 2006367) goto L_0x0619;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:403:0x061f, code lost:
        if (r1.equals("AFTN") == false) goto L_0x062c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:404:0x0621, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x0629, code lost:
        if (r1.equals("AFTA") == false) goto L_0x062c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:408:0x062c, code lost:
        r0 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x062d, code lost:
        switch(r0) {
            case 0: goto L_0x0631;
            case 1: goto L_0x0631;
            default: goto L_0x0630;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:411:0x0631, code lost:
        deviceNeedsSetOutputSurfaceWorkaround = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean codecNeedsSetOutputSurfaceWorkaround(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "OMX.google"
            boolean r7 = r7.startsWith(r0)
            r0 = 0
            if (r7 == 0) goto L_0x000a
            return r0
        L_0x000a:
            java.lang.Class<com.google.android.exoplayer2.video.MediaCodecVideoRenderer> r7 = com.google.android.exoplayer2.video.MediaCodecVideoRenderer.class
            monitor-enter(r7)
            boolean r1 = evaluatedDeviceNeedsSetOutputSurfaceWorkaround     // Catch:{ all -> 0x0639 }
            if (r1 != 0) goto L_0x0635
            int r1 = com.google.android.exoplayer2.util.Util.SDK_INT     // Catch:{ all -> 0x0639 }
            r2 = 27
            r3 = 1
            if (r1 > r2) goto L_0x0030
            java.lang.String r1 = "dangal"
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.DEVICE     // Catch:{ all -> 0x0639 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0639 }
            if (r1 != 0) goto L_0x002c
            java.lang.String r1 = "HWEML"
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.DEVICE     // Catch:{ all -> 0x0639 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0030
        L_0x002c:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0639 }
            goto L_0x0633
        L_0x0030:
            int r1 = com.google.android.exoplayer2.util.Util.SDK_INT     // Catch:{ all -> 0x0639 }
            if (r1 < r2) goto L_0x0036
            goto L_0x0633
        L_0x0036:
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.DEVICE     // Catch:{ all -> 0x0639 }
            int r4 = r1.hashCode()     // Catch:{ all -> 0x0639 }
            r5 = -1
            switch(r4) {
                case -2144781245: goto L_0x05f6;
                case -2144781185: goto L_0x05eb;
                case -2144781160: goto L_0x05e0;
                case -2097309513: goto L_0x05d5;
                case -2022874474: goto L_0x05ca;
                case -1978993182: goto L_0x05bf;
                case -1978990237: goto L_0x05b4;
                case -1936688988: goto L_0x05a9;
                case -1936688066: goto L_0x059e;
                case -1936688065: goto L_0x0592;
                case -1931988508: goto L_0x0586;
                case -1696512866: goto L_0x057a;
                case -1680025915: goto L_0x056e;
                case -1615810839: goto L_0x0562;
                case -1554255044: goto L_0x0556;
                case -1481772737: goto L_0x054a;
                case -1481772730: goto L_0x053e;
                case -1481772729: goto L_0x0532;
                case -1320080169: goto L_0x0526;
                case -1217592143: goto L_0x051a;
                case -1180384755: goto L_0x050e;
                case -1139198265: goto L_0x0502;
                case -1052835013: goto L_0x04f6;
                case -993250464: goto L_0x04eb;
                case -965403638: goto L_0x04df;
                case -958336948: goto L_0x04d3;
                case -879245230: goto L_0x04c7;
                case -842500323: goto L_0x04bb;
                case -821392978: goto L_0x04b0;
                case -797483286: goto L_0x04a4;
                case -794946968: goto L_0x0498;
                case -788334647: goto L_0x048c;
                case -782144577: goto L_0x0480;
                case -575125681: goto L_0x0474;
                case -521118391: goto L_0x0468;
                case -430914369: goto L_0x045c;
                case -290434366: goto L_0x0450;
                case -282781963: goto L_0x0444;
                case -277133239: goto L_0x0438;
                case -173639913: goto L_0x042c;
                case -56598463: goto L_0x0420;
                case 2126: goto L_0x0414;
                case 2564: goto L_0x0408;
                case 2715: goto L_0x03fc;
                case 2719: goto L_0x03f0;
                case 3483: goto L_0x03e4;
                case 73405: goto L_0x03d8;
                case 75739: goto L_0x03cc;
                case 76779: goto L_0x03c0;
                case 78669: goto L_0x03b4;
                case 79305: goto L_0x03a8;
                case 80618: goto L_0x039c;
                case 88274: goto L_0x0390;
                case 98846: goto L_0x0384;
                case 98848: goto L_0x0378;
                case 99329: goto L_0x036c;
                case 101481: goto L_0x0360;
                case 1513190: goto L_0x0355;
                case 1514184: goto L_0x034a;
                case 1514185: goto L_0x033f;
                case 2436959: goto L_0x0333;
                case 2463773: goto L_0x0327;
                case 2464648: goto L_0x031b;
                case 2689555: goto L_0x030f;
                case 3154429: goto L_0x0303;
                case 3284551: goto L_0x02f7;
                case 3351335: goto L_0x02eb;
                case 3386211: goto L_0x02df;
                case 41325051: goto L_0x02d3;
                case 55178625: goto L_0x02c7;
                case 61542055: goto L_0x02bc;
                case 65355429: goto L_0x02b0;
                case 66214468: goto L_0x02a4;
                case 66214470: goto L_0x0298;
                case 66214473: goto L_0x028c;
                case 66215429: goto L_0x0280;
                case 66215431: goto L_0x0274;
                case 66215433: goto L_0x0268;
                case 66216390: goto L_0x025c;
                case 76402249: goto L_0x0250;
                case 76404105: goto L_0x0244;
                case 76404911: goto L_0x0238;
                case 80963634: goto L_0x022c;
                case 82882791: goto L_0x0220;
                case 98715550: goto L_0x0214;
                case 102844228: goto L_0x0208;
                case 165221241: goto L_0x01fd;
                case 182191441: goto L_0x01f1;
                case 245388979: goto L_0x01e5;
                case 287431619: goto L_0x01d9;
                case 307593612: goto L_0x01cd;
                case 308517133: goto L_0x01c1;
                case 316215098: goto L_0x01b5;
                case 316215116: goto L_0x01a9;
                case 316246811: goto L_0x019d;
                case 316246818: goto L_0x0191;
                case 407160593: goto L_0x0185;
                case 507412548: goto L_0x0179;
                case 793982701: goto L_0x016d;
                case 794038622: goto L_0x0161;
                case 794040393: goto L_0x0155;
                case 835649806: goto L_0x0149;
                case 917340916: goto L_0x013e;
                case 958008161: goto L_0x0132;
                case 1060579533: goto L_0x0126;
                case 1150207623: goto L_0x011a;
                case 1176899427: goto L_0x010e;
                case 1280332038: goto L_0x0102;
                case 1306947716: goto L_0x00f6;
                case 1349174697: goto L_0x00ea;
                case 1522194893: goto L_0x00de;
                case 1691543273: goto L_0x00d2;
                case 1709443163: goto L_0x00c6;
                case 1865889110: goto L_0x00ba;
                case 1906253259: goto L_0x00ae;
                case 1977196784: goto L_0x00a2;
                case 2006372676: goto L_0x0096;
                case 2029784656: goto L_0x008a;
                case 2030379515: goto L_0x007e;
                case 2033393791: goto L_0x0072;
                case 2047190025: goto L_0x0066;
                case 2047252157: goto L_0x005a;
                case 2048319463: goto L_0x004e;
                case 2048855701: goto L_0x0042;
                default: goto L_0x0040;
            }     // Catch:{ all -> 0x0639 }
        L_0x0040:
            goto L_0x0601
        L_0x0042:
            java.lang.String r2 = "HWWAS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 54
            goto L_0x0602
        L_0x004e:
            java.lang.String r2 = "HWVNS-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 53
            goto L_0x0602
        L_0x005a:
            java.lang.String r4 = "ELUGA_Prim"
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 27
            goto L_0x0602
        L_0x0066:
            java.lang.String r2 = "ELUGA_Note"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 26
            goto L_0x0602
        L_0x0072:
            java.lang.String r2 = "ASUS_X00AD_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 11
            goto L_0x0602
        L_0x007e:
            java.lang.String r2 = "HWCAM-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 52
            goto L_0x0602
        L_0x008a:
            java.lang.String r2 = "HWBLN-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 51
            goto L_0x0602
        L_0x0096:
            java.lang.String r2 = "BRAVIA_ATV3_4K"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 15
            goto L_0x0602
        L_0x00a2:
            java.lang.String r2 = "Infinix-X572"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 57
            goto L_0x0602
        L_0x00ae:
            java.lang.String r2 = "PB2-670M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 85
            goto L_0x0602
        L_0x00ba:
            java.lang.String r2 = "santoni"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 101(0x65, float:1.42E-43)
            goto L_0x0602
        L_0x00c6:
            java.lang.String r2 = "iball8735_9806"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 56
            goto L_0x0602
        L_0x00d2:
            java.lang.String r2 = "CPH1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 19
            goto L_0x0602
        L_0x00de:
            java.lang.String r2 = "woods_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 117(0x75, float:1.64E-43)
            goto L_0x0602
        L_0x00ea:
            java.lang.String r2 = "htc_e56ml_dtul"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 49
            goto L_0x0602
        L_0x00f6:
            java.lang.String r2 = "EverStar_S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 29
            goto L_0x0602
        L_0x0102:
            java.lang.String r2 = "hwALE-H"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 50
            goto L_0x0602
        L_0x010e:
            java.lang.String r2 = "itel_S41"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 59
            goto L_0x0602
        L_0x011a:
            java.lang.String r2 = "LS-5017"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 65
            goto L_0x0602
        L_0x0126:
            java.lang.String r2 = "panell_d"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 81
            goto L_0x0602
        L_0x0132:
            java.lang.String r2 = "j2xlteins"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 60
            goto L_0x0602
        L_0x013e:
            java.lang.String r2 = "A7000plus"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 7
            goto L_0x0602
        L_0x0149:
            java.lang.String r2 = "manning"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 67
            goto L_0x0602
        L_0x0155:
            java.lang.String r2 = "GIONEE_WBL7519"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 47
            goto L_0x0602
        L_0x0161:
            java.lang.String r2 = "GIONEE_WBL7365"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 46
            goto L_0x0602
        L_0x016d:
            java.lang.String r2 = "GIONEE_WBL5708"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 45
            goto L_0x0602
        L_0x0179:
            java.lang.String r2 = "QM16XE_U"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 99
            goto L_0x0602
        L_0x0185:
            java.lang.String r2 = "Pixi5-10_4G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 91
            goto L_0x0602
        L_0x0191:
            java.lang.String r2 = "TB3-850M"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 109(0x6d, float:1.53E-43)
            goto L_0x0602
        L_0x019d:
            java.lang.String r2 = "TB3-850F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 108(0x6c, float:1.51E-43)
            goto L_0x0602
        L_0x01a9:
            java.lang.String r2 = "TB3-730X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x0602
        L_0x01b5:
            java.lang.String r2 = "TB3-730F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 106(0x6a, float:1.49E-43)
            goto L_0x0602
        L_0x01c1:
            java.lang.String r2 = "A7020a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 9
            goto L_0x0602
        L_0x01cd:
            java.lang.String r2 = "A7010a48"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 8
            goto L_0x0602
        L_0x01d9:
            java.lang.String r2 = "griffin"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 48
            goto L_0x0602
        L_0x01e5:
            java.lang.String r2 = "marino_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 68
            goto L_0x0602
        L_0x01f1:
            java.lang.String r2 = "CPY83_I00"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 20
            goto L_0x0602
        L_0x01fd:
            java.lang.String r2 = "A2016a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 5
            goto L_0x0602
        L_0x0208:
            java.lang.String r2 = "le_x6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 64
            goto L_0x0602
        L_0x0214:
            java.lang.String r2 = "i9031"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 55
            goto L_0x0602
        L_0x0220:
            java.lang.String r2 = "X3_HK"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 119(0x77, float:1.67E-43)
            goto L_0x0602
        L_0x022c:
            java.lang.String r2 = "V23GB"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 112(0x70, float:1.57E-43)
            goto L_0x0602
        L_0x0238:
            java.lang.String r2 = "Q4310"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 97
            goto L_0x0602
        L_0x0244:
            java.lang.String r2 = "Q4260"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 95
            goto L_0x0602
        L_0x0250:
            java.lang.String r2 = "PRO7S"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 93
            goto L_0x0602
        L_0x025c:
            java.lang.String r2 = "F3311"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 36
            goto L_0x0602
        L_0x0268:
            java.lang.String r2 = "F3215"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 35
            goto L_0x0602
        L_0x0274:
            java.lang.String r2 = "F3213"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 34
            goto L_0x0602
        L_0x0280:
            java.lang.String r2 = "F3211"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 33
            goto L_0x0602
        L_0x028c:
            java.lang.String r2 = "F3116"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 32
            goto L_0x0602
        L_0x0298:
            java.lang.String r2 = "F3113"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 31
            goto L_0x0602
        L_0x02a4:
            java.lang.String r2 = "F3111"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 30
            goto L_0x0602
        L_0x02b0:
            java.lang.String r2 = "E5643"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 24
            goto L_0x0602
        L_0x02bc:
            java.lang.String r2 = "A1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 4
            goto L_0x0602
        L_0x02c7:
            java.lang.String r2 = "Aura_Note_2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 12
            goto L_0x0602
        L_0x02d3:
            java.lang.String r2 = "MEIZU_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 69
            goto L_0x0602
        L_0x02df:
            java.lang.String r2 = "p212"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 78
            goto L_0x0602
        L_0x02eb:
            java.lang.String r2 = "mido"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 71
            goto L_0x0602
        L_0x02f7:
            java.lang.String r2 = "kate"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 63
            goto L_0x0602
        L_0x0303:
            java.lang.String r2 = "fugu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 38
            goto L_0x0602
        L_0x030f:
            java.lang.String r2 = "XE2X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x0602
        L_0x031b:
            java.lang.String r2 = "Q427"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 96
            goto L_0x0602
        L_0x0327:
            java.lang.String r2 = "Q350"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 94
            goto L_0x0602
        L_0x0333:
            java.lang.String r2 = "P681"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 79
            goto L_0x0602
        L_0x033f:
            java.lang.String r2 = "1714"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 2
            goto L_0x0602
        L_0x034a:
            java.lang.String r2 = "1713"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 1
            goto L_0x0602
        L_0x0355:
            java.lang.String r2 = "1601"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 0
            goto L_0x0602
        L_0x0360:
            java.lang.String r2 = "flo"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 37
            goto L_0x0602
        L_0x036c:
            java.lang.String r2 = "deb"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 23
            goto L_0x0602
        L_0x0378:
            java.lang.String r2 = "cv3"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 22
            goto L_0x0602
        L_0x0384:
            java.lang.String r2 = "cv1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 21
            goto L_0x0602
        L_0x0390:
            java.lang.String r2 = "Z80"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 123(0x7b, float:1.72E-43)
            goto L_0x0602
        L_0x039c:
            java.lang.String r2 = "QX1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 100
            goto L_0x0602
        L_0x03a8:
            java.lang.String r2 = "PLE"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 92
            goto L_0x0602
        L_0x03b4:
            java.lang.String r2 = "P85"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 80
            goto L_0x0602
        L_0x03c0:
            java.lang.String r2 = "MX6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 72
            goto L_0x0602
        L_0x03cc:
            java.lang.String r2 = "M5c"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 66
            goto L_0x0602
        L_0x03d8:
            java.lang.String r2 = "JGZ"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 61
            goto L_0x0602
        L_0x03e4:
            java.lang.String r2 = "mh"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 70
            goto L_0x0602
        L_0x03f0:
            java.lang.String r2 = "V5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 113(0x71, float:1.58E-43)
            goto L_0x0602
        L_0x03fc:
            java.lang.String r2 = "V1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 111(0x6f, float:1.56E-43)
            goto L_0x0602
        L_0x0408:
            java.lang.String r2 = "Q5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 98
            goto L_0x0602
        L_0x0414:
            java.lang.String r2 = "C1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 16
            goto L_0x0602
        L_0x0420:
            java.lang.String r2 = "woods_fn"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 118(0x76, float:1.65E-43)
            goto L_0x0602
        L_0x042c:
            java.lang.String r2 = "ELUGA_A3_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 25
            goto L_0x0602
        L_0x0438:
            java.lang.String r2 = "Z12_PRO"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 122(0x7a, float:1.71E-43)
            goto L_0x0602
        L_0x0444:
            java.lang.String r2 = "BLACK-1X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 13
            goto L_0x0602
        L_0x0450:
            java.lang.String r2 = "taido_row"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x0602
        L_0x045c:
            java.lang.String r2 = "Pixi4-7_3G"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 90
            goto L_0x0602
        L_0x0468:
            java.lang.String r2 = "GIONEE_GBL7360"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 41
            goto L_0x0602
        L_0x0474:
            java.lang.String r2 = "GiONEE_CBL7513"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 39
            goto L_0x0602
        L_0x0480:
            java.lang.String r2 = "OnePlus5T"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 77
            goto L_0x0602
        L_0x048c:
            java.lang.String r2 = "whyred"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 116(0x74, float:1.63E-43)
            goto L_0x0602
        L_0x0498:
            java.lang.String r2 = "watson"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 115(0x73, float:1.61E-43)
            goto L_0x0602
        L_0x04a4:
            java.lang.String r2 = "SVP-DTV15"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x0602
        L_0x04b0:
            java.lang.String r2 = "A7000-a"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 6
            goto L_0x0602
        L_0x04bb:
            java.lang.String r2 = "nicklaus_f"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 74
            goto L_0x0602
        L_0x04c7:
            java.lang.String r2 = "tcl_eu"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 110(0x6e, float:1.54E-43)
            goto L_0x0602
        L_0x04d3:
            java.lang.String r2 = "ELUGA_Ray_X"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 28
            goto L_0x0602
        L_0x04df:
            java.lang.String r2 = "s905x018"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x0602
        L_0x04eb:
            java.lang.String r2 = "A10-70F"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 3
            goto L_0x0602
        L_0x04f6:
            java.lang.String r2 = "namath"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 73
            goto L_0x0602
        L_0x0502:
            java.lang.String r2 = "Slate_Pro"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 102(0x66, float:1.43E-43)
            goto L_0x0602
        L_0x050e:
            java.lang.String r2 = "iris60"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 58
            goto L_0x0602
        L_0x051a:
            java.lang.String r2 = "BRAVIA_ATV2"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 14
            goto L_0x0602
        L_0x0526:
            java.lang.String r2 = "GiONEE_GBL7319"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 40
            goto L_0x0602
        L_0x0532:
            java.lang.String r2 = "panell_dt"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 84
            goto L_0x0602
        L_0x053e:
            java.lang.String r2 = "panell_ds"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 83
            goto L_0x0602
        L_0x054a:
            java.lang.String r2 = "panell_dl"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 82
            goto L_0x0602
        L_0x0556:
            java.lang.String r2 = "vernee_M5"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 114(0x72, float:1.6E-43)
            goto L_0x0602
        L_0x0562:
            java.lang.String r2 = "Phantom6"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 89
            goto L_0x0602
        L_0x056e:
            java.lang.String r2 = "ComioS1"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 17
            goto L_0x0602
        L_0x057a:
            java.lang.String r2 = "XT1663"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 121(0x79, float:1.7E-43)
            goto L_0x0602
        L_0x0586:
            java.lang.String r2 = "AquaPowerM"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 10
            goto L_0x0602
        L_0x0592:
            java.lang.String r2 = "PGN611"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 88
            goto L_0x0602
        L_0x059e:
            java.lang.String r2 = "PGN610"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 87
            goto L_0x0602
        L_0x05a9:
            java.lang.String r2 = "PGN528"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 86
            goto L_0x0602
        L_0x05b4:
            java.lang.String r2 = "NX573J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 76
            goto L_0x0602
        L_0x05bf:
            java.lang.String r2 = "NX541J"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 75
            goto L_0x0602
        L_0x05ca:
            java.lang.String r2 = "CP8676_I02"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 18
            goto L_0x0602
        L_0x05d5:
            java.lang.String r2 = "K50a40"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 62
            goto L_0x0602
        L_0x05e0:
            java.lang.String r2 = "GIONEE_SWW1631"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 44
            goto L_0x0602
        L_0x05eb:
            java.lang.String r2 = "GIONEE_SWW1627"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 43
            goto L_0x0602
        L_0x05f6:
            java.lang.String r2 = "GIONEE_SWW1609"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x0601
            r1 = 42
            goto L_0x0602
        L_0x0601:
            r1 = -1
        L_0x0602:
            switch(r1) {
                case 0: goto L_0x0606;
                case 1: goto L_0x0606;
                case 2: goto L_0x0606;
                case 3: goto L_0x0606;
                case 4: goto L_0x0606;
                case 5: goto L_0x0606;
                case 6: goto L_0x0606;
                case 7: goto L_0x0606;
                case 8: goto L_0x0606;
                case 9: goto L_0x0606;
                case 10: goto L_0x0606;
                case 11: goto L_0x0606;
                case 12: goto L_0x0606;
                case 13: goto L_0x0606;
                case 14: goto L_0x0606;
                case 15: goto L_0x0606;
                case 16: goto L_0x0606;
                case 17: goto L_0x0606;
                case 18: goto L_0x0606;
                case 19: goto L_0x0606;
                case 20: goto L_0x0606;
                case 21: goto L_0x0606;
                case 22: goto L_0x0606;
                case 23: goto L_0x0606;
                case 24: goto L_0x0606;
                case 25: goto L_0x0606;
                case 26: goto L_0x0606;
                case 27: goto L_0x0606;
                case 28: goto L_0x0606;
                case 29: goto L_0x0606;
                case 30: goto L_0x0606;
                case 31: goto L_0x0606;
                case 32: goto L_0x0606;
                case 33: goto L_0x0606;
                case 34: goto L_0x0606;
                case 35: goto L_0x0606;
                case 36: goto L_0x0606;
                case 37: goto L_0x0606;
                case 38: goto L_0x0606;
                case 39: goto L_0x0606;
                case 40: goto L_0x0606;
                case 41: goto L_0x0606;
                case 42: goto L_0x0606;
                case 43: goto L_0x0606;
                case 44: goto L_0x0606;
                case 45: goto L_0x0606;
                case 46: goto L_0x0606;
                case 47: goto L_0x0606;
                case 48: goto L_0x0606;
                case 49: goto L_0x0606;
                case 50: goto L_0x0606;
                case 51: goto L_0x0606;
                case 52: goto L_0x0606;
                case 53: goto L_0x0606;
                case 54: goto L_0x0606;
                case 55: goto L_0x0606;
                case 56: goto L_0x0606;
                case 57: goto L_0x0606;
                case 58: goto L_0x0606;
                case 59: goto L_0x0606;
                case 60: goto L_0x0606;
                case 61: goto L_0x0606;
                case 62: goto L_0x0606;
                case 63: goto L_0x0606;
                case 64: goto L_0x0606;
                case 65: goto L_0x0606;
                case 66: goto L_0x0606;
                case 67: goto L_0x0606;
                case 68: goto L_0x0606;
                case 69: goto L_0x0606;
                case 70: goto L_0x0606;
                case 71: goto L_0x0606;
                case 72: goto L_0x0606;
                case 73: goto L_0x0606;
                case 74: goto L_0x0606;
                case 75: goto L_0x0606;
                case 76: goto L_0x0606;
                case 77: goto L_0x0606;
                case 78: goto L_0x0606;
                case 79: goto L_0x0606;
                case 80: goto L_0x0606;
                case 81: goto L_0x0606;
                case 82: goto L_0x0606;
                case 83: goto L_0x0606;
                case 84: goto L_0x0606;
                case 85: goto L_0x0606;
                case 86: goto L_0x0606;
                case 87: goto L_0x0606;
                case 88: goto L_0x0606;
                case 89: goto L_0x0606;
                case 90: goto L_0x0606;
                case 91: goto L_0x0606;
                case 92: goto L_0x0606;
                case 93: goto L_0x0606;
                case 94: goto L_0x0606;
                case 95: goto L_0x0606;
                case 96: goto L_0x0606;
                case 97: goto L_0x0606;
                case 98: goto L_0x0606;
                case 99: goto L_0x0606;
                case 100: goto L_0x0606;
                case 101: goto L_0x0606;
                case 102: goto L_0x0606;
                case 103: goto L_0x0606;
                case 104: goto L_0x0606;
                case 105: goto L_0x0606;
                case 106: goto L_0x0606;
                case 107: goto L_0x0606;
                case 108: goto L_0x0606;
                case 109: goto L_0x0606;
                case 110: goto L_0x0606;
                case 111: goto L_0x0606;
                case 112: goto L_0x0606;
                case 113: goto L_0x0606;
                case 114: goto L_0x0606;
                case 115: goto L_0x0606;
                case 116: goto L_0x0606;
                case 117: goto L_0x0606;
                case 118: goto L_0x0606;
                case 119: goto L_0x0606;
                case 120: goto L_0x0606;
                case 121: goto L_0x0606;
                case 122: goto L_0x0606;
                case 123: goto L_0x0606;
                default: goto L_0x0605;
            }     // Catch:{ all -> 0x0639 }
        L_0x0605:
            goto L_0x0608
        L_0x0606:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0639 }
        L_0x0608:
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.MODEL     // Catch:{ all -> 0x0639 }
            int r2 = r1.hashCode()     // Catch:{ all -> 0x0639 }
            r4 = 2006354(0x1e9d52, float:2.811501E-39)
            if (r2 == r4) goto L_0x0623
            r0 = 2006367(0x1e9d5f, float:2.811519E-39)
            if (r2 == r0) goto L_0x0619
            goto L_0x062c
        L_0x0619:
            java.lang.String r0 = "AFTN"
            boolean r0 = r1.equals(r0)     // Catch:{ all -> 0x0639 }
            if (r0 == 0) goto L_0x062c
            r0 = 1
            goto L_0x062d
        L_0x0623:
            java.lang.String r2 = "AFTA"
            boolean r1 = r1.equals(r2)     // Catch:{ all -> 0x0639 }
            if (r1 == 0) goto L_0x062c
            goto L_0x062d
        L_0x062c:
            r0 = -1
        L_0x062d:
            switch(r0) {
                case 0: goto L_0x0631;
                case 1: goto L_0x0631;
                default: goto L_0x0630;
            }     // Catch:{ all -> 0x0639 }
        L_0x0630:
            goto L_0x0633
        L_0x0631:
            deviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0639 }
        L_0x0633:
            evaluatedDeviceNeedsSetOutputSurfaceWorkaround = r3     // Catch:{ all -> 0x0639 }
        L_0x0635:
            monitor-exit(r7)     // Catch:{ all -> 0x0639 }
            boolean r7 = deviceNeedsSetOutputSurfaceWorkaround
            return r7
        L_0x0639:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0639 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.codecNeedsSetOutputSurfaceWorkaround(java.lang.String):boolean");
    }
}
