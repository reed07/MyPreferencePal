package com.myfitnesspal.feature.home.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.Observable;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.OnChildAttachStateChangeListener;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.login.Welcome;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdManager;
import com.myfitnesspal.feature.achievementinterstitialad.ui.AchievementInterstitialAd;
import com.myfitnesspal.feature.achievementinterstitialad.ui.OnCloseAdListener;
import com.myfitnesspal.feature.achievementinterstitialad.ui.OnShowAdListener;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.service.FetchChallengeSummaryTask;
import com.myfitnesspal.feature.challenges.service.FetchChallengeSummaryTask.CompletedEvent;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.consents.model.AdConsentsViewModel.Mode;
import com.myfitnesspal.feature.consents.ui.activity.AdConsentsActivity;
import com.myfitnesspal.feature.dashboard.ui.view.NutrientDashboardRenderer;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.feature.home.event.ChangeBannerAdVisibilityEvent;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.DailySummaryHeader;
import com.myfitnesspal.feature.home.model.EmptyListDisplayItem;
import com.myfitnesspal.feature.home.model.EmptyListDisplayItem.ItemType;
import com.myfitnesspal.feature.home.model.LoadingNewsFeedItem;
import com.myfitnesspal.feature.home.model.NewStatusItem;
import com.myfitnesspal.feature.home.model.NewsFeedDisplayActivityName;
import com.myfitnesspal.feature.home.model.NewsFeedRequestData.FetchType;
import com.myfitnesspal.feature.home.model.VideoEntry;
import com.myfitnesspal.feature.home.service.AppRatingService;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.task.ChangePasswordTask;
import com.myfitnesspal.feature.home.task.ShouldShowRateDialogTask;
import com.myfitnesspal.feature.home.ui.adapter.NativeAdsAdapter;
import com.myfitnesspal.feature.home.ui.adapter.factory.NativeAdsAdapterFactory;
import com.myfitnesspal.feature.home.ui.dialog.AppRatingDialogFragment;
import com.myfitnesspal.feature.home.ui.view.VideoViewHolder;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel.NewsFeedType;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel.Property;
import com.myfitnesspal.feature.home.util.NewsFeedCardUtils;
import com.myfitnesspal.feature.home.util.NewsFeedItemActionUtils;
import com.myfitnesspal.feature.home.util.NotifBellHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.notificationinbox.service.GetPendingCountTask;
import com.myfitnesspal.feature.notificationinbox.ui.activity.NotificationInboxActivity;
import com.myfitnesspal.feature.onboarding.service.PremiumOnboardingAnalyticsHelper;
import com.myfitnesspal.feature.premium.event.PremiumEvents.SubscriptionsRefreshed;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumInterstitialActivity;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.premium.util.MenuItemLookup;
import com.myfitnesspal.feature.premium.util.PremiumCrownUtil;
import com.myfitnesspal.feature.progress.ui.activity.AddWeightActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.feature.registration.ui.activity.UpdatedTermsActivity;
import com.myfitnesspal.feature.settings.model.AdsSettings;
import com.myfitnesspal.feature.settings.model.ChangePasswordViewModel;
import com.myfitnesspal.feature.settings.ui.activity.ChangePasswordActivity;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.feature.video.util.VideoUtil;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.deeplink.DeepLinkManager;
import com.myfitnesspal.shared.event.HomeNewsFragmentResumedEvent;
import com.myfitnesspal.shared.event.NewStatusPostedEvent;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.service.ads.AdUnitService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.location.LocationService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.ui.activity.FloatingButtonMixin;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.activity.MfpFloatingButtonFragment;
import com.myfitnesspal.shared.ui.activity.impl.FullScreenWebView;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.myfitnesspal.shared.ui.fragment.HideBannerAdsListener;
import com.myfitnesspal.shared.ui.fragment.Refreshable;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlightSequence;
import com.myfitnesspal.shared.ui.widget.CatchIOOBLinearLayoutManager;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.GenericWebViewUtil;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class HomeFragment extends MfpFloatingButtonFragment implements OnShowAdListener, Refreshable {
    public static final String ACTION_ADD_ENTRY = "com.myfitnesspal.action.ADD_ENTRY";
    private static final String DIALOG_FRAGMENT_TAG = "image_card_action_dialog";
    public static final String EXTRA_FLOW_ID = "flow_id";
    public static final String EXTRA_PREMIUM_CROWN_TOOLTIP_ENABLED = "extra_premium_crown_tooltip_enabled";
    public static final String EXTRA_SHOW_DIARY_ADD_DEEPLINK_SELECT_MEAL_ON_RESUME = "show_diary_add_deeplink_select_meal_on_resume";
    public static final String EXTRA_SHOW_FAB_ON_RESUME = "show_fab_on_resume";
    private static final int FAB_AUTO_OPEN_DELAY_MS = 500;
    private static final String HERO_CARD_TYPES_REPORTED = "hero_card_types_reported";
    private static final String NEWS_FEED_ENTRY_IDS_REPORTED = "news_feed_entry_ids_reported";
    private static final String NEWS_FEED_LIST_CURRENT_STATE = "news_feed_list_current_state";
    private static final int RECYCLER_VIEW_ITEM_VIEW_CACHE_SIZE = 9;
    private static final int REFRESH_DELAY_MS = 1000;
    @Inject
    Lazy<AchievementAdManager> achievementAdManager;
    @Inject
    AchievementInterstitialAd achievementInterstitialAd;
    @Inject
    Lazy<AdUnitService> adUnitService;
    @Inject
    Lazy<AdsSettings> adsSettings;
    @Inject
    Lazy<AppRatingService> appRatingService;
    private NotifBellHelper bellHelper = new NotifBellHelper();
    @Inject
    Lazy<ChallengesService> challengesService;
    @Inject
    Lazy<ConfigService> configService;
    private FeatureHighlightSequence crownTooltip;
    private boolean crownTooltipEnabled;
    @Inject
    Lazy<DeepLinkManager> deepLinkManager;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<UacfEmailVerificationManager> emailVerificationManager;
    private String flowId;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    private Handler handler = new Handler();
    @Inject
    Lazy<ImageService> imageService;
    /* access modifiers changed from: private */
    public boolean isVideoAutoPlayOn = false;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocationService> locationService;
    private MenuItemLookup menuItemLookup;
    @Inject
    Lazy<MessageService> messageService;
    private NativeAdsAdapterFactory nativeAdsAdapterFactory;
    /* access modifiers changed from: private */
    public NativeAdsAdapter newsFeedAdpter;
    @Inject
    Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    private NewsFeedItemActionListener newsFeedItemActionListener = new NewsFeedItemActionListener() {
        public void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
            HomeFragment.this.newsFeedAdpter.onCardCloseClick(mfpNewsFeedActivityEntry);
        }

        public void onLikeClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, String str, String str2, Function1<MfpNewsFeedActivityEntry> function1, Function1<MfpNewsFeedActivityEntry> function12) {
            if (HomeFragment.this.getActivity() != null) {
                NewsFeedItemActionUtils.likeClick((MfpActivity) HomeFragment.this.getActivity(), HomeFragment.this.newsFeedService, HomeFragment.this.getMessageBus(), mfpNewsFeedActivityEntry, str, str2, function1, function12);
            }
        }

        public void onImageCardActionClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, boolean z) {
            NewsFeedItemActionUtils.imageCardActionClick(this, HomeFragment.this.getFragmentManager(), mfpNewsFeedActivityEntry, HomeFragment.DIALOG_FRAGMENT_TAG, z);
        }

        public void onCommentLikeCountClick(String str) {
            throw new UacfNotImplementedException();
        }

        public void onCommentLikeClick(String str, boolean z, Function1<Integer> function1) {
            throw new UacfNotImplementedException();
        }

        public void onCommentLongClick(String str, int i) {
            throw new UacfNotImplementedException();
        }

        public void onViewMealClick(String str, String str2, String str3, String str4) {
            if (HomeFragment.this.getActivity() != null) {
                NewsFeedItemActionUtils.viewMealFoodClick(HomeFragment.this.getNavigationHelper(), HomeFragment.this.getActivity(), str, str2, str3, str4, HomeFragment.this.getSession());
            }
        }

        public void onUpdateStatusClick() {
            if (HomeFragment.this.getActivity() != null) {
                NewsFeedItemActionUtils.updateStatusClick(HomeFragment.this.getNavigationHelper(), HomeFragment.this.getActivity(), HomeFragment.this);
            }
        }

        public void onCameraClick() {
            if (HomeFragment.this.getActivity() != null) {
                NewsFeedItemActionUtils.statusCameraClick(HomeFragment.this.getNavigationHelper(), HomeFragment.this.getActivity());
            }
        }
    };
    @BindView(2131363141)
    RecyclerView newsFeedRecyclerView;
    private Parcelable newsFeedSavedState;
    @Inject
    Lazy<NewsFeedService> newsFeedService;
    private UacfNotificationSdk notificationSdk;
    @Inject
    Lazy<NutrientDashboardRenderer> nutrientDashboardRenderer;
    @Inject
    Lazy<PremiumOnboardingAnalyticsHelper> premiumOnboardingAnalyticsHelper;
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363426)
    SwipeRefreshLayout refreshLayout;
    private long startTime;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    @Inject
    Lazy<UserPropertiesService> userPropertiesService;
    private final Set<VideoEntry> videoPlayingSet = new HashSet();
    /* access modifiers changed from: private */
    public NewsFeedViewModel viewModel;

    public static HomeFragment newInstance(@NonNull String str) {
        return newInstance(str, true);
    }

    public static HomeFragment newInstance(@NonNull String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("flow_id", str);
        bundle.putBoolean(EXTRA_PREMIUM_CROWN_TOOLTIP_ENABLED, z);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        this.flowId = BundleUtils.getString(getArguments(), "flow_id", UUID.randomUUID().toString());
        this.crownTooltipEnabled = BundleUtils.getBoolean(getArguments(), EXTRA_PREMIUM_CROWN_TOOLTIP_ENABLED, true);
        ((BottomBarMixin) ((MfpActivity) getActivity()).mixin(BottomBarMixin.class)).setFloatingButtonMixin((FloatingButtonMixin) mixin(FloatingButtonMixin.class));
        this.notificationSdk = new UacfNotificationSdkFactory().newSdkInstance();
        Intent intent = intent();
        if (Strings.equals(intent.getAction(), ACTION_ADD_ENTRY)) {
            intent.putExtra(EXTRA_SHOW_FAB_ON_RESUME, true);
        }
        if (((DeepLinkManager) this.deepLinkManager.get()).hasDeepLinkDestination()) {
            ((DeepLinkManager) this.deepLinkManager.get()).navigateToDeepLink();
        }
        if (ExtrasUtils.getBoolean(intent, Extras.APP_JUST_STARTED, false)) {
            new ShouldShowRateDialogTask(this.appRatingService).setCacheMode(CacheMode.CacheAlways).run(getRunner());
            intent.removeExtra(Extras.APP_JUST_STARTED);
        }
        if (ExtrasUtils.getBoolean(intent, "image_reported", false)) {
            showImageReportedSnackbar();
        }
        if (bundle == null) {
            showUpgradeAlertIfNecessary();
            showChangePasswordIfNecessary();
        }
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.home_fragment, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        if (bundle != null) {
            ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).addHeroCardDisplayedEventSentForTypes(BundleUtils.getStringArrayList(bundle, HERO_CARD_TYPES_REPORTED));
            ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).addNewsFeedEntryViewedEventSentForId(BundleUtils.getStringArrayList(bundle, NEWS_FEED_ENTRY_IDS_REPORTED));
        }
        initViewModel();
        setupAdapters();
        setupRecyclerView();
        setupSwipeRefreshLayout();
        if (bundle != null) {
            this.newsFeedSavedState = bundle.getParcelable(NEWS_FEED_LIST_CURRENT_STATE);
        }
        this.newsFeedAdpter.onCreate();
        this.viewModel.onFragmentCreated();
        setupMenuItemLookup();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.viewModel.onDestroyView();
        FeatureHighlightSequence featureHighlightSequence = this.crownTooltip;
        if (featureHighlightSequence != null) {
            featureHighlightSequence.dismiss();
            this.crownTooltip = null;
        }
    }

    public void onStart() {
        super.onStart();
        this.startTime = System.nanoTime();
        this.newsFeedAdpter.setStopped(false);
        showNecessaryInterstitial();
    }

    public void onResume() {
        super.onResume();
        hideBanners();
        resetActiveDateToToday();
        if (!logOutIfSessionInvalid()) {
            checkCurrentAdapter();
            refresh(FetchType.NewItems);
            this.newsFeedAdpter.onResume(this);
            fetchPendingNotifsCount();
            fetchUnseenAndJoinedChallengeCount();
            postEvent(new HomeNewsFragmentResumedEvent());
            Welcome.setHomeViewed(true);
            this.isVideoAutoPlayOn = VideoUtil.isVideoAutoPlayOn(this.userApplicationSettingsService, this.configService);
            showTooltip();
        }
    }

    public void onPostResume() {
        super.onPostResume();
        Intent intent = intent();
        if (ExtrasUtils.getBoolean(intent, EXTRA_SHOW_FAB_ON_RESUME, false)) {
            intent.putExtra(EXTRA_SHOW_FAB_ON_RESUME, false);
            openFloatingButton(500);
        } else if (ExtrasUtils.getBoolean(intent(), EXTRA_SHOW_DIARY_ADD_DEEPLINK_SELECT_MEAL_ON_RESUME, false)) {
            intent.putExtra(EXTRA_SHOW_DIARY_ADD_DEEPLINK_SELECT_MEAL_ON_RESUME, false);
            onDiaryAddDeeplinkSelectMeal();
        }
    }

    public void onPause() {
        super.onPause();
        this.newsFeedAdpter.onPause();
    }

    public void onStop() {
        super.onStop();
        this.bellHelper.setHasNotifIconBeenAnimated(false);
        this.newsFeedAdpter.setStopped(true);
        ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportNewsfeedPageExit(this.flowId, this.startTime);
        if (this.isVideoAutoPlayOn) {
            stopAllVideos();
        }
    }

    private void stopAllVideos() {
        CatchIOOBLinearLayoutManager catchIOOBLinearLayoutManager = (CatchIOOBLinearLayoutManager) this.newsFeedRecyclerView.getLayoutManager();
        if (catchIOOBLinearLayoutManager != null) {
            int findLastVisibleItemPosition = catchIOOBLinearLayoutManager.findLastVisibleItemPosition();
            for (int findFirstVisibleItemPosition = catchIOOBLinearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                stopVideo(catchIOOBLinearLayoutManager.findViewByPosition(findFirstVisibleItemPosition));
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.newsFeedAdpter.destroy();
    }

    private boolean isCrownTooltipEnabled() {
        return this.crownTooltipEnabled && ((PremiumService) this.premiumService.get()).isPremiumSubscribed() && !((LocalSettingsService) this.localSettingsService.get()).isPremiumCrownTooltipShown() && ConfigUtils.isPremiumCrownHeaderEnabled((ConfigService) this.configService.get()) && ConfigUtils.isPremiumOnboardingEnabled((ConfigService) this.configService.get());
    }

    private void setupMenuItemLookup() {
        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            this.menuItemLookup = new MenuItemLookup(R.id.action_premium_features, toolbar);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        if (getActivity() != null) {
            menu.clear();
            preparePremiumCrownMenuItem(menu);
            prepareBellMenuItem(menu);
        }
    }

    private void prepareBellMenuItem(Menu menu) {
        FragmentActivity activity = getActivity();
        this.bellHelper.inflateParent(LayoutInflater.from(activity), getToolbar());
        this.bellHelper.hideCount();
        MenuItem actionView = menu.add(0, R.id.action_notifications, 1, R.string.notifications).setEnabled(true).setActionView(this.bellHelper.getNotifView());
        this.bellHelper.setIcon();
        this.bellHelper.getNotifView().setOnClickListener(new OnClickListener(actionView) {
            private final /* synthetic */ MenuItem f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                HomeFragment.this.onOptionsItemSelected(this.f$1);
            }
        });
        MenuItemCompat.setShowAsAction(actionView, 2);
        this.bellHelper.refreshNotifIcon(activity, getConfigService());
    }

    private void preparePremiumCrownMenuItem(Menu menu) {
        if (ConfigUtils.isPremiumCrownHeaderEnabled(getConfigService()) && ((PremiumService) this.premiumService.get()).isPremiumSubscribed()) {
            menu.add(0, R.id.action_premium_features, 0, R.string.premium_features).setEnabled(true).setIcon(R.drawable.ic_premium_yellow_24_dp).setShowAsAction(2);
        }
    }

    private void showTooltip() {
        MenuItemLookup menuItemLookup2 = this.menuItemLookup;
        if (menuItemLookup2 != null) {
            menuItemLookup2.observe(new kotlin.jvm.functions.Function1() {
                public final Object invoke(Object obj) {
                    return HomeFragment.this.showTooltip((View) obj);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showTooltip(View view) {
        FragmentActivity activity = getActivity();
        if (activity != null && view != null && isCrownTooltipEnabled()) {
            $$Lambda$HomeFragment$XceTQsscL0l0aIdYEr4mX2ISE r1 = new Function0() {
                public final Object invoke() {
                    return HomeFragment.lambda$showTooltip$2(HomeFragment.this);
                }
            };
            FeatureHighlightSequence featureHighlightSequence = this.crownTooltip;
            if (featureHighlightSequence != null) {
                featureHighlightSequence.dismiss();
            }
            ((PremiumOnboardingAnalyticsHelper) this.premiumOnboardingAnalyticsHelper.get()).tooltipDisplayed();
            this.crownTooltip = PremiumCrownUtil.prepareTooltip(activity, view, r1, r1);
            this.crownTooltip.start();
        }
    }

    public static /* synthetic */ Unit lambda$showTooltip$2(HomeFragment homeFragment) {
        ((LocalSettingsService) homeFragment.localSettingsService.get()).setPremiumCrownTooltipShown(true);
        homeFragment.crownTooltip = null;
        return null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        boolean z = true;
        if (itemId == R.id.action_notifications) {
            getNavigationHelper().withIntent(NotificationInboxActivity.newStartIntent(getActivity(), getConfigService())).startActivity();
        } else if (itemId != R.id.action_premium_features) {
            z = super.onOptionsItemSelected(menuItem);
        } else {
            ((PremiumOnboardingAnalyticsHelper) this.premiumOnboardingAnalyticsHelper.get()).crownTapped();
            getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) getActivity(), (String) null, UpsellDisplayMode.FeatureScreen)).startActivity();
        }
        getImmHelper().hideSoftInput();
        return z;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList(HERO_CARD_TYPES_REPORTED, new ArrayList(((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).getHeroCardDisplayedEventSentForType()));
        bundle.putStringArrayList(NEWS_FEED_ENTRY_IDS_REPORTED, new ArrayList(((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).getNewsFeedEntryViewedEventSentForId()));
        RecyclerView recyclerView = this.newsFeedRecyclerView;
        if (recyclerView != null && recyclerView.getLayoutManager() != null) {
            bundle.putParcelable(NEWS_FEED_LIST_CURRENT_STATE, this.newsFeedRecyclerView.getLayoutManager().onSaveInstanceState());
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 25) {
            if (i != 105) {
                if (i != 162) {
                    switch (i) {
                        case 34:
                        case 35:
                            break;
                        default:
                            return;
                    }
                } else if (i2 == -1) {
                    this.newsFeedAdpter.resetDailySummary();
                    return;
                } else {
                    return;
                }
            }
            if (i2 == -1) {
                refresh(FetchType.NewItems);
            }
        } else if (i2 == -1 && intent != null && intent.getBooleanExtra(AddWeightActivity.EXTRA_WEIGHT_UPDATED, false)) {
            getNavigationHelper().withIntent(ProgressActivity.newStartIntent(getActivity(), null)).startActivity();
        }
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        SwipeRefreshLayout swipeRefreshLayout = this.refreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        if (i == Property.NEWS_FEED_FETCHED || i == Property.NEWS_FEED_FETCH_FAILED) {
            NewsFeedViewModel newsFeedViewModel = this.viewModel;
            if (newsFeedViewModel != null) {
                addListToAdapter(newsFeedViewModel.getNewsFeedEntries(), i == Property.NEWS_FEED_FETCHED ? ItemType.EmptyTimeline : ItemType.ErrorFetchingTimeline);
            }
        }
    }

    public void onNewIntent(Intent intent) {
        if (ExtrasUtils.getBoolean(intent, "image_reported", false)) {
            showImageReportedSnackbar();
            refresh();
        }
    }

    @Subscribe
    public void onSyncFinished(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
        if (uacfScheduleFinishedInfo.isSuccessful() && uacfScheduleFinishedInfo.getScheduleGroup() == SyncType.Incremental) {
            fetchPendingNotifsCount();
        }
    }

    @Subscribe
    public void onSubscriptionsRefreshed(SubscriptionsRefreshed subscriptionsRefreshed) {
        checkCurrentAdapter();
    }

    private void checkCurrentAdapter() {
        setupAdapters();
        this.newsFeedAdpter.setNewsFeedItemActionListener(this.newsFeedItemActionListener);
        if (!this.newsFeedAdpter.getClass().getName().equals(this.newsFeedRecyclerView.getAdapter().getClass().getName())) {
            this.newsFeedRecyclerView.setAdapter(this.newsFeedAdpter);
        }
    }

    @Subscribe
    public void onFetchChallengeSummaryTask(CompletedEvent completedEvent) {
        if (completedEvent.getRunnerId() != getRunner().getId() || completedEvent.getFailure() != null) {
            return;
        }
        if (Strings.equals(completedEvent.getType(), Challenges.USER_STATUS_ELIGIBLE)) {
            ChallengesUtil.setNewUnseenChallengesStats((List) completedEvent.getResult(), this.localSettingsService);
        } else if (Strings.equals(completedEvent.getType(), "joined")) {
            ChallengesUtil.setTotalJoinedChallengesStats((List) completedEvent.getResult(), this.localSettingsService);
        }
    }

    @Subscribe
    public void onGetPendingCountCompletedEventTask(GetPendingCountTask.CompletedEvent completedEvent) {
        if (completedEvent.getRunnerId() == getRunner().getId() && completedEvent.getFailure() == null) {
            this.bellHelper.setPendingNotifCount(((Integer) completedEvent.getResult()).intValue());
            invalidateOptionsMenu();
        }
    }

    @Subscribe
    public void onChangeBannerAdVisibilityEvent(ChangeBannerAdVisibilityEvent changeBannerAdVisibilityEvent) {
        if (getView() != null) {
            View findViewById = getView().findViewById(R.id.ads_container);
            ViewUtils.setVisible(changeBannerAdVisibilityEvent.isVisible(), findViewById);
        }
    }

    @Subscribe
    public void onNewStatusPosted(NewStatusPostedEvent newStatusPostedEvent) {
        this.handler.postDelayed(new Runnable() {
            public final void run() {
                HomeFragment.this.refresh();
            }
        }, 1000);
    }

    @Subscribe
    public void onAppRatingTaskCompleted(ShouldShowRateDialogTask.CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner()) && ((Boolean) completedEvent.getResult()).booleanValue()) {
            showDialogFragment(AppRatingDialogFragment.newInstance(), Fragments.RATE_AND_REVIEW);
        }
    }

    @Subscribe
    public void onChangePasswordTaskCompleted(ChangePasswordTask.CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner()) && ((Boolean) completedEvent.getResult()).booleanValue()) {
            getNavigationHelper().withIntent(ChangePasswordActivity.newStartIntent(getActivity())).withExtra(ChangePasswordViewModel.EXTRA_FORCED_PASSWORD_CHANGE, true).startActivity();
        }
    }

    public void refresh() {
        refresh(FetchType.NewItems);
    }

    private void showNecessaryInterstitial() {
        if (!showUpdatedTermsIfNecessary()) {
            String webviewKeyToLaunch = GenericWebViewUtil.getWebviewKeyToLaunch(this.configService, this.localSettingsService);
            if (Strings.notEmpty(webviewKeyToLaunch)) {
                launchGenericWebView(webviewKeyToLaunch);
            } else if (!showPremiumInterstitialIfNecessary() && !showUacfEmailVerificationInterstitialIfNecessary()) {
                showPersonalizedAdConsentsIfNecessary();
            }
        }
    }

    private void launchGenericWebView(String str) {
        getNavigationHelper().withIntent(FullScreenWebView.newStartIntentForGenericWebView(getActivity(), str, GenericWebViewUtil.getUrlForRollout(this.configService, getCountryService()))).startActivity();
    }

    private void hideBanners() {
        if (getActivity() instanceof HideBannerAdsListener) {
            ((HideBannerAdsListener) getActivity()).hideBannerAds();
        }
    }

    private void addListToAdapter(List<MfpNewsFeedActivityEntry> list, ItemType itemType) {
        List list2;
        if (list == null) {
            list2 = new ArrayList();
        } else {
            list2 = NewsFeedCardUtils.pruneUnsupportedCards(list, getConfigService(), (LocalSettingsService) this.localSettingsService.get(), NewsFeedDisplayActivityName.Home, ((PremiumService) this.premiumService.get()).isPremiumSubscribed());
        }
        if (list2.isEmpty() && itemType != null) {
            list2.add(new EmptyListDisplayItem(itemType));
        } else if (!this.viewModel.hasReachedEndOfTimeline()) {
            list2.add(new LoadingNewsFeedItem());
        }
        list2.add(0, new DailySummaryHeader());
        list2.add(1, new NewStatusItem());
        this.newsFeedAdpter.refreshItems(list2);
        RecyclerView recyclerView = this.newsFeedRecyclerView;
        if (recyclerView != null && this.newsFeedSavedState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(this.newsFeedSavedState);
        }
    }

    /* access modifiers changed from: private */
    public void refresh(FetchType fetchType) {
        this.viewModel.load(fetchType);
    }

    private void resetActiveDateToToday() {
        getSession().getUser().setActiveDate(new Date());
    }

    private void setupAdapters() {
        HomeFragment homeFragment;
        if (this.nativeAdsAdapterFactory == null) {
            NativeAdsAdapterFactory nativeAdsAdapterFactory2 = r0;
            NativeAdsAdapterFactory nativeAdsAdapterFactory3 = new NativeAdsAdapterFactory(this, this.newsFeedItemActionListener, this.premiumService, this.imageService, this.nutrientDashboardRenderer, this.userApplicationSettingsService, this.flowId, this.newsFeedAnalyticsHelper, this.configService, this.adUnitService, this.localSettingsService, this.newsFeedService, this.adsSettings, this.locationService, getSession().getUser().isProfileCountryUS());
            homeFragment = this;
            homeFragment.nativeAdsAdapterFactory = nativeAdsAdapterFactory2;
        } else {
            homeFragment = this;
        }
        homeFragment.newsFeedAdpter = homeFragment.nativeAdsAdapterFactory.get();
    }

    private void initViewModel() {
        this.viewModel = (NewsFeedViewModel) getViewModel();
        if (this.viewModel == null) {
            NewsFeedViewModel newsFeedViewModel = new NewsFeedViewModel(getRunner(), this.newsFeedService, "", NewsFeedType.Home, this.configService, this.achievementAdManager);
            this.viewModel = (NewsFeedViewModel) setViewModel(newsFeedViewModel);
            this.viewModel.setOnShowAdListener(this);
        }
    }

    private void setupSwipeRefreshLayout() {
        this.refreshLayout.setColorSchemeResources(R.color.swipe_refresh_load, R.color.swipe_refresh_load, R.color.swipe_refresh_load, R.color.swipe_refresh_load);
        this.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public final void onRefresh() {
                HomeFragment.this.refresh(FetchType.NewItems);
            }
        });
    }

    private void fetchPendingNotifsCount() {
        new GetPendingCountTask((ConfigService) this.configService.get(), this.messageService, this.notificationSdk).run(getRunner());
    }

    private void fetchUnseenAndJoinedChallengeCount() {
        if (ChallengesUtil.isChallengesAvailable(getConfigService())) {
            new FetchChallengeSummaryTask(this.challengesService, Challenges.USER_STATUS_ELIGIBLE).run(getRunner());
            new FetchChallengeSummaryTask(this.challengesService, "joined").run(getRunner());
        }
    }

    private void setupRecyclerView() {
        final CatchIOOBLinearLayoutManager catchIOOBLinearLayoutManager = new CatchIOOBLinearLayoutManager(getActivity());
        this.newsFeedRecyclerView.setHasFixedSize(true);
        this.newsFeedRecyclerView.setLayoutManager(catchIOOBLinearLayoutManager);
        this.newsFeedRecyclerView.setAdapter(this.newsFeedAdpter);
        this.newsFeedRecyclerView.setItemViewCacheSize(9);
        this.newsFeedAdpter.setAdapterOperationListener(catchIOOBLinearLayoutManager);
        this.newsFeedRecyclerView.addItemDecoration(new ItemDecoration() {
            public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView.getChildAdapterPosition(view) != state.getItemCount() - 1 || !HomeFragment.this.viewModel.hasReachedEndOfTimeline()) {
                    rect.set(0, 0, 0, 0);
                } else {
                    rect.set(0, 0, 0, HomeFragment.this.getResources().getDimensionPixelSize(R.dimen.bottom_padding_avoid_fab_overlap));
                }
            }
        });
        this.newsFeedRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (HomeFragment.this.isVideoAutoPlayOn) {
                    int findLastVisibleItemPosition = catchIOOBLinearLayoutManager.findLastVisibleItemPosition();
                    int height = recyclerView.getHeight();
                    for (int findFirstVisibleItemPosition = catchIOOBLinearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
                        View findViewByPosition = catchIOOBLinearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                        if (!(findViewByPosition == null || findViewByPosition.getTag() == null || !(findViewByPosition.getTag() instanceof VideoEntry))) {
                            VideoViewHolder videoViewHolder = (VideoViewHolder) HomeFragment.this.newsFeedRecyclerView.getChildViewHolder(findViewByPosition);
                            VideoEntry videoEntry = (VideoEntry) findViewByPosition.getTag();
                            int bottom = findViewByPosition.getBottom() - (videoViewHolder.getVideoViewHeight() / 2);
                            if (bottom <= 0 || bottom >= height) {
                                HomeFragment.this.pauseVideo(videoEntry, videoViewHolder);
                            } else {
                                HomeFragment.this.startVideo(videoEntry, videoViewHolder);
                            }
                        }
                    }
                }
                if (!HomeFragment.this.viewModel.isFetchingNewsFeedItems() && !HomeFragment.this.viewModel.hasReachedEndOfTimeline() && i2 >= 0) {
                    if (catchIOOBLinearLayoutManager.getChildCount() + catchIOOBLinearLayoutManager.findFirstVisibleItemPosition() >= catchIOOBLinearLayoutManager.getItemCount() - 5) {
                        HomeFragment.this.refresh(FetchType.OlderItems);
                    }
                }
            }
        });
        this.newsFeedRecyclerView.addOnChildAttachStateChangeListener(new OnChildAttachStateChangeListener() {
            public void onChildViewAttachedToWindow(@NonNull View view) {
                if (HomeFragment.this.isVideoAutoPlayOn) {
                    HomeFragment.this.initVideo(view);
                }
            }

            public void onChildViewDetachedFromWindow(@NonNull View view) {
                if (HomeFragment.this.isVideoAutoPlayOn) {
                    HomeFragment.this.stopVideo(view);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void initVideo(@Nullable View view) {
        if (view != null && view.getTag() != null && (view.getTag() instanceof VideoEntry)) {
            ((VideoViewHolder) this.newsFeedRecyclerView.getChildViewHolder(view)).initVideo();
        }
    }

    /* access modifiers changed from: private */
    public void startVideo(@NonNull VideoEntry videoEntry, @NonNull VideoViewHolder videoViewHolder) {
        if (!this.videoPlayingSet.contains(videoEntry)) {
            videoViewHolder.start();
            this.videoPlayingSet.add(videoEntry);
        }
    }

    /* access modifiers changed from: private */
    public void pauseVideo(@NonNull VideoEntry videoEntry, @NonNull VideoViewHolder videoViewHolder) {
        if (this.videoPlayingSet.contains(videoEntry)) {
            videoViewHolder.pause();
            this.videoPlayingSet.remove(videoEntry);
        }
    }

    /* access modifiers changed from: private */
    public void stopVideo(@Nullable View view) {
        if (view != null && view.getTag() != null && (view.getTag() instanceof VideoEntry)) {
            VideoEntry videoEntry = (VideoEntry) view.getTag();
            ((VideoViewHolder) this.newsFeedRecyclerView.getChildViewHolder(view)).stop();
            this.videoPlayingSet.remove(videoEntry);
        }
    }

    private boolean logOutIfSessionInvalid() {
        if (getSession().getUser().isLoggedIn()) {
            return false;
        }
        getSession().logoutAndNavigateToLoginActivity();
        return true;
    }

    private boolean showPersonalizedAdConsentsIfNecessary() {
        if (!ConnectivityUtil.isOnline() || !((LocalSettingsService) this.localSettingsService.get()).getShouldInterruptUserForAdConsents()) {
            return false;
        }
        getNavigationHelper().withIntent(AdConsentsActivity.newStartIntent(getActivity(), Mode.INTERRUPTION)).startActivity();
        return true;
    }

    private boolean showUacfEmailVerificationInterstitialIfNecessary() {
        UacfEmailVerificationManager uacfEmailVerificationManager = (UacfEmailVerificationManager) this.emailVerificationManager.get();
        if (!uacfEmailVerificationManager.shouldShowAppLaunchInterstitial()) {
            return false;
        }
        uacfEmailVerificationManager.showAppLaunchInterstitial(getActivity());
        return true;
    }

    private boolean showPremiumInterstitialIfNecessary() {
        PremiumService premiumService2 = (PremiumService) this.premiumService.get();
        if (!premiumService2.isPremiumAvailable() || premiumService2.isPremiumSubscribed() || ((LocalSettingsService) this.localSettingsService.get()).hasPremiumAdBeenDisplayed()) {
            return false;
        }
        getNavigationHelper().withIntent(PremiumInterstitialActivity.newStartIntent(getActivity())).startActivity();
        ((LocalSettingsService) this.localSettingsService.get()).setPremiumAdDisplayed(true);
        return true;
    }

    private boolean showUpdatedTermsIfNecessary() {
        Intent intent = intent();
        Bundle extras = extras();
        if (ExtrasUtils.getBoolean(intent, Extras.SKIP_PRIVACY_CHECK_ONCE, false)) {
            intent.putExtra(Extras.SKIP_PRIVACY_CHECK_ONCE, false);
            return true;
        } else if (getSession().getUser().hasAcceptedTermsAndPrivacy()) {
            return false;
        } else {
            getNavigationHelper().finishActivityAfterNavigation().withIntent(UpdatedTermsActivity.newStartIntent(getActivity(), extras)).startActivity();
            return true;
        }
    }

    private void onDiaryAddDeeplinkSelectMeal() {
        ((DiaryService) this.diaryService.get()).startLoggingFlow(Extras.REFERRER_DIARY_ADD_DEEPLINK);
        showDialogFragment(MealNamesDialogFragment.newInstance(), Fragments.MEAL_NAMES_DIALOG);
    }

    private void showUpgradeAlertIfNecessary() {
        if (((GlobalSettingsService) this.globalSettingsService.get()).getUpgradeDetails() != null && !((GlobalSettingsService) this.globalSettingsService.get()).getHasSeenUpgradeNotification()) {
            Map upgradeDetails = ((GlobalSettingsService) this.globalSettingsService.get()).getUpgradeDetails();
            if (upgradeDetails != null) {
                ((GlobalSettingsService) this.globalSettingsService.get()).setHasSeenUpgradeNotification(true);
                new MfpAlertDialogBuilder(getActivity()).setMessage((CharSequence) upgradeDetails.get("body")).setTitle((CharSequence) upgradeDetails.get("title")).setCancelable(false).setPositiveButton((CharSequence) getString(R.string.upgrade), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(upgradeDetails) {
                    private final /* synthetic */ Map f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(DialogInterface dialogInterface, int i) {
                        HomeFragment.lambda$showUpgradeAlertIfNecessary$5(HomeFragment.this, this.f$1, dialogInterface, i);
                    }
                }).setNegativeButton((CharSequence) getString(R.string.dismiss), (DialogInterface.OnClickListener) $$Lambda$HomeFragment$M_FSpyWFArmv7ImUjVyPlmnH2zM.INSTANCE).show();
            }
        }
    }

    public static /* synthetic */ void lambda$showUpgradeAlertIfNecessary$5(HomeFragment homeFragment, Map map, DialogInterface dialogInterface, int i) {
        String str = (String) map.get("url");
        if (Strings.notEmpty(str)) {
            homeFragment.getNavigationHelper().withIntent(SharedIntents.newUriIntent(str)).startActivity();
        }
        dialogInterface.cancel();
    }

    private void showChangePasswordIfNecessary() {
        new ChangePasswordTask((LocalSettingsService) this.localSettingsService.get(), (ConfigService) this.configService.get()).run(getRunner());
    }

    private void showImageReportedSnackbar() {
        new SnackbarBuilder(getView()).setMessage((int) R.string.image_reported_confirmation).setDuration(0).show();
    }

    private Intent intent() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return activity.getIntent();
    }

    private Bundle extras() {
        Intent intent = intent();
        if (intent == null) {
            return null;
        }
        return intent.getExtras();
    }

    private void setVideoImageThumbnail(@NonNull VideoEntry videoEntry, @Nullable ImageView imageView, @Nullable FrameLayout frameLayout) {
        ViewUtils.setVisible(frameLayout);
        ViewUtils.setVisible(imageView);
        Glide.with(getActivity()).load(videoEntry.getThumbnail()).apply(new RequestOptions().fitCenter().centerCrop().placeholder((int) R.drawable.video_placeholder).error(R.drawable.video_placeholder)).into(imageView);
    }

    public void showInterstitialAd() {
        if (isUserSeeThisFragment()) {
            int requestedOrientation = getActivity().getRequestedOrientation();
            getActivity().setRequestedOrientation(1);
            this.achievementInterstitialAd.showInterstitialAd(new OnCloseAdListener(requestedOrientation) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onAdClosed() {
                    HomeFragment.lambda$showInterstitialAd$7(HomeFragment.this, this.f$1);
                }
            });
            this.viewModel.interstitialAdShowed();
        }
    }

    public static /* synthetic */ void lambda$showInterstitialAd$7(HomeFragment homeFragment, int i) {
        if (homeFragment.getActivity() != null) {
            homeFragment.getActivity().setRequestedOrientation(i);
        }
    }

    private boolean isUserSeeThisFragment() {
        return isVisible() && isResumed() && getUserVisibleHint();
    }
}
