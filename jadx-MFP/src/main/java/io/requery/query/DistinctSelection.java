package io.requery.query;

public interface DistinctSelection<E> extends From<E>, GroupBy<SetHavingOrderByLimit<E>>, Join<E>, OrderBy<Limit<E>>, Return<E>, SetOperation<Selectable<E>>, Where<E> {
}
