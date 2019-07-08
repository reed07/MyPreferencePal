package com.google.ads.interactivemedia.v3.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: IMASDK */
final class l extends BroadcastReceiver {
    private final /* synthetic */ k a;

    l(k kVar) {
        this.a = kVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                this.a.a(true);
            } else if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                this.a.a(false);
            } else {
                if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                    KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
                    if (keyguardManager != null && !keyguardManager.inKeyguardRestrictedInputMode()) {
                        this.a.a(false);
                    }
                }
            }
        }
    }
}
