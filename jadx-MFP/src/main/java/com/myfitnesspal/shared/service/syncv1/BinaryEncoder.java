package com.myfitnesspal.shared.service.syncv1;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LinearRandomNumberGenerator;
import com.myfitnesspal.shared.util.RichText;
import com.myfitnesspal.shared.util.RichTextAttribute;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class BinaryEncoder {
    private static boolean encodingInProgress = false;
    private static boolean isEncrypting;
    private ByteBuffer buffer;
    private int capacity;

    public ByteBuffer getBuffer() {
        return this.buffer;
    }

    @Inject
    public BinaryEncoder() {
        this.buffer = null;
        this.capacity = 4096;
        this.buffer = ByteBuffer.allocate(this.capacity);
        isEncrypting = false;
    }

    public void clear() {
        this.buffer.clear();
    }

    public void writeBoolean(boolean z) {
        write2ByteInt(z ? 1 : 0);
    }

    public void write2ByteInt(int i) {
        ensureBufferHasSpaceOfSize(2);
        if (isEncrypting) {
            this.buffer.put((byte) ((int) (((long) (i >> 8)) ^ (LinearRandomNumberGenerator.rng_next() & 255))));
            this.buffer.put((byte) ((int) (((long) i) ^ (LinearRandomNumberGenerator.rng_next() & 255))));
            return;
        }
        this.buffer.put((byte) ((i >> 8) & 255));
        this.buffer.put((byte) (i & 255));
    }

    public void write4ByteInt(long j) {
        ensureBufferHasSpaceOfSize(4);
        if (isEncrypting) {
            this.buffer.put((byte) ((int) ((j >> 24) ^ (LinearRandomNumberGenerator.rng_next() & 255))));
            this.buffer.put((byte) ((int) ((j >> 16) ^ (LinearRandomNumberGenerator.rng_next() & 255))));
            this.buffer.put((byte) ((int) ((j >> 8) ^ (LinearRandomNumberGenerator.rng_next() & 255))));
            this.buffer.put((byte) ((int) (j ^ (LinearRandomNumberGenerator.rng_next() & 255))));
            return;
        }
        this.buffer.put((byte) ((int) ((j >> 24) & 255)));
        this.buffer.put((byte) ((int) ((j >> 16) & 255)));
        this.buffer.put((byte) ((int) ((j >> 8) & 255)));
        this.buffer.put((byte) ((int) (j & 255)));
    }

    public void write8ByteInt(long j) {
        write4ByteInt(j >> 32);
        write4ByteInt(j);
    }

    public void writeUUID(byte[] bArr) {
        Ln.d("writing UUID %s", Arrays.toString(bArr));
        writeRawBytes(bArr, bArr.length);
    }

    public void writeFloat(float f) {
        write4ByteInt((long) Float.floatToRawIntBits(f));
    }

    public void writeDouble(double d) {
        write8ByteInt(Double.doubleToLongBits(d));
    }

    public void writeString(String str) {
        try {
            byte[] bytes = Strings.toString(str).getBytes("UTF-8");
            int length = bytes.length;
            write2ByteInt(length);
            writeRawBytes(bytes, length);
        } catch (UnsupportedEncodingException e) {
            Ln.e(e);
        }
    }

    public void writeRawBytes(byte[] bArr, int i) {
        ensureBufferHasSpaceOfSize(i);
        for (int i2 = 0; i2 < i; i2++) {
            if (isEncrypting) {
                this.buffer.put((byte) ((int) (((long) bArr[i2]) ^ (LinearRandomNumberGenerator.rng_next() & 255))));
            } else {
                this.buffer.put((byte) (bArr[i2] & 255));
            }
        }
    }

    public int currentPosition() {
        return this.buffer.position();
    }

    public void patchAtPosition(int i, int i2) {
        this.buffer.put(i, (byte) ((i2 >> 24) & 255));
        this.buffer.put(i + 1, (byte) ((i2 >> 16) & 255));
        this.buffer.put(i + 2, (byte) ((i2 >> 8) & 255));
        this.buffer.put(i + 3, (byte) (i2 & 255));
    }

    public void writeDate(Date date) {
        try {
            byte[] bytes = (date != null ? Format.newIso8601DateFormat().format(date) : "0000-00-00").getBytes("UTF-8");
            writeRawBytes(bytes, bytes.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void writeRichtext(RichText richText) {
        writeString(richText.baseString);
        write4ByteInt((long) richText.attributes.size());
        Iterator it = richText.attributes.iterator();
        while (it.hasNext()) {
            RichTextAttribute richTextAttribute = (RichTextAttribute) it.next();
            write4ByteInt((long) richTextAttribute.startOffset);
            write4ByteInt((long) richTextAttribute.length);
            write2ByteInt(richTextAttribute.attributeType.getValue());
            writeString(richTextAttribute.attributeValue);
        }
    }

    public void writeTimestamp(Date date) {
        try {
            byte[] bytes = (date != null ? DateTimeUtils.format(Format.newDatabaseDateTimeFormat().toPattern(), date) : "0000-00-00 00:00:00").getBytes("UTF-8");
            writeRawBytes(bytes, bytes.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void writeStringList(List<String> list) {
        int size = CollectionUtils.size((Collection<?>) list);
        write4ByteInt((long) size);
        if (size > 0) {
            for (String writeString : list) {
                writeString(writeString);
            }
        }
    }

    public void writeStringToStringMap(int i, Map<String, String> map) {
        if (i > 0) {
            for (String str : map.keySet()) {
                writeString(str);
                writeString((String) map.get(str));
            }
        }
    }

    public void writeStringToStringMap(Map<String, String> map) {
        int size = CollectionUtils.size(map);
        write2ByteInt(size);
        writeStringToStringMap(size, map);
    }

    public void writeLongToStringMap(int i, Map<Long, String> map) {
        if (i > 0) {
            for (Long l : map.keySet()) {
                write4ByteInt(l.longValue());
                writeString((String) map.get(l));
            }
        }
    }

    public void writeStringToLongMap(int i, Map<String, Long> map) {
        if (i > 0) {
            for (String str : map.keySet()) {
                writeString(str);
                write4ByteInt(((Long) map.get(str)).longValue());
            }
        }
    }

    public void writeStringToLongMap(Map<String, Long> map) {
        int size = CollectionUtils.size(map);
        write2ByteInt(size);
        writeStringToLongMap(size, map);
    }

    public <T extends BinaryApiSerializable> void writeObject(T t) {
        t.writeData(this);
    }

    public <T extends BinaryApiSerializable> void writeList(int i, List<T> list) {
        if (i > 0) {
            for (T writeData : list) {
                writeData.writeData(this);
            }
        }
    }

    public <T extends BinaryApiSerializable> void writeList(List<T> list) {
        int size = CollectionUtils.size((Collection<?>) list);
        write4ByteInt((long) size);
        writeList(size, list);
    }

    public <T extends BinaryApiSerializable> void writeListWithTwoByteSize(List<T> list) {
        int size = CollectionUtils.size((Collection<?>) list);
        write2ByteInt(size);
        writeList(size, list);
    }

    public static void startEncoding() {
        if (encodingInProgress) {
            Ln.i("WARNING: recursive encoding", new Object[0]);
        }
        encodingInProgress = true;
        isEncrypting = false;
    }

    public static void finishEncoding() {
        encodingInProgress = false;
    }

    private void ensureBufferHasSpaceOfSize(int i) {
        try {
            if (this.buffer.hasArray()) {
                int limit = this.buffer.limit() - this.buffer.position();
                while (limit < i) {
                    expandBuffer();
                    limit = this.buffer.limit() - this.buffer.position();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void expandBuffer() {
        try {
            int length = this.buffer.array().length * 2;
            byte[] bArr = new byte[length];
            System.arraycopy(this.buffer.array(), 0, bArr, 0, this.buffer.position());
            this.buffer = ByteBuffer.wrap(bArr, this.buffer.position(), bArr.length - this.buffer.position());
            StringBuilder sb = new StringBuilder();
            sb.append("Encoder buffer expanded to: ");
            sb.append(length);
            sb.append(" bytes.");
            Ln.i(sb.toString(), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
