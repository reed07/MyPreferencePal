package com.google.protobuf.util;

import com.facebook.GraphRequest;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;
import com.google.protobuf.Any;
import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.Syntax;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.Duration;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.FieldMask;
import com.google.protobuf.FloatValue;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ListValue;
import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.NullValue;
import com.google.protobuf.StringValue;
import com.google.protobuf.Struct;
import com.google.protobuf.Timestamp;
import com.google.protobuf.UInt32Value;
import com.google.protobuf.UInt64Value;
import com.google.protobuf.Value;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

public class JsonFormat {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(JsonFormat.class.getName());

    private static final class CompactTextGenerator implements TextGenerator {
        private final Appendable output;

        public void indent() {
        }

        public void outdent() {
        }

        private CompactTextGenerator(Appendable appendable) {
            this.output = appendable;
        }

        public void print(CharSequence charSequence) throws IOException {
            this.output.append(charSequence);
        }
    }

    public static class Parser {
        private static final int DEFAULT_RECURSION_LIMIT = 100;
        private final boolean ignoringUnknownFields;
        private final int recursionLimit;
        private final TypeRegistry registry;

        private Parser(TypeRegistry typeRegistry, boolean z, int i) {
            this.registry = typeRegistry;
            this.ignoringUnknownFields = z;
            this.recursionLimit = i;
        }

        public Parser usingTypeRegistry(TypeRegistry typeRegistry) {
            if (this.registry == TypeRegistry.getEmptyTypeRegistry()) {
                return new Parser(typeRegistry, this.ignoringUnknownFields, this.recursionLimit);
            }
            throw new IllegalArgumentException("Only one registry is allowed.");
        }

        public Parser ignoringUnknownFields() {
            return new Parser(this.registry, true, this.recursionLimit);
        }

        public void merge(String str, Builder builder) throws InvalidProtocolBufferException {
            new ParserImpl(this.registry, this.ignoringUnknownFields, this.recursionLimit).merge(str, builder);
        }

        public void merge(Reader reader, Builder builder) throws IOException {
            new ParserImpl(this.registry, this.ignoringUnknownFields, this.recursionLimit).merge(reader, builder);
        }

        /* access modifiers changed from: 0000 */
        public Parser usingRecursionLimit(int i) {
            return new Parser(this.registry, this.ignoringUnknownFields, i);
        }
    }

    private static class ParserImpl {
        private static final double EPSILON = 1.0E-6d;
        private static final BigDecimal MAX_DOUBLE = new BigDecimal(String.valueOf(Double.MAX_VALUE)).multiply(MORE_THAN_ONE);
        private static final BigInteger MAX_UINT64 = new BigInteger("FFFFFFFFFFFFFFFF", 16);
        private static final BigDecimal MIN_DOUBLE = new BigDecimal(String.valueOf(-1.7976931348623157E308d)).multiply(MORE_THAN_ONE);
        private static final BigDecimal MORE_THAN_ONE = new BigDecimal(String.valueOf(1.000001d));
        private static final Map<String, WellKnownTypeParser> wellKnownTypeParsers = buildWellKnownTypeParsers();
        private int currentDepth;
        private final Map<Descriptor, Map<String, FieldDescriptor>> fieldNameMaps = new HashMap();
        private final boolean ignoringUnknownFields;
        private final JsonParser jsonParser;
        private final int recursionLimit;
        private final TypeRegistry registry;

        private interface WellKnownTypeParser {
            void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException;
        }

        ParserImpl(TypeRegistry typeRegistry, boolean z, int i) {
            this.registry = typeRegistry;
            this.ignoringUnknownFields = z;
            this.jsonParser = new JsonParser();
            this.recursionLimit = i;
            this.currentDepth = 0;
        }

        /* access modifiers changed from: 0000 */
        public void merge(Reader reader, Builder builder) throws IOException {
            try {
                JsonReader jsonReader = new JsonReader(reader);
                jsonReader.setLenient(false);
                merge(this.jsonParser.parse(jsonReader), builder);
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (JsonIOException e2) {
                if (e2.getCause() instanceof IOException) {
                    throw ((IOException) e2.getCause());
                }
                throw new InvalidProtocolBufferException(e2.getMessage());
            } catch (Exception e3) {
                throw new InvalidProtocolBufferException(e3.getMessage());
            }
        }

        /* access modifiers changed from: 0000 */
        public void merge(String str, Builder builder) throws InvalidProtocolBufferException {
            try {
                JsonReader jsonReader = new JsonReader(new StringReader(str));
                jsonReader.setLenient(false);
                merge(this.jsonParser.parse(jsonReader), builder);
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Exception e2) {
                throw new InvalidProtocolBufferException(e2.getMessage());
            }
        }

        private static Map<String, WellKnownTypeParser> buildWellKnownTypeParsers() {
            HashMap hashMap = new HashMap();
            hashMap.put(Any.getDescriptor().getFullName(), new WellKnownTypeParser() {
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeAny(jsonElement, builder);
                }
            });
            AnonymousClass2 r1 = new WellKnownTypeParser() {
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeWrapper(jsonElement, builder);
                }
            };
            hashMap.put(BoolValue.getDescriptor().getFullName(), r1);
            hashMap.put(Int32Value.getDescriptor().getFullName(), r1);
            hashMap.put(UInt32Value.getDescriptor().getFullName(), r1);
            hashMap.put(Int64Value.getDescriptor().getFullName(), r1);
            hashMap.put(UInt64Value.getDescriptor().getFullName(), r1);
            hashMap.put(StringValue.getDescriptor().getFullName(), r1);
            hashMap.put(BytesValue.getDescriptor().getFullName(), r1);
            hashMap.put(FloatValue.getDescriptor().getFullName(), r1);
            hashMap.put(DoubleValue.getDescriptor().getFullName(), r1);
            hashMap.put(Timestamp.getDescriptor().getFullName(), new WellKnownTypeParser() {
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeTimestamp(jsonElement, builder);
                }
            });
            hashMap.put(Duration.getDescriptor().getFullName(), new WellKnownTypeParser() {
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeDuration(jsonElement, builder);
                }
            });
            hashMap.put(FieldMask.getDescriptor().getFullName(), new WellKnownTypeParser() {
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeFieldMask(jsonElement, builder);
                }
            });
            hashMap.put(Struct.getDescriptor().getFullName(), new WellKnownTypeParser() {
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeStruct(jsonElement, builder);
                }
            });
            hashMap.put(ListValue.getDescriptor().getFullName(), new WellKnownTypeParser() {
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeListValue(jsonElement, builder);
                }
            });
            hashMap.put(Value.getDescriptor().getFullName(), new WellKnownTypeParser() {
                public void merge(ParserImpl parserImpl, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
                    parserImpl.mergeValue(jsonElement, builder);
                }
            });
            return hashMap;
        }

        private void merge(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            WellKnownTypeParser wellKnownTypeParser = (WellKnownTypeParser) wellKnownTypeParsers.get(builder.getDescriptorForType().getFullName());
            if (wellKnownTypeParser != null) {
                wellKnownTypeParser.merge(this, jsonElement, builder);
            } else {
                mergeMessage(jsonElement, builder, false);
            }
        }

        private Map<String, FieldDescriptor> getFieldNameMap(Descriptor descriptor) {
            if (this.fieldNameMaps.containsKey(descriptor)) {
                return (Map) this.fieldNameMaps.get(descriptor);
            }
            HashMap hashMap = new HashMap();
            for (FieldDescriptor fieldDescriptor : descriptor.getFields()) {
                hashMap.put(fieldDescriptor.getName(), fieldDescriptor);
                hashMap.put(fieldDescriptor.getJsonName(), fieldDescriptor);
            }
            this.fieldNameMaps.put(descriptor, hashMap);
            return hashMap;
        }

        private void mergeMessage(JsonElement jsonElement, Builder builder, boolean z) throws InvalidProtocolBufferException {
            if (jsonElement instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) jsonElement;
                Map fieldNameMap = getFieldNameMap(builder.getDescriptorForType());
                for (Entry entry : jsonObject.entrySet()) {
                    if (!z || !((String) entry.getKey()).equals("@type")) {
                        FieldDescriptor fieldDescriptor = (FieldDescriptor) fieldNameMap.get(entry.getKey());
                        if (fieldDescriptor != null) {
                            mergeField(fieldDescriptor, (JsonElement) entry.getValue(), builder);
                        } else if (!this.ignoringUnknownFields) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Cannot find field: ");
                            sb.append((String) entry.getKey());
                            sb.append(" in message ");
                            sb.append(builder.getDescriptorForType().getFullName());
                            throw new InvalidProtocolBufferException(sb.toString());
                        }
                    }
                }
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expect message object but got: ");
            sb2.append(jsonElement);
            throw new InvalidProtocolBufferException(sb2.toString());
        }

        /* access modifiers changed from: private */
        public void mergeAny(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            Descriptor descriptorForType = builder.getDescriptorForType();
            FieldDescriptor findFieldByName = descriptorForType.findFieldByName("type_url");
            FieldDescriptor findFieldByName2 = descriptorForType.findFieldByName("value");
            if (findFieldByName == null || findFieldByName2 == null || findFieldByName.getType() != Type.STRING || findFieldByName2.getType() != Type.BYTES) {
                throw new InvalidProtocolBufferException("Invalid Any type.");
            } else if (jsonElement instanceof JsonObject) {
                JsonObject jsonObject = (JsonObject) jsonElement;
                if (!jsonObject.entrySet().isEmpty()) {
                    JsonElement jsonElement2 = jsonObject.get("@type");
                    if (jsonElement2 != null) {
                        String asString = jsonElement2.getAsString();
                        Descriptor find = this.registry.find(JsonFormat.getTypeName(asString));
                        if (find != null) {
                            builder.setField(findFieldByName, asString);
                            DynamicMessage.Builder newBuilderForType = DynamicMessage.getDefaultInstance(find).newBuilderForType();
                            WellKnownTypeParser wellKnownTypeParser = (WellKnownTypeParser) wellKnownTypeParsers.get(find.getFullName());
                            if (wellKnownTypeParser != null) {
                                JsonElement jsonElement3 = jsonObject.get("value");
                                if (jsonElement3 != null) {
                                    wellKnownTypeParser.merge(this, jsonElement3, newBuilderForType);
                                }
                            } else {
                                mergeMessage(jsonElement, newBuilderForType, true);
                            }
                            builder.setField(findFieldByName2, newBuilderForType.build().toByteString());
                            return;
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("Cannot resolve type: ");
                        sb.append(asString);
                        throw new InvalidProtocolBufferException(sb.toString());
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Missing type url when parsing: ");
                    sb2.append(jsonElement);
                    throw new InvalidProtocolBufferException(sb2.toString());
                }
            } else {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Expect message object but got: ");
                sb3.append(jsonElement);
                throw new InvalidProtocolBufferException(sb3.toString());
            }
        }

        /* access modifiers changed from: private */
        public void mergeFieldMask(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            builder.mergeFrom(FieldMaskUtil.fromJsonString(jsonElement.getAsString()).toByteString());
        }

        /* access modifiers changed from: private */
        public void mergeTimestamp(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            try {
                builder.mergeFrom(Timestamps.parse(jsonElement.getAsString()).toByteString());
            } catch (ParseException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to parse timestamp: ");
                sb.append(jsonElement);
                throw new InvalidProtocolBufferException(sb.toString());
            }
        }

        /* access modifiers changed from: private */
        public void mergeDuration(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            try {
                builder.mergeFrom(Durations.parse(jsonElement.getAsString()).toByteString());
            } catch (ParseException unused) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to parse duration: ");
                sb.append(jsonElement);
                throw new InvalidProtocolBufferException(sb.toString());
            }
        }

        /* access modifiers changed from: private */
        public void mergeStruct(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            FieldDescriptor findFieldByName = builder.getDescriptorForType().findFieldByName(GraphRequest.FIELDS_PARAM);
            if (findFieldByName != null) {
                mergeMapField(findFieldByName, jsonElement, builder);
                return;
            }
            throw new InvalidProtocolBufferException("Invalid Struct type.");
        }

        /* access modifiers changed from: private */
        public void mergeListValue(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            FieldDescriptor findFieldByName = builder.getDescriptorForType().findFieldByName("values");
            if (findFieldByName != null) {
                mergeRepeatedField(findFieldByName, jsonElement, builder);
                return;
            }
            throw new InvalidProtocolBufferException("Invalid ListValue type.");
        }

        /* access modifiers changed from: private */
        public void mergeValue(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            Descriptor descriptorForType = builder.getDescriptorForType();
            if (jsonElement instanceof JsonPrimitive) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElement;
                if (jsonPrimitive.isBoolean()) {
                    builder.setField(descriptorForType.findFieldByName("bool_value"), Boolean.valueOf(jsonPrimitive.getAsBoolean()));
                } else if (jsonPrimitive.isNumber()) {
                    builder.setField(descriptorForType.findFieldByName("number_value"), Double.valueOf(jsonPrimitive.getAsDouble()));
                } else {
                    builder.setField(descriptorForType.findFieldByName("string_value"), jsonPrimitive.getAsString());
                }
            } else if (jsonElement instanceof JsonObject) {
                FieldDescriptor findFieldByName = descriptorForType.findFieldByName("struct_value");
                Builder newBuilderForField = builder.newBuilderForField(findFieldByName);
                merge(jsonElement, newBuilderForField);
                builder.setField(findFieldByName, newBuilderForField.build());
            } else if (jsonElement instanceof JsonArray) {
                FieldDescriptor findFieldByName2 = descriptorForType.findFieldByName("list_value");
                Builder newBuilderForField2 = builder.newBuilderForField(findFieldByName2);
                merge(jsonElement, newBuilderForField2);
                builder.setField(findFieldByName2, newBuilderForField2.build());
            } else if (jsonElement instanceof JsonNull) {
                builder.setField(descriptorForType.findFieldByName("null_value"), NullValue.NULL_VALUE.getValueDescriptor());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected json data: ");
                sb.append(jsonElement);
                throw new IllegalStateException(sb.toString());
            }
        }

        /* access modifiers changed from: private */
        public void mergeWrapper(JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            Descriptor descriptorForType = builder.getDescriptorForType();
            FieldDescriptor findFieldByName = descriptorForType.findFieldByName("value");
            if (findFieldByName != null) {
                builder.setField(findFieldByName, parseFieldValue(findFieldByName, jsonElement, builder));
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid wrapper type: ");
            sb.append(descriptorForType.getFullName());
            throw new InvalidProtocolBufferException(sb.toString());
        }

        private void mergeField(FieldDescriptor fieldDescriptor, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            if (fieldDescriptor.isRepeated()) {
                if (builder.getRepeatedFieldCount(fieldDescriptor) > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Field ");
                    sb.append(fieldDescriptor.getFullName());
                    sb.append(" has already been set.");
                    throw new InvalidProtocolBufferException(sb.toString());
                }
            } else if (builder.hasField(fieldDescriptor)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Field ");
                sb2.append(fieldDescriptor.getFullName());
                sb2.append(" has already been set.");
                throw new InvalidProtocolBufferException(sb2.toString());
            } else if (!(fieldDescriptor.getContainingOneof() == null || builder.getOneofFieldDescriptor(fieldDescriptor.getContainingOneof()) == null)) {
                FieldDescriptor oneofFieldDescriptor = builder.getOneofFieldDescriptor(fieldDescriptor.getContainingOneof());
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Cannot set field ");
                sb3.append(fieldDescriptor.getFullName());
                sb3.append(" because another field ");
                sb3.append(oneofFieldDescriptor.getFullName());
                sb3.append(" belonging to the same oneof has already been set ");
                throw new InvalidProtocolBufferException(sb3.toString());
            }
            if (!fieldDescriptor.isRepeated() || !(jsonElement instanceof JsonNull)) {
                if (fieldDescriptor.isMapField()) {
                    mergeMapField(fieldDescriptor, jsonElement, builder);
                } else if (fieldDescriptor.isRepeated()) {
                    mergeRepeatedField(fieldDescriptor, jsonElement, builder);
                } else {
                    Object parseFieldValue = parseFieldValue(fieldDescriptor, jsonElement, builder);
                    if (parseFieldValue != null) {
                        builder.setField(fieldDescriptor, parseFieldValue);
                    }
                }
            }
        }

        private void mergeMapField(FieldDescriptor fieldDescriptor, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            if (jsonElement instanceof JsonObject) {
                Descriptor messageType = fieldDescriptor.getMessageType();
                FieldDescriptor findFieldByName = messageType.findFieldByName(IpcUtil.KEY_CODE);
                FieldDescriptor findFieldByName2 = messageType.findFieldByName("value");
                if (findFieldByName == null || findFieldByName2 == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid map field: ");
                    sb.append(fieldDescriptor.getFullName());
                    throw new InvalidProtocolBufferException(sb.toString());
                }
                for (Entry entry : ((JsonObject) jsonElement).entrySet()) {
                    Builder newBuilderForField = builder.newBuilderForField(fieldDescriptor);
                    Object parseFieldValue = parseFieldValue(findFieldByName, new JsonPrimitive((String) entry.getKey()), newBuilderForField);
                    Object parseFieldValue2 = parseFieldValue(findFieldByName2, (JsonElement) entry.getValue(), newBuilderForField);
                    if (parseFieldValue2 != null) {
                        newBuilderForField.setField(findFieldByName, parseFieldValue);
                        newBuilderForField.setField(findFieldByName2, parseFieldValue2);
                        builder.addRepeatedField(fieldDescriptor, newBuilderForField.build());
                    } else {
                        throw new InvalidProtocolBufferException("Map value cannot be null.");
                    }
                }
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expect a map object but found: ");
            sb2.append(jsonElement);
            throw new InvalidProtocolBufferException(sb2.toString());
        }

        private void mergeRepeatedField(FieldDescriptor fieldDescriptor, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            if (jsonElement instanceof JsonArray) {
                JsonArray jsonArray = (JsonArray) jsonElement;
                int i = 0;
                while (i < jsonArray.size()) {
                    Object parseFieldValue = parseFieldValue(fieldDescriptor, jsonArray.get(i), builder);
                    if (parseFieldValue != null) {
                        builder.addRepeatedField(fieldDescriptor, parseFieldValue);
                        i++;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Repeated field elements cannot be null in field: ");
                        sb.append(fieldDescriptor.getFullName());
                        throw new InvalidProtocolBufferException(sb.toString());
                    }
                }
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Expect an array but found: ");
            sb2.append(jsonElement);
            throw new InvalidProtocolBufferException(sb2.toString());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
            return new java.math.BigDecimal(r4.getAsString()).intValueExact();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
            r1 = new java.lang.StringBuilder();
            r1.append("Not an int32 value: ");
            r1.append(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
            throw new com.google.protobuf.InvalidProtocolBufferException(r1.toString());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0009 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int parseInt32(com.google.gson.JsonElement r4) throws com.google.protobuf.InvalidProtocolBufferException {
            /*
                r3 = this;
                java.lang.String r0 = r4.getAsString()     // Catch:{ Exception -> 0x0009 }
                int r4 = java.lang.Integer.parseInt(r0)     // Catch:{ Exception -> 0x0009 }
                return r4
            L_0x0009:
                java.math.BigDecimal r0 = new java.math.BigDecimal     // Catch:{ Exception -> 0x0017 }
                java.lang.String r1 = r4.getAsString()     // Catch:{ Exception -> 0x0017 }
                r0.<init>(r1)     // Catch:{ Exception -> 0x0017 }
                int r4 = r0.intValueExact()     // Catch:{ Exception -> 0x0017 }
                return r4
            L_0x0017:
                com.google.protobuf.InvalidProtocolBufferException r0 = new com.google.protobuf.InvalidProtocolBufferException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Not an int32 value: "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r4 = r1.toString()
                r0.<init>(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.util.JsonFormat.ParserImpl.parseInt32(com.google.gson.JsonElement):int");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
            return new java.math.BigDecimal(r4.getAsString()).longValueExact();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
            r1 = new java.lang.StringBuilder();
            r1.append("Not an int64 value: ");
            r1.append(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
            throw new com.google.protobuf.InvalidProtocolBufferException(r1.toString());
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0009 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private long parseInt64(com.google.gson.JsonElement r4) throws com.google.protobuf.InvalidProtocolBufferException {
            /*
                r3 = this;
                java.lang.String r0 = r4.getAsString()     // Catch:{ Exception -> 0x0009 }
                long r0 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x0009 }
                return r0
            L_0x0009:
                java.math.BigDecimal r0 = new java.math.BigDecimal     // Catch:{ Exception -> 0x0017 }
                java.lang.String r1 = r4.getAsString()     // Catch:{ Exception -> 0x0017 }
                r0.<init>(r1)     // Catch:{ Exception -> 0x0017 }
                long r0 = r0.longValueExact()     // Catch:{ Exception -> 0x0017 }
                return r0
            L_0x0017:
                com.google.protobuf.InvalidProtocolBufferException r0 = new com.google.protobuf.InvalidProtocolBufferException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Not an int64 value: "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r4 = r1.toString()
                r0.<init>(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.util.JsonFormat.ParserImpl.parseInt64(com.google.gson.JsonElement):long");
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0030 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int parseUint32(com.google.gson.JsonElement r6) throws com.google.protobuf.InvalidProtocolBufferException {
            /*
                r5 = this;
                java.lang.String r0 = r6.getAsString()     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                long r0 = java.lang.Long.parseLong(r0)     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                r2 = 0
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 < 0) goto L_0x0019
                r2 = 4294967295(0xffffffff, double:2.1219957905E-314)
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 > 0) goto L_0x0019
                int r6 = (int) r0     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                return r6
            L_0x0019:
                com.google.protobuf.InvalidProtocolBufferException r0 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                r1.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                java.lang.String r2 = "Out of range uint32 value: "
                r1.append(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                r1.append(r6)     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                java.lang.String r1 = r1.toString()     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                r0.<init>(r1)     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
                throw r0     // Catch:{ InvalidProtocolBufferException -> 0x0087, Exception -> 0x0030 }
            L_0x0030:
                java.math.BigDecimal r0 = new java.math.BigDecimal     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                java.lang.String r1 = r6.getAsString()     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                r0.<init>(r1)     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                java.math.BigInteger r0 = r0.toBigIntegerExact()     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                int r1 = r0.signum()     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                if (r1 < 0) goto L_0x0057
                java.math.BigInteger r1 = new java.math.BigInteger     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                java.lang.String r2 = "FFFFFFFF"
                r3 = 16
                r1.<init>(r2, r3)     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                int r1 = r0.compareTo(r1)     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                if (r1 > 0) goto L_0x0057
                int r6 = r0.intValue()     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                return r6
            L_0x0057:
                com.google.protobuf.InvalidProtocolBufferException r0 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                r1.<init>()     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                java.lang.String r2 = "Out of range uint32 value: "
                r1.append(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                r1.append(r6)     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                java.lang.String r1 = r1.toString()     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                r0.<init>(r1)     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
                throw r0     // Catch:{ InvalidProtocolBufferException -> 0x0085, Exception -> 0x006e }
            L_0x006e:
                com.google.protobuf.InvalidProtocolBufferException r0 = new com.google.protobuf.InvalidProtocolBufferException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Not an uint32 value: "
                r1.append(r2)
                r1.append(r6)
                java.lang.String r6 = r1.toString()
                r0.<init>(r6)
                throw r0
            L_0x0085:
                r6 = move-exception
                throw r6
            L_0x0087:
                r6 = move-exception
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.util.JsonFormat.ParserImpl.parseUint32(com.google.gson.JsonElement):int");
        }

        private long parseUint64(JsonElement jsonElement) throws InvalidProtocolBufferException {
            try {
                BigInteger bigIntegerExact = new BigDecimal(jsonElement.getAsString()).toBigIntegerExact();
                if (bigIntegerExact.compareTo(BigInteger.ZERO) >= 0 && bigIntegerExact.compareTo(MAX_UINT64) <= 0) {
                    return bigIntegerExact.longValue();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Out of range uint64 value: ");
                sb.append(jsonElement);
                throw new InvalidProtocolBufferException(sb.toString());
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Not an uint64 value: ");
                sb2.append(jsonElement);
                throw new InvalidProtocolBufferException(sb2.toString());
            }
        }

        private boolean parseBool(JsonElement jsonElement) throws InvalidProtocolBufferException {
            if (jsonElement.getAsString().equals("true")) {
                return true;
            }
            if (jsonElement.getAsString().equals("false")) {
                return false;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid bool value: ");
            sb.append(jsonElement);
            throw new InvalidProtocolBufferException(sb.toString());
        }

        private float parseFloat(JsonElement jsonElement) throws InvalidProtocolBufferException {
            if (jsonElement.getAsString().equals("NaN")) {
                return Float.NaN;
            }
            if (jsonElement.getAsString().equals("Infinity")) {
                return Float.POSITIVE_INFINITY;
            }
            if (jsonElement.getAsString().equals("-Infinity")) {
                return Float.NEGATIVE_INFINITY;
            }
            try {
                double parseDouble = Double.parseDouble(jsonElement.getAsString());
                if (parseDouble <= 3.402826869208755E38d && parseDouble >= -3.402826869208755E38d) {
                    return (float) parseDouble;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Out of range float value: ");
                sb.append(jsonElement);
                throw new InvalidProtocolBufferException(sb.toString());
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Not a float value: ");
                sb2.append(jsonElement);
                throw new InvalidProtocolBufferException(sb2.toString());
            }
        }

        private double parseDouble(JsonElement jsonElement) throws InvalidProtocolBufferException {
            if (jsonElement.getAsString().equals("NaN")) {
                return Double.NaN;
            }
            if (jsonElement.getAsString().equals("Infinity")) {
                return Double.POSITIVE_INFINITY;
            }
            if (jsonElement.getAsString().equals("-Infinity")) {
                return Double.NEGATIVE_INFINITY;
            }
            try {
                BigDecimal bigDecimal = new BigDecimal(jsonElement.getAsString());
                if (bigDecimal.compareTo(MAX_DOUBLE) <= 0 && bigDecimal.compareTo(MIN_DOUBLE) >= 0) {
                    return bigDecimal.doubleValue();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Out of range double value: ");
                sb.append(jsonElement);
                throw new InvalidProtocolBufferException(sb.toString());
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (Exception unused) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Not an double value: ");
                sb2.append(jsonElement);
                throw new InvalidProtocolBufferException(sb2.toString());
            }
        }

        private String parseString(JsonElement jsonElement) {
            return jsonElement.getAsString();
        }

        private ByteString parseBytes(JsonElement jsonElement) throws InvalidProtocolBufferException {
            try {
                return ByteString.copyFrom(BaseEncoding.base64().decode(jsonElement.getAsString()));
            } catch (IllegalArgumentException unused) {
                return ByteString.copyFrom(BaseEncoding.base64Url().decode(jsonElement.getAsString()));
            }
        }

        private EnumValueDescriptor parseEnum(EnumDescriptor enumDescriptor, JsonElement jsonElement) throws InvalidProtocolBufferException {
            EnumValueDescriptor enumValueDescriptor;
            String asString = jsonElement.getAsString();
            EnumValueDescriptor findValueByName = enumDescriptor.findValueByName(asString);
            if (findValueByName == null) {
                try {
                    int parseInt32 = parseInt32(jsonElement);
                    if (enumDescriptor.getFile().getSyntax() == Syntax.PROTO3) {
                        enumValueDescriptor = enumDescriptor.findValueByNumberCreatingIfUnknown(parseInt32);
                    } else {
                        enumValueDescriptor = enumDescriptor.findValueByNumber(parseInt32);
                    }
                    findValueByName = enumValueDescriptor;
                } catch (InvalidProtocolBufferException unused) {
                }
                if (findValueByName == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid enum value: ");
                    sb.append(asString);
                    sb.append(" for enum type: ");
                    sb.append(enumDescriptor.getFullName());
                    throw new InvalidProtocolBufferException(sb.toString());
                }
            }
            return findValueByName;
        }

        private Object parseFieldValue(FieldDescriptor fieldDescriptor, JsonElement jsonElement, Builder builder) throws InvalidProtocolBufferException {
            if (!(jsonElement instanceof JsonNull)) {
                switch (fieldDescriptor.getType()) {
                    case INT32:
                    case SINT32:
                    case SFIXED32:
                        return Integer.valueOf(parseInt32(jsonElement));
                    case INT64:
                    case SINT64:
                    case SFIXED64:
                        return Long.valueOf(parseInt64(jsonElement));
                    case BOOL:
                        return Boolean.valueOf(parseBool(jsonElement));
                    case FLOAT:
                        return Float.valueOf(parseFloat(jsonElement));
                    case DOUBLE:
                        return Double.valueOf(parseDouble(jsonElement));
                    case UINT32:
                    case FIXED32:
                        return Integer.valueOf(parseUint32(jsonElement));
                    case UINT64:
                    case FIXED64:
                        return Long.valueOf(parseUint64(jsonElement));
                    case STRING:
                        return parseString(jsonElement);
                    case BYTES:
                        return parseBytes(jsonElement);
                    case ENUM:
                        return parseEnum(fieldDescriptor.getEnumType(), jsonElement);
                    case MESSAGE:
                    case GROUP:
                        int i = this.currentDepth;
                        if (i < this.recursionLimit) {
                            this.currentDepth = i + 1;
                            Builder newBuilderForField = builder.newBuilderForField(fieldDescriptor);
                            merge(jsonElement, newBuilderForField);
                            this.currentDepth--;
                            return newBuilderForField.build();
                        }
                        throw new InvalidProtocolBufferException("Hit recursion limit.");
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("Invalid field type: ");
                        sb.append(fieldDescriptor.getType());
                        throw new InvalidProtocolBufferException(sb.toString());
                }
            } else if (fieldDescriptor.getJavaType() == JavaType.MESSAGE && fieldDescriptor.getMessageType().getFullName().equals(Value.getDescriptor().getFullName())) {
                return builder.newBuilderForField(fieldDescriptor).mergeFrom(Value.newBuilder().setNullValueValue(0).build().toByteString()).build();
            } else if (fieldDescriptor.getJavaType() != JavaType.ENUM || !fieldDescriptor.getEnumType().getFullName().equals(NullValue.getDescriptor().getFullName())) {
                return null;
            } else {
                return fieldDescriptor.getEnumType().findValueByNumber(0);
            }
        }
    }

    private static final class PrettyTextGenerator implements TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;

        private PrettyTextGenerator(Appendable appendable) {
            this.indent = new StringBuilder();
            this.atStartOfLine = true;
            this.output = appendable;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length >= 2) {
                this.indent.delete(length - 2, length);
                return;
            }
            throw new IllegalArgumentException(" Outdent() without matching Indent().");
        }

        public void print(CharSequence charSequence) throws IOException {
            int length = charSequence.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i2) == 10) {
                    int i3 = i2 + 1;
                    write(charSequence.subSequence(i, i3));
                    this.atStartOfLine = true;
                    i = i3;
                }
            }
            write(charSequence.subSequence(i, length));
        }

        private void write(CharSequence charSequence) throws IOException {
            if (charSequence.length() != 0) {
                if (this.atStartOfLine) {
                    this.atStartOfLine = false;
                    this.output.append(this.indent);
                }
                this.output.append(charSequence);
            }
        }
    }

    public static class Printer {
        private boolean alwaysOutputDefaultValueFields;
        private Set<FieldDescriptor> includingDefaultValueFields;
        private final boolean omittingInsignificantWhitespace;
        private final boolean preservingProtoFieldNames;
        private final TypeRegistry registry;

        private Printer(TypeRegistry typeRegistry, boolean z, Set<FieldDescriptor> set, boolean z2, boolean z3) {
            this.registry = typeRegistry;
            this.alwaysOutputDefaultValueFields = z;
            this.includingDefaultValueFields = set;
            this.preservingProtoFieldNames = z2;
            this.omittingInsignificantWhitespace = z3;
        }

        public Printer usingTypeRegistry(TypeRegistry typeRegistry) {
            if (this.registry == TypeRegistry.getEmptyTypeRegistry()) {
                Printer printer = new Printer(typeRegistry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, this.omittingInsignificantWhitespace);
                return printer;
            }
            throw new IllegalArgumentException("Only one registry is allowed.");
        }

        public Printer includingDefaultValueFields() {
            checkUnsetIncludingDefaultValueFields();
            Printer printer = new Printer(this.registry, true, Collections.emptySet(), this.preservingProtoFieldNames, this.omittingInsignificantWhitespace);
            return printer;
        }

        public Printer includingDefaultValueFields(Set<FieldDescriptor> set) {
            Preconditions.checkArgument(set != null && !set.isEmpty(), "Non-empty Set must be supplied for includingDefaultValueFields.");
            checkUnsetIncludingDefaultValueFields();
            Printer printer = new Printer(this.registry, false, set, this.preservingProtoFieldNames, this.omittingInsignificantWhitespace);
            return printer;
        }

        private void checkUnsetIncludingDefaultValueFields() {
            if (this.alwaysOutputDefaultValueFields || !this.includingDefaultValueFields.isEmpty()) {
                throw new IllegalStateException("JsonFormat includingDefaultValueFields has already been set.");
            }
        }

        public Printer preservingProtoFieldNames() {
            Printer printer = new Printer(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, true, this.omittingInsignificantWhitespace);
            return printer;
        }

        public Printer omittingInsignificantWhitespace() {
            Printer printer = new Printer(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, true);
            return printer;
        }

        public void appendTo(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
            PrinterImpl printerImpl = new PrinterImpl(this.registry, this.alwaysOutputDefaultValueFields, this.includingDefaultValueFields, this.preservingProtoFieldNames, appendable, this.omittingInsignificantWhitespace);
            printerImpl.print(messageOrBuilder);
        }

        public String print(MessageOrBuilder messageOrBuilder) throws InvalidProtocolBufferException {
            try {
                StringBuilder sb = new StringBuilder();
                appendTo(messageOrBuilder, sb);
                return sb.toString();
            } catch (InvalidProtocolBufferException e) {
                throw e;
            } catch (IOException e2) {
                throw new IllegalStateException(e2);
            }
        }
    }

    private static final class PrinterImpl {
        private static final Map<String, WellKnownTypePrinter> wellKnownTypePrinters = buildWellKnownTypePrinters();
        private final boolean alwaysOutputDefaultValueFields;
        private final CharSequence blankOrNewLine;
        private final CharSequence blankOrSpace;
        private final TextGenerator generator;
        private final Gson gson = GsonHolder.DEFAULT_GSON;
        private final Set<FieldDescriptor> includingDefaultValueFields;
        private final boolean preservingProtoFieldNames;
        private final TypeRegistry registry;

        private static class GsonHolder {
            /* access modifiers changed from: private */
            public static final Gson DEFAULT_GSON = new GsonBuilder().disableHtmlEscaping().create();

            private GsonHolder() {
            }
        }

        private interface WellKnownTypePrinter {
            void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException;
        }

        PrinterImpl(TypeRegistry typeRegistry, boolean z, Set<FieldDescriptor> set, boolean z2, Appendable appendable, boolean z3) {
            this.registry = typeRegistry;
            this.alwaysOutputDefaultValueFields = z;
            this.includingDefaultValueFields = set;
            this.preservingProtoFieldNames = z2;
            if (z3) {
                this.generator = new CompactTextGenerator(appendable);
                this.blankOrSpace = "";
                this.blankOrNewLine = "";
                return;
            }
            this.generator = new PrettyTextGenerator(appendable);
            this.blankOrSpace = " ";
            this.blankOrNewLine = "\n";
        }

        /* access modifiers changed from: 0000 */
        public void print(MessageOrBuilder messageOrBuilder) throws IOException {
            WellKnownTypePrinter wellKnownTypePrinter = (WellKnownTypePrinter) wellKnownTypePrinters.get(messageOrBuilder.getDescriptorForType().getFullName());
            if (wellKnownTypePrinter != null) {
                wellKnownTypePrinter.print(this, messageOrBuilder);
            } else {
                print(messageOrBuilder, null);
            }
        }

        private static Map<String, WellKnownTypePrinter> buildWellKnownTypePrinters() {
            HashMap hashMap = new HashMap();
            hashMap.put(Any.getDescriptor().getFullName(), new WellKnownTypePrinter() {
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printAny(messageOrBuilder);
                }
            });
            AnonymousClass2 r1 = new WellKnownTypePrinter() {
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printWrapper(messageOrBuilder);
                }
            };
            hashMap.put(BoolValue.getDescriptor().getFullName(), r1);
            hashMap.put(Int32Value.getDescriptor().getFullName(), r1);
            hashMap.put(UInt32Value.getDescriptor().getFullName(), r1);
            hashMap.put(Int64Value.getDescriptor().getFullName(), r1);
            hashMap.put(UInt64Value.getDescriptor().getFullName(), r1);
            hashMap.put(StringValue.getDescriptor().getFullName(), r1);
            hashMap.put(BytesValue.getDescriptor().getFullName(), r1);
            hashMap.put(FloatValue.getDescriptor().getFullName(), r1);
            hashMap.put(DoubleValue.getDescriptor().getFullName(), r1);
            hashMap.put(Timestamp.getDescriptor().getFullName(), new WellKnownTypePrinter() {
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printTimestamp(messageOrBuilder);
                }
            });
            hashMap.put(Duration.getDescriptor().getFullName(), new WellKnownTypePrinter() {
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printDuration(messageOrBuilder);
                }
            });
            hashMap.put(FieldMask.getDescriptor().getFullName(), new WellKnownTypePrinter() {
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printFieldMask(messageOrBuilder);
                }
            });
            hashMap.put(Struct.getDescriptor().getFullName(), new WellKnownTypePrinter() {
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printStruct(messageOrBuilder);
                }
            });
            hashMap.put(Value.getDescriptor().getFullName(), new WellKnownTypePrinter() {
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printValue(messageOrBuilder);
                }
            });
            hashMap.put(ListValue.getDescriptor().getFullName(), new WellKnownTypePrinter() {
                public void print(PrinterImpl printerImpl, MessageOrBuilder messageOrBuilder) throws IOException {
                    printerImpl.printListValue(messageOrBuilder);
                }
            });
            return hashMap;
        }

        /* access modifiers changed from: private */
        public void printAny(MessageOrBuilder messageOrBuilder) throws IOException {
            if (Any.getDefaultInstance().equals(messageOrBuilder)) {
                this.generator.print("{}");
                return;
            }
            Descriptor descriptorForType = messageOrBuilder.getDescriptorForType();
            FieldDescriptor findFieldByName = descriptorForType.findFieldByName("type_url");
            FieldDescriptor findFieldByName2 = descriptorForType.findFieldByName("value");
            if (findFieldByName == null || findFieldByName2 == null || findFieldByName.getType() != Type.STRING || findFieldByName2.getType() != Type.BYTES) {
                throw new InvalidProtocolBufferException("Invalid Any type.");
            }
            String str = (String) messageOrBuilder.getField(findFieldByName);
            String access$1700 = JsonFormat.getTypeName(str);
            Descriptor find = this.registry.find(access$1700);
            if (find != null) {
                Message message = (Message) DynamicMessage.getDefaultInstance(find).getParserForType().parseFrom((ByteString) messageOrBuilder.getField(findFieldByName2));
                WellKnownTypePrinter wellKnownTypePrinter = (WellKnownTypePrinter) wellKnownTypePrinters.get(access$1700);
                if (wellKnownTypePrinter != null) {
                    TextGenerator textGenerator = this.generator;
                    StringBuilder sb = new StringBuilder();
                    sb.append("{");
                    sb.append(this.blankOrNewLine);
                    textGenerator.print(sb.toString());
                    this.generator.indent();
                    TextGenerator textGenerator2 = this.generator;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\"@type\":");
                    sb2.append(this.blankOrSpace);
                    sb2.append(this.gson.toJson((Object) str));
                    sb2.append(",");
                    sb2.append(this.blankOrNewLine);
                    textGenerator2.print(sb2.toString());
                    TextGenerator textGenerator3 = this.generator;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("\"value\":");
                    sb3.append(this.blankOrSpace);
                    textGenerator3.print(sb3.toString());
                    wellKnownTypePrinter.print(this, message);
                    this.generator.print(this.blankOrNewLine);
                    this.generator.outdent();
                    this.generator.print("}");
                } else {
                    print(message, str);
                }
                return;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Cannot find type for url: ");
            sb4.append(str);
            throw new InvalidProtocolBufferException(sb4.toString());
        }

        /* access modifiers changed from: private */
        public void printWrapper(MessageOrBuilder messageOrBuilder) throws IOException {
            FieldDescriptor findFieldByName = messageOrBuilder.getDescriptorForType().findFieldByName("value");
            if (findFieldByName != null) {
                printSingleFieldValue(findFieldByName, messageOrBuilder.getField(findFieldByName));
                return;
            }
            throw new InvalidProtocolBufferException("Invalid Wrapper type.");
        }

        private ByteString toByteString(MessageOrBuilder messageOrBuilder) {
            if (messageOrBuilder instanceof Message) {
                return ((Message) messageOrBuilder).toByteString();
            }
            return ((Builder) messageOrBuilder).build().toByteString();
        }

        /* access modifiers changed from: private */
        public void printTimestamp(MessageOrBuilder messageOrBuilder) throws IOException {
            Timestamp parseFrom = Timestamp.parseFrom(toByteString(messageOrBuilder));
            TextGenerator textGenerator = this.generator;
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(Timestamps.toString(parseFrom));
            sb.append("\"");
            textGenerator.print(sb.toString());
        }

        /* access modifiers changed from: private */
        public void printDuration(MessageOrBuilder messageOrBuilder) throws IOException {
            Duration parseFrom = Duration.parseFrom(toByteString(messageOrBuilder));
            TextGenerator textGenerator = this.generator;
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(Durations.toString(parseFrom));
            sb.append("\"");
            textGenerator.print(sb.toString());
        }

        /* access modifiers changed from: private */
        public void printFieldMask(MessageOrBuilder messageOrBuilder) throws IOException {
            FieldMask parseFrom = FieldMask.parseFrom(toByteString(messageOrBuilder));
            TextGenerator textGenerator = this.generator;
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(FieldMaskUtil.toJsonString(parseFrom));
            sb.append("\"");
            textGenerator.print(sb.toString());
        }

        /* access modifiers changed from: private */
        public void printStruct(MessageOrBuilder messageOrBuilder) throws IOException {
            FieldDescriptor findFieldByName = messageOrBuilder.getDescriptorForType().findFieldByName(GraphRequest.FIELDS_PARAM);
            if (findFieldByName != null) {
                printMapFieldValue(findFieldByName, messageOrBuilder.getField(findFieldByName));
                return;
            }
            throw new InvalidProtocolBufferException("Invalid Struct type.");
        }

        /* access modifiers changed from: private */
        public void printValue(MessageOrBuilder messageOrBuilder) throws IOException {
            Map allFields = messageOrBuilder.getAllFields();
            if (allFields.isEmpty()) {
                this.generator.print("null");
            } else if (allFields.size() == 1) {
                for (Entry entry : allFields.entrySet()) {
                    printSingleFieldValue((FieldDescriptor) entry.getKey(), entry.getValue());
                }
            } else {
                throw new InvalidProtocolBufferException("Invalid Value type.");
            }
        }

        /* access modifiers changed from: private */
        public void printListValue(MessageOrBuilder messageOrBuilder) throws IOException {
            FieldDescriptor findFieldByName = messageOrBuilder.getDescriptorForType().findFieldByName("values");
            if (findFieldByName != null) {
                printRepeatedFieldValue(findFieldByName, messageOrBuilder.getField(findFieldByName));
                return;
            }
            throw new InvalidProtocolBufferException("Invalid ListValue type.");
        }

        private void print(MessageOrBuilder messageOrBuilder, String str) throws IOException {
            boolean z;
            Map map;
            TextGenerator textGenerator = this.generator;
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append(this.blankOrNewLine);
            textGenerator.print(sb.toString());
            this.generator.indent();
            if (str != null) {
                TextGenerator textGenerator2 = this.generator;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\"@type\":");
                sb2.append(this.blankOrSpace);
                sb2.append(this.gson.toJson((Object) str));
                textGenerator2.print(sb2.toString());
                z = true;
            } else {
                z = false;
            }
            if (this.alwaysOutputDefaultValueFields || !this.includingDefaultValueFields.isEmpty()) {
                Map treeMap = new TreeMap(messageOrBuilder.getAllFields());
                for (FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
                    if ((!fieldDescriptor.isOptional() || ((fieldDescriptor.getJavaType() != JavaType.MESSAGE || messageOrBuilder.hasField(fieldDescriptor)) && (fieldDescriptor.getContainingOneof() == null || messageOrBuilder.hasField(fieldDescriptor)))) && !treeMap.containsKey(fieldDescriptor)) {
                        if (this.alwaysOutputDefaultValueFields || this.includingDefaultValueFields.contains(fieldDescriptor)) {
                            treeMap.put(fieldDescriptor, messageOrBuilder.getField(fieldDescriptor));
                        }
                    }
                }
                map = treeMap;
            } else {
                map = messageOrBuilder.getAllFields();
            }
            for (Entry entry : map.entrySet()) {
                if (z) {
                    TextGenerator textGenerator3 = this.generator;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(",");
                    sb3.append(this.blankOrNewLine);
                    textGenerator3.print(sb3.toString());
                } else {
                    z = true;
                }
                printField((FieldDescriptor) entry.getKey(), entry.getValue());
            }
            if (z) {
                this.generator.print(this.blankOrNewLine);
            }
            this.generator.outdent();
            this.generator.print("}");
        }

        private void printField(FieldDescriptor fieldDescriptor, Object obj) throws IOException {
            if (this.preservingProtoFieldNames) {
                TextGenerator textGenerator = this.generator;
                StringBuilder sb = new StringBuilder();
                sb.append("\"");
                sb.append(fieldDescriptor.getName());
                sb.append("\":");
                sb.append(this.blankOrSpace);
                textGenerator.print(sb.toString());
            } else {
                TextGenerator textGenerator2 = this.generator;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("\"");
                sb2.append(fieldDescriptor.getJsonName());
                sb2.append("\":");
                sb2.append(this.blankOrSpace);
                textGenerator2.print(sb2.toString());
            }
            if (fieldDescriptor.isMapField()) {
                printMapFieldValue(fieldDescriptor, obj);
            } else if (fieldDescriptor.isRepeated()) {
                printRepeatedFieldValue(fieldDescriptor, obj);
            } else {
                printSingleFieldValue(fieldDescriptor, obj);
            }
        }

        private void printRepeatedFieldValue(FieldDescriptor fieldDescriptor, Object obj) throws IOException {
            this.generator.print("[");
            boolean z = false;
            for (Object next : (List) obj) {
                if (z) {
                    TextGenerator textGenerator = this.generator;
                    StringBuilder sb = new StringBuilder();
                    sb.append(",");
                    sb.append(this.blankOrSpace);
                    textGenerator.print(sb.toString());
                } else {
                    z = true;
                }
                printSingleFieldValue(fieldDescriptor, next);
            }
            this.generator.print("]");
        }

        private void printMapFieldValue(FieldDescriptor fieldDescriptor, Object obj) throws IOException {
            Descriptor messageType = fieldDescriptor.getMessageType();
            FieldDescriptor findFieldByName = messageType.findFieldByName(IpcUtil.KEY_CODE);
            FieldDescriptor findFieldByName2 = messageType.findFieldByName("value");
            if (findFieldByName == null || findFieldByName2 == null) {
                throw new InvalidProtocolBufferException("Invalid map field.");
            }
            TextGenerator textGenerator = this.generator;
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            sb.append(this.blankOrNewLine);
            textGenerator.print(sb.toString());
            this.generator.indent();
            boolean z = false;
            for (Message message : (List) obj) {
                Object field = message.getField(findFieldByName);
                Object field2 = message.getField(findFieldByName2);
                if (z) {
                    TextGenerator textGenerator2 = this.generator;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(",");
                    sb2.append(this.blankOrNewLine);
                    textGenerator2.print(sb2.toString());
                } else {
                    z = true;
                }
                printSingleFieldValue(findFieldByName, field, true);
                TextGenerator textGenerator3 = this.generator;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(":");
                sb3.append(this.blankOrSpace);
                textGenerator3.print(sb3.toString());
                printSingleFieldValue(findFieldByName2, field2);
            }
            if (z) {
                this.generator.print(this.blankOrNewLine);
            }
            this.generator.outdent();
            this.generator.print("}");
        }

        private void printSingleFieldValue(FieldDescriptor fieldDescriptor, Object obj) throws IOException {
            printSingleFieldValue(fieldDescriptor, obj, false);
        }

        private void printSingleFieldValue(FieldDescriptor fieldDescriptor, Object obj, boolean z) throws IOException {
            switch (fieldDescriptor.getType()) {
                case INT32:
                case SINT32:
                case SFIXED32:
                    if (z) {
                        this.generator.print("\"");
                    }
                    this.generator.print(((Integer) obj).toString());
                    if (z) {
                        this.generator.print("\"");
                        return;
                    }
                    return;
                case INT64:
                case SINT64:
                case SFIXED64:
                    TextGenerator textGenerator = this.generator;
                    StringBuilder sb = new StringBuilder();
                    sb.append("\"");
                    sb.append(((Long) obj).toString());
                    sb.append("\"");
                    textGenerator.print(sb.toString());
                    return;
                case BOOL:
                    if (z) {
                        this.generator.print("\"");
                    }
                    if (((Boolean) obj).booleanValue()) {
                        this.generator.print("true");
                    } else {
                        this.generator.print("false");
                    }
                    if (z) {
                        this.generator.print("\"");
                        return;
                    }
                    return;
                case FLOAT:
                    Float f = (Float) obj;
                    if (f.isNaN()) {
                        this.generator.print("\"NaN\"");
                        return;
                    } else if (!f.isInfinite()) {
                        if (z) {
                            this.generator.print("\"");
                        }
                        this.generator.print(f.toString());
                        if (z) {
                            this.generator.print("\"");
                            return;
                        }
                        return;
                    } else if (f.floatValue() < BitmapDescriptorFactory.HUE_RED) {
                        this.generator.print("\"-Infinity\"");
                        return;
                    } else {
                        this.generator.print("\"Infinity\"");
                        return;
                    }
                case DOUBLE:
                    Double d = (Double) obj;
                    if (d.isNaN()) {
                        this.generator.print("\"NaN\"");
                        return;
                    } else if (!d.isInfinite()) {
                        if (z) {
                            this.generator.print("\"");
                        }
                        this.generator.print(d.toString());
                        if (z) {
                            this.generator.print("\"");
                            return;
                        }
                        return;
                    } else if (d.doubleValue() < 0.0d) {
                        this.generator.print("\"-Infinity\"");
                        return;
                    } else {
                        this.generator.print("\"Infinity\"");
                        return;
                    }
                case UINT32:
                case FIXED32:
                    if (z) {
                        this.generator.print("\"");
                    }
                    this.generator.print(JsonFormat.unsignedToString(((Integer) obj).intValue()));
                    if (z) {
                        this.generator.print("\"");
                        return;
                    }
                    return;
                case UINT64:
                case FIXED64:
                    TextGenerator textGenerator2 = this.generator;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("\"");
                    sb2.append(JsonFormat.unsignedToString(((Long) obj).longValue()));
                    sb2.append("\"");
                    textGenerator2.print(sb2.toString());
                    return;
                case STRING:
                    this.generator.print(this.gson.toJson(obj));
                    return;
                case BYTES:
                    this.generator.print("\"");
                    this.generator.print(BaseEncoding.base64().encode(((ByteString) obj).toByteArray()));
                    this.generator.print("\"");
                    return;
                case ENUM:
                    if (fieldDescriptor.getEnumType().getFullName().equals("google.protobuf.NullValue")) {
                        if (z) {
                            this.generator.print("\"");
                        }
                        this.generator.print("null");
                        if (z) {
                            this.generator.print("\"");
                            return;
                        }
                        return;
                    }
                    EnumValueDescriptor enumValueDescriptor = (EnumValueDescriptor) obj;
                    if (enumValueDescriptor.getIndex() == -1) {
                        this.generator.print(String.valueOf(enumValueDescriptor.getNumber()));
                        return;
                    }
                    TextGenerator textGenerator3 = this.generator;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("\"");
                    sb3.append(enumValueDescriptor.getName());
                    sb3.append("\"");
                    textGenerator3.print(sb3.toString());
                    return;
                case MESSAGE:
                case GROUP:
                    print((Message) obj);
                    return;
                default:
                    return;
            }
        }
    }

    interface TextGenerator {
        void indent();

        void outdent();

        void print(CharSequence charSequence) throws IOException;
    }

    public static class TypeRegistry {
        private final Map<String, Descriptor> types;

        public static class Builder {
            private final Set<String> files;
            private Map<String, Descriptor> types;

            private Builder() {
                this.files = new HashSet();
                this.types = new HashMap();
            }

            public Builder add(Descriptor descriptor) {
                if (this.types != null) {
                    addFile(descriptor.getFile());
                    return this;
                }
                throw new IllegalStateException("A TypeRegistry.Builer can only be used once.");
            }

            public Builder add(Iterable<Descriptor> iterable) {
                if (this.types != null) {
                    for (Descriptor file : iterable) {
                        addFile(file.getFile());
                    }
                    return this;
                }
                throw new IllegalStateException("A TypeRegistry.Builer can only be used once.");
            }

            public TypeRegistry build() {
                TypeRegistry typeRegistry = new TypeRegistry(this.types);
                this.types = null;
                return typeRegistry;
            }

            private void addFile(FileDescriptor fileDescriptor) {
                if (this.files.add(fileDescriptor.getFullName())) {
                    for (FileDescriptor addFile : fileDescriptor.getDependencies()) {
                        addFile(addFile);
                    }
                    for (Descriptor addMessage : fileDescriptor.getMessageTypes()) {
                        addMessage(addMessage);
                    }
                }
            }

            private void addMessage(Descriptor descriptor) {
                for (Descriptor addMessage : descriptor.getNestedTypes()) {
                    addMessage(addMessage);
                }
                if (this.types.containsKey(descriptor.getFullName())) {
                    Logger access$500 = JsonFormat.logger;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Type ");
                    sb.append(descriptor.getFullName());
                    sb.append(" is added multiple times.");
                    access$500.warning(sb.toString());
                    return;
                }
                this.types.put(descriptor.getFullName(), descriptor);
            }
        }

        private static class EmptyTypeRegistryHolder {
            /* access modifiers changed from: private */
            public static final TypeRegistry EMPTY = new TypeRegistry(Collections.emptyMap());

            private EmptyTypeRegistryHolder() {
            }
        }

        public static TypeRegistry getEmptyTypeRegistry() {
            return EmptyTypeRegistryHolder.EMPTY;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Descriptor find(String str) {
            return (Descriptor) this.types.get(str);
        }

        private TypeRegistry(Map<String, Descriptor> map) {
            this.types = map;
        }
    }

    private JsonFormat() {
    }

    public static Printer printer() {
        Printer printer = new Printer(TypeRegistry.getEmptyTypeRegistry(), false, Collections.emptySet(), false, false);
        return printer;
    }

    public static Parser parser() {
        return new Parser(TypeRegistry.getEmptyTypeRegistry(), false, 100);
    }

    /* access modifiers changed from: private */
    public static String unsignedToString(int i) {
        if (i >= 0) {
            return Integer.toString(i);
        }
        return Long.toString(((long) i) & 4294967295L);
    }

    /* access modifiers changed from: private */
    public static String unsignedToString(long j) {
        if (j >= 0) {
            return Long.toString(j);
        }
        return BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }

    /* access modifiers changed from: private */
    public static String getTypeName(String str) throws InvalidProtocolBufferException {
        String[] split = str.split("/");
        if (split.length != 1) {
            return split[split.length - 1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid type url found: ");
        sb.append(str);
        throw new InvalidProtocolBufferException(sb.toString());
    }
}
