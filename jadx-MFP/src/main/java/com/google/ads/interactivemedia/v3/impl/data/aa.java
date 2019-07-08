package com.google.ads.interactivemedia.v3.impl.data;

import android.util.Log;
import com.brightcove.player.event.AbstractEvent;
import com.google.ads.interactivemedia.v3.internal.ahh;
import com.google.ads.interactivemedia.v3.internal.ahj;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

/* compiled from: IMASDK */
public class aa {
    public double adBreakDuration;
    public String adBreakTime;
    public List<Float> adCuePoints;
    public c adData;
    public int adPosition;
    public long adTimeUpdateMs;
    public double bufferedTime;
    public String clickThroughUrl;
    public Map<String, CompanionData> companions;
    public List<x> cuepoints;
    public double currentTime;
    public double duration;
    public int errorCode;
    public String errorMessage;
    public String eventId;
    public String innerError;
    public SortedSet<Float> internalCuePoints;
    public String ln;
    public a logData;
    public String m;
    public String minutes;
    public boolean monitorAppLifecycle;
    public String n;
    public String queryId;
    public String seconds;
    public double seekTime;
    public String streamId;
    public String streamUrl;
    public List<HashMap<String, String>> subtitles;
    public int totalAds;
    public String translation;
    public String vastEvent;
    public String videoUrl;

    /* compiled from: IMASDK */
    public static class a {
        public int errorCode;
        public String errorMessage;
        public String innerError;
        public String type;

        public final Map<String, String> constructMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("type", this.type);
            hashMap.put("errorCode", String.valueOf(this.errorCode));
            hashMap.put(AbstractEvent.ERROR_MESSAGE, this.errorMessage);
            String str = this.innerError;
            if (str != null) {
                hashMap.put("innerError", str);
            }
            return hashMap;
        }

        public final String toString() {
            return String.format("Log[type=%s, errorCode=%s, errorMessage=%s, innerError=%s]", new Object[]{this.type, Integer.valueOf(this.errorCode), this.errorMessage, this.innerError});
        }
    }

    public boolean equals(Object obj) {
        return ahh.a((Object) this, obj, new String[0]);
    }

    public int hashCode() {
        return ahj.a(this, new String[0]);
    }

    public String toString() {
        Field[] fields;
        StringBuilder sb = new StringBuilder();
        sb.append("JavaScriptMsgData[");
        for (Field field : aa.class.getFields()) {
            try {
                Object obj = field.get(this);
                sb.append(field.getName());
                sb.append(":");
                sb.append(obj);
                sb.append(",");
            } catch (IllegalArgumentException e) {
                Log.e("IMASDK", "IllegalArgumentException occurred", e);
            } catch (IllegalAccessException e2) {
                Log.e("IMASDK", "IllegalAccessException occurred", e2);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
