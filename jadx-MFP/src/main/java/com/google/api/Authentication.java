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

public final class Authentication extends GeneratedMessageV3 implements AuthenticationOrBuilder {
    private static final Authentication DEFAULT_INSTANCE = new Authentication();
    /* access modifiers changed from: private */
    public static final Parser<Authentication> PARSER = new AbstractParser<Authentication>() {
        public Authentication parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Authentication(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PROVIDERS_FIELD_NUMBER = 4;
    public static final int RULES_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<AuthProvider> providers_;
    /* access modifiers changed from: private */
    public List<AuthenticationRule> rules_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AuthenticationOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> providersBuilder_;
        private List<AuthProvider> providers_;
        private RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> rulesBuilder_;
        private List<AuthenticationRule> rules_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_Authentication_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_Authentication_fieldAccessorTable.ensureFieldAccessorsInitialized(Authentication.class, Builder.class);
        }

        private Builder() {
            this.rules_ = Collections.emptyList();
            this.providers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.rules_ = Collections.emptyList();
            this.providers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Authentication.alwaysUseFieldBuilders) {
                getRulesFieldBuilder();
                getProvidersFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV32 = this.providersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                this.providers_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV32.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_Authentication_descriptor;
        }

        public Authentication getDefaultInstanceForType() {
            return Authentication.getDefaultInstance();
        }

        public Authentication build() {
            Authentication buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public Authentication buildPartial() {
            Authentication authentication = new Authentication((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.rules_ = Collections.unmodifiableList(this.rules_);
                    this.bitField0_ &= -2;
                }
                authentication.rules_ = this.rules_;
            } else {
                authentication.rules_ = repeatedFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV32 = this.providersBuilder_;
            if (repeatedFieldBuilderV32 == null) {
                if ((this.bitField0_ & 2) == 2) {
                    this.providers_ = Collections.unmodifiableList(this.providers_);
                    this.bitField0_ &= -3;
                }
                authentication.providers_ = this.providers_;
            } else {
                authentication.providers_ = repeatedFieldBuilderV32.build();
            }
            onBuilt();
            return authentication;
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
            if (message instanceof Authentication) {
                return mergeFrom((Authentication) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Authentication authentication) {
            if (authentication == Authentication.getDefaultInstance()) {
                return this;
            }
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = null;
            if (this.rulesBuilder_ == null) {
                if (!authentication.rules_.isEmpty()) {
                    if (this.rules_.isEmpty()) {
                        this.rules_ = authentication.rules_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureRulesIsMutable();
                        this.rules_.addAll(authentication.rules_);
                    }
                    onChanged();
                }
            } else if (!authentication.rules_.isEmpty()) {
                if (this.rulesBuilder_.isEmpty()) {
                    this.rulesBuilder_.dispose();
                    this.rulesBuilder_ = null;
                    this.rules_ = authentication.rules_;
                    this.bitField0_ &= -2;
                    this.rulesBuilder_ = Authentication.alwaysUseFieldBuilders ? getRulesFieldBuilder() : null;
                } else {
                    this.rulesBuilder_.addAllMessages(authentication.rules_);
                }
            }
            if (this.providersBuilder_ == null) {
                if (!authentication.providers_.isEmpty()) {
                    if (this.providers_.isEmpty()) {
                        this.providers_ = authentication.providers_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureProvidersIsMutable();
                        this.providers_.addAll(authentication.providers_);
                    }
                    onChanged();
                }
            } else if (!authentication.providers_.isEmpty()) {
                if (this.providersBuilder_.isEmpty()) {
                    this.providersBuilder_.dispose();
                    this.providersBuilder_ = null;
                    this.providers_ = authentication.providers_;
                    this.bitField0_ &= -3;
                    if (Authentication.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getProvidersFieldBuilder();
                    }
                    this.providersBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.providersBuilder_.addAllMessages(authentication.providers_);
                }
            }
            mergeUnknownFields(authentication.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.Authentication.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.Authentication.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.Authentication r3 = (com.google.api.Authentication) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.Authentication r4 = (com.google.api.Authentication) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Authentication.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.Authentication$Builder");
        }

        private void ensureRulesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.rules_ = new ArrayList(this.rules_);
                this.bitField0_ |= 1;
            }
        }

        public List<AuthenticationRule> getRulesList() {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.rules_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getRulesCount() {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.rules_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public AuthenticationRule getRules(int i) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AuthenticationRule) this.rules_.get(i);
            }
            return (AuthenticationRule) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRules(int i, AuthenticationRule authenticationRule) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, authenticationRule);
            } else if (authenticationRule != null) {
                ensureRulesIsMutable();
                this.rules_.set(i, authenticationRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRules(int i, com.google.api.AuthenticationRule.Builder builder) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRules(AuthenticationRule authenticationRule) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(authenticationRule);
            } else if (authenticationRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(authenticationRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(int i, AuthenticationRule authenticationRule) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, authenticationRule);
            } else if (authenticationRule != null) {
                ensureRulesIsMutable();
                this.rules_.add(i, authenticationRule);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRules(com.google.api.AuthenticationRule.Builder builder) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRules(int i, com.google.api.AuthenticationRule.Builder builder) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRules(Iterable<? extends AuthenticationRule> iterable) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.rules_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRules() {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.rules_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRules(int i) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRulesIsMutable();
                this.rules_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.AuthenticationRule.Builder getRulesBuilder(int i) {
            return (com.google.api.AuthenticationRule.Builder) getRulesFieldBuilder().getBuilder(i);
        }

        public AuthenticationRuleOrBuilder getRulesOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AuthenticationRuleOrBuilder) this.rules_.get(i);
            }
            return (AuthenticationRuleOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
            RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> repeatedFieldBuilderV3 = this.rulesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.rules_);
        }

        public com.google.api.AuthenticationRule.Builder addRulesBuilder() {
            return (com.google.api.AuthenticationRule.Builder) getRulesFieldBuilder().addBuilder(AuthenticationRule.getDefaultInstance());
        }

        public com.google.api.AuthenticationRule.Builder addRulesBuilder(int i) {
            return (com.google.api.AuthenticationRule.Builder) getRulesFieldBuilder().addBuilder(i, AuthenticationRule.getDefaultInstance());
        }

        public List<com.google.api.AuthenticationRule.Builder> getRulesBuilderList() {
            return getRulesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthenticationRule, com.google.api.AuthenticationRule.Builder, AuthenticationRuleOrBuilder> getRulesFieldBuilder() {
            if (this.rulesBuilder_ == null) {
                List<AuthenticationRule> list = this.rules_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.rulesBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.rules_ = null;
            }
            return this.rulesBuilder_;
        }

        private void ensureProvidersIsMutable() {
            if ((this.bitField0_ & 2) != 2) {
                this.providers_ = new ArrayList(this.providers_);
                this.bitField0_ |= 2;
            }
        }

        public List<AuthProvider> getProvidersList() {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.providers_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getProvidersCount() {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.providers_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public AuthProvider getProviders(int i) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AuthProvider) this.providers_.get(i);
            }
            return (AuthProvider) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setProviders(int i, AuthProvider authProvider) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, authProvider);
            } else if (authProvider != null) {
                ensureProvidersIsMutable();
                this.providers_.set(i, authProvider);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setProviders(int i, com.google.api.AuthProvider.Builder builder) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                this.providers_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addProviders(AuthProvider authProvider) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(authProvider);
            } else if (authProvider != null) {
                ensureProvidersIsMutable();
                this.providers_.add(authProvider);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addProviders(int i, AuthProvider authProvider) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, authProvider);
            } else if (authProvider != null) {
                ensureProvidersIsMutable();
                this.providers_.add(i, authProvider);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addProviders(com.google.api.AuthProvider.Builder builder) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                this.providers_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addProviders(int i, com.google.api.AuthProvider.Builder builder) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                this.providers_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllProviders(Iterable<? extends AuthProvider> iterable) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.providers_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearProviders() {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.providers_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeProviders(int i) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureProvidersIsMutable();
                this.providers_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.AuthProvider.Builder getProvidersBuilder(int i) {
            return (com.google.api.AuthProvider.Builder) getProvidersFieldBuilder().getBuilder(i);
        }

        public AuthProviderOrBuilder getProvidersOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AuthProviderOrBuilder) this.providers_.get(i);
            }
            return (AuthProviderOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
            RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> repeatedFieldBuilderV3 = this.providersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.providers_);
        }

        public com.google.api.AuthProvider.Builder addProvidersBuilder() {
            return (com.google.api.AuthProvider.Builder) getProvidersFieldBuilder().addBuilder(AuthProvider.getDefaultInstance());
        }

        public com.google.api.AuthProvider.Builder addProvidersBuilder(int i) {
            return (com.google.api.AuthProvider.Builder) getProvidersFieldBuilder().addBuilder(i, AuthProvider.getDefaultInstance());
        }

        public List<com.google.api.AuthProvider.Builder> getProvidersBuilderList() {
            return getProvidersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthProvider, com.google.api.AuthProvider.Builder, AuthProviderOrBuilder> getProvidersFieldBuilder() {
            if (this.providersBuilder_ == null) {
                this.providersBuilder_ = new RepeatedFieldBuilderV3<>(this.providers_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                this.providers_ = null;
            }
            return this.providersBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private Authentication(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private Authentication() {
        this.memoizedIsInitialized = -1;
        this.rules_ = Collections.emptyList();
        this.providers_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private Authentication(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.rules_ = new ArrayList();
                            z2 |= true;
                        }
                        this.rules_.add(codedInputStream.readMessage(AuthenticationRule.parser(), extensionRegistryLite));
                    } else if (readTag == 34) {
                        if (!(z2 & true)) {
                            this.providers_ = new ArrayList();
                            z2 |= true;
                        }
                        this.providers_.add(codedInputStream.readMessage(AuthProvider.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.rules_ = Collections.unmodifiableList(this.rules_);
                    }
                    if (z2 & true) {
                        this.providers_ = Collections.unmodifiableList(this.providers_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.rules_ = Collections.unmodifiableList(this.rules_);
            }
            if (z2 & true) {
                this.providers_ = Collections.unmodifiableList(this.providers_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_Authentication_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_Authentication_fieldAccessorTable.ensureFieldAccessorsInitialized(Authentication.class, Builder.class);
    }

    public List<AuthenticationRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public AuthenticationRule getRules(int i) {
        return (AuthenticationRule) this.rules_.get(i);
    }

    public AuthenticationRuleOrBuilder getRulesOrBuilder(int i) {
        return (AuthenticationRuleOrBuilder) this.rules_.get(i);
    }

    public List<AuthProvider> getProvidersList() {
        return this.providers_;
    }

    public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
        return this.providers_;
    }

    public int getProvidersCount() {
        return this.providers_.size();
    }

    public AuthProvider getProviders(int i) {
        return (AuthProvider) this.providers_.get(i);
    }

    public AuthProviderOrBuilder getProvidersOrBuilder(int i) {
        return (AuthProviderOrBuilder) this.providers_.get(i);
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
        for (int i = 0; i < this.rules_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.rules_.get(i));
        }
        for (int i2 = 0; i2 < this.providers_.size(); i2++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.providers_.get(i2));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.rules_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.rules_.get(i3));
        }
        for (int i4 = 0; i4 < this.providers_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.providers_.get(i4));
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
        if (!(obj instanceof Authentication)) {
            return super.equals(obj);
        }
        Authentication authentication = (Authentication) obj;
        if (!((getRulesList().equals(authentication.getRulesList())) && getProvidersList().equals(authentication.getProvidersList())) || !this.unknownFields.equals(authentication.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getRulesCount() > 0) {
            hashCode = (((hashCode * 37) + 3) * 53) + getRulesList().hashCode();
        }
        if (getProvidersCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getProvidersList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static Authentication parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Authentication) PARSER.parseFrom(byteBuffer);
    }

    public static Authentication parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Authentication) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Authentication parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Authentication) PARSER.parseFrom(byteString);
    }

    public static Authentication parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Authentication) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Authentication parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Authentication) PARSER.parseFrom(bArr);
    }

    public static Authentication parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Authentication) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Authentication parseFrom(InputStream inputStream) throws IOException {
        return (Authentication) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Authentication parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Authentication parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Authentication) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Authentication parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Authentication parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Authentication) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Authentication parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Authentication authentication) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authentication);
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

    public static Authentication getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Authentication> parser() {
        return PARSER;
    }

    public Parser<Authentication> getParserForType() {
        return PARSER;
    }

    public Authentication getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
