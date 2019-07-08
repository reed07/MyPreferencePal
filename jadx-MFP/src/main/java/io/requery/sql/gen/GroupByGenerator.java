package io.requery.sql.gen;

import io.requery.query.Expression;
import io.requery.query.element.GroupByElement;
import io.requery.query.element.HavingConditionElement;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import io.requery.sql.QueryBuilder.Appender;
import java.util.Set;

class GroupByGenerator implements Generator<GroupByElement> {
    GroupByGenerator() {
    }

    public void write(final Output output, GroupByElement groupByElement) {
        QueryBuilder builder = output.builder();
        Set groupByExpressions = groupByElement.getGroupByExpressions();
        if (groupByExpressions != null && groupByExpressions.size() > 0) {
            builder.keyword(Keyword.GROUP, Keyword.BY);
            builder.commaSeparated((Iterable<? extends T>) groupByExpressions, (Appender<T>) new Appender<Expression<?>>() {
                public void append(QueryBuilder queryBuilder, Expression<?> expression) {
                    output.appendColumn(expression);
                }
            });
            if (groupByElement.getHavingElements() != null) {
                builder.keyword(Keyword.HAVING);
                for (HavingConditionElement appendConditional : groupByElement.getHavingElements()) {
                    output.appendConditional(appendConditional);
                }
            }
        }
    }
}
