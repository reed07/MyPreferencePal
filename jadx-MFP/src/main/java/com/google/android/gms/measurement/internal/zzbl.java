package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzu;
import com.myfitnesspal.shared.constants.Constants.Analytics.UtmValues;

final class zzbl implements Runnable {
    private final /* synthetic */ zzu zzaod;
    private final /* synthetic */ ServiceConnection zzaoe;
    private final /* synthetic */ zzbk zzaof;

    zzbl(zzbk zzbk, zzu zzu, ServiceConnection serviceConnection) {
        this.zzaof = zzbk;
        this.zzaod = zzu;
        this.zzaoe = serviceConnection;
    }

    public final void run() {
        zzbj zzbj = this.zzaof.zzaoc;
        String zza = this.zzaof.packageName;
        zzu zzu = this.zzaod;
        ServiceConnection serviceConnection = this.zzaoe;
        Bundle zza2 = zzbj.zza(zza, zzu);
        zzbj.zzada.zzgs().zzaf();
        if (zza2 != null) {
            long j = zza2.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zzbj.zzada.zzgt().zzjg().zzby("Service response is missing Install Referrer install timestamp");
            } else {
                String string = zza2.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzbj.zzada.zzgt().zzjg().zzby("No referrer defined in install referrer response");
                } else {
                    zzbj.zzada.zzgt().zzjo().zzg("InstallReferrer API result", string);
                    zzfx zzgr = zzbj.zzada.zzgr();
                    String str = "?";
                    String valueOf = String.valueOf(string);
                    Bundle zza3 = zzgr.zza(Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)));
                    if (zza3 == null) {
                        zzbj.zzada.zzgt().zzjg().zzby("No campaign params defined in install referrer result");
                    } else {
                        String string2 = zza3.getString("medium");
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !UtmValues.ORGANIC.equalsIgnoreCase(string2)) {
                            long j2 = zza2.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (j2 == 0) {
                                zzbj.zzada.zzgt().zzjg().zzby("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                zza3.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzbj.zzada.zzgu().zzani.get()) {
                            zzbj.zzada.zzgw();
                            zzbj.zzada.zzgt().zzjo().zzby("Campaign has already been logged");
                        } else {
                            zzbj.zzada.zzgu().zzani.set(j);
                            zzbj.zzada.zzgw();
                            zzbj.zzada.zzgt().zzjo().zzg("Logging Install Referrer campaign from sdk with ", "referrer API");
                            zza3.putString("_cis", "referrer API");
                            zzbj.zzada.zzgj().logEvent("auto", "_cmp", zza3);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzbj.zzada.getContext(), serviceConnection);
        }
    }
}
