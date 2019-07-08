package com.myfitnesspal.feature.nutrition.ui.view;

import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CaloriePieLegend_MembersInjector implements MembersInjector<CaloriePieLegend> {
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public CaloriePieLegend_MembersInjector(Provider<LocalizedStringsUtil> provider, Provider<UserEnergyService> provider2) {
        this.localizedStringsUtilProvider = provider;
        this.userEnergyServiceProvider = provider2;
    }

    public static MembersInjector<CaloriePieLegend> create(Provider<LocalizedStringsUtil> provider, Provider<UserEnergyService> provider2) {
        return new CaloriePieLegend_MembersInjector(provider, provider2);
    }

    public void injectMembers(CaloriePieLegend caloriePieLegend) {
        injectLocalizedStringsUtil(caloriePieLegend, DoubleCheck.lazy(this.localizedStringsUtilProvider));
        injectUserEnergyService(caloriePieLegend, DoubleCheck.lazy(this.userEnergyServiceProvider));
    }

    public static void injectLocalizedStringsUtil(CaloriePieLegend caloriePieLegend, Lazy<LocalizedStringsUtil> lazy) {
        caloriePieLegend.localizedStringsUtil = lazy;
    }

    public static void injectUserEnergyService(CaloriePieLegend caloriePieLegend, Lazy<UserEnergyService> lazy) {
        caloriePieLegend.userEnergyService = lazy;
    }
}
