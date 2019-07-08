package com.crashlytics.android.beta;

import android.annotation.SuppressLint;
import android.content.Context;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.common.ApiKey;
import io.fabric.sdk.android.services.common.CurrentTimeProvider;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.common.IdManager.DeviceIdentifierType;
import io.fabric.sdk.android.services.network.HttpRequestFactory;
import io.fabric.sdk.android.services.persistence.PreferenceStore;
import io.fabric.sdk.android.services.settings.BetaSettingsData;
import java.util.concurrent.atomic.AtomicBoolean;

abstract class AbstractCheckForUpdatesController implements UpdatesController {
    private Beta beta;
    private BetaSettingsData betaSettings;
    private BuildProperties buildProps;
    private Context context;
    private CurrentTimeProvider currentTimeProvider;
    private final AtomicBoolean externallyReady;
    private HttpRequestFactory httpRequestFactory;
    private IdManager idManager;
    private final AtomicBoolean initialized;
    private long lastCheckTimeMillis;
    private PreferenceStore preferenceStore;

    public AbstractCheckForUpdatesController() {
        this(false);
    }

    public AbstractCheckForUpdatesController(boolean z) {
        this.initialized = new AtomicBoolean();
        this.lastCheckTimeMillis = 0;
        this.externallyReady = new AtomicBoolean(z);
    }

    public void initialize(Context context2, Beta beta2, IdManager idManager2, BetaSettingsData betaSettingsData, BuildProperties buildProperties, PreferenceStore preferenceStore2, CurrentTimeProvider currentTimeProvider2, HttpRequestFactory httpRequestFactory2) {
        this.context = context2;
        this.beta = beta2;
        this.idManager = idManager2;
        this.betaSettings = betaSettingsData;
        this.buildProps = buildProperties;
        this.preferenceStore = preferenceStore2;
        this.currentTimeProvider = currentTimeProvider2;
        this.httpRequestFactory = httpRequestFactory2;
        if (signalInitialized()) {
            checkForUpdates();
        }
    }

    /* access modifiers changed from: protected */
    public boolean signalExternallyReady() {
        this.externallyReady.set(true);
        return this.initialized.get();
    }

    /* access modifiers changed from: 0000 */
    public boolean signalInitialized() {
        this.initialized.set(true);
        return this.externallyReady.get();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"CommitPrefEdits"})
    public void checkForUpdates() {
        synchronized (this.preferenceStore) {
            if (this.preferenceStore.get().contains("last_update_check")) {
                this.preferenceStore.save(this.preferenceStore.edit().remove("last_update_check"));
            }
        }
        long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
        long j = ((long) this.betaSettings.updateSuspendDurationSeconds) * 1000;
        StringBuilder sb = new StringBuilder();
        sb.append("Check for updates delay: ");
        sb.append(j);
        Fabric.getLogger().d("Beta", sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Check for updates last check time: ");
        sb2.append(getLastCheckTimeMillis());
        Fabric.getLogger().d("Beta", sb2.toString());
        long lastCheckTimeMillis2 = getLastCheckTimeMillis() + j;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Check for updates current time: ");
        sb3.append(currentTimeMillis);
        sb3.append(", next check time: ");
        sb3.append(lastCheckTimeMillis2);
        Fabric.getLogger().d("Beta", sb3.toString());
        if (currentTimeMillis >= lastCheckTimeMillis2) {
            try {
                performUpdateCheck();
            } finally {
                setLastCheckTimeMillis(currentTimeMillis);
            }
        } else {
            Fabric.getLogger().d("Beta", "Check for updates next check time was not passed");
        }
    }

    private void performUpdateCheck() {
        Fabric.getLogger().d("Beta", "Performing update check");
        String value = new ApiKey().getValue(this.context);
        String str = (String) this.idManager.getDeviceIdentifiers().get(DeviceIdentifierType.FONT_TOKEN);
        Beta beta2 = this.beta;
        CheckForUpdatesRequest checkForUpdatesRequest = new CheckForUpdatesRequest(beta2, beta2.getOverridenSpiEndpoint(), this.betaSettings.updateUrl, this.httpRequestFactory, new CheckForUpdatesResponseTransform());
        checkForUpdatesRequest.invoke(value, str, this.buildProps);
    }

    /* access modifiers changed from: 0000 */
    public void setLastCheckTimeMillis(long j) {
        this.lastCheckTimeMillis = j;
    }

    /* access modifiers changed from: 0000 */
    public long getLastCheckTimeMillis() {
        return this.lastCheckTimeMillis;
    }
}
