package io.requery.sql;

import java.sql.Connection;
import java.sql.SQLException;

class UncloseableConnection extends ConnectionDelegate {
    public void close() throws SQLException {
    }

    UncloseableConnection(Connection connection) {
        super(connection);
    }
}
