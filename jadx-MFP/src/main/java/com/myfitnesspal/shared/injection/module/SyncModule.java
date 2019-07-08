package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.support.annotation.NonNull;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.externalsync.service.ops.ExternalSyncOp;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.util.PasswordResetHelper;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.v1.MfpSyncApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncDelegate;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncDelegate.SchedulerDelegate;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncOp;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.imagesync.ImageSyncMode;
import com.myfitnesspal.shared.service.imagesync.ImageSyncServiceDelegate;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.session.UserV2Service;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager;
import com.myfitnesspal.shared.service.syncv1.LegacySyncManager.SyncMode;
import com.myfitnesspal.shared.service.syncv1.SyncPointerService;
import com.myfitnesspal.shared.service.syncv2.MfpSyncEngineDelegate;
import com.myfitnesspal.shared.service.syncv2.MfpSyncSchedulerDelegate;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncServiceImpl;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.service.syncv2.SyncUtilImpl;
import com.myfitnesspal.shared.service.syncv2.ops.AuthTokenOp;
import com.myfitnesspal.shared.service.syncv2.ops.ConfigOp;
import com.myfitnesspal.shared.service.syncv2.ops.FindAndCorrectFoodsWithMissingInfoOp;
import com.myfitnesspal.shared.service.syncv2.ops.GeoLocationOp;
import com.myfitnesspal.shared.service.syncv2.ops.MfpSyncV2OpDelegate;
import com.myfitnesspal.shared.service.syncv2.ops.MigrateTokenOp;
import com.myfitnesspal.shared.service.syncv2.ops.ProductServiceOp;
import com.myfitnesspal.shared.service.syncv2.ops.ReceiptsOp;
import com.myfitnesspal.shared.service.syncv2.ops.SyncV1Op;
import com.myfitnesspal.shared.service.syncv2.ops.UacfUserOp;
import com.myfitnesspal.shared.service.syncv2.ops.UserV2Op;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import com.myfitnesspal.shared.uacf.UacfRolloutUtil;
import com.myfitnesspal.shared.util.FoodMigrationAndCorrectionHelper;
import com.squareup.otto.Bus;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.util.Ln;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.sync.engine.UacfScheduleException;
import com.uacf.sync.engine.UacfScheduler;
import com.uacf.sync.engine.UacfSchedulerDelegate;
import com.uacf.sync.engine.UacfSchedulerEngine;
import com.uacf.sync.engine.UacfSchedulerEngineBase;
import com.uacf.sync.engine.UacfSchedulerImplBase;
import com.uacf.sync.provider.internal.model.SyncOpBase;
import com.uacf.sync.provider.sdk.model.SyncItemHandler;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import io.uacf.configuration.sdk.ConfigurationOp;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;
import io.uacf.rollouts.sdk.RolloutsOp;
import io.uacf.thumbprint.ui.sdk.ClientEmailVerificationStatus;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

@Module
public class SyncModule {
    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public MfpSyncEngineDelegate providesSyncServiceDelegate(Lazy<Bus> lazy, Lazy<Session> lazy2, Lazy<AppIndexerBot> lazy3, Provider<SyncV1Op> provider, Provider<SyncOpBase> provider2, Provider<UserV2Op> provider3, Provider<AuthTokenOp> provider4, Provider<ConfigOp> provider5, Provider<ConfigurationOp> provider6, Provider<RolloutsOp> provider7, Provider<UacfUserOp> provider8, Provider<GeoLocationOp> provider9, Provider<ReceiptsOp> provider10, Provider<ExternalSyncOp> provider11, Provider<MigrateTokenOp> provider12, Provider<ProductServiceOp> provider13, Provider<FindAndCorrectFoodsWithMissingInfoOp> provider14, Lazy<SyncService> lazy4, Lazy<UacfSchedulerEngine<ImageSyncMode>> lazy5, Lazy<UacfScheduler<AnalyticsSyncMode>> lazy6, Lazy<UacfConfigurationUtil> lazy7, Lazy<UacfRolloutUtil> lazy8) {
        MfpSyncEngineDelegate mfpSyncEngineDelegate = new MfpSyncEngineDelegate(lazy, lazy2, lazy3, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider14, provider12, provider13, lazy4, lazy5, lazy6, lazy7, lazy8);
        return mfpSyncEngineDelegate;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public SyncService providesSyncEngine(Context context, MfpSyncEngineDelegate mfpSyncEngineDelegate, Lazy<Bus> lazy) {
        return new SyncServiceImpl(context, mfpSyncEngineDelegate, lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UacfSchedulerDelegate<SyncType> providesSyncSchedulerDelegate(Lazy<Session> lazy, Lazy<SyncService> lazy2) {
        return new MfpSyncSchedulerDelegate(lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UacfScheduler<SyncType> providesSyncScheduler(UacfSchedulerDelegate<SyncType> uacfSchedulerDelegate) {
        return new UacfSchedulerImplBase(uacfSchedulerDelegate);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public SyncUtil providesSyncUtil(Context context, Lazy<ConfigService> lazy, @Named("sync-v2-settings") KeyedSharedPreferences keyedSharedPreferences, Lazy<SQLiteDatabaseWrapper> lazy2, @Named("stock_database") Lazy<SQLiteDatabaseWrapper> lazy3, Lazy<ExerciseEntryMapper> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<Session> lazy6) {
        SyncUtilImpl syncUtilImpl = new SyncUtilImpl(context, lazy, keyedSharedPreferences, lazy2, lazy3, lazy4, lazy5, lazy6);
        return syncUtilImpl;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public Map<String, SyncItemHandler> providesSyncItemConsumers(UserV2Service userV2Service, NutrientGoalService nutrientGoalService, SubscriptionService subscriptionService, ExerciseService exerciseService, DiaryService diaryService, ImageService imageService, ImageAssociationService imageAssociationService, UserApplicationSettingsService userApplicationSettingsService) {
        List<SyncItemHandler> asList = Arrays.asList(new SyncItemHandler[]{userV2Service, nutrientGoalService, subscriptionService, exerciseService, diaryService, imageService, imageAssociationService, userApplicationSettingsService, new UacfNotificationSdkFactory().newSdkInstance()});
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (SyncItemHandler syncItemHandler : asList) {
            linkedHashMap.put(syncItemHandler.getSyncResourceName(), syncItemHandler);
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public MfpSyncV2OpDelegate providesSyncV2OpDelegate() {
        return new MfpSyncV2OpDelegate();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public SyncOpBase providesSyncV2Op(MfpSyncV2OpDelegate mfpSyncV2OpDelegate) {
        return new SyncOpBase(mfpSyncV2OpDelegate);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public SyncV1Op providesSyncV1Op(Lazy<Session> lazy, Lazy<SyncUtil> lazy2, LegacySyncManager legacySyncManager) {
        return new SyncV1Op((Session) lazy.get(), lazy2, legacySyncManager);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public ClientEmailVerificationStatus provideClientEmailVerificationStatus(Lazy<Session> lazy, LegacySyncManager legacySyncManager) {
        return new ClientEmailVerificationStatus(lazy) {
            private final /* synthetic */ Lazy f$1;

            {
                this.f$1 = r2;
            }

            public final boolean get() {
                return SyncModule.lambda$provideClientEmailVerificationStatus$0(LegacySyncManager.this, this.f$1);
            }
        };
    }

    static /* synthetic */ boolean lambda$provideClientEmailVerificationStatus$0(LegacySyncManager legacySyncManager, Lazy lazy) {
        try {
            legacySyncManager.sync(SyncMode.Normal, ((Session) lazy.get()).getUser().getUserV1());
        } catch (ApiException | UacfScheduleException e) {
            Ln.e(e, "Failed to sync SyncMode.Normal with Sync V1 for refreshing the users email verification status.", new Object[0]);
        }
        return ((Session) lazy.get()).getUser().getUserV1().isEmailValid();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public ProductServiceOp providesProductServiceOp(Lazy<ProductService> lazy) {
        return new ProductServiceOp(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public LegacySyncManager providesLegacySyncManager(Context context, Lazy<ApiUrlProvider> lazy, Lazy<AnalyticsService> lazy2, Lazy<PasswordResetHelper> lazy3, Lazy<SyncPointerService> lazy4, Provider<MfpSyncApi> provider, Lazy<Session> lazy5, Lazy<SyncUtil> lazy6, Lazy<DiaryService> lazy7, Lazy<RemindersService> lazy8, Lazy<UserImageService> lazy9, Lazy<ExerciseService> lazy10, Lazy<SQLiteDatabaseWrapper> lazy11, Lazy<ExerciseStringService> lazy12, Lazy<MeasurementsService> lazy13, Lazy<ExerciseMapper> lazy14, Lazy<LocalSettingsService> lazy15, Lazy<LoginModel> lazy16, Lazy<SignUpModel> lazy17, Lazy<FoodPermissionsService> lazy18, Lazy<DbConnectionManager> lazy19) {
        LegacySyncManager legacySyncManager = new LegacySyncManager(context, lazy, lazy2, lazy3, lazy4, provider, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18, lazy19);
        return legacySyncManager;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public UserV2Op providesUserV2SyncOp(Lazy<UserV2Service> lazy, @Named("sync-v2-settings") KeyedSharedPreferences keyedSharedPreferences) {
        return new UserV2Op(lazy, keyedSharedPreferences);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public AuthTokenOp providesAuthTokenOp(Lazy<AuthTokenProvider> lazy, Lazy<SignUpModel> lazy2, Lazy<LoginModel> lazy3, Lazy<Session> lazy4) {
        return new AuthTokenOp(lazy, lazy2, lazy3, lazy4);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public ConfigOp providesConfigOp(Lazy<ConfigService> lazy) {
        return new ConfigOp(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public ConfigurationOp providesConfigurationOp() {
        return new ConfigurationOp();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public RolloutsOp providesRolloutsOp() {
        return new RolloutsOp();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public UacfUserOp providesUacfUserOp(Lazy<UacfIdentitySdk> lazy) {
        return new UacfUserOp(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public GeoLocationOp providesGeoLocationOp(Lazy<GeoLocationService> lazy) {
        return new GeoLocationOp(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public ReceiptsOp providesReceiptsOp(Lazy<SubscriptionService> lazy) {
        return new ReceiptsOp(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public MigrateTokenOp providesMigrateTokenOp(Lazy<AuthTokenProvider> lazy) {
        return new MigrateTokenOp(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public FindAndCorrectFoodsWithMissingInfoOp providesFindAndCorrectFoodsWithMissingInfoOp(Provider<MfpV2Api> provider, Lazy<FoodMapper> lazy, Lazy<Session> lazy2, Lazy<FoodMigrationAndCorrectionHelper> lazy3, Lazy<AnalyticsService> lazy4, Lazy<DbConnectionManager> lazy5) {
        FindAndCorrectFoodsWithMissingInfoOp findAndCorrectFoodsWithMissingInfoOp = new FindAndCorrectFoodsWithMissingInfoOp(provider, lazy, lazy2, lazy3, lazy4, lazy5);
        return findAndCorrectFoodsWithMissingInfoOp;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public ImageSyncServiceDelegate providesImageSyncServiceDelegate(Lazy<ImageService> lazy, Lazy<ImageAssociationService> lazy2, Lazy<ImageUploadService> lazy3, Lazy<ConfigService> lazy4) {
        return new ImageSyncServiceDelegate(lazy, lazy2, lazy3, lazy4);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UacfSchedulerEngine<ImageSyncMode> providesImageSyncService(Context context, ImageSyncServiceDelegate imageSyncServiceDelegate) {
        return new UacfSchedulerEngineBase(context, imageSyncServiceDelegate);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public AnalyticsSyncOp providesAnalyticsSyncOp(Lazy<MfpAnalyticsTaskQueue> lazy, Provider<MfpV2Api> provider, @Named("deviceUUID") UUID uuid, @Named("client_id") String str, Lazy<AuthTokenProvider> lazy2) {
        AnalyticsSyncOp analyticsSyncOp = new AnalyticsSyncOp(lazy, provider, uuid, str, lazy2);
        return analyticsSyncOp;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UacfSchedulerEngine<AnalyticsSyncMode> providesAnalyticsSyncService(Context context, Provider<AnalyticsSyncOp> provider) {
        return new UacfSchedulerEngineBase(context, new AnalyticsSyncDelegate(provider));
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UacfScheduler<AnalyticsSyncMode> providesAnalyticsSyncScheduler(UacfSchedulerEngine<AnalyticsSyncMode> uacfSchedulerEngine) {
        return new UacfSchedulerImplBase(new SchedulerDelegate(uacfSchedulerEngine));
    }
}
