package io.requery.query;

public interface JoinAndOr<E> extends AndOr<JoinAndOr<E>>, JoinWhereGroupByOrderBy<E> {
}
