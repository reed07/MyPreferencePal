package io.uacf.net.retrofit;

import android.support.annotation.Nullable;
import com.google.gson.FieldNamingPolicy;
import com.uacf.core.mapping.UacfGsonFactory;
import io.opentracing.Tracer;
import io.uacf.core.api.UacfApiBuilder;
import java.util.Arrays;
import okhttp3.OkHttpClient;
import retrofit2.Converter.Factory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UacfApiRetrofitBuilder<TInterface> extends UacfApiBuilder<UacfApiRetrofitBuilder<TInterface>, TInterface> {
    private final String baseUrl;
    private Class<TInterface> clazz;
    private FieldNamingPolicy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
    private OkHttpClient okHttpClient;
    private Tracer tracer;

    public UacfApiRetrofitBuilder(String str) {
        this.baseUrl = str;
    }

    public TInterface build() {
        return ((UacfRetrofitImpl) ((UacfRetrofitImpl) ((UacfRetrofitImpl) new UacfRetrofitImpl().withBaseUrl(this.baseUrl)).withHeaders(this.headers)).withConverterFactories(Arrays.asList(new Factory[]{new NullOnEmptyConverterFactory(), GsonConverterFactory.create(UacfGsonFactory.newInstance(this.fieldNamingPolicy))})).followRedirects(this.followRedirects)).withCustomHttpClient(this.okHttpClient).withTracer(this.tracer).create(this.clazz);
    }

    public UacfApiRetrofitBuilder<TInterface> withClass(Class<TInterface> cls) {
        this.clazz = cls;
        return this;
    }

    public UacfApiRetrofitBuilder<TInterface> withFieldNamingPolicy(FieldNamingPolicy fieldNamingPolicy2) {
        this.fieldNamingPolicy = fieldNamingPolicy2;
        return this;
    }

    public UacfApiRetrofitBuilder<TInterface> withCustomHttpClient(@Nullable OkHttpClient okHttpClient2) {
        this.okHttpClient = okHttpClient2;
        return this;
    }

    public UacfApiRetrofitBuilder<TInterface> withTracer(Tracer tracer2) {
        this.tracer = tracer2;
        return this;
    }
}
