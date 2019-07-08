package com.brightcove.player.mediacontroller.buttons;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.mediacontroller.BrightcoveMediaController;
import com.brightcove.player.view.BaseVideoView;
import java.util.HashMap;
import java.util.Map;

public abstract class SeekButtonController extends AbstractButtonController {
    protected static final int DEFAULT_ON_HOLD_START_TIME = 500;
    protected static final int DEFAULT_ON_HOLD_UPDATE_FREQUENCY = 40;
    protected static final int DEFAULT_SEEK_PERCENTAGE = 1;
    protected static final int LIVE_OFFSET = 10000;
    /* access modifiers changed from: private */
    public static final String TAG = RewindButtonController.class.getSimpleName();
    protected final OnHoldHandler onHoldHandler;
    protected int onHoldUpdateFrequency = 40;
    protected int onHoldWaitTime = 500;
    protected int seekDefault = 3000;
    protected int seekPercentage = 1;
    protected boolean seekRelativeEnabled = false;
    protected int seekStartPosition;
    protected int seekTargetPosition;

    protected class HideSeekControlsHandler implements EventListener {
        protected HideSeekControlsHandler() {
        }

        @Default
        public void processEvent(Event event) {
            SeekButtonController.this.setVisibility(4);
        }
    }

    protected class OnHoldHandler extends Handler {
        private final int BUTTON_HOLD_DOWN = 1;
        private final int BUTTON_PRESSED_DOWN = 0;
        private final int BUTTON_RELEASE = 2;
        private boolean isDragging;
        private int seekProgress;

        public OnHoldHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (!this.isDragging) {
                        sendMessageAtFrontOfQueue(obtainMessage(1));
                        this.seekProgress = SeekButtonController.this.videoView.getCurrentPosition();
                        SeekButtonController.this.eventEmitter.emit(EventType.SEEKBAR_DRAGGING_START);
                        this.isDragging = true;
                        return;
                    }
                    return;
                case 1:
                    SeekButtonController seekButtonController = SeekButtonController.this;
                    this.seekProgress = seekButtonController.computeTargetSeekPosition(this.seekProgress, seekButtonController.seekDefault);
                    HashMap hashMap = new HashMap();
                    hashMap.put(AbstractEvent.SEEK_PROGRESS, Integer.valueOf(this.seekProgress));
                    SeekButtonController.this.eventEmitter.emit(EventType.SEEKBAR_DRAGGING_PROGRESS, hashMap);
                    sendMessageDelayed(obtainMessage(1), (long) SeekButtonController.this.onHoldUpdateFrequency);
                    return;
                case 2:
                    removeMessages(1);
                    SeekButtonController.this.eventEmitter.emit(EventType.SEEKBAR_DRAGGING_STOP);
                    this.isDragging = false;
                    return;
                default:
                    return;
            }
        }
    }

    protected class SeekConfigurationHandler implements EventListener {
        protected SeekConfigurationHandler() {
        }

        @Default
        public void processEvent(Event event) {
            if (event.properties != null) {
                try {
                    Integer num = (Integer) event.properties.get(AbstractEvent.SEEK_DEFAULT);
                    if (num != null) {
                        SeekButtonController.this.seekDefault = num.intValue();
                    }
                    Boolean bool = (Boolean) event.properties.get(AbstractEvent.SEEK_RELATIVE_ENABLED);
                    if (bool != null) {
                        SeekButtonController.this.seekRelativeEnabled = bool.booleanValue();
                    }
                    Integer num2 = (Integer) event.properties.get(AbstractEvent.SEEK_PERCENTAGE);
                    if (num2 != null) {
                        SeekButtonController.this.seekPercentage = num2.intValue();
                    }
                    Integer num3 = (Integer) event.properties.get(AbstractEvent.SEEK_ON_HOLD_WAIT_TIME);
                    if (num3 != null) {
                        SeekButtonController.this.onHoldWaitTime = num3.intValue();
                    }
                    Integer num4 = (Integer) event.properties.get(AbstractEvent.SEEK_ON_HOLD_UPDATE_FREQ);
                    if (num4 != null) {
                        SeekButtonController.this.onHoldUpdateFrequency = num4.intValue();
                    }
                } catch (ClassCastException e) {
                    Log.e(SeekButtonController.TAG, "Failed to update seek bar controller settings", e);
                }
            }
        }
    }

    protected class SeekHandler implements EventListener {
        protected SeekHandler() {
        }

        @Default
        public void processEvent(Event event) {
            int i = SeekButtonController.this.videoView.getVideoDisplay().isLive() ? 10000 : SeekButtonController.this.seekDefault;
            SeekButtonController.this.seekStartPosition = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
            if (SeekButtonController.this.seekStartPosition <= 0) {
                SeekButtonController seekButtonController = SeekButtonController.this;
                seekButtonController.seekStartPosition = seekButtonController.videoView.getCurrentPosition();
            }
            SeekButtonController seekButtonController2 = SeekButtonController.this;
            seekButtonController2.seekTargetPosition = seekButtonController2.computeTargetSeekPosition(seekButtonController2.seekStartPosition, i);
            HashMap hashMap = new HashMap();
            hashMap.put(AbstractEvent.SEEK_POSITION, Integer.valueOf(SeekButtonController.this.seekTargetPosition));
            SeekButtonController.this.eventEmitter.emit(EventType.SEEK_TO, hashMap);
            EventListener didSeekHandler = SeekButtonController.this.getDidSeekHandler();
            if (didSeekHandler != null) {
                SeekButtonController.this.eventEmitter.once(EventType.DID_SEEK_TO, didSeekHandler);
            }
        }
    }

    protected class SeekOffsetHandler implements EventListener {
        protected SeekOffsetHandler() {
        }

        public void processEvent(Event event) {
            if (SeekButtonController.this.seekRelativeEnabled) {
                int duration = (SeekButtonController.this.videoView.getDuration() * SeekButtonController.this.seekPercentage) / 100;
                if (duration > 0 && duration < SeekButtonController.this.videoView.getDuration()) {
                    SeekButtonController.this.seekDefault = duration;
                }
            }
        }
    }

    protected class ShowSeekControlsHandler implements EventListener {
        protected ShowSeekControlsHandler() {
        }

        @Default
        public void processEvent(Event event) {
            SeekButtonController.this.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public abstract int computeTargetSeekPosition(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract EventListener getDidSeekHandler();

    public int getManagedState() {
        return 0;
    }

    public SeekButtonController(@NonNull Context context, @NonNull BaseVideoView baseVideoView, @NonNull View view, int i, @Nullable Typeface typeface, String str) {
        super(context, baseVideoView, view, i, typeface);
        addListener(str, new SeekHandler());
        addListener(EventType.HIDE_SEEK_CONTROLS, new HideSeekControlsHandler());
        addListener(EventType.SHOW_SEEK_CONTROLS, new ShowSeekControlsHandler());
        addListener(EventType.VIDEO_DURATION_CHANGED, new SeekOffsetHandler());
        addListener(EventType.SEEK_CONTROLLER_CONFIGURATION, new SeekConfigurationHandler());
        this.onHoldHandler = new OnHoldHandler(context.getMainLooper());
    }

    public Map<String, Object> getProperties() {
        this.properties.clear();
        this.properties.put(AbstractEvent.PLAYHEAD_POSITION, Integer.valueOf(this.videoView.getCurrentPosition()));
        return this.properties;
    }

    public int getVisibilityState() {
        boolean isLive = this.videoView.getVideoDisplay().isLive();
        boolean hasDvr = this.videoView.getVideoDisplay().hasDvr();
        BrightcoveMediaController brightcoveMediaController = this.videoView.getBrightcoveMediaController();
        if (brightcoveMediaController != null) {
            hasDvr = hasDvr && brightcoveMediaController.isDVRControllerEnabled();
        }
        if (!isLive || hasDvr) {
            return 0;
        }
        return 8;
    }

    public int getSeekDefault() {
        return this.seekDefault;
    }

    public void setSeekDefault(int i) {
        this.seekDefault = i;
    }

    public boolean isSeekRelativeEnabled() {
        return this.seekRelativeEnabled;
    }

    public void setSeekRelativeEnabled(boolean z) {
        this.seekRelativeEnabled = z;
    }

    public int getSeekPercentage() {
        return this.seekPercentage;
    }

    public void setSeekPercentage(int i) {
        if (i > 0 || i < 100) {
            this.seekPercentage = i;
        }
    }

    public int getOnHoldWaitTime() {
        return this.onHoldWaitTime;
    }

    public void setOnHoldWaitTime(int i) {
        this.onHoldWaitTime = i;
    }

    public int getOnHoldUpdateFrequency() {
        return this.onHoldUpdateFrequency;
    }

    public void setOnHoldUpdateFrequency(int i) {
        this.onHoldUpdateFrequency = i;
    }

    public boolean onDpadCenter(KeyEvent keyEvent) {
        return handleSeekEvent(keyEvent);
    }

    /* access modifiers changed from: protected */
    public boolean handleSeekEvent(KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        long uptimeMillis = SystemClock.uptimeMillis();
        if (action == 0) {
            if (uptimeMillis - keyEvent.getDownTime() > ((long) this.onHoldWaitTime)) {
                OnHoldHandler onHoldHandler2 = this.onHoldHandler;
                onHoldHandler2.sendMessage(onHoldHandler2.obtainMessage(0));
                return true;
            }
        } else if (action == 1 && uptimeMillis - keyEvent.getDownTime() > ((long) this.onHoldWaitTime)) {
            OnHoldHandler onHoldHandler3 = this.onHoldHandler;
            onHoldHandler3.sendMessage(onHoldHandler3.obtainMessage(2));
            return true;
        }
        return false;
    }
}
