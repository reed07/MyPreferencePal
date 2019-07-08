package io.requery.query.element;

import java.util.Set;

public interface WhereElement {
    Set<WhereConditionElement<?>> getWhereElements();

    ExistsElement<?> getWhereExistsElement();
}
