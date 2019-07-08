package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zzark
public final class zzte {
    private final int zzbyy;
    private final int zzbyz;
    private final int zzbza;
    private final zztd zzbzb = new zzti();

    public zzte(int i) {
        this.zzbyz = i;
        this.zzbyy = 6;
        this.zzbza = 0;
    }

    public final String zza(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            sb.append(((String) obj).toLowerCase(Locale.US));
            sb.append(10);
        }
        return zzaz(sb.toString());
    }

    @VisibleForTesting
    private final String zzaz(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return "";
        }
        zztg zztg = new zztg();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzbyz, new zztf(this));
        for (String zze : split) {
            String[] zze2 = zzth.zze(zze, false);
            if (zze2.length != 0) {
                zztk.zza(zze2, this.zzbyz, this.zzbyy, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zztg.write(this.zzbzb.zzay(((zztl) it.next()).zzbzf));
            } catch (IOException e) {
                zzaxz.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zztg.toString();
    }
}
