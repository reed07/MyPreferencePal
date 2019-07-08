package io.uacf.net.retrofit;

import com.uacf.core.tracing.FSTracing;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Tuple2;
import io.opentracing.Scope;
import io.opentracing.Tracer;
import io.uacf.core.api.UacfApiException;
import java.util.Locale;
import retrofit2.Call;

public class FSTraceableRetrofitHelper {
    private Tracer tracer;

    private FSTraceableRetrofitHelper(Tracer tracer2) {
        this.tracer = tracer2;
    }

    public static FSTraceableRetrofitHelper initialize(Tracer tracer2) {
        return new FSTraceableRetrofitHelper(tracer2);
    }

    public <T> T execute(Call<T> call) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        T t = null;
        try {
            t = UacfRetrofitHelper.execute(call);
        } catch (UacfApiException e) {
            try {
                FSTracing.logThenThrowException(startActiveSpanForMethod, e);
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (startActiveSpanForMethod != null) {
            startActiveSpanForMethod.close();
        }
        return t;
        throw th;
    }

    public <T> Tuple2<String, String> executeForRedirectOnly(Call<T> call) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        Tuple2<String, String> tuple2 = null;
        try {
            tuple2 = UacfRetrofitHelper.executeForRedirectOnly(call);
        } catch (UacfApiException e) {
            try {
                FSTracing.logThenThrowException(startActiveSpanForMethod, e);
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (startActiveSpanForMethod != null) {
            startActiveSpanForMethod.close();
        }
        return tuple2;
        throw th;
    }

    private Scope startActiveSpanForMethod() {
        String str;
        if (this.tracer != null) {
            try {
                StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
                String[] split = stackTraceElement.getClassName().split("\\.");
                str = String.format(Locale.US, "%s.%s()", new Object[]{split[split.length - 1], stackTraceElement.getMethodName()});
                try {
                    return this.tracer.buildSpan(str).startActive(true);
                } catch (Exception e) {
                    e = e;
                    StringBuilder sb = new StringBuilder();
                    sb.append("FSTraceableRetrofitHelper: Failed to create start span ");
                    sb.append(str);
                    Ln.e(e, sb.toString(), new Object[0]);
                    return null;
                }
            } catch (Exception e2) {
                e = e2;
                str = null;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("FSTraceableRetrofitHelper: Failed to create start span ");
                sb2.append(str);
                Ln.e(e, sb2.toString(), new Object[0]);
                return null;
            }
        }
        return null;
    }
}
