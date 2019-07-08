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
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ReportResponse extends GeneratedMessageV3 implements ReportResponseOrBuilder {
    private static final ReportResponse DEFAULT_INSTANCE = new ReportResponse();
    /* access modifiers changed from: private */
    public static final Parser<ReportResponse> PARSER = new AbstractParser<ReportResponse>() {
        public ReportResponse parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ReportResponse(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public List<Command> commands_;
    /* access modifiers changed from: private */
    public LazyStringList errors_;
    /* access modifiers changed from: private */
    public LazyStringList infos_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public Timestamp receiveTimestamp_;
    /* access modifiers changed from: private */
    public Timestamp transmitTimestamp_;
    /* access modifiers changed from: private */
    public LazyStringList warnings_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ReportResponseOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Command, com.lightstep.tracer.grpc.Command.Builder, CommandOrBuilder> commandsBuilder_;
        private List<Command> commands_;
        private LazyStringList errors_;
        private LazyStringList infos_;
        private SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> receiveTimestampBuilder_;
        private Timestamp receiveTimestamp_;
        private SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> transmitTimestampBuilder_;
        private Timestamp transmitTimestamp_;
        private LazyStringList warnings_;

        public final boolean isInitialized() {
            return true;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Collector.internal_static_lightstep_collector_ReportResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ReportResponse.class, Builder.class);
        }

        private Builder() {
            this.commands_ = Collections.emptyList();
            this.receiveTimestamp_ = null;
            this.transmitTimestamp_ = null;
            this.errors_ = LazyStringArrayList.EMPTY;
            this.warnings_ = LazyStringArrayList.EMPTY;
            this.infos_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.commands_ = Collections.emptyList();
            this.receiveTimestamp_ = null;
            this.transmitTimestamp_ = null;
            this.errors_ = LazyStringArrayList.EMPTY;
            this.warnings_ = LazyStringArrayList.EMPTY;
            this.infos_ = LazyStringArrayList.EMPTY;
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ReportResponse.alwaysUseFieldBuilders) {
                getCommandsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Command, com.lightstep.tracer.grpc.Command.Builder, CommandOrBuilder> repeatedFieldBuilderV3 = this.commandsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.commands_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.receiveTimestampBuilder_ == null) {
                this.receiveTimestamp_ = null;
            } else {
                this.receiveTimestamp_ = null;
                this.receiveTimestampBuilder_ = null;
            }
            if (this.transmitTimestampBuilder_ == null) {
                this.transmitTimestamp_ = null;
            } else {
                this.transmitTimestamp_ = null;
                this.transmitTimestampBuilder_ = null;
            }
            this.errors_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -9;
            this.warnings_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -17;
            this.infos_ = LazyStringArrayList.EMPTY;
            this.bitField0_ &= -33;
            return this;
        }

        public Descriptor getDescriptorForType() {
            return Collector.internal_static_lightstep_collector_ReportResponse_descriptor;
        }

        public ReportResponse getDefaultInstanceForType() {
            return ReportResponse.getDefaultInstance();
        }

        public ReportResponse build() {
            ReportResponse buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ReportResponse buildPartial() {
            ReportResponse reportResponse = new ReportResponse((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Command, com.lightstep.tracer.grpc.Command.Builder, CommandOrBuilder> repeatedFieldBuilderV3 = this.commandsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.commands_ = Collections.unmodifiableList(this.commands_);
                    this.bitField0_ &= -2;
                }
                reportResponse.commands_ = this.commands_;
            } else {
                reportResponse.commands_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.receiveTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                reportResponse.receiveTimestamp_ = this.receiveTimestamp_;
            } else {
                reportResponse.receiveTimestamp_ = (Timestamp) singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV32 = this.transmitTimestampBuilder_;
            if (singleFieldBuilderV32 == null) {
                reportResponse.transmitTimestamp_ = this.transmitTimestamp_;
            } else {
                reportResponse.transmitTimestamp_ = (Timestamp) singleFieldBuilderV32.build();
            }
            if ((this.bitField0_ & 8) == 8) {
                this.errors_ = this.errors_.getUnmodifiableView();
                this.bitField0_ &= -9;
            }
            reportResponse.errors_ = this.errors_;
            if ((this.bitField0_ & 16) == 16) {
                this.warnings_ = this.warnings_.getUnmodifiableView();
                this.bitField0_ &= -17;
            }
            reportResponse.warnings_ = this.warnings_;
            if ((this.bitField0_ & 32) == 32) {
                this.infos_ = this.infos_.getUnmodifiableView();
                this.bitField0_ &= -33;
            }
            reportResponse.infos_ = this.infos_;
            reportResponse.bitField0_ = 0;
            onBuilt();
            return reportResponse;
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
            if (message instanceof ReportResponse) {
                return mergeFrom((ReportResponse) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ReportResponse reportResponse) {
            if (reportResponse == ReportResponse.getDefaultInstance()) {
                return this;
            }
            if (this.commandsBuilder_ == null) {
                if (!reportResponse.commands_.isEmpty()) {
                    if (this.commands_.isEmpty()) {
                        this.commands_ = reportResponse.commands_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureCommandsIsMutable();
                        this.commands_.addAll(reportResponse.commands_);
                    }
                    onChanged();
                }
            } else if (!reportResponse.commands_.isEmpty()) {
                if (this.commandsBuilder_.isEmpty()) {
                    this.commandsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Command, com.lightstep.tracer.grpc.Command.Builder, CommandOrBuilder> repeatedFieldBuilderV3 = null;
                    this.commandsBuilder_ = null;
                    this.commands_ = reportResponse.commands_;
                    this.bitField0_ &= -2;
                    if (ReportResponse.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getCommandsFieldBuilder();
                    }
                    this.commandsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.commandsBuilder_.addAllMessages(reportResponse.commands_);
                }
            }
            if (reportResponse.hasReceiveTimestamp()) {
                mergeReceiveTimestamp(reportResponse.getReceiveTimestamp());
            }
            if (reportResponse.hasTransmitTimestamp()) {
                mergeTransmitTimestamp(reportResponse.getTransmitTimestamp());
            }
            if (!reportResponse.errors_.isEmpty()) {
                if (this.errors_.isEmpty()) {
                    this.errors_ = reportResponse.errors_;
                    this.bitField0_ &= -9;
                } else {
                    ensureErrorsIsMutable();
                    this.errors_.addAll(reportResponse.errors_);
                }
                onChanged();
            }
            if (!reportResponse.warnings_.isEmpty()) {
                if (this.warnings_.isEmpty()) {
                    this.warnings_ = reportResponse.warnings_;
                    this.bitField0_ &= -17;
                } else {
                    ensureWarningsIsMutable();
                    this.warnings_.addAll(reportResponse.warnings_);
                }
                onChanged();
            }
            if (!reportResponse.infos_.isEmpty()) {
                if (this.infos_.isEmpty()) {
                    this.infos_ = reportResponse.infos_;
                    this.bitField0_ &= -33;
                } else {
                    ensureInfosIsMutable();
                    this.infos_.addAll(reportResponse.infos_);
                }
                onChanged();
            }
            mergeUnknownFields(reportResponse.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.lightstep.tracer.grpc.ReportResponse.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.lightstep.tracer.grpc.ReportResponse.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.lightstep.tracer.grpc.ReportResponse r3 = (com.lightstep.tracer.grpc.ReportResponse) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.lightstep.tracer.grpc.ReportResponse r4 = (com.lightstep.tracer.grpc.ReportResponse) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.lightstep.tracer.grpc.ReportResponse.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.lightstep.tracer.grpc.ReportResponse$Builder");
        }

        private void ensureCommandsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.commands_ = new ArrayList(this.commands_);
                this.bitField0_ |= 1;
            }
        }

        private RepeatedFieldBuilderV3<Command, com.lightstep.tracer.grpc.Command.Builder, CommandOrBuilder> getCommandsFieldBuilder() {
            if (this.commandsBuilder_ == null) {
                List<Command> list = this.commands_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.commandsBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.commands_ = null;
            }
            return this.commandsBuilder_;
        }

        public Builder mergeReceiveTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.receiveTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.receiveTimestamp_;
                if (timestamp2 != null) {
                    this.receiveTimestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.receiveTimestamp_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        public Builder mergeTransmitTimestamp(Timestamp timestamp) {
            SingleFieldBuilderV3<Timestamp, com.google.protobuf.Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.transmitTimestampBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp2 = this.transmitTimestamp_;
                if (timestamp2 != null) {
                    this.transmitTimestamp_ = Timestamp.newBuilder(timestamp2).mergeFrom(timestamp).buildPartial();
                } else {
                    this.transmitTimestamp_ = timestamp;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(timestamp);
            }
            return this;
        }

        private void ensureErrorsIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.errors_ = new LazyStringArrayList(this.errors_);
                this.bitField0_ |= 8;
            }
        }

        private void ensureWarningsIsMutable() {
            if ((this.bitField0_ & 16) != 16) {
                this.warnings_ = new LazyStringArrayList(this.warnings_);
                this.bitField0_ |= 16;
            }
        }

        private void ensureInfosIsMutable() {
            if ((this.bitField0_ & 32) != 32) {
                this.infos_ = new LazyStringArrayList(this.infos_);
                this.bitField0_ |= 32;
            }
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private ReportResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ReportResponse() {
        this.memoizedIsInitialized = -1;
        this.commands_ = Collections.emptyList();
        this.errors_ = LazyStringArrayList.EMPTY;
        this.warnings_ = LazyStringArrayList.EMPTY;
        this.infos_ = LazyStringArrayList.EMPTY;
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ReportResponse(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag != 10) {
                        com.google.protobuf.Timestamp.Builder builder = null;
                        if (readTag == 18) {
                            if (this.receiveTimestamp_ != null) {
                                builder = this.receiveTimestamp_.toBuilder();
                            }
                            this.receiveTimestamp_ = (Timestamp) codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.receiveTimestamp_);
                                this.receiveTimestamp_ = builder.buildPartial();
                            }
                        } else if (readTag == 26) {
                            if (this.transmitTimestamp_ != null) {
                                builder = this.transmitTimestamp_.toBuilder();
                            }
                            this.transmitTimestamp_ = (Timestamp) codedInputStream.readMessage(Timestamp.parser(), extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.transmitTimestamp_);
                                this.transmitTimestamp_ = builder.buildPartial();
                            }
                        } else if (readTag == 34) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.errors_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.errors_.add(readStringRequireUtf8);
                        } else if (readTag == 42) {
                            String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.warnings_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.warnings_.add(readStringRequireUtf82);
                        } else if (readTag == 50) {
                            String readStringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.infos_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.infos_.add(readStringRequireUtf83);
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } else {
                        if (!z2 || !true) {
                            this.commands_ = new ArrayList();
                            z2 |= true;
                        }
                        this.commands_.add(codedInputStream.readMessage(Command.parser(), extensionRegistryLite));
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.commands_ = Collections.unmodifiableList(this.commands_);
                    }
                    if (z2 & true) {
                        this.errors_ = this.errors_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.warnings_ = this.warnings_.getUnmodifiableView();
                    }
                    if (z2 & true) {
                        this.infos_ = this.infos_.getUnmodifiableView();
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.commands_ = Collections.unmodifiableList(this.commands_);
            }
            if (z2 & true) {
                this.errors_ = this.errors_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.warnings_ = this.warnings_.getUnmodifiableView();
            }
            if (z2 & true) {
                this.infos_ = this.infos_.getUnmodifiableView();
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return Collector.internal_static_lightstep_collector_ReportResponse_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return Collector.internal_static_lightstep_collector_ReportResponse_fieldAccessorTable.ensureFieldAccessorsInitialized(ReportResponse.class, Builder.class);
    }

    public List<Command> getCommandsList() {
        return this.commands_;
    }

    public int getCommandsCount() {
        return this.commands_.size();
    }

    public boolean hasReceiveTimestamp() {
        return this.receiveTimestamp_ != null;
    }

    public Timestamp getReceiveTimestamp() {
        Timestamp timestamp = this.receiveTimestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    public boolean hasTransmitTimestamp() {
        return this.transmitTimestamp_ != null;
    }

    public Timestamp getTransmitTimestamp() {
        Timestamp timestamp = this.transmitTimestamp_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    public ProtocolStringList getErrorsList() {
        return this.errors_;
    }

    public int getErrorsCount() {
        return this.errors_.size();
    }

    public ProtocolStringList getWarningsList() {
        return this.warnings_;
    }

    public int getWarningsCount() {
        return this.warnings_.size();
    }

    public ProtocolStringList getInfosList() {
        return this.infos_;
    }

    public int getInfosCount() {
        return this.infos_.size();
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
        for (int i = 0; i < this.commands_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.commands_.get(i));
        }
        if (this.receiveTimestamp_ != null) {
            codedOutputStream.writeMessage(2, getReceiveTimestamp());
        }
        if (this.transmitTimestamp_ != null) {
            codedOutputStream.writeMessage(3, getTransmitTimestamp());
        }
        for (int i2 = 0; i2 < this.errors_.size(); i2++) {
            GeneratedMessageV3.writeString(codedOutputStream, 4, this.errors_.getRaw(i2));
        }
        for (int i3 = 0; i3 < this.warnings_.size(); i3++) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.warnings_.getRaw(i3));
        }
        for (int i4 = 0; i4 < this.infos_.size(); i4++) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.infos_.getRaw(i4));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.commands_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.commands_.get(i3));
        }
        if (this.receiveTimestamp_ != null) {
            i2 += CodedOutputStream.computeMessageSize(2, getReceiveTimestamp());
        }
        if (this.transmitTimestamp_ != null) {
            i2 += CodedOutputStream.computeMessageSize(3, getTransmitTimestamp());
        }
        int i4 = 0;
        for (int i5 = 0; i5 < this.errors_.size(); i5++) {
            i4 += computeStringSizeNoTag(this.errors_.getRaw(i5));
        }
        int size = i2 + i4 + (getErrorsList().size() * 1);
        int i6 = 0;
        for (int i7 = 0; i7 < this.warnings_.size(); i7++) {
            i6 += computeStringSizeNoTag(this.warnings_.getRaw(i7));
        }
        int size2 = size + i6 + (getWarningsList().size() * 1);
        int i8 = 0;
        for (int i9 = 0; i9 < this.infos_.size(); i9++) {
            i8 += computeStringSizeNoTag(this.infos_.getRaw(i9));
        }
        int size3 = size2 + i8 + (getInfosList().size() * 1) + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReportResponse)) {
            return super.equals(obj);
        }
        ReportResponse reportResponse = (ReportResponse) obj;
        boolean z2 = (getCommandsList().equals(reportResponse.getCommandsList())) && hasReceiveTimestamp() == reportResponse.hasReceiveTimestamp();
        if (hasReceiveTimestamp()) {
            z2 = z2 && getReceiveTimestamp().equals(reportResponse.getReceiveTimestamp());
        }
        boolean z3 = z2 && hasTransmitTimestamp() == reportResponse.hasTransmitTimestamp();
        if (hasTransmitTimestamp()) {
            z3 = z3 && getTransmitTimestamp().equals(reportResponse.getTransmitTimestamp());
        }
        if (!(((z3 && getErrorsList().equals(reportResponse.getErrorsList())) && getWarningsList().equals(reportResponse.getWarningsList())) && getInfosList().equals(reportResponse.getInfosList())) || !this.unknownFields.equals(reportResponse.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getCommandsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getCommandsList().hashCode();
        }
        if (hasReceiveTimestamp()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getReceiveTimestamp().hashCode();
        }
        if (hasTransmitTimestamp()) {
            hashCode = (((hashCode * 37) + 3) * 53) + getTransmitTimestamp().hashCode();
        }
        if (getErrorsCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getErrorsList().hashCode();
        }
        if (getWarningsCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getWarningsList().hashCode();
        }
        if (getInfosCount() > 0) {
            hashCode = (((hashCode * 37) + 6) * 53) + getInfosList().hashCode();
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

    public static ReportResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ReportResponse> getParserForType() {
        return PARSER;
    }

    public ReportResponse getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
