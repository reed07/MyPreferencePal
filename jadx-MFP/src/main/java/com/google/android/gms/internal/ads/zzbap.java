package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

@zzark
public final class zzbap {
    @GuardedBy("this")
    private final BroadcastReceiver zzent = new zzbaq(this);
    @GuardedBy("this")
    private final Map<BroadcastReceiver, IntentFilter> zzenu = new WeakHashMap();
    private boolean zzenv;
    private boolean zztd = false;
    private Context zztv;

    public final synchronized void initialize(Context context) {
        if (!this.zztd) {
            this.zztv = context.getApplicationContext();
            if (this.zztv == null) {
                this.zztv = context;
            }
            zzaan.initialize(this.zztv);
            this.zzenv = ((Boolean) zzwu.zzpz().zzd(zzaan.zzcul)).booleanValue();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            this.zztv.registerReceiver(this.zzent, intentFilter);
            this.zztd = true;
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.zzenv) {
            this.zzenu.put(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver) {
        if (this.zzenv) {
            this.zzenu.remove(broadcastReceiver);
        } else {
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zzc(Context context, Intent intent) {
        for (Entry entry : this.zzenu.entrySet()) {
            if (((IntentFilter) entry.getValue()).hasAction(intent.getAction())) {
                ((BroadcastReceiver) entry.getKey()).onReceive(context, intent);
            }
        }
    }
}
