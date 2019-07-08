package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.Enum;
import java.util.HashMap;
import java.util.Map;

/* compiled from: IMASDK */
final class abo<T extends Enum<T>> extends xj<T> {
    private final Map<String, T> a = new HashMap();
    private final Map<T, String> b = new HashMap();

    public abo(Class<T> cls) {
        Enum[] enumArr;
        try {
            for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                String name = enumR.name();
                xn xnVar = (xn) cls.getField(name).getAnnotation(xn.class);
                if (xnVar != null) {
                    name = xnVar.a();
                    for (String put : xnVar.b()) {
                        this.a.put(put, enumR);
                    }
                }
                this.a.put(name, enumR);
                this.b.put(enumR, name);
            }
        } catch (NoSuchFieldException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object read(abu abu) throws IOException {
        if (abu.f() != abw.NULL) {
            return (Enum) this.a.get(abu.h());
        }
        abu.j();
        return null;
    }

    public final /* synthetic */ void write(abx abx, Object obj) throws IOException {
        String str;
        Enum enumR = (Enum) obj;
        if (enumR == null) {
            str = null;
        } else {
            str = (String) this.b.get(enumR);
        }
        abx.b(str);
    }
}
