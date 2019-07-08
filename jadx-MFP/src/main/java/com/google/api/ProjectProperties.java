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

public final class ProjectProperties extends GeneratedMessageV3 implements ProjectPropertiesOrBuilder {
    private static final ProjectProperties DEFAULT_INSTANCE = new ProjectProperties();
    /* access modifiers changed from: private */
    public static final Parser<ProjectProperties> PARSER = new AbstractParser<ProjectProperties>() {
        public ProjectProperties parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ProjectProperties(codedInputStream, extensionRegistryLite);
        }
    };
    public static final int PROPERTIES_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    /* access modifiers changed from: private */
    public List<Property> properties_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements ProjectPropertiesOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> propertiesBuilder_;
        private List<Property> properties_;

        public final boolean isInitialized() {
            return true;
        }

        public static final Descriptor getDescriptor() {
            return ConsumerProto.internal_static_google_api_ProjectProperties_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ConsumerProto.internal_static_google_api_ProjectProperties_fieldAccessorTable.ensureFieldAccessorsInitialized(ProjectProperties.class, Builder.class);
        }

        private Builder() {
            this.properties_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent builderParent) {
            super(builderParent);
            this.properties_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (ProjectProperties.alwaysUseFieldBuilders) {
                getPropertiesFieldBuilder();
            }
        }

        public Builder clear() {
            super.clear();
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.properties_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptor getDescriptorForType() {
            return ConsumerProto.internal_static_google_api_ProjectProperties_descriptor;
        }

        public ProjectProperties getDefaultInstanceForType() {
            return ProjectProperties.getDefaultInstance();
        }

        public ProjectProperties build() {
            ProjectProperties buildPartial = buildPartial();
            if (buildPartial.isInitialized()) {
                return buildPartial;
            }
            throw newUninitializedMessageException(buildPartial);
        }

        public ProjectProperties buildPartial() {
            ProjectProperties projectProperties = new ProjectProperties((com.google.protobuf.GeneratedMessageV3.Builder) this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) == 1) {
                    this.properties_ = Collections.unmodifiableList(this.properties_);
                    this.bitField0_ &= -2;
                }
                projectProperties.properties_ = this.properties_;
            } else {
                projectProperties.properties_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return projectProperties;
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
            if (message instanceof ProjectProperties) {
                return mergeFrom((ProjectProperties) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ProjectProperties projectProperties) {
            if (projectProperties == ProjectProperties.getDefaultInstance()) {
                return this;
            }
            if (this.propertiesBuilder_ == null) {
                if (!projectProperties.properties_.isEmpty()) {
                    if (this.properties_.isEmpty()) {
                        this.properties_ = projectProperties.properties_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePropertiesIsMutable();
                        this.properties_.addAll(projectProperties.properties_);
                    }
                    onChanged();
                }
            } else if (!projectProperties.properties_.isEmpty()) {
                if (this.propertiesBuilder_.isEmpty()) {
                    this.propertiesBuilder_.dispose();
                    RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = null;
                    this.propertiesBuilder_ = null;
                    this.properties_ = projectProperties.properties_;
                    this.bitField0_ &= -2;
                    if (ProjectProperties.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getPropertiesFieldBuilder();
                    }
                    this.propertiesBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.propertiesBuilder_.addAllMessages(projectProperties.properties_);
                }
            }
            mergeUnknownFields(projectProperties.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0023  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.google.api.ProjectProperties.Builder mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.io.IOException {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = com.google.api.ProjectProperties.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
                com.google.api.ProjectProperties r3 = (com.google.api.ProjectProperties) r3     // Catch:{ InvalidProtocolBufferException -> 0x0013 }
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
                com.google.api.ProjectProperties r4 = (com.google.api.ProjectProperties) r4     // Catch:{ all -> 0x0011 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.ProjectProperties.Builder.mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):com.google.api.ProjectProperties$Builder");
        }

        private void ensurePropertiesIsMutable() {
            if ((this.bitField0_ & 1) != 1) {
                this.properties_ = new ArrayList(this.properties_);
                this.bitField0_ |= 1;
            }
        }

        public List<Property> getPropertiesList() {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.properties_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        public int getPropertiesCount() {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.properties_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        public Property getProperties(int i) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (Property) this.properties_.get(i);
            }
            return (Property) repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setProperties(int i, Property property) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.setMessage(i, property);
            } else if (property != null) {
                ensurePropertiesIsMutable();
                this.properties_.set(i, property);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder setProperties(int i, com.google.api.Property.Builder builder) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                this.properties_.set(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.build());
            }
            return this;
        }

        public Builder addProperties(Property property) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(property);
            } else if (property != null) {
                ensurePropertiesIsMutable();
                this.properties_.add(property);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addProperties(int i, Property property) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                repeatedFieldBuilderV3.addMessage(i, property);
            } else if (property != null) {
                ensurePropertiesIsMutable();
                this.properties_.add(i, property);
                onChanged();
            } else {
                throw new NullPointerException();
            }
            return this;
        }

        public Builder addProperties(com.google.api.Property.Builder builder) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                this.properties_.add(builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.build());
            }
            return this;
        }

        public Builder addProperties(int i, com.google.api.Property.Builder builder) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                this.properties_.add(i, builder.build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.build());
            }
            return this;
        }

        public Builder addAllProperties(Iterable<? extends Property> iterable) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.properties_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearProperties() {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.properties_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeProperties(int i) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePropertiesIsMutable();
                this.properties_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public com.google.api.Property.Builder getPropertiesBuilder(int i) {
            return (com.google.api.Property.Builder) getPropertiesFieldBuilder().getBuilder(i);
        }

        public PropertyOrBuilder getPropertiesOrBuilder(int i) {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return (PropertyOrBuilder) this.properties_.get(i);
            }
            return (PropertyOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        public List<? extends PropertyOrBuilder> getPropertiesOrBuilderList() {
            RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> repeatedFieldBuilderV3 = this.propertiesBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.properties_);
        }

        public com.google.api.Property.Builder addPropertiesBuilder() {
            return (com.google.api.Property.Builder) getPropertiesFieldBuilder().addBuilder(Property.getDefaultInstance());
        }

        public com.google.api.Property.Builder addPropertiesBuilder(int i) {
            return (com.google.api.Property.Builder) getPropertiesFieldBuilder().addBuilder(i, Property.getDefaultInstance());
        }

        public List<com.google.api.Property.Builder> getPropertiesBuilderList() {
            return getPropertiesFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Property, com.google.api.Property.Builder, PropertyOrBuilder> getPropertiesFieldBuilder() {
            if (this.propertiesBuilder_ == null) {
                List<Property> list = this.properties_;
                boolean z = true;
                if ((this.bitField0_ & 1) != 1) {
                    z = false;
                }
                this.propertiesBuilder_ = new RepeatedFieldBuilderV3<>(list, z, getParentForChildren(), isClean());
                this.properties_ = null;
            }
            return this.propertiesBuilder_;
        }

        public final Builder setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFieldsProto3(unknownFieldSet);
        }

        public final Builder mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    private ProjectProperties(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = -1;
    }

    private ProjectProperties() {
        this.memoizedIsInitialized = -1;
        this.properties_ = Collections.emptyList();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    private ProjectProperties(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.properties_ = new ArrayList();
                            z2 |= true;
                        }
                        this.properties_.add(codedInputStream.readMessage(Property.parser(), extensionRegistryLite));
                    } else if (!parseUnknownFieldProto3(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        z = true;
                    }
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.properties_ = Collections.unmodifiableList(this.properties_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.properties_ = Collections.unmodifiableList(this.properties_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
            return;
        }
        throw new NullPointerException();
    }

    public static final Descriptor getDescriptor() {
        return ConsumerProto.internal_static_google_api_ProjectProperties_descriptor;
    }

    /* access modifiers changed from: protected */
    public FieldAccessorTable internalGetFieldAccessorTable() {
        return ConsumerProto.internal_static_google_api_ProjectProperties_fieldAccessorTable.ensureFieldAccessorsInitialized(ProjectProperties.class, Builder.class);
    }

    public List<Property> getPropertiesList() {
        return this.properties_;
    }

    public List<? extends PropertyOrBuilder> getPropertiesOrBuilderList() {
        return this.properties_;
    }

    public int getPropertiesCount() {
        return this.properties_.size();
    }

    public Property getProperties(int i) {
        return (Property) this.properties_.get(i);
    }

    public PropertyOrBuilder getPropertiesOrBuilder(int i) {
        return (PropertyOrBuilder) this.properties_.get(i);
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
        for (int i = 0; i < this.properties_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.properties_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.properties_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.properties_.get(i3));
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
        if (!(obj instanceof ProjectProperties)) {
            return super.equals(obj);
        }
        ProjectProperties projectProperties = (ProjectProperties) obj;
        if (!(getPropertiesList().equals(projectProperties.getPropertiesList())) || !this.unknownFields.equals(projectProperties.unknownFields)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hashCode = 779 + getDescriptor().hashCode();
        if (getPropertiesCount() > 0) {
            hashCode = (((hashCode * 37) + 1) * 53) + getPropertiesList().hashCode();
        }
        int hashCode2 = (hashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hashCode2;
        return hashCode2;
    }

    public static ProjectProperties parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ProjectProperties) PARSER.parseFrom(byteBuffer);
    }

    public static ProjectProperties parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProjectProperties) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ProjectProperties) PARSER.parseFrom(byteString);
    }

    public static ProjectProperties parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProjectProperties) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ProjectProperties) PARSER.parseFrom(bArr);
    }

    public static ProjectProperties parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProjectProperties) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(InputStream inputStream) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ProjectProperties parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ProjectProperties parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ProjectProperties parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ProjectProperties parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ProjectProperties parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProjectProperties) GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProjectProperties projectProperties) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(projectProperties);
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

    public static ProjectProperties getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProjectProperties> parser() {
        return PARSER;
    }

    public Parser<ProjectProperties> getParserForType() {
        return PARSER;
    }

    public ProjectProperties getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
