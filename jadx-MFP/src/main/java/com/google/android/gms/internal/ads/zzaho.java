package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
final class zzaho {
    /* access modifiers changed from: private */
    public final String zzboa;
    private final LinkedList<zzahp> zzdhh = new LinkedList<>();
    /* access modifiers changed from: private */
    public zzwb zzdhi;
    private final int zzdhj;
    private boolean zzdhk;

    zzaho(zzwb zzwb, String str, int i) {
        Preconditions.checkNotNull(zzwb);
        Preconditions.checkNotNull(str);
        this.zzdhi = zzwb;
        this.zzboa = str;
        this.zzdhj = i;
    }

    /* access modifiers changed from: 0000 */
    public final zzwb zztj() {
        return this.zzdhi;
    }

    /* access modifiers changed from: 0000 */
    public final int getNetworkType() {
        return this.zzdhj;
    }

    /* access modifiers changed from: 0000 */
    public final String getAdUnitId() {
        return this.zzboa;
    }

    /* access modifiers changed from: 0000 */
    public final zzahp zzl(@Nullable zzwb zzwb) {
        if (zzwb != null) {
            this.zzdhi = zzwb;
        }
        return (zzahp) this.zzdhh.remove();
    }

    /* access modifiers changed from: 0000 */
    public final int size() {
        return this.zzdhh.size();
    }

    /* access modifiers changed from: 0000 */
    public final int zztk() {
        Iterator it = this.zzdhh.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((zzahp) it.next()).zzblw) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzb(zzagi zzagi) {
        zzahp zzahp = new zzahp(this, zzagi);
        this.zzdhh.add(zzahp);
        return zzahp.load();
    }

    /* access modifiers changed from: 0000 */
    public final int zztl() {
        Iterator it = this.zzdhh.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((zzahp) it.next()).load()) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzagi zzagi, zzwb zzwb) {
        this.zzdhh.add(new zzahp(this, zzagi, zzwb));
    }

    /* access modifiers changed from: 0000 */
    public final void zztm() {
        this.zzdhk = true;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zztn() {
        return this.zzdhk;
    }
}
