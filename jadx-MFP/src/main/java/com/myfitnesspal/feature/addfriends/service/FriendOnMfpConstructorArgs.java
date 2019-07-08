package com.myfitnesspal.feature.addfriends.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.model.v15.FriendCheckResponseObject;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.uacf.core.caching.Cache;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class FriendOnMfpConstructorArgs {
    private final Provider<MfpInformationApi> api;
    private final Cache<FriendCheckResponseObject> cache;
    private final Context context;
    private final FriendService friendService;
    private final Handler handler;
    private final NavigationHelper navigationHelper;
    private final SharedPreferences prefs;

    @Inject
    public FriendOnMfpConstructorArgs(Context context2, @Named("friend-invite-settings") SharedPreferences sharedPreferences, FriendService friendService2, Handler handler2, NavigationHelper navigationHelper2, Provider<MfpInformationApi> provider, Cache<FriendCheckResponseObject> cache2) {
        this.context = context2;
        this.prefs = sharedPreferences;
        this.friendService = friendService2;
        this.handler = handler2;
        this.navigationHelper = navigationHelper2;
        this.api = provider;
        this.cache = cache2;
    }

    public Context getContext() {
        return this.context;
    }

    public SharedPreferences getPrefs() {
        return this.prefs;
    }

    public FriendService getFriendService() {
        return this.friendService;
    }

    public Handler getHandler() {
        return this.handler;
    }

    public NavigationHelper getNavigationHelper() {
        return this.navigationHelper;
    }

    public Provider<MfpInformationApi> getApi() {
        return this.api;
    }

    public Cache<FriendCheckResponseObject> getCache() {
        return this.cache;
    }
}
