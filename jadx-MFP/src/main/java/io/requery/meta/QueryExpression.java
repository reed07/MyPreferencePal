package io.requery.meta;

import io.requery.query.Aliasable;
import io.requery.query.Conditional;
import io.requery.query.Expression;
import io.requery.query.Functional;
import io.requery.query.LogicalCondition;

public interface QueryExpression<V> extends Aliasable<QueryExpression<V>>, Conditional<LogicalCondition<? extends Expression<V>, ?>, V>, Expression<V>, Functional<V> {
}
