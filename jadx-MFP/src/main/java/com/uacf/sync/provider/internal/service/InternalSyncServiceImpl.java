package com.uacf.sync.provider.internal.service;

import android.content.Context;
import android.support.annotation.NonNull;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple3;
import com.uacf.sync.provider.internal.model.SyncRawResponseItem;
import com.uacf.sync.provider.sdk.model.SyncMode;
import io.uacf.core.api.ApiResponse;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.net.retrofit.RetrofitBasedServiceImpl;
import io.uacf.net.retrofit.UacfApiRetrofitBuilder;
import io.uacf.net.retrofit.UacfRetrofitHelper;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class InternalSyncServiceImpl extends RetrofitBasedServiceImpl implements InternalSyncService {
    private final UacfAppId appId;

    private interface SyncConsumer {
        @GET("v2.1/import")
        Call<ApiResponse<SyncRawResponseItem>> doImport(@Query("application") String str, @Query("import_token") String str2, @Query(encoded = true, value = "filter%5B%5D") String... strArr);

        @GET("v2.1/sync")
        Call<ApiResponse<SyncRawResponseItem>> doSync(@Query("application") String str, @Query("sync_token") String str2);
    }

    public InternalSyncServiceImpl(Context context, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfAuthProvider uacfAuthProvider, UacfAppId uacfAppId, OkHttpClient okHttpClient, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        super(context, uacfUserAgentProvider, uacfApiEnvironmentProvider, uacfAuthProvider, okHttpClient, uacfOkHttpNetworkTracer);
        this.appId = uacfAppId;
    }

    /* access modifiers changed from: protected */
    public Class<?> getConsumerClass() {
        return SyncConsumer.class;
    }

    public Tuple3<Integer, List<SyncRawResponseItem>, Map<String, List<String>>> doImport(String str, SyncMode syncMode) throws UacfApiException {
        Ln.d("SYNCV2: doImport fetching next page, mode = %s, token = %s, filters = %s", syncMode, str, Strings.join(",", (Collection<T>) syncMode.getFilters()));
        SyncConsumer syncConsumer = (SyncConsumer) getConsumerWithUnderscoresAndBearerAuth();
        List filters = syncMode.getFilters();
        return getTupleFromResponse(UacfRetrofitHelper.executeAndReturnFullResponse(syncConsumer.doImport(this.appId.getBaseAppName(), str, (String[]) filters.toArray(new String[CollectionUtils.size((Collection<?>) filters)]))));
    }

    public Tuple3<Integer, List<SyncRawResponseItem>, Map<String, List<String>>> doSync(String str) throws UacfApiException {
        Ln.d("SYNCV2: doSync fetching next page, token = %s", str);
        return getTupleFromResponse(UacfRetrofitHelper.executeAndReturnFullResponse(((SyncConsumer) ((UacfApiRetrofitBuilder) getBuilderWithUnderscoresAndBearerAuth().followRedirects(false)).build()).doSync(this.appId.getBaseAppName(), str)));
    }

    @NonNull
    private Tuple3<Integer, List<SyncRawResponseItem>, Map<String, List<String>>> getTupleFromResponse(Response<ApiResponse<SyncRawResponseItem>> response) {
        int code = response.code();
        Headers headers = response.headers();
        ApiResponse apiResponse = (ApiResponse) response.body();
        return Tuple.create(Integer.valueOf(code), apiResponse != null ? apiResponse.getItems() : new ArrayList(), headers != null ? headers.toMultimap() : new HashMap());
    }
}
