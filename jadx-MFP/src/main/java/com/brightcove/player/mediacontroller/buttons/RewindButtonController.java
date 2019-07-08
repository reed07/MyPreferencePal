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

public class RewindButtonController extends SeekButtonController {
    private EventListener didSeekHandler = new EventListener() {
        @Default
        public void processEvent(Event event) {
            int integerProperty = event.getIntegerProperty(AbstractEvent.FROM_SEEK_POSITION);
            int integerProperty2 = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
            if (integerProperty == RewindButtonController.this.seekStartPosition && integerProperty2 == RewindButtonController.this.seekTargetPosition) {
                RewindButtonController.this.eventEmitter.emit(EventType.DID_REWIND, event.properties);
            }
        }
    };

    /* access modifiers changed from: protected */
    public int computeTargetSeekPosition(int i, int i2) {
        int i3 = i - i2;
        if (i3 > 0) {
            return i3;
        }
        return 0;
    }

    public RewindButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.rewind, typeface, EventType.REWIND);
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.REWIND_IMAGE);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_rewind, R.string.desc_rewind, image, EventType.REWIND);
        list.add(buttonState);
    }

    public EventListener getDidSeekHandler() {
        return this.didSeekHandler;
    }

    public boolean onRewind(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            this.eventEmitter.emit(EventType.REWIND);
        }
        return handleSeekEvent(keyEvent);
    }
}
