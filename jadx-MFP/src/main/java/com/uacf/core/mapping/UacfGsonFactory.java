package com.uacf.core.mapping;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uacf.core.mapping.GsonMappableIso8601Date.Deserializer;
import com.uacf.core.mapping.GsonMappableIso8601Date.Serializer;
import com.uacf.core.util.MapUtil.Builder;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

public final class UacfGsonFactory {
    private static Map<Object, Type> typeAdapters = new Builder().put(new Deserializer(), GsonMappableIso8601Date.class).put(new Serializer(), GsonMappableIso8601Date.class).put(new GsonMappableYmdDate.Deserializer(), GsonMappableYmdDate.class).put(new GsonMappableYmdDate.Serializer(), GsonMappableYmdDate.class).put(new GsonMappableYmdHmsDate.Deserializer(), GsonMappableYmdHmsDate.class).put(new GsonMappableYmdHmsDate.Serializer(), GsonMappableYmdHmsDate.class).put(new GsonMappableHmsDate.Deserializer(), GsonMappableHmsDate.class).put(new GsonMappableHmsDate.Serializer(), GsonMappableHmsDate.class).build();

    public static Gson newInstance(FieldNamingPolicy fieldNamingPolicy) {
        GsonBuilder excludeFieldsWithoutExposeAnnotation = new GsonBuilder().setFieldNamingPolicy(fieldNamingPolicy).excludeFieldsWithoutExposeAnnotation();
        for (Entry entry : typeAdapters.entrySet()) {
            excludeFieldsWithoutExposeAnnotation.registerTypeAdapter((Type) entry.getValue(), entry.getKey());
        }
        return excludeFieldsWithoutExposeAnnotation.create();
    }

    public static Gson newInstance() {
        return newInstance(FieldNamingPolicy.IDENTITY);
    }
}
