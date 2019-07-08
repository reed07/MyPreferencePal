package com.amplitude.security;

import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.security.DigestException;
import java.security.MessageDigest;

public final class MD5 extends MessageDigest implements Cloneable {
    private long bytes;
    private int hA;
    private int hB;
    private int hC;
    private int hD;
    private byte[] pad = new byte[64];
    private int padded;

    public int engineGetDigestLength() {
        return 16;
    }

    public MD5() {
        super("MD5");
        init();
    }

    public Object clone() throws CloneNotSupportedException {
        MD5 md5 = (MD5) super.clone();
        md5.pad = (byte[]) this.pad.clone();
        return md5;
    }

    public void engineReset() {
        this.padded = 0;
        this.bytes = 0;
        byte[] bArr = this.pad;
        int i = 60;
        do {
            bArr[i - 4] = 0;
            bArr[i - 3] = 0;
            bArr[i - 2] = 0;
            bArr[i - 1] = 0;
            bArr[i] = 0;
            bArr[i + 1] = 0;
            bArr[i + 2] = 0;
            bArr[i + 3] = 0;
            i -= 8;
        } while (i >= 0);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.hA = 1732584193;
        this.hB = -271733879;
        this.hC = -1732584194;
        this.hD = 271733878;
    }

    public void engineUpdate(byte b) {
        this.bytes++;
        int i = this.padded;
        if (i < 63) {
            byte[] bArr = this.pad;
            this.padded = i + 1;
            bArr[i] = b;
            return;
        }
        byte[] bArr2 = this.pad;
        bArr2[63] = b;
        engineUpdate(bArr2, i);
        this.padded = 0;
    }

    public void engineUpdate(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        this.bytes += (long) i2;
        int i3 = this.padded;
        if (i3 > 0 && i3 + i2 >= 64) {
            int i4 = 64 - i3;
            System.arraycopy(bArr, i, this.pad, i3, i4);
            byte[] bArr2 = this.pad;
            this.padded = 0;
            engineUpdate(bArr2, 0);
            i += i4;
            i2 -= i4;
        }
        while (i2 >= 512) {
            engineUpdate(bArr, i);
            engineUpdate(bArr, i + 64);
            engineUpdate(bArr, i + 128);
            engineUpdate(bArr, i + 192);
            engineUpdate(bArr, i + 256);
            engineUpdate(bArr, i + ModuleDescriptor.MODULE_VERSION);
            engineUpdate(bArr, i + 384);
            engineUpdate(bArr, i + 448);
            i += 512;
            i2 -= 512;
        }
        while (i2 >= 64) {
            engineUpdate(bArr, i);
            i += 64;
            i2 -= 64;
        }
        if (i2 > 0) {
            System.arraycopy(bArr, i, this.pad, this.padded, i2);
            this.padded += i2;
        }
    }

    public byte[] engineDigest() {
        try {
            byte[] bArr = new byte[16];
            engineDigest(bArr, 0, 16);
            return bArr;
        } catch (DigestException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        r3[61] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0034, code lost:
        r3[62] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        r3[63] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0038, code lost:
        engineUpdate(r3, 0);
        r4 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        switch((r4 & 7)) {
            case 0: goto L_0x00a6;
            case 1: goto L_0x008d;
            case 2: goto L_0x0078;
            case 3: goto L_0x0067;
            case 4: goto L_0x005a;
            case 5: goto L_0x004f;
            case 6: goto L_0x0047;
            case 7: goto L_0x0043;
            default: goto L_0x0041;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        r4 = r4 - 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r4 = r4 - 2;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        r4 = r4 - 1;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005a, code lost:
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0067, code lost:
        r4 = r4 + 1;
        r3[r4] = 0;
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0078, code lost:
        r4 = r4 + 2;
        r3[r4 - 1] = 0;
        r3[r4] = 0;
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008d, code lost:
        r4 = r4 + 3;
        r3[r4 - 2] = 0;
        r3[r4 - 1] = 0;
        r3[r4] = 0;
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00a6, code lost:
        r4 = r4 + 4;
        r3[r4 - 3] = 0;
        r3[r4 - 2] = 0;
        r3[r4 - 1] = 0;
        r3[r4] = 0;
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00c2, code lost:
        r4 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00c6, code lost:
        if (r4 > 52) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c8, code lost:
        r3[r4 - 4] = 0;
        r3[r4 - 3] = 0;
        r3[r4 - 2] = 0;
        r3[r4 - 1] = 0;
        r3[r4] = 0;
        r3[r4 + 1] = 0;
        r3[r4 + 2] = 0;
        r3[r4 + 3] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00e7, code lost:
        r12 = r0.bytes;
        r14 = ((int) r12) << 3;
        r3[56] = (byte) r14;
        r3[57] = (byte) (r14 >>> 8);
        r3[58] = (byte) (r14 >>> 16);
        r3[59] = (byte) (r14 >>> 24);
        r4 = (int) (r12 >>> 29);
        r3[60] = (byte) r4;
        r3[61] = (byte) (r4 >>> 8);
        r3[62] = (byte) (r4 >>> 16);
        r3[63] = (byte) (r4 >>> 24);
        engineUpdate(r3, 0);
        r3 = r0.hA;
        r1[r18] = (byte) r3;
        r1[r18 + 1] = (byte) (r3 >>> 8);
        r1[r18 + 2] = (byte) (r3 >>> 16);
        r1[r18 + 3] = (byte) (r3 >>> 24);
        r3 = r18 + 4;
        r4 = r0.hB;
        r1[r3] = (byte) r4;
        r1[r18 + 5] = (byte) (r4 >>> 8);
        r3 = r18 + 10;
        r1[r3 - 4] = (byte) (r4 >>> 16);
        r1[r3 - 3] = (byte) (r4 >>> 24);
        r4 = r3 - 2;
        r5 = r0.hC;
        r1[r4] = (byte) r5;
        r1[r3 - 1] = (byte) (r5 >>> 8);
        r1[r3] = (byte) (r5 >>> 16);
        r1[r3 + 1] = (byte) (r5 >>> 24);
        r4 = r3 + 2;
        r5 = r0.hD;
        r1[r4] = (byte) r5;
        r1[r3 + 3] = (byte) (r5 >>> 8);
        r1[r3 + 4] = (byte) (r5 >>> 16);
        r1[r3 + 5] = (byte) (r5 >>> 24);
        engineReset();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x018d, code lost:
        return 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002c, code lost:
        r3[58] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002e, code lost:
        r3[59] = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        r3[60] = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int engineDigest(byte[] r17, int r18, int r19) throws java.security.DigestException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = 16
            r3 = r19
            if (r3 < r2) goto L_0x0196
            int r3 = r1.length
            int r3 = r3 - r18
            if (r3 < r2) goto L_0x018e
            byte[] r3 = r0.pad
            int r4 = r0.padded
            r5 = -128(0xffffffffffffff80, float:NaN)
            r3[r4] = r5
            r5 = 63
            r6 = 62
            r7 = 61
            r8 = 60
            r9 = 59
            r10 = 58
            r11 = 0
            switch(r4) {
                case 56: goto L_0x0028;
                case 57: goto L_0x002c;
                case 58: goto L_0x002e;
                case 59: goto L_0x0030;
                case 60: goto L_0x0032;
                case 61: goto L_0x0034;
                case 62: goto L_0x0036;
                case 63: goto L_0x0038;
                default: goto L_0x0027;
            }
        L_0x0027:
            goto L_0x003c
        L_0x0028:
            r4 = 57
            r3[r4] = r11
        L_0x002c:
            r3[r10] = r11
        L_0x002e:
            r3[r9] = r11
        L_0x0030:
            r3[r8] = r11
        L_0x0032:
            r3[r7] = r11
        L_0x0034:
            r3[r6] = r11
        L_0x0036:
            r3[r5] = r11
        L_0x0038:
            r0.engineUpdate(r3, r11)
            r4 = -1
        L_0x003c:
            r12 = r4 & 7
            switch(r12) {
                case 0: goto L_0x00a6;
                case 1: goto L_0x008d;
                case 2: goto L_0x0078;
                case 3: goto L_0x0067;
                case 4: goto L_0x005a;
                case 5: goto L_0x004f;
                case 6: goto L_0x0047;
                case 7: goto L_0x0043;
                default: goto L_0x0041;
            }
        L_0x0041:
            goto L_0x00c2
        L_0x0043:
            int r4 = r4 + -3
            goto L_0x00c2
        L_0x0047:
            int r4 = r4 + -2
            int r12 = r4 + 3
            r3[r12] = r11
            goto L_0x00c2
        L_0x004f:
            int r4 = r4 + -1
            int r12 = r4 + 2
            r3[r12] = r11
            int r12 = r4 + 3
            r3[r12] = r11
            goto L_0x00c2
        L_0x005a:
            int r12 = r4 + 1
            r3[r12] = r11
            int r12 = r4 + 2
            r3[r12] = r11
            int r12 = r4 + 3
            r3[r12] = r11
            goto L_0x00c2
        L_0x0067:
            int r4 = r4 + 1
            r3[r4] = r11
            int r12 = r4 + 1
            r3[r12] = r11
            int r12 = r4 + 2
            r3[r12] = r11
            int r12 = r4 + 3
            r3[r12] = r11
            goto L_0x00c2
        L_0x0078:
            int r4 = r4 + 2
            int r12 = r4 + -1
            r3[r12] = r11
            r3[r4] = r11
            int r12 = r4 + 1
            r3[r12] = r11
            int r12 = r4 + 2
            r3[r12] = r11
            int r12 = r4 + 3
            r3[r12] = r11
            goto L_0x00c2
        L_0x008d:
            int r4 = r4 + 3
            int r12 = r4 + -2
            r3[r12] = r11
            int r12 = r4 + -1
            r3[r12] = r11
            r3[r4] = r11
            int r12 = r4 + 1
            r3[r12] = r11
            int r12 = r4 + 2
            r3[r12] = r11
            int r12 = r4 + 3
            r3[r12] = r11
            goto L_0x00c2
        L_0x00a6:
            int r4 = r4 + 4
            int r12 = r4 + -3
            r3[r12] = r11
            int r12 = r4 + -2
            r3[r12] = r11
            int r12 = r4 + -1
            r3[r12] = r11
            r3[r4] = r11
            int r12 = r4 + 1
            r3[r12] = r11
            int r12 = r4 + 2
            r3[r12] = r11
            int r12 = r4 + 3
            r3[r12] = r11
        L_0x00c2:
            int r4 = r4 + 8
            r12 = 52
            if (r4 > r12) goto L_0x00e7
            int r12 = r4 + -4
            r3[r12] = r11
            int r12 = r4 + -3
            r3[r12] = r11
            int r12 = r4 + -2
            r3[r12] = r11
            int r12 = r4 + -1
            r3[r12] = r11
            r3[r4] = r11
            int r12 = r4 + 1
            r3[r12] = r11
            int r12 = r4 + 2
            r3[r12] = r11
            int r12 = r4 + 3
            r3[r12] = r11
            goto L_0x00c2
        L_0x00e7:
            r4 = 56
            long r12 = r0.bytes
            int r14 = (int) r12
            int r14 = r14 << 3
            byte r15 = (byte) r14
            r3[r4] = r15
            r4 = 57
            int r15 = r14 >>> 8
            byte r15 = (byte) r15
            r3[r4] = r15
            int r4 = r14 >>> 16
            byte r4 = (byte) r4
            r3[r10] = r4
            int r4 = r14 >>> 24
            byte r4 = (byte) r4
            r3[r9] = r4
            r4 = 29
            long r9 = r12 >>> r4
            int r4 = (int) r9
            byte r9 = (byte) r4
            r3[r8] = r9
            int r8 = r4 >>> 8
            byte r8 = (byte) r8
            r3[r7] = r8
            int r7 = r4 >>> 16
            byte r7 = (byte) r7
            r3[r6] = r7
            int r4 = r4 >>> 24
            byte r4 = (byte) r4
            r3[r5] = r4
            r0.engineUpdate(r3, r11)
            int r3 = r0.hA
            byte r4 = (byte) r3
            r1[r18] = r4
            int r4 = r18 + 1
            int r5 = r3 >>> 8
            byte r5 = (byte) r5
            r1[r4] = r5
            int r4 = r18 + 2
            int r5 = r3 >>> 16
            byte r5 = (byte) r5
            r1[r4] = r5
            int r4 = r18 + 3
            int r3 = r3 >>> 24
            byte r3 = (byte) r3
            r1[r4] = r3
            int r3 = r18 + 4
            int r4 = r0.hB
            byte r5 = (byte) r4
            r1[r3] = r5
            int r3 = r18 + 5
            int r5 = r4 >>> 8
            byte r5 = (byte) r5
            r1[r3] = r5
            int r3 = r18 + 10
            int r5 = r3 + -4
            int r6 = r4 >>> 16
            byte r6 = (byte) r6
            r1[r5] = r6
            int r5 = r3 + -3
            int r4 = r4 >>> 24
            byte r4 = (byte) r4
            r1[r5] = r4
            int r4 = r3 + -2
            int r5 = r0.hC
            byte r6 = (byte) r5
            r1[r4] = r6
            int r4 = r3 + -1
            int r6 = r5 >>> 8
            byte r6 = (byte) r6
            r1[r4] = r6
            int r4 = r5 >>> 16
            byte r4 = (byte) r4
            r1[r3] = r4
            int r4 = r3 + 1
            int r5 = r5 >>> 24
            byte r5 = (byte) r5
            r1[r4] = r5
            int r4 = r3 + 2
            int r5 = r0.hD
            byte r6 = (byte) r5
            r1[r4] = r6
            int r4 = r3 + 3
            int r6 = r5 >>> 8
            byte r6 = (byte) r6
            r1[r4] = r6
            int r4 = r3 + 4
            int r6 = r5 >>> 16
            byte r6 = (byte) r6
            r1[r4] = r6
            int r3 = r3 + 5
            int r4 = r5 >>> 24
            byte r4 = (byte) r4
            r1[r3] = r4
            r16.engineReset()
            return r2
        L_0x018e:
            java.security.DigestException r1 = new java.security.DigestException
            java.lang.String r2 = "insufficient space in output buffer to store the digest"
            r1.<init>(r2)
            throw r1
        L_0x0196:
            java.security.DigestException r1 = new java.security.DigestException
            java.lang.String r2 = "partial digests not returned"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.security.MD5.engineDigest(byte[], int, int):int");
    }

    /* JADX WARNING: type inference failed for: r28v0, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r5v0, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v9, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r8v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r10v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r10v5, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r11v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r12v8, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r13v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v5, types: [byte] */
    /* JADX WARNING: type inference failed for: r14v9, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r6v13, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v4, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v8, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v11, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v14, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v18, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v22, types: [byte] */
    /* JADX WARNING: type inference failed for: r2v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v25, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v29, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v33, types: [byte] */
    /* JADX WARNING: type inference failed for: r0v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v35, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v39, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v43, types: [byte] */
    /* JADX WARNING: type inference failed for: r1v1, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v46, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v50, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v54, types: [byte] */
    /* JADX WARNING: type inference failed for: r0v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v57, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v61, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v64, types: [byte] */
    /* JADX WARNING: type inference failed for: r11v6, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v67, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v71, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v75, types: [byte] */
    /* JADX WARNING: type inference failed for: r1v7, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v78, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v82, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v86, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v6, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v88, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v92, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v96, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v12, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v99, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v103, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v107, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v18, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v110, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v114, types: [byte, int] */
    /* JADX WARNING: type inference failed for: r15v117, types: [byte] */
    /* JADX WARNING: type inference failed for: r13v24, types: [byte] */
    /* JADX WARNING: type inference failed for: r15v120, types: [byte] */
    /* JADX WARNING: type inference failed for: r9v11, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r0v7, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r10v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r10v5, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r11v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r11v6, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r12v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r12v4, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r12v8, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r13v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r13v12, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r13v18, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r13v24, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r13v6, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r14v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r14v5, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r14v9, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v103, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v107, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v11, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v110, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v114, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v117, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v120, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v14, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v18, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v22, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v25, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v29, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v33, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v35, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v39, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v4, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v43, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v46, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v50, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v54, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v57, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v61, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v64, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v67, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v71, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v75, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v78, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v8, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v82, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v86, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v88, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v92, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v96, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r15v99, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r1v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r1v7, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r2v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r3v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r5v0, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r6v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r6v13, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r6v5, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r6v9, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r8v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r9v1, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=null, for r9v11, types: [byte, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r28v0, types: [byte[]] */
    /* JADX WARNING: Unknown variable types count: 65 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void engineUpdate(byte[] r28, int r29) {
        /*
            r27 = this;
            r0 = r27
            int r1 = r0.hB
            int r2 = r0.hC
            int r3 = r0.hD
            r4 = r2 ^ r3
            r4 = r4 & r1
            r4 = r4 ^ r3
            byte r5 = r28[r29]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r6 = r29 + 1
            byte r6 = r28[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            r5 = r5 | r6
            int r6 = r29 + 2
            byte r6 = r28[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 16
            r5 = r5 | r6
            int r6 = r29 + 3
            byte r6 = r28[r6]
            int r6 = r6 << 24
            r5 = r5 | r6
            int r4 = r4 + r5
            r6 = -680876936(0xffffffffd76aa478, float:-2.57992109E14)
            int r4 = r4 + r6
            int r6 = r0.hA
            int r4 = r4 + r6
            int r7 = r4 << 7
            int r4 = r4 >>> 25
            r4 = r4 | r7
            int r4 = r4 + r1
            r7 = r1 ^ r2
            r7 = r7 & r4
            r7 = r7 ^ r2
            int r8 = r29 + 4
            byte r8 = r28[r8]
            r8 = r8 & 255(0xff, float:3.57E-43)
            int r9 = r29 + 5
            byte r9 = r28[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r9 = r9 << 8
            r8 = r8 | r9
            int r9 = r29 + 10
            int r10 = r9 + -4
            byte r10 = r28[r10]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r10 = r10 << 16
            r8 = r8 | r10
            int r10 = r9 + -3
            byte r10 = r28[r10]
            int r10 = r10 << 24
            r8 = r8 | r10
            int r7 = r7 + r8
            r10 = -389564586(0xffffffffe8c7b756, float:-7.545063E24)
            int r7 = r7 + r10
            int r7 = r7 + r3
            int r10 = r7 << 12
            int r7 = r7 >>> 20
            r7 = r7 | r10
            int r7 = r7 + r4
            r10 = r4 ^ r1
            r10 = r10 & r7
            r10 = r10 ^ r1
            int r11 = r9 + -2
            byte r11 = r28[r11]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r12 = r9 + -1
            byte r12 = r28[r12]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << 8
            r11 = r11 | r12
            byte r12 = r28[r9]
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r12 = r12 << 16
            r11 = r11 | r12
            int r12 = r9 + 1
            byte r12 = r28[r12]
            int r12 = r12 << 24
            r11 = r11 | r12
            int r10 = r10 + r11
            r12 = 606105819(0x242070db, float:3.4790062E-17)
            int r10 = r10 + r12
            int r10 = r10 + r2
            int r12 = r10 << 17
            int r10 = r10 >>> 15
            r10 = r10 | r12
            int r10 = r10 + r7
            r12 = r7 ^ r4
            r12 = r12 & r10
            r12 = r12 ^ r4
            int r13 = r9 + 2
            byte r13 = r28[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r14 = r9 + 3
            byte r14 = r28[r14]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << 8
            r13 = r13 | r14
            int r14 = r9 + 4
            byte r14 = r28[r14]
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 << 16
            r13 = r13 | r14
            int r14 = r9 + 5
            byte r14 = r28[r14]
            int r14 = r14 << 24
            r13 = r13 | r14
            int r12 = r12 + r13
            r14 = -1044525330(0xffffffffc1bdceee, float:-23.72604)
            int r12 = r12 + r14
            int r12 = r12 + r1
            int r14 = r12 << 22
            int r12 = r12 >>> 10
            r12 = r12 | r14
            int r12 = r12 + r10
            r14 = r10 ^ r7
            r14 = r14 & r12
            r14 = r14 ^ r7
            int r9 = r9 + 10
            int r15 = r9 + -4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + -3
            r17 = r6
            byte r6 = r28[r16]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            r6 = r6 | r15
            int r15 = r9 + -2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r6 = r6 | r15
            int r15 = r9 + -1
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r6 = r6 | r15
            int r14 = r14 + r6
            r15 = -176418897(0xfffffffff57c0faf, float:-3.1952561E32)
            int r14 = r14 + r15
            int r14 = r14 + r4
            int r4 = r14 << 7
            int r14 = r14 >>> 25
            r4 = r4 | r14
            int r4 = r4 + r12
            r14 = r12 ^ r10
            r14 = r14 & r4
            r14 = r14 ^ r10
            byte r15 = r28[r9]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + 1
            r18 = r3
            byte r3 = r28[r16]
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 8
            r3 = r3 | r15
            int r15 = r9 + 2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r3 = r3 | r15
            int r15 = r9 + 3
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r3 = r3 | r15
            int r14 = r14 + r3
            r15 = 1200080426(0x4787c62a, float:69516.33)
            int r14 = r14 + r15
            int r14 = r14 + r7
            int r7 = r14 << 12
            int r14 = r14 >>> 20
            r7 = r7 | r14
            int r7 = r7 + r4
            r14 = r4 ^ r12
            r14 = r14 & r7
            r14 = r14 ^ r12
            int r15 = r9 + 4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + 5
            r19 = r2
            byte r2 = r28[r16]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r2 = r2 | r15
            int r9 = r9 + 10
            int r15 = r9 + -4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r2 = r2 | r15
            int r15 = r9 + -3
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r2 = r2 | r15
            int r14 = r14 + r2
            r15 = -1473231341(0xffffffffa8304613, float:-9.7851575E-15)
            int r14 = r14 + r15
            int r14 = r14 + r10
            int r10 = r14 << 17
            int r14 = r14 >>> 15
            r10 = r10 | r14
            int r10 = r10 + r7
            r14 = r7 ^ r4
            r14 = r14 & r10
            r14 = r14 ^ r4
            int r15 = r9 + -2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + -1
            byte r0 = r28[r16]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 << 8
            r0 = r0 | r15
            byte r15 = r28[r9]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r0 = r0 | r15
            int r15 = r9 + 1
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r0 = r0 | r15
            int r14 = r14 + r0
            r15 = -45705983(0xfffffffffd469501, float:-1.6497551E37)
            int r14 = r14 + r15
            int r14 = r14 + r12
            int r12 = r14 << 22
            int r14 = r14 >>> 10
            r12 = r12 | r14
            int r12 = r12 + r10
            r14 = r10 ^ r7
            r14 = r14 & r12
            r14 = r14 ^ r7
            int r15 = r9 + 2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + 3
            r20 = r1
            byte r1 = r28[r16]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 8
            r1 = r1 | r15
            int r15 = r9 + 4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r1 = r1 | r15
            int r15 = r9 + 5
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r1 = r1 | r15
            int r14 = r14 + r1
            r15 = 1770035416(0x698098d8, float:1.9433036E25)
            int r14 = r14 + r15
            int r14 = r14 + r4
            int r4 = r14 << 7
            int r14 = r14 >>> 25
            r4 = r4 | r14
            int r4 = r4 + r12
            r14 = r12 ^ r10
            r14 = r14 & r4
            r14 = r14 ^ r10
            int r9 = r9 + 10
            int r15 = r9 + -4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + -3
            r29 = r0
            byte r0 = r28[r16]
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r0 = r0 << 8
            r0 = r0 | r15
            int r15 = r9 + -2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r0 = r0 | r15
            int r15 = r9 + -1
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r0 = r0 | r15
            int r14 = r14 + r0
            r15 = -1958414417(0xffffffff8b44f7af, float:-3.7934563E-32)
            int r14 = r14 + r15
            int r14 = r14 + r7
            int r7 = r14 << 12
            int r14 = r14 >>> 20
            r7 = r7 | r14
            int r7 = r7 + r4
            r14 = r4 ^ r12
            r14 = r14 & r7
            r14 = r14 ^ r12
            byte r15 = r28[r9]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + 1
            r21 = r11
            byte r11 = r28[r16]
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r11 = r11 << 8
            r11 = r11 | r15
            int r15 = r9 + 2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r11 = r11 | r15
            int r15 = r9 + 3
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r11 = r11 | r15
            int r14 = r14 + r11
            r15 = -42063(0xffffffffffff5bb1, float:NaN)
            int r14 = r14 + r15
            int r14 = r14 + r10
            int r10 = r14 << 17
            int r14 = r14 >>> 15
            r10 = r10 | r14
            int r10 = r10 + r7
            r14 = r7 ^ r4
            r14 = r14 & r10
            r14 = r14 ^ r4
            int r15 = r9 + 4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + 5
            r22 = r1
            byte r1 = r28[r16]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 8
            r1 = r1 | r15
            int r9 = r9 + 10
            int r15 = r9 + -4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r1 = r1 | r15
            int r15 = r9 + -3
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r1 = r1 | r15
            int r14 = r14 + r1
            r15 = -1990404162(0xffffffff895cd7be, float:-2.6582978E-33)
            int r14 = r14 + r15
            int r14 = r14 + r12
            int r12 = r14 << 22
            int r14 = r14 >>> 10
            r12 = r12 | r14
            int r12 = r12 + r10
            r14 = r10 ^ r7
            r14 = r14 & r12
            r14 = r14 ^ r7
            int r15 = r9 + -2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + -1
            r23 = r13
            byte r13 = r28[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 8
            r13 = r13 | r15
            byte r15 = r28[r9]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r13 = r13 | r15
            int r15 = r9 + 1
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r13 = r13 | r15
            int r14 = r14 + r13
            r15 = 1804603682(0x6b901122, float:3.4833245E26)
            int r14 = r14 + r15
            int r14 = r14 + r4
            int r4 = r14 << 7
            int r14 = r14 >>> 25
            r4 = r4 | r14
            int r4 = r4 + r12
            r14 = r12 ^ r10
            r14 = r14 & r4
            r14 = r14 ^ r10
            int r15 = r9 + 2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + 3
            r24 = r13
            byte r13 = r28[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 8
            r13 = r13 | r15
            int r15 = r9 + 4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r13 = r13 | r15
            int r15 = r9 + 5
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r13 = r13 | r15
            int r14 = r14 + r13
            r15 = -40341101(0xfffffffffd987193, float:-2.5329046E37)
            int r14 = r14 + r15
            int r14 = r14 + r7
            int r7 = r14 << 12
            int r14 = r14 >>> 20
            r7 = r7 | r14
            int r7 = r7 + r4
            r14 = r4 ^ r12
            r14 = r14 & r7
            r14 = r14 ^ r12
            int r9 = r9 + 10
            int r15 = r9 + -4
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + -3
            r25 = r13
            byte r13 = r28[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 8
            r13 = r13 | r15
            int r15 = r9 + -2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r13 = r13 | r15
            int r15 = r9 + -1
            byte r15 = r28[r15]
            int r15 = r15 << 24
            r13 = r13 | r15
            int r14 = r14 + r13
            r15 = -1502002290(0xffffffffa679438e, float:-8.6480783E-16)
            int r14 = r14 + r15
            int r14 = r14 + r10
            int r10 = r14 << 17
            int r14 = r14 >>> 15
            r10 = r10 | r14
            int r10 = r10 + r7
            r14 = r7 ^ r4
            r14 = r14 & r10
            r14 = r14 ^ r4
            byte r15 = r28[r9]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r16 = r9 + 1
            r26 = r13
            byte r13 = r28[r16]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r13 = r13 << 8
            r13 = r13 | r15
            int r15 = r9 + 2
            byte r15 = r28[r15]
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r15 = r15 << 16
            r13 = r13 | r15
            int r9 = r9 + 3
            byte r9 = r28[r9]
            int r9 = r9 << 24
            r9 = r9 | r13
            int r14 = r14 + r9
            r13 = 1236535329(0x49b40821, float:1474820.1)
            int r14 = r14 + r13
            int r14 = r14 + r12
            int r12 = r14 << 22
            int r13 = r14 >>> 10
            r12 = r12 | r13
            int r12 = r12 + r10
            r13 = r10 ^ r12
            r13 = r13 & r7
            r13 = r13 ^ r10
            int r13 = r13 + r8
            r14 = -165796510(0xfffffffff61e2562, float:-8.018956E32)
            int r13 = r13 + r14
            int r13 = r13 + r4
            int r4 = r13 << 5
            int r13 = r13 >>> 27
            r4 = r4 | r13
            int r4 = r4 + r12
            r13 = r12 ^ r4
            r13 = r13 & r10
            r13 = r13 ^ r12
            int r13 = r13 + r2
            r14 = -1069501632(0xffffffffc040b340, float:-3.0109406)
            int r13 = r13 + r14
            int r13 = r13 + r7
            int r7 = r13 << 9
            int r13 = r13 >>> 23
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r7
            r13 = r13 & r12
            r13 = r13 ^ r4
            int r13 = r13 + r1
            r14 = 643717713(0x265e5a51, float:7.7144124E-16)
            int r13 = r13 + r14
            int r13 = r13 + r10
            int r10 = r13 << 14
            int r13 = r13 >>> 18
            r10 = r10 | r13
            int r10 = r10 + r7
            r13 = r7 ^ r10
            r13 = r13 & r4
            r13 = r13 ^ r7
            int r13 = r13 + r5
            r14 = -373897302(0xffffffffe9b6c7aa, float:-2.7620923E25)
            int r13 = r13 + r14
            int r13 = r13 + r12
            int r12 = r13 << 20
            int r13 = r13 >>> 12
            r12 = r12 | r13
            int r12 = r12 + r10
            r13 = r10 ^ r12
            r13 = r13 & r7
            r13 = r13 ^ r10
            int r13 = r13 + r3
            r14 = -701558691(0xffffffffd62f105d, float:-4.8121204E13)
            int r13 = r13 + r14
            int r13 = r13 + r4
            int r4 = r13 << 5
            int r13 = r13 >>> 27
            r4 = r4 | r13
            int r4 = r4 + r12
            r13 = r12 ^ r4
            r13 = r13 & r10
            r13 = r13 ^ r12
            int r13 = r13 + r11
            r14 = 38016083(0x2441453, float:1.4405639E-37)
            int r13 = r13 + r14
            int r13 = r13 + r7
            int r7 = r13 << 9
            int r13 = r13 >>> 23
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r7
            r13 = r13 & r12
            r13 = r13 ^ r4
            int r13 = r13 + r9
            r14 = -660478335(0xffffffffd8a1e681, float:-1.42409103E15)
            int r13 = r13 + r14
            int r13 = r13 + r10
            int r10 = r13 << 14
            int r13 = r13 >>> 18
            r10 = r10 | r13
            int r10 = r10 + r7
            r13 = r7 ^ r10
            r13 = r13 & r4
            r13 = r13 ^ r7
            int r13 = r13 + r6
            r14 = -405537848(0xffffffffe7d3fbc8, float:-2.0021277E24)
            int r13 = r13 + r14
            int r13 = r13 + r12
            int r12 = r13 << 20
            int r13 = r13 >>> 12
            r12 = r12 | r13
            int r12 = r12 + r10
            r13 = r10 ^ r12
            r13 = r13 & r7
            r13 = r13 ^ r10
            int r13 = r13 + r0
            r14 = 568446438(0x21e1cde6, float:1.5301094E-18)
            int r13 = r13 + r14
            int r13 = r13 + r4
            int r4 = r13 << 5
            int r13 = r13 >>> 27
            r4 = r4 | r13
            int r4 = r4 + r12
            r13 = r12 ^ r4
            r13 = r13 & r10
            r13 = r13 ^ r12
            int r13 = r13 + r26
            r14 = -1019803690(0xffffffffc33707d6, float:-183.03061)
            int r13 = r13 + r14
            int r13 = r13 + r7
            int r7 = r13 << 9
            int r13 = r13 >>> 23
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r7
            r13 = r13 & r12
            r13 = r13 ^ r4
            int r13 = r13 + r23
            r14 = -187363961(0xfffffffff4d50d87, float:-1.3503828E32)
            int r13 = r13 + r14
            int r13 = r13 + r10
            int r10 = r13 << 14
            int r13 = r13 >>> 18
            r10 = r10 | r13
            int r10 = r10 + r7
            r13 = r7 ^ r10
            r13 = r13 & r4
            r13 = r13 ^ r7
            int r13 = r13 + r22
            r14 = 1163531501(0x455a14ed, float:3489.3079)
            int r13 = r13 + r14
            int r13 = r13 + r12
            int r12 = r13 << 20
            int r13 = r13 >>> 12
            r12 = r12 | r13
            int r12 = r12 + r10
            r13 = r10 ^ r12
            r13 = r13 & r7
            r13 = r13 ^ r10
            int r13 = r13 + r25
            r14 = -1444681467(0xffffffffa9e3e905, float:-1.01212475E-13)
            int r13 = r13 + r14
            int r13 = r13 + r4
            int r4 = r13 << 5
            int r13 = r13 >>> 27
            r4 = r4 | r13
            int r4 = r4 + r12
            r13 = r12 ^ r4
            r13 = r13 & r10
            r13 = r13 ^ r12
            int r13 = r13 + r21
            r14 = -51403784(0xfffffffffcefa3f8, float:-9.954277E36)
            int r13 = r13 + r14
            int r13 = r13 + r7
            int r7 = r13 << 9
            int r13 = r13 >>> 23
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r7
            r13 = r13 & r12
            r13 = r13 ^ r4
            int r13 = r13 + r29
            r14 = 1735328473(0x676f02d9, float:1.1286981E24)
            int r13 = r13 + r14
            int r13 = r13 + r10
            int r10 = r13 << 14
            int r13 = r13 >>> 18
            r10 = r10 | r13
            int r10 = r10 + r7
            r13 = r7 ^ r10
            r13 = r13 & r4
            r13 = r13 ^ r7
            int r13 = r13 + r24
            r14 = -1926607734(0xffffffff8d2a4c8a, float:-5.2477425E-31)
            int r13 = r13 + r14
            int r13 = r13 + r12
            int r12 = r13 << 20
            int r13 = r13 >>> 12
            r12 = r12 | r13
            int r12 = r12 + r10
            r13 = r10 ^ r12
            r13 = r13 ^ r7
            int r13 = r13 + r3
            r14 = -378558(0xfffffffffffa3942, float:NaN)
            int r13 = r13 + r14
            int r13 = r13 + r4
            int r4 = r13 << 4
            int r13 = r13 >>> 28
            r4 = r4 | r13
            int r4 = r4 + r12
            r13 = r12 ^ r4
            r13 = r13 ^ r10
            int r13 = r13 + r22
            r14 = -2022574463(0xffffffff8771f681, float:-1.8203266E-34)
            int r13 = r13 + r14
            int r13 = r13 + r7
            int r7 = r13 << 11
            int r13 = r13 >>> 21
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r7
            r13 = r13 ^ r12
            int r13 = r13 + r1
            r14 = 1839030562(0x6d9d6122, float:6.0883216E27)
            int r13 = r13 + r14
            int r13 = r13 + r10
            int r10 = r13 << 16
            int r13 = r13 >>> 16
            r10 = r10 | r13
            int r10 = r10 + r7
            r13 = r7 ^ r10
            r13 = r13 ^ r4
            int r13 = r13 + r26
            r14 = -35309556(0xfffffffffde5380c, float:-3.8085528E37)
            int r13 = r13 + r14
            int r13 = r13 + r12
            int r12 = r13 << 23
            int r13 = r13 >>> 9
            r12 = r12 | r13
            int r12 = r12 + r10
            r13 = r10 ^ r12
            r13 = r13 ^ r7
            int r13 = r13 + r8
            r14 = -1530992060(0xffffffffa4beea44, float:-8.2796227E-17)
            int r13 = r13 + r14
            int r13 = r13 + r4
            int r4 = r13 << 4
            int r13 = r13 >>> 28
            r4 = r4 | r13
            int r4 = r4 + r12
            r13 = r12 ^ r4
            r13 = r13 ^ r10
            int r13 = r13 + r6
            r14 = 1272893353(0x4bdecfa9, float:2.9204306E7)
            int r13 = r13 + r14
            int r13 = r13 + r7
            int r7 = r13 << 11
            int r13 = r13 >>> 21
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r7
            r13 = r13 ^ r12
            int r13 = r13 + r29
            r14 = -155497632(0xfffffffff6bb4b60, float:-1.8993912E33)
            int r13 = r13 + r14
            int r13 = r13 + r10
            int r10 = r13 << 16
            int r13 = r13 >>> 16
            r10 = r10 | r13
            int r10 = r10 + r7
            r13 = r7 ^ r10
            r13 = r13 ^ r4
            int r13 = r13 + r11
            r14 = -1094730640(0xffffffffbebfbc70, float:-0.37448454)
            int r13 = r13 + r14
            int r13 = r13 + r12
            int r12 = r13 << 23
            int r13 = r13 >>> 9
            r12 = r12 | r13
            int r12 = r12 + r10
            r13 = r10 ^ r12
            r13 = r13 ^ r7
            int r13 = r13 + r25
            r14 = 681279174(0x289b7ec6, float:1.7263436E-14)
            int r13 = r13 + r14
            int r13 = r13 + r4
            int r4 = r13 << 4
            int r13 = r13 >>> 28
            r4 = r4 | r13
            int r4 = r4 + r12
            r13 = r12 ^ r4
            r13 = r13 ^ r10
            int r13 = r13 + r5
            r14 = -358537222(0xffffffffeaa127fa, float:-9.741292E25)
            int r13 = r13 + r14
            int r13 = r13 + r7
            int r7 = r13 << 11
            int r13 = r13 >>> 21
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r7
            r13 = r13 ^ r12
            int r13 = r13 + r23
            r14 = -722521979(0xffffffffd4ef3085, float:-8.2184897E12)
            int r13 = r13 + r14
            int r13 = r13 + r10
            int r10 = r13 << 16
            int r13 = r13 >>> 16
            r10 = r10 | r13
            int r10 = r10 + r7
            r13 = r7 ^ r10
            r13 = r13 ^ r4
            int r13 = r13 + r2
            r14 = 76029189(0x4881d05, float:3.2000097E-36)
            int r13 = r13 + r14
            int r13 = r13 + r12
            int r12 = r13 << 23
            int r13 = r13 >>> 9
            r12 = r12 | r13
            int r12 = r12 + r10
            r13 = r10 ^ r12
            r13 = r13 ^ r7
            int r13 = r13 + r0
            r14 = -640364487(0xffffffffd9d4d039, float:-7.4877048E15)
            int r13 = r13 + r14
            int r13 = r13 + r4
            int r4 = r13 << 4
            int r13 = r13 >>> 28
            r4 = r4 | r13
            int r4 = r4 + r12
            r13 = r12 ^ r4
            r13 = r13 ^ r10
            int r13 = r13 + r24
            r14 = -421815835(0xffffffffe6db99e5, float:-5.1851856E23)
            int r13 = r13 + r14
            int r13 = r13 + r7
            int r7 = r13 << 11
            int r13 = r13 >>> 21
            r7 = r7 | r13
            int r7 = r7 + r4
            r13 = r4 ^ r7
            r13 = r13 ^ r12
            int r13 = r13 + r9
            r14 = 530742520(0x1fa27cf8, float:6.881641E-20)
            int r13 = r13 + r14
            int r13 = r13 + r10
            int r10 = r13 << 16
            int r13 = r13 >>> 16
            r10 = r10 | r13
            int r10 = r10 + r7
            r13 = r7 ^ r10
            r13 = r13 ^ r4
            int r13 = r13 + r21
            r14 = -995338651(0xffffffffc4ac5665, float:-1378.6998)
            int r13 = r13 + r14
            int r13 = r13 + r12
            int r12 = r13 << 23
            int r13 = r13 >>> 9
            r12 = r12 | r13
            int r12 = r12 + r10
            int r13 = ~r7
            r13 = r13 | r12
            r13 = r13 ^ r10
            int r13 = r13 + r5
            r5 = -198630844(0xfffffffff4292244, float:-5.3600657E31)
            int r13 = r13 + r5
            int r13 = r13 + r4
            int r4 = r13 << 6
            int r5 = r13 >>> 26
            r4 = r4 | r5
            int r4 = r4 + r12
            int r5 = ~r10
            r5 = r5 | r4
            r5 = r5 ^ r12
            int r5 = r5 + r29
            r13 = 1126891415(0x432aff97, float:170.9984)
            int r5 = r5 + r13
            int r5 = r5 + r7
            int r7 = r5 << 10
            int r5 = r5 >>> 22
            r5 = r5 | r7
            int r5 = r5 + r4
            int r7 = ~r12
            r7 = r7 | r5
            r7 = r7 ^ r4
            int r7 = r7 + r26
            r13 = -1416354905(0xffffffffab9423a7, float:-1.0525928E-12)
            int r7 = r7 + r13
            int r7 = r7 + r10
            int r10 = r7 << 15
            int r7 = r7 >>> 17
            r7 = r7 | r10
            int r7 = r7 + r5
            int r10 = ~r4
            r10 = r10 | r7
            r10 = r10 ^ r5
            int r10 = r10 + r3
            r3 = -57434055(0xfffffffffc93a039, float:-6.132139E36)
            int r10 = r10 + r3
            int r10 = r10 + r12
            int r3 = r10 << 21
            int r10 = r10 >>> 11
            r3 = r3 | r10
            int r3 = r3 + r7
            int r10 = ~r5
            r10 = r10 | r3
            r10 = r10 ^ r7
            int r10 = r10 + r24
            r12 = 1700485571(0x655b59c3, float:6.474088E22)
            int r10 = r10 + r12
            int r10 = r10 + r4
            int r4 = r10 << 6
            int r10 = r10 >>> 26
            r4 = r4 | r10
            int r4 = r4 + r3
            int r10 = ~r7
            r10 = r10 | r4
            r10 = r10 ^ r3
            int r10 = r10 + r23
            r12 = -1894986606(0xffffffff8f0ccc92, float:-6.941932E-30)
            int r10 = r10 + r12
            int r10 = r10 + r5
            int r5 = r10 << 10
            int r10 = r10 >>> 22
            r5 = r5 | r10
            int r5 = r5 + r4
            int r10 = ~r3
            r10 = r10 | r5
            r10 = r10 ^ r4
            int r10 = r10 + r11
            r11 = -1051523(0xffffffffffeff47d, float:NaN)
            int r10 = r10 + r11
            int r10 = r10 + r7
            int r7 = r10 << 15
            int r10 = r10 >>> 17
            r7 = r7 | r10
            int r7 = r7 + r5
            int r10 = ~r4
            r10 = r10 | r7
            r10 = r10 ^ r5
            int r10 = r10 + r8
            r8 = -2054922799(0xffffffff85845dd1, float:-1.2447683E-35)
            int r10 = r10 + r8
            int r10 = r10 + r3
            int r3 = r10 << 21
            int r8 = r10 >>> 11
            r3 = r3 | r8
            int r3 = r3 + r7
            int r8 = ~r5
            r8 = r8 | r3
            r8 = r8 ^ r7
            int r8 = r8 + r22
            r10 = 1873313359(0x6fa87e4f, float:1.0429236E29)
            int r8 = r8 + r10
            int r8 = r8 + r4
            int r4 = r8 << 6
            int r8 = r8 >>> 26
            r4 = r4 | r8
            int r4 = r4 + r3
            int r8 = ~r7
            r8 = r8 | r4
            r8 = r8 ^ r3
            int r8 = r8 + r9
            r9 = -30611744(0xfffffffffe2ce6e0, float:-5.7456497E37)
            int r8 = r8 + r9
            int r8 = r8 + r5
            int r5 = r8 << 10
            int r8 = r8 >>> 22
            r5 = r5 | r8
            int r5 = r5 + r4
            int r8 = ~r3
            r8 = r8 | r5
            r8 = r8 ^ r4
            int r8 = r8 + r2
            r2 = -1560198380(0xffffffffa3014314, float:-7.007308E-18)
            int r8 = r8 + r2
            int r8 = r8 + r7
            int r2 = r8 << 15
            int r7 = r8 >>> 17
            r2 = r2 | r7
            int r2 = r2 + r5
            int r7 = ~r4
            r7 = r7 | r2
            r7 = r7 ^ r5
            int r7 = r7 + r25
            r8 = 1309151649(0x4e0811a1, float:5.7071418E8)
            int r7 = r7 + r8
            int r7 = r7 + r3
            int r3 = r7 << 21
            int r7 = r7 >>> 11
            r3 = r3 | r7
            int r3 = r3 + r2
            int r7 = ~r5
            r7 = r7 | r3
            r7 = r7 ^ r2
            int r7 = r7 + r6
            r6 = -145523070(0xfffffffff7537e82, float:-4.2896114E33)
            int r7 = r7 + r6
            int r7 = r7 + r4
            int r4 = r7 << 6
            int r6 = r7 >>> 26
            r4 = r4 | r6
            int r4 = r4 + r3
            int r6 = ~r2
            r6 = r6 | r4
            r6 = r6 ^ r3
            int r6 = r6 + r1
            r1 = -1120210379(0xffffffffbd3af235, float:-0.045641143)
            int r6 = r6 + r1
            int r6 = r6 + r5
            int r1 = r6 << 10
            int r5 = r6 >>> 22
            r1 = r1 | r5
            int r1 = r1 + r4
            int r5 = ~r3
            r5 = r5 | r1
            r5 = r5 ^ r4
            int r5 = r5 + r21
            r6 = 718787259(0x2ad7d2bb, float:3.8337896E-13)
            int r5 = r5 + r6
            int r5 = r5 + r2
            int r2 = r5 << 15
            int r5 = r5 >>> 17
            r2 = r2 | r5
            int r2 = r2 + r1
            int r5 = r20 + r2
            int r6 = ~r4
            r6 = r6 | r2
            r6 = r6 ^ r1
            int r6 = r6 + r0
            r0 = -343485551(0xffffffffeb86d391, float:-3.259903E26)
            int r6 = r6 + r0
            int r6 = r6 + r3
            int r0 = r6 << 21
            int r3 = r6 >>> 11
            r0 = r0 | r3
            int r5 = r5 + r0
            r0 = r27
            r0.hB = r5
            int r2 = r19 + r2
            r0.hC = r2
            int r3 = r18 + r1
            r0.hD = r3
            int r6 = r17 + r4
            r0.hA = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.security.MD5.engineUpdate(byte[], int):void");
    }
}
