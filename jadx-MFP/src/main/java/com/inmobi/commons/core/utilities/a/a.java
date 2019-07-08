package com.inmobi.commons.core.utilities.a;

import com.inmobi.commons.core.d.c;

/* compiled from: EncryptionDao */
public final class a {
    c a = c.b("aes_key_store");

    public final void a(String str) {
        this.a.a("aes_public_key", str);
        this.a.a("last_generated_ts", System.currentTimeMillis() / 1000);
    }
}
