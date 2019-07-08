package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class zzayo extends BroadcastReceiver {
    private final /* synthetic */ zzayh zzell;

    private zzayo(zzayh zzayh) {
        this.zzell = zzayh;
    }

    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
            this.zzell.zzeld = true;
            return;
        }
        if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
            this.zzell.zzeld = false;
        }
    }

    /* synthetic */ zzayo(zzayh zzayh, zzayk zzayk) {
        this(zzayh);
    }
}
