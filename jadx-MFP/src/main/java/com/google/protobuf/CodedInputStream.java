package com.google.protobuf;

import com.google.common.base.Ascii;
import com.google.protobuf.MessageLite.Builder;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class CodedInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 100;
    private static final int DEFAULT_SIZE_LIMIT = Integer.MAX_VALUE;
    private static volatile boolean proto3DiscardUnknownFieldsDefault = false;
    private boolean explicitDiscardUnknownFields;
    int recursionDepth;
    int recursionLimit;
    int sizeLimit;

    private static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private ArrayDecoder(byte[] bArr, int i, int i2, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = bArr;
            this.limit = i2 + i;
            this.pos = i;
            this.startPos = this.pos;
            this.immutable = z;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    long readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    long readRawLittleEndian64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    if (Utf8.isValidUtf8(this.buffer, i2, i2 + readRawVarint32)) {
                        int i3 = this.pos;
                        this.pos = i3 + readRawVarint32;
                        return new String(this.buffer, i3, readRawVarint32, Internal.UTF_8);
                    }
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth < this.recursionLimit) {
                this.recursionDepth++;
                builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(WireFormat.makeTag(i, 4));
                this.recursionDepth--;
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth < this.recursionLimit) {
                this.recursionDepth++;
                T t = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(WireFormat.makeTag(i, 4));
                this.recursionDepth--;
                return t;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Deprecated
        public void readUnknownGroup(int i, Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int pushLimit = pushLimit(readRawVarint32);
                this.recursionDepth++;
                builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int pushLimit = pushLimit(readRawVarint32);
                this.recursionDepth++;
                T t = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(pushLimit);
                return t;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public ByteString readBytes() throws IOException {
            ByteString byteString;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    if (!this.immutable || !this.enableAliasing) {
                        byteString = ByteString.copyFrom(this.buffer, this.pos, readRawVarint32);
                    } else {
                        byteString = ByteString.wrap(this.buffer, i2, readRawVarint32);
                    }
                    this.pos += readRawVarint32;
                    return byteString;
                }
            }
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            return ByteString.wrap(readRawBytes(readRawVarint32));
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            ByteBuffer byteBuffer;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    if (this.immutable || !this.enableAliasing) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        byteBuffer = ByteBuffer.wrap(Arrays.copyOfRange(bArr, i3, i3 + readRawVarint32));
                    } else {
                        byteBuffer = ByteBuffer.wrap(this.buffer, i2, readRawVarint32).slice();
                    }
                    this.pos += readRawVarint32;
                    return byteBuffer;
                }
            }
            if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
            if (r2[r3] < 0) goto L_0x006a;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.pos
                int r1 = r5.limit
                if (r1 != r0) goto L_0x0007
                goto L_0x006a
            L_0x0007:
                byte[] r2 = r5.buffer
                int r3 = r0 + 1
                byte r0 = r2[r0]
                if (r0 < 0) goto L_0x0012
                r5.pos = r3
                return r0
            L_0x0012:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L_0x0018
                goto L_0x006a
            L_0x0018:
                int r1 = r3 + 1
                byte r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L_0x0024
                r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
                goto L_0x0071
            L_0x0024:
                int r3 = r1 + 1
                byte r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L_0x0031
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                r1 = r3
                goto L_0x0071
            L_0x0031:
                int r1 = r3 + 1
                byte r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L_0x003f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L_0x0071
            L_0x003f:
                int r3 = r1 + 1
                byte r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L_0x0070
                int r1 = r3 + 1
                byte r3 = r2[r3]
                if (r3 >= 0) goto L_0x0071
                int r3 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0070
                int r1 = r3 + 1
                byte r3 = r2[r3]
                if (r3 >= 0) goto L_0x0071
                int r3 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0070
                int r1 = r3 + 1
                byte r2 = r2[r3]
                if (r2 >= 0) goto L_0x0071
            L_0x006a:
                long r0 = r5.readRawVarint64SlowPath()
                int r1 = (int) r0
                return r1
            L_0x0070:
                r1 = r3
            L_0x0071:
                r5.pos = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() throws IOException {
            if (this.limit - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() throws IOException {
            long j;
            int i = this.pos;
            int i2 = this.limit;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    this.pos = i3;
                    return (long) b;
                } else if (i2 - i3 >= 9) {
                    int i4 = i3 + 1;
                    byte b2 = b ^ (bArr[i3] << 7);
                    if (b2 < 0) {
                        j = (long) (b2 ^ Byte.MIN_VALUE);
                    } else {
                        int i5 = i4 + 1;
                        byte b3 = b2 ^ (bArr[i4] << Ascii.SO);
                        if (b3 >= 0) {
                            i4 = i5;
                            j = (long) (b3 ^ 16256);
                        } else {
                            i4 = i5 + 1;
                            byte b4 = b3 ^ (bArr[i5] << Ascii.NAK);
                            if (b4 < 0) {
                                j = (long) (b4 ^ -2080896);
                            } else {
                                long j2 = (long) b4;
                                int i6 = i4 + 1;
                                long j3 = j2 ^ (((long) bArr[i4]) << 28);
                                if (j3 >= 0) {
                                    j = j3 ^ 266354560;
                                    i4 = i6;
                                } else {
                                    i4 = i6 + 1;
                                    long j4 = j3 ^ (((long) bArr[i6]) << 35);
                                    if (j4 < 0) {
                                        j = j4 ^ -34093383808L;
                                    } else {
                                        int i7 = i4 + 1;
                                        long j5 = j4 ^ (((long) bArr[i4]) << 42);
                                        if (j5 >= 0) {
                                            j = j5 ^ 4363953127296L;
                                            i4 = i7;
                                        } else {
                                            i4 = i7 + 1;
                                            long j6 = j5 ^ (((long) bArr[i7]) << 49);
                                            if (j6 < 0) {
                                                j = j6 ^ -558586000294016L;
                                            } else {
                                                int i8 = i4 + 1;
                                                long j7 = (j6 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    i4 = i8 + 1;
                                                    if (((long) bArr[i8]) >= 0) {
                                                        j = j7;
                                                    }
                                                } else {
                                                    i4 = i8;
                                                    j = j7;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = i4;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: 0000 */
        public long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & Byte.MAX_VALUE)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            int i = this.pos;
            if (this.limit - i >= 4) {
                byte[] bArr = this.buffer;
                this.pos = i + 4;
                return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() throws IOException {
            int i = this.pos;
            if (this.limit - i >= 8) {
                byte[] bArr = this.buffer;
                this.pos = i + 8;
                return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                int i2 = this.currentLimit;
                if (totalBytesRead <= i2) {
                    this.currentLimit = totalBytesRead;
                    recomputeBufferSizeAfterLimit();
                    return i2;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void recomputeBufferSizeAfterLimit() {
            this.limit += this.bufferSizeAfterLimit;
            int i = this.limit;
            int i2 = i - this.startPos;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                this.bufferSizeAfterLimit = i2 - i3;
                this.limit = i - this.bufferSizeAfterLimit;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        public byte readRawByte() throws IOException {
            int i = this.pos;
            if (i != this.limit) {
                byte[] bArr = this.buffer;
                this.pos = i + 1;
                return bArr[i];
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte[] readRawBytes(int i) throws IOException {
            if (i > 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    this.pos = i + i3;
                    return Arrays.copyOfRange(this.buffer, i3, this.pos);
                }
            }
            if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int i) throws IOException {
            if (i >= 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    this.pos = i3 + i;
                    return;
                }
            }
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    private static final class IterableDirectByteBufferDecoder extends CodedInputStream {
        private int bufferSizeAfterCurrentLimit;
        private long currentAddress;
        private ByteBuffer currentByteBuffer;
        private long currentByteBufferLimit;
        private long currentByteBufferPos;
        private long currentByteBufferStartPos;
        private int currentLimit;
        private boolean enableAliasing;
        private boolean immutable;
        private Iterable<ByteBuffer> input;
        private Iterator<ByteBuffer> iterator;
        private int lastTag;
        private int startOffset;
        private int totalBufferSize;
        private int totalBytesRead;

        private IterableDirectByteBufferDecoder(Iterable<ByteBuffer> iterable, int i, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.totalBufferSize = i;
            this.input = iterable;
            this.iterator = this.input.iterator();
            this.immutable = z;
            this.totalBytesRead = 0;
            this.startOffset = 0;
            if (i == 0) {
                this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
                this.currentByteBufferPos = 0;
                this.currentByteBufferStartPos = 0;
                this.currentByteBufferLimit = 0;
                this.currentAddress = 0;
                return;
            }
            tryGetNextByteBuffer();
        }

        private void getNextByteBuffer() throws InvalidProtocolBufferException {
            if (this.iterator.hasNext()) {
                tryGetNextByteBuffer();
                return;
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private void tryGetNextByteBuffer() {
            this.currentByteBuffer = (ByteBuffer) this.iterator.next();
            this.totalBytesRead += (int) (this.currentByteBufferPos - this.currentByteBufferStartPos);
            this.currentByteBufferPos = (long) this.currentByteBuffer.position();
            this.currentByteBufferStartPos = this.currentByteBufferPos;
            this.currentByteBufferLimit = (long) this.currentByteBuffer.limit();
            this.currentAddress = UnsafeUtil.addressOffset(this.currentByteBuffer);
            long j = this.currentByteBufferPos;
            long j2 = this.currentAddress;
            this.currentByteBufferPos = j + j2;
            this.currentByteBufferStartPos += j2;
            this.currentByteBufferLimit += j2;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    long readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    long readRawLittleEndian64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = (long) readRawVarint32;
                long j2 = this.currentByteBufferLimit;
                long j3 = this.currentByteBufferPos;
                if (j <= j2 - j3) {
                    byte[] bArr = new byte[readRawVarint32];
                    UnsafeUtil.copyMemory(j3, bArr, 0, j);
                    String str = new String(bArr, Internal.UTF_8);
                    this.currentByteBufferPos += j;
                    return str;
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo(bArr2, 0, readRawVarint32);
                return new String(bArr2, Internal.UTF_8);
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = (long) readRawVarint32;
                long j2 = this.currentByteBufferLimit;
                long j3 = this.currentByteBufferPos;
                if (j <= j2 - j3) {
                    byte[] bArr = new byte[readRawVarint32];
                    UnsafeUtil.copyMemory(j3, bArr, 0, j);
                    if (Utf8.isValidUtf8(bArr)) {
                        String str = new String(bArr, Internal.UTF_8);
                        this.currentByteBufferPos += j;
                        return str;
                    }
                    throw InvalidProtocolBufferException.invalidUtf8();
                }
            }
            if (readRawVarint32 >= 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo(bArr2, 0, readRawVarint32);
                if (Utf8.isValidUtf8(bArr2)) {
                    return new String(bArr2, Internal.UTF_8);
                }
                throw InvalidProtocolBufferException.invalidUtf8();
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth < this.recursionLimit) {
                this.recursionDepth++;
                builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(WireFormat.makeTag(i, 4));
                this.recursionDepth--;
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth < this.recursionLimit) {
                this.recursionDepth++;
                T t = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(WireFormat.makeTag(i, 4));
                this.recursionDepth--;
                return t;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Deprecated
        public void readUnknownGroup(int i, Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int pushLimit = pushLimit(readRawVarint32);
                this.recursionDepth++;
                builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int pushLimit = pushLimit(readRawVarint32);
                this.recursionDepth++;
                T t = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(pushLimit);
                return t;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = (long) readRawVarint32;
                long j2 = this.currentByteBufferLimit;
                long j3 = this.currentByteBufferPos;
                if (j <= j2 - j3) {
                    if (!this.immutable || !this.enableAliasing) {
                        byte[] bArr = new byte[readRawVarint32];
                        UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0, j);
                        this.currentByteBufferPos += j;
                        return ByteString.wrap(bArr);
                    }
                    int i = (int) (j3 - this.currentAddress);
                    ByteString wrap = ByteString.wrap(slice(i, readRawVarint32 + i));
                    this.currentByteBufferPos += j;
                    return wrap;
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo(bArr2, 0, readRawVarint32);
                return ByteString.wrap(bArr2);
            } else if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = (long) readRawVarint32;
                if (j <= currentRemaining()) {
                    if (this.immutable || !this.enableAliasing) {
                        byte[] bArr = new byte[readRawVarint32];
                        UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0, j);
                        this.currentByteBufferPos += j;
                        return ByteBuffer.wrap(bArr);
                    }
                    this.currentByteBufferPos += j;
                    long j2 = this.currentByteBufferPos;
                    long j3 = this.currentAddress;
                    return slice((int) ((j2 - j3) - j), (int) (j2 - j3));
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo(bArr2, 0, readRawVarint32);
                return ByteBuffer.wrap(bArr2);
            } else if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0088, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r4) < 0) goto L_0x008a;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r10 = this;
                long r0 = r10.currentByteBufferPos
                long r2 = r10.currentByteBufferLimit
                int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r4 != 0) goto L_0x000a
                goto L_0x008a
            L_0x000a:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r0 < 0) goto L_0x001a
                long r4 = r10.currentByteBufferPos
                long r4 = r4 + r2
                r10.currentByteBufferPos = r4
                return r0
            L_0x001a:
                long r6 = r10.currentByteBufferLimit
                long r8 = r10.currentByteBufferPos
                long r6 = r6 - r8
                r8 = 10
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L_0x0026
                goto L_0x008a
            L_0x0026:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L_0x0034
                r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
                goto L_0x0095
            L_0x0034:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L_0x0043
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                r6 = r4
                goto L_0x0095
            L_0x0043:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L_0x0053
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L_0x0095
            L_0x0053:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L_0x0094
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L_0x0095
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L_0x0092
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L_0x0095
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L_0x0090
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L_0x0095
            L_0x008a:
                long r0 = r10.readRawVarint64SlowPath()
                int r1 = (int) r0
                return r1
            L_0x0090:
                r6 = r4
                goto L_0x0095
            L_0x0092:
                r6 = r4
                goto L_0x0095
            L_0x0094:
                r6 = r4
            L_0x0095:
                r10.currentByteBufferPos = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.IterableDirectByteBufferDecoder.readRawVarint32():int");
        }

        public long readRawVarint64() throws IOException {
            long j;
            long j2;
            long j3 = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != j3) {
                long j4 = j3 + 1;
                byte b = UnsafeUtil.getByte(j3);
                if (b >= 0) {
                    this.currentByteBufferPos++;
                    return (long) b;
                } else if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long j5 = j4 + 1;
                    byte b2 = b ^ (UnsafeUtil.getByte(j4) << 7);
                    if (b2 < 0) {
                        j2 = (long) (b2 ^ Byte.MIN_VALUE);
                        j = j5;
                    } else {
                        j = j5 + 1;
                        byte b3 = b2 ^ (UnsafeUtil.getByte(j5) << Ascii.SO);
                        if (b3 >= 0) {
                            j2 = (long) (b3 ^ 16256);
                        } else {
                            long j6 = j + 1;
                            byte b4 = b3 ^ (UnsafeUtil.getByte(j) << Ascii.NAK);
                            if (b4 < 0) {
                                j2 = (long) (b4 ^ -2080896);
                                j = j6;
                            } else {
                                j = j6 + 1;
                                long j7 = ((long) b4) ^ (((long) UnsafeUtil.getByte(j6)) << 28);
                                if (j7 >= 0) {
                                    j2 = j7 ^ 266354560;
                                } else {
                                    long j8 = j + 1;
                                    long j9 = j7 ^ (((long) UnsafeUtil.getByte(j)) << 35);
                                    if (j9 < 0) {
                                        j2 = j9 ^ -34093383808L;
                                        j = j8;
                                    } else {
                                        j = j8 + 1;
                                        long j10 = j9 ^ (((long) UnsafeUtil.getByte(j8)) << 42);
                                        if (j10 >= 0) {
                                            j2 = j10 ^ 4363953127296L;
                                        } else {
                                            long j11 = j + 1;
                                            long j12 = j10 ^ (((long) UnsafeUtil.getByte(j)) << 49);
                                            if (j12 < 0) {
                                                j2 = j12 ^ -558586000294016L;
                                                j = j11;
                                            } else {
                                                j = j11 + 1;
                                                j2 = (j12 ^ (((long) UnsafeUtil.getByte(j11)) << 56)) ^ 71499008037633920L;
                                                if (j2 < 0) {
                                                    long j13 = 1 + j;
                                                    if (((long) UnsafeUtil.getByte(j)) >= 0) {
                                                        j = j13;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.currentByteBufferPos = j;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: 0000 */
        public long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & Byte.MAX_VALUE)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            if (currentRemaining() < 4) {
                return (readRawByte() & 255) | ((readRawByte() & 255) << 8) | ((readRawByte() & 255) << Ascii.DLE) | ((readRawByte() & 255) << Ascii.CAN);
            }
            long j = this.currentByteBufferPos;
            this.currentByteBufferPos = 4 + j;
            return ((UnsafeUtil.getByte(j + 3) & 255) << Ascii.CAN) | (UnsafeUtil.getByte(j) & 255) | ((UnsafeUtil.getByte(1 + j) & 255) << 8) | ((UnsafeUtil.getByte(2 + j) & 255) << Ascii.DLE);
        }

        public long readRawLittleEndian64() throws IOException {
            if (currentRemaining() < 8) {
                return (((long) readRawByte()) & 255) | ((((long) readRawByte()) & 255) << 8) | ((((long) readRawByte()) & 255) << 16) | ((((long) readRawByte()) & 255) << 24) | ((((long) readRawByte()) & 255) << 32) | ((((long) readRawByte()) & 255) << 40) | ((((long) readRawByte()) & 255) << 48) | ((((long) readRawByte()) & 255) << 56);
            }
            long j = this.currentByteBufferPos;
            this.currentByteBufferPos = 8 + j;
            return ((((long) UnsafeUtil.getByte(j + 7)) & 255) << 56) | (((long) UnsafeUtil.getByte(j)) & 255) | ((((long) UnsafeUtil.getByte(1 + j)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + j)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + j)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + j)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + j)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + j)) & 255) << 48);
        }

        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        public void resetSizeCounter() {
            this.startOffset = (int) ((((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int totalBytesRead2 = i + getTotalBytesRead();
                int i2 = this.currentLimit;
                if (totalBytesRead2 <= i2) {
                    this.currentLimit = totalBytesRead2;
                    recomputeBufferSizeAfterLimit();
                    return i2;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void recomputeBufferSizeAfterLimit() {
            this.totalBufferSize += this.bufferSizeAfterCurrentLimit;
            int i = this.totalBufferSize;
            int i2 = i - this.startOffset;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                this.bufferSizeAfterCurrentLimit = i2 - i3;
                this.totalBufferSize = i - this.bufferSizeAfterCurrentLimit;
                return;
            }
            this.bufferSizeAfterCurrentLimit = 0;
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        public boolean isAtEnd() throws IOException {
            return (((long) this.totalBytesRead) + this.currentByteBufferPos) - this.currentByteBufferStartPos == ((long) this.totalBufferSize);
        }

        public int getTotalBytesRead() {
            return (int) ((((long) (this.totalBytesRead - this.startOffset)) + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        public byte readRawByte() throws IOException {
            if (currentRemaining() == 0) {
                getNextByteBuffer();
            }
            long j = this.currentByteBufferPos;
            this.currentByteBufferPos = 1 + j;
            return UnsafeUtil.getByte(j);
        }

        public byte[] readRawBytes(int i) throws IOException {
            if (i >= 0) {
                long j = (long) i;
                if (j <= currentRemaining()) {
                    byte[] bArr = new byte[i];
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0, j);
                    this.currentByteBufferPos += j;
                    return bArr;
                }
            }
            if (i >= 0 && i <= remaining()) {
                byte[] bArr2 = new byte[i];
                readRawBytesTo(bArr2, 0, i);
                return bArr2;
            } else if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        private void readRawBytesTo(byte[] bArr, int i, int i2) throws IOException {
            if (i2 >= 0 && i2 <= remaining()) {
                int i3 = i2;
                while (i3 > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int min = Math.min(i3, (int) currentRemaining());
                    long j = (long) min;
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, (long) ((i2 - i3) + i), j);
                    i3 -= min;
                    this.currentByteBufferPos += j;
                }
            } else if (i2 > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i2 != 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int i) throws IOException {
            if (i >= 0 && ((long) i) <= (((long) (this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos) + this.currentByteBufferStartPos) {
                while (i > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int min = Math.min(i, (int) currentRemaining());
                    i -= min;
                    this.currentByteBufferPos += (long) min;
                }
            } else if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void skipRawVarint() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private int remaining() {
            return (int) ((((long) (this.totalBufferSize - this.totalBytesRead)) - this.currentByteBufferPos) + this.currentByteBufferStartPos);
        }

        private long currentRemaining() {
            return this.currentByteBufferLimit - this.currentByteBufferPos;
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|8) */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
            throw r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0027, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
            throw com.google.protobuf.InvalidProtocolBufferException.truncatedMessage();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
            r3.currentByteBuffer.position(r0);
            r3.currentByteBuffer.limit(r1);
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0029 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.nio.ByteBuffer slice(int r4, int r5) throws java.io.IOException {
            /*
                r3 = this;
                java.nio.ByteBuffer r0 = r3.currentByteBuffer
                int r0 = r0.position()
                java.nio.ByteBuffer r1 = r3.currentByteBuffer
                int r1 = r1.limit()
                java.nio.ByteBuffer r2 = r3.currentByteBuffer     // Catch:{ IllegalArgumentException -> 0x0029 }
                r2.position(r4)     // Catch:{ IllegalArgumentException -> 0x0029 }
                java.nio.ByteBuffer r4 = r3.currentByteBuffer     // Catch:{ IllegalArgumentException -> 0x0029 }
                r4.limit(r5)     // Catch:{ IllegalArgumentException -> 0x0029 }
                java.nio.ByteBuffer r4 = r3.currentByteBuffer     // Catch:{ IllegalArgumentException -> 0x0029 }
                java.nio.ByteBuffer r4 = r4.slice()     // Catch:{ IllegalArgumentException -> 0x0029 }
                java.nio.ByteBuffer r5 = r3.currentByteBuffer
                r5.position(r0)
                java.nio.ByteBuffer r5 = r3.currentByteBuffer
                r5.limit(r1)
                return r4
            L_0x0027:
                r4 = move-exception
                goto L_0x002e
            L_0x0029:
                com.google.protobuf.InvalidProtocolBufferException r4 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()     // Catch:{ all -> 0x0027 }
                throw r4     // Catch:{ all -> 0x0027 }
            L_0x002e:
                java.nio.ByteBuffer r5 = r3.currentByteBuffer
                r5.position(r0)
                java.nio.ByteBuffer r5 = r3.currentByteBuffer
                r5.limit(r1)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.IterableDirectByteBufferDecoder.slice(int, int):java.nio.ByteBuffer");
        }
    }

    private static final class StreamDecoder extends CodedInputStream {
        /* access modifiers changed from: private */
        public final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        /* access modifiers changed from: private */
        public int pos;
        private RefillCallback refillCallback;
        private int totalBytesRetired;

        private interface RefillCallback {
            void onRefill();
        }

        private class SkippedDataSink implements RefillCallback {
            private ByteArrayOutputStream byteArrayStream;
            private int lastPos = StreamDecoder.this.pos;

            private SkippedDataSink() {
            }

            public void onRefill() {
                if (this.byteArrayStream == null) {
                    this.byteArrayStream = new ByteArrayOutputStream();
                }
                this.byteArrayStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                this.lastPos = 0;
            }

            /* access modifiers changed from: 0000 */
            public ByteBuffer getSkippedData() {
                ByteArrayOutputStream byteArrayOutputStream = this.byteArrayStream;
                if (byteArrayOutputStream == null) {
                    return ByteBuffer.wrap(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                }
                byteArrayOutputStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos);
                return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
            }
        }

        public void enableAliasing(boolean z) {
        }

        private StreamDecoder(InputStream inputStream, int i) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.refillCallback = null;
            Internal.checkNotNull(inputStream, "input");
            this.input = inputStream;
            this.buffer = new byte[i];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    long readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    long readRawLittleEndian64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.bufferSize;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 > this.bufferSize) {
                return new String(readRawBytesSlowPath(readRawVarint32), Internal.UTF_8);
            }
            refillBuffer(readRawVarint32);
            String str2 = new String(this.buffer, this.pos, readRawVarint32, Internal.UTF_8);
            this.pos += readRawVarint32;
            return str2;
        }

        public String readStringRequireUtf8() throws IOException {
            byte[] bArr;
            int readRawVarint32 = readRawVarint32();
            int i = this.pos;
            int i2 = 0;
            if (readRawVarint32 <= this.bufferSize - i && readRawVarint32 > 0) {
                bArr = this.buffer;
                this.pos = i + readRawVarint32;
                i2 = i;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= this.bufferSize) {
                    refillBuffer(readRawVarint32);
                    bArr = this.buffer;
                    this.pos = readRawVarint32 + 0;
                } else {
                    bArr = readRawBytesSlowPath(readRawVarint32);
                }
            }
            if (Utf8.isValidUtf8(bArr, i2, i2 + readRawVarint32)) {
                return new String(bArr, i2, readRawVarint32, Internal.UTF_8);
            }
            throw InvalidProtocolBufferException.invalidUtf8();
        }

        public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth < this.recursionLimit) {
                this.recursionDepth++;
                builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(WireFormat.makeTag(i, 4));
                this.recursionDepth--;
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth < this.recursionLimit) {
                this.recursionDepth++;
                T t = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(WireFormat.makeTag(i, 4));
                this.recursionDepth--;
                return t;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Deprecated
        public void readUnknownGroup(int i, Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int pushLimit = pushLimit(readRawVarint32);
                this.recursionDepth++;
                builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int pushLimit = pushLimit(readRawVarint32);
                this.recursionDepth++;
                T t = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(pushLimit);
                return t;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 <= i - i2 && readRawVarint32 > 0) {
                ByteString copyFrom = ByteString.copyFrom(this.buffer, i2, readRawVarint32);
                this.pos += readRawVarint32;
                return copyFrom;
            } else if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            } else {
                return readBytesSlowPath(readRawVarint32);
            }
        }

        public byte[] readByteArray() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 > i - i2 || readRawVarint32 <= 0) {
                return readRawBytesSlowPath(readRawVarint32);
            }
            byte[] copyOfRange = Arrays.copyOfRange(this.buffer, i2, i2 + readRawVarint32);
            this.pos += readRawVarint32;
            return copyOfRange;
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 <= i - i2 && readRawVarint32 > 0) {
                ByteBuffer wrap = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i2, i2 + readRawVarint32));
                this.pos += readRawVarint32;
                return wrap;
            } else if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            } else {
                return ByteBuffer.wrap(readRawBytesSlowPath(readRawVarint32));
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0068, code lost:
            if (r2[r3] < 0) goto L_0x006a;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.pos
                int r1 = r5.bufferSize
                if (r1 != r0) goto L_0x0007
                goto L_0x006a
            L_0x0007:
                byte[] r2 = r5.buffer
                int r3 = r0 + 1
                byte r0 = r2[r0]
                if (r0 < 0) goto L_0x0012
                r5.pos = r3
                return r0
            L_0x0012:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L_0x0018
                goto L_0x006a
            L_0x0018:
                int r1 = r3 + 1
                byte r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L_0x0024
                r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
                goto L_0x0071
            L_0x0024:
                int r3 = r1 + 1
                byte r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L_0x0031
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                r1 = r3
                goto L_0x0071
            L_0x0031:
                int r1 = r3 + 1
                byte r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L_0x003f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L_0x0071
            L_0x003f:
                int r3 = r1 + 1
                byte r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L_0x0070
                int r1 = r3 + 1
                byte r3 = r2[r3]
                if (r3 >= 0) goto L_0x0071
                int r3 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0070
                int r1 = r3 + 1
                byte r3 = r2[r3]
                if (r3 >= 0) goto L_0x0071
                int r3 = r1 + 1
                byte r1 = r2[r1]
                if (r1 >= 0) goto L_0x0070
                int r1 = r3 + 1
                byte r2 = r2[r3]
                if (r2 >= 0) goto L_0x0071
            L_0x006a:
                long r0 = r5.readRawVarint64SlowPath()
                int r1 = (int) r0
                return r1
            L_0x0070:
                r1 = r3
            L_0x0071:
                r5.pos = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() throws IOException {
            if (this.bufferSize - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() throws IOException {
            long j;
            int i = this.pos;
            int i2 = this.bufferSize;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    this.pos = i3;
                    return (long) b;
                } else if (i2 - i3 >= 9) {
                    int i4 = i3 + 1;
                    byte b2 = b ^ (bArr[i3] << 7);
                    if (b2 < 0) {
                        j = (long) (b2 ^ Byte.MIN_VALUE);
                    } else {
                        int i5 = i4 + 1;
                        byte b3 = b2 ^ (bArr[i4] << Ascii.SO);
                        if (b3 >= 0) {
                            i4 = i5;
                            j = (long) (b3 ^ 16256);
                        } else {
                            i4 = i5 + 1;
                            byte b4 = b3 ^ (bArr[i5] << Ascii.NAK);
                            if (b4 < 0) {
                                j = (long) (b4 ^ -2080896);
                            } else {
                                long j2 = (long) b4;
                                int i6 = i4 + 1;
                                long j3 = j2 ^ (((long) bArr[i4]) << 28);
                                if (j3 >= 0) {
                                    j = j3 ^ 266354560;
                                    i4 = i6;
                                } else {
                                    i4 = i6 + 1;
                                    long j4 = j3 ^ (((long) bArr[i6]) << 35);
                                    if (j4 < 0) {
                                        j = j4 ^ -34093383808L;
                                    } else {
                                        int i7 = i4 + 1;
                                        long j5 = j4 ^ (((long) bArr[i4]) << 42);
                                        if (j5 >= 0) {
                                            j = j5 ^ 4363953127296L;
                                            i4 = i7;
                                        } else {
                                            i4 = i7 + 1;
                                            long j6 = j5 ^ (((long) bArr[i7]) << 49);
                                            if (j6 < 0) {
                                                j = j6 ^ -558586000294016L;
                                            } else {
                                                int i8 = i4 + 1;
                                                long j7 = (j6 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    i4 = i8 + 1;
                                                    if (((long) bArr[i8]) >= 0) {
                                                        j = j7;
                                                    }
                                                } else {
                                                    i4 = i8;
                                                    j = j7;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = i4;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: 0000 */
        public long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & Byte.MAX_VALUE)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            int i = this.pos;
            if (this.bufferSize - i < 4) {
                refillBuffer(4);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << Ascii.DLE);
        }

        public long readRawLittleEndian64() throws IOException {
            int i = this.pos;
            if (this.bufferSize - i < 8) {
                refillBuffer(8);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }

        public void resetSizeCounter() {
            this.totalBytesRetired = -this.pos;
        }

        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int i2 = i + this.totalBytesRetired + this.pos;
                int i3 = this.currentLimit;
                if (i2 <= i3) {
                    this.currentLimit = i2;
                    recomputeBufferSizeAfterLimit();
                    return i3;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private void recomputeBufferSizeAfterLimit() {
            this.bufferSize += this.bufferSizeAfterLimit;
            int i = this.totalBytesRetired;
            int i2 = this.bufferSize;
            int i3 = i + i2;
            int i4 = this.currentLimit;
            if (i3 > i4) {
                this.bufferSizeAfterLimit = i3 - i4;
                this.bufferSize = i2 - this.bufferSizeAfterLimit;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - (this.totalBytesRetired + this.pos);
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.bufferSize && !tryRefillBuffer(1);
        }

        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        private void refillBuffer(int i) throws IOException {
            if (tryRefillBuffer(i)) {
                return;
            }
            if (i > (this.sizeLimit - this.totalBytesRetired) - this.pos) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        private boolean tryRefillBuffer(int i) throws IOException {
            if (this.pos + i > this.bufferSize) {
                int i2 = this.sizeLimit;
                int i3 = this.totalBytesRetired;
                int i4 = i2 - i3;
                int i5 = this.pos;
                if (i > i4 - i5 || i3 + i5 + i > this.currentLimit) {
                    return false;
                }
                RefillCallback refillCallback2 = this.refillCallback;
                if (refillCallback2 != null) {
                    refillCallback2.onRefill();
                }
                int i6 = this.pos;
                if (i6 > 0) {
                    int i7 = this.bufferSize;
                    if (i7 > i6) {
                        byte[] bArr = this.buffer;
                        System.arraycopy(bArr, i6, bArr, 0, i7 - i6);
                    }
                    this.totalBytesRetired += i6;
                    this.bufferSize -= i6;
                    this.pos = 0;
                }
                InputStream inputStream = this.input;
                byte[] bArr2 = this.buffer;
                int i8 = this.bufferSize;
                int read = inputStream.read(bArr2, i8, Math.min(bArr2.length - i8, (this.sizeLimit - this.totalBytesRetired) - this.bufferSize));
                if (read == 0 || read < -1 || read > this.buffer.length) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("InputStream#read(byte[]) returned invalid result: ");
                    sb.append(read);
                    sb.append("\nThe InputStream implementation is buggy.");
                    throw new IllegalStateException(sb.toString());
                } else if (read <= 0) {
                    return false;
                } else {
                    this.bufferSize += read;
                    recomputeBufferSizeAfterLimit();
                    return this.bufferSize >= i ? true : tryRefillBuffer(i);
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("refillBuffer() called when ");
                sb2.append(i);
                sb2.append(" bytes were already available in buffer");
                throw new IllegalStateException(sb2.toString());
            }
        }

        public byte readRawByte() throws IOException {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }

        public byte[] readRawBytes(int i) throws IOException {
            int i2 = this.pos;
            if (i > this.bufferSize - i2 || i <= 0) {
                return readRawBytesSlowPath(i);
            }
            int i3 = i + i2;
            this.pos = i3;
            return Arrays.copyOfRange(this.buffer, i2, i3);
        }

        private byte[] readRawBytesSlowPath(int i) throws IOException {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (readRawBytesSlowPathOneChunk != null) {
                return readRawBytesSlowPathOneChunk;
            }
            int i2 = this.pos;
            int i3 = this.bufferSize;
            int i4 = i3 - i2;
            this.totalBytesRetired += i3;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - i4);
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, i2, bArr, 0, i4);
            for (byte[] bArr2 : readRawBytesSlowPathRemainingChunks) {
                System.arraycopy(bArr2, 0, bArr, i4, bArr2.length);
                i4 += bArr2.length;
            }
            return bArr;
        }

        private byte[] readRawBytesSlowPathOneChunk(int i) throws IOException {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i >= 0) {
                int i2 = this.totalBytesRetired + this.pos + i;
                if (i2 - this.sizeLimit <= 0) {
                    int i3 = this.currentLimit;
                    if (i2 <= i3) {
                        int i4 = this.bufferSize - this.pos;
                        int i5 = i - i4;
                        if (i5 >= 4096 && i5 > this.input.available()) {
                            return null;
                        }
                        byte[] bArr = new byte[i];
                        System.arraycopy(this.buffer, this.pos, bArr, 0, i4);
                        this.totalBytesRetired += this.bufferSize;
                        this.pos = 0;
                        this.bufferSize = 0;
                        while (i4 < bArr.length) {
                            int read = this.input.read(bArr, i4, i - i4);
                            if (read != -1) {
                                this.totalBytesRetired += read;
                                i4 += read;
                            } else {
                                throw InvalidProtocolBufferException.truncatedMessage();
                            }
                        }
                        return bArr;
                    }
                    skipRawBytes((i3 - this.totalBytesRetired) - this.pos);
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        private List<byte[]> readRawBytesSlowPathRemainingChunks(int i) throws IOException {
            ArrayList arrayList = new ArrayList();
            while (i > 0) {
                byte[] bArr = new byte[Math.min(i, 4096)];
                int i2 = 0;
                while (i2 < bArr.length) {
                    int read = this.input.read(bArr, i2, bArr.length - i2);
                    if (read != -1) {
                        this.totalBytesRetired += read;
                        i2 += read;
                    } else {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                }
                i -= bArr.length;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        private ByteString readBytesSlowPath(int i) throws IOException {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (readRawBytesSlowPathOneChunk != null) {
                return ByteString.wrap(readRawBytesSlowPathOneChunk);
            }
            int i2 = this.pos;
            int i3 = this.bufferSize;
            int i4 = i3 - i2;
            this.totalBytesRetired += i3;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - i4);
            ArrayList arrayList = new ArrayList(readRawBytesSlowPathRemainingChunks.size() + 1);
            arrayList.add(ByteString.copyFrom(this.buffer, i2, i4));
            for (byte[] wrap : readRawBytesSlowPathRemainingChunks) {
                arrayList.add(ByteString.wrap(wrap));
            }
            return ByteString.copyFrom((Iterable<ByteString>) arrayList);
        }

        public void skipRawBytes(int i) throws IOException {
            int i2 = this.bufferSize;
            int i3 = this.pos;
            if (i > i2 - i3 || i < 0) {
                skipRawBytesSlowPath(i);
            } else {
                this.pos = i3 + i;
            }
        }

        private void skipRawBytesSlowPath(int i) throws IOException {
            if (i >= 0) {
                int i2 = this.totalBytesRetired;
                int i3 = this.pos;
                int i4 = i2 + i3 + i;
                int i5 = this.currentLimit;
                if (i4 <= i5) {
                    int i6 = this.bufferSize;
                    int i7 = i6 - i3;
                    this.pos = i6;
                    refillBuffer(1);
                    while (true) {
                        int i8 = i - i7;
                        int i9 = this.bufferSize;
                        if (i8 > i9) {
                            i7 += i9;
                            this.pos = i9;
                            refillBuffer(1);
                        } else {
                            this.pos = i8;
                            return;
                        }
                    }
                } else {
                    skipRawBytes((i5 - i2) - i3);
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }
    }

    private static final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private UnsafeDirectNioDecoder(ByteBuffer byteBuffer, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = byteBuffer;
            this.address = UnsafeUtil.addressOffset(byteBuffer);
            this.limit = this.address + ((long) byteBuffer.limit());
            this.pos = this.address + ((long) byteBuffer.position());
            this.startPos = this.pos;
            this.immutable = z;
        }

        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) != 0) {
                return this.lastTag;
            }
            throw InvalidProtocolBufferException.invalidTag();
        }

        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        public int getLastTag() {
            return this.lastTag;
        }

        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    long readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    long readRawLittleEndian64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                long j = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j);
                String str = new String(bArr, Internal.UTF_8);
                this.pos += j;
                return str;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 >= 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                long j = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j);
                if (Utf8.isValidUtf8(bArr)) {
                    String str = new String(bArr, Internal.UTF_8);
                    this.pos += j;
                    return str;
                }
                throw InvalidProtocolBufferException.invalidUtf8();
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        public void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth < this.recursionLimit) {
                this.recursionDepth++;
                builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(WireFormat.makeTag(i, 4));
                this.recursionDepth--;
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth < this.recursionLimit) {
                this.recursionDepth++;
                T t = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(WireFormat.makeTag(i, 4));
                this.recursionDepth--;
                return t;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        @Deprecated
        public void readUnknownGroup(int i, Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        public void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int pushLimit = pushLimit(readRawVarint32);
                this.recursionDepth++;
                builder.mergeFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(pushLimit);
                return;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth < this.recursionLimit) {
                int pushLimit = pushLimit(readRawVarint32);
                this.recursionDepth++;
                T t = (MessageLite) parser.parsePartialFrom((CodedInputStream) this, extensionRegistryLite);
                checkLastTagWas(0);
                this.recursionDepth--;
                popLimit(pushLimit);
                return t;
            }
            throw InvalidProtocolBufferException.recursionLimitExceeded();
        }

        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (!this.immutable || !this.enableAliasing) {
                byte[] bArr = new byte[readRawVarint32];
                long j = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j);
                this.pos += j;
                return ByteString.wrap(bArr);
            } else {
                long j2 = this.pos;
                long j3 = (long) readRawVarint32;
                ByteBuffer slice = slice(j2, j2 + j3);
                this.pos += j3;
                return ByteString.wrap(slice);
            }
        }

        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (this.immutable || !this.enableAliasing) {
                byte[] bArr = new byte[readRawVarint32];
                long j = (long) readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0, j);
                this.pos += j;
                return ByteBuffer.wrap(bArr);
            } else {
                long j2 = this.pos;
                long j3 = (long) readRawVarint32;
                ByteBuffer slice = slice(j2, j2 + j3);
                this.pos += j3;
                return slice;
            }
        }

        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r4) < 0) goto L_0x0085;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r10 = this;
                long r0 = r10.pos
                long r2 = r10.limit
                int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r4 != 0) goto L_0x000a
                goto L_0x0085
            L_0x000a:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r0 < 0) goto L_0x0017
                r10.pos = r4
                return r0
            L_0x0017:
                long r6 = r10.limit
                long r6 = r6 - r4
                r8 = 9
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L_0x0021
                goto L_0x0085
            L_0x0021:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L_0x002f
                r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
                goto L_0x0090
            L_0x002f:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L_0x003e
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                r6 = r4
                goto L_0x0090
            L_0x003e:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L_0x004e
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L_0x0090
            L_0x004e:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L_0x008f
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L_0x0090
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L_0x008d
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L_0x0090
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L_0x008b
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L_0x0090
            L_0x0085:
                long r0 = r10.readRawVarint64SlowPath()
                int r1 = (int) r0
                return r1
            L_0x008b:
                r6 = r4
                goto L_0x0090
            L_0x008d:
                r6 = r4
                goto L_0x0090
            L_0x008f:
                r6 = r4
            L_0x0090:
                r10.pos = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() throws IOException {
            if (remaining() >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            int i = 0;
            while (i < 10) {
                long j = this.pos;
                this.pos = 1 + j;
                if (UnsafeUtil.getByte(j) < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            int i = 0;
            while (i < 10) {
                if (readRawByte() < 0) {
                    i++;
                } else {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public long readRawVarint64() throws IOException {
            long j;
            long j2;
            long j3 = this.pos;
            if (this.limit != j3) {
                long j4 = j3 + 1;
                byte b = UnsafeUtil.getByte(j3);
                if (b >= 0) {
                    this.pos = j4;
                    return (long) b;
                } else if (this.limit - j4 >= 9) {
                    long j5 = j4 + 1;
                    byte b2 = b ^ (UnsafeUtil.getByte(j4) << 7);
                    if (b2 < 0) {
                        j2 = (long) (b2 ^ Byte.MIN_VALUE);
                        j = j5;
                    } else {
                        j = j5 + 1;
                        byte b3 = b2 ^ (UnsafeUtil.getByte(j5) << Ascii.SO);
                        if (b3 >= 0) {
                            j2 = (long) (b3 ^ 16256);
                        } else {
                            long j6 = j + 1;
                            byte b4 = b3 ^ (UnsafeUtil.getByte(j) << Ascii.NAK);
                            if (b4 < 0) {
                                j2 = (long) (b4 ^ -2080896);
                                j = j6;
                            } else {
                                j = j6 + 1;
                                long j7 = ((long) b4) ^ (((long) UnsafeUtil.getByte(j6)) << 28);
                                if (j7 >= 0) {
                                    j2 = j7 ^ 266354560;
                                } else {
                                    long j8 = j + 1;
                                    long j9 = j7 ^ (((long) UnsafeUtil.getByte(j)) << 35);
                                    if (j9 < 0) {
                                        j2 = j9 ^ -34093383808L;
                                        j = j8;
                                    } else {
                                        j = j8 + 1;
                                        long j10 = j9 ^ (((long) UnsafeUtil.getByte(j8)) << 42);
                                        if (j10 >= 0) {
                                            j2 = j10 ^ 4363953127296L;
                                        } else {
                                            long j11 = j + 1;
                                            long j12 = j10 ^ (((long) UnsafeUtil.getByte(j)) << 49);
                                            if (j12 < 0) {
                                                j2 = j12 ^ -558586000294016L;
                                                j = j11;
                                            } else {
                                                j = j11 + 1;
                                                j2 = (j12 ^ (((long) UnsafeUtil.getByte(j11)) << 56)) ^ 71499008037633920L;
                                                if (j2 < 0) {
                                                    long j13 = 1 + j;
                                                    if (((long) UnsafeUtil.getByte(j)) >= 0) {
                                                        j = j13;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = j;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        /* access modifiers changed from: 0000 */
        public long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= ((long) (readRawByte & Byte.MAX_VALUE)) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        public int readRawLittleEndian32() throws IOException {
            long j = this.pos;
            if (this.limit - j >= 4) {
                this.pos = 4 + j;
                return ((UnsafeUtil.getByte(j + 3) & 255) << Ascii.CAN) | (UnsafeUtil.getByte(j) & 255) | ((UnsafeUtil.getByte(1 + j) & 255) << 8) | ((UnsafeUtil.getByte(2 + j) & 255) << Ascii.DLE);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public long readRawLittleEndian64() throws IOException {
            long j = this.pos;
            if (this.limit - j >= 8) {
                this.pos = 8 + j;
                return ((((long) UnsafeUtil.getByte(j + 7)) & 255) << 56) | (((long) UnsafeUtil.getByte(j)) & 255) | ((((long) UnsafeUtil.getByte(1 + j)) & 255) << 8) | ((((long) UnsafeUtil.getByte(2 + j)) & 255) << 16) | ((((long) UnsafeUtil.getByte(3 + j)) & 255) << 24) | ((((long) UnsafeUtil.getByte(4 + j)) & 255) << 32) | ((((long) UnsafeUtil.getByte(5 + j)) & 255) << 40) | ((((long) UnsafeUtil.getByte(6 + j)) & 255) << 48);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                int i2 = this.currentLimit;
                if (totalBytesRead <= i2) {
                    this.currentLimit = totalBytesRead;
                    recomputeBufferSizeAfterLimit();
                    return i2;
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            throw InvalidProtocolBufferException.negativeSize();
        }

        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        public int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        public byte readRawByte() throws IOException {
            long j = this.pos;
            if (j != this.limit) {
                this.pos = 1 + j;
                return UnsafeUtil.getByte(j);
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        public byte[] readRawBytes(int i) throws IOException {
            if (i >= 0 && i <= remaining()) {
                byte[] bArr = new byte[i];
                long j = this.pos;
                long j2 = (long) i;
                slice(j, j + j2).get(bArr);
                this.pos += j2;
                return bArr;
            } else if (i > 0) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            } else {
                throw InvalidProtocolBufferException.negativeSize();
            }
        }

        public void skipRawBytes(int i) throws IOException {
            if (i >= 0 && i <= remaining()) {
                this.pos += (long) i;
            } else if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void recomputeBufferSizeAfterLimit() {
            this.limit += (long) this.bufferSizeAfterLimit;
            long j = this.limit;
            int i = (int) (j - this.startPos);
            int i2 = this.currentLimit;
            if (i > i2) {
                this.bufferSizeAfterLimit = i - i2;
                this.limit = j - ((long) this.bufferSizeAfterLimit);
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private int remaining() {
            return (int) (this.limit - this.pos);
        }

        private int bufferPos(long j) {
            return (int) (j - this.address);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0040, code lost:
            throw r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x002f, code lost:
            r4 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0035, code lost:
            throw com.google.protobuf.InvalidProtocolBufferException.truncatedMessage();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0036, code lost:
            r3.buffer.position(r0);
            r3.buffer.limit(r1);
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0031 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.nio.ByteBuffer slice(long r4, long r6) throws java.io.IOException {
            /*
                r3 = this;
                java.nio.ByteBuffer r0 = r3.buffer
                int r0 = r0.position()
                java.nio.ByteBuffer r1 = r3.buffer
                int r1 = r1.limit()
                java.nio.ByteBuffer r2 = r3.buffer     // Catch:{ IllegalArgumentException -> 0x0031 }
                int r4 = r3.bufferPos(r4)     // Catch:{ IllegalArgumentException -> 0x0031 }
                r2.position(r4)     // Catch:{ IllegalArgumentException -> 0x0031 }
                java.nio.ByteBuffer r4 = r3.buffer     // Catch:{ IllegalArgumentException -> 0x0031 }
                int r5 = r3.bufferPos(r6)     // Catch:{ IllegalArgumentException -> 0x0031 }
                r4.limit(r5)     // Catch:{ IllegalArgumentException -> 0x0031 }
                java.nio.ByteBuffer r4 = r3.buffer     // Catch:{ IllegalArgumentException -> 0x0031 }
                java.nio.ByteBuffer r4 = r4.slice()     // Catch:{ IllegalArgumentException -> 0x0031 }
                java.nio.ByteBuffer r5 = r3.buffer
                r5.position(r0)
                java.nio.ByteBuffer r5 = r3.buffer
                r5.limit(r1)
                return r4
            L_0x002f:
                r4 = move-exception
                goto L_0x0036
            L_0x0031:
                com.google.protobuf.InvalidProtocolBufferException r4 = com.google.protobuf.InvalidProtocolBufferException.truncatedMessage()     // Catch:{ all -> 0x002f }
                throw r4     // Catch:{ all -> 0x002f }
            L_0x0036:
                java.nio.ByteBuffer r5 = r3.buffer
                r5.position(r0)
                java.nio.ByteBuffer r5 = r3.buffer
                r5.limit(r1)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.slice(long, long):java.nio.ByteBuffer");
        }
    }

    public static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract void checkLastTagWas(int i) throws InvalidProtocolBufferException;

    public abstract void enableAliasing(boolean z);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd() throws IOException;

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i) throws InvalidProtocolBufferException;

    public abstract boolean readBool() throws IOException;

    public abstract byte[] readByteArray() throws IOException;

    public abstract ByteBuffer readByteBuffer() throws IOException;

    public abstract ByteString readBytes() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract int readEnum() throws IOException;

    public abstract int readFixed32() throws IOException;

    public abstract long readFixed64() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readGroup(int i, Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract int readInt32() throws IOException;

    public abstract long readInt64() throws IOException;

    public abstract <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readMessage(Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract byte readRawByte() throws IOException;

    public abstract byte[] readRawBytes(int i) throws IOException;

    public abstract int readRawLittleEndian32() throws IOException;

    public abstract long readRawLittleEndian64() throws IOException;

    public abstract int readRawVarint32() throws IOException;

    public abstract long readRawVarint64() throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract long readRawVarint64SlowPath() throws IOException;

    public abstract int readSFixed32() throws IOException;

    public abstract long readSFixed64() throws IOException;

    public abstract int readSInt32() throws IOException;

    public abstract long readSInt64() throws IOException;

    public abstract String readString() throws IOException;

    public abstract String readStringRequireUtf8() throws IOException;

    public abstract int readTag() throws IOException;

    public abstract int readUInt32() throws IOException;

    public abstract long readUInt64() throws IOException;

    @Deprecated
    public abstract void readUnknownGroup(int i, Builder builder) throws IOException;

    public abstract void resetSizeCounter();

    public abstract boolean skipField(int i) throws IOException;

    @Deprecated
    public abstract boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipMessage() throws IOException;

    public abstract void skipMessage(CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipRawBytes(int i) throws IOException;

    public static CodedInputStream newInstance(InputStream inputStream) {
        return newInstance(inputStream, 4096);
    }

    static CodedInputStream newInstance(InputStream inputStream, int i) {
        if (inputStream == null) {
            return newInstance(Internal.EMPTY_BYTE_ARRAY);
        }
        return new StreamDecoder(inputStream, i);
    }

    public static CodedInputStream newInstance(Iterable<ByteBuffer> iterable) {
        if (!UnsafeDirectNioDecoder.isSupported()) {
            return newInstance((InputStream) new IterableByteBufferInputStream(iterable));
        }
        return newInstance(iterable, false);
    }

    static CodedInputStream newInstance(Iterable<ByteBuffer> iterable, boolean z) {
        boolean z2 = false;
        int i = 0;
        for (ByteBuffer byteBuffer : iterable) {
            i += byteBuffer.remaining();
            z2 = byteBuffer.hasArray() ? z2 | true : byteBuffer.isDirect() ? z2 | true : z2 | true;
        }
        if (z2) {
            return new IterableDirectByteBufferDecoder(iterable, i, z);
        }
        return newInstance((InputStream) new IterableByteBufferInputStream(iterable));
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        return newInstance(bArr, i, i2, false);
    }

    static CodedInputStream newInstance(byte[] bArr, int i, int i2, boolean z) {
        ArrayDecoder arrayDecoder = new ArrayDecoder(bArr, i, i2, z);
        try {
            arrayDecoder.pushLimit(i2);
            return arrayDecoder;
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        return newInstance(byteBuffer, false);
    }

    static CodedInputStream newInstance(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && UnsafeDirectNioDecoder.isSupported()) {
            return new UnsafeDirectNioDecoder(byteBuffer, z);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.duplicate().get(bArr);
        return newInstance(bArr, 0, bArr.length, true);
    }

    private CodedInputStream() {
        this.recursionLimit = 100;
        this.sizeLimit = Integer.MAX_VALUE;
        this.explicitDiscardUnknownFields = false;
    }

    public final int setRecursionLimit(int i) {
        if (i >= 0) {
            int i2 = this.recursionLimit;
            this.recursionLimit = i;
            return i2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Recursion limit cannot be negative: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    public final int setSizeLimit(int i) {
        if (i >= 0) {
            int i2 = this.sizeLimit;
            this.sizeLimit = i;
            return i2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Size limit cannot be negative: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    static void setProto3DiscardUnknownsByDefaultForTest() {
        proto3DiscardUnknownFieldsDefault = true;
    }

    static void setProto3KeepUnknownsByDefaultForTest() {
        proto3DiscardUnknownFieldsDefault = false;
    }

    static boolean getProto3DiscardUnknownFieldsDefault() {
        return proto3DiscardUnknownFieldsDefault;
    }

    /* access modifiers changed from: 0000 */
    public final void discardUnknownFields() {
        this.explicitDiscardUnknownFields = true;
    }

    /* access modifiers changed from: 0000 */
    public final void unsetDiscardUnknownFields() {
        this.explicitDiscardUnknownFields = false;
    }

    /* access modifiers changed from: 0000 */
    public final boolean shouldDiscardUnknownFields() {
        return this.explicitDiscardUnknownFields;
    }

    /* access modifiers changed from: 0000 */
    public final boolean shouldDiscardUnknownFieldsProto3() {
        if (this.explicitDiscardUnknownFields) {
            return true;
        }
        return proto3DiscardUnknownFieldsDefault;
    }

    public static int readRawVarint32(int i, InputStream inputStream) throws IOException {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & Statements.GetOwnedFoodIdsDateDescending;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read != -1) {
                i2 |= (read & Statements.GetOwnedFoodIdsDateDescending) << i3;
                if ((read & 128) == 0) {
                    return i2;
                }
                i3 += 7;
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            } else if ((read2 & 128) == 0) {
                return i2;
            } else {
                i3 += 7;
            }
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    static int readRawVarint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return readRawVarint32(read, inputStream);
        }
        throw InvalidProtocolBufferException.truncatedMessage();
    }
}
