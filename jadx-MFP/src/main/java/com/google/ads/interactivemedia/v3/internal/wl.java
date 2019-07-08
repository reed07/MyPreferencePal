package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Field;
import java.util.Locale;

/* 'enum' access flag removed */
/* compiled from: IMASDK */
final class wl extends wg {
    wl(String str, int i) {
        super(str, 4, 0);
    }

    public final String a(Field field) {
        return a(field.getName(), "-").toLowerCase(Locale.ENGLISH);
    }
}
