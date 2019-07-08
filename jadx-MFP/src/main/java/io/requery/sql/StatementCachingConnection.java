package io.requery.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class StatementCachingConnection extends ConnectionDelegate {
    private final PreparedStatementCache statementCache;

    StatementCachingConnection(PreparedStatementCache preparedStatementCache, Connection connection) {
        super(connection);
        this.statementCache = preparedStatementCache;
    }

    public PreparedStatement prepareStatement(String str, int i, int i2) throws SQLException {
        return prepareStatement(str, i, i2, getHoldability());
    }

    public PreparedStatement prepareStatement(String str, int i, int i2, int i3) throws SQLException {
        PreparedStatement preparedStatement = this.statementCache.get(str);
        if (preparedStatement != null && preparedStatement.getResultSetType() == i && preparedStatement.getResultSetConcurrency() == i2 && preparedStatement.getResultSetHoldability() == i3) {
            return preparedStatement;
        }
        return this.statementCache.put(str, super.prepareStatement(str, i, i2, i3));
    }
}
