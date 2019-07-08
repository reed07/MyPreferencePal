package com.myfitnesspal.shared.api;

import com.myfitnesspal.shared.api.MfpApi;
import com.myfitnesspal.shared.api.auth.AuthTokenProvider;
import com.uacf.core.mapping.Mapper2;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.ReturningFunction1;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public interface MfpApi<TApiType extends MfpApi, TInput> {
    <T> T delete() throws ApiException;

    <T> T delete(String str) throws ApiException;

    <T> T delete(String str, Object... objArr) throws ApiException;

    <T> void deleteAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback);

    <T> void deleteAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    <T> T get() throws ApiException;

    <T> T get(String str) throws ApiException;

    <T> T get(String str, Object... objArr) throws ApiException;

    <T> void getAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback);

    <T> void getAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    <T> void getAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback);

    <T> void getAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    <T> void getAsync(String str, Function2<T, Map<String, List<String>>> function2, ApiErrorCallback apiErrorCallback, Object... objArr);

    int getResponseCode();

    Map<String, List<String>> getResponseHeaders();

    <T> T patch() throws ApiException;

    <T> T patch(String str) throws ApiException;

    <T> T patch(String str, Object... objArr) throws ApiException;

    <T> void patchAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback);

    <T> void patchAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    <T> void patchAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback);

    <T> void patchAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    <TTransform, T> TTransform post(ReturningFunction1<TTransform, T> returningFunction1, Object... objArr) throws ApiException;

    <TTransform, T> TTransform post(String str, ReturningFunction1<TTransform, T> returningFunction1, Object... objArr) throws ApiException;

    <T> T post(String str, Object... objArr) throws ApiException;

    <T> T post(Object... objArr) throws ApiException;

    void postAsync(Function0 function0, ApiErrorCallback apiErrorCallback, Object... objArr);

    <TTransform, T> void postAsync(Function1<TTransform> function1, ApiErrorCallback apiErrorCallback, ReturningFunction1<TTransform, T> returningFunction1, Object... objArr);

    <T> void postAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    void postAsync(String str, Function0 function0, ApiErrorCallback apiErrorCallback, Object... objArr);

    <TTransform, T> void postAsync(String str, Function1<TTransform> function1, ApiErrorCallback apiErrorCallback, ReturningFunction1<TTransform, T> returningFunction1, Object... objArr);

    <T> void postAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    <TTransform, T> void postAsync(String str, Function2<TTransform, Map<String, List<String>>> function2, ApiErrorCallback apiErrorCallback, ReturningFunction1<TTransform, T> returningFunction1, Object... objArr);

    <T> void postAsync(String str, Function2<T, Map<String, List<String>>> function2, ApiErrorCallback apiErrorCallback, Object... objArr);

    <T> T put() throws ApiException;

    <T> T put(String str) throws ApiException;

    <T> T put(String str, Object... objArr) throws ApiException;

    <T> void putAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback);

    <T> void putAsync(Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    <T> void putAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback);

    <T> void putAsync(String str, Function1<T> function1, ApiErrorCallback apiErrorCallback, Object... objArr);

    TApiType withAuthTokenProvider(AuthTokenProvider authTokenProvider);

    TApiType withBinaryData(ByteBuffer byteBuffer);

    TApiType withBody(Object obj);

    TApiType withBody(Object obj, String str);

    TApiType withFlowId(String str);

    TApiType withFollowRedirects(boolean z);

    TApiType withJsonBody(Object obj);

    TApiType withMapper(Mapper2<?, TInput> mapper2);

    <TOutput> TApiType withOutputType(Class<TOutput> cls);

    TApiType withTimeouts(int i, int i2);
}
