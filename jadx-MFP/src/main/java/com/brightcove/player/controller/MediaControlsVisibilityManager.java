package com.brightcove.player.controller;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.view.BaseVideoView;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MediaControlsVisibilityManager {
    private static final String MEDIA_CONTROLS_VISIBILITY_STATE = "mediaControlsVisibilityState";
    /* access modifiers changed from: private */
    public static final String TAG = "MediaControlsVisibilityManager";
    private BaseVideoView videoView;
    /* access modifiers changed from: private */
    public final Map<String, Integer> visibilityMap = new HashMap();

    private class ShowHideEventHandler implements EventListener {
        private ShowHideEventHandler() {
        }

        @Default
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.SEEK_CONTROLS_VISIBILITY);
            if (obj != null && (obj instanceof Map)) {
                Map map = (Map) obj;
                MediaControlsVisibilityManager.this.visibilityMap.clear();
                MediaControlsVisibilityManager.this.visibilityMap.putAll(map);
                MediaControlsVisibilityManager.this.setVisibilityState();
            }
        }
    }

    private class VisibilityRestoreEventHandler implements EventListener {
        private VisibilityRestoreEventHandler() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(MediaControlsVisibilityManager.TAG, "Handling an ACTIVITY_CREATED event to (possibly) restore the media controls visibility state...");
            Object obj = event.properties.get(AbstractEvent.INSTANCE_STATE);
            Bundle bundle = null;
            Bundle bundle2 = (obj == null || !(obj instanceof Bundle)) ? null : (Bundle) obj;
            if (bundle2 != null) {
                bundle = bundle2.getBundle(MediaControlsVisibilityManager.MEDIA_CONTROLS_VISIBILITY_STATE);
            }
            if (bundle != null) {
                MediaControlsVisibilityManager.this.restoreVisibilityState(bundle);
            } else {
                Log.v(MediaControlsVisibilityManager.TAG, "No saved visibility state bundle found.  Restoration aborted.");
            }
        }
    }

    private class VisibilitySaveEventHandler implements EventListener {
        private VisibilitySaveEventHandler() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(MediaControlsVisibilityManager.TAG, "Handling an ACTIVITY_SAVE_INSTANCE_STATE event to save the media controls visibility state...");
            Object obj = event.properties.get(AbstractEvent.INSTANCE_STATE);
            Bundle bundle = (obj == null || !(obj instanceof Bundle)) ? null : (Bundle) obj;
            if (bundle != null && MediaControlsVisibilityManager.this.visibilityMap.size() > 0) {
                MediaControlsVisibilityManager.this.saveVisibilityState(bundle);
            }
        }
    }

    public MediaControlsVisibilityManager(BaseVideoView baseVideoView) {
        this.videoView = baseVideoView;
    }

    public void initListeners(EventEmitter eventEmitter) {
        ShowHideEventHandler showHideEventHandler = new ShowHideEventHandler();
        eventEmitter.on(EventType.HIDE_SEEK_CONTROLS, showHideEventHandler);
        eventEmitter.on(EventType.SHOW_SEEK_CONTROLS, showHideEventHandler);
        eventEmitter.on(EventType.ACTIVITY_CREATED, new VisibilityRestoreEventHandler());
        eventEmitter.on(EventType.ACTIVITY_SAVE_INSTANCE_STATE, new VisibilitySaveEventHandler());
    }

    public void setVisibilityState() {
        Resources system = Resources.getSystem();
        MediaController mediaController = this.videoView.getMediaController();
        if (mediaController == null) {
            Log.e(TAG, "The visibility state cannot be restored!  No media controller exists.");
            return;
        }
        Log.v(TAG, "Updating the media controls visibility state...");
        for (String str : this.visibilityMap.keySet()) {
            View findViewById = mediaController.findViewById(system.getIdentifier(str, "id", "android"));
            if (findViewById != null) {
                findViewById.setVisibility(((Integer) this.visibilityMap.get(str)).intValue());
            } else {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("View/button: ");
                sb.append(str);
                sb.append(" does not exist!");
                Log.w(str2, sb.toString());
            }
        }
        mediaController.show();
    }

    /* access modifiers changed from: private */
    public void saveVisibilityState(Bundle bundle) {
        Log.v(TAG, "Saving media controls visibility state...");
        Bundle bundle2 = new Bundle();
        bundle.putBundle(MEDIA_CONTROLS_VISIBILITY_STATE, bundle2);
        for (String str : this.visibilityMap.keySet()) {
            bundle2.putInt(str, ((Integer) this.visibilityMap.get(str)).intValue());
        }
    }

    /* access modifiers changed from: private */
    public void restoreVisibilityState(Bundle bundle) {
        Log.v(TAG, "Restoring media controls visibility state...");
        this.visibilityMap.clear();
        for (String str : bundle.keySet()) {
            int i = bundle.getInt(str);
            if (i == 0) {
                Log.w(TAG, String.format(Locale.getDefault(), "Invalid visibility state (0) detected using key: %s.", new Object[]{str}));
            } else {
                this.visibilityMap.put(str, Integer.valueOf(i));
            }
        }
    }
}
