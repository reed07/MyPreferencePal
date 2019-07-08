package com.brightcove.player.util;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Pair;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventType;
import com.brightcove.player.model.Video;
import com.brightcove.player.model.Video.Fields;
import com.brightcove.player.view.BaseVideoView;
import com.brightcove.player.view.BrightcoveClosedCaptioningView;
import com.brightcove.player.view.BrightcoveClosedCaptioningView.ClosedCaptioningMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public final class VideoUtil {
    public static boolean addCaptions(@NonNull Video video, @NonNull Map<String, String> map) {
        if (video.getProperties().containsKey(Fields.CAPTION_SOURCES)) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        video.getProperties().put(Fields.CAPTION_SOURCES, arrayList);
        for (Entry entry : map.entrySet()) {
            if (entry != null && !entry.toString().isEmpty()) {
                arrayList.add(Pair.create(Uri.parse((String) entry.getValue()), BrightcoveCaptionFormat.createCaptionFormat("text/unknown", (String) entry.getKey())));
            }
        }
        return true;
    }

    public static boolean toggleClosedCaptions(@NonNull BaseVideoView baseVideoView) {
        boolean isClosedCaptionsEnabled = isClosedCaptionsEnabled(baseVideoView);
        EventEmitter eventEmitter = baseVideoView.getEventEmitter();
        if (eventEmitter != null) {
            eventEmitter.emit(EventType.TOGGLE_CLOSED_CAPTIONS, Collections.singletonMap(AbstractEvent.BOOLEAN, Boolean.valueOf(!isClosedCaptionsEnabled)));
        }
        return isClosedCaptionsEnabled;
    }

    public static boolean isClosedCaptionsEnabled(@NonNull BaseVideoView baseVideoView) {
        BrightcoveClosedCaptioningView closedCaptioningView = baseVideoView.getClosedCaptioningView();
        if (closedCaptioningView == null || closedCaptioningView.getMode() != ClosedCaptioningMode.ON) {
            return false;
        }
        return true;
    }
}
