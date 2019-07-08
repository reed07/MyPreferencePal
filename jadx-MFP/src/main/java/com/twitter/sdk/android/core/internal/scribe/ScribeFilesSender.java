package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import android.text.TextUtils;
import com.twitter.sdk.android.core.GuestSessionProvider;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.SessionManager;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.internal.CommonUtils;
import com.twitter.sdk.android.core.internal.IdManager;
import com.twitter.sdk.android.core.internal.network.GuestAuthInterceptor;
import com.twitter.sdk.android.core.internal.network.OAuth1aInterceptor;
import com.twitter.sdk.android.core.internal.network.OkHttpClientHelper;
import com.twitter.sdk.android.core.internal.scribe.QueueFile.ElementReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

class ScribeFilesSender implements FilesSender {
    /* access modifiers changed from: private */
    public static final byte[] COMMA = {44};
    private static final byte[] END_JSON_ARRAY = {93};
    private static final byte[] START_JSON_ARRAY = {91};
    private final TwitterAuthConfig authConfig;
    private final Context context;
    private final ExecutorService executorService;
    private final GuestSessionProvider guestSessionProvider;
    private final IdManager idManager;
    private final long ownerId;
    private final ScribeConfig scribeConfig;
    private final AtomicReference<ScribeService> scribeService = new AtomicReference<>();
    private final SessionManager<? extends Session<TwitterAuthToken>> sessionManager;

    static class ConfigRequestInterceptor implements Interceptor {
        private final IdManager idManager;
        private final ScribeConfig scribeConfig;

        ConfigRequestInterceptor(ScribeConfig scribeConfig2, IdManager idManager2) {
            this.scribeConfig = scribeConfig2;
            this.idManager = idManager2;
        }

        public Response intercept(Chain chain) throws IOException {
            Builder newBuilder = chain.request().newBuilder();
            if (!TextUtils.isEmpty(this.scribeConfig.userAgent)) {
                newBuilder.header("User-Agent", this.scribeConfig.userAgent);
            }
            if (!TextUtils.isEmpty(this.idManager.getDeviceUUID())) {
                newBuilder.header("X-Client-UUID", this.idManager.getDeviceUUID());
            }
            newBuilder.header("X-Twitter-Polling", "true");
            return chain.proceed(newBuilder.build());
        }
    }

    interface ScribeService {
        @FormUrlEncoded
        @POST("/{version}/jot/{type}")
        @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
        Call<ResponseBody> upload(@Path("version") String str, @Path("type") String str2, @Field("log[]") String str3);

        @FormUrlEncoded
        @POST("/scribe/{sequence}")
        @Headers({"Content-Type: application/x-www-form-urlencoded;charset=UTF-8"})
        Call<ResponseBody> uploadSequence(@Path("sequence") String str, @Field("log[]") String str2);
    }

    public ScribeFilesSender(Context context2, ScribeConfig scribeConfig2, long j, TwitterAuthConfig twitterAuthConfig, SessionManager<? extends Session<TwitterAuthToken>> sessionManager2, GuestSessionProvider guestSessionProvider2, ExecutorService executorService2, IdManager idManager2) {
        this.context = context2;
        this.scribeConfig = scribeConfig2;
        this.ownerId = j;
        this.authConfig = twitterAuthConfig;
        this.sessionManager = sessionManager2;
        this.guestSessionProvider = guestSessionProvider2;
        this.executorService = executorService2;
        this.idManager = idManager2;
    }

    public boolean send(List<File> list) {
        if (hasApiAdapter()) {
            try {
                String scribeEventsAsJsonArrayString = getScribeEventsAsJsonArrayString(list);
                CommonUtils.logControlled(this.context, scribeEventsAsJsonArrayString);
                retrofit2.Response upload = upload(scribeEventsAsJsonArrayString);
                if (upload.code() == 200) {
                    return true;
                }
                CommonUtils.logControlledError(this.context, "Failed sending files", null);
                if (upload.code() == 500 || upload.code() == 400) {
                    return true;
                }
            } catch (Exception e) {
                CommonUtils.logControlledError(this.context, "Failed sending files", e);
            }
        } else {
            CommonUtils.logControlled(this.context, "Cannot attempt upload at this time");
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public String getScribeEventsAsJsonArrayString(List<File> list) throws IOException {
        QueueFile queueFile;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        final boolean[] zArr = new boolean[1];
        byteArrayOutputStream.write(START_JSON_ARRAY);
        for (File queueFile2 : list) {
            try {
                queueFile = new QueueFile(queueFile2);
                try {
                    queueFile.forEach(new ElementReader() {
                        public void read(InputStream inputStream, int i) throws IOException {
                            byte[] bArr = new byte[i];
                            inputStream.read(bArr);
                            boolean[] zArr = zArr;
                            if (zArr[0]) {
                                byteArrayOutputStream.write(ScribeFilesSender.COMMA);
                            } else {
                                zArr[0] = true;
                            }
                            byteArrayOutputStream.write(bArr);
                        }
                    });
                    CommonUtils.closeQuietly(queueFile);
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.closeQuietly(queueFile);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                queueFile = null;
                CommonUtils.closeQuietly(queueFile);
                throw th;
            }
        }
        byteArrayOutputStream.write(END_JSON_ARRAY);
        return byteArrayOutputStream.toString("UTF-8");
    }

    private boolean hasApiAdapter() {
        return getScribeService() != null;
    }

    /* access modifiers changed from: 0000 */
    public synchronized ScribeService getScribeService() {
        OkHttpClient okHttpClient;
        if (this.scribeService.get() == null) {
            Session session = getSession(this.ownerId);
            if (isValidSession(session)) {
                okHttpClient = new OkHttpClient.Builder().certificatePinner(OkHttpClientHelper.getCertificatePinner()).addInterceptor(new ConfigRequestInterceptor(this.scribeConfig, this.idManager)).addInterceptor(new OAuth1aInterceptor(session, this.authConfig)).build();
            } else {
                okHttpClient = new OkHttpClient.Builder().certificatePinner(OkHttpClientHelper.getCertificatePinner()).addInterceptor(new ConfigRequestInterceptor(this.scribeConfig, this.idManager)).addInterceptor(new GuestAuthInterceptor(this.guestSessionProvider)).build();
            }
            this.scribeService.compareAndSet(null, new Retrofit.Builder().baseUrl(this.scribeConfig.baseUrl).client(okHttpClient).build().create(ScribeService.class));
        }
        return (ScribeService) this.scribeService.get();
    }

    private Session getSession(long j) {
        return this.sessionManager.getSession(j);
    }

    private boolean isValidSession(Session session) {
        return (session == null || session.getAuthToken() == null) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public retrofit2.Response<ResponseBody> upload(String str) throws IOException {
        ScribeService scribeService2 = getScribeService();
        if (!TextUtils.isEmpty(this.scribeConfig.sequence)) {
            return scribeService2.uploadSequence(this.scribeConfig.sequence, str).execute();
        }
        return scribeService2.upload(this.scribeConfig.pathVersion, this.scribeConfig.pathType, str).execute();
    }
}
