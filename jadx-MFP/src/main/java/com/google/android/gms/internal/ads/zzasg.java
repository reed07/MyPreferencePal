package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.annotation.NonNull;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;
import com.google.android.gms.common.util.VisibleForTesting;

@zzark
public final class zzasg extends zzasc implements BaseConnectionCallbacks, BaseOnConnectionFailedListener {
    private Context mContext;
    private final Object mLock = new Object();
    private zzbbi zzbob;
    private zzbcn<zzasi> zzdvz;
    private final zzasa zzdwa;
    private zzazb zzdwd;
    @VisibleForTesting
    private zzash zzdwe;

    public zzasg(Context context, zzbbi zzbbi, zzbcn<zzasi> zzbcn, zzasa zzasa) {
        super(zzbcn, zzasa);
        this.mContext = context;
        this.zzbob = zzbbi;
        this.zzdvz = zzbcn;
        this.zzdwa = zzasa;
        this.zzdwe = new zzash(context, zzbv.zzlv().zzaak(), this, this);
        this.zzdwe.checkAvailabilityAndConnect();
    }

    public final zzasq zzwj() {
        zzasq zzwk;
        synchronized (this.mLock) {
            try {
                zzwk = this.zzdwe.zzwk();
            } catch (DeadObjectException | IllegalStateException unused) {
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return zzwk;
    }

    public final void zzwi() {
        synchronized (this.mLock) {
            if (this.zzdwe.isConnected() || this.zzdwe.isConnecting()) {
                this.zzdwe.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzaxz.zzdn("Cannot connect to remote service, fallback to local instance.");
        this.zzdwd = new zzasf(this.mContext, this.zzdvz, this.zzdwa);
        this.zzdwd.zzwa();
        Bundle bundle = new Bundle();
        bundle.putString("action", "gms_connection_failed_fallback_to_local");
        zzbv.zzlf().zzb(this.mContext, this.zzbob.zzdp, "gmob-apps", bundle, true);
    }

    public final void onConnected(Bundle bundle) {
        zzwa();
    }

    public final void onConnectionSuspended(int i) {
        zzaxz.zzdn("Disconnected from remote ad request service.");
    }
}
