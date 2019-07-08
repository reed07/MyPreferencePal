package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import com.google.android.exoplayer2.C;
import java.util.Set;

@TargetApi(11)
public class zzays extends zzayr {
    public boolean zza(Context context, WebSettings webSettings) {
        super.zza(context, webSettings);
        return ((Boolean) zzbak.zza(context, new zzayt(this, context, webSettings))).booleanValue();
    }

    public final boolean zzaa(View view) {
        view.setLayerType(1, null);
        return true;
    }

    public final boolean zzz(View view) {
        view.setLayerType(0, null);
        return true;
    }

    public final boolean zza(Window window) {
        window.setFlags(C.DEFAULT_MUXED_BUFFER_SIZE, C.DEFAULT_MUXED_BUFFER_SIZE);
        return true;
    }

    public zzbgh zza(zzbgg zzbgg, zzum zzum, boolean z) {
        return new zzbhe(zzbgg, zzum, z);
    }

    public final Set<String> zzh(Uri uri) {
        return uri.getQueryParameterNames();
    }

    public final boolean zza(Request request) {
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        return true;
    }
}
