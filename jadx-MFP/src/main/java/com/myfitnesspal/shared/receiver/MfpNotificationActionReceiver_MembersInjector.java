package com.myfitnesspal.shared.receiver;

import com.myfitnesspal.shared.notification.JobServiceFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MfpNotificationActionReceiver_MembersInjector implements MembersInjector<MfpNotificationActionReceiver> {
    private final Provider<JobServiceFactory> jobServiceFactoryProvider;

    public MfpNotificationActionReceiver_MembersInjector(Provider<JobServiceFactory> provider) {
        this.jobServiceFactoryProvider = provider;
    }

    public static MembersInjector<MfpNotificationActionReceiver> create(Provider<JobServiceFactory> provider) {
        return new MfpNotificationActionReceiver_MembersInjector(provider);
    }

    public void injectMembers(MfpNotificationActionReceiver mfpNotificationActionReceiver) {
        injectJobServiceFactory(mfpNotificationActionReceiver, (JobServiceFactory) this.jobServiceFactoryProvider.get());
    }

    public static void injectJobServiceFactory(MfpNotificationActionReceiver mfpNotificationActionReceiver, JobServiceFactory jobServiceFactory) {
        mfpNotificationActionReceiver.jobServiceFactory = jobServiceFactory;
    }
}
