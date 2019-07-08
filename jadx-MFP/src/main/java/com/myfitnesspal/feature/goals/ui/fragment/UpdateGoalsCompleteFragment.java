package com.myfitnesspal.feature.goals.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.UpdateGoalsCompleteEvent;
import com.myfitnesspal.feature.goals.event.UpdateGoalsRefreshEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Subscribe;
import dagger.Lazy;
import javax.inject.Inject;

public class UpdateGoalsCompleteFragment extends MfpFragment {
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    @Inject
    Lazy<UserWeightService> userWeightService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.update_goals_complete, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.btn_ok).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                UpdateGoalsCompleteFragment.this.getMessageBus().post(new UpdateGoalsCompleteEvent());
            }
        });
    }

    public void onResume() {
        super.onResume();
        getMessageBus().register(this);
    }

    public void onPause() {
        super.onPause();
        getMessageBus().unregister(this);
    }

    @Subscribe
    public void onFragmentRefresh(UpdateGoalsRefreshEvent updateGoalsRefreshEvent) {
        View view = getView();
        ((TextView) view.findViewById(R.id.txt_goal_daily_calories)).setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.UPDATE_GOAL_COMPLETE_YOUR_DAILY_GOAL, this.userEnergyService.get()));
        boolean z = false;
        ((TextView) view.findViewById(R.id.txt_goal_calories_value)).setText(String.format("%s %s", new Object[]{((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDefaultEnergyGoalForDisplay(), ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnitString()}));
        String goalPerWeekWeightString = ((UserWeightService) this.userWeightService.get()).getGoalPerWeekWeightString();
        ((TextView) view.findViewById(R.id.txt_weight_loss_goal)).setText(getString(R.string.per_week, goalPerWeekWeightString, ((UserWeightService) this.userWeightService.get()).getDisplayableLbsAndKgUnitString(goalPerWeekWeightString)));
        float goalLossPerWeek = getSession().getUser().getUserV1GoalPreferences().getGoalLossPerWeek();
        if (goalLossPerWeek >= BitmapDescriptorFactory.HUE_RED) {
            z = true;
        }
        ((TextView) view.findViewById(R.id.txt_goal_gain_or_lose)).setText(z ? R.string.update_goals_complete_projected_loss_header : R.string.update_goals_complete_projected_gain_header);
        ((TextView) view.findViewById(R.id.txt_you_should_gain_or_lose)).setText(z ? R.string.update_goals_complete_you_should_lose : R.string.update_goals_complete_you_should_gain);
        ((TextView) view.findViewById(R.id.txt_amount_gain_or_lose)).setText(((UserWeightService) this.userWeightService.get()).getFiveWeekChangeString((double) (Math.abs(goalLossPerWeek) * 5.0f)));
        ((TextView) view.findViewById(R.id.txt_update_goals_note)).setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(z ? LocalizedStrings.UPDATE_GOAL_COMPLETE_NOTE_LOSS : LocalizedStrings.UPDATE_GOAL_COMPLETE_NOTE_GAIN, this.userEnergyService.get()));
    }
}
