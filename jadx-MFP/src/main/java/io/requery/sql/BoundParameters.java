package io.requery.sql;

import io.requery.query.Expression;
import io.requery.util.Objects;
import java.util.ArrayList;

public class BoundParameters {
    private final ArrayList<Expression<?>> expressions = new ArrayList<>();
    private final ArrayList<Object> values = new ArrayList<>();

    public <V> void add(Expression<V> expression, V v) {
        this.expressions.add(expression);
        this.values.add(v);
    }

    /* access modifiers changed from: 0000 */
    public Expression<?> expressionAt(int i) {
        return (Expression) this.expressions.get(i);
    }

    /* access modifiers changed from: 0000 */
    public Object valueAt(int i) {
        return this.values.get(i);
    }

    public int count() {
        return this.expressions.size();
    }

    public boolean isEmpty() {
        return count() == 0;
    }

    public void addAll(BoundParameters boundParameters) {
        this.expressions.addAll(boundParameters.expressions);
        this.values.addAll(boundParameters.values);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BoundParameters)) {
            return false;
        }
        return Objects.equals(this.values, ((BoundParameters) obj).values);
    }

    public int hashCode() {
        return Objects.hash(this.values);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.values.size(); i++) {
            Object valueAt = valueAt(i);
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(String.valueOf(valueAt));
        }
        sb.append("]");
        return sb.toString();
    }
}
