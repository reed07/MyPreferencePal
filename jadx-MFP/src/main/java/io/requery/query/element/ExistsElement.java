package io.requery.query.element;

import io.requery.query.Exists;
import io.requery.query.Return;
import io.requery.util.Objects;

public class ExistsElement<E> implements Exists<E> {
    private boolean notExists;
    private final E parent;
    private Return<?> subQuery;

    public Return<?> getQuery() {
        return this.subQuery;
    }

    public boolean isNotExists() {
        return this.notExists;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof ExistsElement)) {
            return false;
        }
        ExistsElement existsElement = (ExistsElement) obj;
        if (this.parent == existsElement.parent && this.notExists == existsElement.notExists) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.parent, Boolean.valueOf(this.notExists));
    }
}
