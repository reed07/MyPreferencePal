package io.requery.query.element;

import io.requery.query.Expression;
import java.util.Set;

public interface SelectionElement {
    Set<? extends Expression<?>> getSelection();

    boolean isDistinct();
}
