package com.myfitnesspal.shared.service.id;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.model.v2.MfpCorrelationIdDetails;
import com.uacf.core.util.Function1;
import java.util.List;

public interface IdService {

    public static final class Scope {
        public static final String ACTIVITY_ENTRY = "activity_entry";
        public static final String EXERCISE = "exercise";
        public static final String RECIPE_FROM_RECIPE_BOX_ITEM = "recipe-from-recipe-box-item";
    }

    List<MfpCorrelationIdDetails> getV2IdsFromV1Ids(List<MfpCorrelationIdDetails> list) throws ApiException;

    void getV2IdsFromV1IdsAsync(List<MfpCorrelationIdDetails> list, Function1<List<MfpCorrelationIdDetails>> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback);
}
