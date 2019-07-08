package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.Locale;
import java.util.StringTokenizer;

/* compiled from: IMASDK */
final class aay extends xj<Locale> {
    aay() {
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        String str = null;
        if (abu.f() == abw.NULL) {
            abu.j();
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(abu.h(), "_");
        String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        if (stringTokenizer.hasMoreElements()) {
            str = stringTokenizer.nextToken();
        }
        if (nextToken2 == null && str == null) {
            return new Locale(nextToken);
        }
        if (str == null) {
            return new Locale(nextToken, nextToken2);
        }
        return new Locale(nextToken, nextToken2, str);
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        Locale locale = (Locale) obj;
        if (locale == null) {
            str = null;
        } else {
            str = locale.toString();
        }
        abx.b(str);
    }
}
