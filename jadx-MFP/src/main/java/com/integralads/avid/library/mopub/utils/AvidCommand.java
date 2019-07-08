package com.integralads.avid.library.mopub.utils;

import org.json.JSONObject;

public class AvidCommand {
    public static String setNativeViewState(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("setNativeViewState(");
        sb.append(str);
        sb.append(")");
        return callAvidbridge(sb.toString());
    }

    public static String setAppState(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("setAppState(");
        sb.append(JSONObject.quote(str));
        sb.append(")");
        return callAvidbridge(sb.toString());
    }

    public static String publishReadyEventForDeferredAdSession() {
        return callAvidbridge("publishReadyEventForDeferredAdSession()");
    }

    public static String publishVideoEvent(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("publishVideoEvent(");
        sb.append(JSONObject.quote(str));
        sb.append(")");
        return callAvidbridge(sb.toString());
    }

    public static String publishVideoEvent(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("publishVideoEvent(");
        sb.append(JSONObject.quote(str));
        sb.append(",");
        sb.append(str2);
        sb.append(")");
        return callAvidbridge(sb.toString());
    }

    public static String setAvidAdSessionContext(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("setAvidAdSessionContext(");
        sb.append(str);
        sb.append(")");
        return callAvidbridge(sb.toString());
    }

    public static String callAvidbridge(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript: if(window.avidbridge!==undefined){avidbridge.");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    public static String formatJavaScript(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript: ");
        sb.append(str);
        return sb.toString();
    }
}
