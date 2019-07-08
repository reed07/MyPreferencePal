package io.requery.query.element;

import io.requery.query.Expression;
import java.util.Set;

public interface OrderByElement {
    Set<Expression<?>> getOrderByExpressions();
}
