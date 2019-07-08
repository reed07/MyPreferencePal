package org.apache.commons.io;

import com.google.common.base.Ascii;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class EndianUtils {
    public static long readSwappedLong(byte[] bArr, int i) {
        return (((long) (((((bArr[i + 4] & 255) << 0) + ((bArr[i + 5] & 255) << 8)) + ((bArr[i + 6] & 255) << Ascii.DLE)) + ((bArr[i + 7] & 255) << Ascii.CAN))) << 32) + (((long) (((bArr[i + 0] & 255) << 0) + ((bArr[i + 1] & 255) << 8) + ((bArr[i + 2] & 255) << Ascii.DLE) + ((bArr[i + 3] & 255) << Ascii.CAN))) & 4294967295L);
    }

    public static short readSwappedShort(InputStream inputStream) throws IOException {
        return (short) (((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8));
    }

    public static int readSwappedUnsignedShort(InputStream inputStream) throws IOException {
        return ((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8);
    }

    public static int readSwappedInteger(InputStream inputStream) throws IOException {
        return ((read(inputStream) & 255) << 0) + ((read(inputStream) & 255) << 8) + ((read(inputStream) & 255) << 16) + ((read(inputStream) & 255) << 24);
    }

    public static long readSwappedLong(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[8];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) read(inputStream);
        }
        return readSwappedLong(bArr, 0);
    }

    public static float readSwappedFloat(InputStream inputStream) throws IOException {
        return Float.intBitsToFloat(readSwappedInteger(inputStream));
    }

    public static double readSwappedDouble(InputStream inputStream) throws IOException {
        return Double.longBitsToDouble(readSwappedLong(inputStream));
    }

    private static int read(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (-1 != read) {
            return read;
        }
        throw new EOFException("Unexpected EOF reached");
    }
}
