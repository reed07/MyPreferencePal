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

public final class ConfigChange extends GeneratedMessageV3 implements ConfigChangeOrBuilder {
    public static final int ADVICES_FIELD_NUMBER = 5;
    public static final int CHANGE_TYPE_FIELD_NUMBER = 4;
    private static final ConfigChange DEFAULT_INSTANCE = new ConfigChange();
    public static final int ELEMENT_FIELD_NUMBER = 1;
    public static final int NEW_VALUE_FIELD_NUMBER = 3;
    public static final int OLD_VALUE_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Parser<ConfigChange> PARSER = new AbstractParser<ConfigChange>() {
        public ConfigChange parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ConfigChange(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public List<Advice> advices_;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public int changeType_;
    /* access modifiers changed from: private */
    public volatile Object element_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object newValue_;
    /* access modifiers changed from: private */
    public volatile Object oldValue_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ConfigChangeOrBuilder {
        private RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> advicesBuilder_;
        private List<Advice> advices_;
        private int bitField0_;
        private int changeType_;
        private Object element_;
        private Object newValue_;
        private Object oldValue_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigChange.class, Builder.class);
        }

        private Builder() {
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            this.advices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            this.advices_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ConfigChange.alwaysUseFieldBuilders) {
                getAdvicesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.element_ = "";
            this.oldValue_ = "";
            this.newValue_ = "";
            this.changeType_ = 0;
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.advices_ = Collections.emptyList();
                this.bitField0_ &= -17;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
        }

        public ConfigChange getDefaultInstanceForType() {
            return ConfigChange.getDefaultInstance();
        }

        public ConfigChange build() {
            ConfigChange buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ConfigChange buildPartial() {
            ConfigChange configChange = new ConfigChange((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            configChange.element_ = this.element_;
            configChange.oldValue_ = this.oldValue_;
            configChange.newValue_ = this.newValue_;
            configChange.changeType_ = this.changeType_;
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 16) == 16) {
                    this.advices_ = Collections.unmodifiableList(this.advices_);
                    this.bitField0_ &= -17;
                }
                configChange.advices_ = this.advices_;
            } else {
                configChange.advices_ = repeatedFieldBuilderV3.build();
            }
            configChange.bitField0_ = 0;
            onBuilt();
            return configChange;
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
            if (message instanceof ConfigChange) {
                return mergeFrom((ConfigChange) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ConfigChange configChange) {
            if (configChange == ConfigChange.getDefaultInstance()) {
                return this;
            }
            if (!configChange.getElement().isEmpty()) {
                this.element_ = configChange.element_;
                onChanged();
            }
            if (!configChange.getOldValue().isEmpty()) {
                this.oldValue_ = configChange.oldValue_;
                onChanged();
            }
            if (!configChange.getNewValue().isEmpty()) {
                this.newValue_ = configChange.newValue_;
                onChanged();
            }
            if (configChange.changeType_ != 0) {
                setChangeTypeValue(configChange.getChangeTypeValue());
            }
            if (this.advicesBuilder_ == null) {
                if (!configChange.advices_.isEmpty()) {
                    if (this.advices_.isEmpty()) {
                        this.advices_ = configChange.advices_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureAdvicesIsMutable();
                        this.advices_.addAll(configChange.advices_);
                    }
                    onChanged();
                }
            } else if (!configChange.advices_.isEmpty()) {
                if (this.advicesBuilder_.isEmpty()) {
                    this.advicesBuilder_.dispose();
                    RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = null;
                    this.advicesBuilder_ = null;
                    this.advices_ = configChange.advices_;
                    this.bitField0_ &= -17;
                    if (ConfigChange.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getAdvicesFieldBuilder();
                    }
                    this.advicesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.advicesBuilder_.addAllMessages(configChange.advices_);
                }
            }
            mergeUnknownFields(configChange.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.ConfigChange.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.ConfigChange.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.ConfigChange r3 = (com.google.api.ConfigChange) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.ConfigChange r4 = (com.google.api.ConfigChange) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.ConfigChange.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.ConfigChange$Builder");
        }

        public String getElement() {
            Object obj = this.element_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.element_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getElementBytes() {
            Object obj = this.element_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.element_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setElement(String str) {
            if (str != null) {
                this.element_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearElement() {
            this.element_ = ConfigChange.getDefaultInstance().getElement();
            onChanged();
            return this;
        }

        public Builder setElementBytes(ByteString byteString) {
            if (byteString != null) {
                ConfigChange.checkByteStringIsUtf8(byteString);
                this.element_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getOldValue() {
            Object obj = this.oldValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.oldValue_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getOldValueBytes() {
            Object obj = this.oldValue_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.oldValue_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setOldValue(String str) {
            if (str != null) {
                this.oldValue_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearOldValue() {
            this.oldValue_ = ConfigChange.getDefaultInstance().getOldValue();
            onChanged();
            return this;
        }

        public Builder setOldValueBytes(ByteString byteString) {
            if (byteString != null) {
                ConfigChange.checkByteStringIsUtf8(byteString);
                this.oldValue_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getNewValue() {
            Object obj = this.newValue_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.newValue_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getNewValueBytes() {
            Object obj = this.newValue_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.newValue_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setNewValue(String str) {
            if (str != null) {
                this.newValue_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearNewValue() {
            this.newValue_ = ConfigChange.getDefaultInstance().getNewValue();
            onChanged();
            return this;
        }

        public Builder setNewValueBytes(ByteString byteString) {
            if (byteString != null) {
                ConfigChange.checkByteStringIsUtf8(byteString);
                this.newValue_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public int getChangeTypeValue() {
            return this.changeType_;
        }

        public Builder setChangeTypeValue(int i) {
            this.changeType_ = i;
            onChanged();
            return this;
        }

        public ChangeType getChangeType() {
            ChangeType valueOf = ChangeType.valueOf(this.changeType_);
            return valueOf == null ? ChangeType.UNRECOGNIZED : valueOf;
        }

        public Builder setChangeType(ChangeType changeType) {
            if (changeType != null) {
                this.changeType_ = changeType.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearChangeType() {
            this.changeType_ = 0;
            onChanged();
            return this;
        }

        private void ensureAdvicesIsMutable() {
            if ((this.bitField0_ & 16) != 16) {
                this.advices_ = new ArrayList(this.advices_);
                this.bitField0_ |= 16;
            }
        }

        public List<Advice> getAdvicesList() {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.advices_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getAdvicesCount() {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.advices_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Advice getAdvices(int i) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Advice) this.advices_.get(i);
            }
            return (Advice) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setAdvices(int i, Advice advice) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, advice);
            } else if (advice != null) {
                ensureAdvicesIsMutable();
                this.advices_.set(i, advice);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setAdvices(int i, com.google.api.Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAdvices(Advice advice) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(advice);
            } else if (advice != null) {
                ensureAdvicesIsMutable();
                this.advices_.add(advice);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addAdvices(int i, Advice advice) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, advice);
            } else if (advice != null) {
                ensureAdvicesIsMutable();
                this.advices_.add(i, advice);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addAdvices(com.google.api.Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addAdvices(int i, com.google.api.Advice.Builder builder) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllAdvices(Iterable<? extends Advice> iterable) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.advices_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearAdvices() {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.advices_ = Collections.emptyList();
                this.bitField0_ &= -17;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeAdvices(int i) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureAdvicesIsMutable();
                this.advices_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.Advice.Builder getAdvicesBuilder(int i) {
            return (com.google.api.Advice.Builder) getAdvicesFieldBuilder().getBuilder(i);
        }

        public AdviceOrBuilder getAdvicesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (AdviceOrBuilder) this.advices_.get(i);
            }
            return (AdviceOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
            RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> repeatedFieldBuilderV3 = this.advicesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.advices_);
        }

        public com.google.api.Advice.Builder addAdvicesBuilder() {
            return (com.google.api.Advice.Builder) getAdvicesFieldBuilder().addBuilder(Advice.getDefaultInstance());
        }

        public com.google.api.Advice.Builder addAdvicesBuilder(int i) {
            return (com.google.api.Advice.Builder) getAdvicesFieldBuilder().addBuilder(i, Advice.getDefaultInstance());
        }

        public List<com.google.api.Advice.Builder> getAdvicesBuilderList() {
            return getAdvicesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Advice, com.google.api.Advice.Builder, AdviceOrBuilder> getAdvicesFieldBuilder() {
            if (this.advicesBuilder_ == null) {
                this.advicesBuilder_ = new RepeatedFieldBuilderV3<>(this.advices_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                this.advices_ = null;
            }
            return this.advicesBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private ConfigChange(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ConfigChange() {
        this.memoizedIsInitialized = -1;
        this.element_ = "";
        this.oldValue_ = "";
        this.newValue_ = "";
        this.changeType_ = 0;
        this.advices_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ConfigChange(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.element_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.oldValue_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        this.newValue_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 32) {
                        this.changeType_ = codedInputStream.readEnum();
                    } else if (readTag == 42) {
                        if (!(z2 & true)) {
                            this.advices_ = new ArrayList();
                            z2 |= true;
                        }
                        this.advices_.add(codedInputStream.readMessage(Advice.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.advices_ = Collections.unmodifiableList(this.advices_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.advices_ = Collections.unmodifiableList(this.advices_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return ConfigChangeProto.internal_static_google_api_ConfigChange_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ConfigChangeProto.internal_static_google_api_ConfigChange_fieldAccessorTable.ensureFieldAccessorsInitialized(ConfigChange.class, Builder.class);
    }

    public String getElement() {
        Object obj = this.element_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.element_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getElementBytes() {
        Object obj = this.element_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.element_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getOldValue() {
        Object obj = this.oldValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.oldValue_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getOldValueBytes() {
        Object obj = this.oldValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.oldValue_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public String getNewValue() {
        Object obj = this.newValue_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.newValue_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNewValueBytes() {
        Object obj = this.newValue_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.newValue_ = copyFromUtf8;
        return copyFromUtf8;
    }

    public int getChangeTypeValue() {
        return this.changeType_;
    }

    public ChangeType getChangeType() {
        ChangeType valueOf = ChangeType.valueOf(this.changeType_);
        return valueOf == null ? ChangeType.UNRECOGNIZED : valueOf;
    }

    public List<Advice> getAdvicesList() {
        return this.advices_;
    }

    public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
        return this.advices_;
    }

    public int getAdvicesCount() {
        return this.advices_.size();
    }

    public Advice getAdvices(int i) {
        return (Advice) this.advices_.get(i);
    }

    public AdviceOrBuilder getAdvicesOrBuilder(int i) {
        return (AdviceOrBuilder) this.advices_.get(i);
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
        if (!getElementBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.element_);
        }
        if (!getOldValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.oldValue_);
        }
        if (!getNewValueBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.newValue_);
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.changeType_);
        }
        for (int i = 0; i < this.advices_.size(); i++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.advices_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getElementBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.element_) + 0 : 0;
        if (!getOldValueBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.oldValue_);
        }
        if (!getNewValueBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.newValue_);
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(4, this.changeType_);
        }
        for (int i2 = 0; i2 < this.advices_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, (MessageLite) this.advices_.get(i2));
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
        if (!(obj instanceof ConfigChange)) {
            return super.equals(obj);
        }
        ConfigChange configChange = (ConfigChange) obj;
        if (!(((((getElement().equals(configChange.getElement())) && getOldValue().equals(configChange.getOldValue())) && getNewValue().equals(configChange.getNewValue())) && this.changeType_ == configChange.changeType_) && getAdvicesList().equals(configChange.getAdvicesList())) || !this.unknownFields.equals(configChange.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getElement().hashCode()) * 37) + 2) * 53) + getOldValue().hashCode()) * 37) + 3) * 53) + getNewValue().hashCode()) * 37) + 4) * 53) + this.changeType_;
        if (getAdvicesCount() > 0) {
            hashCode = (((hashCode * 37) + 5) * 53) + getAdvicesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ConfigChange parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(byteBuffer);
    }

    public static ConfigChange parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(byteString);
    }

    public static ConfigChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(bArr);
    }

    public static ConfigChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigChange) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(InputStream inputStream) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ConfigChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConfigChange configChange) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(configChange);
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

    public static ConfigChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigChange> parser() {
        return PARSER;
    }

    public Parser<ConfigChange> getParserForType() {
        return PARSER;
    }

    public ConfigChange getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
