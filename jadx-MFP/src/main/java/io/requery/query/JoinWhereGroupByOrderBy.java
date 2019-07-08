package io.requery.query;

public interface JoinWhereGroupByOrderBy<E> extends GroupBy<SetHavingOrderByLimit<E>>, Join<E>, OrderBy<Limit<E>>, Return<E>, Where<E> {
}
