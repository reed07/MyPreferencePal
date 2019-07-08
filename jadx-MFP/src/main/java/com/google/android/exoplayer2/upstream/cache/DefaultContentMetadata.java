package com.google.android.exoplayer2.upstream.cache;

import android.support.annotation.Nullable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class DefaultContentMetadata implements ContentMetadata {
    public static final DefaultContentMetadata EMPTY = new DefaultContentMetadata(Collections.emptyMap());
    private static final int MAX_VALUE_LENGTH = 10485760;
    private int hashCode;
    private final Map<String, byte[]> metadata;

    public static DefaultContentMetadata readFromStream(DataInputStream dataInputStream) throws IOException {
        int readInt = dataInputStream.readInt();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < readInt; i++) {
            String readUTF = dataInputStream.readUTF();
            int readInt2 = dataInputStream.readInt();
            if (readInt2 < 0 || readInt2 > 10485760) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid value size: ");
                sb.append(readInt2);
                throw new IOException(sb.toString());
            }
            byte[] bArr = new byte[readInt2];
            dataInputStream.readFully(bArr);
            hashMap.put(readUTF, bArr);
        }
        return new DefaultContentMetadata(hashMap);
    }

    private DefaultContentMetadata(Map<String, byte[]> map) {
        this.metadata = Collections.unmodifiableMap(map);
    }

    public DefaultContentMetadata copyWithMutationsApplied(ContentMetadataMutations contentMetadataMutations) {
        Map applyMutations = applyMutations(this.metadata, contentMetadataMutations);
        if (isMetadataEqual(applyMutations)) {
            return this;
        }
        return new DefaultContentMetadata(applyMutations);
    }

    public void writeToStream(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.metadata.size());
        for (Entry entry : this.metadata.entrySet()) {
            dataOutputStream.writeUTF((String) entry.getKey());
            byte[] bArr = (byte[]) entry.getValue();
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
        }
    }

    public final byte[] get(String str, byte[] bArr) {
        if (!this.metadata.containsKey(str)) {
            return bArr;
        }
        byte[] bArr2 = (byte[]) this.metadata.get(str);
        return Arrays.copyOf(bArr2, bArr2.length);
    }

    public final String get(String str, String str2) {
        return this.metadata.containsKey(str) ? new String((byte[]) this.metadata.get(str), Charset.forName("UTF-8")) : str2;
    }

    public final long get(String str, long j) {
        return this.metadata.containsKey(str) ? ByteBuffer.wrap((byte[]) this.metadata.get(str)).getLong() : j;
    }

    public final boolean contains(String str) {
        return this.metadata.containsKey(str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return isMetadataEqual(((DefaultContentMetadata) obj).metadata);
    }

    private boolean isMetadataEqual(Map<String, byte[]> map) {
        if (this.metadata.size() != map.size()) {
            return false;
        }
        for (Entry entry : this.metadata.entrySet()) {
            if (!Arrays.equals((byte[]) entry.getValue(), (byte[]) map.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            int i = 0;
            for (Entry entry : this.metadata.entrySet()) {
                i += Arrays.hashCode((byte[]) entry.getValue()) ^ ((String) entry.getKey()).hashCode();
            }
            this.hashCode = i;
        }
        return this.hashCode;
    }

    private static Map<String, byte[]> applyMutations(Map<String, byte[]> map, ContentMetadataMutations contentMetadataMutations) {
        HashMap hashMap = new HashMap(map);
        removeValues(hashMap, contentMetadataMutations.getRemovedValues());
        addValues(hashMap, contentMetadataMutations.getEditedValues());
        return hashMap;
    }

    private static void removeValues(HashMap<String, byte[]> hashMap, List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            hashMap.remove(list.get(i));
        }
    }

    private static void addValues(HashMap<String, byte[]> hashMap, Map<String, Object> map) {
        for (String str : map.keySet()) {
            byte[] bytes = getBytes(map.get(str));
            if (bytes.length <= 10485760) {
                hashMap.put(str, bytes);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("The size of ");
                sb.append(str);
                sb.append(" (");
                sb.append(bytes.length);
                sb.append(") is greater than maximum allowed: ");
                sb.append(10485760);
                throw new IllegalArgumentException(sb.toString());
            }
        }
    }

    private static byte[] getBytes(Object obj) {
        if (obj instanceof Long) {
            return ByteBuffer.allocate(8).putLong(((Long) obj).longValue()).array();
        }
        if (obj instanceof String) {
            return ((String) obj).getBytes(Charset.forName("UTF-8"));
        }
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        throw new IllegalArgumentException();
    }
}
