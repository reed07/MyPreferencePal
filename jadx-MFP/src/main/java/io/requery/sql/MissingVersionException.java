package io.requery.sql;

import io.requery.PersistenceException;
import io.requery.proxy.EntityProxy;

public class MissingVersionException extends PersistenceException {
    private final EntityProxy proxy;

    MissingVersionException(EntityProxy entityProxy) {
        this.proxy = entityProxy;
    }
}
