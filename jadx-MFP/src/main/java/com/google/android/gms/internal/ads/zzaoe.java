package com.google.android.gms.internal.ads;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Environment;
import com.google.android.gms.ads.internal.zzbv;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;

final class zzaoe implements OnClickListener {
    private final /* synthetic */ String zzdpv;
    private final /* synthetic */ String zzdpw;
    private final /* synthetic */ zzaod zzdpx;

    zzaoe(zzaod zzaod, String str, String str2) {
        this.zzdpx = zzaod;
        this.zzdpv = str;
        this.zzdpw = str2;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        DownloadManager downloadManager = (DownloadManager) this.zzdpx.mContext.getSystemService(Attributes.XPROMO_DOWNLOAD);
        try {
            String str = this.zzdpv;
            String str2 = this.zzdpw;
            Request request = new Request(Uri.parse(str));
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, str2);
            zzbv.zzlh().zza(request);
            downloadManager.enqueue(request);
        } catch (IllegalStateException unused) {
            this.zzdpx.zzda("Could not store picture.");
        }
    }
}
