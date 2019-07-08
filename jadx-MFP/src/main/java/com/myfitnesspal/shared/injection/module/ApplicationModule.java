package com.myfitnesspal.shared.injection.module;

import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputMethodManager;
import com.brightcove.player.event.AbstractEvent;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.build.BuildConfiguration;
import com.myfitnesspal.build.StartupManagerImpl;
import com.myfitnesspal.feature.achievementinterstitialad.repository.UserSummaryRepository;
import com.myfitnesspal.feature.achievementinterstitialad.repository.UserSummaryRepositoryImpl;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdAnalyticsEvents;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdManager;
import com.myfitnesspal.feature.achievementinterstitialad.service.StoredAchievementEvents;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.addentry.service.WaterLoggingAnalyticsHelper;
import com.myfitnesspal.feature.alexainterstitial.analytics.AlexaInterstitialAnalyticsHelper;
import com.myfitnesspal.feature.alexainterstitial.analytics.AlexaInterstitialAnalyticsHelperImpl;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.service.AppGalleryServiceImpl;
import com.myfitnesspal.feature.barcode.service.BarcodeService;
import com.myfitnesspal.feature.barcode.service.BarcodeService.ScanResult;
import com.myfitnesspal.feature.barcode.service.BarcodeServiceImpl;
import com.myfitnesspal.feature.blog.service.BlogService;
import com.myfitnesspal.feature.blog.service.BlogServiceImpl;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.service.ChallengesServiceImpl;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.feature.community.service.CommunityServiceImpl;
import com.myfitnesspal.feature.consents.service.ConsentsService;
import com.myfitnesspal.feature.consents.service.ConsentsServiceImpl;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelper;
import com.myfitnesspal.feature.consents.util.AdConsentsAnalyticsHelperImpl;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelperImpl;
import com.myfitnesspal.feature.dashboard.service.NutrientDashboardAnalyticsHelper;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.dashboard.ui.view.RadialGraphNutrientDashboard;
import com.myfitnesspal.feature.dashboard.ui.view.TextNutrientDashboard;
import com.myfitnesspal.feature.debug.ui.activity.PremiumDebugActivity;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtilImpl;
import com.myfitnesspal.feature.debug.util.DebugSettingsService;
import com.myfitnesspal.feature.debug.util.DebugSettingsServiceImpl;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountAnalyticsHelper;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountService;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountServiceImpl;
import com.myfitnesspal.feature.diary.service.DiaryAnalyticsHelper;
import com.myfitnesspal.feature.diary.service.DiaryDayCache;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.service.DiaryServiceImpl;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseSearchAnalyticsHelper;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.exercise.service.ExerciseServiceImpl;
import com.myfitnesspal.feature.explore.service.ExploreService;
import com.myfitnesspal.feature.explore.service.ExploreServiceImpl;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.GoogleFit;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.service.ExternalExerciseService;
import com.myfitnesspal.feature.externalsync.service.ExternalUserService;
import com.myfitnesspal.feature.fileexport.service.FileExportAnalyticsHelper;
import com.myfitnesspal.feature.fileexport.service.FileExportService;
import com.myfitnesspal.feature.fileexport.service.FileExportServiceImpl;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalyticsImpl;
import com.myfitnesspal.feature.foodfeedback.repository.FoodFeedbackAction;
import com.myfitnesspal.feature.foodfeedback.repository.FoodFeedbackRepositoryImpl;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelperImpl;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.feature.friends.service.MessageServiceImpl;
import com.myfitnesspal.feature.friends.service.StatusAnalytics;
import com.myfitnesspal.feature.friends.service.StatusAnalyticsImpl;
import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelper;
import com.myfitnesspal.feature.gdprhelp.util.GDPRHelpAnalyticsHelperImpl;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalServiceImpl;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtilImpl;
import com.myfitnesspal.feature.home.service.AppRatingService;
import com.myfitnesspal.feature.home.service.ImageReportingService;
import com.myfitnesspal.feature.home.service.ImageReportingServiceImpl;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.util.HomeAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.images.service.ImageAssociationServiceImpl;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageServiceImpl;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.feature.images.service.ImageUploadServiceImpl;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.meals.service.MealServiceImpl;
import com.myfitnesspal.feature.meals.util.MealSharingDirectionsAnalyticsHelper;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.meals.util.MealUtilImpl;
import com.myfitnesspal.feature.notificationinbox.util.NotificationInboxAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.service.ChartLegendFactory;
import com.myfitnesspal.feature.nutrition.service.ChartRendererFactory;
import com.myfitnesspal.feature.nutrition.service.NutritionAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.service.NutritionAnalyticsHelperImpl;
import com.myfitnesspal.feature.nutrition.service.NutritionDetailsService;
import com.myfitnesspal.feature.nutrition.service.NutritionDetailsServiceImpl;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphAnalyticsHelperImpl;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphPreferenceService;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphPreferenceServiceImpl;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphService;
import com.myfitnesspal.feature.nutrition.service.NutritionGraphServiceImpl;
import com.myfitnesspal.feature.nutrition.service.renderer.CoreChartRendererBaseConstructorArgs;
import com.myfitnesspal.feature.onboarding.navigation.Navigator;
import com.myfitnesspal.feature.onboarding.navigation.NavigatorImpl;
import com.myfitnesspal.feature.onboarding.navigation.PremiumOnboardingFlowCoordinator;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelperImpl;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingViewModelFactory;
import com.myfitnesspal.feature.payments.db.SubscriptionServiceDbAdapter;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelper;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelperImpl;
import com.myfitnesspal.feature.payments.service.PaymentService;
import com.myfitnesspal.feature.payments.service.SubscriptionService;
import com.myfitnesspal.feature.payments.service.SubscriptionServiceImpl;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.service.ProductServiceImpl;
import com.myfitnesspal.feature.premium.service.UpsellService;
import com.myfitnesspal.feature.premium.service.UpsellServiceImpl;
import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellCoordinator;
import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellNavigator;
import com.myfitnesspal.feature.premium.ui.navigation.PremiumUpsellNavigatorImpl;
import com.myfitnesspal.feature.premium.ui.viewmodel.PremiumUpsellViewModelFactory;
import com.myfitnesspal.feature.premium.util.PremiumApiErrorUtil;
import com.myfitnesspal.feature.premium.util.PremiumFeatureOverrides;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorServiceImpl;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.service.ProgressAnalyticsImpl;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService;
import com.myfitnesspal.feature.progress.service.ProgressCongratsServiceImpl;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.service.ProgressServiceImpl;
import com.myfitnesspal.feature.progress.ui.chart.MeasurementLineChartRenderer;
import com.myfitnesspal.feature.progress.ui.chart.MeasurementLineChartRendererImpl;
import com.myfitnesspal.feature.progress.ui.chart.StepsBarChartRenderer;
import com.myfitnesspal.feature.progress.ui.chart.StepsBarChartRendererImpl;
import com.myfitnesspal.feature.recipes.model.MfpRecipeListContainer;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipeServiceImpl;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.recipes.util.RecipesMealsFoodsAnalyticsHelper;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.SignUpModel;
import com.myfitnesspal.feature.registration.service.InstallManager;
import com.myfitnesspal.feature.registration.service.InstallManagerImpl;
import com.myfitnesspal.feature.registration.service.PrefetchService;
import com.myfitnesspal.feature.registration.service.PrefetchServiceImpl;
import com.myfitnesspal.feature.registration.service.SignInService;
import com.myfitnesspal.feature.registration.service.SignInServiceImpl;
import com.myfitnesspal.feature.registration.service.SignUpService;
import com.myfitnesspal.feature.registration.service.SignUpServiceImpl;
import com.myfitnesspal.feature.registration.service.UpdatedTermsAnalyticsHelper;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.feature.restaurantlogging.service.MenuServiceImpl;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingAnalyticsHelper;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsServiceImpl;
import com.myfitnesspal.feature.restaurantlogging.service.VenueService;
import com.myfitnesspal.feature.restaurantlogging.service.VenueServiceImpl;
import com.myfitnesspal.feature.restaurantlogging.util.MultiAddMenuItemHelper;
import com.myfitnesspal.feature.search.repository.LocalFoodSearchRepository;
import com.myfitnesspal.feature.search.repository.OnlineFoodSearchRepository;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelperImpl;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.service.SearchServiceImpl;
import com.myfitnesspal.feature.search.util.SortOrderHelper;
import com.myfitnesspal.feature.settings.api.TroubleShootingApi;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.settings.model.InsightSettings;
import com.myfitnesspal.feature.settings.repository.TroubleshootingRepository;
import com.myfitnesspal.feature.settings.repository.TroubleshootingRepositoryImpl;
import com.myfitnesspal.feature.settings.service.TroubleshootingService;
import com.myfitnesspal.feature.settings.service.TroubleshootingServiceImpl;
import com.myfitnesspal.feature.settings.util.DiarySharingSettingsManager;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsServiceImpl;
import com.myfitnesspal.feature.video.util.VideoAnalyticsHelper;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl;
import com.myfitnesspal.framework.deeplink.DeepLinkRouter;
import com.myfitnesspal.framework.deeplink.Dispatcher;
import com.myfitnesspal.framework.mvvm.TtlViewModelCache;
import com.myfitnesspal.framework.mvvm.ViewModelCache;
import com.myfitnesspal.shared.api.ApiConstructorArgs;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.api.ApiUrlProviderImpl;
import com.myfitnesspal.shared.api.MfpApiSettings;
import com.myfitnesspal.shared.api.MfpApiSettingsImpl;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.auth.SSO;
import com.myfitnesspal.shared.api.v1.ApiBinaryConstructorArgs;
import com.myfitnesspal.shared.api.v1.MfpActionApi;
import com.myfitnesspal.shared.api.v1.MfpActionApiImpl;
import com.myfitnesspal.shared.api.v1.MfpInformationApi;
import com.myfitnesspal.shared.api.v1.MfpInformationApiImpl;
import com.myfitnesspal.shared.api.v1.MfpSearchApi;
import com.myfitnesspal.shared.api.v1.MfpSearchApiImpl;
import com.myfitnesspal.shared.api.v1.MfpSyncApi;
import com.myfitnesspal.shared.api.v1.MfpSyncApiImpl;
import com.myfitnesspal.shared.api.v2.DeviceActivationApi;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ConfigApi;
import com.myfitnesspal.shared.api.v2.MfpV2ConfigApiImpl;
import com.myfitnesspal.shared.constants.Constants.Analytics;
import com.myfitnesspal.shared.constants.Constants.Injection;
import com.myfitnesspal.shared.constants.SharedConstants;
import com.myfitnesspal.shared.constants.SharedConstants.Validators;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.adapter.DeletedItemsDBAdapter;
import com.myfitnesspal.shared.db.table.DeletedItemsTable;
import com.myfitnesspal.shared.db.table.DeletedMostUsedFoodsTable;
import com.myfitnesspal.shared.db.table.ExerciseEntriesTable;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.db.table.FoodPermissionsTable;
import com.myfitnesspal.shared.db.table.FoodsTable;
import com.myfitnesspal.shared.db.table.MeasurementTypesTable;
import com.myfitnesspal.shared.db.table.MeasurementsTable;
import com.myfitnesspal.shared.db.table.ProfileImagesTable;
import com.myfitnesspal.shared.db.table.RecipesTable;
import com.myfitnesspal.shared.db.table.RemindersTable;
import com.myfitnesspal.shared.db.table.StepsTable;
import com.myfitnesspal.shared.db.table.SyncPointersTable;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManagerImpl;
import com.myfitnesspal.shared.deeplink.MfpDeepLinkRouter;
import com.myfitnesspal.shared.deeplink.Routes;
import com.myfitnesspal.shared.factory.DeviceUuidFactory;
import com.myfitnesspal.shared.geolocation.GeoLocationService;
import com.myfitnesspal.shared.geolocation.GeoLocationServiceImpl;
import com.myfitnesspal.shared.model.mapper.ApiBinaryMapperBase;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.mapper.impl.DiaryNoteMapper;
import com.myfitnesspal.shared.model.mapper.impl.DiaryNoteMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryFromServerMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseEntryMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseFromServerMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapper;
import com.myfitnesspal.shared.model.mapper.impl.ExerciseMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.FoodEntryFromServerMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodEntryFromServerMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.FoodPortionMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodPortionMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.WaterEntryMapper;
import com.myfitnesspal.shared.model.mapper.impl.WaterEntryMapperImpl;
import com.myfitnesspal.shared.model.v1.UserV1GoalPreferences;
import com.myfitnesspal.shared.model.v15.FoodObject;
import com.myfitnesspal.shared.model.v15.FriendCheckResponseObject;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.model.v15.UserSummaryObject.LIST_MAPPER;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryListContainer;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.notification.InAppNotificationManagerImpl;
import com.myfitnesspal.shared.notification.JobServiceFactory;
import com.myfitnesspal.shared.notification.MfpNotificationHandler;
import com.myfitnesspal.shared.notification.PushNotificationManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.BackgroundJobHelperImpl;
import com.myfitnesspal.shared.service.ads.AdIdConsentCompliant;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdUnitServiceImpl;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelperImpl;
import com.myfitnesspal.shared.service.ads.AndroidAdvertisementIdentifier;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.ActionTrackingServiceImpl;
import com.myfitnesspal.shared.service.analytics.AmplitudeService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.GoogleAnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsTask;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsTaskQueue;
import com.myfitnesspal.shared.service.analytics.MultiAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBotImpl;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.config.Configuration;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.myfitnesspal.shared.service.device.UserAgentProviderImpl;
import com.myfitnesspal.shared.service.device.UserAgentProviderImpl.Params;
import com.myfitnesspal.shared.service.facebook.FacebookService;
import com.myfitnesspal.shared.service.facebook.FacebookServiceImpl;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.foods.FoodPermissionsServiceImpl;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.service.foods.FoodServiceImpl;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.friends.FriendServiceImpl;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsServiceImpl;
import com.myfitnesspal.shared.service.id.IdService;
import com.myfitnesspal.shared.service.id.IdServiceImpl;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.install.CountryServiceImpl;
import com.myfitnesspal.shared.service.install.UtmInstallReceiver;
import com.myfitnesspal.shared.service.lifecycle.AppLifecycleObserver;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsServiceImpl;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.location.LocationServiceImpl;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.measurements.MeasurementsServiceImpl;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedServiceImpl;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.myfitnesspal.shared.service.reminders.RemindersServiceImpl;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.steps.StepServiceImpl;
import com.myfitnesspal.shared.service.strings.GrammarService;
import com.myfitnesspal.shared.service.strings.GrammarServiceImpl;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.myfitnesspal.shared.service.syncv1.SyncPointerService;
import com.myfitnesspal.shared.service.syncv1.SyncPointerServiceImpl;
import com.myfitnesspal.shared.service.syncv1.packets.PacketFactory;
import com.myfitnesspal.shared.service.syncv1.packets.PacketFactoryImpl;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.syncv2.SyncUtil;
import com.myfitnesspal.shared.service.thirdparty.ThirdPartyService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserImageService;
import com.myfitnesspal.shared.service.userdata.UserImageServiceImpl;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.service.userdata.UserPropertiesServiceImpl;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserSummaryServiceImpl;
import com.myfitnesspal.shared.service.userdata.UserSummaryServiceTestImpl;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.uacf.UacfConfigurationUtil;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.uacf.UacfRolloutUtil;
import com.myfitnesspal.shared.ui.activity.busymanager.BusyManager;
import com.myfitnesspal.shared.ui.activity.busymanager.BusyManagerImpl;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.FoodMigrationAndCorrectionHelper;
import com.myfitnesspal.shared.util.GlideUtil;
import com.myfitnesspal.shared.util.GlobalAppPreferenceMigrationUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.LocalizedStringsUtilImpl;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.myfitnesspal.shared.util.ResourceUtils;
import com.myfitnesspal.shared.util.ResourceUtilsImpl;
import com.myfitnesspal.shared.validation.RegexValidator;
import com.myfitnesspal.shared.validation.Validator;
import com.squareup.otto.Bus;
import com.uacf.core.caching.Cache;
import com.uacf.core.caching.ExpiringCache;
import com.uacf.core.caching.MemoryCache;
import com.uacf.core.caching.SharedPreferenceCache;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.logging.PrivateFilePrinter;
import com.uacf.core.mapping.Mapper2;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.preferences.KeyedSharedPreferences;
import com.uacf.core.preferences.KeyedSharedPreferences.KeyProvider;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.VersionUtils;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import io.uacf.configuration.sdk.UacfConfigurationSdk;
import io.uacf.configuration.sdk.UacfConfigurationSdkFactory;
import io.uacf.rollouts.sdk.UacfVariantSdk;
import io.uacf.rollouts.sdk.UacfVariantSdkFactory;
import io.uacf.thumbprint.ui.sdk.ClientEmailVerificationStatus;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {SyncModule.class, ExternalSyncModule.class, SessionModule.class, ViewModelModule.class})
public class ApplicationModule {
    private final Application application;

    public static class UserIdSharedPreferenceKeyProvider implements KeyProvider {
        private static final String DEFAULT_USER = "NOT_SIGNED_IN";
        private Lazy<Session> session;

        public UserIdSharedPreferenceKeyProvider(Lazy<Session> lazy) {
            this.session = lazy;
        }

        public String getKey() {
            return Strings.toString(((Session) this.session.get()).getUser().getUserId(), DEFAULT_USER);
        }
    }

    public ApplicationModule(@NonNull Application application2) {
        this.application = application2;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public NavigationHelper providesNavigationHelper() {
        return new NavigationHelper();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MiniUserInfoMapper providesMiniUserInfoMapper() {
        return new MiniUserInfoMapperImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public WaterEntryMapper providesWaterEntryMapper() {
        return new WaterEntryMapperImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public DiaryNoteMapper providesDiaryNoteMapper() {
        return new DiaryNoteMapperImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FoodPortionMapper providesFoodPortionMapper() {
        return new FoodPortionMapperImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FoodMapper providesFoodMapper(FoodPortionMapper foodPortionMapper) {
        return new FoodMapperImpl(foodPortionMapper);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MfpFoodMapper providesMfpFoodMapper() {
        return new MfpFoodMapperImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FoodEntryFromServerMapper providesFoodEntryFromServerMapper(FoodMapper foodMapper) {
        return new FoodEntryFromServerMapperImpl(foodMapper);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ExerciseFromServerMapper providesExerciseFromServerMapper() {
        return new ExerciseFromServerMapperImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ExerciseEntryFromServerMapper providesExerciseEntryFromServerMapper(ExerciseFromServerMapper exerciseFromServerMapper) {
        return new ExerciseEntryFromServerMapperImpl(exerciseFromServerMapper);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ExerciseMapper providesExerciseMapper() {
        return new ExerciseMapperImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MealIngredientMapper providesMealIngedientMapper() {
        return new MealIngredientMapperImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ExerciseEntryMapper providesExerciseEntryMapper(ExerciseMapper exerciseMapper) {
        return new ExerciseEntryMapperImpl(exerciseMapper);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FoodPermissionsService providesFoodPermissionsService(Lazy<Session> lazy, Lazy<FoodPermissionsTable> lazy2) {
        return new FoodPermissionsServiceImpl(lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public SignUpService providesSignUpService(Lazy<LocalSettingsService> lazy, Lazy<MeasurementsService> lazy2, Lazy<AnalyticsService> lazy3, Lazy<AuthTokenProvider> lazy4, Lazy<ThirdPartyService> lazy5, Lazy<SyncService> lazy6, Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Lazy<Session> lazy7, Lazy<PushNotificationManager> lazy8, Lazy<DbConnectionManager> lazy9) {
        SignUpServiceImpl signUpServiceImpl = new SignUpServiceImpl(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, provider, provider2, lazy7, lazy8, lazy9);
        return signUpServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public SignUpModel providesSignUpModel(@Named("signup-shared-preferences") Lazy<SharedPreferences> lazy, Lazy<CountryService> lazy2, Lazy<Session> lazy3) {
        return new SignUpModel(lazy, lazy2, lazy3);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public SignInService providesSignInService(Lazy<Session> lazy, Lazy<LoginModel> lazy2, Lazy<SyncService> lazy3, Lazy<MfpApiSettings> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<AnalyticsService> lazy6, Lazy<AuthTokenProvider> lazy7, Lazy<ThirdPartyService> lazy8, Lazy<PushNotificationManager> lazy9, Lazy<DbConnectionManager> lazy10, Lazy<JobServiceFactory> lazy11) {
        SignInServiceImpl signInServiceImpl = new SignInServiceImpl(lazy2, lazy, lazy4, lazy3, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11);
        return signInServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public AnalyticsService providesAnalyticsService(Lazy<AppIndexerBot> lazy, AmplitudeService amplitudeService, MfpAnalyticsService mfpAnalyticsService, GoogleAnalyticsService googleAnalyticsService) {
        return new MultiAnalyticsService(lazy, amplitudeService, mfpAnalyticsService, googleAnalyticsService);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public AmplitudeService providesAmplitudeService(Context context, Lazy<AppSettings> lazy, @Named("app_session_id") String str, @Named("carrier_name") String str2, @Named("deviceUUID") UUID uuid, Lazy<Session> lazy2, Lazy<FriendService> lazy3, Lazy<AppGalleryService> lazy4, Lazy<ConfigService> lazy5, Lazy<SubscriptionService> lazy6, @Named("app-settings") SharedPreferences sharedPreferences, Lazy<InsightSettings> lazy7, Lazy<LocalSettingsService> lazy8, Lazy<DiarySharingSettingsManager> lazy9, DbConnectionManager dbConnectionManager, LoginModel loginModel, Lazy<NutrientGoalService> lazy10, Lazy<NutrientGoalsUtil> lazy11, Lazy<RemindersService> lazy12, Lazy<DbConnectionManager> lazy13, Lazy<UserApplicationSettingsService> lazy14) {
        AmplitudeService amplitudeService = new AmplitudeService(context, lazy, str, str2, uuid, lazy2, lazy3, lazy4, lazy5, lazy6, sharedPreferences, lazy7, lazy8, lazy9, dbConnectionManager.foodDbAdapter(), loginModel, lazy10, lazy11, lazy12, lazy13, lazy14);
        return amplitudeService;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public MfpAnalyticsService providesMfpAnalyticsService(Context context, Lazy<AppSettings> lazy, @Named("app_session_id") String str, @Named("carrier_name") String str2, Lazy<MfpAnalyticsTaskQueue> lazy2, @Named("deviceUUID") UUID uuid, @Named("client_id") String str3, Lazy<AuthTokenProvider> lazy3, Lazy<Session> lazy4, Lazy<SubscriptionService> lazy5) {
        MfpAnalyticsService mfpAnalyticsService = new MfpAnalyticsService(context, lazy, str, str2, lazy2, uuid, str3, lazy3, lazy4, lazy5);
        return mfpAnalyticsService;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public GoogleAnalyticsService providesGoogleAnalyticsService(Context context, Lazy<ConfigService> lazy) {
        return new GoogleAnalyticsService(context, lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public UserPropertiesService providesUserPropertiesService(Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2) {
        return new UserPropertiesServiceImpl(provider, provider2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MessageService providesMessageService(Provider<MfpActionApi> provider, BackgroundJobHelper backgroundJobHelper) {
        return new MessageServiceImpl(provider);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FriendService providesFriendService(Provider<MfpActionApi> provider, Lazy<BackgroundJobHelper> lazy, Cache<List<UserSummaryObject>> cache, Lazy<LocalSettingsService> lazy2, Lazy<Session> lazy3) {
        FriendServiceImpl friendServiceImpl = new FriendServiceImpl(provider, lazy, cache, lazy2, lazy3);
        return friendServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public DebugSettingsService providesDebugSettingsService(@Named("debug-store") Lazy<SharedPreferences> lazy) {
        return new DebugSettingsServiceImpl(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public UserSummaryService providesUserSummaryService(Provider<MfpInformationApi> provider, Lazy<DebugSettingsService> lazy) {
        if (BuildConfiguration.getBuildConfiguration().isDebug() || BuildConfiguration.getBuildConfiguration().isQABuild()) {
            return new UserSummaryServiceTestImpl(provider, lazy);
        }
        return new UserSummaryServiceImpl(provider);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public AppGalleryService providesAppGalleryService(Provider<MfpV2Api> provider, Cache<ApiResponse<MfpPlatformApp>> cache, DeviceInfo deviceInfo, Lazy<Session> lazy, Lazy<ConfigService> lazy2) {
        AppGalleryServiceImpl appGalleryServiceImpl = new AppGalleryServiceImpl(provider, cache, deviceInfo, lazy, lazy2);
        return appGalleryServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public InstallManager providesInstallManager(Context context, AnalyticsService analyticsService, AppSettings appSettings) {
        return new InstallManagerImpl(context, analyticsService, appSettings);
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public ConsentsService providesConsentService(Context context, CountryService countryService, Session session, LocalSettingsService localSettingsService, PremiumService premiumService, ConfigService configService) {
        ConsentsServiceImpl consentsServiceImpl = new ConsentsServiceImpl(context, countryService, session, localSettingsService, premiumService, configService);
        return consentsServiceImpl;
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public TroubleshootingService providesTroubleshootingService(Context context, ConnectivityManager connectivityManager) {
        return new TroubleshootingServiceImpl(context, connectivityManager);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public DeepLinkManager providesDeepLinkManager(Context context, AppSettings appSettings, NavigationHelper navigationHelper) {
        return new DeepLinkManagerImpl(context, appSettings, navigationHelper);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public DiaryService providesDiaryService(Provider<MfpActionApi> provider, Provider<MfpV2Api> provider2, Lazy<DiaryDayCache> lazy, Lazy<Session> lazy2, Lazy<ExternalExerciseService> lazy3, Lazy<ActionTrackingService> lazy4, Lazy<AnalyticsService> lazy5, Lazy<SQLiteDatabaseWrapper> lazy6, Lazy<ExerciseEntryMapper> lazy7, Lazy<ExerciseService> lazy8, Lazy<DiaryService> lazy9, Lazy<UserEnergyService> lazy10, Lazy<NutrientGoalService> lazy11) {
        DiaryServiceImpl diaryServiceImpl = new DiaryServiceImpl(provider, provider2, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy11);
        return diaryServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public BarcodeService providesBarcodeService(Provider<MfpInformationApi> provider) {
        return new BarcodeServiceImpl(provider);
    }

    private BarcodeService providesBarcodeServiceWithEmptyResults(Provider<MfpInformationApi> provider) {
        return new BarcodeServiceImpl(provider) {
            public ScanResult searchBarcode(String str) throws ApiException {
                return new ScanResult() {
                    public String barcode() {
                        return "";
                    }

                    public List<FoodObject> foods() {
                        return new ArrayList();
                    }
                };
            }
        };
    }

    private BarcodeService providesBarcodeServiceWithForcedError(Provider<MfpInformationApi> provider) {
        return new BarcodeServiceImpl(provider) {
            public ScanResult searchBarcode(String str) throws ApiException {
                throw new ApiException("test message", 257);
            }
        };
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public NewsFeedService providesNewsFeedService(Provider<MfpInformationApi> provider, Provider<MfpV2Api> provider2, Lazy<Session> lazy, Cache<MfpNewsFeedActivityEntryListContainer> cache) {
        return new NewsFeedServiceImpl(provider, provider2, lazy, cache);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public IdService providesIdService(Provider<MfpV2Api> provider) {
        return new IdServiceImpl(provider);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public SearchService providesSearchService(Provider<MfpSearchApi> provider, Provider<MfpV2Api> provider2, Lazy<ActionTrackingService> lazy, Lazy<SortOrderHelper> lazy2, Lazy<MealUtil> lazy3, Lazy<Session> lazy4, Lazy<LocalSettingsService> lazy5, Lazy<CountryService> lazy6, Lazy<DbConnectionManager> lazy7) {
        SearchServiceImpl searchServiceImpl = new SearchServiceImpl(provider, provider2, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7);
        return searchServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public StepService provideStepsService(Lazy<StepsTable> lazy, Lazy<ExerciseEntriesTable> lazy2, Lazy<AnalyticsService> lazy3, Lazy<AppGalleryService> lazy4, Lazy<Session> lazy5, @Named("deviceUUID") UUID uuid) {
        StepServiceImpl stepServiceImpl = new StepServiceImpl(lazy, lazy2, lazy3, lazy4, lazy5, uuid);
        return stepServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public WalkthroughUtil providesWalkThroughUtil(Lazy<Bus> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<UserEnergyService> lazy4) {
        return new WalkthroughUtilImpl(lazy, lazy2, lazy3, lazy4);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public AdvancedDebuggingUtil providesAdvancedDebuggingUtil(Context context, NavigationHelper navigationHelper) {
        return new AdvancedDebuggingUtilImpl(context, navigationHelper);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MealService providesMealService(Context context, Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<DiaryService> lazy2, Lazy<ImageAssociationService> lazy3, Lazy<SyncService> lazy4, Lazy<FoodPermissionsTable> lazy5, Lazy<DbConnectionManager> lazy6, Lazy<CountryService> lazy7) {
        MealServiceImpl mealServiceImpl = new MealServiceImpl(context, provider, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7);
        return mealServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MealUtil providesMealHelperUtil(Lazy<Session> lazy, Lazy<MfpFoodMapper> lazy2, Lazy<FoodMapper> lazy3, Lazy<MealIngredientMapper> lazy4, Lazy<DbConnectionManager> lazy5) {
        MealUtilImpl mealUtilImpl = new MealUtilImpl(lazy, lazy2, lazy3, lazy4, lazy5);
        return mealUtilImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public CommunityService providesVanillaService(Provider<MfpV2Api> provider, Lazy<ConfigService> lazy, Lazy<CountryService> lazy2) {
        return new CommunityServiceImpl(provider, lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public BlogService providesBlogService(Lazy<CountryService> lazy) {
        return new BlogServiceImpl(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public PrefetchService providesPrefetchService(Lazy<LocalSettingsService> lazy, Lazy<NewsFeedService> lazy2, Lazy<FriendService> lazy3, Lazy<ProductService> lazy4, Lazy<GeoLocationService> lazy5, Lazy<SyncUtil> lazy6, Lazy<SyncService> lazy7, Lazy<SubscriptionService> lazy8, Lazy<AppGalleryService> lazy9, Lazy<UserApplicationSettingsService> lazy10) {
        PrefetchServiceImpl prefetchServiceImpl = new PrefetchServiceImpl(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10);
        return prefetchServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FoodService provideFoodService(DeletedMostUsedFoodsTable deletedMostUsedFoodsTable, Lazy<Session> lazy, FoodsTable foodsTable, DeletedItemsTable deletedItemsTable, Lazy<AuthTokenProvider> lazy2, Provider<MfpV2Api> provider, Lazy<ActionTrackingService> lazy3, Provider<MfpActionApi> provider2, Lazy<FoodMapper> lazy4, Lazy<FoodNotesTable> lazy5, Lazy<DeletedItemsDBAdapter> lazy6, Lazy<DbConnectionManager> lazy7) {
        FoodServiceImpl foodServiceImpl = new FoodServiceImpl(deletedMostUsedFoodsTable, lazy, foodsTable, deletedItemsTable, lazy2, provider, lazy3, provider2, lazy4, lazy5, lazy6, lazy7);
        return foodServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ExerciseService provideExerciseService(SyncUtil syncUtil, Lazy<Session> lazy, Provider<MfpV2Api> provider, Lazy<ExerciseMapper> lazy2, Lazy<SQLiteDatabaseWrapper> lazy3, @Named("stock_database") Lazy<SQLiteDatabaseWrapper> lazy4) {
        ExerciseServiceImpl exerciseServiceImpl = new ExerciseServiceImpl(syncUtil, lazy, provider, lazy2, lazy3, lazy4);
        return exerciseServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public RemindersService provideRemindersService(Context context, RemindersTable remindersTable, Lazy<ConfigService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<DbConnectionManager> lazy4) {
        RemindersServiceImpl remindersServiceImpl = new RemindersServiceImpl(context, remindersTable, lazy, lazy2, lazy3, lazy4);
        return remindersServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public UserImageService provideUserImageService(Context context, Lazy<Session> lazy, ProfileImagesTable profileImagesTable) {
        return new UserImageServiceImpl(context, lazy, profileImagesTable);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public RecipeService provideRecipeService(Provider<MfpV2Api> provider, Lazy<IdService> lazy, DbConnectionManager dbConnectionManager, Lazy<CountryService> lazy2, Cache<MfpRecipeListContainer> cache, Lazy<RecipesTable> lazy3, Lazy<SyncService> lazy4, Lazy<Session> lazy5) {
        RecipeServiceImpl recipeServiceImpl = new RecipeServiceImpl(dbConnectionManager.recipePropertiesDBAdapter(), dbConnectionManager.foodDbAdapter(), provider, lazy, lazy2, cache, lazy3, lazy4, lazy5);
        return recipeServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public ActionTrackingService providesActionTrackingService(Lazy<AnalyticsService> lazy) {
        return new ActionTrackingServiceImpl(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ImageService providesImageService(Context context, Lazy<Session> lazy, Lazy<ApiUrlProvider> lazy2, Provider<MfpV2Api> provider) {
        ImageServiceImpl imageServiceImpl = new ImageServiceImpl(context, lazy, DbConnectionManager.getDb(context), lazy2, provider);
        return imageServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ImageUploadService providesImageUploadService(Context context, Lazy<Session> lazy, Lazy<ImageService> lazy2, Lazy<AnalyticsService> lazy3) {
        ImageUploadServiceImpl imageUploadServiceImpl = new ImageUploadServiceImpl(context, lazy, lazy2, DbConnectionManager.getDb(context), lazy3);
        return imageUploadServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ImageAssociationService providesImageAssociationService(Context context, Lazy<Session> lazy, Lazy<ImageService> lazy2, Provider<MfpV2Api> provider) {
        return new ImageAssociationServiceImpl(lazy, lazy2, DbConnectionManager.getDb(context), provider);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public SubscriptionService providesSubscriptionService(Context context, Lazy<Session> lazy, Lazy<Bus> lazy2, Lazy<SubscriptionServiceDbAdapter> lazy3, Provider<MfpV2Api> provider, Lazy<AnalyticsService> lazy4) {
        SubscriptionServiceImpl subscriptionServiceImpl = new SubscriptionServiceImpl(context, new KeyedSharedPreferences(context.getSharedPreferences(Injection.Named.SUBSCRIPTION_SERVICE_STORE, 0), new UserIdSharedPreferenceKeyProvider(lazy)), lazy, lazy2, lazy3, provider, lazy4);
        return subscriptionServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public ProductService providesProductService(Context context, Lazy<PremiumService> lazy, Lazy<GeoLocationService> lazy2, Provider<MfpV2Api> provider, Lazy<Session> lazy3, Lazy<ConfigService> lazy4, MfpApiSettings mfpApiSettings, Lazy<LocalSettingsService> lazy5, Lazy<UserApplicationSettingsService> lazy6) {
        Context context2 = context;
        ProductServiceImpl productServiceImpl = new ProductServiceImpl(context.getSharedPreferences(Injection.Named.PRODUCT_SERVICE_STORE, 0), lazy, lazy2, provider, lazy3, lazy4, mfpApiSettings, lazy5, lazy6);
        return productServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public UserV1GoalPreferences providesUserV1GoalPreferences(@Named("user_goals_preferences") SharedPreferences sharedPreferences) {
        return new UserV1GoalPreferences(sharedPreferences);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public NutrientGoalService providesNutrientGoalService(Context context, Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<PremiumService> lazy2, Lazy<NutrientGoalsUtil> lazy3) {
        NutrientGoalServiceImpl nutrientGoalServiceImpl = new NutrientGoalServiceImpl(context, provider, lazy, lazy2, lazy3);
        return nutrientGoalServiceImpl;
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public MfpAnalyticsTaskQueue providesAnalyticsTaskQueue(@Named("mfp-analytics-store") SharedPreferences sharedPreferences) {
        return new MfpAnalyticsTaskQueue(sharedPreferences, new ApiJsonMapper().withType(MfpAnalyticsTask.class));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public NutrientGoalsUtil providesNutritionalGoalsUtilService(Lazy<Session> lazy, Lazy<UserEnergyService> lazy2, Lazy<NutrientGoalService> lazy3) {
        return new NutrientGoalsUtilImpl(lazy, lazy2, lazy3);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public UserApplicationSettingsService providesUserApplicationSettingsService(Provider<MfpV2Api> provider, Lazy<SQLiteDatabaseWrapper> lazy, Lazy<LocalSettingsService> lazy2, Session session) {
        return new UserApplicationSettingsServiceImpl(provider, lazy, lazy2, session);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("emailValidator")
    public Validator provideEmailValidator() {
        return new RegexValidator(Validators.EMAIL);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("servingSizeValidator")
    public Validator provideServingSizeValidator() {
        return new RegexValidator(String.format(Validators.SERVING_SIZE_REGEX_FORMAT, new Object[]{NumberUtils.getDecimalSeparatorForRegex()}));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("weightValidator")
    public Validator provideWeightValidator() {
        return new RegexValidator(String.format(Validators.WEIGHT_REGEX_FORMAT, new Object[]{NumberUtils.getDecimalSeparatorForRegex()}));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public SQLiteDatabaseWrapper provideDatabase(Context context, DbConnectionManager dbConnectionManager) {
        return DbConnectionManager.getDb(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("stock_database")
    @Provides
    public SQLiteDatabaseWrapper provideStockDatabase(Context context, DbConnectionManager dbConnectionManager, Lazy<AppSettings> lazy) {
        return DbConnectionManager.getStockDb(context, lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public DbConnectionManager provideDbConnectionManager(Context context, Lazy<AppSettings> lazy, Lazy<ExerciseService> lazy2) {
        return new DbConnectionManager(context, lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("news-feed-cache-store")
    public SharedPreferences provideSharedPreferences_NewsFeedCacheStore(Context context) {
        return context.getSharedPreferences(Injection.Named.NEWS_FEED_CACHE_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public Cache<Configuration> providesConfigurationCache(@Named("cache-store") SharedPreferences sharedPreferences) {
        SharedPreferenceCache sharedPreferenceCache = new SharedPreferenceCache(sharedPreferences);
        sharedPreferenceCache.withMapper(new ApiJsonMapper().withType(Configuration.class));
        return sharedPreferenceCache;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public Cache<FriendCheckResponseObject> providesFriendCheckCache(@Named("cache-store") SharedPreferences sharedPreferences) {
        return new ExpiringCache(new SharedPreferenceCache(sharedPreferences)).withMapper(new ApiJsonMapper().withType(FriendCheckResponseObject.class));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public Cache<MfpNewsFeedActivityEntryListContainer> providesNewsFeedCache(@Named("news-feed-cache-store") SharedPreferences sharedPreferences) {
        return new ExpiringCache(new SharedPreferenceCache(sharedPreferences)).setItemExpirationTime(600000).withMapper(new ApiJsonMapper().withType(MfpNewsFeedActivityEntryListContainer.class));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public Cache<ApiResponse<MfpPlatformApp>> providesMfpPlatformAppDetailsCache(@Named("cache-store") SharedPreferences sharedPreferences) {
        return new ExpiringCache(new SharedPreferenceCache(sharedPreferences)).withMapper(new ApiJsonMapper().withType(API_RESPONSE_MAPPER.class));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public Cache<List<UserSummaryObject>> providesFriendsCache(@Named("cache-store") SharedPreferences sharedPreferences) {
        return new ExpiringCache(new SharedPreferenceCache(sharedPreferences)).setItemExpirationTime(600000).withMapper(new ApiJsonMapper().withType(LIST_MAPPER.class));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("installReferrerReceivers")
    @Provides
    public List<BroadcastReceiver> providesInstallReferrerReceivers() {
        return Arrays.asList(new BroadcastReceiver[]{new UtmInstallReceiver()});
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public Application provideApplication() {
        Ln.d("provideApplication", new Object[0]);
        return MyFitnessPalApp.getInstance();
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public Context provideAppContext() {
        Ln.d("provideAppContext", new Object[0]);
        return MyFitnessPalApp.getInstance().getApplicationContext();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public StartupManager provideStartupManager(Context context, Lazy<ConfigService> lazy, Lazy<AnalyticsService> lazy2, Lazy<MfpAnalyticsService> lazy3, Lazy<InstallManager> lazy4, Lazy<Session> lazy5, Lazy<LocalSettingsService> lazy6, Lazy<SyncUtil> lazy7, Lazy<UacfScheduler<SyncType>> lazy8, Lazy<StepService> lazy9, Lazy<LocationService> lazy10, Lazy<PushNotificationManager> lazy11, Lazy<GlobalAppPreferenceMigrationUtil> lazy12, Lazy<AppSettings> lazy13, Lazy<AppRatingService> lazy14, Lazy<DbConnectionManager> lazy15, Lazy<CountryService> lazy16, Lazy<ConsentsService> lazy17, Lazy<UacfEmailVerificationManager> lazy18) {
        StartupManagerImpl startupManagerImpl = new StartupManagerImpl(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13, lazy14, lazy15, lazy16, lazy17, lazy18);
        return startupManagerImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("friend-invite-settings")
    public SharedPreferences provideFriendInviteStore(Context context) {
        Ln.d("provideFriendInviteStore", new Object[0]);
        return context.getSharedPreferences(SharedConstants.Injection.Named.FRIEND_INVITE_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("cache-store")
    public SharedPreferences provideSharedPreferencesCacheStore(Context context) {
        return context.getSharedPreferences(SharedConstants.Injection.Named.CACHE_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("deviceUUID")
    @Singleton
    @Provides
    public UUID provideDeviceUUID(Context context) {
        return new DeviceUuidFactory(context).getDeviceUuid();
    }

    /* access modifiers changed from: protected */
    @Singleton
    @Provides
    @Named("carrier_name")
    public String provideCarrierName(Context context) {
        String networkOperatorName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        return Strings.isEmpty(networkOperatorName) ? Analytics.UNKNOWN : networkOperatorName;
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public AudioManager provideAudioManager(@NonNull Context context) {
        return (AudioManager) getSystemService(context, MimeTypes.BASE_TYPE_AUDIO);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("deviceUUIDBytes")
    @Singleton
    @Provides
    public byte[] provideDeviceUUIDBytes(@Named("deviceUUID") UUID uuid) {
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();
        byte[] bArr = new byte[16];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) ((int) (mostSignificantBits >>> ((7 - i) * 8)));
        }
        for (int i2 = 8; i2 < 16; i2++) {
            bArr[i2] = (byte) ((int) (leastSignificantBits >>> ((7 - i2) * 8)));
        }
        return bArr;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("appVersionName")
    public String provideAppVersionString(Context context) {
        return VersionUtils.getAppVersionName(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("appVersionCode")
    public long provideAppVersionCode(Context context) {
        return (long) VersionUtils.getAppVersionCode(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("abtest-settings")
    public SharedPreferences provideABTestSettingsStore(Context context) {
        return context.getSharedPreferences(Injection.Named.ABTEST_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("mfp-analytics-store")
    public SharedPreferences provideMfpAnalyticsStore(Context context) {
        return context.getSharedPreferences(Injection.Named.MFP_ANALYTICS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("ads-settings")
    public SharedPreferences provideAdsSettingsStore(Context context) {
        return context.getSharedPreferences(Injection.Named.ADS_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("app-settings")
    public SharedPreferences provideAppSettingsStore(Context context) {
        return context.getSharedPreferences(Injection.Named.APP_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("quick-tip-settings")
    public SharedPreferences provideInsightsSettingsStore(Context context) {
        return context.getSharedPreferences(Injection.Named.INSIGHTS_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("xpromo-settings")
    public SharedPreferences provideXPromoSettingsStore(Context context) {
        return context.getSharedPreferences(Injection.Named.XPROMO_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ApiUrlProvider provideApiUrlProvider(Lazy<MfpApiSettings> lazy, Lazy<CountryService> lazy2, Lazy<AuthTokenProvider> lazy3, @Named("client_id") String str, @Named("guestAccessToken") String str2) {
        ApiUrlProviderImpl apiUrlProviderImpl = new ApiUrlProviderImpl(lazy, lazy2, lazy3, str, str2);
        return apiUrlProviderImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("exerciseSortingPreferences")
    public SharedPreferences provideExerciseSortingPrefs(Context context) {
        return context.getSharedPreferences(Injection.Named.EXERCISE_SORTING_PREFERENCES, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("foodSortingPreferences")
    public SharedPreferences provideFoodSortingPrefs(Context context) {
        return context.getSharedPreferences(Injection.Named.FOOD_SORTING_PREFERENCES, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("user_goals_preferences")
    public SharedPreferences provideUserGoalsPrefs(Context context) {
        return context.getSharedPreferences(Injection.Named.USER_GOALS_SHARED_PREFERENCES, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("signup-shared-preferences")
    public SharedPreferences provideSignUpSharedPreferences(Context context) {
        return context.getSharedPreferences(Injection.Named.SIGNUP_SHARED_PREFERENCES, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("login-shared-preferences")
    public SharedPreferences provideLoginSharedPreferences(Context context) {
        return context.getSharedPreferences(Injection.Named.LOGIN_SHARED_PREFERENCES, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("debug-store")
    public SharedPreferences provideDebugSharedPreferences(Context context) {
        return context.getSharedPreferences(Injection.Named.DEBUG_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public AdUnitService provideAdUnitService(Context context, AdsSettings adsSettings, Lazy<UacfConfigurationUtil> lazy) {
        return new AdUnitServiceImpl(context, adsSettings, lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public CountryService providesCountryService(Context context, ResourceUtils resourceUtils, Lazy<GlobalSettingsService> lazy) {
        return new CountryServiceImpl(context, resourceUtils, lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public GrammarService providesLocalizedStringService(CountryService countryService) {
        return new GrammarServiceImpl(countryService);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public PushNotificationManager providesPushNotificationManager(Context context, Lazy<ApiUrlProvider> lazy, Lazy<MfpNotificationHandler> lazy2, Lazy<SyncService> lazy3, Lazy<AnalyticsService> lazy4, Lazy<GlobalSettingsService> lazy5, Lazy<Session> lazy6) {
        PushNotificationManager pushNotificationManager = new PushNotificationManager(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6);
        return pushNotificationManager;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public BusyManager providesBusyManager() {
        return new BusyManagerImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ResourceUtils providesResourceUtils() {
        return new ResourceUtilsImpl();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public LocalizedStringsUtil providesLocalizedStringsUtil(Context context, ResourceUtils resourceUtils) {
        return new LocalizedStringsUtilImpl(context, resourceUtils);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public NutrientDashboardRenderer providesNutrientDashboardRenderer(Provider<TextNutrientDashboard> provider, Provider<RadialGraphNutrientDashboard> provider2, Lazy<Session> lazy, Lazy<PremiumService> lazy2) {
        return new NutrientDashboardRenderer(provider, provider2, lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public RadialGraphNutrientDashboard providesRadialGraphNutrientDashboard(Context context, Lazy<UserEnergyService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<StepService> lazy4, Lazy<ActionTrackingService> lazy5, Lazy<NutrientGoalService> lazy6, Lazy<NutrientGoalsUtil> lazy7, Lazy<PremiumService> lazy8, @Named("fit_client_store") Lazy<SharedPreferences> lazy9, Lazy<DiaryService> lazy10, Lazy<AppGalleryService> lazy11, Lazy<GoogleFitClient> lazy12, Lazy<NutrientDashboardAnalyticsHelper> lazy13) {
        RadialGraphNutrientDashboard radialGraphNutrientDashboard = new RadialGraphNutrientDashboard(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13);
        return radialGraphNutrientDashboard;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public TextNutrientDashboard providesTextNutrientDashboard(Context context, Lazy<UserEnergyService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<StepService> lazy4, Lazy<ActionTrackingService> lazy5, Lazy<NutrientGoalService> lazy6, Lazy<NutrientGoalsUtil> lazy7, Lazy<PremiumService> lazy8, @Named("fit_client_store") Lazy<SharedPreferences> lazy9, Lazy<DiaryService> lazy10, Lazy<AppGalleryService> lazy11, Lazy<GoogleFitClient> lazy12, Lazy<NutrientDashboardAnalyticsHelper> lazy13) {
        TextNutrientDashboard textNutrientDashboard = new TextNutrientDashboard(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8, lazy9, lazy10, lazy11, lazy12, lazy13);
        return textNutrientDashboard;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public NutritionDetailsService providesNutritionalDetailsService(Context context, Lazy<LocalSettingsService> lazy, Lazy<PremiumService> lazy2) {
        return new NutritionDetailsServiceImpl(context, lazy, ((PremiumService) lazy2.get()).isPremiumAvailable());
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MeasurementLineChartRenderer providesMeasurementLineChartRenderer(Context context) {
        return new MeasurementLineChartRendererImpl(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public StepsBarChartRenderer providesStepsBarChartRenderer(Context context) {
        return new StepsBarChartRendererImpl(context);
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public InAppNotificationManager providesInAppNotificationManager(AppSettings appSettings) {
        return new InAppNotificationManagerImpl(appSettings);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public BackgroundJobHelper providesBackgroundServiceHelper(Context context) {
        return new BackgroundJobHelperImpl(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MeasurementsService providesMeasurementsService(Lazy<Session> lazy, Lazy<ExternalUserService> lazy2, Lazy<ImageAssociationService> lazy3, SQLiteDatabaseWrapper sQLiteDatabaseWrapper, MeasurementsTable measurementsTable, MeasurementTypesTable measurementTypesTable, DeletedItemsTable deletedItemsTable) {
        MeasurementsServiceImpl measurementsServiceImpl = new MeasurementsServiceImpl(lazy, lazy2, lazy3, sQLiteDatabaseWrapper, measurementsTable, measurementTypesTable, deletedItemsTable);
        return measurementsServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ProgressService providesProgressService(Context context, Lazy<Session> lazy, Lazy<UserSummaryService> lazy2, Lazy<UserWeightService> lazy3, Lazy<MeasurementsService> lazy4, Lazy<ConfigService> lazy5) {
        ProgressServiceImpl progressServiceImpl = new ProgressServiceImpl(context, lazy, lazy2, lazy3, lazy4, lazy5);
        return progressServiceImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ProgressAnalytics providesProgressAnalytics(Lazy<AnalyticsService> lazy) {
        return new ProgressAnalyticsImpl(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public Dispatcher providesDispatcher(Context context) {
        return new Dispatcher(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public DeepLinkRouter providesDeepLinkRouter(Context context, Dispatcher dispatcher, DeepLinkManager deepLinkManager, NavigationHelper navigationHelper, Lazy<AppSettings> lazy, Lazy<Session> lazy2, Lazy<AnalyticsService> lazy3) {
        return MfpDeepLinkRouter.getInstance(context, Routes.class, dispatcher, null, deepLinkManager, navigationHelper, lazy, lazy2, lazy3);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("app_session_id")
    @Singleton
    @Provides
    public String provideAppSessionId() {
        return UUID.randomUUID().toString();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("logFile")
    public File providesPrivateLogFile(Context context) {
        File file = new File(String.format("%s/logs", new Object[]{context.getExternalFilesDir(null)}));
        file.mkdirs();
        return file;
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public PrivateFilePrinter providesPrivateFilePrinter(@Named("logFile") File file, AppSettings appSettings) {
        PrivateFilePrinter privateFilePrinter = new PrivateFilePrinter(file, false);
        boolean isPrivateFileLoggingEnabled = appSettings.isPrivateFileLoggingEnabled();
        privateFilePrinter.setEnabled(isPrivateFileLoggingEnabled);
        Ln.forceDebugLogging(isPrivateFileLoggingEnabled);
        return privateFilePrinter;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public LocalSettingsService provideSettingsService(@Named("settings_preferences") KeyedSharedPreferences keyedSharedPreferences, Lazy<UserApplicationSettingsService> lazy, Lazy<CountryService> lazy2) {
        return new LocalSettingsServiceImpl(keyedSharedPreferences, lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public LocationService provideLocationService(Context context, Lazy<LocalSettingsService> lazy) {
        return new LocationServiceImpl(context, lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("settings_preferences")
    @Provides
    public KeyedSharedPreferences provideSettingsSharedPreferences(Context context, Lazy<Session> lazy) {
        return new KeyedSharedPreferences(context.getSharedPreferences(Injection.Named.LOCAL_SETTINGS_PREFERENCES, 0), new UserIdSharedPreferenceKeyProvider(lazy));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ProgressCongratsService provideProgressCongratsService(Lazy<UserWeightService> lazy, Lazy<UserHeightService> lazy2, @Named("progress_congrats_preferences") KeyedSharedPreferences keyedSharedPreferences) {
        return new ProgressCongratsServiceImpl(lazy, lazy2, keyedSharedPreferences);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("progress_congrats_preferences")
    @Provides
    public KeyedSharedPreferences provideProgressCongratsSharedPreferences(Context context, Lazy<Session> lazy) {
        return new KeyedSharedPreferences(context.getSharedPreferences(Injection.Named.PROGRESS_CONGRATS_PREFERENCES, 0), new UserIdSharedPreferenceKeyProvider(lazy));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("sync-v2-settings")
    @Provides
    public KeyedSharedPreferences provideSyncV2SharedPreferences(Context context, Lazy<Session> lazy) {
        return new KeyedSharedPreferences(context.getSharedPreferences(Injection.Named.SYNC_V2_SETTINGS_STORE, 0), new UserIdSharedPreferenceKeyProvider(lazy));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("restaurant_settings_preferences")
    @Provides
    public KeyedSharedPreferences provideRestaurantSharedPreferences(Context context, Lazy<Session> lazy) {
        return new KeyedSharedPreferences(context.getSharedPreferences(Injection.Named.RESTAURANT_SETTINGS_PREFERENCES, 0), new UserIdSharedPreferenceKeyProvider(lazy));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("premium-overrides")
    public SharedPreferences providePremiumOverridesSharedPreferences(Context context) {
        return context.getSharedPreferences(Injection.Named.PREMIUM_OVERRIDES_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("facebook-app-id")
    public String getFacebookAppId(Context context) {
        return context.getString(R.string.facebook_app_id);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("client_id")
    public String provideClientId(AppIndexerBot appIndexerBot) {
        return appIndexerBot.isActive() ? appIndexerBot.getClientId() : "mfp-mobile-android-google";
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("guestAccessToken")
    @Provides
    public String provideGuestAccessToken(Lazy<MfpApiSettings> lazy, AppIndexerBot appIndexerBot) {
        if (appIndexerBot.isActive()) {
            return appIndexerBot.getAuthToken();
        }
        return ((MfpApiSettings) lazy.get()).getAPIToken();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MfpV2ConfigApi providesMfpConfigJsonApi(Lazy<ApiUrlProvider> lazy, UserAgentProvider userAgentProvider, @Named("deviceUUID") UUID uuid, @Named("appVersionCode") long j, Lazy<RequestInterceptor> lazy2, ApiJsonMapper apiJsonMapper, Lazy<Bus> lazy3, Lazy<AuthTokenProvider> lazy4, Lazy<CountryService> lazy5, Lazy<DeviceInfo> lazy6) {
        ApiConstructorArgs apiConstructorArgs = new ApiConstructorArgs(lazy, userAgentProvider, uuid, j, lazy2, lazy3, lazy4, lazy5, lazy6);
        return (MfpV2ConfigApi) new MfpV2ConfigApiImpl(apiConstructorArgs).withMapper(apiJsonMapper);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public DeviceActivationApi providesDeviceActivationApi(Lazy<ApiUrlProvider> lazy, Lazy<MfpApiSettings> lazy2, UserAgentProvider userAgentProvider, @Named("deviceUUID") UUID uuid, @Named("appVersionCode") long j, Lazy<RequestInterceptor> lazy3, ApiJsonMapper apiJsonMapper, Lazy<Bus> lazy4, Lazy<AuthTokenProvider> lazy5, Lazy<CountryService> lazy6, Lazy<DeviceInfo> lazy7) {
        ApiConstructorArgs apiConstructorArgs = new ApiConstructorArgs(lazy, userAgentProvider, uuid, j, lazy3, lazy4, lazy5, lazy6, lazy7);
        Lazy<MfpApiSettings> lazy8 = lazy2;
        return new DeviceActivationApi(apiConstructorArgs, lazy2).withMapper((Mapper2) apiJsonMapper);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MfpSyncApi providesSyncApi(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        apiBinaryConstructorArgs.setMapper(null);
        return new MfpSyncApiImpl(apiBinaryConstructorArgs);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MfpSearchApi providesSearchApi(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        return new MfpSearchApiImpl(apiBinaryConstructorArgs);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MfpInformationApi providesInfoApi(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        return new MfpInformationApiImpl(apiBinaryConstructorArgs);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MfpActionApi providesActionApi(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        return new MfpActionApiImpl(apiBinaryConstructorArgs);
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public UserAgentProvider provideUserAgent(Context context, Application application2, TelephonyManager telephonyManager, @Named("client_id") String str) {
        String simOperatorName = telephonyManager != null ? telephonyManager.getSimOperatorName() : null;
        String str2 = "";
        boolean z = false;
        try {
            Class loadClass = application2.getClassLoader().loadClass("android.os.SystemProperties");
            str2 = Strings.toString(loadClass.getMethod("get", new Class[]{String.class}).invoke(loadClass, new Object[]{"ro.com.google.clientidbase"}));
        } catch (Exception e) {
            Ln.d(e);
        }
        BuildConfiguration buildConfiguration = BuildConfiguration.getBuildConfiguration();
        Builder put = new Builder().put("app_name", (buildConfiguration.isDebug() || buildConfiguration.isQABuild()) ? "mfpDebug" : GoogleFit.DATA_SOURCE_NAME).put(Params.API_CLIENT_ID, Strings.toString(str)).put("brand", Build.BRAND).put(Params.CLIENT_ID_BASE, Strings.toString(str2)).put("device", Build.DEVICE).put("locale", Strings.toString(Locale.getDefault())).put("manufacturer", Build.MANUFACTURER).put("model", Build.MODEL);
        String str3 = Params.PRELOAD;
        if ((application2.getApplicationInfo().flags & 1) == 1) {
            z = true;
        }
        return new UserAgentProviderImpl(put.put(str3, Strings.toString(Boolean.valueOf(z))).put(Params.RELEASE_NAME, VERSION.RELEASE).put(Params.SIM_NAME, simOperatorName).put(Params.VERSION_NAME, VersionUtils.getAppVersionName(context)).build());
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public PacketFactory providesPacketFactory(Lazy<DbConnectionManager> lazy) {
        return new PacketFactoryImpl(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public SyncPointerService provideSyncPointerService(Lazy<SyncPointersTable> lazy, Lazy<Session> lazy2, Lazy<SyncUtil> lazy3) {
        return new SyncPointerServiceImpl(lazy, lazy2, lazy3);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("sync-settings")
    public SharedPreferences provideSyncSettingsStore(Context context) {
        return context.getSharedPreferences(SharedConstants.Injection.Named.SYNC_V1_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    @Named("geo-location")
    public SharedPreferences provideGeoLocationSettingsStore(Context context) {
        return context.getSharedPreferences(SharedConstants.Injection.Named.GEO_LOCATION_SETTINGS_STORE, 0);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MfpApiSettings provideApiSettings(@Named("app-settings") SharedPreferences sharedPreferences) {
        return new MfpApiSettingsImpl(sharedPreferences);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ApiBinaryConstructorArgs provideBinaryCtorArgs(Lazy<ApiUrlProvider> lazy, UserAgentProvider userAgentProvider, @Named("deviceUUID") UUID uuid, @Named("appVersionCode") long j, Lazy<RequestInterceptor> lazy2, Lazy<Bus> lazy3, Provider<BinaryEncoder> provider, ApiBinaryMapperBase apiBinaryMapperBase, Lazy<AuthTokenProvider> lazy4, Lazy<CountryService> lazy5, Lazy<DeviceInfo> lazy6) {
        ApiBinaryConstructorArgs apiBinaryConstructorArgs = new ApiBinaryConstructorArgs(lazy, userAgentProvider, uuid, j, lazy2, lazy3, provider, apiBinaryMapperBase, lazy4, lazy5, lazy6);
        return apiBinaryConstructorArgs;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public PackageManager getPackageManager(Context context) {
        return context.getPackageManager();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public TelephonyManager provideTelephonyManager(Context context) {
        return (TelephonyManager) getSystemService(context, "phone");
    }

    private <T> T getSystemService(Context context, String str) {
        return context.getSystemService(str);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public InputMethodManager provideInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService("input_method");
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ConnectivityManager providesConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public Handler provideMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public Glide providesGlide(Context context, Lazy<ApiUrlProvider> lazy) {
        return GlideUtil.init(context, lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ActivityManager providesActivityManager(Context context) {
        return (ActivityManager) context.getSystemService(AbstractEvent.ACTIVITY);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public PremiumDebugActivity providesDevMenu() {
        return new PremiumDebugActivity();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public ChartRendererFactory providesChartRendererFactory(Context context, Lazy<CoreChartRendererBaseConstructorArgs> lazy) {
        return new ChartRendererFactory(context, lazy);
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public ChartLegendFactory providesCoreChartLegendFactory() {
        return new ChartLegendFactory();
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public GeoLocationService providesGeoLocationService(Context context, @Named("geo-location") SharedPreferences sharedPreferences) {
        return new GeoLocationServiceImpl(context, sharedPreferences);
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public PremiumFeatureOverrides providesPremiumFeatureOverrides(@Named("premium-overrides") SharedPreferences sharedPreferences) {
        return new PremiumFeatureOverrides(sharedPreferences);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public UpsellService providesUpsellService(Context context, Lazy<ConfigService> lazy, Lazy<CountryService> lazy2) {
        return new UpsellServiceImpl(context, lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public CoreChartRendererBaseConstructorArgs providesCoreChartRendererBaseConstructorArgs(Lazy<LocalSettingsService> lazy, Lazy<Session> lazy2, Lazy<UserEnergyService> lazy3, Lazy<NutrientGoalService> lazy4, Lazy<NutrientGoalsUtil> lazy5, Lazy<NutritionGraphService> lazy6) {
        CoreChartRendererBaseConstructorArgs coreChartRendererBaseConstructorArgs = new CoreChartRendererBaseConstructorArgs(lazy, lazy2, lazy3, lazy4, lazy5, lazy6);
        return coreChartRendererBaseConstructorArgs;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Named("nutrition-graph-preference")
    @Provides
    public KeyedSharedPreferences provideNutritionGraphPreference(Context context, Lazy<Session> lazy) {
        return new KeyedSharedPreferences(context.getSharedPreferences(Injection.Named.NUTRITION_GRAPH_PREFERENCE, 0), new UserIdSharedPreferenceKeyProvider(lazy));
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public NutritionGraphPreferenceService providesNutritionGraphPreferenceService(@Named("nutrition-graph-preference") KeyedSharedPreferences keyedSharedPreferences) {
        return new NutritionGraphPreferenceServiceImpl(keyedSharedPreferences);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public PaymentAnalyticsHelper providePaymentAnalyticsHelper(Context context, Lazy<ConfigService> lazy, Lazy<AppSettings> lazy2, Lazy<AnalyticsService> lazy3, Lazy<GeoLocationService> lazy4, Lazy<CountryService> lazy5, Lazy<PaymentService> lazy6, Lazy<ProductService> lazy7, Lazy<LocalSettingsService> lazy8) {
        PaymentAnalyticsHelperImpl paymentAnalyticsHelperImpl = new PaymentAnalyticsHelperImpl(context, lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, lazy8);
        return paymentAnalyticsHelperImpl;
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public NutritionGraphAnalyticsHelper providesNutritionGraphAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new NutritionGraphAnalyticsHelperImpl(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public NutritionGraphService providesNutritionGraphService(Lazy<NutrientGoalService> lazy, Lazy<UserEnergyService> lazy2, Lazy<Session> lazy3, Lazy<NutrientGoalsUtil> lazy4) {
        return new NutritionGraphServiceImpl(lazy, lazy2, lazy3, lazy4);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public PremiumApiErrorUtil providesPremiumApiErrorUtil(Context context, Bus bus) {
        return new PremiumApiErrorUtil(context, bus);
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public DiaryDayCache providesDiaryDayCache() {
        return new DiaryDayCache();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public MealSharingDirectionsAnalyticsHelper provideMealBrowseAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new MealSharingDirectionsAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public MultiAddFoodHelper providesMultiAddFoodHelper(Lazy<LocalSettingsService> lazy) {
        return new MultiAddFoodHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FoodSearchAnalyticsHelper providesFoodSearchAnalyticsHelper(Lazy<ActionTrackingService> lazy, Lazy<AnalyticsService> lazy2, Lazy<MultiAddFoodHelper> lazy3, Lazy<CountryService> lazy4, Lazy<DiaryService> lazy5) {
        FoodSearchAnalyticsHelperImpl foodSearchAnalyticsHelperImpl = new FoodSearchAnalyticsHelperImpl(lazy, lazy2, lazy3, lazy4, lazy5);
        return foodSearchAnalyticsHelperImpl;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public ExerciseSearchAnalyticsHelper providesExerciseSearchAnalyticsHelper(Lazy<ActionTrackingService> lazy) {
        return new ExerciseSearchAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public AppIndexerBot providesAppIndexerBot() {
        return new AppIndexerBotImpl();
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public ViewModelCache providesViewModelCache() {
        return new TtlViewModelCache();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ChallengesService providesChallengesService(Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<CountryService> lazy2) {
        return new ChallengesServiceImpl(provider, lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ChallengesAnalyticsHelper providesChallengesAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new ChallengesAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Singleton
    @Provides
    public AdsAnalyticsHelper providesAdsAnalyticHelper(Lazy<MfpAnalyticsService> lazy, Lazy<ConfigService> lazy2) {
        return new AdsAnalyticsHelperImpl(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public VenueService providesVenueService(Provider<MfpV2Api> provider, Lazy<LocationService> lazy) {
        return new VenueServiceImpl(provider, lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MenuService providesMenuService(Provider<MfpV2Api> provider) {
        return new MenuServiceImpl(provider);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public StatusAnalytics providesStatusAnalytics(Lazy<AnalyticsService> lazy) {
        return new StatusAnalyticsImpl(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public RestaurantLoggingSettingsService providesRestaurantLoggingSettingsService(@Named("restaurant_settings_preferences") KeyedSharedPreferences keyedSharedPreferences) {
        return new RestaurantLoggingSettingsServiceImpl(keyedSharedPreferences);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public RestaurantLoggingAnalyticsHelper providesRestaurantLoggingAnalyticsHelper(Lazy<AnalyticsService> lazy, Lazy<MfpAnalyticsService> lazy2) {
        return new RestaurantLoggingAnalyticsHelper(lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public DeviceInfo providesDeviceInfo(Context context) {
        return new DeviceInfo(context);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public UpdatedTermsAnalyticsHelper providesUpdatedTermsAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new UpdatedTermsAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FoodEditorAnalytics providesFoodEditorAnalytics(Lazy<DiaryService> lazy, Lazy<AnalyticsService> lazy2, Lazy<ActionTrackingService> lazy3, Lazy<CountryService> lazy4) {
        return new FoodEditorAnalyticsImpl(lazy, lazy2, lazy3, lazy4);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FacebookService providesFacebookService(Lazy<ThirdPartyService> lazy, Lazy<LoginModel> lazy2, Lazy<Session> lazy3) {
        return new FacebookServiceImpl(lazy, lazy2, lazy3);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public NewsFeedAnalyticsHelper providesNewsFeedAnalyticsHelper(Lazy<AnalyticsService> lazy, Lazy<ConfigService> lazy2, Lazy<UserApplicationSettingsService> lazy3) {
        return new NewsFeedAnalyticsHelper(lazy, lazy2, lazy3);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public WaterLoggingAnalyticsHelper providesWaterLoggingAnalyticsHelper(Lazy<AnalyticsService> lazy, Lazy<ConfigService> lazy2) {
        return new WaterLoggingAnalyticsHelper(lazy, lazy2);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public DiaryAnalyticsHelper providesDiaryAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new DiaryAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public MealAnalyticsHelper providesMealAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new MealAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public RecipesMealsFoodsAnalyticsHelper providesSettingsAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new RecipesMealsFoodsAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public ImageReportingService providesImageReportingService(Provider<MfpV2Api> provider) {
        return new ImageReportingServiceImpl(provider);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FileExportService providesFileExportService(Provider<MfpV2Api> provider) {
        return new FileExportServiceImpl(provider);
    }

    /* access modifiers changed from: protected */
    @Singleton
    @NonNull
    @Provides
    public MultiAddMenuItemHelper providesMultiAddMenuItemHelper() {
        return new MultiAddMenuItemHelper();
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public PremiumAnalyticsHelper providesPremiumAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new PremiumAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: protected */
    @NonNull
    @Provides
    public FoodMigrationAndCorrectionHelper providesFoodMigrationAndCorrectionHelper(Lazy<LocalSettingsService> lazy, Lazy<DbConnectionManager> lazy2) {
        return new FoodMigrationAndCorrectionHelper(lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public NotificationInboxAnalyticsHelper providesNotificationInboxAnalyticsService(Lazy<AnalyticsService> lazy) {
        return new NotificationInboxAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public FileExportAnalyticsHelper providesFileExportAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new FileExportAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public NutrientDashboardAnalyticsHelper providesNutrientDashboardAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new NutrientDashboardAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public GlobalAppPreferenceMigrationUtil provideGlobalAppPreferenceMigrationUtil(Context context, Lazy<LocalSettingsService> lazy, Lazy<GlobalSettingsService> lazy2, Lazy<Session> lazy3) {
        return new GlobalAppPreferenceMigrationUtil(context, lazy, lazy2, lazy3);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    @Named("global_settings_preferences")
    public SharedPreferences provideGlobalSettingsPreference(Context context) {
        return context.getSharedPreferences(Injection.Named.GLOBAL_SETTINGS_PREFERENCES, 0);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public GlobalSettingsService provideGlobalSettingsService(@Named("global_settings_preferences") SharedPreferences sharedPreferences) {
        return new GlobalSettingsServiceImpl(sharedPreferences);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public RecipesAnalyticsHelper provideRecipesAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new RecipesAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @Provides
    @Named("recipe-cache-store")
    public SharedPreferences provideRecipeCacheStoreSharedPreferences(Context context) {
        return context.getSharedPreferences(Injection.Named.RECIPE_CACHE_STORE, 0);
    }

    /* access modifiers changed from: 0000 */
    @Provides
    @Named("achievement-store")
    public SharedPreferences provideAchievementsStoreSharedPreferences(Context context) {
        return context.getSharedPreferences(Injection.Named.ACHIEVEMENT_STORE, 0);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Provides
    public Cache<MfpRecipeListContainer> provideRecipeCache(@Named("recipe-cache-store") SharedPreferences sharedPreferences) {
        return new ExpiringCache(new MemoryCache(new SharedPreferenceCache(sharedPreferences))).setItemExpirationTime(86400000).withMapper(new ApiJsonMapper().withType(MfpRecipeListContainer.class));
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @Provides
    public ProfileAggregatorService provideProfileAggregatorService(Lazy<Session> lazy, Provider<MfpV2Api> provider, SHealthConnection sHealthConnection, GoogleFitClient googleFitClient, Lazy<CountryService> lazy2, Lazy<NutrientGoalService> lazy3, Lazy<UserWeightService> lazy4) {
        ProfileAggregatorServiceImpl profileAggregatorServiceImpl = new ProfileAggregatorServiceImpl(lazy, provider, sHealthConnection, googleFitClient, lazy2, lazy3, lazy4);
        return profileAggregatorServiceImpl;
    }

    /* access modifiers changed from: 0000 */
    @Provides
    public ExploreService providesExploreService(Provider<MfpV2Api> provider, Lazy<ChallengesService> lazy, Lazy<MealService> lazy2, Lazy<VenueService> lazy3) {
        return new ExploreServiceImpl(provider, lazy, lazy2, lazy3);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public VideoAnalyticsHelper provideVideoAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new VideoAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public PermissionAnalyticsHelper providePermissionAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new PermissionAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @Provides
    public DeleteAccountService provideDeleteAccountService(Provider<MfpV2Api> provider, Lazy<Session> lazy) {
        return new DeleteAccountServiceImpl(provider, lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public DeleteAccountAnalyticsHelper provideDeleteAccountAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new DeleteAccountAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public HomeAnalyticsHelper provideHomeAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new HomeAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @Provides
    public JobServiceFactory provideJobServiceFactory(Context context) {
        return new JobServiceFactory(context);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public ExerciseAnalyticsHelper provideExerciseAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new ExerciseAnalyticsHelper(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public ConsentsAnalyticsHelper provideConsentsAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new ConsentsAnalyticsHelperImpl(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public GDPRHelpAnalyticsHelper provideGDPRHelpAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new GDPRHelpAnalyticsHelperImpl(lazy);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public UacfConfigurationSdk provideUacfConfigurationSdk() {
        return new UacfConfigurationSdkFactory().newSdkInstance();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UacfConfigurationUtil provideUacfConfigurationUtil(Lazy<UacfConfigurationSdk> lazy) {
        return new UacfConfigurationUtil(lazy);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public UacfVariantSdk provideUacfVariantSdk() {
        return new UacfVariantSdkFactory().newSdkInstance();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UacfRolloutUtil provideUacfVariantUtil(Lazy<UacfVariantSdk> lazy) {
        return new UacfRolloutUtil(lazy);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public UacfIdentitySdk provideUacfIdentitySdk() {
        return SSO.getSdk();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UacfEmailVerificationManager provideUacfEmailVerificationManager(Lazy<Session> lazy, Lazy<UacfIdentitySdk> lazy2, Lazy<UacfRolloutUtil> lazy3, Lazy<UacfConfigurationUtil> lazy4, @Named("app-settings") SharedPreferences sharedPreferences, Lazy<ClientEmailVerificationStatus> lazy5, Lazy<CountryService> lazy6, Lazy<NavigationHelper> lazy7) {
        UacfEmailVerificationManager uacfEmailVerificationManager = new UacfEmailVerificationManager(lazy, lazy2, lazy3, lazy4, sharedPreferences, lazy5, lazy6, lazy7);
        return uacfEmailVerificationManager;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public NutritionAnalyticsHelper provideNutritionAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new NutritionAnalyticsHelperImpl(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public FoodFeedbackAnalyticsHelper provideFoodFoodbackAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new FoodFeedbackAnalyticsHelperImpl(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public FoodFeedbackAction provideFoodFeedbackRepository(Provider<MfpV2Api> provider, Lazy<DbConnectionManager> lazy, Lazy<AppSettings> lazy2) {
        return new FoodFeedbackRepositoryImpl(provider, lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }

    /* access modifiers changed from: 0000 */
    @Reusable
    @Provides
    public TroubleShootingApi provideTroubleshootingApi(Retrofit retrofit) {
        return (TroubleShootingApi) retrofit.create(TroubleShootingApi.class);
    }

    /* access modifiers changed from: 0000 */
    @Reusable
    @NonNull
    @Provides
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }

    /* access modifiers changed from: 0000 */
    @Reusable
    @Provides
    public Retrofit provideRetrofitInterface(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl("https://sync.myfitnesspal.com").client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public TroubleshootingRepository provideTroubleshootingRepository(TroubleShootingApi troubleShootingApi) {
        return new TroubleshootingRepositoryImpl(troubleShootingApi);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public LocalFoodSearchRepository provideLocalFoodSearchRepository(DbConnectionManager dbConnectionManager, Lazy<LocalSettingsService> lazy, Lazy<CountryService> lazy2, Lazy<Session> lazy3, Lazy<MealUtil> lazy4) {
        LocalFoodSearchRepository localFoodSearchRepository = new LocalFoodSearchRepository(dbConnectionManager, lazy, lazy2, lazy3, lazy4);
        return localFoodSearchRepository;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NotNull
    @Provides
    public OnlineFoodSearchRepository provideOnlineFoodSearchRepository(Context context, SearchService searchService) {
        return new OnlineFoodSearchRepository(context, searchService);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public AdConsentsAnalyticsHelper provideAdConsentsAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new AdConsentsAnalyticsHelperImpl(lazy);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public PremiumOnboardingAnalyticsHelper providePremiumOnboardingAnalyticsHelper(Lazy<LocalSettingsService> lazy, Lazy<AnalyticsService> lazy2) {
        return new PremiumOnboardingAnalyticsHelperImpl(lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public UserSummaryRepository provideUserSummaryRepository(Lazy<Session> lazy, Lazy<UserSummaryService> lazy2) {
        return new UserSummaryRepositoryImpl(lazy, lazy2);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public StoredAchievementEvents provideStoredAchievementEvents(@Named("achievement-store") SharedPreferences sharedPreferences) {
        return new StoredAchievementEvents(sharedPreferences);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public AchievementInterstitialAd provideAchievementInterstitialAd(Context context, AchievementAdAnalyticsEvents achievementAdAnalyticsEvents) {
        return new AchievementInterstitialAd(context, achievementAdAnalyticsEvents);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public AchievementAdAnalyticsEvents provideAchievementAdAnalyticsEvents(AnalyticsService analyticsService) {
        return new AchievementAdAnalyticsEvents(analyticsService);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public AchievementAdManager provideAchievementAdManager(Lazy<UserSummaryRepository> lazy, Lazy<PremiumService> lazy2, Lazy<AdsSettings> lazy3, Lazy<LocalSettingsService> lazy4, Lazy<StoredAchievementEvents> lazy5, Lazy<AchievementInterstitialAd> lazy6, Lazy<AchievementAdAnalyticsEvents> lazy7, @Named("client_id") String str) {
        AchievementAdManager achievementAdManager = new AchievementAdManager(lazy, lazy2, lazy3, lazy4, lazy5, lazy6, lazy7, str);
        return achievementAdManager;
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public Navigator provideNavigator() {
        return new NavigatorImpl();
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public PremiumOnboardingFlowCoordinator providePremiumOnboardingFlowCoordinator(Navigator navigator, UserEnergyService userEnergyService, NutrientGoalService nutrientGoalService) {
        return new PremiumOnboardingFlowCoordinator(navigator, userEnergyService, nutrientGoalService);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public PremiumOnboardingViewModelFactory providePremiumOnboardingViewFactory(PremiumOnboardingFlowCoordinator premiumOnboardingFlowCoordinator, Lazy<PremiumOnboardingAnalyticsHelper> lazy) {
        return new PremiumOnboardingViewModelFactory(this.application, premiumOnboardingFlowCoordinator, lazy);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public PremiumUpsellNavigator providePremiumUpsellNavigator() {
        return new PremiumUpsellNavigatorImpl();
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public PremiumUpsellCoordinator providePremiumUpsellCoordinator(PremiumUpsellNavigator premiumUpsellNavigator, ConfigService configService, PremiumService premiumService) {
        return new PremiumUpsellCoordinator(premiumUpsellNavigator, configService, premiumService);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public PremiumUpsellViewModelFactory providePremiumUpsellViewModelFactory(PremiumUpsellCoordinator premiumUpsellCoordinator) {
        return new PremiumUpsellViewModelFactory(this.application, premiumUpsellCoordinator);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public AndroidAdvertisementIdentifier provideAndroidAdvertisementIdentifier() {
        return new AndroidAdvertisementIdentifier();
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Singleton
    @Provides
    public AdIdConsentCompliant provideAdIdConsentCompliant(Lazy<LocalSettingsService> lazy, Lazy<AdsAnalyticsHelper> lazy2, AndroidAdvertisementIdentifier androidAdvertisementIdentifier) {
        return new AdIdConsentCompliant(lazy, lazy2, androidAdvertisementIdentifier);
    }

    /* access modifiers changed from: 0000 */
    @Singleton
    @NonNull
    @Provides
    public AppLifecycleObserver provideAppLifecycleObserver(Context context, AdIdConsentCompliant adIdConsentCompliant) {
        return new AppLifecycleObserver(context, adIdConsentCompliant);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @Provides
    public AlexaInterstitialAnalyticsHelper provideAlexaInterstitialAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        return new AlexaInterstitialAnalyticsHelperImpl(lazy);
    }
}
