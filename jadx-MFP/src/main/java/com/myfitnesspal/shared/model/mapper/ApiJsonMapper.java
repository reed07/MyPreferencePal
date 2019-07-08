package com.myfitnesspal.shared.model.mapper;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.myfitnesspal.shared.util.JsonSerializers;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.IOException;
import javax.inject.Inject;

public class ApiJsonMapper implements JsonMapper<ApiJsonMapper> {
    private static Gson defaultObjectMapper;
    private Class<?> clazz;
    private Gson objectMapper;

    public ApiJsonMapper(FieldNamingPolicy fieldNamingPolicy) {
        this.objectMapper = createObjectMapper(fieldNamingPolicy);
    }

    @Inject
    public ApiJsonMapper() {
    }

    public <TOutput> ApiJsonMapper withType(Class<? extends TOutput> cls) {
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
            defaultObjectMapper = createObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        }
        return defaultObjectMapper;
    }

    private Gson createObjectMapper(FieldNamingPolicy fieldNamingPolicy) {
        return JsonSerializers.register(new GsonBuilder()).setFieldNamingPolicy(fieldNamingPolicy).excludeFieldsWithoutExposeAnnotation().create();
    }
}
