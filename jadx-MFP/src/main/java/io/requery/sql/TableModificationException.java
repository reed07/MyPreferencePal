package io.requery.sql;

import io.requery.PersistenceException;

public class TableModificationException extends PersistenceException {
    public TableModificationException(Throwable th) {
        super(th);
    }
}
