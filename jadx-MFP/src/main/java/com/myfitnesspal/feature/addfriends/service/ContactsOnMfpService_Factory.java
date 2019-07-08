package com.myfitnesspal.feature.addfriends.service;

import dagger.internal.Factory;
import javax.inject.Provider;

public final class ContactsOnMfpService_Factory implements Factory<ContactsOnMfpService> {
    private final Provider<FriendOnMfpConstructorArgs> argsProvider;

    public ContactsOnMfpService_Factory(Provider<FriendOnMfpConstructorArgs> provider) {
        this.argsProvider = provider;
    }

    public ContactsOnMfpService get() {
        return provideInstance(this.argsProvider);
    }

    public static ContactsOnMfpService provideInstance(Provider<FriendOnMfpConstructorArgs> provider) {
        return new ContactsOnMfpService((FriendOnMfpConstructorArgs) provider.get());
    }

    public static ContactsOnMfpService_Factory create(Provider<FriendOnMfpConstructorArgs> provider) {
        return new ContactsOnMfpService_Factory(provider);
    }

    public static ContactsOnMfpService newContactsOnMfpService(FriendOnMfpConstructorArgs friendOnMfpConstructorArgs) {
        return new ContactsOnMfpService(friendOnMfpConstructorArgs);
    }
}
