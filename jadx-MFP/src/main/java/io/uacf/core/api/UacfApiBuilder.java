package io.uacf.core.api;

import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.uacf.core.util.Base64;
import io.uacf.core.api.UacfApiBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UacfApiBuilder<TBuilder extends UacfApiBuilder<TBuilder, TInterface>, TInterface> {
    protected boolean followRedirects = true;
    protected Map<String, List<String>> headers = new HashMap();

    protected UacfApiBuilder() {
    }

    public TBuilder addHeader(String str, String str2) {
        List list = (List) this.headers.get(str);
        if (list == null) {
            list = new ArrayList();
            this.headers.put(str, list);
        }
        list.add(str2);
        return this;
    }

    public TBuilder followRedirects(boolean z) {
        this.followRedirects = z;
        return this;
    }

    public TBuilder withBasicAuth(String str, String str2) {
        ensureHeaders();
        addHeader("Authorization", String.format("Basic %s", new Object[]{Base64.encodeToString(String.format("%s:%s", new Object[]{str, str2}).getBytes(), 10)}));
        return this;
    }

    public TBuilder withBearerAuth(String str) {
        ensureHeaders();
        addHeader("Authorization", String.format(Http.BEARER_AUTH_FORMAT, new Object[]{str}));
        return this;
    }

    public TBuilder withUserAgent(String str) {
        ensureHeaders();
        addHeader("User-Agent", str);
        return this;
    }

    private void ensureHeaders() {
        if (this.headers == null) {
            this.headers = new HashMap();
        }
    }
}
