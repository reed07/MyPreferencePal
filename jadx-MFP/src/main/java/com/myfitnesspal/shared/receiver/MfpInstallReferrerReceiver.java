package com.myfitnesspal.shared.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class MfpInstallReferrerReceiver extends MfpBroadcastReceiverBase {
    @Inject
    @Named("installReferrerReceivers")
    List<BroadcastReceiver> broadcastReceivers;

    public void onReceive(Context context, Intent intent) {
        List<BroadcastReceiver> list = this.broadcastReceivers;
        if (list != null) {
            for (BroadcastReceiver onReceive : list) {
                onReceive.onReceive(context, intent);
            }
        }
    }
}
