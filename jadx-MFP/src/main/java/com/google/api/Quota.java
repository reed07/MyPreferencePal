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
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Quota extends GeneratedMessageV3 implements QuotaOrBuilder {
    private static final Quota DEFAULT_INSTANCE = new Quota();
    public static final int LIMITS_FIELD_NUMBER = 3;
    public static final int METRIC_RULES_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final Parser<Quota> PARSER = new AbstractParser<Quota>() {
        public Quota parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Quota(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<QuotaLimit> limits_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<MetricRule> metricRules_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements QuotaOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> limitsBuilder_;
        private List<QuotaLimit> limits_;
        private RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> metricRulesBuilder_;
        private List<MetricRule> metricRules_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return QuotaProto.internal_static_google_api_Quota_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return QuotaProto.internal_static_google_api_Quota_fieldAccessorTable.ensureFieldAccessorsInitialized(Quota.class, Builder.class);
        }

        private Builder() {
            this.limits_ = Collections.emptyList();
            this.metricRules_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.limits_ = Collections.emptyList();
            this.metricRules_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Quota.alwaysUseFieldBuilders) {
                getLimitsFieldBuilder();
                getMetricRulesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.limits_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV32 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.metricRules_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return QuotaProto.internal_static_google_api_Quota_descriptor;
        }

        public Quota getDefaultInstanceForType() {
            return Quota.getDefaultInstance();
        }

        public Quota build() {
            Quota buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Quota buildPartial() {
            Quota quota = new Quota((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.limits_ = Collections.unmodifiableList(this.limits_);
                    this.bitField0_ &= -2;
                }
                quota.limits_ = this.limits_;
            } else {
                quota.limits_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV32 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.metricRules_ = Collections.unmodifiableList(this.metricRules_);
                    this.bitField0_ &= -3;
                }
                quota.metricRules_ = this.metricRules_;
            } else {
                quota.metricRules_ = repeatedFieldBuilderV32.build();
            }
            onBuilt();
            return quota;
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
            if (message instanceof Quota) {
                return mergeFrom((Quota) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Quota quota) {
            if (quota == Quota.getDefaultInstance()) {
                return this;
            }
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.limitsBuilder_ == null) {
                if (!quota.limits_.isEmpty()) {
                    if (this.limits_.isEmpty()) {
                        this.limits_ = quota.limits_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureLimitsIsMutable();
                        this.limits_.addAll(quota.limits_);
                    }
                    onChanged();
                }
            } else if (!quota.limits_.isEmpty()) {
                if (this.limitsBuilder_.isEmpty()) {
                    this.limitsBuilder_.dispose();
                    this.limitsBuilder_ = null;
                    this.limits_ = quota.limits_;
                    this.bitField0_ &= -2;
                    this.limitsBuilder_ = Quota.alwaysUseFieldBuilders ? getLimitsFieldBuilder() : null;
                } else {
                    this.limitsBuilder_.addAllMessages(quota.limits_);
                }
            }
            if (this.metricRulesBuilder_ == null) {
                if (!quota.metricRules_.isEmpty()) {
                    if (this.metricRules_.isEmpty()) {
                        this.metricRules_ = quota.metricRules_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureMetricRulesIsMutable();
                        this.metricRules_.addAll(quota.metricRules_);
                    }
                    onChanged();
                }
            } else if (!quota.metricRules_.isEmpty()) {
                if (this.metricRulesBuilder_.isEmpty()) {
                    this.metricRulesBuilder_.dispose();
                    this.metricRulesBuilder_ = null;
                    this.metricRules_ = quota.metricRules_;
                    this.bitField0_ &= -3;
                    if (Quota.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getMetricRulesFieldBuilder();
                    }
                    this.metricRulesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.metricRulesBuilder_.addAllMessages(quota.metricRules_);
                }
            }
            mergeUnknownFields(quota.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Quota.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Quota.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Quota r3 = (com.google.api.Quota) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Quota r4 = (com.google.api.Quota) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Quota.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Quota$Builder");
        }

        private void ensureLimitsIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.limits_ = new ArrayList(this.limits_);
                this.bitField0_ |= 1;
            }
        }

        public List<QuotaLimit> getLimitsList() {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.limits_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getLimitsCount() {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.limits_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public QuotaLimit getLimits(int i) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (QuotaLimit) this.limits_.get(i);
            }
            return (QuotaLimit) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLimits(int i, QuotaLimit quotaLimit) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, quotaLimit);
            } else if (quotaLimit != null) {
                ensureLimitsIsMutable();
                this.limits_.set(i, quotaLimit);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setLimits(int i, com.google.api.QuotaLimit.Builder builder) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLimitsIsMutable();
                this.limits_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLimits(QuotaLimit quotaLimit) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(quotaLimit);
            } else if (quotaLimit != null) {
                ensureLimitsIsMutable();
                this.limits_.add(quotaLimit);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addLimits(int i, QuotaLimit quotaLimit) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, quotaLimit);
            } else if (quotaLimit != null) {
                ensureLimitsIsMutable();
                this.limits_.add(i, quotaLimit);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addLimits(com.google.api.QuotaLimit.Builder builder) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLimitsIsMutable();
                this.limits_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLimits(int i, com.google.api.QuotaLimit.Builder builder) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLimitsIsMutable();
                this.limits_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLimits(Iterable<? extends QuotaLimit> iterable) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLimitsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.limits_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLimits() {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.limits_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLimits(int i) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLimitsIsMutable();
                this.limits_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.QuotaLimit.Builder getLimitsBuilder(int i) {
            return (com.google.api.QuotaLimit.Builder) getLimitsFieldBuilder().getBuilder(i);
        }

        public QuotaLimitOrBuilder getLimitsOrBuilder(int i) {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (QuotaLimitOrBuilder) this.limits_.get(i);
            }
            return (QuotaLimitOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends QuotaLimitOrBuilder> getLimitsOrBuilderList() {
            RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> repeatedFieldBuilderV3 = this.limitsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.limits_);
        }

        public com.google.api.QuotaLimit.Builder addLimitsBuilder() {
            return (com.google.api.QuotaLimit.Builder) getLimitsFieldBuilder().addBuilder(QuotaLimit.getDefaultInstance());
        }

        public com.google.api.QuotaLimit.Builder addLimitsBuilder(int i) {
            return (com.google.api.QuotaLimit.Builder) getLimitsFieldBuilder().addBuilder(i, QuotaLimit.getDefaultInstance());
        }

        public List<com.google.api.QuotaLimit.Builder> getLimitsBuilderList() {
            return getLimitsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<QuotaLimit, com.google.api.QuotaLimit.Builder, QuotaLimitOrBuilder> getLimitsFieldBuilder() {
            if (this.limitsBuilder_ == null) {
                List<QuotaLimit> list = this.limits_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.limitsBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.limits_ = null;
            }
            return this.limitsBuilder_;
        }

        private void ensureMetricRulesIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.metricRules_ = new ArrayList(this.metricRules_);
                this.bitField0_ |= 2;
            }
        }

        public List<MetricRule> getMetricRulesList() {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.metricRules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getMetricRulesCount() {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.metricRules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public MetricRule getMetricRules(int i) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MetricRule) this.metricRules_.get(i);
            }
            return (MetricRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setMetricRules(int i, MetricRule metricRule) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, metricRule);
            } else if (metricRule != null) {
                ensureMetricRulesIsMutable();
                this.metricRules_.set(i, metricRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setMetricRules(int i, com.google.api.MetricRule.Builder builder) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricRulesIsMutable();
                this.metricRules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addMetricRules(MetricRule metricRule) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(metricRule);
            } else if (metricRule != null) {
                ensureMetricRulesIsMutable();
                this.metricRules_.add(metricRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMetricRules(int i, MetricRule metricRule) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, metricRule);
            } else if (metricRule != null) {
                ensureMetricRulesIsMutable();
                this.metricRules_.add(i, metricRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addMetricRules(com.google.api.MetricRule.Builder builder) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricRulesIsMutable();
                this.metricRules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addMetricRules(int i, com.google.api.MetricRule.Builder builder) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricRulesIsMutable();
                this.metricRules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllMetricRules(Iterable<? extends MetricRule> iterable) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricRulesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.metricRules_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearMetricRules() {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.metricRules_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeMetricRules(int i) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureMetricRulesIsMutable();
                this.metricRules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.MetricRule.Builder getMetricRulesBuilder(int i) {
            return (com.google.api.MetricRule.Builder) getMetricRulesFieldBuilder().getBuilder(i);
        }

        public MetricRuleOrBuilder getMetricRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (MetricRuleOrBuilder) this.metricRules_.get(i);
            }
            return (MetricRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends MetricRuleOrBuilder> getMetricRulesOrBuilderList() {
            RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> repeatedFieldBuilderV3 = this.metricRulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.metricRules_);
        }

        public com.google.api.MetricRule.Builder addMetricRulesBuilder() {
            return (com.google.api.MetricRule.Builder) getMetricRulesFieldBuilder().addBuilder(MetricRule.getDefaultInstance());
        }

        public com.google.api.MetricRule.Builder addMetricRulesBuilder(int i) {
            return (com.google.api.MetricRule.Builder) getMetricRulesFieldBuilder().addBuilder(i, MetricRule.getDefaultInstance());
        }

        public List<com.google.api.MetricRule.Builder> getMetricRulesBuilderList() {
            return getMetricRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<MetricRule, com.google.api.MetricRule.Builder, MetricRuleOrBuilder> getMetricRulesFieldBuilder() {
            if (this.metricRulesBuilder_ == null) {
                this.metricRulesBuilder_ = new RepeatedFieldBuilderV3<>(this.metricRules_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.metricRules_ = null;
            }
            return this.metricRulesBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Quota(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Quota() {
        this.memoizedIsInitialized = -1;
        this.limits_ = Collections.emptyList();
        this.metricRules_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Quota(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                    } else if (readTag == 26) {
                        if (!z2 || !true) {
                            this.limits_ = new ArrayList();
                            z2 |= true;
                        }
                        this.limits_.add(codedInputStream.readMessage(QuotaLimit.parser(), extensionRegistryLite));
                    } else if (readTag == 34) {
                        if (!(z2 & true)) {
                            this.metricRules_ = new ArrayList();
                            z2 |= true;
                        }
                        this.metricRules_.add(codedInputStream.readMessage(MetricRule.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.limits_ = Collections.unmodifiableList(this.limits_);
                    }
                    if (z2 & true) {
                        this.metricRules_ = Collections.unmodifiableList(this.metricRules_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.limits_ = Collections.unmodifiableList(this.limits_);
            }
            if (z2 & true) {
                this.metricRules_ = Collections.unmodifiableList(this.metricRules_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return QuotaProto.internal_static_google_api_Quota_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return QuotaProto.internal_static_google_api_Quota_fieldAccessorTable.ensureFieldAccessorsInitialized(Quota.class, Builder.class);
    }

    public List<QuotaLimit> getLimitsList() {
        return this.limits_;
    }

    public List<? extends QuotaLimitOrBuilder> getLimitsOrBuilderList() {
        return this.limits_;
    }

    public int getLimitsCount() {
        return this.limits_.size();
    }

    public QuotaLimit getLimits(int i) {
        return (QuotaLimit) this.limits_.get(i);
    }

    public QuotaLimitOrBuilder getLimitsOrBuilder(int i) {
        return (QuotaLimitOrBuilder) this.limits_.get(i);
    }

    public List<MetricRule> getMetricRulesList() {
        return this.metricRules_;
    }

    public List<? extends MetricRuleOrBuilder> getMetricRulesOrBuilderList() {
        return this.metricRules_;
    }

    public int getMetricRulesCount() {
        return this.metricRules_.size();
    }

    public MetricRule getMetricRules(int i) {
        return (MetricRule) this.metricRules_.get(i);
    }

    public MetricRuleOrBuilder getMetricRulesOrBuilder(int i) {
        return (MetricRuleOrBuilder) this.metricRules_.get(i);
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
        for (int i = 0; i < this.limits_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.limits_.get(i));
        }
        for (int i2 = 0; i2 < this.metricRules_.size(); i2++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.metricRules_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.limits_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.limits_.get(i3));
        }
        for (int i4 = 0; i4 < this.metricRules_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.metricRules_.get(i4));
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
        if (!(obj instanceof Quota)) {
            return super.equals(obj);
        }
        Quota quota = (Quota) obj;
        if (!((getLimitsList().equals(quota.getLimitsList())) && getMetricRulesList().equals(quota.getMetricRulesList())) || !this.unknownFields.equals(quota.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getLimitsCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getLimitsList().hashCode();
        }
        if (getMetricRulesCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getMetricRulesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Quota parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Quota) PARSER.parseFrom(byteBuffer);
    }

    public static Quota parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Quota) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Quota parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Quota) PARSER.parseFrom(byteString);
    }

    public static Quota parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Quota) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Quota parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Quota) PARSER.parseFrom(bArr);
    }

    public static Quota parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Quota) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Quota parseFrom(InputStream inputStream) throws IOException {
        return (Quota) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Quota parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quota) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Quota parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Quota) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Quota parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quota) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Quota parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Quota) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Quota parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quota) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Quota quota) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(quota);
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

    public static Quota getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Quota> parser() {
        return PARSER;
    }

    public Parser<Quota> getParserForType() {
        return PARSER;
    }

    public Quota getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
