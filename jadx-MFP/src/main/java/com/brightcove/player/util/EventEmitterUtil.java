package com.brightcove.player.util;

import android.util.Log;
import com.brightcove.player.event.EventEmitter;
import java.util.HashMap;

public final class EventEmitterUtil {
    private static final String TAG = "EventEmitterUtil";

    public static void emitError(EventEmitter eventEmitter, String str, Exception exc) {
        Log.e(TAG, str, exc);
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(exc.getMessage());
        hashMap.put("message", sb.toString());
        eventEmitter.emit("error", hashMap);
    }

    public static void emitError(EventEmitter eventEmitter, String str) {
        Log.e(TAG, str);
        HashMap hashMap = new HashMap();
        hashMap.put("message", str);
        eventEmitter.emit("error", hashMap);
    }
}
