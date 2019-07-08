package com.myfitnesspal.feature.profile.ui.fragment;

import android.animation.LayoutTransition;
import android.content.DialogInterface;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdManager;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.EmptyListDisplayItem;
import com.myfitnesspal.feature.home.model.EmptyListDisplayItem.ItemType;
import com.myfitnesspal.feature.home.model.LoadingNewsFeedItem;
import com.myfitnesspal.feature.home.model.NewStatusItem;
import com.myfitnesspal.feature.home.model.NewsFeedDisplayActivityName;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.model.NewsFeedRequestData.FetchType;
import com.myfitnesspal.feature.home.model.ProfileHeader;
import com.myfitnesspal.feature.home.model.ProfileHeaderModel;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.ui.adapter.NewsFeedAdapter;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel.NewsFeedType;
import com.myfitnesspal.feature.home.ui.viewmodel.NewsFeedViewModel.Property;
import com.myfitnesspal.feature.home.util.NewsFeedCardUtils;
import com.myfitnesspal.feature.home.util.NewsFeedItemActionUtils;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.settings.event.FriendDeletedEvent;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.feature.settings.ui.activity.EditProfile;
import com.myfitnesspal.feature.settings.ui.dialog.ProfileDialogFragment;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.DismissDialogFragmentEvent;
import com.myfitnesspal.shared.event.ProfileDialogEvent;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.model.v15.UserSummaryObject;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.service.premium.PremiumAnalyticsHelper;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.strings.GrammarService;
import com.myfitnesspal.shared.service.userdata.UserSummaryService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.EmptyStateView;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.myfitnesspal.shared.ui.widget.CatchIOOBLinearLayoutManager;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class ProfileFragment extends MfpFragment implements NewsFeedItemActionListener {
    private static final int EDIT_ACTION_ITEM = 100;
    private static final String EXTRA_DISPLAY_MODE = "extra_display_mode";
    private static final String IMAGE_CARD_DIALOG_TAG = "image_card_dialog_tag";
    private static final int REMOVE_FRIEND_ACTION_ITEM = 101;
    @Inject
    Lazy<AchievementAdManager> achievementAdManager;
    @Inject
    Lazy<AppSettings> appSettings;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    private boolean challengesAvailable;
    @Inject
    Lazy<ConfigService> configService;
    @BindView(2131362467)
    EmptyStateView emptyView;
    @Inject
    Lazy<FriendService> friendService;
    @Inject
    Lazy<GrammarService> grammarService;
    @Inject
    Lazy<ImageService> imageService;
    private boolean isFriendRequest;
    private boolean isShowingProgressDialog = false;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    MiniUserInfoMapper miniUserInfoMapper;
    private NewsFeedAdapter newsFeedAdapter;
    @Inject
    Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    @BindView(2131363141)
    RecyclerView newsFeedRecyclerView;
    @Inject
    Lazy<NewsFeedService> newsFeedService;
    @Inject
    Lazy<PremiumAnalyticsHelper> premiumAnalyticsHelper;
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363426)
    SwipeRefreshLayout refreshLayout;
    private boolean removed;
    @BindView(2131362549)
    ViewGroup rootView;
    /* access modifiers changed from: private */
    public boolean shouldShowNewsfeed;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    private MiniUserInfo userInfo;
    @Inject
    Lazy<UserSummaryService> userSummaryService;
    private UserType userType;
    private String userUid = "";
    @Inject
    Lazy<UserWeightService> userWeightService;
    private String username = "";
    /* access modifiers changed from: private */
    public NewsFeedViewModel viewModel;

    private interface DisplayMode {
        public static final int DEFAULT = 0;
        public static final int NO_HEADER = 1;
    }

    public enum UserType {
        Self,
        Friend,
        NonFriend,
        FriendRequest
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setHasOptionsMenu(isHeaderVisible());
        this.challengesAvailable = ChallengesUtil.isChallengesAvailable(getConfigService());
    }

    public static ProfileFragment newInstanceWithNoHeader(String str) {
        ProfileFragment newInstance = newInstance(str, null, null, false);
        newInstance.getArguments().putInt(EXTRA_DISPLAY_MODE, 1);
        return newInstance;
    }

    public static ProfileFragment newInstance(String str, String str2, MiniUserInfo miniUserInfo, boolean z) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle bundle = new Bundle(2);
        bundle.putString(Extras.USER_NAME, str);
        bundle.putString(Extras.USER_UID, str2);
        bundle.putParcelable(Extras.USER_INFO, miniUserInfo);
        bundle.putBoolean(Extras.IS_FRIEND_REQUEST, z);
        profileFragment.setArguments(bundle);
        return profileFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.home_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        readFromBundle(getArguments());
        setupAdapter();
        NewsFeedViewModel newsFeedViewModel = this.viewModel;
        if (newsFeedViewModel != null) {
            addListToAdapter(newsFeedViewModel.getNewsFeedEntries(), null);
        }
        this.rootView.setLayoutTransition(new LayoutTransition());
        this.emptyView.initializeForType(Type.Profile, new OnClickListener() {
            public final void onClick(View view) {
                ProfileFragment.this.load();
            }
        });
        setupRecyclerView();
        setupSwipeRefreshLayout();
        setupMiniUserInfo(bundle, getArguments());
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable(Extras.USER_INFO, this.userInfo);
    }

    public void onResume() {
        super.onResume();
        MiniUserInfo miniUserInfo = this.userInfo;
        if (miniUserInfo != null && miniUserInfo.isCurrentUser(getSession())) {
            String username2 = this.userInfo.getUsername();
            this.userInfo = MiniUserInfo.forCurrentUser(getSession());
            if (!this.userInfo.getUsername().equalsIgnoreCase(username2)) {
                getActivity().finish();
            }
        }
        if (this.shouldShowNewsfeed) {
            fetchNewsFeedItems(FetchType.NewItems);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (isHeaderVisible()) {
            menu.clear();
            if (this.userType == UserType.Self) {
                MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.edit).setIcon(R.drawable.ic_edit_white_24dp), 2);
            } else if (this.userType == UserType.Friend) {
                MenuItemCompat.setShowAsAction(menu.add(0, 101, 0, R.string.removeFriendBtn), 4);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 100:
                getAnalyticsService().reportEvent(Events.EDIT_PROFILE_CLICK);
                getNavigationHelper().withIntent(EditProfile.newStartIntent(getActivity(), getSession())).startActivity();
                break;
            case 101:
                confirmAndCancel();
                this.userInfo.setIsFriend(false);
                break;
        }
        return true;
    }

    private void confirmAndCancel() {
        $$Lambda$ProfileFragment$745TZYAa5kL3giyUYTSM7z8gvRI r0 = new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                ProfileFragment.lambda$confirmAndCancel$3(ProfileFragment.this, dialogInterface, i);
            }
        };
        new MfpAlertDialogBuilder(getActivity()).setTitle((CharSequence) getString(R.string.confirmTxt)).setMessage((CharSequence) getString(R.string.removeFriendConfirm)).setPositiveButton((CharSequence) getString(R.string.yesBtn), (DialogInterface.OnClickListener) r0).setNegativeButton((CharSequence) getString(R.string.noBtn), (DialogInterface.OnClickListener) r0).show();
    }

    public static /* synthetic */ void lambda$confirmAndCancel$3(ProfileFragment profileFragment, DialogInterface dialogInterface, int i) {
        switch (i) {
            case -2:
                dialogInterface.dismiss();
                return;
            case -1:
                profileFragment.postEvent(new ProfileDialogEvent(ProfileDialogFragment.newInstance(Dialogs.REMOVE_FRIEND_PROGRESS_DIALOG)));
                profileFragment.isShowingProgressDialog = true;
                ((FriendService) profileFragment.friendService.get()).unfriendUserId(profileFragment.userInfo.getMasterDatabaseId(), profileFragment.userInfo.getUid(), new Function0() {
                    public final void execute() {
                        ProfileFragment.lambda$null$1(ProfileFragment.this);
                    }
                }, new ApiErrorCallback() {
                    public final void execute(Object obj) {
                        ProfileFragment.lambda$null$2(ProfileFragment.this, (ApiException) obj);
                    }
                });
                return;
            default:
                return;
        }
    }

    public static /* synthetic */ void lambda$null$1(ProfileFragment profileFragment) throws RuntimeException {
        ((AppSettings) profileFragment.appSettings.get()).setProfileDeletion(Boolean.valueOf(true));
        profileFragment.removed = true;
        profileFragment.postEvent(new FriendDeletedEvent(profileFragment.removed));
        profileFragment.userInfo.setIsFriend(false);
        profileFragment.onUserInfoLoaded();
        if (profileFragment.isShowingProgressDialog) {
            profileFragment.postEvent(new DismissDialogFragmentEvent());
            profileFragment.isShowingProgressDialog = false;
        }
    }

    public static /* synthetic */ void lambda$null$2(ProfileFragment profileFragment, ApiException apiException) throws RuntimeException {
        profileFragment.userInfo.setIsFriend(true);
        if (profileFragment.isShowingProgressDialog) {
            profileFragment.postEvent(new DismissDialogFragmentEvent());
            profileFragment.isShowingProgressDialog = false;
        }
        profileFragment.showError(apiException.getBody(), profileFragment.getString(R.string.failRemoveFriend));
    }

    private void showError(String str, String str2) {
        if (!Strings.notEmpty(str)) {
            str = str2;
        }
        postEvent(new AlertEvent(str));
    }

    private void setupMiniUserInfo(Bundle bundle, Bundle bundle2) {
        if (this.userInfo == null) {
            this.userInfo = (MiniUserInfo) BundleUtils.getParcelable(bundle, Extras.USER_INFO, BundleUtils.getParcelable(bundle2, Extras.USER_INFO, MiniUserInfo.class.getClassLoader()), MiniUserInfo.class.getClassLoader());
        }
        if (this.userInfo != null) {
            onUserInfoLoaded();
        } else {
            load();
        }
    }

    /* access modifiers changed from: private */
    public void load() {
        if (!Strings.isEmpty(this.username)) {
            onLoadingOrLoaded();
            setBusy(true);
            ((UserSummaryService) this.userSummaryService.get()).fetchUserSummaryAsync(this.username, this.userUid, new Function1() {
                public final void execute(Object obj) {
                    ProfileFragment.lambda$load$4(ProfileFragment.this, (UserSummaryObject) obj);
                }
            }, new ApiErrorCallback() {
                public final void execute(Object obj) {
                    ProfileFragment.lambda$load$5(ProfileFragment.this, (ApiException) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$load$4(ProfileFragment profileFragment, UserSummaryObject userSummaryObject) throws RuntimeException {
        profileFragment.setBusy(false);
        if (profileFragment.isEnabled() && userSummaryObject != null) {
            profileFragment.userInfo = profileFragment.miniUserInfoMapper.mapFrom(userSummaryObject);
            profileFragment.onUserInfoLoaded();
        }
    }

    public static /* synthetic */ void lambda$load$5(ProfileFragment profileFragment, ApiException apiException) throws RuntimeException {
        profileFragment.onLoadError();
        profileFragment.setBusy(false);
        Ln.w(apiException, "Could not load the user summary into the profile.", new Object[0]);
    }

    private void onUserInfoLoaded() {
        onLoadingOrLoaded();
        this.userType = getUserType(this.userInfo);
        this.newsFeedAdapter.setMiniUserInfoAndUserType(this.userInfo, this.userType);
        setupProfileState();
        invalidateOptionsMenu();
        initViewModel();
    }

    private void setupProfileState() {
        this.shouldShowNewsfeed = true;
        setupProfileAndStatusView(new ArrayList());
    }

    private UserType getUserType(MiniUserInfo miniUserInfo) {
        if (miniUserInfo.isCurrentUser(getSession())) {
            return UserType.Self;
        }
        if (miniUserInfo.isFriend()) {
            return UserType.Friend;
        }
        if (this.isFriendRequest) {
            return UserType.FriendRequest;
        }
        return UserType.NonFriend;
    }

    private void readFromBundle(Bundle bundle) {
        this.username = BundleUtils.getString(bundle, Extras.USER_NAME);
        this.userUid = BundleUtils.getString(bundle, Extras.USER_UID);
        this.isFriendRequest = BundleUtils.getBoolean(bundle, Extras.IS_FRIEND_REQUEST, false);
    }

    private void setupAdapter() {
        if (this.newsFeedAdapter == null) {
            NavigationHelper navigationHelper = getNavigationHelper();
            Lazy<PremiumService> lazy = this.premiumService;
            Lazy<ImageService> lazy2 = this.imageService;
            Lazy<NewsFeedAnalyticsHelper> lazy3 = this.newsFeedAnalyticsHelper;
            Lazy<ConfigService> lazy4 = this.configService;
            Session session = getSession();
            NewsFeedDisplayActivityName newsFeedDisplayActivityName = NewsFeedDisplayActivityName.Profile;
            Lazy<UserApplicationSettingsService> lazy5 = this.userApplicationSettingsService;
            String str = this.username;
            Lazy<UserWeightService> lazy6 = this.userWeightService;
            Lazy<GrammarService> lazy7 = this.grammarService;
            Lazy<FriendService> lazy8 = this.friendService;
            String str2 = str;
            Lazy<UserApplicationSettingsService> lazy9 = lazy5;
            NewsFeedDisplayActivityName newsFeedDisplayActivityName2 = newsFeedDisplayActivityName;
            Session session2 = session;
            Lazy<ConfigService> lazy10 = lazy4;
            Lazy<NewsFeedAnalyticsHelper> lazy11 = lazy3;
            Lazy<UserWeightService> lazy12 = lazy6;
            Lazy<GrammarService> lazy13 = lazy7;
            Lazy<FriendService> lazy14 = lazy8;
            ProfileHeaderModel profileHeaderModel = new ProfileHeaderModel(lazy12, lazy13, lazy14, this.userSummaryService, this.challengesAnalyticsHelper, this.premiumService, this.premiumAnalyticsHelper, this.miniUserInfoMapper, this.challengesAvailable, this.userUid);
            NewsFeedAdapter newsFeedAdapter2 = new NewsFeedAdapter(navigationHelper, lazy, lazy2, this, lazy11, lazy10, session2, newsFeedDisplayActivityName2, lazy9, str2, profileHeaderModel, "");
            this.newsFeedAdapter = newsFeedAdapter2;
            this.newsFeedAdapter.setAdPinnedToTop(ConfigUtils.isAppNavBottomBarEnabled(getConfigService()));
        }
    }

    private boolean isHeaderVisible() {
        return getArguments().getInt(EXTRA_DISPLAY_MODE, 0) == 0;
    }

    private void setupProfileAndStatusView(List<NewsFeedItem> list) {
        int i;
        if (isHeaderVisible()) {
            list.add(0, new ProfileHeader());
            i = 1;
        } else {
            i = 0;
        }
        if (this.userType == UserType.Self || (this.userType == UserType.Friend && this.userInfo.isProfileViewable())) {
            int i2 = i + 1;
            list.add(i, new NewStatusItem());
            i = i2;
        }
        if (((this.userType == UserType.Friend || this.userType != UserType.Self) && !this.userInfo.isProfileViewable()) || this.userType == UserType.FriendRequest || this.userType == UserType.NonFriend) {
            this.shouldShowNewsfeed = false;
            list.add(i, new EmptyListDisplayItem(ItemType.PrivateTimeline));
        }
        this.newsFeedAdapter.refreshItems(list);
        onLoadingOrLoaded();
    }

    private void setupRecyclerView() {
        final CatchIOOBLinearLayoutManager catchIOOBLinearLayoutManager = new CatchIOOBLinearLayoutManager(getActivity());
        this.newsFeedRecyclerView.setHasFixedSize(true);
        this.newsFeedRecyclerView.setLayoutManager(catchIOOBLinearLayoutManager);
        this.newsFeedRecyclerView.setAdapter(this.newsFeedAdapter);
        this.newsFeedAdapter.setAdapterOperationListener(catchIOOBLinearLayoutManager);
        this.newsFeedRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ProfileFragment.this.shouldShowNewsfeed && !ProfileFragment.this.viewModel.isFetchingNewsFeedItems() && !ProfileFragment.this.viewModel.hasReachedEndOfTimeline() && i2 >= 0) {
                    if (catchIOOBLinearLayoutManager.getChildCount() + catchIOOBLinearLayoutManager.findFirstVisibleItemPosition() >= catchIOOBLinearLayoutManager.getItemCount() - 5) {
                        ProfileFragment.this.fetchNewsFeedItems(FetchType.OlderItems);
                    }
                }
            }
        });
    }

    private void setEmptyStateVisible(boolean z) {
        ViewUtils.setVisible(z, this.emptyView);
        ViewUtils.setVisible(!z, this.newsFeedRecyclerView);
    }

    private void onLoadingOrLoaded() {
        setEmptyStateVisible(false);
    }

    private void onLoadError() {
        NewsFeedViewModel newsFeedViewModel = this.viewModel;
        setEmptyStateVisible(newsFeedViewModel == null || CollectionUtils.isEmpty((Collection<?>) newsFeedViewModel.getNewsFeedEntries()));
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        this.refreshLayout.setRefreshing(false);
        if (i == Property.NEWS_FEED_FETCHED || i == Property.NEWS_FEED_FETCH_FAILED) {
            addListToAdapter(this.viewModel.getNewsFeedEntries(), i == Property.NEWS_FEED_FETCHED ? ItemType.EmptyTimeline : ItemType.ErrorFetchingTimeline);
        } else if (i != Property.LOAD_STATE) {
        } else {
            if (this.viewModel.getState() == State.Error) {
                onLoadError();
            } else {
                onLoadingOrLoaded();
            }
        }
    }

    private void setupSwipeRefreshLayout() {
        this.refreshLayout.setColorSchemeResources(R.color.swipe_refresh_load, R.color.swipe_refresh_load, R.color.swipe_refresh_load, R.color.swipe_refresh_load);
        this.refreshLayout.setEnabled(this.shouldShowNewsfeed);
        this.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public final void onRefresh() {
                ProfileFragment.lambda$setupSwipeRefreshLayout$6(ProfileFragment.this);
            }
        });
    }

    public static /* synthetic */ void lambda$setupSwipeRefreshLayout$6(ProfileFragment profileFragment) {
        if (profileFragment.shouldShowNewsfeed) {
            profileFragment.fetchNewsFeedItems(FetchType.NewItems);
        }
    }

    /* access modifiers changed from: private */
    public void fetchNewsFeedItems(FetchType fetchType) {
        this.viewModel.load(fetchType);
    }

    private void initViewModel() {
        if (this.shouldShowNewsfeed) {
            this.viewModel = (NewsFeedViewModel) getViewModel();
            if (this.viewModel == null) {
                NewsFeedViewModel newsFeedViewModel = new NewsFeedViewModel(getRunner(), this.newsFeedService, this.userInfo.getUid(), NewsFeedType.Profile, this.configService, this.achievementAdManager);
                this.viewModel = (NewsFeedViewModel) setViewModel(newsFeedViewModel);
            }
            addListToAdapter(this.viewModel.getNewsFeedEntries(), null);
            fetchNewsFeedItems(FetchType.NewItems);
        }
    }

    private void addListToAdapter(List<MfpNewsFeedActivityEntry> list, ItemType itemType) {
        List list2;
        if (list == null) {
            list2 = new ArrayList();
        } else {
            list2 = NewsFeedCardUtils.pruneUnsupportedCards(list, getConfigService(), (LocalSettingsService) this.localSettingsService.get(), NewsFeedDisplayActivityName.Profile, ((PremiumService) this.premiumService.get()).isPremiumSubscribed());
        }
        if (list2.isEmpty() && itemType != null) {
            list2.add(new EmptyListDisplayItem(itemType));
        } else if (!this.viewModel.hasReachedEndOfTimeline()) {
            list2.add(new LoadingNewsFeedItem());
        }
        setupProfileAndStatusView(list2);
    }

    public void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        ((NewsFeedService) this.newsFeedService.get()).deleteNewsFeedEntryAsync(mfpNewsFeedActivityEntry);
        ((NewsFeedService) this.newsFeedService.get()).removeCachedEntry(Uri.ACTIVITY_TIMELINE, mfpNewsFeedActivityEntry.getId(), this.userUid);
        this.newsFeedAdapter.removeItem(mfpNewsFeedActivityEntry);
    }

    public void onImageCardActionClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, boolean z) {
        NewsFeedItemActionUtils.imageCardActionClick(this, getFragmentManager(), mfpNewsFeedActivityEntry, IMAGE_CARD_DIALOG_TAG, z);
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

    public void onCameraClick() {
        NewsFeedItemActionUtils.statusCameraClick(getNavigationHelper(), getActivity());
    }

    public void onViewMealClick(String str, String str2, String str3, String str4) {
        NewsFeedItemActionUtils.viewMealFoodClick(getNavigationHelper(), getActivity(), str, str2, str3, str4, getSession());
    }

    public void onLikeClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, String str, String str2, Function1<MfpNewsFeedActivityEntry> function1, Function1<MfpNewsFeedActivityEntry> function12) {
        NewsFeedItemActionUtils.likeClick((MfpActivity) getActivity(), this.newsFeedService, getMessageBus(), mfpNewsFeedActivityEntry, str, str2, function1, function12);
    }

    public void onUpdateStatusClick() {
        NewsFeedItemActionUtils.updateStatusClick(getNavigationHelper(), getActivity(), this, this.userUid);
    }
}
