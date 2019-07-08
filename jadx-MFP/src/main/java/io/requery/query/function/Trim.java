package io.requery.query.function;

import io.requery.query.Expression;

public class Trim<V> extends Function<V> {
    private final String chars;
    private final Expression<V> expression;

    public Object[] arguments() {
        String str = this.chars;
        if (str == null) {
            return new Object[]{this.expression};
        }
        return new Object[]{this.expression, str};
    }
}
