package com.google.ads.interactivemedia.v3.internal;

import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import java.io.FileNotFoundException;
import java.io.IOException;

/* compiled from: IMASDK */
public class ti {
    private final int a;

    public long a(IOException iOException) {
        if (!(iOException instanceof tg)) {
            return -9223372036854775807L;
        }
        int i = ((tg) iOException).a;
        if (i == 404 || i == 410) {
            return DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS;
        }
        return -9223372036854775807L;
    }

    public long a(IOException iOException, int i) {
        if ((iOException instanceof ca) || (iOException instanceof FileNotFoundException) || (iOException instanceof tr)) {
            return -9223372036854775807L;
        }
        return (long) Math.min((i - 1) * 1000, 5000);
    }

    public int a(int i) {
        int i2 = this.a;
        if (i2 == -1) {
            return i == 7 ? 6 : 3;
        }
        return i2;
    }

    public ti() {
        this(-1);
    }

    public ti(int i) {
        this.a = -1;
    }
}
