package com.myfitnesspal.feature.explore.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.explore.ui.view.BlogCard;
import com.myfitnesspal.feature.explore.ui.view.CommunityCard;
import com.myfitnesspal.feature.explore.ui.view.NearbyVenuesCard;
import com.myfitnesspal.feature.explore.ui.view.NewChallengesCard;
import com.myfitnesspal.feature.explore.ui.view.ShopCard;
import com.myfitnesspal.shared.ui.view.EmptyStateView;
import com.myfitnesspal.shared.ui.view.StateAwareScrollView;

public class ExploreActivity_ViewBinding implements Unbinder {
    private ExploreActivity target;

    @UiThread
    public ExploreActivity_ViewBinding(ExploreActivity exploreActivity) {
        this(exploreActivity, exploreActivity.getWindow().getDecorView());
    }

    @UiThread
    public ExploreActivity_ViewBinding(ExploreActivity exploreActivity, View view) {
        this.target = exploreActivity;
        exploreActivity.contentView = Utils.findRequiredView(view, R.id.mainContainer, "field 'contentView'");
        exploreActivity.emptyView = (EmptyStateView) Utils.findRequiredViewAsType(view, R.id.emptyStateView, "field 'emptyView'", EmptyStateView.class);
        exploreActivity.loadingView = Utils.findRequiredView(view, R.id.loadingView, "field 'loadingView'");
        exploreActivity.nearbyVenuesCard = (NearbyVenuesCard) Utils.findRequiredViewAsType(view, R.id.nearby_venues_card, "field 'nearbyVenuesCard'", NearbyVenuesCard.class);
        exploreActivity.challenges = (NewChallengesCard) Utils.findRequiredViewAsType(view, R.id.challengesCard, "field 'challenges'", NewChallengesCard.class);
        exploreActivity.blog = (BlogCard) Utils.findRequiredViewAsType(view, R.id.blogCard, "field 'blog'", BlogCard.class);
        exploreActivity.shop = (ShopCard) Utils.findRequiredViewAsType(view, R.id.shopCard, "field 'shop'", ShopCard.class);
        exploreActivity.community = (CommunityCard) Utils.findRequiredViewAsType(view, R.id.communityCard, "field 'community'", CommunityCard.class);
        exploreActivity.scrollView = (StateAwareScrollView) Utils.findRequiredViewAsType(view, R.id.exploreScrollView, "field 'scrollView'", StateAwareScrollView.class);
    }

    @CallSuper
    public void unbind() {
        ExploreActivity exploreActivity = this.target;
        if (exploreActivity != null) {
            this.target = null;
            exploreActivity.contentView = null;
            exploreActivity.emptyView = null;
            exploreActivity.loadingView = null;
            exploreActivity.nearbyVenuesCard = null;
            exploreActivity.challenges = null;
            exploreActivity.blog = null;
            exploreActivity.shop = null;
            exploreActivity.community = null;
            exploreActivity.scrollView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
