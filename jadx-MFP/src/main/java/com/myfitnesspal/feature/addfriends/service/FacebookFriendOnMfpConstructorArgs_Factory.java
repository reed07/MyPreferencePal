package com.myfitnesspal.feature.addfriends.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.model.v15.FriendCheckResponseObject;
import com.myfitnesspal.shared.service.facebook.FacebookService;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.caching.Cache;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FacebookFriendOnMfpConstructorArgs_Factory implements Factory<FacebookFriendOnMfpConstructorArgs> {
    private final Provider<MfpInformationApi> apiProvider;
    private final Provider<Cache<FriendCheckResponseObject>> cacheProvider;
    private final Provider<Context> contextProvider;
    private final Provider<FacebookService> facebookServiceProvider;
    private final Provider<FriendService> friendServiceProvider;
    private final Provider<Handler> handlerProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<SharedPreferences> prefsProvider;

    public FacebookFriendOnMfpConstructorArgs_Factory(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<FriendService> provider3, Provider<Handler> provider4, Provider<NavigationHelper> provider5, Provider<FacebookService> provider6, Provider<MfpInformationApi> provider7, Provider<Cache<FriendCheckResponseObject>> provider8) {
        this.contextProvider = provider;
        this.prefsProvider = provider2;
        this.friendServiceProvider = provider3;
        this.handlerProvider = provider4;
        this.navigationHelperProvider = provider5;
        this.facebookServiceProvider = provider6;
        this.apiProvider = provider7;
        this.cacheProvider = provider8;
    }

    public FacebookFriendOnMfpConstructorArgs get() {
        return provideInstance(this.contextProvider, this.prefsProvider, this.friendServiceProvider, this.handlerProvider, this.navigationHelperProvider, this.facebookServiceProvider, this.apiProvider, this.cacheProvider);
    }

    public static FacebookFriendOnMfpConstructorArgs provideInstance(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<FriendService> provider3, Provider<Handler> provider4, Provider<NavigationHelper> provider5, Provider<FacebookService> provider6, Provider<MfpInformationApi> provider7, Provider<Cache<FriendCheckResponseObject>> provider8) {
        FacebookFriendOnMfpConstructorArgs facebookFriendOnMfpConstructorArgs = new FacebookFriendOnMfpConstructorArgs((Context) provider.get(), (SharedPreferences) provider2.get(), (FriendService) provider3.get(), (Handler) provider4.get(), (NavigationHelper) provider5.get(), DoubleCheck.lazy(provider6), provider7, (Cache) provider8.get());
        return facebookFriendOnMfpConstructorArgs;
    }

    public static FacebookFriendOnMfpConstructorArgs_Factory create(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<FriendService> provider3, Provider<Handler> provider4, Provider<NavigationHelper> provider5, Provider<FacebookService> provider6, Provider<MfpInformationApi> provider7, Provider<Cache<FriendCheckResponseObject>> provider8) {
        FacebookFriendOnMfpConstructorArgs_Factory facebookFriendOnMfpConstructorArgs_Factory = new FacebookFriendOnMfpConstructorArgs_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return facebookFriendOnMfpConstructorArgs_Factory;
    }

    public static FacebookFriendOnMfpConstructorArgs newFacebookFriendOnMfpConstructorArgs(Context context, SharedPreferences sharedPreferences, FriendService friendService, Handler handler, NavigationHelper navigationHelper, Lazy<FacebookService> lazy, Provider<MfpInformationApi> provider, Cache<FriendCheckResponseObject> cache) {
        FacebookFriendOnMfpConstructorArgs facebookFriendOnMfpConstructorArgs = new FacebookFriendOnMfpConstructorArgs(context, sharedPreferences, friendService, handler, navigationHelper, lazy, provider, cache);
        return facebookFriendOnMfpConstructorArgs;
    }
}
