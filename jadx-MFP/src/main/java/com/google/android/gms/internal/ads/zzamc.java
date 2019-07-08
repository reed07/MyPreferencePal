package com.google.android.gms.internal.ads;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.Set;

@zzark
public final class zzamc implements MediationAdRequest {
    private final int zzcjv;
    private final boolean zzckh;
    private final int zzdnw;
    private final Date zzih;
    private final Set<String> zzij;
    private final boolean zzik;
    private final Location zzil;

    public zzamc(@Nullable Date date, int i, @Nullable Set<String> set, @Nullable Location location, boolean z, int i2, boolean z2) {
        this.zzih = date;
        this.zzcjv = i;
        this.zzij = set;
        this.zzil = location;
        this.zzik = z;
        this.zzdnw = i2;
        this.zzckh = z2;
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zzih;
    }

    @Deprecated
    public final int getGender() {
        return this.zzcjv;
    }

    public final Set<String> getKeywords() {
        return this.zzij;
    }

    public final Location getLocation() {
        return this.zzil;
    }

    public final boolean isTesting() {
        return this.zzik;
    }

    public final int taggedForChildDirectedTreatment() {
        return this.zzdnw;
    }

    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzckh;
    }
}
