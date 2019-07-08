package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveControlBar;
import com.brightcove.player.view.BaseVideoView;
import java.util.List;

public class FastForwardButtonController extends SeekButtonController {
    private EventListener didSeekHandler = new EventListener() {
        @Default
        public void processEvent(Event event) {
            int integerProperty = event.getIntegerProperty(AbstractEvent.FROM_SEEK_POSITION);
            int integerProperty2 = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
            if (integerProperty == FastForwardButtonController.this.seekStartPosition && integerProperty2 == FastForwardButtonController.this.seekTargetPosition) {
                FastForwardButtonController.this.eventEmitter.emit(EventType.DID_FAST_FORWARD, event.properties);
            }
        }
    };

    public FastForwardButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.fast_forward, typeface, EventType.FAST_FORWARD);
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.FAST_FORWARD);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_fast_forward, R.string.desc_fast_forward, image, EventType.FAST_FORWARD);
        list.add(buttonState);
    }

    /* access modifiers changed from: protected */
    public int computeTargetSeekPosition(int i, int i2) {
        int i3;
        if (this.videoView.getVideoDisplay().isLive()) {
            i3 = this.videoView.getVideoDisplay().getLiveEdge();
            if (i3 < 0) {
                i3 = i;
            }
        } else {
            i3 = this.videoView.getDuration();
        }
        int i4 = i + i2;
        return i4 < i3 ? i4 : i3;
    }

    public EventListener getDidSeekHandler() {
        return this.didSeekHandler;
    }

    public boolean onFastForward(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            this.eventEmitter.emit(EventType.FAST_FORWARD);
        }
        return handleSeekEvent(keyEvent);
    }
}
