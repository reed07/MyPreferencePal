package com.myfitnesspal.feature.goals.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.WeightLossGoalDialogEvent;
import com.myfitnesspal.feature.goals.ui.adapter.GoalItem;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class WeightGoalDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    protected Lazy<Session> session;
    @Inject
    protected UserWeightService userWeightService;
    private List<GoalItem> weightGoalList;

    public static WeightGoalDialogFragment newInstance() {
        return new WeightGoalDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        component().inject(this);
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        this.weightGoalList = populateWeightGoalList(dialogContextThemeWrapper, GoalItem.getCurrentlySelected(((Session) this.session.get()).getUser().getUserV1GoalPreferences().getGoalLossPerWeek()), this.userWeightService.getUserCurrentWeightUnit() == Weight.KILOGRAMS);
        return new MfpAlertDialogBuilder(dialogContextThemeWrapper).setSingleChoiceItems(this.weightGoalList, (OnItemClickListener) new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                WeightGoalDialogFragment.this.refreshUI(i);
            }
        }).setTitle((int) R.string.weight_loss_goal).setPositiveButton((int) R.string.setBtn, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                WeightGoalDialogFragment.this.doSave();
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).setDismissOnItemClick(false).create();
    }

    /* access modifiers changed from: private */
    public void refreshUI(int i) {
        int i2 = 0;
        for (GoalItem state : this.weightGoalList) {
            state.setState(i2 == i);
            i2++;
        }
    }

    /* access modifiers changed from: private */
    public void doSave() {
        for (GoalItem goalItem : this.weightGoalList) {
            if (goalItem.getState()) {
                this.messageBus.post(new WeightLossGoalDialogEvent(goalItem));
                return;
            }
        }
    }

    private List<GoalItem> populateWeightGoalList(Context context, GoalItem.Weight weight, boolean z) {
        GoalItem.Weight weight2 = weight;
        boolean z2 = z;
        ArrayList arrayList = new ArrayList();
        if (context != null) {
            Resources resources = context.getResources();
            Weight weight3 = z2 ? Weight.KILOGRAMS : Weight.POUNDS;
            float f = z2 ? 1.0f : 2.0f;
            boolean z3 = true;
            arrayList.add(new GoalItem(resources.getString(R.string.goalLoseTwoTxt, new Object[]{NumberUtils.localeStringFromFloat(f), this.userWeightService.getDisplayableLbsAndKgUnitString(f, weight3)}), weight2 == GoalItem.Weight.LOSE_TWO_POUNDS, GoalItem.Weight.LOSE_TWO_POUNDS, z2));
            float f2 = z2 ? 0.75f : 1.5f;
            arrayList.add(new GoalItem(resources.getString(R.string.goalLoseOneHalfTxt, new Object[]{NumberUtils.localeStringFromFloat(f2), this.userWeightService.getDisplayableLbsAndKgUnitString(f2, weight3)}), weight2 == GoalItem.Weight.LOSE_ONE_HALF_POUNDS, GoalItem.Weight.LOSE_ONE_HALF_POUNDS, z2));
            float f3 = 0.5f;
            float f4 = z2 ? 0.5f : 1.0f;
            arrayList.add(new GoalItem(resources.getString(R.string.goalLoseOneTxt, new Object[]{NumberUtils.localeStringFromFloat(f4), this.userWeightService.getDisplayableLbsAndKgUnitString(f4, weight3)}), weight2 == GoalItem.Weight.LOSE_ONE_POUND, GoalItem.Weight.LOSE_ONE_POUND, z2));
            float f5 = 0.25f;
            float f6 = z2 ? 0.25f : 0.5f;
            arrayList.add(new GoalItem(resources.getString(R.string.goalLoseHalfTxt, new Object[]{NumberUtils.localeStringFromFloat(f6), this.userWeightService.getDisplayableLbsAndKgUnitString(f6, weight3)}), weight2 == GoalItem.Weight.LOSE_HALF_POUND, GoalItem.Weight.LOSE_HALF_POUND, z2));
            arrayList.add(new GoalItem(resources.getString(R.string.goalMaintainTxt), weight2 == GoalItem.Weight.MAINTAIN_WEIGHT, GoalItem.Weight.MAINTAIN_WEIGHT, z2));
            if (!z2) {
                f5 = 0.5f;
            }
            arrayList.add(new GoalItem(resources.getString(R.string.goalGainHalfTxt, new Object[]{NumberUtils.localeStringFromFloat(f5), this.userWeightService.getDisplayableLbsAndKgUnitString(f5, weight3)}), weight2 == GoalItem.Weight.GAIN_HALF_POUND, GoalItem.Weight.GAIN_HALF_POUND, z2));
            if (!z2) {
                f3 = 1.0f;
            }
            String string = resources.getString(R.string.goalGainOneTxt, new Object[]{NumberUtils.localeStringFromFloat(f3), this.userWeightService.getDisplayableLbsAndKgUnitString(f3, weight3)});
            if (weight2 != GoalItem.Weight.GAIN_ONE_POUND) {
                z3 = false;
            }
            arrayList.add(new GoalItem(string, z3, GoalItem.Weight.GAIN_ONE_POUND, z2));
        }
        return arrayList;
    }
}
