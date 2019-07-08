package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

public final class zzqh {
    private final int height;
    private final int width;
    public final int zzakx;
    public final float zzbhq;
    public final List<byte[]> zzzl;

    public static zzqh zzg(zzpx zzpx) throws zzfx {
        float f;
        int i;
        int i2;
        try {
            zzpx.zzbl(4);
            int readUnsignedByte = (zzpx.readUnsignedByte() & 3) + 1;
            if (readUnsignedByte != 3) {
                ArrayList arrayList = new ArrayList();
                int readUnsignedByte2 = zzpx.readUnsignedByte() & 31;
                for (int i3 = 0; i3 < readUnsignedByte2; i3++) {
                    arrayList.add(zzh(zzpx));
                }
                int readUnsignedByte3 = zzpx.readUnsignedByte();
                for (int i4 = 0; i4 < readUnsignedByte3; i4++) {
                    arrayList.add(zzh(zzpx));
                }
                if (readUnsignedByte2 > 0) {
                    zzpv zzd = zzpu.zzd((byte[]) arrayList.get(0), readUnsignedByte, ((byte[]) arrayList.get(0)).length);
                    int i5 = zzd.width;
                    int i6 = zzd.height;
                    f = zzd.zzbhq;
                    i2 = i5;
                    i = i6;
                } else {
                    i2 = -1;
                    i = -1;
                    f = 1.0f;
                }
                zzqh zzqh = new zzqh(arrayList, readUnsignedByte, i2, i, f);
                return zzqh;
            }
            throw new IllegalStateException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzfx("Error parsing AVC config", e);
        }
    }

    private zzqh(List<byte[]> list, int i, int i2, int i3, float f) {
        this.zzzl = list;
        this.zzakx = i;
        this.width = i2;
        this.height = i3;
        this.zzbhq = f;
    }

    private static byte[] zzh(zzpx zzpx) {
        int readUnsignedShort = zzpx.readUnsignedShort();
        int position = zzpx.getPosition();
        zzpx.zzbl(readUnsignedShort);
        return zzpp.zzc(zzpx.data, position, readUnsignedShort);
    }
}
