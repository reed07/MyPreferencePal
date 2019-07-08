package com.myfitnesspal.feature.foodfeedback.model;

import com.uacf.core.util.Ln;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackViewModel.kt */
final class FoodFeedbackViewModel$postToFeedbackService$1$3<T> implements Consumer<Throwable> {
    public static final FoodFeedbackViewModel$postToFeedbackService$1$3 INSTANCE = new FoodFeedbackViewModel$postToFeedbackService$1$3();

    FoodFeedbackViewModel$postToFeedbackService$1$3() {
    }

    public final void accept(Throwable th) {
        Ln.e("FoodFeedback: Failed to post to the feedback service", new Object[0]);
    }
}
