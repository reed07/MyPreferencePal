package com.myfitnesspal.shared.receiver;

import android.content.BroadcastReceiver;
import dagger.internal.Factory;
import java.util.List;
import javax.inject.Provider;

public final class MfpInstallReferrerReceiver_Factory implements Factory<MfpInstallReferrerReceiver> {
    private final Provider<List<BroadcastReceiver>> broadcastReceiversProvider;

    public MfpInstallReferrerReceiver_Factory(Provider<List<BroadcastReceiver>> provider) {
        this.broadcastReceiversProvider = provider;
    }

    public MfpInstallReferrerReceiver get() {
        return provideInstance(this.broadcastReceiversProvider);
    }

    public static MfpInstallReferrerReceiver provideInstance(Provider<List<BroadcastReceiver>> provider) {
        MfpInstallReferrerReceiver mfpInstallReferrerReceiver = new MfpInstallReferrerReceiver();
        MfpInstallReferrerReceiver_MembersInjector.injectBroadcastReceivers(mfpInstallReferrerReceiver, (List) provider.get());
        return mfpInstallReferrerReceiver;
    }

    public static MfpInstallReferrerReceiver_Factory create(Provider<List<BroadcastReceiver>> provider) {
        return new MfpInstallReferrerReceiver_Factory(provider);
    }

    public static MfpInstallReferrerReceiver newMfpInstallReferrerReceiver() {
        return new MfpInstallReferrerReceiver();
    }
}
