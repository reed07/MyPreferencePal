package com.google.zxing.pdf417.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.pdf417.PDF417ResultMetadata;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class DecodedBitStreamParser {
    private static final int AL = 28;
    private static final int AS = 27;
    private static final int BEGIN_MACRO_PDF417_CONTROL_BLOCK = 928;
    private static final int BEGIN_MACRO_PDF417_OPTIONAL_FIELD = 923;
    private static final int BYTE_COMPACTION_MODE_LATCH = 901;
    private static final int BYTE_COMPACTION_MODE_LATCH_6 = 924;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final BigInteger[] EXP900;
    private static final int LL = 27;
    private static final int MACRO_PDF417_TERMINATOR = 922;
    private static final int MAX_NUMERIC_CODEWORDS = 15;
    private static final char[] MIXED_CHARS = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final int ML = 28;
    private static final int MODE_SHIFT_TO_BYTE_COMPACTION_MODE = 913;
    private static final int NUMBER_OF_SEQUENCE_CODEWORDS = 2;
    private static final int NUMERIC_COMPACTION_MODE_LATCH = 902;
    private static final int PAL = 29;
    private static final int PL = 25;
    private static final int PS = 29;
    private static final char[] PUNCT_CHARS = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final int TEXT_COMPACTION_MODE_LATCH = 900;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        EXP900 = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        EXP900[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = EXP900;
            if (i < bigIntegerArr2.length) {
                bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
                i++;
            } else {
                return;
            }
        }
    }

    private DecodedBitStreamParser() {
    }

    static DecoderResult decode(int[] iArr, String str) throws FormatException {
        int i;
        StringBuilder sb = new StringBuilder(iArr.length << 1);
        Charset charset = DEFAULT_ENCODING;
        int i2 = iArr[1];
        PDF417ResultMetadata pDF417ResultMetadata = new PDF417ResultMetadata();
        int i3 = 2;
        while (i3 < iArr[0]) {
            if (i2 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                switch (i2) {
                    case TEXT_COMPACTION_MODE_LATCH /*900*/:
                        i = textCompaction(iArr, i3, sb);
                        break;
                    case BYTE_COMPACTION_MODE_LATCH /*901*/:
                        i = byteCompaction(i2, iArr, charset, i3, sb);
                        break;
                    case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                        i = numericCompaction(iArr, i3, sb);
                        break;
                    default:
                        switch (i2) {
                            case MACRO_PDF417_TERMINATOR /*922*/:
                            case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                throw FormatException.getFormatInstance();
                            case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                break;
                            case ECI_USER_DEFINED /*925*/:
                                i = i3 + 1;
                                break;
                            case ECI_GENERAL_PURPOSE /*926*/:
                                i = i3 + 2;
                                break;
                            case ECI_CHARSET /*927*/:
                                i = i3 + 1;
                                charset = Charset.forName(CharacterSetECI.getCharacterSetECIByValue(iArr[i3]).name());
                                break;
                            case 928:
                                i = decodeMacroBlock(iArr, i3, pDF417ResultMetadata);
                                break;
                            default:
                                i = textCompaction(iArr, i3 - 1, sb);
                                break;
                        }
                        i = byteCompaction(i2, iArr, charset, i3, sb);
                        break;
                }
            } else {
                i = i3 + 1;
                sb.append((char) iArr[i3]);
            }
            if (i < iArr.length) {
                i3 = i + 1;
                i2 = iArr[i];
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (sb.length() != 0) {
            DecoderResult decoderResult = new DecoderResult(null, sb.toString(), null, str);
            decoderResult.setOther(pDF417ResultMetadata);
            return decoderResult;
        }
        throw FormatException.getFormatInstance();
    }

    private static int decodeMacroBlock(int[] iArr, int i, PDF417ResultMetadata pDF417ResultMetadata) throws FormatException {
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            int i2 = i;
            int i3 = 0;
            while (i3 < 2) {
                iArr2[i3] = iArr[i2];
                i3++;
                i2++;
            }
            pDF417ResultMetadata.setSegmentIndex(Integer.parseInt(decodeBase900toBase10(iArr2, 2)));
            StringBuilder sb = new StringBuilder();
            int textCompaction = textCompaction(iArr, i2, sb);
            pDF417ResultMetadata.setFileId(sb.toString());
            if (iArr[textCompaction] == BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                int i4 = textCompaction + 1;
                int[] iArr3 = new int[(iArr[0] - i4)];
                boolean z = false;
                int i5 = 0;
                while (i4 < iArr[0] && !z) {
                    int i6 = i4 + 1;
                    int i7 = iArr[i4];
                    if (i7 < TEXT_COMPACTION_MODE_LATCH) {
                        int i8 = i5 + 1;
                        iArr3[i5] = i7;
                        i4 = i6;
                        i5 = i8;
                    } else if (i7 == MACRO_PDF417_TERMINATOR) {
                        pDF417ResultMetadata.setLastSegment(true);
                        i4 = i6 + 1;
                        z = true;
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                pDF417ResultMetadata.setOptionalData(Arrays.copyOf(iArr3, i5));
                return i4;
            } else if (iArr[textCompaction] != MACRO_PDF417_TERMINATOR) {
                return textCompaction;
            } else {
                pDF417ResultMetadata.setLastSegment(true);
                return textCompaction + 1;
            }
        } else {
            throw FormatException.getFormatInstance();
        }
    }

    private static int textCompaction(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[((iArr[0] - i) << 1)];
        int[] iArr3 = new int[((iArr[0] - i) << 1)];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
                i = i3;
            } else if (i4 != MODE_SHIFT_TO_BYTE_COMPACTION_MODE) {
                if (i4 != 928) {
                    switch (i4) {
                        case TEXT_COMPACTION_MODE_LATCH /*900*/:
                            int i5 = i2 + 1;
                            iArr2[i2] = TEXT_COMPACTION_MODE_LATCH;
                            i2 = i5;
                            i = i3;
                            continue;
                        case BYTE_COMPACTION_MODE_LATCH /*901*/:
                        case NUMERIC_COMPACTION_MODE_LATCH /*902*/:
                            break;
                        default:
                            switch (i4) {
                                case MACRO_PDF417_TERMINATOR /*922*/:
                                case BEGIN_MACRO_PDF417_OPTIONAL_FIELD /*923*/:
                                case BYTE_COMPACTION_MODE_LATCH_6 /*924*/:
                                    break;
                                default:
                                    i = i3;
                                    continue;
                                    continue;
                            }
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = MODE_SHIFT_TO_BYTE_COMPACTION_MODE;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
        }
        decodeTextCompaction(iArr2, iArr3, i2, sb);
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0063, code lost:
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x012a, code lost:
        if (r6 == 0) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x012c, code lost:
        r0.append(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x012f, code lost:
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void decodeTextCompaction(int[] r16, int[] r17, int r18, java.lang.StringBuilder r19) {
        /*
            r0 = r19
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r1 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r2 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r3 = 0
            r4 = r1
            r5 = r2
            r2 = 0
            r1 = r18
        L_0x000c:
            if (r2 >= r1) goto L_0x0133
            r6 = r16[r2]
            int[] r7 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.AnonymousClass1.$SwitchMap$com$google$zxing$pdf417$decoder$DecodedBitStreamParser$Mode
            int r8 = r4.ordinal()
            r7 = r7[r8]
            r8 = 28
            r9 = 27
            r10 = 32
            r11 = 913(0x391, float:1.28E-42)
            r12 = 900(0x384, float:1.261E-42)
            r13 = 29
            r14 = 26
            switch(r7) {
                case 1: goto L_0x00fa;
                case 2: goto L_0x00c6;
                case 3: goto L_0x0086;
                case 4: goto L_0x0066;
                case 5: goto L_0x004c;
                case 6: goto L_0x002b;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x0129
        L_0x002b:
            if (r6 >= r13) goto L_0x0035
            char[] r4 = PUNCT_CHARS
            char r4 = r4[r6]
            r6 = r4
            r4 = r5
            goto L_0x012a
        L_0x0035:
            if (r6 != r13) goto L_0x003c
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x003c:
            if (r6 != r11) goto L_0x0045
            r4 = r17[r2]
            char r4 = (char) r4
            r0.append(r4)
            goto L_0x0063
        L_0x0045:
            if (r6 != r12) goto L_0x0063
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x004c:
            if (r6 >= r14) goto L_0x0055
            int r6 = r6 + 65
            char r4 = (char) r6
            r6 = r4
            r4 = r5
            goto L_0x012a
        L_0x0055:
            if (r6 != r14) goto L_0x005c
            r4 = r5
            r6 = 32
            goto L_0x012a
        L_0x005c:
            if (r6 != r12) goto L_0x0063
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x0063:
            r4 = r5
            goto L_0x0129
        L_0x0066:
            if (r6 >= r13) goto L_0x006e
            char[] r7 = PUNCT_CHARS
            char r6 = r7[r6]
            goto L_0x012a
        L_0x006e:
            if (r6 != r13) goto L_0x0075
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x0075:
            if (r6 != r11) goto L_0x007f
            r6 = r17[r2]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x0129
        L_0x007f:
            if (r6 != r12) goto L_0x0129
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x0086:
            r7 = 25
            if (r6 >= r7) goto L_0x0090
            char[] r7 = MIXED_CHARS
            char r6 = r7[r6]
            goto L_0x012a
        L_0x0090:
            if (r6 != r7) goto L_0x0097
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT
            r6 = 0
            goto L_0x012a
        L_0x0097:
            if (r6 != r14) goto L_0x009d
            r6 = 32
            goto L_0x012a
        L_0x009d:
            if (r6 != r9) goto L_0x00a4
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            r6 = 0
            goto L_0x012a
        L_0x00a4:
            if (r6 != r8) goto L_0x00ab
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x00ab:
            if (r6 != r13) goto L_0x00b5
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            r6 = 0
            r15 = r5
            r5 = r4
            r4 = r15
            goto L_0x012a
        L_0x00b5:
            if (r6 != r11) goto L_0x00bf
            r6 = r17[r2]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x0129
        L_0x00bf:
            if (r6 != r12) goto L_0x0129
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x00c6:
            if (r6 >= r14) goto L_0x00cd
            int r6 = r6 + 97
            char r6 = (char) r6
            goto L_0x012a
        L_0x00cd:
            if (r6 != r14) goto L_0x00d3
            r6 = 32
            goto L_0x012a
        L_0x00d3:
            if (r6 != r9) goto L_0x00dc
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA_SHIFT
            r6 = 0
            r15 = r5
            r5 = r4
            r4 = r15
            goto L_0x012a
        L_0x00dc:
            if (r6 != r8) goto L_0x00e2
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            r6 = 0
            goto L_0x012a
        L_0x00e2:
            if (r6 != r13) goto L_0x00eb
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            r6 = 0
            r15 = r5
            r5 = r4
            r4 = r15
            goto L_0x012a
        L_0x00eb:
            if (r6 != r11) goto L_0x00f4
            r6 = r17[r2]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x0129
        L_0x00f4:
            if (r6 != r12) goto L_0x0129
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x00fa:
            if (r6 >= r14) goto L_0x0100
            int r6 = r6 + 65
            char r6 = (char) r6
            goto L_0x012a
        L_0x0100:
            if (r6 != r14) goto L_0x0105
            r6 = 32
            goto L_0x012a
        L_0x0105:
            if (r6 != r9) goto L_0x010b
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.LOWER
            r6 = 0
            goto L_0x012a
        L_0x010b:
            if (r6 != r8) goto L_0x0111
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.MIXED
            r6 = 0
            goto L_0x012a
        L_0x0111:
            if (r6 != r13) goto L_0x011a
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r5 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.PUNCT_SHIFT
            r6 = 0
            r15 = r5
            r5 = r4
            r4 = r15
            goto L_0x012a
        L_0x011a:
            if (r6 != r11) goto L_0x0123
            r6 = r17[r2]
            char r6 = (char) r6
            r0.append(r6)
            goto L_0x0129
        L_0x0123:
            if (r6 != r12) goto L_0x0129
            com.google.zxing.pdf417.decoder.DecodedBitStreamParser$Mode r4 = com.google.zxing.pdf417.decoder.DecodedBitStreamParser.Mode.ALPHA
            r6 = 0
            goto L_0x012a
        L_0x0129:
            r6 = 0
        L_0x012a:
            if (r6 == 0) goto L_0x012f
            r0.append(r6)
        L_0x012f:
            int r2 = r2 + 1
            goto L_0x000c
        L_0x0133:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.DecodedBitStreamParser.decodeTextCompaction(int[], int[], int, java.lang.StringBuilder):void");
    }

    private static int byteCompaction(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        int i4;
        int i5 = i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i6 = MACRO_PDF417_TERMINATOR;
        int i7 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
        int i8 = 928;
        long j = 900;
        if (i5 == BYTE_COMPACTION_MODE_LATCH) {
            int[] iArr2 = new int[6];
            i3 = i2 + 1;
            int i9 = iArr[i2];
            boolean z = false;
            int i10 = 0;
            long j2 = 0;
            while (i3 < iArr[0] && !z) {
                int i11 = i10 + 1;
                iArr2[i10] = i9;
                j2 = (j2 * j) + ((long) i9);
                int i12 = i3 + 1;
                i9 = iArr[i3];
                if (i9 == TEXT_COMPACTION_MODE_LATCH || i9 == BYTE_COMPACTION_MODE_LATCH || i9 == NUMERIC_COMPACTION_MODE_LATCH || i9 == BYTE_COMPACTION_MODE_LATCH_6 || i9 == 928 || i9 == i7 || i9 == i6) {
                    i3 = i12 - 1;
                    i10 = i11;
                    i6 = MACRO_PDF417_TERMINATOR;
                    i7 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    j = 900;
                    z = true;
                } else if (i11 % 5 != 0 || i11 <= 0) {
                    i3 = i12;
                    i10 = i11;
                    i6 = MACRO_PDF417_TERMINATOR;
                    i7 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    j = 900;
                } else {
                    int i13 = 0;
                    while (i13 < 6) {
                        byteArrayOutputStream.write((byte) ((int) (j2 >> ((5 - i13) * 8))));
                        i13++;
                        i6 = MACRO_PDF417_TERMINATOR;
                        i7 = BEGIN_MACRO_PDF417_OPTIONAL_FIELD;
                    }
                    i3 = i12;
                    j = 900;
                    i10 = 0;
                    j2 = 0;
                }
            }
            if (i3 != iArr[0] || i9 >= TEXT_COMPACTION_MODE_LATCH) {
                i4 = i10;
            } else {
                i4 = i10 + 1;
                iArr2[i10] = i9;
            }
            for (int i14 = 0; i14 < i4; i14++) {
                byteArrayOutputStream.write((byte) iArr2[i14]);
            }
        } else if (i5 == BYTE_COMPACTION_MODE_LATCH_6) {
            int i15 = i2;
            int i16 = 0;
            long j3 = 0;
            boolean z2 = false;
            while (i15 < iArr[0] && !z2) {
                int i17 = i15 + 1;
                int i18 = iArr[i15];
                if (i18 < TEXT_COMPACTION_MODE_LATCH) {
                    i16++;
                    j3 = (j3 * 900) + ((long) i18);
                    i15 = i17;
                } else {
                    if (i18 != TEXT_COMPACTION_MODE_LATCH && i18 != BYTE_COMPACTION_MODE_LATCH && i18 != NUMERIC_COMPACTION_MODE_LATCH && i18 != BYTE_COMPACTION_MODE_LATCH_6 && i18 != i8) {
                        if (i18 != BEGIN_MACRO_PDF417_OPTIONAL_FIELD) {
                            if (i18 != MACRO_PDF417_TERMINATOR) {
                                i15 = i17;
                            }
                            i15 = i17 - 1;
                            z2 = true;
                        }
                    }
                    i15 = i17 - 1;
                    z2 = true;
                }
                if (i16 % 5 == 0 && i16 > 0) {
                    int i19 = 0;
                    for (int i20 = 6; i19 < i20; i20 = 6) {
                        byteArrayOutputStream.write((byte) ((int) (j3 >> ((5 - i19) * 8))));
                        i19++;
                    }
                    i16 = 0;
                    j3 = 0;
                }
                i8 = 928;
            }
            i3 = i15;
        } else {
            i3 = i2;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    private static int numericCompaction(int[] iArr, int i, StringBuilder sb) throws FormatException {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < TEXT_COMPACTION_MODE_LATCH) {
                iArr2[i2] = i4;
                i2++;
            } else if (i4 == TEXT_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH || i4 == BYTE_COMPACTION_MODE_LATCH_6 || i4 == 928 || i4 == BEGIN_MACRO_PDF417_OPTIONAL_FIELD || i4 == MACRO_PDF417_TERMINATOR) {
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == NUMERIC_COMPACTION_MODE_LATCH || z) && i2 > 0) {
                sb.append(decodeBase900toBase10(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }

    private static String decodeBase900toBase10(int[] iArr, int i) throws FormatException {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(EXP900[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
