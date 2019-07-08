package com.google.android.gms.analytics;

import android.content.BroadcastReceiver.PendingResult;

final class zzc implements Runnable {
    private final /* synthetic */ PendingResult zzrf;

    zzc(CampaignTrackingReceiver campaignTrackingReceiver, PendingResult pendingResult) {
        this.zzrf = pendingResult;
    }

    public final void run() {
        PendingResult pendingResult = this.zzrf;
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }
}
