package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveControlBar;
import com.brightcove.player.view.BaseVideoView;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PlayButtonController extends AbstractButtonController {
    /* access modifiers changed from: private */
    public static final String TAG = "PlayButtonController";

    private class PlayLauncher implements OnClickListener {
        private PlayLauncher() {
        }

        public void onClick(View view) {
            Log.d(PlayButtonController.TAG, "Resuming play...");
            PlayButtonController.this.videoView.start();
        }
    }

    private class PlayPauseHandler implements EventListener {
        private PlayPauseHandler() {
        }

        @Default
        public void processEvent(Event event) {
            Log.d(PlayButtonController.TAG, String.format(Locale.getDefault(), "Process event: %s.", new Object[]{event.getType()}));
            PlayButtonController.this.syncStates();
        }
    }

    public PlayButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.play, typeface);
        PlayLauncher playLauncher = new PlayLauncher();
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.PLAY_IMAGE);
        Drawable image2 = brightcoveControlBar.getImage(BrightcoveControlBar.PAUSE_IMAGE);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context, R.string.brightcove_controls_play, R.string.desc_play, image, (OnClickListener) playLauncher);
        list.add(buttonState);
        List list2 = this.stateList;
        ButtonState buttonState2 = new ButtonState(context2, R.string.brightcove_controls_pause, R.string.desc_pause, image2, EventType.PAUSE);
        list2.add(buttonState2);
        PlayPauseHandler playPauseHandler = new PlayPauseHandler();
        addListener(EventType.DID_PLAY, playPauseHandler);
        addListener(EventType.DID_PAUSE, playPauseHandler);
        addListener(EventType.DID_SET_VIDEO, playPauseHandler);
        addListener(EventType.STOP, playPauseHandler);
        addListener(EventType.ACTIVITY_RESUMED, playPauseHandler);
        addListener("completed", playPauseHandler);
        syncStates();
    }

    public int getManagedState() {
        return this.videoView.isPlaying() ? 1 : 0;
    }

    public Map<String, Object> getProperties() {
        this.properties.clear();
        this.properties.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(this.videoView.getCurrentPosition()));
        this.properties.put(AbstractEvent.EVENT_SOURCE, this);
        return this.properties;
    }

    public boolean onPlayPause(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1) {
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.EVENT_SOURCE, this);
        if (this.videoView.isPlaying()) {
            this.eventEmitter.emit(EventType.PAUSE, hashMap);
        } else {
            this.eventEmitter.emit(EventType.PLAY, hashMap);
        }
        return true;
    }
}
