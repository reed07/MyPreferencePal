package io.requery.android.sqlitex;

import io.requery.android.database.sqlite.SQLiteDatabase;
import io.requery.android.database.sqlite.SQLiteOpenHelper;
import io.requery.android.sqlite.DatabaseProvider;
import java.sql.Connection;
import java.sql.SQLException;

public class SqlitexDatabaseSource extends SQLiteOpenHelper implements DatabaseProvider<SQLiteDatabase> {
    private SQLiteDatabase db;

    private Connection getConnection(SQLiteDatabase sQLiteDatabase) throws SQLException {
        SqlitexConnection sqlitexConnection;
        synchronized (this) {
            sqlitexConnection = new SqlitexConnection(sQLiteDatabase);
        }
        return sqlitexConnection;
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        synchronized (this) {
            if (this.db == null) {
                this.db = getWritableDatabase();
            }
            connection = getConnection(this.db);
        }
        return connection;
    }
}
