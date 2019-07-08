package io.requery.query;

public class NamedExpression<V> extends FieldExpression<V> {
    private final String name;
    private final Class<V> type;

    public static <V> NamedExpression<V> of(String str, Class<V> cls) {
        return new NamedExpression<>(str, cls);
    }

    private NamedExpression(String str, Class<V> cls) {
        this.name = str;
        this.type = cls;
    }

    public String getName() {
        return this.name;
    }

    public Class<V> getClassType() {
        return this.type;
    }

    public ExpressionType getExpressionType() {
        return ExpressionType.NAME;
    }
}
