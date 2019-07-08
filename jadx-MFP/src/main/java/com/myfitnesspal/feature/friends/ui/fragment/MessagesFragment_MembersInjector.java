package com.myfitnesspal.feature.friends.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class MessagesFragment_MembersInjector implements MembersInjector<MessagesFragment> {
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<MessageService> messageServiceProvider;

    public MessagesFragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<MessageService> provider3) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.messageServiceProvider = provider3;
    }

    public static MembersInjector<MessagesFragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<MessageService> provider3) {
        return new MessagesFragment_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(MessagesFragment messagesFragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(messagesFragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(messagesFragment, (Glide) this.glideProvider.get());
        injectMessageService(messagesFragment, DoubleCheck.lazy(this.messageServiceProvider));
    }

    public static void injectMessageService(MessagesFragment messagesFragment, Lazy<MessageService> lazy) {
        messagesFragment.messageService = lazy;
    }
}
