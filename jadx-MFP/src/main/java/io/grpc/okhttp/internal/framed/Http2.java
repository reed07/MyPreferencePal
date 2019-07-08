package io.grpc.okhttp.internal.framed;

import com.brightcove.player.C;
import com.google.common.base.Ascii;
import io.grpc.okhttp.internal.framed.FrameReader.Handler;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

public final class Http2 implements Variant {
    /* access modifiers changed from: private */
    public static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(FrameLogger.class.getName());

    static final class ContinuationSource implements Source {
        byte flags;
        int left;
        int length;
        short padding;
        private final BufferedSource source;
        int streamId;

        public void close() throws IOException {
        }

        public ContinuationSource(BufferedSource bufferedSource) {
            this.source = bufferedSource;
        }

        public long read(Buffer buffer, long j) throws IOException {
            while (true) {
                int i = this.left;
                if (i == 0) {
                    this.source.skip((long) this.padding);
                    this.padding = 0;
                    if ((this.flags & 4) != 0) {
                        return -1;
                    }
                    readContinuationHeader();
                } else {
                    long read = this.source.read(buffer, Math.min(j, (long) i));
                    if (read == -1) {
                        return -1;
                    }
                    this.left -= (int) read;
                    return read;
                }
            }
        }

        public Timeout timeout() {
            return this.source.timeout();
        }

        private void readContinuationHeader() throws IOException {
            int i = this.streamId;
            int access$300 = Http2.readMedium(this.source);
            this.left = access$300;
            this.length = access$300;
            byte readByte = (byte) (this.source.readByte() & 255);
            this.flags = (byte) (this.source.readByte() & 255);
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(true, this.streamId, this.length, readByte, this.flags));
            }
            this.streamId = this.source.readInt() & Integer.MAX_VALUE;
            if (readByte != 9) {
                throw Http2.ioException("%s != TYPE_CONTINUATION", Byte.valueOf(readByte));
            } else if (this.streamId != i) {
                throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
            }
        }
    }

    static final class FrameLogger {
        private static final String[] BINARY = new String[256];
        private static final String[] FLAGS = new String[64];
        private static final String[] TYPES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

        FrameLogger() {
        }

        static String formatHeader(boolean z, int i, int i2, byte b, byte b2) {
            String[] strArr = TYPES;
            String format = b < strArr.length ? strArr[b] : String.format("0x%02x", new Object[]{Byte.valueOf(b)});
            String formatFlags = formatFlags(b, b2);
            String str = "%s 0x%08x %5d %-13s %s";
            Object[] objArr = new Object[5];
            objArr[0] = z ? "<<" : ">>";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = Integer.valueOf(i2);
            objArr[3] = format;
            objArr[4] = formatFlags;
            return String.format(str, objArr);
        }

        static String formatFlags(byte b, byte b2) {
            String str;
            if (b2 == 0) {
                return "";
            }
            switch (b) {
                case 2:
                case 3:
                case 7:
                case 8:
                    return BINARY[b2];
                case 4:
                case 6:
                    return b2 == 1 ? "ACK" : BINARY[b2];
                default:
                    String[] strArr = FLAGS;
                    if (b2 < strArr.length) {
                        str = strArr[b2];
                    } else {
                        str = BINARY[b2];
                    }
                    if (b != 5 || (b2 & 4) == 0) {
                        return (b != 0 || (b2 & 32) == 0) ? str : str.replace("PRIORITY", "COMPRESSED");
                    }
                    return str.replace("HEADERS", "PUSH_PROMISE");
            }
        }

        static {
            int[] iArr;
            int i = 0;
            int i2 = 0;
            while (true) {
                String[] strArr = BINARY;
                if (i2 >= strArr.length) {
                    break;
                }
                strArr[i2] = String.format("%8s", new Object[]{Integer.toBinaryString(i2)}).replace(' ', '0');
                i2++;
            }
            String[] strArr2 = FLAGS;
            strArr2[0] = "";
            strArr2[1] = "END_STREAM";
            int[] iArr2 = {1};
            strArr2[8] = "PADDED";
            for (int i3 : iArr2) {
                String[] strArr3 = FLAGS;
                int i4 = i3 | 8;
                StringBuilder sb = new StringBuilder();
                sb.append(FLAGS[i3]);
                sb.append("|PADDED");
                strArr3[i4] = sb.toString();
            }
            String[] strArr4 = FLAGS;
            strArr4[4] = "END_HEADERS";
            strArr4[32] = "PRIORITY";
            strArr4[36] = "END_HEADERS|PRIORITY";
            for (int i5 : new int[]{4, 32, 36}) {
                for (int i6 : iArr2) {
                    String[] strArr5 = FLAGS;
                    int i7 = i6 | i5;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(FLAGS[i6]);
                    sb2.append('|');
                    sb2.append(FLAGS[i5]);
                    strArr5[i7] = sb2.toString();
                    String[] strArr6 = FLAGS;
                    int i8 = i7 | 8;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(FLAGS[i6]);
                    sb3.append('|');
                    sb3.append(FLAGS[i5]);
                    sb3.append("|PADDED");
                    strArr6[i8] = sb3.toString();
                }
            }
            while (true) {
                String[] strArr7 = FLAGS;
                if (i < strArr7.length) {
                    if (strArr7[i] == null) {
                        strArr7[i] = BINARY[i];
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    static final class Reader implements FrameReader {
        private final boolean client;
        private final ContinuationSource continuation = new ContinuationSource(this.source);
        final Reader hpackReader;
        private final BufferedSource source;

        Reader(BufferedSource bufferedSource, int i, boolean z) {
            this.source = bufferedSource;
            this.client = z;
            this.hpackReader = new Reader(i, this.continuation);
        }

        public boolean nextFrame(Handler handler) throws IOException {
            try {
                this.source.require(9);
                int access$300 = Http2.readMedium(this.source);
                if (access$300 < 0 || access$300 > 16384) {
                    throw Http2.ioException("FRAME_SIZE_ERROR: %s", Integer.valueOf(access$300));
                }
                byte readByte = (byte) (this.source.readByte() & 255);
                byte readByte2 = (byte) (this.source.readByte() & 255);
                int readInt = this.source.readInt() & Integer.MAX_VALUE;
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(FrameLogger.formatHeader(true, readInt, access$300, readByte, readByte2));
                }
                switch (readByte) {
                    case 0:
                        readData(handler, access$300, readByte2, readInt);
                        break;
                    case 1:
                        readHeaders(handler, access$300, readByte2, readInt);
                        break;
                    case 2:
                        readPriority(handler, access$300, readByte2, readInt);
                        break;
                    case 3:
                        readRstStream(handler, access$300, readByte2, readInt);
                        break;
                    case 4:
                        readSettings(handler, access$300, readByte2, readInt);
                        break;
                    case 5:
                        readPushPromise(handler, access$300, readByte2, readInt);
                        break;
                    case 6:
                        readPing(handler, access$300, readByte2, readInt);
                        break;
                    case 7:
                        readGoAway(handler, access$300, readByte2, readInt);
                        break;
                    case 8:
                        readWindowUpdate(handler, access$300, readByte2, readInt);
                        break;
                    default:
                        this.source.skip((long) access$300);
                        break;
                }
                return true;
            } catch (IOException unused) {
                return false;
            }
        }

        private void readHeaders(Handler handler, int i, byte b, int i2) throws IOException {
            short s = 0;
            if (i2 != 0) {
                boolean z = (b & 1) != 0;
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & 255);
                }
                if ((b & 32) != 0) {
                    readPriority(handler, i2);
                    i -= 5;
                }
                handler.headers(false, z, i2, -1, readHeaderBlock(Http2.lengthWithoutPadding(i, b, s), s, b, i2), HeadersMode.HTTP_20_HEADERS);
                return;
            }
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
        }

        private List<Header> readHeaderBlock(int i, short s, byte b, int i2) throws IOException {
            ContinuationSource continuationSource = this.continuation;
            continuationSource.left = i;
            continuationSource.length = i;
            continuationSource.padding = s;
            continuationSource.flags = b;
            continuationSource.streamId = i2;
            this.hpackReader.readHeaders();
            return this.hpackReader.getAndResetHeaderList();
        }

        private void readData(Handler handler, int i, byte b, int i2) throws IOException {
            boolean z = true;
            short s = 0;
            boolean z2 = (b & 1) != 0;
            if ((b & 32) == 0) {
                z = false;
            }
            if (!z) {
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & 255);
                }
                handler.data(z2, i2, this.source, Http2.lengthWithoutPadding(i, b, s));
                this.source.skip((long) s);
                return;
            }
            throw Http2.ioException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        }

        private void readPriority(Handler handler, int i, byte b, int i2) throws IOException {
            if (i != 5) {
                throw Http2.ioException("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
            } else if (i2 != 0) {
                readPriority(handler, i2);
            } else {
                throw Http2.ioException("TYPE_PRIORITY streamId == 0", new Object[0]);
            }
        }

        private void readPriority(Handler handler, int i) throws IOException {
            int readInt = this.source.readInt();
            handler.priority(i, readInt & Integer.MAX_VALUE, (this.source.readByte() & 255) + 1, (Integer.MIN_VALUE & readInt) != 0);
        }

        private void readRstStream(Handler handler, int i, byte b, int i2) throws IOException {
            if (i != 4) {
                throw Http2.ioException("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
            } else if (i2 != 0) {
                int readInt = this.source.readInt();
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(readInt);
                if (fromHttp2 != null) {
                    handler.rstStream(i2, fromHttp2);
                } else {
                    throw Http2.ioException("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(readInt));
                }
            } else {
                throw Http2.ioException("TYPE_RST_STREAM streamId == 0", new Object[0]);
            }
        }

        private void readSettings(Handler handler, int i, byte b, int i2) throws IOException {
            if (i2 != 0) {
                throw Http2.ioException("TYPE_SETTINGS streamId != 0", new Object[0]);
            } else if ((b & 1) != 0) {
                if (i == 0) {
                    handler.ackSettings();
                } else {
                    throw Http2.ioException("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                }
            } else if (i % 6 == 0) {
                Settings settings = new Settings();
                for (int i3 = 0; i3 < i; i3 += 6) {
                    short readShort = this.source.readShort();
                    int readInt = this.source.readInt();
                    switch (readShort) {
                        case 1:
                        case 6:
                            break;
                        case 2:
                            if (!(readInt == 0 || readInt == 1)) {
                                throw Http2.ioException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                            }
                        case 3:
                            readShort = 4;
                            break;
                        case 4:
                            readShort = 7;
                            if (readInt < 0) {
                                throw Http2.ioException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                            }
                            break;
                        case 5:
                            if (readInt < 16384 || readInt > 16777215) {
                                throw Http2.ioException("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(readInt));
                            }
                    }
                    settings.set(readShort, 0, readInt);
                }
                handler.settings(false, settings);
                if (settings.getHeaderTableSize() >= 0) {
                    this.hpackReader.headerTableSizeSetting(settings.getHeaderTableSize());
                }
            } else {
                throw Http2.ioException("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
            }
        }

        private void readPushPromise(Handler handler, int i, byte b, int i2) throws IOException {
            short s = 0;
            if (i2 != 0) {
                if ((b & 8) != 0) {
                    s = (short) (this.source.readByte() & 255);
                }
                handler.pushPromise(i2, this.source.readInt() & Integer.MAX_VALUE, readHeaderBlock(Http2.lengthWithoutPadding(i - 4, b, s), s, b, i2));
                return;
            }
            throw Http2.ioException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
        }

        private void readPing(Handler handler, int i, byte b, int i2) throws IOException {
            boolean z = false;
            if (i != 8) {
                throw Http2.ioException("TYPE_PING length != 8: %s", Integer.valueOf(i));
            } else if (i2 == 0) {
                int readInt = this.source.readInt();
                int readInt2 = this.source.readInt();
                if ((b & 1) != 0) {
                    z = true;
                }
                handler.ping(z, readInt, readInt2);
            } else {
                throw Http2.ioException("TYPE_PING streamId != 0", new Object[0]);
            }
        }

        private void readGoAway(Handler handler, int i, byte b, int i2) throws IOException {
            if (i < 8) {
                throw Http2.ioException("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
            } else if (i2 == 0) {
                int readInt = this.source.readInt();
                int readInt2 = this.source.readInt();
                int i3 = i - 8;
                ErrorCode fromHttp2 = ErrorCode.fromHttp2(readInt2);
                if (fromHttp2 != null) {
                    ByteString byteString = ByteString.EMPTY;
                    if (i3 > 0) {
                        byteString = this.source.readByteString((long) i3);
                    }
                    handler.goAway(readInt, fromHttp2, byteString);
                    return;
                }
                throw Http2.ioException("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(readInt2));
            } else {
                throw Http2.ioException("TYPE_GOAWAY streamId != 0", new Object[0]);
            }
        }

        private void readWindowUpdate(Handler handler, int i, byte b, int i2) throws IOException {
            if (i == 4) {
                long readInt = ((long) this.source.readInt()) & 2147483647L;
                if (readInt != 0) {
                    handler.windowUpdate(i2, readInt);
                } else {
                    throw Http2.ioException("windowSizeIncrement was 0", Long.valueOf(readInt));
                }
            } else {
                throw Http2.ioException("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
            }
        }

        public void close() throws IOException {
            this.source.close();
        }
    }

    static final class Writer implements FrameWriter {
        private final boolean client;
        private boolean closed;
        private final Buffer hpackBuffer = new Buffer();
        private final Writer hpackWriter = new Writer(this.hpackBuffer);
        private int maxFrameSize = C.DASH_ROLE_CAPTION_FLAG;
        private final BufferedSink sink;

        Writer(BufferedSink bufferedSink, boolean z) {
            this.sink = bufferedSink;
            this.client = z;
        }

        public synchronized void flush() throws IOException {
            if (!this.closed) {
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        public synchronized void ackSettings(Settings settings) throws IOException {
            if (!this.closed) {
                this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
                frameHeader(0, 0, 4, 1);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        public synchronized void connectionPreface() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            } else if (this.client) {
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.fine(String.format(">> CONNECTION %s", new Object[]{Http2.CONNECTION_PREFACE.hex()}));
                }
                this.sink.write(Http2.CONNECTION_PREFACE.toByteArray());
                this.sink.flush();
            }
        }

        public synchronized void synStream(boolean z, boolean z2, int i, int i2, List<Header> list) throws IOException {
            if (!z2) {
                try {
                    if (!this.closed) {
                        headers(z, i, list);
                    } else {
                        throw new IOException("closed");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                throw new UnsupportedOperationException();
            }
        }

        /* access modifiers changed from: 0000 */
        public void headers(boolean z, int i, List<Header> list) throws IOException {
            if (!this.closed) {
                this.hpackWriter.writeHeaders(list);
                long size = this.hpackBuffer.size();
                int min = (int) Math.min((long) this.maxFrameSize, size);
                long j = (long) min;
                int i2 = (size > j ? 1 : (size == j ? 0 : -1));
                byte b = i2 == 0 ? (byte) 4 : 0;
                if (z) {
                    b = (byte) (b | 1);
                }
                frameHeader(i, min, 1, b);
                this.sink.write(this.hpackBuffer, j);
                if (i2 > 0) {
                    writeContinuationFrames(i, size - j);
                    return;
                }
                return;
            }
            throw new IOException("closed");
        }

        private void writeContinuationFrames(int i, long j) throws IOException {
            while (j > 0) {
                int min = (int) Math.min((long) this.maxFrameSize, j);
                long j2 = (long) min;
                j -= j2;
                frameHeader(i, min, 9, j == 0 ? (byte) 4 : 0);
                this.sink.write(this.hpackBuffer, j2);
            }
        }

        public synchronized void rstStream(int i, ErrorCode errorCode) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            } else if (errorCode.httpCode != -1) {
                frameHeader(i, 4, 3, 0);
                this.sink.writeInt(errorCode.httpCode);
                this.sink.flush();
            } else {
                throw new IllegalArgumentException();
            }
        }

        public int maxDataLength() {
            return this.maxFrameSize;
        }

        public synchronized void data(boolean z, int i, Buffer buffer, int i2) throws IOException {
            if (!this.closed) {
                byte b = 0;
                if (z) {
                    b = (byte) 1;
                }
                dataFrame(i, b, buffer, i2);
            } else {
                throw new IOException("closed");
            }
        }

        /* access modifiers changed from: 0000 */
        public void dataFrame(int i, byte b, Buffer buffer, int i2) throws IOException {
            frameHeader(i, i2, 0, b);
            if (i2 > 0) {
                this.sink.write(buffer, (long) i2);
            }
        }

        public synchronized void settings(Settings settings) throws IOException {
            if (!this.closed) {
                int i = 0;
                frameHeader(0, settings.size() * 6, 4, 0);
                while (i < 10) {
                    if (settings.isSet(i)) {
                        int i2 = i == 4 ? 3 : i == 7 ? 4 : i;
                        this.sink.writeShort(i2);
                        this.sink.writeInt(settings.get(i));
                    }
                    i++;
                }
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        public synchronized void ping(boolean z, int i, int i2) throws IOException {
            if (!this.closed) {
                frameHeader(0, 8, 6, z ? (byte) 1 : 0);
                this.sink.writeInt(i);
                this.sink.writeInt(i2);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        public synchronized void goAway(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            } else if (errorCode.httpCode != -1) {
                frameHeader(0, bArr.length + 8, 7, 0);
                this.sink.writeInt(i);
                this.sink.writeInt(errorCode.httpCode);
                if (bArr.length > 0) {
                    this.sink.write(bArr);
                }
                this.sink.flush();
            } else {
                throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
            }
        }

        public synchronized void windowUpdate(int i, long j) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            } else if (j == 0 || j > 2147483647L) {
                throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            } else {
                frameHeader(i, 4, 8, 0);
                this.sink.writeInt((int) j);
                this.sink.flush();
            }
        }

        public synchronized void close() throws IOException {
            this.closed = true;
            this.sink.close();
        }

        /* access modifiers changed from: 0000 */
        public void frameHeader(int i, int i2, byte b, byte b2) throws IOException {
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.fine(FrameLogger.formatHeader(false, i, i2, b, b2));
            }
            int i3 = this.maxFrameSize;
            if (i2 > i3) {
                throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
            } else if ((Integer.MIN_VALUE & i) == 0) {
                Http2.writeMedium(this.sink, i2);
                this.sink.writeByte(b & 255);
                this.sink.writeByte(b2 & 255);
                this.sink.writeInt(i & Integer.MAX_VALUE);
            } else {
                throw Http2.illegalArgument("reserved bit set: %s", Integer.valueOf(i));
            }
        }
    }

    public FrameReader newReader(BufferedSource bufferedSource, boolean z) {
        return new Reader(bufferedSource, 4096, z);
    }

    public FrameWriter newWriter(BufferedSink bufferedSink, boolean z) {
        return new Writer(bufferedSink, z);
    }

    /* access modifiers changed from: private */
    public static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    /* access modifiers changed from: private */
    public static IOException ioException(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(str, objArr));
    }

    /* access modifiers changed from: private */
    public static int lengthWithoutPadding(int i, byte b, short s) throws IOException {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        throw ioException("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
    }

    /* access modifiers changed from: private */
    public static int readMedium(BufferedSource bufferedSource) throws IOException {
        return (bufferedSource.readByte() & 255) | ((bufferedSource.readByte() & 255) << Ascii.DLE) | ((bufferedSource.readByte() & 255) << 8);
    }

    /* access modifiers changed from: private */
    public static void writeMedium(BufferedSink bufferedSink, int i) throws IOException {
        bufferedSink.writeByte((i >>> 16) & 255);
        bufferedSink.writeByte((i >>> 8) & 255);
        bufferedSink.writeByte(i & 255);
    }
}
