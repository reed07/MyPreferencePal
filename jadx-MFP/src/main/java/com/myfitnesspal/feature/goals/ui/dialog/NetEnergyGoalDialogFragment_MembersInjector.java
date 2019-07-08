package com.myfitnesspal.feature.goals.ui.dialog;

import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment_MembersInjector;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class NetEnergyGoalDialogFragment_MembersInjector implements MembersInjector<NetEnergyGoalDialogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<UacfScheduler<SyncType>> syncSchedulerProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public NetEnergyGoalDialogFragment_MembersInjector(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserEnergyService> provider5, Provider<LocalizedStringsUtil> provider6, Provider<UacfScheduler<SyncType>> provider7, Provider<DbConnectionManager> provider8) {
        this.messageBusProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.analyticsServiceProvider = provider4;
        this.userEnergyServiceProvider = provider5;
        this.localizedStringsUtilProvider = provider6;
        this.syncSchedulerProvider = provider7;
        this.dbConnectionManagerProvider = provider8;
    }

    public static MembersInjector<NetEnergyGoalDialogFragment> create(Provider<Bus> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<AnalyticsService> provider4, Provider<UserEnergyService> provider5, Provider<LocalizedStringsUtil> provider6, Provider<UacfScheduler<SyncType>> provider7, Provider<DbConnectionManager> provider8) {
        NetEnergyGoalDialogFragment_MembersInjector netEnergyGoalDialogFragment_MembersInjector = new NetEnergyGoalDialogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return netEnergyGoalDialogFragment_MembersInjector;
    }

    public void injectMembers(NetEnergyGoalDialogFragment netEnergyGoalDialogFragment) {
        CustomLayoutBaseDialogFragment_MembersInjector.injectMessageBus(netEnergyGoalDialogFragment, (Bus) this.messageBusProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectNavigationHelper(netEnergyGoalDialogFragment, (NavigationHelper) this.navigationHelperProvider.get());
        CustomLayoutBaseDialogFragment_MembersInjector.injectSession(netEnergyGoalDialogFragment, DoubleCheck.lazy(this.sessionProvider));
        CustomLayoutBaseDialogFragment_MembersInjector.injectAnalyticsService(netEnergyGoalDialogFragment, DoubleCheck.lazy(this.analyticsServiceProvider));
        injectUserEnergyService(netEnergyGoalDialogFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalizedStringsUtil(netEnergyGoalDialogFragment, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectSyncScheduler(netEnergyGoalDialogFragment, DoubleCheck.lazy(this.syncSchedulerProvider));
        injectDbConnectionManager(netEnergyGoalDialogFragment, DoubleCheck.lazy(this.dbConnectionManagerProvider));
    }

    public static void injectUserEnergyService(NetEnergyGoalDialogFragment netEnergyGoalDialogFragment, Lazy<UserEnergyService> lazy) {
        netEnergyGoalDialogFragment.userEnergyService = lazy;
    }

    public static void injectLocalizedStringsUtil(NetEnergyGoalDialogFragment netEnergyGoalDialogFragment, Lazy<LocalizedStringsUtil> lazy) {
        netEnergyGoalDialogFragment.localizedStringsUtil = lazy;
    }

    public static void injectSyncScheduler(NetEnergyGoalDialogFragment netEnergyGoalDialogFragment, Lazy<UacfScheduler<SyncType>> lazy) {
        netEnergyGoalDialogFragment.syncScheduler = lazy;
    }

    public static void injectDbConnectionManager(NetEnergyGoalDialogFragment netEnergyGoalDialogFragment, Lazy<DbConnectionManager> lazy) {
        netEnergyGoalDialogFragment.dbConnectionManager = lazy;
    }
}
