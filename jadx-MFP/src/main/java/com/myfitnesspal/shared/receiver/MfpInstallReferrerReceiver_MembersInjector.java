package com.myfitnesspal.shared.receiver;

import android.content.BroadcastReceiver;
import dagger.MembersInjector;
import java.util.List;
import javax.inject.Provider;

public final class MfpInstallReferrerReceiver_MembersInjector implements MembersInjector<MfpInstallReferrerReceiver> {
    private final Provider<List<BroadcastReceiver>> broadcastReceiversProvider;

    public MfpInstallReferrerReceiver_MembersInjector(Provider<List<BroadcastReceiver>> provider) {
        this.broadcastReceiversProvider = provider;
    }

    public static MembersInjector<MfpInstallReferrerReceiver> create(Provider<List<BroadcastReceiver>> provider) {
        return new MfpInstallReferrerReceiver_MembersInjector(provider);
    }

    public void injectMembers(MfpInstallReferrerReceiver mfpInstallReferrerReceiver) {
        injectBroadcastReceivers(mfpInstallReferrerReceiver, (List) this.broadcastReceiversProvider.get());
    }

    public static void injectBroadcastReceivers(MfpInstallReferrerReceiver mfpInstallReferrerReceiver, List<BroadcastReceiver> list) {
        mfpInstallReferrerReceiver.broadcastReceivers = list;
    }
}
