package com.brightcove.player.mediacontroller;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.UiModeManager;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.brightcove.player.R;
import com.brightcove.player.controller.BrightcovePlayerOptionsManager;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.buttons.AudioTracksButtonController;
import com.brightcove.player.mediacontroller.buttons.ButtonController;
import com.brightcove.player.mediacontroller.buttons.CaptionsButtonController;
import com.brightcove.player.mediacontroller.buttons.CloseButtonController;
import com.brightcove.player.mediacontroller.buttons.FastForwardButtonController;
import com.brightcove.player.mediacontroller.buttons.FullScreenButtonController;
import com.brightcove.player.mediacontroller.buttons.LiveButtonController;
import com.brightcove.player.mediacontroller.buttons.PictureInPictureButtonController;
import com.brightcove.player.mediacontroller.buttons.PlayButtonController;
import com.brightcove.player.mediacontroller.buttons.PlayerOptionsButtonController;
import com.brightcove.player.mediacontroller.buttons.RemoteControlKeyState;
import com.brightcove.player.mediacontroller.buttons.RewindButtonController;
import com.brightcove.player.mediacontroller.buttons.VRButtonController;
import com.brightcove.player.model.CuePoint;
import com.brightcove.player.model.CuePoint.PositionType;
import com.brightcove.player.pictureinpicture.PictureInPictureManager;
import com.brightcove.player.util.StringUtil;
import com.brightcove.player.view.BaseVideoView;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class BrightcoveMediaController extends AbstractComponent implements ShowHideController {
    public static final String CONTROL_BAR_CREATED = "controlBarCreated";
    public static final int DEFAULT_TIMEOUT = 3000;
    public static final String DURATION = "duration";
    public static final String MARKER_LIST = "markerList";
    public static final String PROGRESS = "progress";
    public static final String SEEK_BAR_MAX = "seekBarMax";
    public static final String SEEK_BAR_PROGRESS = "seekBarProgress";
    public static final String SET_MARKERS = "setMarkers";
    /* access modifiers changed from: private */
    public static final String TAG = "BrightcoveMediaController";
    /* access modifiers changed from: private */
    public BrightcoveControlBar controlBar;
    /* access modifiers changed from: private */
    public boolean cuePointMarkersState;
    private RemoteControlKeyState currentKeyState;
    private TextView currentTime;
    private RemoteControlKeyState defaultKeyState;
    private TextView endTime;
    private RemoteControlKeyState fastForwardKeyController;
    /* access modifiers changed from: private */
    public boolean isAdPlaying;
    /* access modifiers changed from: private */
    public boolean isDVRControllerEnabled;
    public final boolean isTvMode;
    /* access modifiers changed from: private */
    public int layout;
    private BrightcoveMediaControlRegistry mediaControlRegistry;
    private RemoteControlKeyState nextKeyState;
    /* access modifiers changed from: private */
    public Bundle onChangedBundle;
    private RemoteControlKeyState rewindKeyController;
    /* access modifiers changed from: private */
    public BrightcoveSeekBar seekBar;
    private BrightcoveSeekBarController seekBarController;
    private BrightcoveShowHideController showHideController;
    /* access modifiers changed from: private */
    public BaseVideoView videoView;

    private class ActivityCreatedHandler implements EventListener {
        private ActivityCreatedHandler() {
        }

        public void processEvent(Event event) {
            Log.d(BrightcoveMediaController.TAG, "Processing the activity created event...");
            Object obj = event.properties.get(AbstractEvent.INSTANCE_STATE);
            if (obj instanceof Bundle) {
                Bundle bundle = (Bundle) obj;
                if (bundle.size() > 0) {
                    BrightcoveMediaController.this.restoreSeekBarState(bundle);
                }
            }
        }
    }

    private class ActivitySaveInstanceStateHandler implements EventListener {
        private ActivitySaveInstanceStateHandler() {
        }

        public void processEvent(Event event) {
            BrightcoveMediaController.this.saveControlBarInstanceState(event);
            BrightcoveMediaController.this.saveSeekBarInstanceState(event);
        }
    }

    enum AnimationStyle {
        FADE,
        SLIDE
    }

    private class BufferedUpdateHandler implements EventListener {
        private BufferedUpdateHandler() {
        }

        @Default
        public void processEvent(Event event) {
            int integerProperty = event.getIntegerProperty(AbstractEvent.PERCENT_COMPLETE);
            if (integerProperty != -1 && BrightcoveMediaController.this.seekBar != null) {
                BrightcoveMediaController.this.seekBar.setSecondaryProgress((BrightcoveMediaController.this.seekBar.getMax() * integerProperty) / 100);
            }
        }
    }

    private class ConfigurationChangedHandler implements EventListener {
        private ConfigurationChangedHandler() {
        }

        public void processEvent(Event event) {
            Log.d(BrightcoveMediaController.TAG, String.format(Locale.getDefault(), "Processing a %s event...", new Object[]{event.getType()}));
            BrightcoveMediaController.this.removeControllerListeners();
            BrightcoveMediaController.this.saveControlBarInstanceState(event);
            BrightcoveMediaController.this.saveSeekBarInstanceState(event);
            BrightcoveMediaController.this.videoView.removeView(BrightcoveMediaController.this.controlBar);
            Object obj = event.properties.get(AbstractEvent.INSTANCE_STATE);
            Bundle bundle = obj instanceof Bundle ? (Bundle) obj : null;
            if (VERSION.SDK_INT < 26) {
                BrightcoveMediaController.this.initializeControlBar(bundle);
            } else if (!PictureInPictureManager.getInstance().isInPictureInPictureMode()) {
                BrightcoveMediaController.this.initializeControlBar(bundle);
            }
        }
    }

    private class DidSetVideoHandler implements EventListener {
        private DidSetVideoHandler() {
        }

        @Default
        public void processEvent(Event event) {
            Log.d(BrightcoveMediaController.TAG, String.format(Locale.getDefault(), "Processing event: %1$s...", new Object[]{event.getType()}));
            BrightcoveMediaController.this.eventEmitter.once(EventType.BUFFERED_UPDATE, new EventListener() {
                public void processEvent(Event event) {
                    boolean isLive = BrightcoveMediaController.this.videoView.getVideoDisplay().isLive();
                    boolean z = BrightcoveMediaController.this.videoView.getVideoDisplay().hasDvr() && BrightcoveMediaController.this.isDVRControllerEnabled;
                    if (!isLive || z) {
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.one_line_spacer, 8);
                    } else {
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.one_line_spacer, 0);
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.current_time, 8);
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.seek_bar, 8);
                    }
                    if (isLive) {
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.live, 0);
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.time_separator, 8);
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.end_time, 8);
                    } else {
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.live, 8);
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.time_separator, 0);
                        BrightcoveMediaController.this.setControlBarViewVisibility(R.id.end_time, 0);
                    }
                    BrightcoveMediaController.this.requestFocus(PlayButtonController.DEFAULT_PLAY_BUTTON_ID);
                }
            });
        }
    }

    private class DraggingHandler implements EventListener {
        private DraggingHandler() {
        }

        @Default
        public void processEvent(Event event) {
            if (event.getType().equals(EventType.SEEKBAR_DRAGGING_PROGRESS) && BrightcoveMediaController.this.isDragging()) {
                BrightcoveMediaController.this.updateProgress(event.getIntegerProperty(AbstractEvent.SEEK_PROGRESS));
            }
        }
    }

    private class MediaControllerConfigHandler implements EventListener {
        int savedControlBarId;

        private MediaControllerConfigHandler() {
            this.savedControlBarId = -1;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0030  */
        /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processEvent(com.brightcove.player.event.Event r4) {
            /*
                r3 = this;
                java.lang.String r0 = r4.getType()
                int r1 = r0.hashCode()
                r2 = -577331520(0xffffffffdd969ec0, float:-1.35666541E18)
                if (r1 == r2) goto L_0x001d
                r2 = 1266588237(0x4b7e9a4d, float:1.6685645E7)
                if (r1 == r2) goto L_0x0013
                goto L_0x0027
            L_0x0013:
                java.lang.String r1 = "restoreDefaultMediaController"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0027
                r0 = 1
                goto L_0x0028
            L_0x001d:
                java.lang.String r1 = "setMediaControllerConfig"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0027
                r0 = 0
                goto L_0x0028
            L_0x0027:
                r0 = -1
            L_0x0028:
                switch(r0) {
                    case 0: goto L_0x0030;
                    case 1: goto L_0x002c;
                    default: goto L_0x002b;
                }
            L_0x002b:
                goto L_0x0033
            L_0x002c:
                r3.restoreMediaController(r4)
                goto L_0x0033
            L_0x0030:
                r3.switchMediaController(r4)
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.mediacontroller.BrightcoveMediaController.MediaControllerConfigHandler.processEvent(com.brightcove.player.event.Event):void");
        }

        private void switchMediaController(Event event) {
            Object obj = event.getProperties().get(AbstractEvent.MEDIA_CONTROLLER_CONFIG);
            if (obj instanceof MediaControllerConfig) {
                MediaControllerConfig mediaControllerConfig = (MediaControllerConfig) obj;
                int layoutId = mediaControllerConfig.getLayoutId();
                if (layoutId != -1 && this.savedControlBarId == -1) {
                    BrightcoveMediaController.this.onChangedBundle.clear();
                    BrightcoveMediaController.this.removeControllerListeners();
                    Bundle access$1700 = BrightcoveMediaController.this.onChangedBundle;
                    BrightcoveMediaController brightcoveMediaController = BrightcoveMediaController.this;
                    access$1700.putAll(brightcoveMediaController.getControlBarBundle(brightcoveMediaController.onChangedBundle));
                    Bundle access$17002 = BrightcoveMediaController.this.onChangedBundle;
                    BrightcoveMediaController brightcoveMediaController2 = BrightcoveMediaController.this;
                    access$17002.putAll(brightcoveMediaController2.getSeekBarBundle(brightcoveMediaController2.onChangedBundle));
                    BrightcoveMediaController.this.videoView.removeView(BrightcoveMediaController.this.controlBar);
                    this.savedControlBarId = BrightcoveMediaController.this.layout;
                    BrightcoveMediaController.this.layout = layoutId;
                    BrightcoveMediaController.this.initializeControlBar(null, layoutId);
                    OnTouchListener onTouchListener = mediaControllerConfig.getOnTouchListener();
                    if (onTouchListener != null) {
                        BrightcoveMediaController.this.videoView.setOnTouchListener(onTouchListener);
                    }
                    attemptSettingDuration(mediaControllerConfig);
                    attemptSettingPlayhead(mediaControllerConfig);
                    if (mediaControllerConfig.isShowControlsOnCreation()) {
                        BrightcoveMediaController.this.eventEmitter.emit(ShowHideController.SHOW_MEDIA_CONTROLS);
                    }
                }
            }
        }

        private void restoreMediaController(Event event) {
            if (this.savedControlBarId != -1) {
                BrightcoveMediaController.this.removeControllerListeners();
                BrightcoveMediaController.this.videoView.removeView(BrightcoveMediaController.this.controlBar);
                BrightcoveMediaController.this.layout = this.savedControlBarId;
                BrightcoveMediaController brightcoveMediaController = BrightcoveMediaController.this;
                brightcoveMediaController.initializeControlBar(brightcoveMediaController.onChangedBundle);
                this.savedControlBarId = -1;
                BrightcoveMediaController.this.videoView.setOnTouchListener(new TouchHandler());
                Object obj = event.getProperties().get(AbstractEvent.MEDIA_CONTROLLER_CONFIG);
                if (obj instanceof MediaControllerConfig) {
                    MediaControllerConfig mediaControllerConfig = (MediaControllerConfig) obj;
                    attemptSettingDuration(mediaControllerConfig);
                    attemptSettingPlayhead(mediaControllerConfig);
                }
            }
        }

        private void attemptSettingDuration(MediaControllerConfig mediaControllerConfig) {
            int initialDuration = mediaControllerConfig.getInitialDuration();
            if (initialDuration != -1) {
                BrightcoveMediaController.this.updateDuration(initialDuration);
                if (BrightcoveMediaController.this.seekBar != null) {
                    BrightcoveMediaController.this.seekBar.setMax(initialDuration);
                }
            }
        }

        private void attemptSettingPlayhead(MediaControllerConfig mediaControllerConfig) {
            int initialPlayheadPosition = mediaControllerConfig.getInitialPlayheadPosition();
            if (initialPlayheadPosition != -1) {
                BrightcoveMediaController.this.updateProgress(initialPlayheadPosition);
                if (BrightcoveMediaController.this.seekBar != null) {
                    BrightcoveMediaController.this.seekBar.setProgress(initialPlayheadPosition);
                }
            }
        }
    }

    private class OnRemoveCuePointListener implements EventListener {
        private OnRemoveCuePointListener() {
        }

        @Default
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.CUE_POINT);
            if (obj instanceof CuePoint) {
                CuePoint cuePoint = (CuePoint) obj;
                if (BrightcoveMediaController.this.seekBar != null) {
                    for (Integer num : BrightcoveMediaController.this.seekBar.getMarkers()) {
                        if (cuePoint.getPositionType() == PositionType.POINT_IN_TIME && cuePoint.getPosition() == num.intValue()) {
                            BrightcoveMediaController.this.seekBar.removeMarker(num);
                            return;
                        }
                    }
                }
            }
        }
    }

    private class PictureInPictureHandler implements EventListener {
        int savedControlBarId;

        private PictureInPictureHandler() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0052  */
        /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processEvent(com.brightcove.player.event.Event r3) {
            /*
                r2 = this;
                java.lang.String r3 = r3.getType()
                int r0 = r3.hashCode()
                r1 = 1316367067(0x4e762adb, float:1.03250093E9)
                if (r0 == r1) goto L_0x001d
                r1 = 1942688823(0x73cb1437, float:3.2179146E31)
                if (r0 == r1) goto L_0x0013
                goto L_0x0027
            L_0x0013:
                java.lang.String r0 = "didEnterPictureInPictureMode"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0027
                r3 = 0
                goto L_0x0028
            L_0x001d:
                java.lang.String r0 = "didExitPictureInPictureMode"
                boolean r3 = r3.equals(r0)
                if (r3 == 0) goto L_0x0027
                r3 = 1
                goto L_0x0028
            L_0x0027:
                r3 = -1
            L_0x0028:
                switch(r3) {
                    case 0: goto L_0x0052;
                    case 1: goto L_0x002d;
                    default: goto L_0x002b;
                }
            L_0x002b:
                goto L_0x00b2
            L_0x002d:
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                r3.removeControllerListeners()
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                com.brightcove.player.view.BaseVideoView r3 = r3.videoView
                com.brightcove.player.mediacontroller.BrightcoveMediaController r0 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                com.brightcove.player.mediacontroller.BrightcoveControlBar r0 = r0.controlBar
                r3.removeView(r0)
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                int r0 = r2.savedControlBarId
                r3.layout = r0
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                android.os.Bundle r0 = r3.onChangedBundle
                r3.initializeControlBar(r0)
                goto L_0x00b2
            L_0x0052:
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                android.os.Bundle r3 = r3.onChangedBundle
                r3.clear()
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                r3.removeControllerListeners()
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                android.os.Bundle r3 = r3.onChangedBundle
                com.brightcove.player.mediacontroller.BrightcoveMediaController r0 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                android.os.Bundle r1 = r0.onChangedBundle
                android.os.Bundle r0 = r0.getControlBarBundle(r1)
                r3.putAll(r0)
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                android.os.Bundle r3 = r3.onChangedBundle
                com.brightcove.player.mediacontroller.BrightcoveMediaController r0 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                android.os.Bundle r1 = r0.onChangedBundle
                android.os.Bundle r0 = r0.getSeekBarBundle(r1)
                r3.putAll(r0)
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                com.brightcove.player.view.BaseVideoView r3 = r3.videoView
                com.brightcove.player.mediacontroller.BrightcoveMediaController r0 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                com.brightcove.player.mediacontroller.BrightcoveControlBar r0 = r0.controlBar
                r3.removeView(r0)
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                int r3 = r3.layout
                r2.savedControlBarId = r3
                int r3 = android.os.Build.VERSION.SDK_INT
                r0 = 26
                if (r3 >= r0) goto L_0x00b2
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                int r0 = com.brightcove.player.R.layout.pip_media_controller
                r3.layout = r0
                com.brightcove.player.mediacontroller.BrightcoveMediaController r3 = com.brightcove.player.mediacontroller.BrightcoveMediaController.this
                r0 = 0
                int r1 = com.brightcove.player.R.layout.pip_media_controller
                r3.initializeControlBar(r0, r1)
            L_0x00b2:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.mediacontroller.BrightcoveMediaController.PictureInPictureHandler.processEvent(com.brightcove.player.event.Event):void");
        }
    }

    private class ProgressHandler implements EventListener {
        private ProgressHandler() {
        }

        @Default
        public void processEvent(Event event) {
            if (!BrightcoveMediaController.this.isDragging()) {
                BrightcoveMediaController.this.updateSeekBar(event);
                BrightcoveMediaController.this.updateProgress(event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION));
                BrightcoveMediaController.this.updateDuration(event.getIntegerProperty("duration"));
                return;
            }
            Log.d(BrightcoveMediaController.TAG, "The seek bar is being dragged.  No progress updates are being applied.");
        }
    }

    private class SeekToHandler implements EventListener {
        private SeekToHandler() {
        }

        @Default
        public void processEvent(Event event) {
            int i;
            if (!BrightcoveMediaController.this.isDragging()) {
                if (event.properties.containsKey(AbstractEvent.ORIGINAL_SEEK_POSITION)) {
                    i = event.getIntegerProperty(AbstractEvent.ORIGINAL_SEEK_POSITION);
                } else {
                    i = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
                }
                BrightcoveMediaController.this.updateProgress(i);
                return;
            }
            Log.d(BrightcoveMediaController.TAG, "The seek bar is being dragged.  No SEEK_TO updates are being applied.");
        }
    }

    private class SetCuePointHandler implements EventListener {
        private SetCuePointHandler() {
        }

        public void processEvent(Event event) {
            if (BrightcoveMediaController.this.cuePointMarkersState) {
                Object obj = event.properties.get(AbstractEvent.CUE_POINT);
                if (!(obj instanceof CuePoint)) {
                    String access$3000 = BrightcoveMediaController.TAG;
                    Locale locale = Locale.getDefault();
                    String str = "Invalid cue point {%s} found in the event payload.";
                    Object[] objArr = new Object[1];
                    objArr[0] = obj != null ? obj.getClass() : null;
                    Log.e(access$3000, String.format(locale, str, objArr));
                } else if (BrightcoveMediaController.this.seekBar != null) {
                    CuePoint cuePoint = (CuePoint) obj;
                    if (cuePoint.getPositionType() == PositionType.POINT_IN_TIME) {
                        BrightcoveMediaController.this.seekBar.addMarker(cuePoint.getPosition());
                    }
                }
            }
        }
    }

    private class SetCuePointListHandler implements EventListener {
        private SetCuePointListHandler() {
        }

        public void processEvent(Event event) {
            if (BrightcoveMediaController.this.cuePointMarkersState) {
                Object obj = event.properties.get(AbstractEvent.CUE_POINTS);
                if (!(obj instanceof List)) {
                    String access$3000 = BrightcoveMediaController.TAG;
                    Locale locale = Locale.getDefault();
                    String str = "Invalid cue point list {%s} found in the event payload.";
                    Object[] objArr = new Object[1];
                    objArr[0] = obj != null ? obj.getClass().getSimpleName() : null;
                    Log.e(access$3000, String.format(locale, str, objArr));
                } else if (BrightcoveMediaController.this.seekBar != null) {
                    for (CuePoint cuePoint : (List) obj) {
                        if (cuePoint.getPositionType() == PositionType.POINT_IN_TIME) {
                            BrightcoveMediaController.this.seekBar.addMarker(cuePoint.getPosition());
                        }
                    }
                }
            }
        }
    }

    @SuppressLint({"ClickableViewAcessibility"})
    private class TouchHandler implements OnTouchListener {
        private TouchHandler() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            Log.d(BrightcoveMediaController.TAG, String.format(Locale.getDefault(), "Processing onTouch for view: %s, with event: %s.", new Object[]{view, motionEvent}));
            if (motionEvent.getAction() == 0) {
                if (BrightcoveMediaController.this.isShowing()) {
                    Log.d(BrightcoveMediaController.TAG, "The control bar is showing, hide the media controls...");
                    BrightcoveMediaController.this.eventEmitter.emit(ShowHideController.HIDE_MEDIA_CONTROLS);
                } else {
                    Log.d(BrightcoveMediaController.TAG, "The control bar is hidden, show the media controls...");
                    BrightcoveMediaController.this.eventEmitter.emit(ShowHideController.SHOW_MEDIA_CONTROLS);
                }
            }
            return false;
        }
    }

    private class VideoDurationChangedHandler implements EventListener {
        private VideoDurationChangedHandler() {
        }

        @Default
        public void processEvent(Event event) {
            BrightcoveMediaController.this.setSeekBarMax(event);
            BrightcoveMediaController.this.updateDuration(event.getIntegerProperty("duration"));
        }
    }

    private boolean isDpadControl(int i) {
        switch (i) {
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                return true;
            default:
                return false;
        }
    }

    public BrightcoveMediaController(BaseVideoView baseVideoView) {
        this(baseVideoView, 0);
    }

    public BrightcoveMediaController(BaseVideoView baseVideoView, int i) {
        super(baseVideoView.getEventEmitter());
        boolean z = true;
        this.cuePointMarkersState = true;
        this.onChangedBundle = new Bundle();
        this.isDVRControllerEnabled = true;
        if (baseVideoView.isInEditMode() || !checkTvMode(baseVideoView.getContext())) {
            z = false;
        }
        this.isTvMode = z;
        init(baseVideoView, i);
        if (this.isTvMode) {
            this.eventEmitter.emit(EventType.ENTER_TV_MODE);
            this.eventEmitter.emit(EventType.ENTER_FULL_SCREEN);
        }
    }

    /* access modifiers changed from: protected */
    public void init(BaseVideoView baseVideoView, int i) {
        this.videoView = baseVideoView;
        if (i == 0) {
            this.layout = this.isTvMode ? R.layout.tv_media_controller : R.layout.default_media_controller;
        } else {
            this.layout = i;
        }
        this.mediaControlRegistry = new BrightcoveMediaControlRegistryImpl();
        initEventListeners();
        initializeControlBar(null);
        baseVideoView.setMediaController(this);
        RemoteControlKeyState remoteControlKeyState = (RemoteControlKeyState) this.mediaControlRegistry.getButtonController(PlayButtonController.DEFAULT_PLAY_BUTTON_ID);
        this.currentKeyState = remoteControlKeyState;
        this.defaultKeyState = remoteControlKeyState;
        this.fastForwardKeyController = (RemoteControlKeyState) this.mediaControlRegistry.getButtonController(FastForwardButtonController.DEFAULT_FAST_FORWARD_BUTTON_ID);
        this.rewindKeyController = (RemoteControlKeyState) this.mediaControlRegistry.getButtonController(RewindButtonController.DEFAULT_REWIND_BUTTON_ID);
        AnonymousClass1 r3 = new EventListener() {
            public void processEvent(Event event) {
                BrightcoveMediaController.this.isAdPlaying = event.getType().equals(EventType.AD_BREAK_STARTED);
            }
        };
        this.eventEmitter.on(EventType.AD_BREAK_STARTED, r3);
        this.eventEmitter.on(EventType.AD_BREAK_COMPLETED, r3);
        PictureInPictureHandler pictureInPictureHandler = new PictureInPictureHandler();
        this.eventEmitter.on(EventType.DID_ENTER_PICTURE_IN_PICTURE_MODE, pictureInPictureHandler);
        this.eventEmitter.on(EventType.DID_EXIT_PICTURE_IN_PICTURE_MODE, pictureInPictureHandler);
        MediaControllerConfigHandler mediaControllerConfigHandler = new MediaControllerConfigHandler();
        this.eventEmitter.on(EventType.SET_MEDIA_CONTROLLER_CONFIG, mediaControllerConfigHandler);
        this.eventEmitter.on(EventType.RESTORE_DEFAULT_MEDIA_CONTROLLER, mediaControllerConfigHandler);
    }

    public BrightcoveControlBar getBrightcoveControlBar() {
        return this.controlBar;
    }

    public BrightcoveSeekBar getBrightcoveSeekBar() {
        return this.seekBar;
    }

    public BrightcoveMediaControlRegistry getMediaControlRegistry() {
        return this.mediaControlRegistry;
    }

    public boolean isDVRControllerEnabled() {
        return this.isDVRControllerEnabled;
    }

    public void setDVRControllerEnabled(boolean z) {
        this.isDVRControllerEnabled = z;
    }

    public boolean isDragging() {
        BrightcoveSeekBarController brightcoveSeekBarController = this.seekBarController;
        return brightcoveSeekBarController != null && brightcoveSeekBarController.isDragging();
    }

    public void setCuePointMarkersEnabled(boolean z) {
        this.cuePointMarkersState = z;
    }

    public void hide() {
        BrightcoveShowHideController brightcoveShowHideController = this.showHideController;
        if (brightcoveShowHideController != null) {
            brightcoveShowHideController.hide();
        }
    }

    public boolean isShowing() {
        BrightcoveShowHideController brightcoveShowHideController = this.showHideController;
        return brightcoveShowHideController != null && brightcoveShowHideController.isShowing();
    }

    public boolean isShowControllerEnable() {
        BrightcoveShowHideController brightcoveShowHideController = this.showHideController;
        return brightcoveShowHideController != null && brightcoveShowHideController.isShowControllerEnable();
    }

    public boolean isHideControllerEnable() {
        BrightcoveShowHideController brightcoveShowHideController = this.showHideController;
        return brightcoveShowHideController != null && brightcoveShowHideController.isHideControllerEnable();
    }

    public void setShowControllerEnable(boolean z) {
        BrightcoveShowHideController brightcoveShowHideController = this.showHideController;
        if (brightcoveShowHideController != null) {
            brightcoveShowHideController.setShowControllerEnable(z);
        }
    }

    public void setHideControllerEnable(boolean z) {
        BrightcoveShowHideController brightcoveShowHideController = this.showHideController;
        if (brightcoveShowHideController != null) {
            brightcoveShowHideController.setHideControllerEnable(z);
        }
    }

    public void setShowHideAnimationStyle(AnimationStyle animationStyle) {
        this.showHideController.setShowHideAnimationStyle(animationStyle);
    }

    public void setShowHideTimeout(int i) {
        this.showHideController.setShowHideTimeout(i);
    }

    public void show() {
        if (!this.showHideController.isShowing()) {
            if (!this.isAdPlaying) {
                setMediaControlsVisibility();
            }
            resetKeyStates();
        }
        this.showHideController.show();
    }

    public static boolean checkTvMode(Context context) {
        boolean z = false;
        if (context == null) {
            return false;
        }
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
            z = true;
        }
        return z;
    }

    private AnimationStyle getAnimationStyle(String str) {
        AnimationStyle animationStyle = AnimationStyle.FADE;
        try {
            return AnimationStyle.valueOf(str.toUpperCase(Locale.US));
        } catch (IllegalArgumentException unused) {
            Log.w(TAG, String.format(Locale.getDefault(), "Invalid animation style: %s.", new Object[]{str}));
            return animationStyle;
        }
    }

    private SparseArray<String> getStandardMediaControls() {
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(R.id.play, "Play");
        sparseArray.put(R.id.rewind, "Rewind");
        sparseArray.put(R.id.fast_forward, "FastForward");
        sparseArray.put(R.id.current_time, "ProgressTime");
        sparseArray.put(R.id.time_separator, "TimeSeparator");
        sparseArray.put(R.id.end_time, "DurationTime");
        sparseArray.put(R.id.seek_bar, "SeekBar");
        sparseArray.put(R.id.one_line_spacer, "OneLineSpacer");
        sparseArray.put(R.id.two_line_spacer, "TwoLineSpacer");
        sparseArray.put(R.id.live, "Live");
        sparseArray.put(R.id.audio_tracks, "Audio Tracks");
        sparseArray.put(R.id.captions, "Captions");
        sparseArray.put(R.id.picture_in_picture, "PictureInPicture");
        sparseArray.put(R.id.close, "Close");
        sparseArray.put(R.id.full_screen, "FullScreen");
        sparseArray.put(R.id.player_options, "PlayerOptions");
        sparseArray.put(R.id.vr_mode, "VR Mode");
        return sparseArray;
    }

    private TextView getTextView(int i) {
        View findViewById = this.controlBar.findViewById(i);
        if (findViewById instanceof TextView) {
            return (TextView) findViewById;
        }
        return null;
    }

    private String getTimeValue(int i) {
        TextView textView = getTextView(i);
        return textView != null ? textView.getText().toString() : "";
    }

    private int getVisibilityState(int i) {
        ButtonController buttonController = this.mediaControlRegistry.getButtonController(i);
        if (buttonController != null) {
            return buttonController.getVisibilityState();
        }
        return 8;
    }

    private void initEventListeners() {
        this.videoView.setOnTouchListener(new TouchHandler());
        addListener(EventType.DID_SET_VIDEO, new DidSetVideoHandler());
        addListener(EventType.CONFIGURATION_CHANGED, new ConfigurationChangedHandler());
        addListener(EventType.ACTIVITY_CREATED, new ActivityCreatedHandler());
        addListener(EventType.ACTIVITY_SAVE_INSTANCE_STATE, new ActivitySaveInstanceStateHandler());
        addListener(EventType.VIDEO_DURATION_CHANGED, new VideoDurationChangedHandler());
        addListener(EventType.REMOVE_CUE_POINT, new OnRemoveCuePointListener());
        ProgressHandler progressHandler = new ProgressHandler();
        addListener("progress", progressHandler);
        addListener(EventType.AD_PROGRESS, progressHandler);
        addListener("completed", progressHandler);
        addListener(EventType.BUFFERED_UPDATE, new BufferedUpdateHandler());
        addListener(EventType.SET_CUE_POINT, new SetCuePointHandler());
        addListener(EventType.SET_CUE_POINTS, new SetCuePointListHandler());
        addListener(EventType.SEEK_TO, new SeekToHandler());
        addListener(EventType.SEEKBAR_DRAGGING_PROGRESS, new DraggingHandler());
        addListener(EventType.WILL_CHANGE_VIDEO, new EventListener() {
            public void processEvent(Event event) {
                BrightcoveMediaController.this.setMediaControlsVisibility();
                BrightcoveMediaController.this.addOnceListener(EventType.VIDEO_SIZE_KNOWN, new EventListener() {
                    public void processEvent(Event event) {
                        BrightcoveMediaController.this.show();
                    }
                });
            }
        });
    }

    private void initializeButtons(BrightcoveControlBar brightcoveControlBar, Bundle bundle) {
        Context context = this.videoView.getContext();
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        CloseButtonController closeButtonController = null;
        register(isEnabled(BrightcoveControlBar.PLAY) ? new PlayButtonController(context, this.videoView, brightcoveControlBar, createFromAsset) : null);
        register(isEnabled(BrightcoveControlBar.AUDIO_TRACKS) ? new AudioTracksButtonController(context, this.videoView, brightcoveControlBar, createFromAsset, bundle) : null);
        register(isEnabled(BrightcoveControlBar.CLOSED_CAPTIONS) ? new CaptionsButtonController(context, this.videoView, brightcoveControlBar, createFromAsset, bundle) : null);
        register(isEnabled(BrightcoveControlBar.FULL_SCREEN) ? new FullScreenButtonController(context, this.videoView, brightcoveControlBar, createFromAsset) : null);
        register(isEnabled(BrightcoveControlBar.LIVE) ? new LiveButtonController(context, this.videoView, brightcoveControlBar, createFromAsset, bundle) : null);
        register(isEnabled(BrightcoveControlBar.REWIND) ? new RewindButtonController(context, this.videoView, brightcoveControlBar, createFromAsset) : null);
        register(isEnabled(BrightcoveControlBar.FAST_FORWARD) ? new FastForwardButtonController(context, this.videoView, brightcoveControlBar, createFromAsset) : null);
        register(isEnabled(BrightcoveControlBar.PLAYER_OPTIONS) ? new PlayerOptionsButtonController(context, this.videoView, brightcoveControlBar, createFromAsset) : null);
        register(isEnabled(BrightcoveControlBar.VR_MODE) ? new VRButtonController(context, this.videoView, brightcoveControlBar, createFromAsset) : null);
        if (PictureInPictureManager.getInstance().isPictureInPictureEnabled()) {
            register(isEnabled(BrightcoveControlBar.PICTURE_IN_PICTURE) ? new PictureInPictureButtonController(context, this.videoView, brightcoveControlBar, createFromAsset, bundle) : null);
            if (isEnabled(BrightcoveControlBar.CLOSE)) {
                closeButtonController = new CloseButtonController(context, this.videoView, brightcoveControlBar, createFromAsset);
            }
            register(closeButtonController);
        }
        View view = this.mediaControlRegistry.getView(PlayButtonController.DEFAULT_PLAY_BUTTON_ID);
        if (view != null) {
            resetKeyStates();
            view.requestFocus();
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(12)
    public void initializeControlBar(Bundle bundle) {
        initializeControlBar(bundle, this.layout);
    }

    /* access modifiers changed from: private */
    @TargetApi(12)
    public void initializeControlBar(Bundle bundle, int i) {
        Log.d(TAG, "Initializing the control bar...");
        this.mediaControlRegistry.clear();
        this.controlBar = (BrightcoveControlBar) ((LayoutInflater) this.videoView.getContext().getSystemService("layout_inflater")).inflate(i, this.videoView, false);
        this.controlBar.setVideoView(this.videoView);
        this.mediaControlRegistry.register((View) this.controlBar);
        BrightcoveShowHideController brightcoveShowHideController = this.showHideController;
        if (brightcoveShowHideController == null) {
            this.showHideController = new BrightcoveShowHideController(this.controlBar, this.videoView);
        } else {
            brightcoveShowHideController.setBrightcoveControlBar(this.controlBar);
        }
        initializeButtons(this.controlBar, bundle);
        this.currentTime = setupTimeValue(R.id.current_time, "progress", bundle);
        this.endTime = setupTimeValue(R.id.end_time, "duration", bundle);
        if (isEnabled(BrightcoveControlBar.SEEK_BAR)) {
            initializeSeekBar(bundle);
        } else {
            this.seekBar = null;
            this.seekBarController = null;
        }
        processAttributes();
        setMediaControlsVisibility();
        this.videoView.addView(this.controlBar);
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.BRIGHTCOVE_MEDIA_CONTROLLER, this);
        hashMap.put(AbstractEvent.BRIGHTCOVE_CONTROL_BAR, this.controlBar);
        this.eventEmitter.emit(CONTROL_BAR_CREATED, hashMap);
    }

    private void initializeSeekBar(Bundle bundle) {
        Log.d(TAG, "Initializing the seek bar...");
        this.seekBar = (BrightcoveSeekBar) this.controlBar.findViewById(R.id.seek_bar);
        this.seekBarController = new BrightcoveSeekBarController(this.seekBar, this.videoView);
        restoreSeekBarState(bundle);
    }

    private boolean isEnabled(int i) {
        return this.controlBar.getBooleanResource(i);
    }

    private void processAttributes() {
        this.showHideController.setShowHideAnimationStyle(getAnimationStyle(this.controlBar.getStringResource(R.styleable.BrightcoveMediaController_brightcove_animation_style, ShowHideController.ANIMATION_STYLE_FADE)));
        this.showHideController.setShowHideTimeout(this.controlBar.getIntegerResource(R.styleable.BrightcoveMediaController_brightcove_timeout, 3000));
        if (this.seekBar != null) {
            int i = -1;
            if (this.isTvMode) {
                i = -256;
            }
            this.seekBar.setMarkerColor(this.controlBar.getColorResource(R.styleable.BrightcoveMediaController_brightcove_marker_color, i));
            this.seekBar.setMarkerWidth(this.controlBar.getFloatResource(R.styleable.BrightcoveMediaController_brightcove_marker_width, 5.0f));
        }
    }

    private void register(ButtonController buttonController) {
        if (buttonController != null) {
            this.mediaControlRegistry.register(buttonController);
        }
    }

    /* access modifiers changed from: private */
    public void restoreSeekBarState(Bundle bundle) {
        if (bundle != null && bundle.size() > 0 && this.seekBar != null) {
            if (bundle.containsKey(SEEK_BAR_MAX)) {
                this.seekBar.setMax(bundle.getInt(SEEK_BAR_MAX));
            }
            if (bundle.containsKey(SEEK_BAR_PROGRESS)) {
                int i = bundle.getInt(SEEK_BAR_PROGRESS);
                Log.d(TAG, String.format(Locale.getDefault(), "Setting seekbar progress to %s.", new Object[]{Integer.valueOf(i)}));
                this.seekBar.setProgress(i);
            }
            if (bundle.containsKey(MARKER_LIST)) {
                int[] intArray = bundle.getIntArray(MARKER_LIST);
                if (intArray != null) {
                    for (int addMarker : intArray) {
                        this.seekBar.addMarker(addMarker);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void saveControlBarInstanceState(Event event) {
        Log.d(TAG, "Saving control bar instance state...");
        Object obj = event.properties.get(AbstractEvent.INSTANCE_STATE);
        Bundle controlBarBundle = getControlBarBundle(obj instanceof Bundle ? (Bundle) obj : new Bundle());
        controlBarBundle.putString("progress", getTimeValue(R.id.current_time));
        controlBarBundle.putString("duration", getTimeValue(R.id.end_time));
        ButtonController buttonController = this.mediaControlRegistry.getButtonController(R.id.live);
        if (this.videoView.getVideoDisplay().isLive() && (buttonController instanceof LiveButtonController)) {
            controlBarBundle.putBoolean(LiveButtonController.LIVE_EDGE_STATE, ((LiveButtonController) buttonController).isLiveEdgeState());
        }
        event.properties.put(AbstractEvent.INSTANCE_STATE, controlBarBundle);
    }

    /* access modifiers changed from: private */
    public void saveSeekBarInstanceState(Event event) {
        if (this.seekBar != null) {
            Log.d(TAG, "Saving seek bar instance state...");
            Object obj = event.properties.get(AbstractEvent.INSTANCE_STATE);
            event.properties.put(AbstractEvent.INSTANCE_STATE, getSeekBarBundle(obj instanceof Bundle ? (Bundle) obj : new Bundle()));
        }
    }

    /* access modifiers changed from: private */
    public Bundle getControlBarBundle(@NonNull Bundle bundle) {
        bundle.putString("progress", getTimeValue(R.id.current_time));
        bundle.putString("duration", getTimeValue(R.id.end_time));
        bundle.putBoolean(AbstractEvent.AUDIO_TRACKS_STATE, (getVisibilityState(R.id.audio_tracks) == 0 ? Boolean.TRUE : Boolean.FALSE).booleanValue());
        bundle.putBoolean(AbstractEvent.CAPTIONS_STATE, (getVisibilityState(R.id.captions) == 0 ? Boolean.TRUE : Boolean.FALSE).booleanValue());
        bundle.putBoolean(AbstractEvent.PICTURE_IN_PICTURE_STATE, (getVisibilityState(R.id.picture_in_picture) == 0 ? Boolean.TRUE : Boolean.FALSE).booleanValue());
        return bundle;
    }

    /* access modifiers changed from: private */
    public Bundle getSeekBarBundle(@NonNull Bundle bundle) {
        BrightcoveSeekBar brightcoveSeekBar = this.seekBar;
        if (brightcoveSeekBar != null) {
            bundle.putInt(SEEK_BAR_MAX, brightcoveSeekBar.getMax());
            bundle.putInt(SEEK_BAR_PROGRESS, this.seekBar.getProgress());
            List markers = this.seekBar.getMarkers();
            int[] iArr = new int[markers.size()];
            for (int i = 0; i < markers.size(); i++) {
                iArr[i] = ((Integer) markers.get(i)).intValue();
            }
            bundle.putIntArray(MARKER_LIST, iArr);
        }
        return bundle;
    }

    private TextView setupTimeValue(int i, String str, Bundle bundle) {
        TextView textView = (TextView) this.controlBar.findViewById(i);
        if (!(textView == null || bundle == null)) {
            textView.setText((str == null || !bundle.containsKey(str)) ? StringUtil.ZERO_TIME_STRING : bundle.getString(str));
        }
        return textView;
    }

    /* access modifiers changed from: private */
    public void setMediaControlsVisibility() {
        Log.d(TAG, "Setting the visibility on the player controls...");
        this.controlBar.setVisibility(4);
        SparseArray standardMediaControls = getStandardMediaControls();
        boolean isLive = this.videoView.getVideoDisplay().isLive();
        boolean z = this.videoView.getVideoDisplay().hasDvr() && this.isDVRControllerEnabled;
        String str = TAG;
        Locale locale = Locale.getDefault();
        String str2 = "The video is %1$s.";
        Object[] objArr = new Object[1];
        objArr[0] = isLive ? "live" : "not live";
        Log.d(str, String.format(locale, str2, objArr));
        String str3 = TAG;
        Locale locale2 = Locale.getDefault();
        String str4 = "The video does %1$s DVR support.";
        Object[] objArr2 = new Object[1];
        objArr2[0] = z ? "have" : "not have";
        Log.d(str3, String.format(locale2, str4, objArr2));
        for (int i = 0; i < standardMediaControls.size(); i++) {
            int keyAt = standardMediaControls.keyAt(i);
            View findViewById = this.controlBar.findViewById(keyAt);
            if (findViewById != null) {
                int i2 = 8;
                if (keyAt == R.id.two_line_spacer) {
                    i2 = 0;
                } else if (findViewById instanceof Button) {
                    i2 = getVisibilityState(keyAt);
                } else if (keyAt == R.id.one_line_spacer) {
                    if (isLive && !z) {
                        i2 = 0;
                    }
                } else if (keyAt == R.id.time_separator || keyAt == R.id.end_time) {
                    if (!isLive) {
                        i2 = 0;
                    }
                } else if (!isLive || z || !(keyAt == R.id.current_time || keyAt == R.id.seek_bar)) {
                    if (keyAt != R.id.seek_bar) {
                        Log.w(TAG, String.format(Locale.getDefault(), "View %1$s has not been handled.  It will be visible.", new Object[]{standardMediaControls.get(keyAt)}));
                        i2 = 0;
                    } else if (isEnabled(BrightcoveControlBar.SEEK_BAR)) {
                        i2 = 0;
                    }
                }
                findViewById.setVisibility(i2);
            }
        }
        this.controlBar.invalidate();
    }

    /* access modifiers changed from: private */
    public void updateDuration(int i) {
        if (i != -1 && !this.videoView.getVideoDisplay().isLive()) {
            TextView textView = this.endTime;
            if (textView != null) {
                textView.setText(StringUtil.stringForTime((long) i));
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateProgress(int i) {
        TextView textView = this.currentTime;
        if (textView != null && i >= 0) {
            textView.setText(StringUtil.stringForTime((long) i));
        }
    }

    /* access modifiers changed from: private */
    public void setControlBarViewVisibility(int i, int i2) {
        View findViewById = this.controlBar.findViewById(i);
        if (findViewById != null) {
            findViewById.setVisibility(i2);
        }
    }

    /* access modifiers changed from: private */
    public void requestFocus(int i) {
        View findViewById = this.controlBar.findViewById(i);
        if (findViewById != null) {
            findViewById.requestFocus();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        updateKeyStates(keyEvent);
        RemoteControlKeyState remoteControlKeyState = this.currentKeyState;
        boolean z = false;
        if (remoteControlKeyState == null) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        boolean isShowing = isShowing();
        boolean isPlayerOptionsVisible = BrightcovePlayerOptionsManager.getInstance().isPlayerOptionsVisible();
        if (!this.isAdPlaying && !isPlayerOptionsVisible && keyEvent.getAction() == 1) {
            show();
        }
        if (!this.isAdPlaying && !isPlayerOptionsVisible && !isShowing && isDpadControl(keyCode)) {
            return true;
        }
        if (keyCode == 4 && isPlayerOptionsVisible) {
            if (keyEvent.getAction() == 1) {
                this.eventEmitter.emit(EventType.HIDE_PLAYER_OPTIONS);
            }
            return true;
        } else if (keyCode == 4) {
            return remoteControlKeyState.onBack(keyEvent);
        } else {
            if (keyCode != 85) {
                switch (keyCode) {
                    case 19:
                        return remoteControlKeyState.onDpadUp(keyEvent);
                    case 20:
                        return remoteControlKeyState.onDpadDown(keyEvent);
                    case 21:
                        return remoteControlKeyState.onDpadLeft(keyEvent);
                    case 22:
                        return remoteControlKeyState.onDpadRight(keyEvent);
                    case 23:
                        return remoteControlKeyState.onDpadCenter(keyEvent);
                    default:
                        switch (keyCode) {
                            case 89:
                                RemoteControlKeyState remoteControlKeyState2 = this.rewindKeyController;
                                if (remoteControlKeyState2 != null && remoteControlKeyState2.onRewind(keyEvent)) {
                                    z = true;
                                }
                                return z;
                            case 90:
                                RemoteControlKeyState remoteControlKeyState3 = this.fastForwardKeyController;
                                if (remoteControlKeyState3 != null && remoteControlKeyState3.onFastForward(keyEvent)) {
                                    z = true;
                                }
                                return z;
                            default:
                                return false;
                        }
                }
            } else {
                RemoteControlKeyState remoteControlKeyState4 = this.defaultKeyState;
                if (remoteControlKeyState4 != null && remoteControlKeyState4.onPlayPause(keyEvent)) {
                    z = true;
                }
                return z;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateKeyStates(KeyEvent keyEvent) {
        View findFocusedView = findFocusedView(this.controlBar);
        if (this.mediaControlRegistry != null) {
            ButtonController buttonController = null;
            if (keyEvent.getAction() == 0) {
                if (findFocusedView != null) {
                    buttonController = this.mediaControlRegistry.getButtonController(findFocusedView.getId());
                }
                changeState((RemoteControlKeyState) buttonController);
            }
        }
    }

    private void resetKeyStates() {
        this.currentKeyState = this.defaultKeyState;
        this.nextKeyState = null;
    }

    private void changeState(RemoteControlKeyState remoteControlKeyState) {
        RemoteControlKeyState remoteControlKeyState2 = this.nextKeyState;
        if (remoteControlKeyState2 == null) {
            remoteControlKeyState2 = this.defaultKeyState;
        }
        this.currentKeyState = remoteControlKeyState2;
        this.nextKeyState = remoteControlKeyState;
    }

    private View findFocusedView(View view) {
        return view instanceof ViewGroup ? findFocusedView(((ViewGroup) view).getFocusedChild()) : view;
    }

    /* access modifiers changed from: private */
    public void removeControllerListeners() {
        this.showHideController.removeListeners();
        BrightcoveSeekBarController brightcoveSeekBarController = this.seekBarController;
        if (brightcoveSeekBarController != null) {
            brightcoveSeekBarController.removeListeners();
        }
        for (ButtonController removeListeners : this.mediaControlRegistry.getButtonControllers()) {
            removeListeners.removeListeners();
        }
    }

    /* access modifiers changed from: private */
    public void updateSeekBar(Event event) {
        setSeekBarMax(event);
        setSeekBarProgress(event);
    }

    private void setSeekBarProgress(Event event) {
        int integerProperty = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
        if (integerProperty != -1 && this.seekBarController != null) {
            if (this.videoView.getVideoDisplay().isLive()) {
                integerProperty -= this.seekBarController.getSeekBarOffset();
                int integerProperty2 = event.getIntegerProperty(AbstractEvent.MAX_POSITION);
                if (integerProperty2 > 0 && integerProperty > integerProperty2) {
                    integerProperty = integerProperty2;
                }
            }
            this.seekBarController.setSeekBarProgress(integerProperty);
        }
    }

    /* access modifiers changed from: private */
    public void setSeekBarMax(Event event) {
        int i;
        int integerProperty = event.getIntegerProperty("duration");
        if (integerProperty != -1 && this.seekBarController != null) {
            if (this.videoView.getVideoDisplay().isLive()) {
                int integerProperty2 = event.getIntegerProperty(AbstractEvent.MAX_POSITION);
                i = event.getIntegerProperty(AbstractEvent.MIN_POSITION);
                if (integerProperty2 > 0 && i >= 0) {
                    integerProperty = integerProperty2 - i;
                    this.seekBarController.setSeekBarOffset(i);
                    this.seekBarController.setSeekBarMax(integerProperty);
                }
            }
            i = 0;
            this.seekBarController.setSeekBarOffset(i);
            this.seekBarController.setSeekBarMax(integerProperty);
        }
    }
}
