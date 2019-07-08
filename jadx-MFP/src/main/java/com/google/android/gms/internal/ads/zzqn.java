package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

public final class zzqn {
    public final int zzakx;
    public final List<byte[]> zzzl;

    public static zzqn zzi(zzpx zzpx) throws zzfx {
        List list;
        try {
            zzpx.zzbl(21);
            int readUnsignedByte = zzpx.readUnsignedByte() & 3;
            int readUnsignedByte2 = zzpx.readUnsignedByte();
            int position = zzpx.getPosition();
            int i = 0;
            int i2 = 0;
            while (i < readUnsignedByte2) {
                zzpx.zzbl(1);
                int readUnsignedShort = zzpx.readUnsignedShort();
                int i3 = i2;
                for (int i4 = 0; i4 < readUnsignedShort; i4++) {
                    int readUnsignedShort2 = zzpx.readUnsignedShort();
                    i3 += readUnsignedShort2 + 4;
                    zzpx.zzbl(readUnsignedShort2);
                }
                i++;
                i2 = i3;
            }
            zzpx.setPosition(position);
            byte[] bArr = new byte[i2];
            int i5 = 0;
            int i6 = 0;
            while (i5 < readUnsignedByte2) {
                zzpx.zzbl(1);
                int readUnsignedShort3 = zzpx.readUnsignedShort();
                int i7 = i6;
                for (int i8 = 0; i8 < readUnsignedShort3; i8++) {
                    int readUnsignedShort4 = zzpx.readUnsignedShort();
                    System.arraycopy(zzpu.zzbhi, 0, bArr, i7, zzpu.zzbhi.length);
                    int length = i7 + zzpu.zzbhi.length;
                    System.arraycopy(zzpx.data, zzpx.getPosition(), bArr, length, readUnsignedShort4);
                    i7 = length + readUnsignedShort4;
                    zzpx.zzbl(readUnsignedShort4);
                }
                i5++;
                i6 = i7;
            }
            if (i2 == 0) {
                list = null;
            } else {
                list = Collections.singletonList(bArr);
            }
            return new zzqn(list, readUnsignedByte + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzfx("Error parsing HEVC config", e);
        }
    }

    private zzqn(List<byte[]> list, int i) {
        this.zzzl = list;
        this.zzakx = i;
    }
}
