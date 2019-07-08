package com.inmobi.ads;

import android.support.annotation.Nullable;
import com.brightcove.player.event.EventType;
import com.inmobi.commons.core.a.a;
import com.inmobi.commons.core.utilities.d;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class NativeTracker {
    private static final String e = "NativeTracker";
    String a;
    TrackerEventType b;
    Map<String, String> c;
    Map<String, Object> d;
    private String f;
    private int g;

    enum TrackerEventType {
        TRACKER_EVENT_TYPE_UNKNOWN,
        TRACKER_EVENT_TYPE_LOAD,
        TRACKER_EVENT_TYPE_CLIENT_FILL,
        TRACKER_EVENT_TYPE_RENDER,
        TRACKER_EVENT_TYPE_PAGE_VIEW,
        TRACKER_EVENT_TYPE_CLICK,
        TRACKER_EVENT_TYPE_VIDEO_RENDER,
        TRACKER_EVENT_TYPE_FALLBACK_URL,
        TRACKER_EVENT_TYPE_PLAY,
        TRACKER_EVENT_TYPE_Q1,
        TRACKER_EVENT_TYPE_Q2,
        TRACKER_EVENT_TYPE_Q3,
        TRACKER_EVENT_TYPE_Q4,
        TRACKER_EVENT_TYPE_CREATIVE_VIEW,
        TRACKER_EVENT_TYPE_FULLSCREEN,
        TRACKER_EVENT_TYPE_EXIT_FULLSCREEN,
        TRACKER_EVENT_TYPE_MUTE,
        TRACKER_EVENT_TYPE_UNMUTE,
        TRACKER_EVENT_TYPE_PAUSE,
        TRACKER_EVENT_TYPE_RESUME,
        TRACKER_EVENT_TYPE_ERROR,
        TRACKER_EVENT_TYPE_END_CARD_CLOSE,
        TRACKER_EVENT_TYPE_CUSTOM_EVENT_VIDEO,
        TRACKER_EVENT_TYPE_IAS,
        TRACKER_EVENT_TYPE_MOAT,
        TRACKER_EVENT_TYPE_DOWNLOADER_INIT,
        TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADING,
        TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADED,
        TRACKER_EVENT_TYPE_DOWNLOADER_ERROR
    }

    public NativeTracker(String str, int i, TrackerEventType trackerEventType, Map<String, String> map) {
        this("url_ping", str, i, trackerEventType, map);
    }

    private NativeTracker(String str, String str2, int i, TrackerEventType trackerEventType, Map<String, String> map) {
        this.f = str;
        this.a = str2.trim();
        this.g = i;
        this.b = trackerEventType;
        this.c = map;
    }

    @Nullable
    static NativeTracker a(JSONObject jSONObject) {
        String str;
        try {
            String string = jSONObject.getString("type");
            if (string != null) {
                if (string.length() != 0) {
                    String lowerCase = string.toLowerCase(Locale.US);
                    int hashCode = lowerCase.hashCode();
                    if (hashCode == -1918378017) {
                        str = "html_script";
                    } else if (hashCode == -970292670) {
                        str = "url_ping";
                    } else if (hashCode == -284840886) {
                        str = "unknown";
                    } else if (hashCode == 2015859192) {
                        lowerCase.equals("webview_ping");
                    }
                    boolean equals = lowerCase.equals(str);
                }
            }
            return new NativeTracker(jSONObject.getString("url"), jSONObject.optInt("eventId", -1), a(jSONObject.getString("eventType")), new HashMap());
        } catch (JSONException e2) {
            new StringBuilder("Error building tracker from JSONObject; ").append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
            return null;
        }
    }

    static TrackerEventType a(String str) {
        if (str == null || str.length() == 0) {
            return TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;
        }
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1638835128:
                if (str.equals("midpoint")) {
                    c2 = 10;
                    break;
                }
                break;
            case -1337830390:
                if (str.equals("thirdQuartile")) {
                    c2 = 11;
                    break;
                }
                break;
            case -934426579:
                if (str.equals("resume")) {
                    c2 = 19;
                    break;
                }
                break;
            case -840405966:
                if (str.equals("unmute")) {
                    c2 = 17;
                    break;
                }
                break;
            case -667101923:
                if (str.equals("zMoatVASTIDs")) {
                    c2 = 22;
                    break;
                }
                break;
            case -599445191:
                if (str.equals("complete")) {
                    c2 = 12;
                    break;
                }
                break;
            case -284840886:
                if (str.equals("unknown")) {
                    c2 = 1;
                    break;
                }
                break;
            case -174104201:
                if (str.equals("client_fill")) {
                    c2 = 3;
                    break;
                }
                break;
            case -45894975:
                if (str.equals("IAS_VIEWABILITY")) {
                    c2 = 21;
                    break;
                }
                break;
            case 3327206:
                if (str.equals("load")) {
                    c2 = 2;
                    break;
                }
                break;
            case 3363353:
                if (str.equals("mute")) {
                    c2 = 16;
                    break;
                }
                break;
            case 94750088:
                if (str.equals("click")) {
                    c2 = 7;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    c2 = 20;
                    break;
                }
                break;
            case 106440182:
                if (str.equals(EventType.PAUSE)) {
                    c2 = 18;
                    break;
                }
                break;
            case 109757538:
                if (str.equals(TtmlNode.START)) {
                    c2 = 8;
                    break;
                }
                break;
            case 110066619:
                if (str.equals("fullscreen")) {
                    c2 = 14;
                    break;
                }
                break;
            case 113951609:
                if (str.equals("exitFullscreen")) {
                    c2 = 15;
                    break;
                }
                break;
            case 354294980:
                if (str.equals("VideoImpression")) {
                    c2 = 5;
                    break;
                }
                break;
            case 560220243:
                if (str.equals("firstQuartile")) {
                    c2 = 9;
                    break;
                }
                break;
            case 883937877:
                if (str.equals("page_view")) {
                    c2 = 6;
                    break;
                }
                break;
            case 1342121331:
                if (str.equals("closeEndCard")) {
                    c2 = 23;
                    break;
                }
                break;
            case 1778167540:
                if (str.equals("creativeView")) {
                    c2 = 13;
                    break;
                }
                break;
            case 2114088489:
                if (str.equals("Impression")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 2:
                return TrackerEventType.TRACKER_EVENT_TYPE_LOAD;
            case 3:
                return TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL;
            case 4:
                return TrackerEventType.TRACKER_EVENT_TYPE_RENDER;
            case 5:
                return TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER;
            case 6:
                return TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW;
            case 7:
                return TrackerEventType.TRACKER_EVENT_TYPE_CLICK;
            case 8:
                return TrackerEventType.TRACKER_EVENT_TYPE_PLAY;
            case 9:
                return TrackerEventType.TRACKER_EVENT_TYPE_Q1;
            case 10:
                return TrackerEventType.TRACKER_EVENT_TYPE_Q2;
            case 11:
                return TrackerEventType.TRACKER_EVENT_TYPE_Q3;
            case 12:
                return TrackerEventType.TRACKER_EVENT_TYPE_Q4;
            case 13:
                return TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
            case 14:
                return TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN;
            case 15:
                return TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN;
            case 16:
                return TrackerEventType.TRACKER_EVENT_TYPE_MUTE;
            case 17:
                return TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE;
            case 18:
                return TrackerEventType.TRACKER_EVENT_TYPE_PAUSE;
            case 19:
                return TrackerEventType.TRACKER_EVENT_TYPE_RESUME;
            case 20:
                return TrackerEventType.TRACKER_EVENT_TYPE_ERROR;
            case 21:
                return TrackerEventType.TRACKER_EVENT_TYPE_IAS;
            case 22:
                return TrackerEventType.TRACKER_EVENT_TYPE_MOAT;
            case 23:
                return TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE;
            default:
                return TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;
        }
    }

    public final String toString() {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.f);
            jSONObject.put("url", this.a);
            String str2 = "eventType";
            switch (this.b) {
                case TRACKER_EVENT_TYPE_LOAD:
                    str = "load";
                    break;
                case TRACKER_EVENT_TYPE_CLIENT_FILL:
                    str = "client_fill";
                    break;
                case TRACKER_EVENT_TYPE_RENDER:
                    str = "Impression";
                    break;
                case TRACKER_EVENT_TYPE_VIDEO_RENDER:
                    str = "VideoImpression";
                    break;
                case TRACKER_EVENT_TYPE_PAGE_VIEW:
                    str = "page_view";
                    break;
                case TRACKER_EVENT_TYPE_CLICK:
                    str = "click";
                    break;
                case TRACKER_EVENT_TYPE_PLAY:
                    str = TtmlNode.START;
                    break;
                case TRACKER_EVENT_TYPE_Q1:
                    str = "firstQuartile";
                    break;
                case TRACKER_EVENT_TYPE_Q2:
                    str = "midpoint";
                    break;
                case TRACKER_EVENT_TYPE_Q3:
                    str = "thirdQuartile";
                    break;
                case TRACKER_EVENT_TYPE_Q4:
                    str = "complete";
                    break;
                case TRACKER_EVENT_TYPE_CREATIVE_VIEW:
                    str = "creativeView";
                    break;
                case TRACKER_EVENT_TYPE_FULLSCREEN:
                    str = "fullscreen";
                    break;
                case TRACKER_EVENT_TYPE_EXIT_FULLSCREEN:
                    str = "exitFullscreen";
                    break;
                case TRACKER_EVENT_TYPE_MUTE:
                    str = "mute";
                    break;
                case TRACKER_EVENT_TYPE_UNMUTE:
                    str = "unmute";
                    break;
                case TRACKER_EVENT_TYPE_PAUSE:
                    str = EventType.PAUSE;
                    break;
                case TRACKER_EVENT_TYPE_RESUME:
                    str = "resume";
                    break;
                case TRACKER_EVENT_TYPE_ERROR:
                    str = "error";
                    break;
                case TRACKER_EVENT_TYPE_IAS:
                    str = "IAS_VIEWABILITY";
                    break;
                case TRACKER_EVENT_TYPE_MOAT:
                    str = "zMoatVASTIDs";
                    break;
                case TRACKER_EVENT_TYPE_END_CARD_CLOSE:
                    str = "closeEndCard";
                    break;
                default:
                    str = "unknown";
                    break;
            }
            jSONObject.put(str2, str);
            jSONObject.put("eventId", this.g);
            jSONObject.put("extras", d.a(this.c == null ? new HashMap<>() : this.c, ","));
            return jSONObject.toString();
        } catch (JSONException e2) {
            StringBuilder sb = new StringBuilder("Error serializing an ");
            sb.append(e);
            sb.append(" instance (");
            sb.append(e2.getMessage());
            a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }
}
