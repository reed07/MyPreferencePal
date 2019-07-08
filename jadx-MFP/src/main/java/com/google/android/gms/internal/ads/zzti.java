package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;

@zzark
public final class zzti extends zztd {
    private MessageDigest zzbze;

    public final byte[] zzay(String str) {
        byte[] bArr;
        String[] split = str.split(" ");
        int i = 4;
        if (split.length == 1) {
            int zzba = zzth.zzba(split[0]);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(zzba);
            bArr = allocate.array();
        } else if (split.length < 5) {
            bArr = new byte[(split.length << 1)];
            for (int i2 = 0; i2 < split.length; i2++) {
                int zzba2 = zzth.zzba(split[i2]);
                int i3 = (zzba2 >> 16) ^ (65535 & zzba2);
                byte[] bArr2 = {(byte) i3, (byte) (i3 >> 8)};
                int i4 = i2 << 1;
                bArr[i4] = bArr2[0];
                bArr[i4 + 1] = bArr2[1];
            }
        } else {
            bArr = new byte[split.length];
            for (int i5 = 0; i5 < split.length; i5++) {
                int zzba3 = zzth.zzba(split[i5]);
                bArr[i5] = (byte) ((zzba3 >> 24) ^ (((zzba3 & 255) ^ ((zzba3 >> 8) & 255)) ^ ((zzba3 >> 16) & 255)));
            }
        }
        this.zzbze = zzoc();
        synchronized (this.mLock) {
            if (this.zzbze == null) {
                byte[] bArr3 = new byte[0];
                return bArr3;
            }
            this.zzbze.reset();
            this.zzbze.update(bArr);
            byte[] digest = this.zzbze.digest();
            if (digest.length <= 4) {
                i = digest.length;
            }
            byte[] bArr4 = new byte[i];
            System.arraycopy(digest, 0, bArr4, 0, bArr4.length);
            return bArr4;
        }
    }
}
