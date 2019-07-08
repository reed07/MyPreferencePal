package com.google.android.exoplayer2.ui.spherical;

import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.support.annotation.Nullable;
import com.brightcove.player.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.TimedValueQueue;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;
import com.google.android.exoplayer2.video.spherical.CameraMotionListener;
import com.google.android.exoplayer2.video.spherical.FrameRotationQueue;
import com.google.android.exoplayer2.video.spherical.Projection;
import com.google.android.exoplayer2.video.spherical.ProjectionDecoder;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

class SceneRenderer implements VideoFrameMetadataListener, CameraMotionListener {
    private volatile int defaultStereoMode = 0;
    private final AtomicBoolean frameAvailable = new AtomicBoolean();
    private final FrameRotationQueue frameRotationQueue = new FrameRotationQueue();
    @Nullable
    private byte[] lastProjectionData;
    private int lastStereoMode = -1;
    private final TimedValueQueue<Projection> projectionQueue = new TimedValueQueue<>();
    private final ProjectionRenderer projectionRenderer = new ProjectionRenderer();
    private final AtomicBoolean resetRotationAtNextFrame = new AtomicBoolean(true);
    private final float[] rotationMatrix = new float[16];
    private final TimedValueQueue<Long> sampleTimestampQueue = new TimedValueQueue<>();
    private SurfaceTexture surfaceTexture;
    private final float[] tempMatrix = new float[16];
    private int textureId;

    public void setDefaultStereoMode(int i) {
        this.defaultStereoMode = i;
    }

    public SurfaceTexture init() {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
        GlUtil.checkGlError();
        this.projectionRenderer.init();
        GlUtil.checkGlError();
        this.textureId = GlUtil.createExternalTexture();
        this.surfaceTexture = new SurfaceTexture(this.textureId);
        this.surfaceTexture.setOnFrameAvailableListener(new OnFrameAvailableListener() {
            public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
                SceneRenderer.this.frameAvailable.set(true);
            }
        });
        return this.surfaceTexture;
    }

    public void drawFrame(float[] fArr, int i) {
        GLES20.glClear(C.DASH_ROLE_CAPTION_FLAG);
        GlUtil.checkGlError();
        if (this.frameAvailable.compareAndSet(true, false)) {
            ((SurfaceTexture) Assertions.checkNotNull(this.surfaceTexture)).updateTexImage();
            GlUtil.checkGlError();
            if (this.resetRotationAtNextFrame.compareAndSet(true, false)) {
                Matrix.setIdentityM(this.rotationMatrix, 0);
            }
            long timestamp = this.surfaceTexture.getTimestamp();
            Long l = (Long) this.sampleTimestampQueue.poll(timestamp);
            if (l != null) {
                this.frameRotationQueue.pollRotationMatrix(this.rotationMatrix, l.longValue());
            }
            Projection projection = (Projection) this.projectionQueue.pollFloor(timestamp);
            if (projection != null) {
                this.projectionRenderer.setProjection(projection);
            }
        }
        Matrix.multiplyMM(this.tempMatrix, 0, fArr, 0, this.rotationMatrix, 0);
        this.projectionRenderer.draw(this.textureId, this.tempMatrix, i);
    }

    public void onVideoFrameAboutToBeRendered(long j, long j2, Format format) {
        this.sampleTimestampQueue.add(j2, Long.valueOf(j));
        setProjection(format.projectionData, format.stereoMode, j2);
    }

    public void onCameraMotion(long j, float[] fArr) {
        this.frameRotationQueue.setRotation(j, fArr);
    }

    public void onCameraMotionReset() {
        this.sampleTimestampQueue.clear();
        this.frameRotationQueue.reset();
        this.resetRotationAtNextFrame.set(true);
    }

    private void setProjection(@Nullable byte[] bArr, int i, long j) {
        byte[] bArr2 = this.lastProjectionData;
        int i2 = this.lastStereoMode;
        this.lastProjectionData = bArr;
        if (i == -1) {
            i = this.defaultStereoMode;
        }
        this.lastStereoMode = i;
        if (i2 != this.lastStereoMode || !Arrays.equals(bArr2, this.lastProjectionData)) {
            Projection projection = null;
            byte[] bArr3 = this.lastProjectionData;
            if (bArr3 != null) {
                projection = ProjectionDecoder.decode(bArr3, this.lastStereoMode);
            }
            if (projection == null || !ProjectionRenderer.isSupported(projection)) {
                projection = Projection.createEquirectangular(this.lastStereoMode);
            }
            this.projectionQueue.add(j, projection);
        }
    }
}
