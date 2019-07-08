package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

class zzcq extends BroadcastReceiver {
    @VisibleForTesting
    private static final String zzabi = "com.google.android.gms.internal.measurement.zzcq";
    private boolean zzabj;
    private boolean zzabk;
    private final zzaw zzvy;

    zzcq(zzaw zzaw) {
        Preconditions.checkNotNull(zzaw);
        this.zzvy = zzaw;
    }

    public void onReceive(Context context, Intent intent) {
        zzez();
        String action = intent.getAction();
        this.zzvy.zzby().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzfb = zzfb();
            if (this.zzabk != zzfb) {
                this.zzabk = zzfb;
                zzal zzcc = this.zzvy.zzcc();
                zzcc.zza("Network connectivity status changed", Boolean.valueOf(zzfb));
                zzcc.zzca().zza((Runnable) new zzan(zzcc, zzfb));
            }
        } else if ("com.google.analytics.RADIO_POWERED".equals(action)) {
            if (!intent.hasExtra(zzabi)) {
                zzal zzcc2 = this.zzvy.zzcc();
                zzcc2.zzq("Radio powered up");
                zzcc2.zzbs();
            }
        } else {
            this.zzvy.zzby().zzd("NetworkBroadcastReceiver received unknown action", action);
        }
    }

    public final void zzey() {
        zzez();
        if (!this.zzabj) {
            Context context = this.zzvy.getContext();
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(context.getPackageName());
            context.registerReceiver(this, intentFilter);
            this.zzabk = zzfb();
            this.zzvy.zzby().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzabk));
            this.zzabj = true;
        }
    }

    private final void zzez() {
        this.zzvy.zzby();
        this.zzvy.zzcc();
    }

    public final void unregister() {
        if (this.zzabj) {
            this.zzvy.zzby().zzq("Unregistering connectivity change receiver");
            this.zzabj = false;
            this.zzabk = false;
            try {
                this.zzvy.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zzvy.zzby().zze("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @VisibleForTesting
    public final void zzfa() {
        Context context = this.zzvy.getContext();
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzabi, true);
        context.sendOrderedBroadcast(intent, null);
    }

    public final boolean isConnected() {
        if (!this.zzabj) {
            this.zzvy.zzby().zzt("Connectivity unknown. Receiver not registered");
        }
        return this.zzabk;
    }

    @VisibleForTesting
    private final boolean zzfb() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzvy.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (SecurityException unused) {
            return false;
        }
    }
}
