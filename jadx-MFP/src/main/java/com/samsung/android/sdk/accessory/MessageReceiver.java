package com.samsung.android.sdk.accessory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.util.Log;

public final class MessageReceiver extends BroadcastReceiver {
    private String a;

    public MessageReceiver() {
        StringBuilder sb = new StringBuilder("[SA_SDK] ");
        sb.append(MessageReceiver.class.getSimpleName());
        this.a = sb.toString();
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            Log.d(this.a, "received null intent!");
            return;
        }
        if (SAMessage.ACTION_ACCESSORY_MESSAGE_RECEIVED.equalsIgnoreCase(intent.getAction())) {
            Log.d(this.a, "Incoming Data Received!!!");
            try {
                new i(context);
                String stringExtra = intent.getStringExtra("agentImplclass");
                if (stringExtra == null) {
                    Log.e(this.a, "Impl class not available in intent. ignoring message received");
                    return;
                }
                intent.setClassName(context, stringExtra);
                try {
                    if (((VERSION.SDK_INT < 26 || context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.targetSdkVersion < 26) ? context.startService(intent) : context.startForegroundService(intent)) == null) {
                        String str = this.a;
                        StringBuilder sb = new StringBuilder("Agent ");
                        sb.append(stringExtra);
                        sb.append(" not found. Check Accessory Service XML for serviceImpl attribute");
                        Log.e(str, sb.toString());
                    }
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (c e2) {
                String str2 = this.a;
                StringBuilder sb2 = new StringBuilder("SDK config initialization failed.");
                sb2.append(e2);
                Log.e(str2, sb2.toString());
            }
        }
    }
}
