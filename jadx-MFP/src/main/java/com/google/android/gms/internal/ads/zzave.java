package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;

@zzark
public final class zzave implements RewardItem {
    private final zzaur zzeel;

    public zzave(zzaur zzaur) {
        this.zzeel = zzaur;
    }

    public final String getType() {
        zzaur zzaur = this.zzeel;
        if (zzaur == null) {
            return null;
        }
        try {
            return zzaur.getType();
        } catch (RemoteException e) {
            zzbbd.zzc("Could not forward getType to RewardItem", e);
            return null;
        }
    }

    public final int getAmount() {
        zzaur zzaur = this.zzeel;
        if (zzaur == null) {
            return 0;
        }
        try {
            return zzaur.getAmount();
        } catch (RemoteException e) {
            zzbbd.zzc("Could not forward getAmount to RewardItem", e);
            return 0;
        }
    }
}
