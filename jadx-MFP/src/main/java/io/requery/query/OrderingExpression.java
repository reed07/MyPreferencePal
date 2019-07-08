package io.requery.query;

public interface OrderingExpression<V> extends Expression<V> {

    public enum NullOrder {
        FIRST,
        LAST
    }

    Expression<V> getInnerExpression();

    NullOrder getNullOrder();

    Order getOrder();
}
