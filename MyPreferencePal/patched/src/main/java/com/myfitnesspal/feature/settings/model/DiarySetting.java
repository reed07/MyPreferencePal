package com.myfitnesspal.feature.settings.model;
import lanchon.dexpatcher.annotation.*;

//Ignoring because I made a second class
@DexEdit(defaultAction = DexAction.IGNORE)
public enum DiarySetting
{
    CustomMealNames(2131231464), 
    CustomNutrientDashboard(2131231467), 
    DefaultSearchTab(2131231500), 
    DiarySharing(2131231536), 
    FoodInsights(2131233495), 
    MealMacros(2131233496), 
    MealsDiaryTab(2131233493), 
    MultiAdd(2131230962), 
    WaterCard(2131233500);
	//NetCarbs(2146435072); //7ff00000
    
	@DexIgnore
    private final int stringResId;
    
	@DexIgnore
    private DiarySetting(final int stringResId) {
        this.stringResId = stringResId;
    }
    
	@DexIgnore
    public int getStringResId() {
        return this.stringResId;
    }
}
