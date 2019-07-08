package com.myfitnesspal.shared.api.v1;

import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.MfpApiImpl;
import com.myfitnesspal.shared.api.v1.MfpBinaryApi;
import com.myfitnesspal.shared.event.AuthFailedEvent;
import com.squareup.otto.Bus;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.ByteString;

public abstract class MfpBinaryApiImplBase<TApi extends MfpBinaryApi> extends MfpApiImpl<TApi, byte[]> implements MfpBinaryApi<TApi> {
    private static final String MULTIPART_FILE_NAME = "syncdata.dat";
    private static final String MULTIPART_NAME = "syncdata";
    private static final String OCTECT_TYPE = "application/octet-stream";

    /* access modifiers changed from: protected */
    public boolean getShouldRetry() {
        return true;
    }

    protected MfpBinaryApiImplBase(ApiBinaryConstructorArgs apiBinaryConstructorArgs) {
        super(apiBinaryConstructorArgs);
        withMapper(apiBinaryConstructorArgs.getMapper());
    }

    /* access modifiers changed from: protected */
    public byte[] getResponseData(Response response) {
        try {
            return response.body().bytes();
        } catch (IOException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getResponseData(InterceptorResponse interceptorResponse) {
        return interceptorResponse.getBytes();
    }

    /* access modifiers changed from: protected */
    public RequestBody getRequestBodyOverride() {
        if (this.binaryData == null) {
            return null;
        }
        if (this.binaryData.position() > 0) {
            this.binaryData.flip();
        }
        return new Builder().setType(MultipartBody.FORM).addFormDataPart(MULTIPART_NAME, MULTIPART_FILE_NAME, RequestBody.create(MediaType.parse(OCTECT_TYPE), ByteString.of(this.binaryData))).build();
    }

    /* access modifiers changed from: protected */
    public void reauthenticate() throws ApiException {
        try {
            getAuthTokenProvider().refreshAccessToken();
        } catch (ApiException unused) {
            ((Bus) this.messageBus.get()).post(new AuthFailedEvent());
        }
    }
}
