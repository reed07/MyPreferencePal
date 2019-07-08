package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.FileDescriptor.Syntax;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.Internal.EnumLite;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractMessage extends AbstractMessageLite implements Message {
    protected int memoizedSize = -1;

    public static abstract class Builder<BuilderType extends Builder<BuilderType>> extends com.google.protobuf.AbstractMessageLite.Builder implements com.google.protobuf.Message.Builder {
        public BuilderType clone() {
            throw new UnsupportedOperationException("clone() should be implemented in subclasses.");
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("hasOneof() is not implemented.");
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
        }

        public BuilderType clearOneof(OneofDescriptor oneofDescriptor) {
            throw new UnsupportedOperationException("clearOneof() is not implemented.");
        }

        public BuilderType clear() {
            for (Entry key : getAllFields().entrySet()) {
                clearField((FieldDescriptor) key.getKey());
            }
            return this;
        }

        public List<String> findInitializationErrors() {
            return MessageReflection.findMissingFields(this);
        }

        public String getInitializationErrorString() {
            return MessageReflection.delimitWithCommas(findInitializationErrors());
        }

        /* access modifiers changed from: protected */
        public BuilderType internalMergeFrom(AbstractMessageLite abstractMessageLite) {
            return mergeFrom((Message) abstractMessageLite);
        }

        public BuilderType mergeFrom(Message message) {
            return mergeFrom(message, message.getAllFields());
        }

        /* access modifiers changed from: 0000 */
        public BuilderType mergeFrom(Message message, Map<FieldDescriptor, Object> map) {
            if (message.getDescriptorForType() == getDescriptorForType()) {
                for (Entry entry : map.entrySet()) {
                    FieldDescriptor fieldDescriptor = (FieldDescriptor) entry.getKey();
                    if (fieldDescriptor.isRepeated()) {
                        for (Object addRepeatedField : (List) entry.getValue()) {
                            addRepeatedField(fieldDescriptor, addRepeatedField);
                        }
                    } else if (fieldDescriptor.getJavaType() == JavaType.MESSAGE) {
                        Message message2 = (Message) getField(fieldDescriptor);
                        if (message2 == message2.getDefaultInstanceForType()) {
                            setField(fieldDescriptor, entry.getValue());
                        } else {
                            setField(fieldDescriptor, message2.newBuilderForType().mergeFrom(message2).mergeFrom((Message) entry.getValue()).build());
                        }
                    } else {
                        setField(fieldDescriptor, entry.getValue());
                    }
                }
                mergeUnknownFields(message.getUnknownFields());
                return this;
            }
            throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
        }

        public BuilderType mergeFrom(CodedInputStream codedInputStream) throws IOException {
            return mergeFrom(codedInputStream, (ExtensionRegistryLite) ExtensionRegistry.getEmptyRegistry());
        }

        public BuilderType mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            boolean z;
            com.google.protobuf.UnknownFieldSet.Builder builder;
            int readTag;
            if (getDescriptorForType().getFile().getSyntax() == Syntax.PROTO3) {
                z = codedInputStream.shouldDiscardUnknownFieldsProto3();
            } else {
                z = codedInputStream.shouldDiscardUnknownFields();
            }
            if (z) {
                builder = null;
            } else {
                builder = UnknownFieldSet.newBuilder(getUnknownFields());
            }
            do {
                readTag = codedInputStream.readTag();
                if (readTag == 0) {
                    break;
                }
            } while (MessageReflection.mergeFieldFrom(codedInputStream, builder, extensionRegistryLite, getDescriptorForType(), new BuilderAdapter(this), readTag));
            if (builder != null) {
                setUnknownFields(builder.build());
            }
            return this;
        }

        public BuilderType mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(unknownFieldSet).build());
            return this;
        }

        public com.google.protobuf.Message.Builder getFieldBuilder(FieldDescriptor fieldDescriptor) {
            throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
        }

        public com.google.protobuf.Message.Builder getRepeatedFieldBuilder(FieldDescriptor fieldDescriptor, int i) {
            throw new UnsupportedOperationException("getRepeatedFieldBuilder() called on an unsupported message type.");
        }

        public String toString() {
            return TextFormat.printToString((MessageOrBuilder) this);
        }

        protected static UninitializedMessageException newUninitializedMessageException(Message message) {
            return new UninitializedMessageException(MessageReflection.findMissingFields(message));
        }

        /* access modifiers changed from: 0000 */
        public void markClean() {
            throw new IllegalStateException("Should be overridden by subclasses.");
        }

        /* access modifiers changed from: 0000 */
        public void dispose() {
            throw new IllegalStateException("Should be overridden by subclasses.");
        }

        public BuilderType mergeFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Builder) super.mergeFrom(byteString);
        }

        public BuilderType mergeFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Builder) super.mergeFrom(byteString, extensionRegistryLite);
        }

        public BuilderType mergeFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Builder) super.mergeFrom(bArr);
        }

        public BuilderType mergeFrom(byte[] bArr, int i, int i2) throws InvalidProtocolBufferException {
            return (Builder) super.mergeFrom(bArr, i, i2);
        }

        public BuilderType mergeFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Builder) super.mergeFrom(bArr, extensionRegistryLite);
        }

        public BuilderType mergeFrom(byte[] bArr, int i, int i2, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Builder) super.mergeFrom(bArr, i, i2, extensionRegistryLite);
        }

        public BuilderType mergeFrom(InputStream inputStream) throws IOException {
            return (Builder) super.mergeFrom(inputStream);
        }

        public BuilderType mergeFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Builder) super.mergeFrom(inputStream, extensionRegistryLite);
        }

        public boolean mergeDelimitedFrom(InputStream inputStream) throws IOException {
            return super.mergeDelimitedFrom(inputStream);
        }

        public boolean mergeDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return super.mergeDelimitedFrom(inputStream, extensionRegistryLite);
        }
    }

    protected interface BuilderParent {
        void markDirty();
    }

    @Deprecated
    protected static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    @Deprecated
    protected static int hashLong(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public boolean isInitialized() {
        return MessageReflection.isInitialized(this);
    }

    /* access modifiers changed from: protected */
    public com.google.protobuf.Message.Builder newBuilderForType(BuilderParent builderParent) {
        throw new UnsupportedOperationException("Nested builder is not supported for this type.");
    }

    public List<String> findInitializationErrors() {
        return MessageReflection.findMissingFields(this);
    }

    public String getInitializationErrorString() {
        return MessageReflection.delimitWithCommas(findInitializationErrors());
    }

    public boolean hasOneof(OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("hasOneof() is not implemented.");
    }

    public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
        throw new UnsupportedOperationException("getOneofFieldDescriptor() is not implemented.");
    }

    public final String toString() {
        return TextFormat.printToString((MessageOrBuilder) this);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        MessageReflection.writeMessageTo(this, getAllFields(), codedOutputStream, false);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSize = MessageReflection.getSerializedSize(this, getAllFields());
        return this.memoizedSize;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        if (getDescriptorForType() != message.getDescriptorForType()) {
            return false;
        }
        if (!compareFields(getAllFields(), message.getAllFields()) || !getUnknownFields().equals(message.getUnknownFields())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = this.memoizedHashCode;
        if (i != 0) {
            return i;
        }
        int hashFields = (hashFields(779 + getDescriptorForType().hashCode(), getAllFields()) * 29) + getUnknownFields().hashCode();
        this.memoizedHashCode = hashFields;
        return hashFields;
    }

    private static ByteString toByteString(Object obj) {
        if (obj instanceof byte[]) {
            return ByteString.copyFrom((byte[]) obj);
        }
        return (ByteString) obj;
    }

    private static boolean compareBytes(Object obj, Object obj2) {
        if (!(obj instanceof byte[]) || !(obj2 instanceof byte[])) {
            return toByteString(obj).equals(toByteString(obj2));
        }
        return Arrays.equals((byte[]) obj, (byte[]) obj2);
    }

    private static Map convertMapEntryListToMap(List list) {
        if (list.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        Iterator it = list.iterator();
        Message message = (Message) it.next();
        Descriptor descriptorForType = message.getDescriptorForType();
        FieldDescriptor findFieldByName = descriptorForType.findFieldByName(IpcUtil.KEY_CODE);
        FieldDescriptor findFieldByName2 = descriptorForType.findFieldByName("value");
        Object field = message.getField(findFieldByName2);
        if (field instanceof EnumValueDescriptor) {
            field = Integer.valueOf(((EnumValueDescriptor) field).getNumber());
        }
        hashMap.put(message.getField(findFieldByName), field);
        while (it.hasNext()) {
            Message message2 = (Message) it.next();
            Object field2 = message2.getField(findFieldByName2);
            if (field2 instanceof EnumValueDescriptor) {
                field2 = Integer.valueOf(((EnumValueDescriptor) field2).getNumber());
            }
            hashMap.put(message2.getField(findFieldByName), field2);
        }
        return hashMap;
    }

    private static boolean compareMapField(Object obj, Object obj2) {
        return MapFieldLite.equals(convertMapEntryListToMap((List) obj), convertMapEntryListToMap((List) obj2));
    }

    static boolean compareFields(Map<FieldDescriptor, Object> map, Map<FieldDescriptor, Object> map2) {
        if (map.size() != map2.size()) {
            return false;
        }
        for (FieldDescriptor fieldDescriptor : map.keySet()) {
            if (!map2.containsKey(fieldDescriptor)) {
                return false;
            }
            Object obj = map.get(fieldDescriptor);
            Object obj2 = map2.get(fieldDescriptor);
            if (fieldDescriptor.getType() == Type.BYTES) {
                if (fieldDescriptor.isRepeated()) {
                    List list = (List) obj;
                    List list2 = (List) obj2;
                    if (list.size() != list2.size()) {
                        return false;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        if (!compareBytes(list.get(i), list2.get(i))) {
                            return false;
                        }
                    }
                    continue;
                } else if (!compareBytes(obj, obj2)) {
                    return false;
                }
            } else if (fieldDescriptor.isMapField()) {
                if (!compareMapField(obj, obj2)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    private static int hashMapField(Object obj) {
        return MapFieldLite.calculateHashCodeForMap(convertMapEntryListToMap((List) obj));
    }

    protected static int hashFields(int i, Map<FieldDescriptor, Object> map) {
        for (Entry entry : map.entrySet()) {
            FieldDescriptor fieldDescriptor = (FieldDescriptor) entry.getKey();
            Object value = entry.getValue();
            int number = (i * 37) + fieldDescriptor.getNumber();
            if (fieldDescriptor.isMapField()) {
                i = (number * 53) + hashMapField(value);
            } else if (fieldDescriptor.getType() != Type.ENUM) {
                i = (number * 53) + value.hashCode();
            } else if (fieldDescriptor.isRepeated()) {
                i = (number * 53) + Internal.hashEnumList((List) value);
            } else {
                i = (number * 53) + Internal.hashEnum((EnumLite) value);
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public UninitializedMessageException newUninitializedMessageException() {
        return Builder.newUninitializedMessageException(this);
    }

    @Deprecated
    protected static int hashEnum(EnumLite enumLite) {
        return enumLite.getNumber();
    }

    @Deprecated
    protected static int hashEnumList(List<? extends EnumLite> list) {
        int i = 1;
        for (EnumLite hashEnum : list) {
            i = (i * 31) + hashEnum(hashEnum);
        }
        return i;
    }
}
