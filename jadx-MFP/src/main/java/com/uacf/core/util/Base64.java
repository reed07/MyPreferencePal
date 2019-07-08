package com.uacf.core.util;

import com.google.android.exoplayer2.C;
import java.io.UnsupportedEncodingException;

public final class Base64 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CRLF = 4;
    public static final int DEFAULT = 0;
    public static final int NO_CLOSE = 16;
    public static final int NO_PADDING = 1;
    public static final int NO_WRAP = 2;
    public static final int URL_SAFE = 8;

    static abstract class Coder {
        public int op;
        public byte[] output;

        public abstract int maxOutputSize(int i);

        public abstract boolean process(byte[] bArr, int i, int i2, boolean z);

        Coder() {
        }
    }

    static class Decoder extends Coder {
        private static final int[] DECODE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] DECODE_WEBSAFE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int EQUALS = -2;
        private static final int SKIP = -1;
        private final int[] alphabet;
        private int state;
        private int value;

        public Decoder(int i, byte[] bArr) {
            this.output = bArr;
            this.alphabet = (i & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        public int maxOutputSize(int i) {
            return ((i * 3) / 4) + 10;
        }

        /* JADX WARNING: Removed duplicated region for block: B:50:0x00e4  */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x00eb  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] r12, int r13, int r14, boolean r15) {
            /*
                r11 = this;
                int r0 = r11.state
                r1 = 0
                r2 = 6
                if (r0 != r2) goto L_0x0007
                return r1
            L_0x0007:
                int r14 = r14 + r13
                int r3 = r11.value
                byte[] r4 = r11.output
                int[] r5 = r11.alphabet
                r6 = 0
            L_0x000f:
                r7 = 4
                if (r13 >= r14) goto L_0x00e1
                if (r0 != 0) goto L_0x0059
            L_0x0014:
                int r8 = r13 + 4
                if (r8 > r14) goto L_0x0055
                byte r3 = r12[r13]
                r3 = r3 & 255(0xff, float:3.57E-43)
                r3 = r5[r3]
                int r3 = r3 << 18
                int r9 = r13 + 1
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                int r9 = r9 << 12
                r3 = r3 | r9
                int r9 = r13 + 2
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                int r9 = r9 << r2
                r3 = r3 | r9
                int r9 = r13 + 3
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                r3 = r3 | r9
                if (r3 < 0) goto L_0x0055
                int r13 = r6 + 2
                byte r9 = (byte) r3
                r4[r13] = r9
                int r13 = r6 + 1
                int r9 = r3 >> 8
                byte r9 = (byte) r9
                r4[r13] = r9
                int r13 = r3 >> 16
                byte r13 = (byte) r13
                r4[r6] = r13
                int r6 = r6 + 3
                r13 = r8
                goto L_0x0014
            L_0x0055:
                if (r13 < r14) goto L_0x0059
                goto L_0x00e1
            L_0x0059:
                int r8 = r13 + 1
                byte r13 = r12[r13]
                r13 = r13 & 255(0xff, float:3.57E-43)
                r13 = r5[r13]
                r9 = -2
                r10 = -1
                switch(r0) {
                    case 0: goto L_0x00d3;
                    case 1: goto L_0x00c5;
                    case 2: goto L_0x00ab;
                    case 3: goto L_0x0078;
                    case 4: goto L_0x006d;
                    case 5: goto L_0x0068;
                    default: goto L_0x0066;
                }
            L_0x0066:
                goto L_0x00de
            L_0x0068:
                if (r13 == r10) goto L_0x00de
                r11.state = r2
                return r1
            L_0x006d:
                if (r13 != r9) goto L_0x0073
                int r0 = r0 + 1
                goto L_0x00de
            L_0x0073:
                if (r13 == r10) goto L_0x00de
                r11.state = r2
                return r1
            L_0x0078:
                if (r13 < 0) goto L_0x0093
                int r0 = r3 << 6
                r13 = r13 | r0
                int r0 = r6 + 2
                byte r3 = (byte) r13
                r4[r0] = r3
                int r0 = r6 + 1
                int r3 = r13 >> 8
                byte r3 = (byte) r3
                r4[r0] = r3
                int r0 = r13 >> 16
                byte r0 = (byte) r0
                r4[r6] = r0
                int r6 = r6 + 3
                r3 = r13
                r0 = 0
                goto L_0x00de
            L_0x0093:
                if (r13 != r9) goto L_0x00a6
                int r13 = r6 + 1
                int r0 = r3 >> 2
                byte r0 = (byte) r0
                r4[r13] = r0
                int r13 = r3 >> 10
                byte r13 = (byte) r13
                r4[r6] = r13
                int r6 = r6 + 2
                r13 = 5
                r0 = 5
                goto L_0x00de
            L_0x00a6:
                if (r13 == r10) goto L_0x00de
                r11.state = r2
                return r1
            L_0x00ab:
                if (r13 < 0) goto L_0x00b4
                int r3 = r3 << 6
                r13 = r13 | r3
                int r0 = r0 + 1
                r3 = r13
                goto L_0x00de
            L_0x00b4:
                if (r13 != r9) goto L_0x00c0
                int r13 = r6 + 1
                int r0 = r3 >> 4
                byte r0 = (byte) r0
                r4[r6] = r0
                r6 = r13
                r0 = 4
                goto L_0x00de
            L_0x00c0:
                if (r13 == r10) goto L_0x00de
                r11.state = r2
                return r1
            L_0x00c5:
                if (r13 < 0) goto L_0x00ce
                int r3 = r3 << 6
                r13 = r13 | r3
                int r0 = r0 + 1
                r3 = r13
                goto L_0x00de
            L_0x00ce:
                if (r13 == r10) goto L_0x00de
                r11.state = r2
                return r1
            L_0x00d3:
                if (r13 < 0) goto L_0x00d9
                int r0 = r0 + 1
                r3 = r13
                goto L_0x00de
            L_0x00d9:
                if (r13 == r10) goto L_0x00de
                r11.state = r2
                return r1
            L_0x00de:
                r13 = r8
                goto L_0x000f
            L_0x00e1:
                r12 = 1
                if (r15 != 0) goto L_0x00eb
                r11.state = r0
                r11.value = r3
                r11.op = r6
                return r12
            L_0x00eb:
                switch(r0) {
                    case 0: goto L_0x010d;
                    case 1: goto L_0x010a;
                    case 2: goto L_0x0101;
                    case 3: goto L_0x00f2;
                    case 4: goto L_0x00ef;
                    default: goto L_0x00ee;
                }
            L_0x00ee:
                goto L_0x010d
            L_0x00ef:
                r11.state = r2
                return r1
            L_0x00f2:
                int r13 = r6 + 1
                int r14 = r3 >> 10
                byte r14 = (byte) r14
                r4[r6] = r14
                int r6 = r13 + 1
                int r14 = r3 >> 2
                byte r14 = (byte) r14
                r4[r13] = r14
                goto L_0x010d
            L_0x0101:
                int r13 = r6 + 1
                int r14 = r3 >> 4
                byte r14 = (byte) r14
                r4[r6] = r14
                r6 = r13
                goto L_0x010d
            L_0x010a:
                r11.state = r2
                return r1
            L_0x010d:
                r11.state = r0
                r11.op = r6
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.Base64.Decoder.process(byte[], int, int, boolean):boolean");
        }
    }

    static class Encoder extends Coder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final byte[] ENCODE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, Framer.EXIT_FRAME_PREFIX, 121, 122, 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] ENCODE_WEBSAFE = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, Framer.EXIT_FRAME_PREFIX, 121, 122, 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52, 53, 54, 55, 56, 57, Framer.STDIN_FRAME_PREFIX, Framer.STDIN_REQUEST_FRAME_PREFIX};
        public static final int LINE_GROUPS = 19;
        private final byte[] alphabet;
        private int count;
        public final boolean do_cr;
        public final boolean do_newline;
        public final boolean do_padding;
        private final byte[] tail;
        int tailLen;

        static {
            Class<Base64> cls = Base64.class;
        }

        public Encoder(int i, byte[] bArr) {
            this.output = bArr;
            boolean z = true;
            this.do_padding = (i & 1) == 0;
            this.do_newline = (i & 2) == 0;
            if ((i & 4) == 0) {
                z = false;
            }
            this.do_cr = z;
            this.alphabet = (i & 8) == 0 ? ENCODE : ENCODE_WEBSAFE;
            this.tail = new byte[2];
            this.tailLen = 0;
            this.count = this.do_newline ? 19 : -1;
        }

        public int maxOutputSize(int i) {
            return ((i * 8) / 5) + 10;
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte[] r18, int r19, int r20, boolean r21) {
            /*
                r17 = this;
                r0 = r17
                byte[] r1 = r0.alphabet
                byte[] r2 = r0.output
                int r3 = r0.count
                int r4 = r20 + r19
                int r5 = r0.tailLen
                r6 = -1
                r7 = 0
                r8 = 1
                switch(r5) {
                    case 0: goto L_0x004f;
                    case 1: goto L_0x0030;
                    case 2: goto L_0x0013;
                    default: goto L_0x0012;
                }
            L_0x0012:
                goto L_0x004f
            L_0x0013:
                int r5 = r19 + 1
                if (r5 > r4) goto L_0x004f
                byte[] r9 = r0.tail
                byte r10 = r9[r7]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r10 = r10 << 16
                byte r9 = r9[r8]
                r9 = r9 & 255(0xff, float:3.57E-43)
                int r9 = r9 << 8
                r9 = r9 | r10
                byte r10 = r18[r19]
                r10 = r10 & 255(0xff, float:3.57E-43)
                r9 = r9 | r10
                r0.tailLen = r7
                r10 = r5
                r5 = r9
                goto L_0x0052
            L_0x0030:
                int r5 = r19 + 2
                if (r5 > r4) goto L_0x004f
                byte[] r5 = r0.tail
                byte r5 = r5[r7]
                r5 = r5 & 255(0xff, float:3.57E-43)
                int r5 = r5 << 16
                int r9 = r19 + 1
                byte r10 = r18[r19]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r10 = r10 << 8
                r5 = r5 | r10
                int r10 = r9 + 1
                byte r9 = r18[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r5 = r5 | r9
                r0.tailLen = r7
                goto L_0x0052
            L_0x004f:
                r10 = r19
                r5 = -1
            L_0x0052:
                r11 = 4
                r12 = 13
                r13 = 10
                r14 = 2
                if (r5 == r6) goto L_0x008f
                int r6 = r5 >> 18
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r7] = r6
                int r6 = r5 >> 12
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r8] = r6
                int r6 = r5 >> 6
                r6 = r6 & 63
                byte r6 = r1[r6]
                r2[r14] = r6
                r5 = r5 & 63
                byte r5 = r1[r5]
                r6 = 3
                r2[r6] = r5
                int r3 = r3 + -1
                if (r3 != 0) goto L_0x008d
                boolean r3 = r0.do_cr
                if (r3 == 0) goto L_0x0085
                r3 = 5
                r2[r11] = r12
                goto L_0x0086
            L_0x0085:
                r3 = 4
            L_0x0086:
                int r5 = r3 + 1
                r2[r3] = r13
                r3 = 19
                goto L_0x0090
            L_0x008d:
                r5 = 4
                goto L_0x0090
            L_0x008f:
                r5 = 0
            L_0x0090:
                int r6 = r10 + 3
                if (r6 > r4) goto L_0x00e8
                byte r15 = r18[r10]
                r15 = r15 & 255(0xff, float:3.57E-43)
                int r15 = r15 << 16
                int r16 = r10 + 1
                byte r9 = r18[r16]
                r9 = r9 & 255(0xff, float:3.57E-43)
                int r9 = r9 << 8
                r9 = r9 | r15
                int r10 = r10 + 2
                byte r10 = r18[r10]
                r10 = r10 & 255(0xff, float:3.57E-43)
                r9 = r9 | r10
                int r10 = r9 >> 18
                r10 = r10 & 63
                byte r10 = r1[r10]
                r2[r5] = r10
                int r10 = r5 + 1
                int r15 = r9 >> 12
                r15 = r15 & 63
                byte r15 = r1[r15]
                r2[r10] = r15
                int r10 = r5 + 2
                int r15 = r9 >> 6
                r15 = r15 & 63
                byte r15 = r1[r15]
                r2[r10] = r15
                int r10 = r5 + 3
                r9 = r9 & 63
                byte r9 = r1[r9]
                r2[r10] = r9
                int r5 = r5 + 4
                int r3 = r3 + -1
                if (r3 != 0) goto L_0x00e6
                boolean r3 = r0.do_cr
                if (r3 == 0) goto L_0x00dd
                int r3 = r5 + 1
                r2[r5] = r12
                goto L_0x00de
            L_0x00dd:
                r3 = r5
            L_0x00de:
                int r5 = r3 + 1
                r2[r3] = r13
                r10 = r6
                r3 = 19
                goto L_0x0090
            L_0x00e6:
                r10 = r6
                goto L_0x0090
            L_0x00e8:
                if (r21 == 0) goto L_0x01bb
                int r6 = r0.tailLen
                int r9 = r10 - r6
                int r15 = r4 + -1
                if (r9 != r15) goto L_0x0138
                if (r6 <= 0) goto L_0x00fa
                byte[] r4 = r0.tail
                byte r4 = r4[r7]
                r7 = 1
                goto L_0x00fc
            L_0x00fa:
                byte r4 = r18[r10]
            L_0x00fc:
                r4 = r4 & 255(0xff, float:3.57E-43)
                int r4 = r4 << r11
                int r6 = r0.tailLen
                int r6 = r6 - r7
                r0.tailLen = r6
                int r6 = r5 + 1
                int r7 = r4 >> 6
                r7 = r7 & 63
                byte r7 = r1[r7]
                r2[r5] = r7
                int r5 = r6 + 1
                r4 = r4 & 63
                byte r1 = r1[r4]
                r2[r6] = r1
                boolean r1 = r0.do_padding
                if (r1 == 0) goto L_0x0124
                int r1 = r5 + 1
                r4 = 61
                r2[r5] = r4
                int r5 = r1 + 1
                r2[r1] = r4
            L_0x0124:
                boolean r1 = r0.do_newline
                if (r1 == 0) goto L_0x01e6
                boolean r1 = r0.do_cr
                if (r1 == 0) goto L_0x0131
                int r1 = r5 + 1
                r2[r5] = r12
                goto L_0x0132
            L_0x0131:
                r1 = r5
            L_0x0132:
                int r5 = r1 + 1
                r2[r1] = r13
                goto L_0x01e6
            L_0x0138:
                int r9 = r10 - r6
                int r4 = r4 - r14
                if (r9 != r4) goto L_0x01a1
                if (r6 <= r8) goto L_0x0145
                byte[] r4 = r0.tail
                byte r4 = r4[r7]
                r7 = 1
                goto L_0x014b
            L_0x0145:
                int r4 = r10 + 1
                byte r6 = r18[r10]
                r10 = r4
                r4 = r6
            L_0x014b:
                r4 = r4 & 255(0xff, float:3.57E-43)
                int r4 = r4 << r13
                int r6 = r0.tailLen
                if (r6 <= 0) goto L_0x015a
                byte[] r6 = r0.tail
                int r9 = r7 + 1
                byte r6 = r6[r7]
                r7 = r9
                goto L_0x015c
            L_0x015a:
                byte r6 = r18[r10]
            L_0x015c:
                r6 = r6 & 255(0xff, float:3.57E-43)
                int r6 = r6 << r14
                r4 = r4 | r6
                int r6 = r0.tailLen
                int r6 = r6 - r7
                r0.tailLen = r6
                int r6 = r5 + 1
                int r7 = r4 >> 12
                r7 = r7 & 63
                byte r7 = r1[r7]
                r2[r5] = r7
                int r5 = r6 + 1
                int r7 = r4 >> 6
                r7 = r7 & 63
                byte r7 = r1[r7]
                r2[r6] = r7
                int r6 = r5 + 1
                r4 = r4 & 63
                byte r1 = r1[r4]
                r2[r5] = r1
                boolean r1 = r0.do_padding
                if (r1 == 0) goto L_0x018c
                int r1 = r6 + 1
                r4 = 61
                r2[r6] = r4
                goto L_0x018d
            L_0x018c:
                r1 = r6
            L_0x018d:
                boolean r4 = r0.do_newline
                if (r4 == 0) goto L_0x019f
                boolean r4 = r0.do_cr
                if (r4 == 0) goto L_0x019a
                int r4 = r1 + 1
                r2[r1] = r12
                r1 = r4
            L_0x019a:
                int r4 = r1 + 1
                r2[r1] = r13
                r1 = r4
            L_0x019f:
                r5 = r1
                goto L_0x01e6
            L_0x01a1:
                boolean r1 = r0.do_newline
                if (r1 == 0) goto L_0x01e6
                if (r5 <= 0) goto L_0x01e6
                r1 = 19
                if (r3 == r1) goto L_0x01e6
                boolean r1 = r0.do_cr
                if (r1 == 0) goto L_0x01b4
                int r1 = r5 + 1
                r2[r5] = r12
                goto L_0x01b5
            L_0x01b4:
                r1 = r5
            L_0x01b5:
                int r4 = r1 + 1
                r2[r1] = r13
                r5 = r4
                goto L_0x01e6
            L_0x01bb:
                int r1 = r4 + -1
                if (r10 != r1) goto L_0x01cc
                byte[] r1 = r0.tail
                int r2 = r0.tailLen
                int r4 = r2 + 1
                r0.tailLen = r4
                byte r4 = r18[r10]
                r1[r2] = r4
                goto L_0x01e6
            L_0x01cc:
                int r4 = r4 - r14
                if (r10 != r4) goto L_0x01e6
                byte[] r1 = r0.tail
                int r2 = r0.tailLen
                int r4 = r2 + 1
                r0.tailLen = r4
                byte r4 = r18[r10]
                r1[r2] = r4
                int r2 = r0.tailLen
                int r4 = r2 + 1
                r0.tailLen = r4
                int r10 = r10 + r8
                byte r4 = r18[r10]
                r1[r2] = r4
            L_0x01e6:
                r0.op = r5
                r0.count = r3
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.Base64.Encoder.process(byte[], int, int, boolean):boolean");
        }
    }

    public static byte[] decode(String str, int i) {
        return decode(str.getBytes(), i);
    }

    public static byte[] decode(byte[] bArr, int i) {
        return decode(bArr, 0, bArr.length, i);
    }

    public static byte[] decode(byte[] bArr, int i, int i2, int i3) {
        Decoder decoder = new Decoder(i3, new byte[((i2 * 3) / 4)]);
        if (!decoder.process(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (decoder.op == decoder.output.length) {
            return decoder.output;
        } else {
            byte[] bArr2 = new byte[decoder.op];
            System.arraycopy(decoder.output, 0, bArr2, 0, decoder.op);
            return bArr2;
        }
    }

    public static String encodeToString(byte[] bArr, int i) {
        try {
            return new String(encode(bArr, i), C.ASCII_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static String encodeToString(byte[] bArr, int i, int i2, int i3) {
        try {
            return new String(encode(bArr, i, i2, i3), C.ASCII_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, bArr.length, i);
    }

    public static byte[] encode(byte[] bArr, int i, int i2, int i3) {
        Encoder encoder = new Encoder(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!encoder.do_padding) {
            switch (i2 % 3) {
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (encoder.do_newline && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (encoder.do_cr ? 2 : 1);
        }
        encoder.output = new byte[i4];
        encoder.process(bArr, i, i2, true);
        return encoder.output;
    }

    private Base64() {
    }
}
