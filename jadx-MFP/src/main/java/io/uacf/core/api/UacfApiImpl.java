package io.uacf.core.api;

import com.uacf.core.util.Tuple2;
import io.uacf.core.api.UacfApiImpl;
import java.util.List;
import java.util.Map;

public abstract class UacfApiImpl<T extends UacfApiImpl> implements UacfApi<T> {
    protected String baseUrl;
    protected boolean followRedirects = true;
    protected Map<String, List<String>> headers;
    protected List<Tuple2<String, String>> queryParams;

    protected UacfApiImpl() {
    }

    public T withHeaders(Map<String, List<String>> map) {
        this.headers = map;
        return this;
    }

    public T withBaseUrl(String str) {
        this.baseUrl = str;
        return this;
    }

    public T followRedirects(boolean z) {
        this.followRedirects = z;
        return this;
    }
}
