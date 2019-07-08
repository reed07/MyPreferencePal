package com.uacf.core.mapping;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public abstract class GsonMappableDateBase extends Date {

    protected static class Deserializer implements JsonDeserializer<GsonMappableDateBase> {
        private final ReturningFunction1<GsonMappableDateBase, Date> factory;
        private final DateFormat formatter;

        public Deserializer(DateFormat dateFormat, ReturningFunction1<GsonMappableDateBase, Date> returningFunction1) {
            this.formatter = dateFormat;
            this.factory = returningFunction1;
        }

        public GsonMappableDateBase deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                return (GsonMappableDateBase) this.factory.execute(parseDate(jsonElement.getAsString()));
            } catch (ParseException e) {
                Ln.e(e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public Date parseDate(String str) throws ParseException {
            return this.formatter.parse(str);
        }
    }

    protected static class Serializer implements JsonSerializer<GsonMappableDateBase> {
        private final DateFormat formatter;

        public Serializer(DateFormat dateFormat) {
            this.formatter = dateFormat;
        }

        public JsonElement serialize(GsonMappableDateBase gsonMappableDateBase, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(this.formatter.format(gsonMappableDateBase));
        }
    }
}
