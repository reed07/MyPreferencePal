package com.myfitnesspal.feature.addfriends.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.model.v15.FriendCheckResponseObject;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.caching.Cache;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FriendOnMfpConstructorArgs_Factory implements Factory<FriendOnMfpConstructorArgs> {
    private final Provider<MfpInformationApi> apiProvider;
    private final Provider<Cache<FriendCheckResponseObject>> cacheProvider;
    private final Provider<Context> contextProvider;
    private final Provider<FriendService> friendServiceProvider;
    private final Provider<Handler> handlerProvider;
    private final Provider<NavigationHelper> navigationHelperProvider;
    private final Provider<SharedPreferences> prefsProvider;

    public FriendOnMfpConstructorArgs_Factory(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<FriendService> provider3, Provider<Handler> provider4, Provider<NavigationHelper> provider5, Provider<MfpInformationApi> provider6, Provider<Cache<FriendCheckResponseObject>> provider7) {
        this.contextProvider = provider;
        this.prefsProvider = provider2;
        this.friendServiceProvider = provider3;
        this.handlerProvider = provider4;
        this.navigationHelperProvider = provider5;
        this.apiProvider = provider6;
        this.cacheProvider = provider7;
    }

    public FriendOnMfpConstructorArgs get() {
        return provideInstance(this.contextProvider, this.prefsProvider, this.friendServiceProvider, this.handlerProvider, this.navigationHelperProvider, this.apiProvider, this.cacheProvider);
    }

    public static FriendOnMfpConstructorArgs provideInstance(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<FriendService> provider3, Provider<Handler> provider4, Provider<NavigationHelper> provider5, Provider<MfpInformationApi> provider6, Provider<Cache<FriendCheckResponseObject>> provider7) {
        FriendOnMfpConstructorArgs friendOnMfpConstructorArgs = new FriendOnMfpConstructorArgs((Context) provider.get(), (SharedPreferences) provider2.get(), (FriendService) provider3.get(), (Handler) provider4.get(), (NavigationHelper) provider5.get(), provider6, (Cache) provider7.get());
        return friendOnMfpConstructorArgs;
    }

    public static FriendOnMfpConstructorArgs_Factory create(Provider<Context> provider, Provider<SharedPreferences> provider2, Provider<FriendService> provider3, Provider<Handler> provider4, Provider<NavigationHelper> provider5, Provider<MfpInformationApi> provider6, Provider<Cache<FriendCheckResponseObject>> provider7) {
        FriendOnMfpConstructorArgs_Factory friendOnMfpConstructorArgs_Factory = new FriendOnMfpConstructorArgs_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return friendOnMfpConstructorArgs_Factory;
    }

    public static FriendOnMfpConstructorArgs newFriendOnMfpConstructorArgs(Context context, SharedPreferences sharedPreferences, FriendService friendService, Handler handler, NavigationHelper navigationHelper, Provider<MfpInformationApi> provider, Cache<FriendCheckResponseObject> cache) {
        FriendOnMfpConstructorArgs friendOnMfpConstructorArgs = new FriendOnMfpConstructorArgs(context, sharedPreferences, friendService, handler, navigationHelper, provider, cache);
        return friendOnMfpConstructorArgs;
    }
}
