package com.brightcove.player.mediacontroller;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.model.CuePoint;
import com.brightcove.player.model.CuePoint.PositionType;
import com.brightcove.player.view.BaseVideoView;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class BrightcoveSeekBarController extends AbstractComponent {
    /* access modifiers changed from: private */
    public static final String TAG = "BrightcoveSeekBarController";
    /* access modifiers changed from: private */
    public boolean dragging;
    /* access modifiers changed from: private */
    public int offset = 0;
    /* access modifiers changed from: private */
    public BrightcoveSeekBar seekBar;
    /* access modifiers changed from: private */
    public BaseVideoView videoView;

    private class BufferedUpdateHandler implements EventListener {
        private BufferedUpdateHandler() {
        }

        @Default
        public void processEvent(Event event) {
            int integerProperty = event.getIntegerProperty(AbstractEvent.PERCENT_COMPLETE);
            if (integerProperty > -1) {
                BrightcoveSeekBarController.this.seekBar.setSecondaryProgress((BrightcoveSeekBarController.this.seekBar.getMax() * integerProperty) / 100);
            }
        }
    }

    private class DraggingHandler implements EventListener {
        private DraggingHandler() {
        }

        @Default
        public void processEvent(Event event) {
            String type = event.getType();
            if (EventType.SEEKBAR_DRAGGING_PROGRESS.equals(type)) {
                if (!BrightcoveSeekBarController.this.dragging) {
                    BrightcoveSeekBarController.this.setDragging(true);
                }
                int integerProperty = event.getIntegerProperty(AbstractEvent.SEEK_PROGRESS);
                BrightcoveSeekBarController.this.eventEmitter.emit(ShowHideController.SHOW_MEDIA_CONTROLS);
                BrightcoveSeekBarController.this.seekBar.setProgress(integerProperty);
            } else if (EventType.SEEKBAR_DRAGGING_STOP.equals(type)) {
                BrightcoveSeekBarController.this.onStopDragging();
            }
        }
    }

    private class HideSeekControlsHandler implements EventListener {
        private HideSeekControlsHandler() {
        }

        @Default
        public void processEvent(Event event) {
            BrightcoveSeekBarController.this.seekBar.setVisibility(4);
        }
    }

    private class SeekBarChangeHandler implements OnSeekBarChangeListener {
        private int lastProgress = -1;
        private int progressIncrement = 10;

        SeekBarChangeHandler() {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            BrightcoveSeekBarController.this.onStartDragging();
            this.progressIncrement = Math.round(((float) seekBar.getMax()) * 0.01f);
            BrightcoveSeekBarController.this.eventEmitter.emit(EventType.SEEKBAR_DRAGGING_START);
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                int i2 = this.lastProgress;
                if (i2 == -1 || Math.abs(i - i2) > this.progressIncrement) {
                    this.lastProgress = i;
                    HashMap hashMap = new HashMap();
                    hashMap.put(AbstractEvent.SEEK_PROGRESS, Integer.valueOf(i));
                    BrightcoveSeekBarController.this.eventEmitter.emit(EventType.SEEKBAR_DRAGGING_PROGRESS, hashMap);
                }
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            this.lastProgress = -1;
            BrightcoveSeekBarController.this.eventEmitter.emit(EventType.SEEKBAR_DRAGGING_STOP);
        }
    }

    private class SeekToHandler implements EventListener {
        private SeekToHandler() {
        }

        @Default
        public void processEvent(Event event) {
            int i;
            if (!BrightcoveSeekBarController.this.isDragging()) {
                if (event.properties.containsKey(AbstractEvent.ORIGINAL_SEEK_POSITION)) {
                    i = event.getIntegerProperty(AbstractEvent.ORIGINAL_SEEK_POSITION);
                } else {
                    i = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
                }
                if (i != -1) {
                    if (BrightcoveSeekBarController.this.videoView.getVideoDisplay().isLive()) {
                        i -= BrightcoveSeekBarController.this.offset;
                    }
                    BrightcoveSeekBarController.this.seekBar.setProgress(i);
                    return;
                }
                return;
            }
            Log.v(BrightcoveSeekBarController.TAG, "The seek bar is being dragged.  No SEEK_TO updates are being applied.");
        }
    }

    private class SetMarkersHandler implements EventListener {
        private SetMarkersHandler() {
        }

        @Default
        public void processEvent(Event event) {
            Object obj = event.properties.get(BrightcoveMediaController.MARKER_LIST);
            if (obj instanceof int[]) {
                Log.d(BrightcoveSeekBarController.TAG, String.format(Locale.getDefault(), "tbd %s", new Object[]{obj}));
                BrightcoveSeekBarController.this.seekBar.clearMarkers();
                for (int addMarker : (int[]) obj) {
                    BrightcoveSeekBarController.this.seekBar.addMarker(addMarker);
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                if (list.isEmpty()) {
                    BrightcoveSeekBarController.this.seekBar.clearMarkers();
                }
                for (Object next : list) {
                    Log.d(BrightcoveSeekBarController.TAG, String.format(Locale.getDefault(), "Processing a marker item of type {%s}.", new Object[]{next.getClass().getSimpleName()}));
                    if (next instanceof Integer) {
                        BrightcoveSeekBarController.this.seekBar.addMarker(((Integer) next).intValue());
                    } else if (next instanceof CuePoint) {
                        CuePoint cuePoint = (CuePoint) next;
                        if (cuePoint.getPositionType() == PositionType.POINT_IN_TIME) {
                            int position = cuePoint.getPosition();
                            Log.d(BrightcoveSeekBarController.TAG, String.format(Locale.getDefault(), "Adding a marker at position {%d}.", new Object[]{Integer.valueOf(position)}));
                            BrightcoveSeekBarController.this.seekBar.addMarker(position);
                        }
                    } else {
                        Log.e(BrightcoveSeekBarController.TAG, String.format(Locale.getDefault(), "Invalid marker type {%s} encountered.", new Object[]{next.getClass().getSimpleName()}));
                        return;
                    }
                }
            } else {
                BrightcoveSeekBarController.this.seekBar.clearMarkers();
                String str = "The markers payload {%s} type is invalid.  Should be either int[], List<Integer> or List<CuePoint>.";
                String access$1300 = BrightcoveSeekBarController.TAG;
                Locale locale = Locale.getDefault();
                Object[] objArr = new Object[1];
                objArr[0] = obj == null ? "" : obj.getClass().getSimpleName();
                Log.e(access$1300, String.format(locale, str, objArr));
            }
        }
    }

    private class ShowSeekControlsHandler implements EventListener {
        private ShowSeekControlsHandler() {
        }

        @Default
        public void processEvent(Event event) {
            BrightcoveSeekBarController.this.seekBar.setVisibility(0);
        }
    }

    private class WillResumeContentHandler implements EventListener {
        private WillResumeContentHandler() {
        }

        @Default
        public void processEvent(Event event) {
            int currentPosition = BrightcoveSeekBarController.this.videoView.getCurrentPosition();
            if (currentPosition >= 0) {
                BrightcoveSeekBarController.this.seekBar.setMax(BrightcoveSeekBarController.this.videoView.getDuration());
                BrightcoveSeekBarController.this.seekBar.setProgress(currentPosition);
            }
        }
    }

    public BrightcoveSeekBarController(@NonNull BrightcoveSeekBar brightcoveSeekBar, @NonNull BaseVideoView baseVideoView) {
        super(baseVideoView.getEventEmitter());
        this.seekBar = brightcoveSeekBar;
        this.videoView = baseVideoView;
        brightcoveSeekBar.setOnSeekBarChangeListener(new SeekBarChangeHandler());
        addListener(BrightcoveMediaController.SET_MARKERS, new SetMarkersHandler());
        addListener(EventType.BUFFERED_UPDATE, new BufferedUpdateHandler());
        addListener(EventType.HIDE_SEEK_CONTROLS, new HideSeekControlsHandler());
        addListener(EventType.SHOW_SEEK_CONTROLS, new ShowSeekControlsHandler());
        Log.d("SSAI-TEST", ">>> Adding PROGRESS to SeekBarController");
        addListener(EventType.SEEK_TO, new SeekToHandler());
        DraggingHandler draggingHandler = new DraggingHandler();
        addListener(EventType.SEEKBAR_DRAGGING_PROGRESS, draggingHandler);
        addListener(EventType.SEEKBAR_DRAGGING_STOP, draggingHandler);
        addListener(EventType.WILL_RESUME_CONTENT, new WillResumeContentHandler());
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public void setDragging(boolean z) {
        this.dragging = z;
    }

    /* access modifiers changed from: 0000 */
    public void setSeekBarMax(int i) {
        if (i >= 0) {
            this.seekBar.setMax(i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setSeekBarProgress(int i) {
        if (i != -1) {
            if (i > this.seekBar.getMax()) {
                i = this.seekBar.getMax();
            }
            this.seekBar.setProgress(i);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getSeekBarOffset() {
        return this.offset;
    }

    /* access modifiers changed from: 0000 */
    public void setSeekBarOffset(int i) {
        if (i >= 0) {
            this.offset = i;
        }
    }

    /* access modifiers changed from: private */
    public void onStartDragging() {
        this.eventEmitter.emit(ShowHideController.SHOW_MEDIA_CONTROLS);
        setDragging(true);
    }

    /* access modifiers changed from: private */
    public void onStopDragging() {
        this.videoView.seekTo(this.seekBar.getProgress() + this.offset);
        this.eventEmitter.emit(ShowHideController.SHOW_MEDIA_CONTROLS);
        setDragging(false);
    }
}
