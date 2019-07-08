package com.myfitnesspal.shared.model.v1;

import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.goals.service.NutrientGoalsUtil;
import com.myfitnesspal.shared.constants.Constants.Goals;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;

public class UserV1NutrientGoals extends UserV1PropertyBag {
    private static final int DEFAULT_CALORIE_GOAL = 1500;
    private static final Set<String> PROPERTY_KEY_WHITELIST = new HashSet();
    private Date lastRecalculatedDate;
    @Inject
    Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    private MfpDailyGoal v1Goal;
    private MfpDailyGoal v2Goal;

    static {
        for (int i = 0; i < 17; i++) {
            PROPERTY_KEY_WHITELIST.add(propertyKeyForNutrientIndex(i));
        }
    }

    public static void removeNutrientGoals(Map<String, String> map) {
        for (String remove : PROPERTY_KEY_WHITELIST) {
            map.remove(remove);
        }
    }

    /* access modifiers changed from: protected */
    public Set<String> getPropertyKeyWhitelist() {
        return PROPERTY_KEY_WHITELIST;
    }

    public UserV1NutrientGoals() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void setDefaults() {
        set(Goals.GOAL_CALORIES_PER_DAY, 1500.0f);
    }

    public void updateDefaultGoal(MfpNutrientGoal mfpNutrientGoal) {
        if (mfpNutrientGoal != null && mfpNutrientGoal.getDefaultGoal() != null) {
            Ln.d("updateDefaultGoal: setting new internal V2 goal", new Object[0]);
            this.v2Goal = mfpNutrientGoal.getDefaultGoal();
        }
    }

    public Date getLastRecalculatedDate() {
        return this.lastRecalculatedDate;
    }

    public void setLastRecalculatedDate(Date date) {
        this.lastRecalculatedDate = date;
    }

    @Deprecated
    public void recalculate(float f, MealNames mealNames) {
        this.v1Goal = ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).recalculateGoals(f, mealNames).getDefaultGoal();
        setLastRecalculatedDate(new Date());
    }

    public float get(String str) {
        MfpDailyGoal defaultGoal = getDefaultGoal();
        if (Strings.notEmpty(str) && defaultGoal != null) {
            char c = 65535;
            switch (str.hashCode()) {
                case -2029475427:
                    if (str.equals(Goals.GOAL_CHOLESTEROL_PER_DAY)) {
                        c = 5;
                        break;
                    }
                    break;
                case -1981639879:
                    if (str.equals(Goals.GOAL_POLYSAT_PER_DAY)) {
                        c = 15;
                        break;
                    }
                    break;
                case -1779064145:
                    if (str.equals(Goals.GOAL_IRON_PER_DAY)) {
                        c = 13;
                        break;
                    }
                    break;
                case -1616374511:
                    if (str.equals(Goals.GOAL_TRANS_PER_DAY)) {
                        c = 9;
                        break;
                    }
                    break;
                case -1048315557:
                    if (str.equals(Goals.GOAL_CALCIUM_PER_DAY)) {
                        c = 14;
                        break;
                    }
                    break;
                case -839852219:
                    if (str.equals(Goals.GOAL_SUGAR_PER_DAY)) {
                        c = 10;
                        break;
                    }
                    break;
                case -311679874:
                    if (str.equals(Goals.GOAL_SAT_FAT_PER_DAY)) {
                        c = 4;
                        break;
                    }
                    break;
                case -113414301:
                    if (str.equals(Goals.GOAL_VIT_A_PER_DAY)) {
                        c = 11;
                        break;
                    }
                    break;
                case 239101506:
                    if (str.equals(Goals.GOAL_MONOSAT_PER_DAY)) {
                        c = 16;
                        break;
                    }
                    break;
                case 566644069:
                    if (str.equals(Goals.GOAL_VIT_C_PER_DAY)) {
                        c = 12;
                        break;
                    }
                    break;
                case 629023981:
                    if (str.equals(Goals.GOAL_CALORIES_PER_DAY)) {
                        c = 0;
                        break;
                    }
                    break;
                case 747569184:
                    if (str.equals(Goals.GOAL_SODIUM_PER_DAY)) {
                        c = 6;
                        break;
                    }
                    break;
                case 977345076:
                    if (str.equals(Goals.GOAL_CARBS_PER_DAY)) {
                        c = 1;
                        break;
                    }
                    break;
                case 1025208104:
                    if (str.equals(Goals.GOAL_FAT_PER_DAY)) {
                        c = 3;
                        break;
                    }
                    break;
                case 1034162427:
                    if (str.equals(Goals.GOAL_FIBER_PER_DAY)) {
                        c = 8;
                        break;
                    }
                    break;
                case 1270708804:
                    if (str.equals(Goals.GOAL_POTASSIUM_PER_DAY)) {
                        c = 7;
                        break;
                    }
                    break;
                case 1828202386:
                    if (str.equals(Goals.GOAL_PROTEIN_PER_DAY)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return ((UserEnergyService) this.userEnergyService.get()).getEnergy(Energy.CALORIES, defaultGoal.getEnergy());
                case 1:
                    return defaultGoal.getCarbohydrates();
                case 2:
                    return defaultGoal.getProtein();
                case 3:
                    return defaultGoal.getFat();
                case 4:
                    return defaultGoal.getSaturatedFat();
                case 5:
                    return defaultGoal.getCholesterol();
                case 6:
                    return defaultGoal.getSodium();
                case 7:
                    return defaultGoal.getPotassium();
                case 8:
                    return defaultGoal.getFiber();
                case 9:
                    return defaultGoal.getTransFat();
                case 10:
                    return defaultGoal.getSugar();
                case 11:
                    return (float) defaultGoal.getVitaminA();
                case 12:
                    return (float) defaultGoal.getVitaminC();
                case 13:
                    return (float) defaultGoal.getIron();
                case 14:
                    return (float) defaultGoal.getCalcium();
                case 15:
                    return defaultGoal.getPolyunsaturatedFat();
                case 16:
                    return defaultGoal.getMonounsaturatedFat();
            }
        }
        Ln.e("could not find value! querying property bag for %s", str);
        return super.get(str);
    }

    public NutritionalValues getValues() {
        MfpDailyGoal defaultGoal = getDefaultGoal();
        if (defaultGoal == null) {
            return null;
        }
        return defaultGoal.toNutritionalValues((UserEnergyService) this.userEnergyService.get());
    }

    private MfpDailyGoal getDefaultGoal() {
        MfpDailyGoal mfpDailyGoal = this.v2Goal;
        if (mfpDailyGoal != null) {
            return mfpDailyGoal;
        }
        if (this.v1Goal == null) {
            this.v1Goal = createMfpDailyGoalFromV1Values();
        }
        Ln.d("getDefaultGoal: v2 goal not found yet! falling back go v1.", new Object[0]);
        return this.v1Goal;
    }

    public MfpDailyGoal createMfpDailyGoalFromV1Values() {
        MfpDailyGoal mfpDailyGoal = new MfpDailyGoal();
        mfpDailyGoal.setEnergy(new MfpMeasuredValue("calories", getV1Nutrient(0)));
        mfpDailyGoal.setCarbohydrates(getV1Nutrient(9));
        mfpDailyGoal.setProtein(getV1Nutrient(12));
        mfpDailyGoal.setFat(getV1Nutrient(1));
        mfpDailyGoal.setSaturatedFat(getV1Nutrient(2));
        mfpDailyGoal.setCholesterol(getV1Nutrient(6));
        mfpDailyGoal.setSodium(getV1Nutrient(7));
        mfpDailyGoal.setPotassium(getV1Nutrient(8));
        mfpDailyGoal.setFiber(getV1Nutrient(10));
        mfpDailyGoal.setTransFat(getV1Nutrient(5));
        mfpDailyGoal.setSugar(getV1Nutrient(11));
        mfpDailyGoal.setVitaminA((int) getV1Nutrient(13));
        mfpDailyGoal.setVitaminC((int) getV1Nutrient(14));
        mfpDailyGoal.setIron((int) getV1Nutrient(16));
        mfpDailyGoal.setCalcium((int) getV1Nutrient(15));
        mfpDailyGoal.setPolyunsaturatedFat(getV1Nutrient(3));
        mfpDailyGoal.setMonounsaturatedFat(getV1Nutrient(4));
        return mfpDailyGoal;
    }

    private float getV1Nutrient(int i) {
        return super.get(propertyKeyForNutrientIndex(i));
    }

    private static String propertyKeyForNutrientIndex(int i) {
        return String.format("goal_%s_per_day", new Object[]{NutritionalValues.getIdentifierForNutrientIndex(i)});
    }
}
