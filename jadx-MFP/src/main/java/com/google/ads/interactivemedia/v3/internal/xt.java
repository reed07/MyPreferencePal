package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* compiled from: IMASDK */
final class xt implements Serializable, WildcardType {
    private static final long serialVersionUID = 0;
    private final Type a;
    private final Type b;

    public xt(Type[] typeArr, Type[] typeArr2) {
        boolean z = true;
        tt.a(typeArr2.length <= 1);
        tt.a(typeArr.length == 1);
        if (typeArr2.length == 1) {
            tt.a(typeArr2[0]);
            xq.d(typeArr2[0]);
            if (typeArr[0] != Object.class) {
                z = false;
            }
            tt.a(z);
            this.b = xq.a(typeArr2[0]);
            this.a = Object.class;
            return;
        }
        tt.a(typeArr[0]);
        xq.d(typeArr[0]);
        this.b = null;
        this.a = xq.a(typeArr[0]);
    }

    public final Type[] getUpperBounds() {
        return new Type[]{this.a};
    }

    public final Type[] getLowerBounds() {
        Type type = this.b;
        if (type == null) {
            return xq.a;
        }
        return new Type[]{type};
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && xq.a((Type) this, (Type) (WildcardType) obj);
    }

    public final int hashCode() {
        Type type = this.b;
        return (type != null ? type.hashCode() + 31 : 1) ^ (this.a.hashCode() + 31);
    }

    public final String toString() {
        if (this.b != null) {
            StringBuilder sb = new StringBuilder("? super ");
            sb.append(xq.c(this.b));
            return sb.toString();
        } else if (this.a == Object.class) {
            return "?";
        } else {
            StringBuilder sb2 = new StringBuilder("? extends ");
            sb2.append(xq.c(this.a));
            return sb2.toString();
        }
    }
}
