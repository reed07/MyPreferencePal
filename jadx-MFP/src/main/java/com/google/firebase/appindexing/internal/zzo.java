package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.FirebaseUserActions;

public final class zzo extends FirebaseUserActions {
    private zzq zzfe;

    public zzo(Context context) {
        this.zzfe = new zzq(context);
    }

    public final Task<Void> start(Action action) {
        return zza(1, action);
    }

    public final Task<Void> end(Action action) {
        return zza(2, action);
    }

    private final Task<Void> zza(int i, Action action) {
        zza[] zzaArr = new zza[1];
        if (action != null) {
            if (!(action instanceof zza)) {
                return Tasks.forException(new FirebaseAppIndexingInvalidArgumentException("Custom Action objects are not allowed. Please use the 'Actions' or 'ActionBuilder' class for creating Action objects."));
            }
            zzaArr[0] = (zza) action;
            zzaArr[0].zzad().zzf(i);
        }
        return this.zzfe.doWrite((TaskApiCall<A, TResult>) new zzp<A,TResult>(this, zzaArr));
    }
}
