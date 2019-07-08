package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveControlBar;
import com.brightcove.player.pictureinpicture.PictureInPictureManager;
import com.brightcove.player.view.BaseVideoView;
import java.util.List;

public class PictureInPictureButtonController extends AbstractButtonController {
    /* access modifiers changed from: private */
    public boolean shouldBeVisible;

    private class PipHandler implements EventListener {
        private PipHandler() {
        }

        public void processEvent(Event event) {
            PictureInPictureButtonController.this.syncStates();
        }
    }

    public PictureInPictureButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface, Bundle bundle) {
        BrightcoveControlBar brightcoveControlBar2 = brightcoveControlBar;
        Bundle bundle2 = bundle;
        super(context, baseVideoView, brightcoveControlBar, R.id.picture_in_picture, typeface);
        Drawable image = brightcoveControlBar2.getImage(BrightcoveControlBar.PICTURE_IN_PICTURE_ON_IMAGE);
        Drawable image2 = brightcoveControlBar2.getImage(BrightcoveControlBar.PICTURE_IN_PICTURE_OFF_IMAGE);
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(View view) {
                PictureInPictureButtonController.this.eventEmitter.emit(EventType.EXIT_PICTURE_IN_PICTURE_MODE);
            }
        };
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context, R.string.brightcove_picture_in_picture_on, R.string.desc_picture_in_picture, image, EventType.ENTER_PICTURE_IN_PICTURE_MODE);
        list.add(buttonState);
        List list2 = this.stateList;
        ButtonState buttonState2 = new ButtonState(context, R.string.brightcove_picture_in_picture_off, R.string.desc_picture_in_picture, image2, (OnClickListener) r0);
        list2.add(buttonState2);
        PipHandler pipHandler = new PipHandler();
        addListener(EventType.DID_ENTER_PICTURE_IN_PICTURE_MODE, pipHandler);
        addListener(EventType.DID_EXIT_PICTURE_IN_PICTURE_MODE, pipHandler);
        syncStates();
        addListener(EventType.DID_SET_VIDEO, new EventListener() {
            public void processEvent(Event event) {
                PictureInPictureButtonController.this.shouldBeVisible = true;
                PictureInPictureButtonController pictureInPictureButtonController = PictureInPictureButtonController.this;
                pictureInPictureButtonController.setVisibility(pictureInPictureButtonController.getVisibilityState());
            }
        });
        this.shouldBeVisible = bundle2 != null && bundle2.containsKey(AbstractEvent.PICTURE_IN_PICTURE_STATE) && bundle2.getBoolean(AbstractEvent.PICTURE_IN_PICTURE_STATE);
    }

    public int getManagedState() {
        return this.videoView.getPictureInPictureManager().isInPictureInPictureMode() ? 1 : 0;
    }

    public int getVisibilityState() {
        return (!PictureInPictureManager.getInstance().isPictureInPictureEnabled() || !this.shouldBeVisible) ? 8 : 0;
    }
}
