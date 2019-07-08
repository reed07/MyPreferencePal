package io.requery.query.function;

import io.requery.query.Expression;

public class Substr<V> extends Function<V> {
    private final Expression<V> expression;
    private final int length;
    private final int offset;

    private Substr(Expression<V> expression2, int i, int i2) {
        super("substr", expression2.getClassType());
        this.expression = expression2;
        this.offset = i;
        this.length = i2;
    }

    public static <U> Substr<U> substr(Expression<U> expression2, int i, int i2) {
        return new Substr<>(expression2, i, i2);
    }

    public Object[] arguments() {
        return new Object[]{this.expression, Integer.valueOf(this.offset), Integer.valueOf(this.length)};
    }
}
