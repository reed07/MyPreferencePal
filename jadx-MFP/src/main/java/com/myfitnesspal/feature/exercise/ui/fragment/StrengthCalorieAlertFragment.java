package com.myfitnesspal.feature.exercise.ui.fragment;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;

public class StrengthCalorieAlertFragment {
    public static final String STRENGTH_CALORIE_FRAGMENT = "strength_calorie_fragment";

    public static AlertDialogFragment getStrengthCalorieAlertFragment(final Context context, final NavigationHelper navigationHelper) {
        return (AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.strength_calorie_reason)).setPositiveText(R.string.learn_more, new DialogPositiveListener() {
            public void onClick(Object obj) {
                NavigationHelper navigationHelper = navigationHelper;
                Context context = context;
                navigationHelper.withIntent(Faq.newStartIntent(context, 110, context.getString(R.string.faq))).startActivity();
            }
        });
    }
}
