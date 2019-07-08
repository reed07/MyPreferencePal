package com.google.ads.interactivemedia.v3.internal;

import java.lang.reflect.Field;
import java.util.Locale;

/* 'enum' access flag removed */
/* compiled from: IMASDK */
final class wm extends wg {
    wm(String str, int i) {
        super(str, 5, 0);
    }

    public final String a(Field field) {
        return a(field.getName(), ".").toLowerCase(Locale.ENGLISH);
    }
}
