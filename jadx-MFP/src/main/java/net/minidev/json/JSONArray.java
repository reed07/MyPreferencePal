package net.minidev.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.reader.JsonWriter;

public class JSONArray extends ArrayList<Object> implements List<Object>, JSONAwareEx, JSONStreamAwareEx {
    private static final long serialVersionUID = 9106884089231309568L;

    public static String toJSONString(List<? extends Object> list, JSONStyle jSONStyle) {
        StringBuilder sb = new StringBuilder();
        try {
            writeJSONString(list, sb, jSONStyle);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public static void writeJSONString(Iterable<? extends Object> iterable, Appendable appendable, JSONStyle jSONStyle) throws IOException {
        if (iterable == null) {
            appendable.append("null");
        } else {
            JsonWriter.JSONIterableWriter.writeJSONString(iterable, appendable, jSONStyle);
        }
    }

    public String toJSONString() {
        return toJSONString(this, JSONValue.COMPRESSION);
    }

    public String toJSONString(JSONStyle jSONStyle) {
        return toJSONString(this, jSONStyle);
    }

    public String toString() {
        return toJSONString();
    }

    public void writeJSONString(Appendable appendable) throws IOException {
        writeJSONString(this, appendable, JSONValue.COMPRESSION);
    }

    public void writeJSONString(Appendable appendable, JSONStyle jSONStyle) throws IOException {
        writeJSONString(this, appendable, jSONStyle);
    }
}
