package com.google.android.gms.internal.icing;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.internal.icing.zzak.zzb;
import com.google.android.gms.internal.icing.zzak.zzc;
import com.google.android.gms.internal.icing.zzak.zzc.zza;
import com.google.android.gms.internal.icing.zzak.zzd;

public final class zzah {
    public static zzx zza(Action action, long j, String str, int i) {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putAll(action.zze());
        Bundle bundle2 = bundle.getBundle("object");
        Uri parse = bundle2.containsKey("id") ? Uri.parse(bundle2.getString("id")) : null;
        String string = bundle2.getString("name");
        String string2 = bundle2.getString("type");
        Intent zza = zzai.zza(str, Uri.parse(bundle2.getString("url")));
        zzh zza2 = zzx.zza(zza, string, parse, string2, null);
        if (bundle.containsKey(".private:ssbContext")) {
            zza2.zza(zzl.zza(bundle.getByteArray(".private:ssbContext")));
            bundle.remove(".private:ssbContext");
        }
        if (bundle.containsKey(".private:accountName")) {
            zza2.zza(new Account(bundle.getString(".private:accountName"), AccountType.GOOGLE));
            bundle.remove(".private:accountName");
        }
        boolean z = false;
        if (!bundle.containsKey(".private:isContextOnly") || !bundle.getBoolean(".private:isContextOnly")) {
            i2 = 0;
        } else {
            i2 = 4;
            bundle.remove(".private:isContextOnly");
        }
        if (bundle.containsKey(".private:isDeviceOnly")) {
            z = bundle.getBoolean(".private:isDeviceOnly", false);
            bundle.remove(".private:isDeviceOnly");
        }
        String str2 = ".private:action";
        zza2.zza(new zzl(zza(bundle).toByteArray(), new zzt(str2).zzb(true).zzd(str2).zzc("blob").zzc()));
        return new zzy().zza(zzx.zza(str, zza)).zza(j).zzb(i2).zza(zza2.zzb()).zzd(z).zzc(i).zzd();
    }

    private static zzc zza(Bundle bundle) {
        zza zzl = zzc.zzl();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                zzl.zzb((zzb) ((zzdj) zzb.zzj().zzi(str).zzb((zzd) ((zzdj) zzd.zzn().zzm((String) obj).zzca())).zzca()));
            } else if (obj instanceof Bundle) {
                zzl.zzb((zzb) ((zzdj) zzb.zzj().zzi(str).zzb((zzd) ((zzdj) zzd.zzn().zzb(zza((Bundle) obj)).zzca())).zzca()));
            } else {
                int i = 0;
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    int length = strArr.length;
                    while (i < length) {
                        String str2 = strArr[i];
                        if (str2 != null) {
                            zzl.zzb((zzb) ((zzdj) zzb.zzj().zzi(str).zzb((zzd) ((zzdj) zzd.zzn().zzm(str2).zzca())).zzca()));
                        }
                        i++;
                    }
                } else if (obj instanceof Bundle[]) {
                    Bundle[] bundleArr = (Bundle[]) obj;
                    int length2 = bundleArr.length;
                    while (i < length2) {
                        Bundle bundle2 = bundleArr[i];
                        if (bundle2 != null) {
                            zzl.zzb((zzb) ((zzdj) zzb.zzj().zzi(str).zzb((zzd) ((zzdj) zzd.zzn().zzb(zza(bundle2)).zzca())).zzca()));
                        }
                        i++;
                    }
                } else if (obj instanceof Boolean) {
                    zzl.zzb((zzb) ((zzdj) zzb.zzj().zzi(str).zzb((zzd) ((zzdj) zzd.zzn().zzf(((Boolean) obj).booleanValue()).zzca())).zzca()));
                } else {
                    String valueOf = String.valueOf(obj);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
                    sb.append("Unsupported value: ");
                    sb.append(valueOf);
                    Log.e("SearchIndex", sb.toString());
                }
            }
        }
        if (bundle.containsKey("type")) {
            zzl.zzk(bundle.getString("type"));
        }
        return (zzc) ((zzdj) zzl.zzca());
    }
}
