package io.requery.sql;

import io.requery.PersistenceException;

public class CircularReferenceException extends PersistenceException {
    CircularReferenceException(String str) {
        super(str);
    }
}
