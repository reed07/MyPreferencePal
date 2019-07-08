package io.requery.android.sqlcipher;

import io.requery.android.sqlite.DatabaseProvider;
import java.sql.Connection;
import java.sql.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

public class SqlCipherDatabaseSource extends SQLiteOpenHelper implements DatabaseProvider<SQLiteDatabase> {
    private SQLiteDatabase db;
    private final String password;

    private Connection getConnection(SQLiteDatabase sQLiteDatabase) throws SQLException {
        SqlCipherConnection sqlCipherConnection;
        synchronized (this) {
            sqlCipherConnection = new SqlCipherConnection(sQLiteDatabase);
        }
        return sqlCipherConnection;
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        synchronized (this) {
            if (this.db == null) {
                this.db = getWritableDatabase(this.password);
            }
            connection = getConnection(this.db);
        }
        return connection;
    }
}
