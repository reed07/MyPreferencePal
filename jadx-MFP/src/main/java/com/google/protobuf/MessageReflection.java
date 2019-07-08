package com.google.protobuf;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FieldDescriptor.JavaType;
import com.google.protobuf.Descriptors.FieldDescriptor.Type;
import com.google.protobuf.Descriptors.OneofDescriptor;
import com.google.protobuf.ExtensionRegistry.ExtensionInfo;
import com.google.protobuf.Message.Builder;
import com.google.protobuf.UnknownFieldSet.Field;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

class MessageReflection {

    static class BuilderAdapter implements MergeTarget {
        private final Builder builder;

        public Descriptor getDescriptorForType() {
            return this.builder.getDescriptorForType();
        }

        public BuilderAdapter(Builder builder2) {
            this.builder = builder2;
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            return this.builder.getField(fieldDescriptor);
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            return this.builder.hasField(fieldDescriptor);
        }

        public MergeTarget setField(FieldDescriptor fieldDescriptor, Object obj) {
            this.builder.setField(fieldDescriptor, obj);
            return this;
        }

        public MergeTarget clearField(FieldDescriptor fieldDescriptor) {
            this.builder.clearField(fieldDescriptor);
            return this;
        }

        public MergeTarget setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            this.builder.setRepeatedField(fieldDescriptor, i, obj);
            return this;
        }

        public MergeTarget addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            this.builder.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            return this.builder.hasOneof(oneofDescriptor);
        }

        public MergeTarget clearOneof(OneofDescriptor oneofDescriptor) {
            this.builder.clearOneof(oneofDescriptor);
            return this;
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            return this.builder.getOneofFieldDescriptor(oneofDescriptor);
        }

        public ContainerType getContainerType() {
            return ContainerType.MESSAGE;
        }

        public ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str) {
            return extensionRegistry.findImmutableExtensionByName(str);
        }

        public ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptor descriptor, int i) {
            return extensionRegistry.findImmutableExtensionByNumber(descriptor, i);
        }

        public Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException {
            Builder builder2;
            if (message != null) {
                builder2 = message.newBuilderForType();
            } else {
                builder2 = this.builder.newBuilderForField(fieldDescriptor);
            }
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    builder2.mergeFrom(message2);
                }
            }
            codedInputStream.readGroup(fieldDescriptor.getNumber(), (MessageLite.Builder) builder2, extensionRegistryLite);
            return builder2.buildPartial();
        }

        public Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException {
            Builder builder2;
            if (message != null) {
                builder2 = message.newBuilderForType();
            } else {
                builder2 = this.builder.newBuilderForField(fieldDescriptor);
            }
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    builder2.mergeFrom(message2);
                }
            }
            codedInputStream.readMessage((MessageLite.Builder) builder2, extensionRegistryLite);
            return builder2.buildPartial();
        }

        public Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException {
            Builder builder2;
            if (message != null) {
                builder2 = message.newBuilderForType();
            } else {
                builder2 = this.builder.newBuilderForField(fieldDescriptor);
            }
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    builder2.mergeFrom(message2);
                }
            }
            builder2.mergeFrom(byteString, extensionRegistryLite);
            return builder2.buildPartial();
        }

        public MergeTarget newMergeTargetForField(FieldDescriptor fieldDescriptor, Message message) {
            if (message != null) {
                return new BuilderAdapter(message.newBuilderForType());
            }
            return new BuilderAdapter(this.builder.newBuilderForField(fieldDescriptor));
        }

        public Utf8Validation getUtf8Validation(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.needsUtf8Check()) {
                return Utf8Validation.STRICT;
            }
            if (fieldDescriptor.isRepeated() || !(this.builder instanceof GeneratedMessage.Builder)) {
                return Utf8Validation.LOOSE;
            }
            return Utf8Validation.LAZY;
        }

        public Object finish() {
            return this.builder.buildPartial();
        }
    }

    static class ExtensionAdapter implements MergeTarget {
        private final FieldSet<FieldDescriptor> extensions;

        public MergeTarget clearOneof(OneofDescriptor oneofDescriptor) {
            return this;
        }

        public FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor) {
            return null;
        }

        public boolean hasOneof(OneofDescriptor oneofDescriptor) {
            return false;
        }

        ExtensionAdapter(FieldSet<FieldDescriptor> fieldSet) {
            this.extensions = fieldSet;
        }

        public Descriptor getDescriptorForType() {
            throw new UnsupportedOperationException("getDescriptorForType() called on FieldSet object");
        }

        public Object getField(FieldDescriptor fieldDescriptor) {
            return this.extensions.getField(fieldDescriptor);
        }

        public boolean hasField(FieldDescriptor fieldDescriptor) {
            return this.extensions.hasField(fieldDescriptor);
        }

        public MergeTarget setField(FieldDescriptor fieldDescriptor, Object obj) {
            this.extensions.setField(fieldDescriptor, obj);
            return this;
        }

        public MergeTarget clearField(FieldDescriptor fieldDescriptor) {
            this.extensions.clearField(fieldDescriptor);
            return this;
        }

        public MergeTarget setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj) {
            this.extensions.setRepeatedField(fieldDescriptor, i, obj);
            return this;
        }

        public MergeTarget addRepeatedField(FieldDescriptor fieldDescriptor, Object obj) {
            this.extensions.addRepeatedField(fieldDescriptor, obj);
            return this;
        }

        public ContainerType getContainerType() {
            return ContainerType.EXTENSION_SET;
        }

        public ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str) {
            return extensionRegistry.findImmutableExtensionByName(str);
        }

        public ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptor descriptor, int i) {
            return extensionRegistry.findImmutableExtensionByNumber(descriptor, i);
        }

        public Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException {
            Builder newBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            codedInputStream.readGroup(fieldDescriptor.getNumber(), (MessageLite.Builder) newBuilderForType, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException {
            Builder newBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            codedInputStream.readMessage((MessageLite.Builder) newBuilderForType, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException {
            Builder newBuilderForType = message.newBuilderForType();
            if (!fieldDescriptor.isRepeated()) {
                Message message2 = (Message) getField(fieldDescriptor);
                if (message2 != null) {
                    newBuilderForType.mergeFrom(message2);
                }
            }
            newBuilderForType.mergeFrom(byteString, extensionRegistryLite);
            return newBuilderForType.buildPartial();
        }

        public MergeTarget newMergeTargetForField(FieldDescriptor fieldDescriptor, Message message) {
            throw new UnsupportedOperationException("newMergeTargetForField() called on FieldSet object");
        }

        public Utf8Validation getUtf8Validation(FieldDescriptor fieldDescriptor) {
            if (fieldDescriptor.needsUtf8Check()) {
                return Utf8Validation.STRICT;
            }
            return Utf8Validation.LOOSE;
        }

        public Object finish() {
            throw new UnsupportedOperationException("finish() called on FieldSet object");
        }
    }

    interface MergeTarget {

        public enum ContainerType {
            MESSAGE,
            EXTENSION_SET
        }

        MergeTarget addRepeatedField(FieldDescriptor fieldDescriptor, Object obj);

        MergeTarget clearField(FieldDescriptor fieldDescriptor);

        MergeTarget clearOneof(OneofDescriptor oneofDescriptor);

        ExtensionInfo findExtensionByName(ExtensionRegistry extensionRegistry, String str);

        ExtensionInfo findExtensionByNumber(ExtensionRegistry extensionRegistry, Descriptor descriptor, int i);

        Object finish();

        ContainerType getContainerType();

        Descriptor getDescriptorForType();

        Object getField(FieldDescriptor fieldDescriptor);

        FieldDescriptor getOneofFieldDescriptor(OneofDescriptor oneofDescriptor);

        Utf8Validation getUtf8Validation(FieldDescriptor fieldDescriptor);

        boolean hasField(FieldDescriptor fieldDescriptor);

        boolean hasOneof(OneofDescriptor oneofDescriptor);

        MergeTarget newMergeTargetForField(FieldDescriptor fieldDescriptor, Message message);

        Object parseGroup(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException;

        Object parseMessage(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException;

        Object parseMessageFromBytes(ByteString byteString, ExtensionRegistryLite extensionRegistryLite, FieldDescriptor fieldDescriptor, Message message) throws IOException;

        MergeTarget setField(FieldDescriptor fieldDescriptor, Object obj);

        MergeTarget setRepeatedField(FieldDescriptor fieldDescriptor, int i, Object obj);
    }

    MessageReflection() {
    }

    static void writeMessageTo(Message message, Map<FieldDescriptor, Object> map, CodedOutputStream codedOutputStream, boolean z) throws IOException {
        boolean messageSetWireFormat = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
        if (z) {
            Map<FieldDescriptor, Object> treeMap = new TreeMap<>(map);
            for (FieldDescriptor fieldDescriptor : message.getDescriptorForType().getFields()) {
                if (fieldDescriptor.isRequired() && !treeMap.containsKey(fieldDescriptor)) {
                    treeMap.put(fieldDescriptor, message.getField(fieldDescriptor));
                }
            }
            map = treeMap;
        }
        for (Entry entry : map.entrySet()) {
            FieldDescriptor fieldDescriptor2 = (FieldDescriptor) entry.getKey();
            Object value = entry.getValue();
            if (!messageSetWireFormat || !fieldDescriptor2.isExtension() || fieldDescriptor2.getType() != Type.MESSAGE || fieldDescriptor2.isRepeated()) {
                FieldSet.writeField(fieldDescriptor2, value, codedOutputStream);
            } else {
                codedOutputStream.writeMessageSetExtension(fieldDescriptor2.getNumber(), (Message) value);
            }
        }
        UnknownFieldSet unknownFields = message.getUnknownFields();
        if (messageSetWireFormat) {
            unknownFields.writeAsMessageSetTo(codedOutputStream);
        } else {
            unknownFields.writeTo(codedOutputStream);
        }
    }

    static int getSerializedSize(Message message, Map<FieldDescriptor, Object> map) {
        boolean messageSetWireFormat = message.getDescriptorForType().getOptions().getMessageSetWireFormat();
        int i = 0;
        for (Entry entry : map.entrySet()) {
            FieldDescriptor fieldDescriptor = (FieldDescriptor) entry.getKey();
            Object value = entry.getValue();
            if (!messageSetWireFormat || !fieldDescriptor.isExtension() || fieldDescriptor.getType() != Type.MESSAGE || fieldDescriptor.isRepeated()) {
                i += FieldSet.computeFieldSize(fieldDescriptor, value);
            } else {
                i += CodedOutputStream.computeMessageSetExtensionSize(fieldDescriptor.getNumber(), (Message) value);
            }
        }
        UnknownFieldSet unknownFields = message.getUnknownFields();
        if (messageSetWireFormat) {
            return i + unknownFields.getSerializedSizeAsMessageSet();
        }
        return i + unknownFields.getSerializedSize();
    }

    static String delimitWithCommas(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(str);
        }
        return sb.toString();
    }

    static boolean isInitialized(MessageOrBuilder messageOrBuilder) {
        for (FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !messageOrBuilder.hasField(fieldDescriptor)) {
                return false;
            }
        }
        for (Entry entry : messageOrBuilder.getAllFields().entrySet()) {
            FieldDescriptor fieldDescriptor2 = (FieldDescriptor) entry.getKey();
            if (fieldDescriptor2.getJavaType() == JavaType.MESSAGE) {
                if (fieldDescriptor2.isRepeated()) {
                    for (Message isInitialized : (List) entry.getValue()) {
                        if (!isInitialized.isInitialized()) {
                            return false;
                        }
                    }
                    continue;
                } else if (!((Message) entry.getValue()).isInitialized()) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String subMessagePrefix(String str, FieldDescriptor fieldDescriptor, int i) {
        StringBuilder sb = new StringBuilder(str);
        if (fieldDescriptor.isExtension()) {
            sb.append('(');
            sb.append(fieldDescriptor.getFullName());
            sb.append(')');
        } else {
            sb.append(fieldDescriptor.getName());
        }
        if (i != -1) {
            sb.append('[');
            sb.append(i);
            sb.append(']');
        }
        sb.append('.');
        return sb.toString();
    }

    private static void findMissingFields(MessageOrBuilder messageOrBuilder, String str, List<String> list) {
        for (FieldDescriptor fieldDescriptor : messageOrBuilder.getDescriptorForType().getFields()) {
            if (fieldDescriptor.isRequired() && !messageOrBuilder.hasField(fieldDescriptor)) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(fieldDescriptor.getName());
                list.add(sb.toString());
            }
        }
        for (Entry entry : messageOrBuilder.getAllFields().entrySet()) {
            FieldDescriptor fieldDescriptor2 = (FieldDescriptor) entry.getKey();
            Object value = entry.getValue();
            if (fieldDescriptor2.getJavaType() == JavaType.MESSAGE) {
                if (fieldDescriptor2.isRepeated()) {
                    int i = 0;
                    for (MessageOrBuilder findMissingFields : (List) value) {
                        int i2 = i + 1;
                        findMissingFields(findMissingFields, subMessagePrefix(str, fieldDescriptor2, i), list);
                        i = i2;
                    }
                } else if (messageOrBuilder.hasField(fieldDescriptor2)) {
                    findMissingFields((MessageOrBuilder) value, subMessagePrefix(str, fieldDescriptor2, -1), list);
                }
            }
        }
    }

    static List<String> findMissingFields(MessageOrBuilder messageOrBuilder) {
        ArrayList arrayList = new ArrayList();
        findMissingFields(messageOrBuilder, "", arrayList);
        return arrayList;
    }

    static boolean mergeFieldFrom(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, Descriptor descriptor, MergeTarget mergeTarget, int i) throws IOException {
        Message message;
        boolean z;
        Object obj;
        if (!descriptor.getOptions().getMessageSetWireFormat() || i != WireFormat.MESSAGE_SET_ITEM_TAG) {
            int tagWireType = WireFormat.getTagWireType(i);
            int tagFieldNumber = WireFormat.getTagFieldNumber(i);
            FieldDescriptor fieldDescriptor = null;
            if (descriptor.isExtensionNumber(tagFieldNumber)) {
                if (extensionRegistryLite instanceof ExtensionRegistry) {
                    ExtensionInfo findExtensionByNumber = mergeTarget.findExtensionByNumber((ExtensionRegistry) extensionRegistryLite, descriptor, tagFieldNumber);
                    if (findExtensionByNumber == null) {
                        message = null;
                    } else {
                        FieldDescriptor fieldDescriptor2 = findExtensionByNumber.descriptor;
                        Message message2 = findExtensionByNumber.defaultInstance;
                        if (message2 == null && fieldDescriptor2.getJavaType() == JavaType.MESSAGE) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Message-typed extension lacked default instance: ");
                            sb.append(fieldDescriptor2.getFullName());
                            throw new IllegalStateException(sb.toString());
                        }
                        message = message2;
                        fieldDescriptor = fieldDescriptor2;
                    }
                } else {
                    message = null;
                }
            } else if (mergeTarget.getContainerType() == ContainerType.MESSAGE) {
                fieldDescriptor = descriptor.findFieldByNumber(tagFieldNumber);
                message = null;
            } else {
                message = null;
            }
            boolean z2 = false;
            if (fieldDescriptor == null) {
                z = false;
                z2 = true;
            } else if (tagWireType == FieldSet.getWireFormatForFieldType(fieldDescriptor.getLiteType(), false)) {
                z = false;
            } else if (!fieldDescriptor.isPackable() || tagWireType != FieldSet.getWireFormatForFieldType(fieldDescriptor.getLiteType(), true)) {
                z = false;
                z2 = true;
            } else {
                z = true;
            }
            if (!z2) {
                if (z) {
                    int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                    if (fieldDescriptor.getLiteType() == FieldType.ENUM) {
                        while (codedInputStream.getBytesUntilLimit() > 0) {
                            int readEnum = codedInputStream.readEnum();
                            if (fieldDescriptor.getFile().supportsUnknownEnumValue()) {
                                mergeTarget.addRepeatedField(fieldDescriptor, fieldDescriptor.getEnumType().findValueByNumberCreatingIfUnknown(readEnum));
                            } else {
                                EnumValueDescriptor findValueByNumber = fieldDescriptor.getEnumType().findValueByNumber(readEnum);
                                if (findValueByNumber == null) {
                                    return true;
                                }
                                mergeTarget.addRepeatedField(fieldDescriptor, findValueByNumber);
                            }
                        }
                    } else {
                        while (codedInputStream.getBytesUntilLimit() > 0) {
                            mergeTarget.addRepeatedField(fieldDescriptor, WireFormat.readPrimitiveField(codedInputStream, fieldDescriptor.getLiteType(), mergeTarget.getUtf8Validation(fieldDescriptor)));
                        }
                    }
                    codedInputStream.popLimit(pushLimit);
                } else {
                    switch (fieldDescriptor.getType()) {
                        case GROUP:
                            obj = mergeTarget.parseGroup(codedInputStream, extensionRegistryLite, fieldDescriptor, message);
                            break;
                        case MESSAGE:
                            obj = mergeTarget.parseMessage(codedInputStream, extensionRegistryLite, fieldDescriptor, message);
                            break;
                        case ENUM:
                            int readEnum2 = codedInputStream.readEnum();
                            if (!fieldDescriptor.getFile().supportsUnknownEnumValue()) {
                                Object findValueByNumber2 = fieldDescriptor.getEnumType().findValueByNumber(readEnum2);
                                if (findValueByNumber2 != null) {
                                    obj = findValueByNumber2;
                                    break;
                                } else {
                                    if (builder != null) {
                                        builder.mergeVarintField(tagFieldNumber, readEnum2);
                                    }
                                    return true;
                                }
                            } else {
                                obj = fieldDescriptor.getEnumType().findValueByNumberCreatingIfUnknown(readEnum2);
                                break;
                            }
                        default:
                            obj = WireFormat.readPrimitiveField(codedInputStream, fieldDescriptor.getLiteType(), mergeTarget.getUtf8Validation(fieldDescriptor));
                            break;
                    }
                    if (fieldDescriptor.isRepeated()) {
                        mergeTarget.addRepeatedField(fieldDescriptor, obj);
                    } else {
                        mergeTarget.setField(fieldDescriptor, obj);
                    }
                }
                return true;
            } else if (builder != null) {
                return builder.mergeFieldFrom(i, codedInputStream);
            } else {
                return codedInputStream.skipField(i);
            }
        } else {
            mergeMessageSetExtensionFromCodedStream(codedInputStream, builder, extensionRegistryLite, descriptor, mergeTarget);
            return true;
        }
    }

    private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream codedInputStream, UnknownFieldSet.Builder builder, ExtensionRegistryLite extensionRegistryLite, Descriptor descriptor, MergeTarget mergeTarget) throws IOException {
        int i = 0;
        ByteString byteString = null;
        ExtensionInfo extensionInfo = null;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                i = codedInputStream.readUInt32();
                if (i != 0 && (extensionRegistryLite instanceof ExtensionRegistry)) {
                    extensionInfo = mergeTarget.findExtensionByNumber((ExtensionRegistry) extensionRegistryLite, descriptor, i);
                }
            } else if (readTag == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                if (i == 0 || extensionInfo == null || !ExtensionRegistryLite.isEagerlyParseMessageSets()) {
                    byteString = codedInputStream.readBytes();
                } else {
                    eagerlyMergeMessageSetExtension(codedInputStream, extensionInfo, extensionRegistryLite, mergeTarget);
                    byteString = null;
                }
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
        if (byteString != null && i != 0) {
            if (extensionInfo != null) {
                mergeMessageSetExtensionFromBytes(byteString, extensionInfo, extensionRegistryLite, mergeTarget);
            } else if (byteString != null && builder != null) {
                builder.mergeField(i, Field.newBuilder().addLengthDelimited(byteString).build());
            }
        }
    }

    private static void mergeMessageSetExtensionFromBytes(ByteString byteString, ExtensionInfo extensionInfo, ExtensionRegistryLite extensionRegistryLite, MergeTarget mergeTarget) throws IOException {
        FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
        if (mergeTarget.hasField(fieldDescriptor) || ExtensionRegistryLite.isEagerlyParseMessageSets()) {
            mergeTarget.setField(fieldDescriptor, mergeTarget.parseMessageFromBytes(byteString, extensionRegistryLite, fieldDescriptor, extensionInfo.defaultInstance));
        } else {
            mergeTarget.setField(fieldDescriptor, new LazyField(extensionInfo.defaultInstance, extensionRegistryLite, byteString));
        }
    }

    private static void eagerlyMergeMessageSetExtension(CodedInputStream codedInputStream, ExtensionInfo extensionInfo, ExtensionRegistryLite extensionRegistryLite, MergeTarget mergeTarget) throws IOException {
        FieldDescriptor fieldDescriptor = extensionInfo.descriptor;
        mergeTarget.setField(fieldDescriptor, mergeTarget.parseMessage(codedInputStream, extensionRegistryLite, fieldDescriptor, extensionInfo.defaultInstance));
    }
}
