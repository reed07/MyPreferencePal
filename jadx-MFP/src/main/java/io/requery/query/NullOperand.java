package io.requery.query;

import io.requery.util.Objects;

public class NullOperand<L, R> implements Condition<L, R> {
    public L getLeftOperand() {
        return null;
    }

    public Operator getOperator() {
        return null;
    }

    public R getRightOperand() {
        return null;
    }

    public boolean equals(Object obj) {
        return obj == this || (obj instanceof NullOperand);
    }

    public int hashCode() {
        return Objects.hash(getClass());
    }
}
