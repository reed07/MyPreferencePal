package com.uacf.core.mapping;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.IOException;

public class GsonObjectMapper implements Mapper2<GsonObjectMapper, String> {
    private static Gson defaultObjectMapper;
    private Class<?> clazz;
    private Gson objectMapper;

    public GsonObjectMapper(FieldNamingPolicy fieldNamingPolicy) {
        this.objectMapper = UacfGsonFactory.newInstance(fieldNamingPolicy);
    }

    public GsonObjectMapper() {
        this(FieldNamingPolicy.IDENTITY);
    }

    public <TOutput> GsonObjectMapper withType(Class<? extends TOutput> cls) {
        this.clazz = cls;
        return this;
    }

    public <T> String reverseMap(T t) {
        return Strings.toString(getObjectMapper().toJson((Object) t), "");
    }

    public <TOutput> TOutput mapFrom(String str) throws IOException {
        Class<?> cls = this.clazz;
        if (cls != null) {
            return mapFrom(str, cls);
        }
        throw new IllegalStateException("Clazz must be valid");
    }

    public <TOutput> TOutput tryMapFrom(String str) {
        try {
            return mapFrom(Strings.toString(str));
        } catch (Exception e) {
            Ln.d(e, "MAPPER: error while mapping", new Object[0]);
            return null;
        }
    }

    public <T> T mapFrom(String str, Class<? extends T> cls) throws IOException {
        if (Strings.isEmpty(str)) {
            return null;
        }
        try {
            return getObjectMapper().fromJson(str, cls);
        } catch (JsonSyntaxException e) {
            throw new IOException(e);
        }
    }

    public <T> T tryMapFrom(String str, Class<? extends T> cls) {
        try {
            return mapFrom(str, cls);
        } catch (Exception e) {
            Ln.d(e, "MAPPER: error while mapping", new Object[0]);
            return null;
        }
    }

    private Gson getObjectMapper() {
        Gson gson = this.objectMapper;
        if (gson != null) {
            return gson;
        }
        if (defaultObjectMapper == null) {
            defaultObjectMapper = UacfGsonFactory.newInstance();
        }
        return defaultObjectMapper;
    }
}
