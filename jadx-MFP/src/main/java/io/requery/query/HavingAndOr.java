package io.requery.query;

public interface HavingAndOr<E> extends AndOr<HavingAndOr<E>>, OrderByLimit<E> {
}
