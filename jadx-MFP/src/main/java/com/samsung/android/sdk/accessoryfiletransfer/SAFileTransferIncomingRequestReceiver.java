package com.samsung.android.sdk.accessoryfiletransfer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class SAFileTransferIncomingRequestReceiver extends BroadcastReceiver {
    private String a = "[SA_SDK]SAFileTransferIncomingRequestReceiver";

    public void onReceive(Context context, Intent intent) {
        Log.d(this.a, "onReceive");
        if (intent != null && intent.getAction() != null && intent.getAction().equalsIgnoreCase(SAFileTransfer.ACTION_SAP_FILE_TRANSFER_REQUESTED)) {
            String str = this.a;
            StringBuilder sb = new StringBuilder("Intent action is ");
            sb.append(intent.getAction());
            Log.i(str, sb.toString());
            intent.setAction("com.samsung.accessory.ftconnection.internal");
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
