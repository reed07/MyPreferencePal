package com.myfitnesspal.feature.notificationinbox.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.notificationinbox.util.NotificationInboxAnalyticsHelper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class NotificationInboxFragment_MembersInjector implements MembersInjector<NotificationInboxFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<NotificationInboxAnalyticsHelper> inboxAnalyticsHelperProvider;
    private final Provider<SyncService> syncServiceProvider;

    public NotificationInboxFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NotificationInboxAnalyticsHelper> provider3, Provider<SyncService> provider4) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.inboxAnalyticsHelperProvider = provider3;
        this.syncServiceProvider = provider4;
    }

    public static MembersInjector<NotificationInboxFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<NotificationInboxAnalyticsHelper> provider3, Provider<SyncService> provider4) {
        return new NotificationInboxFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(NotificationInboxFragment notificationInboxFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(notificationInboxFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(notificationInboxFragment, (Glide) this.glideProvider.get());
        injectInboxAnalyticsHelper(notificationInboxFragment, DoubleCheck.lazy(this.inboxAnalyticsHelperProvider));
        injectSyncService(notificationInboxFragment, DoubleCheck.lazy(this.syncServiceProvider));
    }

    public static void injectInboxAnalyticsHelper(NotificationInboxFragment notificationInboxFragment, Lazy<NotificationInboxAnalyticsHelper> lazy) {
        notificationInboxFragment.inboxAnalyticsHelper = lazy;
    }

    public static void injectSyncService(NotificationInboxFragment notificationInboxFragment, Lazy<SyncService> lazy) {
        notificationInboxFragment.syncService = lazy;
    }
}
