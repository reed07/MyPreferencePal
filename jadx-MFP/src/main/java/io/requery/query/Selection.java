package io.requery.query;

public interface Selection<E> extends Distinct<DistinctSelection<E>>, From<E>, GroupBy<SetHavingOrderByLimit<E>>, Join<E>, OrderBy<Limit<E>>, Return<E>, SetOperation<Selectable<E>>, Where<E> {
}
