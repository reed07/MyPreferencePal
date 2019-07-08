package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

final class zzuj extends PushbackInputStream {
    private final /* synthetic */ zzug zzcam;

    zzuj(zzug zzug, InputStream inputStream, int i) {
        this.zzcam = zzug;
        super(inputStream, 1);
    }

    public final void close() throws IOException {
        this.zzcam.zzcad.disconnect();
        super.close();
    }
}
