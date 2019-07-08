package com.google.android.gms.ads.mediation;

import android.os.Bundle;

public interface MediationAdapter {

    public static class zza {
        private int zzfbj;

        public final zza zzdi(int i) {
            this.zzfbj = 1;
            return this;
        }

        public final Bundle zzafg() {
            Bundle bundle = new Bundle();
            bundle.putInt("capabilities", this.zzfbj);
            return bundle;
        }
    }

    void onDestroy();

    void onPause();

    void onResume();
}
