package com.myfitnesspal.shared.model.date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.ReturningFunction1;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

public class MfpYmdDate extends MfpDateBase {

    public static class Deserializer extends Deserializer {
        public /* bridge */ /* synthetic */ MfpDateBase deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return super.deserialize(jsonElement, type, jsonDeserializationContext);
        }

        public Deserializer() {
            super(MfpYmdDate.getFormatter(), new ReturningFunction1<MfpDateBase, Date>() {
                public MfpDateBase execute(Date date) {
                    return MfpYmdDate.newInstance(date);
                }
            });
        }
    }

    public static class Serializer extends Serializer {
        public /* bridge */ /* synthetic */ JsonElement serialize(MfpDateBase mfpDateBase, Type type, JsonSerializationContext jsonSerializationContext) {
            return super.serialize(mfpDateBase, type, jsonSerializationContext);
        }

        public Serializer() {
            super(MfpYmdDate.getFormatter());
        }
    }

    public static MfpYmdDate newInstance() {
        return new MfpYmdDate();
    }

    public static MfpYmdDate newInstance(long j) {
        MfpYmdDate newInstance = newInstance();
        newInstance.setTime(j);
        return newInstance;
    }

    public static MfpYmdDate newInstance(Date date) {
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
