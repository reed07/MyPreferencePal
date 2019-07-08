package com.google.android.gms.internal.ads;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.Nonnull;

@zzark
public final class zzze extends ContentProvider {
    public final int delete(@NonNull Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Nullable
    public final String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    public final Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        return null;
    }

    public final boolean onCreate() {
        return false;
    }

    @Nullable
    public final Cursor query(@Nonnull Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public final int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public final void attachInfo(Context context, ProviderInfo providerInfo) {
        String str;
        Bundle zzh = zzh(context);
        Boolean bool = null;
        if (zzh != null) {
            try {
                str = (String) zzh.get("com.google.android.gms.ads.APPLICATION_ID");
            } catch (ClassCastException unused) {
                zzbbd.e("The com.google.android.gms.ads.APPLICATION_ID metadata must have a String value");
                str = null;
            }
            try {
                bool = (Boolean) zzh.get("com.google.android.gms.ads.AD_MANAGER_APP");
            } catch (ClassCastException unused2) {
                zzbbd.e("The com.google.android.gms.ads.AD_MANAGER_APP metadata must have a boolean value");
            }
        } else {
            str = null;
        }
        if ((bool == null || !bool.booleanValue()) && str == null) {
            throw new IllegalStateException("\n\n******************************************************************************\n* The Google Mobile Ads SDK was initialized incorrectly. AdMob publishers    *\n* should follow the instructions here: https://goo.gl/fQ2neu to add a valid  *\n* App ID inside the AndroidManifest. Google Ad Manager publishers should     *\n* follow instructions here: https://goo.gl/h17b6x.                           *\n******************************************************************************\n\n");
        }
        if (str != null) {
            if (str.matches("^ca-app-pub-[0-9]{16}~[0-9]{10}$")) {
                String str2 = "Publisher provided Google AdMob App ID in manifest: ";
                String valueOf = String.valueOf(str);
                zzbbd.zzdn(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                Bundle bundle = new Bundle();
                bundle.putBoolean("measurementEnabled", false);
                zzakm.zza(context, str, bundle);
            } else {
                throw new IllegalStateException("\n\n******************************************************************************\n* Invalid application ID. Follow instructions here: https://goo.gl/fQ2neu to *\n* find your app ID.                                                          *\n******************************************************************************\n\n");
            }
        }
        super.attachInfo(context, providerInfo);
    }

    @Nullable
    private static Bundle zzh(Context context) {
        try {
            return Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (NullPointerException e) {
            zzbbd.zzb("Failed to load metadata: Null pointer exception", e);
            return null;
        } catch (NameNotFoundException e2) {
            zzbbd.zzb("Failed to load metadata: Package name not found", e2);
            return null;
        }
    }
}
