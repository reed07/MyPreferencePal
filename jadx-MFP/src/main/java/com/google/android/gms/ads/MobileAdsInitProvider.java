package com.google.android.gms.ads;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.internal.ads.zzze;

@KeepForSdkWithMembers
public class MobileAdsInitProvider extends ContentProvider {
    private final zzze zzwa = new zzze();

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        this.zzwa.attachInfo(context, providerInfo);
    }

    public boolean onCreate() {
        return this.zzwa.onCreate();
    }

    @Nullable
    public Cursor query(@NonNull Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return this.zzwa.query(uri, strArr, str, strArr2, str2);
    }

    @Nullable
    public String getType(@NonNull Uri uri) {
        return this.zzwa.getType(uri);
    }

    @Nullable
    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        return this.zzwa.insert(uri, contentValues);
    }

    public int delete(@NonNull Uri uri, String str, String[] strArr) {
        return this.zzwa.delete(uri, str, strArr);
    }

    public int update(@NonNull Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return this.zzwa.update(uri, contentValues, str, strArr);
    }
}
