package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
@Deprecated
public final class GoogleServices {
    private static final Object sLock = new Object();
    @GuardedBy("sLock")
    private static GoogleServices zzax;
    private final String zzay;
    private final Status zzaz;
    private final boolean zzba;
    private final boolean zzbb;

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("google_app_measurement_enable", "integer", resources.getResourcePackageName(R.string.common_google_play_services_unknown_issue));
        boolean z = true;
        if (identifier != 0) {
            if (resources.getInteger(identifier) == 0) {
                z = false;
            }
            this.zzbb = !z;
        } else {
            this.zzbb = false;
        }
        this.zzba = z;
        String zzc = zzp.zzc(context);
        if (zzc == null) {
            zzc = new StringResourceValueReader(context).getString("google_app_id");
        }
        if (TextUtils.isEmpty(zzc)) {
            this.zzaz = new Status(10, "Missing google app id value from from string resources with name google_app_id.");
            this.zzay = null;
            return;
        }
        this.zzay = zzc;
        this.zzaz = Status.RESULT_SUCCESS;
    }

    @KeepForSdk
    @VisibleForTesting
    GoogleServices(String str, boolean z) {
        this.zzay = str;
        this.zzaz = Status.RESULT_SUCCESS;
        this.zzba = z;
        this.zzbb = !z;
    }

    @KeepForSdk
    public static Status initialize(Context context, String str, boolean z) {
        Preconditions.checkNotNull(context, "Context must not be null.");
        Preconditions.checkNotEmpty(str, "App ID must be nonempty.");
        synchronized (sLock) {
            if (zzax != null) {
                Status checkGoogleAppId = zzax.checkGoogleAppId(str);
                return checkGoogleAppId;
            }
            GoogleServices googleServices = new GoogleServices(str, z);
            zzax = googleServices;
            Status status = googleServices.zzaz;
            return status;
        }
    }

    /* access modifiers changed from: 0000 */
    @KeepForSdk
    @VisibleForTesting
    public final Status checkGoogleAppId(String str) {
        String str2 = this.zzay;
        if (str2 == null || str2.equals(str)) {
            return Status.RESULT_SUCCESS;
        }
        String str3 = this.zzay;
        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 97);
        sb.append("Initialize was called with two different Google App IDs.  Only the first app ID will be used: '");
        sb.append(str3);
        sb.append("'.");
        return new Status(10, sb.toString());
    }

    @KeepForSdk
    public static Status initialize(Context context) {
        Status status;
        Preconditions.checkNotNull(context, "Context must not be null.");
        synchronized (sLock) {
            if (zzax == null) {
                zzax = new GoogleServices(context);
            }
            status = zzax.zzaz;
        }
        return status;
    }

    @KeepForSdk
    public static String getGoogleAppId() {
        return checkInitialized("getGoogleAppId").zzay;
    }

    @KeepForSdk
    public static boolean isMeasurementEnabled() {
        GoogleServices checkInitialized = checkInitialized("isMeasurementEnabled");
        return checkInitialized.zzaz.isSuccess() && checkInitialized.zzba;
    }

    @KeepForSdk
    public static boolean isMeasurementExplicitlyDisabled() {
        return checkInitialized("isMeasurementExplicitlyDisabled").zzbb;
    }

    @KeepForSdk
    @VisibleForTesting
    static void clearInstanceForTest() {
        synchronized (sLock) {
            zzax = null;
        }
    }

    @KeepForSdk
    private static GoogleServices checkInitialized(String str) {
        GoogleServices googleServices;
        synchronized (sLock) {
            if (zzax != null) {
                googleServices = zzax;
            } else {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
                sb.append("Initialize must be called before ");
                sb.append(str);
                sb.append(".");
                throw new IllegalStateException(sb.toString());
            }
        }
        return googleServices;
    }
}
