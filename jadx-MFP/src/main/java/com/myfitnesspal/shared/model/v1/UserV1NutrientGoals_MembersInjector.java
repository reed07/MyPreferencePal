package com.myfitnesspal.shared.model.v1;

import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class UserV1NutrientGoals_MembersInjector implements MembersInjector<UserV1NutrientGoals> {
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<UserEnergyService> userEnergyServiceProvider;

    public UserV1NutrientGoals_MembersInjector(Provider<UserEnergyService> provider, Provider<NutrientGoalsUtil> provider2) {
        this.userEnergyServiceProvider = provider;
        this.nutrientGoalsUtilProvider = provider2;
    }

    public static MembersInjector<UserV1NutrientGoals> create(Provider<UserEnergyService> provider, Provider<NutrientGoalsUtil> provider2) {
        return new UserV1NutrientGoals_MembersInjector(provider, provider2);
    }

    public void injectMembers(UserV1NutrientGoals userV1NutrientGoals) {
        injectUserEnergyService(userV1NutrientGoals, DoubleCheck.lazy(this.userEnergyServiceProvider));
        injectNutrientGoalsUtil(userV1NutrientGoals, DoubleCheck.lazy(this.nutrientGoalsUtilProvider));
    }

    public static void injectUserEnergyService(UserV1NutrientGoals userV1NutrientGoals, Lazy<UserEnergyService> lazy) {
        userV1NutrientGoals.userEnergyService = lazy;
    }

    public static void injectNutrientGoalsUtil(UserV1NutrientGoals userV1NutrientGoals, Lazy<NutrientGoalsUtil> lazy) {
        userV1NutrientGoals.nutrientGoalsUtil = lazy;
    }
}
