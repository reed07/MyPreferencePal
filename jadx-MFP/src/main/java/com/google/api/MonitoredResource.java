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

public final class MonitoredResource extends GeneratedMessageV3 implements MonitoredResourceOrBuilder {
    private static final MonitoredResource DEFAULT_INSTANCE = new MonitoredResource();
    public static final int LABELS_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<MonitoredResource> PARSER = new AbstractParser<MonitoredResource>() {
        public MonitoredResource parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MonitoredResource(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public MapField<String, String> labels_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object type_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MonitoredResourceOrBuilder {
        private int bitField0_;
        private MapField<String, String> labels_;
        private Object type_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResource_descriptor;
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetLabels();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableLabels();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResource_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResource.class, Builder.class);
        }

        private Builder() {
            this.type_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.type_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            MonitoredResource.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.type_ = "";
            internalGetMutableLabels().clear();
            return this;
        }

        public Descriptor getDescriptorForType() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResource_descriptor;
        }

        public MonitoredResource getDefaultInstanceForType() {
            return MonitoredResource.getDefaultInstance();
        }

        public MonitoredResource build() {
            MonitoredResource buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public MonitoredResource buildPartial() {
            MonitoredResource monitoredResource = new MonitoredResource((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            monitoredResource.type_ = this.type_;
            monitoredResource.labels_ = internalGetLabels();
            monitoredResource.labels_.makeImmutable();
            monitoredResource.bitField0_ = 0;
            onBuilt();
            return monitoredResource;
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
            if (message instanceof MonitoredResource) {
                return mergeFrom((MonitoredResource) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MonitoredResource monitoredResource) {
            if (monitoredResource == MonitoredResource.getDefaultInstance()) {
                return this;
            }
            if (!monitoredResource.getType().isEmpty()) {
                this.type_ = monitoredResource.type_;
                onChanged();
            }
            internalGetMutableLabels().mergeFrom(monitoredResource.internalGetLabels());
            mergeUnknownFields(monitoredResource.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.MonitoredResource.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MonitoredResource.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.MonitoredResource r3 = (com.google.api.MonitoredResource) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.MonitoredResource r4 = (com.google.api.MonitoredResource) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MonitoredResource.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MonitoredResource$Builder");
        }

        public String getType() {
            Object obj = this.type_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.type_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getTypeBytes() {
            Object obj = this.type_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.type_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setType(String str) {
            if (str != null) {
                this.type_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearType() {
            this.type_ = MonitoredResource.getDefaultInstance().getType();
            onChanged();
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResource.checkByteStringIsUtf8(byteString);
                this.type_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private MapField<String, String> internalGetLabels() {
            MapField<String, String> mapField = this.labels_;
            return mapField == null ? MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableLabels() {
            onChanged();
            if (this.labels_ == null) {
                this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.labels_.isMutable()) {
                this.labels_ = this.labels_.copy();
            }
            return this.labels_;
        }

        public int getLabelsCount() {
            return internalGetLabels().getMap().size();
        }

        public boolean containsLabels(String str) {
            if (str != null) {
                return internalGetLabels().getMap().containsKey(str);
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, String> getLabels() {
            return getLabelsMap();
        }

        public Map<String, String> getLabelsMap() {
            return internalGetLabels().getMap();
        }

        public String getLabelsOrDefault(String str, String str2) {
            if (str != null) {
                Map map = internalGetLabels().getMap();
                return map.containsKey(str) ? (String) map.get(str) : str2;
            }
            throw new NullPointerException();
        }

        public String getLabelsOrThrow(String str) {
            if (str != null) {
                Map map = internalGetLabels().getMap();
                if (map.containsKey(str)) {
                    return (String) map.get(str);
                }
                throw new IllegalArgumentException();
            }
            throw new NullPointerException();
        }

        public Builder clearLabels() {
            internalGetMutableLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeLabels(String str) {
            if (str != null) {
                internalGetMutableLabels().getMutableMap().remove(str);
                return this;
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, String> getMutableLabels() {
            return internalGetMutableLabels().getMutableMap();
        }

        public Builder putLabels(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            } else if (str2 != null) {
                internalGetMutableLabels().getMutableMap().put(str, str2);
                return this;
            } else {
                throw new NullPointerException();
            }
        }

        public Builder putAllLabels(Map<String, String> map) {
            internalGetMutableLabels().getMutableMap().putAll(map);
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private static final class LabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(MonitoredResourceProto.internal_static_google_api_MonitoredResource_LabelsEntry_descriptor, FieldType.STRING, "", FieldType.STRING, "");

        private LabelsDefaultEntryHolder() {
        }
    }

    private MonitoredResource(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private MonitoredResource() {
        this.memoizedIsInitialized = -1;
        this.type_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MonitoredResource(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.type_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.labels_ = MapField.newMapField(LabelsDefaultEntryHolder.defaultEntry);
                            z2 |= true;
                        }
                        MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(LabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                        this.labels_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
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
        return MonitoredResourceProto.internal_static_google_api_MonitoredResource_descriptor;
    }

    /* access modifiers changed from: protected */
    public MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetLabels();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid map field number: ");
        sb.append(i);
        throw new RuntimeException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResource_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResource.class, Builder.class);
    }

    public String getType() {
        Object obj = this.type_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.type_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getTypeBytes() {
        Object obj = this.type_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.type_ = copyFromUtf8;
        return copyFromUtf8;
    }

    /* access modifiers changed from: private */
    public MapField<String, String> internalGetLabels() {
        MapField<String, String> mapField = this.labels_;
        return mapField == null ? MapField.emptyMapField(LabelsDefaultEntryHolder.defaultEntry) : mapField;
    }

    public int getLabelsCount() {
        return internalGetLabels().getMap().size();
    }

    public boolean containsLabels(String str) {
        if (str != null) {
            return internalGetLabels().getMap().containsKey(str);
        }
        throw new NullPointerException();
    }

    @Deprecated
    public Map<String, String> getLabels() {
        return getLabelsMap();
    }

    public Map<String, String> getLabelsMap() {
        return internalGetLabels().getMap();
    }

    public String getLabelsOrDefault(String str, String str2) {
        if (str != null) {
            Map map = internalGetLabels().getMap();
            return map.containsKey(str) ? (String) map.get(str) : str2;
        }
        throw new NullPointerException();
    }

    public String getLabelsOrThrow(String str) {
        if (str != null) {
            Map map = internalGetLabels().getMap();
            if (map.containsKey(str)) {
                return (String) map.get(str);
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
        if (!getTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetLabels(), LabelsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!getTypeBytes().isEmpty()) {
            i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.type_);
        }
        for (Entry entry : internalGetLabels().getMap().entrySet()) {
            i2 += CodedOutputStream.computeMessageSize(2, LabelsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
        if (!(obj instanceof MonitoredResource)) {
            return super.equals(obj);
        }
        MonitoredResource monitoredResource = (MonitoredResource) obj;
        if (!((getType().equals(monitoredResource.getType())) && internalGetLabels().equals(monitoredResource.internalGetLabels())) || !this.unknownFields.equals(monitoredResource.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType().hashCode();
        if (!internalGetLabels().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 2) * 53) + internalGetLabels().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static MonitoredResource parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MonitoredResource) PARSER.parseFrom(byteBuffer);
    }

    public static MonitoredResource parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResource) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MonitoredResource) PARSER.parseFrom(byteString);
    }

    public static MonitoredResource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResource) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MonitoredResource) PARSER.parseFrom(bArr);
    }

    public static MonitoredResource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResource) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(InputStream inputStream) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MonitoredResource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResource parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MonitoredResource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResource parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MonitoredResource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResource) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MonitoredResource monitoredResource) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoredResource);
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

    public static MonitoredResource getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResource> parser() {
        return PARSER;
    }

    public Parser<MonitoredResource> getParserForType() {
        return PARSER;
    }

    public MonitoredResource getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
