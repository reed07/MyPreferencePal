package io.requery.sql.gen;

import io.requery.query.Expression;
import io.requery.query.ExpressionType;
import io.requery.query.Order;
import io.requery.query.OrderingExpression;
import io.requery.query.element.OrderByElement;
import io.requery.sql.Keyword;
import io.requery.sql.QueryBuilder;
import java.util.Set;

public class OrderByGenerator implements Generator<OrderByElement> {
    public void write(Output output, OrderByElement orderByElement) {
        Set<Expression> orderByExpressions = orderByElement.getOrderByExpressions();
        if (orderByExpressions != null && orderByExpressions.size() > 0) {
            QueryBuilder builder = output.builder();
            builder.keyword(Keyword.ORDER, Keyword.BY);
            int size = orderByExpressions.size();
            int i = 0;
            for (Expression expression : orderByExpressions) {
                if (expression.getExpressionType() == ExpressionType.ORDERING) {
                    OrderingExpression orderingExpression = (OrderingExpression) expression;
                    output.appendColumn(orderingExpression.getInnerExpression());
                    Keyword[] keywordArr = new Keyword[1];
                    keywordArr[0] = orderingExpression.getOrder() == Order.ASC ? Keyword.ASC : Keyword.DESC;
                    builder.keyword(keywordArr);
                    if (orderingExpression.getNullOrder() != null) {
                        builder.keyword(Keyword.NULLS);
                        switch (orderingExpression.getNullOrder()) {
                            case FIRST:
                                builder.keyword(Keyword.FIRST);
                                break;
                            case LAST:
                                builder.keyword(Keyword.LAST);
                                break;
                        }
                    }
                } else {
                    output.appendColumn(expression);
                }
                if (i < size - 1) {
                    builder.append(",");
                }
                i++;
            }
        }
    }
}
