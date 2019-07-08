package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserEnergyService_Factory implements Factory<UserEnergyService> {
    private final Provider<Context> contextProvider;
    private final Provider<LocalizedStringsUtil> localizedStringsUtilProvider;
    private final Provider<MealUtil> mealHelperUtilProvider;
    private final Provider<Session> sessionProvider;

    public UserEnergyService_Factory(Provider<Context> provider, Provider<Session> provider2, Provider<LocalizedStringsUtil> provider3, Provider<MealUtil> provider4) {
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.localizedStringsUtilProvider = provider3;
        this.mealHelperUtilProvider = provider4;
    }

    public UserEnergyService get() {
        return provideInstance(this.contextProvider, this.sessionProvider, this.localizedStringsUtilProvider, this.mealHelperUtilProvider);
    }

    public static UserEnergyService provideInstance(Provider<Context> provider, Provider<Session> provider2, Provider<LocalizedStringsUtil> provider3, Provider<MealUtil> provider4) {
        return new UserEnergyService((Context) provider.get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static UserEnergyService_Factory create(Provider<Context> provider, Provider<Session> provider2, Provider<LocalizedStringsUtil> provider3, Provider<MealUtil> provider4) {
        return new UserEnergyService_Factory(provider, provider2, provider3, provider4);
    }

    public static UserEnergyService newUserEnergyService(Context context, Lazy<Session> lazy, Lazy<LocalizedStringsUtil> lazy2, Lazy<MealUtil> lazy3) {
        return new UserEnergyService(context, lazy, lazy2, lazy3);
    }
}
