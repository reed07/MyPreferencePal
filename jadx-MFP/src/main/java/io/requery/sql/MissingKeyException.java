package io.requery.sql;

import io.requery.PersistenceException;
import io.requery.proxy.EntityProxy;

public class MissingKeyException extends PersistenceException {
    private EntityProxy proxy;

    MissingKeyException() {
    }

    MissingKeyException(EntityProxy entityProxy) {
        super("No key in provided entity");
        this.proxy = entityProxy;
    }
}
