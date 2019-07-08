package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.LogPrinter;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;

@VisibleForTesting
public final class zze implements zzo {
    private static final Uri zzrd;
    private final LogPrinter zzrx = new LogPrinter(4, "GA/LogCatTransport");

    public final Uri zzo() {
        return zzrd;
    }

    public final void zzb(zzg zzg) {
        ArrayList arrayList = new ArrayList(zzg.zzt());
        Collections.sort(arrayList, new zzf(this));
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            String obj2 = ((zzi) obj).toString();
            if (!TextUtils.isEmpty(obj2)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(obj2);
            }
        }
        this.zzrx.println(sb.toString());
    }

    static {
        Builder builder = new Builder();
        builder.scheme(ShareConstants.MEDIA_URI);
        builder.authority(ImagesContract.LOCAL);
        zzrd = builder.build();
    }
}
