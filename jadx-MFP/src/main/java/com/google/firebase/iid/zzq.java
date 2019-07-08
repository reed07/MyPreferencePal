package com.google.firebase.iid;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;

final /* synthetic */ class zzq implements EventHandler {
    private final zza zzbe;

    zzq(zza zza) {
        this.zzbe = zza;
    }

    public final void handle(Event event) {
        zza zza = this.zzbe;
        synchronized (zza) {
            if (zza.isEnabled()) {
                FirebaseInstanceId.this.zzg();
            }
        }
    }
}
