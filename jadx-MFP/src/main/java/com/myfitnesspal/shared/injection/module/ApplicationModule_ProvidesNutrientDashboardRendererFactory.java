package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.dashboard.ui.view.RadialGraphNutrientDashboard;
import com.myfitnesspal.feature.dashboard.ui.view.TextNutrientDashboard;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.service.session.Session;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ApplicationModule_ProvidesNutrientDashboardRendererFactory implements Factory<NutrientDashboardRenderer> {
    private final ApplicationModule module;
    private final Provider<PremiumService> premiumServiceProvider;
    private final Provider<RadialGraphNutrientDashboard> radialGraphNutrientDashboardProvider;
    private final Provider<Session> sessionProvider;
    private final Provider<TextNutrientDashboard> textNutrientDashboardProvider;

    public ApplicationModule_ProvidesNutrientDashboardRendererFactory(ApplicationModule applicationModule, Provider<TextNutrientDashboard> provider, Provider<RadialGraphNutrientDashboard> provider2, Provider<Session> provider3, Provider<PremiumService> provider4) {
        this.module = applicationModule;
        this.textNutrientDashboardProvider = provider;
        this.radialGraphNutrientDashboardProvider = provider2;
        this.sessionProvider = provider3;
        this.premiumServiceProvider = provider4;
    }

    public NutrientDashboardRenderer get() {
        return provideInstance(this.module, this.textNutrientDashboardProvider, this.radialGraphNutrientDashboardProvider, this.sessionProvider, this.premiumServiceProvider);
    }

    public static NutrientDashboardRenderer provideInstance(ApplicationModule applicationModule, Provider<TextNutrientDashboard> provider, Provider<RadialGraphNutrientDashboard> provider2, Provider<Session> provider3, Provider<PremiumService> provider4) {
        return proxyProvidesNutrientDashboardRenderer(applicationModule, provider, provider2, DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static ApplicationModule_ProvidesNutrientDashboardRendererFactory create(ApplicationModule applicationModule, Provider<TextNutrientDashboard> provider, Provider<RadialGraphNutrientDashboard> provider2, Provider<Session> provider3, Provider<PremiumService> provider4) {
        ApplicationModule_ProvidesNutrientDashboardRendererFactory applicationModule_ProvidesNutrientDashboardRendererFactory = new ApplicationModule_ProvidesNutrientDashboardRendererFactory(applicationModule, provider, provider2, provider3, provider4);
        return applicationModule_ProvidesNutrientDashboardRendererFactory;
    }

    public static NutrientDashboardRenderer proxyProvidesNutrientDashboardRenderer(ApplicationModule applicationModule, Provider<TextNutrientDashboard> provider, Provider<RadialGraphNutrientDashboard> provider2, Lazy<Session> lazy, Lazy<PremiumService> lazy2) {
        return (NutrientDashboardRenderer) Preconditions.checkNotNull(applicationModule.providesNutrientDashboardRenderer(provider, provider2, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
