package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import com.brightcove.player.R;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveControlBar;
import com.brightcove.player.view.BaseVideoView;
import java.util.List;

public class CloseButtonController extends AbstractButtonController {
    private static final String TAG = "CloseButtonController";

    public int getManagedState() {
        return 0;
    }

    public CloseButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.close, typeface);
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.CLOSE_IMAGE);
        AnonymousClass1 r5 = new OnClickListener() {
            public void onClick(View view) {
                CloseButtonController.this.eventEmitter.emit(EventType.EXIT_PICTURE_IN_PICTURE_MODE);
            }
        };
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_close, R.string.desc_close, image, (OnClickListener) r5);
        list.add(buttonState);
        syncStates();
    }
}
