package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNutrientGoalServiceFactory implements Factory<NutrientGoalService> {
    private final Provider<MfpV2Api> apiProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<NutrientGoalsUtil> nutrientGoalsUtilProvider;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<Session> sessionProvider;

    public ApplicationModule_ProvidesNutrientGoalServiceFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<PremiumService> provider4, Provider<NutrientGoalsUtil> provider5) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.apiProvider = provider2;
        this.sessionProvider = provider3;
        this.premiumServiceProvider = provider4;
        this.nutrientGoalsUtilProvider = provider5;
    }

    public NutrientGoalService get() {
        return provideInstance(this.module, this.contextProvider, this.apiProvider, this.sessionProvider, this.premiumServiceProvider, this.nutrientGoalsUtilProvider);
    }

    public static NutrientGoalService provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<PremiumService> provider4, Provider<NutrientGoalsUtil> provider5) {
        return proxyProvidesNutrientGoalService(applicationModule, (Context) provider.get(), provider2, DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
    }

    public static ApplicationModule_ProvidesNutrientGoalServiceFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<MfpV2Api> provider2, Provider<Session> provider3, Provider<PremiumService> provider4, Provider<NutrientGoalsUtil> provider5) {
        ApplicationModule_ProvidesNutrientGoalServiceFactory applicationModule_ProvidesNutrientGoalServiceFactory = new ApplicationModule_ProvidesNutrientGoalServiceFactory(applicationModule, provider, provider2, provider3, provider4, provider5);
        return applicationModule_ProvidesNutrientGoalServiceFactory;
    }

    public static NutrientGoalService proxyProvidesNutrientGoalService(ApplicationModule applicationModule, Context context, Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<PremiumService> lazy2, Lazy<NutrientGoalsUtil> lazy3) {
        return (NutrientGoalService) Preconditions.checkNotNull(applicationModule.providesNutrientGoalService(context, provider, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
