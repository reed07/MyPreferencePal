package com.crashlytics.android.core;

import android.annotation.SuppressLint;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.persistence.PreferenceStoreImpl;

@SuppressLint({"CommitPrefEdits"})
class PreferenceManager {
    private final CrashlyticsCore kit;
    private final PreferenceStore preferenceStore;

    public static PreferenceManager create(PreferenceStore preferenceStore2, CrashlyticsCore crashlyticsCore) {
        return new PreferenceManager(preferenceStore2, crashlyticsCore);
    }

    public PreferenceManager(PreferenceStore preferenceStore2, CrashlyticsCore crashlyticsCore) {
        this.preferenceStore = preferenceStore2;
        this.kit = crashlyticsCore;
    }

    /* access modifiers changed from: 0000 */
    public void setShouldAlwaysSendReports(boolean z) {
        PreferenceStore preferenceStore2 = this.preferenceStore;
        preferenceStore2.save(preferenceStore2.edit().putBoolean("always_send_reports_opt_in", z));
    }

    /* access modifiers changed from: 0000 */
    public boolean shouldAlwaysSendReports() {
        if (!this.preferenceStore.get().contains("preferences_migration_complete")) {
            PreferenceStoreImpl preferenceStoreImpl = new PreferenceStoreImpl(this.kit);
            if (!this.preferenceStore.get().contains("always_send_reports_opt_in") && preferenceStoreImpl.get().contains("always_send_reports_opt_in")) {
                boolean z = preferenceStoreImpl.get().getBoolean("always_send_reports_opt_in", false);
                PreferenceStore preferenceStore2 = this.preferenceStore;
                preferenceStore2.save(preferenceStore2.edit().putBoolean("always_send_reports_opt_in", z));
            }
            PreferenceStore preferenceStore3 = this.preferenceStore;
            preferenceStore3.save(preferenceStore3.edit().putBoolean("preferences_migration_complete", true));
        }
        return this.preferenceStore.get().getBoolean("always_send_reports_opt_in", false);
    }
}
