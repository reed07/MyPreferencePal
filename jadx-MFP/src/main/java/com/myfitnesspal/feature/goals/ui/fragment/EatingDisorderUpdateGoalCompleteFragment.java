package com.myfitnesspal.feature.goals.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.UpdateGoalsCompleteEvent;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import dagger.Lazy;
import java.text.DecimalFormat;
import javax.inject.Inject;

public class EatingDisorderUpdateGoalCompleteFragment extends MfpFragment {
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
        return layoutInflater.inflate(R.layout.eating_disorder_update_goals_complete, null);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ((TextView) view.findViewById(R.id.txt_goal_daily_calories)).setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.UPDATE_GOAL_COMPLETE_YOUR_DAILY_GOAL, this.userEnergyService.get()));
        ((TextView) view.findViewById(R.id.txt_goal_calories_value)).setText(String.format("%s %s", new Object[]{((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).getDefaultEnergyGoalForDisplay(), ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnitString()}));
        double goalWeightInPounds = (double) ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds();
        String str = "";
        if (((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit() == Weight.POUNDS) {
            str = getString(R.string.weight_in_pounds, Long.valueOf(Math.round(goalWeightInPounds)));
        } else if (((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit() == Weight.KILOGRAMS) {
            str = getString(R.string.weight_in_kilograms, new DecimalFormat("#,##0.0").format(UnitsUtils.getKilogramsFromPounds(goalWeightInPounds)));
        } else if (((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit() == Weight.STONES_POUNDS) {
            String[] stonesPoundsFromPounds = UnitsUtils.getStonesPoundsFromPounds(goalWeightInPounds);
            str = getString(R.string.weight_in_stone_pounds, stonesPoundsFromPounds[0], stonesPoundsFromPounds[1]);
        }
        ((TextView) view.findViewById(R.id.txt_weight_loss_goal)).setText(str);
        view.findViewById(R.id.btn_ok).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                EatingDisorderUpdateGoalCompleteFragment.this.getMessageBus().post(new UpdateGoalsCompleteEvent());
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
}
