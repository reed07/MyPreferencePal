package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzark
public final class zzaod extends zzaok {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Map<String, String> zzczl;

    public zzaod(zzbgg zzbgg, Map<String, String> map) {
        super(zzbgg, "storePicture");
        this.zzczl = map;
        this.mContext = zzbgg.zzabw();
    }

    public final void execute() {
        if (this.mContext == null) {
            zzda("Activity context is not available");
            return;
        }
        zzbv.zzlf();
        if (!zzayh.zzam(this.mContext).zzqt()) {
            zzda("Feature is not supported by the device.");
            return;
        }
        String str = (String) this.zzczl.get("iurl");
        if (TextUtils.isEmpty(str)) {
            zzda("Image url cannot be empty.");
        } else if (!URLUtil.isValidUrl(str)) {
            String str2 = "Invalid image url: ";
            String valueOf = String.valueOf(str);
            zzda(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else {
            String lastPathSegment = Uri.parse(str).getLastPathSegment();
            zzbv.zzlf();
            if (!zzayh.zzdz(lastPathSegment)) {
                String str3 = "Image type not recognized: ";
                String valueOf2 = String.valueOf(lastPathSegment);
                zzda(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                return;
            }
            Resources resources = zzbv.zzlj().getResources();
            zzbv.zzlf();
            Builder zzal = zzayh.zzal(this.mContext);
            zzal.setTitle(resources != null ? resources.getString(R.string.s1) : "Save image");
            zzal.setMessage(resources != null ? resources.getString(R.string.s2) : "Allow Ad to store image in Picture gallery?");
            zzal.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzaoe(this, str, lastPathSegment));
            zzal.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzaof(this));
            zzal.create().show();
        }
    }
}
