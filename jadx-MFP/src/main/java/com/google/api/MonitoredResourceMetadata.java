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
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Map.Entry;

public final class MonitoredResourceMetadata extends GeneratedMessageV3 implements MonitoredResourceMetadataOrBuilder {
    private static final MonitoredResourceMetadata DEFAULT_INSTANCE = new MonitoredResourceMetadata();
    /* access modifiers changed from: private */
    public static final Parser<MonitoredResourceMetadata> PARSER = new AbstractParser<MonitoredResourceMetadata>() {
        public MonitoredResourceMetadata parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MonitoredResourceMetadata(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int SYSTEM_LABELS_FIELD_NUMBER = 1;
    public static final int USER_LABELS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public Struct systemLabels_;
    /* access modifiers changed from: private */
    public MapField<String, String> userLabels_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MonitoredResourceMetadataOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> systemLabelsBuilder_;
        private Struct systemLabels_;
        private MapField<String, String> userLabels_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetUserLabels();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutableUserLabels();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid map field number: ");
            sb.append(i);
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceMetadata.class, Builder.class);
        }

        private Builder() {
            this.systemLabels_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.systemLabels_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            MonitoredResourceMetadata.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = null;
            } else {
                this.systemLabels_ = null;
                this.systemLabelsBuilder_ = null;
            }
            internalGetMutableUserLabels().clear();
            return this;
        }

        public Descriptor getDescriptorForType() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
        }

        public MonitoredResourceMetadata getDefaultInstanceForType() {
            return MonitoredResourceMetadata.getDefaultInstance();
        }

        public MonitoredResourceMetadata build() {
            MonitoredResourceMetadata buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public MonitoredResourceMetadata buildPartial() {
            MonitoredResourceMetadata monitoredResourceMetadata = new MonitoredResourceMetadata((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                monitoredResourceMetadata.systemLabels_ = this.systemLabels_;
            } else {
                monitoredResourceMetadata.systemLabels_ = (Struct) singleFieldBuilderV3.build();
            }
            monitoredResourceMetadata.userLabels_ = internalGetUserLabels();
            monitoredResourceMetadata.userLabels_.makeImmutable();
            monitoredResourceMetadata.bitField0_ = 0;
            onBuilt();
            return monitoredResourceMetadata;
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
            if (message instanceof MonitoredResourceMetadata) {
                return mergeFrom((MonitoredResourceMetadata) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MonitoredResourceMetadata monitoredResourceMetadata) {
            if (monitoredResourceMetadata == MonitoredResourceMetadata.getDefaultInstance()) {
                return this;
            }
            if (monitoredResourceMetadata.hasSystemLabels()) {
                mergeSystemLabels(monitoredResourceMetadata.getSystemLabels());
            }
            internalGetMutableUserLabels().mergeFrom(monitoredResourceMetadata.internalGetUserLabels());
            mergeUnknownFields(monitoredResourceMetadata.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.MonitoredResourceMetadata.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MonitoredResourceMetadata.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.MonitoredResourceMetadata r3 = (com.google.api.MonitoredResourceMetadata) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.MonitoredResourceMetadata r4 = (com.google.api.MonitoredResourceMetadata) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MonitoredResourceMetadata.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MonitoredResourceMetadata$Builder");
        }

        public boolean hasSystemLabels() {
            return (this.systemLabelsBuilder_ == null && this.systemLabels_ == null) ? false : true;
        }

        public Struct getSystemLabels() {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (Struct) singleFieldBuilderV3.getMessage();
            }
            Struct struct = this.systemLabels_;
            if (struct == null) {
                struct = Struct.getDefaultInstance();
            }
            return struct;
        }

        public Builder setSystemLabels(Struct struct) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(struct);
            } else if (struct != null) {
                this.systemLabels_ = struct;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setSystemLabels(com.google.protobuf.Struct.Builder builder) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.systemLabels_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeSystemLabels(Struct struct) {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 == null) {
                Struct struct2 = this.systemLabels_;
                if (struct2 != null) {
                    this.systemLabels_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                } else {
                    this.systemLabels_ = struct;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(struct);
            }
            return this;
        }

        public Builder clearSystemLabels() {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabels_ = null;
                onChanged();
            } else {
                this.systemLabels_ = null;
                this.systemLabelsBuilder_ = null;
            }
            return this;
        }

        public com.google.protobuf.Struct.Builder getSystemLabelsBuilder() {
            onChanged();
            return (com.google.protobuf.Struct.Builder) getSystemLabelsFieldBuilder().getBuilder();
        }

        public StructOrBuilder getSystemLabelsOrBuilder() {
            SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.systemLabelsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (StructOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Struct struct = this.systemLabels_;
            if (struct == null) {
                struct = Struct.getDefaultInstance();
            }
            return struct;
        }

        private SingleFieldBuilderV3<Struct, com.google.protobuf.Struct.Builder, StructOrBuilder> getSystemLabelsFieldBuilder() {
            if (this.systemLabelsBuilder_ == null) {
                this.systemLabelsBuilder_ = new SingleFieldBuilderV3<>(getSystemLabels(), getParentForChildren(), isClean());
                this.systemLabels_ = null;
            }
            return this.systemLabelsBuilder_;
        }

        private MapField<String, String> internalGetUserLabels() {
            MapField<String, String> mapField = this.userLabels_;
            return mapField == null ? MapField.emptyMapField(UserLabelsDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, String> internalGetMutableUserLabels() {
            onChanged();
            if (this.userLabels_ == null) {
                this.userLabels_ = MapField.newMapField(UserLabelsDefaultEntryHolder.defaultEntry);
            }
            if (!this.userLabels_.isMutable()) {
                this.userLabels_ = this.userLabels_.copy();
            }
            return this.userLabels_;
        }

        public int getUserLabelsCount() {
            return internalGetUserLabels().getMap().size();
        }

        public boolean containsUserLabels(String str) {
            if (str != null) {
                return internalGetUserLabels().getMap().containsKey(str);
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, String> getUserLabels() {
            return getUserLabelsMap();
        }

        public Map<String, String> getUserLabelsMap() {
            return internalGetUserLabels().getMap();
        }

        public String getUserLabelsOrDefault(String str, String str2) {
            if (str != null) {
                Map map = internalGetUserLabels().getMap();
                return map.containsKey(str) ? (String) map.get(str) : str2;
            }
            throw new NullPointerException();
        }

        public String getUserLabelsOrThrow(String str) {
            if (str != null) {
                Map map = internalGetUserLabels().getMap();
                if (map.containsKey(str)) {
                    return (String) map.get(str);
                }
                throw new IllegalArgumentException();
            }
            throw new NullPointerException();
        }

        public Builder clearUserLabels() {
            internalGetMutableUserLabels().getMutableMap().clear();
            return this;
        }

        public Builder removeUserLabels(String str) {
            if (str != null) {
                internalGetMutableUserLabels().getMutableMap().remove(str);
                return this;
            }
            throw new NullPointerException();
        }

        @Deprecated
        public Map<String, String> getMutableUserLabels() {
            return internalGetMutableUserLabels().getMutableMap();
        }

        public Builder putUserLabels(String str, String str2) {
            if (str == null) {
                throw new NullPointerException();
            } else if (str2 != null) {
                internalGetMutableUserLabels().getMutableMap().put(str, str2);
                return this;
            } else {
                throw new NullPointerException();
            }
        }

        public Builder putAllUserLabels(Map<String, String> map) {
            internalGetMutableUserLabels().getMutableMap().putAll(map);
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private static final class UserLabelsDefaultEntryHolder {
        static final MapEntry<String, String> defaultEntry = MapEntry.newDefaultInstance(MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_UserLabelsEntry_descriptor, FieldType.STRING, "", FieldType.STRING, "");

        private UserLabelsDefaultEntryHolder() {
        }
    }

    private MonitoredResourceMetadata(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private MonitoredResourceMetadata() {
        this.memoizedIsInitialized = -1;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MonitoredResourceMetadata(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        com.google.protobuf.Struct.Builder builder = null;
                        if (this.systemLabels_ != null) {
                            builder = this.systemLabels_.toBuilder();
                        }
                        this.systemLabels_ = (Struct) codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.systemLabels_);
                            this.systemLabels_ = builder.buildPartial();
                        }
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.userLabels_ = MapField.newMapField(UserLabelsDefaultEntryHolder.defaultEntry);
                            z2 |= true;
                        }
                        MapEntry mapEntry = (MapEntry) codedInputStream.readMessage(UserLabelsDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                        this.userLabels_.getMutableMap().put(mapEntry.getKey(), mapEntry.getValue());
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
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_descriptor;
    }

    /* access modifiers changed from: protected */
    public MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetUserLabels();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid map field number: ");
        sb.append(i);
        throw new RuntimeException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceMetadata_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceMetadata.class, Builder.class);
    }

    public boolean hasSystemLabels() {
        return this.systemLabels_ != null;
    }

    public Struct getSystemLabels() {
        Struct struct = this.systemLabels_;
        return struct == null ? Struct.getDefaultInstance() : struct;
    }

    public StructOrBuilder getSystemLabelsOrBuilder() {
        return getSystemLabels();
    }

    /* access modifiers changed from: private */
    public MapField<String, String> internalGetUserLabels() {
        MapField<String, String> mapField = this.userLabels_;
        return mapField == null ? MapField.emptyMapField(UserLabelsDefaultEntryHolder.defaultEntry) : mapField;
    }

    public int getUserLabelsCount() {
        return internalGetUserLabels().getMap().size();
    }

    public boolean containsUserLabels(String str) {
        if (str != null) {
            return internalGetUserLabels().getMap().containsKey(str);
        }
        throw new NullPointerException();
    }

    @Deprecated
    public Map<String, String> getUserLabels() {
        return getUserLabelsMap();
    }

    public Map<String, String> getUserLabelsMap() {
        return internalGetUserLabels().getMap();
    }

    public String getUserLabelsOrDefault(String str, String str2) {
        if (str != null) {
            Map map = internalGetUserLabels().getMap();
            return map.containsKey(str) ? (String) map.get(str) : str2;
        }
        throw new NullPointerException();
    }

    public String getUserLabelsOrThrow(String str) {
        if (str != null) {
            Map map = internalGetUserLabels().getMap();
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
        if (this.systemLabels_ != null) {
            codedOutputStream.writeMessage(1, getSystemLabels());
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetUserLabels(), UserLabelsDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.systemLabels_ != null) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getSystemLabels());
        }
        for (Entry entry : internalGetUserLabels().getMap().entrySet()) {
            i2 += CodedOutputStream.computeMessageSize(2, UserLabelsDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
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
        if (!(obj instanceof MonitoredResourceMetadata)) {
            return super.equals(obj);
        }
        MonitoredResourceMetadata monitoredResourceMetadata = (MonitoredResourceMetadata) obj;
        boolean z2 = hasSystemLabels() == monitoredResourceMetadata.hasSystemLabels();
        if (hasSystemLabels()) {
            z2 = z2 && getSystemLabels().equals(monitoredResourceMetadata.getSystemLabels());
        }
        if (!(z2 && internalGetUserLabels().equals(monitoredResourceMetadata.internalGetUserLabels())) || !this.unknownFields.equals(monitoredResourceMetadata.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (hasSystemLabels()) {
            hashCode = (((hashCode * 37) + 1) * 53) + getSystemLabels().hashCode();
        }
        if (!internalGetUserLabels().getMap().isEmpty()) {
            hashCode = (((hashCode * 37) + 2) * 53) + internalGetUserLabels().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(byteBuffer);
    }

    public static MonitoredResourceMetadata parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(byteString);
    }

    public static MonitoredResourceMetadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(bArr);
    }

    public static MonitoredResourceMetadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceMetadata) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream inputStream) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceMetadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceMetadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MonitoredResourceMetadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceMetadata) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MonitoredResourceMetadata monitoredResourceMetadata) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoredResourceMetadata);
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

    public static MonitoredResourceMetadata getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResourceMetadata> parser() {
        return PARSER;
    }

    public Parser<MonitoredResourceMetadata> getParserForType() {
        return PARSER;
    }

    public MonitoredResourceMetadata getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
