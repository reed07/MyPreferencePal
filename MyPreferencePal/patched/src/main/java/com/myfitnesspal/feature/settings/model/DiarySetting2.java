package com.myfitnesspal.feature.settings.model;
import lanchon.dexpatcher.annotation.*;

@DexAdd
public enum DiarySetting2
{
    /*
    CustomMealNames(2131231464), 
    CustomNutrientDashboard(2131231467), 
    DefaultSearchTab(2131231500), 
    DiarySharing(2131231536), 
    FoodInsights(2131233495), 
    MealMacros(2131233496), 
    MealsDiaryTab(2131233493), 
    MultiAdd(2131230962), 
    WaterCard(2131233500),
	NetCarbs(2146435072), //7ff00000
    SmartCarbs(2146435073);
    */
    CustomMealNames(2131231464),
    CustomNutrientDashboard(2131231467),
    DefaultSearchTab(2131231500),
    DiarySharing(2131231539),
    EditGoals(2131231529),
    FoodInsights(2131233501),
    MealMacros(2131233502),
    MultiAdd(2131230962),
    ShowAllFoods(2131231531),
    WaterCard(2131233506),
    NetCarbs(2146435072), //7ff00000
    SmartCarbs(2146435073);
    
    private final int stringResId;
    
    private DiarySetting2(final int stringResId) {
        this.stringResId = stringResId;
    }
    
    public int getStringResId() {
        return this.stringResId;
    }
}
