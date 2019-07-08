package com.lightstep.tracer.grpc;

import com.google.protobuf.AbstractParser;
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
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Reporter extends GeneratedMessageV3 implements ReporterOrBuilder {
    private static final Reporter DEFAULT_INSTANCE = new Reporter();
    /* access modifiers changed from: private */
    public static final Parser<Reporter> PARSER = new AbstractParser<Reporter>() {
        public Reporter parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Reporter(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public long reporterId_;
    /* access modifiers changed from: private */
    public List<KeyValue> tags_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ReporterOrBuilder {
        private int bitField0_;
        private long reporterId_;
        private RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> tagsBuilder_;
        private List<KeyValue> tags_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_Reporter_fieldAccessorTable.ensureFieldAccessorsInitialized(Reporter.class, Builder.class);
        }

        private Builder() {
            this.tags_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.tags_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Reporter.alwaysUseFieldBuilders) {
                getTagsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.reporterId_ = 0;
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.tags_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_Reporter_descriptor;
        }

        public Reporter getDefaultInstanceForType() {
            return Reporter.getDefaultInstance();
        }

        public Reporter build() {
            Reporter buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Reporter buildPartial() {
            Reporter reporter = new Reporter((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            reporter.reporterId_ = this.reporterId_;
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.tags_ = Collections.unmodifiableList(this.tags_);
                    this.bitField0_ &= -3;
                }
                reporter.tags_ = this.tags_;
            } else {
                reporter.tags_ = repeatedFieldBuilderV3.build();
            }
            reporter.bitField0_ = 0;
            onBuilt();
            return reporter;
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
            if (message instanceof Reporter) {
                return mergeFrom((Reporter) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Reporter reporter) {
            if (reporter == Reporter.getDefaultInstance()) {
                return this;
            }
            if (reporter.getReporterId() != 0) {
                setReporterId(reporter.getReporterId());
            }
            if (this.tagsBuilder_ == null) {
                if (!reporter.tags_.isEmpty()) {
                    if (this.tags_.isEmpty()) {
                        this.tags_ = reporter.tags_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureTagsIsMutable();
                        this.tags_.addAll(reporter.tags_);
                    }
                    onChanged();
                }
            } else if (!reporter.tags_.isEmpty()) {
                if (this.tagsBuilder_.isEmpty()) {
                    this.tagsBuilder_.dispose();
                    RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = null;
                    this.tagsBuilder_ = null;
                    this.tags_ = reporter.tags_;
                    this.bitField0_ &= -3;
                    if (Reporter.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getTagsFieldBuilder();
                    }
                    this.tagsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.tagsBuilder_.addAllMessages(reporter.tags_);
                }
            }
            mergeUnknownFields(reporter.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.Reporter.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.Reporter.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.Reporter r3 = (com.lightstep.tracer.grpc.Reporter) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.Reporter r4 = (com.lightstep.tracer.grpc.Reporter) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.Reporter.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.Reporter$Builder");
        }

        public Builder setReporterId(long j) {
            this.reporterId_ = j;
            onChanged();
            return this;
        }

        private void ensureTagsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.tags_ = new ArrayList(this.tags_);
                this.bitField0_ |= 2;
            }
        }

        public Builder addTags(com.lightstep.tracer.grpc.KeyValue.Builder builder) {
            RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> repeatedFieldBuilderV3 = this.tagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureTagsIsMutable();
                this.tags_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        private RepeatedFieldBuilderV3<KeyValue, com.lightstep.tracer.grpc.KeyValue.Builder, KeyValueOrBuilder> getTagsFieldBuilder() {
            if (this.tagsBuilder_ == null) {
                this.tagsBuilder_ = new RepeatedFieldBuilderV3<>(this.tags_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.tags_ = null;
            }
            return this.tagsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Reporter(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Reporter() {
        this.memoizedIsInitialized = -1;
        this.reporterId_ = 0;
        this.tags_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Reporter(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 8) {
                        this.reporterId_ = codedInputStream.readUInt64();
                    } else if (readTag == 34) {
                        if (!(z2 & true)) {
                            this.tags_ = new ArrayList();
                            z2 |= true;
                        }
                        this.tags_.add(codedInputStream.readMessage(KeyValue.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.tags_ = Collections.unmodifiableList(this.tags_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.tags_ = Collections.unmodifiableList(this.tags_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return Collector.internal_static_lightstep_collector_Reporter_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_Reporter_fieldAccessorTable.ensureFieldAccessorsInitialized(Reporter.class, Builder.class);
    }

    public long getReporterId() {
        return this.reporterId_;
    }

    public List<KeyValue> getTagsList() {
        return this.tags_;
    }

    public int getTagsCount() {
        return this.tags_.size();
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
        long j = this.reporterId_;
        if (j != 0) {
            codedOutputStream.writeUInt64(1, j);
        }
        for (int i = 0; i < this.tags_.size(); i++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.tags_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        long j = this.reporterId_;
        int computeUInt64Size = j != 0 ? CodedOutputStream.computeUInt64Size(1, j) + 0 : 0;
        for (int i2 = 0; i2 < this.tags_.size(); i2++) {
            computeUInt64Size += CodedOutputStream.computeMessageSize(4, (MessageLite) this.tags_.get(i2));
        }
        int serializedSize = computeUInt64Size + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Reporter)) {
            return super.equals(obj);
        }
        Reporter reporter = (Reporter) obj;
        if (!(((getReporterId() > reporter.getReporterId() ? 1 : (getReporterId() == reporter.getReporterId() ? 0 : -1)) == 0) && getTagsList().equals(reporter.getTagsList())) || !this.unknownFields.equals(reporter.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + Internal.hashLong(getReporterId());
        if (getTagsCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getTagsList().hashCode();
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

    public static Builder newBuilder(Reporter reporter) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(reporter);
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

    public static Reporter getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Reporter> parser() {
        return PARSER;
    }

    public Parser<Reporter> getParserForType() {
        return PARSER;
    }

    public Reporter getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
