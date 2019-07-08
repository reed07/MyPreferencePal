package com.myfitnesspal.shared.api;

import android.net.Uri;
import com.myfitnesspal.shared.api.MfpApi;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.myfitnesspal.shared.api.exception.CouldNotRetryException;
import com.myfitnesspal.shared.api.exception.DuplicateResourceException;
import com.myfitnesspal.shared.api.exception.HttpAuthFailureRetryException;
import com.myfitnesspal.shared.api.interceptor.MockInterceptorMapperException;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.myfitnesspal.shared.event.AuthFailedEvent;
import com.myfitnesspal.shared.event.MfpEventBase;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.service.device.UserAgentProvider;
import com.squareup.otto.Bus;
import com.uacf.core.mapping.Mapper2;
import com.uacf.core.mock.interceptor.InterceptorResponse;
import com.uacf.core.mock.interceptor.RequestInterceptor;
import com.uacf.core.util.ArrayUtil;
import com.uacf.core.util.CheckedReturningFunction2;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.SafeAsyncTask;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;
import dagger.Lazy;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class MfpApiImpl<TApiType extends MfpApi, TInput> implements MfpApi<TApiType, TInput> {
    private static final Set<String> DISABLE_URL_ENCODING_KEYS = new HashSet();
    private static final OkHttpClient MASTER_HTTP_CLIENT = new OkHttpClient();
    protected final Lazy<ApiUrlProvider> apiUrlProvider;
    private AuthTokenProvider authTokenProvider;
    protected ByteBuffer binaryData;
    private int connectTimeout = 30000;
    private String contentTypeOverride;
    private final Lazy<AuthTokenProvider> defaultAuthTokenProvider;
    protected final UUID deviceId;
    private String flowId;
    private boolean followRedirects = true;
    private Mapper2<?, TInput> mapper;
    protected final Lazy<Bus> messageBus;
    private final Lazy<RequestInterceptor> mockInterceptor;
    private Object rawBody;
    private int readTimeout = 30000;
    private Map<String, List<String>> requestHeaders;
    private int responseCode = 0;
    /* access modifiers changed from: private */
    public Map<String, List<String>> responseHeaders;
    private final UserAgentProvider userAgentProvider;

    /* access modifiers changed from: protected */
    public abstract String getBaseUrl();

    /* access modifiers changed from: protected */
    public RequestBody getRequestBodyOverride() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract TInput getResponseData(InterceptorResponse interceptorResponse);

    /* access modifiers changed from: protected */
    public abstract TInput getResponseData(Response response);

    /* access modifiers changed from: protected */
    public boolean getShouldRetry() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isSuccessCode(int i) {
        return i == 200 || i == 204;
    }

    /* access modifiers changed from: protected */
    public void reauthenticate() throws ApiException {
    }

    static {
        DISABLE_URL_ENCODING_KEYS.add("password");
    }

    protected MfpApiImpl(ApiConstructorArgs apiConstructorArgs) {
        this.apiUrlProvider = apiConstructorArgs.getApiUrlProvider();
        this.userAgentProvider = apiConstructorArgs.getUserAgentProvider();
        this.deviceId = apiConstructorArgs.getDeviceId();
        this.mockInterceptor = apiConstructorArgs.getMockInterceptor();
        this.messageBus = apiConstructorArgs.getMessageBus();
        this.defaultAuthTokenProvider = apiConstructorArgs.getAuthTokenProvider();
    }

    private void handleUnauthorizedResultIfNecessary(int i) throws ApiException {
        if (i == 401 && getShouldRetry()) {
            reauthenticate();
            throw new HttpAuthFailureRetryException("Auth failure. Retrying. ", 401);
        }
    }

    private void throwApiException(int i, TInput tinput) throws ApiException {
        Ln.e("API: MfpApiImpl#throwApiException, statusCode=%s, body=%s", Integer.valueOf(i), Strings.toString(tinput));
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected response code: ");
        sb.append(i);
        throw new ApiException(sb.toString(), i, Strings.toString(tinput));
    }

    /* access modifiers changed from: protected */
    public OkHttpClient client() {
        return MASTER_HTTP_CLIENT.newBuilder().connectTimeout((long) this.connectTimeout, TimeUnit.MILLISECONDS).readTimeout((long) this.readTimeout, TimeUnit.MILLISECONDS).followRedirects(this.followRedirects).build();
    }

    public TApiType withFlowId(String str) {
        this.flowId = str;
        return this;
    }

    public TApiType withBinaryData(ByteBuffer byteBuffer) {
        this.binaryData = byteBuffer;
        return this;
    }

    public TApiType withAuthTokenProvider(AuthTokenProvider authTokenProvider2) {
        this.authTokenProvider = authTokenProvider2;
        return this;
    }

    public TApiType withTimeouts(int i, int i2) {
        this.connectTimeout = i;
        this.readTimeout = i2;
        return this;
    }

    public TApiType withFollowRedirects(boolean z) {
        this.followRedirects = z;
        return this;
    }

    /* access modifiers changed from: protected */
    public void addHeadersTo(Builder builder) {
        addHeader(builder, "User-Agent", this.userAgentProvider.get());
        addHeader(builder, "device_id", Strings.toString(this.deviceId));
        if (Strings.notEmpty(this.flowId)) {
            addHeader(builder, Http.FLOW_ID_HEADER, this.flowId);
        }
    }

    /* access modifiers changed from: protected */
    public final Builder addCredentialsTo(Builder builder) throws ApiException {
        Map credentials = ((ApiUrlProvider) this.apiUrlProvider.get()).getCredentials(getAuthTokenProvider());
        if (CollectionUtils.notEmpty(credentials)) {
            for (Entry entry : credentials.entrySet()) {
                addHeader(builder, (String) entry.getKey(), entry.getValue());
            }
        }
        return builder;
    }

    public <T> T get(String str, Object... objArr) throws ApiException {
        return wrapRequestWithAuthFailureRetry(new CheckedReturningFunction2<T, String, Object[], ApiException>() {
            public T execute(String str, Object[] objArr) throws ApiException {
                return MfpApiImpl.this.performGet(str, objArr);
            }
        }, str, objArr);
    }

    /* access modifiers changed from: private */
    public <T> T performGet(String str, Object[] objArr) throws ApiException {
        return makeRequest(str, HttpConstants.METHOD_GET, objArr);
    }

    public <T> T get() throws ApiException {
        return get("");
    }

    public <T> T get(String str) throws ApiException {
        return get(str, null);
    }

    public <T> void getAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback) {
        getAsync("", function1, apiErrorCallback);
    }

    public <T> void getAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        getAsync("", function1, apiErrorCallback, objArr);
    }

    public <T> void getAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback) {
        getAsync(str, function1, apiErrorCallback, (Object[]) null);
    }

    public <T> void getAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        getAsync(str, (Function2<T, Map<String, List<String>>>) new Function2() {
            public final void execute(Object obj, Object obj2) {
                FunctionUtils.invokeIfValid(Function1.this, obj);
            }
        }, apiErrorCallback, objArr);
    }

    public <T> void getAsync(String str, Function2<T, Map<String, List<String>>> function2, ApiErrorCallback apiErrorCallback, Object... objArr) {
        final String str2 = str;
        final Object[] objArr2 = objArr;
        final Function2<T, Map<String, List<String>>> function22 = function2;
        final ApiErrorCallback apiErrorCallback2 = apiErrorCallback;
        AnonymousClass2 r0 = new SafeAsyncTask<T>() {
            public T call() throws Exception {
                return MfpApiImpl.this.get(str2, objArr2);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(T t) throws Exception {
                super.onSuccess(t);
                FunctionUtils.invokeIfValid(function22, t, MfpApiImpl.this.responseHeaders);
            }

            /* access modifiers changed from: protected */
            public void onException(Exception exc) {
                super.onException(exc);
                MfpApiImpl.this.invokeErrorCallback(apiErrorCallback2, exc);
            }
        };
        r0.execute();
    }

    public <T> T post(Object... objArr) throws ApiException {
        return post("", objArr);
    }

    public <T> T post(String str, Object... objArr) throws ApiException {
        return wrapRequestWithAuthFailureRetry(new CheckedReturningFunction2<T, String, Object[], ApiException>() {
            public T execute(String str, Object[] objArr) throws ApiException {
                return MfpApiImpl.this.performPost(str, objArr);
            }
        }, str, objArr);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0009, code lost:
        return r1.execute(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return throwAndNotifyAuthFailed(r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T> T wrapRequestWithAuthFailureRetry(com.uacf.core.util.CheckedReturningFunction2<T, java.lang.String, java.lang.Object[], com.myfitnesspal.shared.api.ApiException> r1, java.lang.String r2, java.lang.Object... r3) throws com.myfitnesspal.shared.api.ApiException {
        /*
            r0 = this;
            java.lang.Object r1 = r1.execute(r2, r3)     // Catch:{ HttpAuthFailureRetryException -> 0x0005 }
            return r1
        L_0x0005:
            java.lang.Object r1 = r1.execute(r2, r3)     // Catch:{ HttpAuthFailureRetryException -> 0x000a }
            return r1
        L_0x000a:
            r1 = move-exception
            java.lang.Object r1 = r0.throwAndNotifyAuthFailed(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.api.MfpApiImpl.wrapRequestWithAuthFailureRetry(com.uacf.core.util.CheckedReturningFunction2, java.lang.String, java.lang.Object[]):java.lang.Object");
    }

    private <T> T throwAndNotifyAuthFailed(HttpAuthFailureRetryException httpAuthFailureRetryException) throws CouldNotRetryException {
        ((Bus) this.messageBus.get()).post(new AuthFailedEvent());
        throw new CouldNotRetryException(httpAuthFailureRetryException, httpAuthFailureRetryException.getStatusCode());
    }

    /* access modifiers changed from: private */
    public <T> T performPost(String str, Object... objArr) throws ApiException {
        return makeRequest(str, HttpConstants.METHOD_POST, objArr);
    }

    public <TTransform, T> TTransform post(ReturningFunction1<TTransform, T> returningFunction1, Object... objArr) throws ApiException {
        return returningFunction1.execute(post(objArr));
    }

    public <TTransform, T> TTransform post(String str, ReturningFunction1<TTransform, T> returningFunction1, Object... objArr) throws ApiException {
        return returningFunction1.execute(post(str, objArr));
    }

    public <T> void postAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        postAsync("", function1, apiErrorCallback, objArr);
    }

    public <T> void postAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        postAsync(str, function1, apiErrorCallback, (ReturningFunction1<TTransform, T>) new ReturningFunction1<T, T>() {
            public T execute(T t) {
                return t;
            }
        }, objArr);
    }

    public <T> void postAsync(String str, Function2<T, Map<String, List<String>>> function2, ApiErrorCallback apiErrorCallback, Object... objArr) {
        postAsync(str, function2, apiErrorCallback, (ReturningFunction1<TTransform, T>) new ReturningFunction1<T, T>() {
            public T execute(T t) {
                return t;
            }
        }, objArr);
    }

    public void postAsync(Function0 function0, ApiErrorCallback apiErrorCallback, Object... objArr) {
        postAsync("", function0, apiErrorCallback, objArr);
    }

    public void postAsync(String str, final Function0 function0, ApiErrorCallback apiErrorCallback, Object... objArr) {
        postAsync(str, (Function1<T>) new Function1<Object>() {
            public void execute(Object obj) {
                FunctionUtils.invokeIfValid(function0);
            }
        }, apiErrorCallback, objArr);
    }

    public <TTransform, T> void postAsync(Function1<TTransform> function1, ApiErrorCallback apiErrorCallback, ReturningFunction1<TTransform, T> returningFunction1, Object... objArr) {
        postAsync("", function1, apiErrorCallback, returningFunction1, objArr);
    }

    public <TTransform, T> void postAsync(String str, final Function1<TTransform> function1, ApiErrorCallback apiErrorCallback, ReturningFunction1<TTransform, T> returningFunction1, Object... objArr) {
        postAsync(str, (Function2<TTransform, Map<String, List<String>>>) new Function2<TTransform, Map<String, List<String>>>() {
            public void execute(TTransform ttransform, Map<String, List<String>> map) throws RuntimeException {
                FunctionUtils.invokeIfValid(function1, ttransform);
            }
        }, apiErrorCallback, returningFunction1, objArr);
    }

    public <TTransform, T> void postAsync(String str, Function2<TTransform, Map<String, List<String>>> function2, ApiErrorCallback apiErrorCallback, ReturningFunction1<TTransform, T> returningFunction1, Object... objArr) {
        final String str2 = str;
        final ReturningFunction1<TTransform, T> returningFunction12 = returningFunction1;
        final Object[] objArr2 = objArr;
        final Function2<TTransform, Map<String, List<String>>> function22 = function2;
        final ApiErrorCallback apiErrorCallback2 = apiErrorCallback;
        AnonymousClass8 r0 = new SafeAsyncTask<TTransform>() {
            public TTransform call() throws Exception {
                return MfpApiImpl.this.post(str2, returningFunction12, objArr2);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(TTransform ttransform) throws Exception {
                super.onSuccess(ttransform);
                FunctionUtils.invokeIfValid(function22, ttransform, MfpApiImpl.this.responseHeaders);
            }

            /* access modifiers changed from: protected */
            public void onException(Exception exc) {
                super.onException(exc);
                MfpApiImpl.this.invokeErrorCallback(apiErrorCallback2, exc);
            }
        };
        r0.execute();
    }

    public <T> T put() throws ApiException {
        return put("");
    }

    public <T> T put(String str) throws ApiException {
        return put(str, null);
    }

    public <T> T put(String str, Object... objArr) throws ApiException {
        return wrapRequestWithAuthFailureRetry(new CheckedReturningFunction2<T, String, Object[], ApiException>() {
            public T execute(String str, Object[] objArr) throws ApiException {
                return MfpApiImpl.this.performPut(str, objArr);
            }
        }, str, objArr);
    }

    /* access modifiers changed from: private */
    public <T> T performPut(String str, Object... objArr) throws ApiException {
        return makeRequest(str, HttpConstants.METHOD_PUT, objArr);
    }

    public <T> void putAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback) {
        putAsync("", function1, apiErrorCallback, null);
    }

    public <T> void putAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback) {
        putAsync(str, function1, apiErrorCallback, null);
    }

    public <T> void putAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        putAsync("", function1, apiErrorCallback, objArr);
    }

    public <T> void putAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        final String str2 = str;
        final Object[] objArr2 = objArr;
        final Function1<T> function12 = function1;
        final ApiErrorCallback apiErrorCallback2 = apiErrorCallback;
        AnonymousClass10 r0 = new SafeAsyncTask<T>() {
            public T call() throws Exception {
                return MfpApiImpl.this.put(str2, objArr2);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(T t) throws Exception {
                super.onSuccess(t);
                FunctionUtils.invokeIfValid(function12, t);
            }

            /* access modifiers changed from: protected */
            public void onException(Exception exc) {
                super.onException(exc);
                MfpApiImpl.this.invokeErrorCallback(apiErrorCallback2, exc);
            }
        };
        r0.execute();
    }

    public <T> T delete() throws ApiException {
        return delete("");
    }

    public <T> T delete(String str) throws ApiException {
        return delete(str, null);
    }

    public <T> T delete(String str, Object... objArr) throws ApiException {
        return wrapRequestWithAuthFailureRetry(new CheckedReturningFunction2<T, String, Object[], ApiException>() {
            public T execute(String str, Object[] objArr) throws ApiException {
                return MfpApiImpl.this.performDelete(str);
            }
        }, str, objArr);
    }

    /* access modifiers changed from: private */
    public <T> T performDelete(String str) throws ApiException {
        return makeRequest(str, HttpConstants.METHOD_DELETE, null);
    }

    public <T> void deleteAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback) {
        deleteAsync(str, function1, apiErrorCallback, null);
    }

    public <T> void deleteAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        final String str2 = str;
        final Object[] objArr2 = objArr;
        final Function1<T> function12 = function1;
        final ApiErrorCallback apiErrorCallback2 = apiErrorCallback;
        AnonymousClass12 r0 = new SafeAsyncTask<T>() {
            public T call() throws Exception {
                return MfpApiImpl.this.delete(str2, objArr2);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(T t) throws Exception {
                super.onSuccess(t);
                FunctionUtils.invokeIfValid(function12, t);
            }

            /* access modifiers changed from: protected */
            public void onException(Exception exc) {
                super.onException(exc);
                MfpApiImpl.this.invokeErrorCallback(apiErrorCallback2, exc);
            }
        };
        r0.execute();
    }

    public <T> T patch() throws ApiException {
        return patch("");
    }

    public <T> T patch(String str) throws ApiException {
        return patch(str, null);
    }

    public <T> T patch(String str, Object... objArr) throws ApiException {
        return wrapRequestWithAuthFailureRetry(new CheckedReturningFunction2<T, String, Object[], ApiException>() {
            public T execute(String str, Object[] objArr) throws ApiException {
                return MfpApiImpl.this.performPatch(str, objArr);
            }
        }, str, objArr);
    }

    /* access modifiers changed from: private */
    public <T> T performPatch(String str, Object[] objArr) throws ApiException {
        return makeRequest(str, "PATCH", objArr);
    }

    public <T> void patchAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback) {
        patchAsync("", function1, apiErrorCallback);
    }

    public <T> void patchAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        patchAsync("", function1, apiErrorCallback, objArr);
    }

    public <T> void patchAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback) {
        patchAsync(str, function1, apiErrorCallback, null);
    }

    public <T> void patchAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr) {
        final String str2 = str;
        final Object[] objArr2 = objArr;
        final Function1<T> function12 = function1;
        final ApiErrorCallback apiErrorCallback2 = apiErrorCallback;
        AnonymousClass14 r0 = new SafeAsyncTask<T>() {
            public T call() throws Exception {
                return MfpApiImpl.this.patch(str2, objArr2);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(T t) throws Exception {
                super.onSuccess(t);
                FunctionUtils.invokeIfValid(function12, t);
            }

            /* access modifiers changed from: protected */
            public void onException(Exception exc) {
                super.onException(exc);
                MfpApiImpl.this.invokeErrorCallback(apiErrorCallback2, exc);
            }
        };
        r0.execute();
    }

    public TApiType withMapper(Mapper2<?, TInput> mapper2) {
        this.mapper = mapper2;
        return this;
    }

    public <TOutput> TApiType withOutputType(Class<TOutput> cls) {
        this.mapper.withType(cls);
        return this;
    }

    public TApiType withBody(Object obj) {
        this.rawBody = obj;
        return this;
    }

    public TApiType withBody(Object obj, String str) {
        this.contentTypeOverride = str;
        return withBody(obj);
    }

    public TApiType withJsonBody(Object obj) {
        return withBody(obj != null ? new ApiJsonRequestData(obj) : null, HttpConstants.CONTENT_TYPE_JSON);
    }

    /* access modifiers changed from: protected */
    public AuthTokenProvider getAuthTokenProvider() {
        AuthTokenProvider authTokenProvider2 = this.authTokenProvider;
        return authTokenProvider2 != null ? authTokenProvider2 : (AuthTokenProvider) this.defaultAuthTokenProvider.get();
    }

    /* access modifiers changed from: protected */
    public String getFullUri(String str, Object[] objArr) {
        Uri.Builder builder;
        Uri parse = Uri.parse(getUrlWithPathEncoded(str));
        if (!parse.isAbsolute()) {
            Uri parse2 = Uri.parse(getBaseUrl());
            builder = parse2.buildUpon();
            if (Strings.notEmpty(parse.getPath())) {
                builder.encodedPath(parse.getPath());
            }
            builder.encodedQuery(mergeUriParams(parse2, parse));
            builder.encodedFragment(parse.getEncodedFragment());
        } else {
            builder = Uri.parse(str).buildUpon();
        }
        if (ArrayUtil.notEmpty(objArr)) {
            int size = ArrayUtil.size(objArr);
            int i = 0;
            while (i < size) {
                builder.appendQueryParameter(Strings.toString(objArr[i]), i < size + -1 ? getQueryParamAsString(objArr[i + 1], true) : "");
                i += 2;
            }
        }
        return builder.toString();
    }

    private static String mergeUriParams(Uri uri, Uri uri2) {
        String encodedQuery = uri.getEncodedQuery();
        if (Strings.isEmpty(encodedQuery)) {
            encodedQuery = "";
        }
        String encodedQuery2 = uri2 != null ? uri2.getEncodedQuery() : null;
        if (!Strings.notEmpty(encodedQuery2)) {
            return encodedQuery;
        }
        String str = Strings.notEmpty(encodedQuery) ? "&" : "";
        StringBuilder sb = new StringBuilder();
        sb.append(encodedQuery);
        sb.append(str);
        sb.append(encodedQuery2);
        return sb.toString();
    }

    private static String getUrlWithPathEncoded(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        String str = "";
        int indexOf = charSequence2.indexOf("?");
        if (indexOf >= 0) {
            str = charSequence2.substring(indexOf);
            charSequence2 = charSequence2.substring(0, indexOf);
        }
        int indexOf2 = charSequence2.indexOf("://");
        int indexOf3 = charSequence2.indexOf("/", indexOf2 >= 0 ? indexOf2 + 3 : 0);
        if (indexOf3 >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(charSequence2.substring(0, indexOf3));
            sb.append(UriUtils.encodeBrackets(charSequence2.substring(indexOf3)).replace(":", "%3A"));
            charSequence2 = sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(charSequence2);
        sb2.append(str);
        return sb2.toString();
    }

    public <T> T getInterceptedRequestOfT(Request request, String str) throws ApiException {
        InterceptorResponse response = ((RequestInterceptor) this.mockInterceptor.get()).getResponse(request.method(), request.url().encodedPath(), request.url().encodedQuery(), str);
        if (response != null) {
            Object responseData = getResponseData(response);
            if (!isSuccessCode(response.getStatusCode())) {
                throwApiException(response.getStatusCode(), responseData);
            } else {
                T tryMapFrom = this.mapper.tryMapFrom(responseData);
                if (tryMapFrom != null) {
                    Map headers = response.getHeaders();
                    if (headers != null) {
                        Map<String, List<String>> responseHeaders2 = getResponseHeaders();
                        for (String str2 : headers.keySet()) {
                            List list = (List) responseHeaders2.get(str2);
                            if (list == null) {
                                list = new ArrayList();
                            }
                            list.add(headers.get(str2));
                            responseHeaders2.put(str2, list);
                        }
                        this.responseHeaders = responseHeaders2;
                    }
                    return tryMapFrom;
                }
                throw new ApiException((Throwable) new MockInterceptorMapperException(), 0);
            }
        }
        return null;
    }

    public Map<String, List<String>> getResponseHeaders() {
        HashMap hashMap = new HashMap();
        Map<String, List<String>> map = this.responseHeaders;
        if (map != null) {
            for (String str : map.keySet()) {
                hashMap.put(str, new ArrayList((Collection) this.responseHeaders.get(str)));
            }
        }
        return hashMap;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    private <T> T makeRequest(String str, String str2, Object[] objArr) throws ApiException {
        T t;
        String fullUri = getFullUri(str, objArr);
        Response response = null;
        try {
            OkHttpClient client = client();
            Builder builder = new Builder();
            builder.url(fullUri);
            addHeadersTo(builder);
            addCredentialsTo(builder);
            if ("PATCH".equals(str2)) {
                str2 = HttpConstants.METHOD_PUT;
                addHeader(builder, Http.HEADER_X_HTTP_METHOD_OVERRIDE, "PATCH");
            }
            String queryParamAsString = this.rawBody == null ? null : getQueryParamAsString(this.rawBody, false);
            RequestBody requestBodyOverride = getRequestBodyOverride();
            if (requestBodyOverride == null) {
                if (Strings.notEmpty(queryParamAsString)) {
                    requestBodyOverride = RequestBody.create(Strings.notEmpty(this.contentTypeOverride) ? MediaType.parse(String.format("%s; charset=utf-8", new Object[]{this.contentTypeOverride})) : null, queryParamAsString);
                } else if (!HttpConstants.METHOD_GET.equals(str2)) {
                    FormBody.Builder builder2 = new FormBody.Builder();
                    if (objArr != null && objArr.length > 0) {
                        Map encodedQueryParams = getEncodedQueryParams(objArr);
                        for (String str3 : encodedQueryParams.keySet()) {
                            builder2.addEncoded(str3, (String) encodedQueryParams.get(str3));
                        }
                    }
                    requestBodyOverride = builder2.build();
                }
            }
            Request build = builder.method(str2, requestBodyOverride).build();
            T interceptedRequestOfT = getInterceptedRequestOfT(build, queryParamAsString);
            if (interceptedRequestOfT != null) {
                return interceptedRequestOfT;
            }
            response = client.newCall(build).execute();
            this.responseHeaders = response.headers().toMultimap();
            this.responseCode = response.code();
            handleUnauthorizedResultIfNecessary(this.responseCode);
            if (!isSuccessCode(this.responseCode)) {
                throwApiException(this.responseCode, getResponseData(response));
            }
            if (this.responseCode == 204) {
                t = new ApiResponseBase();
            } else if (this.responseCode == 409) {
                throw new DuplicateResourceException("Conflict for %s", build.url().toString());
            } else if (this.mapper == null) {
                t = getResponseData(response);
            } else {
                t = this.mapper.mapFrom(getResponseData(response));
            }
            if (!(response == null || response.body() == null)) {
                response.body().close();
            }
            return t;
        } catch (ApiException e) {
            throw e;
        } catch (IOException e2) {
            throw new ApiException((Throwable) e2, 556);
        } catch (Exception e3) {
            throw new ApiException((Throwable) e3, 555);
        } catch (Throwable th) {
            if (!(response == null || response.body() == null)) {
                response.body().close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void postEvent(MfpEventBase mfpEventBase) {
        ((Bus) this.messageBus.get()).post(mfpEventBase);
    }

    /* access modifiers changed from: protected */
    public final Builder addHeader(Builder builder, String str, Object obj) {
        if (this.requestHeaders == null) {
            this.requestHeaders = new HashMap();
        }
        if (!this.requestHeaders.containsKey(str)) {
            this.requestHeaders.put(str, new ArrayList());
        }
        List list = (List) this.requestHeaders.get(str);
        String strings = Strings.toString(obj);
        list.add(!Strings.equals(str, "Authorization") ? strings : "Bearer AUTH_TOKEN_HERE");
        builder.addHeader(str, strings);
        return builder;
    }

    private Map<String, String> getEncodedQueryParams(Object[] objArr) {
        if (objArr != null) {
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                String queryParamAsString = getQueryParamAsString(objArr[i], false);
                objArr[i] = queryParamAsString;
                if (i < length + -1) {
                    int i2 = i + 1;
                    objArr[i2] = getQueryParamAsString(objArr[i2], !DISABLE_URL_ENCODING_KEYS.contains(queryParamAsString));
                }
                i += 2;
            }
        }
        return CollectionUtils.nameValuePairsToMap(objArr);
    }

    private static String getQueryParamAsString(Object obj, boolean z) {
        if (obj instanceof ApiJsonRequestData) {
            return new ApiJsonMapper().reverseMap(((ApiJsonRequestData) obj).getData());
        }
        String strings = Strings.toString(obj);
        if (z) {
            strings = UriUtils.encodeBrackets(strings);
        }
        return strings;
    }

    /* access modifiers changed from: private */
    public void invokeErrorCallback(ApiErrorCallback apiErrorCallback, Exception exc) {
        Ln.e(exc.getLocalizedMessage(), new Object[0]);
        FunctionUtils.invokeIfValid(apiErrorCallback, exc instanceof ApiException ? (ApiException) exc : new ApiException((Throwable) exc, 555));
    }

    /* access modifiers changed from: protected */
    public UUID getDeviceId() {
        return this.deviceId;
    }
}
