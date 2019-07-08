package com.brightcove.player.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.MeasureSpec;
import com.brightcove.player.model.Video.ProjectionFormat;
import com.brightcove.player.video360.RenderThread;

public class BrightcoveSurfaceView extends SurfaceView implements OnFrameAvailableListener, RenderView {
    private static final String TAG = "BrightcoveSurfaceView";
    private final SurfaceHolderProxy holderProxy = new SurfaceHolderProxy();
    /* access modifiers changed from: private */
    public Handler mainHandler = new Handler();
    private int measuredVideoHeight;
    private int measuredVideoWidth;
    /* access modifiers changed from: private */
    public OnFrameAvailableListener onFrameAvailableListener;
    private ProjectionFormat projectionFormat = ProjectionFormat.NORMAL;
    /* access modifiers changed from: private */
    public RenderThread renderThread;
    private final Callback surfaceListener = new Callback() {
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            if (BrightcoveSurfaceView.this.isVideo360Mode()) {
                BrightcoveSurfaceView.this.createRenderThread();
            }
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            if (BrightcoveSurfaceView.this.isVideo360Supported() && BrightcoveSurfaceView.this.renderThread != null) {
                BrightcoveSurfaceView.this.renderThread.notifySurfaceChanged(surfaceHolder.getSurface(), i2, i3);
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            BrightcoveSurfaceView.this.destroyRenderThread();
        }
    };
    private int videoHeight;
    private int videoWidth;

    private class SurfaceHolderProxy implements SurfaceHolder {
        /* access modifiers changed from: private */
        public Surface surface;

        private SurfaceHolderProxy() {
        }

        public boolean isCreating() {
            return BrightcoveSurfaceView.this.getBaseHolder().isCreating();
        }

        public void addCallback(Callback callback) {
            BrightcoveSurfaceView.this.getBaseHolder().addCallback(callback);
        }

        public void removeCallback(Callback callback) {
            BrightcoveSurfaceView.this.getBaseHolder().removeCallback(callback);
        }

        public void setFixedSize(int i, int i2) {
            BrightcoveSurfaceView.this.getBaseHolder().setFixedSize(i, i2);
        }

        public void setSizeFromLayout() {
            BrightcoveSurfaceView.this.getBaseHolder().setSizeFromLayout();
        }

        public void setFormat(int i) {
            BrightcoveSurfaceView.this.getBaseHolder().setFormat(i);
        }

        public void setType(int i) {
            BrightcoveSurfaceView.this.getBaseHolder().setType(i);
        }

        public void setKeepScreenOn(boolean z) {
            BrightcoveSurfaceView.this.getBaseHolder().setKeepScreenOn(z);
        }

        public Canvas lockCanvas() {
            return BrightcoveSurfaceView.this.getBaseHolder().lockCanvas();
        }

        public Canvas lockCanvas(Rect rect) {
            return BrightcoveSurfaceView.this.getBaseHolder().lockCanvas(rect);
        }

        public void unlockCanvasAndPost(Canvas canvas) {
            BrightcoveSurfaceView.this.getBaseHolder().unlockCanvasAndPost(canvas);
        }

        public void setSurface(final Surface surface2) {
            BrightcoveSurfaceView.this.mainHandler.post(new Runnable() {
                public void run() {
                    SurfaceHolderProxy.this.surface = surface2;
                }
            });
        }

        public Surface getSurface() {
            Surface surface2 = this.surface;
            return surface2 == null ? BrightcoveSurfaceView.this.getBaseHolder().getSurface() : surface2;
        }

        public Rect getSurfaceFrame() {
            return BrightcoveSurfaceView.this.getBaseHolder().getSurfaceFrame();
        }
    }

    public void release() {
    }

    public boolean isVideo360Supported() {
        return VERSION.SDK_INT >= 17;
    }

    @NonNull
    public ProjectionFormat getProjectionFormat() {
        return this.projectionFormat;
    }

    public void setProjectionFormat(@Nullable ProjectionFormat projectionFormat2) {
        if (projectionFormat2 == null) {
            projectionFormat2 = ProjectionFormat.NORMAL;
        }
        if (this.projectionFormat != projectionFormat2) {
            destroyRenderThread();
            this.projectionFormat = projectionFormat2;
            if (isVideo360Mode()) {
                createRenderThread();
            }
        }
    }

    public boolean isVideo360Mode() {
        return this.projectionFormat != ProjectionFormat.NORMAL;
    }

    @TargetApi(17)
    public boolean isVrMode() {
        RenderThread renderThread2 = this.renderThread;
        return renderThread2 != null && renderThread2.isVrMode();
    }

    public void setVrMode(boolean z) {
        if (isVideo360Supported()) {
            RenderThread renderThread2 = this.renderThread;
            if (renderThread2 != null) {
                renderThread2.setVrMode(z);
            }
        }
    }

    /* access modifiers changed from: private */
    public void createRenderThread() {
        if (isVideo360Supported()) {
            this.renderThread = new RenderThread(this);
            this.renderThread.start();
            SurfaceHolder holder = getHolder();
            Rect surfaceFrame = holder.getSurfaceFrame();
            this.renderThread.notifySurfaceAvailable(holder.getSurface(), surfaceFrame.width(), surfaceFrame.height());
            this.renderThread.setOnFrameAvailableListener(this);
        }
    }

    /* access modifiers changed from: private */
    public void destroyRenderThread() {
        if (isVideo360Supported()) {
            RenderThread renderThread2 = this.renderThread;
            if (renderThread2 != null) {
                renderThread2.notifySurfaceDestroyed();
                this.renderThread = null;
                setSurface(null);
            }
        }
    }

    public BrightcoveSurfaceView(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getHolder().addCallback(this.surfaceListener);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getHolder().removeCallback(this.surfaceListener);
    }

    public Surface getSurface() {
        return this.holderProxy.getSurface();
    }

    public void setSurface(Surface surface) {
        this.holderProxy.setSurface(surface);
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

    public SurfaceHolder getHolder() {
        return this.holderProxy;
    }

    public SurfaceHolder getBaseHolder() {
        return super.getHolder();
    }

    public void setOnFrameAvailableListener(OnFrameAvailableListener onFrameAvailableListener2) {
        this.onFrameAvailableListener = onFrameAvailableListener2;
    }

    public void onFrameAvailable(final SurfaceTexture surfaceTexture) {
        this.mainHandler.post(new Runnable() {
            public void run() {
                if (BrightcoveSurfaceView.this.onFrameAvailableListener != null) {
                    BrightcoveSurfaceView.this.onFrameAvailableListener.onFrameAvailable(surfaceTexture);
                }
            }
        });
    }
}
