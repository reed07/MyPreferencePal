package com.google.android.gms.internal.ads;

import java.io.IOException;

public class zzpc extends IOException {
    private final int type;
    private final zzoz zzazo;

    public zzpc(String str, zzoz zzoz, int i) {
        super(str);
        this.zzazo = zzoz;
        this.type = 1;
    }

    public zzpc(IOException iOException, zzoz zzoz, int i) {
        super(iOException);
        this.zzazo = zzoz;
        this.type = i;
    }

    public zzpc(String str, IOException iOException, zzoz zzoz, int i) {
        super(str, iOException);
        this.zzazo = zzoz;
        this.type = 1;
    }
}
