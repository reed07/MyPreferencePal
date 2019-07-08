package com.myfitnesspal.feature.recipes.event;

import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.StartScreen;
import com.myfitnesspal.shared.event.MfpEventBase;
import java.util.Date;

public class CreateNewRecipeEvent extends MfpEventBase {
    private final String categoryName;
    private final Date date;
    private final StartScreen startScreen;

    public CreateNewRecipeEvent(StartScreen startScreen2) {
        this(null, null, startScreen2);
    }

    public CreateNewRecipeEvent(String str, Date date2, StartScreen startScreen2) {
        this.categoryName = str;
        this.date = date2;
        this.startScreen = startScreen2;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public Date getDate() {
        return this.date;
    }

    public StartScreen getStartScreen() {
        return this.startScreen;
    }
}
