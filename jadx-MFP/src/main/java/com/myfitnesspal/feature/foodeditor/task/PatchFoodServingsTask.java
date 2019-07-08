package com.myfitnesspal.feature.foodeditor.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;

public class PatchFoodServingsTask extends EventedTaskBase<MfpFood, ApiException> {
    private final Lazy<FoodService> foodService;
    private final MfpFood mfpFood;
    private final List<MfpServingSize> servingSizes;

    public static class CompletedEvent extends TaskEventBase<MfpFood, ApiException> {
        final float numServings;
        final MfpServingSize servingSize;
        final int servingSizeIndex;

        public CompletedEvent(MfpServingSize mfpServingSize, float f, int i) {
            this.servingSize = mfpServingSize;
            this.numServings = f;
            this.servingSizeIndex = i;
        }

        public MfpServingSize getServingSize() {
            return this.servingSize;
        }

        public float getNumServings() {
            return this.numServings;
        }

        public int getServingSizeIndex() {
            return this.servingSizeIndex;
        }
    }

    public PatchFoodServingsTask(MfpFood mfpFood2, Lazy<FoodService> lazy, List<MfpServingSize> list, MfpServingSize mfpServingSize, float f, int i) {
        super((TaskEventBase) new CompletedEvent(mfpServingSize, f, i));
        this.mfpFood = mfpFood2;
        this.foodService = lazy;
        this.servingSizes = list;
    }

    /* access modifiers changed from: protected */
    public MfpFood exec(Context context) throws ApiException {
        return ((FoodService) this.foodService.get()).patchFoodServings(this.servingSizes, Strings.toString(this.mfpFood.getId()), Strings.toString(this.mfpFood.getVersion()));
    }
}
