package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveControlBar;
import com.brightcove.player.util.LayoutUtil;
import com.brightcove.player.view.BaseVideoView;
import com.brightcove.player.view.RenderView;
import java.util.Collections;
import java.util.List;

public class VRButtonController extends AbstractButtonController {
    /* access modifiers changed from: private */
    public final boolean largeScreen;

    private class EventHandler implements EventListener {
        private EventHandler() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0058  */
        /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processEvent(com.brightcove.player.event.Event r5) {
            /*
                r4 = this;
                java.lang.String r0 = r5.getType()
                int r1 = r0.hashCode()
                r2 = -1465496402(0xffffffffa8a64cae, float:-1.8462957E-14)
                r3 = 0
                if (r1 == r2) goto L_0x001e
                r2 = 1792586013(0x6ad8b11d, float:1.3098219E26)
                if (r1 == r2) goto L_0x0014
                goto L_0x0028
            L_0x0014:
                java.lang.String r1 = "activityPaused"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0028
                r0 = 0
                goto L_0x0029
            L_0x001e:
                java.lang.String r1 = "projectionFormatChanged"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0028
                r0 = 1
                goto L_0x0029
            L_0x0028:
                r0 = -1
            L_0x0029:
                switch(r0) {
                    case 0: goto L_0x0058;
                    case 1: goto L_0x002d;
                    default: goto L_0x002c;
                }
            L_0x002c:
                goto L_0x006d
            L_0x002d:
                java.util.Map<java.lang.String, java.lang.Object> r5 = r5.properties
                java.lang.String r0 = "projectionFormat"
                java.lang.Object r5 = r5.get(r0)
                if (r5 == 0) goto L_0x006d
                boolean r0 = r5 instanceof com.brightcove.player.model.Video.ProjectionFormat
                if (r0 == 0) goto L_0x006d
                com.brightcove.player.model.Video$ProjectionFormat r5 = (com.brightcove.player.model.Video.ProjectionFormat) r5
                com.brightcove.player.mediacontroller.buttons.VRButtonController r0 = com.brightcove.player.mediacontroller.buttons.VRButtonController.this
                boolean r0 = r0.largeScreen
                if (r0 != 0) goto L_0x0050
                com.brightcove.player.model.Video$ProjectionFormat r0 = com.brightcove.player.model.Video.ProjectionFormat.NORMAL
                if (r5 != r0) goto L_0x004a
                goto L_0x0050
            L_0x004a:
                com.brightcove.player.mediacontroller.buttons.VRButtonController r5 = com.brightcove.player.mediacontroller.buttons.VRButtonController.this
                r5.setVisibility(r3)
                goto L_0x006d
            L_0x0050:
                com.brightcove.player.mediacontroller.buttons.VRButtonController r5 = com.brightcove.player.mediacontroller.buttons.VRButtonController.this
                r0 = 8
                r5.setVisibility(r0)
                goto L_0x006d
            L_0x0058:
                com.brightcove.player.mediacontroller.buttons.VRButtonController r5 = com.brightcove.player.mediacontroller.buttons.VRButtonController.this
                com.brightcove.player.view.BaseVideoView r5 = r5.videoView
                com.brightcove.player.view.RenderView r5 = r5.getRenderView()
                if (r5 == 0) goto L_0x006d
                boolean r0 = r5.isVrMode()
                if (r0 == 0) goto L_0x006d
                com.brightcove.player.mediacontroller.buttons.VRButtonController r0 = com.brightcove.player.mediacontroller.buttons.VRButtonController.this
                r0.exitVrMode(r5)
            L_0x006d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.mediacontroller.buttons.VRButtonController.EventHandler.processEvent(com.brightcove.player.event.Event):void");
        }
    }

    private class VRToggler implements OnClickListener {
        private VRToggler() {
        }

        public void onClick(View view) {
            RenderView renderView = VRButtonController.this.videoView.getRenderView();
            if (renderView == null) {
                return;
            }
            if (renderView.isVrMode()) {
                VRButtonController.this.exitVrMode(renderView);
                return;
            }
            VRButtonController.this.eventEmitter.emit(EventType.CHANGE_ORIENTATION, Collections.singletonMap(AbstractEvent.REQUESTED_ORIENTATION, Integer.valueOf(6)));
            VRButtonController.this.eventEmitter.emit(EventType.ENTER_FULL_SCREEN);
            renderView.setVrMode(true);
            VRButtonController.this.eventEmitter.emit(EventType.ENTERED_VR_MODE);
        }
    }

    public int getManagedState() {
        return 0;
    }

    public VRButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.vr_mode, typeface);
        this.largeScreen = LayoutUtil.isLargeScreen(context);
        VRToggler vRToggler = new VRToggler();
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.VR_MODE_IMAGE);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_vr_mode, R.string.desc_vr_mode, image, (OnClickListener) vRToggler);
        list.add(buttonState);
        EventHandler eventHandler = new EventHandler();
        addListener(EventType.ACTIVITY_PAUSED, eventHandler);
        addListener(EventType.PROJECTION_FORMAT_CHANGED, eventHandler);
        syncStates();
    }

    public int getVisibilityState() {
        return (this.largeScreen || !this.videoView.getVideoDisplay().isCurrentVideo360Mode()) ? 8 : 0;
    }

    /* access modifiers changed from: private */
    public void exitVrMode(@NonNull RenderView renderView) {
        this.eventEmitter.emit(EventType.CHANGE_ORIENTATION, Collections.singletonMap(AbstractEvent.REQUESTED_ORIENTATION, Integer.valueOf(4)));
        this.eventEmitter.emit(EventType.EXIT_FULL_SCREEN);
        renderView.setVrMode(false);
        this.eventEmitter.emit(EventType.EXITED_VR_MODE);
    }
}
