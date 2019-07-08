package com.myfitnesspal.feature.drawer.ui.view;

import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LeftDrawerLayout_MembersInjector implements MembersInjector<LeftDrawerLayout> {
    private final Provider<Bus> busProvider;

    public LeftDrawerLayout_MembersInjector(Provider<Bus> provider) {
        this.busProvider = provider;
    }

    public static MembersInjector<LeftDrawerLayout> create(Provider<Bus> provider) {
        return new LeftDrawerLayout_MembersInjector(provider);
    }

    public void injectMembers(LeftDrawerLayout leftDrawerLayout) {
        injectBus(leftDrawerLayout, (Bus) this.busProvider.get());
    }

    public static void injectBus(LeftDrawerLayout leftDrawerLayout, Bus bus) {
        leftDrawerLayout.bus = bus;
    }
}
