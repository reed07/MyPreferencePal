package com.google.android.gms.tagmanager;

import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzbw {
    private final long zzaax;
    private final long zzbcl;
    private final long zzbcm;
    private String zzbcn;

    /* access modifiers changed from: 0000 */
    public final long zzov() {
        return this.zzbcl;
    }

    /* access modifiers changed from: 0000 */
    public final long zzow() {
        return this.zzbcm;
    }

    zzbw(long j, long j2, long j3) {
        this.zzbcl = j;
        this.zzaax = j2;
        this.zzbcm = j3;
    }

    /* access modifiers changed from: 0000 */
    public final String zzox() {
        return this.zzbcn;
    }

    /* access modifiers changed from: 0000 */
    public final void zzds(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzbcn = str;
        }
    }
}
