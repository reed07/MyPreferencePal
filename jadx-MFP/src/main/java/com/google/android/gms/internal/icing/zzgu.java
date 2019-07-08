package com.google.android.gms.internal.icing;

public final class zzgu implements zzbs<zzgv> {
    private static zzgu zzqq = new zzgu();
    private final zzbs<zzgv> zzqr;

    public static boolean zzef() {
        return ((zzgv) zzqq.get()).zzef();
    }

    private zzgu(zzbs<zzgv> zzbs) {
        this.zzqr = zzbt.zza(zzbs);
    }

    public zzgu() {
        this(zzbt.zzb(new zzgw()));
    }

    public final /* synthetic */ Object get() {
        return (zzgv) this.zzqr.get();
    }
}
