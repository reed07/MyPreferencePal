package com.myfitnesspal.feature.goals.util;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpGoalPreferences;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public class GoalPreferenceUtil {

    enum Type {
        Diary,
        Home
    }

    public static String getHomeGoalDisplay(Session session) {
        return getGoalDisplay(session, Type.Home);
    }

    public static String getDiaryGoalDisplay(Session session) {
        return getGoalDisplay(session, Type.Diary);
    }

    public static String getGoalDisplay(Session session, Type type) {
        MfpGoalPreferences goalPreferences = session.getUser().getGoalPreferences();
        Object obj = null;
        if (goalPreferences != null) {
            switch (type) {
                case Home:
                    obj = goalPreferences.getHomeGoalDisplay();
                    break;
                case Diary:
                    obj = goalPreferences.getDiaryGoalDisplay();
                    break;
            }
        } else {
            Ln.e("found a null MfpGoalPreference in User!", new Object[0]);
        }
        return Strings.toString(obj, Extras.DEFAULT_GOAL_DISPLAY);
    }

    public static String getStringForWeeklyGoal(Context context, UserWeightService userWeightService) {
        int i = (userWeightService.getGoalPerWeekWeight() > BitmapDescriptorFactory.HUE_RED ? 1 : (userWeightService.getGoalPerWeekWeight() == BitmapDescriptorFactory.HUE_RED ? 0 : -1));
        if (i == 0) {
            return context.getString(R.string.maintain_weight);
        }
        int i2 = i > 0 ? R.string.goalLosePerWeekTxt : R.string.goalGainPerWeekTxt;
        String goalPerWeekWeightString = userWeightService.getGoalPerWeekWeightString();
        return context.getString(i2, new Object[]{goalPerWeekWeightString, userWeightService.getDisplayableLbsAndKgUnitString(goalPerWeekWeightString)});
    }
}
