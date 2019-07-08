package io.requery.query.element;

import io.requery.query.AndOr;
import io.requery.query.Condition;
import io.requery.util.Objects;
import java.util.Set;

abstract class BaseLogicalElement<E extends S, S> implements AndOr<S>, LogicalElement {
    private final Condition<?, ?> condition;
    private final Set<E> elements;
    private final LogicalOperator operator;

    /* access modifiers changed from: 0000 */
    public abstract E newElement(Set<E> set, Condition<?, ?> condition2, LogicalOperator logicalOperator);

    BaseLogicalElement(Set<E> set, Condition<?, ?> condition2, LogicalOperator logicalOperator) {
        this.elements = set;
        this.condition = condition2;
        this.operator = logicalOperator;
    }

    public <V> S and(Condition<V, ?> condition2) {
        S newElement = newElement(this.elements, condition2, LogicalOperator.AND);
        this.elements.add(newElement);
        return newElement;
    }

    public <V> S or(Condition<V, ?> condition2) {
        S newElement = newElement(this.elements, condition2, LogicalOperator.OR);
        this.elements.add(newElement);
        return newElement;
    }

    public Condition<?, ?> getCondition() {
        return this.condition;
    }

    public LogicalOperator getOperator() {
        return this.operator;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof BaseLogicalElement)) {
            return false;
        }
        BaseLogicalElement baseLogicalElement = (BaseLogicalElement) obj;
        if (Objects.equals(this.operator, baseLogicalElement.operator) && Objects.equals(this.condition, baseLogicalElement.condition)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.operator, this.condition);
    }
}
