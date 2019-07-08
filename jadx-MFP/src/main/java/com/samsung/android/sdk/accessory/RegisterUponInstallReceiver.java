package com.samsung.android.sdk.accessory;

import android.content.BroadcastReceiver;
import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class RegisterUponInstallReceiver extends BroadcastReceiver {
    /* access modifiers changed from: private */
    public static String a = "[SA_SDK]RegisterUponInstallReceiver";

    public final void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals("com.samsung.accessory.action.REGISTER_AGENT")) {
            String str = a;
            StringBuilder sb = new StringBuilder("Received register intent:");
            sb.append(context.getPackageName());
            Log.d(str, sb.toString());
            try {
                new i(context);
            } catch (c e) {
                String str2 = a;
                StringBuilder sb2 = new StringBuilder("SDK config initialization failed.");
                sb2.append(e);
                Log.e(str2, sb2.toString());
            }
            final PendingResult goAsync = goAsync();
            h hVar = new h(context.getApplicationContext());
            final Future a2 = hVar.a();
            new Thread("RegistrationThread") {
                public final void run() {
                    String a2;
                    String str;
                    try {
                        a2.get();
                    } catch (InterruptedException e) {
                        e = e;
                        a2 = RegisterUponInstallReceiver.a;
                        str = "Service Registration has failed!";
                    } catch (ExecutionException e2) {
                        e = e2;
                        a2 = RegisterUponInstallReceiver.a;
                        str = "Service Registration has failed!";
                    } catch (Throwable th) {
                        goAsync.finish();
                        throw th;
                    }
                    goAsync.finish();
                    Log.e(a2, str, e);
                    goAsync.finish();
                }
            }.start();
            hVar.b();
        }
    }
}
