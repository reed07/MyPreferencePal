package com.myfitnesspal.shared.util;

import android.content.Context;
import android.content.res.Resources;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Exercise.ExerciseTypeName;
import com.myfitnesspal.shared.constants.Constants.Goals.Nutrient;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.constants.Constants.Measurement.Localization;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class LocalizedStringsUtilImpl implements LocalizedStringsUtil {
    private Context context;
    ResourceUtils resourceUtils;

    @Inject
    public LocalizedStringsUtilImpl(Context context2, ResourceUtils resourceUtils2) {
        this.context = context2;
        this.resourceUtils = resourceUtils2;
    }

    public String getMealNameString(String str, UserEnergyService userEnergyService) {
        String strings = Strings.toString(str);
        if (this.context == null) {
            return strings;
        }
        if (Strings.equalsIgnoreCase(strings, MealTypeName.BREAKFAST)) {
            return getString(R.string.breakfast);
        }
        if (Strings.equalsIgnoreCase(strings, MealTypeName.LUNCH)) {
            return getString(R.string.lunch);
        }
        if (Strings.equalsIgnoreCase(strings, MealTypeName.DINNER)) {
            return getString(R.string.dinner);
        }
        if (Strings.equalsIgnoreCase(strings, MealTypeName.SNACK)) {
            return getString(R.string.snacks);
        }
        if (!Strings.equalsIgnoreCase(strings, MealTypeName.LEGACY_QUICK_ADDED_CALORIES) || userEnergyService == null) {
            return Strings.equalsIgnoreCase(strings, MealTypeName.QUICK_ADD) ? getString(R.string.quick_add) : strings;
        }
        return getLocalizedString(LocalizedStrings.QUICK_ADDED, userEnergyService);
    }

    public String getExerciseTypeNameString(String str) {
        if (Strings.isEmpty(str)) {
            str = "";
        }
        if (this.context == null) {
            return str;
        }
        if (Strings.equalsIgnoreCase(str, ExerciseTypeName.CARDIOVASCULAR)) {
            return getString(R.string.cardiovascular);
        }
        return Strings.equalsIgnoreCase(str, ExerciseTypeName.STRENGTH) ? getString(R.string.strength) : str;
    }

    public String getLocalizedMeasurementName(String str) {
        if (Strings.isEmpty(str)) {
            str = "";
        }
        Context context2 = this.context;
        if (context2 == null) {
            return str;
        }
        Resources resources = context2.getResources();
        if (Strings.equalsIgnoreCase(str, Measurement.WEIGHT)) {
            return resources.getString(R.string.weight);
        }
        if (Strings.equalsIgnoreCase(str, Measurement.NECK)) {
            return resources.getString(R.string.neck);
        }
        if (Strings.equalsIgnoreCase(str, Measurement.WAIST)) {
            return resources.getString(R.string.waist);
        }
        if (Strings.equalsIgnoreCase(str, Measurement.HIPS)) {
            return resources.getString(R.string.hips);
        }
        return Strings.equalsIgnoreCase(str, Measurement.STEPS) ? resources.getString(R.string.steps) : str;
    }

    public String getLocalizedString(String str, int i, Object obj) {
        int localizedStringId = getLocalizedStringId(this.context, str, i, obj);
        if (localizedStringId == 0) {
            return "";
        }
        return Strings.toString(getText(localizedStringId));
    }

    public String getLocalizedString(String str) {
        return getLocalizedString(str, null);
    }

    public String getLocalizedString(String str, Object obj) {
        int localizedStringId = getLocalizedStringId(str, obj);
        if (localizedStringId == 0) {
            return "";
        }
        return Strings.toString(getText(localizedStringId));
    }

    public String getNutrientString(String str, int i, Object obj) {
        String str2;
        if (i == 0) {
            return getLocalizedString(str, obj);
        }
        switch (i) {
            case 1:
                str2 = "fat";
                break;
            case 2:
                str2 = "saturated_fat";
                break;
            case 3:
                str2 = Nutrient.POLYUNSATURATED_FAT;
                break;
            case 4:
                str2 = Nutrient.MONOUNSATURATED_FAT;
                break;
            case 5:
                str2 = "trans_fat";
                break;
            case 6:
                str2 = "cholesterol";
                break;
            case 7:
                str2 = "sodium";
                break;
            case 8:
                str2 = "potassium";
                break;
            case 9:
                str2 = Nutrient.CARBOHYDRATES;
                break;
            case 10:
                str2 = Nutrient.FIBER;
                break;
            case 11:
                str2 = "sugar";
                break;
            case 12:
                str2 = "protein";
                break;
            case 13:
                str2 = "vitamin_a";
                break;
            case 14:
                str2 = "vitamin_c";
                break;
            case 15:
                str2 = "calcium";
                break;
            case 16:
                str2 = "iron";
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unknown nutrient index: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
        int localizedStringId = getLocalizedStringId(String.format("%s_%s", new Object[]{str, str2}));
        if (localizedStringId == 0) {
            localizedStringId = getLocalizedStringId(str);
        }
        return getString(localizedStringId);
    }

    private int getLocalizedStringId(String str) {
        return this.resourceUtils.getResourceIdentifier(this.context, str);
    }

    public int getLocalizedStringId(String str, Object obj) {
        if (obj instanceof Lazy) {
            obj = ((Lazy) obj).get();
        }
        if (obj instanceof UserEnergyService) {
            return this.resourceUtils.getResourceIdentifier(this.context, getEnergyStringId(str, (UserEnergyService) obj));
        }
        if (obj instanceof UserWeightService) {
            return this.resourceUtils.getResourceIdentifier(this.context, getWeightStringId(str, (UserWeightService) obj));
        }
        return this.resourceUtils.getResourceIdentifier(this.context, str);
    }

    private int getLocalizedStringId(Context context2, String str, int i, Object obj) {
        if (obj instanceof Lazy) {
            obj = ((Lazy) obj).get();
        }
        if (obj instanceof UserEnergyService) {
            return this.resourceUtils.getResourceIdentifier(context2, getQuantityStringId(getEnergyStringId(str, (UserEnergyService) obj), i));
        }
        return this.resourceUtils.getResourceIdentifier(context2, str);
    }

    private String getEnergyStringId(String str, UserEnergyService userEnergyService) {
        if (userEnergyService.getUserCurrentEnergyUnit() == Energy.KILOJOULES) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(Localization.KILOJOULES_SUFFIX);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(Localization.CALORIES_SUFFIX);
        return sb2.toString();
    }

    private String getWeightStringId(String str, UserWeightService userWeightService) {
        switch (userWeightService.getUserCurrentWeightUnit()) {
            case KILOGRAMS:
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(Localization.KILOGRAMS_SUFFIX);
                return sb.toString();
            case STONES:
            case STONES_POUNDS:
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(Localization.STONES_SUFFIX);
                return sb2.toString();
            default:
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(Localization.POUNDS_SUFFIX);
                return sb3.toString();
        }
    }

    private String getQuantityStringId(String str, int i) {
        if (i == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(Localization.SINGULAR_SUFFIX);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append(Localization.PLURAL_SUFFIX);
        return sb2.toString();
    }

    private String getString(int i) {
        if (i > 0) {
            return this.context.getResources().getString(i);
        }
        return null;
    }

    private CharSequence getText(int i) {
        if (i > 0) {
            return this.context.getResources().getText(i);
        }
        return null;
    }
}
