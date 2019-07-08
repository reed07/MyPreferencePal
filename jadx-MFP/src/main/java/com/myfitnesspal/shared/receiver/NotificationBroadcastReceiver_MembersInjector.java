package com.myfitnesspal.shared.receiver;

import com.myfitnesspal.shared.notification.JobServiceFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class NotificationBroadcastReceiver_MembersInjector implements MembersInjector<NotificationBroadcastReceiver> {
    private final Provider<JobServiceFactory> jobServiceFactoryProvider;

    public NotificationBroadcastReceiver_MembersInjector(Provider<JobServiceFactory> provider) {
        this.jobServiceFactoryProvider = provider;
    }

    public static MembersInjector<NotificationBroadcastReceiver> create(Provider<JobServiceFactory> provider) {
        return new NotificationBroadcastReceiver_MembersInjector(provider);
    }

    public void injectMembers(NotificationBroadcastReceiver notificationBroadcastReceiver) {
        injectJobServiceFactory(notificationBroadcastReceiver, (JobServiceFactory) this.jobServiceFactoryProvider.get());
    }

    public static void injectJobServiceFactory(NotificationBroadcastReceiver notificationBroadcastReceiver, JobServiceFactory jobServiceFactory) {
        notificationBroadcastReceiver.jobServiceFactory = jobServiceFactory;
    }
}
