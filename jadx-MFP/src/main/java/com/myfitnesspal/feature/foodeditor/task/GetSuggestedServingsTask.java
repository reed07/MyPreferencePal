package com.myfitnesspal.feature.foodeditor.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.uacf.core.util.Strings;
import dagger.Lazy;

public class GetSuggestedServingsTask extends EventedTaskBase<ApiResponse<MfpServingSize>, ApiException> {
    private static final String TASK_NAME = "GetSuggestedServingsTask";
    private final Lazy<FoodService> foodService;
    private final MfpFood mfpFood;

    public static class CompletedEvent extends TaskEventBase<MfpFood, ApiException> {
    }

    public GetSuggestedServingsTask(Lazy<FoodService> lazy, MfpFood mfpFood2) {
        super((TaskEventBase) new CompletedEvent());
        this.foodService = lazy;
        this.mfpFood = mfpFood2;
    }

    public static boolean matches(String str) {
        return Strings.notEmpty(str) && str.contains(TASK_NAME);
    }

    /* access modifiers changed from: protected */
    public ApiResponse<MfpServingSize> exec(Context context) throws ApiException {
        return ((FoodService) this.foodService.get()).getSuggestedServings(this.mfpFood.getId(), this.mfpFood.getVersion());
    }
}
