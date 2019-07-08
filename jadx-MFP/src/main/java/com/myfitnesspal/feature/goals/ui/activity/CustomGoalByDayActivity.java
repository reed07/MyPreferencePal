package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.ui.dialog.NetEnergyGoalDialogFragment.NetCalorieGoalDialogFragmentListener;
import com.myfitnesspal.feature.goals.ui.fragment.CustomGoalByDayFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import java.util.ArrayList;
import java.util.Collection;

public class CustomGoalByDayActivity extends MfpActivity implements NetCalorieGoalDialogFragmentListener {
    private static final String FRAGMENT_TAG;
    private static final String TAG = CustomGoalByDayActivity.class.getCanonicalName();
    private CustomGoalByDayFragment customGoalByDayFragment;

    public String getAnalyticsScreenTag() {
        return Screens.CUSTOM_CALORIE_MACRO_GOAL_BY_DAY;
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(TAG);
        sb.append(".CustomGoalByDayFragment");
        FRAGMENT_TAG = sb.toString();
    }

    public static Intent newStartIntent(Context context, ArrayList<String> arrayList) {
        return new Intent(context, CustomGoalByDayActivity.class).putStringArrayListExtra(Extras.CUSTOM_DAYS, arrayList);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.fragment_container);
        Intent intent = getIntent();
        if (bundle == null) {
            ArrayList stringArrayList = ExtrasUtils.getStringArrayList(intent, Extras.CUSTOM_DAYS);
            this.customGoalByDayFragment = CustomGoalByDayFragment.newInstance(stringArrayList);
            if (CollectionUtils.notEmpty((Collection<?>) stringArrayList)) {
                setTitle(R.string.edit_custom_goal);
            }
            getSupportFragmentManager().beginTransaction().add(R.id.container, this.customGoalByDayFragment, FRAGMENT_TAG).commit();
            return;
        }
        this.customGoalByDayFragment = (CustomGoalByDayFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }

    public void onCalorieGoalUpdatedDialog(float f) {
        this.customGoalByDayFragment.onCalorieGoalUpdatedDialog(f);
    }

    public void onUpPressed() {
        finish();
    }
}
