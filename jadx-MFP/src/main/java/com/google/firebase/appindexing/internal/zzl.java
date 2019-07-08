package com.google.firebase.appindexing.internal;

import com.google.android.gms.tasks.OnFailureListener;

final /* synthetic */ class zzl implements OnFailureListener {
    private final zzk zzfa;

    zzl(zzk zzk) {
        this.zzfa = zzk;
    }

    public final void onFailure(Exception exc) {
        this.zzfa.zza(exc);
    }
}
