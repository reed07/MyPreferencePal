package com.shinobicontrols.charts;

import com.shinobicontrols.charts.ag.a;
import java.util.Set;

abstract class ag<THandler extends a> {

    interface a {
    }

    static class b {
        b() {
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract b a();

    /* access modifiers changed from: 0000 */
    public abstract void a(THandler thandler);

    /* access modifiers changed from: 0000 */
    public THandler c(a aVar) {
        return aVar;
    }

    ag() {
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        return ((ag) obj).a().equals(a());
    }

    public int hashCode() {
        return a().hashCode();
    }

    /* access modifiers changed from: 0000 */
    public void b(a aVar) {
        a c = c(aVar);
        if (c != null) {
            a((THandler) c);
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(Set<? extends a> set) {
        for (a b2 : set) {
            b(b2);
        }
    }
}
