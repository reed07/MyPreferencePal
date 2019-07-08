package com.google.firebase.appindexing.internal;

import android.annotation.TargetApi;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

@TargetApi(19)
final class zzaa extends zzz {
    private final Context zzdc;
    private final ContentResolver zzfk;

    public zzaa(Context context) {
        this.zzdc = context;
        this.zzfk = context.getContentResolver();
    }

    public final void grantSlicePermission(String str, Uri uri) {
        ContentProviderClient acquireUnstableContentProviderClient = this.zzfk.acquireUnstableContentProviderClient(uri);
        try {
            Bundle bundle = new Bundle();
            bundle.putParcelable("slice_uri", uri);
            bundle.putString("provider_pkg", this.zzdc.getPackageName());
            bundle.putString("pkg", str);
            acquireUnstableContentProviderClient.call("grant_perms", null, bundle);
        } catch (RemoteException e) {
            Log.e("ContentValues", "Unable to get slice descendants", e);
        } finally {
            acquireUnstableContentProviderClient.release();
        }
    }
}
