package com.samsung.android.sdk.accessory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.util.Log;

public final class ServiceConnectionIndicationBroadcastReceiver extends BroadcastReceiver {
    private String a = "[SA_SDK]ServiceConnectionIndicationReceiver";

    public final void onReceive(Context context, Intent intent) {
        if (!(intent == null || intent.getAction() == null || !intent.getAction().equalsIgnoreCase("com.samsung.accessory.action.SERVICE_CONNECTION_REQUESTED"))) {
            Log.i(this.a, "Incoming service connection request received.");
            try {
                new i(context);
                String stringExtra = intent.getStringExtra("agentImplclass");
                if (stringExtra == null) {
                    Log.e(this.a, "Impl class not availabel in intent. Ignoring request");
                    return;
                }
                String str = this.a;
                StringBuilder sb = new StringBuilder("Connection request will be handled by :");
                sb.append(stringExtra);
                Log.v(str, sb.toString());
                try {
                    String simpleName = Class.forName(stringExtra).getSuperclass().getSimpleName();
                    String str2 = this.a;
                    StringBuilder sb2 = new StringBuilder("implClass.getSuperclass() :");
                    sb2.append(simpleName);
                    Log.v(str2, sb2.toString());
                    if (simpleName.equalsIgnoreCase(SAServiceAgent.class.getSimpleName())) {
                        intent.setClassName(context, SAService.class.getName());
                    } else {
                        intent.setClassName(context, stringExtra);
                    }
                    if (((VERSION.SDK_INT < 26 || context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion < 26) ? context.startService(intent) : context.startForegroundService(intent)) == null) {
                        String str3 = this.a;
                        StringBuilder sb3 = new StringBuilder("Agent ");
                        sb3.append(stringExtra);
                        sb3.append(" not found. Check Accessory Service XML for serviceImpl attribute");
                        Log.e(str3, sb3.toString());
                    }
                } catch (ClassNotFoundException e) {
                    String str4 = this.a;
                    StringBuilder sb4 = new StringBuilder("Agent Impl class not found!");
                    sb4.append(e);
                    Log.e(str4, sb4.toString());
                } catch (NameNotFoundException e2) {
                    e2.printStackTrace();
                }
            } catch (c e3) {
                String str5 = this.a;
                StringBuilder sb5 = new StringBuilder("SDK config init failed.");
                sb5.append(e3);
                Log.e(str5, sb5.toString());
            }
        }
    }
}
