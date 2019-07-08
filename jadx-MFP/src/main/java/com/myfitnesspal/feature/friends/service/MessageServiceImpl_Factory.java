package com.myfitnesspal.feature.friends.service;

import com.myfitnesspal.shared.api.v1.MfpActionApi;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MessageServiceImpl_Factory implements Factory<MessageServiceImpl> {
    private final Provider<MfpActionApi> apiProvider;

    public MessageServiceImpl_Factory(Provider<MfpActionApi> provider) {
        this.apiProvider = provider;
    }

    public MessageServiceImpl get() {
        return provideInstance(this.apiProvider);
    }

    public static MessageServiceImpl provideInstance(Provider<MfpActionApi> provider) {
        return new MessageServiceImpl(provider);
    }

    public static MessageServiceImpl_Factory create(Provider<MfpActionApi> provider) {
        return new MessageServiceImpl_Factory(provider);
    }

    public static MessageServiceImpl newMessageServiceImpl(Provider<MfpActionApi> provider) {
        return new MessageServiceImpl(provider);
    }
}
