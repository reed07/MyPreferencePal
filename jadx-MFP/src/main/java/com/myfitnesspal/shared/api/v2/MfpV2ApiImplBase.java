package com.myfitnesspal.shared.api.v2;

import com.myfitnesspal.shared.api.ApiConstructorArgs;
import com.myfitnesspal.shared.api.MfpApi;
import com.myfitnesspal.shared.api.MfpApiImpl;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import com.uacf.core.util.Strings;
import java.io.IOException;
import okhttp3.Request.Builder;
import okhttp3.Response;

abstract class MfpV2ApiImplBase<TApi extends MfpApi> extends MfpApiImpl<TApi, String> {
    public MfpV2ApiImplBase(ApiConstructorArgs apiConstructorArgs) {
        super(apiConstructorArgs);
    }

    /* access modifiers changed from: protected */
    public String getResponseData(Response response) {
        try {
            return response.body().string();
        } catch (IOException unused) {
            return "{}";
        }
    }

    /* access modifiers changed from: protected */
    public String getResponseData(InterceptorResponse interceptorResponse) {
        String body = interceptorResponse.getBody();
        return Strings.isEmpty(body) ? "{}" : body;
    }

    /* access modifiers changed from: protected */
    public void addHeadersTo(Builder builder) {
        super.addHeadersTo(builder);
        addHeader(builder, "Accept", HttpConstants.CONTENT_TYPE_JSON);
    }
}
