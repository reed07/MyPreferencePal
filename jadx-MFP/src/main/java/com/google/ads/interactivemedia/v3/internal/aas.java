package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.net.InetAddress;

/* compiled from: IMASDK */
final class aas extends xj<InetAddress> {
    aas() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return InetAddress.getByName(abu.h());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        InetAddress inetAddress = (InetAddress) obj;
        if (inetAddress == null) {
            str = null;
        } else {
            str = inetAddress.getHostAddress();
        }
        abx.b(str);
    }
}
