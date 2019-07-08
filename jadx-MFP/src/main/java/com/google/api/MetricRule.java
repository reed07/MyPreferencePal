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

public final class MetricRule extends GeneratedMessageV3 implements MetricRuleOrBuilder {
    private static final MetricRule DEFAULT_INSTANCE = new MetricRule();
    public static final int METRIC_COSTS_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<MetricRule> PARSER = new AbstractParser<MetricRule>() {
        public MetricRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetricRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public MapField<String, Long> metricCosts_;
    /* access modifiers changed from: private */
    public volatile Object selector_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MetricRuleOrBuilder {
        private int bitField0_;
        private MapField<String, Long> metricCosts_;
        private Object selector_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return QuotaProto.internal_static_google_api_MetricRule_descriptor;
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetMetricCosts();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableMetricCosts();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            MetricRule.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.selector_ = "";
            internalGetMutableMetricCosts().clear();
            return this;
        }

        public Descriptor getDescriptorForType() {
            return QuotaProto.internal_static_google_api_MetricRule_descriptor;
        }

        public MetricRule getDefaultInstanceForType() {
            return MetricRule.getDefaultInstance();
        }

        public MetricRule build() {
            MetricRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public MetricRule buildPartial() {
            MetricRule metricRule = new MetricRule((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            metricRule.selector_ = this.selector_;
            metricRule.metricCosts_ = internalGetMetricCosts();
            metricRule.metricCosts_.makeImmutable();
            metricRule.bitField0_ = 0;
            onBuilt();
            return metricRule;
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
            if (message instanceof MetricRule) {
                return mergeFrom((MetricRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetricRule metricRule) {
            if (metricRule == MetricRule.getDefaultInstance()) {
                return this;
            }
            if (!metricRule.getSelector().isEmpty()) {
                this.selector_ = metricRule.selector_;
                onChanged();
            }
            internalGetMutableMetricCosts().mergeFrom(metricRule.internalGetMetricCosts());
            mergeUnknownFields(metricRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.MetricRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MetricRule.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.MetricRule r3 = (com.google.api.MetricRule) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.MetricRule r4 = (com.google.api.MetricRule) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MetricRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MetricRule$Builder");
        }

        public String getSelector() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.selector_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setSelector(String str) {
            if (str != null) {
                this.selector_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearSelector() {
            this.selector_ = MetricRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                MetricRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private MapField<String, Long> internalGetMetricCosts() {
            MapField<String, Long> mapField = this.metricCosts_;
            return mapField == null ? MapField.emptyMapField(MetricCostsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Long> internalGetMutableMetricCosts() {
            onChanged();
            if (this.metricCosts_ == null) {
                this.metricCosts_ = MapField.newMapField(MetricCostsDefaultEntryHolder.defaultEntry);
            }
            if (!this.metricCosts_.isMutable()) {
                this.metricCosts_ = this.metricCosts_.copy();
            }
            return this.metricCosts_;
        }

        public int getMetricCostsCount() {
            return internalGetMetricCosts().getMap().size();
        }

        public boolean containsMetricCosts(String str) {
            if (str != null) {
                return internalGetMetricCosts().getMap().containsKey(str);
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, Long> getMetricCosts() {
            return getMetricCostsMap();
        }

        public Map<String, Long> getMetricCostsMap() {
            return internalGetMetricCosts().getMap();
        }

        public long getMetricCostsOrDefault(String str, long j) {
            if (str != null) {
                Map map = internalGetMetricCosts().getMap();
                return map.containsKey(str) ? ((Long) map.get(str)).longValue() : j;
            }
            throw new NullPointerException();
        }

        public long getMetricCostsOrThrow(String str) {
            if (str != null) {
                Map map = internalGetMetricCosts().getMap();
                if (map.containsKey(str)) {
                    return ((Long) map.get(str)).longValue();
                }
                throw new IllegalArgumentException();
            }
            throw new NullPointerException();
        }

        public Builder clearMetricCosts() {
            internalGetMutableMetricCosts().getMutableMap().clear();
            return this;
        }

        public Builder removeMetricCosts(String str) {
            if (str != null) {
                internalGetMutableMetricCosts().getMutableMap().remove(str);
                return this;
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, Long> getMutableMetricCosts() {
            return internalGetMutableMetricCosts().getMutableMap();
        }

        public Builder putMetricCosts(String str, long j) {
            if (str != null) {
                internalGetMutableMetricCosts().getMutableMap().put(str, Long.valueOf(j));
                return this;
            }
            throw new NullPointerException();
        }

        public Builder putAllMetricCosts(Map<String, Long> map) {
            internalGetMutableMetricCosts().getMutableMap().putAll(map);
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private static final class MetricCostsDefaultEntryHolder {
        static final MapEntry<String, Long> defaultEntry = MapEntry.newDefaultInstance(QuotaProto.internal_static_google_api_MetricRule_MetricCostsEntry_descriptor, FieldType.STRING, "", FieldType.INT64, Long.valueOf(0));

        private MetricCostsDefaultEntryHolder() {
        }
    }

    private MetricRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private MetricRule() {
        this.memoizedIsInitialized = -1;
        this.selector_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MetricRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 10) {
                        this.selector_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.metricCosts_ = MapField.newMapField(MetricCostsDefaultEntryHolder.defaultEntry);
                            z2 |= true;
                        }
                        MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(MetricCostsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                        this.metricCosts_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
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
        return QuotaProto.internal_static_google_api_MetricRule_descriptor;
    }

    /* access modifiers changed from: protected */
    public MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetMetricCosts();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid map field number: ");
        sb.append(i);
        throw new RuntimeException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return QuotaProto.internal_static_google_api_MetricRule_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricRule.class, Builder.class);
    }

    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.selector_ = copyFromUtf8;
        return copyFromUtf8;
    }

    /* access modifiers changed from: private */
    public MapField<String, Long> internalGetMetricCosts() {
        MapField<String, Long> mapField = this.metricCosts_;
        return mapField == null ? MapField.emptyMapField(MetricCostsDefaultEntryHolder.defaultEntry) : mapField;
    }

    public int getMetricCostsCount() {
        return internalGetMetricCosts().getMap().size();
    }

    public boolean containsMetricCosts(String str) {
        if (str != null) {
            return internalGetMetricCosts().getMap().containsKey(str);
        }
        throw new NullPointerException();
    }

    @Deprecated
    public Map<String, Long> getMetricCosts() {
        return getMetricCostsMap();
    }

    public Map<String, Long> getMetricCostsMap() {
        return internalGetMetricCosts().getMap();
    }

    public long getMetricCostsOrDefault(String str, long j) {
        if (str != null) {
            Map map = internalGetMetricCosts().getMap();
            return map.containsKey(str) ? ((Long) map.get(str)).longValue() : j;
        }
        throw new NullPointerException();
    }

    public long getMetricCostsOrThrow(String str) {
        if (str != null) {
            Map map = internalGetMetricCosts().getMap();
            if (map.containsKey(str)) {
                return ((Long) map.get(str)).longValue();
            }
            throw new IllegalArgumentException();
        }
        throw new NullPointerException();
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
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetMetricCosts(), MetricCostsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getSelectorBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.selector_);
        }
        for (Entry entry : internalGetMetricCosts().getMap().entrySet()) {
            i2 += CodedOutputStream.computeMessageSize(2, MetricCostsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
        if (!(obj instanceof MetricRule)) {
            return super.equals(obj);
        }
        MetricRule metricRule = (MetricRule) obj;
        if (!((getSelector().equals(metricRule.getSelector())) && internalGetMetricCosts().equals(metricRule.internalGetMetricCosts())) || !this.unknownFields.equals(metricRule.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (!internalGetMetricCosts().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 2) * 53) + internalGetMetricCosts().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static MetricRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(byteBuffer);
    }

    public static MetricRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MetricRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(byteString);
    }

    public static MetricRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MetricRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(bArr);
    }

    public static MetricRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MetricRule parseFrom(InputStream inputStream) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MetricRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MetricRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MetricRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MetricRule metricRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(metricRule);
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

    public static MetricRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricRule> parser() {
        return PARSER;
    }

    public Parser<MetricRule> getParserForType() {
        return PARSER;
    }

    public MetricRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
