package com.myfitnesspal.shared.service.syncv1;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LinearRandomNumberGenerator;
import com.myfitnesspal.shared.util.RichAttributeType;
import com.myfitnesspal.shared.util.RichText;
import com.myfitnesspal.shared.util.RichTextAttribute;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryDecoder {
    private long dataBufferPosition = 0;
    private ArrayList<ByteBuffer> dataBuffers = new ArrayList<>();
    private boolean isBorked = false;
    private boolean isDecrypting = false;
    private long totalBytesLeft = 0;

    public boolean isBorked() {
        return this.isBorked;
    }

    public boolean hasMoreData() {
        return this.totalBytesLeft > 0 && !isBorked();
    }

    public long getTotalBytesLeft() {
        return this.totalBytesLeft;
    }

    private boolean ensureMoreData() {
        if (!hasMoreData()) {
            this.isBorked = true;
        }
        return !this.isBorked;
    }

    public void appendDataBuffer(byte[] bArr) {
        if (bArr.length > 0) {
            this.dataBuffers.add(ByteBuffer.wrap(bArr));
            this.totalBytesLeft += (long) bArr.length;
        }
    }

    public void startDecryptingWithKey(long j) {
        LinearRandomNumberGenerator.seed_rng((int) j);
        this.isDecrypting = true;
    }

    public void stopDecrypting() {
        this.isDecrypting = false;
    }

    public byte decodeByte() {
        BufferUnderflowException e;
        byte b;
        if (!ensureMoreData()) {
            return 0;
        }
        try {
            b = getDataToDecode().get((int) this.dataBufferPosition);
            try {
                this.totalBytesLeft--;
                this.dataBufferPosition++;
                return this.isDecrypting ? (byte) ((int) (((long) b) ^ (LinearRandomNumberGenerator.rng_next() & 255))) : b;
            } catch (BufferUnderflowException e2) {
                e = e2;
                Ln.e(e);
                return b;
            }
        } catch (BufferUnderflowException e3) {
            e = e3;
            b = 0;
            Ln.e(e);
            return b;
        }
    }

    public ByteBuffer getDataToDecode() {
        if (!ensureMoreData()) {
            return null;
        }
        while (true) {
            ByteBuffer byteBuffer = (ByteBuffer) this.dataBuffers.get(0);
            if (this.dataBufferPosition < ((long) byteBuffer.limit())) {
                return byteBuffer;
            }
            this.dataBuffers.remove(0);
            this.dataBufferPosition = 0;
        }
    }

    public int decodeUnencrypted2ByteInt() {
        if (!ensureMoreData()) {
            return 0;
        }
        short s = getDataToDecode().getShort((int) this.dataBufferPosition);
        this.totalBytesLeft -= 2;
        this.dataBufferPosition += 2;
        return s;
    }

    public void skipDecoderBytes(int i) {
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                decodeByte();
                i = i2;
            } else {
                return;
            }
        }
    }

    public boolean decodeBoolean() {
        return decode2ByteInt() != 0;
    }

    public int decode2ByteInt() {
        if (!ensureMoreData()) {
            return 0;
        }
        if (!this.isDecrypting) {
            return decodeUnencrypted2ByteInt();
        }
        return allocateBufferAndFlip(2).getShort();
    }

    public int decodeUnencrypted4ByteInt() {
        if (!ensureMoreData()) {
            return 0;
        }
        int i = getDataToDecode().getInt((int) this.dataBufferPosition);
        this.totalBytesLeft -= 4;
        this.dataBufferPosition += 4;
        return i;
    }

    public Long decodeUnencrypted8ByteInt() {
        Long valueOf = Long.valueOf(0);
        if (!ensureMoreData()) {
            return valueOf;
        }
        Long valueOf2 = Long.valueOf(getDataToDecode().getLong((int) this.dataBufferPosition));
        this.totalBytesLeft -= 8;
        this.dataBufferPosition += 8;
        return valueOf2;
    }

    public float decodeUnencryptedFloat() {
        boolean ensureMoreData = ensureMoreData();
        float f = BitmapDescriptorFactory.HUE_RED;
        if (!ensureMoreData) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        try {
            f = getDataToDecode().getFloat((int) this.dataBufferPosition);
            this.totalBytesLeft -= 4;
            this.dataBufferPosition += 4;
            return f;
        } catch (BufferUnderflowException e) {
            Ln.e(e);
            return f;
        }
    }

    public double decodeUnencryptedDouble() {
        double d = 0.0d;
        if (!ensureMoreData()) {
            return 0.0d;
        }
        try {
            d = getDataToDecode().getDouble((int) this.dataBufferPosition);
            this.totalBytesLeft -= 8;
            this.dataBufferPosition += 8;
            return d;
        } catch (BufferUnderflowException e) {
            Ln.e(e);
            return d;
        }
    }

    public long decode4ByteInt() {
        if (ensureMoreData()) {
            if (!this.isDecrypting) {
                return (long) decodeUnencrypted4ByteInt();
            }
            try {
                return allocateBufferAndFlip(4).getLong();
            } catch (BufferUnderflowException e) {
                Ln.e(e);
            }
        }
        return 0;
    }

    public long decode8ByteInt() {
        if (ensureMoreData()) {
            if (!this.isDecrypting) {
                return decodeUnencrypted8ByteInt().longValue();
            }
            try {
                return allocateBufferAndFlip(8).getLong();
            } catch (BufferUnderflowException e) {
                Ln.e(e);
            }
        }
        return 0;
    }

    public float decodeFloat() {
        if (ensureMoreData()) {
            if (!this.isDecrypting) {
                return decodeUnencryptedFloat();
            }
            try {
                return allocateBufferAndFlip(4).getFloat();
            } catch (BufferUnderflowException e) {
                Ln.e(e);
            }
        }
        return BitmapDescriptorFactory.HUE_RED;
    }

    public double decodeDouble() {
        if (ensureMoreData()) {
            if (!this.isDecrypting) {
                return decodeUnencryptedDouble();
            }
            try {
                return allocateBufferAndFlip(8).getDouble();
            } catch (BufferUnderflowException e) {
                Ln.e(e);
            }
        }
        return 0.0d;
    }

    public String decodeString() {
        return ensureMoreData() ? decodeFixedLengthString((short) decode2ByteInt()) : "";
    }

    public String decodeFixedLengthString(int i) {
        if (ensureMoreData() && i > 0) {
            try {
                return new String(decodeBytes(i), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                Ln.e(e);
            }
        }
        return "";
    }

    public byte[] decodeBytes(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = decodeByte();
        }
        return bArr;
    }

    public Date decodeDate() {
        return decodeDateTime(Format.newIso8601DateFormat().toPattern(), "0000-00-00");
    }

    public Date decodeTimestamp() {
        return decodeDateTime(Format.newDatabaseDateTimeFormat().toPattern(), "0000-00-00 00:00:00");
    }

    public Date decodeDateTime(String str, String str2) {
        String decodeFixedLengthString = decodeFixedLengthString(str.length());
        if (!Strings.notEmpty(str2) || !Strings.equals(decodeFixedLengthString, str2)) {
            return DateTimeUtils.parse(str, decodeFixedLengthString);
        }
        return null;
    }

    public List<String> decodeStringList() {
        ArrayList arrayList = new ArrayList();
        if (ensureMoreData()) {
            int decode4ByteInt = (int) decode4ByteInt();
            for (int i = 0; i < decode4ByteInt; i++) {
                arrayList.add(decodeString());
            }
        }
        return arrayList;
    }

    public Map<String, String> decodeStringToStringMap(int i) {
        HashMap hashMap = new HashMap();
        if (ensureMoreData()) {
            for (int i2 = 0; i2 < i; i2++) {
                hashMap.put(decodeString(), decodeString());
            }
        }
        return hashMap;
    }

    public Map<String, String> decodeStringToStringMap() {
        return decodeStringToStringMap(ensureMoreData() ? decode2ByteInt() : 0);
    }

    public Map<String, Long> decodeStringToLongMap(int i) {
        HashMap hashMap = new HashMap();
        if (ensureMoreData()) {
            for (int i2 = 0; i2 < i; i2++) {
                hashMap.put(decodeString(), Long.valueOf(decode4ByteInt()));
            }
        }
        return hashMap;
    }

    public Map<String, Long> decodeStringToLongMap() {
        return decodeStringToLongMap(ensureMoreData() ? decode2ByteInt() : 0);
    }

    public <T extends BinaryApiSerializable> T decodeObject(BinaryCreator<T> binaryCreator) {
        if (ensureMoreData()) {
            return (BinaryApiSerializable) binaryCreator.create(this);
        }
        return null;
    }

    public <T extends BinaryApiSerializable> List<T> decodeList(int i, BinaryCreator<T> binaryCreator) {
        ArrayList arrayList = new ArrayList();
        if (ensureMoreData()) {
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add(decodeObject(binaryCreator));
            }
        }
        return arrayList;
    }

    public <T extends BinaryApiSerializable> List<T> decodeList(BinaryCreator<T> binaryCreator) {
        return decodeList(ensureMoreData() ? (int) decode4ByteInt() : 0, binaryCreator);
    }

    public <T extends BinaryApiSerializable> List<T> decodeListWithTwoByteSize(BinaryCreator<T> binaryCreator) {
        return decodeList(ensureMoreData() ? decode2ByteInt() : 0, binaryCreator);
    }

    public RichText decodedRichText() {
        try {
            RichText richText = new RichText();
            richText.baseString = decodeString();
            byte[] bytes = richText.baseString.getBytes("UTF-8");
            int decode4ByteInt = (int) decode4ByteInt();
            ArrayList<RichTextAttribute> arrayList = new ArrayList<>(decode4ByteInt);
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = decode4ByteInt - 1;
                if (decode4ByteInt > 0) {
                    RichTextAttribute richTextAttribute = new RichTextAttribute();
                    int decode4ByteInt2 = (int) decode4ByteInt();
                    int decode4ByteInt3 = (int) decode4ByteInt();
                    if (decode4ByteInt2 < i) {
                        i2 = countCharactersAtByteOffset(0, decode4ByteInt2, bytes);
                    } else if (decode4ByteInt2 == i) {
                        decode4ByteInt2 = i;
                    } else {
                        i2 += countCharactersAtByteOffset(i, decode4ByteInt2 - i, bytes);
                    }
                    int countCharactersAtByteOffset = countCharactersAtByteOffset(decode4ByteInt2, decode4ByteInt3, bytes);
                    richTextAttribute.startOffset = i2;
                    richTextAttribute.length = countCharactersAtByteOffset;
                    int i4 = decode4ByteInt3 + decode4ByteInt2;
                    i2 += countCharactersAtByteOffset;
                    richTextAttribute.attributeType = RichAttributeType.getType(decode2ByteInt());
                    richTextAttribute.attributeValue = decodeString();
                    arrayList.add(richTextAttribute);
                    decode4ByteInt = i3;
                    i = i4;
                } else {
                    richText.attributes = arrayList;
                    return richText;
                }
            }
        } catch (UnsupportedEncodingException e) {
            Ln.e("BinaryDecoder::decodeNextRichText", "Unsupported encoding");
            Ln.e(e);
            return null;
        } catch (Exception e2) {
            Ln.e(e2);
            return null;
        }
    }

    private ByteBuffer allocateBufferAndFlip(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        for (int i2 = 0; i2 < i; i2++) {
            allocate.put(decodeByte());
        }
        return (ByteBuffer) allocate.flip();
    }

    private int countCharactersAtByteOffset(int i, int i2, byte[] bArr) {
        try {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            return new String(bArr2, "UTF-8").length();
        } catch (Exception e) {
            Ln.e(e);
            return 0;
        }
    }
}
