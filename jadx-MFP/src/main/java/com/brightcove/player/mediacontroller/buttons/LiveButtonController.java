package com.brightcove.player.mediacontroller.buttons;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.view.BaseVideoView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.List;
import java.util.Locale;

public class LiveButtonController extends AbstractButtonController {
    public static final String LIVE_EDGE_STATE = "liveEdgeState";
    private static final float LIVE_SHADOW_RADIUS = 8.0f;
    /* access modifiers changed from: private */
    public static final String TAG = "LiveButtonController";
    /* access modifiers changed from: private */
    public boolean isTvMode;
    /* access modifiers changed from: private */
    public ColorFilter liveColorFilter;
    /* access modifiers changed from: private */
    public boolean liveEdgeState;
    /* access modifiers changed from: private */
    public int liveForegroundColor = getColor(R.color.bmc_live, -16711936);
    /* access modifiers changed from: private */
    public boolean liveListenersSet;
    /* access modifiers changed from: private */
    public ColorFilter nonLiveColorFilter;
    /* access modifiers changed from: private */
    public int nonLiveForegroundColor = getColor(R.color.bmc_not_live, -1);
    /* access modifiers changed from: private */
    public boolean seekingToLiveEdge;

    private class HideSeekControlsHandler implements EventListener {
        private HideSeekControlsHandler() {
        }

        @Default
        public void processEvent(Event event) {
            LiveButtonController.this.setVisibility(4);
        }
    }

    private class LiveButtonHandler implements OnClickListener {
        private LiveButtonHandler() {
        }

        public void onClick(View view) {
            Log.d(LiveButtonController.TAG, "Returning to live video...");
            LiveButtonController.this.seekToLiveEdge();
        }
    }

    private class LiveDidPlayHandler implements EventListener {
        private LiveDidPlayHandler() {
        }

        @Default
        public void processEvent(Event event) {
            Log.d(LiveButtonController.TAG, String.format(Locale.getDefault(), "Processing event: %s.", new Object[]{event.getType()}));
            LiveButtonController.this.videoView.seekToLive();
        }
    }

    private class LiveEventHandlerSetter implements EventListener {
        private LiveEventHandlerSetter() {
        }

        public void processEvent(Event event) {
            if (!LiveButtonController.this.liveListenersSet && LiveButtonController.this.videoView.getVideoDisplay().isLive()) {
                LiveButtonController.this.removeLiveEventHandlers();
                if (EventType.VIDEO_SIZE_KNOWN.equals(event.getType()) && !LiveButtonController.this.videoView.isPlaying()) {
                    LiveButtonController.this.eventEmitter.once(EventType.DID_PLAY, new LiveDidPlayHandler());
                }
                if (LiveButtonController.this.videoView.isPlaying()) {
                    LiveButtonController.this.updateLiveState(LiveButtonController.this.liveEdgeState || LiveButtonController.this.videoView.getVideoDisplay().isInLiveEdge());
                }
                LiveButtonController.this.addLiveEventHandlers();
                LiveButtonController.this.liveListenersSet = true;
            }
        }
    }

    private class LiveUpdateHandler implements EventListener {
        private LiveUpdateHandler() {
        }

        public void processEvent(Event event) {
            if (LiveButtonController.this.videoView.getVideoDisplay().isLive()) {
                String type = event.getType();
                char c = 65535;
                int hashCode = type.hashCode();
                boolean z = true;
                if (hashCode != -906224877) {
                    if (hashCode != -174217033) {
                        if (hashCode != 3443508) {
                            if (hashCode == 1656958035 && type.equals(EventType.DID_PLAY)) {
                                c = 2;
                            }
                        } else if (type.equals(EventType.PLAY)) {
                            c = 3;
                        }
                    } else if (type.equals(EventType.DID_PAUSE)) {
                        c = 0;
                    }
                } else if (type.equals(EventType.SEEK_TO)) {
                    c = 1;
                }
                switch (c) {
                    case 0:
                        LiveButtonController.this.updateLiveState(false);
                        break;
                    case 1:
                        if (event.getIntegerProperty(AbstractEvent.SEEK_POSITION) < LiveButtonController.this.videoView.getVideoDisplay().getLiveEdge() || !LiveButtonController.this.videoView.isPlaying()) {
                            z = false;
                        }
                        if (!LiveButtonController.this.seekingToLiveEdge) {
                            LiveButtonController.this.updateLiveState(z);
                        }
                        LiveButtonController.this.seekingToLiveEdge = false;
                        break;
                    case 2:
                        if (!LiveButtonController.this.seekingToLiveEdge) {
                            LiveButtonController liveButtonController = LiveButtonController.this;
                            liveButtonController.updateLiveState(liveButtonController.videoView.getVideoDisplay().isInLiveEdge());
                        }
                        LiveButtonController.this.seekingToLiveEdge = false;
                        break;
                    case 3:
                        BrightcoveMediaController brightcoveMediaController = LiveButtonController.this.videoView.getBrightcoveMediaController();
                        if (brightcoveMediaController != null && !brightcoveMediaController.isDVRControllerEnabled()) {
                            LiveButtonController.this.seekToLiveEdge();
                            break;
                        }
                }
            }
        }
    }

    private class ShowSeekControlsHandler implements EventListener {
        private ShowSeekControlsHandler() {
        }

        @Default
        public void processEvent(Event event) {
            LiveButtonController.this.setVisibility(0);
        }
    }

    public int getManagedState() {
        return 0;
    }

    public LiveButtonController(Context context, BaseVideoView baseVideoView, View view, Typeface typeface, Bundle bundle) {
        Context context2 = context;
        super(context2, baseVideoView, view, R.id.live, typeface);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_live, R.string.desc_live, (Drawable) null, (OnClickListener) new LiveButtonHandler());
        list.add(buttonState);
        this.liveEdgeState = bundle != null && bundle.containsKey(LIVE_EDGE_STATE) && bundle.getBoolean(LIVE_EDGE_STATE);
        addListener(EventType.ENTER_TV_MODE, new EventListener() {
            public void processEvent(Event event) {
                LiveButtonController.this.isTvMode = true;
                LiveButtonController liveButtonController = LiveButtonController.this;
                liveButtonController.liveColorFilter = new PorterDuffColorFilter(liveButtonController.liveForegroundColor, Mode.MULTIPLY);
                LiveButtonController liveButtonController2 = LiveButtonController.this;
                liveButtonController2.nonLiveColorFilter = new PorterDuffColorFilter(liveButtonController2.nonLiveForegroundColor, Mode.MULTIPLY);
                if (LiveButtonController.this.getButton() != null) {
                    LiveButtonController.this.getButton().setOnFocusChangeListener(new OnFocusChangeListener() {
                        public void onFocusChange(View view, boolean z) {
                            ((Button) view).setShadowLayer(z ? 8.0f : BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, LiveButtonController.this.nonLiveForegroundColor);
                        }
                    });
                }
            }
        });
        LiveEventHandlerSetter liveEventHandlerSetter = new LiveEventHandlerSetter();
        addOnceListener(EventType.VIDEO_SIZE_KNOWN, liveEventHandlerSetter);
        addOnceListener(BrightcoveMediaController.CONTROL_BAR_CREATED, liveEventHandlerSetter);
        LiveUpdateHandler liveUpdateHandler = new LiveUpdateHandler();
        addListener(EventType.DID_PAUSE, liveUpdateHandler);
        addListener(EventType.SEEK_TO, liveUpdateHandler);
        addListener(EventType.DID_PLAY, liveUpdateHandler);
        addListener(EventType.PLAY, liveUpdateHandler);
    }

    public boolean isLiveEdgeState() {
        return this.liveEdgeState;
    }

    private int getColor(int i, int i2) {
        try {
            if (VERSION.SDK_INT < 23) {
                return this.videoView.getResources().getColor(i);
            }
            return getColor(i);
        } catch (NotFoundException unused) {
            Log.w(TAG, String.format(Locale.getDefault(), "The resource with id (%1$x) cannot be found.  Going with the default.", new Object[]{Integer.valueOf(i)}));
            return i2;
        }
    }

    @TargetApi(23)
    private int getColor(int i) {
        return this.videoView.getResources().getColor(i, null);
    }

    /* access modifiers changed from: private */
    public void addLiveEventHandlers() {
        Log.v(TAG, "addLiveEventHandlers");
        addListener(EventType.HIDE_SEEK_CONTROLS, new HideSeekControlsHandler());
        addListener(EventType.SHOW_SEEK_CONTROLS, new ShowSeekControlsHandler());
    }

    /* access modifiers changed from: private */
    public void removeLiveEventHandlers() {
        Log.v(TAG, "removeLiveEventHandlers");
        removeListener(EventType.BUFFERING_COMPLETED);
        removeListener(EventType.HIDE_SEEK_CONTROLS);
        removeListener(EventType.SHOW_SEEK_CONTROLS);
    }

    public int getVisibilityState() {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("getVisibilityState: isLive = ");
        sb.append(this.videoView.getVideoDisplay().isLive());
        Log.v(str, sb.toString());
        return this.videoView.getVideoDisplay().isLive() ? 0 : 8;
    }

    /* access modifiers changed from: private */
    public void updateLiveState(boolean z) {
        this.liveEdgeState = z;
        if (this.isTvMode) {
            setDrawableFilter(z ? this.liveColorFilter : this.nonLiveColorFilter);
            return;
        }
        int i = z ? this.liveForegroundColor : this.nonLiveForegroundColor;
        if (getButton() != null) {
            getButton().setTextColor(i);
        }
    }

    private void setDrawableFilter(ColorFilter colorFilter) {
        if (getButton() != null) {
            Drawable[] compoundDrawables = getButton().getCompoundDrawables();
            if (compoundDrawables.length > 0) {
                Drawable drawable = compoundDrawables[0];
                if (drawable != null) {
                    drawable.setColorFilter(colorFilter);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void seekToLiveEdge() {
        if (!this.liveEdgeState) {
            updateLiveState(true);
            this.seekingToLiveEdge = true;
            this.videoView.seekToLive();
            if (!this.videoView.isPlaying()) {
                this.videoView.start();
            }
        }
    }
}
