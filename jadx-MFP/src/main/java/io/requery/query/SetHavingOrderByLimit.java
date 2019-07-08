package io.requery.query;

public interface SetHavingOrderByLimit<E> extends Having<E>, Limit<E>, OrderBy<Limit<E>>, SetOperation<Selectable<E>> {
}
