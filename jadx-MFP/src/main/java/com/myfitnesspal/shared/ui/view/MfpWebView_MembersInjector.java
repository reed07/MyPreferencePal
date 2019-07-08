package com.myfitnesspal.shared.ui.view;

import com.myfitnesspal.shared.service.device.UserAgentProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class MfpWebView_MembersInjector implements MembersInjector<MfpWebView> {
    private final Provider<UserAgentProvider> userAgentProvider;

    public MfpWebView_MembersInjector(Provider<UserAgentProvider> provider) {
        this.userAgentProvider = provider;
    }

    public static MembersInjector<MfpWebView> create(Provider<UserAgentProvider> provider) {
        return new MfpWebView_MembersInjector(provider);
    }

    public void injectMembers(MfpWebView mfpWebView) {
        injectUserAgentProvider(mfpWebView, (UserAgentProvider) this.userAgentProvider.get());
    }

    public static void injectUserAgentProvider(MfpWebView mfpWebView, UserAgentProvider userAgentProvider2) {
        mfpWebView.userAgentProvider = userAgentProvider2;
    }
}
