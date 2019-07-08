package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;

final class zzbny extends zzbpa {
    private static final byte[] zzfis = new byte[16];

    zzbny(byte[] bArr, int i) throws InvalidKeyException {
        super(bArr, i);
    }

    /* access modifiers changed from: 0000 */
    public final int zzajy() {
        return 12;
    }

    /* access modifiers changed from: 0000 */
    public final ByteBuffer zzd(byte[] bArr, int i) {
        int[] iArr = new int[16];
        System.arraycopy(zzbpa.zzfkg, 0, iArr, 0, zzfkg.length);
        int[] zzm = zzm(ByteBuffer.wrap(this.zzfkh.getBytes()));
        int i2 = 4;
        System.arraycopy(zzm, 0, iArr, 4, zzm.length);
        iArr[12] = i;
        System.arraycopy(zzm(ByteBuffer.wrap(bArr)), 0, iArr, 13, 3);
        int[] iArr2 = (int[]) iArr.clone();
        int i3 = 0;
        while (i3 < 10) {
            zza(iArr2, 0, i2, 8, 12);
            zza(iArr2, 1, 5, 9, 13);
            zza(iArr2, 2, 6, 10, 14);
            zza(iArr2, 3, 7, 11, 15);
            zza(iArr2, 0, 5, 10, 15);
            zza(iArr2, 1, 6, 11, 12);
            zza(iArr2, 2, 7, 8, 13);
            zza(iArr2, 3, 4, 9, 14);
            i3++;
            i2 = 4;
        }
        int i4 = 0;
        for (int i5 = 16; i4 < i5; i5 = 16) {
            iArr[i4] = iArr[i4] + iArr2[i4];
            i4++;
        }
        ByteBuffer order = ByteBuffer.allocate(64).order(ByteOrder.LITTLE_ENDIAN);
        order.asIntBuffer().put(iArr, 0, 16);
        return order;
    }

    private static void zza(int[] iArr, int i, int i2, int i3, int i4) {
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = rotateLeft(iArr[i4] ^ iArr[i], 16);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = rotateLeft(iArr[i2] ^ iArr[i3], 12);
        iArr[i] = iArr[i] + iArr[i2];
        iArr[i4] = rotateLeft(iArr[i] ^ iArr[i4], 8);
        iArr[i3] = iArr[i3] + iArr[i4];
        iArr[i2] = rotateLeft(iArr[i2] ^ iArr[i3], 7);
    }
}
