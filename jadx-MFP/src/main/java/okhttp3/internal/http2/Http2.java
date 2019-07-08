package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.Util;
import okio.ByteString;

public final class Http2 {
    static final String[] BINARY = new String[256];
    static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
    static final String[] FLAGS = new String[64];
    private static final String[] FRAME_NAMES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    static {
        int[] iArr;
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = BINARY;
            if (i2 >= strArr.length) {
                break;
            }
            strArr[i2] = Util.format("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
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

    private Http2() {
    }

    static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(Util.format(str, objArr));
    }

    static IOException ioException(String str, Object... objArr) throws IOException {
        throw new IOException(Util.format(str, objArr));
    }

    static String frameLog(boolean z, int i, int i2, byte b, byte b2) {
        String[] strArr = FRAME_NAMES;
        String format = b < strArr.length ? strArr[b] : Util.format("0x%02x", Byte.valueOf(b));
        String formatFlags = formatFlags(b, b2);
        String str = "%s 0x%08x %5d %-13s %s";
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = format;
        objArr[4] = formatFlags;
        return Util.format(str, objArr);
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
}
