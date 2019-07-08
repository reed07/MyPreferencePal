package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.ui.dialog.NetEnergyGoalDialogFragment.NetCalorieGoalDialogFragmentListener;
import com.myfitnesspal.feature.goals.ui.fragment.EditCustomMacroGoalsFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.ExtrasUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class EditCustomMacroGoalsActivity extends MfpActivity implements NetCalorieGoalDialogFragmentListener {
    private EditCustomMacroGoalsFragment fragment;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public String getAnalyticsScreenTag() {
        return Screens.CALORIE_MACRO_GOALS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, EditCustomMacroGoalsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setTitle(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.TITLE_ACTIVITY_MACRO_GOALS, this.userEnergyService));
        setContentView((int) R.layout.activity_calorie_macro_goals);
        this.fragment = EditCustomMacroGoalsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.calorieMacroFragmentContainer, this.fragment).commit();
    }

    public void onUpPressed() {
        finish();
    }

    public void onCalorieGoalUpdatedDialog(float f) {
        EditCustomMacroGoalsFragment editCustomMacroGoalsFragment = this.fragment;
        if (editCustomMacroGoalsFragment != null) {
            editCustomMacroGoalsFragment.updateEnergyGoalFromDialog(f);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            return;
        }
        if (ExtrasUtils.hasExtra(intent, Extras.EVENT_SOURCE)) {
            Object obj = ExtrasUtils.get(intent, Extras.EVENT_SOURCE);
            if (obj instanceof MacroGoalsUpdatedEvent) {
                this.fragment.recalculateAndUpdateGoals(BitmapDescriptorFactory.HUE_RED, (MacroGoalsUpdatedEvent) obj);
            }
        } else if (!ExtrasUtils.hasExtra(intent, Extras.ENERGY_CHANGED) && !ExtrasUtils.hasExtra(intent, Extras.CUSTOM_GOAL_DELETED)) {
        } else {
            if (ExtrasUtils.getBoolean(intent, Extras.ENERGY_CHANGED, false) || ExtrasUtils.getBoolean(intent, Extras.CUSTOM_GOAL_DELETED, false)) {
                this.fragment.showCustomGoalsChangeDialog();
            }
        }
    }
}
