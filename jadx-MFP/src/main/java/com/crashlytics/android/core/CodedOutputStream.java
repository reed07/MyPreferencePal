package com.crashlytics.android.core;

import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class CodedOutputStream implements Flushable {
    private final byte[] buffer;
    private final int limit;
    private final OutputStream output;
    private int position = 0;

    static class OutOfSpaceException extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    public static int computeBoolSizeNoTag(boolean z) {
        return 1;
    }

    public static int computeFloatSizeNoTag(float f) {
        return 4;
    }

    public static int computeRawVarint32Size(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        return (i & -268435456) == 0 ? 4 : 5;
    }

    public static int computeRawVarint64Size(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int encodeZigZag32(int i) {
        return (i >> 31) ^ (i << 1);
    }

    private CodedOutputStream(OutputStream outputStream, byte[] bArr) {
        this.output = outputStream;
        this.buffer = bArr;
        this.limit = bArr.length;
    }

    public static CodedOutputStream newInstance(OutputStream outputStream) {
        return newInstance(outputStream, 4096);
    }

    public static CodedOutputStream newInstance(OutputStream outputStream, int i) {
        return new CodedOutputStream(outputStream, new byte[i]);
    }

    public void writeFloat(int i, float f) throws IOException {
        writeTag(i, 5);
        writeFloatNoTag(f);
    }

    public void writeUInt64(int i, long j) throws IOException {
        writeTag(i, 0);
        writeUInt64NoTag(j);
    }

    public void writeBool(int i, boolean z) throws IOException {
        writeTag(i, 0);
        writeBoolNoTag(z);
    }

    public void writeBytes(int i, ByteString byteString) throws IOException {
        writeTag(i, 2);
        writeBytesNoTag(byteString);
    }

    public void writeUInt32(int i, int i2) throws IOException {
        writeTag(i, 0);
        writeUInt32NoTag(i2);
    }

    public void writeEnum(int i, int i2) throws IOException {
        writeTag(i, 0);
        writeEnumNoTag(i2);
    }

    public void writeSInt32(int i, int i2) throws IOException {
        writeTag(i, 0);
        writeSInt32NoTag(i2);
    }

    public void writeFloatNoTag(float f) throws IOException {
        writeRawLittleEndian32(Float.floatToRawIntBits(f));
    }

    public void writeUInt64NoTag(long j) throws IOException {
        writeRawVarint64(j);
    }

    public void writeInt32NoTag(int i) throws IOException {
        if (i >= 0) {
            writeRawVarint32(i);
        } else {
            writeRawVarint64((long) i);
        }
    }

    public void writeBoolNoTag(boolean z) throws IOException {
        writeRawByte(z ? 1 : 0);
    }

    public void writeBytesNoTag(ByteString byteString) throws IOException {
        writeRawVarint32(byteString.size());
        writeRawBytes(byteString);
    }

    public void writeUInt32NoTag(int i) throws IOException {
        writeRawVarint32(i);
    }

    public void writeEnumNoTag(int i) throws IOException {
        writeInt32NoTag(i);
    }

    public void writeSInt32NoTag(int i) throws IOException {
        writeRawVarint32(encodeZigZag32(i));
    }

    public static int computeFloatSize(int i, float f) {
        return computeTagSize(i) + computeFloatSizeNoTag(f);
    }

    public static int computeUInt64Size(int i, long j) {
        return computeTagSize(i) + computeUInt64SizeNoTag(j);
    }

    public static int computeBoolSize(int i, boolean z) {
        return computeTagSize(i) + computeBoolSizeNoTag(z);
    }

    public static int computeBytesSize(int i, ByteString byteString) {
        return computeTagSize(i) + computeBytesSizeNoTag(byteString);
    }

    public static int computeUInt32Size(int i, int i2) {
        return computeTagSize(i) + computeUInt32SizeNoTag(i2);
    }

    public static int computeEnumSize(int i, int i2) {
        return computeTagSize(i) + computeEnumSizeNoTag(i2);
    }

    public static int computeSInt32Size(int i, int i2) {
        return computeTagSize(i) + computeSInt32SizeNoTag(i2);
    }

    public static int computeUInt64SizeNoTag(long j) {
        return computeRawVarint64Size(j);
    }

    public static int computeInt32SizeNoTag(int i) {
        if (i >= 0) {
            return computeRawVarint32Size(i);
        }
        return 10;
    }

    public static int computeBytesSizeNoTag(ByteString byteString) {
        return computeRawVarint32Size(byteString.size()) + byteString.size();
    }

    public static int computeUInt32SizeNoTag(int i) {
        return computeRawVarint32Size(i);
    }

    public static int computeEnumSizeNoTag(int i) {
        return computeInt32SizeNoTag(i);
    }

    public static int computeSInt32SizeNoTag(int i) {
        return computeRawVarint32Size(encodeZigZag32(i));
    }

    private void refreshBuffer() throws IOException {
        OutputStream outputStream = this.output;
        if (outputStream != null) {
            outputStream.write(this.buffer, 0, this.position);
            this.position = 0;
            return;
        }
        throw new OutOfSpaceException();
    }

    public void flush() throws IOException {
        if (this.output != null) {
            refreshBuffer();
        }
    }

    public void writeRawByte(byte b) throws IOException {
        if (this.position == this.limit) {
            refreshBuffer();
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
    }

    public void writeRawByte(int i) throws IOException {
        writeRawByte((byte) i);
    }

    public void writeRawBytes(ByteString byteString) throws IOException {
        writeRawBytes(byteString, 0, byteString.size());
    }

    public void writeRawBytes(byte[] bArr) throws IOException {
        writeRawBytes(bArr, 0, bArr.length);
    }

    public void writeRawBytes(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.limit;
        int i4 = this.position;
        if (i3 - i4 >= i2) {
            System.arraycopy(bArr, i, this.buffer, i4, i2);
            this.position += i2;
            return;
        }
        int i5 = i3 - i4;
        System.arraycopy(bArr, i, this.buffer, i4, i5);
        int i6 = i + i5;
        int i7 = i2 - i5;
        this.position = this.limit;
        refreshBuffer();
        if (i7 <= this.limit) {
            System.arraycopy(bArr, i6, this.buffer, 0, i7);
            this.position = i7;
            return;
        }
        this.output.write(bArr, i6, i7);
    }

    public void writeRawBytes(ByteString byteString, int i, int i2) throws IOException {
        int i3 = this.limit;
        int i4 = this.position;
        if (i3 - i4 >= i2) {
            byteString.copyTo(this.buffer, i, i4, i2);
            this.position += i2;
            return;
        }
        int i5 = i3 - i4;
        byteString.copyTo(this.buffer, i, i4, i5);
        int i6 = i + i5;
        int i7 = i2 - i5;
        this.position = this.limit;
        refreshBuffer();
        if (i7 <= this.limit) {
            byteString.copyTo(this.buffer, i6, 0, i7);
            this.position = i7;
            return;
        }
        InputStream newInput = byteString.newInput();
        long j = (long) i6;
        if (j == newInput.skip(j)) {
            while (i7 > 0) {
                int min = Math.min(i7, this.limit);
                int read = newInput.read(this.buffer, 0, min);
                if (read == min) {
                    this.output.write(this.buffer, 0, read);
                    i7 -= read;
                } else {
                    throw new IllegalStateException("Read failed.");
                }
            }
            return;
        }
        throw new IllegalStateException("Skip failed.");
    }

    public void writeTag(int i, int i2) throws IOException {
        writeRawVarint32(WireFormat.makeTag(i, i2));
    }

    public static int computeTagSize(int i) {
        return computeRawVarint32Size(WireFormat.makeTag(i, 0));
    }

    public void writeRawVarint32(int i) throws IOException {
        while ((i & -128) != 0) {
            writeRawByte((i & Statements.GetOwnedFoodIdsDateDescending) | 128);
            i >>>= 7;
        }
        writeRawByte(i);
    }

    public void writeRawVarint64(long j) throws IOException {
        while ((-128 & j) != 0) {
            writeRawByte((((int) j) & Statements.GetOwnedFoodIdsDateDescending) | 128);
            j >>>= 7;
        }
        writeRawByte((int) j);
    }

    public void writeRawLittleEndian32(int i) throws IOException {
        writeRawByte(i & 255);
        writeRawByte((i >> 8) & 255);
        writeRawByte((i >> 16) & 255);
        writeRawByte((i >> 24) & 255);
    }
}
