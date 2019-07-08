package net.minidev.json;

import java.io.IOException;
import net.minidev.json.parser.FakeContainerFactory;
import net.minidev.json.reader.JsonWriter;
import net.minidev.json.reader.JsonWriterI;

public class JSONValue {
    public static JSONStyle COMPRESSION = JSONStyle.NO_COMPRESS;
    private static final FakeContainerFactory FACTORY_FAKE_COINTAINER = new FakeContainerFactory();
    public static JsonWriter defaultWriter = new JsonWriter();

    public static void writeJSONString(Object obj, Appendable appendable, JSONStyle jSONStyle) throws IOException {
        if (obj == null) {
            appendable.append("null");
            return;
        }
        Class cls = obj.getClass();
        JsonWriterI<Object> write = defaultWriter.getWrite(cls);
        if (write == null) {
            if (cls.isArray()) {
                write = JsonWriter.arrayWriter;
            } else {
                write = defaultWriter.getWriterByInterface(obj.getClass());
                if (write == null) {
                    write = JsonWriter.beansWriter;
                }
            }
            defaultWriter.registerWriter(write, cls);
        }
        write.writeJSONString(obj, appendable, jSONStyle);
    }

    public static String toJSONString(Object obj) {
        return toJSONString(obj, COMPRESSION);
    }

    public static String toJSONString(Object obj, JSONStyle jSONStyle) {
        StringBuilder sb = new StringBuilder();
        try {
            writeJSONString(obj, sb, jSONStyle);
        } catch (IOException unused) {
        }
        return sb.toString();
    }

    public static String escape(String str) {
        return escape(str, COMPRESSION);
    }

    public static String escape(String str, JSONStyle jSONStyle) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        jSONStyle.escape(str, sb);
        return sb.toString();
    }

    public static void escape(String str, Appendable appendable, JSONStyle jSONStyle) {
        if (str != null) {
            jSONStyle.escape(str, appendable);
        }
    }
}
