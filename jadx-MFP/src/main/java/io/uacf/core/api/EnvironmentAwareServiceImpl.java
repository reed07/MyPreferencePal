package io.uacf.core.api;

import android.content.Context;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import io.opentracing.Tracer;
import io.uacf.core.util.ContextUtil;

public abstract class EnvironmentAwareServiceImpl extends SimpleAsyncServiceBase {
    @Deprecated
    private UacfApiEnvironmentProvider apiEnvironmentProvider;
    private final String clientId;
    private final String clientSecret;
    protected final Context context;
    protected final UacfUserAgentProvider userAgentProvider;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "EnvironmentAwareServiceImpl";
    }

    @Deprecated
    public EnvironmentAwareServiceImpl(Context context2, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider) {
        this(context2, uacfUserAgentProvider, uacfApiEnvironmentProvider, null);
    }

    @Deprecated
    public EnvironmentAwareServiceImpl(Context context2, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, Tracer tracer) {
        this(context2, uacfUserAgentProvider, null, null, tracer);
        this.apiEnvironmentProvider = uacfApiEnvironmentProvider;
    }

    public EnvironmentAwareServiceImpl(Context context2, UacfUserAgentProvider uacfUserAgentProvider, String str, String str2, Tracer tracer) {
        super(tracer);
        this.context = ContextUtil.getApplicationContextSafe(context2);
        this.userAgentProvider = uacfUserAgentProvider;
        this.apiEnvironmentProvider = null;
        this.clientId = str;
        this.clientSecret = str2;
    }

    /* access modifiers changed from: protected */
    public UacfApiEnvironment getCurrentApiEnvironment() {
        return this.apiEnvironmentProvider.get();
    }

    /* access modifiers changed from: protected */
    public String getClientId() {
        return this.clientId;
    }

    /* access modifiers changed from: protected */
    public String getClientSecret() {
        return this.clientSecret;
    }
}
