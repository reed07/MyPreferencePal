package com.myfitnesspal.shared.injection.component;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.barcode.service.BarcodeService;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter;
import com.myfitnesspal.feature.diary.ui.adapter.DiaryLandscapeAdapter;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.service.NutritionDetailsDelegate;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.profile.analytics.MeAnalytics;
import com.myfitnesspal.feature.progress.ui.viewmodel.ProgressViewModel;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.ui.FoodSearchActivityFactory;
import com.myfitnesspal.feature.search.ui.adapter.SearchCategoryItemAdapter;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin;
import com.myfitnesspal.feature.tizen.service.MfpGearMessageBridge;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.db.adapter.SessionDBAdapter;
import com.myfitnesspal.shared.injection.module.ApiModule;
import com.myfitnesspal.shared.injection.module.ApplicationModule;
import com.myfitnesspal.shared.injection.module.ExternalSyncModule;
import com.myfitnesspal.shared.injection.module.ServiceModule;
import com.myfitnesspal.shared.injection.module.SessionModule;
import com.myfitnesspal.shared.injection.module.SyncModule;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v1.UserV1NutrientGoals;
import com.myfitnesspal.shared.model.v15.StatusUpdateObject;
import com.myfitnesspal.shared.notification.service.InAppNotificationService;
import com.myfitnesspal.shared.notification.service.MfpNotificationActionService;
import com.myfitnesspal.shared.notification.service.NotificationInvokerService;
import com.myfitnesspal.shared.provider.MPFAppWidgetProvider;
import com.myfitnesspal.shared.receiver.MfpNotificationActionReceiver;
import com.myfitnesspal.shared.receiver.NotificationBroadcastReceiver;
import com.myfitnesspal.shared.service.BackgroundJobHelperImpl;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.message.MFPFirebaseMessagingService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv1.BinaryResponse;
import com.myfitnesspal.shared.service.syncv1.SynchronizationRequest;
import com.myfitnesspal.shared.service.syncv1.SynchronizationResponse;
import com.myfitnesspal.shared.service.syncv1.packets.request.ApiRequestPacketImpl;
import com.myfitnesspal.shared.service.syncv1.packets.request.EmailUniquenessCheckRequestPacket;
import com.myfitnesspal.shared.service.syncv2.ops.MfpSyncV2OpDelegate;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.uacf.UacfSharedLibrary.UacfThumbprintAnalyticsCallback;
import com.myfitnesspal.shared.ui.component.MfpUiComponentDelegate;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UpdateWeightProxy;
import dagger.Component;
import dagger.Lazy;
import dagger.android.AndroidInjectionModule;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {AndroidInjectionModule.class, ApplicationModule.class, SyncModule.class, ExternalSyncModule.class, SessionModule.class, ServiceModule.class, ApiModule.class})
public interface ApplicationComponent extends UIInterface {
    AnalyticsService analyticsService();

    AuthTokenProvider authTokenProvider();

    Lazy<BarcodeService> barcodeService();

    ConfigService configService();

    Lazy<DiaryService> diaryService();

    ExerciseService exerciseService();

    Lazy<FoodEditorAnalytics> foodEditorAnalytics();

    Lazy<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelper();

    FoodSearchActivityFactory foodSearchRouter();

    @Named("http_client")
    OkHttpClient httpClient();

    void inject(MyFitnessPalApp myFitnessPalApp);

    void inject(DiaryAdapter diaryAdapter);

    void inject(DiaryLandscapeAdapter diaryLandscapeAdapter);

    void inject(ExploreAnalytics exploreAnalytics);

    void inject(NutritionDetailsDelegate nutritionDetailsDelegate);

    void inject(MeAnalytics meAnalytics);

    void inject(ProgressViewModel progressViewModel);

    void inject(SearchCategoryItemAdapter searchCategoryItemAdapter);

    void inject(TimestampPickerMixin timestampPickerMixin);

    void inject(MfpGearMessageBridge mfpGearMessageBridge);

    void inject(SessionDBAdapter sessionDBAdapter);

    void inject(DiaryDay diaryDay);

    void inject(UserV1 userV1);

    void inject(UserV1NutrientGoals userV1NutrientGoals);

    void inject(StatusUpdateObject statusUpdateObject);

    void inject(InAppNotificationService inAppNotificationService);

    void inject(MfpNotificationActionService mfpNotificationActionService);

    void inject(NotificationInvokerService notificationInvokerService);

    void inject(MPFAppWidgetProvider mPFAppWidgetProvider);

    void inject(MfpNotificationActionReceiver mfpNotificationActionReceiver);

    void inject(NotificationBroadcastReceiver notificationBroadcastReceiver);

    void inject(BackgroundJobHelperImpl backgroundJobHelperImpl);

    void inject(MFPFirebaseMessagingService mFPFirebaseMessagingService);

    void inject(BinaryResponse binaryResponse);

    void inject(SynchronizationRequest synchronizationRequest);

    void inject(SynchronizationResponse synchronizationResponse);

    void inject(ApiRequestPacketImpl apiRequestPacketImpl);

    void inject(EmailUniquenessCheckRequestPacket emailUniquenessCheckRequestPacket);

    void inject(MfpSyncV2OpDelegate mfpSyncV2OpDelegate);

    void inject(UacfThumbprintAnalyticsCallback uacfThumbprintAnalyticsCallback);

    void inject(MfpUiComponentDelegate mfpUiComponentDelegate);

    void inject(UpdateWeightProxy updateWeightProxy);

    Lazy<LocalSettingsService> localSettingsService();

    Lazy<LocalizedStringsUtil> localizedStringsUtil();

    MfpApiSettings mfpApiSettings();

    PremiumService premiumService();

    Lazy<SearchService> searchService();

    Lazy<Session> session();

    Lazy<UserEnergyService> userEnergyService();
}
