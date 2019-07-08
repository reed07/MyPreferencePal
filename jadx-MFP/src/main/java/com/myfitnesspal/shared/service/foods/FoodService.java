package com.myfitnesspal.shared.service.foods;

import android.content.Context;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.userdata.UserDistanceService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Tuple2;
import io.reactivex.Single;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface FoodService {
    void deleteFood(long j, boolean z, long j2, String str, long j3, String str2, boolean z2, boolean z3);

    Single<List<MfpServingSize>> fetchSuggestedServings(@NotNull String str, @NotNull String str2);

    int getFoodInsightAsync(NutritionalValues nutritionalValues, List<FoodEntry> list, FoodEntry foodEntry, UserEnergyService userEnergyService, UserWeightService userWeightService, UserDistanceService userDistanceService, float f, Function2<List<FoodAnalyzerResponseData>, Integer> function2);

    Tuple2<MealFood, FoodNotes> getMealFoodForId(String str, boolean z) throws ApiException;

    @Deprecated
    ApiResponse<MfpServingSize> getSuggestedServings(String str, String str2);

    @Deprecated
    void getSuggestedServingsAsync(String str, String str2, Function1<ApiResponse<MfpServingSize>> function1);

    void hideFood(long j, String str, long j2, long j3, long j4, String str2, long j5);

    MfpFood patchFoodServings(List<MfpServingSize> list, String str, String str2);

    void patchFoodServingsAsync(List<MfpServingSize> list, String str, String str2, Function1<ApiResponse<MfpFood>> function1);

    void postFoodQuestionAnswer(Context context, FoodEntry foodEntry, int i, boolean z, Function0 function0);

    FoodNotes saveFoodNotes(String str, Food food);
}
