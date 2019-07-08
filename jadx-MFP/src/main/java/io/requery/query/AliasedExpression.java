package io.requery.query;

public class AliasedExpression<V> extends FieldExpression<V> {
    private final String alias;
    private final Expression<V> expression;
    private final String name;

    public AliasedExpression(Expression<V> expression2, String str) {
        this(expression2, expression2.getName(), str);
    }

    public AliasedExpression(Expression<V> expression2, String str, String str2) {
        this.expression = expression2;
        this.alias = str2;
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public Class<V> getClassType() {
        return this.expression.getClassType();
    }

    public ExpressionType getExpressionType() {
        return ExpressionType.ALIAS;
    }

    public String getAlias() {
        return this.alias;
    }

    public Expression<V> getInnerExpression() {
        return this.expression;
    }
}
