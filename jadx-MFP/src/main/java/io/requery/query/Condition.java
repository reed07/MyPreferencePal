package io.requery.query;

public interface Condition<L, R> {
    L getLeftOperand();

    Operator getOperator();

    R getRightOperand();
}
