package com.myfitnesspal.shared.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.myfitnesspal.shared.model.date.MfpHmsDate;
import com.myfitnesspal.shared.model.date.MfpIso8601Date;
import com.myfitnesspal.shared.model.date.MfpIso8601Date.Deserializer;
import com.myfitnesspal.shared.model.date.MfpIso8601Date.Serializer;
import com.myfitnesspal.shared.model.date.MfpYmdDate;
import com.myfitnesspal.shared.model.date.MfpYmdHmsDate;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.mapping.GsonMappableHmsDate;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import com.uacf.core.mapping.GsonMappableYmdDate;
import com.uacf.core.mapping.GsonMappableYmdHmsDate;
import com.uacf.core.util.Tuple2;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class JsonSerializers {
    private static List<Tuple2<? extends Class, Object>> TYPE_ADAPTERS = new CopyOnWriteArrayList();

    public static class EnergyDeserializer implements JsonDeserializer<Energy> {
        public Energy deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return Energy.fromString(jsonElement.getAsString());
        }
    }

    public static class EnergySerializer implements JsonSerializer<Energy> {
        public JsonElement serialize(Energy energy, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(energy.toString());
        }
    }

    public static class LengthDeserializer implements JsonDeserializer<Length> {
        public Length deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return Length.fromString(jsonElement.getAsString());
        }
    }

    public static class LengthSerializer implements JsonSerializer<Length> {
        public JsonElement serialize(Length length, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(length.toString());
        }
    }

    public static class WeightDeserializer implements JsonDeserializer<Weight> {
        public Weight deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return Weight.fromString(jsonElement.getAsString());
        }
    }

    public static class WeightSerializer implements JsonSerializer<Weight> {
        public JsonElement serialize(Weight weight, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(weight.toString());
        }
    }

    static {
        addTypeAdapter(Weight.class, new WeightSerializer());
        addTypeAdapter(Weight.class, new WeightDeserializer());
        addTypeAdapter(Length.class, new LengthSerializer());
        addTypeAdapter(Length.class, new LengthDeserializer());
        addTypeAdapter(Energy.class, new EnergySerializer());
        addTypeAdapter(Energy.class, new EnergyDeserializer());
        addTypeAdapter(MfpIso8601Date.class, new Deserializer());
        addTypeAdapter(MfpIso8601Date.class, new Serializer());
        addTypeAdapter(MfpYmdDate.class, new MfpYmdDate.Deserializer());
        addTypeAdapter(MfpYmdDate.class, new MfpYmdDate.Serializer());
        addTypeAdapter(MfpYmdHmsDate.class, new MfpYmdHmsDate.Deserializer());
        addTypeAdapter(MfpYmdHmsDate.class, new MfpYmdHmsDate.Serializer());
        addTypeAdapter(MfpHmsDate.class, new MfpHmsDate.Deserializer());
        addTypeAdapter(MfpHmsDate.class, new MfpHmsDate.Serializer());
        addTypeAdapter(GsonMappableIso8601Date.class, new GsonMappableIso8601Date.Deserializer());
        addTypeAdapter(GsonMappableIso8601Date.class, new GsonMappableIso8601Date.Serializer());
        addTypeAdapter(GsonMappableYmdDate.class, new GsonMappableYmdDate.Deserializer());
        addTypeAdapter(GsonMappableYmdDate.class, new GsonMappableYmdDate.Serializer());
        addTypeAdapter(GsonMappableYmdHmsDate.class, new GsonMappableYmdHmsDate.Deserializer());
        addTypeAdapter(GsonMappableYmdHmsDate.class, new GsonMappableYmdHmsDate.Serializer());
        addTypeAdapter(GsonMappableHmsDate.class, new GsonMappableHmsDate.Deserializer());
        addTypeAdapter(GsonMappableHmsDate.class, new GsonMappableHmsDate.Serializer());
    }

    public static void addTypeAdapter(Class<?> cls, Object obj) {
        TYPE_ADAPTERS.add(Tuple2.create(cls, obj));
    }

    public static GsonBuilder register(GsonBuilder gsonBuilder) {
        for (Tuple2 tuple2 : TYPE_ADAPTERS) {
            gsonBuilder.registerTypeAdapter((Type) tuple2.getItem1(), tuple2.getItem2());
        }
        return gsonBuilder;
    }
}
