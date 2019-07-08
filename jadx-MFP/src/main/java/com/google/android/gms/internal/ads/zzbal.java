package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzbal {
    public static boolean zzf(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(AdMobAdapter.class.getName());
        if (bundle2 == null || !bundle2.getBoolean("render_test_ad_label", false)) {
            return false;
        }
        return true;
    }
}
