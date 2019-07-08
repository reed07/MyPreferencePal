package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.exoplayer2.C;

/* compiled from: IMASDK */
final class ads extends AsyncTask<Void, Void, Void> {
    private final /* synthetic */ String a;
    private final /* synthetic */ aeb b;

    ads(aeb aeb, String str) {
        this.b = aeb;
        this.a = str;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.a));
        if (!(this.b.h instanceof Activity)) {
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
        }
        this.b.h.startActivity(intent);
        return null;
    }
}
