package io.requery.sql.gen;

import io.requery.query.Expression;
import io.requery.query.Operator;
import io.requery.query.element.LogicalElement;
import io.requery.query.element.QueryWrapper;
import io.requery.sql.BoundParameters;
import io.requery.sql.QueryBuilder;

public interface Output {
    void appendColumn(Expression<?> expression);

    void appendColumnForSelect(Expression<?> expression);

    void appendConditionValue(Expression expression, Object obj);

    void appendConditional(LogicalElement logicalElement);

    void appendOperator(Operator operator);

    void appendQuery(QueryWrapper<?> queryWrapper);

    void appendTables();

    QueryBuilder builder();

    BoundParameters parameters();
}
