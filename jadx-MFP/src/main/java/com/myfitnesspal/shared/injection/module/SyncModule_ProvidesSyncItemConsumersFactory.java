package com.myfitnesspal.shared.injection.module;

import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.service.session.UserV2Service;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
import javax.inject.Provider;

public final class SyncModule_ProvidesSyncItemConsumersFactory implements Factory<Map<String, SyncItemHandler>> {
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<ExerciseService> exerciseServiceProvider;
    private final Provider<ImageAssociationService> imageAssociationServiceProvider;
    private final Provider<ImageService> imageServiceProvider;
    private final SyncModule module;
    private final Provider<NutrientGoalService> nutrientGoalServiceProvider;
    private final Provider<SubscriptionService> subscriptionServiceProvider;
    private final Provider<UserApplicationSettingsService> userApplicationSettingsServiceProvider;
    private final Provider<UserV2Service> userV2ServiceProvider;

    public SyncModule_ProvidesSyncItemConsumersFactory(SyncModule syncModule, Provider<UserV2Service> provider, Provider<NutrientGoalService> provider2, Provider<SubscriptionService> provider3, Provider<ExerciseService> provider4, Provider<DiaryService> provider5, Provider<ImageService> provider6, Provider<ImageAssociationService> provider7, Provider<UserApplicationSettingsService> provider8) {
        this.module = syncModule;
        this.userV2ServiceProvider = provider;
        this.nutrientGoalServiceProvider = provider2;
        this.subscriptionServiceProvider = provider3;
        this.exerciseServiceProvider = provider4;
        this.diaryServiceProvider = provider5;
        this.imageServiceProvider = provider6;
        this.imageAssociationServiceProvider = provider7;
        this.userApplicationSettingsServiceProvider = provider8;
    }

    public Map<String, SyncItemHandler> get() {
        return provideInstance(this.module, this.userV2ServiceProvider, this.nutrientGoalServiceProvider, this.subscriptionServiceProvider, this.exerciseServiceProvider, this.diaryServiceProvider, this.imageServiceProvider, this.imageAssociationServiceProvider, this.userApplicationSettingsServiceProvider);
    }

    public static Map<String, SyncItemHandler> provideInstance(SyncModule syncModule, Provider<UserV2Service> provider, Provider<NutrientGoalService> provider2, Provider<SubscriptionService> provider3, Provider<ExerciseService> provider4, Provider<DiaryService> provider5, Provider<ImageService> provider6, Provider<ImageAssociationService> provider7, Provider<UserApplicationSettingsService> provider8) {
        return proxyProvidesSyncItemConsumers(syncModule, (UserV2Service) provider.get(), (NutrientGoalService) provider2.get(), (SubscriptionService) provider3.get(), (ExerciseService) provider4.get(), (DiaryService) provider5.get(), (ImageService) provider6.get(), (ImageAssociationService) provider7.get(), (UserApplicationSettingsService) provider8.get());
    }

    public static SyncModule_ProvidesSyncItemConsumersFactory create(SyncModule syncModule, Provider<UserV2Service> provider, Provider<NutrientGoalService> provider2, Provider<SubscriptionService> provider3, Provider<ExerciseService> provider4, Provider<DiaryService> provider5, Provider<ImageService> provider6, Provider<ImageAssociationService> provider7, Provider<UserApplicationSettingsService> provider8) {
        SyncModule_ProvidesSyncItemConsumersFactory syncModule_ProvidesSyncItemConsumersFactory = new SyncModule_ProvidesSyncItemConsumersFactory(syncModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
        return syncModule_ProvidesSyncItemConsumersFactory;
    }

    public static Map<String, SyncItemHandler> proxyProvidesSyncItemConsumers(SyncModule syncModule, UserV2Service userV2Service, NutrientGoalService nutrientGoalService, SubscriptionService subscriptionService, ExerciseService exerciseService, DiaryService diaryService, ImageService imageService, ImageAssociationService imageAssociationService, UserApplicationSettingsService userApplicationSettingsService) {
        return (Map) Preconditions.checkNotNull(syncModule.providesSyncItemConsumers(userV2Service, nutrientGoalService, subscriptionService, exerciseService, diaryService, imageService, imageAssociationService, userApplicationSettingsService), "Cannot return null from a non-@Nullable @Provides method");
    }
}
