package com.myfitnesspal.feature.blog.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class BlogFragment_MembersInjector implements MembersInjector<BlogFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<CountryService> countryServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<PremiumService> premiumServiceProvider;

    public BlogFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<ConfigService> provider4, Provider<CountryService> provider5, Provider<PremiumService> provider6, Provider<ApiUrlProvider> provider7) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.countryServiceProvider = provider5;
        this.premiumServiceProvider = provider6;
        this.apiUrlProvider = provider7;
    }

    public static MembersInjector<BlogFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<ConfigService> provider4, Provider<CountryService> provider5, Provider<PremiumService> provider6, Provider<ApiUrlProvider> provider7) {
        BlogFragment_MembersInjector blogFragment_MembersInjector = new BlogFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return blogFragment_MembersInjector;
    }

    public void injectMembers(BlogFragment blogFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(blogFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(blogFragment, (Glide) this.glideProvider.get());
        ExternalWebViewFragment_MembersInjector.injectAnalyticsService(blogFragment, (AnalyticsService) this.analyticsServiceProvider.get());
        ExternalWebViewFragment_MembersInjector.injectConfigService(blogFragment, (ConfigService) this.configServiceProvider.get());
        injectCountryService(blogFragment, DoubleCheck.lazy(this.countryServiceProvider));
        injectPremiumService(blogFragment, DoubleCheck.lazy(this.premiumServiceProvider));
        injectApiUrlProvider(blogFragment, DoubleCheck.lazy(this.apiUrlProvider));
    }

    public static void injectCountryService(BlogFragment blogFragment, Lazy<CountryService> lazy) {
        blogFragment.countryService = lazy;
    }

    public static void injectPremiumService(BlogFragment blogFragment, Lazy<PremiumService> lazy) {
        blogFragment.premiumService = lazy;
    }

    public static void injectApiUrlProvider(BlogFragment blogFragment, Lazy<ApiUrlProvider> lazy) {
        blogFragment.apiUrlProvider = lazy;
    }
}
