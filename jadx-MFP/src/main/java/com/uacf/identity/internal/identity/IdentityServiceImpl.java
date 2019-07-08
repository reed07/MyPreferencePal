package com.uacf.identity.internal.identity;

import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Patterns;
import com.google.android.gms.common.Scopes;
import com.myfitnesspal.shared.constants.Constants.Config;
import com.myfitnesspal.shared.constants.SharedConstants.Http;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import com.uacf.core.util.CheckedReturningFunction0;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import com.uacf.identity.internal.analytics.IdmAnalyticsAttributes.EmailSearch;
import com.uacf.identity.internal.analytics.IdmAnalyticsAttributes.Error;
import com.uacf.identity.internal.analytics.IdmAnalyticsAttributes.Login;
import com.uacf.identity.internal.analytics.IdmAnalyticsAttributes.LoginType;
import com.uacf.identity.internal.analytics.IdmAnalyticsAttributes.TokenRefresh;
import com.uacf.identity.internal.analytics.IdmAnalyticsAttributes.TokenRefresh.Type;
import com.uacf.identity.internal.constants.Tracing;
import com.uacf.identity.internal.model.AppSessionInfo;
import com.uacf.identity.internal.model.AppUserInfo;
import com.uacf.identity.internal.model.IdmAccountLink;
import com.uacf.identity.internal.model.IdmAccountMergeFinalizeRequest;
import com.uacf.identity.internal.model.IdmAccountMergeFindRequest;
import com.uacf.identity.internal.model.IdmAccountMergeFindResponse;
import com.uacf.identity.internal.model.IdmAccountMergeIdentityContainer;
import com.uacf.identity.internal.model.IdmAccountVerificationConfirmRequest;
import com.uacf.identity.internal.model.IdmAccountVerificationInitRequest;
import com.uacf.identity.internal.model.IdmAccountVerificationInitResponse;
import com.uacf.identity.internal.model.IdmCancelEmailChangeRequest;
import com.uacf.identity.internal.model.IdmChangeEmailRequest;
import com.uacf.identity.internal.model.IdmClientKeyContainer;
import com.uacf.identity.internal.model.IdmConnectSocialRequest;
import com.uacf.identity.internal.model.IdmConnectSocialResponse;
import com.uacf.identity.internal.model.IdmCreateUserRequest;
import com.uacf.identity.internal.model.IdmKeyDesc;
import com.uacf.identity.internal.model.IdmKeyInfo;
import com.uacf.identity.internal.model.IdmOAuthCodeInformation;
import com.uacf.identity.internal.model.IdmOAuthTokenInfo;
import com.uacf.identity.internal.model.IdmPasswordResetRequest;
import com.uacf.identity.internal.model.IdmProfile;
import com.uacf.identity.internal.model.IdmProfileEmails;
import com.uacf.identity.internal.model.IdmServerKeyContainer;
import com.uacf.identity.internal.model.IdmUpdateUserConsentRequest;
import com.uacf.identity.internal.model.IdmUpdateUserPasswordRequest;
import com.uacf.identity.internal.model.IdmUser;
import com.uacf.identity.internal.model.IdmUserInfo;
import com.uacf.identity.internal.model.IdmUserInfoContainer;
import com.uacf.identity.internal.model.IdmVerifyEmailRequest;
import com.uacf.identity.internal.session.Session;
import com.uacf.identity.internal.util.JWTUtil;
import io.opentracing.Scope;
import io.opentracing.Tracer;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.api.UacfUserAgentProvider;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfSocialNetworkProvider;
import io.uacf.core.app.UacfUserAccountDomain;
import io.uacf.core.interfaces.UacfClientEventsCallback;
import io.uacf.net.retrofit.FSTraceableRetrofitHelper;
import io.uacf.net.retrofit.UacfApiRetrofitBuilder;
import io.uacf.net.retrofit.UacfNetworkingServiceImpl;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class IdentityServiceImpl extends UacfNetworkingServiceImpl implements IdentityService {
    private static final AtomicBoolean isRefreshingClientToken = new AtomicBoolean(false);
    private static final AtomicBoolean isRefreshingUserToken = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public final UacfAppId appId;
    private final UacfClientEventsCallback clientEventsCallback;
    private final String redirectUri;
    /* access modifiers changed from: private */
    public final FSTraceableRetrofitHelper retrofitHelper;
    private final Session session;

    private interface IdentityApiConsumer {
        @POST("connect_social")
        Call<IdmConnectSocialResponse> addSocialLink(@Body IdmConnectSocialRequest idmConnectSocialRequest);

        @POST("profiles/{userId}/emails/cancel-change")
        Call<IdmProfileEmails> cancelEmailChange(@Path("userId") String str, @Body IdmCancelEmailChangeRequest idmCancelEmailChangeRequest);

        @POST("profiles/{userId}/emails/change")
        Call<IdmProfileEmails> changeEmail(@Path("userId") String str, @Body IdmChangeEmailRequest idmChangeEmailRequest);

        @POST("sms/confirm-verification")
        Call<Void> confirmAccountVerification(@Body IdmAccountVerificationConfirmRequest idmAccountVerificationConfirmRequest);

        @POST("profiles")
        Call<IdmProfile> createProfile(@Body IdmProfile idmProfile);

        @POST("users/social")
        Call<IdmUser> createSocialUser(@Query("full") boolean z, @Body IdmCreateUserRequest idmCreateUserRequest);

        @POST("users")
        Call<IdmUser> createUser(@Query("full") boolean z, @Body IdmCreateUserRequest idmCreateUserRequest);

        @GET("clientKeys")
        Call<IdmClientKeyContainer> fetchClientKeys();

        @GET("jwks.json")
        Call<IdmServerKeyContainer> fetchServerKeys();

        @POST("accountmerge/find")
        Call<IdmAccountMergeFindResponse> findAccountsToMerge(@Body IdmAccountMergeFindRequest idmAccountMergeFindRequest);

        @GET("users/search")
        Call<List<IdmUser>> findUserByEmail(@Query("emailAddress") String str);

        @GET("oauth/authorize")
        Call<IdmOAuthCodeInformation> getAuthorizationCode(@Query("client_id") String str, @Query("credentials") String str2, @Query("nonce") int i, @Query("redirect_uri") String str3, @Query("response_type") String str4, @Query("scope") String str5);

        @FormUrlEncoded
        @POST("oauth/token")
        Call<IdmOAuthTokenInfo> getClientToken(@Field("client_id") String str, @Field("client_secret") String str2, @Field("grant_type") String str3);

        @FormUrlEncoded
        @POST("oauth/token")
        Call<IdmOAuthTokenInfo> getOAuthTokenFromAuthorizationCode(@Field("grant_type") String str, @Field("code") String str2, @Field("redirect_uri") String str3);

        @GET("users/{user_id}")
        Call<IdmUser> getUser(@Path("user_id") String str, @Query("fetch_emails") boolean z, @Query("fetch_profile") boolean z2);

        @GET("userinfo")
        Call<IdmUserInfoContainer> getUserInfo();

        @POST("sms/init-verification")
        Call<IdmAccountVerificationInitResponse> initiateAccountVerification(@Body IdmAccountVerificationInitRequest idmAccountVerificationInitRequest);

        @POST("accountmerge/merge")
        Call<IdmAccountMergeIdentityContainer> mergeAccounts(@Body IdmAccountMergeFinalizeRequest idmAccountMergeFinalizeRequest);

        @POST("password_reset")
        Call<Void> passwordReset(@Query("operation") String str, @Query("app_name") String str2, @Body IdmPasswordResetRequest idmPasswordResetRequest);

        @FormUrlEncoded
        @POST("oauth/token")
        Call<IdmOAuthTokenInfo> refreshAuthToken(@Field("grant_type") String str, @Field("refresh_token") String str2);

        @POST("disconnect_social")
        Call<IdmConnectSocialResponse> removeSocialLink(@Body IdmConnectSocialRequest idmConnectSocialRequest);

        @POST("profiles/{userId}/emails/verify")
        Call<Void> sendVerificationEmail(@Path("userId") String str, @Body IdmVerifyEmailRequest idmVerifyEmailRequest);

        @PATCH("users/{user_id}")
        Call<IdmUser> updateUser(@Path("user_id") String str, @Query("full") Boolean bool, @Body Object obj);

        @FormUrlEncoded
        @POST("validate_credentials")
        Call<IdmUserInfo> validateAuthCode(@Field("grant_type") String str, @Field("code") String str2, @Field("redirect_uri") String str3);
    }

    private interface UacfCall<T> extends CheckedReturningFunction0<T, UacfApiException> {
    }

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return "IdentityServiceImpl";
    }

    public IdentityServiceImpl(Context context, Session session2, UacfAppId uacfAppId, UacfUserAgentProvider uacfUserAgentProvider, String str, String str2, String str3, String str4, UacfClientEventsCallback uacfClientEventsCallback, OkHttpClient okHttpClient, Tracer tracer) {
        super(context, str, uacfUserAgentProvider, str2, str3, str4, null, okHttpClient, tracer);
        this.appId = uacfAppId;
        this.session = session2;
        this.clientEventsCallback = uacfClientEventsCallback;
        this.redirectUri = str4;
        this.retrofitHelper = FSTraceableRetrofitHelper.initialize(tracer);
    }

    /* access modifiers changed from: protected */
    public Class<?> getConsumerClass() {
        return IdentityApiConsumer.class;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r0 != null) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001c, code lost:
        r1.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAuthorizationCode(java.lang.String r3, java.lang.String r4) throws io.uacf.core.api.UacfApiException {
        /*
            r2 = this;
            io.opentracing.Scope r0 = r2.startActiveSpanForMethod()
            r1 = 0
            java.lang.String r3 = r2.getAuthorizationCodeInternal(r3, r4, r1)     // Catch:{ Throwable -> 0x0011 }
            if (r0 == 0) goto L_0x000e
            r0.close()
        L_0x000e:
            return r3
        L_0x000f:
            r3 = move-exception
            goto L_0x0013
        L_0x0011:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000f }
        L_0x0013:
            if (r0 == 0) goto L_0x0023
            if (r1 == 0) goto L_0x0020
            r0.close()     // Catch:{ Throwable -> 0x001b }
            goto L_0x0023
        L_0x001b:
            r4 = move-exception
            r1.addSuppressed(r4)
            goto L_0x0023
        L_0x0020:
            r0.close()
        L_0x0023:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.identity.IdentityServiceImpl.getAuthorizationCode(java.lang.String, java.lang.String):java.lang.String");
    }

    private String getAuthorizationCodeInternal(String str, String str2, UacfUserAccountDomain uacfUserAccountDomain) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            String codeUsingJwt = getCodeUsingJwt(generateCredentials(str, str2, uacfUserAccountDomain), LoginType.PASSWORD, null, null, null);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return codeUsingJwt;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r0 != null) goto L_0x0015;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001c, code lost:
        r1.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getAuthorizationCode(io.uacf.core.app.UacfSocialNetworkProvider r3, java.lang.String r4, java.lang.String r5) throws io.uacf.core.api.UacfApiException {
        /*
            r2 = this;
            io.opentracing.Scope r0 = r2.startActiveSpanForMethod()
            r1 = 0
            java.lang.String r3 = r2.getAuthorizationCodeInternal(r3, r4, r5, r1)     // Catch:{ Throwable -> 0x0011 }
            if (r0 == 0) goto L_0x000e
            r0.close()
        L_0x000e:
            return r3
        L_0x000f:
            r3 = move-exception
            goto L_0x0013
        L_0x0011:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000f }
        L_0x0013:
            if (r0 == 0) goto L_0x0023
            if (r1 == 0) goto L_0x0020
            r0.close()     // Catch:{ Throwable -> 0x001b }
            goto L_0x0023
        L_0x001b:
            r4 = move-exception
            r1.addSuppressed(r4)
            goto L_0x0023
        L_0x0020:
            r0.close()
        L_0x0023:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.identity.IdentityServiceImpl.getAuthorizationCode(io.uacf.core.app.UacfSocialNetworkProvider, java.lang.String, java.lang.String):java.lang.String");
    }

    private String getAuthorizationCodeInternal(UacfSocialNetworkProvider uacfSocialNetworkProvider, String str, String str2, UacfUserAccountDomain uacfUserAccountDomain) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            String codeUsingJwt = getCodeUsingJwt(generateCredentials(uacfSocialNetworkProvider, str, str2, uacfUserAccountDomain), LoginType.SOCIAL, uacfSocialNetworkProvider, str, null);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return codeUsingJwt;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public String getAuthorizationCodeForOtherApp(String str) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            String codeUsingJwt = getCodeUsingJwt(generateCredentialsForOtherApp(str), LoginType.SSO, null, null, null);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return codeUsingJwt;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0034, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
        if (r0 != null) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        if (r1 != null) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0041, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0045, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.identity.internal.model.IdmKeyInfo obtainClientKeys() throws io.uacf.core.api.UacfApiException {
        /*
            r4 = this;
            io.opentracing.Scope r0 = r4.startActiveSpanForMethod()
            r1 = 0
            java.lang.Object r2 = r4.getConsumerWithIdentityFieldsAndBasicAuthUsingCurrentClientConfig()     // Catch:{ Throwable -> 0x0036 }
            com.uacf.identity.internal.identity.IdentityServiceImpl$IdentityApiConsumer r2 = (com.uacf.identity.internal.identity.IdentityServiceImpl.IdentityApiConsumer) r2     // Catch:{ Throwable -> 0x0036 }
            retrofit2.Call r2 = r2.fetchClientKeys()     // Catch:{ Throwable -> 0x0036 }
            io.uacf.net.retrofit.FSTraceableRetrofitHelper r3 = r4.retrofitHelper     // Catch:{ Throwable -> 0x0036 }
            java.lang.Object r2 = r3.execute(r2)     // Catch:{ Throwable -> 0x0036 }
            com.uacf.identity.internal.model.IdmClientKeyContainer r2 = (com.uacf.identity.internal.model.IdmClientKeyContainer) r2     // Catch:{ Throwable -> 0x0036 }
            if (r2 != 0) goto L_0x001f
            if (r0 == 0) goto L_0x001e
            r0.close()
        L_0x001e:
            return r1
        L_0x001f:
            java.util.List r2 = r2.getKeys()     // Catch:{ Throwable -> 0x0036 }
            com.uacf.identity.internal.identity.IdentityServiceImpl$1 r3 = new com.uacf.identity.internal.identity.IdentityServiceImpl$1     // Catch:{ Throwable -> 0x0036 }
            r3.<init>()     // Catch:{ Throwable -> 0x0036 }
            java.lang.Object r2 = com.uacf.core.util.Enumerable.firstOrDefault(r2, r3)     // Catch:{ Throwable -> 0x0036 }
            com.uacf.identity.internal.model.IdmKeyInfo r2 = (com.uacf.identity.internal.model.IdmKeyInfo) r2     // Catch:{ Throwable -> 0x0036 }
            if (r0 == 0) goto L_0x0033
            r0.close()
        L_0x0033:
            return r2
        L_0x0034:
            r2 = move-exception
            goto L_0x0038
        L_0x0036:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0034 }
        L_0x0038:
            if (r0 == 0) goto L_0x0048
            if (r1 == 0) goto L_0x0045
            r0.close()     // Catch:{ Throwable -> 0x0040 }
            goto L_0x0048
        L_0x0040:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0048
        L_0x0045:
            r0.close()
        L_0x0048:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.identity.IdentityServiceImpl.obtainClientKeys():com.uacf.identity.internal.model.IdmKeyInfo");
    }

    public IdmOAuthTokenInfo getClientToken() throws UacfApiException {
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmOAuthTokenInfo freshestClientTokenPossible = getFreshestClientTokenPossible(false);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return freshestClientTokenPossible;
        } catch (Throwable th) {
            th = th;
        }
        throw th;
        if (startActiveSpanForMethod != null) {
            if (r2 != null) {
                try {
                    startActiveSpanForMethod.close();
                } catch (Throwable th2) {
                    r2.addSuppressed(th2);
                }
            } else {
                startActiveSpanForMethod.close();
            }
        }
        throw th;
    }

    public IdmOAuthTokenInfo getCachedClientToken() {
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmOAuthTokenInfo currentClientTokenInfo = getCurrentClientTokenInfo();
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return currentClientTokenInfo;
        } catch (Throwable th) {
            th = th;
        }
        throw th;
        if (startActiveSpanForMethod != null) {
            if (r2 != null) {
                try {
                    startActiveSpanForMethod.close();
                } catch (Throwable th2) {
                    r2.addSuppressed(th2);
                }
            } else {
                startActiveSpanForMethod.close();
            }
        }
        throw th;
    }

    public IdmOAuthTokenInfo getCachedUserToken() {
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmOAuthTokenInfo currentUserTokenInfo = getCurrentUserTokenInfo();
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return currentUserTokenInfo;
        } catch (Throwable th) {
            th = th;
        }
        throw th;
        if (startActiveSpanForMethod != null) {
            if (r2 != null) {
                try {
                    startActiveSpanForMethod.close();
                } catch (Throwable th2) {
                    r2.addSuppressed(th2);
                }
            } else {
                startActiveSpanForMethod.close();
            }
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0020, code lost:
        if (r0 != null) goto L_0x0022;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        if (r1 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0028, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0029, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x002d, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.identity.internal.model.IdmOAuthTokenInfo getUserToken() throws io.uacf.core.api.UacfApiException {
        /*
            r3 = this;
            io.opentracing.Scope r0 = r3.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r3.getCurrentUserTokenInfo()     // Catch:{ Throwable -> 0x001e }
            if (r2 != 0) goto L_0x0011
            if (r0 == 0) goto L_0x0010
            r0.close()
        L_0x0010:
            return r1
        L_0x0011:
            r2 = 0
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r1 = r3.getFreshestUserTokenPossibleOrThrow(r2)     // Catch:{ Throwable -> 0x001e }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r1
        L_0x001c:
            r2 = move-exception
            goto L_0x0020
        L_0x001e:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001c }
        L_0x0020:
            if (r0 == 0) goto L_0x0030
            if (r1 == 0) goto L_0x002d
            r0.close()     // Catch:{ Throwable -> 0x0028 }
            goto L_0x0030
        L_0x0028:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0030
        L_0x002d:
            r0.close()
        L_0x0030:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.identity.IdentityServiceImpl.getUserToken():com.uacf.identity.internal.model.IdmOAuthTokenInfo");
    }

    public IdmOAuthTokenInfo refreshUserToken() throws UacfApiException {
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmOAuthTokenInfo freshestUserTokenPossibleOrThrow = getFreshestUserTokenPossibleOrThrow(true);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return freshestUserTokenPossibleOrThrow;
        } catch (Throwable th) {
            th = th;
        }
        throw th;
        if (startActiveSpanForMethod != null) {
            if (r2 != null) {
                try {
                    startActiveSpanForMethod.close();
                } catch (Throwable th2) {
                    r2.addSuppressed(th2);
                }
            } else {
                startActiveSpanForMethod.close();
            }
        }
        throw th;
    }

    public IdmOAuthTokenInfo obtainOAuthToken(String str, String str2) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmOAuthTokenInfo obtainOAuthTokenFromAuthCode = obtainOAuthTokenFromAuthCode(getAuthorizationCode(str, str2));
            updateUserRelatedInformation();
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return obtainOAuthTokenFromAuthCode;
        } catch (UacfApiException e) {
            Ln.e(e);
            throw e;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public IdmOAuthTokenInfo storeTokenInfo(String str) throws UacfApiException {
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        String str2 = "sso_user_login_succeeded";
        try {
            IdmOAuthTokenInfo obtainOAuthTokenFromOtherApp = obtainOAuthTokenFromOtherApp(str);
            Login login = new Login(null, LoginType.LEGACY, null, null, null);
            sendEvent(str2, login);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return obtainOAuthTokenFromOtherApp;
        } catch (UacfApiException e) {
            Ln.e(e);
            String str3 = "sso_user_login_failed";
            String message = e.getMessage();
            this.session.removeSessionInformationFor(this.appId);
            this.session.saveAndNotify();
            throw e;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                throw th2;
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
        }
        throw th;
    }

    public IdmOAuthTokenInfo obtainOAuthToken(UacfSocialNetworkProvider uacfSocialNetworkProvider, String str, String str2, String str3) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            validateSocialParameters(uacfSocialNetworkProvider, str, str2, str3);
            IdmOAuthTokenInfo obtainOAuthTokenFromAuthCode = obtainOAuthTokenFromAuthCode(getAuthorizationCode(uacfSocialNetworkProvider, str, str3));
            updateUserRelatedInformation();
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return obtainOAuthTokenFromAuthCode;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public IdmOAuthTokenInfo obtainOAuthTokenFromOtherApp(String str) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmOAuthTokenInfo internalExchangeToken = internalExchangeToken(str);
            updateUserRelatedInformation();
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return internalExchangeToken;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public List<IdmUser> findUserByEmail(final String str) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            List<IdmUser> list = (List) handle401Or403ForClientTokenCall(new UacfCall<List<IdmUser>>() {
                public List<IdmUser> execute() throws UacfApiException {
                    IdentityServiceImpl.this.validateEmailAddress(str);
                    IdentityServiceImpl.this.getCurrentClientTokenInfoOrThrow();
                    return (List) IdentityServiceImpl.this.retrofitHelper.execute(((IdentityApiConsumer) IdentityServiceImpl.this.getConsumerWithIdentityFieldsAndBearerAuth(IdentityServiceImpl.this.getCurrentClientTokenInfo())).findUserByEmail(str));
                }
            });
            sendEvent("sso_email_search_succeeded", new EmailSearch(CollectionUtils.size((Collection<?>) list)));
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return list;
        } catch (UacfApiException e) {
            sendErrorEvent("sso_email_search_failed", (Throwable) e);
            Tracing.logException(startActiveSpanForMethod, e);
            throw e;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void initiatePasswordReset(final String str, final String str2) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            handle401Or403ForClientTokenCall(new UacfCall<Void>() {
                public Void execute() throws UacfApiException {
                    IdentityServiceImpl.this.validateEmailAddress(str);
                    IdentityServiceImpl.this.getCurrentClientTokenInfoOrThrow();
                    return (Void) IdentityServiceImpl.this.retrofitHelper.execute(((IdentityApiConsumer) IdentityServiceImpl.this.getConsumerWithUnderscoresAndBearerAuth(IdentityServiceImpl.this.getCurrentClientTokenInfo())).passwordReset("initiate", Strings.toString(UacfAppId.convertFromDeprecatedValue(IdentityServiceImpl.this.appId)), new IdmPasswordResetRequest(str, str2)));
                }
            });
            sendEvent("sso_password_reset_initiate_succeeded", null);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
                return;
            }
            return;
        } catch (UacfApiException e) {
            sendErrorEvent("sso_password_reset_initiate_failed", (Throwable) e);
            Tracing.logException(startActiveSpanForMethod, e);
            throw e;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void sendEvent(String str, Object obj) {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            this.clientEventsCallback.reportEvent(str, obj);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
                return;
            }
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void sendErrorEvent(String str, Throwable th) {
        String str2 = "";
        if (th instanceof UacfApiException) {
            str2 = ((UacfApiException) th).getBody();
        }
        if (Strings.isEmpty(str2)) {
            str2 = th.getMessage();
        }
        sendErrorEvent(str, str2);
    }

    public void sendErrorEvent(String str, String str2) {
        sendEvent(str, new Error(str2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        if (r0 != null) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r1 != null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0035, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clearClientToken() {
        /*
            r5 = this;
            io.opentracing.Scope r0 = r5.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.session.Session r2 = r5.session     // Catch:{ Throwable -> 0x0026 }
            io.uacf.core.app.UacfAppId r3 = r5.appId     // Catch:{ Throwable -> 0x0026 }
            com.uacf.identity.internal.model.AppSessionInfo r2 = r2.getSessionInformationFor(r3)     // Catch:{ Throwable -> 0x0026 }
            if (r2 == 0) goto L_0x001e
            r2.setClientTokenInfo(r1)     // Catch:{ Throwable -> 0x0026 }
            com.uacf.identity.internal.session.Session r3 = r5.session     // Catch:{ Throwable -> 0x0026 }
            io.uacf.core.app.UacfAppId r4 = r5.appId     // Catch:{ Throwable -> 0x0026 }
            r3.setSessionInformationFor(r4, r2)     // Catch:{ Throwable -> 0x0026 }
            com.uacf.identity.internal.session.Session r2 = r5.session     // Catch:{ Throwable -> 0x0026 }
            r2.saveAndNotify()     // Catch:{ Throwable -> 0x0026 }
        L_0x001e:
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            return
        L_0x0024:
            r2 = move-exception
            goto L_0x0028
        L_0x0026:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0024 }
        L_0x0028:
            if (r0 == 0) goto L_0x0038
            if (r1 == 0) goto L_0x0035
            r0.close()     // Catch:{ Throwable -> 0x0030 }
            goto L_0x0038
        L_0x0030:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0038
        L_0x0035:
            r0.close()
        L_0x0038:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.identity.IdentityServiceImpl.clearClientToken():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d6, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00da, code lost:
        if (r0 != null) goto L_0x00dc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00dc, code lost:
        if (r1 != null) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00e3, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00e7, code lost:
        r0.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002d A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0032 A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0060 A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0065 A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006d A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0070 A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075 A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0078 A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x007d A[Catch:{ UacfApiException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.identity.internal.model.IdmUserInfoContainer updateUserRelatedInformation() throws io.uacf.core.api.UacfApiException {
        /*
            r10 = this;
            io.opentracing.Scope r0 = r10.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r10.getCurrentUserTokenInfoOrThrow()     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.model.IdmUserInfoContainer r3 = r10.fetchUserInfo(r2)     // Catch:{ UacfApiException -> 0x0015 }
            java.lang.String r4 = "sso_fetch_userinfo_succeeded"
            r10.sendEvent(r4, r1)     // Catch:{ UacfApiException -> 0x0013 }
            goto L_0x002b
        L_0x0013:
            r4 = move-exception
            goto L_0x0017
        L_0x0015:
            r4 = move-exception
            r3 = r1
        L_0x0017:
            java.lang.String r5 = "sso_fetch_userinfo_failed"
            java.lang.String r6 = r4.getMessage()     // Catch:{ Throwable -> 0x00d8 }
            r10.sendErrorEvent(r5, r6)     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.constants.Tracing.logException(r0, r4)     // Catch:{ Throwable -> 0x00d8 }
            int r5 = r4.getStatusCode()     // Catch:{ Throwable -> 0x00d8 }
            r6 = 404(0x194, float:5.66E-43)
            if (r5 != r6) goto L_0x00d5
        L_0x002b:
            if (r3 == 0) goto L_0x0032
            com.uacf.identity.internal.model.IdmUserInfo r4 = r3.getProfile()     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x0033
        L_0x0032:
            r4 = r1
        L_0x0033:
            if (r3 == 0) goto L_0x005a
            java.lang.Long r5 = r3.getMergedUserId()     // Catch:{ Throwable -> 0x00d8 }
            if (r5 == 0) goto L_0x005a
            java.lang.String r2 = r2.getAccessToken()     // Catch:{ UacfApiException -> 0x004d }
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r10.internalExchangeToken(r2)     // Catch:{ UacfApiException -> 0x004d }
            com.uacf.identity.internal.model.IdmUserInfoContainer r3 = r10.fetchUserInfo(r2)     // Catch:{ UacfApiException -> 0x004d }
            java.lang.String r2 = "sso_merged_user_id_exchange_succeeded"
            r10.sendEvent(r2, r1)     // Catch:{ UacfApiException -> 0x004d }
            goto L_0x005a
        L_0x004d:
            r2 = move-exception
            java.lang.String r5 = "sso_merged_user_id_exchange_failed"
            java.lang.String r6 = r2.getMessage()     // Catch:{ Throwable -> 0x00d8 }
            r10.sendErrorEvent(r5, r6)     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.constants.Tracing.logException(r0, r2)     // Catch:{ Throwable -> 0x00d8 }
        L_0x005a:
            com.uacf.identity.internal.model.AppSessionInfo r2 = r10.getAppSessionInfoOrThrow()     // Catch:{ Throwable -> 0x00d8 }
            if (r3 == 0) goto L_0x0065
            io.uacf.core.app.UacfUserAccountDomain r5 = r3.getDomain()     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x0066
        L_0x0065:
            r5 = r1
        L_0x0066:
            if (r3 == 0) goto L_0x006d
            java.util.List r6 = r3.getEmails()     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x006e
        L_0x006d:
            r6 = r1
        L_0x006e:
            if (r3 == 0) goto L_0x0075
            java.util.List r7 = r3.getAccountLinks()     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x0076
        L_0x0075:
            r7 = r1
        L_0x0076:
            if (r3 == 0) goto L_0x007d
            java.util.List r8 = r3.getSocialMediaLinks()     // Catch:{ Throwable -> 0x00d8 }
            goto L_0x007e
        L_0x007d:
            r8 = r1
        L_0x007e:
            com.uacf.identity.internal.model.AppUserInfo r9 = r10.getCurrentUserInfoOrCreate()     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.model.AppUserInfo r5 = r9.setDomain(r5)     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.model.AppUserInfo r5 = r5.setUserInfo(r4)     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.model.AppUserInfo r5 = r5.setEmails(r6)     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.model.AppUserInfo r5 = r5.setAccountLinks(r7)     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.model.AppUserInfo r5 = r5.setSocialMediaLinks(r8)     // Catch:{ Throwable -> 0x00d8 }
            if (r4 == 0) goto L_0x00b5
            com.uacf.identity.internal.model.AppUserInfo r6 = r10.getCurrentUserInfo()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r6 = r6.getUserId()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.Long r7 = r4.getUserId()     // Catch:{ Throwable -> 0x00d8 }
            boolean r6 = com.uacf.core.util.Strings.equalsIgnoreCase(r6, r7)     // Catch:{ Throwable -> 0x00d8 }
            if (r6 != 0) goto L_0x00b5
            java.lang.Long r4 = r4.getUserId()     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r4 = com.uacf.core.util.Strings.toString(r4)     // Catch:{ Throwable -> 0x00d8 }
            r5.setUserId(r4)     // Catch:{ Throwable -> 0x00d8 }
        L_0x00b5:
            java.lang.String r4 = r5.getUserId()     // Catch:{ Throwable -> 0x00d8 }
            r2.setUserInfo(r4, r5)     // Catch:{ Throwable -> 0x00d8 }
            java.lang.String r4 = r5.getUserId()     // Catch:{ Throwable -> 0x00d8 }
            r2.setCurrentUserId(r4)     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.session.Session r4 = r10.session     // Catch:{ Throwable -> 0x00d8 }
            io.uacf.core.app.UacfAppId r5 = r10.appId     // Catch:{ Throwable -> 0x00d8 }
            r4.setSessionInformationFor(r5, r2)     // Catch:{ Throwable -> 0x00d8 }
            com.uacf.identity.internal.session.Session r2 = r10.session     // Catch:{ Throwable -> 0x00d8 }
            r2.saveAndNotify()     // Catch:{ Throwable -> 0x00d8 }
            if (r0 == 0) goto L_0x00d4
            r0.close()
        L_0x00d4:
            return r3
        L_0x00d5:
            throw r4     // Catch:{ Throwable -> 0x00d8 }
        L_0x00d6:
            r2 = move-exception
            goto L_0x00da
        L_0x00d8:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00d6 }
        L_0x00da:
            if (r0 == 0) goto L_0x00ea
            if (r1 == 0) goto L_0x00e7
            r0.close()     // Catch:{ Throwable -> 0x00e2 }
            goto L_0x00ea
        L_0x00e2:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x00ea
        L_0x00e7:
            r0.close()
        L_0x00ea:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.identity.IdentityServiceImpl.updateUserRelatedInformation():com.uacf.identity.internal.model.IdmUserInfoContainer");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r0 != null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
        if (r1 != null) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003a, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x002d, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.identity.internal.model.IdmUser fetchCurrentUser() throws io.uacf.core.api.UacfApiException {
        /*
            r5 = this;
            io.opentracing.Scope r0 = r5.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r5.getCurrentUserTokenInfoOrThrow()     // Catch:{ Throwable -> 0x002f }
            java.lang.Object r2 = r5.getConsumerWithIdentityFieldsAndBearerAuth(r2)     // Catch:{ Throwable -> 0x002f }
            com.uacf.identity.internal.identity.IdentityServiceImpl$IdentityApiConsumer r2 = (com.uacf.identity.internal.identity.IdentityServiceImpl.IdentityApiConsumer) r2     // Catch:{ Throwable -> 0x002f }
            com.uacf.identity.internal.model.AppUserInfo r3 = r5.getCurrentUserInfo()     // Catch:{ Throwable -> 0x002f }
            java.lang.String r3 = r3.getUserId()     // Catch:{ Throwable -> 0x002f }
            r4 = 1
            retrofit2.Call r2 = r2.getUser(r3, r4, r4)     // Catch:{ Throwable -> 0x002f }
            io.uacf.net.retrofit.FSTraceableRetrofitHelper r3 = r5.retrofitHelper     // Catch:{ Throwable -> 0x002f }
            java.lang.Object r2 = r3.execute(r2)     // Catch:{ Throwable -> 0x002f }
            com.uacf.identity.internal.model.IdmUser r2 = (com.uacf.identity.internal.model.IdmUser) r2     // Catch:{ Throwable -> 0x002f }
            r5.cacheUser(r2)     // Catch:{ Throwable -> 0x002f }
            if (r0 == 0) goto L_0x002c
            r0.close()
        L_0x002c:
            return r2
        L_0x002d:
            r2 = move-exception
            goto L_0x0031
        L_0x002f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002d }
        L_0x0031:
            if (r0 == 0) goto L_0x0041
            if (r1 == 0) goto L_0x003e
            r0.close()     // Catch:{ Throwable -> 0x0039 }
            goto L_0x0041
        L_0x0039:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0041
        L_0x003e:
            r0.close()
        L_0x0041:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.identity.IdentityServiceImpl.fetchCurrentUser():com.uacf.identity.internal.model.IdmUser");
    }

    private IdmOAuthTokenInfo internalExchangeToken(String str) throws UacfApiException {
        return decodeIdTokenAndStoreAllTokenInformation(internalExchangeTokenWithoutStoring(str));
    }

    private IdmOAuthTokenInfo internalExchangeTokenWithoutStoring(String str) throws UacfApiException {
        if (!Strings.isEmpty(str)) {
            return obtainOauthTokenFromAuthCodeWithoutStoring(getAuthorizationCodeForOtherApp(str));
        }
        throw new IllegalArgumentException("accessToken must not be null or empty");
    }

    private IdmUserInfoContainer fetchUserInfo(IdmOAuthTokenInfo idmOAuthTokenInfo) throws UacfApiException {
        return (IdmUserInfoContainer) this.retrofitHelper.execute(((IdentityApiConsumer) getConsumerWithIdentityFieldsAndBearerAuth(idmOAuthTokenInfo)).getUserInfo());
    }

    private String generateCredentials(String str, String str2, UacfUserAccountDomain uacfUserAccountDomain) throws UacfApiException {
        if (Strings.isEmpty(str)) {
            throw new UacfApiException("invalid_username", "username may not be null or empty");
        } else if (!Strings.isEmpty(str2)) {
            Builder claim = new Builder().claim("username", str).claim("password", str2);
            if (uacfUserAccountDomain != null) {
                claim.claim("domain", Strings.toString(uacfUserAccountDomain));
            }
            return generateCredentials(claim.build());
        } else {
            throw new UacfApiException("invalid_password", "password may not be null or empty");
        }
    }

    private String generateCredentials(UacfSocialNetworkProvider uacfSocialNetworkProvider, String str, String str2, UacfUserAccountDomain uacfUserAccountDomain) throws UacfApiException {
        if (Strings.isEmpty((Object) uacfSocialNetworkProvider)) {
            throw new UacfApiException("invalid_provider", "socialNetworkProvider may not be null or empty");
        } else if (Strings.isEmpty(str)) {
            throw new UacfApiException("invalid_app_id", "socialNetworkAppId may not be null or empty");
        } else if (!Strings.isEmpty(str2)) {
            return generateCredentials(new Builder().claim("uacf_social_appid", str).claim("uacf_social_provider", uacfSocialNetworkProvider.toString()).claim("uacf_social_oauthtoken", str2).build());
        } else {
            throw new UacfApiException("invalid_social_network_access_token", "socialNetworkAccessToken may not be null or empty");
        }
    }

    private String generateCredentialsForOtherApp(String str) throws UacfApiException {
        if (!Strings.isEmpty(str)) {
            return generateCredentials(new Builder().claim("source_token", str).build());
        }
        throw new UacfApiException("invalid_access_token_for_other_app", "otherAppAccessToken may not be null or empty");
    }

    private String generateCredentials(JWTClaimsSet jWTClaimsSet) throws UacfApiException {
        requireClientKeys();
        return JWTUtil.encode(jWTClaimsSet, getAppSessionInfoOrThrow().getClientKeyInfo().getKey());
    }

    private IdmOAuthTokenInfo getFreshestClientTokenPossible(boolean z) throws UacfApiException {
        IdmOAuthTokenInfo currentClientTokenInfo;
        String str;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            currentClientTokenInfo = getCurrentClientTokenInfo();
            final long timeUntilExpirationInSeconds = currentClientTokenInfo != null ? currentClientTokenInfo.getTimeUntilExpirationInSeconds() : 0;
            str = "invalid_client_token_before_refresh";
            if (z) {
                currentClientTokenInfo = obtainAndWriteClientToken(Type.Explicit, timeUntilExpirationInSeconds);
                str = "invalid_client_token_after_explicit_refresh";
            } else if (currentClientTokenInfo == null) {
                currentClientTokenInfo = obtainAndWriteClientToken(Type.Initial, timeUntilExpirationInSeconds);
                str = "invalid_client_token_after_initial_refresh";
            } else if (!currentThreadIsMainThread() && currentClientTokenInfo.hasAccessToken() && currentClientTokenInfo.accessTokenWillExpireWithin(60000)) {
                str = "invalid_client_token_after_implicit_refresh";
                startActiveSpanForMethod.span().log("token_expiration_imminent");
                currentClientTokenInfo = obtainAndWriteClientToken(Type.Implicit, timeUntilExpirationInSeconds);
            } else if (currentClientTokenInfo.hasAccessToken() && currentClientTokenInfo.accessTokenWillExpireWithin(Config.CACHE_TTL_MILLISECONDS)) {
                Ln.d("TOKEN: check refresh of client token", new Object[0]);
                startActiveSpanForMethod.span().log("token_expiration_soon");
                if (!isRefreshingClientToken.get()) {
                    Ln.d("TOKEN: start background refresh of client token", new Object[0]);
                    async(new Runnable() {
                        public void run() {
                            try {
                                IdentityServiceImpl.this.obtainAndWriteClientToken(Type.Async, timeUntilExpirationInSeconds);
                            } catch (UacfApiException e) {
                                Ln.e(e);
                            }
                        }
                    });
                }
            }
        } catch (UacfApiException e) {
            Ln.e(e);
            if (currentClientTokenInfo.isExpired()) {
                throw e;
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                throw th2;
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
        }
        if (currentClientTokenInfo == null || !currentClientTokenInfo.hasAccessToken()) {
            UacfApiException uacfApiException = new UacfApiException(str, "Client token is not valid");
            sendErrorEvent("sso_client_token_refresh_failed", (Throwable) uacfApiException);
            Tracing.logException(startActiveSpanForMethod, uacfApiException);
            throw uacfApiException;
        }
        if (startActiveSpanForMethod != null) {
            startActiveSpanForMethod.close();
        }
        return currentClientTokenInfo;
        throw th;
    }

    /* access modifiers changed from: private */
    public IdmOAuthTokenInfo obtainAndWriteClientToken(Type type, long j) throws UacfApiException {
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            if (!isRefreshingClientToken.compareAndSet(false, true)) {
                Ln.d("TOKEN: client token refresh already in progress", new Object[0]);
                startActiveSpanForMethod.span().log("already_being_refreshed");
                synchronized (isRefreshingClientToken) {
                    try {
                        isRefreshingClientToken.wait();
                    } catch (InterruptedException e) {
                        Ln.e(e, "Interrupted waiting on client token refresh", new Object[0]);
                        Tracing.logException(startActiveSpanForMethod, e);
                    }
                }
                IdmOAuthTokenInfo currentClientTokenInfo = getCurrentClientTokenInfo();
                if (startActiveSpanForMethod != null) {
                    startActiveSpanForMethod.close();
                }
                return currentClientTokenInfo;
            }
            sendTokenRefreshEvent("sso_client_token_refresh_initiated", type, j);
            IdmOAuthTokenInfo idmOAuthTokenInfo = (IdmOAuthTokenInfo) this.retrofitHelper.execute(((IdentityApiConsumer) getBuilderWithUnderscores().build()).getClientToken(getClientId(), getClientSecret(), "client_credentials"));
            AppSessionInfo appSessionInfoOrCreate = getAppSessionInfoOrCreate();
            appSessionInfoOrCreate.setClientTokenInfo(idmOAuthTokenInfo);
            this.session.setSessionInformationFor(this.appId, appSessionInfoOrCreate);
            this.session.saveAndNotify();
            sendTokenRefreshEvent("sso_client_token_refresh_succeeded", type, j);
            isRefreshingClientToken.set(false);
            synchronized (isRefreshingClientToken) {
                isRefreshingClientToken.notifyAll();
            }
            Ln.d("TOKEN: finish refresh of client token", new Object[0]);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return idmOAuthTokenInfo;
        } catch (UacfApiException e2) {
            sendErrorEvent("sso_client_token_refresh_failed", (Throwable) e2);
            Tracing.logException(startActiveSpanForMethod, e2);
            throw e2;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                throw th2;
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
        }
        throw th;
    }

    private void fetchClientKeysIfNecessary() throws UacfApiException {
        AppSessionInfo appSessionInfoOrCreate = getAppSessionInfoOrCreate();
        if (appSessionInfoOrCreate.getClientKeyInfo() == null) {
            appSessionInfoOrCreate.setClientKeyInfo(obtainClientKeys());
            this.session.setSessionInformationFor(this.appId, appSessionInfoOrCreate);
            this.session.saveAndNotify();
        }
    }

    private void fetchServerKeysIfNecessary() throws UacfApiException {
        AppSessionInfo appSessionInfoOrCreate = getAppSessionInfoOrCreate();
        if (appSessionInfoOrCreate.getServerKeyInfo() == null) {
            appSessionInfoOrCreate.setServerKeyInfo(obtainServerKey());
            this.session.setSessionInformationFor(this.appId, appSessionInfoOrCreate);
            this.session.saveAndNotify();
        }
    }

    private void requireClientKeys() throws UacfApiException {
        fetchClientKeysIfNecessary();
        IdmKeyInfo clientKeyInfo = getAppSessionInfoOrThrow().getClientKeyInfo();
        if (clientKeyInfo == null || clientKeyInfo.getKey() == null) {
            throw new UacfApiException("invalid_client_keys", "Client keys are not valid");
        }
    }

    private void requireServerKeys() throws UacfApiException {
        fetchServerKeysIfNecessary();
        if (getAppSessionInfoOrThrow().getServerKeyInfo() == null) {
            throw new UacfApiException("invalid_server_keys", "Server keys are not valid");
        }
    }

    /* access modifiers changed from: private */
    public IdmOAuthTokenInfo getCurrentClientTokenInfoOrThrow() throws UacfApiException {
        return getFreshestClientTokenPossible(false);
    }

    private AppSessionInfo getAppSessionInfo() {
        return this.session.getSessionInformationFor(this.appId);
    }

    private AppSessionInfo getAppSessionInfoOrCreate() {
        AppSessionInfo appSessionInfo = getAppSessionInfo();
        if (appSessionInfo != null) {
            return appSessionInfo;
        }
        AppSessionInfo appSessionInfo2 = new AppSessionInfo(this.appId);
        this.session.setSessionInformationFor(this.appId, appSessionInfo2);
        return appSessionInfo2;
    }

    private AppSessionInfo getAppSessionInfoOrThrow() throws UacfApiException {
        AppSessionInfo appSessionInfo = getAppSessionInfo();
        if (appSessionInfo != null) {
            return appSessionInfo;
        }
        throw new UacfApiException("no_app_info_found", 0, String.format("Session information for appId %s not found", new Object[]{this.appId}));
    }

    private AppUserInfo getCurrentUserInfoOrThrow() throws UacfApiException {
        AppUserInfo currentUserInfo = getAppSessionInfoOrThrow().getCurrentUserInfo();
        if (currentUserInfo != null) {
            return currentUserInfo;
        }
        throw new UacfApiException("no_current_user_found", 0, String.format("Current user for appId %s not found", new Object[]{this.appId}));
    }

    private AppUserInfo getCurrentUserInfo() {
        AppSessionInfo appSessionInfo = getAppSessionInfo();
        if (appSessionInfo != null) {
            return appSessionInfo.getCurrentUserInfo();
        }
        return null;
    }

    private AppUserInfo getCurrentUserInfoOrCreate() {
        AppUserInfo currentUserInfo = getCurrentUserInfo();
        return currentUserInfo == null ? new AppUserInfo() : currentUserInfo;
    }

    /* access modifiers changed from: private */
    public IdmOAuthTokenInfo getCurrentClientTokenInfo() {
        AppSessionInfo appSessionInfo = getAppSessionInfo();
        if (appSessionInfo != null) {
            return appSessionInfo.getClientTokenInfo();
        }
        return null;
    }

    private IdmOAuthTokenInfo getCurrentUserTokenInfoOrThrow() throws UacfApiException {
        IdmOAuthTokenInfo userToken = getUserToken();
        if (userToken != null) {
            return userToken;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to find token information for the current user on appId ");
        sb.append(this.appId);
        throw new UacfApiException("no_current_user_found", sb.toString());
    }

    private IdmOAuthTokenInfo getCurrentUserTokenInfo() {
        AppSessionInfo appSessionInfo = getAppSessionInfo();
        if (appSessionInfo != null) {
            AppUserInfo currentUserInfo = appSessionInfo.getCurrentUserInfo();
            if (currentUserInfo != null) {
                return currentUserInfo.getTokenInfo();
            }
        }
        return null;
    }

    private IdmOAuthTokenInfo getFreshestUserTokenPossibleOrThrow(boolean z) throws UacfApiException {
        IdmOAuthTokenInfo currentUserTokenInfo;
        String str;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            currentUserTokenInfo = getCurrentUserTokenInfo();
            final int expiresIn = currentUserTokenInfo != null ? currentUserTokenInfo.getExpiresIn() : 0;
            str = "invalid_user_token_before_refresh";
            if (currentUserTokenInfo != null && currentUserTokenInfo.hasAccessToken()) {
                if (z) {
                    currentUserTokenInfo = obtainAndWriteRefreshedUserToken(Type.Explicit, expiresIn);
                    str = "invalid_user_token_after_explicit_refresh";
                } else if (!currentThreadIsMainThread() && currentUserTokenInfo.accessTokenWillExpireWithin(60000)) {
                    String str2 = "invalid_user_token_after_implicit_refresh";
                    startActiveSpanForMethod.span().log("token_expiration_imminent");
                    currentUserTokenInfo = obtainAndWriteRefreshedUserToken(Type.Implicit, expiresIn);
                    str = str2;
                } else if (currentUserTokenInfo.accessTokenWillExpireWithin(Config.CACHE_TTL_MILLISECONDS)) {
                    Ln.d("TOKEN: check refresh of user token", new Object[0]);
                    startActiveSpanForMethod.span().log("token_expiration_soon");
                    if (!isRefreshingUserToken.get()) {
                        Ln.d("TOKEN: start background refresh of user token", new Object[0]);
                        async(new Runnable() {
                            public void run() {
                                try {
                                    IdentityServiceImpl.this.obtainAndWriteRefreshedUserToken(Type.Async, expiresIn);
                                } catch (UacfApiException e) {
                                    Ln.e(e);
                                }
                            }
                        });
                    }
                }
                if (currentUserTokenInfo != null && currentUserTokenInfo.hasAccessToken()) {
                    if (startActiveSpanForMethod != null) {
                        startActiveSpanForMethod.close();
                    }
                    return currentUserTokenInfo;
                }
            }
        } catch (UacfApiException e) {
            Ln.e(e);
            if (currentUserTokenInfo.isExpired()) {
                throw e;
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                throw th2;
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
        }
        UacfApiException uacfApiException = new UacfApiException(str, "User token is not valid");
        sendErrorEvent("sso_user_token_refresh_failed", (Throwable) uacfApiException);
        Tracing.logException(startActiveSpanForMethod, uacfApiException);
        throw uacfApiException;
        throw th;
    }

    private IdmKeyDesc obtainServerKey() throws UacfApiException {
        IdmServerKeyContainer idmServerKeyContainer = (IdmServerKeyContainer) this.retrofitHelper.execute(((IdentityApiConsumer) getConsumerWithIdentityFieldsAndBasicAuthUsingCurrentClientConfig()).fetchServerKeys());
        if (idmServerKeyContainer == null) {
            return null;
        }
        return (IdmKeyDesc) Enumerable.firstOrDefault(idmServerKeyContainer.getKeys(), new ReturningFunction1<Boolean, IdmKeyDesc>() {
            public Boolean execute(IdmKeyDesc idmKeyDesc) {
                return Boolean.valueOf(idmKeyDesc != null && Strings.equalsIgnoreCase(idmKeyDesc.getUse(), "sig"));
            }
        });
    }

    private IdmOAuthTokenInfo obtainOAuthTokenFromAuthCode(String str) throws UacfApiException {
        return decodeIdTokenAndStoreAllTokenInformation(obtainOauthTokenFromAuthCodeWithoutStoring(str));
    }

    private IdmOAuthTokenInfo obtainOauthTokenFromAuthCodeWithoutStoring(String str) throws UacfApiException {
        if (!Strings.isEmpty(str)) {
            return (IdmOAuthTokenInfo) this.retrofitHelper.execute(((IdentityApiConsumer) getConsumerWithUnderscoresAndBearerAuth(getCurrentClientTokenInfoOrThrow())).getOAuthTokenFromAuthorizationCode("authorization_code", str, this.redirectUri));
        }
        throw new UacfApiException("unable_fetch_authorization_code", "Unable to obtain authorization code");
    }

    private IdmOAuthTokenInfo decodeIdTokenAndStoreAllTokenInformation(IdmOAuthTokenInfo idmOAuthTokenInfo) throws UacfApiException {
        if (idmOAuthTokenInfo == null) {
            return null;
        }
        requireServerKeys();
        AppSessionInfo appSessionInfoOrThrow = getAppSessionInfoOrThrow();
        AppUserInfo currentUserInfoOrCreate = getCurrentUserInfoOrCreate();
        currentUserInfoOrCreate.setTokenInfo(idmOAuthTokenInfo, appSessionInfoOrThrow.getServerKeyInfo());
        appSessionInfoOrThrow.setUserInfo(currentUserInfoOrCreate.getUserId(), currentUserInfoOrCreate);
        appSessionInfoOrThrow.setCurrentUserId(currentUserInfoOrCreate.getUserId());
        this.session.setSessionInformationFor(this.appId, appSessionInfoOrThrow);
        this.session.saveAndNotify();
        return currentUserInfoOrCreate.getTokenInfo();
    }

    private String getCodeUsingJwt(String str, LoginType loginType, UacfSocialNetworkProvider uacfSocialNetworkProvider, String str2, UacfAppId uacfAppId) throws UacfApiException {
        Throwable th;
        Throwable th2;
        String str3;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        String str4 = "sso_user_login_succeeded";
        String str5 = null;
        try {
            getCurrentClientTokenInfoOrThrow();
            Tuple2 executeForRedirectOnly = this.retrofitHelper.executeForRedirectOnly(((IdentityApiConsumer) ((UacfApiRetrofitBuilder) getBuilderWithUnderscores().followRedirects(false)).build()).getAuthorizationCode(getClientId(), str, new SecureRandom().nextInt(Integer.MAX_VALUE), this.redirectUri, "code", Scopes.OPEN_ID));
            String str6 = (String) executeForRedirectOnly.getItem1();
            if (Strings.notEmpty(str6)) {
                Uri parse = Uri.parse(str6);
                String queryParameter = parse.getQueryParameter("code");
                if (Strings.notEmpty(queryParameter)) {
                    Login login = new Login(null, loginType, uacfSocialNetworkProvider, str2, uacfAppId);
                    sendEvent(str4, login);
                    if (startActiveSpanForMethod != null) {
                        startActiveSpanForMethod.close();
                    }
                    return queryParameter;
                }
                str5 = parse.getQueryParameter("error");
                if (Strings.isEmpty(str5)) {
                    str5 = "unknown";
                }
                str3 = parse.getQueryParameter("error_description");
                if (Strings.isEmpty(str3)) {
                    str3 = "An unknown error occurred";
                }
            } else {
                str3 = null;
            }
            throw new UacfApiException(str5, String.format("REQUEST ID: %s; %s", new Object[]{executeForRedirectOnly.getItem2(), Strings.toString(str3)}));
        } catch (UacfApiException e) {
            clearClientToken();
            String str7 = "sso_user_login_failed";
            Tracing.logException(startActiveSpanForMethod, e);
            throw e;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            try {
                throw th4;
            } catch (Throwable th5) {
                th.addSuppressed(th5);
            }
        }
        throw th2;
    }

    /* access modifiers changed from: private */
    public IdmOAuthTokenInfo obtainAndWriteRefreshedUserToken(Type type, int i) throws UacfApiException {
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            AppUserInfo currentUserInfoOrThrow = getCurrentUserInfoOrThrow();
            IdmOAuthTokenInfo tokenInfo = currentUserInfoOrThrow.getTokenInfo();
            if (!isRefreshingUserToken.compareAndSet(false, true)) {
                Ln.d("TOKEN: user token refresh already in progress", new Object[0]);
                startActiveSpanForMethod.span().log("already_being_refreshed");
                synchronized (isRefreshingUserToken) {
                    try {
                        isRefreshingUserToken.wait();
                    } catch (InterruptedException e) {
                        Ln.e(e, "Interrupted waiting on user token refresh", new Object[0]);
                    }
                }
                IdmOAuthTokenInfo tokenInfo2 = getCurrentUserInfoOrThrow().getTokenInfo();
                if (startActiveSpanForMethod != null) {
                    startActiveSpanForMethod.close();
                }
                return tokenInfo2;
            }
            String refreshToken = tokenInfo.getRefreshToken();
            if (Strings.isEmpty(refreshToken)) {
                Tracing.logThenThrowException(startActiveSpanForMethod, new UacfApiException("invalid_refresh_token", "Refresh token is missing"));
            }
            long j = (long) i;
            sendTokenRefreshEvent("sso_user_token_refresh_initiated", type, j);
            Call refreshAuthToken = ((IdentityApiConsumer) getConsumerWithUnderscoresAndBearerAuth(getCurrentClientTokenInfoOrThrow())).refreshAuthToken(Http.REFRESH_TOKEN, refreshToken);
            IdmOAuthTokenInfo idToken = ((IdmOAuthTokenInfo) this.retrofitHelper.execute(refreshAuthToken)).setIdToken(tokenInfo.getIdToken());
            AppSessionInfo appSessionInfoOrThrow = getAppSessionInfoOrThrow();
            currentUserInfoOrThrow.setTokenInfo(idToken, appSessionInfoOrThrow.getServerKeyInfo());
            appSessionInfoOrThrow.setCurrentUserInfo(currentUserInfoOrThrow);
            this.session.setSessionInformationFor(this.appId, appSessionInfoOrThrow);
            this.session.saveAndNotify();
            sendTokenRefreshEvent("sso_user_token_refresh_succeeded", type, j);
            isRefreshingUserToken.set(false);
            synchronized (isRefreshingUserToken) {
                isRefreshingUserToken.notifyAll();
            }
            Ln.d("TOKEN: finish refresh of user token", new Object[0]);
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return idToken;
        } catch (UacfApiException e2) {
            sendErrorEvent("sso_user_token_refresh_failed", (Throwable) e2);
            Tracing.logException(startActiveSpanForMethod, e2);
            throw e2;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                throw th2;
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
        }
        throw th;
    }

    private void sendTokenRefreshEvent(String str, Type type, long j) {
        sendEvent(str, new TokenRefresh(type, j));
    }

    private boolean currentThreadIsMainThread() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }

    /* access modifiers changed from: private */
    public void validateEmailAddress(String str) throws UacfApiException {
        if (!Patterns.EMAIL_ADDRESS.matcher(Strings.toString(str)).matches()) {
            throw new UacfApiException("invalid_email_address", 0);
        }
    }

    private void validateSocialParameters(UacfSocialNetworkProvider uacfSocialNetworkProvider, String str, String str2, String str3) {
        boolean z = uacfSocialNetworkProvider != null;
        boolean notEmpty = Strings.notEmpty(str);
        boolean notEmpty2 = Strings.notEmpty(str2);
        boolean notEmpty3 = Strings.notEmpty(str3);
        if (z && (!notEmpty || !notEmpty2 || !notEmpty3)) {
            throw new IllegalArgumentException("Must specify ALL social network information if you specify the provider");
        } else if (z) {
        } else {
            if (notEmpty || notEmpty2 || notEmpty3) {
                throw new IllegalArgumentException("Must specify NO social network information if you do not specify the provider");
            }
        }
    }

    private <T> T handle401Or403ForClientTokenCall(UacfCall<T> uacfCall) throws UacfApiException {
        try {
            return uacfCall.execute();
        } catch (UacfApiException e) {
            int statusCode = e.getStatusCode();
            if (statusCode == 401 || statusCode == 403) {
                clearClientToken();
                return uacfCall.execute();
            }
            throw e;
        }
    }

    public IdmUser updateAccount(Long l, String str, IdmAccountLink idmAccountLink) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmOAuthTokenInfo currentClientTokenInfo = getCurrentClientTokenInfo();
            if (currentClientTokenInfo != null) {
                IdentityApiConsumer identityApiConsumer = (IdentityApiConsumer) getConsumerWithIdentityFieldsAndBearerAuth(currentClientTokenInfo);
                ArrayList arrayList = new ArrayList();
                arrayList.add(idmAccountLink);
                IdmUser idmUser = (IdmUser) this.retrofitHelper.execute(identityApiConsumer.updateUser(String.valueOf(l), Boolean.valueOf(true), new IdmUpdateUserConsentRequest(str, arrayList)));
                cacheUser(idmUser);
                if (startActiveSpanForMethod != null) {
                    startActiveSpanForMethod.close();
                }
                return idmUser;
            }
            throw new UacfApiException("unable_fetch_client_token", "Unable to fetch a client token");
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        if (r1 != null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003e, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0031, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
        if (r0 != null) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendVerificationEmail() throws io.uacf.core.api.UacfApiException {
        /*
            r5 = this;
            io.opentracing.Scope r0 = r5.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r5.getCurrentUserTokenInfoOrThrow()     // Catch:{ Throwable -> 0x0033 }
            java.lang.Object r2 = r5.getConsumerWithIdentityFieldsAndBearerAuth(r2)     // Catch:{ Throwable -> 0x0033 }
            com.uacf.identity.internal.identity.IdentityServiceImpl$IdentityApiConsumer r2 = (com.uacf.identity.internal.identity.IdentityServiceImpl.IdentityApiConsumer) r2     // Catch:{ Throwable -> 0x0033 }
            com.uacf.identity.internal.model.IdmVerifyEmailRequest r3 = new com.uacf.identity.internal.model.IdmVerifyEmailRequest     // Catch:{ Throwable -> 0x0033 }
            io.uacf.core.app.UacfAppId r4 = r5.appId     // Catch:{ Throwable -> 0x0033 }
            io.uacf.core.app.UacfAppId r4 = io.uacf.core.app.UacfAppId.convertFromDeprecatedValue(r4)     // Catch:{ Throwable -> 0x0033 }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x0033 }
            com.uacf.identity.internal.model.AppUserInfo r4 = r5.getCurrentUserInfo()     // Catch:{ Throwable -> 0x0033 }
            java.lang.String r4 = r4.getUserId()     // Catch:{ Throwable -> 0x0033 }
            retrofit2.Call r2 = r2.sendVerificationEmail(r4, r3)     // Catch:{ Throwable -> 0x0033 }
            io.uacf.net.retrofit.FSTraceableRetrofitHelper r3 = r5.retrofitHelper     // Catch:{ Throwable -> 0x0033 }
            r3.execute(r2)     // Catch:{ Throwable -> 0x0033 }
            if (r0 == 0) goto L_0x0030
            r0.close()
        L_0x0030:
            return
        L_0x0031:
            r2 = move-exception
            goto L_0x0035
        L_0x0033:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0031 }
        L_0x0035:
            if (r0 == 0) goto L_0x0045
            if (r1 == 0) goto L_0x0042
            r0.close()     // Catch:{ Throwable -> 0x003d }
            goto L_0x0045
        L_0x003d:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0045
        L_0x0042:
            r0.close()
        L_0x0045:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.identity.IdentityServiceImpl.sendVerificationEmail():void");
    }

    @NonNull
    public IdmUser changePassword(@NonNull String str) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmOAuthTokenInfo currentUserTokenInfo = getCurrentUserTokenInfo();
            if (currentUserTokenInfo != null) {
                IdmUser idmUser = (IdmUser) this.retrofitHelper.execute(((IdentityApiConsumer) getConsumerWithIdentityFieldsAndBearerAuth(currentUserTokenInfo)).updateUser(getCurrentUserInfo().getUserId(), null, new IdmUpdateUserPasswordRequest(str)));
                cacheUser(idmUser);
                if (startActiveSpanForMethod != null) {
                    startActiveSpanForMethod.close();
                }
                return idmUser;
            }
            throw new UacfApiException("unable_fetch_user_token", "Unable to fetch a user token");
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private void cacheUser(IdmUser idmUser) {
        AppSessionInfo sessionInformationFor = this.session.getSessionInformationFor(this.appId);
        AppUserInfo currentUserInfo = sessionInformationFor.getCurrentUserInfo();
        if (currentUserInfo != null) {
            currentUserInfo.setUser(idmUser);
            this.session.setSessionInformationFor(this.appId, sessionInformationFor);
        }
    }

    /* access modifiers changed from: protected */
    public <TConsumer> TConsumer getConsumerWithIdentityFieldsAndBearerAuth(IdmOAuthTokenInfo idmOAuthTokenInfo) {
        return ((UacfApiRetrofitBuilder) getBuilderWithIdentityFields().withBearerAuth(idmOAuthTokenInfo.getAccessToken())).build();
    }

    /* access modifiers changed from: protected */
    public <TConsumer> TConsumer getConsumerWithUnderscoresAndBearerAuth(IdmOAuthTokenInfo idmOAuthTokenInfo) {
        return ((UacfApiRetrofitBuilder) getBuilderWithUnderscores().withBearerAuth(idmOAuthTokenInfo.getAccessToken())).build();
    }
}
