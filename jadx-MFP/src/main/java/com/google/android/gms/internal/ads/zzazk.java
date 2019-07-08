package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

final class zzazk implements Runnable {
    final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzemh;
    private final /* synthetic */ boolean zzemi;
    private final /* synthetic */ boolean zzemj;

    zzazk(zzazj zzazj, Context context, String str, boolean z, boolean z2) {
        this.val$context = context;
        this.zzemh = str;
        this.zzemi = z;
        this.zzemj = z2;
    }

    public final void run() {
        Builder builder = new Builder(this.val$context);
        builder.setMessage(this.zzemh);
        if (this.zzemi) {
            builder.setTitle("Error");
        } else {
            builder.setTitle("Info");
        }
        if (this.zzemj) {
            builder.setNeutralButton("Dismiss", null);
        } else {
            builder.setPositiveButton(CtaButton.DEFAULT_CTA_TEXT, new zzazl(this));
            builder.setNegativeButton("Dismiss", null);
        }
        builder.create().show();
    }
}
