package com.google.firebase.appindexing.internal;

import android.annotation.TargetApi;
import android.app.slice.SliceManager;
import android.content.Context;
import android.net.Uri;

@TargetApi(28)
final class zzab extends zzz {
    private final SliceManager zzfl;

    public zzab(Context context) {
        this.zzfl = (SliceManager) context.getSystemService(SliceManager.class);
    }

    public final void grantSlicePermission(String str, Uri uri) {
        this.zzfl.grantSlicePermission(str, uri);
    }
}
