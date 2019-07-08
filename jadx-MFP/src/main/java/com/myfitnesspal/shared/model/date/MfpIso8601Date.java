package com.myfitnesspal.shared.model.date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class MfpIso8601Date extends MfpDateBase {

    public static class Deserializer extends Deserializer {
        public /* bridge */ /* synthetic */ MfpDateBase deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return super.deserialize(jsonElement, type, jsonDeserializationContext);
        }

        public Deserializer() {
            super(MfpIso8601Date.getFormatter(), new ReturningFunction1<MfpDateBase, Date>() {
                public MfpDateBase execute(Date date) {
                    return MfpIso8601Date.newInstance(date);
                }
            });
        }
    }

    public static class Serializer extends Serializer {
        public /* bridge */ /* synthetic */ JsonElement serialize(MfpDateBase mfpDateBase, Type type, JsonSerializationContext jsonSerializationContext) {
            return super.serialize(mfpDateBase, type, jsonSerializationContext);
        }

        public Serializer() {
            super(MfpIso8601Date.getFormatter());
        }
    }

    public static MfpIso8601Date newInstance() {
        return new MfpIso8601Date();
    }

    public static MfpIso8601Date newInstance(long j) {
        MfpIso8601Date newInstance = newInstance();
        newInstance.setTime(j);
        return newInstance;
    }

    public static MfpIso8601Date newInstance(String str) {
        try {
            return newInstance(getFormatter().parse(str));
        } catch (ParseException e) {
            Ln.e(e);
            throw new RuntimeException(e);
        }
    }

    public static MfpIso8601Date newInstance(Date date) {
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
