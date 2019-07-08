package com.myfitnesspal.shared.model.v1;

import android.content.SharedPreferences;
import com.myfitnesspal.shared.constants.Constants.Exercise.ActivityLevel;
import com.myfitnesspal.shared.constants.Constants.Goals;
import com.myfitnesspal.shared.constants.Constants.Preference;
import com.myfitnesspal.shared.constants.SharedConstants.UserGoals;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.NumberUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserV1GoalPreferences extends UserV1PropertyBag {
    private static final float DEFAULT_PAL_MULTIPLIER = 1.0f;
    private static final Map<String, Float> PAL_MULTIPLIER_MAP = new HashMap();
    private static final Set<String> PROPERTY_KEYS = new HashSet();
    private SharedPreferences prefs;

    static {
        PAL_MULTIPLIER_MAP.put(ActivityLevel.SEDENTARY, Float.valueOf(1.25f));
        PAL_MULTIPLIER_MAP.put(ActivityLevel.LIGHTLY_ACTIVE, Float.valueOf(1.4f));
        PAL_MULTIPLIER_MAP.put(ActivityLevel.ACTIVE, Float.valueOf(1.6f));
        PAL_MULTIPLIER_MAP.put(ActivityLevel.VERY_ACTIVE, Float.valueOf(1.8f));
        PROPERTY_KEYS.add(Goals.GOAL_CALORIES_BURNED_PER_WEEK);
        PROPERTY_KEYS.add(Goals.WORKOUTS_PER_WEEK);
        PROPERTY_KEYS.add(Goals.MIN_PER_WORKOUT);
        PROPERTY_KEYS.add(Goals.GOAL_LOSS_PER_WEEK);
        PROPERTY_KEYS.add(Goals.PROJECTED_POUNDS_LOST_PER_WEEK);
    }

    public UserV1GoalPreferences(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    public void setDefaults() {
        setWorkoutsPerWeek(0);
        setMinutesPerWorkout(0);
        setGoalLossPerWeek(1.0f);
    }

    /* access modifiers changed from: protected */
    public Set<String> getPropertyKeyWhitelist() {
        return PROPERTY_KEYS;
    }

    public void recalculate(UserV1 userV1, float f) {
        set(Goals.GOAL_LOSS_PER_WEEK, getGoalLossPerWeek());
        set(Goals.PROJECTED_POUNDS_LOST_PER_WEEK, calculateProjectedWeightLossForUser(userV1, (double) f));
    }

    public void setRaisedMaleCalorieGoal(Boolean bool) {
        this.prefs.edit().putBoolean(Preference.RAISED_MALE_CALORIE_GOAL, bool.booleanValue()).apply();
    }

    public Boolean isMaleCalorieGoalRaised() {
        return Boolean.valueOf(this.prefs.getBoolean(Preference.RAISED_MALE_CALORIE_GOAL, false));
    }

    public void setRaisedMaleCalorieGoalAlertShown(Boolean bool) {
        this.prefs.edit().putBoolean(Preference.RAISED_MALE_CALORIE_GOAL_ALERT_SHOWN, bool.booleanValue()).apply();
    }

    public Boolean getRaisedMaleCalorieGoalAlertShown() {
        return Boolean.valueOf(this.prefs.getBoolean(Preference.RAISED_MALE_CALORIE_GOAL_ALERT_SHOWN, false));
    }

    public float getGoalLossPerWeek() {
        return get(Goals.GOAL_LOSS_PER_WEEK);
    }

    public void setGoalLossPerWeek(float f) {
        set(Goals.GOAL_LOSS_PER_WEEK, f);
    }

    public int getWorkoutsPerWeek() {
        return (int) get(Goals.WORKOUTS_PER_WEEK);
    }

    public void setWorkoutsPerWeek(int i) {
        set(Goals.WORKOUTS_PER_WEEK, (float) i);
    }

    public int getMinutesPerWorkout() {
        return (int) get(Goals.MIN_PER_WORKOUT);
    }

    public void setMinutesPerWorkout(int i) {
        set(Goals.MIN_PER_WORKOUT, (float) i);
    }

    public float calculateCalorieGoalForUser(UserV1 userV1) {
        return modifyGoalCaloriesAccordingToVariant(userV1, calculateBMRForUser(userV1) - (getGoalLossPerWeek() * 500.0f));
    }

    public float calculateCalorieGoalForUserForGoalLossPerWeek(UserV1 userV1, float f) {
        return modifyGoalCaloriesAccordingToVariant(userV1, calculateBMRForUser(userV1) - (f * 500.0f));
    }

    private static float calculateProjectedWeightLossForUser(UserV1 userV1, double d) {
        return (float) ((((double) calculateBMRForUser(userV1)) - d) / 500.0d);
    }

    private float modifyGoalCaloriesAccordingToVariant(UserV1 userV1, float f) {
        boolean z = !userV1.getProfile().isFemale().booleanValue();
        float f2 = z ? UserGoals.LOW_CALORIES_MEN : UserGoals.LOW_CALORIES_WOMEN;
        if (f < f2) {
            if (z) {
                setRaisedMaleCalorieGoal(Boolean.valueOf(true));
            }
            f = f2;
        }
        return NumberUtils.roundToNearestPlace(f, 10.0f);
    }

    private static float calculateBMRForUser(UserV1 userV1) {
        UserProfile profile = userV1.getProfile();
        return (PAL_MULTIPLIER_MAP.containsKey(profile.getLifestyleName()) ? ((Float) PAL_MULTIPLIER_MAP.get(profile.getLifestyleName())).floatValue() : 1.0f) * (((profile.getCurrentWeight().getKilograms() * 10.0f) + (profile.getHeight().centimetres() * 6.25f)) - ((((float) DateTimeUtils.getAgeInYears(profile.getDateOfBirth())) * 5.0f) + (profile.isFemale().booleanValue() ? 161.0f : -5.0f)));
    }
}
