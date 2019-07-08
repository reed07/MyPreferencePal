package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesMealHelperUtilFactory implements Factory<MealUtil> {
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<FoodMapper> foodMapperProvider;
    private final Provider<MealIngredientMapper> mealIngredientMapperProvider;
    private final Provider<MfpFoodMapper> mfpFoodMapperProvider;
    private final ApplicationModule module;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesMealHelperUtilFactory(ApplicationModule applicationModule, Provider<Session> provider, Provider<MfpFoodMapper> provider2, Provider<FoodMapper> provider3, Provider<MealIngredientMapper> provider4, Provider<DbConnectionManager> provider5) {
        this.module = applicationModule;
        this.sessionProvider = provider;
        this.mfpFoodMapperProvider = provider2;
        this.foodMapperProvider = provider3;
        this.mealIngredientMapperProvider = provider4;
        this.dbConnectionManagerProvider = provider5;
    }

    public MealUtil get() {
        return provideInstance(this.module, this.sessionProvider, this.mfpFoodMapperProvider, this.foodMapperProvider, this.mealIngredientMapperProvider, this.dbConnectionManagerProvider);
    }

    public static MealUtil provideInstance(ApplicationModule applicationModule, Provider<Session> provider, Provider<MfpFoodMapper> provider2, Provider<FoodMapper> provider3, Provider<MealIngredientMapper> provider4, Provider<DbConnectionManager> provider5) {
        return proxyProvidesMealHelperUtil(applicationModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static ApplicationModule_ProvidesMealHelperUtilFactory create(ApplicationModule applicationModule, Provider<Session> provider, Provider<MfpFoodMapper> provider2, Provider<FoodMapper> provider3, Provider<MealIngredientMapper> provider4, Provider<DbConnectionManager> provider5) {
        ApplicationModule_ProvidesMealHelperUtilFactory applicationModule_ProvidesMealHelperUtilFactory = new ApplicationModule_ProvidesMealHelperUtilFactory(applicationModule, provider, provider2, provider3, provider4, provider5);
        return applicationModule_ProvidesMealHelperUtilFactory;
    }

    public static MealUtil proxyProvidesMealHelperUtil(ApplicationModule applicationModule, Lazy<Session> lazy, Lazy<MfpFoodMapper> lazy2, Lazy<FoodMapper> lazy3, Lazy<MealIngredientMapper> lazy4, Lazy<DbConnectionManager> lazy5) {
        return (MealUtil) Preconditions.checkNotNull(applicationModule.providesMealHelperUtil(lazy, lazy2, lazy3, lazy4, lazy5), "Cannot return null from a non-@Nullable @Provides method");
    }
}
