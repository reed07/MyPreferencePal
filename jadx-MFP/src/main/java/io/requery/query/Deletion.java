package io.requery.query;

public interface Deletion<E> extends From<E>, GroupBy<SetHavingOrderByLimit<E>>, Join<E>, OrderBy<Limit<E>>, Return<E>, Where<E> {
}
