package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

@zzark
public final class zzaul extends zzaus {
    private final String zzeee;
    private final int zzeef;

    public zzaul(String str, int i) {
        this.zzeee = str;
        this.zzeef = i;
    }

    public final String getType() {
        return this.zzeee;
    }

    public final int getAmount() {
        return this.zzeef;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzaul)) {
            return false;
        }
        zzaul zzaul = (zzaul) obj;
        if (!Objects.equal(this.zzeee, zzaul.zzeee) || !Objects.equal(Integer.valueOf(this.zzeef), Integer.valueOf(zzaul.zzeef))) {
            return false;
        }
        return true;
    }
}
