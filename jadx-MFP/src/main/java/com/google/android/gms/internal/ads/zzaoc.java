package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.util.Map;

@zzark
public final class zzaoc {
    private final zzbgg zzdin;
    private final boolean zzdpt;
    private final String zzdpu;

    public zzaoc(zzbgg zzbgg, Map<String, String> map) {
        this.zzdin = zzbgg;
        this.zzdpu = (String) map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzdpt = Boolean.parseBoolean((String) map.get("allowOrientationChange"));
        } else {
            this.zzdpt = true;
        }
    }

    public final void execute() {
        int i;
        if (this.zzdin == null) {
            zzaxz.zzeo("AdWebView is null");
            return;
        }
        if (Extras.ORIENTATION_PORTRAIT.equalsIgnoreCase(this.zzdpu)) {
            i = zzbv.zzlh().zzzx();
        } else if (Extras.ORIENTATION_LANDSCAPE.equalsIgnoreCase(this.zzdpu)) {
            i = zzbv.zzlh().zzzw();
        } else if (this.zzdpt) {
            i = -1;
        } else {
            i = zzbv.zzlh().zzzy();
        }
        this.zzdin.setRequestedOrientation(i);
    }
}
