package com.myfitnesspal.feature.profile.ui.fragment;

import android.content.Intent;
import android.databinding.Observable;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsParent;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengesActivity;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.profile.analytics.MeAnalytics;
import com.myfitnesspal.feature.profile.analytics.MeAnalytics.Action;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.OfflineData;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.OnlineData;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.ProfileChallenges;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.ProfileFriends;
import com.myfitnesspal.feature.profile.ui.view.AppsCard;
import com.myfitnesspal.feature.profile.ui.view.ChallengesCard;
import com.myfitnesspal.feature.profile.ui.view.FriendsCard;
import com.myfitnesspal.feature.profile.ui.view.GoalsCard;
import com.myfitnesspal.feature.profile.ui.view.MeCardBase;
import com.myfitnesspal.feature.profile.ui.view.ProgressCard;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyInfoViewModel;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyInfoViewModel.Property;
import com.myfitnesspal.feature.profile.util.ProfileViewUtil;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.ui.activity.AddWeightActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressActivity;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.StateAwareScrollView;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

public class MyInfoFragment extends MfpFragment {
    private List<MeCardBase> allCards;
    private MeAnalytics analytics = new MeAnalytics();
    @BindView(2131361910)
    AppsCard apps;
    @BindView(2131362122)
    ChallengesCard challenges;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalytics;
    @Inject
    Lazy<ConfigService> configService;
    @BindView(2131362679)
    FriendsCard friends;
    @BindView(2131362719)
    GoalsCard goals;
    @Inject
    Lazy<ImageService> imageService;
    @Inject
    Lazy<PremiumService> premiumService;
    @BindView(2131363320)
    ProgressCard progress;
    private Rect scrollBounds = new Rect();
    @BindView(2131363087)
    StateAwareScrollView scrollView;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    Lazy<UserWeightService> userWeightService;
    private MyInfoViewModel viewModel;

    public static MyInfoFragment newInstance() {
        return new MyInfoFragment();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        initViewModel();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.my_info_fragment, viewGroup, false);
        updateTopMargin(inflate.findViewById(R.id.progressCard));
        if (!ChallengesUtil.isChallengesAvailable(getConfigService())) {
            ViewUtils.setGone(inflate.findViewById(R.id.challengesCard));
        }
        return inflate;
    }

    /* access modifiers changed from: private */
    public void markVisibleCards() {
        this.scrollView.getHitRect(this.scrollBounds);
        for (MeCardBase meCardBase : this.allCards) {
            if (!this.viewModel.cardSeen(meCardBase.getId()) && meCardBase.getLocalVisibleRect(this.scrollBounds)) {
                this.viewModel.markCardSeen(meCardBase.getId());
                this.analytics.reportCardViewed(meCardBase.getAnalyticsType());
            }
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.allCards = new ArrayList(Arrays.asList(new MeCardBase[]{this.progress, this.goals, this.challenges, this.friends, this.apps}));
        bindListeners();
        redrawOfflineCards();
        redrawOnlineCards();
    }

    public void onResume() {
        super.onResume();
        this.viewModel.load(new Void[0]);
        updateTopMargin();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 25 && i2 == -1) {
            getNavigationHelper().withIntent(ProgressActivity.newStartIntent(getActivity(), Measurement.WEIGHT)).startActivity();
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }

    private void initViewModel() {
        this.viewModel = (MyInfoViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (MyInfoViewModel) setViewModel(new MyInfoViewModel(getRunner()));
        }
    }

    private void updateTopMargin(View view) {
        ProfileViewUtil.addTopPaddingIfAdVisible((PremiumService) this.premiumService.get(), view, R.dimen.news_feed_card_marginTop);
    }

    private void updateTopMargin() {
        updateTopMargin(this.progress);
    }

    private void bindListeners() {
        this.progress.setOnButtonClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MyInfoFragment.lambda$bindListeners$0(MyInfoFragment.this, view);
            }
        });
        this.goals.setOnButtonClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MyInfoFragment.lambda$bindListeners$1(MyInfoFragment.this, view);
            }
        });
        this.challenges.setOnButtonClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MyInfoFragment.lambda$bindListeners$2(MyInfoFragment.this, view);
            }
        });
        this.friends.setOnButtonClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MyInfoFragment.lambda$bindListeners$3(MyInfoFragment.this, view);
            }
        });
        this.apps.setOnButtonClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MyInfoFragment.lambda$bindListeners$4(MyInfoFragment.this, view);
            }
        });
        this.scrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
            public final void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                MyInfoFragment.this.markVisibleCards();
            }
        });
    }

    public static /* synthetic */ void lambda$bindListeners$0(MyInfoFragment myInfoFragment, View view) {
        myInfoFragment.analytics.reportCardActionTapped(myInfoFragment.progress.getAnalyticsType(), "add_weight");
        myInfoFragment.getNavigationHelper().withNoAnimations().withIntent(AddWeightActivity.newStartIntent(myInfoFragment.getActivity(), ProgressEntryPoint.MyInfo, ImportDevice.None)).fromFragment(myInfoFragment).startActivity(25);
    }

    public static /* synthetic */ void lambda$bindListeners$1(MyInfoFragment myInfoFragment, View view) {
        myInfoFragment.analytics.reportCardActionTapped(myInfoFragment.goals.getAnalyticsType(), Action.UPDATE_GOALS);
        myInfoFragment.getNavigationHelper().withIntent(Goals.newStartIntent(myInfoFragment.getActivity())).startActivity();
    }

    public static /* synthetic */ void lambda$bindListeners$2(MyInfoFragment myInfoFragment, View view) {
        myInfoFragment.analytics.reportCardActionTapped(myInfoFragment.challenges.getAnalyticsType(), "view_challenges");
        myInfoFragment.getNavigationHelper().withIntent(ChallengesActivity.newStartIntent(myInfoFragment.getContext())).startActivity();
    }

    public static /* synthetic */ void lambda$bindListeners$3(MyInfoFragment myInfoFragment, View view) {
        myInfoFragment.analytics.reportCardActionTapped(myInfoFragment.friends.getAnalyticsType(), "add_friend");
        myInfoFragment.getNavigationHelper().withIntent(AddFriendsParent.newStartIntent(myInfoFragment.getContext())).startActivity();
    }

    public static /* synthetic */ void lambda$bindListeners$4(MyInfoFragment myInfoFragment, View view) {
        myInfoFragment.analytics.reportCardActionTapped(myInfoFragment.apps.getAnalyticsType(), Action.CONNECT_APPS);
        myInfoFragment.getNavigationHelper().withIntent(AppGalleryActivity.newStartIntent(myInfoFragment.getContext())).startActivity();
    }

    private void redrawOfflineCards() {
        OfflineData offlineData = this.viewModel.getOfflineData();
        this.progress.redraw((UserWeightService) this.userWeightService.get(), offlineData);
        this.goals.redraw((UserWeightService) this.userWeightService.get(), (UserEnergyService) this.userEnergyService.get(), offlineData);
    }

    private void redrawOnlineCards() {
        ProfileChallenges profileChallenges;
        List list;
        OnlineData onlineData = this.viewModel.getOnlineData();
        ProfileFriends profileFriends = null;
        if (onlineData == null) {
            profileChallenges = null;
        } else {
            profileChallenges = onlineData.getChallenges();
        }
        if (onlineData == null) {
            list = null;
        } else {
            list = onlineData.getApps();
        }
        if (onlineData != null) {
            profileFriends = onlineData.getFriends();
        }
        this.challenges.redrawForMe(this.imageService, this.challengesAnalytics, getConfigService(), profileChallenges);
        this.apps.redraw(list);
        this.friends.redraw(profileFriends);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.OFFLINE_DATA) {
            redrawOfflineCards();
            markVisibleCards();
        } else if (i == Property.ONLINE_DATA) {
            redrawOnlineCards();
            markVisibleCards();
        }
        super.onViewModelPropertyChanged(observable, i);
    }
}
