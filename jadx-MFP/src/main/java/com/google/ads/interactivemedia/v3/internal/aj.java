package com.google.ads.interactivemedia.v3.internal;

import android.os.AsyncTask;

/* compiled from: IMASDK */
public abstract class aj extends AsyncTask<Object, Void, String> {
    private ak a;
    protected final al d;

    public aj(al alVar) {
        this.d = alVar;
    }

    public final void a(ak akVar) {
        this.a = akVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(String str) {
        ak akVar = this.a;
        if (akVar != null) {
            akVar.a();
        }
    }
}
