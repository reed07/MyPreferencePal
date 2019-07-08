package io.uacf.configuration.internal.service;

import android.content.Context;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.uacf.core.caching.Cache;
import io.uacf.configuration.internal.analytics.Attributes.ConfigAccessed;
import io.uacf.configuration.internal.constants.HttpUris;
import io.uacf.configuration.internal.model.Configuration;
import io.uacf.configuration.internal.model.Configuration.Builder;
import io.uacf.configuration.sdk.exception.UacfRequestedIncorrectTypeException;
import io.uacf.core.api.UacfApiEnvironmentProvider;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.auth.UacfAuthProvider;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.net.retrofit.RetrofitBasedServiceImpl;
import io.uacf.net.retrofit.UacfRetrofitHelper;
import io.uacf.net.retrofit.tracers.UacfOkHttpNetworkTracer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class ConfigurationServiceImpl extends RetrofitBasedServiceImpl implements ConfigurationService {
    private static final long SYNC_CONFIGURATION_TIMEOUT = TimeUnit.HOURS.toMillis(12);
    private static final Map<String, Long> lastSyncedTimesMap = new HashMap();
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private UacfAppId appId;
    private UacfClientEventsCallback clientEventsCallback;
    private Cache<Configuration> configurationCache;
    private Map<String, Configuration> transferMap = new HashMap();

    private interface ConfigurationConsumer {
        @GET
        Call<ResponseBody> fetchConfiguration(@Url String str);
    }

    public ConfigurationServiceImpl(Context context, UacfAppId uacfAppId, UacfUserAgentProvider uacfUserAgentProvider, UacfApiEnvironmentProvider uacfApiEnvironmentProvider, UacfAuthProvider uacfAuthProvider, UacfClientEventsCallback uacfClientEventsCallback, Cache<Configuration> cache, OkHttpClient okHttpClient, UacfOkHttpNetworkTracer uacfOkHttpNetworkTracer) {
        super(context, uacfUserAgentProvider, uacfApiEnvironmentProvider, uacfAuthProvider, okHttpClient, uacfOkHttpNetworkTracer);
        this.appId = uacfAppId;
        this.clientEventsCallback = uacfClientEventsCallback;
        this.configurationCache = cache;
    }

    /* access modifiers changed from: protected */
    public Class<?> getConsumerClass() {
        return ConfigurationConsumer.class;
    }

    public void fetchConfiguration() throws UacfApiException {
        if (isDataStale().booleanValue()) {
            Response executeAndReturnFullResponse = UacfRetrofitHelper.executeAndReturnFullResponse(((ConfigurationConsumer) getConsumerWithUnderscoresAndBearerAuth()).fetchConfiguration(HttpUris.getConfigurationUri(this.appId)));
            setLastSyncedTimeStamp();
            try {
                this.transferMap.clear();
                parseAndStoreConfigurationApiResult((ResponseBody) executeAndReturnFullResponse.body());
                readWriteLock.writeLock().lock();
                this.configurationCache.clear();
                for (Entry entry : this.transferMap.entrySet()) {
                    this.configurationCache.put((String) entry.getKey(), entry.getValue());
                }
                readWriteLock.writeLock().unlock();
            } catch (IOException e) {
                throw new UacfApiException("Failed to decode the response body", (Throwable) e, executeAndReturnFullResponse.code());
            } catch (JsonSyntaxException e2) {
                throw new UacfApiException("Failed parse the response body into json", (Throwable) e2, executeAndReturnFullResponse.code());
            }
        }
    }

    public void forceFetchConfiguration() throws UacfApiException {
        resetLastSyncedTimeStamp();
        fetchConfiguration();
    }

    public Map<String, Configuration> getAllConfigurations() {
        HashMap hashMap = new HashMap();
        readWriteLock.readLock().lock();
        for (Entry entry : this.configurationCache.allItems().entrySet()) {
            if (!((String) entry.getKey()).startsWith("metadata_")) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        readWriteLock.readLock().unlock();
        return hashMap;
    }

    public void clearConfiguration() {
        readWriteLock.writeLock().lock();
        this.configurationCache.clear();
        readWriteLock.writeLock().unlock();
    }

    public Configuration getConfiguration(String str) {
        readWriteLock.readLock().lock();
        Configuration configuration = (Configuration) this.configurationCache.get(str);
        readWriteLock.readLock().unlock();
        return configuration;
    }

    private void setConfiguration(String str, Configuration configuration) {
        readWriteLock.writeLock().lock();
        this.configurationCache.put(str, configuration);
        readWriteLock.writeLock().unlock();
    }

    public boolean deleteConfiguration(String str) {
        boolean z;
        readWriteLock.writeLock().lock();
        if (str == null || !this.configurationCache.contains(str)) {
            z = false;
        } else {
            this.configurationCache.remove(str);
            z = true;
        }
        readWriteLock.writeLock().unlock();
        return z;
    }

    public Integer getIntegerForKey(String str, Integer num) throws UacfRequestedIncorrectTypeException {
        Configuration configuration = getConfiguration(str);
        if (configuration == null) {
            emitValueReadClientEvent(str, num, true);
            return num;
        }
        if (configuration.getIntValue() == null) {
            throwUacfIncorrectRequestTypeException(configuration, Integer.class);
        }
        emitValueReadClientEvent(str, configuration.getIntValue(), false);
        return configuration.getIntValue();
    }

    public void setIntegerForKey(String str, Integer num) {
        if (str != null) {
            setConfiguration(str, new Builder().setKey(str).setIntValue(num).build());
        }
    }

    public void setFloatForKey(String str, Float f) {
        if (str != null) {
            setConfiguration(str, new Builder().setKey(str).setFloatValue(f).build());
        }
    }

    public String getStringForKey(String str, String str2) throws UacfRequestedIncorrectTypeException {
        Configuration configuration = getConfiguration(str);
        if (configuration == null) {
            emitValueReadClientEvent(str, str2, true);
            return str2;
        }
        if (configuration.getStringValue() == null) {
            throwUacfIncorrectRequestTypeException(configuration, String.class);
        }
        String stringValue = configuration.getStringValue();
        emitValueReadClientEvent(str, stringValue, false);
        return stringValue;
    }

    public void setStringForKey(String str, String str2) {
        if (str != null) {
            setConfiguration(str, new Builder().setKey(str).setStringValue(str2).build());
        }
    }

    public Map getMapForKey(String str, Map map) throws UacfRequestedIncorrectTypeException {
        Configuration configuration = getConfiguration(str);
        if (configuration == null) {
            emitValueReadClientEvent(str, map, true);
            return map;
        }
        if (configuration.getMapValue() == null) {
            throwUacfIncorrectRequestTypeException(configuration, Map.class);
        }
        Map mapValue = configuration.getMapValue();
        emitValueReadClientEvent(str, mapValue, false);
        return mapValue;
    }

    public void setMapForKey(String str, Map map) {
        if (str != null) {
            setConfiguration(str, new Builder().setKey(str).setMapValue(map).build());
        }
    }

    private void emitValueReadClientEvent(String str, Object obj, boolean z) {
        this.clientEventsCallback.reportEvent("config_accessed", new ConfigAccessed(str, String.valueOf(obj), String.valueOf(getMetaDataVersion()), String.valueOf(z)));
    }

    public Integer getMetaDataVersion() {
        Configuration configuration = getConfiguration("metadata_version");
        if (configuration == null) {
            return null;
        }
        return configuration.getIntValue();
    }

    public String getMetaDataUpdateBy() {
        Configuration configuration = getConfiguration("metadata_updated_by");
        if (configuration == null) {
            return null;
        }
        return configuration.getStringValue();
    }

    public String getMetaDataUpdateAt() {
        Configuration configuration = getConfiguration("metadata_updated_at");
        if (configuration == null) {
            return null;
        }
        return configuration.getStringValue();
    }

    private void throwUacfIncorrectRequestTypeException(Configuration configuration, Class cls) throws UacfRequestedIncorrectTypeException {
        Class cls2 = configuration.getIntValue() != null ? Integer.class : configuration.getFloatValue() != null ? Float.class : configuration.getStringValue() != null ? String.class : configuration.getMapValue() != null ? Map.class : null;
        Object[] objArr = new Object[3];
        objArr[0] = cls.getSimpleName();
        objArr[1] = configuration.getKey();
        objArr[2] = cls2 != null ? cls2.getSimpleName() : "none";
        throw new UacfRequestedIncorrectTypeException(String.format("Requested value type %s for key %s, value for key %2$s is of type %s.", objArr));
    }

    private void parseAndStoreConfigurationApiResult(ResponseBody responseBody) throws IOException, JsonSyntaxException {
        JsonObject asJsonObject = new JsonParser().parse(responseBody.charStream()).getAsJsonObject();
        parseAndStoreConfigs(asJsonObject.get("configuration").getAsJsonObject().get(this.appId.toString().toLowerCase(Locale.ENGLISH)).getAsJsonObject().get("android").getAsJsonObject());
        parseAndStoreMetaData(asJsonObject.get(TtmlNode.TAG_METADATA).getAsJsonObject());
    }

    private void parseAndStoreMetaData(JsonObject jsonObject) {
        for (Entry entry : jsonObject.entrySet()) {
            Configuration createConfiguration = createConfiguration((String) entry.getKey(), jsonObject);
            Map<String, Configuration> map = this.transferMap;
            StringBuilder sb = new StringBuilder();
            sb.append("metadata_");
            sb.append((String) entry.getKey());
            map.put(sb.toString(), createConfiguration);
        }
    }

    private void parseAndStoreConfigs(JsonObject jsonObject) {
        for (Entry entry : jsonObject.entrySet()) {
            this.transferMap.put(entry.getKey(), createConfiguration((String) entry.getKey(), jsonObject));
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:14|15|16|17) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0099, code lost:
        r5 = r5.substring(1, r5.length() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00a2, code lost:
        r0.setStringValue(r5);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0081 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.uacf.configuration.internal.model.Configuration createConfiguration(java.lang.String r5, com.google.gson.JsonObject r6) {
        /*
            r4 = this;
            io.uacf.configuration.internal.model.Configuration$Builder r0 = new io.uacf.configuration.internal.model.Configuration$Builder
            r0.<init>()
            r0.setKey(r5)
            com.google.gson.JsonElement r5 = r6.get(r5)
            java.lang.String r5 = r5.toString()
            r6 = 0
            char r6 = r5.charAt(r6)
            r1 = 34
            r2 = 1
            if (r6 != r1) goto L_0x002d
            int r6 = r5.length()
            int r6 = r6 - r2
            char r6 = r5.charAt(r6)
            if (r6 != r1) goto L_0x002d
            java.lang.String r6 = "\""
            java.lang.String r1 = ""
            java.lang.String r5 = r5.replace(r6, r1)
        L_0x002d:
            java.lang.String r6 = "{"
            boolean r6 = r5.startsWith(r6)
            if (r6 == 0) goto L_0x0079
            java.lang.String r6 = "}"
            boolean r6 = r5.endsWith(r6)
            if (r6 == 0) goto L_0x0079
            com.google.gson.JsonParser r6 = new com.google.gson.JsonParser
            r6.<init>()
            com.google.gson.JsonElement r5 = r6.parse(r5)
            com.google.gson.JsonObject r5 = r5.getAsJsonObject()
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            java.util.Set r1 = r5.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0057:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0075
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r2.getKey()
            java.lang.String r2 = (java.lang.String) r2
            io.uacf.configuration.internal.model.Configuration r2 = r4.createConfiguration(r2, r5)
            r6.put(r3, r2)
            goto L_0x0057
        L_0x0075:
            r0.setMapValue(r6)
            goto L_0x00a5
        L_0x0079:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)     // Catch:{ NumberFormatException -> 0x0081 }
            r0.setIntValue(r6)     // Catch:{ NumberFormatException -> 0x0081 }
            goto L_0x00a5
        L_0x0081:
            java.lang.Float r6 = java.lang.Float.valueOf(r5)     // Catch:{ NumberFormatException -> 0x0089 }
            r0.setFloatValue(r6)     // Catch:{ NumberFormatException -> 0x0089 }
            goto L_0x00a5
        L_0x0089:
            java.lang.String r6 = "\""
            boolean r6 = r5.startsWith(r6)
            if (r6 == 0) goto L_0x00a2
            java.lang.String r6 = "\""
            boolean r6 = r5.endsWith(r6)
            if (r6 == 0) goto L_0x00a2
            int r6 = r5.length()
            int r6 = r6 - r2
            java.lang.String r5 = r5.substring(r2, r6)
        L_0x00a2:
            r0.setStringValue(r5)
        L_0x00a5:
            io.uacf.configuration.internal.model.Configuration r5 = r0.build()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.uacf.configuration.internal.service.ConfigurationServiceImpl.createConfiguration(java.lang.String, com.google.gson.JsonObject):io.uacf.configuration.internal.model.Configuration");
    }

    private Boolean isDataStale() {
        return Boolean.valueOf(!lastSyncedTimesMap.containsKey(this.authProvider.getUacfUserId()) || System.currentTimeMillis() - getLastSyncedTimeStamp() > SYNC_CONFIGURATION_TIMEOUT);
    }

    private void resetLastSyncedTimeStamp() {
        synchronized (lastSyncedTimesMap) {
            lastSyncedTimesMap.put(this.authProvider.getUacfUserId(), Long.valueOf(0));
        }
    }

    private void setLastSyncedTimeStamp() {
        synchronized (lastSyncedTimesMap) {
            lastSyncedTimesMap.put(this.authProvider.getUacfUserId(), Long.valueOf(System.currentTimeMillis()));
        }
    }

    private long getLastSyncedTimeStamp() {
        long longValue;
        synchronized (lastSyncedTimesMap) {
            longValue = ((Long) lastSyncedTimesMap.get(this.authProvider.getUacfUserId())).longValue();
        }
        return longValue;
    }
}
