package com.uacf.identity.internal.sdk;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.uacf.core.tracing.FSTraceableSdkImpl;
import com.uacf.core.tracing.FSTracing;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.identity.internal.identity.IdentityService;
import com.uacf.identity.internal.model.IdmAccountLink;
import com.uacf.identity.internal.model.IdmProfileEmail;
import com.uacf.identity.internal.model.IdmSocialMediaLink;
import com.uacf.identity.internal.model.IdmUser;
import com.uacf.identity.internal.session.Session;
import com.uacf.identity.sdk.UacfIdentitySdk;
import com.uacf.identity.sdk.model.UacfAccountLink;
import com.uacf.identity.sdk.model.UacfProfileEmail;
import com.uacf.identity.sdk.model.UacfProfileEmails;
import com.uacf.identity.sdk.model.UacfSocialMediaLink;
import com.uacf.identity.sdk.model.UacfSocialMediaLink.Builder;
import com.uacf.identity.sdk.model.UacfUser;
import io.opentracing.Scope;
import io.opentracing.Tracer;
import io.uacf.core.api.UacfApiException;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfSocialNetworkProvider;
import io.uacf.core.app.UacfUserAccountDomain;
import io.uacf.core.consents.UacfUserConsentStatus;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public class UacfIdentitySdkImpl extends FSTraceableSdkImpl<UacfIdentitySdk> implements UacfIdentitySdk {
    private final UacfAppId appId;
    private final IdentityService identityService;
    private final Session session;

    public UacfIdentitySdkImpl(UacfAppId uacfAppId, Session session2, IdentityService identityService2, Tracer tracer, String str, String str2, String str3, String str4) {
        super(tracer, str, str2, str3, uacfAppId, str4);
        this.identityService = identityService2;
        this.appId = uacfAppId;
        this.session = session2;
    }

    public String login(String str, String str2) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            String accessToken = this.identityService.obtainOAuthToken(str, str2).getAccessToken();
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return accessToken;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public String login(UacfSocialNetworkProvider uacfSocialNetworkProvider, String str, String str2, String str3) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            String accessToken = this.identityService.obtainOAuthToken(uacfSocialNetworkProvider, str, str2, str3).getAccessToken();
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return accessToken;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public String loginWithToken(String str) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            String accessToken = this.identityService.storeTokenInfo(str).getAccessToken();
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return accessToken;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        if (r0 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0053, code lost:
        if (r1 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0059, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005e, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.identity.sdk.model.UacfUser getCachedCurrentUser() {
        /*
            r6 = this;
            io.opentracing.Scope r0 = r6.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.session.Session r2 = r6.session     // Catch:{ Throwable -> 0x004f }
            io.uacf.core.app.UacfAppId r3 = r6.appId     // Catch:{ Throwable -> 0x004f }
            com.uacf.identity.internal.model.AppSessionInfo r2 = r2.getSessionInformationFor(r3)     // Catch:{ Throwable -> 0x004f }
            r3 = 0
            if (r2 == 0) goto L_0x003e
            com.uacf.identity.internal.model.AppUserInfo r4 = r2.getCurrentUserInfo()     // Catch:{ Throwable -> 0x004f }
            if (r4 == 0) goto L_0x003e
            com.uacf.identity.internal.model.AppUserInfo r4 = r2.getCurrentUserInfo()     // Catch:{ Throwable -> 0x004f }
            com.uacf.identity.internal.model.IdmUser r4 = r4.getUser()     // Catch:{ Throwable -> 0x004f }
            if (r4 == 0) goto L_0x003e
            com.uacf.identity.internal.model.AppUserInfo r2 = r2.getCurrentUserInfo()     // Catch:{ Throwable -> 0x004f }
            com.uacf.identity.internal.model.IdmUser r2 = r2.getUser()     // Catch:{ Throwable -> 0x004f }
            io.opentracing.Span r4 = r0.span()     // Catch:{ Throwable -> 0x004f }
            java.lang.String r5 = "user_found"
            if (r2 == 0) goto L_0x0031
            r3 = 1
        L_0x0031:
            r4.setTag(r5, r3)     // Catch:{ Throwable -> 0x004f }
            com.uacf.identity.sdk.model.UacfUser r1 = r6.convertIdmUserToUacfUser(r2)     // Catch:{ Throwable -> 0x004f }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            io.opentracing.Span r2 = r0.span()     // Catch:{ Throwable -> 0x004f }
            java.lang.String r4 = "user_found"
            r2.setTag(r4, r3)     // Catch:{ Throwable -> 0x004f }
            if (r0 == 0) goto L_0x004c
            r0.close()
        L_0x004c:
            return r1
        L_0x004d:
            r2 = move-exception
            goto L_0x0051
        L_0x004f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x004d }
        L_0x0051:
            if (r0 == 0) goto L_0x0061
            if (r1 == 0) goto L_0x005e
            r0.close()     // Catch:{ Throwable -> 0x0059 }
            goto L_0x0061
        L_0x0059:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0061
        L_0x005e:
            r0.close()
        L_0x0061:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.getCachedCurrentUser():com.uacf.identity.sdk.model.UacfUser");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r0 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (r1 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0026, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0015, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.identity.sdk.model.UacfUser refreshCurrentUser() throws io.uacf.core.api.UacfApiException {
        /*
            r3 = this;
            io.opentracing.Scope r0 = r3.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.identity.IdentityService r2 = r3.identityService     // Catch:{ Throwable -> 0x0017 }
            com.uacf.identity.internal.model.IdmUser r2 = r2.fetchCurrentUser()     // Catch:{ Throwable -> 0x0017 }
            com.uacf.identity.sdk.model.UacfUser r1 = r3.convertIdmUserToUacfUser(r2)     // Catch:{ Throwable -> 0x0017 }
            if (r0 == 0) goto L_0x0014
            r0.close()
        L_0x0014:
            return r1
        L_0x0015:
            r2 = move-exception
            goto L_0x0019
        L_0x0017:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0015 }
        L_0x0019:
            if (r0 == 0) goto L_0x0029
            if (r1 == 0) goto L_0x0026
            r0.close()     // Catch:{ Throwable -> 0x0021 }
            goto L_0x0029
        L_0x0021:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0029
        L_0x0026:
            r0.close()
        L_0x0029:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.refreshCurrentUser():com.uacf.identity.sdk.model.UacfUser");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (r1 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0028, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r0 != null) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void logout() throws io.uacf.core.api.UacfApiException {
        /*
            r4 = this;
            io.opentracing.Scope r0 = r4.startActiveSpanForMethod()
            r1 = 0
            java.lang.String r2 = "sso_logout"
            r4.reportEvent(r2, r1)     // Catch:{ Throwable -> 0x001e }
            com.uacf.identity.internal.session.Session r2 = r4.session     // Catch:{ Throwable -> 0x001e }
            io.uacf.core.app.UacfAppId r3 = r4.appId     // Catch:{ Throwable -> 0x001e }
            r2.removeSessionInformationFor(r3)     // Catch:{ Throwable -> 0x001e }
            com.uacf.identity.internal.session.Session r2 = r4.session     // Catch:{ Throwable -> 0x001e }
            r2.saveAndNotify()     // Catch:{ Throwable -> 0x001e }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return
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
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.logout():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (r1 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String refreshUserToken() throws io.uacf.core.api.UacfApiException {
        /*
            r3 = this;
            io.opentracing.Scope r0 = r3.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.identity.IdentityService r2 = r3.identityService     // Catch:{ Throwable -> 0x0019 }
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r2.refreshUserToken()     // Catch:{ Throwable -> 0x0019 }
            if (r2 == 0) goto L_0x0011
            java.lang.String r1 = r2.getAccessToken()     // Catch:{ Throwable -> 0x0019 }
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            return r1
        L_0x0017:
            r2 = move-exception
            goto L_0x001b
        L_0x0019:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x001b:
            if (r0 == 0) goto L_0x002b
            if (r1 == 0) goto L_0x0028
            r0.close()     // Catch:{ Throwable -> 0x0023 }
            goto L_0x002b
        L_0x0023:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x002b
        L_0x0028:
            r0.close()
        L_0x002b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.refreshUserToken():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (r1 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCurrentClientToken() throws io.uacf.core.api.UacfApiException {
        /*
            r3 = this;
            io.opentracing.Scope r0 = r3.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.identity.IdentityService r2 = r3.identityService     // Catch:{ Throwable -> 0x0019 }
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r2.getClientToken()     // Catch:{ Throwable -> 0x0019 }
            if (r2 == 0) goto L_0x0011
            java.lang.String r1 = r2.getAccessToken()     // Catch:{ Throwable -> 0x0019 }
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            return r1
        L_0x0017:
            r2 = move-exception
            goto L_0x001b
        L_0x0019:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x001b:
            if (r0 == 0) goto L_0x002b
            if (r1 == 0) goto L_0x0028
            r0.close()     // Catch:{ Throwable -> 0x0023 }
            goto L_0x002b
        L_0x0023:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x002b
        L_0x0028:
            r0.close()
        L_0x002b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.getCurrentClientToken():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (r1 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCachedClientToken() {
        /*
            r3 = this;
            io.opentracing.Scope r0 = r3.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.identity.IdentityService r2 = r3.identityService     // Catch:{ Throwable -> 0x0019 }
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r2.getCachedClientToken()     // Catch:{ Throwable -> 0x0019 }
            if (r2 == 0) goto L_0x0011
            java.lang.String r1 = r2.getAccessToken()     // Catch:{ Throwable -> 0x0019 }
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            return r1
        L_0x0017:
            r2 = move-exception
            goto L_0x001b
        L_0x0019:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x001b:
            if (r0 == 0) goto L_0x002b
            if (r1 == 0) goto L_0x0028
            r0.close()     // Catch:{ Throwable -> 0x0023 }
            goto L_0x002b
        L_0x0023:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x002b
        L_0x0028:
            r0.close()
        L_0x002b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.getCachedClientToken():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (r1 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCurrentUserToken() throws io.uacf.core.api.UacfApiException {
        /*
            r3 = this;
            io.opentracing.Scope r0 = r3.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.identity.IdentityService r2 = r3.identityService     // Catch:{ Throwable -> 0x0019 }
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r2.getUserToken()     // Catch:{ Throwable -> 0x0019 }
            if (r2 == 0) goto L_0x0011
            java.lang.String r1 = r2.getAccessToken()     // Catch:{ Throwable -> 0x0019 }
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            return r1
        L_0x0017:
            r2 = move-exception
            goto L_0x001b
        L_0x0019:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x001b:
            if (r0 == 0) goto L_0x002b
            if (r1 == 0) goto L_0x0028
            r0.close()     // Catch:{ Throwable -> 0x0023 }
            goto L_0x002b
        L_0x0023:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x002b
        L_0x0028:
            r0.close()
        L_0x002b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.getCurrentUserToken():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        if (r0 != null) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        if (r1 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCachedUserToken() {
        /*
            r3 = this;
            io.opentracing.Scope r0 = r3.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.identity.IdentityService r2 = r3.identityService     // Catch:{ Throwable -> 0x0019 }
            com.uacf.identity.internal.model.IdmOAuthTokenInfo r2 = r2.getCachedUserToken()     // Catch:{ Throwable -> 0x0019 }
            if (r2 == 0) goto L_0x0011
            java.lang.String r1 = r2.getAccessToken()     // Catch:{ Throwable -> 0x0019 }
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            return r1
        L_0x0017:
            r2 = move-exception
            goto L_0x001b
        L_0x0019:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0017 }
        L_0x001b:
            if (r0 == 0) goto L_0x002b
            if (r1 == 0) goto L_0x0028
            r0.close()     // Catch:{ Throwable -> 0x0023 }
            goto L_0x002b
        L_0x0023:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x002b
        L_0x0028:
            r0.close()
        L_0x002b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.getCachedUserToken():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        if (r0 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r1 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0026, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCurrentUserId() {
        /*
            r4 = this;
            io.opentracing.Scope r0 = r4.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.session.Session r2 = r4.session     // Catch:{ Throwable -> 0x001b }
            io.uacf.core.app.UacfAppId r3 = r4.appId     // Catch:{ Throwable -> 0x001b }
            com.uacf.identity.internal.model.AppSessionInfo r2 = r2.getSessionInformationFor(r3)     // Catch:{ Throwable -> 0x001b }
            if (r2 == 0) goto L_0x0013
            java.lang.String r1 = r2.getCurrentUserId()     // Catch:{ Throwable -> 0x001b }
        L_0x0013:
            if (r0 == 0) goto L_0x0018
            r0.close()
        L_0x0018:
            return r1
        L_0x0019:
            r2 = move-exception
            goto L_0x001d
        L_0x001b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0019 }
        L_0x001d:
            if (r0 == 0) goto L_0x002d
            if (r1 == 0) goto L_0x002a
            r0.close()     // Catch:{ Throwable -> 0x0025 }
            goto L_0x002d
        L_0x0025:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x002d
        L_0x002a:
            r0.close()
        L_0x002d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.getCurrentUserId():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        if (r0 != null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r1 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0026, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.identity.sdk.model.UacfVerticalAccountInfo getCurrentUserAccount() {
        /*
            r4 = this;
            io.opentracing.Scope r0 = r4.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.session.Session r2 = r4.session     // Catch:{ Throwable -> 0x001b }
            io.uacf.core.app.UacfAppId r3 = r4.appId     // Catch:{ Throwable -> 0x001b }
            com.uacf.identity.internal.model.AppSessionInfo r2 = r2.getSessionInformationFor(r3)     // Catch:{ Throwable -> 0x001b }
            if (r2 == 0) goto L_0x0013
            com.uacf.identity.sdk.model.UacfVerticalAccountInfo r1 = r2.toVerticalAccountInfo()     // Catch:{ Throwable -> 0x001b }
        L_0x0013:
            if (r0 == 0) goto L_0x0018
            r0.close()
        L_0x0018:
            return r1
        L_0x0019:
            r2 = move-exception
            goto L_0x001d
        L_0x001b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0019 }
        L_0x001d:
            if (r0 == 0) goto L_0x002d
            if (r1 == 0) goto L_0x002a
            r0.close()     // Catch:{ Throwable -> 0x0025 }
            goto L_0x002d
        L_0x0025:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x002d
        L_0x002a:
            r0.close()
        L_0x002d:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.getCurrentUserAccount():com.uacf.identity.sdk.model.UacfVerticalAccountInfo");
    }

    public void forgotPassword(String str, String str2) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            this.identityService.initiatePasswordReset(str, str2);
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

    public List<UacfUser> findUserByEmailAddress(String str) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            List findUserByEmail = this.identityService.findUserByEmail(str);
            Collections.sort(findUserByEmail, Collections.reverseOrder(new Comparator<IdmUser>() {
                public int compare(IdmUser idmUser, IdmUser idmUser2) {
                    int i = 0;
                    if (idmUser == idmUser2) {
                        return 0;
                    }
                    if (idmUser == null) {
                        return -1;
                    }
                    if (idmUser2 == null) {
                        return 1;
                    }
                    int domainValue = Utils.getDomainValue(UacfUserAccountDomain.tryGetValueOf(idmUser.getDomain()));
                    int domainValue2 = Utils.getDomainValue(UacfUserAccountDomain.tryGetValueOf(idmUser2.getDomain()));
                    if (domainValue < domainValue2) {
                        i = -1;
                    } else if (domainValue != domainValue2) {
                        i = 1;
                    }
                    return i;
                }
            }));
            List<UacfUser> select = Enumerable.select((Collection<T>) findUserByEmail, (ReturningFunction1<U, T>) new ReturningFunction1<UacfUser, IdmUser>() {
                public UacfUser execute(IdmUser idmUser) {
                    return UacfIdentitySdkImpl.this.convertIdmUserToUacfUser(idmUser);
                }
            });
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return select;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public void reportEvent(String str, Object obj) {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            this.identityService.sendEvent(str, obj);
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

    public UacfUser updateAccount(String str, UacfAccountLink uacfAccountLink) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            IdmAccountLink convertUacfAccountLinkToIdmAccountLink = convertUacfAccountLinkToIdmAccountLink(uacfAccountLink);
            if (!uacfAccountLink.getUacfUserConsentStatusProvider().getUpdatedConsents().isEmpty() && TextUtils.isEmpty(uacfAccountLink.getUacfUserConsentStatusProvider().getConsentMatrixVersion())) {
                FSTracing.logThenThrowException(startActiveSpanForMethod, new UacfApiException("Consent matrix version cannot be null"));
            }
            if (TextUtils.isEmpty(uacfAccountLink.getUacfUserConsentStatusProvider().getIsoCode())) {
                FSTracing.logThenThrowException(startActiveSpanForMethod, new UacfApiException("Iso code cannot be null"));
            }
            UacfUser convertIdmUserToUacfUser = convertIdmUserToUacfUser(this.identityService.updateAccount(Long.valueOf(str), uacfAccountLink.getUacfUserConsentStatusProvider().getIsoCode(), convertUacfAccountLinkToIdmAccountLink));
            if (startActiveSpanForMethod != null) {
                startActiveSpanForMethod.close();
            }
            return convertIdmUserToUacfUser;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r1 != null) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r0 != null) goto L_0x0016;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendVerificationEmail() throws io.uacf.core.api.UacfApiException {
        /*
            r3 = this;
            io.opentracing.Scope r0 = r3.startActiveSpanForMethod()
            r1 = 0
            com.uacf.identity.internal.identity.IdentityService r2 = r3.identityService     // Catch:{ Throwable -> 0x0012 }
            r2.sendVerificationEmail()     // Catch:{ Throwable -> 0x0012 }
            if (r0 == 0) goto L_0x000f
            r0.close()
        L_0x000f:
            return
        L_0x0010:
            r2 = move-exception
            goto L_0x0014
        L_0x0012:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0010 }
        L_0x0014:
            if (r0 == 0) goto L_0x0024
            if (r1 == 0) goto L_0x0021
            r0.close()     // Catch:{ Throwable -> 0x001c }
            goto L_0x0024
        L_0x001c:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0024
        L_0x0021:
            r0.close()
        L_0x0024:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.internal.sdk.UacfIdentitySdkImpl.sendVerificationEmail():void");
    }

    public void sendVerificationEmail(String str) throws UacfApiException {
        sendVerificationEmail();
    }

    public void changePassword(@NonNull String str) throws UacfApiException {
        Throwable th;
        Scope startActiveSpanForMethod = startActiveSpanForMethod();
        try {
            if (TextUtils.isEmpty(str)) {
                FSTracing.logThenThrowException(startActiveSpanForMethod, new IllegalArgumentException("New password cannot be null or empty"));
            }
            this.identityService.changePassword(str);
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

    private IdmAccountLink convertUacfAccountLinkToIdmAccountLink(UacfAccountLink uacfAccountLink) {
        return new IdmAccountLink().setDomain(uacfAccountLink.getDomain()).setDomainUserId(uacfAccountLink.getDomainUserId()).setAdConsentsLastSeen(uacfAccountLink.getUacfUserConsentStatusProvider().getAdConsentsLastSeenDate()).setIdmConsentMap(uacfAccountLink.getUacfUserConsentStatusProvider().getUpdatedConsents()).setConsentMatrixVersion(uacfAccountLink.getUacfUserConsentStatusProvider().getConsentMatrixVersion());
    }

    /* access modifiers changed from: private */
    public UacfUser convertIdmUserToUacfUser(final IdmUser idmUser) {
        UacfProfileEmails uacfProfileEmails;
        List select = Enumerable.select((Collection<T>) idmUser.getAccountLinks(), (ReturningFunction1<U, T>) new ReturningFunction1<UacfAccountLink, IdmAccountLink>() {
            public UacfAccountLink execute(IdmAccountLink idmAccountLink) throws RuntimeException {
                UacfUserConsentStatus uacfUserConsentStatus;
                if (idmAccountLink.getIdmConsentMap() != null) {
                    uacfUserConsentStatus = new UacfUserConsentStatus();
                    uacfUserConsentStatus.setConsentMatrixVersion(idmAccountLink.getConsentMatrixVersion());
                    uacfUserConsentStatus.setAdConsentsLastSeenDate(idmAccountLink.getAdConsentsLastSeen());
                    uacfUserConsentStatus.setIsoCode(idmUser.getRegion());
                    for (Entry entry : idmAccountLink.getIdmConsentMap().entrySet()) {
                        uacfUserConsentStatus.setConsentStatus((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
                    }
                } else {
                    uacfUserConsentStatus = null;
                }
                return new UacfAccountLink().setDomain(idmAccountLink.getDomain()).setDomainUserId(idmAccountLink.getDomainUserId()).setUacfUserConsentStatusProvider(uacfUserConsentStatus);
            }
        });
        List select2 = Enumerable.select((Collection<T>) idmUser.getSocialMediaLinks(), (ReturningFunction1<U, T>) new ReturningFunction1<UacfSocialMediaLink, IdmSocialMediaLink>() {
            public UacfSocialMediaLink execute(IdmSocialMediaLink idmSocialMediaLink) throws RuntimeException {
                return new Builder().withAppId(idmSocialMediaLink.getAppId()).withAuthToken(idmSocialMediaLink.getAuthToken()).withExpiry(idmSocialMediaLink.getExpiry()).withProvider(idmSocialMediaLink.getProvider()).withRefreshToken(idmSocialMediaLink.getRefreshToken()).withUserId(idmSocialMediaLink.getUserId()).withUsername(idmSocialMediaLink.getUsername()).build();
            }
        });
        if (idmUser.getProfileEmails() == null || idmUser.getProfileEmails().getEmails() == null || idmUser.getProfileEmails().getEmails().isEmpty()) {
            uacfProfileEmails = null;
        } else {
            uacfProfileEmails = new UacfProfileEmails.Builder().withIsEmailVerified(idmUser.getProfileEmails().isEmailVerified()).withEmails(Enumerable.select((Collection<T>) idmUser.getProfileEmails().getEmails(), (ReturningFunction1<U, T>) new ReturningFunction1<UacfProfileEmail, IdmProfileEmail>() {
                public UacfProfileEmail execute(IdmProfileEmail idmProfileEmail) throws RuntimeException {
                    return new UacfProfileEmail.Builder().withVerificationState(idmProfileEmail.getVerificationState()).withVerificationConfirmedAt(idmProfileEmail.getVerificationConfirmedAt()).withVerificationRemovedAt(idmProfileEmail.getVerificationRemovedAt()).withVerificationRequestedAt(idmProfileEmail.getVerificationRequestedAt()).withPendingEmail(idmProfileEmail.getPendingEmail()).withIsVerified(idmProfileEmail.isVerified()).withIsPrimary(idmProfileEmail.isPrimary()).withType(idmProfileEmail.getType()).withScopes(idmProfileEmail.getScopes()).withUserId(idmProfileEmail.getUserId()).withEmail(idmProfileEmail.getEmail()).build();
                }
            })).build();
        }
        return new UacfUser.Builder().withUserId(Strings.toString(idmUser.getUserId())).withDomain(UacfUserAccountDomain.tryGetValueOf(idmUser.getDomain())).withRegion(idmUser.getRegion()).withAccountLinks(select).withSocialMediaLinks(select2).withProfileEmails(uacfProfileEmails).withAccountStatus(idmUser.getStatus()).build();
    }
}
