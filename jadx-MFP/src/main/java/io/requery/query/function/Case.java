package io.requery.query.function;

import io.requery.query.Condition;
import java.util.ArrayList;

public class Case<E> extends Function<E> {
    private final ArrayList<CaseCondition<?, ?>> conditions;
    private Object elseValue;

    public static class CaseCondition<V, W> {
        private final Condition<V, ?> condition;
        private final W then;

        public Condition<V, ?> condition() {
            return this.condition;
        }

        public W thenValue() {
            return this.then;
        }
    }

    public Object[] arguments() {
        return new Object[0];
    }

    public Object elseValue() {
        return this.elseValue;
    }

    public ArrayList<CaseCondition<?, ?>> conditions() {
        return this.conditions;
    }
}
