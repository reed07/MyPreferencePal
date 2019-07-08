package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class zzan {
    final String zza;
    final long zzb;
    final long zzc;
    long zzca;
    final String zzcb;
    final long zzd;
    final long zze;
    final List<zzl> zzg;

    private zzan(String str, String str2, long j, long j2, long j3, long j4, List<zzl> list) {
        this.zzcb = str;
        if ("".equals(str2)) {
            str2 = null;
        }
        this.zza = str2;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = j3;
        this.zze = j4;
        this.zzg = list;
    }

    zzan(String str, zzc zzc2) {
        List list;
        zzc zzc3 = zzc2;
        String str2 = zzc3.zza;
        long j = zzc3.zzb;
        long j2 = zzc3.zzc;
        long j3 = zzc3.zzd;
        long j4 = zzc3.zze;
        if (zzc3.zzg != null) {
            list = zzc3.zzg;
        } else {
            Map<String, String> map = zzc3.zzf;
            ArrayList arrayList = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                arrayList.add(new zzl((String) entry.getKey(), (String) entry.getValue()));
            }
            list = arrayList;
        }
        this(str, str2, j, j2, j3, j4, list);
        this.zzca = (long) zzc3.data.length;
    }

    static zzan zzc(zzao zzao) throws IOException {
        if (zzam.zzb((InputStream) zzao) == 538247942) {
            zzan zzan = new zzan(zzam.zza(zzao), zzam.zza(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzc(zzao), zzam.zzb(zzao));
            return zzan;
        }
        throw new IOException();
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(OutputStream outputStream) {
        try {
            zzam.zza(outputStream, 538247942);
            zzam.zza(outputStream, this.zzcb);
            zzam.zza(outputStream, this.zza == null ? "" : this.zza);
            zzam.zza(outputStream, this.zzb);
            zzam.zza(outputStream, this.zzc);
            zzam.zza(outputStream, this.zzd);
            zzam.zza(outputStream, this.zze);
            List<zzl> list = this.zzg;
            if (list != null) {
                zzam.zza(outputStream, list.size());
                for (zzl zzl : list) {
                    zzam.zza(outputStream, zzl.getName());
                    zzam.zza(outputStream, zzl.getValue());
                }
            } else {
                zzam.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzaf.d("%s", e.toString());
            return false;
        }
    }
}
