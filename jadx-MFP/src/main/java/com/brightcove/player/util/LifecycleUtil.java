package com.brightcove.player.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.view.BaseVideoView;
import java.util.HashMap;

@ListensFor(events = {"activityDestroyed", "fragmentDestroyedView"})
@Emits(events = {"activityCreated", "activityDestroyed", "activityPaused", "activityRestarted", "activityResumed", "activitySaveInstanceState", "activityStarted", "activityStopped", "configurationChanged", "fragmentActivityCreated", "fragmentCreatedView", "fragmentDestroyed", "fragmentDestroyedView", "fragmentDetached", "fragmentPaused", "fragmentResumed", "fragmentSaveInstanceState", "fragmentStarted", "fragmentStopped", "fragmentViewStateRestored"})
public class LifecycleUtil extends AbstractComponent {
    public static final String POSITION = "position";
    public static final String TAG = "LifecycleUtil";
    public static final String VIDEO_HEIGHT = "height";
    public static final String VIDEO_WIDTH = "width";
    public static final String WAS_PLAYING = "wasPlaying";
    public BaseVideoView baseVideoView;
    private int position;
    private int videoHeight;
    private int videoWidth;
    private boolean wasPlaying;

    public LifecycleUtil(BaseVideoView baseVideoView2) {
        super(baseVideoView2.getEventEmitter(), LifecycleUtil.class);
        this.baseVideoView = baseVideoView2;
    }

    public void onConfigurationChanged(Configuration configuration) {
        HashMap hashMap = new HashMap();
        hashMap.put("configuration", configuration);
        this.baseVideoView.getEventEmitter().emit(EventType.CONFIGURATION_CHANGED, hashMap);
    }

    public void onActivityCreated(Bundle bundle) {
        HashMap hashMap = new HashMap();
        if (bundle != null) {
            hashMap.put(AbstractEvent.INSTANCE_STATE, bundle);
        }
        this.baseVideoView.getEventEmitter().emit(EventType.FRAGMENT_ACTIVITY_CREATED, hashMap);
    }

    public void onCreate(Bundle bundle, Activity activity) {
        if (bundle != null) {
            this.position = bundle.getInt("position");
            this.wasPlaying = bundle.getBoolean(WAS_PLAYING);
            this.videoWidth = bundle.getInt("width");
            this.videoHeight = bundle.getInt("height");
        }
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.ACTIVITY, activity);
        if (bundle != null) {
            hashMap.put(AbstractEvent.INSTANCE_STATE, bundle);
        }
        this.baseVideoView.getEventEmitter().emit(EventType.ACTIVITY_CREATED, hashMap);
    }

    public void onCreateView(Bundle bundle, Object obj) {
        if (bundle != null) {
            this.position = bundle.getInt("position");
            this.wasPlaying = bundle.getBoolean(WAS_PLAYING);
            this.videoWidth = bundle.getInt("width");
            this.videoHeight = bundle.getInt("height");
        }
        HashMap hashMap = new HashMap();
        if (obj instanceof Fragment) {
            hashMap.put(AbstractEvent.FRAGMENT, obj);
        } else {
            hashMap.put(AbstractEvent.SUPPORT_FRAGMENT, obj);
        }
        if (bundle != null) {
            hashMap.put(AbstractEvent.INSTANCE_STATE, bundle);
        }
        this.baseVideoView.getEventEmitter().emit(EventType.FRAGMENT_CREATED_VIEW, hashMap);
    }

    public void activityOnStart() {
        onStart();
        this.baseVideoView.getEventEmitter().emit(EventType.ACTIVITY_STARTED);
    }

    public void fragmentOnStart() {
        onStart();
        this.baseVideoView.getEventEmitter().emit(EventType.FRAGMENT_STARTED);
    }

    private void onStart() {
        if (this.baseVideoView.getCurrentIndex() != -1) {
            restoreState();
        } else {
            this.baseVideoView.getEventEmitter().once(EventType.VIDEO_DURATION_CHANGED, new EventListener() {
                @Default
                public void processEvent(Event event) {
                    LifecycleUtil.this.restoreState();
                }
            });
        }
        if (VERSION.SDK_INT >= 24) {
            resumePlaybackIfNeeded();
        }
    }

    /* access modifiers changed from: private */
    public void restoreState() {
        int i = this.position;
        if (i > 0) {
            this.baseVideoView.seekTo(i);
            this.position = -1;
        }
        if (this.videoWidth > 0 && this.videoHeight > 0) {
            this.baseVideoView.getRenderView().setVideoSize(this.videoWidth, this.videoHeight);
            this.videoWidth = -1;
            this.videoHeight = -1;
        }
    }

    public void activityOnPause() {
        onPause();
        this.baseVideoView.getEventEmitter().emit(EventType.ACTIVITY_PAUSED);
    }

    public void fragmentOnPause() {
        onPause();
        this.baseVideoView.getEventEmitter().emit(EventType.FRAGMENT_PAUSED);
    }

    private void onPause() {
        if (this.baseVideoView.isPlaying()) {
            this.wasPlaying = true;
        } else {
            this.wasPlaying = false;
        }
        this.position = this.baseVideoView.getCurrentPosition();
        this.videoWidth = this.baseVideoView.getVideoWidth();
        this.videoHeight = this.baseVideoView.getVideoHeight();
        if (VERSION.SDK_INT < 24 && this.wasPlaying) {
            this.baseVideoView.pause();
        }
    }

    public void activityOnResume() {
        onResume();
        this.baseVideoView.getEventEmitter().emit(EventType.ACTIVITY_RESUMED);
    }

    public void fragmentOnResume() {
        onResume();
        this.eventEmitter.emit(EventType.FRAGMENT_RESUMED);
    }

    private void onResume() {
        if (VERSION.SDK_INT < 24) {
            resumePlaybackIfNeeded();
        }
    }

    public void onRestart() {
        this.baseVideoView.getEventEmitter().emit(EventType.ACTIVITY_RESTARTED);
    }

    public void activityOnDestroy() {
        onDestroy();
        this.baseVideoView.getEventEmitter().emit(EventType.ACTIVITY_DESTROYED);
    }

    public void fragmentOnDestroy() {
        onDestroy();
        this.baseVideoView.getEventEmitter().emit(EventType.FRAGMENT_DESTROYED);
    }

    private void onDestroy() {
        this.baseVideoView.getEventEmitter().on(EventType.ACTIVITY_DESTROYED, new EventListener() {
            @Default
            public void processEvent(Event event) {
                LifecycleUtil.this.baseVideoView.getEventEmitter().off();
            }
        });
    }

    public void onDestroyView() {
        this.eventEmitter.emit(EventType.FRAGMENT_DESTROYED_VIEW);
    }

    public void onDetach() {
        this.eventEmitter.emit(EventType.FRAGMENT_DETACHED);
    }

    public void activityOnStop() {
        onStop();
        this.baseVideoView.getEventEmitter().emit(EventType.ACTIVITY_STOPPED);
    }

    public void fragmentOnStop() {
        onStop();
        this.eventEmitter.emit(EventType.FRAGMENT_STOPPED);
    }

    private void onStop() {
        if (VERSION.SDK_INT >= 24) {
            this.baseVideoView.pause();
        }
        this.baseVideoView.stopPlayback();
    }

    private void resumePlaybackIfNeeded() {
        if (!this.wasPlaying) {
            return;
        }
        if (this.baseVideoView.getCurrentIndex() != -1) {
            this.baseVideoView.start();
        } else {
            this.baseVideoView.getEventEmitter().once(EventType.VIDEO_DURATION_CHANGED, new EventListener() {
                @Default
                public void processEvent(Event event) {
                    LifecycleUtil.this.baseVideoView.start();
                }
            });
        }
    }

    public void activityOnSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.INSTANCE_STATE, bundle);
        this.baseVideoView.getEventEmitter().emit(EventType.ACTIVITY_SAVE_INSTANCE_STATE, hashMap);
    }

    public void fragmentOnSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.INSTANCE_STATE, bundle);
        this.baseVideoView.getEventEmitter().emit(EventType.FRAGMENT_SAVE_INSTANCE_STATE, hashMap);
    }

    private void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("position", this.position);
        bundle.putBoolean(WAS_PLAYING, this.wasPlaying);
    }

    public void onViewStateRestored(Bundle bundle) {
        HashMap hashMap = new HashMap();
        if (bundle != null) {
            hashMap.put(AbstractEvent.INSTANCE_STATE, bundle);
        }
        this.eventEmitter.emit(EventType.FRAGMENT_VIEW_STATE_RESTORED, hashMap);
    }
}
