package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import java.util.Map.Entry;

/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
final /* synthetic */ class zzi implements Runnable {
    private final Entry zza;
    private final Event zzb;

    private zzi(Entry entry, Event event) {
        this.zza = entry;
        this.zzb = event;
    }

    public static Runnable zza(Entry entry, Event event) {
        return new zzi(entry, event);
    }

    public final void run() {
        ((EventHandler) this.zza.getKey()).handle(this.zzb);
    }
}
