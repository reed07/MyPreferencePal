package io.uacf.net.retrofit;

import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Tuple2;
import io.opentracing.Tracer;
import io.uacf.core.api.UacfApiImpl;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public class UacfRetrofitImpl extends UacfApiImpl<UacfRetrofitImpl> {
    private static final OkHttpClient GlobalHttpClient = new Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();
    private List<Factory> converterFactories;
    private OkHttpClient customClient = null;
    private Tracer tracer = null;

    public <TInterface, TImpl extends TInterface> TInterface create(Class<TImpl> cls) {
        Builder builder;
        OkHttpClient okHttpClient = this.customClient;
        if (okHttpClient == null) {
            builder = GlobalHttpClient.newBuilder();
        } else {
            builder = okHttpClient.newBuilder();
        }
        Builder followSslRedirects = builder.addInterceptor(new UacfCommonInterceptor(this.headers)).followRedirects(this.followRedirects).followSslRedirects(this.followRedirects);
        final HttpUrl.Builder newBuilder = HttpUrl.parse(this.baseUrl).newBuilder();
        Enumerable.forEach((Collection<T>) this.queryParams, (Function1<T>) new Function1<Tuple2<String, String>>() {
            public void execute(Tuple2<String, String> tuple2) {
                newBuilder.addQueryParameter((String) tuple2.getItem1(), (String) tuple2.getItem2());
            }
        });
        HttpUrl build = newBuilder.build();
        OkHttpClient build2 = followSslRedirects.build();
        final Retrofit.Builder client = new Retrofit.Builder().baseUrl(build).client(build2);
        Tracer tracer2 = this.tracer;
        if (tracer2 != null) {
            client.callFactory(new UacfOpenTracingCallFactory.Builder(build2, tracer2).build());
        }
        Enumerable.forEach((Collection<T>) this.converterFactories, (Function1<T>) new Function1<Factory>() {
            public void execute(Factory factory) {
                client.addConverterFactory(factory);
            }
        });
        return client.build().create(cls);
    }

    public UacfRetrofitImpl withConverterFactories(List<Factory> list) {
        this.converterFactories = list;
        return this;
    }

    public UacfRetrofitImpl withCustomHttpClient(OkHttpClient okHttpClient) {
        this.customClient = okHttpClient;
        return this;
    }

    public UacfRetrofitImpl withTracer(Tracer tracer2) {
        this.tracer = tracer2;
        return this;
    }
}
