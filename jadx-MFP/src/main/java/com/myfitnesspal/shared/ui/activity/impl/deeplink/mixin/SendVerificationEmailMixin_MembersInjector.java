package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class SendVerificationEmailMixin_MembersInjector implements MembersInjector<SendVerificationEmailMixin> {
    private final Provider<AnalyticsService> analyticsProvider;
    private final Provider<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelperProvider;
    private final Provider<UserPropertiesService> userPropertiesServiceProvider;

    public SendVerificationEmailMixin_MembersInjector(Provider<AnalyticsService> provider, Provider<UserPropertiesService> provider2, Provider<NewsFeedAnalyticsHelper> provider3) {
        this.analyticsProvider = provider;
        this.userPropertiesServiceProvider = provider2;
        this.newsFeedAnalyticsHelperProvider = provider3;
    }

    public static MembersInjector<SendVerificationEmailMixin> create(Provider<AnalyticsService> provider, Provider<UserPropertiesService> provider2, Provider<NewsFeedAnalyticsHelper> provider3) {
        return new SendVerificationEmailMixin_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(SendVerificationEmailMixin sendVerificationEmailMixin) {
        DeepLinkMixinBase_MembersInjector.injectAnalytics(sendVerificationEmailMixin, DoubleCheck.lazy(this.analyticsProvider));
        injectUserPropertiesService(sendVerificationEmailMixin, DoubleCheck.lazy(this.userPropertiesServiceProvider));
        injectNewsFeedAnalyticsHelper(sendVerificationEmailMixin, DoubleCheck.lazy(this.newsFeedAnalyticsHelperProvider));
    }

    public static void injectUserPropertiesService(SendVerificationEmailMixin sendVerificationEmailMixin, Lazy<UserPropertiesService> lazy) {
        sendVerificationEmailMixin.userPropertiesService = lazy;
    }

    public static void injectNewsFeedAnalyticsHelper(SendVerificationEmailMixin sendVerificationEmailMixin, Lazy<NewsFeedAnalyticsHelper> lazy) {
        sendVerificationEmailMixin.newsFeedAnalyticsHelper = lazy;
    }
}
