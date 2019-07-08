package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzv;
import com.google.android.gms.common.util.Predicate;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzaik implements zzaic, zzaii {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzbgg zzdin;

    public zzaik(Context context, zzbbi zzbbi, @Nullable zzcu zzcu, zzv zzv) throws zzbgq {
        this.mContext = context;
        zzbv.zzlg();
        this.zzdin = zzbgm.zza(context, zzbht.zzaey(), "", false, false, zzcu, zzbbi, null, null, null, zzum.zzoi());
        this.zzdin.getView().setWillNotDraw(true);
    }

    public final void zza(String str, Map map) {
        zzaid.zza((zzaic) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzaid.zzb(this, str, jSONObject);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        zzaid.zza((zzaic) this, str, jSONObject);
    }

    public final void zzh(String str, String str2) {
        zzaid.zza((zzaic) this, str, str2);
    }

    private static void runOnUiThread(Runnable runnable) {
        zzwu.zzpv();
        if (zzbat.zzaar()) {
            runnable.run();
        } else {
            zzayh.zzelc.post(runnable);
        }
    }

    public final void zzcg(String str) {
        runOnUiThread(new zzail(this, str));
    }

    public final void zzcd(String str) {
        runOnUiThread(new zzaio(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public final void zzce(String str) {
        runOnUiThread(new zzaip(this, str));
    }

    public final void zzcf(String str) {
        runOnUiThread(new zzaiq(this, str));
    }

    public final void zza(String str, zzu<? super zzajr> zzu) {
        this.zzdin.zza(str, (zzu<? super zzbgg>) new zzair<Object>(this, zzu));
    }

    public final void zzb(String str, zzu<? super zzajr> zzu) {
        this.zzdin.zza(str, (Predicate<zzu<? super zzbgg>>) new zzaim<zzu<? super zzbgg>>(zzu));
    }

    public final void zza(zzaij zzaij) {
        zzbhn zzadl = this.zzdin.zzadl();
        zzaij.getClass();
        zzadl.zza(zzain.zzb(zzaij));
    }

    public final zzajs zzua() {
        return new zzajt(this);
    }

    public final void destroy() {
        this.zzdin.destroy();
    }

    public final boolean isDestroyed() {
        return this.zzdin.isDestroyed();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzck(String str) {
        this.zzdin.zzcg(str);
    }
}
