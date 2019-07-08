package io.uacf.net.retrofit;

import com.myfitnesspal.shared.constants.HttpConstants;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Request.Builder;
import okhttp3.Response;

final class UacfCommonInterceptor implements Interceptor {
    private Map<String, List<String>> headers;

    public UacfCommonInterceptor() {
        this(null);
    }

    public UacfCommonInterceptor(Map<String, List<String>> map) {
        this.headers = map;
    }

    public Response intercept(Chain chain) throws IOException {
        final Builder addHeader = chain.request().newBuilder().addHeader("Content-Type", HttpConstants.CONTENT_TYPE_JSON).addHeader("Accept", HttpConstants.CONTENT_TYPE_JSON);
        if (CollectionUtils.notEmpty(this.headers)) {
            Enumerable.forEach((Collection<T>) this.headers.entrySet(), (Function1<T>) new Function1<Entry<String, List<String>>>() {
                public void execute(Entry<String, List<String>> entry) {
                    addHeader.addHeader((String) entry.getKey(), Strings.join(",", (Collection) entry.getValue()));
                }
            });
        }
        return chain.proceed(addHeader.build());
    }
}
