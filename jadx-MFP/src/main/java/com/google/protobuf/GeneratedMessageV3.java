package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.Syntax;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.GeneratedMessage.GeneratedExtension;
import com.google.protobuf.Internal.EnumLite;
import com.mopub.common.AdType;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public abstract class GeneratedMessageV3 extends AbstractMessage implements Serializable {
    /* access modifiers changed from: protected */
    public static boolean alwaysUseFieldBuilders = false;
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: protected */
    public UnknownFieldSet unknownFields;

    public static abstract class Builder<BuilderType extends Builder<BuilderType>> extends com.google.protobuf.AbstractMessage.Builder<BuilderType> {
        private BuilderParent builderParent;
        private boolean isClean;
        private BuilderParentImpl meAsParent;
        private UnknownFieldSet unknownFields;

        private class BuilderParentImpl implements BuilderParent {
            private BuilderParentImpl() {
            }

            public void markDirty() {
                Builder.this.onChanged();
            }
        }

        /* access modifiers changed from: protected */
        public abstract FieldAccessorTable internalGetFieldAccessorTable();

        protected Builder() {
            this(null);
        }

        protected Builder(BuilderParent builderParent2) {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            this.builderParent = builderParent2;
        }

        /* access modifiers changed from: 0000 */
        public void dispose() {
            this.builderParent = null;
        }

        /* access modifiers changed from: protected */
        public void onBuilt() {
            if (this.builderParent != null) {
                markClean();
            }
        }

        /* access modifiers changed from: protected */
        public void markClean() {
            this.isClean = true;
        }

        /* access modifiers changed from: protected */
        public boolean isClean() {
            return this.isClean;
        }

        public BuilderType clone() {
            BuilderType buildertype = (Builder) getDefaultInstanceForType().newBuilderForType();
            buildertype.mergeFrom(buildPartial());
            return buildertype;
        }

        public BuilderType clear() {
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
            onChanged();
            return this;
        }

        public Descriptor getDescriptorForType() {
            return internalGetFieldAccessorTable().descriptor;
        }

        public Map<FieldDescriptor, Object> getAllFields() {
            return Collections.unmodifiableMap(getAllFieldsMutable());
        }

        /* access modifiers changed from: private */
        public Map<FieldDescriptor, Object> getAllFieldsMutable() {
            TreeMap treeMap = new TreeMap();
            List fields = internalGetFieldAccessorTable().descriptor.getFields();
            int i = 0;
            while (i < fields.size()) {
                FieldDescriptor fieldDescriptor = (FieldDescriptor) fields.get(i);
                OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
                if (containingOneof != null) {
                    i += containingOneof.getFieldCount() - 1;
                    if (!hasOneof(containingOneof)) {
                        i++;
                    } else {
                        fieldDescriptor = getOneofFieldDescriptor(containingOneof);
                    }
                } else {
                    if (fieldDescriptor.isRepeated()) {
                        List list = (List) getField(fieldDescriptor);
                        if (!list.isEmpty()) {
                            treeMap.put(fieldDescriptor, list);
                        }
                    } else if (!hasField(fieldDescriptor)) {
                    }
                    i++;
                }
                treeMap.put(fieldDescriptor, getField(fieldDescriptor));
                i++;
            }
            return treeMap;
        }

        public com.google.protobuf.Message.Builder newBuilderForField(FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).newBuilder();
        }

        public com.google.protobuf.Message.Builder getFieldBuilder(FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getBuilder(this);
        }

        public com.google.protobuf.Message.Builder getRepeatedFieldBuilder(FieldDescriptor fieldDescriptor, int i) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeatedBuilder(this, i);
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            return internalGetFieldAccessorTable().getOneof(oneofDescriptor).has(this);
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            return internalGetFieldAccessorTable().getOneof(oneofDescriptor).get(this);
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).has(this);
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            Object obj = internalGetFieldAccessorTable().getField(fieldDescriptor).get(this);
            return fieldDescriptor.isRepeated() ? Collections.unmodifiableList((List) obj) : obj;
        }

        public BuilderType setField(FieldDescriptor fieldDescriptor, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).set(this, obj);
            return this;
        }

        public BuilderType clearField(FieldDescriptor fieldDescriptor) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).clear(this);
            return this;
        }

        public BuilderType clearOneof(OneofDescriptor oneofDescriptor) {
            internalGetFieldAccessorTable().getOneof(oneofDescriptor).clear(this);
            return this;
        }

        public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeatedCount(this);
        }

        public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
            return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeated(this, i);
        }

        public BuilderType setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).setRepeated(this, i, obj);
            return this;
        }

        public BuilderType addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            internalGetFieldAccessorTable().getField(fieldDescriptor).addRepeated(this, obj);
            return this;
        }

        public BuilderType setUnknownFields(UnknownFieldSet unknownFieldSet) {
            this.unknownFields = unknownFieldSet;
            onChanged();
            return this;
        }

        /* access modifiers changed from: protected */
        public BuilderType setUnknownFieldsProto3(UnknownFieldSet unknownFieldSet) {
            if (CodedInputStream.getProto3DiscardUnknownFieldsDefault()) {
                return this;
            }
            this.unknownFields = unknownFieldSet;
            onChanged();
            return this;
        }

        public BuilderType mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return setUnknownFields(UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(unknownFieldSet).build());
        }

        public boolean isInitialized() {
            for (FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                    return false;
                }
                if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                    if (fieldDescriptor.isRepeated()) {
                        for (Message isInitialized : (List) getField(fieldDescriptor)) {
                            if (!isInitialized.isInitialized()) {
                                return false;
                            }
                        }
                        continue;
                    } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                        return false;
                    }
                }
            }
            return true;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* access modifiers changed from: protected */
        public BuilderParent getParentForChildren() {
            if (this.meAsParent == null) {
                this.meAsParent = new BuilderParentImpl<>();
            }
            return this.meAsParent;
        }

        /* access modifiers changed from: protected */
        public final void onChanged() {
            if (this.isClean) {
                BuilderParent builderParent2 = this.builderParent;
                if (builderParent2 != null) {
                    builderParent2.markDirty();
                    this.isClean = false;
                }
            }
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMapField(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append("No map fields found in ");
            sb.append(getClass().getName());
            throw new RuntimeException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public MapField internalGetMutableMapField(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append("No map fields found in ");
            sb.append(getClass().getName());
            throw new RuntimeException(sb.toString());
        }
    }

    protected interface BuilderParent extends BuilderParent {
    }

    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<BuilderType> implements ExtendableMessageOrBuilder<MessageType> {
        private FieldSet<FieldDescriptor> extensions = FieldSet.emptySet();

        protected ExtendableBuilder() {
        }

        protected ExtendableBuilder(BuilderParent builderParent) {
            super(builderParent);
        }

        /* access modifiers changed from: 0000 */
        public void internalSetExtensionSet(FieldSet<FieldDescriptor> fieldSet) {
            this.extensions = fieldSet;
        }

        public BuilderType clear() {
            this.extensions = FieldSet.emptySet();
            return (ExtendableBuilder) super.clear();
        }

        private void ensureExtensionsIsMutable() {
            if (this.extensions.isImmutable()) {
                this.extensions = this.extensions.clone();
            }
        }

        private void verifyExtensionContainingType(Extension<MessageType, ?> extension) {
            if (extension.getDescriptor().getContainingType() != getDescriptorForType()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Extension is for type \"");
                sb.append(extension.getDescriptor().getContainingType().getFullName());
                sb.append("\" which does not match message type \"");
                sb.append(getDescriptorForType().getFullName());
                sb.append("\".");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extensionLite) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            return this.extensions.hasField(access$500.getDescriptor());
        }

        public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extensionLite) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            return this.extensions.getRepeatedFieldCount(access$500.getDescriptor());
        }

        public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extensionLite) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            FieldDescriptor descriptor = access$500.getDescriptor();
            Object field = this.extensions.getField(descriptor);
            if (field != null) {
                return access$500.fromReflectionType(field);
            }
            if (descriptor.isRepeated()) {
                return Collections.emptyList();
            }
            if (descriptor.getJavaType() == JavaType.MESSAGE) {
                return access$500.getMessageDefaultInstance();
            }
            return access$500.fromReflectionType(descriptor.getDefaultValue());
        }

        public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int i) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            return access$500.singularFromReflectionType(this.extensions.getRepeatedField(access$500.getDescriptor(), i));
        }

        public final <Type> BuilderType setExtension(ExtensionLite<MessageType, Type> extensionLite, Type type) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            ensureExtensionsIsMutable();
            this.extensions.setField(access$500.getDescriptor(), access$500.toReflectionType(type));
            onChanged();
            return this;
        }

        public final <Type> BuilderType setExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int i, Type type) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(access$500.getDescriptor(), i, access$500.singularToReflectionType(type));
            onChanged();
            return this;
        }

        public final <Type> BuilderType addExtension(ExtensionLite<MessageType, List<Type>> extensionLite, Type type) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(access$500.getDescriptor(), access$500.singularToReflectionType(type));
            onChanged();
            return this;
        }

        public final <Type> BuilderType clearExtension(ExtensionLite<MessageType, ?> extensionLite) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            ensureExtensionsIsMutable();
            this.extensions.clearField(access$500.getDescriptor());
            onChanged();
            return this;
        }

        public final <Type> boolean hasExtension(Extension<MessageType, Type> extension) {
            return hasExtension((ExtensionLite<MessageType, Type>) extension);
        }

        public final <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            return hasExtension((ExtensionLite<MessageType, Type>) generatedExtension);
        }

        public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension) {
            return getExtensionCount((ExtensionLite<MessageType, List<Type>>) extension);
        }

        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension) {
            return getExtensionCount((ExtensionLite<MessageType, List<Type>>) generatedExtension);
        }

        public final <Type> Type getExtension(Extension<MessageType, Type> extension) {
            return getExtension((ExtensionLite<MessageType, Type>) extension);
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            return getExtension((ExtensionLite<MessageType, Type>) generatedExtension);
        }

        public final <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int i) {
            return getExtension((ExtensionLite<MessageType, List<Type>>) extension, i);
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i) {
            return getExtension((ExtensionLite<MessageType, List<Type>>) generatedExtension, i);
        }

        public final <Type> BuilderType setExtension(Extension<MessageType, Type> extension, Type type) {
            return setExtension((ExtensionLite<MessageType, Type>) extension, type);
        }

        public <Type> BuilderType setExtension(GeneratedExtension<MessageType, Type> generatedExtension, Type type) {
            return setExtension((ExtensionLite<MessageType, Type>) generatedExtension, type);
        }

        public final <Type> BuilderType setExtension(Extension<MessageType, List<Type>> extension, int i, Type type) {
            return setExtension((ExtensionLite<MessageType, List<Type>>) extension, i, type);
        }

        public <Type> BuilderType setExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i, Type type) {
            return setExtension((ExtensionLite<MessageType, List<Type>>) generatedExtension, i, type);
        }

        public final <Type> BuilderType addExtension(Extension<MessageType, List<Type>> extension, Type type) {
            return addExtension((ExtensionLite<MessageType, List<Type>>) extension, type);
        }

        public <Type> BuilderType addExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, Type type) {
            return addExtension((ExtensionLite<MessageType, List<Type>>) generatedExtension, type);
        }

        public final <Type> BuilderType clearExtension(Extension<MessageType, ?> extension) {
            return clearExtension((ExtensionLite<MessageType, ?>) extension);
        }

        public <Type> BuilderType clearExtension(GeneratedExtension<MessageType, ?> generatedExtension) {
            return clearExtension((ExtensionLite<MessageType, ?>) generatedExtension);
        }

        /* access modifiers changed from: protected */
        public boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        /* access modifiers changed from: private */
        public FieldSet<FieldDescriptor> buildExtensions() {
            this.extensions.makeImmutable();
            return this.extensions;
        }

        public boolean isInitialized() {
            return super.isInitialized() && extensionsAreInitialized();
        }

        public Map<FieldDescriptor, Object> getAllFields() {
            Map access$900 = getAllFieldsMutable();
            access$900.putAll(this.extensions.getAllFields());
            return Collections.unmodifiableMap(access$900);
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            Object field = this.extensions.getField(fieldDescriptor);
            if (field != null) {
                return field;
            }
            if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                return DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType());
            }
            return fieldDescriptor.getDefaultValue();
        }

        public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedFieldCount(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedFieldCount(fieldDescriptor);
        }

        public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
            if (!fieldDescriptor.isExtension()) {
                return super.getRepeatedField(fieldDescriptor, i);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedField(fieldDescriptor, i);
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return super.hasField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.hasField(fieldDescriptor);
        }

        public BuilderType setField(FieldDescriptor fieldDescriptor, Object obj) {
            if (!fieldDescriptor.isExtension()) {
                return (ExtendableBuilder) super.setField(fieldDescriptor, obj);
            }
            verifyContainingType(fieldDescriptor);
            ensureExtensionsIsMutable();
            this.extensions.setField(fieldDescriptor, obj);
            onChanged();
            return this;
        }

        public BuilderType clearField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return (ExtendableBuilder) super.clearField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            ensureExtensionsIsMutable();
            this.extensions.clearField(fieldDescriptor);
            onChanged();
            return this;
        }

        public BuilderType setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            if (!fieldDescriptor.isExtension()) {
                return (ExtendableBuilder) super.setRepeatedField(fieldDescriptor, i, obj);
            }
            verifyContainingType(fieldDescriptor);
            ensureExtensionsIsMutable();
            this.extensions.setRepeatedField(fieldDescriptor, i, obj);
            onChanged();
            return this;
        }

        public BuilderType addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            if (!fieldDescriptor.isExtension()) {
                return (ExtendableBuilder) super.addRepeatedField(fieldDescriptor, obj);
            }
            verifyContainingType(fieldDescriptor);
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(fieldDescriptor, obj);
            onChanged();
            return this;
        }

        /* access modifiers changed from: protected */
        public final void mergeExtensionFields(ExtendableMessage extendableMessage) {
            ensureExtensionsIsMutable();
            this.extensions.mergeFrom(extendableMessage.extensions);
            onChanged();
        }

        private void verifyContainingType(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }
    }

    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage> extends GeneratedMessageV3 implements ExtendableMessageOrBuilder<MessageType> {
        private static final long serialVersionUID = 1;
        /* access modifiers changed from: private */
        public final FieldSet<FieldDescriptor> extensions;

        protected class ExtensionWriter {
            private final Iterator<Entry<FieldDescriptor, Object>> iter;
            private final boolean messageSetWireFormat;
            private Entry<FieldDescriptor, Object> next;

            private ExtensionWriter(boolean z) {
                this.iter = ExtendableMessage.this.extensions.iterator();
                if (this.iter.hasNext()) {
                    this.next = (Entry) this.iter.next();
                }
                this.messageSetWireFormat = z;
            }

            public void writeUntil(int i, CodedOutputStream codedOutputStream) throws IOException {
                while (true) {
                    Entry<FieldDescriptor, Object> entry = this.next;
                    if (entry != null && ((FieldDescriptor) entry.getKey()).getNumber() < i) {
                        FieldDescriptor fieldDescriptor = (FieldDescriptor) this.next.getKey();
                        if (!this.messageSetWireFormat || fieldDescriptor.getLiteJavaType() != WireFormat.JavaType.MESSAGE || fieldDescriptor.isRepeated()) {
                            FieldSet.writeField(fieldDescriptor, this.next.getValue(), codedOutputStream);
                        } else if (this.next instanceof LazyEntry) {
                            codedOutputStream.writeRawMessageSetExtension(fieldDescriptor.getNumber(), ((LazyEntry) this.next).getField().toByteString());
                        } else {
                            codedOutputStream.writeMessageSetExtension(fieldDescriptor.getNumber(), (Message) this.next.getValue());
                        }
                        if (this.iter.hasNext()) {
                            this.next = (Entry) this.iter.next();
                        } else {
                            this.next = null;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        protected ExtendableMessage() {
            this.extensions = FieldSet.newFieldSet();
        }

        protected ExtendableMessage(ExtendableBuilder<MessageType, ?> extendableBuilder) {
            super(extendableBuilder);
            this.extensions = extendableBuilder.buildExtensions();
        }

        private void verifyExtensionContainingType(Extension<MessageType, ?> extension) {
            if (extension.getDescriptor().getContainingType() != getDescriptorForType()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Extension is for type \"");
                sb.append(extension.getDescriptor().getContainingType().getFullName());
                sb.append("\" which does not match message type \"");
                sb.append(getDescriptorForType().getFullName());
                sb.append("\".");
                throw new IllegalArgumentException(sb.toString());
            }
        }

        public final <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extensionLite) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            return this.extensions.hasField(access$500.getDescriptor());
        }

        public final <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extensionLite) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            return this.extensions.getRepeatedFieldCount(access$500.getDescriptor());
        }

        public final <Type> Type getExtension(ExtensionLite<MessageType, Type> extensionLite) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            FieldDescriptor descriptor = access$500.getDescriptor();
            Object field = this.extensions.getField(descriptor);
            if (field != null) {
                return access$500.fromReflectionType(field);
            }
            if (descriptor.isRepeated()) {
                return Collections.emptyList();
            }
            if (descriptor.getJavaType() == JavaType.MESSAGE) {
                return access$500.getMessageDefaultInstance();
            }
            return access$500.fromReflectionType(descriptor.getDefaultValue());
        }

        public final <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int i) {
            Extension access$500 = GeneratedMessageV3.checkNotLite(extensionLite);
            verifyExtensionContainingType(access$500);
            return access$500.singularFromReflectionType(this.extensions.getRepeatedField(access$500.getDescriptor(), i));
        }

        public final <Type> boolean hasExtension(Extension<MessageType, Type> extension) {
            return hasExtension((ExtensionLite<MessageType, Type>) extension);
        }

        public final <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            return hasExtension((ExtensionLite<MessageType, Type>) generatedExtension);
        }

        public final <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension) {
            return getExtensionCount((ExtensionLite<MessageType, List<Type>>) extension);
        }

        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension) {
            return getExtensionCount((ExtensionLite<MessageType, List<Type>>) generatedExtension);
        }

        public final <Type> Type getExtension(Extension<MessageType, Type> extension) {
            return getExtension((ExtensionLite<MessageType, Type>) extension);
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            return getExtension((ExtensionLite<MessageType, Type>) generatedExtension);
        }

        public final <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int i) {
            return getExtension((ExtensionLite<MessageType, List<Type>>) extension, i);
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i) {
            return getExtension((ExtensionLite<MessageType, List<Type>>) generatedExtension, i);
        }

        /* access modifiers changed from: protected */
        public boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        public boolean isInitialized() {
            return GeneratedMessageV3.super.isInitialized() && extensionsAreInitialized();
        }

        /* access modifiers changed from: protected */
        public boolean parseUnknownField(CodedInputStream codedInputStream, com.google.protobuf.UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
            if (codedInputStream.shouldDiscardUnknownFields()) {
                builder = null;
            }
            return MessageReflection.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, getDescriptorForType(), new ExtensionAdapter(this.extensions), i);
        }

        /* access modifiers changed from: protected */
        public boolean parseUnknownFieldProto3(CodedInputStream codedInputStream, com.google.protobuf.UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
            if (codedInputStream.shouldDiscardUnknownFieldsProto3()) {
                builder = null;
            }
            return MessageReflection.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, getDescriptorForType(), new ExtensionAdapter(this.extensions), i);
        }

        /* access modifiers changed from: protected */
        public void makeExtensionsImmutable() {
            this.extensions.makeImmutable();
        }

        /* access modifiers changed from: protected */
        public ExtensionWriter newExtensionWriter() {
            return new ExtensionWriter<>(false);
        }

        /* access modifiers changed from: protected */
        public ExtensionWriter newMessageSetExtensionWriter() {
            return new ExtensionWriter<>(true);
        }

        /* access modifiers changed from: protected */
        public int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }

        /* access modifiers changed from: protected */
        public int extensionsSerializedSizeAsMessageSet() {
            return this.extensions.getMessageSetSerializedSize();
        }

        /* access modifiers changed from: protected */
        public Map<FieldDescriptor, Object> getExtensionFields() {
            return this.extensions.getAllFields();
        }

        public Map<FieldDescriptor, Object> getAllFields() {
            Map access$800 = getAllFieldsMutable(false);
            access$800.putAll(getExtensionFields());
            return Collections.unmodifiableMap(access$800);
        }

        public Map<FieldDescriptor, Object> getAllFieldsRaw() {
            Map access$800 = getAllFieldsMutable(false);
            access$800.putAll(getExtensionFields());
            return Collections.unmodifiableMap(access$800);
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return GeneratedMessageV3.super.hasField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.hasField(fieldDescriptor);
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return GeneratedMessageV3.super.getField(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            Object field = this.extensions.getField(fieldDescriptor);
            if (field != null) {
                return field;
            }
            if (fieldDescriptor.isRepeated()) {
                return Collections.emptyList();
            }
            if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                return DynamicMessage.getDefaultInstance(fieldDescriptor.getMessageType());
            }
            return fieldDescriptor.getDefaultValue();
        }

        public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
            if (!fieldDescriptor.isExtension()) {
                return GeneratedMessageV3.super.getRepeatedFieldCount(fieldDescriptor);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedFieldCount(fieldDescriptor);
        }

        public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
            if (!fieldDescriptor.isExtension()) {
                return GeneratedMessageV3.super.getRepeatedField(fieldDescriptor, i);
            }
            verifyContainingType(fieldDescriptor);
            return this.extensions.getRepeatedField(fieldDescriptor, i);
        }

        private void verifyContainingType(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != getDescriptorForType()) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            }
        }
    }

    public interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage> extends MessageOrBuilder {
        Message getDefaultInstanceForType();

        <Type> Type getExtension(Extension<MessageType, Type> extension);

        <Type> Type getExtension(Extension<MessageType, List<Type>> extension, int i);

        <Type> Type getExtension(ExtensionLite<MessageType, Type> extensionLite);

        <Type> Type getExtension(ExtensionLite<MessageType, List<Type>> extensionLite, int i);

        <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension);

        <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i);

        <Type> int getExtensionCount(Extension<MessageType, List<Type>> extension);

        <Type> int getExtensionCount(ExtensionLite<MessageType, List<Type>> extensionLite);

        <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension);

        <Type> boolean hasExtension(Extension<MessageType, Type> extension);

        <Type> boolean hasExtension(ExtensionLite<MessageType, Type> extensionLite);

        <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> generatedExtension);
    }

    interface ExtensionDescriptorRetriever {
        FieldDescriptor getDescriptor();
    }

    public static final class FieldAccessorTable {
        private String[] camelCaseNames;
        /* access modifiers changed from: private */
        public final Descriptor descriptor;
        private final FieldAccessor[] fields;
        private volatile boolean initialized;
        private final OneofAccessor[] oneofs;

        private interface FieldAccessor {
            void addRepeated(Builder builder, Object obj);

            void clear(Builder builder);

            Object get(Builder builder);

            Object get(GeneratedMessageV3 generatedMessageV3);

            com.google.protobuf.Message.Builder getBuilder(Builder builder);

            Object getRaw(Builder builder);

            Object getRaw(GeneratedMessageV3 generatedMessageV3);

            Object getRepeated(Builder builder, int i);

            Object getRepeated(GeneratedMessageV3 generatedMessageV3, int i);

            com.google.protobuf.Message.Builder getRepeatedBuilder(Builder builder, int i);

            int getRepeatedCount(Builder builder);

            int getRepeatedCount(GeneratedMessageV3 generatedMessageV3);

            Object getRepeatedRaw(Builder builder, int i);

            Object getRepeatedRaw(GeneratedMessageV3 generatedMessageV3, int i);

            boolean has(Builder builder);

            boolean has(GeneratedMessageV3 generatedMessageV3);

            com.google.protobuf.Message.Builder newBuilder();

            void set(Builder builder, Object obj);

            void setRepeated(Builder builder, int i, Object obj);
        }

        private static class MapFieldAccessor implements FieldAccessor {
            private final FieldDescriptor field;
            private final Message mapEntryMessageDefaultInstance;

            MapFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2) {
                this.field = fieldDescriptor;
                this.mapEntryMessageDefaultInstance = getMapField((GeneratedMessageV3) GeneratedMessageV3.invokeOrDie(GeneratedMessageV3.getMethodOrDie(cls, "getDefaultInstance", new Class[0]), null, new Object[0])).getMapEntryMessageDefaultInstance();
            }

            private MapField<?, ?> getMapField(GeneratedMessageV3 generatedMessageV3) {
                return generatedMessageV3.internalGetMapField(this.field.getNumber());
            }

            private MapField<?, ?> getMapField(Builder builder) {
                return builder.internalGetMapField(this.field.getNumber());
            }

            private MapField<?, ?> getMutableMapField(Builder builder) {
                return builder.internalGetMutableMapField(this.field.getNumber());
            }

            private Message coerceType(Message message) {
                if (message == null) {
                    return null;
                }
                if (this.mapEntryMessageDefaultInstance.getClass().isInstance(message)) {
                    return message;
                }
                return this.mapEntryMessageDefaultInstance.toBuilder().mergeFrom(message).build();
            }

            public Object get(GeneratedMessageV3 generatedMessageV3) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < getRepeatedCount(generatedMessageV3); i++) {
                    arrayList.add(getRepeated(generatedMessageV3, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            public Object get(Builder builder) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < getRepeatedCount(builder); i++) {
                    arrayList.add(getRepeated(builder, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            public Object getRaw(GeneratedMessageV3 generatedMessageV3) {
                return get(generatedMessageV3);
            }

            public Object getRaw(Builder builder) {
                return get(builder);
            }

            public void set(Builder builder, Object obj) {
                clear(builder);
                for (Object addRepeated : (List) obj) {
                    addRepeated(builder, addRepeated);
                }
            }

            public Object getRepeated(GeneratedMessageV3 generatedMessageV3, int i) {
                return getMapField(generatedMessageV3).getList().get(i);
            }

            public Object getRepeated(Builder builder, int i) {
                return getMapField(builder).getList().get(i);
            }

            public Object getRepeatedRaw(GeneratedMessageV3 generatedMessageV3, int i) {
                return getRepeated(generatedMessageV3, i);
            }

            public Object getRepeatedRaw(Builder builder, int i) {
                return getRepeated(builder, i);
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                getMutableMapField(builder).getMutableList().set(i, coerceType((Message) obj));
            }

            public void addRepeated(Builder builder, Object obj) {
                getMutableMapField(builder).getMutableList().add(coerceType((Message) obj));
            }

            public boolean has(GeneratedMessageV3 generatedMessageV3) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            public boolean has(Builder builder) {
                throw new UnsupportedOperationException("hasField() is not supported for repeated fields.");
            }

            public int getRepeatedCount(GeneratedMessageV3 generatedMessageV3) {
                return getMapField(generatedMessageV3).getList().size();
            }

            public int getRepeatedCount(Builder builder) {
                return getMapField(builder).getList().size();
            }

            public void clear(Builder builder) {
                getMutableMapField(builder).getMutableList().clear();
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                return this.mapEntryMessageDefaultInstance.newBuilderForType();
            }

            public com.google.protobuf.Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("Nested builder not supported for map fields.");
            }

            public com.google.protobuf.Message.Builder getRepeatedBuilder(Builder builder, int i) {
                throw new UnsupportedOperationException("Nested builder not supported for map fields.");
            }
        }

        private static class OneofAccessor {
            private final Method caseMethod;
            private final Method caseMethodBuilder;
            private final Method clearMethod;
            private final Descriptor descriptor;

            OneofAccessor(Descriptor descriptor2, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2) {
                this.descriptor = descriptor2;
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(str);
                sb.append("Case");
                this.caseMethod = GeneratedMessageV3.getMethodOrDie(cls, sb.toString(), new Class[0]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("get");
                sb2.append(str);
                sb2.append("Case");
                this.caseMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
                StringBuilder sb3 = new StringBuilder();
                sb3.append(AdType.CLEAR);
                sb3.append(str);
                this.clearMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb3.toString(), new Class[0]);
            }

            public boolean has(GeneratedMessageV3 generatedMessageV3) {
                return ((EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethod, generatedMessageV3, new Object[0])).getNumber() != 0;
            }

            public boolean has(Builder builder) {
                return ((EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber() != 0;
            }

            public FieldDescriptor get(GeneratedMessageV3 generatedMessageV3) {
                int number = ((EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethod, generatedMessageV3, new Object[0])).getNumber();
                if (number > 0) {
                    return this.descriptor.findFieldByNumber(number);
                }
                return null;
            }

            public FieldDescriptor get(Builder builder) {
                int number = ((EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
                if (number > 0) {
                    return this.descriptor.findFieldByNumber(number);
                }
                return null;
            }

            public void clear(Builder builder) {
                GeneratedMessageV3.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }
        }

        private static final class RepeatedEnumFieldAccessor extends RepeatedFieldAccessor {
            private Method addRepeatedValueMethod;
            private EnumDescriptor enumDescriptor;
            private Method getRepeatedValueMethod;
            private Method getRepeatedValueMethodBuilder;
            private final Method getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            private Method setRepeatedValueMethod;
            private boolean supportUnknownEnumValue;
            private final Method valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", EnumValueDescriptor.class);

            RepeatedEnumFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2) {
                super(fieldDescriptor, str, cls, cls2);
                this.enumDescriptor = fieldDescriptor.getEnumType();
                this.supportUnknownEnumValue = fieldDescriptor.getFile().supportsUnknownEnumValue();
                if (this.supportUnknownEnumValue) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("get");
                    sb.append(str);
                    sb.append("Value");
                    this.getRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(cls, sb.toString(), Integer.TYPE);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("get");
                    sb2.append(str);
                    sb2.append("Value");
                    this.getRepeatedValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb2.toString(), Integer.TYPE);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("set");
                    sb3.append(str);
                    sb3.append("Value");
                    this.setRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb3.toString(), Integer.TYPE, Integer.TYPE);
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("add");
                    sb4.append(str);
                    sb4.append("Value");
                    this.addRepeatedValueMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb4.toString(), Integer.TYPE);
                }
            }

            public Object get(GeneratedMessageV3 generatedMessageV3) {
                ArrayList arrayList = new ArrayList();
                int repeatedCount = getRepeatedCount(generatedMessageV3);
                for (int i = 0; i < repeatedCount; i++) {
                    arrayList.add(getRepeated(generatedMessageV3, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            public Object get(Builder builder) {
                ArrayList arrayList = new ArrayList();
                int repeatedCount = getRepeatedCount(builder);
                for (int i = 0; i < repeatedCount; i++) {
                    arrayList.add(getRepeated(builder, i));
                }
                return Collections.unmodifiableList(arrayList);
            }

            public Object getRepeated(GeneratedMessageV3 generatedMessageV3, int i) {
                if (!this.supportUnknownEnumValue) {
                    return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(generatedMessageV3, i), new Object[0]);
                }
                return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethod, generatedMessageV3, Integer.valueOf(i))).intValue());
            }

            public Object getRepeated(Builder builder, int i) {
                if (!this.supportUnknownEnumValue) {
                    return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(builder, i), new Object[0]);
                }
                return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessageV3.invokeOrDie(this.getRepeatedValueMethodBuilder, builder, Integer.valueOf(i))).intValue());
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                if (this.supportUnknownEnumValue) {
                    GeneratedMessageV3.invokeOrDie(this.setRepeatedValueMethod, builder, Integer.valueOf(i), Integer.valueOf(((EnumValueDescriptor) obj).getNumber()));
                    return;
                }
                super.setRepeated(builder, i, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, obj));
            }

            public void addRepeated(Builder builder, Object obj) {
                if (this.supportUnknownEnumValue) {
                    GeneratedMessageV3.invokeOrDie(this.addRepeatedValueMethod, builder, Integer.valueOf(((EnumValueDescriptor) obj).getNumber()));
                    return;
                }
                super.addRepeated(builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, obj));
            }
        }

        private static class RepeatedFieldAccessor implements FieldAccessor {
            protected final Method addRepeatedMethod;
            protected final Method clearMethod;
            protected final Method getCountMethod;
            protected final Method getCountMethodBuilder;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final Method getRepeatedMethod;
            protected final Method getRepeatedMethodBuilder;
            protected final Method setRepeatedMethod;
            protected final Class type = this.getRepeatedMethod.getReturnType();

            RepeatedFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2) {
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(str);
                sb.append("List");
                this.getMethod = GeneratedMessageV3.getMethodOrDie(cls, sb.toString(), new Class[0]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("get");
                sb2.append(str);
                sb2.append("List");
                this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("get");
                sb3.append(str);
                this.getRepeatedMethod = GeneratedMessageV3.getMethodOrDie(cls, sb3.toString(), Integer.TYPE);
                StringBuilder sb4 = new StringBuilder();
                sb4.append("get");
                sb4.append(str);
                this.getRepeatedMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb4.toString(), Integer.TYPE);
                StringBuilder sb5 = new StringBuilder();
                sb5.append("set");
                sb5.append(str);
                this.setRepeatedMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb5.toString(), Integer.TYPE, this.type);
                StringBuilder sb6 = new StringBuilder();
                sb6.append("add");
                sb6.append(str);
                this.addRepeatedMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb6.toString(), this.type);
                StringBuilder sb7 = new StringBuilder();
                sb7.append("get");
                sb7.append(str);
                sb7.append("Count");
                this.getCountMethod = GeneratedMessageV3.getMethodOrDie(cls, sb7.toString(), new Class[0]);
                StringBuilder sb8 = new StringBuilder();
                sb8.append("get");
                sb8.append(str);
                sb8.append("Count");
                this.getCountMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb8.toString(), new Class[0]);
                StringBuilder sb9 = new StringBuilder();
                sb9.append(AdType.CLEAR);
                sb9.append(str);
                this.clearMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb9.toString(), new Class[0]);
            }

            public Object get(GeneratedMessageV3 generatedMessageV3) {
                return GeneratedMessageV3.invokeOrDie(this.getMethod, generatedMessageV3, new Object[0]);
            }

            public Object get(Builder builder) {
                return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            public Object getRaw(GeneratedMessageV3 generatedMessageV3) {
                return get(generatedMessageV3);
            }

            public Object getRaw(Builder builder) {
                return get(builder);
            }

            public void set(Builder builder, Object obj) {
                clear(builder);
                for (Object addRepeated : (List) obj) {
                    addRepeated(builder, addRepeated);
                }
            }

            public Object getRepeated(GeneratedMessageV3 generatedMessageV3, int i) {
                return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethod, generatedMessageV3, Integer.valueOf(i));
            }

            public Object getRepeated(Builder builder, int i) {
                return GeneratedMessageV3.invokeOrDie(this.getRepeatedMethodBuilder, builder, Integer.valueOf(i));
            }

            public Object getRepeatedRaw(GeneratedMessageV3 generatedMessageV3, int i) {
                return getRepeated(generatedMessageV3, i);
            }

            public Object getRepeatedRaw(Builder builder, int i) {
                return getRepeated(builder, i);
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                GeneratedMessageV3.invokeOrDie(this.setRepeatedMethod, builder, Integer.valueOf(i), obj);
            }

            public void addRepeated(Builder builder, Object obj) {
                GeneratedMessageV3.invokeOrDie(this.addRepeatedMethod, builder, obj);
            }

            public boolean has(GeneratedMessageV3 generatedMessageV3) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            public boolean has(Builder builder) {
                throw new UnsupportedOperationException("hasField() called on a repeated field.");
            }

            public int getRepeatedCount(GeneratedMessageV3 generatedMessageV3) {
                return ((Integer) GeneratedMessageV3.invokeOrDie(this.getCountMethod, generatedMessageV3, new Object[0])).intValue();
            }

            public int getRepeatedCount(Builder builder) {
                return ((Integer) GeneratedMessageV3.invokeOrDie(this.getCountMethodBuilder, builder, new Object[0])).intValue();
            }

            public void clear(Builder builder) {
                GeneratedMessageV3.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            public com.google.protobuf.Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }

            public com.google.protobuf.Message.Builder getRepeatedBuilder(Builder builder, int i) {
                throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
            }
        }

        private static final class RepeatedMessageFieldAccessor extends RepeatedFieldAccessor {
            private final Method getBuilderMethodBuilder;
            private final Method newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);

            RepeatedMessageFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2) {
                super(fieldDescriptor, str, cls, cls2);
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(str);
                sb.append("Builder");
                this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb.toString(), Integer.TYPE);
            }

            private Object coerceType(Object obj) {
                if (this.type.isInstance(obj)) {
                    return obj;
                }
                return ((com.google.protobuf.Message.Builder) GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) obj).build();
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                super.setRepeated(builder, i, coerceType(obj));
            }

            public void addRepeated(Builder builder, Object obj) {
                super.addRepeated(builder, coerceType(obj));
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                return (com.google.protobuf.Message.Builder) GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            public com.google.protobuf.Message.Builder getRepeatedBuilder(Builder builder, int i) {
                return (com.google.protobuf.Message.Builder) GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, builder, Integer.valueOf(i));
            }
        }

        private static final class SingularEnumFieldAccessor extends SingularFieldAccessor {
            private EnumDescriptor enumDescriptor;
            private Method getValueDescriptorMethod = GeneratedMessageV3.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
            private Method getValueMethod;
            private Method getValueMethodBuilder;
            private Method setValueMethod;
            private boolean supportUnknownEnumValue;
            private Method valueOfMethod = GeneratedMessageV3.getMethodOrDie(this.type, "valueOf", EnumValueDescriptor.class);

            SingularEnumFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2, String str2) {
                super(fieldDescriptor, str, cls, cls2, str2);
                this.enumDescriptor = fieldDescriptor.getEnumType();
                this.supportUnknownEnumValue = fieldDescriptor.getFile().supportsUnknownEnumValue();
                if (this.supportUnknownEnumValue) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("get");
                    sb.append(str);
                    sb.append("Value");
                    this.getValueMethod = GeneratedMessageV3.getMethodOrDie(cls, sb.toString(), new Class[0]);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("get");
                    sb2.append(str);
                    sb2.append("Value");
                    this.getValueMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("set");
                    sb3.append(str);
                    sb3.append("Value");
                    this.setValueMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb3.toString(), Integer.TYPE);
                }
            }

            public Object get(GeneratedMessageV3 generatedMessageV3) {
                if (!this.supportUnknownEnumValue) {
                    return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(generatedMessageV3), new Object[0]);
                }
                return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessageV3.invokeOrDie(this.getValueMethod, generatedMessageV3, new Object[0])).intValue());
            }

            public Object get(Builder builder) {
                if (!this.supportUnknownEnumValue) {
                    return GeneratedMessageV3.invokeOrDie(this.getValueDescriptorMethod, super.get(builder), new Object[0]);
                }
                return this.enumDescriptor.findValueByNumberCreatingIfUnknown(((Integer) GeneratedMessageV3.invokeOrDie(this.getValueMethodBuilder, builder, new Object[0])).intValue());
            }

            public void set(Builder builder, Object obj) {
                if (this.supportUnknownEnumValue) {
                    GeneratedMessageV3.invokeOrDie(this.setValueMethod, builder, Integer.valueOf(((EnumValueDescriptor) obj).getNumber()));
                    return;
                }
                super.set(builder, GeneratedMessageV3.invokeOrDie(this.valueOfMethod, null, obj));
            }
        }

        private static class SingularFieldAccessor implements FieldAccessor {
            protected final Method caseMethod;
            protected final Method caseMethodBuilder;
            protected final Method clearMethod;
            protected final FieldDescriptor field;
            protected final Method getMethod;
            protected final Method getMethodBuilder;
            protected final boolean hasHasMethod;
            protected final Method hasMethod;
            protected final Method hasMethodBuilder;
            protected final boolean isOneofField;
            protected final Method setMethod;
            protected final Class<?> type;

            SingularFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2, String str2) {
                Method method;
                Method method2;
                Method method3;
                this.field = fieldDescriptor;
                this.isOneofField = fieldDescriptor.getContainingOneof() != null;
                this.hasHasMethod = FieldAccessorTable.supportFieldPresence(fieldDescriptor.getFile()) || (!this.isOneofField && fieldDescriptor.getJavaType() == JavaType.MESSAGE);
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(str);
                this.getMethod = GeneratedMessageV3.getMethodOrDie(cls, sb.toString(), new Class[0]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("get");
                sb2.append(str);
                this.getMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
                this.type = this.getMethod.getReturnType();
                StringBuilder sb3 = new StringBuilder();
                sb3.append("set");
                sb3.append(str);
                this.setMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb3.toString(), this.type);
                Method method4 = null;
                if (this.hasHasMethod) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("has");
                    sb4.append(str);
                    method = GeneratedMessageV3.getMethodOrDie(cls, sb4.toString(), new Class[0]);
                } else {
                    method = null;
                }
                this.hasMethod = method;
                if (this.hasHasMethod) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("has");
                    sb5.append(str);
                    method2 = GeneratedMessageV3.getMethodOrDie(cls2, sb5.toString(), new Class[0]);
                } else {
                    method2 = null;
                }
                this.hasMethodBuilder = method2;
                StringBuilder sb6 = new StringBuilder();
                sb6.append(AdType.CLEAR);
                sb6.append(str);
                this.clearMethod = GeneratedMessageV3.getMethodOrDie(cls2, sb6.toString(), new Class[0]);
                if (this.isOneofField) {
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("get");
                    sb7.append(str2);
                    sb7.append("Case");
                    method3 = GeneratedMessageV3.getMethodOrDie(cls, sb7.toString(), new Class[0]);
                } else {
                    method3 = null;
                }
                this.caseMethod = method3;
                if (this.isOneofField) {
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("get");
                    sb8.append(str2);
                    sb8.append("Case");
                    method4 = GeneratedMessageV3.getMethodOrDie(cls2, sb8.toString(), new Class[0]);
                }
                this.caseMethodBuilder = method4;
            }

            private int getOneofFieldNumber(GeneratedMessageV3 generatedMessageV3) {
                return ((EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethod, generatedMessageV3, new Object[0])).getNumber();
            }

            private int getOneofFieldNumber(Builder builder) {
                return ((EnumLite) GeneratedMessageV3.invokeOrDie(this.caseMethodBuilder, builder, new Object[0])).getNumber();
            }

            public Object get(GeneratedMessageV3 generatedMessageV3) {
                return GeneratedMessageV3.invokeOrDie(this.getMethod, generatedMessageV3, new Object[0]);
            }

            public Object get(Builder builder) {
                return GeneratedMessageV3.invokeOrDie(this.getMethodBuilder, builder, new Object[0]);
            }

            public Object getRaw(GeneratedMessageV3 generatedMessageV3) {
                return get(generatedMessageV3);
            }

            public Object getRaw(Builder builder) {
                return get(builder);
            }

            public void set(Builder builder, Object obj) {
                GeneratedMessageV3.invokeOrDie(this.setMethod, builder, obj);
            }

            public Object getRepeated(GeneratedMessageV3 generatedMessageV3, int i) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            public Object getRepeatedRaw(GeneratedMessageV3 generatedMessageV3, int i) {
                throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
            }

            public Object getRepeated(Builder builder, int i) {
                throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
            }

            public Object getRepeatedRaw(Builder builder, int i) {
                throw new UnsupportedOperationException("getRepeatedFieldRaw() called on a singular field.");
            }

            public void setRepeated(Builder builder, int i, Object obj) {
                throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
            }

            public void addRepeated(Builder builder, Object obj) {
                throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
            }

            public boolean has(GeneratedMessageV3 generatedMessageV3) {
                boolean z = false;
                if (this.hasHasMethod) {
                    return ((Boolean) GeneratedMessageV3.invokeOrDie(this.hasMethod, generatedMessageV3, new Object[0])).booleanValue();
                }
                if (!this.isOneofField) {
                    return !get(generatedMessageV3).equals(this.field.getDefaultValue());
                }
                if (getOneofFieldNumber(generatedMessageV3) == this.field.getNumber()) {
                    z = true;
                }
                return z;
            }

            public boolean has(Builder builder) {
                boolean z = false;
                if (this.hasHasMethod) {
                    return ((Boolean) GeneratedMessageV3.invokeOrDie(this.hasMethodBuilder, builder, new Object[0])).booleanValue();
                }
                if (!this.isOneofField) {
                    return !get(builder).equals(this.field.getDefaultValue());
                }
                if (getOneofFieldNumber(builder) == this.field.getNumber()) {
                    z = true;
                }
                return z;
            }

            public int getRepeatedCount(GeneratedMessageV3 generatedMessageV3) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            public int getRepeatedCount(Builder builder) {
                throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
            }

            public void clear(Builder builder) {
                GeneratedMessageV3.invokeOrDie(this.clearMethod, builder, new Object[0]);
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
            }

            public com.google.protobuf.Message.Builder getBuilder(Builder builder) {
                throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
            }

            public com.google.protobuf.Message.Builder getRepeatedBuilder(Builder builder, int i) {
                throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on a non-Message type.");
            }
        }

        private static final class SingularMessageFieldAccessor extends SingularFieldAccessor {
            private final Method getBuilderMethodBuilder;
            private final Method newBuilderMethod = GeneratedMessageV3.getMethodOrDie(this.type, "newBuilder", new Class[0]);

            SingularMessageFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2, String str2) {
                super(fieldDescriptor, str, cls, cls2, str2);
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(str);
                sb.append("Builder");
                this.getBuilderMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb.toString(), new Class[0]);
            }

            private Object coerceType(Object obj) {
                if (this.type.isInstance(obj)) {
                    return obj;
                }
                return ((com.google.protobuf.Message.Builder) GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message) obj).buildPartial();
            }

            public void set(Builder builder, Object obj) {
                super.set(builder, coerceType(obj));
            }

            public com.google.protobuf.Message.Builder newBuilder() {
                return (com.google.protobuf.Message.Builder) GeneratedMessageV3.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
            }

            public com.google.protobuf.Message.Builder getBuilder(Builder builder) {
                return (com.google.protobuf.Message.Builder) GeneratedMessageV3.invokeOrDie(this.getBuilderMethodBuilder, builder, new Object[0]);
            }
        }

        private static final class SingularStringFieldAccessor extends SingularFieldAccessor {
            private final Method getBytesMethod;
            private final Method getBytesMethodBuilder;
            private final Method setBytesMethodBuilder;

            SingularStringFieldAccessor(FieldDescriptor fieldDescriptor, String str, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2, String str2) {
                super(fieldDescriptor, str, cls, cls2, str2);
                StringBuilder sb = new StringBuilder();
                sb.append("get");
                sb.append(str);
                sb.append("Bytes");
                this.getBytesMethod = GeneratedMessageV3.getMethodOrDie(cls, sb.toString(), new Class[0]);
                StringBuilder sb2 = new StringBuilder();
                sb2.append("get");
                sb2.append(str);
                sb2.append("Bytes");
                this.getBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb2.toString(), new Class[0]);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("set");
                sb3.append(str);
                sb3.append("Bytes");
                this.setBytesMethodBuilder = GeneratedMessageV3.getMethodOrDie(cls2, sb3.toString(), ByteString.class);
            }

            public Object getRaw(GeneratedMessageV3 generatedMessageV3) {
                return GeneratedMessageV3.invokeOrDie(this.getBytesMethod, generatedMessageV3, new Object[0]);
            }

            public Object getRaw(Builder builder) {
                return GeneratedMessageV3.invokeOrDie(this.getBytesMethodBuilder, builder, new Object[0]);
            }

            public void set(Builder builder, Object obj) {
                if (obj instanceof ByteString) {
                    GeneratedMessageV3.invokeOrDie(this.setBytesMethodBuilder, builder, obj);
                    return;
                }
                super.set(builder, obj);
            }
        }

        public FieldAccessorTable(Descriptor descriptor2, String[] strArr, Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2) {
            this(descriptor2, strArr);
            ensureFieldAccessorsInitialized(cls, cls2);
        }

        public FieldAccessorTable(Descriptor descriptor2, String[] strArr) {
            this.descriptor = descriptor2;
            this.camelCaseNames = strArr;
            this.fields = new FieldAccessor[descriptor2.getFields().size()];
            this.oneofs = new OneofAccessor[descriptor2.getOneofs().size()];
            this.initialized = false;
        }

        public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessageV3> cls, Class<? extends Builder> cls2) {
            if (this.initialized) {
                return this;
            }
            synchronized (this) {
                if (this.initialized) {
                    return this;
                }
                int length = this.fields.length;
                for (int i = 0; i < length; i++) {
                    FieldDescriptor fieldDescriptor = (FieldDescriptor) this.descriptor.getFields().get(i);
                    String str = fieldDescriptor.getContainingOneof() != null ? this.camelCaseNames[fieldDescriptor.getContainingOneof().getIndex() + length] : null;
                    if (fieldDescriptor.isRepeated()) {
                        if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                            if (fieldDescriptor.isMapField()) {
                                this.fields[i] = new MapFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2);
                            } else {
                                this.fields[i] = new RepeatedMessageFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2);
                            }
                        } else if (fieldDescriptor.getJavaType() == JavaType.ENUM) {
                            this.fields[i] = new RepeatedEnumFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2);
                        } else {
                            this.fields[i] = new RepeatedFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2);
                        }
                    } else if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                        FieldAccessor[] fieldAccessorArr = this.fields;
                        SingularMessageFieldAccessor singularMessageFieldAccessor = new SingularMessageFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2, str);
                        fieldAccessorArr[i] = singularMessageFieldAccessor;
                    } else if (fieldDescriptor.getJavaType() == JavaType.ENUM) {
                        FieldAccessor[] fieldAccessorArr2 = this.fields;
                        SingularEnumFieldAccessor singularEnumFieldAccessor = new SingularEnumFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2, str);
                        fieldAccessorArr2[i] = singularEnumFieldAccessor;
                    } else if (fieldDescriptor.getJavaType() == JavaType.STRING) {
                        FieldAccessor[] fieldAccessorArr3 = this.fields;
                        SingularStringFieldAccessor singularStringFieldAccessor = new SingularStringFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2, str);
                        fieldAccessorArr3[i] = singularStringFieldAccessor;
                    } else {
                        FieldAccessor[] fieldAccessorArr4 = this.fields;
                        SingularFieldAccessor singularFieldAccessor = new SingularFieldAccessor(fieldDescriptor, this.camelCaseNames[i], cls, cls2, str);
                        fieldAccessorArr4[i] = singularFieldAccessor;
                    }
                }
                int length2 = this.oneofs.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    this.oneofs[i2] = new OneofAccessor(this.descriptor, this.camelCaseNames[i2 + length], cls, cls2);
                }
                this.initialized = true;
                this.camelCaseNames = null;
                return this;
            }
        }

        /* access modifiers changed from: private */
        public FieldAccessor getField(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.getContainingType() != this.descriptor) {
                throw new IllegalArgumentException("FieldDescriptor does not match message type.");
            } else if (!fieldDescriptor.isExtension()) {
                return this.fields[fieldDescriptor.getIndex()];
            } else {
                throw new IllegalArgumentException("This type does not have extensions.");
            }
        }

        /* access modifiers changed from: private */
        public OneofAccessor getOneof(OneofDescriptor oneofDescriptor) {
            if (oneofDescriptor.getContainingType() == this.descriptor) {
                return this.oneofs[oneofDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("OneofDescriptor does not match message type.");
        }

        /* access modifiers changed from: private */
        public static boolean supportFieldPresence(FileDescriptor fileDescriptor) {
            return fileDescriptor.getSyntax() == Syntax.PROTO2;
        }
    }

    /* access modifiers changed from: protected */
    public abstract FieldAccessorTable internalGetFieldAccessorTable();

    /* access modifiers changed from: protected */
    public void makeExtensionsImmutable() {
    }

    /* access modifiers changed from: protected */
    public abstract com.google.protobuf.Message.Builder newBuilderForType(BuilderParent builderParent);

    protected GeneratedMessageV3() {
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }

    protected GeneratedMessageV3(Builder<?> builder) {
        this.unknownFields = builder.getUnknownFields();
    }

    public Parser<? extends GeneratedMessageV3> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    static void enableAlwaysUseFieldBuildersForTesting() {
        alwaysUseFieldBuilders = true;
    }

    public Descriptor getDescriptorForType() {
        return internalGetFieldAccessorTable().descriptor;
    }

    /* access modifiers changed from: private */
    public Map<FieldDescriptor, Object> getAllFieldsMutable(boolean z) {
        TreeMap treeMap = new TreeMap();
        List fields = internalGetFieldAccessorTable().descriptor.getFields();
        int i = 0;
        while (i < fields.size()) {
            FieldDescriptor fieldDescriptor = (FieldDescriptor) fields.get(i);
            OneofDescriptor containingOneof = fieldDescriptor.getContainingOneof();
            if (containingOneof != null) {
                i += containingOneof.getFieldCount() - 1;
                if (!hasOneof(containingOneof)) {
                    i++;
                } else {
                    fieldDescriptor = getOneofFieldDescriptor(containingOneof);
                }
            } else {
                if (fieldDescriptor.isRepeated()) {
                    List list = (List) getField(fieldDescriptor);
                    if (!list.isEmpty()) {
                        treeMap.put(fieldDescriptor, list);
                    }
                } else if (!hasField(fieldDescriptor)) {
                }
                i++;
            }
            if (!z || fieldDescriptor.getJavaType() != JavaType.STRING) {
                treeMap.put(fieldDescriptor, getField(fieldDescriptor));
                i++;
            } else {
                treeMap.put(fieldDescriptor, getFieldRaw(fieldDescriptor));
                i++;
            }
        }
        return treeMap;
    }

    public boolean isInitialized() {
        for (FieldDescriptor fieldDescriptor : getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !hasField(fieldDescriptor)) {
                return false;
            }
            if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                if (fieldDescriptor.isRepeated()) {
                    for (Message isInitialized : (List) getField(fieldDescriptor)) {
                        if (!isInitialized.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (hasField(fieldDescriptor) && !((Message) getField(fieldDescriptor)).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Map<FieldDescriptor, Object> getAllFields() {
        return Collections.unmodifiableMap(getAllFieldsMutable(false));
    }

    /* access modifiers changed from: 0000 */
    public Map<FieldDescriptor, Object> getAllFieldsRaw() {
        return Collections.unmodifiableMap(getAllFieldsMutable(true));
    }

    public boolean hasOneof(OneofDescriptor oneofDescriptor) {
        return internalGetFieldAccessorTable().getOneof(oneofDescriptor).has(this);
    }

    public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
        return internalGetFieldAccessorTable().getOneof(oneofDescriptor).get(this);
    }

    public boolean hasField(FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).has(this);
    }

    public Object getField(FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).get(this);
    }

    /* access modifiers changed from: 0000 */
    public Object getFieldRaw(FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).getRaw(this);
    }

    public int getRepeatedFieldCount(FieldDescriptor fieldDescriptor) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeatedCount(this);
    }

    public Object getRepeatedField(FieldDescriptor fieldDescriptor, int i) {
        return internalGetFieldAccessorTable().getField(fieldDescriptor).getRepeated(this, i);
    }

    public UnknownFieldSet getUnknownFields() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    /* access modifiers changed from: protected */
    public boolean parseUnknownField(CodedInputStream codedInputStream, com.google.protobuf.UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
        if (codedInputStream.shouldDiscardUnknownFields()) {
            return codedInputStream.skipField(i);
        }
        return builder.mergeFieldFrom(i, codedInputStream);
    }

    /* access modifiers changed from: protected */
    public boolean parseUnknownFieldProto3(CodedInputStream codedInputStream, com.google.protobuf.UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
        if (codedInputStream.shouldDiscardUnknownFieldsProto3()) {
            return codedInputStream.skipField(i);
        }
        return builder.mergeFieldFrom(i, codedInputStream);
    }

    protected static <M extends Message> M parseWithIOException(Parser<M> parser, InputStream inputStream) throws IOException {
        try {
            return (Message) parser.parseFrom(inputStream);
        } catch (InvalidProtocolBufferException e) {
            throw e.unwrapIOException();
        }
    }

    protected static <M extends Message> M parseWithIOException(Parser<M> parser, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        try {
            return (Message) parser.parseFrom(inputStream, extensionRegistryLite);
        } catch (InvalidProtocolBufferException e) {
            throw e.unwrapIOException();
        }
    }

    protected static <M extends Message> M parseWithIOException(Parser<M> parser, CodedInputStream codedInputStream) throws IOException {
        try {
            return (Message) parser.parseFrom(codedInputStream);
        } catch (InvalidProtocolBufferException e) {
            throw e.unwrapIOException();
        }
    }

    protected static <M extends Message> M parseWithIOException(Parser<M> parser, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        try {
            return (Message) parser.parseFrom(codedInputStream, extensionRegistryLite);
        } catch (InvalidProtocolBufferException e) {
            throw e.unwrapIOException();
        }
    }

    protected static <M extends Message> M parseDelimitedWithIOException(Parser<M> parser, InputStream inputStream) throws IOException {
        try {
            return (Message) parser.parseDelimitedFrom(inputStream);
        } catch (InvalidProtocolBufferException e) {
            throw e.unwrapIOException();
        }
    }

    protected static <M extends Message> M parseDelimitedWithIOException(Parser<M> parser, InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        try {
            return (Message) parser.parseDelimitedFrom(inputStream, extensionRegistryLite);
        } catch (InvalidProtocolBufferException e) {
            throw e.unwrapIOException();
        }
    }

    protected static boolean canUseUnsafe() {
        return UnsafeUtil.hasUnsafeArrayOperations() && UnsafeUtil.hasUnsafeByteBufferOperations();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        MessageReflection.writeMessageTo(this, getAllFieldsRaw(), codedOutputStream, false);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSize = MessageReflection.getSerializedSize(this, getAllFieldsRaw());
        return this.memoizedSize;
    }

    /* access modifiers changed from: protected */
    public com.google.protobuf.Message.Builder newBuilderForType(final BuilderParent builderParent) {
        return newBuilderForType((BuilderParent) new BuilderParent() {
            public void markDirty() {
                builderParent.markDirty();
            }
        });
    }

    /* access modifiers changed from: private */
    public static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Generated message class \"");
            sb.append(cls.getName());
            sb.append("\" missing method \"");
            sb.append(str);
            sb.append("\".");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public static Object invokeOrDie(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    /* access modifiers changed from: protected */
    public MapField internalGetMapField(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("No map fields found in ");
        sb.append(getClass().getName());
        throw new RuntimeException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public Object writeReplace() throws ObjectStreamException {
        return new SerializedForm(this);
    }

    /* access modifiers changed from: private */
    public static <MessageType extends ExtendableMessage<MessageType>, T> Extension<MessageType, T> checkNotLite(ExtensionLite<MessageType, T> extensionLite) {
        if (!extensionLite.isLite()) {
            return (Extension) extensionLite;
        }
        throw new IllegalArgumentException("Expected non-lite extension.");
    }

    protected static int computeStringSize(int i, Object obj) {
        if (obj instanceof String) {
            return CodedOutputStream.computeStringSize(i, (String) obj);
        }
        return CodedOutputStream.computeBytesSize(i, (ByteString) obj);
    }

    protected static int computeStringSizeNoTag(Object obj) {
        if (obj instanceof String) {
            return CodedOutputStream.computeStringSizeNoTag((String) obj);
        }
        return CodedOutputStream.computeBytesSizeNoTag((ByteString) obj);
    }

    protected static void writeString(CodedOutputStream codedOutputStream, int i, Object obj) throws IOException {
        if (obj instanceof String) {
            codedOutputStream.writeString(i, (String) obj);
        } else {
            codedOutputStream.writeBytes(i, (ByteString) obj);
        }
    }

    protected static void writeStringNoTag(CodedOutputStream codedOutputStream, Object obj) throws IOException {
        if (obj instanceof String) {
            codedOutputStream.writeStringNoTag((String) obj);
        } else {
            codedOutputStream.writeBytesNoTag((ByteString) obj);
        }
    }

    protected static <V> void serializeIntegerMapTo(CodedOutputStream codedOutputStream, MapField<Integer, V> mapField, MapEntry<Integer, V> mapEntry, int i) throws IOException {
        Map map = mapField.getMap();
        if (!codedOutputStream.isSerializationDeterministic()) {
            serializeMapTo(codedOutputStream, map, mapEntry, i);
            return;
        }
        int[] iArr = new int[map.size()];
        int i2 = 0;
        for (Integer intValue : map.keySet()) {
            int i3 = i2 + 1;
            iArr[i2] = intValue.intValue();
            i2 = i3;
        }
        Arrays.sort(iArr);
        for (int i4 : iArr) {
            codedOutputStream.writeMessage(i, mapEntry.newBuilderForType().setKey(Integer.valueOf(i4)).setValue(map.get(Integer.valueOf(i4))).build());
        }
    }

    protected static <V> void serializeLongMapTo(CodedOutputStream codedOutputStream, MapField<Long, V> mapField, MapEntry<Long, V> mapEntry, int i) throws IOException {
        Map map = mapField.getMap();
        if (!codedOutputStream.isSerializationDeterministic()) {
            serializeMapTo(codedOutputStream, map, mapEntry, i);
            return;
        }
        long[] jArr = new long[map.size()];
        int i2 = 0;
        for (Long longValue : map.keySet()) {
            int i3 = i2 + 1;
            jArr[i2] = longValue.longValue();
            i2 = i3;
        }
        Arrays.sort(jArr);
        for (long j : jArr) {
            codedOutputStream.writeMessage(i, mapEntry.newBuilderForType().setKey(Long.valueOf(j)).setValue(map.get(Long.valueOf(j))).build());
        }
    }

    protected static <V> void serializeStringMapTo(CodedOutputStream codedOutputStream, MapField<String, V> mapField, MapEntry<String, V> mapEntry, int i) throws IOException {
        Map map = mapField.getMap();
        if (!codedOutputStream.isSerializationDeterministic()) {
            serializeMapTo(codedOutputStream, map, mapEntry, i);
            return;
        }
        String[] strArr = (String[]) map.keySet().toArray(new String[map.size()]);
        Arrays.sort(strArr);
        for (String str : strArr) {
            codedOutputStream.writeMessage(i, mapEntry.newBuilderForType().setKey(str).setValue(map.get(str)).build());
        }
    }

    protected static <V> void serializeBooleanMapTo(CodedOutputStream codedOutputStream, MapField<Boolean, V> mapField, MapEntry<Boolean, V> mapEntry, int i) throws IOException {
        Map map = mapField.getMap();
        if (!codedOutputStream.isSerializationDeterministic()) {
            serializeMapTo(codedOutputStream, map, mapEntry, i);
            return;
        }
        maybeSerializeBooleanEntryTo(codedOutputStream, map, mapEntry, i, false);
        maybeSerializeBooleanEntryTo(codedOutputStream, map, mapEntry, i, true);
    }

    private static <V> void maybeSerializeBooleanEntryTo(CodedOutputStream codedOutputStream, Map<Boolean, V> map, MapEntry<Boolean, V> mapEntry, int i, boolean z) throws IOException {
        if (map.containsKey(Boolean.valueOf(z))) {
            codedOutputStream.writeMessage(i, mapEntry.newBuilderForType().setKey(Boolean.valueOf(z)).setValue(map.get(Boolean.valueOf(z))).build());
        }
    }

    private static <K, V> void serializeMapTo(CodedOutputStream codedOutputStream, Map<K, V> map, MapEntry<K, V> mapEntry, int i) throws IOException {
        for (Entry entry : map.entrySet()) {
            codedOutputStream.writeMessage(i, mapEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
    }
}
