package com.shinobicontrols.charts;

import java.util.Comparator;
import java.util.Locale;

class ap {
    static final Comparator<ap> a = new Comparator<ap>() {
        /* renamed from: a */
        public int compare(ap apVar, ap apVar2) {
            if (apVar.b < apVar2.b) {
                return -1;
            }
            return apVar.b > apVar2.b ? 1 : 0;
        }
    };
    final double b;
    final bj c;

    ap(double d, bj bjVar) {
        this.b = d;
        this.c = bjVar;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ap)) {
            return false;
        }
        if (((ap) obj).b != this.b) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.b);
        return 527 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    public String toString() {
        return String.format(Locale.US, "%s, from %f", new Object[]{this.c.toString(), Double.valueOf(this.b)});
    }
}
