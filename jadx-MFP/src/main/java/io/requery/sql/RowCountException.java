package io.requery.sql;

import io.requery.PersistenceException;

public class RowCountException extends PersistenceException {
    private final long actual;
    private final long expected;

    RowCountException(long j, long j2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Expected ");
        sb.append(j);
        sb.append(" row affected actual ");
        sb.append(j2);
        super(sb.toString());
        this.expected = j;
        this.actual = j2;
    }
}
