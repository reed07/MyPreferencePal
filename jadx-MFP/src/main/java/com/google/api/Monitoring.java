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

public final class Monitoring extends GeneratedMessageV3 implements MonitoringOrBuilder {
    public static final int CONSUMER_DESTINATIONS_FIELD_NUMBER = 2;
    private static final Monitoring DEFAULT_INSTANCE = new Monitoring();
    /* access modifiers changed from: private */
    public static final Parser<Monitoring> PARSER = new AbstractParser<Monitoring>() {
        public Monitoring parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Monitoring(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PRODUCER_DESTINATIONS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<MonitoringDestination> consumerDestinations_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<MonitoringDestination> producerDestinations_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MonitoringOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> consumerDestinationsBuilder_;
        private List<MonitoringDestination> consumerDestinations_;
        private RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> producerDestinationsBuilder_;
        private List<MonitoringDestination> producerDestinations_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return MonitoringProto.internal_static_google_api_Monitoring_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoringProto.internal_static_google_api_Monitoring_fieldAccessorTable.ensureFieldAccessorsInitialized(Monitoring.class, Builder.class);
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
            if (Monitoring.alwaysUseFieldBuilders) {
                getProducerDestinationsFieldBuilder();
                getConsumerDestinationsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.producerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV32 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.consumerDestinations_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return MonitoringProto.internal_static_google_api_Monitoring_descriptor;
        }

        public Monitoring getDefaultInstanceForType() {
            return Monitoring.getDefaultInstance();
        }

        public Monitoring build() {
            Monitoring buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Monitoring buildPartial() {
            Monitoring monitoring = new Monitoring((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.producerDestinations_ = Collections.unmodifiableList(this.producerDestinations_);
                    this.bitField0_ &= -2;
                }
                monitoring.producerDestinations_ = this.producerDestinations_;
            } else {
                monitoring.producerDestinations_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV32 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.consumerDestinations_ = Collections.unmodifiableList(this.consumerDestinations_);
                    this.bitField0_ &= -3;
                }
                monitoring.consumerDestinations_ = this.consumerDestinations_;
            } else {
                monitoring.consumerDestinations_ = repeatedFieldBuilderV32.build();
            }
            onBuilt();
            return monitoring;
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
            if (message instanceof Monitoring) {
                return mergeFrom((Monitoring) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Monitoring monitoring) {
            if (monitoring == Monitoring.getDefaultInstance()) {
                return this;
            }
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.producerDestinationsBuilder_ == null) {
                if (!monitoring.producerDestinations_.isEmpty()) {
                    if (this.producerDestinations_.isEmpty()) {
                        this.producerDestinations_ = monitoring.producerDestinations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureProducerDestinationsIsMutable();
                        this.producerDestinations_.addAll(monitoring.producerDestinations_);
                    }
                    onChanged();
                }
            } else if (!monitoring.producerDestinations_.isEmpty()) {
                if (this.producerDestinationsBuilder_.isEmpty()) {
                    this.producerDestinationsBuilder_.dispose();
                    this.producerDestinationsBuilder_ = null;
                    this.producerDestinations_ = monitoring.producerDestinations_;
                    this.bitField0_ &= -2;
                    this.producerDestinationsBuilder_ = Monitoring.alwaysUseFieldBuilders ? getProducerDestinationsFieldBuilder() : null;
                } else {
                    this.producerDestinationsBuilder_.addAllMessages(monitoring.producerDestinations_);
                }
            }
            if (this.consumerDestinationsBuilder_ == null) {
                if (!monitoring.consumerDestinations_.isEmpty()) {
                    if (this.consumerDestinations_.isEmpty()) {
                        this.consumerDestinations_ = monitoring.consumerDestinations_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureConsumerDestinationsIsMutable();
                        this.consumerDestinations_.addAll(monitoring.consumerDestinations_);
                    }
                    onChanged();
                }
            } else if (!monitoring.consumerDestinations_.isEmpty()) {
                if (this.consumerDestinationsBuilder_.isEmpty()) {
                    this.consumerDestinationsBuilder_.dispose();
                    this.consumerDestinationsBuilder_ = null;
                    this.consumerDestinations_ = monitoring.consumerDestinations_;
                    this.bitField0_ &= -3;
                    if (Monitoring.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getConsumerDestinationsFieldBuilder();
                    }
                    this.consumerDestinationsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.consumerDestinationsBuilder_.addAllMessages(monitoring.consumerDestinations_);
                }
            }
            mergeUnknownFields(monitoring.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Monitoring.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Monitoring.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Monitoring r3 = (com.google.api.Monitoring) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Monitoring r4 = (com.google.api.Monitoring) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Monitoring.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Monitoring$Builder");
        }

        private void ensureProducerDestinationsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.producerDestinations_ = new ArrayList(this.producerDestinations_);
                this.bitField0_ |= 1;
            }
        }

        public List<MonitoringDestination> getProducerDestinationsList() {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.producerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getProducerDestinationsCount() {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.producerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public MonitoringDestination getProducerDestinations(int i) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MonitoringDestination) this.producerDestinations_.get(i);
            }
            return (MonitoringDestination) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setProducerDestinations(int i, MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, monitoringDestination);
            } else if (monitoringDestination != null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.set(i, monitoringDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setProducerDestinations(int i, Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addProducerDestinations(MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(monitoringDestination);
            } else if (monitoringDestination != null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(monitoringDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addProducerDestinations(int i, MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, monitoringDestination);
            } else if (monitoringDestination != null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(i, monitoringDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addProducerDestinations(Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProducerDestinationsIsMutable();
                this.producerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllProducerDestinations(Iterable<? extends MonitoringDestination> iterable) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
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

        public MonitoringDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MonitoringDestinationOrBuilder) this.producerDestinations_.get(i);
            }
            return (MonitoringDestinationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends MonitoringDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.producerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.producerDestinations_);
        }

        public Builder addProducerDestinationsBuilder() {
            return (Builder) getProducerDestinationsFieldBuilder().addBuilder(MonitoringDestination.getDefaultInstance());
        }

        public Builder addProducerDestinationsBuilder(int i) {
            return (Builder) getProducerDestinationsFieldBuilder().addBuilder(i, MonitoringDestination.getDefaultInstance());
        }

        public List<Builder> getProducerDestinationsBuilderList() {
            return getProducerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> getProducerDestinationsFieldBuilder() {
            if (this.producerDestinationsBuilder_ == null) {
                List<MonitoringDestination> list = this.producerDestinations_;
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

        public List<MonitoringDestination> getConsumerDestinationsList() {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.consumerDestinations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getConsumerDestinationsCount() {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.consumerDestinations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public MonitoringDestination getConsumerDestinations(int i) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MonitoringDestination) this.consumerDestinations_.get(i);
            }
            return (MonitoringDestination) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setConsumerDestinations(int i, MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, monitoringDestination);
            } else if (monitoringDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, monitoringDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setConsumerDestinations(int i, Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addConsumerDestinations(MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(monitoringDestination);
            } else if (monitoringDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(monitoringDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addConsumerDestinations(int i, MonitoringDestination monitoringDestination) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, monitoringDestination);
            } else if (monitoringDestination != null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, monitoringDestination);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addConsumerDestinations(Builder builder) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConsumerDestinationsIsMutable();
                this.consumerDestinations_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllConsumerDestinations(Iterable<? extends MonitoringDestination> iterable) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
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
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
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

        public MonitoringDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MonitoringDestinationOrBuilder) this.consumerDestinations_.get(i);
            }
            return (MonitoringDestinationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends MonitoringDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
            RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> repeatedFieldBuilderV3 = this.consumerDestinationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.consumerDestinations_);
        }

        public Builder addConsumerDestinationsBuilder() {
            return (Builder) getConsumerDestinationsFieldBuilder().addBuilder(MonitoringDestination.getDefaultInstance());
        }

        public Builder addConsumerDestinationsBuilder(int i) {
            return (Builder) getConsumerDestinationsFieldBuilder().addBuilder(i, MonitoringDestination.getDefaultInstance());
        }

        public List<Builder> getConsumerDestinationsBuilderList() {
            return getConsumerDestinationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MonitoringDestination, Builder, MonitoringDestinationOrBuilder> getConsumerDestinationsFieldBuilder() {
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

    public static final class MonitoringDestination extends GeneratedMessageV3 implements MonitoringDestinationOrBuilder {
        private static final MonitoringDestination DEFAULT_INSTANCE = new MonitoringDestination();
        public static final int METRICS_FIELD_NUMBER = 2;
        public static final int MONITORED_RESOURCE_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final Parser<MonitoringDestination> PARSER = new AbstractParser<MonitoringDestination>() {
            public MonitoringDestination parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new MonitoringDestination(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        /* access modifiers changed from: private */
        public LazyStringList metrics_;
        /* access modifiers changed from: private */
        public volatile Object monitoredResource_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MonitoringDestinationOrBuilder {
            private int bitField0_;
            private LazyStringList metrics_;
            private Object monitoredResource_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return MonitoringProto.internal_static_google_api_Monitoring_MonitoringDestination_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return MonitoringProto.internal_static_google_api_Monitoring_MonitoringDestination_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoringDestination.class, Builder.class);
            }

            private Builder() {
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                MonitoringDestination.alwaysUseFieldBuilders;
            }

            public Builder clear() {
                super.clear();
                this.monitoredResource_ = "";
                this.metrics_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Descriptor getDescriptorForType() {
                return MonitoringProto.internal_static_google_api_Monitoring_MonitoringDestination_descriptor;
            }

            public MonitoringDestination getDefaultInstanceForType() {
                return MonitoringDestination.getDefaultInstance();
            }

            public MonitoringDestination build() {
                MonitoringDestination buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public MonitoringDestination buildPartial() {
                MonitoringDestination monitoringDestination = new MonitoringDestination((com.google.protobuf.GeneratedMessageV3.Builder) this);
                int i = this.bitField0_;
                monitoringDestination.monitoredResource_ = this.monitoredResource_;
                if ((this.bitField0_ & 2) == 2) {
                    this.metrics_ = this.metrics_.getUnmodifiableView();
                    this.bitField0_ &= -3;
                }
                monitoringDestination.metrics_ = this.metrics_;
                monitoringDestination.bitField0_ = 0;
                onBuilt();
                return monitoringDestination;
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
                if (message instanceof MonitoringDestination) {
                    return mergeFrom((MonitoringDestination) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(MonitoringDestination monitoringDestination) {
                if (monitoringDestination == MonitoringDestination.getDefaultInstance()) {
                    return this;
                }
                if (!monitoringDestination.getMonitoredResource().isEmpty()) {
                    this.monitoredResource_ = monitoringDestination.monitoredResource_;
                    onChanged();
                }
                if (!monitoringDestination.metrics_.isEmpty()) {
                    if (this.metrics_.isEmpty()) {
                        this.metrics_ = monitoringDestination.metrics_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureMetricsIsMutable();
                        this.metrics_.addAll(monitoringDestination.metrics_);
                    }
                    onChanged();
                }
                mergeUnknownFields(monitoringDestination.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.google.api.Monitoring.MonitoringDestination.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = com.google.api.Monitoring.MonitoringDestination.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                    com.google.api.Monitoring$MonitoringDestination r3 = (com.google.api.Monitoring.MonitoringDestination) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                    com.google.api.Monitoring$MonitoringDestination r4 = (com.google.api.Monitoring.MonitoringDestination) r4     // Catch:{ all -> 0x0011 }
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
                throw new UnsupportedOperationException("Method not decompiled: com.google.api.Monitoring.MonitoringDestination.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Monitoring$MonitoringDestination$Builder");
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
                this.monitoredResource_ = MonitoringDestination.getDefaultInstance().getMonitoredResource();
                onChanged();
                return this;
            }

            public Builder setMonitoredResourceBytes(ByteString byteString) {
                if (byteString != null) {
                    MonitoringDestination.checkByteStringIsUtf8(byteString);
                    this.monitoredResource_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureMetricsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.metrics_ = new LazyStringArrayList(this.metrics_);
                    this.bitField0_ |= 2;
                }
            }

            public ProtocolStringList getMetricsList() {
                return this.metrics_.getUnmodifiableView();
            }

            public int getMetricsCount() {
                return this.metrics_.size();
            }

            public String getMetrics(int i) {
                return (String) this.metrics_.get(i);
            }

            public ByteString getMetricsBytes(int i) {
                return this.metrics_.getByteString(i);
            }

            public Builder setMetrics(int i, String str) {
                if (str != null) {
                    ensureMetricsIsMutable();
                    this.metrics_.set(i, str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addMetrics(String str) {
                if (str != null) {
                    ensureMetricsIsMutable();
                    this.metrics_.add(str);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addAllMetrics(Iterable<String> iterable) {
                ensureMetricsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, (List<? super T>) this.metrics_);
                onChanged();
                return this;
            }

            public Builder clearMetrics() {
                this.metrics_ = LazyStringArrayList.EMPTY;
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            public Builder addMetricsBytes(ByteString byteString) {
                if (byteString != null) {
                    MonitoringDestination.checkByteStringIsUtf8(byteString);
                    ensureMetricsIsMutable();
                    this.metrics_.add(byteString);
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

        private MonitoringDestination(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
        }

        private MonitoringDestination() {
            this.memoizedIsInitialized = -1;
            this.monitoredResource_ = "";
            this.metrics_ = LazyStringArrayList.EMPTY;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MonitoringDestination(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.monitoredResource_ = codedInputStream.readStringRequireUtf8();
                        } else if (readTag == 18) {
                            String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            if (!(z2 & true)) {
                                this.metrics_ = new LazyStringArrayList();
                                z2 |= true;
                            }
                            this.metrics_.add(readStringRequireUtf8);
                        } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                            z = true;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    } catch (Throwable th) {
                        if (z2 & true) {
                            this.metrics_ = this.metrics_.getUnmodifiableView();
                        }
                        this.unknownFields = newBuilder.build();
                        makeExtensionsImmutable();
                        throw th;
                    }
                }
                if (z2 & true) {
                    this.metrics_ = this.metrics_.getUnmodifiableView();
                }
                this.unknownFields = newBuilder.build();
                makeExtensionsImmutable();
                return;
            }
            throw new NullPointerException();
        }

        public static final Descriptor getDescriptor() {
            return MonitoringProto.internal_static_google_api_Monitoring_MonitoringDestination_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoringProto.internal_static_google_api_Monitoring_MonitoringDestination_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoringDestination.class, Builder.class);
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

        public ProtocolStringList getMetricsList() {
            return this.metrics_;
        }

        public int getMetricsCount() {
            return this.metrics_.size();
        }

        public String getMetrics(int i) {
            return (String) this.metrics_.get(i);
        }

        public ByteString getMetricsBytes(int i) {
            return this.metrics_.getByteString(i);
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
            if (!getMonitoredResourceBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.monitoredResource_);
            }
            for (int i = 0; i < this.metrics_.size(); i++) {
                GeneratedMessageV3.writeString(codedOutputStream, 2, this.metrics_.getRaw(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = !getMonitoredResourceBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.monitoredResource_) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.metrics_.size(); i3++) {
                i2 += computeStringSizeNoTag(this.metrics_.getRaw(i3));
            }
            int size = computeStringSize + i2 + (getMetricsList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSize = size;
            return size;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MonitoringDestination)) {
                return super.equals(obj);
            }
            MonitoringDestination monitoringDestination = (MonitoringDestination) obj;
            if (!((getMonitoredResource().equals(monitoringDestination.getMonitoredResource())) && getMetricsList().equals(monitoringDestination.getMetricsList())) || !this.unknownFields.equals(monitoringDestination.unknownFields)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getMonitoredResource().hashCode();
            if (getMetricsCount() > 0) {
                hashCode = (((hashCode * 37) + 2) * 53) + getMetricsList().hashCode();
            }
            int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = hashCode2;
            return hashCode2;
        }

        public static MonitoringDestination parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (MonitoringDestination) PARSER.parseFrom(byteBuffer);
        }

        public static MonitoringDestination parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MonitoringDestination) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (MonitoringDestination) PARSER.parseFrom(byteString);
        }

        public static MonitoringDestination parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MonitoringDestination) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (MonitoringDestination) PARSER.parseFrom(bArr);
        }

        public static MonitoringDestination parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (MonitoringDestination) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(InputStream inputStream) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static MonitoringDestination parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MonitoringDestination parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static MonitoringDestination parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static MonitoringDestination parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static MonitoringDestination parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (MonitoringDestination) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(MonitoringDestination monitoringDestination) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoringDestination);
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

        public static MonitoringDestination getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<MonitoringDestination> parser() {
            return PARSER;
        }

        public Parser<MonitoringDestination> getParserForType() {
            return PARSER;
        }

        public MonitoringDestination getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }
    }

    public interface MonitoringDestinationOrBuilder extends MessageOrBuilder {
        String getMetrics(int i);

        ByteString getMetricsBytes(int i);

        int getMetricsCount();

        List<String> getMetricsList();

        String getMonitoredResource();

        ByteString getMonitoredResourceBytes();
    }

    private Monitoring(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Monitoring() {
        this.memoizedIsInitialized = -1;
        this.producerDestinations_ = Collections.emptyList();
        this.consumerDestinations_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Monitoring(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.producerDestinations_.add(codedInputStream.readMessage(MonitoringDestination.parser(), extensionRegistryLite));
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.consumerDestinations_ = new ArrayList();
                            z2 |= true;
                        }
                        this.consumerDestinations_.add(codedInputStream.readMessage(MonitoringDestination.parser(), extensionRegistryLite));
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
        return MonitoringProto.internal_static_google_api_Monitoring_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoringProto.internal_static_google_api_Monitoring_fieldAccessorTable.ensureFieldAccessorsInitialized(Monitoring.class, Builder.class);
    }

    public List<MonitoringDestination> getProducerDestinationsList() {
        return this.producerDestinations_;
    }

    public List<? extends MonitoringDestinationOrBuilder> getProducerDestinationsOrBuilderList() {
        return this.producerDestinations_;
    }

    public int getProducerDestinationsCount() {
        return this.producerDestinations_.size();
    }

    public MonitoringDestination getProducerDestinations(int i) {
        return (MonitoringDestination) this.producerDestinations_.get(i);
    }

    public MonitoringDestinationOrBuilder getProducerDestinationsOrBuilder(int i) {
        return (MonitoringDestinationOrBuilder) this.producerDestinations_.get(i);
    }

    public List<MonitoringDestination> getConsumerDestinationsList() {
        return this.consumerDestinations_;
    }

    public List<? extends MonitoringDestinationOrBuilder> getConsumerDestinationsOrBuilderList() {
        return this.consumerDestinations_;
    }

    public int getConsumerDestinationsCount() {
        return this.consumerDestinations_.size();
    }

    public MonitoringDestination getConsumerDestinations(int i) {
        return (MonitoringDestination) this.consumerDestinations_.get(i);
    }

    public MonitoringDestinationOrBuilder getConsumerDestinationsOrBuilder(int i) {
        return (MonitoringDestinationOrBuilder) this.consumerDestinations_.get(i);
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
        if (!(obj instanceof Monitoring)) {
            return super.equals(obj);
        }
        Monitoring monitoring = (Monitoring) obj;
        if (!((getProducerDestinationsList().equals(monitoring.getProducerDestinationsList())) && getConsumerDestinationsList().equals(monitoring.getConsumerDestinationsList())) || !this.unknownFields.equals(monitoring.unknownFields)) {
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

    public static Monitoring parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Monitoring) PARSER.parseFrom(byteBuffer);
    }

    public static Monitoring parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Monitoring) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Monitoring parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Monitoring) PARSER.parseFrom(byteString);
    }

    public static Monitoring parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Monitoring) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Monitoring parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Monitoring) PARSER.parseFrom(bArr);
    }

    public static Monitoring parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Monitoring) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Monitoring parseFrom(InputStream inputStream) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Monitoring parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Monitoring parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Monitoring parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Monitoring parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Monitoring parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Monitoring) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Monitoring monitoring) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoring);
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

    public static Monitoring getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Monitoring> parser() {
        return PARSER;
    }

    public Parser<Monitoring> getParserForType() {
        return PARSER;
    }

    public Monitoring getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
