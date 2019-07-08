package com.myfitnesspal.feature.settings.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.framework.deeplink.DeepLinkRouter;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class StepsSettingsListFragment_MembersInjector implements MembersInjector<StepsSettingsListFragment> {
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<AppSettings> appSettingsProvider;
    private final Provider<AuthTokenProvider> authTokensProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DeviceInfo> deviceInfoProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<GoogleFitClient> googleFitProvider;
    private final Provider<DeepLinkRouter> routerProvider;
    private final Provider<SHealthConnection> samsungHealthProvider;
    private final Provider<StepService> stepsServiceProvider;

    public StepsSettingsListFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<StepService> provider3, Provider<AppGalleryService> provider4, Provider<AuthTokenProvider> provider5, Provider<DeepLinkRouter> provider6, Provider<DeviceInfo> provider7, Provider<ApiUrlProvider> provider8, Provider<AppSettings> provider9, Provider<GoogleFitClient> provider10, Provider<SHealthConnection> provider11) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.stepsServiceProvider = provider3;
        this.appGalleryServiceProvider = provider4;
        this.authTokensProvider = provider5;
        this.routerProvider = provider6;
        this.deviceInfoProvider = provider7;
        this.apiUrlProvider = provider8;
        this.appSettingsProvider = provider9;
        this.googleFitProvider = provider10;
        this.samsungHealthProvider = provider11;
    }

    public static MembersInjector<StepsSettingsListFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<StepService> provider3, Provider<AppGalleryService> provider4, Provider<AuthTokenProvider> provider5, Provider<DeepLinkRouter> provider6, Provider<DeviceInfo> provider7, Provider<ApiUrlProvider> provider8, Provider<AppSettings> provider9, Provider<GoogleFitClient> provider10, Provider<SHealthConnection> provider11) {
        StepsSettingsListFragment_MembersInjector stepsSettingsListFragment_MembersInjector = new StepsSettingsListFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11);
        return stepsSettingsListFragment_MembersInjector;
    }

    public void injectMembers(StepsSettingsListFragment stepsSettingsListFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(stepsSettingsListFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(stepsSettingsListFragment, (Glide) this.glideProvider.get());
        injectStepsService(stepsSettingsListFragment, DoubleCheck.lazy(this.stepsServiceProvider));
        injectAppGalleryService(stepsSettingsListFragment, DoubleCheck.lazy(this.appGalleryServiceProvider));
        injectAuthTokens(stepsSettingsListFragment, DoubleCheck.lazy(this.authTokensProvider));
        injectRouter(stepsSettingsListFragment, DoubleCheck.lazy(this.routerProvider));
        injectDeviceInfo(stepsSettingsListFragment, DoubleCheck.lazy(this.deviceInfoProvider));
        injectApiUrlProvider(stepsSettingsListFragment, DoubleCheck.lazy(this.apiUrlProvider));
        injectAppSettings(stepsSettingsListFragment, DoubleCheck.lazy(this.appSettingsProvider));
        injectGoogleFit(stepsSettingsListFragment, (GoogleFitClient) this.googleFitProvider.get());
        injectSamsungHealth(stepsSettingsListFragment, (SHealthConnection) this.samsungHealthProvider.get());
    }

    public static void injectStepsService(StepsSettingsListFragment stepsSettingsListFragment, Lazy<StepService> lazy) {
        stepsSettingsListFragment.stepsService = lazy;
    }

    public static void injectAppGalleryService(StepsSettingsListFragment stepsSettingsListFragment, Lazy<AppGalleryService> lazy) {
        stepsSettingsListFragment.appGalleryService = lazy;
    }

    public static void injectAuthTokens(StepsSettingsListFragment stepsSettingsListFragment, Lazy<AuthTokenProvider> lazy) {
        stepsSettingsListFragment.authTokens = lazy;
    }

    public static void injectRouter(StepsSettingsListFragment stepsSettingsListFragment, Lazy<DeepLinkRouter> lazy) {
        stepsSettingsListFragment.router = lazy;
    }

    public static void injectDeviceInfo(StepsSettingsListFragment stepsSettingsListFragment, Lazy<DeviceInfo> lazy) {
        stepsSettingsListFragment.deviceInfo = lazy;
    }

    public static void injectApiUrlProvider(StepsSettingsListFragment stepsSettingsListFragment, Lazy<ApiUrlProvider> lazy) {
        stepsSettingsListFragment.apiUrlProvider = lazy;
    }

    public static void injectAppSettings(StepsSettingsListFragment stepsSettingsListFragment, Lazy<AppSettings> lazy) {
        stepsSettingsListFragment.appSettings = lazy;
    }

    public static void injectGoogleFit(StepsSettingsListFragment stepsSettingsListFragment, GoogleFitClient googleFitClient) {
        stepsSettingsListFragment.googleFit = googleFitClient;
    }

    public static void injectSamsungHealth(StepsSettingsListFragment stepsSettingsListFragment, SHealthConnection sHealthConnection) {
        stepsSettingsListFragment.samsungHealth = sHealthConnection;
    }
}
