package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@zzark
public final class zzaog {
    private final boolean zzdpy;
    private final boolean zzdpz;
    private final boolean zzdqa;
    private final boolean zzdqb;
    private final boolean zzdqc;

    private zzaog(zzaoi zzaoi) {
        this.zzdpy = zzaoi.zzdpy;
        this.zzdpz = zzaoi.zzdpz;
        this.zzdqa = zzaoi.zzdqa;
        this.zzdqb = zzaoi.zzdqb;
        this.zzdqc = zzaoi.zzdqc;
    }

    public final JSONObject zzvn() {
        try {
            return new JSONObject().put("sms", this.zzdpy).put("tel", this.zzdpz).put("calendar", this.zzdqa).put("storePicture", this.zzdqb).put("inlineVideo", this.zzdqc);
        } catch (JSONException e) {
            zzaxz.zzb("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
