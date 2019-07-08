package io.requery.query;

import java.util.Collection;

public class RowExpression extends FieldExpression<Collection<?>> {
    private Collection<? extends Expression<?>> expressions;

    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        int i = 0;
        for (Object next : this.expressions) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(next);
            i++;
        }
        sb.append(")");
        return sb.toString();
    }

    public Class<Collection<?>> getClassType() {
        return this.expressions.getClass();
    }

    public ExpressionType getExpressionType() {
        return ExpressionType.ROW;
    }

    public Collection<? extends Expression<?>> getExpressions() {
        return this.expressions;
    }
}
