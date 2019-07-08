package com.myfitnesspal.feature.recipes.util;

import android.support.v4.app.FragmentManager;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.recipes.event.ManualRecipeImportEvent;
import com.myfitnesspal.feature.recipes.model.MfpPartialRecipe;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v2.MfpIngredient;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Bus;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RecipeImportUtils {
    public static final String getMatchedIngredientRowTitle(MfpIngredient mfpIngredient, UserEnergyService userEnergyService, LocalizedStringsUtil localizedStringsUtil) {
        return localizedStringsUtil.getMealNameString(mfpIngredient.getFood().getDescription(), userEnergyService);
    }

    public static final String getMatchedIngredientRowSubtitle(MfpIngredient mfpIngredient, UserEnergyService userEnergyService) {
        MfpServingSize servingSize = mfpIngredient.getServingSize();
        return String.format("%s %s • %s %s", new Object[]{NumberUtils.localeStringFromDoubleOneDecimalIfNeeded(mfpIngredient.getServings().doubleValue() * servingSize.getValue().doubleValue()), servingSize.getUnit(), userEnergyService.getDisplayableEnergy(mfpIngredient.getCaloriesValue()), userEnergyService.getCurrentEnergyUnitString()});
    }

    public static boolean startMatchIfRecipeDataIsComplete(String str, String str2, String str3, List<MfpIngredientItem> list, FragmentManager fragmentManager, Bus bus) {
        String trimmed = Strings.trimmed((Object) str);
        String trimmed2 = Strings.trimmed((Object) str2);
        String trimmed3 = Strings.trimmed((Object) str3);
        if (Strings.isEmpty(trimmed) || Strings.isEmpty(trimmed2)) {
            ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.error_save_recipe_validation_error_title)).setMessage((int) R.string.error_manual_recipe_creation_validation_error)).setPositiveText(R.string.ok, null)).show(fragmentManager, AlertDialogFragment.class.getName());
            return false;
        }
        ArrayList arrayList = new ArrayList();
        if (!Strings.isEmpty(trimmed3)) {
            arrayList.addAll(Arrays.asList(trimmed3.split("\n")));
        }
        ManualRecipeImportEvent manualRecipeImportEvent = new ManualRecipeImportEvent(arrayList, list, trimmed, NumberUtils.tryParseDouble(trimmed2, 1.0d));
        bus.post(manualRecipeImportEvent);
        return true;
    }

    public static int getPerServingEnergyValue(MfpRecipe mfpRecipe, double d, UserEnergyService userEnergyService) {
        return Math.round(userEnergyService.getCurrentEnergy(mfpRecipe.getNutritionalContents().getCalories().doubleValue() / d));
    }

    public static int getPerServingEnergyValue(RecipeFood recipeFood, double d, UserEnergyService userEnergyService) {
        return Math.round(userEnergyService.getCurrentEnergy(MfpNutritionalContents.fromFood(recipeFood).getCalories().doubleValue() / d));
    }

    public static int getPerServingEnergyValueForPartialRecipe(MfpPartialRecipe mfpPartialRecipe, double d, UserEnergyService userEnergyService) {
        return Math.round(userEnergyService.getCurrentEnergy(mfpPartialRecipe.getNutritionalContents().getCalories().doubleValue() / d));
    }
}
