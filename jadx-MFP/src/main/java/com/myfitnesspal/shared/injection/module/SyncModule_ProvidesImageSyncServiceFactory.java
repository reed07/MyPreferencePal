package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import com.myfitnesspal.shared.service.imagesync.ImageSyncMode;
import com.myfitnesspal.shared.service.imagesync.ImageSyncServiceDelegate;
import com.uacf.sync.engine.UacfSchedulerEngine;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesImageSyncServiceFactory implements Factory<UacfSchedulerEngine<ImageSyncMode>> {
    private final Provider<Context> contextProvider;
    private final Provider<ImageSyncServiceDelegate> delegateProvider;
    private final SyncModule module;

    public SyncModule_ProvidesImageSyncServiceFactory(SyncModule syncModule, Provider<Context> provider, Provider<ImageSyncServiceDelegate> provider2) {
        this.module = syncModule;
        this.contextProvider = provider;
        this.delegateProvider = provider2;
    }

    public UacfSchedulerEngine<ImageSyncMode> get() {
        return provideInstance(this.module, this.contextProvider, this.delegateProvider);
    }

    public static UacfSchedulerEngine<ImageSyncMode> provideInstance(SyncModule syncModule, Provider<Context> provider, Provider<ImageSyncServiceDelegate> provider2) {
        return proxyProvidesImageSyncService(syncModule, (Context) provider.get(), (ImageSyncServiceDelegate) provider2.get());
    }

    public static SyncModule_ProvidesImageSyncServiceFactory create(SyncModule syncModule, Provider<Context> provider, Provider<ImageSyncServiceDelegate> provider2) {
        return new SyncModule_ProvidesImageSyncServiceFactory(syncModule, provider, provider2);
    }

    public static UacfSchedulerEngine<ImageSyncMode> proxyProvidesImageSyncService(SyncModule syncModule, Context context, ImageSyncServiceDelegate imageSyncServiceDelegate) {
        return (UacfSchedulerEngine) Preconditions.checkNotNull(syncModule.providesImageSyncService(context, imageSyncServiceDelegate), "Cannot return null from a non-@Nullable @Provides method");
    }
}
