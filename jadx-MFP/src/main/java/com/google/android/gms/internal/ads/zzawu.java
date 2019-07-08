package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@TargetApi(21)
@ParametersAreNonnullByDefault
final class zzawu {
    private static final Map<String, String> zzegp;
    private final List<String> zzegq;
    private final zzawg zzegr;
    private final Context zzsp;

    zzawu(Context context, List<String> list, zzawg zzawg) {
        this.zzsp = context;
        this.zzegq = list;
        this.zzegr = zzawg;
    }

    /* access modifiers changed from: 0000 */
    public final List<String> zzc(String[] strArr) {
        boolean z;
        boolean z2;
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Iterator it = this.zzegq.iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                String str2 = (String) it.next();
                if (!str2.equals(str)) {
                    String valueOf = String.valueOf("android.webkit.resource.");
                    String valueOf2 = String.valueOf(str2);
                    if ((valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).equals(str)) {
                        z2 = true;
                        break;
                    }
                } else {
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                if (zzegp.containsKey(str)) {
                    zzbv.zzlf();
                    if (!zzayh.zzn(this.zzsp, (String) zzegp.get(str))) {
                        z = false;
                    }
                }
                if (z) {
                    arrayList.add(str);
                } else {
                    this.zzegr.zzdk(str);
                }
            } else {
                this.zzegr.zzdj(str);
            }
        }
        return arrayList;
    }

    static {
        HashMap hashMap = new HashMap();
        if (PlatformVersion.isAtLeastLollipop()) {
            hashMap.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
            hashMap.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        }
        zzegp = hashMap;
    }
}
