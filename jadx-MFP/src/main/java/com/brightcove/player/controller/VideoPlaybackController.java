package com.brightcove.player.controller;

import android.util.Log;
import com.brightcove.player.event.AbstractComponent;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.model.CuePoint;
import com.brightcove.player.model.CuePoint.PositionType;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.EventUtil;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ListensFor(events = {"completed", "cuePoint", "seekTo", "didSeekTo", "didSetSource", "play", "didPlay", "progress", "readyToPlay", "removeCuePoint", "setCuePoint", "setCuePoints", "setVideo", "willChangeVideo"})
@Emits(events = {"completed", "cuePoint", "didSelectSource", "didSetVideo", "play", "selectSource", "setSource", "setVideoStill"})
public class VideoPlaybackController extends AbstractComponent implements Component {
    public static final String TAG = "VideoPlaybackController";
    protected boolean adsDisabled;
    /* access modifiers changed from: private */
    public List<CuePoint> cuePoints;
    /* access modifiers changed from: private */
    public int currentTime;
    /* access modifiers changed from: private */
    public boolean hasSeenPlayerReadyEvent;
    protected int liveWindowTimeMs = 0;
    OnDidSeekToListener onDidSeekToListener;
    OnDidSetSourceListener onDidSetSourceListener;
    OnPlayListener onPlayListener;
    OnPlayerReadyListener onPlayerReadyListener;
    OnProgressUpdateListener onProgressUpdateListener;
    OnRemoveCuePointListener onRemoveCuePointListener;
    OnSeekToListener onSeekToListener;
    OnSetCuePointListener onSetCuePointListener;
    OnSetCuePointsListener onSetCuePointsListener;
    OnSetVideoListener onSetVideoListener;
    OnVideoCompletedListener onVideoCompletedListener;
    OnWillChangeVideoListener onWillChangeVideoListener;
    /* access modifiers changed from: private */
    public List<Source> pendingSourcesToLoad;
    /* access modifiers changed from: private */
    public boolean seekWithoutAds;
    /* access modifiers changed from: private */
    public Map<Source, Video> sourceToVideoMapping;
    /* access modifiers changed from: private */
    public boolean videoStarted;

    protected class OnDidSeekToListener implements EventListener {
        protected OnDidSeekToListener() {
        }

        @Default
        public void processEvent(Event event) {
            int access$000 = VideoPlaybackController.this.currentTime;
            int integerProperty = event.getIntegerProperty(AbstractEvent.SEEK_POSITION);
            VideoPlaybackController videoPlaybackController = VideoPlaybackController.this;
            if (integerProperty == -1) {
                integerProperty = 0;
            }
            videoPlaybackController.currentTime = integerProperty;
            if (access$000 < VideoPlaybackController.this.currentTime) {
                VideoPlaybackController videoPlaybackController2 = VideoPlaybackController.this;
                videoPlaybackController2.emitCuePoints(access$000, videoPlaybackController2.currentTime);
                return;
            }
            VideoPlaybackController videoPlaybackController3 = VideoPlaybackController.this;
            videoPlaybackController3.emitCuePoints(videoPlaybackController3.currentTime, access$000);
        }
    }

    protected class OnDidSetSourceListener implements EventListener {
        protected OnDidSetSourceListener() {
        }

        @Default
        public void processEvent(Event event) {
            Video video = (Video) event.properties.get("video");
            Source source = (Source) event.properties.get("source");
            if (video != null && source != null) {
                EventUtil.emit(VideoPlaybackController.this.eventEmitter, EventType.DID_SET_VIDEO, video, source);
            }
        }
    }

    protected class OnPlayListener implements EventListener {
        protected OnPlayListener() {
        }

        @Default
        public void processEvent(final Event event) {
            String str = VideoPlaybackController.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnPlayListener: playEvent = ");
            sb.append(event);
            sb.append(", currentTime = ");
            sb.append(VideoPlaybackController.this.currentTime);
            Log.v(str, sb.toString());
            if ((!VideoPlaybackController.this.videoStarted || VideoPlaybackController.this.currentTime == 0) && !event.properties.containsKey(AbstractEvent.SKIP_CUE_POINTS)) {
                String str2 = VideoPlaybackController.TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("cuePoints = ");
                sb2.append(VideoPlaybackController.this.cuePoints);
                Log.i(str2, sb2.toString());
                ArrayList arrayList = new ArrayList();
                for (CuePoint cuePoint : VideoPlaybackController.this.cuePoints) {
                    if (cuePoint.getPositionType().equals(PositionType.BEFORE) || (cuePoint.getPositionType().equals(PositionType.POINT_IN_TIME) && cuePoint.getPosition() == 0)) {
                        arrayList.add(cuePoint);
                    }
                }
                if (!VideoPlaybackController.this.seekWithoutAds && !arrayList.isEmpty()) {
                    String str3 = VideoPlaybackController.TAG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("batch = ");
                    sb3.append(arrayList);
                    Log.i(str3, sb3.toString());
                    event.preventDefault();
                    event.stopPropagation();
                    event.properties.put(AbstractEvent.SKIP_CUE_POINTS, Boolean.valueOf(true));
                    final UUID randomUUID = UUID.randomUUID();
                    VideoPlaybackController.this.eventEmitter.once(EventType.CUE_POINT, new EventListener() {
                        @Default
                        public void processEvent(Event event) {
                            if (randomUUID.equals(event.properties.get(AbstractEvent.UUID))) {
                                VideoPlaybackController.this.eventEmitter.emit(event.getType(), event.properties);
                            }
                        }
                    });
                    HashMap hashMap = new HashMap();
                    hashMap.put(AbstractEvent.CUE_POINTS, arrayList);
                    hashMap.put(AbstractEvent.END_TIME, Integer.valueOf(0));
                    hashMap.put("startTime", Integer.valueOf(0));
                    hashMap.put(AbstractEvent.ORIGINAL_EVENT, event);
                    hashMap.put(AbstractEvent.UUID, randomUUID);
                    VideoPlaybackController.this.eventEmitter.emit(EventType.CUE_POINT, hashMap);
                }
            }
        }
    }

    protected class OnPlayerReadyListener implements EventListener {
        protected OnPlayerReadyListener() {
        }

        @Default
        public void processEvent(Event event) {
            if (!VideoPlaybackController.this.hasSeenPlayerReadyEvent) {
                VideoPlaybackController.this.hasSeenPlayerReadyEvent = true;
                if (VideoPlaybackController.this.pendingSourcesToLoad.size() > 0) {
                    while (VideoPlaybackController.this.pendingSourcesToLoad.size() > 0) {
                        Source source = (Source) VideoPlaybackController.this.pendingSourcesToLoad.remove(0);
                        EventUtil.emit(VideoPlaybackController.this.eventEmitter, EventType.SET_SOURCE, (Video) VideoPlaybackController.this.sourceToVideoMapping.remove(source), source);
                    }
                }
            }
        }
    }

    private class OnProgressUpdateListener implements EventListener {
        private OnProgressUpdateListener() {
        }

        @Default
        public void processEvent(Event event) {
            int integerProperty = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
            if (VideoPlaybackController.this.currentTime != -1 && VideoPlaybackController.this.currentTime < integerProperty) {
                int access$000 = VideoPlaybackController.this.currentTime;
                VideoPlaybackController.this.currentTime = integerProperty;
                VideoPlaybackController videoPlaybackController = VideoPlaybackController.this;
                videoPlaybackController.emitCuePoints(access$000, videoPlaybackController.currentTime);
            }
            VideoPlaybackController.this.seekWithoutAds = false;
        }
    }

    protected class OnRemoveCuePointListener implements EventListener {
        protected OnRemoveCuePointListener() {
        }

        @Default
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.CUE_POINT);
            if (obj == null || !(obj instanceof CuePoint)) {
                Log.e(VideoPlaybackController.TAG, "could not remove CuePoint");
                return;
            }
            VideoPlaybackController.this.cuePoints.remove((CuePoint) obj);
        }
    }

    protected class OnSeekToListener implements EventListener {
        protected OnSeekToListener() {
        }

        public void processEvent(Event event) {
            if (VideoPlaybackController.this.adsDisabled) {
                VideoPlaybackController.this.seekWithoutAds = true;
            }
        }
    }

    protected class OnSetCuePointListener implements EventListener {
        protected OnSetCuePointListener() {
        }

        @Default
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.CUE_POINT);
            if (obj == null || !(obj instanceof CuePoint)) {
                Log.e(VideoPlaybackController.TAG, "could not find CuePoint in given Event");
                return;
            }
            VideoPlaybackController.this.cuePoints.add((CuePoint) obj);
        }
    }

    protected class OnSetCuePointsListener implements EventListener {
        protected OnSetCuePointsListener() {
        }

        @Default
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.CUE_POINTS);
            if (obj == null || !(obj instanceof List)) {
                Log.e(VideoPlaybackController.TAG, "could not find List of CuePoints in given Event");
                return;
            }
            List list = (List) obj;
            VideoPlaybackController.this.cuePoints.clear();
            VideoPlaybackController.this.cuePoints.addAll(list);
        }
    }

    protected class OnSetVideoListener implements EventListener {
        protected OnSetVideoListener() {
        }

        @Default
        public void processEvent(Event event) {
            Video video = (Video) event.properties.get("video");
            String str = VideoPlaybackController.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnSetVideoListener: ");
            sb.append(video);
            Log.v(str, sb.toString());
            HashMap hashMap = new HashMap();
            URI posterImage = video.getPosterImage();
            if (posterImage != null) {
                hashMap.put(AbstractEvent.VIDEO_STILL, posterImage);
            }
            VideoPlaybackController.this.eventEmitter.emit(EventType.SET_VIDEO_STILL, hashMap);
            List cuePoints = video.getCuePoints();
            if (cuePoints != null) {
                VideoPlaybackController.this.cuePoints.addAll(cuePoints);
            }
            HashMap hashMap2 = new HashMap();
            hashMap2.put("video", video);
            OnSourceSelectedListener onSourceSelectedListener = new OnSourceSelectedListener();
            onSourceSelectedListener.currentVideo = video;
            VideoPlaybackController.this.eventEmitter.request(EventType.SELECT_SOURCE, hashMap2, onSourceSelectedListener);
        }
    }

    protected class OnSourceSelectedListener implements EventListener {
        public Video currentVideo;

        protected OnSourceSelectedListener() {
        }

        @Default
        public void processEvent(Event event) {
            Log.v(VideoPlaybackController.TAG, "OnSourceSelectedListener");
            Source source = (Source) event.properties.get("source");
            VideoPlaybackController.this.sourceToVideoMapping.put(source, this.currentVideo);
            EventUtil.emit(VideoPlaybackController.this.eventEmitter, EventType.DID_SELECT_SOURCE, this.currentVideo, source);
            if (VideoPlaybackController.this.hasSeenPlayerReadyEvent) {
                EventUtil.emit(VideoPlaybackController.this.eventEmitter, EventType.SET_SOURCE, this.currentVideo, source);
            } else {
                VideoPlaybackController.this.pendingSourcesToLoad.add(source);
            }
        }
    }

    protected class OnVideoCompletedListener implements EventListener {
        protected OnVideoCompletedListener() {
        }

        @Default
        public void processEvent(Event event) {
            String str = VideoPlaybackController.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("OnVideoCompletedListener: ");
            sb.append(event);
            Log.v(str, sb.toString());
            VideoPlaybackController.this.videoStarted = false;
            VideoPlaybackController.this.currentTime = 0;
            if (!event.properties.containsKey(AbstractEvent.SKIP_CUE_POINTS)) {
                ArrayList arrayList = new ArrayList();
                for (CuePoint cuePoint : VideoPlaybackController.this.cuePoints) {
                    if (cuePoint.getPositionType().equals(PositionType.AFTER)) {
                        arrayList.add(cuePoint);
                    }
                }
                if (!arrayList.isEmpty()) {
                    event.preventDefault();
                    event.stopPropagation();
                    event.properties.put(AbstractEvent.SKIP_CUE_POINTS, Boolean.valueOf(true));
                    HashMap hashMap = new HashMap();
                    hashMap.put(AbstractEvent.CUE_POINTS, arrayList);
                    hashMap.put(AbstractEvent.END_TIME, Integer.valueOf(0));
                    hashMap.put("startTime", Integer.valueOf(0));
                    hashMap.put(AbstractEvent.ORIGINAL_EVENT, event);
                    VideoPlaybackController.this.eventEmitter.emit(EventType.CUE_POINT, hashMap);
                }
            }
        }
    }

    protected class OnWillChangeVideoListener implements EventListener {
        protected OnWillChangeVideoListener() {
        }

        @Default
        public void processEvent(Event event) {
            VideoPlaybackController.this.videoStarted = false;
            VideoPlaybackController.this.cuePoints.clear();
            VideoPlaybackController.this.currentTime = 0;
        }
    }

    public boolean isAdsDisabled() {
        return this.adsDisabled;
    }

    public void setAdsDisabled(boolean z) {
        this.adsDisabled = z;
    }

    @Deprecated
    public int getLiveWindowTimeMs() {
        return this.liveWindowTimeMs;
    }

    @Deprecated
    public void setLiveWindowTimeMs(int i) {
        if (i >= 0) {
            this.liveWindowTimeMs = i;
        }
    }

    public VideoPlaybackController(EventEmitter eventEmitter) {
        super(eventEmitter, VideoPlaybackController.class);
        initialize();
    }

    public void initialize() {
        Log.i(TAG, "Initializing VideoPlaybackController");
        this.hasSeenPlayerReadyEvent = false;
        this.pendingSourcesToLoad = new ArrayList();
        this.sourceToVideoMapping = new HashMap();
        this.cuePoints = new ArrayList();
        initializeListeners();
    }

    public boolean hasPendingSourcesToLoad() {
        return this.pendingSourcesToLoad.size() > 0;
    }

    /* access modifiers changed from: private */
    public void emitCuePoints(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (CuePoint cuePoint : this.cuePoints) {
            if (cuePoint.getPositionType() != null && cuePoint.getPositionType().equals(PositionType.POINT_IN_TIME)) {
                int position = cuePoint.getPosition();
                if (i <= position && position <= i2) {
                    arrayList.add(cuePoint);
                }
            }
        }
        if (!this.seekWithoutAds && !arrayList.isEmpty()) {
            Collections.sort(arrayList);
            HashMap hashMap = new HashMap();
            hashMap.put("startTime", Integer.valueOf(i));
            hashMap.put(AbstractEvent.END_TIME, Integer.valueOf(i2));
            hashMap.put(AbstractEvent.CUE_POINTS, arrayList);
            this.eventEmitter.emit(EventType.CUE_POINT, hashMap);
        }
    }

    /* access modifiers changed from: protected */
    public void initializeListeners() {
        this.onSetVideoListener = new OnSetVideoListener();
        this.onPlayerReadyListener = new OnPlayerReadyListener();
        this.onDidSetSourceListener = new OnDidSetSourceListener();
        this.onPlayListener = new OnPlayListener();
        this.onProgressUpdateListener = new OnProgressUpdateListener();
        this.onVideoCompletedListener = new OnVideoCompletedListener();
        this.onRemoveCuePointListener = new OnRemoveCuePointListener();
        this.onSetCuePointListener = new OnSetCuePointListener();
        this.onSetCuePointsListener = new OnSetCuePointsListener();
        this.onSeekToListener = new OnSeekToListener();
        this.onDidSeekToListener = new OnDidSeekToListener();
        this.onWillChangeVideoListener = new OnWillChangeVideoListener();
        addListener(EventType.READY_TO_PLAY, this.onPlayerReadyListener);
        addListener(EventType.SET_VIDEO, this.onSetVideoListener);
        addListener(EventType.DID_SET_SOURCE, this.onDidSetSourceListener);
        addListener(EventType.PLAY, this.onPlayListener);
        addListener("progress", this.onProgressUpdateListener);
        addListener("completed", this.onVideoCompletedListener);
        addListener(EventType.REMOVE_CUE_POINT, this.onRemoveCuePointListener);
        addListener(EventType.SET_CUE_POINT, this.onSetCuePointListener);
        addListener(EventType.SET_CUE_POINTS, this.onSetCuePointsListener);
        addListener(EventType.SEEK_TO, this.onSeekToListener);
        addListener(EventType.DID_SEEK_TO, this.onDidSeekToListener);
        addListener(EventType.WILL_CHANGE_VIDEO, this.onWillChangeVideoListener);
        addListener(EventType.DID_PLAY, new EventListener() {
            public void processEvent(Event event) {
                VideoPlaybackController.this.videoStarted = true;
            }
        });
    }
}
