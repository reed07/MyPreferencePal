package com.myfitnesspal.feature.addfriends.service;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class FacebookFriendOnMfpService_Factory implements Factory<FacebookFriendOnMfpService> {
    private final Provider<FacebookFriendOnMfpConstructorArgs> argsProvider;

    public FacebookFriendOnMfpService_Factory(Provider<FacebookFriendOnMfpConstructorArgs> provider) {
        this.argsProvider = provider;
    }

    public FacebookFriendOnMfpService get() {
        return provideInstance(this.argsProvider);
    }

    public static FacebookFriendOnMfpService provideInstance(Provider<FacebookFriendOnMfpConstructorArgs> provider) {
        return new FacebookFriendOnMfpService((FacebookFriendOnMfpConstructorArgs) provider.get());
    }

    public static FacebookFriendOnMfpService_Factory create(Provider<FacebookFriendOnMfpConstructorArgs> provider) {
        return new FacebookFriendOnMfpService_Factory(provider);
    }

    public static FacebookFriendOnMfpService newFacebookFriendOnMfpService(FacebookFriendOnMfpConstructorArgs facebookFriendOnMfpConstructorArgs) {
        return new FacebookFriendOnMfpService(facebookFriendOnMfpConstructorArgs);
    }
}
