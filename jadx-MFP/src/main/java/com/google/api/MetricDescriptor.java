package com.google.api;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.GeneratedMessageV3.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MetricDescriptor extends GeneratedMessageV3 implements MetricDescriptorOrBuilder {
    private static final MetricDescriptor DEFAULT_INSTANCE = new MetricDescriptor();
    public static final int DESCRIPTION_FIELD_NUMBER = 6;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 7;
    public static final int LABELS_FIELD_NUMBER = 2;
    public static final int METRIC_KIND_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Parser<MetricDescriptor> PARSER = new AbstractParser<MetricDescriptor>() {
        public MetricDescriptor parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetricDescriptor(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int TYPE_FIELD_NUMBER = 8;
    public static final int UNIT_FIELD_NUMBER = 5;
    public static final int VALUE_TYPE_FIELD_NUMBER = 4;
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
    public int metricKind_;
    /* access modifiers changed from: private */
    public volatile Object name_;
    /* access modifiers changed from: private */
    public volatile Object type_;
    /* access modifiers changed from: private */
    public volatile Object unit_;
    /* access modifiers changed from: private */
    public int valueType_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements MetricDescriptorOrBuilder {
        private int bitField0_;
        private Object description_;
        private Object displayName_;
        private RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> labelsBuilder_;
        private List<LabelDescriptor> labels_;
        private int metricKind_;
        private Object name_;
        private Object type_;
        private Object unit_;
        private int valueType_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return MetricProto.internal_static_google_api_MetricDescriptor_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return MetricProto.internal_static_google_api_MetricDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricDescriptor.class, Builder.class);
        }

        private Builder() {
            this.name_ = "";
            this.type_ = "";
            this.labels_ = Collections.emptyList();
            this.metricKind_ = 0;
            this.valueType_ = 0;
            this.unit_ = "";
            this.description_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.type_ = "";
            this.labels_ = Collections.emptyList();
            this.metricKind_ = 0;
            this.valueType_ = 0;
            this.unit_ = "";
            this.description_ = "";
            this.displayName_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (MetricDescriptor.alwaysUseFieldBuilders) {
                getLabelsFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            this.name_ = "";
            this.type_ = "";
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.labels_ = Collections.emptyList();
                this.bitField0_ &= -5;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.metricKind_ = 0;
            this.valueType_ = 0;
            this.unit_ = "";
            this.description_ = "";
            this.displayName_ = "";
            return this;
        }

        public Descriptor getDescriptorForType() {
            return MetricProto.internal_static_google_api_MetricDescriptor_descriptor;
        }

        public MetricDescriptor getDefaultInstanceForType() {
            return MetricDescriptor.getDefaultInstance();
        }

        public MetricDescriptor build() {
            MetricDescriptor buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public MetricDescriptor buildPartial() {
            MetricDescriptor metricDescriptor = new MetricDescriptor((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            metricDescriptor.name_ = this.name_;
            metricDescriptor.type_ = this.type_;
            RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = this.labelsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 4) == 4) {
                    this.labels_ = Collections.unmodifiableList(this.labels_);
                    this.bitField0_ &= -5;
                }
                metricDescriptor.labels_ = this.labels_;
            } else {
                metricDescriptor.labels_ = repeatedFieldBuilderV3.build();
            }
            metricDescriptor.metricKind_ = this.metricKind_;
            metricDescriptor.valueType_ = this.valueType_;
            metricDescriptor.unit_ = this.unit_;
            metricDescriptor.description_ = this.description_;
            metricDescriptor.displayName_ = this.displayName_;
            metricDescriptor.bitField0_ = 0;
            onBuilt();
            return metricDescriptor;
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
            if (message instanceof MetricDescriptor) {
                return mergeFrom((MetricDescriptor) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetricDescriptor metricDescriptor) {
            if (metricDescriptor == MetricDescriptor.getDefaultInstance()) {
                return this;
            }
            if (!metricDescriptor.getName().isEmpty()) {
                this.name_ = metricDescriptor.name_;
                onChanged();
            }
            if (!metricDescriptor.getType().isEmpty()) {
                this.type_ = metricDescriptor.type_;
                onChanged();
            }
            if (this.labelsBuilder_ == null) {
                if (!metricDescriptor.labels_.isEmpty()) {
                    if (this.labels_.isEmpty()) {
                        this.labels_ = metricDescriptor.labels_;
                        this.bitField0_ &= -5;
                    } else {
                        ensureLabelsIsMutable();
                        this.labels_.addAll(metricDescriptor.labels_);
                    }
                    onChanged();
                }
            } else if (!metricDescriptor.labels_.isEmpty()) {
                if (this.labelsBuilder_.isEmpty()) {
                    this.labelsBuilder_.dispose();
                    RepeatedFieldBuilderV3<LabelDescriptor, com.google.api.LabelDescriptor.Builder, LabelDescriptorOrBuilder> repeatedFieldBuilderV3 = null;
                    this.labelsBuilder_ = null;
                    this.labels_ = metricDescriptor.labels_;
                    this.bitField0_ &= -5;
                    if (MetricDescriptor.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getLabelsFieldBuilder();
                    }
                    this.labelsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.labelsBuilder_.addAllMessages(metricDescriptor.labels_);
                }
            }
            if (metricDescriptor.metricKind_ != 0) {
                setMetricKindValue(metricDescriptor.getMetricKindValue());
            }
            if (metricDescriptor.valueType_ != 0) {
                setValueTypeValue(metricDescriptor.getValueTypeValue());
            }
            if (!metricDescriptor.getUnit().isEmpty()) {
                this.unit_ = metricDescriptor.unit_;
                onChanged();
            }
            if (!metricDescriptor.getDescription().isEmpty()) {
                this.description_ = metricDescriptor.description_;
                onChanged();
            }
            if (!metricDescriptor.getDisplayName().isEmpty()) {
                this.displayName_ = metricDescriptor.displayName_;
                onChanged();
            }
            mergeUnknownFields(metricDescriptor.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.MetricDescriptor.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.MetricDescriptor.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.MetricDescriptor r3 = (com.google.api.MetricDescriptor) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.MetricDescriptor r4 = (com.google.api.MetricDescriptor) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MetricDescriptor.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.MetricDescriptor$Builder");
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
            this.name_ = MetricDescriptor.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            if (byteString != null) {
                MetricDescriptor.checkByteStringIsUtf8(byteString);
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
            this.type_ = MetricDescriptor.getDefaultInstance().getType();
            onChanged();
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            if (byteString != null) {
                MetricDescriptor.checkByteStringIsUtf8(byteString);
                this.type_ = byteString;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureLabelsIsMutable() {
            if ((this.bitField0_ & 4) != 4) {
                this.labels_ = new ArrayList(this.labels_);
                this.bitField0_ |= 4;
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
                this.bitField0_ &= -5;
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
                this.labelsBuilder_ = new RepeatedFieldBuilderV3<>(this.labels_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                this.labels_ = null;
            }
            return this.labelsBuilder_;
        }

        public int getMetricKindValue() {
            return this.metricKind_;
        }

        public Builder setMetricKindValue(int i) {
            this.metricKind_ = i;
            onChanged();
            return this;
        }

        public MetricKind getMetricKind() {
            MetricKind valueOf = MetricKind.valueOf(this.metricKind_);
            return valueOf == null ? MetricKind.UNRECOGNIZED : valueOf;
        }

        public Builder setMetricKind(MetricKind metricKind) {
            if (metricKind != null) {
                this.metricKind_ = metricKind.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearMetricKind() {
            this.metricKind_ = 0;
            onChanged();
            return this;
        }

        public int getValueTypeValue() {
            return this.valueType_;
        }

        public Builder setValueTypeValue(int i) {
            this.valueType_ = i;
            onChanged();
            return this;
        }

        public ValueType getValueType() {
            ValueType valueOf = ValueType.valueOf(this.valueType_);
            return valueOf == null ? ValueType.UNRECOGNIZED : valueOf;
        }

        public Builder setValueType(ValueType valueType) {
            if (valueType != null) {
                this.valueType_ = valueType.getNumber();
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearValueType() {
            this.valueType_ = 0;
            onChanged();
            return this;
        }

        public String getUnit() {
            Object obj = this.unit_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.unit_ = stringUtf8;
            return stringUtf8;
        }

        public ByteString getUnitBytes() {
            Object obj = this.unit_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.unit_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public Builder setUnit(String str) {
            if (str != null) {
                this.unit_ = str;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        public Builder clearUnit() {
            this.unit_ = MetricDescriptor.getDefaultInstance().getUnit();
            onChanged();
            return this;
        }

        public Builder setUnitBytes(ByteString byteString) {
            if (byteString != null) {
                MetricDescriptor.checkByteStringIsUtf8(byteString);
                this.unit_ = byteString;
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
            this.description_ = MetricDescriptor.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                MetricDescriptor.checkByteStringIsUtf8(byteString);
                this.description_ = byteString;
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
            this.displayName_ = MetricDescriptor.getDefaultInstance().getDisplayName();
            onChanged();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            if (byteString != null) {
                MetricDescriptor.checkByteStringIsUtf8(byteString);
                this.displayName_ = byteString;
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

    public enum MetricKind implements ProtocolMessageEnum {
        METRIC_KIND_UNSPECIFIED(0),
        GAUGE(1),
        DELTA(2),
        CUMULATIVE(3),
        UNRECOGNIZED(-1);
        
        public static final int CUMULATIVE_VALUE = 3;
        public static final int DELTA_VALUE = 2;
        public static final int GAUGE_VALUE = 1;
        public static final int METRIC_KIND_UNSPECIFIED_VALUE = 0;
        private static final MetricKind[] VALUES = null;
        private static final EnumLiteMap<MetricKind> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<MetricKind>() {
                public MetricKind findValueByNumber(int i) {
                    return MetricKind.forNumber(i);
                }
            };
            VALUES = values();
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static MetricKind valueOf(int i) {
            return forNumber(i);
        }

        public static MetricKind forNumber(int i) {
            switch (i) {
                case 0:
                    return METRIC_KIND_UNSPECIFIED;
                case 1:
                    return GAUGE;
                case 2:
                    return DELTA;
                case 3:
                    return CUMULATIVE;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<MetricKind> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) MetricDescriptor.getDescriptor().getEnumTypes().get(0);
        }

        public static MetricKind valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        private MetricKind(int i) {
            this.value = i;
        }
    }

    public enum ValueType implements ProtocolMessageEnum {
        VALUE_TYPE_UNSPECIFIED(0),
        BOOL(1),
        INT64(2),
        DOUBLE(3),
        STRING(4),
        DISTRIBUTION(5),
        MONEY(6),
        UNRECOGNIZED(-1);
        
        public static final int BOOL_VALUE = 1;
        public static final int DISTRIBUTION_VALUE = 5;
        public static final int DOUBLE_VALUE = 3;
        public static final int INT64_VALUE = 2;
        public static final int MONEY_VALUE = 6;
        public static final int STRING_VALUE = 4;
        private static final ValueType[] VALUES = null;
        public static final int VALUE_TYPE_UNSPECIFIED_VALUE = 0;
        private static final EnumLiteMap<ValueType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<ValueType>() {
                public ValueType findValueByNumber(int i) {
                    return ValueType.forNumber(i);
                }
            };
            VALUES = values();
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        @Deprecated
        public static ValueType valueOf(int i) {
            return forNumber(i);
        }

        public static ValueType forNumber(int i) {
            switch (i) {
                case 0:
                    return VALUE_TYPE_UNSPECIFIED;
                case 1:
                    return BOOL;
                case 2:
                    return INT64;
                case 3:
                    return DOUBLE;
                case 4:
                    return STRING;
                case 5:
                    return DISTRIBUTION;
                case 6:
                    return MONEY;
                default:
                    return null;
            }
        }

        public static EnumLiteMap<ValueType> internalGetValueMap() {
            return internalValueMap;
        }

        public final EnumValueDescriptor getValueDescriptor() {
            return (EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final EnumDescriptor getDescriptor() {
            return (EnumDescriptor) MetricDescriptor.getDescriptor().getEnumTypes().get(1);
        }

        public static ValueType valueOf(EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            } else if (enumValueDescriptor.getIndex() == -1) {
                return UNRECOGNIZED;
            } else {
                return VALUES[enumValueDescriptor.getIndex()];
            }
        }

        private ValueType(int i) {
            this.value = i;
        }
    }

    private MetricDescriptor(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private MetricDescriptor() {
        this.memoizedIsInitialized = -1;
        this.name_ = "";
        this.type_ = "";
        this.labels_ = Collections.emptyList();
        this.metricKind_ = 0;
        this.valueType_ = 0;
        this.unit_ = "";
        this.description_ = "";
        this.displayName_ = "";
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private MetricDescriptor(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        this.name_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 18) {
                        if (!(z2 & true)) {
                            this.labels_ = new ArrayList();
                            z2 |= true;
                        }
                        this.labels_.add(codedInputStream.readMessage(LabelDescriptor.parser(), extensionRegistryLite));
                    } else if (readTag == 24) {
                        this.metricKind_ = codedInputStream.readEnum();
                    } else if (readTag == 32) {
                        this.valueType_ = codedInputStream.readEnum();
                    } else if (readTag == 42) {
                        this.unit_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 50) {
                        this.description_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 58) {
                        this.displayName_ = codedInputStream.readStringRequireUtf8();
                    } else if (readTag == 66) {
                        this.type_ = codedInputStream.readStringRequireUtf8();
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
        return MetricProto.internal_static_google_api_MetricDescriptor_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return MetricProto.internal_static_google_api_MetricDescriptor_fieldAccessorTable.ensureFieldAccessorsInitialized(MetricDescriptor.class, Builder.class);
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

    public int getMetricKindValue() {
        return this.metricKind_;
    }

    public MetricKind getMetricKind() {
        MetricKind valueOf = MetricKind.valueOf(this.metricKind_);
        return valueOf == null ? MetricKind.UNRECOGNIZED : valueOf;
    }

    public int getValueTypeValue() {
        return this.valueType_;
    }

    public ValueType getValueType() {
        ValueType valueOf = ValueType.valueOf(this.valueType_);
        return valueOf == null ? ValueType.UNRECOGNIZED : valueOf;
    }

    public String getUnit() {
        Object obj = this.unit_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.unit_ = stringUtf8;
        return stringUtf8;
    }

    public ByteString getUnitBytes() {
        Object obj = this.unit_;
        if (!(obj instanceof String)) {
            return (ByteString) obj;
        }
        ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
        this.unit_ = copyFromUtf8;
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
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        for (int i = 0; i < this.labels_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.labels_.get(i));
        }
        if (this.metricKind_ != MetricKind.METRIC_KIND_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(3, this.metricKind_);
        }
        if (this.valueType_ != ValueType.VALUE_TYPE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.valueType_);
        }
        if (!getUnitBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.unit_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 6, this.description_);
        }
        if (!getDisplayNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.displayName_);
        }
        if (!getTypeBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 8, this.type_);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) + 0 : 0;
        for (int i2 = 0; i2 < this.labels_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.labels_.get(i2));
        }
        if (this.metricKind_ != MetricKind.METRIC_KIND_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(3, this.metricKind_);
        }
        if (this.valueType_ != ValueType.VALUE_TYPE_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(4, this.valueType_);
        }
        if (!getUnitBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(5, this.unit_);
        }
        if (!getDescriptionBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(6, this.description_);
        }
        if (!getDisplayNameBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(7, this.displayName_);
        }
        if (!getTypeBytes().isEmpty()) {
            computeStringSize += GeneratedMessageV3.computeStringSize(8, this.type_);
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
        if (!(obj instanceof MetricDescriptor)) {
            return super.equals(obj);
        }
        MetricDescriptor metricDescriptor = (MetricDescriptor) obj;
        if (!((((((((getName().equals(metricDescriptor.getName())) && getType().equals(metricDescriptor.getType())) && getLabelsList().equals(metricDescriptor.getLabelsList())) && this.metricKind_ == metricDescriptor.metricKind_) && this.valueType_ == metricDescriptor.valueType_) && getUnit().equals(metricDescriptor.getUnit())) && getDescription().equals(metricDescriptor.getDescription())) && getDisplayName().equals(metricDescriptor.getDisplayName())) || !this.unknownFields.equals(metricDescriptor.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 8) * 53) + getType().hashCode();
        if (getLabelsCount() > 0) {
            hashCode = (((hashCode * 37) + 2) * 53) + getLabelsList().hashCode();
        }
        int hashCode2 = (((((((((((((((((((((hashCode * 37) + 3) * 53) + this.metricKind_) * 37) + 4) * 53) + this.valueType_) * 37) + 5) * 53) + getUnit().hashCode()) * 37) + 6) * 53) + getDescription().hashCode()) * 37) + 7) * 53) + getDisplayName().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static MetricDescriptor parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(byteBuffer);
    }

    public static MetricDescriptor parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(byteString);
    }

    public static MetricDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(bArr);
    }

    public static MetricDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricDescriptor) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(InputStream inputStream) throws IOException {
        return (MetricDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MetricDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MetricDescriptor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MetricDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricDescriptor) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MetricDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MetricDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricDescriptor) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MetricDescriptor metricDescriptor) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(metricDescriptor);
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

    public static MetricDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricDescriptor> parser() {
        return PARSER;
    }

    public Parser<MetricDescriptor> getParserForType() {
        return PARSER;
    }

    public MetricDescriptor getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
