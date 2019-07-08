package com.brightcove.player.view;

import android.content.Context;
import android.util.AttributeSet;
import com.brightcove.player.controller.DefaultSourceSelectionController;
import com.brightcove.player.controller.ExoPlayerSourceSelectionController;
import com.brightcove.player.display.ExoPlayerVideoDisplayComponent;
import com.brightcove.player.display.VideoDisplayComponent;
import com.brightcove.player.event.EventEmitter;

public class BrightcoveExoPlayerTextureVideoView extends BrightcoveTextureVideoView {
    public boolean isHlsRecommended() {
        return true;
    }

    public BrightcoveExoPlayerTextureVideoView(Context context) {
        super(context);
    }

    public BrightcoveExoPlayerTextureVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BrightcoveExoPlayerTextureVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void seekToLive() {
        int liveEdge = this.videoDisplay.getLiveEdge();
        if (liveEdge != -1 && getCurrentPosition() < liveEdge) {
            seekTo(liveEdge);
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasPlayer() {
        return ((ExoPlayerVideoDisplayComponent) this.videoDisplay).getExoPlayer() != null;
    }

    /* access modifiers changed from: protected */
    public void onPrepared() {
        if (this.onPreparedListener != null) {
            this.onPreparedListener.onPrepared(null);
        }
    }

    /* access modifiers changed from: protected */
    public VideoDisplayComponent createVideoDisplayComponent(EventEmitter eventEmitter) {
        return new ExoPlayerVideoDisplayComponent(this.brightcoveTextureView, eventEmitter);
    }

    /* access modifiers changed from: protected */
    public DefaultSourceSelectionController createSourceSelectionController(EventEmitter eventEmitter) {
        return new ExoPlayerSourceSelectionController(eventEmitter);
    }
}
