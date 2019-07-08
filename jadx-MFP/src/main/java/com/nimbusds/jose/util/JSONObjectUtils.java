package com.nimbusds.jose.util;

import com.myfitnesspal.feature.video.task.AmazonAdTask;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class JSONObjectUtils {
    public static JSONObject parseJSONObject(String str) throws ParseException {
        try {
            Object parse = new JSONParser(AmazonAdTask.DEFAULT_AD_WIDTH).parse(str);
            if (parse instanceof JSONObject) {
                return (JSONObject) parse;
            }
            throw new ParseException("JSON entity is not an object", 0);
        } catch (net.minidev.json.parser.ParseException e) {
            StringBuilder sb = new StringBuilder("Invalid JSON: ");
            sb.append(e.getMessage());
            throw new ParseException(sb.toString(), 0);
        }
    }

    private static <T> T getGeneric(JSONObject jSONObject, String str, Class<T> cls) throws ParseException {
        if (!jSONObject.containsKey(str)) {
            StringBuilder sb = new StringBuilder("Missing JSON object member with key \"");
            sb.append(str);
            sb.append("\"");
            throw new ParseException(sb.toString(), 0);
        } else if (jSONObject.get(str) != null) {
            T t = jSONObject.get(str);
            if (cls.isAssignableFrom(t.getClass())) {
                return t;
            }
            StringBuilder sb2 = new StringBuilder("Unexpected type of JSON object member with key \"");
            sb2.append(str);
            sb2.append("\"");
            throw new ParseException(sb2.toString(), 0);
        } else {
            StringBuilder sb3 = new StringBuilder("JSON object member with key \"");
            sb3.append(str);
            sb3.append("\" has null value");
            throw new ParseException(sb3.toString(), 0);
        }
    }

    public static String getString(JSONObject jSONObject, String str) throws ParseException {
        return (String) getGeneric(jSONObject, str, String.class);
    }

    public static URI getURI(JSONObject jSONObject, String str) throws ParseException {
        try {
            return new URI((String) getGeneric(jSONObject, str, String.class));
        } catch (URISyntaxException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    public static JSONArray getJSONArray(JSONObject jSONObject, String str) throws ParseException {
        return (JSONArray) getGeneric(jSONObject, str, JSONArray.class);
    }

    public static String[] getStringArray(JSONObject jSONObject, String str) throws ParseException {
        try {
            return (String[]) getJSONArray(jSONObject, str).toArray(new String[0]);
        } catch (ArrayStoreException unused) {
            StringBuilder sb = new StringBuilder("JSON object member with key \"");
            sb.append(str);
            sb.append("\" is not an array of strings");
            throw new ParseException(sb.toString(), 0);
        }
    }

    public static List<String> getStringList(JSONObject jSONObject, String str) throws ParseException {
        return Arrays.asList(getStringArray(jSONObject, str));
    }

    public static JSONObject getJSONObject(JSONObject jSONObject, String str) throws ParseException {
        return (JSONObject) getGeneric(jSONObject, str, JSONObject.class);
    }

    private JSONObjectUtils() {
    }
}
