package com.myfitnesspal.feature.community.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.myfitnesspal.shared.ui.fragment.impl.ExternalWebViewFragment_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class CommunityFragment_MembersInjector implements MembersInjector<CommunityFragment> {
    private final Provider<AnalyticsService> analyticsServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<CommunityService> communityServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<Glide> glideProvider;

    public CommunityFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<ConfigService> provider4, Provider<CommunityService> provider5) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.analyticsServiceProvider = provider3;
        this.configServiceProvider = provider4;
        this.communityServiceProvider = provider5;
    }

    public static MembersInjector<CommunityFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<AnalyticsService> provider3, Provider<ConfigService> provider4, Provider<CommunityService> provider5) {
        CommunityFragment_MembersInjector communityFragment_MembersInjector = new CommunityFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
        return communityFragment_MembersInjector;
    }

    public void injectMembers(CommunityFragment communityFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(communityFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(communityFragment, (Glide) this.glideProvider.get());
        ExternalWebViewFragment_MembersInjector.injectAnalyticsService(communityFragment, (AnalyticsService) this.analyticsServiceProvider.get());
        ExternalWebViewFragment_MembersInjector.injectConfigService(communityFragment, (ConfigService) this.configServiceProvider.get());
        injectCommunityService(communityFragment, (CommunityService) this.communityServiceProvider.get());
    }

    public static void injectCommunityService(CommunityFragment communityFragment, CommunityService communityService) {
        communityFragment.communityService = communityService;
    }
}
