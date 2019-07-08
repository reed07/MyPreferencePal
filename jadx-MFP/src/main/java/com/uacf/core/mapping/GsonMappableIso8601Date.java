package com.uacf.core.mapping;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.ReturningFunction1;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class GsonMappableIso8601Date extends GsonMappableDateBase {

    public static class Deserializer extends Deserializer {
        private static DateFormat altFormat = Format.newIso8601DateTimeFormatWithMs();

        public /* bridge */ /* synthetic */ GsonMappableDateBase deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return super.deserialize(jsonElement, type, jsonDeserializationContext);
        }

        public Deserializer() {
            super(GsonMappableIso8601Date.getFormatter(), new ReturningFunction1<GsonMappableDateBase, Date>() {
                public GsonMappableDateBase execute(Date date) {
                    return GsonMappableIso8601Date.newInstance(date);
                }
            });
        }

        /* access modifiers changed from: protected */
        public Date parseDate(String str) throws ParseException {
            try {
                return super.parseDate(str);
            } catch (ParseException unused) {
                return altFormat.parse(str);
            }
        }
    }

    public static class Serializer extends Serializer {
        public /* bridge */ /* synthetic */ JsonElement serialize(GsonMappableDateBase gsonMappableDateBase, Type type, JsonSerializationContext jsonSerializationContext) {
            return super.serialize(gsonMappableDateBase, type, jsonSerializationContext);
        }

        public Serializer() {
            super(GsonMappableIso8601Date.getFormatter());
        }
    }

    public static GsonMappableIso8601Date newInstance() {
        return new GsonMappableIso8601Date();
    }

    public static GsonMappableIso8601Date newInstance(long j) {
        GsonMappableIso8601Date newInstance = newInstance();
        newInstance.setTime(j);
        return newInstance;
    }

    public static GsonMappableIso8601Date newInstance(Date date) {
        if (date != null) {
            return newInstance(date.getTime());
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static DateFormat getFormatter() {
        return Format.newIso8601DateTimeFormat();
    }
}
