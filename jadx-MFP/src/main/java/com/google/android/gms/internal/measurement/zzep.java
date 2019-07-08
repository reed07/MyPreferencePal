package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzep extends zzb {
    private final /* synthetic */ zzea zzadv;
    private final /* synthetic */ int zzaeh = 5;
    private final /* synthetic */ String zzaei;
    private final /* synthetic */ Object zzaej;
    private final /* synthetic */ Object zzaek;
    private final /* synthetic */ Object zzael;

    zzep(zzea zzea, boolean z, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzadv = zzea;
        this.zzaei = str;
        this.zzaej = obj;
        this.zzaek = obj2;
        this.zzael = obj3;
        super(false);
    }

    /* access modifiers changed from: 0000 */
    public final void zzgd() throws RemoteException {
        this.zzadv.zzadr.logHealthData(this.zzaeh, this.zzaei, ObjectWrapper.wrap(this.zzaej), ObjectWrapper.wrap(this.zzaek), ObjectWrapper.wrap(this.zzael));
    }
}
