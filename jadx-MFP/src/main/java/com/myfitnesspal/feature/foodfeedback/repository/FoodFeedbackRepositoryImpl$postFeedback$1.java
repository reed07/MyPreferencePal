package com.myfitnesspal.feature.foodfeedback.repository;

import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackRepositoryImpl.kt */
final class FoodFeedbackRepositoryImpl$postFeedback$1<V> implements Callable<Object> {
    final /* synthetic */ ApiRequest $data;
    final /* synthetic */ FoodFeedbackRepositoryImpl this$0;

    FoodFeedbackRepositoryImpl$postFeedback$1(FoodFeedbackRepositoryImpl foodFeedbackRepositoryImpl, ApiRequest apiRequest) {
        this.this$0 = foodFeedbackRepositoryImpl;
        this.$data = apiRequest;
    }

    public final Object call() {
        return ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.this$0.getMfpV2Api().get()).withOutputType(ApiResponseBase.class)).withJsonBody(this.$data)).post(Uri.FOOD_FEEDBACK, new Object[0]);
    }
}
