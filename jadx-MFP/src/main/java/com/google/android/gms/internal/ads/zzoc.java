package com.google.android.gms.internal.ads;

import android.util.Log;

public final class zzoc {
    public static void zza(long j, zzpx zzpx, zzii[] zziiArr) {
        boolean z;
        zzpx zzpx2 = zzpx;
        zzii[] zziiArr2 = zziiArr;
        while (zzpx.zzhb() > 1) {
            int zzf = zzf(zzpx);
            int zzf2 = zzf(zzpx);
            if (zzf2 == -1 || zzf2 > zzpx.zzhb()) {
                Log.w("CeaUtil", "Skipping remainder of malformed SEI NAL unit.");
                zzpx2.setPosition(zzpx.limit());
            } else {
                if (zzf != 4 || zzf2 < 8) {
                    z = false;
                } else {
                    int position = zzpx.getPosition();
                    int readUnsignedByte = zzpx.readUnsignedByte();
                    int readUnsignedShort = zzpx.readUnsignedShort();
                    int readInt = zzpx.readInt();
                    int readUnsignedByte2 = zzpx.readUnsignedByte();
                    zzpx2.setPosition(position);
                    z = readUnsignedByte == 181 && readUnsignedShort == 49 && readInt == 1195456820 && readUnsignedByte2 == 3;
                }
                if (z) {
                    zzpx2.zzbl(8);
                    int readUnsignedByte3 = zzpx.readUnsignedByte() & 31;
                    zzpx2.zzbl(1);
                    int i = readUnsignedByte3 * 3;
                    int position2 = zzpx.getPosition();
                    for (zzii zzii : zziiArr2) {
                        zzpx2.setPosition(position2);
                        zzii.zza(zzpx2, i);
                        zzii.zza(j, 1, i, 0, null);
                    }
                    zzpx2.zzbl(zzf2 - (i + 10));
                } else {
                    zzpx2.zzbl(zzf2);
                }
            }
        }
    }

    private static int zzf(zzpx zzpx) {
        int i = 0;
        while (zzpx.zzhb() != 0) {
            int readUnsignedByte = zzpx.readUnsignedByte();
            i += readUnsignedByte;
            if (readUnsignedByte != 255) {
                return i;
            }
        }
        return -1;
    }
}
