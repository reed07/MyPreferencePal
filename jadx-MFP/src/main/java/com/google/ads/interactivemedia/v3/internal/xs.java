package com.google.ads.interactivemedia.v3.internal;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* compiled from: IMASDK */
final class xs implements Serializable, ParameterizedType {
    private static final long serialVersionUID = 0;
    private final Type a;
    private final Type b;
    private final Type[] c;

    public xs(Type type, Type type2, Type... typeArr) {
        Type type3;
        if (type2 instanceof Class) {
            Class cls = (Class) type2;
            boolean z = true;
            boolean z2 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
            if (type == null && !z2) {
                z = false;
            }
            tt.a(z);
        }
        if (type == null) {
            type3 = null;
        } else {
            type3 = xq.a(type);
        }
        this.a = type3;
        this.b = xq.a(type2);
        this.c = (Type[]) typeArr.clone();
        int length = this.c.length;
        for (int i = 0; i < length; i++) {
            tt.a(this.c[i]);
            xq.d(this.c[i]);
            Type[] typeArr2 = this.c;
            typeArr2[i] = xq.a(typeArr2[i]);
        }
    }

    public final Type[] getActualTypeArguments() {
        return (Type[]) this.c.clone();
    }

    public final Type getRawType() {
        return this.b;
    }

    public final Type getOwnerType() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && xq.a((Type) this, (Type) (ParameterizedType) obj);
    }

    public final int hashCode() {
        int hashCode = Arrays.hashCode(this.c) ^ this.b.hashCode();
        Type type = this.a;
        return hashCode ^ (type != null ? type.hashCode() : 0);
    }

    public final String toString() {
        int length = this.c.length;
        if (length == 0) {
            return xq.c(this.b);
        }
        StringBuilder sb = new StringBuilder((length + 1) * 30);
        sb.append(xq.c(this.b));
        sb.append("<");
        sb.append(xq.c(this.c[0]));
        for (int i = 1; i < length; i++) {
            sb.append(", ");
            sb.append(xq.c(this.c[i]));
        }
        sb.append(">");
        return sb.toString();
    }
}
