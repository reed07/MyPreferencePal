package org.apache.sanselan.common;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;

public class BinaryFileFunctions implements BinaryConstants {
    protected boolean debug = false;

    public final boolean getDebug() {
        return this.debug;
    }

    public final void debugNumber(String str, int i) {
        debugNumber(str, i, 1);
    }

    public final void debugNumber(String str, int i, int i2) {
        PrintWriter printWriter = new PrintWriter(System.out);
        debugNumber(printWriter, str, i, i2);
        printWriter.flush();
    }

    public final void debugNumber(PrintWriter printWriter, String str, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(i);
        sb.append(" (");
        printWriter.print(sb.toString());
        int i3 = i;
        for (int i4 = 0; i4 < i2; i4++) {
            if (i4 > 0) {
                printWriter.print(",");
            }
            int i5 = i3 & 255;
            StringBuilder sb2 = new StringBuilder();
            sb2.append((char) i5);
            sb2.append(" [");
            sb2.append(i5);
            sb2.append("]");
            printWriter.print(sb2.toString());
            i3 >>= 8;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(") [0x");
        sb3.append(Integer.toHexString(i));
        sb3.append(", ");
        sb3.append(Integer.toBinaryString(i));
        sb3.append("]");
        printWriter.println(sb3.toString());
        printWriter.flush();
    }

    public final boolean startsWith(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr == null || bArr2.length > bArr.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr2[i] != bArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final byte[] readBytes(InputStream inputStream, int i) throws ImageReadException, IOException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) inputStream.read();
        }
        return bArr;
    }

    public final void readAndVerifyBytes(InputStream inputStream, byte[] bArr, String str) throws ImageReadException, IOException {
        int i = 0;
        while (i < bArr.length) {
            int read = inputStream.read();
            byte b = (byte) (read & 255);
            if (read < 0) {
                throw new ImageReadException("Unexpected EOF.");
            } else if (b == bArr[i]) {
                i++;
            } else {
                throw new ImageReadException(str);
            }
        }
    }

    public final void skipBytes(InputStream inputStream, int i, String str) throws IOException {
        long j = 0;
        while (true) {
            long j2 = (long) i;
            if (j2 != j) {
                long skip = inputStream.skip(j2 - j);
                if (skip >= 1) {
                    j += skip;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(" (");
                    sb.append(skip);
                    sb.append(")");
                    throw new IOException(sb.toString());
                }
            } else {
                return;
            }
        }
    }

    public final byte readByte(String str, InputStream inputStream, String str2) throws ImageReadException, IOException {
        int read = inputStream.read();
        if (read >= 0) {
            if (this.debug) {
                debugNumber(str, read);
            }
            return (byte) (read & 255);
        }
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(read);
        printStream.println(sb.toString());
        throw new IOException(str2);
    }

    /* access modifiers changed from: protected */
    public final RationalNumber[] convertByteArrayToRationalArray(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 8) + i;
        if (bArr.length < i4) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": expected length: ");
            sb.append(i4);
            sb.append(", actual length: ");
            sb.append(bArr.length);
            printStream.println(sb.toString());
            return null;
        }
        RationalNumber[] rationalNumberArr = new RationalNumber[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            rationalNumberArr[i5] = convertByteArrayToRational(str, bArr, (i5 * 8) + i, i3);
        }
        return rationalNumberArr;
    }

    /* access modifiers changed from: protected */
    public final RationalNumber convertByteArrayToRational(String str, byte[] bArr, int i) {
        return convertByteArrayToRational(str, bArr, 0, i);
    }

    /* access modifiers changed from: protected */
    public final RationalNumber convertByteArrayToRational(String str, byte[] bArr, int i, int i2) {
        return new RationalNumber(convertByteArrayToInt(str, bArr, i + 0, i2), convertByteArrayToInt(str, bArr, i + 4, i2));
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToInt(String str, byte[] bArr, int i) {
        return convertByteArrayToInt(str, bArr, 0, i);
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToInt(String str, byte[] bArr, int i, int i2) {
        int i3;
        byte b = bArr[i + 0];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        if (i2 == 77) {
            i3 = ((b4 & 255) << 0) | ((b & 255) << Ascii.CAN) | ((b2 & 255) << Ascii.DLE) | ((b3 & 255) << 8);
        } else {
            i3 = ((b4 & 255) << Ascii.CAN) | ((b3 & 255) << Ascii.DLE) | ((b2 & 255) << 8) | ((b & 255) << 0);
        }
        if (this.debug) {
            debugNumber(str, i3, 4);
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    public final int[] convertByteArrayToIntArray(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 4) + i;
        if (bArr.length < i4) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": expected length: ");
            sb.append(i4);
            sb.append(", actual length: ");
            sb.append(bArr.length);
            printStream.println(sb.toString());
            return null;
        }
        int[] iArr = new int[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            iArr[i5] = convertByteArrayToInt(str, bArr, (i5 * 4) + i, i3);
        }
        return iArr;
    }

    /* access modifiers changed from: protected */
    public final void writeIntInToByteArray(int i, byte[] bArr, int i2, int i3) {
        if (i3 == 77) {
            bArr[i2 + 0] = (byte) (i >> 24);
            bArr[i2 + 1] = (byte) (i >> 16);
            bArr[i2 + 2] = (byte) (i >> 8);
            bArr[i2 + 3] = (byte) (i >> 0);
            return;
        }
        bArr[i2 + 3] = (byte) (i >> 24);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 0] = (byte) (i >> 0);
    }

    /* access modifiers changed from: protected */
    public final byte[] convertIntArrayToByteArray(int[] iArr, int i) {
        byte[] bArr = new byte[(iArr.length * 4)];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            writeIntInToByteArray(iArr[i2], bArr, i2 * 4, i);
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertShortArrayToByteArray(int[] iArr, int i) {
        byte[] bArr = new byte[(iArr.length * 2)];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            if (i == 77) {
                int i4 = i2 * 2;
                bArr[i4 + 0] = (byte) (i3 >> 8);
                bArr[i4 + 1] = (byte) (i3 >> 0);
            } else {
                int i5 = i2 * 2;
                bArr[i5 + 1] = (byte) (i3 >> 8);
                bArr[i5 + 0] = (byte) (i3 >> 0);
            }
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertShortToByteArray(int i, int i2) {
        byte[] bArr = new byte[2];
        if (i2 == 77) {
            bArr[0] = (byte) (i >> 8);
            bArr[1] = (byte) (i >> 0);
        } else {
            bArr[1] = (byte) (i >> 8);
            bArr[0] = (byte) (i >> 0);
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertRationalArrayToByteArray(RationalNumber[] rationalNumberArr, int i) throws ImageWriteException {
        byte[] bArr = new byte[(rationalNumberArr.length * 8)];
        for (int i2 = 0; i2 < rationalNumberArr.length; i2++) {
            int i3 = i2 * 8;
            writeIntInToByteArray(rationalNumberArr[i2].numerator, bArr, i3, i);
            writeIntInToByteArray(rationalNumberArr[i2].divisor, bArr, i3 + 4, i);
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertRationalToByteArray(RationalNumber rationalNumber, int i) throws ImageWriteException {
        byte[] bArr = new byte[8];
        writeIntInToByteArray(rationalNumber.numerator, bArr, 0, i);
        writeIntInToByteArray(rationalNumber.divisor, bArr, 4, i);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToShort(String str, byte[] bArr, int i) throws ImageReadException {
        return convertByteArrayToShort(str, 0, bArr, i);
    }

    /* access modifiers changed from: protected */
    public final int convertByteArrayToShort(String str, int i, byte[] bArr, int i2) throws ImageReadException {
        int i3 = i + 1;
        if (i3 < bArr.length) {
            byte b = bArr[i + 0] & 255;
            byte b2 = bArr[i3] & 255;
            byte b3 = i2 == 77 ? (b << 8) | b2 : b | (b2 << 8);
            if (this.debug) {
                debugNumber(str, b3, 2);
            }
            return b3;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Index out of bounds. Array size: ");
        sb.append(bArr.length);
        sb.append(", index: ");
        sb.append(i);
        throw new ImageReadException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final int[] convertByteArrayToShortArray(String str, byte[] bArr, int i, int i2, int i3) throws ImageReadException {
        int i4 = (i2 * 2) + i;
        if (bArr.length < i4) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": expected length: ");
            sb.append(i4);
            sb.append(", actual length: ");
            sb.append(bArr.length);
            printStream.println(sb.toString());
            return null;
        }
        int[] iArr = new int[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            iArr[i5] = convertByteArrayToShort(str, (i5 * 2) + i, bArr, i3);
        }
        return iArr;
    }

    public final byte[] readByteArray(String str, int i, InputStream inputStream, String str2) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        while (i3 < i) {
            int read = inputStream.read(bArr, i3, i - i3);
            if (read >= 1) {
                i3 += read;
            } else {
                throw new IOException(str2);
            }
        }
        if (this.debug) {
            while (i2 < i && i2 < 50) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" (");
                sb.append(i2);
                sb.append(")");
                debugNumber(sb.toString(), bArr[i2] & 255);
                i2++;
            }
        }
        return bArr;
    }

    public final void debugByteArray(String str, byte[] bArr) {
        PrintStream printStream = System.out;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(bArr.length);
        printStream.println(sb.toString());
        int i = 0;
        while (i < bArr.length && i < 50) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("\t (");
            sb2.append(i);
            sb2.append(")");
            debugNumber(sb2.toString(), bArr[i] & 255);
            i++;
        }
    }

    public final byte[] readBytearray(String str, byte[] bArr, int i, int i2) throws ImageReadException {
        if (bArr.length >= i + i2) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            if (this.debug) {
                debugByteArray(str, bArr2);
            }
            return bArr2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid read. bytes.length: ");
        sb.append(bArr.length);
        sb.append(", start: ");
        sb.append(i);
        sb.append(", count: ");
        sb.append(i2);
        throw new ImageReadException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public final byte[] getByteArrayTail(String str, byte[] bArr, int i) throws ImageReadException {
        return readBytearray(str, bArr, i, bArr.length - i);
    }

    public final boolean compareByteArrays(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        return compareByteArrays(bArr, 0, bArr2, 0, bArr.length);
    }

    public final boolean compareByteArrays(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    public static final boolean compareBytes(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i + i4] != bArr2[i2 + i4]) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final int read4Bytes(String str, InputStream inputStream, String str2, int i) throws ImageReadException, IOException {
        byte[] bArr = new byte[4];
        int i2 = 0;
        while (i2 < 4) {
            int read = inputStream.read(bArr, i2, 4 - i2);
            if (read >= 1) {
                i2 += read;
            } else {
                throw new IOException(str2);
            }
        }
        return convertByteArrayToInt(str, bArr, i);
    }

    /* access modifiers changed from: protected */
    public final int read2Bytes(String str, InputStream inputStream, String str2, int i) throws ImageReadException, IOException {
        byte[] bArr = new byte[2];
        int i2 = 0;
        while (i2 < 2) {
            int read = inputStream.read(bArr, i2, 2 - i2);
            if (read >= 1) {
                i2 += read;
            } else {
                throw new IOException(str2);
            }
        }
        return convertByteArrayToShort(str, bArr, i);
    }

    /* access modifiers changed from: protected */
    public final byte[] getRAFBytes(RandomAccessFile randomAccessFile, long j, int i, String str) throws IOException {
        if (this.debug) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("getRAFBytes pos: ");
            sb.append(j);
            printStream.println(sb.toString());
            PrintStream printStream2 = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("getRAFBytes length: ");
            sb2.append(i);
            printStream2.println(sb2.toString());
        }
        byte[] bArr = new byte[i];
        randomAccessFile.seek(j);
        int i2 = 0;
        while (i2 < i) {
            int read = randomAccessFile.read(bArr, i2, i - i2);
            if (read >= 1) {
                i2 += read;
            } else {
                throw new IOException(str);
            }
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final float convertByteArrayToFloat(String str, byte[] bArr, int i) {
        return convertByteArrayToFloat(str, bArr, 0, i);
    }

    /* access modifiers changed from: protected */
    public final float convertByteArrayToFloat(String str, byte[] bArr, int i, int i2) {
        int i3;
        byte b = bArr[i + 0];
        byte b2 = bArr[i + 1];
        byte b3 = bArr[i + 2];
        byte b4 = bArr[i + 3];
        if (i2 == 77) {
            i3 = ((b & 255) << Ascii.CAN) | ((b2 & 255) << Ascii.DLE) | ((b3 & 255) << 8) | ((b4 & 255) << 0);
        } else {
            i3 = ((b & 255) << 0) | ((b4 & 255) << Ascii.CAN) | ((b3 & 255) << Ascii.DLE) | ((b2 & 255) << 8);
        }
        return Float.intBitsToFloat(i3);
    }

    /* access modifiers changed from: protected */
    public final float[] convertByteArrayToFloatArray(String str, byte[] bArr, int i, int i2, int i3) {
        int i4 = (i2 * 4) + i;
        if (bArr.length < i4) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(": expected length: ");
            sb.append(i4);
            sb.append(", actual length: ");
            sb.append(bArr.length);
            printStream.println(sb.toString());
            return null;
        }
        float[] fArr = new float[i2];
        for (int i5 = 0; i5 < i2; i5++) {
            fArr[i5] = convertByteArrayToFloat(str, bArr, (i5 * 4) + i, i3);
        }
        return fArr;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertFloatToByteArray(float f, int i) {
        byte[] bArr = new byte[4];
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        if (i == 77) {
            bArr[0] = (byte) ((floatToRawIntBits >> 0) & 255);
            bArr[1] = (byte) ((floatToRawIntBits >> 8) & 255);
            bArr[2] = (byte) ((floatToRawIntBits >> 16) & 255);
            bArr[3] = (byte) ((floatToRawIntBits >> 24) & 255);
        } else {
            bArr[3] = (byte) ((floatToRawIntBits >> 0) & 255);
            bArr[2] = (byte) ((floatToRawIntBits >> 8) & 255);
            bArr[1] = (byte) ((floatToRawIntBits >> 16) & 255);
            bArr[0] = (byte) ((floatToRawIntBits >> 24) & 255);
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertFloatArrayToByteArray(float[] fArr, int i) {
        byte[] bArr = new byte[(fArr.length * 4)];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            int floatToRawIntBits = Float.floatToRawIntBits(fArr[i2]);
            int i3 = i2 * 4;
            if (i == 77) {
                bArr[i3 + 0] = (byte) ((floatToRawIntBits >> 0) & 255);
                bArr[i3 + 1] = (byte) ((floatToRawIntBits >> 8) & 255);
                bArr[i3 + 2] = (byte) ((floatToRawIntBits >> 16) & 255);
                bArr[i3 + 3] = (byte) ((floatToRawIntBits >> 24) & 255);
            } else {
                bArr[i3 + 3] = (byte) ((floatToRawIntBits >> 0) & 255);
                bArr[i3 + 2] = (byte) ((floatToRawIntBits >> 8) & 255);
                bArr[i3 + 1] = (byte) ((floatToRawIntBits >> 16) & 255);
                bArr[i3 + 0] = (byte) ((floatToRawIntBits >> 24) & 255);
            }
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertDoubleToByteArray(double d, int i) {
        byte[] bArr = new byte[8];
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        if (i == 77) {
            bArr[0] = (byte) ((int) ((doubleToRawLongBits >> 0) & 255));
            bArr[1] = (byte) ((int) ((doubleToRawLongBits >> 8) & 255));
            bArr[2] = (byte) ((int) ((doubleToRawLongBits >> 16) & 255));
            bArr[3] = (byte) ((int) ((doubleToRawLongBits >> 24) & 255));
            bArr[4] = (byte) ((int) ((doubleToRawLongBits >> 32) & 255));
            bArr[5] = (byte) ((int) ((doubleToRawLongBits >> 40) & 255));
            bArr[6] = (byte) ((int) ((doubleToRawLongBits >> 48) & 255));
            bArr[7] = (byte) ((int) ((doubleToRawLongBits >> 56) & 255));
        } else {
            bArr[7] = (byte) ((int) ((doubleToRawLongBits >> 0) & 255));
            bArr[6] = (byte) ((int) ((doubleToRawLongBits >> 8) & 255));
            bArr[5] = (byte) ((int) ((doubleToRawLongBits >> 16) & 255));
            bArr[4] = (byte) ((int) ((doubleToRawLongBits >> 24) & 255));
            bArr[3] = (byte) ((int) ((doubleToRawLongBits >> 32) & 255));
            bArr[2] = (byte) ((int) ((doubleToRawLongBits >> 40) & 255));
            bArr[1] = (byte) ((int) ((doubleToRawLongBits >> 48) & 255));
            bArr[0] = (byte) ((int) ((doubleToRawLongBits >> 56) & 255));
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    public final byte[] convertDoubleArrayToByteArray(double[] dArr, int i) {
        int i2;
        double[] dArr2 = dArr;
        byte[] bArr = new byte[(dArr2.length * 8)];
        boolean z = false;
        int i3 = 0;
        while (i3 < dArr2.length) {
            long doubleToRawLongBits = Double.doubleToRawLongBits(dArr2[i3]);
            int i4 = i3 * 8;
            if (i == 77) {
                i2 = i3;
                bArr[i4 + 0] = (byte) ((int) ((doubleToRawLongBits >> (z ? 1 : 0)) & 255));
                bArr[i4 + 1] = (byte) ((int) ((doubleToRawLongBits >> 8) & 255));
                bArr[i4 + 2] = (byte) ((int) ((doubleToRawLongBits >> 16) & 255));
                bArr[i4 + 3] = (byte) ((int) ((doubleToRawLongBits >> 24) & 255));
                bArr[i4 + 4] = (byte) ((int) ((doubleToRawLongBits >> 32) & 255));
                bArr[i4 + 5] = (byte) ((int) ((doubleToRawLongBits >> 40) & 255));
                bArr[i4 + 6] = (byte) ((int) ((doubleToRawLongBits >> 48) & 255));
                bArr[i4 + 7] = (byte) ((int) ((doubleToRawLongBits >> 56) & 255));
            } else {
                i2 = i3;
                bArr[i4 + 7] = (byte) ((int) ((doubleToRawLongBits >> 0) & 255));
                bArr[i4 + 6] = (byte) ((int) ((doubleToRawLongBits >> 8) & 255));
                bArr[i4 + 5] = (byte) ((int) ((doubleToRawLongBits >> 16) & 255));
                bArr[i4 + 4] = (byte) ((int) ((doubleToRawLongBits >> 24) & 255));
                bArr[i4 + 3] = (byte) ((int) ((doubleToRawLongBits >> 32) & 255));
                bArr[i4 + 2] = (byte) ((int) ((doubleToRawLongBits >> 40) & 255));
                bArr[i4 + 1] = (byte) ((int) ((doubleToRawLongBits >> 48) & 255));
                bArr[i4 + 0] = (byte) ((int) ((doubleToRawLongBits >> 56) & 255));
            }
            i3 = i2 + 1;
            z = false;
        }
        return bArr;
    }
}
