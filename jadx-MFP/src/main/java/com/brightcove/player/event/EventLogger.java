package com.brightcove.player.event;

import android.util.Log;
import java.util.ArrayList;
import java.util.Map.Entry;

public class EventLogger {
    private static final String DEFAULT_TAG = "EventLogger";
    private int currentListenerToken;
    private EventEmitter emitter;
    /* access modifiers changed from: private */
    public ArrayList<String> excludeList;
    private EventListener genericListener;
    /* access modifiers changed from: private */
    public LoggerCallback loggerCallback;
    /* access modifiers changed from: private */
    public String tag;
    /* access modifiers changed from: private */
    public boolean verbose;
    /* access modifiers changed from: private */
    public ArrayList<String> whiteList;

    public interface LoggerCallback {
        void logError(String str, Throwable th);

        void logMessage(String str);
    }

    public EventLogger(EventEmitter eventEmitter, boolean z) {
        this(eventEmitter, z, DEFAULT_TAG);
    }

    public EventLogger(EventEmitter eventEmitter, boolean z, LoggerCallback loggerCallback2) {
        this(eventEmitter, z, DEFAULT_TAG, loggerCallback2);
    }

    public EventLogger(EventEmitter eventEmitter, boolean z, String str) {
        this(eventEmitter, z, str, null);
    }

    public EventLogger(EventEmitter eventEmitter, boolean z, String str, LoggerCallback loggerCallback2) {
        this.currentListenerToken = 0;
        this.excludeList = new ArrayList<>();
        this.whiteList = new ArrayList<>();
        this.emitter = eventEmitter;
        this.verbose = z;
        this.tag = str;
        this.loggerCallback = loggerCallback2;
        this.excludeList.add(EventType.BUFFERED_UPDATE);
        this.excludeList.add("progress");
        this.excludeList.add(EventType.AD_PROGRESS);
        start();
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public void start() {
        stop();
        this.genericListener = new EventListener() {
            public void processEvent(Event event) {
                if (!EventLogger.this.whiteList.isEmpty()) {
                    if (EventLogger.this.whiteList.contains(event.getType())) {
                        logEvent(event);
                    }
                } else if (!EventLogger.this.excludeList.contains(event.getType())) {
                    logEvent(event);
                }
            }

            private void logEvent(Event event) {
                StringBuffer stringBuffer = new StringBuffer(event.getType());
                Object obj = event.properties.get(AbstractEvent.EMITTER);
                if (obj != null) {
                    stringBuffer.append(" (");
                    stringBuffer.append(obj);
                    stringBuffer.append(')');
                }
                if (EventLogger.this.verbose) {
                    stringBuffer.append(" { ");
                    for (Entry entry : event.properties.entrySet()) {
                        stringBuffer.append((String) entry.getKey());
                        stringBuffer.append(": ");
                        stringBuffer.append(entry.getValue());
                        stringBuffer.append(' ');
                    }
                    stringBuffer.append('}');
                }
                if (EventLogger.this.loggerCallback != null) {
                    EventLogger.this.loggerCallback.logMessage(stringBuffer.toString());
                } else {
                    Log.d(EventLogger.this.tag, stringBuffer.toString());
                }
                if ("error".equals(event.getType()) && event.properties.containsKey("error")) {
                    String str = "Unhandled error event";
                    Throwable th = (Throwable) event.properties.get("error");
                    if (EventLogger.this.loggerCallback != null) {
                        EventLogger.this.loggerCallback.logError(str, th);
                    } else {
                        Log.e(EventLogger.DEFAULT_TAG, str, th);
                    }
                }
            }
        };
        this.currentListenerToken = this.emitter.on(EventType.ANY, this.genericListener);
    }

    public void stop() {
        int i = this.currentListenerToken;
        if (i > 0) {
            this.emitter.off(EventType.ANY, i);
            this.currentListenerToken = 0;
        }
    }

    public void addExclude(String str) {
        if (!this.excludeList.contains(str)) {
            this.excludeList.add(str);
        }
    }

    public void removeExclude(String str) {
        this.excludeList.remove(str);
    }

    public void clearExcludes() {
        this.excludeList.clear();
    }

    public void addWhitelist(String str) {
        if (!this.whiteList.contains(str)) {
            this.whiteList.add(str);
        }
    }

    public void removeFromWhitelist(String str) {
        this.whiteList.remove(str);
    }

    public void clearWhitelist() {
        this.whiteList.clear();
    }
}
