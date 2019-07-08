package io.requery.sql.platform;

import io.requery.PersistenceException;
import io.requery.sql.Platform;
import io.requery.util.function.Function;
import java.sql.Connection;
import java.sql.SQLException;

class PlatformFromConnection implements Function<Connection, Platform> {
    PlatformFromConnection() {
    }

    public Platform apply(Connection connection) {
        try {
            String databaseProductName = connection.getMetaData().getDatabaseProductName();
            if (databaseProductName.contains("PostgreSQL")) {
                return new PostgresSQL();
            }
            if (databaseProductName.contains("SQLite")) {
                return new SQLite();
            }
            if (databaseProductName.contains("MySQL")) {
                return new MySQL();
            }
            if (databaseProductName.contains("H2")) {
                return new H2();
            }
            if (databaseProductName.contains("HSQL Database Engine")) {
                return new HSQL();
            }
            if (databaseProductName.contains("Apache Derby")) {
                return new Derby();
            }
            if (databaseProductName.contains("Oracle")) {
                return new Oracle();
            }
            if (databaseProductName.contains("Microsoft SQL Server")) {
                return new SQLServer();
            }
            return new Generic();
        } catch (SQLException e) {
            throw new PersistenceException((Throwable) e);
        }
    }
}
