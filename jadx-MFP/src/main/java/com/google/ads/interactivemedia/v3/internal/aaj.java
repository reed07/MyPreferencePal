package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;

/* compiled from: IMASDK */
final class aaj extends xj<Character> {
    aaj() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        String h = abu.h();
        if (h.length() == 1) {
            return Character.valueOf(h.charAt(0));
        }
        StringBuilder sb = new StringBuilder("Expecting character, got: ");
        sb.append(h);
        throw new xh(sb.toString());
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        Character ch = (Character) obj;
        if (ch == null) {
            str = null;
        } else {
            str = String.valueOf(ch);
        }
        abx.b(str);
    }
}
