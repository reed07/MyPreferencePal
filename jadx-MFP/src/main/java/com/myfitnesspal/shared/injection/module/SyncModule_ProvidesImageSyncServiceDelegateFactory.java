package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.imagesync.ImageSyncServiceDelegate;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SyncModule_ProvidesImageSyncServiceDelegateFactory implements Factory<ImageSyncServiceDelegate> {
    private final Provider<ConfigService> configServiceProvider;
    private final Provider<ImageAssociationService> imageAssociationServiceProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final Provider<ImageUploadService> imageUploadServiceProvider;
    private final SyncModule module;

    public SyncModule_ProvidesImageSyncServiceDelegateFactory(SyncModule syncModule, Provider<ImageService> provider, Provider<ImageAssociationService> provider2, Provider<ImageUploadService> provider3, Provider<ConfigService> provider4) {
        this.module = syncModule;
        this.imageServiceProvider = provider;
        this.imageAssociationServiceProvider = provider2;
        this.imageUploadServiceProvider = provider3;
        this.configServiceProvider = provider4;
    }

    public ImageSyncServiceDelegate get() {
        return provideInstance(this.module, this.imageServiceProvider, this.imageAssociationServiceProvider, this.imageUploadServiceProvider, this.configServiceProvider);
    }

    public static ImageSyncServiceDelegate provideInstance(SyncModule syncModule, Provider<ImageService> provider, Provider<ImageAssociationService> provider2, Provider<ImageUploadService> provider3, Provider<ConfigService> provider4) {
        return proxyProvidesImageSyncServiceDelegate(syncModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static SyncModule_ProvidesImageSyncServiceDelegateFactory create(SyncModule syncModule, Provider<ImageService> provider, Provider<ImageAssociationService> provider2, Provider<ImageUploadService> provider3, Provider<ConfigService> provider4) {
        SyncModule_ProvidesImageSyncServiceDelegateFactory syncModule_ProvidesImageSyncServiceDelegateFactory = new SyncModule_ProvidesImageSyncServiceDelegateFactory(syncModule, provider, provider2, provider3, provider4);
        return syncModule_ProvidesImageSyncServiceDelegateFactory;
    }

    public static ImageSyncServiceDelegate proxyProvidesImageSyncServiceDelegate(SyncModule syncModule, Lazy<ImageService> lazy, Lazy<ImageAssociationService> lazy2, Lazy<ImageUploadService> lazy3, Lazy<ConfigService> lazy4) {
        return (ImageSyncServiceDelegate) Preconditions.checkNotNull(syncModule.providesImageSyncServiceDelegate(lazy, lazy2, lazy3, lazy4), "Cannot return null from a non-@Nullable @Provides method");
    }
}
