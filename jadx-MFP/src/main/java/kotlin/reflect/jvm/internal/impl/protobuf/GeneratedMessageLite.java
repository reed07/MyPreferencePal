package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import kotlin.reflect.jvm.internal.impl.protobuf.FieldSet.FieldDescriptorLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLite;
import kotlin.reflect.jvm.internal.impl.protobuf.Internal.EnumLiteMap;
import kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.FieldType;
import kotlin.reflect.jvm.internal.impl.protobuf.WireFormat.JavaType;

public abstract class GeneratedMessageLite extends AbstractMessageLite implements Serializable {

    public static abstract class Builder<MessageType extends GeneratedMessageLite, BuilderType extends Builder> extends kotlin.reflect.jvm.internal.impl.protobuf.AbstractMessageLite.Builder<BuilderType> {
        private ByteString unknownFields = ByteString.EMPTY;

        public abstract MessageType getDefaultInstanceForType();

        public abstract BuilderType mergeFrom(MessageType messagetype);

        protected Builder() {
        }

        public BuilderType clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        public final ByteString getUnknownFields() {
            return this.unknownFields;
        }

        public final BuilderType setUnknownFields(ByteString byteString) {
            this.unknownFields = byteString;
            return this;
        }
    }

    public static abstract class ExtendableBuilder<MessageType extends ExtendableMessage<MessageType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends Builder<MessageType, BuilderType> implements ExtendableMessageOrBuilder<MessageType> {
        private FieldSet<ExtensionDescriptor> extensions = FieldSet.emptySet();
        private boolean extensionsIsMutable;

        protected ExtendableBuilder() {
        }

        private void ensureExtensionsIsMutable() {
            if (!this.extensionsIsMutable) {
                this.extensions = this.extensions.clone();
                this.extensionsIsMutable = true;
            }
        }

        /* access modifiers changed from: private */
        public FieldSet<ExtensionDescriptor> buildExtensions() {
            this.extensions.makeImmutable();
            this.extensionsIsMutable = false;
            return this.extensions;
        }

        public BuilderType clone() {
            throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
        }

        /* access modifiers changed from: protected */
        public boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        /* access modifiers changed from: protected */
        public final void mergeExtensionFields(MessageType messagetype) {
            ensureExtensionsIsMutable();
            this.extensions.mergeFrom(messagetype.extensions);
        }
    }

    public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType>> extends GeneratedMessageLite implements ExtendableMessageOrBuilder<MessageType> {
        /* access modifiers changed from: private */
        public final FieldSet<ExtensionDescriptor> extensions;

        protected class ExtensionWriter {
            private final Iterator<Entry<ExtensionDescriptor, Object>> iter;
            private final boolean messageSetWireFormat;
            private Entry<ExtensionDescriptor, Object> next;

            private ExtensionWriter(boolean z) {
                this.iter = ExtendableMessage.this.extensions.iterator();
                if (this.iter.hasNext()) {
                    this.next = (Entry) this.iter.next();
                }
                this.messageSetWireFormat = z;
            }

            public void writeUntil(int i, CodedOutputStream codedOutputStream) throws IOException {
                while (true) {
                    Entry<ExtensionDescriptor, Object> entry = this.next;
                    if (entry != null && ((ExtensionDescriptor) entry.getKey()).getNumber() < i) {
                        ExtensionDescriptor extensionDescriptor = (ExtensionDescriptor) this.next.getKey();
                        if (!this.messageSetWireFormat || extensionDescriptor.getLiteJavaType() != JavaType.MESSAGE || extensionDescriptor.isRepeated()) {
                            FieldSet.writeField(extensionDescriptor, this.next.getValue(), codedOutputStream);
                        } else {
                            codedOutputStream.writeMessageSetExtension(extensionDescriptor.getNumber(), (MessageLite) this.next.getValue());
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
            this.extensions = extendableBuilder.buildExtensions();
        }

        private void verifyExtensionContainingType(GeneratedExtension<MessageType, ?> generatedExtension) {
            if (generatedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType()) {
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            }
        }

        public final <Type> boolean hasExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.hasField(generatedExtension.descriptor);
        }

        public final <Type> int getExtensionCount(GeneratedExtension<MessageType, List<Type>> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            return this.extensions.getRepeatedFieldCount(generatedExtension.descriptor);
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, Type> generatedExtension) {
            verifyExtensionContainingType(generatedExtension);
            Object field = this.extensions.getField(generatedExtension.descriptor);
            if (field == null) {
                return generatedExtension.defaultValue;
            }
            return generatedExtension.fromFieldSetType(field);
        }

        public final <Type> Type getExtension(GeneratedExtension<MessageType, List<Type>> generatedExtension, int i) {
            verifyExtensionContainingType(generatedExtension);
            return generatedExtension.singularFromFieldSetType(this.extensions.getRepeatedField(generatedExtension.descriptor, i));
        }

        /* access modifiers changed from: protected */
        public boolean extensionsAreInitialized() {
            return this.extensions.isInitialized();
        }

        /* access modifiers changed from: protected */
        public boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
            return GeneratedMessageLite.parseUnknownField(this.extensions, getDefaultInstanceForType(), codedInputStream, codedOutputStream, extensionRegistryLite, i);
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
        public int extensionsSerializedSize() {
            return this.extensions.getSerializedSize();
        }
    }

    public interface ExtendableMessageOrBuilder<MessageType extends ExtendableMessage> extends MessageLiteOrBuilder {
    }

    static final class ExtensionDescriptor implements FieldDescriptorLite<ExtensionDescriptor> {
        final EnumLiteMap<?> enumTypeMap;
        final boolean isPacked;
        final boolean isRepeated;
        final int number;
        final FieldType type;

        ExtensionDescriptor(EnumLiteMap<?> enumLiteMap, int i, FieldType fieldType, boolean z, boolean z2) {
            this.enumTypeMap = enumLiteMap;
            this.number = i;
            this.type = fieldType;
            this.isRepeated = z;
            this.isPacked = z2;
        }

        public int getNumber() {
            return this.number;
        }

        public FieldType getLiteType() {
            return this.type;
        }

        public JavaType getLiteJavaType() {
            return this.type.getJavaType();
        }

        public boolean isRepeated() {
            return this.isRepeated;
        }

        public boolean isPacked() {
            return this.isPacked;
        }

        public EnumLiteMap<?> getEnumType() {
            return this.enumTypeMap;
        }

        public kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder internalMergeFrom(kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder builder, MessageLite messageLite) {
            return ((Builder) builder).mergeFrom((GeneratedMessageLite) messageLite);
        }

        public int compareTo(ExtensionDescriptor extensionDescriptor) {
            return this.number - extensionDescriptor.number;
        }
    }

    public static class GeneratedExtension<ContainingType extends MessageLite, Type> {
        final ContainingType containingTypeDefaultInstance;
        final Type defaultValue;
        final ExtensionDescriptor descriptor;
        final Method enumValueOf;
        final MessageLite messageDefaultInstance;
        final Class singularType;

        GeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, ExtensionDescriptor extensionDescriptor, Class cls) {
            if (containingtype == null) {
                throw new IllegalArgumentException("Null containingTypeDefaultInstance");
            } else if (extensionDescriptor.getLiteType() == FieldType.MESSAGE && messageLite == null) {
                throw new IllegalArgumentException("Null messageDefaultInstance");
            } else {
                this.containingTypeDefaultInstance = containingtype;
                this.defaultValue = type;
                this.messageDefaultInstance = messageLite;
                this.descriptor = extensionDescriptor;
                this.singularType = cls;
                if (EnumLite.class.isAssignableFrom(cls)) {
                    this.enumValueOf = GeneratedMessageLite.getMethodOrDie(cls, "valueOf", Integer.TYPE);
                    return;
                }
                this.enumValueOf = null;
            }
        }

        public ContainingType getContainingTypeDefaultInstance() {
            return this.containingTypeDefaultInstance;
        }

        public int getNumber() {
            return this.descriptor.getNumber();
        }

        public MessageLite getMessageDefaultInstance() {
            return this.messageDefaultInstance;
        }

        /* access modifiers changed from: 0000 */
        public Object fromFieldSetType(Object obj) {
            if (!this.descriptor.isRepeated()) {
                return singularFromFieldSetType(obj);
            }
            if (this.descriptor.getLiteJavaType() != JavaType.ENUM) {
                return obj;
            }
            ArrayList arrayList = new ArrayList();
            for (Object singularFromFieldSetType : (List) obj) {
                arrayList.add(singularFromFieldSetType(singularFromFieldSetType));
            }
            return arrayList;
        }

        /* access modifiers changed from: 0000 */
        public Object singularFromFieldSetType(Object obj) {
            if (this.descriptor.getLiteJavaType() != JavaType.ENUM) {
                return obj;
            }
            return GeneratedMessageLite.invokeOrDie(this.enumValueOf, null, (Integer) obj);
        }

        /* access modifiers changed from: 0000 */
        public Object singularToFieldSetType(Object obj) {
            return this.descriptor.getLiteJavaType() == JavaType.ENUM ? Integer.valueOf(((EnumLite) obj).getNumber()) : obj;
        }
    }

    /* access modifiers changed from: protected */
    public void makeExtensionsImmutable() {
    }

    protected GeneratedMessageLite() {
    }

    protected GeneratedMessageLite(Builder builder) {
    }

    public Parser<? extends MessageLite> getParserForType() {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    /* access modifiers changed from: protected */
    public boolean parseUnknownField(CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
        return codedInputStream.skipField(i, codedOutputStream);
    }

    /* access modifiers changed from: private */
    public static <MessageType extends MessageLite> boolean parseUnknownField(FieldSet<ExtensionDescriptor> fieldSet, MessageType messagetype, CodedInputStream codedInputStream, CodedOutputStream codedOutputStream, ExtensionRegistryLite extensionRegistryLite, int i) throws IOException {
        boolean z;
        boolean z2;
        Object obj;
        int tagWireType = WireFormat.getTagWireType(i);
        GeneratedExtension findLiteExtensionByNumber = extensionRegistryLite.findLiteExtensionByNumber(messagetype, WireFormat.getTagFieldNumber(i));
        if (findLiteExtensionByNumber == null) {
            z2 = true;
            z = false;
        } else if (tagWireType == FieldSet.getWireFormatForFieldType(findLiteExtensionByNumber.descriptor.getLiteType(), false)) {
            z2 = false;
            z = false;
        } else if (!findLiteExtensionByNumber.descriptor.isRepeated || !findLiteExtensionByNumber.descriptor.type.isPackable() || tagWireType != FieldSet.getWireFormatForFieldType(findLiteExtensionByNumber.descriptor.getLiteType(), true)) {
            z2 = true;
            z = false;
        } else {
            z2 = false;
            z = true;
        }
        if (z2) {
            return codedInputStream.skipField(i, codedOutputStream);
        }
        if (z) {
            int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
            if (findLiteExtensionByNumber.descriptor.getLiteType() == FieldType.ENUM) {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    EnumLite findValueByNumber = findLiteExtensionByNumber.descriptor.getEnumType().findValueByNumber(codedInputStream.readEnum());
                    if (findValueByNumber == null) {
                        return true;
                    }
                    fieldSet.addRepeatedField(findLiteExtensionByNumber.descriptor, findLiteExtensionByNumber.singularToFieldSetType(findValueByNumber));
                }
            } else {
                while (codedInputStream.getBytesUntilLimit() > 0) {
                    fieldSet.addRepeatedField(findLiteExtensionByNumber.descriptor, FieldSet.readPrimitiveField(codedInputStream, findLiteExtensionByNumber.descriptor.getLiteType(), false));
                }
            }
            codedInputStream.popLimit(pushLimit);
        } else {
            switch (findLiteExtensionByNumber.descriptor.getLiteJavaType()) {
                case MESSAGE:
                    kotlin.reflect.jvm.internal.impl.protobuf.MessageLite.Builder builder = null;
                    if (!findLiteExtensionByNumber.descriptor.isRepeated()) {
                        MessageLite messageLite = (MessageLite) fieldSet.getField(findLiteExtensionByNumber.descriptor);
                        if (messageLite != null) {
                            builder = messageLite.toBuilder();
                        }
                    }
                    if (builder == null) {
                        builder = findLiteExtensionByNumber.getMessageDefaultInstance().newBuilderForType();
                    }
                    if (findLiteExtensionByNumber.descriptor.getLiteType() == FieldType.GROUP) {
                        codedInputStream.readGroup(findLiteExtensionByNumber.getNumber(), builder, extensionRegistryLite);
                    } else {
                        codedInputStream.readMessage(builder, extensionRegistryLite);
                    }
                    obj = builder.build();
                    break;
                case ENUM:
                    int readEnum = codedInputStream.readEnum();
                    obj = findLiteExtensionByNumber.descriptor.getEnumType().findValueByNumber(readEnum);
                    if (obj == null) {
                        codedOutputStream.writeRawVarint32(i);
                        codedOutputStream.writeUInt32NoTag(readEnum);
                        return true;
                    }
                    break;
                default:
                    obj = FieldSet.readPrimitiveField(codedInputStream, findLiteExtensionByNumber.descriptor.getLiteType(), false);
                    break;
            }
            if (findLiteExtensionByNumber.descriptor.isRepeated()) {
                fieldSet.addRepeatedField(findLiteExtensionByNumber.descriptor, findLiteExtensionByNumber.singularToFieldSetType(obj));
            } else {
                fieldSet.setField(findLiteExtensionByNumber.descriptor, findLiteExtensionByNumber.singularToFieldSetType(obj));
            }
        }
        return true;
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType containingtype, Type type, MessageLite messageLite, EnumLiteMap<?> enumLiteMap, int i, FieldType fieldType, Class cls) {
        ExtensionDescriptor extensionDescriptor = new ExtensionDescriptor(enumLiteMap, i, fieldType, false, false);
        GeneratedExtension generatedExtension = new GeneratedExtension(containingtype, type, messageLite, extensionDescriptor, cls);
        return generatedExtension;
    }

    public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType containingtype, MessageLite messageLite, EnumLiteMap<?> enumLiteMap, int i, FieldType fieldType, boolean z, Class cls) {
        List emptyList = Collections.emptyList();
        ExtensionDescriptor extensionDescriptor = new ExtensionDescriptor(enumLiteMap, i, fieldType, true, z);
        GeneratedExtension generatedExtension = new GeneratedExtension(containingtype, emptyList, messageLite, extensionDescriptor, cls);
        return generatedExtension;
    }

    static Method getMethodOrDie(Class cls, String str, Class... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            String valueOf = String.valueOf(String.valueOf(cls.getName()));
            String valueOf2 = String.valueOf(String.valueOf(str));
            StringBuilder sb = new StringBuilder(valueOf.length() + 45 + valueOf2.length());
            sb.append("Generated message class \"");
            sb.append(valueOf);
            sb.append("\" missing method \"");
            sb.append(valueOf2);
            sb.append("\".");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    static Object invokeOrDie(Method method, Object obj, Object... objArr) {
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
}
