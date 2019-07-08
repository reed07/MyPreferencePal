package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.config.ConfigService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class AddFoodDeepLinkMixin_MembersInjector implements MembersInjector<AddFoodDeepLinkMixin> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<AppIndexerBot> appIndexerBotProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<DbConnectionManager> dbConnectionManagerProvider;
    private final Provider<FoodMapper> foodMapperProvider;
    private final Provider<MfpFoodMapper> mfpFoodMapperProvider;
    private final Provider<SearchService> searchServiceProvider;

    public AddFoodDeepLinkMixin_MembersInjector(Provider<AnalyticsService> provider, Provider<AppIndexerBot> provider2, Provider<SearchService> provider3, Provider<FoodMapper> provider4, Provider<MfpFoodMapper> provider5, Provider<DbConnectionManager> provider6, Provider<ConfigService> provider7) {
        this.analyticsProvider = provider;
        this.appIndexerBotProvider = provider2;
        this.searchServiceProvider = provider3;
        this.foodMapperProvider = provider4;
        this.mfpFoodMapperProvider = provider5;
        this.dbConnectionManagerProvider = provider6;
        this.configServiceProvider = provider7;
    }

    public static MembersInjector<AddFoodDeepLinkMixin> create(Provider<AnalyticsService> provider, Provider<AppIndexerBot> provider2, Provider<SearchService> provider3, Provider<FoodMapper> provider4, Provider<MfpFoodMapper> provider5, Provider<DbConnectionManager> provider6, Provider<ConfigService> provider7) {
        AddFoodDeepLinkMixin_MembersInjector addFoodDeepLinkMixin_MembersInjector = new AddFoodDeepLinkMixin_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return addFoodDeepLinkMixin_MembersInjector;
    }

    public void injectMembers(AddFoodDeepLinkMixin addFoodDeepLinkMixin) {
        DeepLinkMixinBase_MembersInjector.injectAnalytics(addFoodDeepLinkMixin, DoubleCheck.lazy(this.analyticsProvider));
        AppIndexerMixinBase_MembersInjector.injectAnalytics(addFoodDeepLinkMixin, DoubleCheck.lazy(this.analyticsProvider));
        AppIndexerMixinBase_MembersInjector.injectAppIndexerBot(addFoodDeepLinkMixin, DoubleCheck.lazy(this.appIndexerBotProvider));
        injectSearchService(addFoodDeepLinkMixin, DoubleCheck.lazy(this.searchServiceProvider));
        injectAnalytics(addFoodDeepLinkMixin, DoubleCheck.lazy(this.analyticsProvider));
        injectFoodMapper(addFoodDeepLinkMixin, DoubleCheck.lazy(this.foodMapperProvider));
        injectMfpFoodMapper(addFoodDeepLinkMixin, DoubleCheck.lazy(this.mfpFoodMapperProvider));
        injectDbConnectionManager(addFoodDeepLinkMixin, DoubleCheck.lazy(this.dbConnectionManagerProvider));
        injectConfigService(addFoodDeepLinkMixin, DoubleCheck.lazy(this.configServiceProvider));
    }

    public static void injectSearchService(AddFoodDeepLinkMixin addFoodDeepLinkMixin, Lazy<SearchService> lazy) {
        addFoodDeepLinkMixin.searchService = lazy;
    }

    public static void injectAnalytics(AddFoodDeepLinkMixin addFoodDeepLinkMixin, Lazy<AnalyticsService> lazy) {
        addFoodDeepLinkMixin.analytics = lazy;
    }

    public static void injectFoodMapper(AddFoodDeepLinkMixin addFoodDeepLinkMixin, Lazy<FoodMapper> lazy) {
        addFoodDeepLinkMixin.foodMapper = lazy;
    }

    public static void injectMfpFoodMapper(AddFoodDeepLinkMixin addFoodDeepLinkMixin, Lazy<MfpFoodMapper> lazy) {
        addFoodDeepLinkMixin.mfpFoodMapper = lazy;
    }

    public static void injectDbConnectionManager(AddFoodDeepLinkMixin addFoodDeepLinkMixin, Lazy<DbConnectionManager> lazy) {
        addFoodDeepLinkMixin.dbConnectionManager = lazy;
    }

    public static void injectConfigService(AddFoodDeepLinkMixin addFoodDeepLinkMixin, Lazy<ConfigService> lazy) {
        addFoodDeepLinkMixin.configService = lazy;
    }
}
