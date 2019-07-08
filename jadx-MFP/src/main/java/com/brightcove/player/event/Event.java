package com.brightcove.player.event;

import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Event extends AbstractEvent<Object> {
    public final Map<String, Object> properties = new HashMap();

    public Event(String str) {
        super(str);
    }

    @NonNull
    public Map<String, Object> getProperties() {
        return Collections.unmodifiableMap(this.properties);
    }
}
