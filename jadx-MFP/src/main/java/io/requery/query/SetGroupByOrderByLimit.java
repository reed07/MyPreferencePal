package io.requery.query;

public interface SetGroupByOrderByLimit<E> extends GroupBy<SetHavingOrderByLimit<E>>, Limit<E>, OrderBy<Limit<E>>, SetOperation<Selectable<E>> {
}
