package io.requery.query;

public interface Expression<V> {
    Class<V> getClassType();

    ExpressionType getExpressionType();

    Expression<V> getInnerExpression();

    String getName();
}
