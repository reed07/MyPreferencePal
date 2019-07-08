package com.brightcove.player.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Map;

public abstract class AbstractEvent<PropertyValueType> {
    public static final String ACTIVITY = "activity";
    public static final String AD_ID = "adId";
    public static final String AD_TITLE = "adTitle";
    public static final String ALIGNMENT = "alignment";
    public static final String ANDROID_VIEW = "androidView";
    public static final String AUDIO_TRACKS_STATE = "audioTracksState";
    public static final String AUDIO_VARIANT = "audioVariant";
    public static final String BOOLEAN = "boolean";
    public static final String BRIGHTCOVE_CONTROL_BAR = "brightcoveControlBar";
    public static final String BRIGHTCOVE_MEDIA_CONTROLLER = "brightcoveMediaController";
    public static final String BUILD_VERSION = "buildVersion";
    public static final String CAPTIONS_STATE = "captionsState";
    public static final String CAPTION_FORMAT = "captionFormat";
    public static final String CAPTION_URI = "captionUri";
    public static final String CATALOG_URL = "catalogUrl";
    public static final String CONFIGURATION = "configuration";
    public static final String CUE_POINT = "cue_point";
    public static final String CUE_POINTS = "cue_points";
    public static final String CURRENT_VIDEO = "currentVideo";
    public static final String DEBUG = "debug";
    public static final String EMITTER = "emitter";
    public static final String END_TIME = "endTime";
    public static final String ERROR = "error";
    public static final String ERRORS = "errors";
    public static final String ERROR_CODE = "errorCode";
    public static final String ERROR_EXTRA = "errorExtra";
    public static final String ERROR_MESSAGE = "errorMessage";
    public static final String EVENT_SOURCE = "eventSource";
    public static final String FORWARD_BUFFER_SECONDS = "forwardBufferSeconds";
    public static final String FRAGMENT = "fragment";
    public static final String FROM_SEEK_POSITION = "fromSeekPosition";
    public static final String ID = "id";
    public static final String INDEX = "index";
    public static final String INSTANCE_STATE = "instanceState";
    public static final String LANGUAGES = "languages";
    public static final String LEFT_VOLUME = "leftVolume";
    public static final String LINE = "line";
    public static final String LINE_ANCHOR = "lineAnchor";
    public static final String LINE_TYPE = "lineType";
    public static final String LIST = "list";
    public static final String LOCALE_CODE = "localeCode";
    public static final String MAX_POSITION = "maxPosition";
    public static final String MEASURED_BPS = "measuredBps";
    public static final String MEDIA_BYTES_TRANSFERRED = "mediaBytesTransferred";
    public static final String MEDIA_CONTROLLER_CONFIG = "mediaControllerConfig";
    public static final String MIN_POSITION = "minPosition";
    public static final String NEXT_VIDEO = "nextVideo";
    public static final String ORIGINAL_EVENT = "original";
    public static final String ORIGINAL_PLAYHEAD_POSITION = "originalPlayheadPosition";
    public static final String ORIGINAL_SEEK_POSITION = "originalSeekPosition";
    public static final String PERCENT_COMPLETE = "percentComplete";
    public static final String PICTURE_IN_PICTURE_STATE = "pictureInPictureState";
    public static final String PLAYBACK_LOCATION = "playbackLocation";
    public static final String PLAYHEAD_POSITION = "playheadPosition";
    public static final String PLAYLIST = "playlist";
    public static final String PLUGIN_NAME = "pluginName";
    public static final String POSITION = "position";
    public static final String POSITION_ANCHOR = "positionAnchor";
    public static final String PROJECTION_FORMAT = "projectionFormat";
    public static final String RENDITION_HEIGHT = "renditionHeight";
    public static final String RENDITION_INDICATED_BPS = "renditionIndicatedBps";
    public static final String RENDITION_MIME_TYPE = "renditionMimeType";
    public static final String RENDITION_URL = "renditionUrl";
    public static final String RENDITION_WIDTH = "renditionWidth";
    public static final String REQUESTED_ORIENTATION = "requestedOrientation";
    public static final String REQUEST_TOKEN = "requestToken";
    public static final String RESPONSE_TIME_MS = "responseTimeMs";
    public static final String RIGHT_VOLUME = "rightVolume";
    public static final String SEEK_CONTROLS_VISIBILITY = "seekControlsVisibility";
    public static final String SEEK_DEFAULT = "seekDefault";
    public static final String SEEK_ON_HOLD_UPDATE_FREQ = "seekOnHoldUpdateFreq";
    public static final String SEEK_ON_HOLD_WAIT_TIME = "seekOnHoldWaitTime";
    public static final String SEEK_PERCENTAGE = "seekPercentage";
    public static final String SEEK_POSITION = "seekPosition";
    public static final String SEEK_PROGRESS = "seekProgress";
    public static final String SEEK_RELATIVE_ENABLED = "seekRelativeEabled";
    public static final String SEGMENT_DURATION = "segmentDuration";
    public static final String SELECTED_TRACK = "track";
    public static final String SIZE = "size";
    public static final String SKIP_CUE_POINTS = "skipCuePoints";
    public static final String SOURCE = "source";
    public static final String START_TIME = "startTime";
    public static final String SUPPORT_FRAGMENT = "supportFragment";
    private static final String TAG = "AbstractEvent";
    public static final String TEXT = "text";
    public static final String TRACKS = "tracks";
    public static final String TTML_DOCUMENT = "ttmlDocument";
    public static final String UUID = "uuid";
    public static final String VALUE = "value";
    public static final String VIDEO = "video";
    public static final String VIDEO_DURATION = "duration";
    public static final String VIDEO_HEIGHT = "height";
    public static final String VIDEO_STILL = "video_still";
    public static final String VIDEO_WIDTH = "width";
    public static final String VOLUME = "volume";
    public static final String WEBVTT_DOCUMENT = "webvttDocument";
    private static int counter;
    protected boolean isPrevented = false;
    protected boolean isStopped = false;
    protected final String type;

    @NonNull
    public abstract Map<String, PropertyValueType> getProperties();

    public AbstractEvent(@NonNull String str) {
        this.type = str;
    }

    public static int getNextId() {
        int i = counter;
        counter = i + 1;
        return i;
    }

    public int getId() {
        return getIntegerProperty("id");
    }

    public String getType() {
        return this.type;
    }

    public void preventDefault() {
        this.isPrevented = true;
    }

    public void stopPropagation() {
        this.isStopped = true;
    }

    public boolean isPrevented() {
        return this.isPrevented;
    }

    public boolean isStopped() {
        return this.isStopped;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{");
        sb.append(this.type);
        sb.append(getProperties());
        sb.append("}");
        return sb.toString();
    }

    @Nullable
    public PropertyValueType getProperty(@NonNull String str) {
        return getProperties().get(str);
    }

    public <T> T getProperty(String str, Class<T> cls) {
        if (cls == null) {
            return null;
        }
        try {
            return cls.cast(getProperties().get(str));
        } catch (ClassCastException unused) {
            return null;
        }
    }

    public int getIntegerProperty(@NonNull String str) {
        Object property = getProperty(str);
        if (property != null) {
            try {
                return Integer.parseInt(property.toString());
            } catch (NumberFormatException unused) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error converting value of <");
                sb.append(property);
                sb.append("> for key '");
                sb.append(str);
                sb.append("'");
                Log.e(str2, sb.toString());
            }
        }
        return -1;
    }
}
