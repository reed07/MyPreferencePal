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

public final class PreconditionFailure extends GeneratedMessageV3 implements PreconditionFailureOrBuilder {
    private static final PreconditionFailure DEFAULT_INSTANCE = new PreconditionFailure();
    /* access modifiers changed from: private */
    public static final Parser<PreconditionFailure> PARSER = new AbstractParser<PreconditionFailure>() {
        public PreconditionFailure parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new PreconditionFailure(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int VIOLATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<Violation> violations_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements PreconditionFailureOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> violationsBuilder_;
        private List<Violation> violations_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_fieldAccessorTable.ensureFieldAccessorsInitialized(PreconditionFailure.class, Builder.class);
        }

        private Builder() {
            this.violations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.violations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (PreconditionFailure.alwaysUseFieldBuilders) {
                getViolationsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.violations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
        }

        public PreconditionFailure getDefaultInstanceForType() {
            return PreconditionFailure.getDefaultInstance();
        }

        public PreconditionFailure build() {
            PreconditionFailure buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public PreconditionFailure buildPartial() {
            PreconditionFailure preconditionFailure = new PreconditionFailure((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.violations_ = Collections.unmodifiableList(this.violations_);
                    this.bitField0_ &= -2;
                }
                preconditionFailure.violations_ = this.violations_;
            } else {
                preconditionFailure.violations_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return preconditionFailure;
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
            if (message instanceof PreconditionFailure) {
                return mergeFrom((PreconditionFailure) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(PreconditionFailure preconditionFailure) {
            if (preconditionFailure == PreconditionFailure.getDefaultInstance()) {
                return this;
            }
            if (this.violationsBuilder_ == null) {
                if (!preconditionFailure.violations_.isEmpty()) {
                    if (this.violations_.isEmpty()) {
                        this.violations_ = preconditionFailure.violations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureViolationsIsMutable();
                        this.violations_.addAll(preconditionFailure.violations_);
                    }
                    onChanged();
                }
            } else if (!preconditionFailure.violations_.isEmpty()) {
                if (this.violationsBuilder_.isEmpty()) {
                    this.violationsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = null;
                    this.violationsBuilder_ = null;
                    this.violations_ = preconditionFailure.violations_;
                    this.bitField0_ &= -2;
                    if (PreconditionFailure.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getViolationsFieldBuilder();
                    }
                    this.violationsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.violationsBuilder_.addAllMessages(preconditionFailure.violations_);
                }
            }
            mergeUnknownFields(preconditionFailure.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.rpc.PreconditionFailure.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.rpc.PreconditionFailure.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.rpc.PreconditionFailure r3 = (com.google.rpc.PreconditionFailure) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.rpc.PreconditionFailure r4 = (com.google.rpc.PreconditionFailure) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.PreconditionFailure.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.PreconditionFailure$Builder");
        }

        private void ensureViolationsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.violations_ = new ArrayList(this.violations_);
                this.bitField0_ |= 1;
            }
        }

        public List<Violation> getViolationsList() {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.violations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getViolationsCount() {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.violations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Violation getViolations(int i) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Violation) this.violations_.get(i);
            }
            return (Violation) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setViolations(int i, Violation violation) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, violation);
            } else if (violation != null) {
                ensureViolationsIsMutable();
                this.violations_.set(i, violation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setViolations(int i, Builder builder) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addViolations(Violation violation) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(violation);
            } else if (violation != null) {
                ensureViolationsIsMutable();
                this.violations_.add(violation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addViolations(int i, Violation violation) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, violation);
            } else if (violation != null) {
                ensureViolationsIsMutable();
                this.violations_.add(i, violation);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addViolations(Builder builder) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addViolations(int i, Builder builder) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllViolations(Iterable<? extends Violation> iterable) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.violations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearViolations() {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.violations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeViolations(int i) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureViolationsIsMutable();
                this.violations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getViolationsBuilder(int i) {
            return (Builder) getViolationsFieldBuilder().getBuilder(i);
        }

        public ViolationOrBuilder getViolationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (ViolationOrBuilder) this.violations_.get(i);
            }
            return (ViolationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
            RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> repeatedFieldBuilderV3 = this.violationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.violations_);
        }

        public Builder addViolationsBuilder() {
            return (Builder) getViolationsFieldBuilder().addBuilder(Violation.getDefaultInstance());
        }

        public Builder addViolationsBuilder(int i) {
            return (Builder) getViolationsFieldBuilder().addBuilder(i, Violation.getDefaultInstance());
        }

        public List<Builder> getViolationsBuilderList() {
            return getViolationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Violation, Builder, ViolationOrBuilder> getViolationsFieldBuilder() {
            if (this.violationsBuilder_ == null) {
                List<Violation> list = this.violations_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.violationsBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.violations_ = null;
            }
            return this.violationsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static final class Violation extends GeneratedMessageV3 implements ViolationOrBuilder {
        private static final Violation DEFAULT_INSTANCE = new Violation();
        public static final int DESCRIPTION_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final Parser<Violation> PARSER = new AbstractParser<Violation>() {
            public Violation parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Violation(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SUBJECT_FIELD_NUMBER = 2;
        public static final int TYPE_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public volatile Object description_;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public volatile Object subject_;
        /* access modifiers changed from: private */
        public volatile Object type_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ViolationOrBuilder {
            private Object description_;
            private Object subject_;
            private Object type_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_fieldAccessorTable.ensureFieldAccessorsInitialized(Violation.class, Builder.class);
            }

            private Builder() {
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Violation.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.type_ = "";
                this.subject_ = "";
                this.description_ = "";
                return this;
            }

            public Descriptor getDescriptorForType() {
                return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_descriptor;
            }

            public Violation getDefaultInstanceForType() {
                return Violation.getDefaultInstance();
            }

            public Violation build() {
                Violation buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Violation buildPartial() {
                Violation violation = new Violation((com.google.protobuf.GeneratedMessageV3.Builder) this);
                violation.type_ = this.type_;
                violation.subject_ = this.subject_;
                violation.description_ = this.description_;
                onBuilt();
                return violation;
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
                if (message instanceof Violation) {
                    return mergeFrom((Violation) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Violation violation) {
                if (violation == Violation.getDefaultInstance()) {
                    return this;
                }
                if (!violation.getType().isEmpty()) {
                    this.type_ = violation.type_;
                    onChanged();
                }
                if (!violation.getSubject().isEmpty()) {
                    this.subject_ = violation.subject_;
                    onChanged();
                }
                if (!violation.getDescription().isEmpty()) {
                    this.description_ = violation.description_;
                    onChanged();
                }
                mergeUnknownFields(violation.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.rpc.PreconditionFailure.Violation.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.rpc.PreconditionFailure.Violation.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    com.google.rpc.PreconditionFailure$Violation r3 = (com.google.rpc.PreconditionFailure.Violation) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                    com.google.rpc.PreconditionFailure$Violation r4 = (com.google.rpc.PreconditionFailure.Violation) r4     // Catch:{ all -> 0x0011 }
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.PreconditionFailure.Violation.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.rpc.PreconditionFailure$Violation$Builder");
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
                this.type_ = Violation.getDefaultInstance().getType();
                onChanged();
                return this;
            }

            public Builder setTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    Violation.checkByteStringIsUtf8(byteString);
                    this.type_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public String getSubject() {
                Object obj = this.subject_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.subject_ = stringUtf8;
                return stringUtf8;
            }

            public ByteString getSubjectBytes() {
                Object obj = this.subject_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.subject_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setSubject(String str) {
                if (str != null) {
                    this.subject_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSubject() {
                this.subject_ = Violation.getDefaultInstance().getSubject();
                onChanged();
                return this;
            }

            public Builder setSubjectBytes(ByteString byteString) {
                if (byteString != null) {
                    Violation.checkByteStringIsUtf8(byteString);
                    this.subject_ = byteString;
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
                this.description_ = Violation.getDefaultInstance().getDescription();
                onChanged();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                if (byteString != null) {
                    Violation.checkByteStringIsUtf8(byteString);
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

        private Violation(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private Violation() {
            this.memoizedIsInitialized = -1;
            this.type_ = "";
            this.subject_ = "";
            this.description_ = "";
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Violation(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.type_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            this.subject_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 26) {
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
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_Violation_fieldAccessorTable.ensureFieldAccessorsInitialized(Violation.class, Builder.class);
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

        public String getSubject() {
            Object obj = this.subject_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.subject_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getSubjectBytes() {
            Object obj = this.subject_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.subject_ = copyFromUtf8;
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
            if (!getTypeBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
            }
            if (!getSubjectBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.subject_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.description_);
            }
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
            if (!getSubjectBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(2, this.subject_);
            }
            if (!getDescriptionBytes().isEmpty()) {
                i2 += GeneratedMessageV3.computeStringSize(3, this.description_);
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
            if (!(obj instanceof Violation)) {
                return super.equals(obj);
            }
            Violation violation = (Violation) obj;
            if (!(((getType().equals(violation.getType())) && getSubject().equals(violation.getSubject())) && getDescription().equals(violation.getDescription())) || !this.unknownFields.equals(violation.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getType().hashCode()) * 37) + 2) * 53) + getSubject().hashCode()) * 37) + 3) * 53) + getDescription().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode;
            return hashCode;
        }

        public static Violation parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Violation) PARSER.parseFrom(byteBuffer);
        }

        public static Violation parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Violation) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Violation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Violation) PARSER.parseFrom(byteString);
        }

        public static Violation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Violation) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Violation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Violation) PARSER.parseFrom(bArr);
        }

        public static Violation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Violation) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Violation parseFrom(InputStream inputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Violation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Violation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Violation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Violation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Violation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Violation) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Violation violation) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(violation);
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

        public static Violation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Violation> parser() {
            return PARSER;
        }

        public Parser<Violation> getParserForType() {
            return PARSER;
        }

        public Violation getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface ViolationOrBuilder extends MessageOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getSubject();

        ByteString getSubjectBytes();

        String getType();

        ByteString getTypeBytes();
    }

    private PreconditionFailure(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private PreconditionFailure() {
        this.memoizedIsInitialized = -1;
        this.violations_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private PreconditionFailure(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.violations_ = new ArrayList();
                            z2 |= true;
                        }
                        this.violations_.add(codedInputStream.readMessage(Violation.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.violations_ = Collections.unmodifiableList(this.violations_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.violations_ = Collections.unmodifiableList(this.violations_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ErrorDetailsProto.internal_static_google_rpc_PreconditionFailure_fieldAccessorTable.ensureFieldAccessorsInitialized(PreconditionFailure.class, Builder.class);
    }

    public List<Violation> getViolationsList() {
        return this.violations_;
    }

    public List<? extends ViolationOrBuilder> getViolationsOrBuilderList() {
        return this.violations_;
    }

    public int getViolationsCount() {
        return this.violations_.size();
    }

    public Violation getViolations(int i) {
        return (Violation) this.violations_.get(i);
    }

    public ViolationOrBuilder getViolationsOrBuilder(int i) {
        return (ViolationOrBuilder) this.violations_.get(i);
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
        for (int i = 0; i < this.violations_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.violations_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.violations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.violations_.get(i3));
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
        if (!(obj instanceof PreconditionFailure)) {
            return super.equals(obj);
        }
        PreconditionFailure preconditionFailure = (PreconditionFailure) obj;
        if (!(getViolationsList().equals(preconditionFailure.getViolationsList())) || !this.unknownFields.equals(preconditionFailure.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getViolationsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getViolationsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static PreconditionFailure parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (PreconditionFailure) PARSER.parseFrom(byteBuffer);
    }

    public static PreconditionFailure parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PreconditionFailure) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (PreconditionFailure) PARSER.parseFrom(byteString);
    }

    public static PreconditionFailure parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PreconditionFailure) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (PreconditionFailure) PARSER.parseFrom(bArr);
    }

    public static PreconditionFailure parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (PreconditionFailure) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(InputStream inputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static PreconditionFailure parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PreconditionFailure parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static PreconditionFailure parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static PreconditionFailure parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static PreconditionFailure parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (PreconditionFailure) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(PreconditionFailure preconditionFailure) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(preconditionFailure);
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

    public static PreconditionFailure getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<PreconditionFailure> parser() {
        return PARSER;
    }

    public Parser<PreconditionFailure> getParserForType() {
        return PARSER;
    }

    public PreconditionFailure getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
