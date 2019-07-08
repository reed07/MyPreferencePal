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

public final class MonitoredResourceDescriptor extends GeneratedMessageV3 implements MonitoredResourceDescriptorOrBuilder {
    private static final MonitoredResourceDescriptor DEFAULT_INSTANCE = new MonitoredResourceDescriptor();
    public static final int DESCRIPTION_FIELD_NUMBER = 3;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
    public static final int LABELS_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 5;
    /* access modifiers changed from: private */
    public static final Parser<MonitoredResourceDescriptor> PARSER = new AbstractParser<MonitoredResourceDescriptor>() {
        public MonitoredResourceDescriptor parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MonitoredResourceDescriptor(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public int bitField0_;
    /* access modifiers changed from: private */
    public volatile Object description_;
    /* access modifiers changed from: private */
    public volatile Object displayName_;
    /* access modifiers changed from: private */
    public List<LabelDescriptor> labels_;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public volatile Object type_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MonitoredResourceDescriptorOrBuilder {
        private int bitField0_;
        private Object description_;
        private Object displayName_;
        private RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> labelsBuilder_;
        private List<LabelDescriptor> labels_;
        private Object name_;
        private Object type_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceDescriptor.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.type_ = "";
            this.displayName_ = "";
            this.description_ = "";
            this.labels_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.type_ = "";
            this.displayName_ = "";
            this.description_ = "";
            this.labels_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (MonitoredResourceDescriptor.alwaysUseFieldBuilders) {
                getLabelsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.type_ = "";
            this.displayName_ = "";
            this.description_ = "";
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= -17;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_descriptor;
        }

        public MonitoredResourceDescriptor getDefaultInstanceForType() {
            return MonitoredResourceDescriptor.getDefaultInstance();
        }

        public MonitoredResourceDescriptor build() {
            MonitoredResourceDescriptor buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public MonitoredResourceDescriptor buildPartial() {
            MonitoredResourceDescriptor monitoredResourceDescriptor = new MonitoredResourceDescriptor((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            monitoredResourceDescriptor.name_ = this.name_;
            monitoredResourceDescriptor.type_ = this.type_;
            monitoredResourceDescriptor.displayName_ = this.displayName_;
            monitoredResourceDescriptor.description_ = this.description_;
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 16) == 16) {
                    this.labels_ = Collections.unmodifiableList(this.labels_);
                    this.bitField0_ &= -17;
                }
                monitoredResourceDescriptor.labels_ = this.labels_;
            } else {
                monitoredResourceDescriptor.labels_ = repeatedFieldBuilderV3.build();
            }
            monitoredResourceDescriptor.bitField0_ = 0;
            onBuilt();
            return monitoredResourceDescriptor;
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
            if (message instanceof MonitoredResourceDescriptor) {
                return mergeFrom((MonitoredResourceDescriptor) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MonitoredResourceDescriptor monitoredResourceDescriptor) {
            if (monitoredResourceDescriptor == MonitoredResourceDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!monitoredResourceDescriptor.getName().isEmpty()) {
                this.name_ = monitoredResourceDescriptor.name_;
                onChanged();
            }
            if (!monitoredResourceDescriptor.getType().isEmpty()) {
                this.type_ = monitoredResourceDescriptor.type_;
                onChanged();
            }
            if (!monitoredResourceDescriptor.getDisplayName().isEmpty()) {
                this.displayName_ = monitoredResourceDescriptor.displayName_;
                onChanged();
            }
            if (!monitoredResourceDescriptor.getDescription().isEmpty()) {
                this.description_ = monitoredResourceDescriptor.description_;
                onChanged();
            }
            if (this.labelsBuilder_ == null) {
                if (!monitoredResourceDescriptor.labels_.isEmpty()) {
                    if (this.labels_.isEmpty()) {
                        this.labels_ = monitoredResourceDescriptor.labels_;
                        this.bitField0_ &= -17;
                    } else {
                        ensureLabelsIsMutable();
                        this.labels_.addAll(monitoredResourceDescriptor.labels_);
                    }
                    onChanged();
                }
            } else if (!monitoredResourceDescriptor.labels_.isEmpty()) {
                if (this.labelsBuilder_.isEmpty()) {
                    this.labelsBuilder_.dispose();
                    RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = null;
                    this.labelsBuilder_ = null;
                    this.labels_ = monitoredResourceDescriptor.labels_;
                    this.bitField0_ &= -17;
                    if (MonitoredResourceDescriptor.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getLabelsFieldBuilder();
                    }
                    this.labelsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.labelsBuilder_.addAllMessages(monitoredResourceDescriptor.labels_);
                }
            }
            mergeUnknownFields(monitoredResourceDescriptor.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.MonitoredResourceDescriptor.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MonitoredResourceDescriptor.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.MonitoredResourceDescriptor r3 = (com.google.api.MonitoredResourceDescriptor) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.MonitoredResourceDescriptor r4 = (com.google.api.MonitoredResourceDescriptor) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MonitoredResourceDescriptor.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MonitoredResourceDescriptor$Builder");
        }

        public String getName() {
            Object obj = this.name_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.name_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setName(String str) {
            if (str != null) {
                this.name_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearName() {
            this.name_ = MonitoredResourceDescriptor.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResourceDescriptor.checkByteStringIsUtf8(byteString);
                this.name_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
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
            this.type_ = MonitoredResourceDescriptor.getDefaultInstance().getType();
            onChanged();
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResourceDescriptor.checkByteStringIsUtf8(byteString);
                this.type_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public String getDisplayName() {
            Object obj = this.displayName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.displayName_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getDisplayNameBytes() {
            Object obj = this.displayName_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.displayName_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setDisplayName(String str) {
            if (str != null) {
                this.displayName_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearDisplayName() {
            this.displayName_ = MonitoredResourceDescriptor.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResourceDescriptor.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString;
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
            this.description_ = MonitoredResourceDescriptor.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                MonitoredResourceDescriptor.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureLabelsIsMutable() {
            if ((this.bitField0_ & 16) != 16) {
                this.labels_ = new ArrayList(this.labels_);
                this.bitField0_ |= 16;
            }
        }

        public List<LabelDescriptor> getLabelsList() {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.labels_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getLabelsCount() {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.labels_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public LabelDescriptor getLabels(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LabelDescriptor) this.labels_.get(i);
            }
            return (LabelDescriptor) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setLabels(int i, LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, labelDescriptor);
            } else if (labelDescriptor != null) {
                ensureLabelsIsMutable();
                this.labels_.set(i, labelDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setLabels(int i, com.google.api.LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addLabels(LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(labelDescriptor);
            } else if (labelDescriptor != null) {
                ensureLabelsIsMutable();
                this.labels_.add(labelDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor labelDescriptor) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, labelDescriptor);
            } else if (labelDescriptor != null) {
                ensureLabelsIsMutable();
                this.labels_.add(i, labelDescriptor);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addLabels(com.google.api.LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addLabels(int i, com.google.api.LabelDescriptor.Builder builder) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllLabels(Iterable<? extends LabelDescriptor> iterable) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.labels_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearLabels() {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= -17;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeLabels(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureLabelsIsMutable();
                this.labels_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.LabelDescriptor.Builder getLabelsBuilder(int i) {
            return (com.google.api.LabelDescriptor.Builder) getLabelsFieldBuilder().getBuilder(i);
        }

        public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (LabelDescriptorOrBuilder) this.labels_.get(i);
            }
            return (LabelDescriptorOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.labels_);
        }

        public com.google.api.LabelDescriptor.Builder addLabelsBuilder() {
            return (com.google.api.LabelDescriptor.Builder) getLabelsFieldBuilder().addBuilder(LabelDescriptor.getDefaultInstance());
        }

        public com.google.api.LabelDescriptor.Builder addLabelsBuilder(int i) {
            return (com.google.api.LabelDescriptor.Builder) getLabelsFieldBuilder().addBuilder(i, LabelDescriptor.getDefaultInstance());
        }

        public List<com.google.api.LabelDescriptor.Builder> getLabelsBuilderList() {
            return getLabelsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> getLabelsFieldBuilder() {
            if (this.labelsBuilder_ == null) {
                this.labelsBuilder_ = new RepeatedFieldBuilderV3<>(this.labels_, (this.bitField0_ & 16) == 16, getParentForChildren(), isClean());
                this.labels_ = null;
            }
            return this.labelsBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private MonitoredResourceDescriptor(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private MonitoredResourceDescriptor() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.type_ = "";
        this.displayName_ = "";
        this.description_ = "";
        this.labels_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MonitoredResourceDescriptor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.type_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        this.displayName_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 26) {
                        this.description_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 34) {
                        if (!(z2 & true)) {
                            this.labels_ = new ArrayList();
                            z2 |= true;
                        }
                        this.labels_.add(codedInputStream.readMessage(LabelDescriptor.parser(), extensionRegistryLite));
                    } else if (readTag == 42) {
                        this.name_ = codedInputStream.readStringRequireUtf8();
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.labels_ = Collections.unmodifiableList(this.labels_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.labels_ = Collections.unmodifiableList(this.labels_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return MonitoredResourceProto.internal_static_google_api_MonitoredResourceDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(MonitoredResourceDescriptor.class, Builder.class);
    }

    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.name_ = copyFromUtf8;
        return copyFromUtf8;
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

    public String getDisplayName() {
        Object obj = this.displayName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.displayName_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getDisplayNameBytes() {
        Object obj = this.displayName_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.displayName_ = copyFromUtf8;
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

    public List<LabelDescriptor> getLabelsList() {
        return this.labels_;
    }

    public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
        return this.labels_;
    }

    public int getLabelsCount() {
        return this.labels_.size();
    }

    public LabelDescriptor getLabels(int i) {
        return (LabelDescriptor) this.labels_.get(i);
    }

    public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
        return (LabelDescriptorOrBuilder) this.labels_.get(i);
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
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.displayName_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 3, this.description_);
        }
        for (int i = 0; i < this.labels_.size(); i++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.labels_.get(i));
        }
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.name_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getTypeBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.type_) + 0 : 0;
        if (!getDisplayNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(2, this.displayName_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(3, this.description_);
        }
        for (int i2 = 0; i2 < this.labels_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, (MessageLite) this.labels_.get(i2));
        }
        if (!getNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.name_);
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
        if (!(obj instanceof MonitoredResourceDescriptor)) {
            return super.equals(obj);
        }
        MonitoredResourceDescriptor monitoredResourceDescriptor = (MonitoredResourceDescriptor) obj;
        if (!(((((getName().equals(monitoredResourceDescriptor.getName())) && getType().equals(monitoredResourceDescriptor.getType())) && getDisplayName().equals(monitoredResourceDescriptor.getDisplayName())) && getDescription().equals(monitoredResourceDescriptor.getDescription())) && getLabelsList().equals(monitoredResourceDescriptor.getLabelsList())) || !this.unknownFields.equals(monitoredResourceDescriptor.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 5) * 53) + getName().hashCode()) * 37) + 1) * 53) + getType().hashCode()) * 37) + 2) * 53) + getDisplayName().hashCode()) * 37) + 3) * 53) + getDescription().hashCode();
        if (getLabelsCount() > 0) {
            hashCode = (((hashCode * 37) + 4) * 53) + getLabelsList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static MonitoredResourceDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MonitoredResourceDescriptor) PARSER.parseFrom(byteBuffer);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceDescriptor) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MonitoredResourceDescriptor) PARSER.parseFrom(byteString);
    }

    public static MonitoredResourceDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceDescriptor) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MonitoredResourceDescriptor) PARSER.parseFrom(bArr);
    }

    public static MonitoredResourceDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MonitoredResourceDescriptor) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(InputStream inputStream) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MonitoredResourceDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MonitoredResourceDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MonitoredResourceDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MonitoredResourceDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MonitoredResourceDescriptor monitoredResourceDescriptor) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(monitoredResourceDescriptor);
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

    public static MonitoredResourceDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MonitoredResourceDescriptor> parser() {
        return PARSER;
    }

    public Parser<MonitoredResourceDescriptor> getParserForType() {
        return PARSER;
    }

    public MonitoredResourceDescriptor getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
