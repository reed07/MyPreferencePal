package io.uacf.net.retrofit;

import com.google.common.net.HttpHeaders;
import com.uacf.core.mock.interceptor.simple.SimpleRequestInterceptor;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import io.uacf.core.api.UacfApiException;
import java.io.IOException;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Response;

public final class UacfRetrofitHelper {
    public static final SimpleRequestInterceptor GlobalRequestInterceptor = new SimpleRequestInterceptor();

    public static <T> T execute(Call<T> call) throws UacfApiException {
        return executeAndReturnFullResponse(call).body();
    }

    public static <T> Response<T> executeAndReturnFullResponse(Call<T> call) throws UacfApiException {
        try {
            Response<T> execute = call.execute();
            if (execute.isSuccessful() || execute.code() < 400) {
                return execute;
            }
            String str = null;
            try {
                str = execute.errorBody().string();
            } catch (IOException unused) {
            }
            throw new UacfApiException(execute.message(), execute.code(), addRequestIdToBody(execute, str));
        } catch (Exception e) {
            throw new UacfApiException((Throwable) e);
        }
    }

    public static <T> Tuple2<String, String> executeForRedirectOnly(Call<T> call) throws UacfApiException {
        try {
            Response execute = call.execute();
            if (execute.isSuccessful() || execute.code() < 400) {
                Headers headers = execute.headers();
                return Tuple.create(headers.get(HttpHeaders.LOCATION), headers.get("UACF-Request-ID"));
            }
            String str = null;
            try {
                str = execute.errorBody().string();
            } catch (IOException unused) {
            }
            throw new UacfApiException(execute.message(), execute.code(), addRequestIdToBody(execute, str));
        } catch (Exception e) {
            throw new UacfApiException((Throwable) e);
        }
    }

    private static <T> String addRequestIdToBody(Response<T> response, String str) {
        String str2 = response.headers().get("UACF-Request-ID");
        if (!Strings.notEmpty(str2)) {
            return str;
        }
        return String.format("Request ID: %s; %s", new Object[]{str2, Strings.toString(str)});
    }
}
