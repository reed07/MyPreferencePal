package com.myfitnesspal.feature.foodfeedback.model;

import android.arch.lifecycle.MutableLiveData;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref.ObjectRef;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Boolean;)V", "com/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel$checkIfFoodWithSameNameDescriptionExists$1$1"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackViewModel.kt */
final class FoodFeedbackViewModel$checkIfFoodWithSameNameDescriptionExists$$inlined$let$lambda$1<T> implements Consumer<Boolean> {
    final /* synthetic */ ObjectRef $liveDataFoodExists$inlined;
    final /* synthetic */ FoodFeedbackViewModel this$0;

    FoodFeedbackViewModel$checkIfFoodWithSameNameDescriptionExists$$inlined$let$lambda$1(FoodFeedbackViewModel foodFeedbackViewModel, ObjectRef objectRef) {
        this.this$0 = foodFeedbackViewModel;
        this.$liveDataFoodExists$inlined = objectRef;
    }

    public final void accept(Boolean bool) {
        ((MutableLiveData) this.$liveDataFoodExists$inlined.element).setValue(bool);
    }
}
