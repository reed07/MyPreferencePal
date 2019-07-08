package com.myfitnesspal.feature.registration.ui.activity;

import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class OAuthActivity_MembersInjector implements MembersInjector<OAuthActivity> {
    private final Provider<ApiUrlProvider> apiUrlProvider;
    private final Provider<LoginModel> loginModelProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<Session> sessionProvider;

    public OAuthActivity_MembersInjector(Provider<ApiUrlProvider> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<LoginModel> provider4) {
        this.apiUrlProvider = provider;
        this.navigationHelperProvider = provider2;
        this.sessionProvider = provider3;
        this.loginModelProvider = provider4;
    }

    public static MembersInjector<OAuthActivity> create(Provider<ApiUrlProvider> provider, Provider<NavigationHelper> provider2, Provider<Session> provider3, Provider<LoginModel> provider4) {
        return new OAuthActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(OAuthActivity oAuthActivity) {
        injectApiUrlProvider(oAuthActivity, DoubleCheck.lazy(this.apiUrlProvider));
        injectNavigationHelper(oAuthActivity, DoubleCheck.lazy(this.navigationHelperProvider));
        injectSession(oAuthActivity, DoubleCheck.lazy(this.sessionProvider));
        injectLoginModel(oAuthActivity, DoubleCheck.lazy(this.loginModelProvider));
    }

    public static void injectApiUrlProvider(OAuthActivity oAuthActivity, Lazy<ApiUrlProvider> lazy) {
        oAuthActivity.apiUrlProvider = lazy;
    }

    public static void injectNavigationHelper(OAuthActivity oAuthActivity, Lazy<NavigationHelper> lazy) {
        oAuthActivity.navigationHelper = lazy;
    }

    public static void injectSession(OAuthActivity oAuthActivity, Lazy<Session> lazy) {
        oAuthActivity.session = lazy;
    }

    public static void injectLoginModel(OAuthActivity oAuthActivity, Lazy<LoginModel> lazy) {
        oAuthActivity.loginModel = lazy;
    }
}
