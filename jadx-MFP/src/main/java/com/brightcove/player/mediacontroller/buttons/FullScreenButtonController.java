package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import com.brightcove.player.R;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveControlBar;
import com.brightcove.player.view.BaseVideoView;
import java.util.List;

public class FullScreenButtonController extends AbstractButtonController {
    private static final int BUTTON_STATE_ENTER_FULL_SCREEN = 0;
    private static final int BUTTON_STATE_EXIT_FULL_SCREEN = 1;
    private static final String TAG = "FullScreenButtonController";

    private class FullScreenHandler implements EventListener {
        private FullScreenHandler() {
        }

        @Default
        public void processEvent(Event event) {
            FullScreenButtonController.this.syncStates();
        }
    }

    public FullScreenButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.full_screen, typeface);
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.ENTER_FULL_SCREEN_IMAGE);
        Drawable image2 = brightcoveControlBar.getImage(BrightcoveControlBar.EXIT_FULL_SCREEN_IMAGE);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_enter_full_screen, R.string.desc_enter_full_screen, image, EventType.ENTER_FULL_SCREEN);
        list.add(buttonState);
        List list2 = this.stateList;
        ButtonState buttonState2 = new ButtonState(context, R.string.brightcove_controls_exit_full_screen, R.string.desc_exit_full_screen, image2, EventType.EXIT_FULL_SCREEN);
        list2.add(buttonState2);
        FullScreenHandler fullScreenHandler = new FullScreenHandler();
        addListener(EventType.ENTER_FULL_SCREEN, fullScreenHandler);
        addListener(EventType.EXIT_FULL_SCREEN, fullScreenHandler);
        addListener(EventType.DID_ENTER_FULL_SCREEN, fullScreenHandler);
        addListener(EventType.DID_EXIT_FULL_SCREEN, fullScreenHandler);
        addListener(EventType.ENTERED_VR_MODE, new EventListener() {
            public void processEvent(Event event) {
                FullScreenButtonController.this.setVisibility(8);
            }
        });
        addListener(EventType.EXITED_VR_MODE, new EventListener() {
            public void processEvent(Event event) {
                FullScreenButtonController.this.setVisibility(0);
            }
        });
        syncStates();
    }

    public int getManagedState() {
        return this.videoView.isFullScreen() ? 1 : 0;
    }

    public int getVisibilityState() {
        if (this.videoView.getRenderView().isVrMode()) {
            return 8;
        }
        return super.getVisibilityState();
    }
}
