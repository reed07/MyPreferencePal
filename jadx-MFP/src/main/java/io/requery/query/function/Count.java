package io.requery.query.function;

import io.requery.meta.Attribute;

public class Count extends Function<Integer> {
    private Attribute<?, ?>[] attributes;
    private Class<?> entityClass;

    private Count(Class<?> cls) {
        super("count", Integer.class);
        this.entityClass = cls;
    }

    public static Count count(Class<?> cls) {
        return new Count(cls);
    }

    public Object[] arguments() {
        Class<?> cls = this.entityClass;
        if (cls == null) {
            return this.attributes;
        }
        return new Object[]{cls};
    }
}
