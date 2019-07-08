package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.UpdateGoalsCompleteEvent;
import com.myfitnesspal.feature.goals.event.UpdateGoalsRefreshEvent;
import com.myfitnesspal.feature.goals.ui.fragment.UpdateGoalsFragment;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.premium.service.ProductService;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpMarketingOptInFragment;
import com.myfitnesspal.feature.registration.ui.fragment.SignUpMarketingOptInFragment.Mode;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.event.BusyEvent;
import com.myfitnesspal.shared.event.UpdateGoalsSetEvent;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import dagger.Lazy;
import java.util.Calendar;
import javax.inject.Inject;

public class UpdateGoals extends MfpActivity {
    private static final String MARKETING_OPT_IN_FRAGMENT = "MarketingOptInFragment";
    private static final String UPDATE_GOALS_FRAGMENT = "UpdateGoalsFragment";
    @Inject
    ActionTrackingService actionTrackingService;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private FragmentManager fm;
    private UpdateGoalsFragment fragment;
    @Inject
    Lazy<ProductService> productService;

    public String getAnalyticsScreenTag() {
        return Screens.UPDATE_GOALS;
    }

    public void onBackPressed() {
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, UpdateGoals.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.update_goals);
        component().inject(this);
        setTitle(R.string.update_goals_header);
        getToolbar().setNavigationIcon((Drawable) null);
        Calendar instance = Calendar.getInstance();
        this.actionTrackingService.registerEvent(Events.REACTIVATE);
        this.actionTrackingService.appendToEvent(Events.REACTIVATE, "date", instance.getTime().toString());
        this.actionTrackingService.appendToEvent(Events.REACTIVATE, "user", getSession().getUser().getUsername());
        this.fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = this.fm.beginTransaction();
        this.fragment = (UpdateGoalsFragment) this.fm.findFragmentByTag(UPDATE_GOALS_FRAGMENT);
        if (this.fragment == null) {
            this.fragment = new UpdateGoalsFragment();
            beginTransaction.add(R.id.container, this.fragment, UPDATE_GOALS_FRAGMENT);
        }
        beginTransaction.show(this.fragment).commit();
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.WELCOME_BACK_SCREEN_VIEW);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Subscribe
    public void onUpdateGoalsSet(UpdateGoalsSetEvent updateGoalsSetEvent) {
        getSession().getUser().setShouldUpdateGoalsAndUpdateProperty(false);
        getMessageBus().post(new StartSyncEvent());
        getMessageBus().post(new BusyEvent(1, false));
        this.fm.beginTransaction().setCustomAnimations(R.anim.slide_in_right_100_short, R.anim.slide_out_left_100_short).remove(this.fragment).commit();
        getNavigationHelper().finishActivityAfterNavigation().withIntent(HomeActivity.newStartIntent(this)).startActivity();
    }

    @Subscribe
    public void onNavigateToMarketingOptIn(NavigateToMarketingOptInEvent navigateToMarketingOptInEvent) {
        this.fm.beginTransaction().setCustomAnimations(R.anim.slide_in_right_100_short, R.anim.slide_out_left_100_short).remove(this.fragment).commit();
        FragmentTransaction beginTransaction = this.fm.beginTransaction();
        SignUpMarketingOptInFragment signUpMarketingOptInFragment = (SignUpMarketingOptInFragment) this.fm.findFragmentByTag(MARKETING_OPT_IN_FRAGMENT);
        if (signUpMarketingOptInFragment == null) {
            signUpMarketingOptInFragment = SignUpMarketingOptInFragment.newInstance(Mode.Reactivation);
            beginTransaction.add(R.id.container, signUpMarketingOptInFragment, MARKETING_OPT_IN_FRAGMENT);
        }
        beginTransaction.show(signUpMarketingOptInFragment).commit();
    }

    public void onUpPressed() {
        super.onUpPressed();
        postEvent(new UpdateGoalsCompleteEvent());
    }

    @Produce
    public UpdateGoalsRefreshEvent produceUpdateGoalsRefreshEvent() {
        return new UpdateGoalsRefreshEvent();
    }
}
