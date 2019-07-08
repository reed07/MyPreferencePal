package com.myfitnesspal.feature.profile.ui.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.ui.activity.Goals;
import com.myfitnesspal.feature.goals.util.GoalPreferenceUtil;
import com.myfitnesspal.feature.profile.analytics.MeAnalytics.CardType;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.OfflineData;
import com.myfitnesspal.shared.model.unitconv.LocalizedEnergy;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.ViewUtils;

public class GoalsCard extends MeCardBase {
    private TextView dailyGoalEnergy;
    private TextView dailyGoalHeader;
    private TextView dailyGoalMacros;
    private TextView goalWeight;
    private TextView weeklyChange;

    public String getAnalyticsType() {
        return CardType.GOALS;
    }

    /* access modifiers changed from: protected */
    public int getButtonTextId() {
        return R.string.me_card_button_update_goals;
    }

    /* access modifiers changed from: protected */
    public int getContentLayoutId() {
        return R.layout.me_card_goals;
    }

    /* access modifiers changed from: protected */
    public int getLeftBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRightBadgeLayoutId() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getTitleTextId() {
        return R.string.me_card_title_goals;
    }

    public GoalsCard(@NonNull Context context) {
        super(context);
    }

    public GoalsCard(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GoalsCard(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onInflated() {
        this.goalWeight = (TextView) findViewById(R.id.goalWeight);
        this.weeklyChange = (TextView) findViewById(R.id.weeklyChange);
        this.dailyGoalHeader = (TextView) findViewById(R.id.dailyGoalHeader);
        this.dailyGoalEnergy = (TextView) findViewById(R.id.dailyGoalEnergy);
        this.dailyGoalMacros = (TextView) findViewById(R.id.dailyGoalMacros);
        setOnContentViewClickListener(new OnClickListener() {
            public void onClick(View view) {
                GoalsCard.this.getNavigationHelper().withIntent(Goals.newStartIntent(GoalsCard.this.getContext())).startActivity();
            }
        });
    }

    public void redraw(UserWeightService userWeightService, UserEnergyService userEnergyService, OfflineData offlineData) {
        if (offlineData != null) {
            ViewUtils.setVisible(this.contentContainer);
            Context context = getContext();
            MfpDailyGoal dailyGoal = offlineData.getDailyGoal();
            Weight userCurrentWeightUnit = userWeightService.getUserCurrentWeightUnit();
            Energy userCurrentEnergyUnit = userEnergyService.getUserCurrentEnergyUnit();
            this.goalWeight.setText(LocalizedWeight.getDisplayString(context, offlineData.getGoalWeight(), userCurrentWeightUnit));
            UserWeightService userWeightService2 = userWeightService;
            this.weeklyChange.setText(GoalPreferenceUtil.getStringForWeeklyGoal(context, userWeightService));
            this.dailyGoalHeader.setText(userCurrentEnergyUnit == Energy.KILOJOULES ? R.string.me_card_daily_energy_kilojoules : R.string.me_card_daily_energy_calories);
            this.dailyGoalEnergy.setText(LocalizedEnergy.getDisplayString(context, LocalizedEnergy.fromMeasuredValue(dailyGoal.getEnergy()), userCurrentEnergyUnit));
            this.dailyGoalMacros.setText(NutritionUtils.getNutritionalMacrosDetails(context, (double) dailyGoal.getCarbohydrates(), (double) dailyGoal.getFat(), (double) dailyGoal.getProtein(), 1.0d, " / "));
            return;
        }
        ViewUtils.setInvisible(this.contentContainer);
    }
}
