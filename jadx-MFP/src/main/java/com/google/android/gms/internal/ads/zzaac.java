package com.google.android.gms.internal.ads;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONObject;

@zzark
public abstract class zzaac<T> {
    private final String mKey;
    private final int zzcni;
    private final T zzcnj;

    private zzaac(int i, String str, T t) {
        this.zzcni = i;
        this.mKey = str;
        this.zzcnj = t;
        zzwu.zzpy().zza(this);
    }

    /* access modifiers changed from: protected */
    public abstract T zza(SharedPreferences sharedPreferences);

    public abstract void zza(Editor editor, T t);

    /* access modifiers changed from: protected */
    public abstract T zzb(JSONObject jSONObject);

    public final String getKey() {
        return this.mKey;
    }

    public final T zzqv() {
        return this.zzcnj;
    }

    public static zzaac<Boolean> zza(int i, String str, Boolean bool) {
        return new zzaad(i, str, bool);
    }

    public static zzaac<Integer> zza(int i, String str, int i2) {
        return new zzaae(i, str, Integer.valueOf(i2));
    }

    public static zzaac<Long> zza(int i, String str, long j) {
        return new zzaaf(i, str, Long.valueOf(j));
    }

    public static zzaac<Float> zza(int i, String str, float f) {
        return new zzaag(i, str, Float.valueOf(f));
    }

    public static zzaac<String> zza(int i, String str, String str2) {
        return new zzaah(i, str, str2);
    }

    public static zzaac<String> zzb(int i, String str) {
        zzaac<String> zza = zza(i, str, (String) null);
        zzwu.zzpy().zzb(zza);
        return zza;
    }

    public static zzaac<String> zzc(int i, String str) {
        zzaac<String> zza = zza(i, str, (String) null);
        zzwu.zzpy().zzc(zza);
        return zza;
    }

    public final int getSource() {
        return this.zzcni;
    }

    /* synthetic */ zzaac(int i, String str, Object obj, zzaad zzaad) {
        this(i, str, obj);
    }
}
