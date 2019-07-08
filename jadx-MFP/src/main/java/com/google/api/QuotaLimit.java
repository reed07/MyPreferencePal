package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Map.Entry;

public final class QuotaLimit extends GeneratedMessageV3 implements QuotaLimitOrBuilder {
    private static final QuotaLimit DEFAULT_INSTANCE = new QuotaLimit();
    public static final int DEFAULT_LIMIT_FIELD_NUMBER = 3;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 12;
    public static final int DURATION_FIELD_NUMBER = 5;
    public static final int FREE_TIER_FIELD_NUMBER = 7;
    public static final int MAX_LIMIT_FIELD_NUMBER = 4;
    public static final int METRIC_FIELD_NUMBER = 8;
    public static final int NAME_FIELD_NUMBER = 6;
    /* access modifiers changed from: private */
    public static final Parser<QuotaLimit> PARSER = new AbstractParser<QuotaLimit>() {
        public QuotaLimit parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new QuotaLimit(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int UNIT_FIELD_NUMBER = 9;
    public static final int VALUES_FIELD_NUMBER = 10;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public long defaultLimit_;
    /* access modifiers changed from: private */
    public volatile Object description_;
    /* access modifiers changed from: private */
    public volatile Object displayName_;
    /* access modifiers changed from: private */
    public volatile Object duration_;
    /* access modifiers changed from: private */
    public long freeTier_;
    /* access modifiers changed from: private */
    public long maxLimit_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object metric_;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public volatile Object unit_;
    /* access modifiers changed from: private */
    public MapField<String, Long> values_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements QuotaLimitOrBuilder {
        private int bitField0_;
        private long defaultLimit_;
        private Object description_;
        private Object displayName_;
        private Object duration_;
        private long freeTier_;
        private long maxLimit_;
        private Object metric_;
        private Object name_;
        private Object unit_;
        private MapField<String, Long> values_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return QuotaProto.internal_static_google_api_QuotaLimit_descriptor;
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMapField(int i) {
            if (i == 10) {
                return internalGetValues();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMutableMapField(int i) {
            if (i == 10) {
                return internalGetMutableValues();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return QuotaProto.internal_static_google_api_QuotaLimit_fieldAccessorTable.ensureFieldAccessorsInitialized(QuotaLimit.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.description_ = "";
            this.duration_ = "";
            this.metric_ = "";
            this.unit_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.description_ = "";
            this.duration_ = "";
            this.metric_ = "";
            this.unit_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            QuotaLimit.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.description_ = "";
            this.defaultLimit_ = 0;
            this.maxLimit_ = 0;
            this.freeTier_ = 0;
            this.duration_ = "";
            this.metric_ = "";
            this.unit_ = "";
            internalGetMutableValues().clear();
            this.displayName_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return QuotaProto.internal_static_google_api_QuotaLimit_descriptor;
        }

        public QuotaLimit getDefaultInstanceForType() {
            return QuotaLimit.getDefaultInstance();
        }

        public QuotaLimit build() {
            QuotaLimit buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public QuotaLimit buildPartial() {
            QuotaLimit quotaLimit = new QuotaLimit((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            quotaLimit.name_ = this.name_;
            quotaLimit.description_ = this.description_;
            quotaLimit.defaultLimit_ = this.defaultLimit_;
            quotaLimit.maxLimit_ = this.maxLimit_;
            quotaLimit.freeTier_ = this.freeTier_;
            quotaLimit.duration_ = this.duration_;
            quotaLimit.metric_ = this.metric_;
            quotaLimit.unit_ = this.unit_;
            quotaLimit.values_ = internalGetValues();
            quotaLimit.values_.makeImmutable();
            quotaLimit.displayName_ = this.displayName_;
            quotaLimit.bitField0_ = 0;
            onBuilt();
            return quotaLimit;
        }

        public Builder clone() {
            return (Builder) super.clone();
        }

        public Builder setField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        public Builder clearField(FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        public Builder clearOneof(OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        public Builder setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        public Builder addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        public Builder mergeFrom(Message message) {
            if (message instanceof QuotaLimit) {
                return mergeFrom((QuotaLimit) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(QuotaLimit quotaLimit) {
            if (quotaLimit == QuotaLimit.getDefaultInstance()) {
                return this;
            }
            if (!quotaLimit.getName().isEmpty()) {
                this.name_ = quotaLimit.name_;
                onChanged();
            }
            if (!quotaLimit.getDescription().isEmpty()) {
                this.description_ = quotaLimit.description_;
                onChanged();
            }
            if (quotaLimit.getDefaultLimit() != 0) {
                setDefaultLimit(quotaLimit.getDefaultLimit());
            }
            if (quotaLimit.getMaxLimit() != 0) {
                setMaxLimit(quotaLimit.getMaxLimit());
            }
            if (quotaLimit.getFreeTier() != 0) {
                setFreeTier(quotaLimit.getFreeTier());
            }
            if (!quotaLimit.getDuration().isEmpty()) {
                this.duration_ = quotaLimit.duration_;
                onChanged();
            }
            if (!quotaLimit.getMetric().isEmpty()) {
                this.metric_ = quotaLimit.metric_;
                onChanged();
            }
            if (!quotaLimit.getUnit().isEmpty()) {
                this.unit_ = quotaLimit.unit_;
                onChanged();
            }
            internalGetMutableValues().mergeFrom(quotaLimit.internalGetValues());
            if (!quotaLimit.getDisplayName().isEmpty()) {
                this.displayName_ = quotaLimit.displayName_;
                onChanged();
            }
            mergeUnknownFields(quotaLimit.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.QuotaLimit.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.QuotaLimit.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.QuotaLimit r3 = (com.google.api.QuotaLimit) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                if (r3 == 0) goto L_0x0010
                r2.mergeFrom(r3)
            L_0x0010:
                return r2
            L_0x0011:
                r3 = move-exception
                goto L_0x0021
            L_0x0013:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch:{ all -> 0x0011 }
                com.google.api.QuotaLimit r4 = (com.google.api.QuotaLimit) r4     // Catch:{ all -> 0x0011 }
                java.io.IOException r3 = r3.unwrapIOException()     // Catch:{ all -> 0x001f }
                throw r3     // Catch:{ all -> 0x001f }
            L_0x001f:
                r3 = move-exception
                r0 = r4
            L_0x0021:
                if (r0 == 0) goto L_0x0026
                r2.mergeFrom(r0)
            L_0x0026:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.QuotaLimit.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.QuotaLimit$Builder");
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearName() {
            this.name_ = QuotaLimit.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getDescription() {
            Object obj = this.description_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.description_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDescriptionBytes() {
            Object obj = this.description_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.description_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDescription(String str) {
            if (str != null) {
                this.description_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDescription() {
            this.description_ = QuotaLimit.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public long getDefaultLimit() {
            return this.defaultLimit_;
        }

        public Builder setDefaultLimit(long j) {
            this.defaultLimit_ = j;
            onChanged();
            return this;
        }

        public Builder clearDefaultLimit() {
            this.defaultLimit_ = 0;
            onChanged();
            return this;
        }

        public long getMaxLimit() {
            return this.maxLimit_;
        }

        public Builder setMaxLimit(long j) {
            this.maxLimit_ = j;
            onChanged();
            return this;
        }

        public Builder clearMaxLimit() {
            this.maxLimit_ = 0;
            onChanged();
            return this;
        }

        public long getFreeTier() {
            return this.freeTier_;
        }

        public Builder setFreeTier(long j) {
            this.freeTier_ = j;
            onChanged();
            return this;
        }

        public Builder clearFreeTier() {
            this.freeTier_ = 0;
            onChanged();
            return this;
        }

        public String getDuration() {
            Object obj = this.duration_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.duration_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDurationBytes() {
            Object obj = this.duration_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.duration_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDuration(String str) {
            if (str != null) {
                this.duration_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDuration() {
            this.duration_ = QuotaLimit.getDefaultInstance().getDuration();
            onChanged();
            return this;
        }

        public Builder setDurationBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.duration_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getMetric() {
            Object obj = this.metric_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.metric_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getMetricBytes() {
            Object obj = this.metric_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.metric_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setMetric(String str) {
            if (str != null) {
                this.metric_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearMetric() {
            this.metric_ = QuotaLimit.getDefaultInstance().getMetric();
            onChanged();
            return this;
        }

        public Builder setMetricBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.metric_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getUnit() {
            Object obj = this.unit_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.unit_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getUnitBytes() {
            Object obj = this.unit_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.unit_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setUnit(String str) {
            if (str != null) {
                this.unit_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearUnit() {
            this.unit_ = QuotaLimit.getDefaultInstance().getUnit();
            onChanged();
            return this;
        }

        public Builder setUnitBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.unit_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private MapField<String, Long> internalGetValues() {
            MapField<String, Long> mapField = this.values_;
            return mapField == null ? MapField.emptyMapField(ValuesDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Long> internalGetMutableValues() {
            onChanged();
            if (this.values_ == null) {
                this.values_ = MapField.newMapField(ValuesDefaultEntryHolder.defaultEntry);
            }
            if (!this.values_.isMutable()) {
                this.values_ = this.values_.copy();
            }
            return this.values_;
        }

        public int getValuesCount() {
            return internalGetValues().getMap().size();
        }

        public boolean containsValues(String str) {
            if (str != null) {
                return internalGetValues().getMap().containsKey(str);
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, Long> getValues() {
            return getValuesMap();
        }

        public Map<String, Long> getValuesMap() {
            return internalGetValues().getMap();
        }

        public long getValuesOrDefault(String str, long j) {
            if (str != null) {
                Map map = internalGetValues().getMap();
                return map.containsKey(str) ? ((Long) map.get(str)).longValue() : j;
            }
            throw new NullPointerException();
        }

        public long getValuesOrThrow(String str) {
            if (str != null) {
                Map map = internalGetValues().getMap();
                if (map.containsKey(str)) {
                    return ((Long) map.get(str)).longValue();
                }
                throw new IllegalArgumentException();
            }
            throw new NullPointerException();
        }

        public Builder clearValues() {
            internalGetMutableValues().getMutableMap().clear();
            return this;
        }

        public Builder removeValues(String str) {
            if (str != null) {
                internalGetMutableValues().getMutableMap().remove(str);
                return this;
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, Long> getMutableValues() {
            return internalGetMutableValues().getMutableMap();
        }

        public Builder putValues(String str, long j) {
            if (str != null) {
                internalGetMutableValues().getMutableMap().put(str, Long.valueOf(j));
                return this;
            }
            throw new NullPointerException();
        }

        public Builder putAllValues(Map<String, Long> map) {
            internalGetMutableValues().getMutableMap().putAll(map);
            return this;
        }

        public String getDisplayName() {
            Object obj = this.displayName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.displayName_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDisplayNameBytes() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDisplayName(String str) {
            if (str != null) {
                this.displayName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDisplayName() {
            this.displayName_ = QuotaLimit.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                QuotaLimit.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private static final class ValuesDefaultEntryHolder {
        static final MapEntry<String, Long> defaultEntry = MapEntry.newDefaultInstance(QuotaProto.internal_static_google_api_QuotaLimit_ValuesEntry_descriptor, FieldType.STRING, "", FieldType.INT64, Long.valueOf(0));

        private ValuesDefaultEntryHolder() {
        }
    }

    private QuotaLimit(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private QuotaLimit() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.description_ = "";
        this.defaultLimit_ = 0;
        this.maxLimit_ = 0;
        this.freeTier_ = 0;
        this.duration_ = "";
        this.metric_ = "";
        this.unit_ = "";
        this.displayName_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private QuotaLimit(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 18:
                            this.description_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 24:
                            this.defaultLimit_ = codedInputStream.readInt64();
                            break;
                        case 32:
                            this.maxLimit_ = codedInputStream.readInt64();
                            break;
                        case 42:
                            this.duration_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 50:
                            this.name_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 56:
                            this.freeTier_ = codedInputStream.readInt64();
                            break;
                        case 66:
                            this.metric_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 74:
                            this.unit_ = codedInputStream.readStringRequireUtf8();
                            break;
                        case 82:
                            if (!(z2 & true)) {
                                this.values_ = MapField.newMapField(ValuesDefaultEntryHolder.defaultEntry);
                                z2 |= true;
                            }
                            MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(ValuesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.values_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                            break;
                        case 98:
                            this.displayName_ = codedInputStream.readStringRequireUtf8();
                            break;
                        default:
                            if (parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                break;
                            } else {
                                z = true;
                                break;
                            }
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return QuotaProto.internal_static_google_api_QuotaLimit_descriptor;
    }

    /* access modifiers changed from: protected */
    public MapField internalGetMapField(int i) {
        if (i == 10) {
            return internalGetValues();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid map field number: ");
        sb.append(i);
        throw new RuntimeException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return QuotaProto.internal_static_google_api_QuotaLimit_fieldAccessorTable.ensureFieldAccessorsInitialized(QuotaLimit.class, Builder.class);
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getDescription() {
        Object obj = this.description_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.description_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDescriptionBytes() {
        Object obj = this.description_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.description_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public long getDefaultLimit() {
        return this.defaultLimit_;
    }

    public long getMaxLimit() {
        return this.maxLimit_;
    }

    public long getFreeTier() {
        return this.freeTier_;
    }

    public String getDuration() {
        Object obj = this.duration_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.duration_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDurationBytes() {
        Object obj = this.duration_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.duration_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getMetric() {
        Object obj = this.metric_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.metric_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getMetricBytes() {
        Object obj = this.metric_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.metric_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getUnit() {
        Object obj = this.unit_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.unit_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getUnitBytes() {
        Object obj = this.unit_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.unit_ = copyFromUtf8;
        return copyFromUtf8;
    }

    /* access modifiers changed from: private */
    public MapField<String, Long> internalGetValues() {
        MapField<String, Long> mapField = this.values_;
        return mapField == null ? MapField.emptyMapField(ValuesDefaultEntryHolder.defaultEntry) : mapField;
    }

    public int getValuesCount() {
        return internalGetValues().getMap().size();
    }

    public boolean containsValues(String str) {
        if (str != null) {
            return internalGetValues().getMap().containsKey(str);
        }
        throw new NullPointerException();
    }

    @Deprecated
    public Map<String, Long> getValues() {
        return getValuesMap();
    }

    public Map<String, Long> getValuesMap() {
        return internalGetValues().getMap();
    }

    public long getValuesOrDefault(String str, long j) {
        if (str != null) {
            Map map = internalGetValues().getMap();
            return map.containsKey(str) ? ((Long) map.get(str)).longValue() : j;
        }
        throw new NullPointerException();
    }

    public long getValuesOrThrow(String str) {
        if (str != null) {
            Map map = internalGetValues().getMap();
            if (map.containsKey(str)) {
                return ((Long) map.get(str)).longValue();
            }
            throw new IllegalArgumentException();
        }
        throw new NullPointerException();
    }

    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.displayName_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = 1;
        return true;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.description_);
        }
        long j = this.defaultLimit_;
        if (j != 0) {
            codedOutputStream.writeInt64(3, j);
        }
        long j2 = this.maxLimit_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(4, j2);
        }
        if (!getDurationBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.duration_);
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.name_);
        }
        long j3 = this.freeTier_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(7, j3);
        }
        if (!getMetricBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.metric_);
        }
        if (!getUnitBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 9, this.unit_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetValues(), ValuesDefaultEntryHolder.defaultEntry, 10);
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 12, this.displayName_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getDescriptionBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(2, this.description_);
        }
        long j = this.defaultLimit_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(3, j);
        }
        long j2 = this.maxLimit_;
        if (j2 != 0) {
            i2 += CodedOutputStream.computeInt64Size(4, j2);
        }
        if (!getDurationBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(5, this.duration_);
        }
        if (!getNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(6, this.name_);
        }
        long j3 = this.freeTier_;
        if (j3 != 0) {
            i2 += CodedOutputStream.computeInt64Size(7, j3);
        }
        if (!getMetricBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(8, this.metric_);
        }
        if (!getUnitBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(9, this.unit_);
        }
        for (Entry entry : internalGetValues().getMap().entrySet()) {
            i2 += CodedOutputStream.computeMessageSize(10, ValuesDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        if (!getDisplayNameBytes().isEmpty()) {
            i2 += GeneratedMessageV3.computeStringSize(12, this.displayName_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QuotaLimit)) {
            return super.equals(obj);
        }
        QuotaLimit quotaLimit = (QuotaLimit) obj;
        if (!((((((((((getName().equals(quotaLimit.getName())) && getDescription().equals(quotaLimit.getDescription())) && (getDefaultLimit() > quotaLimit.getDefaultLimit() ? 1 : (getDefaultLimit() == quotaLimit.getDefaultLimit() ? 0 : -1)) == 0) && (getMaxLimit() > quotaLimit.getMaxLimit() ? 1 : (getMaxLimit() == quotaLimit.getMaxLimit() ? 0 : -1)) == 0) && (getFreeTier() > quotaLimit.getFreeTier() ? 1 : (getFreeTier() == quotaLimit.getFreeTier() ? 0 : -1)) == 0) && getDuration().equals(quotaLimit.getDuration())) && getMetric().equals(quotaLimit.getMetric())) && getUnit().equals(quotaLimit.getUnit())) && internalGetValues().equals(quotaLimit.internalGetValues())) && getDisplayName().equals(quotaLimit.getDisplayName())) || !this.unknownFields.equals(quotaLimit.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 6) * 53) + getName().hashCode()) * 37) + 2) * 53) + getDescription().hashCode()) * 37) + 3) * 53) + Internal.hashLong(getDefaultLimit())) * 37) + 4) * 53) + Internal.hashLong(getMaxLimit())) * 37) + 7) * 53) + Internal.hashLong(getFreeTier())) * 37) + 5) * 53) + getDuration().hashCode()) * 37) + 8) * 53) + getMetric().hashCode()) * 37) + 9) * 53) + getUnit().hashCode();
        if (!internalGetValues().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 10) * 53) + internalGetValues().hashCode();
        }
        int hashCode2 = (((((hashCode * 37) + 12) * 53) + getDisplayName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static QuotaLimit parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (QuotaLimit) PARSER.parseFrom(byteBuffer);
    }

    public static QuotaLimit parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (QuotaLimit) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (QuotaLimit) PARSER.parseFrom(byteString);
    }

    public static QuotaLimit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (QuotaLimit) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (QuotaLimit) PARSER.parseFrom(bArr);
    }

    public static QuotaLimit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (QuotaLimit) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(InputStream inputStream) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static QuotaLimit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QuotaLimit parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static QuotaLimit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static QuotaLimit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(QuotaLimit quotaLimit) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(quotaLimit);
    }

    public Builder toBuilder() {
        if (this == DEFAULT_INSTANCE) {
            return new Builder();
        }
        return new Builder().mergeFrom(this);
    }

    /* access modifiers changed from: protected */
    public Builder newBuilderForType(BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static QuotaLimit getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<QuotaLimit> parser() {
        return PARSER;
    }

    public Parser<QuotaLimit> getParserForType() {
        return PARSER;
    }

    public QuotaLimit getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
