package com.myfitnesspal.feature.walkthrough.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.event.MealSelectedEvent;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.home.ui.fragment.HomeFragment;
import com.myfitnesspal.feature.home.util.WalkthroughHelper;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.search.event.FoodItemSelectedEvent;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.walkthrough.event.SkipLoggingWalkthroughEvent;
import com.myfitnesspal.feature.walkthrough.ui.fragment.WalkthroughFoodSearchFragment;
import com.myfitnesspal.feature.walkthrough.ui.fragment.WalkthroughServingSizeV2Fragment;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.shared.constants.Constants.ABTest.Premium.SignUpUpsell;
import com.myfitnesspal.shared.constants.Constants.Analytics.Flows;
import com.myfitnesspal.shared.constants.Constants.UpsellDisplayMode;
import com.myfitnesspal.shared.event.HomeNewsFragmentResumedEvent;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.BottomBarMixin;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Arrays;
import java.util.UUID;
import javax.inject.Inject;

public class WalkthroughLoggingActivity extends MfpActivity {
    public static final String EXTRA_FLOW_ID = "flow_id";
    private static final String FRAGMENT_HOME_NEWS = "HomeNews";
    private static final String UPSELL_DISPLAYED = "upsell_displayed";
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private String flowId;
    @Inject
    Lazy<GlobalSettingsService> globalSettingsService;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<Bus> messageBus;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<ProductService> productService;
    private boolean upsellDisplayed;
    private WalkthroughHelper walkthroughHelper;
    @Inject
    Lazy<WalkthroughUtil> walkthroughUtil;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, WalkthroughLoggingActivity.class);
    }

    public void onCreate(Bundle bundle) {
        registerMixin(new BottomBarMixin(this));
        ((BottomBarMixin) mixin(BottomBarMixin.class)).setEnabled(false);
        super.onCreate(bundle);
        setContentView((int) R.layout.logging_walk_through);
        component().inject(this);
        this.flowId = BundleUtils.getString(bundle, "flow_id", UUID.randomUUID().toString());
        showHomeWalkthrough();
    }

    private void showHomeWalkthrough() {
        MaterialUtils.removeDefaultToolbarElevation(this);
        addHomeFragment();
    }

    @Subscribe
    public void onHomeNewsFragmentResumedEvent(HomeNewsFragmentResumedEvent homeNewsFragmentResumedEvent) {
        showFABWalkthrough();
    }

    private void addHomeFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(FRAGMENT_HOME_NEWS);
        if (findFragmentByTag == null || !findFragmentByTag.isVisible()) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            if (findFragmentByTag == null) {
                findFragmentByTag = HomeFragment.newInstance(this.flowId, false);
                beginTransaction.add(R.id.container, findFragmentByTag, FRAGMENT_HOME_NEWS);
            }
            beginTransaction.show(findFragmentByTag);
            beginTransaction.commit();
        }
    }

    private void showFABWalkthrough() {
        if (this.walkthroughHelper == null) {
            this.walkthroughHelper = new WalkthroughHelper(this, this.walkthroughUtil, this.analyticsService, this.messageBus);
        }
        this.walkthroughHelper.showWalkthrough();
    }

    private void showUpsellScreenIfRequired() {
        boolean z = ((PremiumService) this.premiumService.get()).isPremiumAvailableGeographically() && getConfigService().isVariantEnabled(SignUpUpsell.NAME);
        if (!this.upsellDisplayed && z) {
            getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) this, (String) null, UpsellDisplayMode.SignUp)).startActivity();
            this.upsellDisplayed = true;
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(UPSELL_DISPLAYED, this.upsellDisplayed);
        bundle.putString("flow_id", this.flowId);
    }

    @Subscribe
    public void onSkipLoggingWalkthroughEvent(SkipLoggingWalkthroughEvent skipLoggingWalkthroughEvent) {
        getAnalyticsService().reportEvent(skipLoggingWalkthroughEvent.getSkipAnalyticsEventName());
        getNavigationHelper().withIntent(HomeActivity.newStartIntent(this)).startActivity();
        showUpsellScreenIfRequired();
    }

    @Subscribe
    public void onMealSelectedEvent(MealSelectedEvent mealSelectedEvent) {
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Flows.LOGGING, "meal", Strings.toString(mealSelectedEvent.getMealName()));
        showFoodSearchFragmentFor(mealSelectedEvent.getMealName());
    }

    @Subscribe
    public void onFoodItemSelectedEvent(FoodItemSelectedEvent foodItemSelectedEvent) {
        processAnalytics(foodItemSelectedEvent);
        navigateToEditServingSizeFragment(foodItemSelectedEvent);
    }

    private void processAnalytics(FoodItemSelectedEvent foodItemSelectedEvent) {
        MfpFood item = foodItemSelectedEvent.getItem();
        if (item != null) {
            int index = foodItemSelectedEvent.getIndex();
            boolean verified = item.getVerified();
            FoodV2Logging foodV2Logging = new FoodV2Logging(foodItemSelectedEvent.getQueryTerm(), item.getId(), item.getVersion(), index, -1, verified, SearchSource.ONLINE.getTitle());
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Flows.LOGGING, MapUtil.createMap("index", Strings.toString(Integer.valueOf(index)), "list_type", "search", "source", "online_search", "foods", new ApiJsonMapper().reverseMap((Object) Arrays.asList(new FoodV2Logging[]{foodV2Logging}))));
        }
    }

    private void navigateToEditServingSizeFragment(FoodItemSelectedEvent foodItemSelectedEvent) {
        MfpFood item = foodItemSelectedEvent.getItem();
        if (item != null) {
            WalkthroughServingSizeV2Fragment newInstance = WalkthroughServingSizeV2Fragment.newInstance(item, foodItemSelectedEvent.getMealName());
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            setFragmentAnimations(beginTransaction);
            beginTransaction.replace(R.id.container, newInstance);
            beginTransaction.addToBackStack(null);
            beginTransaction.commit();
        }
    }

    /* access modifiers changed from: protected */
    public void setFragmentAnimations(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right_100_short, R.anim.slide_out_left_100_short, R.anim.slide_in_left_100_short, R.anim.slide_out_right_100_short);
    }

    private void showFoodSearchFragmentFor(String str) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        setFragmentAnimations(beginTransaction);
        beginTransaction.replace(R.id.container, WalkthroughFoodSearchFragment.newInstance(str, true));
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();
    }
}
