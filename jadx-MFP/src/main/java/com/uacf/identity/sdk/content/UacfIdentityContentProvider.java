package com.uacf.identity.sdk.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.google.gson.FieldNamingPolicy;
import com.myfitnesspal.shared.constants.HttpConstants;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Tuple2;
import com.uacf.identity.internal.build.RuntimeConfiguration;
import com.uacf.identity.internal.build.RuntimeConfigurationImpl;
import com.uacf.identity.internal.model.AppSessionInfo;
import com.uacf.identity.internal.model.AppUserInfo;
import com.uacf.identity.internal.model.IdmConfiguration;
import com.uacf.identity.internal.model.IdmOAuthTokenInfo;
import com.uacf.identity.internal.model.IdmOAuthTokenInfo.Builder;
import com.uacf.identity.internal.session.Session;
import com.uacf.identity.internal.util.AppIdMigrationUtil;
import com.uacf.identity.sdk.UacfIdentitySdkFactory;
import io.uacf.core.app.UacfAppId;
import io.uacf.core.app.UacfAppId.AppType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public abstract class UacfIdentityContentProvider extends ContentProvider {
    private static final String AUTHORITY_DEBUG_APP_SUFFIX = "_debug";
    private static final String AUTHORITY_PREFIX = "com.uacf.identity.provider.";
    private static final String AUTHORITY_SAMPLE_APP_SUFFIX = "_sample";
    private static final int CODE_USERS = 1;
    public static final String COLUMN_DATA = "data";
    private static final String[] COLUMN_NAMES = {"data"};
    public static final String EXPIRES_IN = "expiresIn";
    public static final String START_TIME = "startTime";
    public static final String USERS = "/users";
    private IdmConfiguration configuration;
    private RuntimeConfiguration runtimeConfiguration = new RuntimeConfigurationImpl();
    private UriMatcher uriMatcher;

    private void validateInstaller() {
    }

    /* access modifiers changed from: protected */
    public abstract UacfAppId getAppId();

    public boolean onCreate() {
        this.uriMatcher = new UriMatcher(-1);
        for (String addURI : getAuthorities()) {
            this.uriMatcher.addURI(addURI, USERS, 1);
        }
        this.configuration = new IdmConfiguration(getContext());
        return true;
    }

    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            if (this.runtimeConfiguration.allowSso()) {
                validateCallEnvironment();
                if (this.uriMatcher.match(uri) == 1) {
                    Ln.d("CONTENT: appId = %s, query %s", getAppId(), uri);
                    return getUsersForCurrentApp();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("uri ");
                sb.append(uri.toString());
                sb.append(" is not a valid Uri");
                throw new IllegalArgumentException(sb.toString());
            }
            throw new SecurityException();
        } catch (SecurityException e) {
            Ln.d("CONTENT: query, appId = %s, got SecurityException, return empty cursor; message = '%s'", getAppId(), e.getMessage());
            return new MatrixCursor(COLUMN_NAMES);
        }
    }

    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("INSERT is not supported");
    }

    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("DELETE is not supported");
    }

    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (!this.runtimeConfiguration.isDebug() || !this.runtimeConfiguration.allowSso()) {
            return 0;
        }
        try {
            validateCallEnvironment();
            if (this.uriMatcher.match(uri) == 1) {
                return updateExpirationTimeForCurrentApp(contentValues);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("uri ");
            sb.append(uri.toString());
            sb.append(" is not a valid Uri");
            throw new IllegalArgumentException(sb.toString());
        } catch (SecurityException e) {
            Ln.d("CONTENT: update, appId = %s, got SecurityException, return 0; message = '%s'", getAppId(), e.getMessage());
            return 0;
        }
    }

    public final String getType(Uri uri) {
        if (this.uriMatcher.match(uri) == 1) {
            return HttpConstants.CONTENT_TYPE_JSON;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("uri ");
        sb.append(uri.toString());
        sb.append(" is not a valid Uri");
        throw new IllegalArgumentException(sb.toString());
    }

    private Cursor getUsersForCurrentApp() {
        MatrixCursor matrixCursor = new MatrixCursor(COLUMN_NAMES);
        AppSessionInfo currentSessionInfo = getCurrentSessionInfo();
        if (!(currentSessionInfo == null || currentSessionInfo.getCurrentUserInfo() == null)) {
            matrixCursor.newRow().add(new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).reverseMap((Object) currentSessionInfo));
        }
        return matrixCursor;
    }

    private int updateExpirationTimeForCurrentApp(ContentValues contentValues) {
        Session currentSession = new UacfIdentitySdkFactory().getCurrentSession();
        AppSessionInfo currentSessionInfo = getCurrentSessionInfo();
        AppUserInfo currentUserInfo = currentSessionInfo != null ? currentSessionInfo.getCurrentUserInfo() : null;
        IdmOAuthTokenInfo tokenInfo = currentUserInfo != null ? currentUserInfo.getTokenInfo() : null;
        if (tokenInfo != null) {
            currentUserInfo.setTokenInfo(new Builder(tokenInfo).withStartTime(contentValues.getAsLong("startTime").longValue()).withExpiresIn(contentValues.getAsInteger(EXPIRES_IN).intValue()).build(), null);
            currentSession.setSessionInformationFor(getAppId(), currentSessionInfo);
            currentSession.saveAndNotify();
        }
        return 0;
    }

    private AppSessionInfo getCurrentSessionInfo() {
        return new UacfIdentitySdkFactory().getCurrentSession().getSessionInformationFor(getAppId());
    }

    private void validateCallEnvironment() {
        try {
            validateCallerSignature();
            validateInstaller();
            validateNotRunningInEmulator();
            validateNotDebuggable();
        } catch (SecurityException e) {
            if (this.runtimeConfiguration.isDebug()) {
                StringBuilder sb = new StringBuilder();
                sb.append("NONFATAL for appId = ");
                sb.append(getAppId());
                Ln.e(e, sb.toString(), new Object[0]);
            }
            if (this.runtimeConfiguration.shouldValidateCaller()) {
                throw e;
            }
        }
    }

    private void validateCallerSignature() {
        List<String> signatures = this.configuration.getSignatures();
        Ln.d("CONTENT: appId = %s, checking %s signatures", getAppId(), Integer.valueOf(CollectionUtils.size((Collection<?>) signatures)));
        for (String str : signatures) {
            if (ApplicationUtils.validateAppSignatureForCallingPackage(getContext(), str)) {
                Ln.d("CONTENT: appId = %s, validated %s!", getAppId(), str);
                return;
            }
        }
        throw new SecurityException(String.format("Calling package %s does not have a valid signature", new Object[]{getAppId()}));
    }

    private void validateNotRunningInEmulator() {
        if (ApplicationUtils.isRunningInsideEmulator()) {
            throw new SecurityException(String.format("Caller is %s, Operation not allowed from inside emulator", new Object[]{getAppId()}));
        }
    }

    private void validateNotDebuggable() {
        if (ApplicationUtils.isDebuggable(getContext())) {
            throw new SecurityException(String.format("Caller is %s, Operation not allowed from a debuggable app", new Object[]{getAppId()}));
        }
    }

    private List<String> getAuthorities() {
        ArrayList arrayList = new ArrayList();
        Tuple2 appIdMigrationTuple = AppIdMigrationUtil.getAppIdMigrationTuple(getAppId());
        UacfAppId uacfAppId = (UacfAppId) appIdMigrationTuple.getItem1();
        UacfAppId uacfAppId2 = (UacfAppId) appIdMigrationTuple.getItem2();
        arrayList.add(getAuthority(uacfAppId2));
        if (uacfAppId != uacfAppId2) {
            arrayList.add(getAuthority(uacfAppId));
        }
        return arrayList;
    }

    private static String getAuthority(UacfAppId uacfAppId) {
        StringBuilder sb = new StringBuilder();
        sb.append(AUTHORITY_PREFIX);
        sb.append(uacfAppId.toString().toLowerCase(Locale.ENGLISH));
        String sb2 = sb.toString();
        if (uacfAppId.getAppType() == AppType.DEBUG) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(AUTHORITY_DEBUG_APP_SUFFIX);
            return sb3.toString();
        } else if (uacfAppId.getAppType() != AppType.SAMPLE) {
            return sb2;
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(sb2);
            sb4.append(AUTHORITY_SAMPLE_APP_SUFFIX);
            return sb4.toString();
        }
    }

    public static Uri getUri(UacfAppId uacfAppId, String str) {
        return new Uri.Builder().scheme(Param.CONTENT).authority(getAuthority(uacfAppId)).path(str).build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0057  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.uacf.identity.internal.model.AppSessionInfo queryUsersForApp(android.content.Context r9, io.uacf.core.app.UacfAppId r10) {
        /*
            java.lang.String r0 = "/users"
            android.net.Uri r2 = getUri(r10, r0)
            java.lang.String r10 = "CREDENTIALS: queryCredentialsForApp, query app uri %s"
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r7 = 0
            r1[r7] = r2
            com.uacf.core.util.Ln.d(r10, r1)
            r10 = 0
            android.content.ContentResolver r1 = r9.getContentResolver()     // Catch:{ all -> 0x0051 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r9 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0051 }
            if (r9 == 0) goto L_0x004b
            boolean r1 = r9.moveToFirst()     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x004b
            java.lang.String r10 = "data"
            java.lang.String r10 = com.uacf.core.util.CursorUtils.getString(r9, r10)     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = "CREDENTIALS: got json %s"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x0049 }
            r0[r7] = r10     // Catch:{ all -> 0x0049 }
            com.uacf.core.util.Ln.d(r1, r0)     // Catch:{ all -> 0x0049 }
            com.uacf.core.mapping.GsonObjectMapper r0 = new com.uacf.core.mapping.GsonObjectMapper     // Catch:{ all -> 0x0049 }
            com.google.gson.FieldNamingPolicy r1 = com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES     // Catch:{ all -> 0x0049 }
            r0.<init>(r1)     // Catch:{ all -> 0x0049 }
            java.lang.Class<com.uacf.identity.internal.model.AppSessionInfo> r1 = com.uacf.identity.internal.model.AppSessionInfo.class
            com.uacf.core.mapping.GsonObjectMapper r0 = r0.withType(r1)     // Catch:{ all -> 0x0049 }
            java.lang.Object r10 = r0.tryMapFrom(r10)     // Catch:{ all -> 0x0049 }
            com.uacf.identity.internal.model.AppSessionInfo r10 = (com.uacf.identity.internal.model.AppSessionInfo) r10     // Catch:{ all -> 0x0049 }
            goto L_0x004b
        L_0x0049:
            r10 = move-exception
            goto L_0x0055
        L_0x004b:
            if (r9 == 0) goto L_0x0050
            r9.close()
        L_0x0050:
            return r10
        L_0x0051:
            r9 = move-exception
            r8 = r10
            r10 = r9
            r9 = r8
        L_0x0055:
            if (r9 == 0) goto L_0x005a
            r9.close()
        L_0x005a:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.identity.sdk.content.UacfIdentityContentProvider.queryUsersForApp(android.content.Context, io.uacf.core.app.UacfAppId):com.uacf.identity.internal.model.AppSessionInfo");
    }

    public static int updateExpirationTimeForApp(Context context, UacfAppId uacfAppId, long j, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("startTime", Long.valueOf(j));
        contentValues.put(EXPIRES_IN, Long.valueOf(j2));
        return context.getContentResolver().update(getUri(uacfAppId, USERS), contentValues, null, null);
    }

    public static void notifyChanges(Context context, UacfAppId uacfAppId, String str) {
        Uri uri = getUri(uacfAppId, str);
        Ln.d("CREDENTIALS: notify on %s", uri.toString());
        context.getContentResolver().notifyChange(uri, null);
    }
}
