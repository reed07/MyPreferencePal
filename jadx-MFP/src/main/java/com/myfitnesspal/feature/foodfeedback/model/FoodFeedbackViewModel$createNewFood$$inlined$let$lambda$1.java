package com.myfitnesspal.feature.foodfeedback.model;

import android.arch.lifecycle.MutableLiveData;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpFood;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.ObjectRef;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/myfitnesspal/shared/model/v1/Food;", "kotlin.jvm.PlatformType", "accept", "com/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel$createNewFood$1$1$1", "com/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel$$special$$inlined$let$lambda$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackViewModel.kt */
final class FoodFeedbackViewModel$createNewFood$$inlined$let$lambda$1<T> implements Consumer<Food> {
    final /* synthetic */ ObjectRef $liveDataFood$inlined;
    final /* synthetic */ MfpFood $mfpFood$inlined;
    final /* synthetic */ FoodFeedbackViewModel this$0;

    FoodFeedbackViewModel$createNewFood$$inlined$let$lambda$1(MfpFood mfpFood, FoodFeedbackViewModel foodFeedbackViewModel, ObjectRef objectRef) {
        this.$mfpFood$inlined = mfpFood;
        this.this$0 = foodFeedbackViewModel;
        this.$liveDataFood$inlined = objectRef;
    }

    public final void accept(Food food) {
        ((MutableLiveData) this.$liveDataFood$inlined.element).setValue(food);
    }
}
