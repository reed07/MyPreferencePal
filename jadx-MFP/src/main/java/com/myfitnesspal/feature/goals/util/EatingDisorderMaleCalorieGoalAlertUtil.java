package com.myfitnesspal.feature.goals.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Settings.App.URLs;
import com.myfitnesspal.shared.model.v1.UserV1GoalPreferences;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;

public class EatingDisorderMaleCalorieGoalAlertUtil {
    private final Context context;
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;
    private final Session session;

    public EatingDisorderMaleCalorieGoalAlertUtil(Session session2, NavigationHelper navigationHelper2, Context context2) {
        this.session = session2;
        this.navigationHelper = navigationHelper2;
        this.context = context2;
    }

    public void showRaisedMaleCalorieGoalAlertIfNecessary() {
        UserV1GoalPreferences userV1GoalPreferences = this.session.getUser().getUserV1GoalPreferences();
        if (userV1GoalPreferences.isMaleCalorieGoalRaised().booleanValue() && !userV1GoalPreferences.getRaisedMaleCalorieGoalAlertShown().booleanValue()) {
            new MfpAlertDialogBuilder(this.context).setMessage((int) R.string.raised_male_calorie_goal).setTitle((CharSequence) null).setPositiveButton((int) R.string.ok, (OnClickListener) null).setNegativeButton((int) R.string.learn_more, (OnClickListener) new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    EatingDisorderMaleCalorieGoalAlertUtil.this.navigationHelper.withIntent(SharedIntents.newUriIntent(URLs.ED_LEARN_MORE_URL)).startActivity();
                }
            }).show();
            userV1GoalPreferences.setRaisedMaleCalorieGoalAlertShown(Boolean.valueOf(true));
        }
    }
}
