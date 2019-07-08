package io.requery.query.element;

import io.requery.query.Condition;
import io.requery.query.JoinAndOr;
import io.requery.query.JoinOn;
import io.requery.query.Return;
import io.requery.util.Objects;
import java.util.LinkedHashSet;
import java.util.Set;

public class JoinOnElement<E> implements JoinOn<E> {
    private final Set<JoinConditionElement<E>> conditions;
    private final JoinType joinType;
    private final QueryElement<E> query;
    private final Return<?> subQuery = null;
    private final String table;

    JoinOnElement(QueryElement<E> queryElement, String str, JoinType joinType2) {
        this.query = queryElement;
        this.table = str;
        this.joinType = joinType2;
        this.conditions = new LinkedHashSet();
    }

    public String tableName() {
        return this.table;
    }

    public Return<?> subQuery() {
        return this.subQuery;
    }

    public JoinType joinType() {
        return this.joinType;
    }

    public Set<JoinConditionElement<E>> conditions() {
        return this.conditions;
    }

    public <V> JoinAndOr<E> on(Condition<V, ?> condition) {
        JoinConditionElement joinConditionElement = new JoinConditionElement(this.query, this.conditions, condition, null);
        this.conditions.add(joinConditionElement);
        return joinConditionElement;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof JoinOnElement)) {
            return false;
        }
        JoinOnElement joinOnElement = (JoinOnElement) obj;
        if (Objects.equals(this.table, joinOnElement.table) && Objects.equals(this.joinType, joinOnElement.joinType) && Objects.equals(this.conditions, joinOnElement.conditions)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.table, this.joinType, this.conditions);
    }
}
