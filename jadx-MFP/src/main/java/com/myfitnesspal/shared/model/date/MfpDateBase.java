package com.myfitnesspal.shared.model.date;

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

public abstract class MfpDateBase extends Date {

    protected static class Deserializer implements JsonDeserializer<MfpDateBase> {
        private final ReturningFunction1<MfpDateBase, Date> factory;
        private final DateFormat formatter;

        public Deserializer(DateFormat dateFormat, ReturningFunction1<MfpDateBase, Date> returningFunction1) {
            this.formatter = dateFormat;
            this.factory = returningFunction1;
        }

        public MfpDateBase deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                return (MfpDateBase) this.factory.execute(this.formatter.parse(jsonElement.getAsString()));
            } catch (ParseException e) {
                Ln.e(e);
                return null;
            }
        }
    }

    protected static class Serializer implements JsonSerializer<MfpDateBase> {
        private final DateFormat formatter;

        public Serializer(DateFormat dateFormat) {
            this.formatter = dateFormat;
        }

        public JsonElement serialize(MfpDateBase mfpDateBase, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(this.formatter.format(mfpDateBase));
        }
    }

    public MfpDateBase() {
    }

    public MfpDateBase(long j) {
        super(j);
    }

    public MfpDateBase(Date date) {
        if (date != null) {
            setTime(date.getTime());
        }
    }
}
