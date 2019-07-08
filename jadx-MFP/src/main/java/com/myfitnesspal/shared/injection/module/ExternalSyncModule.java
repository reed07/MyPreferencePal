package com.myfitnesspal.shared.injection.module;

import android.content.Context;
import android.content.SharedPreferences;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.externalsync.impl.aggregate.service.AggregateExternalExerciseService;
import com.myfitnesspal.feature.externalsync.impl.aggregate.service.AggregateExternalNutritionService;
import com.myfitnesspal.feature.externalsync.impl.aggregate.service.AggregateExternalStepsService;
import com.myfitnesspal.feature.externalsync.impl.aggregate.service.AggregateExternalUserService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitExerciseService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitNutritionService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitStepsService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitSubscriptionService;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitSubscriptionServiceImpl;
import com.myfitnesspal.feature.externalsync.impl.googlefit.service.GoogleFitUserService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthExerciseService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthNutritionService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthStepsService;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthUserService;
import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.externalsync.service.ExternalStepsService;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.myfitnesspal.feature.externalsync.service.ops.ExternalSyncOp;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.injection.module.ApplicationModule.UserIdSharedPreferenceKeyProvider;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import com.uacf.core.preferences.KeyedSharedPreferences;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.annotation.Nonnull;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ExternalSyncModule {
    public static final String GLOBAL_GOOGLE_FIT_PREFERENCES = "fit_client_store";
    private static final String KEYED_GOOGLE_FIT_PREFERENCES = "google_fit_store";
    private static final String SHEALTH_PREFERENCES = "shealth_client_store";

    /* access modifiers changed from: 0000 */
    @Named("google_fit_store")
    @Nonnull
    @Provides
    public KeyedSharedPreferences provideGoogleFitPreferences(Context context, Lazy<Session> lazy) {
        return new KeyedSharedPreferences(context.getSharedPreferences(KEYED_GOOGLE_FIT_PREFERENCES, 0), new UserIdSharedPreferenceKeyProvider(lazy));
    }

    /* access modifiers changed from: 0000 */
    @Nonnull
    @Provides
    @Named("fit_client_store")
    public SharedPreferences providesFitClientPreferences(Context context) {
        return context.getSharedPreferences(GLOBAL_GOOGLE_FIT_PREFERENCES, 0);
    }

    /* access modifiers changed from: 0000 */
    @Named("shealth_client_store")
    @Nonnull
    @Provides
    public KeyedSharedPreferences providesSHealthPreferences(Context context, Lazy<Session> lazy) {
        return new KeyedSharedPreferences(context.getSharedPreferences(SHEALTH_PREFERENCES, 0), new UserIdSharedPreferenceKeyProvider(lazy));
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public GoogleFitUserService providesGoogleFitUserService(Context context, @Named("fit_client_store") Lazy<SharedPreferences> lazy, Lazy<GoogleFitClient> lazy2, Lazy<Session> lazy3, Lazy<ConfigService> lazy4, Lazy<UserWeightService> lazy5, Lazy<AppGalleryService> lazy6, Lazy<AnalyticsService> lazy7, @Named("google_fit_store") Lazy<KeyedSharedPreferences> lazy8) {
        GoogleFitUserService googleFitUserService = new GoogleFitUserService(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8);
        return googleFitUserService;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public GoogleFitNutritionService providesGoogleFitNutrientService(Context context, Lazy<GoogleFitClient> lazy, Lazy<Session> lazy2, Lazy<ConfigService> lazy3, Lazy<AnalyticsService> lazy4, Lazy<SyncService> lazy5) {
        GoogleFitNutritionService googleFitNutritionService = new GoogleFitNutritionService(context, lazy, lazy2, lazy3, lazy4, lazy5);
        return googleFitNutritionService;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public GoogleFitExerciseService providesGoogleFitActivityService(@Named("google_fit_store") Lazy<KeyedSharedPreferences> lazy, Lazy<GoogleFitClient> lazy2, Lazy<ConfigService> lazy3, Lazy<AnalyticsService> lazy4, Lazy<DiaryService> lazy5, Lazy<AppGalleryService> lazy6) {
        GoogleFitExerciseService googleFitExerciseService = new GoogleFitExerciseService(lazy, lazy2, lazy3, lazy4, lazy5, lazy6);
        return googleFitExerciseService;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public GoogleFitStepsService providesGoogleFitStepsService(@Named("fit_client_store") Lazy<SharedPreferences> lazy, @Named("google_fit_store") Lazy<KeyedSharedPreferences> lazy2, Lazy<Session> lazy3, Lazy<GoogleFitClient> lazy4, Lazy<ConfigService> lazy5, Lazy<DiaryService> lazy6, Lazy<StepService> lazy7) {
        GoogleFitStepsService googleFitStepsService = new GoogleFitStepsService(lazy3, lazy, lazy2, lazy4, lazy5, lazy6, lazy7);
        return googleFitStepsService;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public GoogleFitSubscriptionService providesGoogleFitSubscriptionService() {
        return new GoogleFitSubscriptionServiceImpl();
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public GoogleFitClient providesGoogleFitClient(Lazy<Session> lazy, @Named("fit_client_store") Lazy<SharedPreferences> lazy2, @Named("google_fit_store") Lazy<KeyedSharedPreferences> lazy3, Lazy<GoogleFitSubscriptionService> lazy4, Lazy<StepService> lazy5, Lazy<Bus> lazy6) {
        GoogleFitClient googleFitClient = new GoogleFitClient(lazy, lazy2, lazy3, lazy4, lazy5, lazy6);
        return googleFitClient;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public SHealthUserService providesSHealthUserService(SHealthConnection sHealthConnection, Session session, @Named("shealth_client_store") KeyedSharedPreferences keyedSharedPreferences, Lazy<ConfigService> lazy, Lazy<MeasurementsService> lazy2, Lazy<AppGalleryService> lazy3) {
        SHealthUserService sHealthUserService = new SHealthUserService(sHealthConnection, session, keyedSharedPreferences, lazy, lazy2, lazy3);
        return sHealthUserService;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public SHealthNutritionService providesSHealthNutritionService(SHealthConnection sHealthConnection, Lazy<ConfigService> lazy, Lazy<LocalizedStringsUtil> lazy2, Lazy<UserEnergyService> lazy3, Lazy<AppGalleryService> lazy4, DbConnectionManager dbConnectionManager) {
        SHealthNutritionService sHealthNutritionService = new SHealthNutritionService(sHealthConnection, lazy, lazy2, lazy3, lazy4, dbConnectionManager);
        return sHealthNutritionService;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public SHealthExerciseService providesSHealthExerciseService(SHealthConnection sHealthConnection, Lazy<ConfigService> lazy, Lazy<DiaryService> lazy2, Lazy<AppGalleryService> lazy3, Lazy<ExerciseStringService> lazy4) {
        SHealthExerciseService sHealthExerciseService = new SHealthExerciseService(sHealthConnection, lazy, lazy2, lazy3, lazy4);
        return sHealthExerciseService;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public SHealthStepsService providesSHealthStepsService(SHealthConnection sHealthConnection, Session session, Lazy<SyncService> lazy, Lazy<DiaryService> lazy2, Lazy<StepService> lazy3, Lazy<ConfigService> lazy4, Lazy<AppGalleryService> lazy5) {
        SHealthStepsService sHealthStepsService = new SHealthStepsService(sHealthConnection, session, lazy, lazy2, lazy3, lazy4, lazy5);
        return sHealthStepsService;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public SHealthConnection providesSHealthConnection(Lazy<Session> lazy, Lazy<AnalyticsService> lazy2, @Named("shealth_client_store") Lazy<KeyedSharedPreferences> lazy3, Lazy<StepService> lazy4, Lazy<ConfigService> lazy5, Bus bus) {
        SHealthConnection sHealthConnection = new SHealthConnection(lazy, lazy2, lazy3, lazy4, lazy5, bus);
        return sHealthConnection;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public ExternalUserService providesAggregateExternalUserService(Lazy<GoogleFitUserService> lazy, Lazy<SHealthUserService> lazy2) {
        return new AggregateExternalUserService(lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public ExternalNutritionService providesAggregateExternalNutritionService(Lazy<GoogleFitNutritionService> lazy, Lazy<SHealthNutritionService> lazy2) {
        return new AggregateExternalNutritionService(lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public ExternalExerciseService providesAggregateExternalExerciseService(Lazy<GoogleFitExerciseService> lazy, Lazy<SHealthExerciseService> lazy2) {
        return new AggregateExternalExerciseService(lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Nonnull
    @Provides
    public ExternalStepsService providesAggregateExternalStepsService(Lazy<GoogleFitStepsService> lazy, Lazy<SHealthStepsService> lazy2) {
        return new AggregateExternalStepsService(lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @Nonnull
    @Provides
    public ExternalSyncOp providesExternalSyncOp(Lazy<ExternalExerciseService> lazy, Lazy<ExternalNutritionService> lazy2, Lazy<ExternalStepsService> lazy3, Lazy<ExternalUserService> lazy4) {
        return new ExternalSyncOp(lazy, lazy2, lazy3, lazy4);
    }
}
