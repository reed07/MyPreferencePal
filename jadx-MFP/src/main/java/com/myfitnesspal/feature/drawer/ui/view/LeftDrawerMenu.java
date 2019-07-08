package com.myfitnesspal.feature.drawer.ui.view;

import android.app.Activity;
import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.DefaultLifecycleObserver.CC;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.appgallery.ui.OurOtherAppsActivity;
import com.myfitnesspal.feature.blog.service.BlogService;
import com.myfitnesspal.feature.blog.ui.activity.BlogActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengesActivity;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.feature.community.ui.activity.CommunityActivity;
import com.myfitnesspal.feature.consents.util.ConsentsAnalyticsHelper;
import com.myfitnesspal.feature.debug.ui.activity.AdvancedDebuggingActivity;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.service.ExternalSyncCooldown;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity;
import com.myfitnesspal.feature.friends.ui.activity.MessagesActivity;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.help.ui.activity.FeedbackActivity;
import com.myfitnesspal.feature.help.ui.activity.Help;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.nutrition.ui.activity.Nutrition;
import com.myfitnesspal.feature.payments.service.PaymentAnalyticsHelperImpl;
import com.myfitnesspal.feature.premium.event.PremiumEvents.ProductAvailabilityUpdated;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods;
import com.myfitnesspal.feature.settings.ui.activity.PrivacyCenterActivity;
import com.myfitnesspal.feature.settings.ui.activity.RemindersActivity;
import com.myfitnesspal.feature.settings.ui.activity.SettingsActivity;
import com.myfitnesspal.feature.settings.ui.activity.StepsSettings;
import com.myfitnesspal.feature.uashop.util.UAShopHelper;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.ABTest.UAShopAndroid201511;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.event.CurrentSelectedMenuItemClickedEvent;
import com.myfitnesspal.shared.event.ItemTalliesUpdatedEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.notification.InAppNotificationManager;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import javax.inject.Inject;

public class LeftDrawerMenu implements DefaultLifecycleObserver {
    private static final long BADGE_UPDATE_FREQUENCY_MILLIS = 300000;
    private static long sLastBadgeUpdateTime;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    private final Activity activity;
    /* access modifiers changed from: private */
    public LeftDrawerAdapter adapter;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<BackgroundJobHelper> backgroundJobHelper;
    @Inject
    Lazy<BlogService> blogService;
    @Inject
    Lazy<Bus> bus;
    @Inject
    Lazy<CommunityService> communityService;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<ConsentsAnalyticsHelper> consentsAnalyticsHelper;
    private CompositeDisposable disposable = new CompositeDisposable();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Inject
    Lazy<GoogleFitClient> googleFitClient;
    @Inject
    Lazy<InAppNotificationManager> inAppNotificationManager;
    private ListView list;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    /* access modifiers changed from: private */
    public Runnable mPendingNavigation;
    private View navContentContainer;
    @Inject
    NavigationHelper navigationHelper;
    @Inject
    Lazy<PremiumService> premiumService;
    private MfpImageView profileImage;
    private TextView profileUserInfo;
    private TextView profileUserNameText;
    private View profileView;
    @Inject
    Lazy<Session> session;
    private View syncButton;
    @Inject
    Lazy<SyncService> syncEngine;
    private final Toolbar toolbar;
    @Inject
    Lazy<UserSummaryService> userSummaryService;
    @Inject
    Lazy<UserWeightService> userWeightService;
    private boolean visibleInToolbar = false;
    private boolean wasPremiumAvailable = false;

    public /* synthetic */ void onCreate(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onStart(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onStart(this, lifecycleOwner);
    }

    public /* synthetic */ void onStop(@NonNull LifecycleOwner lifecycleOwner) {
        CC.$default$onStop(this, lifecycleOwner);
    }

    public static void invalidateBadges() {
        sLastBadgeUpdateTime = 0;
    }

    public LeftDrawerMenu(Activity activity2, Toolbar toolbar2) {
        this.activity = activity2;
        this.toolbar = toolbar2;
        MyFitnessPalApp.getInstance().component().inject(this);
        this.wasPremiumAvailable = ((PremiumService) this.premiumService.get()).isPremiumAvailable();
        initViews();
        initListeners();
        initAdapter();
        refresh();
        updateDrawerWidth();
    }

    public void onResume(@NonNull LifecycleOwner lifecycleOwner) {
        ((Bus) this.bus.get()).register(this);
        refreshAndUpdateProfileView();
    }

    public void onPause(@NonNull LifecycleOwner lifecycleOwner) {
        ((Bus) this.bus.get()).unregister(this);
        this.disposable.clear();
    }

    private Activity getContext() {
        return this.activity;
    }

    private Resources getResources() {
        return this.activity.getResources();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return this.drawerToggle.onOptionsItemSelected(menuItem);
    }

    public void onNewIntent() {
        refresh();
    }

    public void configurationChanged(Configuration configuration) {
        ActionBarDrawerToggle actionBarDrawerToggle = this.drawerToggle;
        if (actionBarDrawerToggle != null) {
            actionBarDrawerToggle.onConfigurationChanged(configuration);
        }
    }

    @Subscribe
    public void onProductAvailabilityUpdated(ProductAvailabilityUpdated productAvailabilityUpdated) {
        checkRefreshPremiumAvailability();
    }

    @Subscribe
    public void onItemTalliesUpdatedEvent(ItemTalliesUpdatedEvent itemTalliesUpdatedEvent) {
        refresh();
    }

    @Subscribe
    public void onSyncFinished(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
        if (uacfScheduleFinishedInfo.getScheduleGroup() == SyncType.Incremental) {
            refreshAndUpdateProfileView();
        }
    }

    public void toggle() {
        toggle(null);
    }

    public void toggle(MenuItem menuItem) {
        if (menuItem != null) {
            onOptionsItemSelected(menuItem);
        } else if (isOpen()) {
            this.drawerLayout.closeDrawer(this.navContentContainer);
        } else {
            this.drawerLayout.openDrawer(this.navContentContainer);
        }
    }

    public void close() {
        if (isOpen()) {
            this.drawerLayout.closeDrawer(this.navContentContainer);
        }
    }

    public void setVisibleInToolbar(boolean z) {
        if (this.drawerToggle != null) {
            this.visibleInToolbar = z;
            boolean z2 = !z;
            this.drawerLayout.setDrawerLockMode(z2 ? 1 : 0);
            this.drawerToggle.onDrawerStateChanged(z2);
            this.drawerToggle.setDrawerIndicatorEnabled(z);
            this.drawerToggle.syncState();
        }
    }

    public boolean isVisibleInToolbar() {
        return this.visibleInToolbar;
    }

    public boolean isOpen() {
        DrawerLayout drawerLayout2 = this.drawerLayout;
        return drawerLayout2 != null && drawerLayout2.isDrawerOpen(this.navContentContainer);
    }

    public void refresh() {
        updateSyncButton();
        updateBadges();
    }

    private void refreshAndUpdateProfileView() {
        refresh();
        updateProfileView();
    }

    private void initViews() {
        LayoutInflater from = LayoutInflater.from(getContext());
        this.list = (ListView) this.activity.findViewById(R.id.left_drawer);
        this.drawerLayout = (DrawerLayout) this.activity.findViewById(R.id.drawer_layout);
        this.navContentContainer = this.activity.findViewById(R.id.nav_content_container);
        this.syncButton = from.inflate(R.layout.sliding_menu_sync, this.list, false);
        this.profileView = from.inflate(R.layout.sliding_menu_my_profile, this.list, false);
        this.profileImage = (MfpImageView) ViewUtils.findById(this.profileView, R.id.image);
        this.profileUserNameText = (TextView) ViewUtils.findById(this.profileView, R.id.user_name);
        this.profileUserInfo = (TextView) ViewUtils.findById(this.profileView, R.id.user_info);
        this.list.addFooterView(this.syncButton);
        this.list.addHeaderView(this.profileView, new LeftDrawerItem().withType(LeftDrawerItemType.Profile), true);
    }

    private void initAdapter() {
        LeftDrawerAdapter leftDrawerAdapter = new LeftDrawerAdapter(getContext(), this.configService, this.premiumService, this.inAppNotificationManager, this.blogService, this.communityService, this.localSettingsService);
        this.adapter = leftDrawerAdapter;
        this.list.setAdapter(this.adapter);
    }

    private void initToolbar() {
        final AppCompatActivity appCompatActivity = (AppCompatActivity) getContext();
        AnonymousClass1 r1 = new ActionBarDrawerToggle(appCompatActivity, this.drawerLayout, this.toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                appCompatActivity.supportInvalidateOptionsMenu();
                syncState();
                if (LeftDrawerMenu.this.mPendingNavigation != null) {
                    LeftDrawerMenu.this.mPendingNavigation.run();
                    LeftDrawerMenu.this.mPendingNavigation = null;
                }
            }

            public void onDrawerSlide(View view, float f) {
                super.onDrawerSlide(view, f);
            }

            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                ((AnalyticsService) LeftDrawerMenu.this.analyticsService.get()).reportEvent(Events.NAV_DRAWER_VIEWED);
                LeftDrawerMenu.this.mPendingNavigation = null;
                if (!LeftDrawerMenu.this.checkRefreshPremiumAvailability()) {
                    LeftDrawerMenu.this.updateSyncButton();
                    LeftDrawerMenu.this.adapter.notifyDataSetChanged();
                }
                appCompatActivity.supportInvalidateOptionsMenu();
                syncState();
            }
        };
        this.drawerToggle = r1;
        this.drawerLayout.setDrawerListener(this.drawerToggle);
        this.drawerToggle.syncState();
    }

    private void initListeners() {
        this.syncButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                LeftDrawerMenu.lambda$initListeners$0(LeftDrawerMenu.this, view);
            }
        });
        this.list.setOnItemClickListener(new OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                LeftDrawerMenu.lambda$initListeners$2(LeftDrawerMenu.this, adapterView, view, i, j);
            }
        });
        initToolbar();
    }

    public static /* synthetic */ void lambda$initListeners$0(LeftDrawerMenu leftDrawerMenu, View view) {
        ((GoogleFitClient) leftDrawerMenu.googleFitClient.get()).resetTimePointerForSyncExercises();
        ExternalSyncCooldown.clear();
        ((Bus) leftDrawerMenu.bus.get()).post(new StartSyncEvent());
        ((AnalyticsService) leftDrawerMenu.analyticsService.get()).reportEvent(Events.NAV_DRAWER_SYNC_CLICK);
        leftDrawerMenu.syncButton.setEnabled(false);
    }

    public static /* synthetic */ void lambda$initListeners$2(LeftDrawerMenu leftDrawerMenu, AdapterView adapterView, View view, int i, long j) {
        if (leftDrawerMenu.adapter.positionIsSelectable(i - leftDrawerMenu.list.getHeaderViewsCount())) {
            leftDrawerMenu.mPendingNavigation = new Runnable(adapterView, i) {
                private final /* synthetic */ AdapterView f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    LeftDrawerMenu.this.onItemSelected((LeftDrawerItem) this.f$1.getItemAtPosition(this.f$2));
                }
            };
        } else {
            ((Bus) leftDrawerMenu.bus.get()).post(new CurrentSelectedMenuItemClickedEvent());
        }
        leftDrawerMenu.close();
    }

    /* access modifiers changed from: private */
    public boolean checkRefreshPremiumAvailability() {
        boolean isPremiumAvailable = ((PremiumService) this.premiumService.get()).isPremiumAvailable();
        if (this.wasPremiumAvailable == isPremiumAvailable) {
            return false;
        }
        this.adapter.refresh();
        refresh();
        this.wasPremiumAvailable = isPremiumAvailable;
        return true;
    }

    private void updateDrawerWidth() {
        LayoutParams layoutParams = (LayoutParams) this.navContentContainer.getLayoutParams();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        layoutParams.width = Math.min(i, i2) - getResources().getDimensionPixelSize(R.dimen.nav_drawer_spacing);
        this.navContentContainer.invalidate();
    }

    /* access modifiers changed from: private */
    public void updateSyncButton() {
        View view = this.syncButton;
        if (view != null) {
            view.setEnabled(((SyncService) this.syncEngine.get()).isIdle());
            TextViewUtils.setText((TextView) ViewUtils.findById(this.syncButton, R.id.subtext), String.format("%s %s", new Object[]{getResources().getString(R.string.nav_drawer_sync_last), DateTimeUtils.formatHumanReadableTime(getContext(), ((SyncService) this.syncEngine.get()).getLastSyncDate())}));
        }
    }

    /* access modifiers changed from: private */
    public void onItemSelected(LeftDrawerItem leftDrawerItem) {
        String str;
        Activity context = getContext();
        HashMap hashMap = new HashMap();
        switch (leftDrawerItem.getType()) {
            case AppGallery:
                navigateTo(AppGalleryActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_APP_GALLERY_CLICK;
                break;
            case Steps:
                navigateTo(StepsSettings.newStartIntent(context));
                str = Events.NAV_DRAWER_STEPS_CLICK;
                break;
            case Diary:
                ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent("channel", "referrer", "navigation");
                navigateTo(Diary.newStartIntentWithReferrer(context, "navigation"));
                str = Events.NAV_DRAWER_DIARY_CLICK;
                break;
            case Friends:
                navigateTo(FriendsActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_FRIENDS_CLICK;
                break;
            case Messages:
                navigateTo(MessagesActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_MESSAGES_CLICK;
                break;
            case Home:
                navigateTo(HomeActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_HOME_CLICK;
                break;
            case Profile:
                User user = ((Session) this.session.get()).getUser();
                navigateTo(ProfileView.newStartIntent(context, user.getUsername(), user.getUid()), 36);
                str = Events.NAV_DRAWER_PROFILE_CLICK;
                break;
            case Settings:
                navigateTo(SettingsActivity.newStartIntent((ConfigService) this.configService.get(), context));
                str = Events.NAV_DRAWER_SETTINGS_CLICK;
                break;
            case Reminders:
                navigateTo(RemindersActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_REMINDERS_CLICK;
                break;
            case Progress:
                navigateTo(ProgressActivity.newStartIntent(context, Measurement.WEIGHT));
                str = Events.NAV_DRAWER_WEIGHT_CLICK;
                break;
            case Nutrition:
                navigateTo(Nutrition.newStartIntent(context));
                str = Events.NAV_DRAWER_NUTRITION_CLICK;
                break;
            case RecipesAndFoods:
                ((ActionTrackingService) this.actionTrackingService.get()).registerEvent("recipe_importer", "channel", "navigation");
                navigateTo(RecipesAndFoods.newStartIntent(context));
                str = Events.NAV_DRAWER_RECIPES_AND_FOODS_CLICK;
                break;
            case Help:
                navigateTo(Help.newStartIntent(context));
                str = Events.NAV_DRAWER_HELP_CLICK;
                break;
            case Goals:
                navigateTo(Goals.newStartIntent(context));
                str = Events.NAV_DRAWER_GOALS_CLICK;
                break;
            case DebugMenu:
                navigateTo(AdvancedDebuggingActivity.newStartIntent(context));
                str = null;
                break;
            case Blog:
                navigateTo(BlogActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_BLOG_CLICK;
                break;
            case Community:
                navigateTo(CommunityActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_FORUM_CLICK;
                break;
            case Premium:
                str = premiumItemClicked(context);
                break;
            case Challenges:
                navigateTo(ChallengesActivity.newStartIntent(context), RequestCodes.CHALLENGES);
                str = Events.NAV_DRAWER_CHALLENGES_CLICK;
                hashMap.put(Attributes.BADGE_COUNT, Strings.toString(Integer.valueOf(((LocalSettingsService) this.localSettingsService.get()).getUnseenNewChallenges())));
                break;
            case UaApps:
                navigateTo(OurOtherAppsActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_OUR_OTHER_APPS_CLICK;
                break;
            case PrivacyCenter:
                ((ConsentsAnalyticsHelper) this.consentsAnalyticsHelper.get()).reportPrivacyCenterSee();
                navigateTo(PrivacyCenterActivity.newStartIntent(context));
                str = Events.NAV_DRAWER_PRIVACY_CENTER_CLICK;
                break;
            case UaShop:
                UAShopHelper.loadUAShopUrl(getContext(), this.session, this.configService);
                str = Events.NAV_DRAWER_UA_SHOP_CLICK;
                hashMap.put("variant", ((ConfigService) this.configService.get()).getVariant(UAShopAndroid201511.NAME));
                break;
            case BetaFeedback:
                navigateTo(FeedbackActivity.newStartIntentForBetaFeedback(context));
                str = Events.NAV_DRAWER_BETA_FEEDBACK_CLICK;
                break;
            default:
                throw new IllegalStateException();
        }
        if (Strings.notEmpty(str)) {
            ((AnalyticsService) this.analyticsService.get()).reportEvent(str, (Map<String, String>) hashMap);
        }
    }

    private String premiumItemClicked(Context context) {
        if (((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
            String str = Events.NAV_DRAWER_PREMIUM_CONTENT_CLICK;
            navigateTo(PremiumUpsellActivity.newStartIntent(context, PaymentAnalyticsHelperImpl.ANALYTICS_MENU_DRAWER, UpsellDisplayMode.FeatureScreen));
            return str;
        }
        String str2 = Events.NAV_DRAWER_PREMIUM_UPSELL_CLICK;
        navigateTo(PremiumUpsellActivity.newStartIntent(context, PaymentAnalyticsHelperImpl.ANALYTICS_MENU_DRAWER));
        return str2;
    }

    private void navigateTo(Intent intent, int i) {
        Activity context = getContext();
        this.navigationHelper.withContext(context).withClearTopAndSingleTop().withIntent(context.getIntent()).startActivity();
        if (!HomeActivity.class.equals(intent.getComponent().getClass())) {
            this.navigationHelper.asTopLevelActivity().withIntent(intent).withAnimations(-1, R.anim.activity_fade_out).startActivity(i);
        }
    }

    private void navigateTo(Intent intent) {
        navigateTo(intent, -1);
    }

    private void updateBadges() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - sLastBadgeUpdateTime > BADGE_UPDATE_FREQUENCY_MILLIS) {
            ((BackgroundJobHelper) this.backgroundJobHelper.get()).updateInAppNotifications();
            sLastBadgeUpdateTime = currentTimeMillis;
        }
    }

    private void updateProfileView() {
        if (this.profileView != null) {
            MiniUserInfo forCurrentUser = MiniUserInfo.forCurrentUser((Session) this.session.get());
            if (forCurrentUser != null) {
                ((UserSummaryService) this.userSummaryService.get()).fetchUserSummaryAsync(forCurrentUser.getUsername(), forCurrentUser.getUid(), new Function1() {
                    public final void execute(Object obj) {
                        LeftDrawerMenu.lambda$updateProfileView$3(LeftDrawerMenu.this, (UserSummaryObject) obj);
                    }
                }, new ApiErrorCallback() {
                    public final void execute(Object obj) {
                        LeftDrawerMenu.lambda$updateProfileView$4(LeftDrawerMenu.this, (ApiException) obj);
                    }
                });
            } else {
                setStreakLostVisible(false);
            }
            this.disposable.add(Single.fromCallable(new Callable() {
                public final Object call() {
                    return LeftDrawerMenu.lambda$updateProfileView$5(LeftDrawerMenu.this);
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Consumer<? super T>) new Consumer() {
                public final void accept(Object obj) {
                    LeftDrawerMenu.lambda$updateProfileView$6(LeftDrawerMenu.this, (Tuple2) obj);
                }
            }));
        }
    }

    public static /* synthetic */ void lambda$updateProfileView$3(LeftDrawerMenu leftDrawerMenu, UserSummaryObject userSummaryObject) throws RuntimeException {
        leftDrawerMenu.setStreakLostVisible(true);
        if (userSummaryObject != null) {
            leftDrawerMenu.setUserInfo(userSummaryObject);
        } else {
            ViewUtils.setGone(leftDrawerMenu.profileUserInfo);
        }
    }

    public static /* synthetic */ void lambda$updateProfileView$4(LeftDrawerMenu leftDrawerMenu, ApiException apiException) throws RuntimeException {
        Ln.w(apiException, "Could not load the user summary into the profile.", new Object[0]);
        leftDrawerMenu.setStreakLostVisible(false);
    }

    public static /* synthetic */ Tuple2 lambda$updateProfileView$5(LeftDrawerMenu leftDrawerMenu) throws Exception {
        User user = ((Session) leftDrawerMenu.session.get()).getUser();
        return Tuple.create(user.hasMasterDatabaseId() ? user.getUsername() : null, ((Session) leftDrawerMenu.session.get()).getUser().getImageUrl());
    }

    public static /* synthetic */ void lambda$updateProfileView$6(LeftDrawerMenu leftDrawerMenu, Tuple2 tuple2) throws Exception {
        if (leftDrawerMenu.profileView != null) {
            String str = (String) tuple2.getItem1();
            TextView textView = leftDrawerMenu.profileUserNameText;
            if (str == null) {
                str = leftDrawerMenu.getContext().getString(R.string.local_account);
            }
            textView.setText(str);
            leftDrawerMenu.profileImage.setUrl((String) tuple2.getItem2());
        }
    }

    private void setUserInfo(UserSummaryObject userSummaryObject) {
        String userInfoStreakString = getUserInfoStreakString(userSummaryObject);
        String userInfoWeightChangeString = getUserInfoWeightChangeString(userSummaryObject);
        this.profileUserInfo.setText(String.format("%s, %s", new Object[]{userInfoStreakString, userInfoWeightChangeString}));
    }

    private String getUserInfoStreakString(UserSummaryObject userSummaryObject) {
        Resources resources;
        int i;
        int loginStreak = userSummaryObject.getLoginStreak();
        String str = "%1$s %2$s";
        Object[] objArr = new Object[2];
        objArr[0] = Strings.toString(Integer.valueOf(loginStreak));
        if (loginStreak > 1) {
            resources = getResources();
            i = R.string.days;
        } else {
            resources = getResources();
            i = R.string.day;
        }
        objArr[1] = resources.getString(i);
        String format = String.format(str, objArr);
        return getResources().getString(R.string.days_streak, new Object[]{format});
    }

    private String getUserInfoWeightChangeString(UserSummaryObject userSummaryObject) {
        float f;
        int i;
        boolean z = ((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeight() >= BitmapDescriptorFactory.HUE_RED;
        float poundsLost = userSummaryObject.getPoundsLost();
        if (z) {
            f = Math.max(BitmapDescriptorFactory.HUE_RED, poundsLost);
            i = R.string.progress_photos_congrats_subheader_loss;
        } else {
            f = Math.min(BitmapDescriptorFactory.HUE_RED, poundsLost);
            i = R.string.progress_photos_congrats_subheader_gain;
        }
        return getContext().getString(i, new Object[]{((UserWeightService) this.userWeightService.get()).getDisplayableString(WeightType.JUST_WEIGHT, Math.abs(f))});
    }

    private void setStreakLostVisible(boolean z) {
        ViewUtils.setVisible(z, this.profileUserInfo);
    }
}
