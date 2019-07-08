package io.requery.query;

public interface Join<E> {
    <J> JoinOn<E> join(Class<J> cls);
}
