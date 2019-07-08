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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolStringList;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Logging extends GeneratedMessageV3 implements LoggingOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    private static final Logging DEFAULT_INSTANCE = new Logging();
    /* access modifiers changed from: private */
    public static final Parser<Logging> PARSER = new AbstractParser<Logging>() {
        public Logging parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Logging(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<LoggingDestination> consumerDestinations_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<LoggingDestination> producerDestinations_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements LoggingOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> consumerDestinationsBuilder_;
        private List<LoggingDestination> consumerDestinations_;
        private RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> producerDestinationsBuilder_;
        private List<LoggingDestination> producerDestinations_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_api_Logging_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_api_Logging_fieldAccessorTable.ensureFieldAccessorsInitialized(Logging.class, Builder.class);
        }

        private Builder() {
            this.producerDestinations_ = Collections.emptyList();
            this.consumerDestinations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.producerDestinations_ = Collections.emptyList();
            this.consumerDestinations_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Logging.alwaysUseFieldBuilders) {
                getProducerDestinationsFieldBuilder();
                getConsumerDestinationsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.producerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV32 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.consumerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return LoggingProto.internal_static_google_api_Logging_descriptor;
        }

        public Logging getDefaultInstanceForType() {
            return Logging.getDefaultInstance();
        }

        public Logging build() {
            Logging buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Logging buildPartial() {
            Logging logging = new Logging((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.producerDestinations_ = Collections.unmodifiableList(this.producerDestinations_);
                    this.bitField0_ &= -2;
                }
                logging.producerDestinations_ = this.producerDestinations_;
            } else {
                logging.producerDestinations_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV32 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
                    this.bitField0_ &= -3;
                }
                logging.consumerDestinations_ = this.consumerDestinations_;
            } else {
                logging.consumerDestinations_ = repeatedFieldBuilderV32.build();
            }
            onBuilt();
            return logging;
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
            if (message instanceof Logging) {
                return mergeFrom((Logging) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Logging logging) {
            if (logging == Logging.getDefaultInstance()) {
                return this;
            }
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.producerDestinationsBuilder_ == null) {
                if (!logging.producerDestinations_.isEmpty()) {
                    if (this.producerDestinations_.isEmpty()) {
                        this.producerDestinations_ = logging.producerDestinations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureProducerDestinationsIsMutable();
                        this.producerDestinations_.addAll(logging.producerDestinations_);
                    }
                    onChanged();
                }
            } else if (!logging.producerDestinations_.isEmpty()) {
                if (this.producerDestinationsBuilder_.isEmpty()) {
                    this.producerDestinationsBuilder_.dispose();
                    this.producerDestinationsBuilder_ = null;
                    this.producerDestinations_ = logging.producerDestinations_;
                    this.bitField0_ &= -2;
                    this.producerDestinationsBuilder_ = Logging.alwaysUseFieldBuilders ? getProducerDestinationsFieldBuilder() : null;
                } else {
                    this.producerDestinationsBuilder_.addAllMessages(logging.producerDestinations_);
                }
            }
            if (this.consumerDestinationsBuilder_ == null) {
                if (!logging.consumerDestinations_.isEmpty()) {
                    if (this.consumerDestinations_.isEmpty()) {
                        this.consumerDestinations_ = logging.consumerDestinations_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureConsumerDestinationsIsMutable();
                        this.consumerDestinations_.addAll(logging.consumerDestinations_);
                    }
                    onChanged();
                }
            } else if (!logging.consumerDestinations_.isEmpty()) {
                if (this.consumerDestinationsBuilder_.isEmpty()) {
                    this.consumerDestinationsBuilder_.dispose();
                    this.consumerDestinationsBuilder_ = null;
                    this.consumerDestinations_ = logging.consumerDestinations_;
                    this.bitField0_ &= -3;
                    if (Logging.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getConsumerDestinationsFieldBuilder();
                    }
                    this.consumerDestinationsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.consumerDestinationsBuilder_.addAllMessages(logging.consumerDestinations_);
                }
            }
            mergeUnknownFields(logging.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Logging.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Logging.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Logging r3 = (com.google.api.Logging) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Logging r4 = (com.google.api.Logging) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Logging.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Logging$Builder");
        }

        private void ensureProducerDestinationsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.producerDestinations_ = new ArrayList(this.producerDestinations_);
                this.bitField0_ |= 1;
            }
        }

        public List<LoggingDestination> getProducerDestinationsList() {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.producerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getProducerDestinationsCount() {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.producerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public LoggingDestination getProducerDestinations(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LoggingDestination) this.producerDestinations_.get(i);
            }
            return (LoggingDestination) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setProducerDestinations(int i, LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, loggingDestination);
            } else if (loggingDestination != null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.set(i, loggingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setProducerDestinations(int i, Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addProducerDestinations(LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(loggingDestination);
            } else if (loggingDestination != null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(loggingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addProducerDestinations(int i, LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, loggingDestination);
            } else if (loggingDestination != null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(i, loggingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addProducerDestinations(Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addProducerDestinations(int i, Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends LoggingDestination> iterable) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.producerDestinations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearProducerDestinations() {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.producerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeProducerDestinations(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getProducerDestinationsBuilder(int i) {
            return (Builder) getProducerDestinationsFieldBuilder().getBuilder(i);
        }

        public LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LoggingDestinationOrBuilder) this.producerDestinations_.get(i);
            }
            return (LoggingDestinationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.producerDestinations_);
        }

        public Builder addProducerDestinationsBuilder() {
            return (Builder) getProducerDestinationsFieldBuilder().addBuilder(LoggingDestination.getDefaultInstance());
        }

        public Builder addProducerDestinationsBuilder(int i) {
            return (Builder) getProducerDestinationsFieldBuilder().addBuilder(i, LoggingDestination.getDefaultInstance());
        }

        public List<Builder> getProducerDestinationsBuilderList() {
            return getProducerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> getProducerDestinationsFieldBuilder() {
            if (this.producerDestinationsBuilder_ == null) {
                List<LoggingDestination> list = this.producerDestinations_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.producerDestinationsBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.producerDestinations_ = null;
            }
            return this.producerDestinationsBuilder_;
        }

        private void ensureConsumerDestinationsIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.consumerDestinations_ = new ArrayList(this.consumerDestinations_);
                this.bitField0_ |= 2;
            }
        }

        public List<LoggingDestination> getConsumerDestinationsList() {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.consumerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getConsumerDestinationsCount() {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public LoggingDestination getConsumerDestinations(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LoggingDestination) this.consumerDestinations_.get(i);
            }
            return (LoggingDestination) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setConsumerDestinations(int i, LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, loggingDestination);
            } else if (loggingDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, loggingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setConsumerDestinations(int i, Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(loggingDestination);
            } else if (loggingDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(loggingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, LoggingDestination loggingDestination) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, loggingDestination);
            } else if (loggingDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, loggingDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addConsumerDestinations(Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, Builder builder) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends LoggingDestination> iterable) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.consumerDestinations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearConsumerDestinations() {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.consumerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeConsumerDestinations(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public Builder getConsumerDestinationsBuilder(int i) {
            return (Builder) getConsumerDestinationsFieldBuilder().getBuilder(i);
        }

        public LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LoggingDestinationOrBuilder) this.consumerDestinations_.get(i);
            }
            return (LoggingDestinationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.consumerDestinations_);
        }

        public Builder addConsumerDestinationsBuilder() {
            return (Builder) getConsumerDestinationsFieldBuilder().addBuilder(LoggingDestination.getDefaultInstance());
        }

        public Builder addConsumerDestinationsBuilder(int i) {
            return (Builder) getConsumerDestinationsFieldBuilder().addBuilder(i, LoggingDestination.getDefaultInstance());
        }

        public List<Builder> getConsumerDestinationsBuilderList() {
            return getConsumerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LoggingDestination, Builder, LoggingDestinationOrBuilder> getConsumerDestinationsFieldBuilder() {
            if (this.consumerDestinationsBuilder_ == null) {
                this.consumerDestinationsBuilder_ = new RepeatedFieldBuilderV3<>(this.consumerDestinations_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.consumerDestinations_ = null;
            }
            return this.consumerDestinationsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    public static final class LoggingDestination extends GeneratedMessageV3 implements LoggingDestinationOrBuilder {
        private static final LoggingDestination DEFAULT_INSTANCE = new LoggingDestination();
        public static final int LOGS_FIELD_NUMBER = 1;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final Parser<LoggingDestination> PARSER = new AbstractParser<LoggingDestination>() {
            public LoggingDestination parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new LoggingDestination(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public LazyStringList logs_;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public volatile Object monitoredResource_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements LoggingDestinationOrBuilder {
            private int bitField0_;
            private LazyStringList logs_;
            private Object monitoredResource_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return LoggingProto.internal_static_google_api_Logging_LoggingDestination_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return LoggingProto.internal_static_google_api_Logging_LoggingDestination_fieldAccessorTable.ensureFieldAccessorsInitialized(LoggingDestination.class, Builder.class);
            }

            private Builder() {
                this.monitoredResource_ = "";
                this.logs_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.monitoredResource_ = "";
                this.logs_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                LoggingDestination.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.monitoredResource_ = "";
                this.logs_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Descriptor getDescriptorForType() {
                return LoggingProto.internal_static_google_api_Logging_LoggingDestination_descriptor;
            }

            public LoggingDestination getDefaultInstanceForType() {
                return LoggingDestination.getDefaultInstance();
            }

            public LoggingDestination build() {
                LoggingDestination buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public LoggingDestination buildPartial() {
                LoggingDestination loggingDestination = new LoggingDestination((com.google.protobuf.GeneratedMessageV3.Builder) this);
                int i = this.bitField0_;
                loggingDestination.monitoredResource_ = this.monitoredResource_;
                if ((this.bitField0_ & 2) == 2) {
                    this.logs_ = this.logs_.getUnmodifiableView();
                    this.bitField0_ &= -3;
                }
                loggingDestination.logs_ = this.logs_;
                loggingDestination.bitField0_ = 0;
                onBuilt();
                return loggingDestination;
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
                if (message instanceof LoggingDestination) {
                    return mergeFrom((LoggingDestination) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(LoggingDestination loggingDestination) {
                if (loggingDestination == LoggingDestination.getDefaultInstance()) {
                    return this;
                }
                if (!loggingDestination.getMonitoredResource().isEmpty()) {
                    this.monitoredResource_ = loggingDestination.monitoredResource_;
                    onChanged();
                }
                if (!loggingDestination.logs_.isEmpty()) {
                    if (this.logs_.isEmpty()) {
                        this.logs_ = loggingDestination.logs_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureLogsIsMutable();
                        this.logs_.addAll(loggingDestination.logs_);
                    }
                    onChanged();
                }
                mergeUnknownFields(loggingDestination.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.api.Logging.LoggingDestination.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.api.Logging.LoggingDestination.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    com.google.api.Logging$LoggingDestination r3 = (com.google.api.Logging.LoggingDestination) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                    com.google.api.Logging$LoggingDestination r4 = (com.google.api.Logging.LoggingDestination) r4     // Catch:{ all -> 0x0011 }
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.api.Logging.LoggingDestination.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Logging$LoggingDestination$Builder");
            }

            public String getMonitoredResource() {
                Object obj = this.monitoredResource_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.monitoredResource_ = stringUtf8;
                return stringUtf8;
            }

            public ByteString getMonitoredResourceBytes() {
                Object obj = this.monitoredResource_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.monitoredResource_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setMonitoredResource(String str) {
                if (str != null) {
                    this.monitoredResource_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMonitoredResource() {
                this.monitoredResource_ = LoggingDestination.getDefaultInstance().getMonitoredResource();
                onChanged();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString byteString) {
                if (byteString != null) {
                    LoggingDestination.checkByteStringIsUtf8(byteString);
                    this.monitoredResource_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureLogsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.logs_ = new LazyStringArrayList(this.logs_);
                    this.bitField0_ |= 2;
                }
            }

            public ProtocolStringList getLogsList() {
                return this.logs_.getUnmodifiableView();
            }

            public int getLogsCount() {
                return this.logs_.size();
            }

            public String getLogs(int i) {
                return (String) this.logs_.get(i);
            }

            public ByteString getLogsBytes(int i) {
                return this.logs_.getByteString(i);
            }

            public Builder setLogs(int i, String str) {
                if (str != null) {
                    ensureLogsIsMutable();
                    this.logs_.set(i, str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addLogs(String str) {
                if (str != null) {
                    ensureLogsIsMutable();
                    this.logs_.add(str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addAllLogs(Iterable<String> iterable) {
                ensureLogsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.logs_);
                onChanged();
                return this;
            }

            public Builder clearLogs() {
                this.logs_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            public Builder addLogsBytes(ByteString byteString) {
                if (byteString != null) {
                    LoggingDestination.checkByteStringIsUtf8(byteString);
                    ensureLogsIsMutable();
                    this.logs_.add(byteString);
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

        private LoggingDestination(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private LoggingDestination() {
            this.memoizedIsInitialized = -1;
            this.monitoredResource_ = "";
            this.logs_ = LazyStringArrayList.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private LoggingDestination(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.logs_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.logs_.add(readStringRequireUtf8);
                        } else if (readTag == 26) {
                            this.monitoredResource_ = codedInputStream.readStringRequireUtf8();
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.logs_ = this.logs_.getUnmodifiableView();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.logs_ = this.logs_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                return;
            }
            throw new NullPointerException();
        }

        public static final Descriptor getDescriptor() {
            return LoggingProto.internal_static_google_api_Logging_LoggingDestination_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return LoggingProto.internal_static_google_api_Logging_LoggingDestination_fieldAccessorTable.ensureFieldAccessorsInitialized(LoggingDestination.class, Builder.class);
        }

        public String getMonitoredResource() {
            Object obj = this.monitoredResource_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.monitoredResource_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getMonitoredResourceBytes() {
            Object obj = this.monitoredResource_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.monitoredResource_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public ProtocolStringList getLogsList() {
            return this.logs_;
        }

        public int getLogsCount() {
            return this.logs_.size();
        }

        public String getLogs(int i) {
            return (String) this.logs_.get(i);
        }

        public ByteString getLogsBytes(int i) {
            return this.logs_.getByteString(i);
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
            for (int i = 0; i < this.logs_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.logs_.getRaw(i));
            }
            if (!getMonitoredResourceBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 3, this.monitoredResource_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.logs_.size(); i3++) {
                i2 += computeStringSizeNoTag(this.logs_.getRaw(i3));
            }
            int size = 0 + i2 + (getLogsList().size() * 1);
            if (!getMonitoredResourceBytes().isEmpty()) {
                size += GeneratedMessageV3.computeStringSize(3, this.monitoredResource_);
            }
            int serializedSize = size + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof LoggingDestination)) {
                return super.equals(obj);
            }
            LoggingDestination loggingDestination = (LoggingDestination) obj;
            if (!((getMonitoredResource().equals(loggingDestination.getMonitoredResource())) && getLogsList().equals(loggingDestination.getLogsList())) || !this.unknownFields.equals(loggingDestination.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 3) * 53) + getMonitoredResource().hashCode();
            if (getLogsCount() > 0) {
                hashCode = (((hashCode * 37) + 1) * 53) + getLogsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static LoggingDestination parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (LoggingDestination) PARSER.parseFrom(byteBuffer);
        }

        public static LoggingDestination parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LoggingDestination) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (LoggingDestination) PARSER.parseFrom(byteString);
        }

        public static LoggingDestination parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LoggingDestination) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (LoggingDestination) PARSER.parseFrom(bArr);
        }

        public static LoggingDestination parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (LoggingDestination) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(InputStream inputStream) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static LoggingDestination parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static LoggingDestination parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static LoggingDestination parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static LoggingDestination parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (LoggingDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(LoggingDestination loggingDestination) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(loggingDestination);
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

        public static LoggingDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<LoggingDestination> parser() {
            return PARSER;
        }

        public Parser<LoggingDestination> getParserForType() {
            return PARSER;
        }

        public LoggingDestination getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface LoggingDestinationOrBuilder extends MessageOrBuilder {
        String getLogs(int i);

        ByteString getLogsBytes(int i);

        int getLogsCount();

        List<String> getLogsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    private Logging(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Logging() {
        this.memoizedIsInitialized = -1;
        this.producerDestinations_ = Collections.emptyList();
        this.consumerDestinations_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Logging(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.producerDestinations_ = new ArrayList();
                            z2 |= true;
                        }
                        this.producerDestinations_.add(codedInputStream.readMessage(LoggingDestination.parser(), extensionRegistryLite));
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.consumerDestinations_ = new ArrayList();
                            z2 |= true;
                        }
                        this.consumerDestinations_.add(codedInputStream.readMessage(LoggingDestination.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.producerDestinations_ = Collections.unmodifiableList(this.producerDestinations_);
                    }
                    if (z2 & true) {
                        this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.producerDestinations_ = Collections.unmodifiableList(this.producerDestinations_);
            }
            if (z2 & true) {
                this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return LoggingProto.internal_static_google_api_Logging_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return LoggingProto.internal_static_google_api_Logging_fieldAccessorTable.ensureFieldAccessorsInitialized(Logging.class, Builder.class);
    }

    public List<LoggingDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    public List<? extends LoggingDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    public LoggingDestination getProducerDestinations(int i) {
        return (LoggingDestination) this.producerDestinations_.get(i);
    }

    public LoggingDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
        return (LoggingDestinationOrBuilder) this.producerDestinations_.get(i);
    }

    public List<LoggingDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public List<? extends LoggingDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    public LoggingDestination getConsumerDestinations(int i) {
        return (LoggingDestination) this.consumerDestinations_.get(i);
    }

    public LoggingDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
        return (LoggingDestinationOrBuilder) this.consumerDestinations_.get(i);
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
        for (int i = 0; i < this.producerDestinations_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.producerDestinations_.get(i));
        }
        for (int i2 = 0; i2 < this.consumerDestinations_.size(); i2++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.consumerDestinations_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.producerDestinations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.producerDestinations_.get(i3));
        }
        for (int i4 = 0; i4 < this.consumerDestinations_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(2, (MessageLite) this.consumerDestinations_.get(i4));
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
        if (!(obj instanceof Logging)) {
            return super.equals(obj);
        }
        Logging logging = (Logging) obj;
        if (!((getProducerDestinationsList().equals(logging.getProducerDestinationsList())) && getConsumerDestinationsList().equals(logging.getConsumerDestinationsList())) || !this.unknownFields.equals(logging.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getProducerDestinationsCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getProducerDestinationsList().hashCode();
        }
        if (getConsumerDestinationsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getConsumerDestinationsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Logging parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Logging) PARSER.parseFrom(byteBuffer);
    }

    public static Logging parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Logging) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Logging parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Logging) PARSER.parseFrom(byteString);
    }

    public static Logging parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Logging) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Logging parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Logging) PARSER.parseFrom(bArr);
    }

    public static Logging parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Logging) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Logging parseFrom(InputStream inputStream) throws IOException {
        return (Logging) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Logging parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Logging) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Logging parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Logging) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Logging parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Logging) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Logging parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Logging) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Logging parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Logging) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Logging logging) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(logging);
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

    public static Logging getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Logging> parser() {
        return PARSER;
    }

    public Parser<Logging> getParserForType() {
        return PARSER;
    }

    public Logging getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
