package com.brightcove.player.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View.MeasureSpec;
import com.brightcove.player.model.Video.ProjectionFormat;

@TargetApi(14)
public class BrightcoveTextureView extends TextureView implements OnFrameAvailableListener, RenderView {
    private static final String TAG = "BrightcoveTextureView";
    private int measuredVideoHeight;
    private int measuredVideoWidth;
    private OnFrameAvailableListener onFrameAvailableListener;
    private ProjectionFormat projectionFormat = ProjectionFormat.NORMAL;
    private Surface surface;
    private int videoHeight;
    private int videoWidth;

    public SurfaceHolder getHolder() {
        return null;
    }

    public boolean isVrMode() {
        return false;
    }

    public BrightcoveTextureView(Context context) {
        super(context);
    }

    public Surface getSurface() {
        if (this.surface == null) {
            this.surface = new Surface(getSurfaceTexture());
        }
        return this.surface;
    }

    public void setSurface(Surface surface2) {
        if (this.surface != surface2) {
            release();
            this.surface = surface2;
        }
    }

    @NonNull
    public ProjectionFormat getProjectionFormat() {
        return ProjectionFormat.NORMAL;
    }

    public void setProjectionFormat(@Nullable ProjectionFormat projectionFormat2) {
        if (projectionFormat2 != ProjectionFormat.NORMAL) {
            StringBuilder sb = new StringBuilder();
            sb.append("Texture view does not support ");
            sb.append(projectionFormat2);
            sb.append(" format");
            throw new UnsupportedOperationException(sb.toString());
        }
    }

    public void setVrMode(boolean z) {
        throw new UnsupportedOperationException("Texture view does not support VR mode");
    }

    public int getVideoWidth() {
        return this.videoWidth;
    }

    public int getVideoHeight() {
        return this.videoHeight;
    }

    public int getMeasuredVideoWidth() {
        return this.measuredVideoWidth;
    }

    public int getMeasuredVideoHeight() {
        return this.measuredVideoHeight;
    }

    public void setVideoSize(int i, int i2) {
        this.videoWidth = i;
        this.videoHeight = i2;
        if (i > 0 && i2 > 0) {
            super.requestLayout();
            super.invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int i3 = this.videoWidth;
        if (i3 > 0) {
            int i4 = this.videoHeight;
            if (i4 > 0) {
                if (i3 * size2 > size * i4) {
                    size2 = (i4 * size) / i3;
                } else if (i3 * size2 < size * i4) {
                    size = (i3 * size2) / i4;
                }
            }
        }
        this.measuredVideoHeight = size2;
        this.measuredVideoWidth = size;
        setMeasuredDimension(size, size2);
    }

    public void release() {
        Surface surface2 = this.surface;
        if (surface2 != null) {
            surface2.release();
            this.surface = null;
        }
    }

    public void setOnFrameAvailableListener(OnFrameAvailableListener onFrameAvailableListener2) {
        this.onFrameAvailableListener = onFrameAvailableListener2;
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        OnFrameAvailableListener onFrameAvailableListener2 = this.onFrameAvailableListener;
        if (onFrameAvailableListener2 != null) {
            onFrameAvailableListener2.onFrameAvailable(surfaceTexture);
        }
    }
}
