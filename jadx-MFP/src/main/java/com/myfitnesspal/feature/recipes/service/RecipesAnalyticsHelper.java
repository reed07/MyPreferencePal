package com.myfitnesspal.feature.recipes.service;

import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.shared.model.v2.MfpIngredientItem;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;

public class RecipesAnalyticsHelper {
    private static final String ATTRIBUTE_ACTION = "action";
    private static final String ATTRIBUTE_BULK = "bulk";
    private static final String ATTRIBUTE_CONTINUE = "continue";
    private static final String ATTRIBUTE_FLOW_ID = "flow_id";
    private static final String ATTRIBUTE_INGREDIENT_ADD_COUNT = "ingredient_add_count";
    private static final String ATTRIBUTE_INGREDIENT_COUNT = "ingredient_count";
    private static final String ATTRIBUTE_INGREDIENT_DELETE_COUNT = "ingredient_delete_count";
    private static final String ATTRIBUTE_INGREDIENT_EDIT_COUNT = "ingredient_edit_count";
    private static final String ATTRIBUTE_MATCHED = "matched";
    private static final String ATTRIBUTE_PAGEVIEWS = "pageviews";
    private static final String ATTRIBUTE_RECIPE_NAME = "recipe_name";
    private static final String ATTRIBUTE_RECIPE_URL = "recipe_url";
    private static final String ATTRIBUTE_SOURCE = "source";
    private static final String ATTRIBUTE_START_SCREEN = "start_screen";
    private static final String ATTRIBUTE_TYPE = "type";
    private static final String ATTRIBUTE_UNMATCHED = "unmatched";
    private static final String EVENT_IMPORT_RECIPE = "import_recipe";
    private static final String EVENT_RECIPE_BROWSER = "recipe_browser";
    private static final String EVENT_RECIPE_FLOW_COMPLETED = "recipe_flow_completed";
    private static final String EVENT_RECIPE_FLOW_STARTED = "recipe_flow_started";
    private static final String EVENT_RECIPE_INGREDIENT_MANUAL_ENTRY_SUMMARY = "recipe_ingredient_manual_entry_summary";
    private static final String EVENT_RECIPE_MANUAL_IMPORT = "recipe_manual_import";
    private static final String EVENT_RECIPE_MATCH_SUMMARY = "recipe_match_summary";
    private static final String EVENT_RECIPE_TEXT_PARSE_SUMMARY = "recipe_text_parse_summary";
    private static final String EVENT_RECIPE_URL_LIST = "recipe_url_list";
    public static final String VALUE_MANUAL = "manual";
    private static final String VALUE_NEW = "new";
    private static final String VALUE_SAVE = "save";
    private static final String VALUE_SAVE_AND_LOG = "save_and_log";
    public static final String VALUE_WEB = "web";
    private final Lazy<AnalyticsService> analyticsService;

    public RecipesAnalyticsHelper(Lazy<AnalyticsService> lazy) {
        this.analyticsService = lazy;
    }

    public void reportRecipeFlowStarted(RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_RECIPE_FLOW_STARTED, MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId(), ATTRIBUTE_START_SCREEN, recipeAnalyticsIntentData.getStartScreen().getAnalyticsAttributeValue(), "type", recipeAnalyticsIntentData.getActionType().getAnalyticsAttributeValue()));
    }

    public void reportRecipeFlowCompleted(RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_RECIPE_FLOW_COMPLETED, MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId(), ATTRIBUTE_START_SCREEN, recipeAnalyticsIntentData.getStartScreen().getAnalyticsAttributeValue(), "type", recipeAnalyticsIntentData.getActionType().getAnalyticsAttributeValue()));
    }

    public void reportImportRecipe(RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent("import_recipe", MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId(), "source", recipeAnalyticsIntentData.getStartScreen().getAnalyticsAttributeValue(), "type", str));
    }

    public void reportRecipeManualImport(RecipeAnalyticsIntentData recipeAnalyticsIntentData, boolean z, boolean z2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent("recipe_manual_import", MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId(), "bulk", Boolean.toString(z), "continue", Boolean.toString(z2)));
    }

    public void reportRecipeUrlList(RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_RECIPE_URL_LIST, MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId()));
    }

    public void reportRecipeBrowser(RecipeAnalyticsIntentData recipeAnalyticsIntentData, int i) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent("recipe_browser", MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId(), "source", recipeAnalyticsIntentData.getStartScreen().getAnalyticsAttributeValue(), "pageviews", Integer.toString(i)));
    }

    public void reportRecipeTextParseSummary(RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str, int i) {
        int i2 = 0;
        for (String trim : str.split("\n")) {
            if (Strings.notEmpty(trim.trim())) {
                i2++;
            }
        }
        ((AnalyticsService) this.analyticsService.get()).reportEvent("recipe_text_parse_summary", MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId(), "source", recipeAnalyticsIntentData.getStartScreen().getAnalyticsAttributeValue(), "ingredient_count", Integer.toString(i2), "ingredient_edit_count", Integer.toString(i2 - i)));
    }

    public void reportIngredientMatchingSummary(List<MfpIngredientItem> list, RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str, String str2, int i, int i2) {
        int size = list.size();
        int i3 = 0;
        int i4 = 0;
        for (MfpIngredientItem mfpIngredientItem : list) {
            if (mfpIngredientItem.wasManuallyAdded()) {
                i3++;
            }
            if (mfpIngredientItem.hasMatches()) {
                i4++;
            }
        }
        if (Strings.notEmpty(str2)) {
            reportRecipeMatchSummary(recipeAnalyticsIntentData, str, str2, i3, i, i2, i4, size - i4);
            return;
        }
        RecipeAnalyticsIntentData recipeAnalyticsIntentData2 = recipeAnalyticsIntentData;
        reportRecipeIngredientManualEntrySummary(recipeAnalyticsIntentData, i3, i);
    }

    @Deprecated
    public void reportRecipeFlowCompletedForNewRecipe(String str, boolean z) {
        reportRecipeFlowCompleted(VALUE_NEW, str, z ? VALUE_SAVE_AND_LOG : VALUE_SAVE);
    }

    private void reportRecipeMatchSummary(RecipeAnalyticsIntentData recipeAnalyticsIntentData, String str, String str2, int i, int i2, int i3, int i4, int i5) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent("recipe_match_summary", MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId(), "source", recipeAnalyticsIntentData.getStartScreen().getAnalyticsAttributeValue(), "recipe_name", str, ATTRIBUTE_RECIPE_URL, str2, "ingredient_add_count", Integer.toString(i), "ingredient_edit_count", Integer.toString(i2), "ingredient_delete_count", Integer.toString(i3), "matched", Integer.toString(i4), "unmatched", Integer.toString(i5)));
    }

    private void reportRecipeIngredientManualEntrySummary(RecipeAnalyticsIntentData recipeAnalyticsIntentData, int i, int i2) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent("recipe_ingredient_manual_entry_summary", MapUtil.createMap("flow_id", recipeAnalyticsIntentData.getFlowId(), "source", recipeAnalyticsIntentData.getStartScreen().getAnalyticsAttributeValue(), "ingredient_add_count", Integer.toString(i), "ingredient_edit_count", Integer.toString(i2)));
    }

    private void reportRecipeFlowCompleted(String str, String str2, String str3) {
        ((AnalyticsService) this.analyticsService.get()).reportEvent(EVENT_RECIPE_FLOW_COMPLETED, MapUtil.createMap("type", str, ATTRIBUTE_START_SCREEN, str2, "action", str3));
    }
}
