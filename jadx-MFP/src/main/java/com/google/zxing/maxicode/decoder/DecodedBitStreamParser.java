package com.google.zxing.maxicode.decoder;

import com.google.common.base.Ascii;
import com.google.zxing.common.DecoderResult;
import java.text.DecimalFormat;

final class DecodedBitStreamParser {
    private static final char ECI = '￺';
    private static final char FS = '\u001c';
    private static final char GS = '\u001d';
    private static final char LATCHA = '￷';
    private static final char LATCHB = '￸';
    private static final char LOCK = '￹';
    private static final char NS = '￻';
    private static final char PAD = '￼';
    private static final char RS = '\u001e';
    private static final String[] SETS = {"\nABCDEFGHIJKLMNOPQRSTUVWXYZ￺\u001c\u001d\u001e￻ ￼\"#$%&'()*+,-./0123456789:￱￲￳￴￸", "`abcdefghijklmnopqrstuvwxyz￺\u001c\u001d\u001e￻{￼}~;<=>?[\\]^_ ,./:@!|￼￵￶￼￰￲￳￴￷", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ￺\u001c\u001d\u001eÛÜÝÞßª¬±²³µ¹º¼½¾￷ ￹￳￴￸", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú￺\u001c\u001d\u001e￻ûüýþÿ¡¨«¯°´·¸»¿￷ ￲￹￴￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a￺￼￼\u001b￻\u001c\u001d\u001e\u001f ¢£¤¥¦§©­®¶￷ ￲￳￹￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};
    private static final char SHIFTA = '￰';
    private static final char SHIFTB = '￱';
    private static final char SHIFTC = '￲';
    private static final char SHIFTD = '￳';
    private static final char SHIFTE = '￴';
    private static final char THREESHIFTA = '￶';
    private static final char TWOSHIFTA = '￵';

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(byte[] bArr, int i) {
        String str;
        StringBuilder sb = new StringBuilder(144);
        switch (i) {
            case 2:
            case 3:
                if (i == 2) {
                    str = new DecimalFormat("0000000000".substring(0, getPostCode2Length(bArr))).format((long) getPostCode2(bArr));
                } else {
                    str = getPostCode3(bArr);
                }
                DecimalFormat decimalFormat = new DecimalFormat("000");
                String format = decimalFormat.format((long) getCountry(bArr));
                String format2 = decimalFormat.format((long) getServiceClass(bArr));
                sb.append(getMessage(bArr, 10, 84));
                if (!sb.toString().startsWith("[)>\u001e01\u001d")) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(GS);
                    sb2.append(format);
                    sb2.append(GS);
                    sb2.append(format2);
                    sb2.append(GS);
                    sb.insert(0, sb2.toString());
                    break;
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(str);
                    sb3.append(GS);
                    sb3.append(format);
                    sb3.append(GS);
                    sb3.append(format2);
                    sb3.append(GS);
                    sb.insert(9, sb3.toString());
                    break;
                }
            case 4:
                sb.append(getMessage(bArr, 1, 93));
                break;
            case 5:
                sb.append(getMessage(bArr, 1, 77));
                break;
        }
        return new DecoderResult(bArr, sb.toString(), null, String.valueOf(i));
    }

    private static int getBit(int i, byte[] bArr) {
        int i2 = i - 1;
        return ((1 << (5 - (i2 % 6))) & bArr[i2 / 6]) == 0 ? 0 : 1;
    }

    private static int getInt(byte[] bArr, byte[] bArr2) {
        if (bArr2.length != 0) {
            int i = 0;
            for (int i2 = 0; i2 < bArr2.length; i2++) {
                i += getBit(bArr2[i2], bArr) << ((bArr2.length - i2) - 1);
            }
            return i;
        }
        throw new IllegalArgumentException();
    }

    private static int getCountry(byte[] bArr) {
        return getInt(bArr, new byte[]{53, 54, 43, 44, Framer.STDIN_FRAME_PREFIX, 46, 47, 48, 37, 38});
    }

    private static int getServiceClass(byte[] bArr) {
        return getInt(bArr, new byte[]{55, 56, 57, 58, 59, 60, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52});
    }

    private static int getPostCode2Length(byte[] bArr) {
        return getInt(bArr, new byte[]{39, 40, 41, 42, Ascii.US, 32});
    }

    private static int getPostCode2(byte[] bArr) {
        return getInt(bArr, new byte[]{Framer.ENTER_FRAME_PREFIX, 34, 35, 36, Ascii.EM, Ascii.SUB, Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.RS, 19, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.CR, Ascii.SO, Ascii.SI, Ascii.DLE, 17, Ascii.DC2, 7, 8, 9, 10, Ascii.VT, Ascii.FF, 1, 2});
    }

    private static String getPostCode3(byte[] bArr) {
        return String.valueOf(new char[]{SETS[0].charAt(getInt(bArr, new byte[]{39, 40, 41, 42, Ascii.US, 32})), SETS[0].charAt(getInt(bArr, new byte[]{Framer.ENTER_FRAME_PREFIX, 34, 35, 36, Ascii.EM, Ascii.SUB})), SETS[0].charAt(getInt(bArr, new byte[]{Ascii.ESC, Ascii.FS, Ascii.GS, Ascii.RS, 19, Ascii.DC4})), SETS[0].charAt(getInt(bArr, new byte[]{Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.CR, Ascii.SO})), SETS[0].charAt(getInt(bArr, new byte[]{Ascii.SI, Ascii.DLE, 17, Ascii.DC2, 7, 8})), SETS[0].charAt(getInt(bArr, new byte[]{9, 10, Ascii.VT, Ascii.FF, 1, 2}))});
    }

    private static String getMessage(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        int i3 = i;
        int i4 = 0;
        int i5 = -1;
        int i6 = 0;
        while (i3 < i + i2) {
            char charAt = SETS[i4].charAt(bArr[i3]);
            switch (charAt) {
                case 65520:
                case 65521:
                case 65522:
                case 65523:
                case 65524:
                    i6 = i4;
                    i4 = charAt - SHIFTA;
                    i5 = 1;
                    break;
                case 65525:
                    i5 = 2;
                    i6 = i4;
                    i4 = 0;
                    break;
                case 65526:
                    i5 = 3;
                    i6 = i4;
                    i4 = 0;
                    break;
                case 65527:
                    i4 = 0;
                    i5 = -1;
                    break;
                case 65528:
                    i4 = 1;
                    i5 = -1;
                    break;
                case 65529:
                    i5 = -1;
                    break;
                case 65531:
                    int i7 = i3 + 1;
                    int i8 = i7 + 1;
                    int i9 = i8 + 1;
                    int i10 = i9 + 1;
                    i3 = i10 + 1;
                    sb.append(new DecimalFormat("000000000").format((long) ((bArr[i7] << Ascii.CAN) + (bArr[i8] << Ascii.DC2) + (bArr[i9] << Ascii.FF) + (bArr[i10] << 6) + bArr[i3])));
                    break;
                default:
                    sb.append(charAt);
                    break;
            }
            int i11 = i5 - 1;
            if (i5 == 0) {
                i4 = i6;
            }
            i3++;
            i5 = i11;
        }
        while (sb.length() > 0 && sb.charAt(sb.length() - 1) == 65532) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
