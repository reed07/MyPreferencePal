package io.requery.query;

import io.requery.meta.QueryExpression;
import io.requery.query.OrderingExpression.NullOrder;
import io.requery.query.function.Substr;
import io.requery.query.function.Sum;
import io.requery.util.Objects;
import java.util.Collection;

public abstract class FieldExpression<V> implements QueryExpression<V> {

    private static class ExpressionCondition<L, R> implements LogicalCondition<L, R> {
        private final L leftOperand;
        private final Operator operator;
        private final R rightOperand;

        ExpressionCondition(L l, Operator operator2, R r) {
            this.leftOperand = l;
            this.operator = operator2;
            this.rightOperand = r;
        }

        public <V> LogicalCondition<LogicalCondition<L, R>, Condition<?, ?>> and(Condition<V, ?> condition) {
            return new ExpressionCondition(this, Operator.AND, condition);
        }

        public <V> LogicalCondition<LogicalCondition<L, R>, Condition<?, ?>> or(Condition<V, ?> condition) {
            return new ExpressionCondition(this, Operator.OR, condition);
        }

        public Operator getOperator() {
            return this.operator;
        }

        public R getRightOperand() {
            return this.rightOperand;
        }

        public L getLeftOperand() {
            return this.leftOperand;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof ExpressionCondition)) {
                return false;
            }
            ExpressionCondition expressionCondition = (ExpressionCondition) obj;
            if (Objects.equals(this.leftOperand, expressionCondition.leftOperand) && Objects.equals(this.operator, expressionCondition.operator) && Objects.equals(this.rightOperand, expressionCondition.rightOperand)) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return Objects.hash(this.leftOperand, this.rightOperand, this.operator);
        }
    }

    private static class OrderExpression<X> implements OrderingExpression<X> {
        private final Expression<X> expression;
        private NullOrder nullOrder;
        private final Order order;

        OrderExpression(Expression<X> expression2, Order order2) {
            this.expression = expression2;
            this.order = order2;
        }

        public Order getOrder() {
            return this.order;
        }

        public NullOrder getNullOrder() {
            return this.nullOrder;
        }

        public String getName() {
            return this.expression.getName();
        }

        public Class<X> getClassType() {
            return this.expression.getClassType();
        }

        public ExpressionType getExpressionType() {
            return ExpressionType.ORDERING;
        }

        public Expression<X> getInnerExpression() {
            return this.expression;
        }
    }

    public String getAlias() {
        return null;
    }

    public abstract Class<V> getClassType();

    public Expression<V> getInnerExpression() {
        return null;
    }

    public abstract String getName();

    protected FieldExpression() {
    }

    public FieldExpression<V> as(String str) {
        return new AliasedExpression(this, str);
    }

    public OrderingExpression<V> asc() {
        return new OrderExpression(this, Order.ASC);
    }

    public OrderingExpression<V> desc() {
        return new OrderExpression(this, Order.DESC);
    }

    public Sum<V> sum() {
        return Sum.sum(this);
    }

    public Substr<V> substr(int i, int i2) {
        return Substr.substr(this, i, i2);
    }

    public LogicalCondition<? extends Expression<V>, V> equal(V v) {
        if (v == null) {
            return isNull();
        }
        return new ExpressionCondition(this, Operator.EQUAL, v);
    }

    public LogicalCondition<? extends Expression<V>, V> notEqual(V v) {
        Objects.requireNotNull(v);
        return new ExpressionCondition(this, Operator.NOT_EQUAL, v);
    }

    public LogicalCondition<? extends Expression<V>, V> lessThan(V v) {
        Objects.requireNotNull(v);
        return new ExpressionCondition(this, Operator.LESS_THAN, v);
    }

    public LogicalCondition<? extends Expression<V>, ? extends Expression<V>> equal(Expression<V> expression) {
        return new ExpressionCondition(this, Operator.EQUAL, expression);
    }

    public LogicalCondition<? extends Expression<V>, V> eq(V v) {
        return equal(v);
    }

    public LogicalCondition<? extends Expression<V>, V> ne(V v) {
        return notEqual(v);
    }

    public LogicalCondition<? extends Expression<V>, ? extends Expression<V>> eq(Expression<V> expression) {
        return equal(expression);
    }

    public LogicalCondition<? extends Expression<V>, Collection<V>> in(Collection<V> collection) {
        Objects.requireNotNull(collection);
        return new ExpressionCondition(this, Operator.IN, collection);
    }

    public LogicalCondition<? extends Expression<V>, V> isNull() {
        return new ExpressionCondition(this, Operator.IS_NULL, null);
    }

    public LogicalCondition<? extends Expression<V>, V> notNull() {
        return new ExpressionCondition(this, Operator.NOT_NULL, null);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldExpression)) {
            return false;
        }
        FieldExpression fieldExpression = (FieldExpression) obj;
        if (!Objects.equals(getName(), fieldExpression.getName()) || !Objects.equals(getClassType(), fieldExpression.getClassType()) || !Objects.equals(getAlias(), fieldExpression.getAlias())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(getName(), getClassType(), getAlias());
    }
}
