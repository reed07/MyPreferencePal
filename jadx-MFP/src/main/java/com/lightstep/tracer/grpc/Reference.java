package com.lightstep.tracer.grpc;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;

public final class Reference extends GeneratedMessageV3 implements ReferenceOrBuilder {
    private static final Reference DEFAULT_INSTANCE = new Reference();
    /* access modifiers changed from: private */
    public static final Parser<Reference> PARSER = new AbstractParser<Reference>() {
        public Reference parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Reference(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public int relationship_;
    /* access modifiers changed from: private */
    public SpanContext spanContext_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ReferenceOrBuilder {
        private int relationship_;
        private SingleFieldBuilderV3<SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, SpanContextOrBuilder> spanContextBuilder_;
        private SpanContext spanContext_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_Reference_fieldAccessorTable.ensureFieldAccessorsInitialized(Reference.class, Builder.class);
        }

        private Builder() {
            this.relationship_ = 0;
            this.spanContext_ = null;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.relationship_ = 0;
            this.spanContext_ = null;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            Reference.alwaysUseFieldBuilders;
        }

        public Builder clear() {
            super.clear();
            this.relationship_ = 0;
            if (this.spanContextBuilder_ == null) {
                this.spanContext_ = null;
            } else {
                this.spanContext_ = null;
                this.spanContextBuilder_ = null;
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_Reference_descriptor;
        }

        public Reference getDefaultInstanceForType() {
            return Reference.getDefaultInstance();
        }

        public Reference build() {
            Reference buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Reference buildPartial() {
            Reference reference = new Reference((com.google.protobuf.GeneratedMessageV3.Builder) this);
            reference.relationship_ = this.relationship_;
            SingleFieldBuilderV3<SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, SpanContextOrBuilder> singleFieldBuilderV3 = this.spanContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                reference.spanContext_ = this.spanContext_;
            } else {
                reference.spanContext_ = (SpanContext) singleFieldBuilderV3.build();
            }
            onBuilt();
            return reference;
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
            if (message instanceof Reference) {
                return mergeFrom((Reference) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Reference reference) {
            if (reference == Reference.getDefaultInstance()) {
                return this;
            }
            if (reference.relationship_ != 0) {
                setRelationshipValue(reference.getRelationshipValue());
            }
            if (reference.hasSpanContext()) {
                mergeSpanContext(reference.getSpanContext());
            }
            mergeUnknownFields(reference.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.Reference.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.Reference.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.Reference r3 = (com.lightstep.tracer.grpc.Reference) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.Reference r4 = (com.lightstep.tracer.grpc.Reference) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.Reference.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.Reference$Builder");
        }

        public Builder setRelationshipValue(int i) {
            this.relationship_ = i;
            onChanged();
            return this;
        }

        public Builder setRelationship(Relationship relationship) {
            if (relationship != null) {
                this.relationship_ = relationship.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder setSpanContext(SpanContext spanContext) {
            SingleFieldBuilderV3<SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, SpanContextOrBuilder> singleFieldBuilderV3 = this.spanContextBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(spanContext);
            } else if (spanContext != null) {
                this.spanContext_ = spanContext;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder mergeSpanContext(SpanContext spanContext) {
            SingleFieldBuilderV3<SpanContext, com.lightstep.tracer.grpc.SpanContext.Builder, SpanContextOrBuilder> singleFieldBuilderV3 = this.spanContextBuilder_;
            if (singleFieldBuilderV3 == null) {
                SpanContext spanContext2 = this.spanContext_;
                if (spanContext2 != null) {
                    this.spanContext_ = SpanContext.newBuilder(spanContext2).mergeFrom(spanContext).buildPartial();
                } else {
                    this.spanContext_ = spanContext;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(spanContext);
            }
            return this;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public enum Relationship implements ProtocolMessageEnum {
        CHILD_OF(0),
        FOLLOWS_FROM(1),
        UNRECOGNIZED(-1);
        
        private static final Relationship[] VALUES = null;
        private static final EnumLiteMap<Relationship> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<Relationship>() {
                public Relationship findValueByNumber(int i) {
                    return Relationship.forNumber(i);
                }
            };
            VALUES = values();
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public static Relationship forNumber(int i) {
            switch (i) {
                case 0:
                    return CHILD_OF;
                case 1:
                    return FOLLOWS_FROM;
                default:
                    return null;
            }
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) Reference.getDescriptor().getEnumTypes().get(0);
        }

        private Relationship(int i) {
            this.value = i;
        }
    }

    private Reference(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Reference() {
        this.memoizedIsInitialized = -1;
        this.relationship_ = 0;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Reference(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistryLite != null) {
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag == 0) {
                        z = true;
                    } else if (readTag == 8) {
                        this.relationship_ = codedInputStream.readEnum();
                    } else if (readTag == 18) {
                        com.lightstep.tracer.grpc.SpanContext.Builder builder = null;
                        if (this.spanContext_ != null) {
                            builder = this.spanContext_.toBuilder();
                        }
                        this.spanContext_ = (SpanContext) codedInputStream.readMessage(SpanContext.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.spanContext_);
                            this.spanContext_ = builder.buildPartial();
                        }
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
        return Collector.internal_static_lightstep_collector_Reference_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_Reference_fieldAccessorTable.ensureFieldAccessorsInitialized(Reference.class, Builder.class);
    }

    public int getRelationshipValue() {
        return this.relationship_;
    }

    public boolean hasSpanContext() {
        return this.spanContext_ != null;
    }

    public SpanContext getSpanContext() {
        SpanContext spanContext = this.spanContext_;
        return spanContext == null ? SpanContext.getDefaultInstance() : spanContext;
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
        if (this.relationship_ != Relationship.CHILD_OF.getNumber()) {
            codedOutputStream.writeEnum(1, this.relationship_);
        }
        if (this.spanContext_ != null) {
            codedOutputStream.writeMessage(2, getSpanContext());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.relationship_ != Relationship.CHILD_OF.getNumber()) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, this.relationship_);
        }
        if (this.spanContext_ != null) {
            i2 += CodedOutputStream.computeMessageSize(2, getSpanContext());
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
        if (!(obj instanceof Reference)) {
            return super.equals(obj);
        }
        Reference reference = (Reference) obj;
        boolean z2 = (this.relationship_ == reference.relationship_) && hasSpanContext() == reference.hasSpanContext();
        if (hasSpanContext()) {
            z2 = z2 && getSpanContext().equals(reference.getSpanContext());
        }
        if (!z2 || !this.unknownFields.equals(reference.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.relationship_;
        if (hasSpanContext()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getSpanContext().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
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

    public static Reference getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Reference> parser() {
        return PARSER;
    }

    public Parser<Reference> getParserForType() {
        return PARSER;
    }

    public Reference getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
