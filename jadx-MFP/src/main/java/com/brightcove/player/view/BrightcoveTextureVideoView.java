package com.brightcove.player.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.brightcove.player.display.VideoDisplayComponent;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventType;

@TargetApi(14)
public class BrightcoveTextureVideoView extends BaseVideoView {
    /* access modifiers changed from: private */
    public static final String TAG = "BrightcoveTextureVideoView";
    protected BrightcoveTextureView brightcoveTextureView;
    protected TextureListener textureListener;

    private class TextureListener implements SurfaceTextureListener {
        private TextureListener() {
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            Log.d(BrightcoveTextureVideoView.TAG, "Texture available");
            BrightcoveTextureVideoView.this.eventEmitter.emit(EventType.READY_TO_PLAY);
            BrightcoveTextureVideoView.this.videoDisplay.onSurfaceTextureAvailable(surfaceTexture, i, i2);
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            Log.d(BrightcoveTextureVideoView.TAG, "Texture size changed");
            BrightcoveTextureVideoView.this.videoDisplay.onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            Log.d(BrightcoveTextureVideoView.TAG, "Texture destroyed");
            BrightcoveTextureVideoView.this.videoDisplay.onSurfaceTextureDestroyed(surfaceTexture);
            return true;
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            BrightcoveTextureVideoView.this.videoDisplay.onSurfaceTextureUpdated(surfaceTexture);
        }
    }

    public BrightcoveTextureVideoView(Context context) {
        super(context);
    }

    public BrightcoveTextureVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BrightcoveTextureVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RenderView getRenderView() {
        return this.brightcoveTextureView;
    }

    public TextureView getTextureView() {
        return this.brightcoveTextureView;
    }

    public int getVideoWidth() {
        return this.brightcoveTextureView.getVideoWidth();
    }

    public int getVideoHeight() {
        return this.brightcoveTextureView.getVideoHeight();
    }

    public int getMeasuredVideoWidth() {
        return this.brightcoveTextureView.getMeasuredVideoWidth();
    }

    public int getMeasuredVideoHeight() {
        return this.brightcoveTextureView.getMeasuredVideoHeight();
    }

    /* access modifiers changed from: protected */
    public boolean canShowMediaControls() {
        return this.brightcoveTextureView.isShown();
    }

    /* access modifiers changed from: protected */
    public void init(Context context) {
        Log.i(TAG, "init");
        this.brightcoveTextureView = new BrightcoveTextureView(context);
        this.textureListener = new TextureListener();
        this.brightcoveTextureView.setSurfaceTextureListener(this.textureListener);
        addView(this.brightcoveTextureView);
        super.init(context);
    }

    /* access modifiers changed from: protected */
    public void setChildLayoutParams(LayoutParams layoutParams) {
        this.brightcoveTextureView.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height, 17));
        super.setChildLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void resetMetaData() {
        BrightcoveTextureView brightcoveTextureView2 = this.brightcoveTextureView;
        if (brightcoveTextureView2 != null) {
            brightcoveTextureView2.setVideoSize(0, 0);
        }
        super.resetMetaData();
    }

    /* access modifiers changed from: protected */
    public VideoDisplayComponent createVideoDisplayComponent(EventEmitter eventEmitter) {
        return new VideoDisplayComponent(this.brightcoveTextureView, eventEmitter);
    }
}
