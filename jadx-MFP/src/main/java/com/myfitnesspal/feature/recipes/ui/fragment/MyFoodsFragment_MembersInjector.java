package com.myfitnesspal.feature.recipes.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MyFoodsFragment_MembersInjector implements MembersInjector<MyFoodsFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<LocalSettingsService> localSettingsServiceProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public MyFoodsFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<DbConnectionManager> provider3, Provider<UserEnergyService> provider4, Provider<LocalSettingsService> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.dbConnectionManagerProvider = provider3;
        this.userEnergyServiceProvider = provider4;
        this.localSettingsServiceProvider = provider5;
    }

    public static MembersInjector<MyFoodsFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<DbConnectionManager> provider3, Provider<UserEnergyService> provider4, Provider<LocalSettingsService> provider5) {
        MyFoodsFragment_MembersInjector myFoodsFragment_MembersInjector = new MyFoodsFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return myFoodsFragment_MembersInjector;
    }

    public void injectMembers(MyFoodsFragment myFoodsFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(myFoodsFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(myFoodsFragment, (Glide) this.glideProvider.get());
        injectDbConnectionManager(myFoodsFragment, (DbConnectionManager) this.dbConnectionManagerProvider.get());
        injectUserEnergyService(myFoodsFragment, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectLocalSettingsService(myFoodsFragment, DoubleCheck.lazy(this.localSettingsServiceProvider));
    }

    public static void injectDbConnectionManager(MyFoodsFragment myFoodsFragment, DbConnectionManager dbConnectionManager) {
        myFoodsFragment.dbConnectionManager = dbConnectionManager;
    }

    public static void injectUserEnergyService(MyFoodsFragment myFoodsFragment, Lazy<UserEnergyService> lazy) {
        myFoodsFragment.userEnergyService = lazy;
    }

    public static void injectLocalSettingsService(MyFoodsFragment myFoodsFragment, Lazy<LocalSettingsService> lazy) {
        myFoodsFragment.localSettingsService = lazy;
    }
}
