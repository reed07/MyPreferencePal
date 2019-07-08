package io.requery.query.element;

public interface SetOperationElement {
    QueryElement<?> getInnerSetQuery();

    SetOperator getOperator();
}
