package com.brightcove.player.mediacontroller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.buttons.PlayButtonController;
import com.brightcove.player.view.BaseVideoView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;

public class BrightcoveShowHideController extends AbstractComponent implements ShowHideController {
    private static final int HIDE = 1;
    private static final String TAG = "BrightcoveShowHideController";
    private int animationDuration = 0;
    private AnimationStyle animationStyle = AnimationStyle.FADE;
    private AnimationTerminator animationTerminator;
    /* access modifiers changed from: private */
    public BrightcoveControlBar controlBar;
    private boolean hideControllerEnable = true;
    private float initialY;
    private MediaHandler mediaHandler;
    private boolean showControllerEnable = true;
    private int[] showHideResources;
    private int showHideTimeout = 3000;
    private boolean showing;

    /* renamed from: com.brightcove.player.mediacontroller.BrightcoveShowHideController$5 reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$brightcove$player$mediacontroller$BrightcoveMediaController$AnimationStyle = new int[AnimationStyle.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.brightcove.player.mediacontroller.BrightcoveMediaController$AnimationStyle[] r0 = com.brightcove.player.mediacontroller.BrightcoveMediaController.AnimationStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$brightcove$player$mediacontroller$BrightcoveMediaController$AnimationStyle = r0
                int[] r0 = $SwitchMap$com$brightcove$player$mediacontroller$BrightcoveMediaController$AnimationStyle     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.brightcove.player.mediacontroller.BrightcoveMediaController$AnimationStyle r1 = com.brightcove.player.mediacontroller.BrightcoveMediaController.AnimationStyle.SLIDE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$brightcove$player$mediacontroller$BrightcoveMediaController$AnimationStyle     // Catch:{ NoSuchFieldError -> 0x001f }
                com.brightcove.player.mediacontroller.BrightcoveMediaController$AnimationStyle r1 = com.brightcove.player.mediacontroller.BrightcoveMediaController.AnimationStyle.FADE     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.mediacontroller.BrightcoveShowHideController.AnonymousClass5.<clinit>():void");
        }
    }

    @TargetApi(12)
    private class AnimationTerminator extends AnimatorListenerAdapter {
        private AnimationTerminator() {
        }

        public void onAnimationEnd(Animator animator) {
            BrightcoveShowHideController.this.controlBar.setVisibility(8);
        }
    }

    static class MediaHandler extends Handler {
        private final WeakReference<BrightcoveShowHideController> controllerWeakReference;

        MediaHandler(BrightcoveShowHideController brightcoveShowHideController) {
            this.controllerWeakReference = new WeakReference<>(brightcoveShowHideController);
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                BrightcoveShowHideController brightcoveShowHideController = (BrightcoveShowHideController) this.controllerWeakReference.get();
                if (brightcoveShowHideController != null) {
                    brightcoveShowHideController.hide();
                }
            }
        }
    }

    public boolean isShowControllerEnable() {
        return this.showControllerEnable;
    }

    public boolean isHideControllerEnable() {
        return this.hideControllerEnable;
    }

    public void setShowControllerEnable(boolean z) {
        this.showControllerEnable = z;
    }

    public void setHideControllerEnable(boolean z) {
        this.hideControllerEnable = z;
    }

    @TargetApi(12)
    public BrightcoveShowHideController(BrightcoveControlBar brightcoveControlBar, BaseVideoView baseVideoView) {
        super(baseVideoView.getEventEmitter());
        this.controlBar = brightcoveControlBar;
        this.animationDuration = baseVideoView.getContext().getResources().getInteger(17694720);
        if (VERSION.SDK_INT > 12) {
            this.animationTerminator = new AnimationTerminator();
            this.initialY = brightcoveControlBar.getY();
        }
        this.mediaHandler = new MediaHandler(this);
        AnonymousClass1 r3 = new EventListener() {
            public void processEvent(Event event) {
                BrightcoveShowHideController.this.hide();
            }
        };
        AnonymousClass2 r0 = new EventListener() {
            public void processEvent(Event event) {
                BrightcoveShowHideController.this.show();
            }
        };
        EventEmitter eventEmitter = baseVideoView.getEventEmitter();
        eventEmitter.on(ShowHideController.SHOW_MEDIA_CONTROLS, r0);
        eventEmitter.on(ShowHideController.HIDE_MEDIA_CONTROLS, r3);
        eventEmitter.on(EventType.ENTER_FULL_SCREEN, r3);
        eventEmitter.on(EventType.EXIT_FULL_SCREEN, r3);
        eventEmitter.on(EventType.DID_ENTER_FULL_SCREEN, r3);
        eventEmitter.on(EventType.DID_EXIT_FULL_SCREEN, r3);
    }

    @TargetApi(12)
    public void hide() {
        if (this.hideControllerEnable) {
            this.showing = false;
            if (VERSION.SDK_INT < 12) {
                this.controlBar.setVisibility(8);
            } else if (AnonymousClass5.$SwitchMap$com$brightcove$player$mediacontroller$BrightcoveMediaController$AnimationStyle[this.animationStyle.ordinal()] != 1) {
                this.controlBar.animate().alpha(BitmapDescriptorFactory.HUE_RED).setDuration((long) this.animationDuration).setListener(this.animationTerminator);
            } else {
                this.controlBar.animate().setInterpolator(new DecelerateInterpolator(1.0f)).setDuration((long) this.animationDuration).translationY(this.initialY + ((float) this.controlBar.getHeight())).setListener(this.animationTerminator);
            }
            this.eventEmitter.emit(ShowHideController.DID_HIDE_MEDIA_CONTROLS);
        }
    }

    public boolean isShowing() {
        return this.showing;
    }

    public void setShowHideAnimationStyle(AnimationStyle animationStyle2) {
        this.animationStyle = animationStyle2;
    }

    public void setShowHideTimeout(int i) {
        this.showHideTimeout = i;
        if (this.showHideTimeout == 0) {
            this.mediaHandler.removeMessages(1);
        }
    }

    public void setBrightcoveControlBar(BrightcoveControlBar brightcoveControlBar) {
        if (brightcoveControlBar != null) {
            this.controlBar = brightcoveControlBar;
            this.initialY = this.controlBar.getY();
        }
    }

    @TargetApi(12)
    public void show() {
        if (this.showControllerEnable) {
            Log.d(TAG, String.format(Locale.getDefault(), "Showing the media controls.  They will be hidden in %d milliseconds using animation style: %s.", new Object[]{Integer.valueOf(this.showHideTimeout), this.animationStyle}));
            this.controlBar.setVisibility(0);
            if (!this.showing) {
                final Button button = (Button) this.controlBar.findViewById(PlayButtonController.DEFAULT_PLAY_BUTTON_ID);
                if (button != null) {
                    button.postDelayed(new Runnable() {
                        public void run() {
                            button.requestFocus();
                        }
                    }, 100);
                }
            }
            this.showing = true;
            if (VERSION.SDK_INT >= 12) {
                if (AnonymousClass5.$SwitchMap$com$brightcove$player$mediacontroller$BrightcoveMediaController$AnimationStyle[this.animationStyle.ordinal()] != 1) {
                    this.controlBar.animate().alpha(1.0f).setDuration((long) this.animationDuration).setListener(null);
                } else {
                    this.controlBar.animate().setInterpolator(new DecelerateInterpolator(1.0f)).setDuration((long) this.animationDuration).translationY(this.initialY).setListener(null);
                }
            }
            if (this.controlBar.getHeight() == 0) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        HashMap hashMap = new HashMap();
                        hashMap.put(ShowHideController.CONTROLS_HEIGHT, Integer.valueOf(BrightcoveShowHideController.this.controlBar.getHeight()));
                        BrightcoveShowHideController.this.eventEmitter.emit(ShowHideController.DID_SHOW_MEDIA_CONTROLS, hashMap);
                    }
                }, 150);
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put(ShowHideController.CONTROLS_HEIGHT, Integer.valueOf(this.controlBar.getHeight()));
                this.eventEmitter.emit(ShowHideController.DID_SHOW_MEDIA_CONTROLS, hashMap);
            }
            this.mediaHandler.removeMessages(1);
            if (this.showHideTimeout > 0) {
                this.mediaHandler.sendMessageDelayed(this.mediaHandler.obtainMessage(1), (long) this.showHideTimeout);
            }
        }
    }
}
