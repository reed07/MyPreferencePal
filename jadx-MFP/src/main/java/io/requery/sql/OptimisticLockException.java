package io.requery.sql;

import io.requery.PersistenceException;

public class OptimisticLockException extends PersistenceException {
    private final Object entity;

    OptimisticLockException(Object obj, Object obj2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Couldn't update (");
        sb.append(obj);
        sb.append(") with version ");
        sb.append(obj2);
        sb.append(" entity may have been modified or deleted");
        super(sb.toString());
        this.entity = obj;
    }
}
