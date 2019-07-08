package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;

@zzark
public final class zzaum extends ContextWrapper {
    public final synchronized void setAppPackageName(String str) throws NameNotFoundException {
        throw new NoSuchMethodError();
    }

    public final Context getApplicationContext() {
        throw new NoSuchMethodError();
    }

    public final synchronized ApplicationInfo getApplicationInfo() {
        throw new NoSuchMethodError();
    }

    public final synchronized String getPackageName() {
        throw new NoSuchMethodError();
    }

    public final synchronized String getPackageResourcePath() {
        throw new NoSuchMethodError();
    }

    public final synchronized void zzf(Activity activity) {
        throw new NoSuchMethodError();
    }

    public final synchronized void startActivity(Intent intent) {
        throw new NoSuchMethodError();
    }

    public static Context zzu(Context context) {
        if (context instanceof zzaum) {
            return ((zzaum) context).getBaseContext();
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }
}
