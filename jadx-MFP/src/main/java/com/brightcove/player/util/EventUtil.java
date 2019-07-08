package com.brightcove.player.util;

import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.Video;
import java.util.HashMap;

public class EventUtil {
    public static void emit(EventEmitter eventEmitter, String str, Video video) {
        if (video != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("video", video);
            eventEmitter.emit(str, hashMap);
            return;
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.VIDEO_REQUIRED));
    }

    public static void emit(EventEmitter eventEmitter, String str, Video video, Source source) {
        if (video == null) {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.VIDEO_REQUIRED));
        } else if (source != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("video", video);
            hashMap.put("source", source);
            eventEmitter.emit(str, hashMap);
        } else {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.SOURCE_REQUIRED));
        }
    }
}
