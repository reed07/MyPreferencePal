package com.google.android.gms.internal.ads;

import android.os.Environment;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzuo.zza.zzb;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzum {
    private final zzur zzcan;
    @GuardedBy("this")
    private final zzvp zzcao;
    private final boolean zzcap;

    public static zzum zzoi() {
        return new zzum();
    }

    public zzum(zzur zzur) {
        this.zzcan = zzur;
        this.zzcap = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcwp)).booleanValue();
        this.zzcao = new zzvp();
        zzoj();
    }

    private zzum() {
        this.zzcap = false;
        this.zzcan = new zzur();
        this.zzcao = new zzvp();
        zzoj();
    }

    public final synchronized void zza(zzb zzb) {
        if (this.zzcap) {
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwq)).booleanValue()) {
                zzc(zzb);
            } else {
                zzb(zzb);
            }
        }
    }

    private final synchronized void zzb(zzb zzb) {
        this.zzcao.zzcht = zzok();
        this.zzcan.zzg(zzbuz.zzb(this.zzcao)).zzbz(zzb.zzom()).zzbd();
        String str = "Logging Event with event code : ";
        String valueOf = String.valueOf(Integer.toString(zzb.zzom(), 10));
        zzaxz.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    private final synchronized void zzc(zzb zzb) {
        FileOutputStream fileOutputStream;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            try {
                fileOutputStream = new FileOutputStream(new File(externalStorageDirectory, "clearcut_events.txt"), true);
                try {
                    fileOutputStream.write(zzd(zzb).getBytes());
                    fileOutputStream.write(10);
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        zzaxz.v("Could not close Clearcut output stream.");
                    }
                } catch (IOException unused2) {
                    zzaxz.v("Could not write Clearcut to file.");
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                        zzaxz.v("Could not close Clearcut output stream.");
                    }
                }
            } catch (FileNotFoundException unused4) {
                zzaxz.v("Could not find file for Clearcut");
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused5) {
                    zzaxz.v("Could not close Clearcut output stream.");
                }
                throw th;
            }
        }
    }

    private final synchronized String zzd(zzb zzb) {
        return String.format("id=%s,timestamp=%s,event=%s", new Object[]{this.zzcao.zzchp, Long.valueOf(zzbv.zzlm().elapsedRealtime()), Integer.valueOf(zzb.zzom())});
    }

    public final synchronized void zza(zzun zzun) {
        if (this.zzcap) {
            try {
                zzun.zza(this.zzcao);
            } catch (NullPointerException e) {
                zzbv.zzlj().zza(e, "AdMobClearcutLogger.modify");
            }
        }
    }

    private final synchronized void zzoj() {
        this.zzcao.zzchx = new zzvl();
        this.zzcao.zzchx.zzcgp = new zzvm();
        this.zzcao.zzchu = new zzvn();
    }

    private static long[] zzok() {
        int i;
        List zzqx = zzaan.zzqx();
        ArrayList arrayList = new ArrayList();
        Iterator it = zzqx.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            String[] split = ((String) it.next()).split(",");
            int length = split.length;
            while (i < length) {
                try {
                    arrayList.add(Long.valueOf(split[i]));
                } catch (NumberFormatException unused) {
                    zzaxz.v("Experiment ID is not a number");
                }
                i++;
            }
        }
        long[] jArr = new long[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            jArr[i2] = ((Long) obj).longValue();
            i2++;
        }
        return jArr;
    }
}
