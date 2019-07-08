package com.myfitnesspal.feature.explore.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengesActivity;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.community.service.CommunityService;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics;
import com.myfitnesspal.feature.explore.analytics.ExploreAnalytics.Action;
import com.myfitnesspal.feature.explore.service.ExploreService;
import com.myfitnesspal.feature.explore.service.ExploreService.Response;
import com.myfitnesspal.feature.explore.ui.view.BlogCard;
import com.myfitnesspal.feature.explore.ui.view.CommunityCard;
import com.myfitnesspal.feature.explore.ui.view.NearbyVenuesCard;
import com.myfitnesspal.feature.explore.ui.view.NewChallengesCard;
import com.myfitnesspal.feature.explore.ui.view.ShopCard;
import com.myfitnesspal.feature.explore.ui.viewmodel.ExploreViewModel;
import com.myfitnesspal.feature.explore.ui.viewmodel.ExploreViewModel.Property;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.VenuesActivity;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.ui.view.EmptyStateView;
import com.myfitnesspal.shared.ui.view.EmptyStateView.Type;
import com.myfitnesspal.shared.ui.view.GenericCardBase;
import com.myfitnesspal.shared.ui.view.StateAwareScrollView;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class ExploreActivity extends MfpActivity {
    private static final String ANALYTICS_TAG = "Explore";
    private static final int MARK_VISIBLE_CARDS_DELAY_MS = 250;
    private List<GenericCardBase> allCards;
    /* access modifiers changed from: private */
    public ExploreAnalytics analytics = new ExploreAnalytics();
    @BindView(2131361944)
    BlogCard blog;
    @BindView(2131362122)
    NewChallengesCard challenges;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @BindView(2131362173)
    CommunityCard community;
    @Inject
    Lazy<CommunityService> communityService;
    @BindView(2131362987)
    View contentView;
    @BindView(2131362456)
    EmptyStateView emptyView;
    @Inject
    Lazy<ExploreService> exploreService;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(2131362944)
    View loadingView;
    @Inject
    Lazy<MealService> mealService;
    @BindView(2131363103)
    NearbyVenuesCard nearbyVenuesCard;
    private Rect scrollBounds = new Rect();
    @BindView(2131362534)
    StateAwareScrollView scrollView;
    @BindView(2131363643)
    ShopCard shop;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    /* access modifiers changed from: private */
    public ExploreViewModel viewModel;

    public String getAnalyticsScreenTag() {
        return ANALYTICS_TAG;
    }

    public int getCustomToolbarLayoutResId() {
        return R.layout.toolbar_no_progress_layout;
    }

    public boolean showAsTopLevelActivity() {
        return true;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, ExploreActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        registerMixin(new BottomBarMixin(this));
        super.onCreate(bundle);
        setContentView((int) R.layout.explore_activity);
        this.allCards = new ArrayList(Arrays.asList(new GenericCardBase[]{this.nearbyVenuesCard, this.challenges, this.blog, this.shop, this.community}));
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(this, true);
        getSupportActionBar().setTitle((int) R.string.menu_explore);
        this.emptyView.initializeForType(Type.Explore, new OnClickListener() {
            public void onClick(View view) {
                ExploreActivity.this.viewModel.load(new Void[0]);
            }
        });
        initListeners();
        initViewModel();
        if (bundle != null) {
            rebindUi();
        }
        this.nearbyVenuesCard.onActivityCreated(this, bundle);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.nearbyVenuesCard.onActivityStarted(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.viewModel.onResume(this);
        this.viewModel.load(new Void[0]);
        updateViewVisibility();
        rebindUi();
        this.nearbyVenuesCard.onActivityResumed(this);
        this.nearbyVenuesCard.moveMapToLocation(this.viewModel.getLastKnownLocation());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.nearbyVenuesCard.onActivityPaused(this);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.nearbyVenuesCard.onActivityStopped(this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.nearbyVenuesCard.onActivitySaveInstanceState(this, bundle);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.nearbyVenuesCard.onActivityDestroyed(this);
        super.onDestroy();
    }

    public boolean backPressed() {
        return ((BottomBarMixin) mixin(BottomBarMixin.class)).backPressed() || super.backPressed();
    }

    private void initViewModel() {
        this.viewModel = (ExploreViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (ExploreViewModel) setViewModel(new ExploreViewModel(getRunner(), getConfigService()));
        }
    }

    private void initListeners() {
        this.nearbyVenuesCard.setOnButtonClickListener(new OnClickListener() {
            public void onClick(View view) {
                ExploreActivity.this.analytics.reportCardActionTapped(ExploreActivity.this.nearbyVenuesCard.getAnalyticsType(), Action.VIEW_VENUES);
                ExploreActivity.this.getNavigationHelper().withIntent(VenuesActivity.newStartIntent(ExploreActivity.this)).startActivity();
            }
        });
        this.challenges.setOnButtonClickListener(new OnClickListener() {
            public void onClick(View view) {
                ExploreActivity.this.analytics.reportCardActionTapped(ExploreActivity.this.challenges.getAnalyticsType(), "view_challenges");
                ExploreActivity.this.getNavigationHelper().withIntent(ChallengesActivity.newStartIntent(ExploreActivity.this)).startActivity();
            }
        });
        this.scrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                ExploreActivity.this.markVisibleCards();
            }
        });
    }

    private void rebindUi() {
        Response data = this.viewModel.getData();
        if (data == null || CollectionUtils.isEmpty((Collection<?>) data.getNewChallenges())) {
            ViewUtils.setGone(this.challenges);
        } else {
            ViewUtils.setVisible(this.challenges);
            this.challenges.redrawForExplore(this.imageService, this.challengesAnalyticsHelper, getConfigService(), data.getNewChallenges());
        }
        bindNearbyVenuesCard(data);
        this.blog.render(data == null ? null : data.getBlogPost());
        this.community.render(this.communityService);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.LOAD_STATE) {
            updateViewVisibility();
            if (this.viewModel.isLoaded()) {
                this.contentView.postDelayed(new Runnable() {
                    public void run() {
                        ExploreActivity.this.markVisibleCards();
                    }
                }, 250);
            }
        } else if (i == Property.LAST_KNOWN_LOCATION) {
            this.nearbyVenuesCard.moveMapToLocation(this.viewModel.getLastKnownLocation());
        }
    }

    private void drawError() {
        ViewUtils.setVisible(this.emptyView);
        ViewUtils.setGone(this.contentView);
        ViewUtils.setGone(this.loadingView);
    }

    private void drawContent() {
        ViewUtils.setVisible(this.contentView);
        ViewUtils.setGone(this.emptyView);
        ViewUtils.setGone(this.loadingView);
        rebindUi();
    }

    private void drawLoading() {
        ViewUtils.setGone(this.contentView);
        ViewUtils.setGone(this.emptyView);
        ViewUtils.setVisible(this.loadingView);
    }

    private void bindNearbyVenuesCard(Response response) {
        if (!ConfigUtils.isNearbyVenuesExploreCardEnabled(getConfigService()) || response == null || response.getNearbyVenues() == null || response.getNearbyVenues().isEmpty()) {
            this.nearbyVenuesCard.setVisibility(8);
            return;
        }
        this.nearbyVenuesCard.setVisibility(0);
        this.nearbyVenuesCard.drawVenues(response.getNearbyVenues());
    }

    private void updateViewVisibility() {
        switch (this.viewModel.getState()) {
            case Error:
                drawError();
                return;
            case Loading:
                if (this.viewModel.getData() != null) {
                    drawContent();
                    return;
                } else {
                    drawLoading();
                    return;
                }
            case Loaded:
                drawContent();
                return;
            case NotLoaded:
                drawLoading();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void markVisibleCards() {
        this.scrollView.getHitRect(this.scrollBounds);
        for (GenericCardBase genericCardBase : this.allCards) {
            if (!this.viewModel.cardSeen(genericCardBase.getId()) && genericCardBase.getLocalVisibleRect(this.scrollBounds)) {
                this.viewModel.markCardSeen(genericCardBase.getId());
                this.analytics.reportCardViewed(genericCardBase.getAnalyticsType());
            }
        }
    }
}
