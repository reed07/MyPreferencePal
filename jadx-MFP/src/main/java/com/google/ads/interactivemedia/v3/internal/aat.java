package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.UUID;

/* compiled from: IMASDK */
final class aat extends xj<UUID> {
    aat() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return UUID.fromString(abu.h());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        UUID uuid = (UUID) obj;
        if (uuid == null) {
            str = null;
        } else {
            str = uuid.toString();
        }
        abx.b(str);
    }
}
