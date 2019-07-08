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
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AuthenticationRule extends GeneratedMessageV3 implements AuthenticationRuleOrBuilder {
    public static final int ALLOW_WITHOUT_CREDENTIAL_FIELD_NUMBER = 5;
    private static final AuthenticationRule DEFAULT_INSTANCE = new AuthenticationRule();
    public static final int OAUTH_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<AuthenticationRule> PARSER = new AbstractParser<AuthenticationRule>() {
        public AuthenticationRule parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new AuthenticationRule(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int REQUIREMENTS_FIELD_NUMBER = 7;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public boolean allowWithoutCredential_;
    /* access modifiers changed from: private */
    public int bitField0_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public OAuthRequirements oauth_;
    /* access modifiers changed from: private */
    public List<AuthRequirement> requirements_;
    /* access modifiers changed from: private */
    public volatile Object selector_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements AuthenticationRuleOrBuilder {
        private boolean allowWithoutCredential_;
        private int bitField0_;
        private SingleFieldBuilderV3<OAuthRequirements, com.google.api.OAuthRequirements.Builder, OAuthRequirementsOrBuilder> oauthBuilder_;
        private OAuthRequirements oauth_;
        private RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> requirementsBuilder_;
        private List<AuthRequirement> requirements_;
        private Object selector_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return AuthProto.internal_static_google_api_AuthenticationRule_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationRule.class, Builder.class);
        }

        private Builder() {
            this.selector_ = "";
            this.oauth_ = null;
            this.requirements_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.selector_ = "";
            this.oauth_ = null;
            this.requirements_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (AuthenticationRule.alwaysUseFieldBuilders) {
                getRequirementsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.selector_ = "";
            if (this.oauthBuilder_ == null) {
                this.oauth_ = null;
            } else {
                this.oauth_ = null;
                this.oauthBuilder_ = null;
            }
            this.allowWithoutCredential_ = false;
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.requirements_ = Collections.emptyList();
                this.bitField0_ &= -9;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
        }

        public AuthenticationRule getDefaultInstanceForType() {
            return AuthenticationRule.getDefaultInstance();
        }

        public AuthenticationRule build() {
            AuthenticationRule buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public AuthenticationRule buildPartial() {
            AuthenticationRule authenticationRule = new AuthenticationRule((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            authenticationRule.selector_ = this.selector_;
            SingleFieldBuilderV3<OAuthRequirements, com.google.api.OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 == null) {
                authenticationRule.oauth_ = this.oauth_;
            } else {
                authenticationRule.oauth_ = (OAuthRequirements) singleFieldBuilderV3.build();
            }
            authenticationRule.allowWithoutCredential_ = this.allowWithoutCredential_;
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 8) == 8) {
                    this.requirements_ = Collections.unmodifiableList(this.requirements_);
                    this.bitField0_ &= -9;
                }
                authenticationRule.requirements_ = this.requirements_;
            } else {
                authenticationRule.requirements_ = repeatedFieldBuilderV3.build();
            }
            authenticationRule.bitField0_ = 0;
            onBuilt();
            return authenticationRule;
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
            if (message instanceof AuthenticationRule) {
                return mergeFrom((AuthenticationRule) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(AuthenticationRule authenticationRule) {
            if (authenticationRule == AuthenticationRule.getDefaultInstance()) {
                return this;
            }
            if (!authenticationRule.getSelector().isEmpty()) {
                this.selector_ = authenticationRule.selector_;
                onChanged();
            }
            if (authenticationRule.hasOauth()) {
                mergeOauth(authenticationRule.getOauth());
            }
            if (authenticationRule.getAllowWithoutCredential()) {
                setAllowWithoutCredential(authenticationRule.getAllowWithoutCredential());
            }
            if (this.requirementsBuilder_ == null) {
                if (!authenticationRule.requirements_.isEmpty()) {
                    if (this.requirements_.isEmpty()) {
                        this.requirements_ = authenticationRule.requirements_;
                        this.bitField0_ &= -9;
                    } else {
                        ensureRequirementsIsMutable();
                        this.requirements_.addAll(authenticationRule.requirements_);
                    }
                    onChanged();
                }
            } else if (!authenticationRule.requirements_.isEmpty()) {
                if (this.requirementsBuilder_.isEmpty()) {
                    this.requirementsBuilder_.dispose();
                    RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = null;
                    this.requirementsBuilder_ = null;
                    this.requirements_ = authenticationRule.requirements_;
                    this.bitField0_ &= -9;
                    if (AuthenticationRule.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getRequirementsFieldBuilder();
                    }
                    this.requirementsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.requirementsBuilder_.addAllMessages(authenticationRule.requirements_);
                }
            }
            mergeUnknownFields(authenticationRule.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.AuthenticationRule.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.AuthenticationRule.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.AuthenticationRule r3 = (com.google.api.AuthenticationRule) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.AuthenticationRule r4 = (com.google.api.AuthenticationRule) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.AuthenticationRule.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.AuthenticationRule$Builder");
        }

        public String getSelector() {
            Object obj = this.selector_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.selector_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getSelectorBytes() {
            Object obj = this.selector_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.selector_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setSelector(String str) {
            if (str != null) {
                this.selector_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearSelector() {
            this.selector_ = AuthenticationRule.getDefaultInstance().getSelector();
            onChanged();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            if (byteString != null) {
                AuthenticationRule.checkByteStringIsUtf8(byteString);
                this.selector_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public boolean hasOauth() {
            return (this.oauthBuilder_ == null && this.oauth_ == null) ? false : true;
        }

        public OAuthRequirements getOauth() {
            SingleFieldBuilderV3<OAuthRequirements, com.google.api.OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (OAuthRequirements) singleFieldBuilderV3.getMessage();
            }
            OAuthRequirements oAuthRequirements = this.oauth_;
            if (oAuthRequirements == null) {
                oAuthRequirements = OAuthRequirements.getDefaultInstance();
            }
            return oAuthRequirements;
        }

        public Builder setOauth(OAuthRequirements oAuthRequirements) {
            SingleFieldBuilderV3<OAuthRequirements, com.google.api.OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 != null) {
                singleFieldBuilderV3.setMessage(oAuthRequirements);
            } else if (oAuthRequirements != null) {
                this.oauth_ = oAuthRequirements;
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setOauth(com.google.api.OAuthRequirements.Builder builder) {
            SingleFieldBuilderV3<OAuthRequirements, com.google.api.OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.oauth_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeOauth(OAuthRequirements oAuthRequirements) {
            SingleFieldBuilderV3<OAuthRequirements, com.google.api.OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 == null) {
                OAuthRequirements oAuthRequirements2 = this.oauth_;
                if (oAuthRequirements2 != null) {
                    this.oauth_ = OAuthRequirements.newBuilder(oAuthRequirements2).mergeFrom(oAuthRequirements).buildPartial();
                } else {
                    this.oauth_ = oAuthRequirements;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(oAuthRequirements);
            }
            return this;
        }

        public Builder clearOauth() {
            if (this.oauthBuilder_ == null) {
                this.oauth_ = null;
                onChanged();
            } else {
                this.oauth_ = null;
                this.oauthBuilder_ = null;
            }
            return this;
        }

        public com.google.api.OAuthRequirements.Builder getOauthBuilder() {
            onChanged();
            return (com.google.api.OAuthRequirements.Builder) getOauthFieldBuilder().getBuilder();
        }

        public OAuthRequirementsOrBuilder getOauthOrBuilder() {
            SingleFieldBuilderV3<OAuthRequirements, com.google.api.OAuthRequirements.Builder, OAuthRequirementsOrBuilder> singleFieldBuilderV3 = this.oauthBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (OAuthRequirementsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            OAuthRequirements oAuthRequirements = this.oauth_;
            if (oAuthRequirements == null) {
                oAuthRequirements = OAuthRequirements.getDefaultInstance();
            }
            return oAuthRequirements;
        }

        private SingleFieldBuilderV3<OAuthRequirements, com.google.api.OAuthRequirements.Builder, OAuthRequirementsOrBuilder> getOauthFieldBuilder() {
            if (this.oauthBuilder_ == null) {
                this.oauthBuilder_ = new SingleFieldBuilderV3<>(getOauth(), getParentForChildren(), isClean());
                this.oauth_ = null;
            }
            return this.oauthBuilder_;
        }

        public boolean getAllowWithoutCredential() {
            return this.allowWithoutCredential_;
        }

        public Builder setAllowWithoutCredential(boolean z) {
            this.allowWithoutCredential_ = z;
            onChanged();
            return this;
        }

        public Builder clearAllowWithoutCredential() {
            this.allowWithoutCredential_ = false;
            onChanged();
            return this;
        }

        private void ensureRequirementsIsMutable() {
            if ((this.bitField0_ & 8) != 8) {
                this.requirements_ = new ArrayList(this.requirements_);
                this.bitField0_ |= 8;
            }
        }

        public List<AuthRequirement> getRequirementsList() {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.requirements_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getRequirementsCount() {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.requirements_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public AuthRequirement getRequirements(int i) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AuthRequirement) this.requirements_.get(i);
            }
            return (AuthRequirement) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setRequirements(int i, AuthRequirement authRequirement) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, authRequirement);
            } else if (authRequirement != null) {
                ensureRequirementsIsMutable();
                this.requirements_.set(i, authRequirement);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setRequirements(int i, com.google.api.AuthRequirement.Builder builder) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                this.requirements_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addRequirements(AuthRequirement authRequirement) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(authRequirement);
            } else if (authRequirement != null) {
                ensureRequirementsIsMutable();
                this.requirements_.add(authRequirement);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRequirements(int i, AuthRequirement authRequirement) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, authRequirement);
            } else if (authRequirement != null) {
                ensureRequirementsIsMutable();
                this.requirements_.add(i, authRequirement);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addRequirements(com.google.api.AuthRequirement.Builder builder) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                this.requirements_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addRequirements(int i, com.google.api.AuthRequirement.Builder builder) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                this.requirements_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllRequirements(Iterable<? extends AuthRequirement> iterable) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.requirements_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearRequirements() {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.requirements_ = Collections.emptyList();
                this.bitField0_ &= -9;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeRequirements(int i) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureRequirementsIsMutable();
                this.requirements_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.AuthRequirement.Builder getRequirementsBuilder(int i) {
            return (com.google.api.AuthRequirement.Builder) getRequirementsFieldBuilder().getBuilder(i);
        }

        public AuthRequirementOrBuilder getRequirementsOrBuilder(int i) {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AuthRequirementOrBuilder) this.requirements_.get(i);
            }
            return (AuthRequirementOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList() {
            RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> repeatedFieldBuilderV3 = this.requirementsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.requirements_);
        }

        public com.google.api.AuthRequirement.Builder addRequirementsBuilder() {
            return (com.google.api.AuthRequirement.Builder) getRequirementsFieldBuilder().addBuilder(AuthRequirement.getDefaultInstance());
        }

        public com.google.api.AuthRequirement.Builder addRequirementsBuilder(int i) {
            return (com.google.api.AuthRequirement.Builder) getRequirementsFieldBuilder().addBuilder(i, AuthRequirement.getDefaultInstance());
        }

        public List<com.google.api.AuthRequirement.Builder> getRequirementsBuilderList() {
            return getRequirementsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<AuthRequirement, com.google.api.AuthRequirement.Builder, AuthRequirementOrBuilder> getRequirementsFieldBuilder() {
            if (this.requirementsBuilder_ == null) {
                this.requirementsBuilder_ = new RepeatedFieldBuilderV3<>(this.requirements_, (this.bitField0_ & 8) == 8, getParentForChildren(), isClean());
                this.requirements_ = null;
            }
            return this.requirementsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private AuthenticationRule(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private AuthenticationRule() {
        this.memoizedIsInitialized = -1;
        this.selector_ = "";
        this.allowWithoutCredential_ = false;
        this.requirements_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private AuthenticationRule(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.selector_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        com.google.api.OAuthRequirements.Builder builder = null;
                        if (this.oauth_ != null) {
                            builder = this.oauth_.toBuilder();
                        }
                        this.oauth_ = (OAuthRequirements) codedInputStream.readMessage(OAuthRequirements.parser(), extensionRegistryLite);
                        if (builder != null) {
                            builder.mergeFrom(this.oauth_);
                            this.oauth_ = builder.buildPartial();
                        }
                    } else if (readTag == 40) {
                        this.allowWithoutCredential_ = codedInputStream.readBool();
                    } else if (readTag == 58) {
                        if (!(z2 & true)) {
                            this.requirements_ = new ArrayList();
                            z2 |= true;
                        }
                        this.requirements_.add(codedInputStream.readMessage(AuthRequirement.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.requirements_ = Collections.unmodifiableList(this.requirements_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.requirements_ = Collections.unmodifiableList(this.requirements_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return AuthProto.internal_static_google_api_AuthenticationRule_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return AuthProto.internal_static_google_api_AuthenticationRule_fieldAccessorTable.ensureFieldAccessorsInitialized(AuthenticationRule.class, Builder.class);
    }

    public String getSelector() {
        Object obj = this.selector_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.selector_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getSelectorBytes() {
        Object obj = this.selector_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.selector_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public boolean hasOauth() {
        return this.oauth_ != null;
    }

    public OAuthRequirements getOauth() {
        OAuthRequirements oAuthRequirements = this.oauth_;
        return oAuthRequirements == null ? OAuthRequirements.getDefaultInstance() : oAuthRequirements;
    }

    public OAuthRequirementsOrBuilder getOauthOrBuilder() {
        return getOauth();
    }

    public boolean getAllowWithoutCredential() {
        return this.allowWithoutCredential_;
    }

    public List<AuthRequirement> getRequirementsList() {
        return this.requirements_;
    }

    public List<? extends AuthRequirementOrBuilder> getRequirementsOrBuilderList() {
        return this.requirements_;
    }

    public int getRequirementsCount() {
        return this.requirements_.size();
    }

    public AuthRequirement getRequirements(int i) {
        return (AuthRequirement) this.requirements_.get(i);
    }

    public AuthRequirementOrBuilder getRequirementsOrBuilder(int i) {
        return (AuthRequirementOrBuilder) this.requirements_.get(i);
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
        if (!getSelectorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.selector_);
        }
        if (this.oauth_ != null) {
            codedOutputStream.writeMessage(2, getOauth());
        }
        boolean z = this.allowWithoutCredential_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        for (int i = 0; i < this.requirements_.size(); i++) {
            codedOutputStream.writeMessage(7, (MessageLite) this.requirements_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getSelectorBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.selector_) + 0 : 0;
        if (this.oauth_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getOauth());
        }
        boolean z = this.allowWithoutCredential_;
        if (z) {
            computeStringSize += CodedOutputStream.computeBoolSize(5, z);
        }
        for (int i2 = 0; i2 < this.requirements_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(7, (MessageLite) this.requirements_.get(i2));
        }
        int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AuthenticationRule)) {
            return super.equals(obj);
        }
        AuthenticationRule authenticationRule = (AuthenticationRule) obj;
        boolean z2 = (getSelector().equals(authenticationRule.getSelector())) && hasOauth() == authenticationRule.hasOauth();
        if (hasOauth()) {
            z2 = z2 && getOauth().equals(authenticationRule.getOauth());
        }
        if (!((z2 && getAllowWithoutCredential() == authenticationRule.getAllowWithoutCredential()) && getRequirementsList().equals(authenticationRule.getRequirementsList())) || !this.unknownFields.equals(authenticationRule.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getSelector().hashCode();
        if (hasOauth()) {
            hashCode = (((hashCode * 37) + 2) * 53) + getOauth().hashCode();
        }
        int hashBoolean = (((hashCode * 37) + 5) * 53) + Internal.hashBoolean(getAllowWithoutCredential());
        if (getRequirementsCount() > 0) {
            hashBoolean = (((hashBoolean * 37) + 7) * 53) + getRequirementsList().hashCode();
        }
        int hashCode2 = (hashBoolean * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static AuthenticationRule parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (AuthenticationRule) PARSER.parseFrom(byteBuffer);
    }

    public static AuthenticationRule parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthenticationRule) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthenticationRule) PARSER.parseFrom(byteString);
    }

    public static AuthenticationRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthenticationRule) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthenticationRule) PARSER.parseFrom(bArr);
    }

    public static AuthenticationRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthenticationRule) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(InputStream inputStream) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static AuthenticationRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static AuthenticationRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static AuthenticationRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static AuthenticationRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthenticationRule) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthenticationRule authenticationRule) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(authenticationRule);
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

    public static AuthenticationRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthenticationRule> parser() {
        return PARSER;
    }

    public Parser<AuthenticationRule> getParserForType() {
        return PARSER;
    }

    public AuthenticationRule getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
