package com.google.android.gms.internal.measurement;

import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.UUID;

public final class zzcv {
    private final String name;
    private final long zzabv;
    private final /* synthetic */ zzct zzabw;

    private zzcv(zzct zzct, String str, long j) {
        this.zzabw = zzct;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(j > 0);
        this.name = str;
        this.zzabv = j;
    }

    private final void zzfl() {
        long currentTimeMillis = this.zzabw.zzbx().currentTimeMillis();
        Editor edit = this.zzabw.zzabr.edit();
        edit.remove(zzfp());
        edit.remove(zzfq());
        edit.putLong(zzfo(), currentTimeMillis);
        edit.commit();
    }

    public final void zzad(String str) {
        if (zzfn() == 0) {
            zzfl();
        }
        if (str == null) {
            str = "";
        }
        synchronized (this) {
            long j = this.zzabw.zzabr.getLong(zzfp(), 0);
            if (j <= 0) {
                Editor edit = this.zzabw.zzabr.edit();
                edit.putString(zzfq(), str);
                edit.putLong(zzfp(), 1);
                edit.apply();
                return;
            }
            long j2 = j + 1;
            boolean z = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / j2;
            Editor edit2 = this.zzabw.zzabr.edit();
            if (z) {
                edit2.putString(zzfq(), str);
            }
            edit2.putLong(zzfp(), j2);
            edit2.apply();
        }
    }

    public final Pair<String, Long> zzfm() {
        long j;
        long zzfn = zzfn();
        if (zzfn == 0) {
            j = 0;
        } else {
            j = Math.abs(zzfn - this.zzabw.zzbx().currentTimeMillis());
        }
        long j2 = this.zzabv;
        if (j < j2) {
            return null;
        }
        if (j > (j2 << 1)) {
            zzfl();
            return null;
        }
        String string = this.zzabw.zzabr.getString(zzfq(), null);
        long j3 = this.zzabw.zzabr.getLong(zzfp(), 0);
        zzfl();
        if (string == null || j3 <= 0) {
            return null;
        }
        return new Pair<>(string, Long.valueOf(j3));
    }

    private final long zzfn() {
        return this.zzabw.zzabr.getLong(zzfo(), 0);
    }

    private final String zzfo() {
        return String.valueOf(this.name).concat(":start");
    }

    private final String zzfp() {
        return String.valueOf(this.name).concat(":count");
    }

    @VisibleForTesting
    private final String zzfq() {
        return String.valueOf(this.name).concat(":value");
    }
}
