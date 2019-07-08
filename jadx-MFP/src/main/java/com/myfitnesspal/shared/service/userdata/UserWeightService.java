package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.ui.adapter.GoalItem;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.Goals;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Units;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v1.UserV1GoalPreferences;
import com.myfitnesspal.shared.model.v1.UserWeight;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue;
import com.myfitnesspal.shared.model.v2.MfpMeasuredValue.Unit;
import com.myfitnesspal.shared.model.v2.MfpProfile;
import com.myfitnesspal.shared.model.v2.UserV2;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.session.UserV2Service;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.myfitnesspal.shared.validation.Validator;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.Ln;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Named;

public class UserWeightService extends SimpleAsyncServiceBase {
    private static final String KILOGRAM_PREF = "_kilogram";
    private static final int MAX_THREADS = 2;
    public static final float MAX_VALID_WEIGHT_VALUE = 999.0f;
    private static final String POUND_PREF = "_pound";
    private static final String STONE_PREF = "_stone";
    private static final String TAG = "UserWeightService";
    private final Lazy<ConfigService> configService;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Lazy<MeasurementsService> measurementsService;
    private final Lazy<Session> session;
    private final Lazy<UserV2Service> userV2Service;
    private final Validator validator;

    public enum WeightType {
        CURRENT,
        GOAL,
        JUST_WEIGHT,
        STARTING
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 2;
    }

    @Inject
    public UserWeightService(Context context, Lazy<Session> lazy, Lazy<MeasurementsService> lazy2, Lazy<ConfigService> lazy3, Lazy<UserV2Service> lazy4, @Named("weightValidator") Validator validator2) {
        this.mContext = context;
        this.session = lazy;
        this.measurementsService = lazy2;
        this.configService = lazy3;
        this.userV2Service = lazy4;
        this.validator = validator2;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public void setUseCurrentWeightUnit(Weight weight) {
        if (weight != null) {
            ((Session) this.session.get()).getUser().setProperty(Units.BODY_WEIGHT_UNIT_PREFERENCE, String.valueOf(weight.getValue()));
        }
    }

    public Weight getUserCurrentWeightUnit() {
        return Weight.fromInt(((Session) this.session.get()).getUser().getBodyWeightUnitPreference());
    }

    public float getCurrentWeightInPounds() {
        UserWeight currentWeight = ((Session) this.session.get()).getUser().getProfile().getCurrentWeight();
        if (currentWeight == null) {
            return 120.0f;
        }
        return currentWeight.getPounds();
    }

    public float getGoalWeightInPounds() {
        UserWeight goalWeight = ((Session) this.session.get()).getUser().getProfile().getGoalWeight();
        if (goalWeight == null) {
            return 100.0f;
        }
        return goalWeight.getPounds();
    }

    public float getStartingWeightInPounds() {
        float f;
        User user = ((Session) this.session.get()).getUser();
        if (ConfigUtils.isNewStartingWeightOn((ConfigService) this.configService.get())) {
            MfpProfile userProfile = user.getUserProfile();
            if (userProfile == null) {
                f = BitmapDescriptorFactory.HUE_RED;
            } else {
                f = convertStartingWeightToPounds(userProfile.getStartingWeight());
            }
            return f;
        }
        UserWeight startingWeight = user.getProfile().getStartingWeight((MeasurementsService) this.measurementsService.get());
        if (startingWeight == null) {
            return 120.0f;
        }
        return startingWeight.getPounds();
    }

    private float convertStartingWeightToPounds(MfpMeasuredValue mfpMeasuredValue) {
        return (float) LocalizedWeight.fromMeasuredValue(mfpMeasuredValue).getValue(Weight.POUNDS);
    }

    public String getStartingWeightDate() {
        if (!ConfigUtils.isNewStartingWeightOn((ConfigService) this.configService.get())) {
            return "";
        }
        MfpProfile userProfile = ((Session) this.session.get()).getUser().getUserProfile();
        return userProfile != null ? userProfile.getStartingWeightDate() : "";
    }

    public double getBMI() {
        return ((Session) this.session.get()).getUser().getProfile().getCurrentBMI();
    }

    public String getGoalPerWeekWeightString() {
        return getGoalPerWeekWeightString(getGoalPerWeekWeight());
    }

    public String getGoalPerWeekWeightString(float f) {
        return NumberUtils.localeStringFromFloat(Math.abs(GoalItem.getCurrentlySelectedWeight(f, getUserCurrentWeightUnit() == Weight.KILOGRAMS, true)));
    }

    public float getGoalPerWeekWeight() {
        UserV1GoalPreferences userV1GoalPreferences = ((Session) this.session.get()).getUser().getUserV1GoalPreferences();
        if (userV1GoalPreferences == null) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return userV1GoalPreferences.getGoalLossPerWeek();
    }

    public String[] getWeight(WeightType weightType, float f) {
        return getWeight(getUserCurrentWeightUnit(), weightType, f);
    }

    public String[] getWeight(Weight weight, WeightType weightType, float f) {
        switch (weightType) {
            case CURRENT:
                f = getCurrentWeightInPounds();
                break;
            case GOAL:
                f = getGoalWeightInPounds();
                break;
            case STARTING:
                f = getStartingWeightInPounds();
                break;
        }
        if (weight == Weight.STONES_POUNDS) {
            return UnitsUtils.getStonesPoundsFromPounds((double) f);
        }
        return new String[]{UnitsUtils.getLocalizedWeightString(Weight.POUNDS, weight, (double) f), "0"};
    }

    public double getWeight(Weight weight, MfpMeasuredValue mfpMeasuredValue) {
        return UnitsUtils.getWeight(weight, Weight.fromString(mfpMeasuredValue.getUnit()), (double) mfpMeasuredValue.getValue());
    }

    public float getExerciseWeightInPounds(String str) {
        if (getUserCurrentWeightUnit() == Weight.KILOGRAMS) {
            Weight weight = Weight.KILOGRAMS;
            String[] strArr = new String[2];
            if (Strings.isEmpty(str)) {
                str = "0";
            }
            strArr[0] = str;
            strArr[1] = "0";
            return (float) UnitsUtils.getWeightInPounds(weight, strArr);
        }
        Weight weight2 = Weight.POUNDS;
        String[] strArr2 = new String[2];
        if (Strings.isEmpty(str)) {
            str = "0";
        }
        strArr2[0] = str;
        strArr2[1] = "0";
        return (float) UnitsUtils.getWeightInPounds(weight2, strArr2);
    }

    public float validateConvertWeight(String[] strArr) {
        return validateConvertWeight(strArr, getUserCurrentWeightUnit());
    }

    public float validateConvertWeight(String[] strArr, Weight weight) {
        if (strArr == null || strArr.length < 2) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        float f = (weight != Weight.POUNDS || !this.validator.validate(strArr[0])) ? (weight != Weight.KILOGRAMS || !this.validator.validate(strArr[0])) ? (weight != Weight.STONES_POUNDS || !this.validator.validate(strArr[0]) || !this.validator.validate(strArr[1])) ? BitmapDescriptorFactory.HUE_RED : (float) (UnitsUtils.getPoundsFromStones((double) NumberUtils.localeFloatFromString(strArr[0])) + ((double) NumberUtils.localeFloatFromString(strArr[1]))) : (float) UnitsUtils.getPoundsFromKilograms((double) NumberUtils.localeFloatFromString(strArr[0])) : NumberUtils.localeFloatFromString(strArr[0]);
        if (f < BitmapDescriptorFactory.HUE_RED || f > 999.0f) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        return f;
    }

    private static float canonicalizeWeight(float f) {
        return new BigDecimal((double) f).round(new MathContext(String.valueOf((int) f).length() + 1)).floatValue();
    }

    public boolean setWeight(float f, WeightType weightType) {
        if (f > BitmapDescriptorFactory.HUE_RED) {
            User user = ((Session) this.session.get()).getUser();
            UserWeight userWeight = new UserWeight(canonicalizeWeight(f));
            switch (weightType) {
                case CURRENT:
                    user.getProfile().setCurrentWeight(userWeight);
                    user.updatePropertyNamed(Basic.CURRENT_WEIGHT_IN_POUNDS);
                    return true;
                case GOAL:
                    user.getProfile().setGoalWeight(userWeight);
                    user.updatePropertyNamed(Basic.GOAL_WEIGHT_IN_POUNDS);
                    return true;
            }
        }
        return false;
    }

    public UserV2 updateStartingWeight(float f, String str) throws ApiException {
        MfpProfile userProfile = ((Session) this.session.get()).getUser().getUserProfile();
        float canonicalizeWeight = canonicalizeWeight(f);
        MfpProfile mfpProfile = new MfpProfile();
        mfpProfile.setStartingWeight(new MfpMeasuredValue(Unit.POUNDS, canonicalizeWeight));
        mfpProfile.setStartingWeightDate(str);
        mfpProfile.setType(userProfile.getType());
        UserV2 patchMfpProfile = ((UserV2Service) this.userV2Service.get()).patchMfpProfile(mfpProfile);
        ((MeasurementsService) this.measurementsService.get()).insertOrUpdateMeasurement(Measurement.WEIGHT, DateTimeUtils.getCalendarFromDate(DateTimeUtils.parseDb(mfpProfile.getStartingWeightDate())), canonicalizeWeight, null);
        return patchMfpProfile;
    }

    public void recalculateAndUpdateGoalLossPerWeek() {
        User user = ((Session) this.session.get()).getUser();
        UserV1GoalPreferences userV1GoalPreferences = user.getUserV1GoalPreferences();
        float canonicalizeWeight = canonicalizeWeight(getCurrentWeightInPounds());
        float canonicalizeWeight2 = canonicalizeWeight(getGoalWeightInPounds());
        float goalLossPerWeek = userV1GoalPreferences.getGoalLossPerWeek();
        int i = (canonicalizeWeight > canonicalizeWeight2 ? 1 : (canonicalizeWeight == canonicalizeWeight2 ? 0 : -1));
        if (i == 0 && !NumberUtils.isEffectivelyZero((double) goalLossPerWeek)) {
            user.getUserV1GoalPreferences().setGoalLossPerWeek(BitmapDescriptorFactory.HUE_RED);
        } else if (i > 0 && goalLossPerWeek <= BitmapDescriptorFactory.HUE_RED) {
            user.getUserV1GoalPreferences().setGoalLossPerWeek(1.0f);
        } else if (canonicalizeWeight < canonicalizeWeight2 && goalLossPerWeek >= BitmapDescriptorFactory.HUE_RED) {
            user.getUserV1GoalPreferences().setGoalLossPerWeek(-0.5f);
        }
        user.updatePropertyNamed(Goals.GOAL_LOSS_PER_WEEK);
    }

    public String getDisplayableString(WeightType weightType, float f) {
        return getDisplayableString(weightType, f, R.string.weight_in_pounds, R.string.weight_in_kilograms, R.string.weight_in_stone_pounds);
    }

    public String getDisplayableStringFull(WeightType weightType, float f) {
        return getDisplayableString(weightType, f, R.string.weight_in_pounds_full, R.string.weight_in_kilograms_full, R.string.weight_in_stone_pounds_full);
    }

    private String getDisplayableString(WeightType weightType, float f, int i, int i2, int i3) {
        String str;
        if (f <= BitmapDescriptorFactory.HUE_RED) {
            f = BitmapDescriptorFactory.HUE_RED;
        }
        try {
            switch (getUserCurrentWeightUnit()) {
                case POUNDS:
                    String[] weight = getWeight(Weight.POUNDS, weightType, f);
                    return this.mContext.getString(i, new Object[]{weight[0]});
                case KILOGRAMS:
                    String[] weight2 = getWeight(Weight.KILOGRAMS, weightType, f);
                    return this.mContext.getString(i2, new Object[]{weight2[0]});
                case STONES_POUNDS:
                    String[] weight3 = getWeight(Weight.STONES_POUNDS, weightType, f);
                    if (Integer.parseInt(weight3[0]) == 0) {
                        str = this.mContext.getString(i, new Object[]{weight3[1]});
                    } else {
                        str = this.mContext.getString(i3, new Object[]{weight3[0], weight3[1]});
                    }
                    return str;
                default:
                    String[] weight4 = getWeight(Weight.POUNDS, weightType, f);
                    return this.mContext.getString(i2, new Object[]{weight4[0]});
            }
        } catch (NumberFormatException e) {
            Ln.e(e);
            return this.mContext.getString(i2, new Object[]{"0"});
        }
    }

    public String getDisplayableExerciseStringWithUnits(float f) {
        String displayableExerciseString = getDisplayableExerciseString(f);
        StringBuilder sb = new StringBuilder();
        sb.append(displayableExerciseString);
        sb.append(" ");
        sb.append(getDisplayableLbsAndKgUnitString(displayableExerciseString));
        return sb.toString();
    }

    public String getDisplayableExerciseString(float f) {
        if (f <= BitmapDescriptorFactory.HUE_RED) {
            f = BitmapDescriptorFactory.HUE_RED;
        }
        try {
            switch (getUserCurrentWeightUnit()) {
                case POUNDS:
                case STONES_POUNDS:
                    return getWeight(Weight.POUNDS, WeightType.JUST_WEIGHT, f)[0];
                case KILOGRAMS:
                    return getWeight(Weight.KILOGRAMS, WeightType.JUST_WEIGHT, f)[0];
                default:
                    return getWeight(Weight.POUNDS, WeightType.JUST_WEIGHT, f)[0];
            }
        } catch (NumberFormatException e) {
            Ln.e(e);
            return "0";
        }
    }

    public String getDisplayableUnitString(String str, WeightType weightType) {
        return getDisplayableUnitString(NumberUtils.localeFloatFromString(str), weightType);
    }

    public String getDisplayableUnitString(float f, WeightType weightType) {
        switch (getUserCurrentWeightUnit()) {
            case POUNDS:
                return getLbsString(f, weightType);
            case KILOGRAMS:
                return this.mContext.getString(weightType == WeightType.STARTING ? R.string.kg_on : R.string.kg);
            case STONES_POUNDS:
            case STONES:
                return this.mContext.getString(R.string.stone);
            default:
                return this.mContext.getString(R.string.unknown);
        }
    }

    public String getLbsString(float f, WeightType weightType) {
        if (f == 1.0f) {
            return this.mContext.getString(weightType == WeightType.STARTING ? R.string.lb_on : R.string.lb);
        }
        return this.mContext.getString(weightType == WeightType.STARTING ? R.string.lbs_on : R.string.lbs);
    }

    public String getDisplayableLbsAndKgUnitString(String str) {
        return getDisplayableLbsAndKgUnitString(NumberUtils.localeFloatFromString(str));
    }

    public String getDisplayableLbsAndKgUnitString(float f) {
        return getDisplayableLbsAndKgUnitString(f, getUserCurrentWeightUnit());
    }

    public String getDisplayableLbsAndKgUnitString(float f, Weight weight) {
        switch (weight) {
            case POUNDS:
            case STONES_POUNDS:
            case STONES:
                return this.mContext.getString(f == 1.0f ? R.string.lb : R.string.lbs);
            case KILOGRAMS:
                return this.mContext.getString(R.string.kg);
            default:
                return this.mContext.getString(R.string.unknown);
        }
    }

    public String getCurrentWeightUnitStringWithoutStone() {
        if (AnonymousClass2.$SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Weight[getUserCurrentWeightUnit().ordinal()] != 2) {
            return this.mContext.getString(R.string.lbs_setting);
        }
        return this.mContext.getString(R.string.kg_setting);
    }

    public String getCurrentWeightUnitString() {
        switch (getUserCurrentWeightUnit()) {
            case KILOGRAMS:
                return this.mContext.getString(R.string.kg_setting);
            case STONES_POUNDS:
            case STONES:
                return this.mContext.getString(R.string.stones_settings);
            default:
                return this.mContext.getString(R.string.lbs_setting);
        }
    }

    public boolean shouldRecalculateGoals() {
        Weight userCurrentWeightUnit = getUserCurrentWeightUnit();
        return ((Session) this.session.get()).getUser().poundsLost() >= ((userCurrentWeightUnit == Weight.POUNDS || userCurrentWeightUnit == Weight.STONES_POUNDS || userCurrentWeightUnit == Weight.STONES) ? 10.0f : 11.0f);
    }

    public String getEndingForStringResource() {
        switch (getUserCurrentWeightUnit()) {
            case POUNDS:
                return POUND_PREF;
            case KILOGRAMS:
                return KILOGRAM_PREF;
            case STONES_POUNDS:
            case STONES:
                return STONE_PREF;
            default:
                return "";
        }
    }

    public String getFiveWeekChangeString(double d) {
        Weight fromInt = Weight.fromInt(((Session) this.session.get()).getUser().getBodyWeightUnitPreference());
        Calendar instance = Calendar.getInstance();
        instance.add(5, 35);
        String mediumLocaleFormattedDate = DateTimeUtils.getMediumLocaleFormattedDate(this.mContext, instance.getTime());
        boolean z = fromInt == Weight.KILOGRAMS;
        if (z) {
            d = UnitsUtils.getKilogramsFromPounds(d);
        }
        String localeStringFromDoubleOneDecimalIfNeeded = NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(d);
        String string = this.mContext.getString(R.string.should_lose_by);
        Object[] objArr = new Object[3];
        objArr[0] = localeStringFromDoubleOneDecimalIfNeeded;
        objArr[1] = this.mContext.getString(z ? R.string.kg : R.string.lbs);
        objArr[2] = mediumLocaleFormattedDate;
        return String.format(string, objArr);
    }

    public void writeCurrentWeight(long j, float f, String str) {
        final float f2 = f;
        final long j2 = j;
        final String str2 = str;
        AnonymousClass1 r0 = new Runnable() {
            public void run() {
                ((MeasurementsService) UserWeightService.this.measurementsService.get()).insertOrUpdateThirdPartyMeasurement(Measurement.WEIGHT, f2, j2, str2);
            }
        };
        auto(r0);
    }
}
