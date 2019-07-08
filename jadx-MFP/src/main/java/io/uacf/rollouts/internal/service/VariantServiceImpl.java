package io.uacf.rollouts.internal.service;

import android.content.Context;
import com.google.gson.FieldNamingPolicy;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import io.uacf.core.api.ApiResponse;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfUserAccountDomain;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.net.retrofit.RetrofitBasedServiceImpl;
import io.uacf.net.retrofit.UacfApiRetrofitBuilder;
import io.uacf.net.retrofit.UacfRetrofitHelper;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import io.uacf.rollouts.internal.database.VariantsTable;
import io.uacf.rollouts.internal.model.Variant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class VariantServiceImpl extends RetrofitBasedServiceImpl implements VariantService {
    private static String defaultVariantValuesAsJsonString;
    private static final Map<String, List<Variant>> inMemoryDataStoreMap = new HashMap();
    private static final Map<String, Long> lastSyncedTimesMap = new HashMap();
    private final UacfAppId appId;
    private final String appVersion;
    private final UacfClientEventsCallback clientEventsCallback;
    private final String deviceId;
    private final UacfUserAccountDomain domain;
    private final VariantsTable variantsTable;

    private static final class VariantList extends ApiResponse<Variant> {
        private VariantList() {
        }
    }

    private interface VariantServiceApiConsumer {
        @POST("variants")
        Call<ApiResponse<Variant>> fetchVariants(@Body VariantsRequest variantsRequest);
    }

    public VariantServiceImpl(Context context, String str, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfAuthProvider uacfAuthProvider, UacfAppId uacfAppId, UacfUserAccountDomain uacfUserAccountDomain, String str2, SQLiteDatabaseWrapper sQLiteDatabaseWrapper, UacfClientEventsCallback uacfClientEventsCallback, String str3, OkHttpClient okHttpClient, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        super(context, uacfUserAgentProvider, uacfApiEnvironmentProvider, uacfAuthProvider, okHttpClient, uacfOkHttpNetworkTracer);
        this.deviceId = str;
        this.appId = uacfAppId;
        this.domain = uacfUserAccountDomain;
        this.appVersion = str2;
        this.variantsTable = new VariantsTable(sQLiteDatabaseWrapper);
        this.clientEventsCallback = uacfClientEventsCallback;
        defaultVariantValuesAsJsonString = str3;
    }

    public Variant getVariant(String str) {
        if (!Strings.isEmpty(str)) {
            for (Variant variant : lookUpVariants()) {
                if (Strings.equals(variant.getRolloutName(), str)) {
                    return variant;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("targetVariantName may not be null or empty");
    }

    public List<Variant> getVariants() {
        return lookUpVariants();
    }

    public void fetchVariants() throws UacfApiException {
        String str;
        if (isDataStale().booleanValue()) {
            if (Strings.notEmpty(this.authProvider.getAccessToken())) {
                str = this.authProvider.getAccessToken();
            } else {
                str = this.authProvider.getClientToken();
            }
            VariantServiceApiConsumer variantServiceApiConsumer = (VariantServiceApiConsumer) ((UacfApiRetrofitBuilder) getBuilderWithUnderscores().withBearerAuth(str)).build();
            VariantsRequest variantsRequest = new VariantsRequest(this.domain, this.authProvider.getUacfUserId(), this.deviceId, null, this.appId.getBaseAppName());
            List items = ((ApiResponse) UacfRetrofitHelper.execute(variantServiceApiConsumer.fetchVariants(variantsRequest))).getItems();
            saveTimeStamp(System.currentTimeMillis());
            putVariantsInInternalStorage(items);
            this.variantsTable.overwrite(items, this.authProvider.getUacfUserId());
        }
    }

    public void forceFetchVariants() throws UacfApiException {
        saveTimeStamp(0);
        fetchVariants();
    }

    private Boolean isDataStale() {
        return Boolean.valueOf(!lastSyncedTimesMap.containsKey(this.authProvider.getUacfUserId()) || System.currentTimeMillis() - returnTimeStamp() > 43200000);
    }

    private List<Variant> lookUpVariants() {
        List<Variant> variantsFromInternalStorage = getVariantsFromInternalStorage();
        if (CollectionUtils.isEmpty((Collection<?>) variantsFromInternalStorage)) {
            variantsFromInternalStorage = this.variantsTable.returnVariants();
            if (CollectionUtils.isEmpty((Collection<?>) variantsFromInternalStorage)) {
                VariantList variantList = (VariantList) new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).withType(VariantList.class).tryMapFrom(defaultVariantValuesAsJsonString);
                variantsFromInternalStorage = (variantList == null || variantList.getItems() == null) ? new ArrayList<>() : variantList.getItems();
            }
        }
        putVariantsInInternalStorage(variantsFromInternalStorage);
        return variantsFromInternalStorage;
    }

    private void putVariantsInInternalStorage(List<Variant> list) {
        synchronized (inMemoryDataStoreMap) {
            inMemoryDataStoreMap.put(this.authProvider.getUacfUserId(), list);
        }
    }

    private List<Variant> getVariantsFromInternalStorage() {
        List<Variant> list;
        synchronized (inMemoryDataStoreMap) {
            list = (List) inMemoryDataStoreMap.get(this.authProvider.getUacfUserId());
        }
        return list;
    }

    private void saveTimeStamp(long j) {
        synchronized (lastSyncedTimesMap) {
            lastSyncedTimesMap.put(this.authProvider.getUacfUserId(), Long.valueOf(j));
        }
    }

    private long returnTimeStamp() {
        long longValue;
        synchronized (lastSyncedTimesMap) {
            longValue = ((Long) lastSyncedTimesMap.get(this.authProvider.getUacfUserId())).longValue();
        }
        return longValue;
    }

    /* access modifiers changed from: protected */
    public Class<?> getConsumerClass() {
        return VariantServiceApiConsumer.class;
    }
}
