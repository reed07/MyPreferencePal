package com.google.android.gms.internal.icing;

import android.accounts.Account;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.ArrayList;
import java.util.List;

@ShowFirstParty
public final class zzh {
    private Account account;
    private String zzj;
    private boolean zzk;
    private List<zzl> zzl;

    public final zzh zza(zzl zzl2) {
        if (this.zzl == null && zzl2 != null) {
            this.zzl = new ArrayList();
        }
        if (zzl2 != null) {
            this.zzl.add(zzl2);
        }
        return this;
    }

    public final zzh zza(String str) {
        this.zzj = str;
        return this;
    }

    public final zzh zza(boolean z) {
        this.zzk = true;
        return this;
    }

    public final zzh zza(Account account2) {
        this.account = account2;
        return this;
    }

    public final zzg zzb() {
        String str = this.zzj;
        boolean z = this.zzk;
        Account account2 = this.account;
        List<zzl> list = this.zzl;
        return new zzg(str, z, account2, list != null ? (zzl[]) list.toArray(new zzl[list.size()]) : null);
    }
}
