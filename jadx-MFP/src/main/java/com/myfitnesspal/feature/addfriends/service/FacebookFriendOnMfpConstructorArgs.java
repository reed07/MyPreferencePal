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
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class FacebookFriendOnMfpConstructorArgs extends FriendOnMfpConstructorArgs {
    private final Lazy<FacebookService> facebookService;

    @Inject
    public FacebookFriendOnMfpConstructorArgs(Context context, @Named("friend-invite-settings") SharedPreferences sharedPreferences, FriendService friendService, Handler handler, NavigationHelper navigationHelper, Lazy<FacebookService> lazy, Provider<MfpInformationApi> provider, Cache<FriendCheckResponseObject> cache) {
        super(context, sharedPreferences, friendService, handler, navigationHelper, provider, cache);
        this.facebookService = lazy;
    }

    public Lazy<FacebookService> getFacebookService() {
        return this.facebookService;
    }
}
