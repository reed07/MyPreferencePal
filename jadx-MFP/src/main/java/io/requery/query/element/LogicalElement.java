package io.requery.query.element;

import io.requery.query.Condition;

public interface LogicalElement {
    Condition<?, ?> getCondition();

    LogicalOperator getOperator();
}
