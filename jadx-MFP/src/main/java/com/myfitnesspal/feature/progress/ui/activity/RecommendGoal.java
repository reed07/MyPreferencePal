package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.feature.goals.ui.activity.EatingDisorderAdjustGoalComplete;
import com.myfitnesspal.feature.goals.util.EatingDisorderMaleCalorieGoalAlertUtil;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.progress.ui.fragment.LegacyWeightPickerFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.event.DialogWeightEvent;
import com.myfitnesspal.shared.event.GoalsRecalculatedEvent;
import com.myfitnesspal.shared.event.UpdateGoalsSetEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.UserWeight;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.task.RecalculateNutrientGoalsTask;
import com.myfitnesspal.shared.task.RecalculateNutrientGoalsTask.CompletedEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.myfitnesspal.shared.util.UpdateWeightProxy;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import dagger.Lazy;
import java.text.DecimalFormat;
import javax.inject.Inject;

public class RecommendGoal extends MfpActivity {
    private static final boolean GOAL_UPDATE_FROM_SETTINGS = false;
    static final String WEIGHT_PICKER = "weight_picker";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<UserHeightService> userHeightService;
    @Inject
    Lazy<UserWeightService> userWeightService;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, RecommendGoal.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.recommend_new_goal_material);
        ((UserWeightService) this.userWeightService.get()).getBMI();
        double currentHeightInInches = ((UserHeightService) this.userHeightService.get()).getCurrentHeightInInches();
        TextView textView = (TextView) findById(R.id.tvYourCurrentBmi);
        double calculateWeight = UpdateWeightProxy.calculateWeight(18.5d, currentHeightInInches);
        double calculateWeight2 = UpdateWeightProxy.calculateWeight(25.0d, currentHeightInInches);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.ED_LOW_CURRENT_WEIGHT_SHOW_GOAL_RECOMMENDATION_SCREEN);
        float goalWeightInPounds = ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds();
        String str = "";
        DecimalFormat decimalFormat = new DecimalFormat("#");
        ((TextView) findById(R.id.tvSuggestedWeight)).setText(getString(R.string.would_you_like_to_change_your_goal_weight));
        TextView textView2 = (TextView) findById(R.id.tvUnderweight);
        String str2 = "";
        if (((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit() == Weight.KILOGRAMS) {
            DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
            String format = decimalFormat2.format(UnitsUtils.getKilogramsFromPounds(calculateWeight));
            str2 = getString(R.string.your_current_goal_is_kilos, new Object[]{decimalFormat2.format(UnitsUtils.getKilogramsFromPounds((double) goalWeightInPounds))});
            str = getString(R.string.a_normal_weight_range_kilos, new Object[]{format, decimalFormat2.format(UnitsUtils.getKilogramsFromPounds(calculateWeight2)), format});
        } else if (((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit() == Weight.POUNDS) {
            String format2 = decimalFormat.format(calculateWeight);
            str2 = getString(R.string.your_current_goal_is_pounds, new Object[]{decimalFormat.format((double) goalWeightInPounds)});
            str = getString(R.string.a_normal_weight_range_pounds, new Object[]{format2, decimalFormat.format(calculateWeight2), format2});
        } else if (((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit() == Weight.STONES_POUNDS) {
            String[] stonesPoundsFromPounds = UnitsUtils.getStonesPoundsFromPounds((double) goalWeightInPounds);
            str2 = getString(R.string.your_current_goal_is_stones, new Object[]{stonesPoundsFromPounds[0], stonesPoundsFromPounds[1]});
            String[] stonesPoundsFromPounds2 = UnitsUtils.getStonesPoundsFromPounds(calculateWeight);
            String[] stonesPoundsFromPounds3 = UnitsUtils.getStonesPoundsFromPounds(calculateWeight2);
            str = getString(R.string.a_normal_weight_range_stones, new Object[]{stonesPoundsFromPounds2[0], stonesPoundsFromPounds2[1], stonesPoundsFromPounds3[0], stonesPoundsFromPounds3[1], stonesPoundsFromPounds2[0], stonesPoundsFromPounds2[1]});
        }
        textView.setText(str2);
        textView2.setText(str);
        if (((double) ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds()) > calculateWeight) {
            findViewById(R.id.btChangeWeightGoal).setVisibility(8);
            findViewById(R.id.btNoThanks).setVisibility(8);
        }
        View findViewById = findViewById(R.id.btChangeWeightGoal);
        final double d = calculateWeight;
        final double d2 = calculateWeight2;
        AnonymousClass1 r0 = new OnClickListener() {
            public void onClick(View view) {
                ((AnalyticsService) RecommendGoal.this.analyticsService.get()).reportEvent(Events.ED_GOAL_RECOMMENDATION_SCREEN_CLICK_CHANGE_GOAL_WEIGHT);
                LegacyWeightPickerFragment.newInstance(WeightType.GOAL, (double) ((UserWeightService) RecommendGoal.this.userWeightService.get()).getGoalWeightInPounds(), Math.ceil(d), Math.floor(d2), false).show(RecommendGoal.this.getSupportFragmentManager(), RecommendGoal.WEIGHT_PICKER);
            }
        };
        findViewById.setOnClickListener(r0);
        findViewById(R.id.btNoThanks).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                RecommendGoal.this.handleNoThanks();
            }
        });
    }

    @Subscribe
    public void onDialogWeightEvent(DialogWeightEvent dialogWeightEvent) {
        float weight = dialogWeightEvent.getWeight();
        float currentWeightInPounds = ((UserWeightService) this.userWeightService.get()).getCurrentWeightInPounds();
        ((UserWeightService) this.userWeightService.get()).setWeight(weight, WeightType.GOAL);
        User user = getSession().getUser();
        user.getProfile().setGoalWeight(new UserWeight(weight));
        user.updatePropertyNamed(Basic.GOAL_WEIGHT_IN_POUNDS);
        float goalLossPerWeek = user.getUserV1GoalPreferences().getGoalLossPerWeek();
        Weight userCurrentWeightUnit = ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit();
        if (currentWeightInPounds < weight && ((double) goalLossPerWeek) > 0.0d) {
            user.getUserV1GoalPreferences().setGoalLossPerWeek(userCurrentWeightUnit == Weight.KILOGRAMS ? -0.6f : -0.5f);
        } else if (((double) goalLossPerWeek) < 0.0d) {
            user.getUserV1GoalPreferences().setGoalLossPerWeek(userCurrentWeightUnit == Weight.KILOGRAMS ? 1.1f : 1.0f);
        }
        setBusy(true);
        new RecalculateNutrientGoalsTask(this.nutrientGoalsUtil, this.diaryService, this.nutrientGoalService, this.session).run(getRunner());
    }

    @Subscribe
    public void onNutrientGoalsRecalculated(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
            if (completedEvent.successful()) {
                getMessageBus().post(new UpdateGoalsSetEvent());
                getNavigationHelper().withIntent(EatingDisorderAdjustGoalComplete.newStartIntent(this)).startActivity();
                return;
            }
            RecalculateNutrientGoalsTask.showErrorDialogIfFailed((MfpActivity) this, completedEvent);
        }
    }

    @Subscribe
    public void onGoalsRecalculatedEvent(GoalsRecalculatedEvent goalsRecalculatedEvent) {
        new EatingDisorderMaleCalorieGoalAlertUtil(getSession(), getNavigationHelper(), this).showRaisedMaleCalorieGoalAlertIfNecessary();
    }

    public void onUpPressed() {
        if (!checkGoHomeInstead()) {
            super.onUpPressed();
        }
    }

    public void onBackPressed() {
        if (!checkGoHomeInstead()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void handleNoThanks() {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.ED_GOAL_RECOMMENDATION_SCREEN_CLICK_NO_THANKS);
        checkGoHomeInstead();
        setResult(0);
        finish();
    }

    private boolean checkGoHomeInstead() {
        boolean z = ExtrasUtils.getBoolean(getIntent(), Extras.GO_HOME_INSTEAD_OF_BACK);
        if (z) {
            getNavigationHelper().finishActivityAfterNavigation().withIntent(HomeActivity.newStartIntent(this)).startActivity();
        }
        return z;
    }
}
