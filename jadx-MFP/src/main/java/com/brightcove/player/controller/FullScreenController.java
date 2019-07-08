package com.brightcove.player.controller;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.view.BaseVideoView;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.Locale;

public final class FullScreenController {
    /* access modifiers changed from: private */
    public static final String TAG = "FullScreenController";
    private ActionBar actionBar;
    /* access modifiers changed from: private */
    public Window activityWindow;
    /* access modifiers changed from: private */
    public EventEmitter eventEmitter;
    /* access modifiers changed from: private */
    public boolean isFullscreen = false;
    /* access modifiers changed from: private */
    public Integer originalLayoutParamsHeight;
    /* access modifiers changed from: private */
    public Integer originalLayoutParamsWidth;
    /* access modifiers changed from: private */
    public BaseVideoView videoView;

    private class EnterFullScreenHandler implements EventListener {
        private EnterFullScreenHandler() {
        }

        @Default
        public void processEvent(Event event) {
            onEnterFullScreen();
            FullScreenController.this.eventEmitter.emit(EventType.DID_ENTER_FULL_SCREEN);
        }

        private void onEnterFullScreen() {
            FullScreenController.this.setActionBarVisibility(false);
            LayoutParams attributes = FullScreenController.this.activityWindow.getAttributes();
            attributes.flags |= 1024;
            FullScreenController.this.activityWindow.setAttributes(attributes);
            ViewGroup.LayoutParams layoutParams = FullScreenController.this.videoView.getLayoutParams();
            FullScreenController.this.originalLayoutParamsWidth = Integer.valueOf(layoutParams.width);
            FullScreenController.this.originalLayoutParamsHeight = Integer.valueOf(layoutParams.height);
            Log.v(FullScreenController.TAG, String.format(Locale.getDefault(), "Saving normal screen size: %dx%d.", new Object[]{FullScreenController.this.originalLayoutParamsWidth, FullScreenController.this.originalLayoutParamsHeight}));
            layoutParams.width = -1;
            layoutParams.height = -1;
            FullScreenController.this.videoView.setLayoutParams(layoutParams);
        }
    }

    private class ExitFullScreenHandler implements EventListener {
        private ExitFullScreenHandler() {
        }

        @Default
        public void processEvent(Event event) {
            if (FullScreenController.this.videoView.getRenderView().isVrMode()) {
                Log.d(FullScreenController.TAG, "Cannot exit fullscreen mode, when VR mode is active!");
                return;
            }
            onExitFullScreen();
            FullScreenController.this.eventEmitter.emit(EventType.DID_EXIT_FULL_SCREEN);
        }

        private void onExitFullScreen() {
            String access$900 = FullScreenController.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Back to normal screen: ");
            sb.append(FullScreenController.this.originalLayoutParamsWidth);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(FullScreenController.this.originalLayoutParamsHeight);
            Log.v(access$900, sb.toString());
            if (FullScreenController.this.originalLayoutParamsWidth != null && FullScreenController.this.originalLayoutParamsHeight != null) {
                FullScreenController.this.setActionBarVisibility(true);
                LayoutParams attributes = FullScreenController.this.activityWindow.getAttributes();
                attributes.flags ^= 1024;
                FullScreenController.this.activityWindow.setAttributes(attributes);
                ViewGroup.LayoutParams layoutParams = FullScreenController.this.videoView.getLayoutParams();
                layoutParams.width = FullScreenController.this.originalLayoutParamsWidth.intValue();
                layoutParams.height = FullScreenController.this.originalLayoutParamsHeight.intValue();
                FullScreenController.this.videoView.setLayoutParams(layoutParams);
                FullScreenController.this.originalLayoutParamsWidth = null;
                FullScreenController.this.originalLayoutParamsHeight = null;
            }
        }
    }

    @TargetApi(11)
    public FullScreenController(BaseVideoView baseVideoView) {
        boolean z = false;
        this.videoView = baseVideoView;
        this.eventEmitter = baseVideoView.getEventEmitter();
        Context context = baseVideoView.getContext();
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity == null || this.eventEmitter == null) {
            Log.wtf(TAG, getErrorMessage(activity, this.eventEmitter));
            return;
        }
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        this.actionBar = z ? activity.getActionBar() : null;
        this.activityWindow = activity.getWindow();
        this.eventEmitter.on(EventType.ENTER_FULL_SCREEN, new EnterFullScreenHandler());
        this.eventEmitter.on(EventType.EXIT_FULL_SCREEN, new ExitFullScreenHandler());
        this.eventEmitter.on(EventType.DID_ENTER_FULL_SCREEN, new EventListener() {
            public void processEvent(Event event) {
                FullScreenController.this.isFullscreen = true;
            }
        });
        this.eventEmitter.on(EventType.DID_EXIT_FULL_SCREEN, new EventListener() {
            public void processEvent(Event event) {
                FullScreenController.this.isFullscreen = false;
            }
        });
    }

    public boolean isFullScreen() {
        return this.isFullscreen;
    }

    /* access modifiers changed from: private */
    @TargetApi(11)
    public void setActionBarVisibility(boolean z) {
        ActionBar actionBar2 = this.actionBar;
        if (actionBar2 == null) {
            return;
        }
        if (z) {
            actionBar2.show();
        } else {
            actionBar2.hide();
        }
    }

    private String getErrorMessage(Activity activity, EventEmitter eventEmitter2) {
        StringBuilder sb = new StringBuilder("Aborting because ");
        if (activity == null && eventEmitter2 != null) {
            sb.append("the video view context is invalid (not an Activity)");
        } else if (activity == null || eventEmitter2 != null) {
            sb.append("both ");
            sb.append("the video view context is invalid (not an Activity)");
            sb.append(" and ");
            sb.append("the event emitter is invalid, it is null");
        } else {
            sb.append("the event emitter is invalid, it is null");
        }
        sb.append(".");
        return sb.toString();
    }
}
