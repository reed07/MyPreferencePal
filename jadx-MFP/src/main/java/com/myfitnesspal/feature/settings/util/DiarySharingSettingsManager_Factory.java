package com.myfitnesspal.feature.settings.util;

import android.content.Context;
import com.myfitnesspal.shared.service.session.Session;
import com.squareup.otto.Bus;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DiarySharingSettingsManager_Factory implements Factory<DiarySharingSettingsManager> {
    private final Provider<Context> contextProvider;
    private final Provider<Bus> messageBusProvider;
    private final Provider<Session> sessionProvider;

    public DiarySharingSettingsManager_Factory(Provider<Context> provider, Provider<Session> provider2, Provider<Bus> provider3) {
        this.contextProvider = provider;
        this.sessionProvider = provider2;
        this.messageBusProvider = provider3;
    }

    public DiarySharingSettingsManager get() {
        return provideInstance(this.contextProvider, this.sessionProvider, this.messageBusProvider);
    }

    public static DiarySharingSettingsManager provideInstance(Provider<Context> provider, Provider<Session> provider2, Provider<Bus> provider3) {
        return new DiarySharingSettingsManager((Context) provider.get(), DoubleCheck.lazy(provider2), (Bus) provider3.get());
    }

    public static DiarySharingSettingsManager_Factory create(Provider<Context> provider, Provider<Session> provider2, Provider<Bus> provider3) {
        return new DiarySharingSettingsManager_Factory(provider, provider2, provider3);
    }

    public static DiarySharingSettingsManager newDiarySharingSettingsManager(Context context, Lazy<Session> lazy, Bus bus) {
        return new DiarySharingSettingsManager(context, lazy, bus);
    }
}
