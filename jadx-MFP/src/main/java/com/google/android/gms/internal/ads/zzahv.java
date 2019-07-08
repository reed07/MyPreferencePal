package com.google.android.gms.internal.ads;

import android.content.Context;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzahv extends zzaig<zzajr> implements zzaic, zzaii {
    private final zzbig zzdid;
    /* access modifiers changed from: private */
    public zzaij zzdie;

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.ads.zzahv, com.google.android.gms.internal.ads.zzaig, java.lang.Object, com.google.android.gms.internal.ads.zzaif] */
    public zzahv(Context context, zzbbi zzbbi) throws zzbgq {
        try {
            this.zzdid = new zzbig(context, new zzaib(this));
            this.zzdid.setWillNotDraw(true);
            this.zzdid.addJavascriptInterface(new zzaia(this), "GoogleJsInterface");
            zzbv.zzlf().zza(context, zzbbi.zzdp, this.zzdid.getSettings());
            super.zzi(this);
        } catch (Throwable th) {
            throw new zzbgq("Init failed.", th);
        }
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

    public final void zzcd(String str) {
        zzce(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", new Object[]{str}));
    }

    public final void zzce(String str) {
        zzbcg.zzepo.execute(new zzahw(this, str));
    }

    public final void zzcf(String str) {
        zzbcg.zzepo.execute(new zzahx(this, str));
    }

    public final void zza(zzaij zzaij) {
        this.zzdie = zzaij;
    }

    public final void destroy() {
        this.zzdid.destroy();
    }

    public final boolean isDestroyed() {
        return this.zzdid.isDestroyed();
    }

    public final zzajs zzua() {
        return new zzajt(this);
    }

    public final void zzcg(String str) {
        zzbcg.zzepo.execute(new zzahy(this, str));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzch(String str) {
        this.zzdid.zzcg(str);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzci(String str) {
        this.zzdid.loadUrl(str);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzcj(String str) {
        this.zzdid.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
    }
}
