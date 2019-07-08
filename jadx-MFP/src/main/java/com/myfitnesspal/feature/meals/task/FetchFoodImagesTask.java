package com.myfitnesspal.feature.meals.task;

import android.content.Context;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpImage;
import java.util.List;

public class FetchFoodImagesTask extends EventedTaskBase<List<MfpImage>, Exception> {
    private ImageService imageService;
    private MealFood mealFood;

    public static class CompletedEvent extends TaskEventBase<List<MfpImage>, Exception> {
    }

    public FetchFoodImagesTask(ImageService imageService2, MealFood mealFood2) {
        super((TaskEventBase) new CompletedEvent());
        this.imageService = imageService2;
        this.mealFood = mealFood2;
    }

    /* access modifiers changed from: protected */
    public List<MfpImage> exec(Context context) {
        return this.imageService.getImagesForResource("food_entry", this.mealFood.getUid(), this.mealFood.getLocalId());
    }
}
