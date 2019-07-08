package io.requery.query.function;

import io.requery.query.Expression;

public class Sum<V> extends Function<V> {
    private final Expression<V> expression;

    private Sum(Expression<V> expression2) {
        super("sum", expression2.getClassType());
        this.expression = expression2;
    }

    public static <U> Sum<U> sum(Expression<U> expression2) {
        return new Sum<>(expression2);
    }

    public Object[] arguments() {
        return new Object[]{this.expression};
    }
}
