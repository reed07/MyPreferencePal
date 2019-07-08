package com.brightcove.player.view;

import android.app.Activity;
import android.app.PictureInPictureParams.Builder;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventLogger;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.pictureinpicture.PictureInPictureManager;
import com.brightcove.player.util.LifecycleUtil;

@ListensFor(events = {"activitySaveInstanceState"})
public class BrightcovePlayer extends Activity implements Component {
    public static final String TAG = "BrightcovePlayer";
    protected BaseVideoView brightcoveVideoView;
    /* access modifiers changed from: private */
    public EventLogger eventLogger;
    private LifecycleUtil lifecycleUtil;
    private boolean pictureInPictureEnabled;
    private Bundle savedInstanceState;

    public BrightcoveVideoView getBrightcoveVideoView() {
        BaseVideoView baseVideoView = this.brightcoveVideoView;
        if (baseVideoView instanceof BrightcoveVideoView) {
            return (BrightcoveVideoView) baseVideoView;
        }
        return null;
    }

    public BaseVideoView getBaseVideoView() {
        return this.brightcoveVideoView;
    }

    public void showClosedCaptioningDialog() {
        this.brightcoveVideoView.getClosedCaptioningController().showCaptionsDialog();
    }

    public void fullScreen() {
        if (!this.brightcoveVideoView.isFullScreen()) {
            this.brightcoveVideoView.getEventEmitter().emit(EventType.ENTER_FULL_SCREEN);
        } else {
            Log.e(TAG, "The video view is already in full screen mode.");
        }
    }

    public void normalScreen() {
        if (this.brightcoveVideoView.isFullScreen()) {
            this.brightcoveVideoView.getEventEmitter().emit(EventType.EXIT_FULL_SCREEN);
        } else {
            Log.e(TAG, "The video view is not in full screen mode!");
        }
    }

    public EventLogger getEventLogger() {
        return this.eventLogger;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.lifecycleUtil.onConfigurationChanged(configuration);
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.brightcoveVideoView != null) {
            LifecycleUtil lifecycleUtil2 = this.lifecycleUtil;
            if (lifecycleUtil2 == null || lifecycleUtil2.baseVideoView != this.brightcoveVideoView) {
                this.lifecycleUtil = new LifecycleUtil(this.brightcoveVideoView);
                attemptToRegisterPiPActivity();
                this.lifecycleUtil.onCreate(bundle, this);
                this.eventLogger = new EventLogger(this.brightcoveVideoView.getEventEmitter(), true, getClass().getSimpleName());
                return;
            }
        }
        this.savedInstanceState = bundle;
    }

    private void initializeLifecycleUtil(View view) {
        if (this.brightcoveVideoView == null) {
            findBaseVideoView(view);
            BaseVideoView baseVideoView = this.brightcoveVideoView;
            if (baseVideoView != null) {
                this.lifecycleUtil = new LifecycleUtil(baseVideoView);
                attemptToRegisterPiPActivity();
                this.lifecycleUtil.onCreate(this.savedInstanceState, this);
                this.eventLogger = new EventLogger(this.brightcoveVideoView.getEventEmitter(), true, getClass().getSimpleName());
            } else {
                throw new IllegalStateException("A BaseVideoView must be wired up to the layout.");
            }
        }
        this.savedInstanceState = null;
    }

    private void attemptToRegisterPiPActivity() {
        if (VERSION.SDK_INT >= 26) {
            try {
                setPictureInPictureParams(new Builder().build());
                PictureInPictureManager.getInstance().registerActivity(this, this.brightcoveVideoView);
                this.pictureInPictureEnabled = true;
            } catch (IllegalStateException unused) {
                this.pictureInPictureEnabled = false;
                Log.w(TAG, "This activity was not set to use Picture-in-Picture.");
            }
        }
    }

    private void findBaseVideoView(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (i < childCount) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof BaseVideoView) {
                    this.brightcoveVideoView = (BaseVideoView) childAt;
                    return;
                } else {
                    findBaseVideoView(childAt);
                    i++;
                }
            }
        }
    }

    public void setContentView(View view) {
        super.setContentView(view);
        initializeLifecycleUtil(view);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        initializeLifecycleUtil(findViewById(16908290));
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        initializeLifecycleUtil(view);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
        this.lifecycleUtil.activityOnStart();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.v(TAG, "onPause");
        super.onPause();
        this.lifecycleUtil.activityOnPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
        this.brightcoveVideoView.getEventEmitter().on(EventType.CHANGE_ORIENTATION, new EventListener() {
            public void processEvent(Event event) {
                BrightcovePlayer.this.setRequestedOrientation(event.getIntegerProperty(AbstractEvent.REQUESTED_ORIENTATION));
            }
        });
        this.lifecycleUtil.activityOnResume();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        Log.v(TAG, "onRestart");
        super.onRestart();
        this.lifecycleUtil.onRestart();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
        this.brightcoveVideoView.getEventEmitter().on(EventType.ACTIVITY_DESTROYED, new EventListener() {
            public void processEvent(Event event) {
                if (BrightcovePlayer.this.eventLogger != null) {
                    BrightcovePlayer.this.eventLogger.stop();
                }
            }
        });
        this.lifecycleUtil.activityOnDestroy();
        PictureInPictureManager.getInstance().unregisterActivity(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
        this.lifecycleUtil.activityOnStop();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(final Bundle bundle) {
        this.brightcoveVideoView.getEventEmitter().on(EventType.ACTIVITY_SAVE_INSTANCE_STATE, new EventListener() {
            @Default
            public void processEvent(Event event) {
                BrightcovePlayer.super.onSaveInstanceState(bundle);
            }
        });
        this.lifecycleUtil.activityOnSaveInstanceState(bundle);
    }

    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (this.pictureInPictureEnabled) {
            PictureInPictureManager.getInstance().onUserLeaveHint();
        }
    }

    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        super.onPictureInPictureModeChanged(z, configuration);
        PictureInPictureManager.getInstance().onPictureInPictureModeChanged(z, configuration);
    }
}
