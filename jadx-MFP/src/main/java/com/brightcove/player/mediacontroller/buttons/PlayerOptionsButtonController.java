package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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

public class PlayerOptionsButtonController extends AbstractButtonController {
    /* access modifiers changed from: private */
    public boolean isAudioTracksEnabled;
    /* access modifiers changed from: private */
    public boolean isCaptionsEnabled;
    /* access modifiers changed from: private */
    public boolean isTvMode;
    private OnClickListener onClickListener = new OnClickListener() {
        public void onClick(View view) {
            HashMap hashMap = new HashMap();
            hashMap.put(AbstractEvent.ANDROID_VIEW, view);
            PlayerOptionsButtonController.this.eventEmitter.emit(EventType.SHOW_PLAYER_OPTIONS, hashMap);
        }
    };

    public int getManagedState() {
        return 0;
    }

    public PlayerOptionsButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.player_options, typeface);
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.CLOSED_CAPTIONS_IMAGE);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_player_options, R.string.desc_player_options, image, this.onClickListener);
        list.add(buttonState);
        addListener(EventType.ENTER_TV_MODE, new EventListener() {
            public void processEvent(Event event) {
                PlayerOptionsButtonController.this.isTvMode = true;
            }
        });
        addListener(EventType.AUDIO_TRACKS, new EventListener() {
            @Default
            public void processEvent(Event event) {
                Object obj = event.properties.get(AbstractEvent.TRACKS);
                if (obj instanceof List) {
                    List list = (List) obj;
                    PlayerOptionsButtonController playerOptionsButtonController = PlayerOptionsButtonController.this;
                    boolean z = true;
                    if (list.size() <= 1) {
                        z = false;
                    }
                    playerOptionsButtonController.isAudioTracksEnabled = z;
                    PlayerOptionsButtonController.this.updateVisibility();
                }
            }
        });
        addListener(EventType.CAPTIONS_LANGUAGES, new EventListener() {
            public void processEvent(Event event) {
                List list = (List) event.properties.get(AbstractEvent.LANGUAGES);
                PlayerOptionsButtonController.this.isCaptionsEnabled = list != null && !list.isEmpty();
                PlayerOptionsButtonController.this.updateVisibility();
            }
        });
    }

    public int getVisibilityState() {
        return (!this.isTvMode || (!this.isAudioTracksEnabled && !this.isCaptionsEnabled)) ? 8 : 0;
    }

    /* access modifiers changed from: private */
    public void updateVisibility() {
        setVisibility(getVisibilityState());
    }
}
