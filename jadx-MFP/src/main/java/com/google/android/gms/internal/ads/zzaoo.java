package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import com.google.android.gms.dynamic.RemoteCreator.RemoteCreatorException;

@zzark
public final class zzaoo extends RemoteCreator<zzaos> {
    public zzaoo() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public final zzaop zze(Activity activity) {
        try {
            IBinder zzr = ((zzaos) getRemoteCreatorInstance(activity)).zzr(ObjectWrapper.wrap(activity));
            if (zzr == null) {
                return null;
            }
            IInterface queryLocalInterface = zzr.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            if (queryLocalInterface instanceof zzaop) {
                return (zzaop) queryLocalInterface;
            }
            return new zzaor(zzr);
        } catch (RemoteException e) {
            zzbbd.zzc("Could not create remote AdOverlay.", e);
            return null;
        } catch (RemoteCreatorException e2) {
            zzbbd.zzc("Could not create remote AdOverlay.", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
        if (queryLocalInterface instanceof zzaos) {
            return (zzaos) queryLocalInterface;
        }
        return new zzaot(iBinder);
    }
}
