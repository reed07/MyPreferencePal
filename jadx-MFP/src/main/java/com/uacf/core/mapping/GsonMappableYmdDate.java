package com.uacf.core.mapping;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.ReturningFunction1;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

public class GsonMappableYmdDate extends GsonMappableDateBase {

    public static class Deserializer extends Deserializer {
        public /* bridge */ /* synthetic */ GsonMappableDateBase deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return super.deserialize(jsonElement, type, jsonDeserializationContext);
        }

        public Deserializer() {
            super(GsonMappableYmdDate.getFormatter(), new ReturningFunction1<GsonMappableDateBase, Date>() {
                public GsonMappableDateBase execute(Date date) {
                    return GsonMappableYmdDate.newInstance(date);
                }
            });
        }
    }

    public static class Serializer extends Serializer {
        public /* bridge */ /* synthetic */ JsonElement serialize(GsonMappableDateBase gsonMappableDateBase, Type type, JsonSerializationContext jsonSerializationContext) {
            return super.serialize(gsonMappableDateBase, type, jsonSerializationContext);
        }

        public Serializer() {
            super(GsonMappableYmdDate.getFormatter());
        }
    }

    public static GsonMappableYmdDate newInstance() {
        return new GsonMappableYmdDate();
    }

    public static GsonMappableYmdDate newInstance(long j) {
        GsonMappableYmdDate newInstance = newInstance();
        newInstance.setTime(j);
        return newInstance;
    }

    public static GsonMappableYmdDate newInstance(Date date) {
        if (date != null) {
            return newInstance(date.getTime());
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static DateFormat getFormatter() {
        return Format.newIso8601DateFormat();
    }
}
