package com.google.android.gms.tagmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.util.VisibleForTesting;

class zzdn extends BroadcastReceiver {
    @VisibleForTesting
    private static final String zzabi = "com.google.android.gms.tagmanager.zzdn";
    private final zzfm zzbdp;

    zzdn(zzfm zzfm) {
        this.zzbdp = zzfm;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            Bundle extras = intent.getExtras();
            Boolean bool = Boolean.FALSE;
            if (extras != null) {
                bool = Boolean.valueOf(intent.getExtras().getBoolean("noConnectivity"));
            }
            this.zzbdp.zzp(!bool.booleanValue());
            return;
        }
        if ("com.google.analytics.RADIO_POWERED".equals(action) && !intent.hasExtra(zzabi)) {
            this.zzbdp.zzqd();
        }
    }

    public static void zzw(Context context) {
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzabi, true);
        context.sendBroadcast(intent);
    }
}
