package net.minidev.json.reader;

import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.text.Typography;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONAwareEx;
import net.minidev.json.JSONStreamAware;
import net.minidev.json.JSONStreamAwareEx;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONUtil;
import net.minidev.json.JSONValue;

public class JsonWriter {
    public static final JsonWriterI<Enum<?>> EnumWriter = new JsonWriterI<Enum<?>>() {
        public <E extends Enum<?>> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            jSONStyle.writeString(appendable, e.name());
        }
    };
    public static final JsonWriterI<Iterable<? extends Object>> JSONIterableWriter = new JsonWriterI<Iterable<? extends Object>>() {
        public <E extends Iterable<? extends Object>> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            jSONStyle.arrayStart(appendable);
            boolean z = true;
            for (Object next : e) {
                if (z) {
                    z = false;
                    jSONStyle.arrayfirstObject(appendable);
                } else {
                    jSONStyle.arrayNextElm(appendable);
                }
                if (next == null) {
                    appendable.append("null");
                } else {
                    JSONValue.writeJSONString(next, appendable, jSONStyle);
                }
                jSONStyle.arrayObjectEnd(appendable);
            }
            jSONStyle.arrayStop(appendable);
        }
    };
    public static final JsonWriterI<JSONAwareEx> JSONJSONAwareExWriter = new JsonWriterI<JSONAwareEx>() {
        public <E extends JSONAwareEx> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            appendable.append(e.toJSONString(jSONStyle));
        }
    };
    public static final JsonWriterI<JSONAware> JSONJSONAwareWriter = new JsonWriterI<JSONAware>() {
        public <E extends JSONAware> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            appendable.append(e.toJSONString());
        }
    };
    public static final JsonWriterI<Map<String, ? extends Object>> JSONMapWriter = new JsonWriterI<Map<String, ? extends Object>>() {
        public <E extends Map<String, ? extends Object>> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            jSONStyle.objectStart(appendable);
            boolean z = true;
            for (Entry entry : e.entrySet()) {
                Object value = entry.getValue();
                if (value != null || !jSONStyle.ignoreNull()) {
                    if (z) {
                        jSONStyle.objectFirstStart(appendable);
                        z = false;
                    } else {
                        jSONStyle.objectNext(appendable);
                    }
                    JsonWriter.writeJSONKV(entry.getKey().toString(), value, appendable, jSONStyle);
                }
            }
            jSONStyle.objectStop(appendable);
        }
    };
    public static final JsonWriterI<JSONStreamAwareEx> JSONStreamAwareExWriter = new JsonWriterI<JSONStreamAwareEx>() {
        public <E extends JSONStreamAwareEx> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            e.writeJSONString(appendable, jSONStyle);
        }
    };
    public static final JsonWriterI<JSONStreamAwareEx> JSONStreamAwareWriter = new JsonWriterI<JSONStreamAwareEx>() {
        public <E extends JSONStreamAwareEx> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            e.writeJSONString(appendable);
        }
    };
    public static final JsonWriterI<Object> arrayWriter = new JsonWriterI<Object>() {
        public <E> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            Object[] objArr;
            jSONStyle.arrayStart(appendable);
            boolean z = false;
            for (Object obj : (Object[]) e) {
                if (z) {
                    jSONStyle.objectNext(appendable);
                } else {
                    z = true;
                }
                JSONValue.writeJSONString(obj, appendable, jSONStyle);
            }
            jSONStyle.arrayStop(appendable);
        }
    };
    public static final JsonWriterI<Object> beansWriter = new JsonWriterI<Object>() {
        public <E> void writeJSONString(E e, Appendable appendable, JSONStyle jSONStyle) throws IOException {
            Field[] declaredFields;
            Object obj;
            try {
                Class<Object> cls = e.getClass();
                jSONStyle.objectStart(appendable);
                boolean z = false;
                while (cls != Object.class) {
                    boolean z2 = z;
                    for (Field field : cls.getDeclaredFields()) {
                        int modifiers = field.getModifiers();
                        if ((modifiers & PacketTypes.EmailUniquenessCheckResponse) <= 0) {
                            if ((modifiers & 1) > 0) {
                                obj = field.get(e);
                            } else {
                                Method method = null;
                                try {
                                    method = cls.getDeclaredMethod(JSONUtil.getGetterName(field.getName()), new Class[0]);
                                } catch (Exception unused) {
                                }
                                if (method == null) {
                                    Class<Boolean> type = field.getType();
                                    if (type == Boolean.TYPE || type == Boolean.class) {
                                        method = cls.getDeclaredMethod(JSONUtil.getIsName(field.getName()), new Class[0]);
                                    }
                                }
                                if (method != null) {
                                    obj = method.invoke(e, new Object[0]);
                                }
                            }
                            if (obj != null || !jSONStyle.ignoreNull()) {
                                if (z2) {
                                    jSONStyle.objectNext(appendable);
                                } else {
                                    z2 = true;
                                }
                                JsonWriter.writeJSONKV(field.getName(), obj, appendable, jSONStyle);
                            }
                        }
                    }
                    cls = cls.getSuperclass();
                    z = z2;
                }
                jSONStyle.objectStop(appendable);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
    };
    private HashMap<Class<?>, JsonWriterI<?>> data = new HashMap<>();
    private LinkedList<WriterByInterface> writerInterfaces = new LinkedList<>();

    static class WriterByInterface {
        public Class<?> _interface;
        public JsonWriterI<?> _writer;

        public WriterByInterface(Class<?> cls, JsonWriterI<?> jsonWriterI) {
            this._interface = cls;
            this._writer = jsonWriterI;
        }
    }

    public JsonWriter() {
        init();
    }

    public JsonWriterI getWriterByInterface(Class<?> cls) {
        Iterator it = this.writerInterfaces.iterator();
        while (it.hasNext()) {
            WriterByInterface writerByInterface = (WriterByInterface) it.next();
            if (writerByInterface._interface.isAssignableFrom(cls)) {
                return writerByInterface._writer;
            }
        }
        return null;
    }

    public JsonWriterI getWrite(Class cls) {
        return (JsonWriterI) this.data.get(cls);
    }

    public void init() {
        registerWriter(new JsonWriterI<String>() {
            public void writeJSONString(String str, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                jSONStyle.writeString(appendable, str);
            }
        }, String.class);
        registerWriter(new JsonWriterI<Boolean>() {
            public void writeJSONString(Boolean bool, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                appendable.append(bool.toString());
            }
        }, Boolean.class);
        registerWriter(new JsonWriterI<Double>() {
            public void writeJSONString(Double d, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                if (d.isInfinite()) {
                    appendable.append("null");
                } else {
                    appendable.append(d.toString());
                }
            }
        }, Double.class);
        registerWriter(new JsonWriterI<Date>() {
            public void writeJSONString(Date date, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                appendable.append(Typography.quote);
                JSONValue.escape(date.toString(), appendable, jSONStyle);
                appendable.append(Typography.quote);
            }
        }, Date.class);
        registerWriter(new JsonWriterI<Float>() {
            public void writeJSONString(Float f, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                if (f.isInfinite()) {
                    appendable.append("null");
                } else {
                    appendable.append(f.toString());
                }
            }
        }, Float.class);
        registerWriter(new JsonWriterI<Number>() {
            public void writeJSONString(Number number, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                appendable.append(number.toString());
            }
        }, Integer.class, Long.class, Byte.class, Short.class, BigInteger.class);
        registerWriter(new JsonWriterI<Boolean>() {
            public void writeJSONString(Boolean bool, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                appendable.append(bool.toString());
            }
        }, Boolean.class);
        registerWriter(new JsonWriterI<Boolean>() {
            public void writeJSONString(Boolean bool, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                appendable.append(bool.toString());
            }
        }, Boolean.class);
        registerWriter(new JsonWriterI<int[]>() {
            public void writeJSONString(int[] iArr, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (int i : iArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Integer.toString(i));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, int[].class);
        registerWriter(new JsonWriterI<short[]>() {
            public void writeJSONString(short[] sArr, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (short s : sArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Short.toString(s));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, short[].class);
        registerWriter(new JsonWriterI<long[]>() {
            public void writeJSONString(long[] jArr, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (long j : jArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Long.toString(j));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, long[].class);
        registerWriter(new JsonWriterI<float[]>() {
            public void writeJSONString(float[] fArr, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (float f : fArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Float.toString(f));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, float[].class);
        registerWriter(new JsonWriterI<double[]>() {
            public void writeJSONString(double[] dArr, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (double d : dArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Double.toString(d));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, double[].class);
        registerWriter(new JsonWriterI<boolean[]>() {
            public void writeJSONString(boolean[] zArr, Appendable appendable, JSONStyle jSONStyle) throws IOException {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (boolean z2 : zArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Boolean.toString(z2));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, boolean[].class);
        addInterfaceWriterLast(JSONStreamAwareEx.class, JSONStreamAwareExWriter);
        addInterfaceWriterLast(JSONStreamAware.class, JSONStreamAwareWriter);
        addInterfaceWriterLast(JSONAwareEx.class, JSONJSONAwareExWriter);
        addInterfaceWriterLast(JSONAware.class, JSONJSONAwareWriter);
        addInterfaceWriterLast(Map.class, JSONMapWriter);
        addInterfaceWriterLast(Iterable.class, JSONIterableWriter);
        addInterfaceWriterLast(Enum.class, EnumWriter);
    }

    public void addInterfaceWriterLast(Class<?> cls, JsonWriterI<?> jsonWriterI) {
        this.writerInterfaces.addLast(new WriterByInterface(cls, jsonWriterI));
    }

    public <T> void registerWriter(JsonWriterI<T> jsonWriterI, Class<?>... clsArr) {
        for (Class<?> put : clsArr) {
            this.data.put(put, jsonWriterI);
        }
    }

    public static void writeJSONKV(String str, Object obj, Appendable appendable, JSONStyle jSONStyle) throws IOException {
        if (str == null) {
            appendable.append("null");
        } else if (!jSONStyle.mustProtectKey(str)) {
            appendable.append(str);
        } else {
            appendable.append(Typography.quote);
            JSONValue.escape(str, appendable, jSONStyle);
            appendable.append(Typography.quote);
        }
        jSONStyle.objectEndOfKey(appendable);
        if (obj instanceof String) {
            jSONStyle.writeString(appendable, (String) obj);
        } else {
            JSONValue.writeJSONString(obj, appendable, jSONStyle);
        }
        jSONStyle.objectElmStop(appendable);
    }
}
