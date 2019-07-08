package com.google.ads.interactivemedia.v3.internal;

/* compiled from: IMASDK */
public final class hs {
    public final boolean a;
    public final String b;
    public final gd c;
    public final int d;
    public final byte[] e;

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        if (r6.equals(com.google.android.exoplayer2.C.CENC_TYPE_cenc) != false) goto L_0x0064;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public hs(boolean r5, java.lang.String r6, int r7, byte[] r8, int r9, int r10, byte[] r11) {
        /*
            r4 = this;
            r4.<init>()
            r0 = 0
            r1 = 1
            if (r7 != 0) goto L_0x0009
            r2 = 1
            goto L_0x000a
        L_0x0009:
            r2 = 0
        L_0x000a:
            if (r11 != 0) goto L_0x000e
            r3 = 1
            goto L_0x000f
        L_0x000e:
            r3 = 0
        L_0x000f:
            r2 = r2 ^ r3
            com.google.ads.interactivemedia.v3.internal.qi.b(r2)
            r4.a = r5
            r4.b = r6
            r4.d = r7
            r4.e = r11
            com.google.ads.interactivemedia.v3.internal.gd r5 = new com.google.ads.interactivemedia.v3.internal.gd
            r7 = 2
            if (r6 != 0) goto L_0x0022
            goto L_0x008e
        L_0x0022:
            r11 = -1
            int r2 = r6.hashCode()
            r3 = 3046605(0x2e7ccd, float:4.269203E-39)
            if (r2 == r3) goto L_0x0059
            r3 = 3046671(0x2e7d0f, float:4.269295E-39)
            if (r2 == r3) goto L_0x004f
            r3 = 3049879(0x2e8997, float:4.273791E-39)
            if (r2 == r3) goto L_0x0046
            r0 = 3049895(0x2e89a7, float:4.273813E-39)
            if (r2 == r0) goto L_0x003c
            goto L_0x0063
        L_0x003c:
            java.lang.String r0 = "cens"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0063
            r0 = 1
            goto L_0x0064
        L_0x0046:
            java.lang.String r2 = "cenc"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto L_0x0063
            goto L_0x0064
        L_0x004f:
            java.lang.String r0 = "cbcs"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0063
            r0 = 3
            goto L_0x0064
        L_0x0059:
            java.lang.String r0 = "cbc1"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x0063
            r0 = 2
            goto L_0x0064
        L_0x0063:
            r0 = -1
        L_0x0064:
            switch(r0) {
                case 0: goto L_0x008e;
                case 1: goto L_0x008e;
                case 2: goto L_0x008d;
                case 3: goto L_0x008d;
                default: goto L_0x0067;
            }
        L_0x0067:
            java.lang.String r7 = "TrackEncryptionBox"
            java.lang.String r11 = java.lang.String.valueOf(r6)
            int r11 = r11.length()
            int r11 = r11 + 68
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r11)
            java.lang.String r11 = "Unsupported protection scheme type '"
            r0.append(r11)
            r0.append(r6)
            java.lang.String r6 = "'. Assuming AES-CTR crypto mode."
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            android.util.Log.w(r7, r6)
            goto L_0x008e
        L_0x008d:
            r1 = 2
        L_0x008e:
            r5.<init>(r1, r8, r9, r10)
            r4.c = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.hs.<init>(boolean, java.lang.String, int, byte[], int, int, byte[]):void");
    }
}
