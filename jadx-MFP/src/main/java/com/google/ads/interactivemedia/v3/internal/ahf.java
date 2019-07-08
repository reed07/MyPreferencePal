package com.google.ads.interactivemedia.v3.internal;

import com.brightcove.player.event.AbstractEvent;
import com.myfitnesspal.shared.constants.Constants.ABTest.SmartWaterPhase1;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: IMASDK */
public final class ahf {
    private static final Map<String, Class<?>> a;
    private static final Map<Class<?>, Class<?>> b;
    private static final Map<Class<?>, Class<?>> c = new HashMap();

    public static boolean a(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        if (cls.isPrimitive() || c.containsKey(cls)) {
            return true;
        }
        return false;
    }

    static {
        HashMap hashMap = new HashMap();
        a = hashMap;
        hashMap.put(AbstractEvent.BOOLEAN, Boolean.TYPE);
        a.put("byte", Byte.TYPE);
        a.put("char", Character.TYPE);
        a.put("short", Short.TYPE);
        a.put("int", Integer.TYPE);
        a.put("long", Long.TYPE);
        a.put("double", Double.TYPE);
        a.put("float", Float.TYPE);
        a.put("void", Void.TYPE);
        HashMap hashMap2 = new HashMap();
        b = hashMap2;
        hashMap2.put(Boolean.TYPE, Boolean.class);
        b.put(Byte.TYPE, Byte.class);
        b.put(Character.TYPE, Character.class);
        b.put(Short.TYPE, Short.class);
        b.put(Integer.TYPE, Integer.class);
        b.put(Long.TYPE, Long.class);
        b.put(Double.TYPE, Double.class);
        b.put(Float.TYPE, Float.class);
        b.put(Void.TYPE, Void.TYPE);
        for (Entry entry : b.entrySet()) {
            Class cls = (Class) entry.getKey();
            Class cls2 = (Class) entry.getValue();
            if (!cls.equals(cls2)) {
                c.put(cls2, cls);
            }
        }
        HashMap hashMap3 = new HashMap();
        hashMap3.put("int", "I");
        hashMap3.put(AbstractEvent.BOOLEAN, "Z");
        hashMap3.put("float", Attributes.FRIDAY);
        hashMap3.put("long", "J");
        hashMap3.put("short", "S");
        hashMap3.put("byte", SmartWaterPhase1.SPONSORED_WATER_ON_VARIANT);
        hashMap3.put("double", "D");
        hashMap3.put("char", "C");
        HashMap hashMap4 = new HashMap();
        for (Entry entry2 : hashMap3.entrySet()) {
            hashMap4.put(entry2.getValue(), entry2.getKey());
        }
        Collections.unmodifiableMap(hashMap3);
        Collections.unmodifiableMap(hashMap4);
    }
}
