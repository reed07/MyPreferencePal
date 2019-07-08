package com.google.protobuf;

import com.google.protobuf.MessageLite.Builder;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map.Entry;

public class MapEntryLite<K, V> {
    private static final int KEY_FIELD_NUMBER = 1;
    private static final int VALUE_FIELD_NUMBER = 2;
    private final K key;
    private final Metadata<K, V> metadata;
    private final V value;

    static class Metadata<K, V> {
        public final K defaultKey;
        public final V defaultValue;
        public final FieldType keyType;
        public final FieldType valueType;

        public Metadata(FieldType fieldType, K k, FieldType fieldType2, V v) {
            this.keyType = fieldType;
            this.defaultKey = k;
            this.valueType = fieldType2;
            this.defaultValue = v;
        }
    }

    private MapEntryLite(FieldType fieldType, K k, FieldType fieldType2, V v) {
        this.metadata = new Metadata<>(fieldType, k, fieldType2, v);
        this.key = k;
        this.value = v;
    }

    private MapEntryLite(Metadata<K, V> metadata2, K k, V v) {
        this.metadata = metadata2;
        this.key = k;
        this.value = v;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public static <K, V> MapEntryLite<K, V> newDefaultInstance(FieldType fieldType, K k, FieldType fieldType2, V v) {
        return new MapEntryLite<>(fieldType, k, fieldType2, v);
    }

    static <K, V> void writeTo(CodedOutputStream codedOutputStream, Metadata<K, V> metadata2, K k, V v) throws IOException {
        FieldSet.writeElement(codedOutputStream, metadata2.keyType, 1, k);
        FieldSet.writeElement(codedOutputStream, metadata2.valueType, 2, v);
    }

    static <K, V> int computeSerializedSize(Metadata<K, V> metadata2, K k, V v) {
        return FieldSet.computeElementSize(metadata2.keyType, 1, k) + FieldSet.computeElementSize(metadata2.valueType, 2, v);
    }

    static <T> T parseField(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite, FieldType fieldType, T t) throws IOException {
        switch (fieldType) {
            case MESSAGE:
                Builder builder = ((MessageLite) t).toBuilder();
                codedInputStream.readMessage(builder, extensionRegistryLite);
                return builder.buildPartial();
            case ENUM:
                return Integer.valueOf(codedInputStream.readEnum());
            case GROUP:
                throw new RuntimeException("Groups are not allowed in maps.");
            default:
                return FieldSet.readPrimitiveField(codedInputStream, fieldType, true);
        }
    }

    public void serializeTo(CodedOutputStream codedOutputStream, int i, K k, V v) throws IOException {
        codedOutputStream.writeTag(i, 2);
        codedOutputStream.writeUInt32NoTag(computeSerializedSize(this.metadata, k, v));
        writeTo(codedOutputStream, this.metadata, k, v);
    }

    public int computeMessageSize(int i, K k, V v) {
        return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(computeSerializedSize(this.metadata, k, v));
    }

    public Entry<K, V> parseEntry(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return parseEntry(byteString.newCodedInput(), this.metadata, extensionRegistryLite);
    }

    static <K, V> Entry<K, V> parseEntry(CodedInputStream codedInputStream, Metadata<K, V> metadata2, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        K k = metadata2.defaultKey;
        V v = metadata2.defaultValue;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.makeTag(1, metadata2.keyType.getWireType())) {
                k = parseField(codedInputStream, extensionRegistryLite, metadata2.keyType, k);
            } else if (readTag == WireFormat.makeTag(2, metadata2.valueType.getWireType())) {
                v = parseField(codedInputStream, extensionRegistryLite, metadata2.valueType, v);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        return new SimpleImmutableEntry(k, v);
    }

    public void parseInto(MapFieldLite<K, V> mapFieldLite, CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
        K k = this.metadata.defaultKey;
        V v = this.metadata.defaultValue;
        while (true) {
            int readTag = codedInputStream.readTag();
            if (readTag == 0) {
                break;
            } else if (readTag == WireFormat.makeTag(1, this.metadata.keyType.getWireType())) {
                k = parseField(codedInputStream, extensionRegistryLite, this.metadata.keyType, k);
            } else if (readTag == WireFormat.makeTag(2, this.metadata.valueType.getWireType())) {
                v = parseField(codedInputStream, extensionRegistryLite, this.metadata.valueType, v);
            } else if (!codedInputStream.skipField(readTag)) {
                break;
            }
        }
        codedInputStream.checkLastTagWas(0);
        codedInputStream.popLimit(pushLimit);
        mapFieldLite.put(k, v);
    }

    /* access modifiers changed from: 0000 */
    public Metadata<K, V> getMetadata() {
        return this.metadata;
    }
}
