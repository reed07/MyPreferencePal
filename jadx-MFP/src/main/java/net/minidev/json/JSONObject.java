package net.minidev.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.minidev.json.reader.JsonWriter;

public class JSONObject extends HashMap<String, Object> implements JSONAware, JSONAwareEx, JSONStreamAwareEx {
    private static final long serialVersionUID = -503443796854799292L;

    public JSONObject() {
    }

    public static String escape(String str) {
        return JSONValue.escape(str);
    }

    public static String toJSONString(Map<String, ? extends Object> map, JSONStyle jSONStyle) {
        StringBuilder sb = new StringBuilder();
        try {
            writeJSON(map, sb, jSONStyle);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public JSONObject(Map<String, ?> map) {
        super(map);
    }

    public static void writeJSON(Map<String, ? extends Object> map, Appendable appendable, JSONStyle jSONStyle) throws IOException {
        if (map == null) {
            appendable.append("null");
        } else {
            JsonWriter.JSONMapWriter.writeJSONString(map, appendable, jSONStyle);
        }
    }

    public void writeJSONString(Appendable appendable) throws IOException {
        writeJSON(this, appendable, JSONValue.COMPRESSION);
    }

    public void writeJSONString(Appendable appendable, JSONStyle jSONStyle) throws IOException {
        writeJSON(this, appendable, jSONStyle);
    }

    public String toJSONString() {
        return toJSONString(this, JSONValue.COMPRESSION);
    }

    public String toJSONString(JSONStyle jSONStyle) {
        return toJSONString(this, jSONStyle);
    }

    public String toString() {
        return toJSONString(this, JSONValue.COMPRESSION);
    }
}
