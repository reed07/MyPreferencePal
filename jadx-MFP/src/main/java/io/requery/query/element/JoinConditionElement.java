package io.requery.query.element;

import io.requery.query.Condition;
import io.requery.query.Expression;
import io.requery.query.JoinAndOr;
import io.requery.query.JoinOn;
import io.requery.query.Limit;
import io.requery.query.Offset;
import io.requery.query.Return;
import io.requery.query.WhereAndOr;
import java.util.Set;

public class JoinConditionElement<E> extends BaseLogicalElement<JoinConditionElement<E>, JoinAndOr<E>> implements JoinAndOr<E>, LogicalElement, QueryWrapper<E> {
    private final QueryElement<E> query;

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ Condition getCondition() {
        return super.getCondition();
    }

    public /* bridge */ /* synthetic */ LogicalOperator getOperator() {
        return super.getOperator();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    JoinConditionElement(QueryElement<E> queryElement, Set<JoinConditionElement<E>> set, Condition<?, ?> condition, LogicalOperator logicalOperator) {
        super(set, condition, logicalOperator);
        this.query = queryElement;
    }

    /* access modifiers changed from: 0000 */
    public JoinConditionElement<E> newElement(Set<JoinConditionElement<E>> set, Condition<?, ?> condition, LogicalOperator logicalOperator) {
        return new JoinConditionElement<>(this.query, set, condition, logicalOperator);
    }

    public QueryElement<E> unwrapQuery() {
        return this.query;
    }

    public E get() {
        return this.query.get();
    }

    public <J> JoinOn<E> join(Class<J> cls) {
        return this.query.join(cls);
    }

    public <V> Limit<E> orderBy(Expression<V> expression) {
        return this.query.orderBy(expression);
    }

    public Limit<E> orderBy(Expression<?>... expressionArr) {
        return this.query.orderBy(expressionArr);
    }

    public <V> WhereAndOr<E> where(Condition<V, ?> condition) {
        return this.query.where(condition);
    }

    public Offset<E> limit(int i) {
        return this.query.limit(i);
    }

    public Return<E> as(String str) {
        return this.query.as(str);
    }

    public String getAlias() {
        return this.query.getAlias();
    }
}
