package com.google.rpc;

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
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BadRequest extends GeneratedMessageV3 implements BadRequestOrBuilder {
    private static final BadRequest DEFAULT_INSTANCE = new BadRequest();
    public static final int FIELD_VIOLATIONS_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<BadRequest> PARSER = new AbstractParser<BadRequest>() {
        public BadRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new BadRequest(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<FieldViolation> fieldViolations_;
    private byte memoizedIsInitialized;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements BadRequestOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> fieldViolationsBuilder_;
        private List<FieldViolation> fieldViolations_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(BadRequest.class, Builder.class);
        }

        private Builder() {
            this.fieldViolations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.fieldViolations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (BadRequest.alwaysUseFieldBuilders) {
                getFieldViolationsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fieldViolations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
        }

        public BadRequest getDefaultInstanceForType() {
            return BadRequest.getDefaultInstance();
        }

        public BadRequest build() {
            BadRequest buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public BadRequest buildPartial() {
            BadRequest badRequest = new BadRequest((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.fieldViolations_ = Collections.unmodifiableList(this.fieldViolations_);
                    this.bitField0_ &= -2;
                }
                badRequest.fieldViolations_ = this.fieldViolations_;
            } else {
                badRequest.fieldViolations_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return badRequest;
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
            if (message instanceof BadRequest) {
                return mergeFrom((BadRequest) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(BadRequest badRequest) {
            if (badRequest == BadRequest.getDefaultInstance()) {
                return this;
            }
            if (this.fieldViolationsBuilder_ == null) {
                if (!badRequest.fieldViolations_.isEmpty()) {
                    if (this.fieldViolations_.isEmpty()) {
                        this.fieldViolations_ = badRequest.fieldViolations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureFieldViolationsIsMutable();
                        this.fieldViolations_.addAll(badRequest.fieldViolations_);
                    }
                    onChanged();
                }
            } else if (!badRequest.fieldViolations_.isEmpty()) {
                if (this.fieldViolationsBuilder_.isEmpty()) {
                    this.fieldViolationsBuilder_.dispose();
                    RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = null;
                    this.fieldViolationsBuilder_ = null;
                    this.fieldViolations_ = badRequest.fieldViolations_;
                    this.bitField0_ &= -2;
                    if (BadRequest.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getFieldViolationsFieldBuilder();
                    }
                    this.fieldViolationsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.fieldViolationsBuilder_.addAllMessages(badRequest.fieldViolations_);
                }
            }
            mergeUnknownFields(badRequest.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.BadRequest.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.BadRequest.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.BadRequest r3 = (com.google.rpc.BadRequest) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.BadRequest r4 = (com.google.rpc.BadRequest) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.BadRequest.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.BadRequest$Builder");
        }

        private void ensureFieldViolationsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.fieldViolations_ = new ArrayList(this.fieldViolations_);
                this.bitField0_ |= 1;
            }
        }

        public List<FieldViolation> getFieldViolationsList() {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.fieldViolations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getFieldViolationsCount() {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.fieldViolations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public FieldViolation getFieldViolations(int i) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (FieldViolation) this.fieldViolations_.get(i);
            }
            return (FieldViolation) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setFieldViolations(int i, FieldViolation fieldViolation) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, fieldViolation);
            } else if (fieldViolation != null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.set(i, fieldViolation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setFieldViolations(int i, Builder builder) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addFieldViolations(FieldViolation fieldViolation) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(fieldViolation);
            } else if (fieldViolation != null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(fieldViolation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addFieldViolations(int i, FieldViolation fieldViolation) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, fieldViolation);
            } else if (fieldViolation != null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(i, fieldViolation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addFieldViolations(Builder builder) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addFieldViolations(int i, Builder builder) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllFieldViolations(Iterable<? extends FieldViolation> iterable) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.fieldViolations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearFieldViolations() {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.fieldViolations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeFieldViolations(int i) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureFieldViolationsIsMutable();
                this.fieldViolations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getFieldViolationsBuilder(int i) {
            return (Builder) getFieldViolationsFieldBuilder().getBuilder(i);
        }

        public FieldViolationOrBuilder getFieldViolationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (FieldViolationOrBuilder) this.fieldViolations_.get(i);
            }
            return (FieldViolationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList() {
            RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> repeatedFieldBuilderV3 = this.fieldViolationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.fieldViolations_);
        }

        public Builder addFieldViolationsBuilder() {
            return (Builder) getFieldViolationsFieldBuilder().addBuilder(FieldViolation.getDefaultInstance());
        }

        public Builder addFieldViolationsBuilder(int i) {
            return (Builder) getFieldViolationsFieldBuilder().addBuilder(i, FieldViolation.getDefaultInstance());
        }

        public List<Builder> getFieldViolationsBuilderList() {
            return getFieldViolationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<FieldViolation, Builder, FieldViolationOrBuilder> getFieldViolationsFieldBuilder() {
            if (this.fieldViolationsBuilder_ == null) {
                List<FieldViolation> list = this.fieldViolations_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.fieldViolationsBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.fieldViolations_ = null;
            }
            return this.fieldViolationsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static final class FieldViolation extends GeneratedMessageV3 implements FieldViolationOrBuilder {
        private static final FieldViolation DEFAULT_INSTANCE = new FieldViolation();
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        public static final int FIELD_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Parser<FieldViolation> PARSER = new AbstractParser<FieldViolation>() {
            public FieldViolation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new FieldViolation(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public volatile Object description_;
        /* access modifiers changed from: private */
        public volatile Object field_;
        private byte memoizedIsInitialized;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements FieldViolationOrBuilder {
            private Object description_;
            private Object field_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldViolation.class, Builder.class);
            }

            private Builder() {
                this.field_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.field_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                FieldViolation.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.field_ = "";
                this.description_ = "";
                return this;
            }

            public Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
            }

            public FieldViolation getDefaultInstanceForType() {
                return FieldViolation.getDefaultInstance();
            }

            public FieldViolation build() {
                FieldViolation buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public FieldViolation buildPartial() {
                FieldViolation fieldViolation = new FieldViolation((com.google.protobuf.GeneratedMessageV3.Builder) this);
                fieldViolation.field_ = this.field_;
                fieldViolation.description_ = this.description_;
                onBuilt();
                return fieldViolation;
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
                if (message instanceof FieldViolation) {
                    return mergeFrom((FieldViolation) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(FieldViolation fieldViolation) {
                if (fieldViolation == FieldViolation.getDefaultInstance()) {
                    return this;
                }
                if (!fieldViolation.getField().isEmpty()) {
                    this.field_ = fieldViolation.field_;
                    onChanged();
                }
                if (!fieldViolation.getDescription().isEmpty()) {
                    this.description_ = fieldViolation.description_;
                    onChanged();
                }
                mergeUnknownFields(fieldViolation.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.rpc.BadRequest.FieldViolation.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.rpc.BadRequest.FieldViolation.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    com.google.rpc.BadRequest$FieldViolation r3 = (com.google.rpc.BadRequest.FieldViolation) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                    com.google.rpc.BadRequest$FieldViolation r4 = (com.google.rpc.BadRequest.FieldViolation) r4     // Catch:{ all -> 0x0011 }
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.BadRequest.FieldViolation.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.BadRequest$FieldViolation$Builder");
            }

            public String getField() {
                Object obj = this.field_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.field_ = stringUtf8;
                return stringUtf8;
            }

            public ByteString getFieldBytes() {
                Object obj = this.field_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.field_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setField(String str) {
                if (str != null) {
                    this.field_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearField() {
                this.field_ = FieldViolation.getDefaultInstance().getField();
                onChanged();
                return this;
            }

            public Builder setFieldBytes(ByteString byteString) {
                if (byteString != null) {
                    FieldViolation.checkByteStringIsUtf8(byteString);
                    this.field_ = byteString;
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
                this.description_ = FieldViolation.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                if (byteString != null) {
                    FieldViolation.checkByteStringIsUtf8(byteString);
                    this.description_ = byteString;
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

        private FieldViolation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private FieldViolation() {
            this.memoizedIsInitialized = -1;
            this.field_ = "";
            this.description_ = "";
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private FieldViolation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            if (extensionRegistryLite != null) {
                com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0) {
                            z = true;
                        } else if (readTag == 10) {
                            this.field_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            this.description_ = codedInputStream.readStringRequireUtf8();
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
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_BadRequest_FieldViolation_fieldAccessorTable.ensureFieldAccessorsInitialized(FieldViolation.class, Builder.class);
        }

        public String getField() {
            Object obj = this.field_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.field_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getFieldBytes() {
            Object obj = this.field_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.field_ = copyFromUtf8;
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
            if (!getFieldBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.field_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.description_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!getFieldBytes().isEmpty()) {
                i2 = 0 + GeneratedMessageV3.computeStringSize(1, this.field_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.description_);
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
            if (!(obj instanceof FieldViolation)) {
                return super.equals(obj);
            }
            FieldViolation fieldViolation = (FieldViolation) obj;
            if (!((getField().equals(fieldViolation.getField())) && getDescription().equals(fieldViolation.getDescription())) || !this.unknownFields.equals(fieldViolation.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getField().hashCode()) * 37) + 2) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static FieldViolation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (FieldViolation) PARSER.parseFrom(byteBuffer);
        }

        public static FieldViolation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldViolation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldViolation) PARSER.parseFrom(byteString);
        }

        public static FieldViolation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldViolation) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldViolation) PARSER.parseFrom(bArr);
        }

        public static FieldViolation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldViolation) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(InputStream inputStream) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static FieldViolation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FieldViolation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static FieldViolation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static FieldViolation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FieldViolation fieldViolation) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(fieldViolation);
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

        public static FieldViolation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldViolation> parser() {
            return PARSER;
        }

        public Parser<FieldViolation> getParserForType() {
            return PARSER;
        }

        public FieldViolation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface FieldViolationOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getField();

        ByteString getFieldBytes();
    }

    private BadRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private BadRequest() {
        this.memoizedIsInitialized = -1;
        this.fieldViolations_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private BadRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (!z2 || !true) {
                            this.fieldViolations_ = new ArrayList();
                            z2 |= true;
                        }
                        this.fieldViolations_.add(codedInputStream.readMessage(FieldViolation.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.fieldViolations_ = Collections.unmodifiableList(this.fieldViolations_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.fieldViolations_ = Collections.unmodifiableList(this.fieldViolations_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_BadRequest_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_BadRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(BadRequest.class, Builder.class);
    }

    public List<FieldViolation> getFieldViolationsList() {
        return this.fieldViolations_;
    }

    public List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList() {
        return this.fieldViolations_;
    }

    public int getFieldViolationsCount() {
        return this.fieldViolations_.size();
    }

    public FieldViolation getFieldViolations(int i) {
        return (FieldViolation) this.fieldViolations_.get(i);
    }

    public FieldViolationOrBuilder getFieldViolationsOrBuilder(int i) {
        return (FieldViolationOrBuilder) this.fieldViolations_.get(i);
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
        for (int i = 0; i < this.fieldViolations_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.fieldViolations_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.fieldViolations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.fieldViolations_.get(i3));
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
        if (!(obj instanceof BadRequest)) {
            return super.equals(obj);
        }
        BadRequest badRequest = (BadRequest) obj;
        if (!(getFieldViolationsList().equals(badRequest.getFieldViolationsList())) || !this.unknownFields.equals(badRequest.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getFieldViolationsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getFieldViolationsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static BadRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (BadRequest) PARSER.parseFrom(byteBuffer);
    }

    public static BadRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BadRequest) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static BadRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BadRequest) PARSER.parseFrom(byteString);
    }

    public static BadRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BadRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static BadRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BadRequest) PARSER.parseFrom(bArr);
    }

    public static BadRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BadRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static BadRequest parseFrom(InputStream inputStream) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static BadRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BadRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static BadRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static BadRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static BadRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BadRequest badRequest) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(badRequest);
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

    public static BadRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BadRequest> parser() {
        return PARSER;
    }

    public Parser<BadRequest> getParserForType() {
        return PARSER;
    }

    public BadRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
