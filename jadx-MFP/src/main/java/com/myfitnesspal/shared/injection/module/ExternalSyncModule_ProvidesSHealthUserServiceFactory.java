package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ExternalSyncModule_ProvidesSHealthUserServiceFactory implements Factory<SHealthUserService> {
    private final Provider<AppGalleryService> appGalleryServiceProvider;
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<SHealthConnection> connectionProvider;
    private final Provider<MeasurementsService> measurementsServiceProvider;
    private final ExternalSyncModule module;
    private final Provider<KeyedSharedPreferences> prefsProvider;
    private final Provider<Session> sessionProvider;

    public ExternalSyncModule_ProvidesSHealthUserServiceFactory(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<Session> provider2, Provider<KeyedSharedPreferences> provider3, Provider<ConfigService> provider4, Provider<MeasurementsService> provider5, Provider<AppGalleryService> provider6) {
        this.module = externalSyncModule;
        this.connectionProvider = provider;
        this.sessionProvider = provider2;
        this.prefsProvider = provider3;
        this.configServiceProvider = provider4;
        this.measurementsServiceProvider = provider5;
        this.appGalleryServiceProvider = provider6;
    }

    public SHealthUserService get() {
        return provideInstance(this.module, this.connectionProvider, this.sessionProvider, this.prefsProvider, this.configServiceProvider, this.measurementsServiceProvider, this.appGalleryServiceProvider);
    }

    public static SHealthUserService provideInstance(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<Session> provider2, Provider<KeyedSharedPreferences> provider3, Provider<ConfigService> provider4, Provider<MeasurementsService> provider5, Provider<AppGalleryService> provider6) {
        return proxyProvidesSHealthUserService(externalSyncModule, (SHealthConnection) provider.get(), (Session) provider2.get(), (KeyedSharedPreferences) provider3.get(), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), DoubleCheck.lazy(provider6));
    }

    public static ExternalSyncModule_ProvidesSHealthUserServiceFactory create(ExternalSyncModule externalSyncModule, Provider<SHealthConnection> provider, Provider<Session> provider2, Provider<KeyedSharedPreferences> provider3, Provider<ConfigService> provider4, Provider<MeasurementsService> provider5, Provider<AppGalleryService> provider6) {
        ExternalSyncModule_ProvidesSHealthUserServiceFactory externalSyncModule_ProvidesSHealthUserServiceFactory = new ExternalSyncModule_ProvidesSHealthUserServiceFactory(externalSyncModule, provider, provider2, provider3, provider4, provider5, provider6);
        return externalSyncModule_ProvidesSHealthUserServiceFactory;
    }

    public static SHealthUserService proxyProvidesSHealthUserService(ExternalSyncModule externalSyncModule, SHealthConnection sHealthConnection, Session session, KeyedSharedPreferences keyedSharedPreferences, Lazy<ConfigService> lazy, Lazy<MeasurementsService> lazy2, Lazy<AppGalleryService> lazy3) {
        return (SHealthUserService) Preconditions.checkNotNull(externalSyncModule.providesSHealthUserService(sHealthConnection, session, keyedSharedPreferences, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }
}
