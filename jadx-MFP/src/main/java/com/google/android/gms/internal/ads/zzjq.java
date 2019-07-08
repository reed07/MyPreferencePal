package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class zzjq {
    public static byte[] zza(UUID uuid, byte[] bArr) {
        int length = bArr.length + 32;
        ByteBuffer allocate = ByteBuffer.allocate(length);
        allocate.putInt(length);
        allocate.putInt(zziv.zzamu);
        allocate.putInt(0);
        allocate.putLong(uuid.getMostSignificantBits());
        allocate.putLong(uuid.getLeastSignificantBits());
        allocate.putInt(bArr.length);
        allocate.put(bArr);
        return allocate.array();
    }

    public static UUID zze(byte[] bArr) {
        Pair pair;
        zzpx zzpx = new zzpx(bArr);
        if (zzpx.limit() < 32) {
            pair = null;
        } else {
            zzpx.setPosition(0);
            if (zzpx.readInt() != zzpx.zzhb() + 4) {
                pair = null;
            } else if (zzpx.readInt() != zziv.zzamu) {
                pair = null;
            } else {
                int zzaf = zziv.zzaf(zzpx.readInt());
                if (zzaf > 1) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Unsupported pssh version: ");
                    sb.append(zzaf);
                    Log.w("PsshAtomUtil", sb.toString());
                    pair = null;
                } else {
                    UUID uuid = new UUID(zzpx.readLong(), zzpx.readLong());
                    if (zzaf == 1) {
                        zzpx.zzbl(zzpx.zzhg() << 4);
                    }
                    int zzhg = zzpx.zzhg();
                    if (zzhg != zzpx.zzhb()) {
                        pair = null;
                    } else {
                        byte[] bArr2 = new byte[zzhg];
                        zzpx.zze(bArr2, 0, zzhg);
                        pair = Pair.create(uuid, bArr2);
                    }
                }
            }
        }
        if (pair == null) {
            return null;
        }
        return (UUID) pair.first;
    }
}
