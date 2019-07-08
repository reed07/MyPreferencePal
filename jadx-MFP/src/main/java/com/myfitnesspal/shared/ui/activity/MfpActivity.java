package com.myfitnesspal.shared.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.mopub.mobileads.MoPubView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.barcode.event.BarcodeScanSuccessEvent;
import com.myfitnesspal.feature.consents.event.ConsentsRequire;
import com.myfitnesspal.feature.consents.model.ConsentsViewModel.Mode;
import com.myfitnesspal.feature.consents.task.ConsentsTask.CompletedEvent;
import com.myfitnesspal.feature.consents.ui.activity.ConsentsActivity;
import com.myfitnesspal.feature.consents.ui.activity.LearnMoreActivity;
import com.myfitnesspal.feature.debug.ui.activity.AdvancedDebuggingActivity;
import com.myfitnesspal.feature.debug.ui.activity.AnalyticsEventsActivity;
import com.myfitnesspal.feature.debug.ui.activity.EndpointActivity;
import com.myfitnesspal.feature.debug.ui.activity.PremiumDebugActivity;
import com.myfitnesspal.feature.debug.ui.activity.RolloutDebugActivity;
import com.myfitnesspal.feature.debug.util.AdvancedDebuggingUtil;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.drawer.event.ClearDrawerMenuBadgeEvent;
import com.myfitnesspal.feature.drawer.ui.view.LeftDrawerMenu;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.mixin.GoogleFitMixin;
import com.myfitnesspal.feature.externalsync.impl.shealth.mixin.SHealthMixin;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.goals.event.UpdateGoalsCompleteEvent;
import com.myfitnesspal.feature.goals.ui.activity.AdjustGoalComplete;
import com.myfitnesspal.feature.goals.ui.activity.EatingDisorderAdjustGoalComplete;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.goals.ui.activity.UpdateGoals;
import com.myfitnesspal.feature.help.ui.activity.AboutUs;
import com.myfitnesspal.feature.home.event.AppResumedFromExtendedIdleEvent;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.permissions.PermissionAnalyticsHelper;
import com.myfitnesspal.feature.permissions.PermissionsMixin;
import com.myfitnesspal.feature.premium.event.PremiumEvents.SubscriptionsRefreshed;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.feature.progress.ui.activity.RecommendGoal;
import com.myfitnesspal.feature.recipes.event.CreateNewRecipeEvent;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.ActionType;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.recipes.ui.activity.CreateRecipeManuallyActivity;
import com.myfitnesspal.feature.recipes.ui.dialog.CreateRecipeDialogFragment;
import com.myfitnesspal.feature.registration.event.UnderageRegistrationFailureEvent;
import com.myfitnesspal.feature.registration.ui.activity.AccountRestrictedActivity;
import com.myfitnesspal.feature.registration.ui.activity.FacebookLoginActivity;
import com.myfitnesspal.feature.registration.ui.activity.FinishOnboardingActivity;
import com.myfitnesspal.feature.registration.ui.activity.ForgotPasswordActivity;
import com.myfitnesspal.feature.registration.ui.activity.LoginActivity;
import com.myfitnesspal.feature.registration.ui.activity.LogoutActivity;
import com.myfitnesspal.feature.registration.ui.activity.SignUpActivity;
import com.myfitnesspal.feature.registration.ui.activity.TermsOfUseActivity;
import com.myfitnesspal.feature.registration.util.StartupManager;
import com.myfitnesspal.feature.search.ui.dialog.SearchCategoryDialog;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.settings.ui.activity.LegacySettingsActivity;
import com.myfitnesspal.feature.settings.ui.activity.PasscodeView;
import com.myfitnesspal.feature.settings.ui.activity.SettingsActivity;
import com.myfitnesspal.feature.settings.ui.activity.TroubleshootingActivity;
import com.myfitnesspal.framework.mixin.LifecycleMixin.AttachTarget;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelCallback;
import com.myfitnesspal.framework.mvvm.ViewModelComponent;
import com.myfitnesspal.framework.mvvm.ViewModelParent;
import com.myfitnesspal.framework.taskrunner.TaskRunnerUtil;
import com.myfitnesspal.shared.api.ApiUrlProvider;
import com.myfitnesspal.shared.constants.Constants.ABTest.BannerAdCount201602;
import com.myfitnesspal.shared.constants.Constants.ABTest.NewUserBannerAdsAndroid201507;
import com.myfitnesspal.shared.constants.Constants.ABTest.ReactivatingUserBannerAdsAndroid201507;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.AuthFailedEvent;
import com.myfitnesspal.shared.event.BusyEvent;
import com.myfitnesspal.shared.event.DialogWeightEvent;
import com.myfitnesspal.shared.event.HideSoftInputEvent;
import com.myfitnesspal.shared.event.NewStatusPostedEvent;
import com.myfitnesspal.shared.event.NoConnectionEvent;
import com.myfitnesspal.shared.event.RefreshAdEvent;
import com.myfitnesspal.shared.event.SearchTermTooShortEvent;
import com.myfitnesspal.shared.event.ShowDialogFragmentEvent;
import com.myfitnesspal.shared.event.SyncServiceIncrementalFailedEvent;
import com.myfitnesspal.shared.event.WaterAddedEvent;
import com.myfitnesspal.shared.extension.BuildUtil;
import com.myfitnesspal.shared.injection.component.ApplicationComponent;
import com.myfitnesspal.shared.model.AdNetworkType;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.ads.AdsAnalyticsHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.analytics.AnalyticsSyncMode;
import com.myfitnesspal.shared.service.analytics.MfpAnalyticsService;
import com.myfitnesspal.shared.service.appindexer.AppIndexerBot;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.component.MfpUiComponentDelegate;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.StateAwareScrollView;
import com.myfitnesspal.shared.util.ActivityUtils;
import com.myfitnesspal.shared.util.AdsHelper;
import com.myfitnesspal.shared.util.AdsHelper.Builder;
import com.myfitnesspal.shared.util.AdsHelper.DfpAdsListener;
import com.myfitnesspal.shared.util.AnalyticsUtil;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.InputMethodManagerHelper;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.MoPubUtil;
import com.myfitnesspal.shared.util.PincodeHelper;
import com.myfitnesspal.shared.util.ShakeDetectorMixin;
import com.myfitnesspal.shared.util.Toaster;
import com.myfitnesspal.shared.util.UpdateWeightProxy;
import com.myfitnesspal.shared.util.UpdateWeightProxy.FinishMode;
import com.myfitnesspal.shared.util.UpdateWeightProxy.Listener;
import com.myfitnesspal.shared.util.UpdateWeightProxy.UpdateMode;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.sync.engine.UacfScheduler;
import com.uacf.taskrunner.LifecycleDelegate;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.TaskCallbacks;
import com.uacf.taskrunner.Task;
import dagger.Lazy;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

public abstract class MfpActivity extends AppCompatActivity implements ViewModelCallback, ViewModelParent, MfpUiComponentInterface, TaskCallbacks {
    private static final String EXTRA_DIALOG_FRAGMENT_TAGS = "activity_delegate_dialog_fragment_tags";
    protected static final String EXTRA_EXTRAS = "extras";
    private static final String MULTI_WINDOW_MODE_ENTER = "multi_window_mode_on";
    private static final String MULTI_WINDOW_MODE_EXIT = "multi_window_mode_off";
    private static final String ORIENTATION = "orientation";
    private static final String ORIENTATION_SCREEN = "screen";
    protected static final int PROMINENT_ACTION_ITEM = 5003;
    private static final HashSet<Class<?>> REGISTRATION_AND_SYNC_ACTIVITIES = new HashSet<>();
    private static String lastOrientation = "";
    private static Date sLastPausedTime = new Date();
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<AdUnitService> adUnitService;
    @Inject
    Lazy<AdsAnalyticsHelper> adsAnalyticsHelper;
    private AdsHelper adsHelper;
    @Inject
    Lazy<AdsSettings> adsSettings;
    @Inject
    Lazy<AdvancedDebuggingUtil> advancedDebuggingUtil;
    @Inject
    Lazy<UacfScheduler<AnalyticsSyncMode>> analyticsSyncScheduler;
    @Inject
    public Lazy<ApiUrlProvider> apiUrlProvider;
    @Inject
    Lazy<AppIndexerBot> appIndexerBot;
    @Inject
    Lazy<BackgroundJobHelper> backgroundServiceHelper;
    /* access modifiers changed from: private */
    public BannerAdDisplayState bannerAdDisplayState = BannerAdDisplayState.Unknown;
    protected MfpUiComponentDelegate componentDelegate = new MfpUiComponentDelegate(this);
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    DbConnectionManager dbConnectionManager;
    @Inject
    Lazy<DeepLinkManager> deepLinkManager;
    private DfpAdsListener dfpAdsListener = new DfpAdsListener() {
        public void onAdFailedToLoad() {
        }

        public void onAdLoaded() {
        }
    };
    private LeftDrawerMenu drawerMenu;
    @Inject
    Glide glide;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    @Inject
    Lazy<GoogleFitClient> googleFitClient;
    private Handler handler = new Handler();
    private boolean hideBannerAds;
    @Inject
    Lazy<InputMethodManager> imm;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocationService> locationService;
    @Inject
    Lazy<MfpAnalyticsService> mfpAnalyticsService;
    private MoPubView moPubAdView;
    private final DialogPositiveListener onSignUpGenderAgeDialogPostiveListener = new DialogPositiveListener() {
        public final void onClick(Object obj) {
            
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0002: INVOKE  (wrap: com.myfitnesspal.shared.ui.activity.MfpActivity
                  0x0000: IGET  (r0v0 com.myfitnesspal.shared.ui.activity.MfpActivity) = (r1v0 'this' com.myfitnesspal.shared.ui.activity.-$$Lambda$MfpActivity$HFcsJRH4imDmjHr6wD8llH9tNnA A[THIS]) com.myfitnesspal.shared.ui.activity.-$$Lambda$MfpActivity$HFcsJRH4imDmjHr6wD8llH9tNnA.f$0 com.myfitnesspal.shared.ui.activity.MfpActivity), (r2v0 'obj' java.lang.Object) com.myfitnesspal.shared.ui.activity.MfpActivity.lambda$new$2(com.myfitnesspal.shared.ui.activity.MfpActivity, java.lang.Object):void type: STATIC in method: com.myfitnesspal.shared.ui.activity.-$$Lambda$MfpActivity$HFcsJRH4imDmjHr6wD8llH9tNnA.onClick(java.lang.Object):void, dex: classes4.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:245)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:213)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:210)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:203)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:316)
                	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:661)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:595)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:353)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:105)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:95)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:469)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:223)
                	at jadx.core.codegen.ClassGen.addInsnBody(ClassGen.java:435)
                	at jadx.core.codegen.ClassGen.addField(ClassGen.java:376)
                	at jadx.core.codegen.ClassGen.addFields(ClassGen.java:346)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:223)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
                	at jadx.core.ProcessClass.process(ProcessClass.java:36)
                	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
                	at jadx.api.JavaClass.decompile(JavaClass.java:62)
                	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
                Caused by: org.objenesis.ObjenesisException: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:57)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.newConstructorForSerialization(SunReflectionFactoryHelper.java:37)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator.<init>(SunReflectionFactoryInstantiator.java:41)
                	at org.objenesis.strategy.StdInstantiatorStrategy.newInstantiatorOf(StdInstantiatorStrategy.java:68)
                	at org.objenesis.ObjenesisBase.getInstantiatorOf(ObjenesisBase.java:94)
                	at org.objenesis.ObjenesisBase.newInstance(ObjenesisBase.java:73)
                	at com.rits.cloning.ObjenesisInstantiationStrategy.newInstance(ObjenesisInstantiationStrategy.java:17)
                	at com.rits.cloning.Cloner.newInstance(Cloner.java:300)
                	at com.rits.cloning.Cloner.cloneObject(Cloner.java:461)
                	at com.rits.cloning.Cloner.cloneInternal(Cloner.java:456)
                	at com.rits.cloning.Cloner.deepClone(Cloner.java:326)
                	at jadx.core.dex.nodes.InsnNode.copy(InsnNode.java:352)
                	at jadx.core.dex.nodes.InsnNode.copyCommonParams(InsnNode.java:333)
                	at jadx.core.dex.instructions.InvokeNode.copy(InvokeNode.java:56)
                	at jadx.core.codegen.InsnGen.inlineMethod(InsnGen.java:880)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:669)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:357)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:239)
                	... 31 more
                Caused by: java.lang.ClassNotFoundException: sun.reflect.ReflectionFactory
                	at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(Unknown Source)
                	at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.ClassLoader.loadClass(Unknown Source)
                	at java.base/java.lang.Class.forName0(Native Method)
                	at java.base/java.lang.Class.forName(Unknown Source)
                	at org.objenesis.instantiator.sun.SunReflectionFactoryHelper.getReflectionFactoryClass(SunReflectionFactoryHelper.java:54)
                	... 48 more
                */
            /*
                this = this;
                com.myfitnesspal.shared.ui.activity.MfpActivity r0 = com.myfitnesspal.shared.ui.activity.MfpActivity.this
                
                // error: 0x0002: INVOKE  (r0 I:com.myfitnesspal.shared.ui.activity.MfpActivity), (r2 I:java.lang.Object) com.myfitnesspal.shared.ui.activity.MfpActivity.lambda$new$2(com.myfitnesspal.shared.ui.activity.MfpActivity, java.lang.Object):void type: STATIC
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.activity.$$Lambda$MfpActivity$HFcsJRH4imDmjHr6wD8llH9tNnA.onClick(java.lang.Object):void");
        }
    };
    @Inject
    Lazy<PermissionAnalyticsHelper> permissionAnalyticsHelper;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<RecipeService> recipeService;
    @Inject
    Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    private LifecycleDelegate runnerDelegate;
    @Inject
    Lazy<SHealthConnection> samsungConnection;
    private Debouncer setupBannerAdDebouncer = new Debouncer<Boolean>(Callback.DEFAULT_SWIPE_ANIMATION_DURATION) {
        /* access modifiers changed from: protected */
        public void onDebounced(Boolean bool) {
            if (MfpActivity.this.bannerAdDisplayState == BannerAdDisplayState.Unknown || MfpActivity.this.bannerAdDisplayState != BannerAdDisplayState.from(MfpActivity.this.shouldDisplayAds()) || (bool != null && bool.booleanValue())) {
                MfpActivity.this.setupBannerAds();
            }
        }
    };
    private String sponsoredAdCategory;
    @Inject
    StartupManager startupManager;
    @Inject
    Lazy<StepService> stepService;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;
    @Inject
    Lazy<SyncService> syncService;

    enum BannerAdDisplayState {
        Unknown,
        Visible,
        Hidden;

        public static BannerAdDisplayState from(boolean z) {
            return z ? Visible : Hidden;
        }
    }

    private static class BusEventHelper implements BusEventHandler {
        private final MfpActivity parent;

        public BusEventHelper(MfpActivity mfpActivity) {
            this.parent = mfpActivity;
        }

        @Subscribe
        public void onAlertEvent(AlertEvent alertEvent) {
            this.parent.onAlertEvent(alertEvent);
        }

        @Subscribe
        public void onHideSoftInputEvent(HideSoftInputEvent hideSoftInputEvent) {
            this.parent.onHideSoftInputEvent(hideSoftInputEvent);
        }

        @Subscribe
        public void onNoConnectionAvailable(NoConnectionEvent noConnectionEvent) {
            this.parent.onNoConnectionAvailable(noConnectionEvent);
        }

        @Subscribe
        public void onAuthFailed(AuthFailedEvent authFailedEvent) {
            this.parent.onAuthFailed(authFailedEvent);
        }

        @Subscribe
        public void onBusyEvent(BusyEvent busyEvent) {
            this.parent.onBusyEvent(busyEvent);
        }

        @Subscribe
        public void onRefreshAd(RefreshAdEvent refreshAdEvent) {
            this.parent.onRefreshAd(refreshAdEvent);
        }

        @Subscribe
        public void onSubscriptionsRefreshed(SubscriptionsRefreshed subscriptionsRefreshed) {
            this.parent.onSubscriptionsRefreshed(subscriptionsRefreshed);
        }

        @Subscribe
        public void onSyncFinishedEvent(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
            this.parent.onSyncFinishedEvent(uacfScheduleFinishedInfo);
        }

        @Subscribe
        public void onSearchTermTooShortEvent(SearchTermTooShortEvent searchTermTooShortEvent) {
            this.parent.onSearchTermTooShortEvent(searchTermTooShortEvent);
        }

        @Subscribe
        public void onIncrementalSyncUnsuccessful(SyncServiceIncrementalFailedEvent syncServiceIncrementalFailedEvent) {
            this.parent.onIncrementalSyncUnsuccessful(syncServiceIncrementalFailedEvent);
        }

        @Subscribe
        public void onCreateRecipe(CreateNewRecipeEvent createNewRecipeEvent) {
            this.parent.onCreateRecipe(createNewRecipeEvent);
        }

        @Subscribe
        public void onWaterAddedEvent(WaterAddedEvent waterAddedEvent) {
            this.parent.onWaterAddedEvent(waterAddedEvent);
        }

        @Subscribe
        public void onDialogWeightEvent(DialogWeightEvent dialogWeightEvent) {
            this.parent.onDialogWeightEvent(dialogWeightEvent);
        }

        @Subscribe
        public void onNewStatusPosted(NewStatusPostedEvent newStatusPostedEvent) {
            this.parent.onNewStatusPosted();
        }

        @Subscribe
        public void onClearDrawerMenuBadgeEvent(ClearDrawerMenuBadgeEvent clearDrawerMenuBadgeEvent) {
            this.parent.clearDrawerMenuBadge();
        }

        @Subscribe
        public void onBarcodeScanSuccessEvent(BarcodeScanSuccessEvent barcodeScanSuccessEvent) {
            this.parent.onBarcodeScanSuccessEvent(barcodeScanSuccessEvent);
        }

        @Subscribe
        public void onConsentsTaskCompletedEvent(CompletedEvent completedEvent) {
            if (completedEvent != null && ((Boolean) completedEvent.getResult()).booleanValue()) {
                this.parent.componentDelegate.postEventAfterResume(new ConsentsRequire());
            }
        }

        @Subscribe
        public void onUpdateGoalsCompleted(UpdateGoalsCompleteEvent updateGoalsCompleteEvent) {
            this.parent.onUpdateGoalsCompleted(updateGoalsCompleteEvent);
        }

        @Subscribe
        public void onConsentsRequire(ConsentsRequire consentsRequire) {
            this.parent.getNavigationHelper().withIntent(ConsentsActivity.newStartIntent(this.parent, Mode.EXISTING_ADD, "", "")).startActivity();
        }

        @Subscribe
        public void onUnderageRegistrationFailureEvent(UnderageRegistrationFailureEvent underageRegistrationFailureEvent) {
            this.parent.onUnderageRegistrationFailureEvent();
        }

        @Subscribe
        public void onShowDialogFragmentEvent(ShowDialogFragmentEvent showDialogFragmentEvent) {
            DialogFragment dialogFragment = showDialogFragmentEvent.getDialogFragment();
            String tag = showDialogFragmentEvent.getTag();
            if (dialogFragment != null && Strings.notEmpty(tag)) {
                this.parent.showDialogFragment(showDialogFragmentEvent.getDialogFragment(), showDialogFragmentEvent.getTag());
            }
        }
    }

    public Activity getActivity() {
        return this;
    }

    public AdUnit getAdUnit() {
        return null;
    }

    public String getAdUnitId() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int getAdsContainerLayoutId() {
        return R.id.ads_container;
    }

    public Map<String, String> getAnalyticsScreenAttributes() {
        return null;
    }

    public String getAnalyticsScreenTag() {
        return null;
    }

    public int getCustomBaseLayoutResId() {
        return 0;
    }

    public int getCustomToolbarLayoutResId() {
        return 0;
    }

    public int getNavigationIcon() {
        return R.drawable.ic_arrow_back_white_24dp;
    }

    public ViewModelCallback getViewModelCallback() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBannerAdSetupCompleted() {
    }

    public void onChildViewModelReset(ViewModelComponent viewModelComponent) {
    }

    public void onViewModelChanged(BaseViewModel baseViewModel, BaseViewModel baseViewModel2) {
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
    }

    public void onViewModelReset() {
    }

    public void onViewModelRestored(BaseViewModel baseViewModel) {
    }

    public boolean shouldShowTitle() {
        return true;
    }

    public boolean showLeftDrawerIfTopLevel() {
        return true;
    }

    public boolean showToolbar() {
        return true;
    }

    static {
        REGISTRATION_AND_SYNC_ACTIVITIES.add(EndpointActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(LoginActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(LogoutActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(FinishOnboardingActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(FacebookLoginActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(ForgotPasswordActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(AboutUs.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(FullScreenWebView.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(SignUpActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(TermsOfUseActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(PremiumDebugActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(RolloutDebugActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(AdvancedDebuggingActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(AnalyticsEventsActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(ConsentsActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(LearnMoreActivity.class);
        REGISTRATION_AND_SYNC_ACTIVITIES.add(TroubleshootingActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        registerMixin(new GoogleFitMixin(this, this.stepService, getSession(), this.googleFitClient));
        registerMixin(new PermissionsMixin(this, this.permissionAnalyticsHelper));
        registerMixin(new SHealthMixin(this, this.samsungConnection));
        registerMixin(new ShakeDetectorMixin(this, getNavigationHelper(), this.advancedDebuggingUtil));
        onPreCreate(bundle);
        super.onCreate(bundle);
        this.runnerDelegate = new LifecycleDelegate(this, this, getClass(), null);
        this.runnerDelegate.onCreate(bundle);
        this.componentDelegate.create(bundle);
        this.startupManager.doStartupTasksIfNecessary(this);
        reportOrientationChangeIfRequired(getResources().getConfiguration());
        this.handler.post(new Runnable(bundle) {
            private final /* synthetic */ Bundle f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                StateAwareScrollView.loadScrollState(MfpActivity.this.findViewById(16908290), this.f$1);
            }
        });
    }

    public void setContentView(int i) {
        View contentView = getContentView(i);
        if (contentView != null) {
            super.setContentView(contentView);
        } else {
            super.setContentView(i);
        }
        onSetContentView();
    }

    public void setContentView(View view) {
        View contentView = getContentView(view);
        if (contentView != null) {
            view = contentView;
        }
        super.setContentView(view);
        onSetContentView();
    }

    public View getContentView(int i) {
        boolean showToolbar = showToolbar();
        if (showAsTopLevelActivity() || showToolbar) {
            return getContentView(LayoutInflater.from(this).inflate(i, null, false));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public MyFitnessPalApp application() {
        return (MyFitnessPalApp) getApplication();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public ApplicationComponent component() {
        return application().component();
    }

    public View getContentView(View view) {
        boolean showToolbar = showToolbar();
        LayoutInflater from = LayoutInflater.from(this);
        int customBaseLayoutResId = getCustomBaseLayoutResId();
        View view2 = null;
        if (customBaseLayoutResId != 0) {
            view2 = from.inflate(customBaseLayoutResId, null, false);
        } else if (!ConfigUtils.isLeftDrawerHidden((ConfigService) this.configService.get()) && showAsTopLevelActivity()) {
            view2 = from.inflate(R.layout.navigation_drawer, null, false);
        } else if (showToolbar) {
            view2 = from.inflate(R.layout.base_layout_toolbar, null, false);
        }
        if (view2 != null) {
            if (showToolbar) {
                ViewGroup viewGroup = (ViewGroup) ViewUtils.findById(view2, R.id.toolbar_container);
                int customToolbarLayoutResId = getCustomToolbarLayoutResId();
                if (customToolbarLayoutResId == 0) {
                    customToolbarLayoutResId = R.layout.toolbar_layout;
                }
                viewGroup.addView(from.inflate(customToolbarLayoutResId, viewGroup, false));
            }
            FrameLayout frameLayout = (FrameLayout) view2.findViewById(R.id.content_frame);
            if (frameLayout != null) {
                frameLayout.addView(view, new LayoutParams(-1, -1));
            }
            view2.findViewById(R.id.content_parent).setContentDescription(getActivity().getClass().getSimpleName());
        }
        return view2;
    }

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    public void onSetContentView() {
        setupToolbar();
        MaterialUtils.showIndeterminateProgress(this, false);
        ButterKnife.bind((Activity) this);
    }

    public PremiumService getPremiumService() {
        return (PremiumService) this.premiumService.get();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        LeftDrawerMenu leftDrawerMenu = this.drawerMenu;
        if (leftDrawerMenu != null) {
            leftDrawerMenu.onNewIntent();
        }
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.componentDelegate.postCreate(bundle);
        setBusy(false);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.componentDelegate.configurationChanged(configuration);
        reportOrientationChangeIfRequired(configuration);
        LeftDrawerMenu leftDrawerMenu = this.drawerMenu;
        if (leftDrawerMenu != null) {
            leftDrawerMenu.configurationChanged(configuration);
        }
    }

    public void reportOrientationChangeIfRequired(Configuration configuration) {
        String orientationForAnalytics = AnalyticsUtil.getOrientationForAnalytics(configuration);
        if (Strings.isEmpty(lastOrientation)) {
            lastOrientation = orientationForAnalytics;
            return;
        }
        if (!Strings.equals(lastOrientation, orientationForAnalytics)) {
            getAnalyticsService().reportEvent(Events.SCREEN_ROTATED, MapUtil.createMap("screen", getAnalyticsScreenTag(), ORIENTATION, orientationForAnalytics));
            lastOrientation = orientationForAnalytics;
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.componentDelegate.activityResult(i, i2, intent);
        if (i != 18) {
            if (i == 28 && i2 == -1) {
                getNavigationHelper().withIntent(AdjustGoalComplete.newStartIntent(this)).startActivity();
            }
        } else if (i2 == -1) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.invitation_sent), getString(R.string.sentInvite), getString(R.string.dismiss));
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.componentDelegate.start();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.componentDelegate.stop();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.componentDelegate.resume();
        registerAllChildrenForBusEvents(true);
        this.runnerDelegate.onResume();
        updateAppState();
        ((ConfigService) this.configService.get()).refreshIfExpiredAsync();
        if (isActivitySyncable()) {
            ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
        }
        ((UacfScheduler) this.analyticsSyncScheduler.get()).debounceDefaultSync();
        postEvent(new RefreshAdEvent());
        try {
            PincodeHelper.current().activityResumed(((GlobalSettingsService) this.globalSettingsService.get()).getRequiresPinCodeOnAppEntry());
            updatePasscodeHelperState();
            if (!showPincodeScreenIfNeeded()) {
                boolean z = !((AppIndexerBot) this.appIndexerBot.get()).isActive() && !getSession().getUser().isLoggedIn() && !REGISTRATION_AND_SYNC_ACTIVITIES.contains(getClass());
                Ln.d("BAILOUT: %s", Boolean.valueOf(z));
                if (z) {
                    getNavigationHelper().finishActivityAfterNavigation().withIntent(LoginActivity.newStartIntent(this)).startActivity();
                    return;
                }
            }
            if (Strings.notEmpty(getAnalyticsScreenTag())) {
                getAnalyticsService().reportScreenView(getAnalyticsScreenTag(), getAnalyticsScreenAttributes());
            }
            manageDeepLinkState();
            checkForAccountRestrictions();
        } catch (Exception e) {
            Ln.e(e);
        }
        checkShouldUpdateGoals();
        if (shouldDisplayAds()) {
            MoPubUtil.resume(this.moPubAdView);
        }
        AdsHelper adsHelper2 = this.adsHelper;
        if (adsHelper2 != null) {
            adsHelper2.resume(this.dfpAdsListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        this.componentDelegate.postResume();
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        LeftDrawerMenu leftDrawerMenu = this.drawerMenu;
        if (leftDrawerMenu != null) {
            leftDrawerMenu.refresh();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        if (r3.isOpen() != false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPrepareOptionsMenu(android.view.Menu r3) {
        /*
            r2 = this;
            boolean r0 = super.onPrepareOptionsMenu(r3)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            r3.clear()
            boolean r3 = r2.showAsTopLevelActivity()
            if (r3 == 0) goto L_0x001b
            com.myfitnesspal.feature.drawer.ui.view.LeftDrawerMenu r3 = r2.drawerMenu
            if (r3 == 0) goto L_0x001b
            boolean r3 = r3.isOpen()
            if (r3 != 0) goto L_0x001c
        L_0x001b:
            r1 = 1
        L_0x001c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.activity.MfpActivity.onPrepareOptionsMenu(android.view.Menu):boolean");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (showAsTopLevelActivity()) {
            getAnalyticsService().reportScreenView(Screens.DRAWER_MENU);
            this.drawerMenu.toggle(menuItem);
        } else {
            onUpPressed();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.runnerDelegate.onPause();
        this.componentDelegate.pause();
        sLastPausedTime = new Date();
        ((ActionTrackingService) this.actionTrackingService.get()).registerEvent(Events.ON_PAUSE);
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ON_PAUSE, "date", Long.toString(sLastPausedTime.getTime()));
        registerAllChildrenForBusEvents(false);
        PincodeHelper.current().activityPaused(((GlobalSettingsService) this.globalSettingsService.get()).getRequiresPinCodeOnAppEntry());
        if (shouldDisplayAds()) {
            MoPubUtil.pause(this.moPubAdView);
        }
        AdsHelper adsHelper2 = this.adsHelper;
        if (adsHelper2 != null) {
            adsHelper2.pause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.runnerDelegate.onDestroy();
        this.componentDelegate.destroy();
        MoPubView moPubView = this.moPubAdView;
        if (moPubView != null) {
            moPubView.destroy();
            this.moPubAdView.setBannerAdListener(null);
        }
        AdsHelper adsHelper2 = this.adsHelper;
        if (adsHelper2 != null) {
            adsHelper2.destroy();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.componentDelegate.attachedToWindow();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.componentDelegate.detachedFromWindow();
    }

    public boolean onSearchRequested() {
        if (getSession().getUser().isLoggedIn()) {
            getNavigationHelper().withIntent(SearchCategoryDialog.newStartIntent(this)).startActivity();
        }
        return false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.runnerDelegate.onSaveInstanceState(bundle);
        this.componentDelegate.saveInstanceState(bundle);
        Set dialogFragmentTags = this.componentDelegate.getDialogFragmentTags();
        if (CollectionUtils.notEmpty((Collection<?>) dialogFragmentTags)) {
            bundle.putStringArray(EXTRA_DIALOG_FRAGMENT_TAGS, (String[]) dialogFragmentTags.toArray(new String[0]));
        }
        StateAwareScrollView.saveScrollState(findViewById(16908290), bundle);
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(str, Fragments.SIGN_UP_GENDER_AGE_FRAGMENT_TAG)) {
            return false;
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onSignUpGenderAgeDialogPostiveListener);
        return true;
    }

    public void showDialogFragment(DialogFragment dialogFragment, String str) {
        this.componentDelegate.showDialogFragment(dialogFragment, str);
    }

    public ConfigService getConfigService() {
        return this.componentDelegate.getConfigService();
    }

    public NavigationHelper getNavigationHelper() {
        return this.componentDelegate.getNavigationHelper().withContext(this);
    }

    public AdsSettings getAdsSettings() {
        return (AdsSettings) this.adsSettings.get();
    }

    public Bus getMessageBus() {
        return this.componentDelegate.getMessageBus();
    }

    public Session getSession() {
        return this.componentDelegate.getSession();
    }

    public CountryService getCountryService() {
        return this.componentDelegate.getCountryService();
    }

    public AnalyticsService getAnalyticsService() {
        return this.componentDelegate.getAnalyticsService();
    }

    public void addBusEventHandlers(List<BusEventHandler> list) {
        list.add(new BusEventHelper(this));
    }

    public boolean isBusy() {
        return this.componentDelegate.isBusy();
    }

    public boolean isBusy(int i) {
        return this.componentDelegate.isBusy(i);
    }

    public void setBusy(boolean z) {
        this.componentDelegate.setBusy(z);
    }

    public void setBusy(int i, boolean z) {
        this.componentDelegate.setBusy(i, z);
    }

    public void postEvent(Object obj) {
        this.componentDelegate.postEvent(obj);
    }

    public void postEventAfterResume(Object obj) {
        this.componentDelegate.postEventAfterResume(obj);
    }

    public boolean shouldDisplayAds() {
        return !(((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.AdFree) == Availability.Available) && !(((LocalSettingsService) this.localSettingsService.get()).isNewUser() && getConfigService().isVariantEnabled(NewUserBannerAdsAndroid201507.NAME, "on")) && !getConfigService().isVariantEnabled(ReactivatingUserBannerAdsAndroid201507.NAME, "on") && !this.hideBannerAds;
    }

    public void forceLoadBannerAdWithCategory(@Nullable String str) {
        this.sponsoredAdCategory = str;
        AdsHelper adsHelper2 = this.adsHelper;
        if (adsHelper2 != null) {
            adsHelper2.loadAdWithCategory(str);
        }
    }

    /* access modifiers changed from: protected */
    public final BannerAdDisplayState getBannerAdDisplayState() {
        return this.bannerAdDisplayState;
    }

    /* access modifiers changed from: protected */
    public ActionTrackingService getActionTrackingService() {
        return (ActionTrackingService) this.actionTrackingService.get();
    }

    private void handleAuthFailed() {
        getSession().logoutAndNavigateToLoginActivity();
    }

    /* access modifiers changed from: private */
    public void setupBannerAds() {
        boolean shouldDisplayAds = shouldDisplayAds();
        AdUnit adUnit = getAdUnit();
        if (!shouldDisplayAds || adUnit == null) {
            hideAdsContainer();
        } else if (Strings.notEmpty(adUnit.getDfpAdUnitId())) {
            showAdsContainer();
            if (shouldAdGoThroughDfp()) {
                hideMoPubAdView();
                addDfpAdsView(adUnit.getDfpAdUnitId());
            } else {
                this.moPubAdView = (MoPubView) findById(R.id.adview);
                setupMoPubAdView(adUnit.getMoPubAdUnitId());
            }
        }
        this.bannerAdDisplayState = BannerAdDisplayState.from(shouldDisplayAds);
        onBannerAdSetupCompleted();
    }

    private void addDfpAdsView(@NonNull String str) {
        ViewGroup adsContainer = getAdsContainer();
        if (adsContainer != null) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.ad_container, null, false);
            adsContainer.removeAllViews();
            adsContainer.addView(viewGroup);
            Builder builder = new Builder(viewGroup, getAdUnit(), getAnalyticsScreenTag(), AdNetworkType.AMAZON, this.configService, this.localSettingsService, this.locationService, this.adsSettings, this.adsAnalyticsHelper, getNavigationHelper(), true, getSession().getUser().isProfileCountryUS());
            this.adsHelper = builder.build();
            String str2 = this.sponsoredAdCategory;
            if (str2 != null) {
                this.adsHelper.loadAdWithCategory(str2);
            } else {
                this.adsHelper.loadAds();
            }
        }
    }

    private void setupMoPubAdView(String str) {
        MoPubView moPubView = this.moPubAdView;
        if (moPubView != null) {
            ViewUtils.setVisible(true, moPubView);
            this.moPubAdView.setAdUnitId(str);
            this.moPubAdView.setKeywords(getAdsSettings().getKeywordString());
            this.moPubAdView.loadAd();
            this.moPubAdView.setBackgroundColor(0);
            reportBannerAdsRequested();
        }
    }

    private void reportBannerAdsRequested() {
        if (getConfigService().isVariantEnabled(BannerAdCount201602.NAME)) {
            ((MfpAnalyticsService) this.mfpAnalyticsService.get()).reportEvent(Events.BANNER_AD_REQUESTED_OLD, MapUtil.createMap("screen", getAnalyticsScreenTag()));
        }
    }

    private ViewGroup getAdsContainer() {
        int adsContainerLayoutId = getAdsContainerLayoutId();
        if (adsContainerLayoutId > 0) {
            return (ViewGroup) findById(adsContainerLayoutId);
        }
        return null;
    }

    private void showAdsContainer() {
        ViewGroup adsContainer = getAdsContainer();
        if (adsContainer != null) {
            ViewUtils.setVisible(true, adsContainer);
        }
    }

    private void hideAdsContainer() {
        ViewGroup adsContainer = getAdsContainer();
        if (adsContainer != null) {
            ViewUtils.setVisible(false, adsContainer);
        }
        hideMoPubAdView();
    }

    private void hideMoPubAdView() {
        this.moPubAdView = (MoPubView) findById(R.id.adview);
        MoPubView moPubView = this.moPubAdView;
        if (moPubView != null) {
            moPubView.destroy();
            this.moPubAdView.setBannerAdListener(null);
            ViewUtils.setGone(this.moPubAdView);
        }
    }

    private boolean shouldAdGoThroughDfp() {
        return ConfigUtils.isBannerAdsDfpRolloutOn(getConfigService());
    }

    public boolean showAsTopLevelActivity() {
        return BundleUtils.getBoolean(getIntent().getExtras(), Extras.SHOW_AS_TOP_LEVEL_ACTIVITY, false);
    }

    public void scheduleSync() {
        if (!(this instanceof TermsOfUseActivity) && !(this instanceof LoginActivity)) {
            ((SyncService) this.syncService.get()).enqueue(SyncType.Incremental);
            setBusy(32768, true);
        }
    }

    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar supportActionBar = getSupportActionBar();
            if (!shouldShowTitle()) {
                toolbar.setLogo((int) R.drawable.ic_ua_myfitnesspal_brand);
                supportActionBar.setTitle((CharSequence) "");
            } else if (getCustomToolbarLayoutResId() != 0) {
                supportActionBar.setTitle((CharSequence) "");
            }
            if (!showAsTopLevelActivity()) {
                toolbar.setNavigationIcon(getNavigationIcon());
            }
            toolbar.setNavigationOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    MfpActivity.lambda$setupToolbar$1(MfpActivity.this, view);
                }
            });
            if (!showAsTopLevelActivity() || !showLeftDrawerIfTopLevel() || findById(R.id.left_drawer) == null) {
                LeftDrawerMenu leftDrawerMenu = this.drawerMenu;
                if (leftDrawerMenu != null) {
                    leftDrawerMenu.setVisibleInToolbar(false);
                    return;
                }
                return;
            }
            if (this.drawerMenu == null) {
                this.drawerMenu = new LeftDrawerMenu(this, getToolbar());
                getLifecycle().addObserver(this.drawerMenu);
            }
            this.drawerMenu.setVisibleInToolbar(true);
        }
    }

    public static /* synthetic */ void lambda$setupToolbar$1(MfpActivity mfpActivity, View view) {
        LeftDrawerMenu leftDrawerMenu = mfpActivity.drawerMenu;
        if (leftDrawerMenu != null && leftDrawerMenu.isVisibleInToolbar()) {
            mfpActivity.drawerMenu.toggle();
        } else if (mfpActivity.hasResumed()) {
            mfpActivity.onUpPressed();
        }
    }

    public void onPreCreate(Bundle bundle) {
        if (showAsTopLevelActivity()) {
            setTheme(R.style.Mfp_Theme_Default_Toplevel);
        }
    }

    public AttachTarget getAttachTarget() {
        return AttachTarget.Window;
    }

    public <T extends RunnerLifecycleMixin> void unregisterMixin(Class<T> cls) {
        this.componentDelegate.unregisterMixin(cls);
    }

    public void registerMixin(RunnerLifecycleMixin... runnerLifecycleMixinArr) {
        this.componentDelegate.registerMixin(runnerLifecycleMixinArr);
    }

    public <T extends RunnerLifecycleMixin> T mixin(Class<T> cls) {
        return this.componentDelegate.mixin(cls);
    }

    public boolean hasResumed() {
        return this.componentDelegate.hasResumed();
    }

    public boolean isEnabled() {
        return this.componentDelegate.isEnabled();
    }

    public void onUpPressed() {
        onBackPressed();
    }

    public void onBackPressed() {
        if (!backPressed()) {
            try {
                super.onBackPressed();
            } catch (IllegalStateException e) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                if (supportFragmentManager.getBackStackEntryCount() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("back stack crash ");
                    sb.append(supportFragmentManager.getBackStackEntryAt(0).getId());
                    Ln.e(new Exception(sb.toString(), e));
                }
                throw e;
            }
        }
    }

    public boolean backPressed() {
        if (!fragmentBackStackEmpty()) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        LeftDrawerMenu leftDrawerMenu = this.drawerMenu;
        if (leftDrawerMenu == null || !leftDrawerMenu.isOpen()) {
            return false;
        }
        this.drawerMenu.close();
        return true;
    }

    public <T extends View> T findById(int i) {
        return ActivityUtils.findById(this, i);
    }

    public InputMethodManagerHelper getImmHelper() {
        return this.componentDelegate.getImmHelper();
    }

    public void addProminentActionItem(Menu menu, int i, OnClickListener onClickListener) {
        MenuItem add = menu.add(0, 5003, 0, i);
        MenuItemCompat.setActionView(add, (int) R.layout.prominent_action_item);
        MenuItemCompat.setShowAsAction(add, 2);
        TextView textView = (TextView) MenuItemCompat.getActionView(menu.findItem(5003));
        textView.setText(i);
        textView.setOnClickListener(onClickListener);
    }

    public void invalidateOptionsMenu() {
        if (hasResumed()) {
            supportInvalidateOptionsMenu();
        }
    }

    public void setTitle(int i, Object... objArr) {
        setTitle(getString(i, objArr));
    }

    public GoogleFitClient getGoogleFitClient() {
        return (GoogleFitClient) this.googleFitClient.get();
    }

    public Runner getRunner() {
        LifecycleDelegate lifecycleDelegate = this.runnerDelegate;
        if (lifecycleDelegate != null) {
            return lifecycleDelegate.runner();
        }
        return null;
    }

    public void onTaskCompleted(String str, long j, Task task, Object obj) {
        TaskRunnerUtil.postSuccess(this, getRunner(), task, str, j, obj);
    }

    public void onTaskError(String str, long j, Task task, Throwable th) {
        TaskRunnerUtil.postFailure(this, getRunner(), task, str, j, th);
    }

    public <T extends BaseViewModel> T getViewModel() {
        return this.componentDelegate.getViewModel();
    }

    public <T extends BaseViewModel> T setViewModel(T t) {
        return this.componentDelegate.setViewModel(t);
    }

    public void rebindView() {
        this.componentDelegate.rebindView();
    }

    public Glide getGlide() {
        return this.glide;
    }

    /* access modifiers changed from: protected */
    public void registerAllChildrenForBusEvents(boolean z) {
        this.componentDelegate.registerChildForBusEvents(this, z);
        this.componentDelegate.registerAllChildrenForBusEvents(z);
    }

    /* access modifiers changed from: protected */
    public AdUnitService getAdUnitService() {
        return (AdUnitService) this.adUnitService.get();
    }

    protected static <T extends Fragment> String tag(Class<T> cls) {
        return cls.getCanonicalName();
    }

    protected static String tag(Fragment fragment) {
        return fragment.getClass().getCanonicalName();
    }

    private void updatePasscodeHelperState() {
        if (this instanceof LoginActivity) {
            PincodeHelper.current().setEnteredApp(false);
            PincodeHelper.current().setPincodeSuccess(false);
            return;
        }
        PincodeHelper.current().setEnteredApp(getSession().getUser().isLoggedIn());
    }

    private boolean fragmentBackStackEmpty() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if ((supportFragmentManager != null ? supportFragmentManager.getBackStackEntryCount() : 0) <= 0) {
            return true;
        }
        return false;
    }

    private boolean showPincodeScreenIfNeeded() {
        PincodeHelper current = PincodeHelper.current();
        if (current.hasEnteredApp() && !(this instanceof PasscodeView) && !current.isPincodeSuccess() && current.isPincodeLockEnabled((GlobalSettingsService) this.globalSettingsService.get())) {
            if (current.getPincode(getSession(), this.dbConnectionManager).length() == 4) {
                getNavigationHelper().withIntent(PasscodeView.newStartIntent(this)).startActivity();
                return true;
            }
        }
        return false;
    }

    private void checkShouldUpdateGoals() {
        if (!(this instanceof UpdateGoals) && !(this instanceof RecommendGoal) && !(this instanceof EatingDisorderAdjustGoalComplete)) {
            User user = getSession().getUser();
            if (user.isLoggedIn() && user.shouldUpdateGoals()) {
                getNavigationHelper().finishActivityAfterNavigation().withIntent(UpdateGoals.newStartIntent(this)).startActivity();
                overridePendingTransition(R.anim.slide_in_right_100_short, R.anim.slide_out_left_100_short);
            }
        }
    }

    private void checkForAccountRestrictions() {
        if (!(this instanceof LoginActivity) && !(this instanceof AccountRestrictedActivity) && getSession().getUser().isUnderageOrEatingDisorder()) {
            getNavigationHelper().finishActivityAfterNavigation().withIntent(AccountRestrictedActivity.newStartIntent(this, (ApiUrlProvider) this.apiUrlProvider.get(), getSession().getUser().getUserStatus())).startActivity();
        }
    }

    private boolean isActivitySyncable() {
        return !(this instanceof LoginActivity) && !(this instanceof LegacySettingsActivity) && !(this instanceof SettingsActivity);
    }

    private void manageDeepLinkState() {
        ((DeepLinkManager) this.deepLinkManager.get()).visit(getIntent(), getClass().getName());
    }

    private void updateAppState() {
        setBusy(32768, !((SyncService) this.syncService.get()).isIdle());
        if (getActivity().getIntent().getBooleanExtra(Extras.STARTED_FROM_WIDGET_OR_NOTIFICATION, false)) {
            sLastPausedTime = new Date();
        }
        if (getSession().getUser().isLoggedIn() && isActivitySyncable()) {
            if (ExtrasUtils.getBoolean(getIntent(), Extras.APP_JUST_STARTED, false)) {
                postEventAfterResume(new AppResumedFromExtendedIdleEvent());
            } else {
                checkIfHasBeenIdleLongEnoughToGoBackHome();
            }
        }
    }

    private void checkIfHasBeenIdleLongEnoughToGoBackHome() {
        if (sLastPausedTime != null) {
            Date time = Calendar.getInstance().getTime();
            long time2 = sLastPausedTime.getTime();
            long j = 0;
            if (time2 > 0) {
                j = time.getTime() - time2;
            }
            if (((float) j) >= 1800000.0f) {
                getNavigationHelper().withIntent(HomeActivity.newStartIntent(this)).startActivity();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onBarcodeScanSuccessEvent(BarcodeScanSuccessEvent barcodeScanSuccessEvent) {
        if (Strings.notEmpty(barcodeScanSuccessEvent.getReferrer())) {
            getAnalyticsService().reportEvent(Events.BARCODE_SCAN_MATCH_AND_LOGGED, new MapUtil.Builder().put("referrer", barcodeScanSuccessEvent.getReferrer()).build());
        }
    }

    /* access modifiers changed from: private */
    public void onUpdateGoalsCompleted(UpdateGoalsCompleteEvent updateGoalsCompleteEvent) {
        getSession().getUser().setShouldUpdateGoalsAndUpdateProperty(false);
        getMessageBus().post(new StartSyncEvent());
        if (updateGoalsCompleteEvent.shouldNavigateToHome()) {
            getNavigationHelper().withIntent(HomeActivity.newStartIntent(this)).startActivity();
            overridePendingTransition(R.anim.slide_in_right_100_short, R.anim.slide_out_left_100_short);
        }
    }

    /* access modifiers changed from: protected */
    public void setHideBannerAds() {
        if (!this.hideBannerAds) {
            this.hideBannerAds = true;
            setupBannerAds();
        }
    }

    /* access modifiers changed from: protected */
    public void onHideSoftInputEvent(HideSoftInputEvent hideSoftInputEvent) {
        getImmHelper().hideSoftInput();
    }

    /* access modifiers changed from: private */
    public void onUnderageRegistrationFailureEvent() {
        if (hasResumed()) {
            getAnalyticsService().reportEvent(Events.UNDERAGE_REGISTRATION_FAILED);
            AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.underage_registration_fail)).setTitle(R.string.error)).setPositiveText(R.string.ok, this.onSignUpGenderAgeDialogPostiveListener);
            alertDialogFragment.setCancelable(false);
            showDialogFragment(alertDialogFragment, Fragments.SIGN_UP_GENDER_AGE_FRAGMENT_TAG);
        }
    }

    /* access modifiers changed from: private */
    public void onAuthFailed(AuthFailedEvent authFailedEvent) {
        if (!((AppIndexerBot) this.appIndexerBot.get()).isActive() && !REGISTRATION_AND_SYNC_ACTIVITIES.contains(getActivity().getClass())) {
            handleAuthFailed();
        }
    }

    /* access modifiers changed from: private */
    public void onBusyEvent(BusyEvent busyEvent) {
        setBusy(busyEvent.getMask(), busyEvent.isBusy());
    }

    /* access modifiers changed from: private */
    public void onRefreshAd(RefreshAdEvent refreshAdEvent) {
        this.setupBannerAdDebouncer.call(Boolean.valueOf(true));
    }

    /* access modifiers changed from: private */
    public void onSubscriptionsRefreshed(SubscriptionsRefreshed subscriptionsRefreshed) {
        this.setupBannerAdDebouncer.call();
    }

    /* access modifiers changed from: protected */
    public void onAlertEvent(AlertEvent alertEvent) {
        String strings = Strings.toString(alertEvent.getMessage());
        if (alertEvent.isToast()) {
            Toaster.showLong((Activity) this, strings);
        } else {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(strings);
        }
    }

    /* access modifiers changed from: private */
    public void onSyncFinishedEvent(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
        Ln.d("SYNC: finished, type = %s, success = %s, code = %s, message = %s", uacfScheduleFinishedInfo.getScheduleGroup(), Boolean.valueOf(uacfScheduleFinishedInfo.isSuccessful()), Integer.valueOf(uacfScheduleFinishedInfo.getStatusCode()), uacfScheduleFinishedInfo.getStatusMessage());
        switch ((SyncType) uacfScheduleFinishedInfo.getScheduleGroup()) {
            case SignIn:
            case SignUp:
            case Incremental:
                setBusy(32768, false);
                checkForAccountRestrictions();
                checkShouldUpdateGoals();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void onSearchTermTooShortEvent(SearchTermTooShortEvent searchTermTooShortEvent) {
        new MfpAlertDialogBuilder(this).setMessage((CharSequence) getString(searchTermTooShortEvent.getMinLength() == 1 ? R.string.searchTermTooShortAlertMessageSingular : R.string.searchTermTooShortAlertMessagePlural, new Object[]{Integer.valueOf(searchTermTooShortEvent.getMinLength())})).setNegativeButton((CharSequence) getString(R.string.dismiss), (DialogInterface.OnClickListener) null).setTitle((CharSequence) getString(R.string.searchTermTooShortAlertTitle)).show();
    }

    /* access modifiers changed from: private */
    public void onIncrementalSyncUnsuccessful(SyncServiceIncrementalFailedEvent syncServiceIncrementalFailedEvent) {
        int statusCode = syncServiceIncrementalFailedEvent.getStatusCode();
        if (!(statusCode == 2 || statusCode == 7)) {
            switch (statusCode) {
                case 4:
                case 5:
                    break;
                default:
                    return;
            }
        }
        if (!REGISTRATION_AND_SYNC_ACTIVITIES.contains(getClass())) {
            getSession().logoutAndNavigateToLoginActivity();
        }
    }

    /* access modifiers changed from: private */
    public void onCreateRecipe(CreateNewRecipeEvent createNewRecipeEvent) {
        RecipeAnalyticsIntentData create = RecipeAnalyticsIntentData.create(createNewRecipeEvent.getStartScreen(), ActionType.Create);
        ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportRecipeFlowStarted(create);
        if (((RecipeService) this.recipeService.get()).isRecipeParsingEnabledForCurrentLocale()) {
            CreateRecipeDialogFragment.newInstance(createNewRecipeEvent.getCategoryName(), createNewRecipeEvent.getDate(), create).show(getSupportFragmentManager(), Fragments.CREATE_RECIPE_FRAGMENT);
            return;
        }
        ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportImportRecipe(create, "manual");
        getNavigationHelper().withIntent(CreateRecipeManuallyActivity.newStartIntent(this, create)).startActivity(128);
    }

    /* access modifiers changed from: private */
    public void onWaterAddedEvent(WaterAddedEvent waterAddedEvent) {
        if (!(this instanceof Diary) && waterAddedEvent.getCups() > 0) {
            getNavigationHelper().withIntent(Diary.newStartIntentWithReferrer(this, "navigation")).startActivity();
        }
    }

    /* access modifiers changed from: private */
    public void onDialogWeightEvent(DialogWeightEvent dialogWeightEvent) {
        if (!(this instanceof ProgressActivity) && !(this instanceof Goals) && !(this instanceof UpdateGoals) && dialogWeightEvent.getWeightType() == WeightType.CURRENT) {
            new UpdateWeightProxy(this, getNavigationHelper(), getMessageBus()).updateWeightAndPromptForWarnings(dialogWeightEvent.getWeight(), FinishMode.Back, UpdateMode.Weight, new Listener() {
                public void onCancel() {
                }

                public void onNavigatedForward() {
                }

                public void onSuccess() {
                    MfpActivity.this.getNavigationHelper().withIntent(ProgressActivity.newStartIntent(MfpActivity.this.getActivity(), Measurement.WEIGHT)).startActivity();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void onNewStatusPosted() {
        postEvent(new AlertEvent(getString(R.string.new_status_message_posted)).asToast());
    }

    /* access modifiers changed from: private */
    public void onNoConnectionAvailable(NoConnectionEvent noConnectionEvent) {
        ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.internet_unavailable), getString(R.string.no_connection_available), getString(R.string.dismiss));
    }

    /* access modifiers changed from: protected */
    public void clearDrawerMenuBadge() {
        ((BackgroundJobHelper) this.backgroundServiceHelper.get()).updateInAppNotifications();
    }

    @TargetApi(26)
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        if (BuildUtil.isOreoOrHigher()) {
            reportMultiWindowStateToAnalytics(z);
        }
    }

    @TargetApi(24)
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (!BuildUtil.isOreoOrHigher()) {
            reportMultiWindowStateToAnalytics(z);
        }
    }

    private void reportMultiWindowStateToAnalytics(boolean z) {
        if (isTaskRoot()) {
            getAnalyticsService().reportEvent(z ? MULTI_WINDOW_MODE_ENTER : MULTI_WINDOW_MODE_EXIT);
        }
    }
}
