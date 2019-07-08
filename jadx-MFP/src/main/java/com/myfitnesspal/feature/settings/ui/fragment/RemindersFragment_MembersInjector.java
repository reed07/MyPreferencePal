package com.myfitnesspal.feature.settings.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class RemindersFragment_MembersInjector implements MembersInjector<RemindersFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<RemindersService> remindersServiceProvider;
    private final Provider<SyncService> syncServiceProvider;

    public RemindersFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<RemindersService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<SyncService> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.remindersServiceProvider = provider3;
        this.localizedStringsUtilProvider = provider4;
        this.syncServiceProvider = provider5;
    }

    public static MembersInjector<RemindersFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<RemindersService> provider3, Provider<LocalizedStringsUtil> provider4, Provider<SyncService> provider5) {
        RemindersFragment_MembersInjector remindersFragment_MembersInjector = new RemindersFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return remindersFragment_MembersInjector;
    }

    public void injectMembers(RemindersFragment remindersFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(remindersFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(remindersFragment, (Glide) this.glideProvider.get());
        injectRemindersService(remindersFragment, DoubleCheck.lazy(this.remindersServiceProvider));
        injectLocalizedStringsUtil(remindersFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectSyncService(remindersFragment, DoubleCheck.lazy(this.syncServiceProvider));
    }

    public static void injectRemindersService(RemindersFragment remindersFragment, Lazy<RemindersService> lazy) {
        remindersFragment.remindersService = lazy;
    }

    public static void injectLocalizedStringsUtil(RemindersFragment remindersFragment, Lazy<LocalizedStringsUtil> lazy) {
        remindersFragment.localizedStringsUtil = lazy;
    }

    public static void injectSyncService(RemindersFragment remindersFragment, Lazy<SyncService> lazy) {
        remindersFragment.syncService = lazy;
    }
}
