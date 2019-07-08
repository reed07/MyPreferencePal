package com.google.ads.interactivemedia.v3.internal;

import android.util.SparseArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.primitives.SignedBytes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: IMASDK */
public class jc {
    private final int a;
    private final List<bs> b;

    public SparseArray<iz> a() {
        return new SparseArray<>();
    }

    public iz a(int i, jb jbVar) {
        switch (i) {
            case 2:
                return new io(new id(b(jbVar)));
            case 3:
            case 4:
                return new io(new im(jbVar.b));
            case 15:
                if (a(2)) {
                    return null;
                }
                return new io(new hz(false, jbVar.b));
            case 17:
                if (a(2)) {
                    return null;
                }
                return new io(new il(jbVar.b));
            case 21:
                return new io(new ik());
            case 27:
                if (a(4)) {
                    return null;
                }
                return new io(new Cif(a(jbVar), a(1), a(8)));
            case 36:
                return new io(new ii(a(jbVar)));
            case 89:
                return new io(new ib(jbVar.c));
            case 129:
            case 135:
                return new io(new hx(jbVar.b));
            case 130:
                if (a(64)) {
                    return null;
                }
                break;
            case 134:
                if (a(16)) {
                    return null;
                }
                return new iq(new is());
            case 138:
                break;
            default:
                return null;
        }
        return new io(new ia(jbVar.b));
    }

    public jc() {
        this(0);
    }

    public jc(int i) {
        this(i, Collections.singletonList(bs.a(null, MimeTypes.APPLICATION_CEA608, 0, null)));
    }

    public jc(int i, List<bs> list) {
        this.a = i;
        this.b = list;
    }

    private ir a(jb jbVar) {
        return new ir(c(jbVar));
    }

    private je b(jb jbVar) {
        return new je(c(jbVar));
    }

    private List<bs> c(jb jbVar) {
        int i;
        String str;
        List list;
        if (a(32)) {
            return this.b;
        }
        ut utVar = new ut(jbVar.d);
        List<bs> list2 = this.b;
        while (utVar.b() > 0) {
            int d = utVar.d() + utVar.e();
            if (utVar.e() == 134) {
                list2 = new ArrayList<>();
                int e = utVar.e() & 31;
                for (int i2 = 0; i2 < e; i2++) {
                    String e2 = utVar.e(3);
                    int e3 = utVar.e();
                    boolean z = true;
                    boolean z2 = (e3 & 128) != 0;
                    if (z2) {
                        i = e3 & 63;
                        str = MimeTypes.APPLICATION_CEA708;
                    } else {
                        str = MimeTypes.APPLICATION_CEA608;
                        i = 1;
                    }
                    byte e4 = (byte) utVar.e();
                    utVar.d(1);
                    if (z2) {
                        if ((e4 & SignedBytes.MAX_POWER_OF_TWO) == 0) {
                            z = false;
                        }
                        list = qi.a(z);
                    } else {
                        list = null;
                    }
                    list2.add(bs.a((String) null, str, (String) null, -1, 0, e2, i, (fa) null, Long.MAX_VALUE, list));
                }
            }
            utVar.c(d);
        }
        return list2;
    }

    private boolean a(int i) {
        return (i & this.a) != 0;
    }
}
