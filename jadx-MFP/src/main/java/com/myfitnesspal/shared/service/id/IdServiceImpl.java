package com.myfitnesspal.shared.service.id;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.MfpApiUtil;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.v2.MfpCorrelationIdDetails;
import com.myfitnesspal.shared.model.v2.MfpCorrelationIdDetails.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.service.id.IdService.Scope;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Strings;
import java.util.List;
import javax.inject.Provider;

public class IdServiceImpl implements IdService {
    private final Provider<MfpV2Api> apiV2Provider;

    public IdServiceImpl(Provider<MfpV2Api> provider) {
        this.apiV2Provider = provider;
    }

    public void getV2IdsFromV1IdsAsync(List<MfpCorrelationIdDetails> list, Function1<List<MfpCorrelationIdDetails>> function1, MfpV2ApiErrorCallback mfpV2ApiErrorCallback) {
        try {
            FunctionUtils.invokeIfValid(function1, getV2IdsFromV1Ids(list));
        } catch (ApiException e) {
            FunctionUtils.invokeIfValid(mfpV2ApiErrorCallback, MfpApiUtil.mapException(e));
        }
    }

    public List<MfpCorrelationIdDetails> getV2IdsFromV1Ids(List<MfpCorrelationIdDetails> list) throws ApiException {
        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setItems(list);
        return ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiV2Provider.get()).withOutputType(API_RESPONSE_MAPPER.class)).withJsonBody(apiRequest)).post(isRecipeScope(list) ? Uri.RECIPE_ID_GENERATOR : Uri.ID_GENERATOR, new Object[0])).getItems();
    }

    private boolean isRecipeScope(List<MfpCorrelationIdDetails> list) {
        return Strings.equals(((MfpCorrelationIdDetails) list.get(0)).getScope(), Scope.RECIPE_FROM_RECIPE_BOX_ITEM);
    }
}
