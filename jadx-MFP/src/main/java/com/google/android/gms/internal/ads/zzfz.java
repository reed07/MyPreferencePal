package com.google.android.gms.internal.ads;

import java.io.IOException;

public interface zzfz extends zzfi {
    void disable();

    int getState();

    int getTrackType();

    boolean isReady();

    void setIndex(int i);

    void start() throws zzff;

    void stop() throws zzff;

    void zza(zzgb zzgb, zzfs[] zzfsArr, zzlv zzlv, long j, boolean z, long j2) throws zzff;

    void zza(zzfs[] zzfsArr, zzlv zzlv, long j) throws zzff;

    void zzb(long j, long j2) throws zzff;

    zzga zzbe();

    zzps zzbf();

    zzlv zzbg();

    boolean zzbh();

    void zzbi();

    boolean zzbj();

    void zzbk() throws IOException;

    boolean zzcj();

    void zzd(long j) throws zzff;
}
