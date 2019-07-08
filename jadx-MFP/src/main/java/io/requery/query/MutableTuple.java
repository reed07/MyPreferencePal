package io.requery.query;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class MutableTuple implements Tuple, Serializable {
    private static final Map<Class<?>, Class<?>> boxedTypes = new HashMap();
    private final Map<String, Object> map;
    private final Object[] values;

    static {
        boxedTypes.put(Boolean.TYPE, Boolean.class);
        boxedTypes.put(Integer.TYPE, Integer.class);
        boxedTypes.put(Long.TYPE, Long.class);
        boxedTypes.put(Short.TYPE, Short.class);
        boxedTypes.put(Float.TYPE, Float.class);
        boxedTypes.put(Double.TYPE, Double.class);
        boxedTypes.put(Character.TYPE, Character.class);
        boxedTypes.put(Byte.TYPE, Byte.class);
    }

    public MutableTuple(int i) {
        if (i > 0) {
            this.map = new HashMap(i);
            this.values = new Object[i];
            return;
        }
        throw new IllegalStateException();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000e, code lost:
        if (r3 != null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String keyOf(io.requery.query.Expression<?> r3) {
        /*
            r2 = this;
            java.lang.String r0 = r3.getName()
            boolean r1 = r3 instanceof io.requery.query.Aliasable
            if (r1 == 0) goto L_0x0011
            io.requery.query.Aliasable r3 = (io.requery.query.Aliasable) r3
            java.lang.String r3 = r3.getAlias()
            if (r3 == 0) goto L_0x0011
            goto L_0x0012
        L_0x0011:
            r3 = r0
        L_0x0012:
            if (r3 != 0) goto L_0x0016
            r3 = 0
            goto L_0x001c
        L_0x0016:
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r3 = r3.toLowerCase(r0)
        L_0x001c:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.requery.query.MutableTuple.keyOf(io.requery.query.Expression):java.lang.String");
    }

    public void set(int i, Expression<?> expression, Object obj) {
        this.map.put(keyOf(expression), obj);
        this.values[i] = obj;
    }

    public <V> V get(Expression<V> expression) {
        Object obj = this.map.get(keyOf(expression));
        if (obj == null) {
            return null;
        }
        Class classType = expression.getClassType();
        if (classType.isPrimitive()) {
            return ((Class) boxedTypes.get(classType)).cast(obj);
        }
        return classType.cast(obj);
    }

    public <V> V get(int i) {
        return this.values[i];
    }

    public <V> V get(String str) {
        return this.map.get(str.toLowerCase(Locale.ROOT));
    }

    public int hashCode() {
        return Arrays.hashCode(this.values);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MutableTuple)) {
            return false;
        }
        return Arrays.equals(this.values, ((MutableTuple) obj).values);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(" [ ");
        int i = 0;
        for (Entry entry : this.map.entrySet()) {
            if (i > 0) {
                sb.append(", ");
            }
            if (entry.getValue() == null) {
                str = "null";
            } else {
                str = entry.getValue().toString();
            }
            sb.append(str);
            i++;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
