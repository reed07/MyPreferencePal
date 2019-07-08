package com.brightcove.player.pictureinpicture;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Icon;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Rational;
import com.brightcove.player.R;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.pictureinpicture.BrightcovePictureInPictureParams.Builder;
import com.brightcove.player.util.LayoutUtil;
import com.brightcove.player.util.VideoUtil;
import com.brightcove.player.view.BaseVideoView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PictureInPictureManager {
    private static final String ACTION_MEDIA_CONTROL = "media_control";
    private static final int CONTROL_TYPE_PAUSE = 2;
    private static final int CONTROL_TYPE_PLAY = 1;
    public static final float DEFAULT_CLOSED_CAPTION_REDUCTION_SCALE_FACTOR = 0.5f;
    private static final String EXTRA_CONTROL_TYPE = "control_type";
    private static final int REQUEST_PAUSE = 2;
    private static final int REQUEST_PLAY = 1;
    private static final String TAG = "com.brightcove.player.pictureinpicture.PictureInPictureManager";
    private static final PictureInPictureManager mInstance = new PictureInPictureManager();
    private WeakReference<Activity> activityWeakReference;
    private Rational aspectRatio;
    private boolean closedCaptionsEnabled;
    private float closedCaptionsScaleFactor = 0.5f;
    /* access modifiers changed from: private */
    public BroadcastReceiver mReceiver;
    /* access modifiers changed from: private */
    public BaseVideoView mVideoView;
    private boolean onUserLeaveEnabled;
    private PictureInPictureComponent pictureInPictureComponent;
    private Builder pictureInPictureParamsBuilder = new Builder();
    private Rect sourceRectHint;
    private List<RemoteAction> userActions;
    /* access modifiers changed from: private */
    public boolean wasFullScreen;
    /* access modifiers changed from: private */
    public boolean wereClosedCaptionsEnabled;

    @TargetApi(26)
    private class EventHandlerForAndroidOreo implements EventListener {
        private EventHandlerForAndroidOreo() {
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processEvent(com.brightcove.player.event.Event r2) {
            /*
                r1 = this;
                java.lang.String r2 = r2.getType()
                int r0 = r2.hashCode()
                switch(r0) {
                    case -1391887370: goto L_0x0034;
                    case -174217033: goto L_0x002a;
                    case 1316367067: goto L_0x0020;
                    case 1656958035: goto L_0x0016;
                    case 1942688823: goto L_0x000c;
                    default: goto L_0x000b;
                }
            L_0x000b:
                goto L_0x003e
            L_0x000c:
                java.lang.String r0 = "didEnterPictureInPictureMode"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x003e
                r2 = 1
                goto L_0x003f
            L_0x0016:
                java.lang.String r0 = "didPlay"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x003e
                r2 = 3
                goto L_0x003f
            L_0x0020:
                java.lang.String r0 = "didExitPictureInPictureMode"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x003e
                r2 = 2
                goto L_0x003f
            L_0x002a:
                java.lang.String r0 = "didPause"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x003e
                r2 = 4
                goto L_0x003f
            L_0x0034:
                java.lang.String r0 = "enterPictureInPictureMode"
                boolean r2 = r2.equals(r0)
                if (r2 == 0) goto L_0x003e
                r2 = 0
                goto L_0x003f
            L_0x003e:
                r2 = -1
            L_0x003f:
                switch(r2) {
                    case 0: goto L_0x004f;
                    case 1: goto L_0x004b;
                    case 2: goto L_0x0047;
                    case 3: goto L_0x0043;
                    case 4: goto L_0x0043;
                    default: goto L_0x0042;
                }
            L_0x0042:
                goto L_0x0052
            L_0x0043:
                r1.handlePlayPause()
                goto L_0x0052
            L_0x0047:
                r1.handleDidExitPipMode()
                goto L_0x0052
            L_0x004b:
                r1.handleDidEnterPipMode()
                goto L_0x0052
            L_0x004f:
                r1.handleEnterPipMode()
            L_0x0052:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.pictureinpicture.PictureInPictureManager.EventHandlerForAndroidOreo.processEvent(com.brightcove.player.event.Event):void");
        }

        private void handleEnterPipMode() {
            PictureInPictureManager.this.enterPictureInPictureMode();
        }

        private void handleDidEnterPipMode() {
            EventEmitter eventEmitter = PictureInPictureManager.this.mVideoView.getEventEmitter();
            PictureInPictureManager pictureInPictureManager = PictureInPictureManager.this;
            pictureInPictureManager.wasFullScreen = pictureInPictureManager.mVideoView.isFullScreen();
            PictureInPictureManager pictureInPictureManager2 = PictureInPictureManager.this;
            pictureInPictureManager2.wereClosedCaptionsEnabled = VideoUtil.isClosedCaptionsEnabled(pictureInPictureManager2.mVideoView);
            if (!PictureInPictureManager.this.getBrightcovePictureInPictureParams().isClosedCaptionsEnabled() && PictureInPictureManager.this.wereClosedCaptionsEnabled) {
                VideoUtil.toggleClosedCaptions(PictureInPictureManager.this.mVideoView);
            }
            if (!PictureInPictureManager.this.wasFullScreen) {
                eventEmitter.emit(EventType.ENTER_FULL_SCREEN);
            }
            PictureInPictureManager.this.mReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null && PictureInPictureManager.ACTION_MEDIA_CONTROL.equals(intent.getAction())) {
                        switch (intent.getIntExtra(PictureInPictureManager.EXTRA_CONTROL_TYPE, 0)) {
                            case 1:
                                PictureInPictureManager.this.mVideoView.start();
                                break;
                            case 2:
                                PictureInPictureManager.this.mVideoView.pause();
                                break;
                        }
                    }
                }
            };
            Activity access$500 = PictureInPictureManager.this.getRegisteredActivity();
            if (access$500 != null) {
                access$500.registerReceiver(PictureInPictureManager.this.mReceiver, new IntentFilter(PictureInPictureManager.ACTION_MEDIA_CONTROL));
            }
        }

        private void handleDidExitPipMode() {
            EventEmitter eventEmitter = PictureInPictureManager.this.mVideoView.getEventEmitter();
            if (!PictureInPictureManager.this.wasFullScreen) {
                eventEmitter.emit(EventType.EXIT_FULL_SCREEN);
            }
            if (PictureInPictureManager.this.wereClosedCaptionsEnabled && !VideoUtil.isClosedCaptionsEnabled(PictureInPictureManager.this.mVideoView)) {
                VideoUtil.toggleClosedCaptions(PictureInPictureManager.this.mVideoView);
            }
            Activity access$500 = PictureInPictureManager.this.getRegisteredActivity();
            if (access$500 != null) {
                access$500.unregisterReceiver(PictureInPictureManager.this.mReceiver);
            }
            PictureInPictureManager.this.mReceiver = null;
        }

        private void handlePlayPause() {
            Activity access$500 = PictureInPictureManager.this.getRegisteredActivity();
            if (access$500 != null) {
                access$500.setPictureInPictureParams(PictureInPictureManager.getInstance().getBrightcovePictureInPictureParams().getAndroidPictureInPictureParams());
            }
        }
    }

    private class PictureInPictureComponent extends AbstractComponent {
        PictureInPictureComponent(EventEmitter eventEmitter) {
            super(eventEmitter);
        }
    }

    private PictureInPictureManager() {
    }

    public static PictureInPictureManager getInstance() {
        return mInstance;
    }

    public void registerActivity(@NonNull Activity activity, @NonNull BaseVideoView baseVideoView) {
        if (VERSION.SDK_INT >= 26) {
            Activity registeredActivity = getRegisteredActivity();
            if (registeredActivity == null || registeredActivity != activity) {
                PictureInPictureComponent pictureInPictureComponent2 = this.pictureInPictureComponent;
                if (pictureInPictureComponent2 != null) {
                    pictureInPictureComponent2.removeListeners();
                }
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Registering Activity ");
                sb.append(activity);
                Log.v(str, sb.toString());
                this.mVideoView = baseVideoView;
                EventEmitter eventEmitter = this.mVideoView.getEventEmitter();
                this.activityWeakReference = new WeakReference<>(activity);
                if (LayoutUtil.isLargeScreen(activity)) {
                    this.closedCaptionsEnabled = true;
                }
                this.pictureInPictureComponent = new PictureInPictureComponent(eventEmitter);
                EventHandlerForAndroidOreo eventHandlerForAndroidOreo = new EventHandlerForAndroidOreo();
                this.pictureInPictureComponent.addListener(EventType.ENTER_PICTURE_IN_PICTURE_MODE, eventHandlerForAndroidOreo);
                this.pictureInPictureComponent.addListener(EventType.DID_ENTER_PICTURE_IN_PICTURE_MODE, eventHandlerForAndroidOreo);
                this.pictureInPictureComponent.addListener(EventType.DID_EXIT_PICTURE_IN_PICTURE_MODE, eventHandlerForAndroidOreo);
                this.pictureInPictureComponent.addListener(EventType.DID_PLAY, eventHandlerForAndroidOreo);
                this.pictureInPictureComponent.addListener(EventType.DID_PAUSE, eventHandlerForAndroidOreo);
            } else {
                String str2 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("The activity is already registered with PictureInPictureManager:");
                sb2.append(activity);
                Log.w(str2, sb2.toString());
            }
        } else {
            String str3 = TAG;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("PictureInPicture mode is not supported for Android build version ");
            sb3.append(VERSION.SDK_INT);
            Log.w(str3, sb3.toString());
        }
    }

    public void unregisterActivity(Activity activity) {
        Activity registeredActivity = getRegisteredActivity();
        if (registeredActivity == null) {
            Log.w(TAG, "PictureInPictureManager did not find an activity registered. Did you forget to call registerActivity?");
        } else if (registeredActivity == activity) {
            this.activityWeakReference.clear();
            this.activityWeakReference = null;
            this.pictureInPictureComponent.removeListeners();
            this.pictureInPictureComponent = null;
            this.mVideoView = null;
        } else {
            Log.w(TAG, String.format("The activity %s is different than the previously registered activity %s", new Object[]{activity, registeredActivity}));
        }
    }

    public void enterPictureInPictureMode() {
        if (VERSION.SDK_INT < 26) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("PictureInPicture mode is not supported for Android build version ");
            sb.append(VERSION.SDK_INT);
            Log.w(str, sb.toString());
        } else if (isPictureInPictureEnabled()) {
            Activity registeredActivity = getRegisteredActivity();
            if (registeredActivity != null) {
                registeredActivity.enterPictureInPictureMode(getInstance().getBrightcovePictureInPictureParams().getAndroidPictureInPictureParams());
            } else {
                throwNoRegisteredActivityException();
            }
        } else if (getRegisteredActivity() == null) {
            throwNoRegisteredActivityException();
        }
    }

    public boolean isInPictureInPictureMode() {
        if (VERSION.SDK_INT < 26) {
            return false;
        }
        Activity registeredActivity = getRegisteredActivity();
        if (registeredActivity == null || !registeredActivity.isInPictureInPictureMode()) {
            return false;
        }
        return true;
    }

    public boolean isPictureInPictureEnabled() {
        if (VERSION.SDK_INT < 26) {
            return false;
        }
        Activity registeredActivity = getRegisteredActivity();
        if (registeredActivity == null || registeredActivity.isDestroyed() || this.mVideoView.getVideoDisplay().isCurrentVideo360Mode()) {
            return false;
        }
        return true;
    }

    public void onUserLeaveHint() {
        if (VERSION.SDK_INT >= 26 && getInstance().getBrightcovePictureInPictureParams().isOnUserLeaveEnabled()) {
            enterPictureInPictureMode();
        }
    }

    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        if (VERSION.SDK_INT >= 26) {
            HashMap hashMap = new HashMap();
            hashMap.put("configuration", configuration);
            if (z) {
                emitEvent(EventType.DID_ENTER_PICTURE_IN_PICTURE_MODE, hashMap);
            } else {
                emitEvent(EventType.DID_EXIT_PICTURE_IN_PICTURE_MODE, hashMap);
            }
        }
    }

    /* access modifiers changed from: private */
    @Nullable
    public Activity getRegisteredActivity() {
        WeakReference<Activity> weakReference = this.activityWeakReference;
        if (weakReference != null) {
            return (Activity) weakReference.get();
        }
        return null;
    }

    private void emitEvent(String str, Map<String, Object> map) {
        BaseVideoView baseVideoView = this.mVideoView;
        if (baseVideoView != null) {
            EventEmitter eventEmitter = baseVideoView.getEventEmitter();
            if (eventEmitter == null) {
                return;
            }
            if (map == null || map.isEmpty()) {
                eventEmitter.emit(str);
            } else {
                eventEmitter.emit(str, map);
            }
        }
    }

    private void throwNoRegisteredActivityException() {
        if (VERSION.SDK_INT >= 26) {
            throw new PictureInPictureManagerException("PictureInPictureManager did not find an activity registered. Did you forget to call registerActivity?");
        }
    }

    @TargetApi(26)
    @NonNull
    private List<RemoteAction> getDefaultRemoteActionList() {
        ArrayList arrayList = new ArrayList();
        Activity registeredActivity = getRegisteredActivity();
        if (registeredActivity != null) {
            BaseVideoView baseVideoView = this.mVideoView;
            int i = 1;
            boolean z = baseVideoView != null && baseVideoView.isPlaying();
            int i2 = z ? 2 : 1;
            if (z) {
                i = 2;
            }
            int i3 = z ? R.drawable.ic_pause_24dp : R.drawable.ic_play_arrow_24dp;
            String string = registeredActivity.getString(z ? R.string.desc_pause : R.string.desc_play);
            arrayList.add(new RemoteAction(Icon.createWithResource(registeredActivity, i3), string, string, PendingIntent.getBroadcast(registeredActivity, i2, new Intent(ACTION_MEDIA_CONTROL).putExtra(EXTRA_CONTROL_TYPE, i), 0)));
        } else {
            throwNoRegisteredActivityException();
        }
        return arrayList;
    }

    public BrightcovePictureInPictureParams getBrightcovePictureInPictureParams() {
        return this.pictureInPictureParamsBuilder.setAspectRatio(this.aspectRatio).setActions(getUserActions()).setSourceRectHint(this.sourceRectHint).setClosedCaptionsReductionScaleFactor(this.closedCaptionsScaleFactor).setClosedCaptionsEnabled(this.closedCaptionsEnabled).setOnUserLeaveEnabled(this.onUserLeaveEnabled).build();
    }

    public PictureInPictureManager setAspectRatio(@Nullable Rational rational) {
        this.aspectRatio = rational;
        return this;
    }

    @NonNull
    private List<RemoteAction> getUserActions() {
        ArrayList arrayList = new ArrayList();
        List<RemoteAction> list = this.userActions;
        if (list != null) {
            arrayList.addAll(list);
        }
        arrayList.addAll(getDefaultRemoteActionList());
        return arrayList;
    }

    public PictureInPictureManager setUserActions(@Nullable List<RemoteAction> list) {
        this.userActions = list;
        return this;
    }

    public PictureInPictureManager setSourceRectHint(@Nullable Rect rect) {
        this.sourceRectHint = rect;
        return this;
    }

    public PictureInPictureManager setClosedCaptionsEnabled(boolean z) {
        this.closedCaptionsEnabled = z;
        return this;
    }

    public PictureInPictureManager setOnUserLeaveEnabled(boolean z) {
        this.onUserLeaveEnabled = z;
        return this;
    }

    public PictureInPictureManager setClosedCaptionsReductionScaleFactor(float f) {
        if (f > BitmapDescriptorFactory.HUE_RED && f <= 1.0f) {
            this.closedCaptionsScaleFactor = f;
        }
        return this;
    }
}
