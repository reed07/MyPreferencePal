package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.mediacontroller.BrightcoveControlBar;
import com.brightcove.player.view.BaseVideoView;
import java.util.List;

@ListensFor(events = {"activityResumed", "captionsDialogOk", "captionsDialogSettings", "fragmentResumed", "enterTvMode", "willChangeVideo", "adBreakStarted", "adBreakCompleted", "adProgress"})
public class CaptionsButtonController extends AbstractButtonController {
    /* access modifiers changed from: private */
    public static final String TAG = "CaptionsButtonController";
    /* access modifiers changed from: private */
    public int activityResumedToken;
    /* access modifiers changed from: private */
    public int captionsDialogOkToken;
    /* access modifiers changed from: private */
    public int captionsDialogSettingsToken;
    /* access modifiers changed from: private */
    public boolean captionsState;
    /* access modifiers changed from: private */
    public int fragmentResumedToken;
    /* access modifiers changed from: private */
    public boolean isPlayingAds;
    /* access modifiers changed from: private */
    public boolean isTvMode;
    /* access modifiers changed from: private */
    public EventListener showButtonListener = new EventListener() {
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.ANDROID_VIEW);
            if (CaptionsButtonController.this.captionsState && (obj instanceof View)) {
                ((ButtonState) CaptionsButtonController.this.getStateList().get(CaptionsButtonController.this.getManagedState())).getHandler().onClick((View) obj);
            }
        }
    };

    private class AdBreakListener implements EventListener {
        private AdBreakListener() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x003d  */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x004c  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x0052  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0060 A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:28:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processEvent(com.brightcove.player.event.Event r5) {
            /*
                r4 = this;
                java.lang.String r5 = r5.getType()
                int r0 = r5.hashCode()
                r1 = -1245394161(0xffffffffb5c4cb0f, float:-1.4662236E-6)
                r2 = 0
                r3 = 1
                if (r0 == r1) goto L_0x002e
                r1 = -167414203(0xfffffffff6057645, float:-6.767327E32)
                if (r0 == r1) goto L_0x0024
                r1 = 1119772528(0x42be5f70, float:95.1864)
                if (r0 == r1) goto L_0x001a
                goto L_0x0038
            L_0x001a:
                java.lang.String r0 = "adProgress"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L_0x0038
                r5 = 2
                goto L_0x0039
            L_0x0024:
                java.lang.String r0 = "adBreakStarted"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L_0x0038
                r5 = 0
                goto L_0x0039
            L_0x002e:
                java.lang.String r0 = "adBreakCompleted"
                boolean r5 = r5.equals(r0)
                if (r5 == 0) goto L_0x0038
                r5 = 1
                goto L_0x0039
            L_0x0038:
                r5 = -1
            L_0x0039:
                switch(r5) {
                    case 0: goto L_0x0052;
                    case 1: goto L_0x004c;
                    case 2: goto L_0x003d;
                    default: goto L_0x003c;
                }
            L_0x003c:
                goto L_0x0057
            L_0x003d:
                com.brightcove.player.mediacontroller.buttons.CaptionsButtonController r5 = com.brightcove.player.mediacontroller.buttons.CaptionsButtonController.this
                boolean r5 = r5.isPlayingAds
                if (r5 == 0) goto L_0x0046
                goto L_0x0058
            L_0x0046:
                com.brightcove.player.mediacontroller.buttons.CaptionsButtonController r5 = com.brightcove.player.mediacontroller.buttons.CaptionsButtonController.this
                r5.isPlayingAds = r3
                goto L_0x0057
            L_0x004c:
                com.brightcove.player.mediacontroller.buttons.CaptionsButtonController r5 = com.brightcove.player.mediacontroller.buttons.CaptionsButtonController.this
                r5.isPlayingAds = r2
                goto L_0x0057
            L_0x0052:
                com.brightcove.player.mediacontroller.buttons.CaptionsButtonController r5 = com.brightcove.player.mediacontroller.buttons.CaptionsButtonController.this
                r5.isPlayingAds = r3
            L_0x0057:
                r2 = 1
            L_0x0058:
                com.brightcove.player.mediacontroller.buttons.CaptionsButtonController r5 = com.brightcove.player.mediacontroller.buttons.CaptionsButtonController.this
                boolean r5 = r5.isTvMode
                if (r5 != 0) goto L_0x006b
                if (r2 == 0) goto L_0x006b
                com.brightcove.player.mediacontroller.buttons.CaptionsButtonController r5 = com.brightcove.player.mediacontroller.buttons.CaptionsButtonController.this
                int r0 = r5.getVisibilityState()
                r5.setVisibility(r0)
            L_0x006b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.mediacontroller.buttons.CaptionsButtonController.AdBreakListener.processEvent(com.brightcove.player.event.Event):void");
        }
    }

    private class CaptionsDialogLauncher implements OnClickListener {
        private CaptionsDialogLauncher() {
        }

        public void onClick(View view) {
            Log.d(CaptionsButtonController.TAG, "Showing the captions dialog.");
            if (CaptionsButtonController.this.videoView.isPlaying()) {
                CaptionsButtonController.this.videoView.pause();
                CaptionsButtonController captionsButtonController = CaptionsButtonController.this;
                captionsButtonController.captionsDialogOkToken = captionsButtonController.eventEmitter.once(EventType.CAPTIONS_DIALOG_OK, new EventListener() {
                    public void processEvent(Event event) {
                        CaptionsButtonController.this.videoView.start();
                        CaptionsButtonController.this.eventEmitter.off(EventType.CAPTIONS_DIALOG_SETTINGS, CaptionsButtonController.this.captionsDialogSettingsToken);
                    }
                });
                CaptionsButtonController captionsButtonController2 = CaptionsButtonController.this;
                captionsButtonController2.captionsDialogSettingsToken = captionsButtonController2.eventEmitter.once(EventType.CAPTIONS_DIALOG_SETTINGS, new EventListener() {
                    public void processEvent(Event event) {
                        CaptionsButtonController.this.activityResumedToken = CaptionsButtonController.this.eventEmitter.once(EventType.ACTIVITY_RESUMED, new EventListener() {
                            public void processEvent(Event event) {
                                CaptionsButtonController.this.videoView.start();
                                CaptionsButtonController.this.eventEmitter.off(EventType.FRAGMENT_RESUMED, CaptionsButtonController.this.fragmentResumedToken);
                            }
                        });
                        CaptionsButtonController.this.fragmentResumedToken = CaptionsButtonController.this.eventEmitter.once(EventType.FRAGMENT_RESUMED, new EventListener() {
                            public void processEvent(Event event) {
                                CaptionsButtonController.this.videoView.start();
                                CaptionsButtonController.this.eventEmitter.off(EventType.ACTIVITY_RESUMED, CaptionsButtonController.this.activityResumedToken);
                            }
                        });
                        CaptionsButtonController.this.eventEmitter.off(EventType.CAPTIONS_DIALOG_OK, CaptionsButtonController.this.captionsDialogOkToken);
                    }
                });
            }
            CaptionsButtonController.this.videoView.getClosedCaptioningController().showCaptionsDialog();
        }
    }

    private class CaptionsStateCapture implements EventListener {
        private CaptionsStateCapture() {
        }

        public void processEvent(Event event) {
            List list = (List) event.properties.get(AbstractEvent.LANGUAGES);
            CaptionsButtonController.this.captionsState = list != null && !list.isEmpty();
            if (!CaptionsButtonController.this.isTvMode) {
                CaptionsButtonController captionsButtonController = CaptionsButtonController.this;
                captionsButtonController.setVisibility(captionsButtonController.getVisibilityState());
            }
        }
    }

    public int getManagedState() {
        return 0;
    }

    public CaptionsButtonController(Context context, BaseVideoView baseVideoView, BrightcoveControlBar brightcoveControlBar, Typeface typeface, Bundle bundle) {
        Context context2 = context;
        super(context2, baseVideoView, brightcoveControlBar, R.id.captions, typeface);
        CaptionsDialogLauncher captionsDialogLauncher = new CaptionsDialogLauncher();
        Drawable image = brightcoveControlBar.getImage(BrightcoveControlBar.CLOSED_CAPTIONS_IMAGE);
        List list = this.stateList;
        ButtonState buttonState = new ButtonState(context2, R.string.brightcove_controls_captions, R.string.desc_captions, image, (OnClickListener) captionsDialogLauncher);
        list.add(buttonState);
        addListener(EventType.CAPTIONS_LANGUAGES, new CaptionsStateCapture());
        this.captionsState = bundle != null && bundle.containsKey(AbstractEvent.CAPTIONS_STATE) && bundle.getBoolean(AbstractEvent.CAPTIONS_STATE);
        addListener(EventType.ENTER_TV_MODE, new EventListener() {
            public void processEvent(Event event) {
                CaptionsButtonController.this.isTvMode = true;
                CaptionsButtonController captionsButtonController = CaptionsButtonController.this;
                captionsButtonController.addListener(EventType.SHOW_PLAYER_OPTIONS, captionsButtonController.showButtonListener);
            }
        });
        addListener(EventType.WILL_CHANGE_VIDEO, new EventListener() {
            public void processEvent(Event event) {
                CaptionsButtonController.this.captionsState = false;
            }
        });
        AdBreakListener adBreakListener = new AdBreakListener();
        addListener(EventType.AD_BREAK_STARTED, adBreakListener);
        addListener(EventType.AD_BREAK_COMPLETED, adBreakListener);
        addListener(EventType.AD_PROGRESS, adBreakListener);
    }

    public int getVisibilityState() {
        return !this.isPlayingAds && this.captionsState ? 0 : 8;
    }
}
