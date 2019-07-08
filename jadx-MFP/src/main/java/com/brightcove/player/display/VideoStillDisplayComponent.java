package com.brightcove.player.display;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import com.brightcove.player.R;
import com.brightcove.player.display.tasks.LoadImageTask;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.model.PlaybackLocation;
import com.brightcove.player.util.ErrorUtil;
import java.net.URI;

@ListensFor(events = {"activityStopped", "cuePoint", "didSeekTo", "progress", "adProgress", "adBreakStarted", "fragmentStopped", "setVideoStill", "willInterruptContent", "removeVideoStill"})
@Emits(events = {"didSetVideoStill", "didRemoveVideoStill"})
public class VideoStillDisplayComponent extends AbstractComponent implements Component {
    public static final String TAG = "VideoStillDisplayComponent";
    private int activityStoppedListenerToken;
    private int adBreakStartedListenerToken;
    private int adProgressListenerToken;
    private int cuePointListenerToken;
    private int didSeekToListenerToken;
    private int fragmentStoppedListenerToken;
    /* access modifiers changed from: private */
    public boolean isRemote;
    private int progressListenerToken;
    private int removeVideoStillListenerToken;
    /* access modifiers changed from: private */
    public LoadImageTask task;
    /* access modifiers changed from: private */
    public ImageView view;
    private int willInterruptContentListenerToken;

    private class HideListener implements EventListener {
        private HideListener() {
        }

        public void processEvent(Event event) {
            Log.v(VideoStillDisplayComponent.TAG, "OnHideListener");
            VideoStillDisplayComponent.this.view.setVisibility(4);
            if (VideoStillDisplayComponent.this.task != null) {
                VideoStillDisplayComponent.this.task.cancel(true);
                VideoStillDisplayComponent.this.task = null;
            }
            VideoStillDisplayComponent.this.recycle();
            VideoStillDisplayComponent.this.eventEmitter.emit(EventType.DID_REMOVE_VIDEO_STILL);
            VideoStillDisplayComponent.this.removeHideListener();
        }
    }

    private class OnSetVideoStill implements EventListener {
        private OnSetVideoStill() {
        }

        @SuppressLint({"NewApi"})
        @Default
        public void processEvent(Event event) {
            Log.v(VideoStillDisplayComponent.TAG, "OnSetVideoStill");
            VideoStillDisplayComponent.this.removeHideListener();
            Object obj = event.properties.get(AbstractEvent.PLAYBACK_LOCATION);
            VideoStillDisplayComponent.this.isRemote = (obj instanceof PlaybackLocation) && obj == PlaybackLocation.REMOTE;
            VideoStillDisplayComponent.this.setHideListener();
            if (VERSION.SDK_INT >= 16) {
                ViewParent parent = VideoStillDisplayComponent.this.view.getParent();
                if (parent instanceof View) {
                    VideoStillDisplayComponent.this.view.setBackground(((View) parent).getBackground());
                }
            }
            if (event.properties.get(AbstractEvent.VIDEO_STILL) instanceof URI) {
                URI uri = (URI) event.properties.get(AbstractEvent.VIDEO_STILL);
                VideoStillDisplayComponent videoStillDisplayComponent = VideoStillDisplayComponent.this;
                videoStillDisplayComponent.task = new LoadImageTask(videoStillDisplayComponent.view, VideoStillDisplayComponent.this.eventEmitter);
                VideoStillDisplayComponent.this.task.setSuccessEventType(EventType.DID_SET_VIDEO_STILL);
                VideoStillDisplayComponent.this.task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new URI[]{uri});
                return;
            }
            VideoStillDisplayComponent.this.setDefaultVideoStill();
        }
    }

    public VideoStillDisplayComponent(ImageView imageView, EventEmitter eventEmitter) {
        super(eventEmitter, VideoStillDisplayComponent.class);
        if (imageView != null) {
            this.view = imageView;
            addListener(EventType.SET_VIDEO_STILL, new OnSetVideoStill());
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.IMAGE_VIEW_REQUIRED));
    }

    /* access modifiers changed from: private */
    public void removeHideListener() {
        this.eventEmitter.off("progress", this.progressListenerToken);
        this.eventEmitter.off(EventType.AD_PROGRESS, this.adProgressListenerToken);
        this.eventEmitter.off(EventType.AD_BREAK_STARTED, this.adBreakStartedListenerToken);
        this.eventEmitter.off(EventType.DID_SEEK_TO, this.didSeekToListenerToken);
        this.eventEmitter.off(EventType.CUE_POINT, this.cuePointListenerToken);
        this.eventEmitter.off(EventType.WILL_INTERRUPT_CONTENT, this.willInterruptContentListenerToken);
        this.eventEmitter.off(EventType.ACTIVITY_STOPPED, this.activityStoppedListenerToken);
        this.eventEmitter.off(EventType.FRAGMENT_STOPPED, this.fragmentStoppedListenerToken);
        this.eventEmitter.off(EventType.REMOVE_VIDEO_STILL, this.removeVideoStillListenerToken);
    }

    /* access modifiers changed from: private */
    public void setHideListener() {
        HideListener hideListener = new HideListener();
        if (!this.isRemote) {
            this.progressListenerToken = this.eventEmitter.once("progress", hideListener);
            this.adProgressListenerToken = this.eventEmitter.once(EventType.AD_PROGRESS, hideListener);
            this.adBreakStartedListenerToken = this.eventEmitter.once(EventType.AD_BREAK_STARTED, hideListener);
            this.didSeekToListenerToken = this.eventEmitter.once(EventType.DID_SEEK_TO, hideListener);
            this.cuePointListenerToken = this.eventEmitter.once(EventType.CUE_POINT, hideListener);
        }
        this.willInterruptContentListenerToken = this.eventEmitter.once(EventType.WILL_INTERRUPT_CONTENT, hideListener);
        this.activityStoppedListenerToken = this.eventEmitter.once(EventType.ACTIVITY_STOPPED, hideListener);
        this.fragmentStoppedListenerToken = this.eventEmitter.once(EventType.FRAGMENT_STOPPED, hideListener);
        this.removeVideoStillListenerToken = this.eventEmitter.once(EventType.REMOVE_VIDEO_STILL, hideListener);
    }

    /* access modifiers changed from: private */
    @TargetApi(16)
    public void setDefaultVideoStill() {
        Drawable drawable = this.view.getResources().getDrawable(R.drawable.default_video_still);
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            for (int i = 0; i < layerDrawable.getNumberOfLayers(); i++) {
                Drawable drawable2 = layerDrawable.getDrawable(i);
                if (drawable2 != null) {
                    drawable2.setLevel(1);
                }
            }
        }
        this.view.setBackground(drawable);
        this.view.setVisibility(0);
        this.view.requestLayout();
        this.eventEmitter.emit(EventType.DID_SET_VIDEO_STILL);
    }

    /* access modifiers changed from: private */
    public void recycle() {
        Drawable drawable = this.view.getDrawable();
        if (drawable != null && (drawable instanceof BitmapDrawable)) {
            ((BitmapDrawable) drawable).getBitmap().recycle();
        }
    }
}
