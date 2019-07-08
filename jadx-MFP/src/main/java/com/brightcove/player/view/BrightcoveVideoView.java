package com.brightcove.player.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.brightcove.player.display.VideoDisplayComponent;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventType;
import com.brightcove.player.model.Video;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

public class BrightcoveVideoView extends BaseVideoView {
    /* access modifiers changed from: private */
    public static final String TAG = "BrightcoveVideoView";
    private RenderView renderView;
    private final Callback surfaceListener = new Callback() {
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            Log.d(BrightcoveVideoView.TAG, "Surface created.");
            BrightcoveVideoView.this.videoDisplay.surfaceCreated(surfaceHolder);
            BrightcoveVideoView.this.eventEmitter.emit(EventType.READY_TO_PLAY);
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.d(BrightcoveVideoView.TAG, "Surface destroyed.");
            BrightcoveVideoView.this.videoDisplay.surfaceDestroyed(surfaceHolder);
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            String access$000 = BrightcoveVideoView.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Surface changed to ");
            sb.append(i2);
            sb.append(", ");
            sb.append(i3);
            Log.d(access$000, sb.toString());
            BrightcoveVideoView.this.videoDisplay.surfaceChanged(surfaceHolder, i, i2, i3);
        }
    };

    public BrightcoveVideoView(Context context) {
        super(context);
    }

    public BrightcoveVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BrightcoveVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        createRenderView(context);
        super.init(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    public Class<? extends RenderView> getRenderViewClass() {
        return BrightcoveSurfaceView.class;
    }

    private void createRenderView(Context context) {
        try {
            Class renderViewClass = getRenderViewClass();
            this.renderView = (RenderView) renderViewClass.getConstructors()[0].newInstance(new Object[]{context});
            if (this.renderView instanceof View) {
                addView((View) this.renderView);
            } else {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Render View[ ");
                sb.append(renderViewClass);
                sb.append("] is not an Android View");
                Log.e(str, sb.toString());
            }
            if (this.renderView instanceof BrightcoveSurfaceView) {
                ((BrightcoveSurfaceView) this.renderView).setOnFrameAvailableListener(new OnFrameAvailableListener() {
                    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                        if (BrightcoveVideoView.this.eventEmitter != null) {
                            BrightcoveVideoView.this.eventEmitter.emit(EventType.ON_FRAME_AVAILABLE);
                            Video currentVideo = BrightcoveVideoView.this.getCurrentVideo();
                            if (currentVideo != null) {
                                BrightcoveVideoView.this.eventEmitter.emit(EventType.PROJECTION_FORMAT_CHANGED, Collections.singletonMap("projectionFormat", currentVideo.getProjectionFormat()));
                            }
                        }
                    }
                });
            }
        } catch (IllegalAccessException e) {
            Log.e(TAG, "Failed to create surface view", e);
        } catch (InstantiationException e2) {
            Log.e(TAG, "Failed to create surface view", e2);
        } catch (InvocationTargetException e3) {
            Log.e(TAG, "Failed to create surface view", e3);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.renderView.getHolder().addCallback(this.surfaceListener);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.renderView.getHolder().removeCallback(this.surfaceListener);
    }

    public RenderView getRenderView() {
        return this.renderView;
    }

    /* access modifiers changed from: protected */
    public void setChildLayoutParams(LayoutParams layoutParams) {
        int childCount = getChildCount();
        int i = 0;
        boolean z = this.renderView == null;
        while (!z && i < childCount) {
            if (getChildAt(i).getClass().getName().equals("com.brightcove.ima.GoogleIMAVideoAdPlayer")) {
                z = true;
            }
            i++;
        }
        if (!z) {
            this.renderView.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height, 17));
        }
        super.setChildLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void resetMetaData() {
        RenderView renderView2 = this.renderView;
        if (renderView2 != null) {
            renderView2.setVideoSize(0, 0);
        }
        super.resetMetaData();
    }

    public SurfaceView getSurfaceView() {
        return (SurfaceView) this.renderView;
    }

    /* access modifiers changed from: protected */
    public VideoDisplayComponent createVideoDisplayComponent(EventEmitter eventEmitter) {
        return new VideoDisplayComponent(this.renderView, eventEmitter);
    }

    public int getVideoWidth() {
        RenderView renderView2 = this.renderView;
        if (renderView2 == null) {
            return 0;
        }
        return renderView2.getVideoWidth();
    }

    public int getVideoHeight() {
        RenderView renderView2 = this.renderView;
        if (renderView2 == null) {
            return 0;
        }
        return renderView2.getVideoHeight();
    }

    /* access modifiers changed from: protected */
    public boolean canShowMediaControls() {
        RenderView renderView2 = this.renderView;
        return renderView2 != null && renderView2.isShown();
    }
}
