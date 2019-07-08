package io.requery.android.sqlcipher;

import io.requery.android.sqlite.BaseConnection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import net.sqlcipher.database.SQLiteConstraintException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;

class SqlCipherConnection extends BaseConnection {
    private final SQLiteDatabase db;
    private boolean enteredTransaction;
    private final SqlCipherMetaData metaData;

    SqlCipherConnection(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            this.db = sQLiteDatabase;
            this.autoCommit = true;
            this.metaData = new SqlCipherMetaData(this);
            return;
        }
        throw new IllegalArgumentException("null db");
    }

    static void throwSQLException(SQLiteException sQLiteException) throws SQLException {
        if (sQLiteException instanceof SQLiteConstraintException) {
            throw new SQLIntegrityConstraintViolationException(sQLiteException);
        }
        throw new SQLException(sQLiteException);
    }

    /* access modifiers changed from: 0000 */
    public SQLiteDatabase getDatabase() {
        return this.db;
    }

    /* access modifiers changed from: protected */
    public void ensureTransaction() {
        if (!this.autoCommit && !this.db.inTransaction()) {
            this.db.beginTransaction();
            this.enteredTransaction = true;
        }
    }

    /* access modifiers changed from: protected */
    public void execSQL(String str) throws SQLException {
        try {
            this.db.execSQL(str);
        } catch (SQLiteException e) {
            throwSQLException(e);
        }
    }

    public void commit() throws SQLException {
        if (this.autoCommit) {
            throw new SQLException("commit called while in autoCommit mode");
        } else if (this.db.inTransaction() && this.enteredTransaction) {
            try {
                this.db.setTransactionSuccessful();
                this.db.endTransaction();
                this.enteredTransaction = false;
            } catch (IllegalStateException e) {
                throw new SQLException(e);
            } catch (Throwable th) {
                this.db.endTransaction();
                this.enteredTransaction = false;
                throw th;
            }
        }
    }

    public Statement createStatement() throws SQLException {
        return new SqlCipherStatement(this);
    }

    public Statement createStatement(int i, int i2) throws SQLException {
        return createStatement(i, i2, 1);
    }

    public Statement createStatement(int i, int i2, int i3) throws SQLException {
        if (i2 != 1008) {
            return new SqlCipherStatement(this);
        }
        throw new SQLFeatureNotSupportedException("CONCUR_UPDATABLE not supported");
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return this.metaData;
    }

    public boolean isClosed() throws SQLException {
        return !this.db.isOpen();
    }

    public boolean isReadOnly() throws SQLException {
        return this.db.isReadOnly();
    }

    public PreparedStatement prepareStatement(String str, int i) throws SQLException {
        return new SqlCipherPreparedStatement(this, str, i);
    }

    public PreparedStatement prepareStatement(String str, int i, int i2, int i3) throws SQLException {
        return new SqlCipherPreparedStatement(this, str, 2);
    }

    public PreparedStatement prepareStatement(String str, String[] strArr) throws SQLException {
        return new SqlCipherPreparedStatement(this, str, 1);
    }

    public void rollback() throws SQLException {
        if (!this.autoCommit) {
            this.db.endTransaction();
            return;
        }
        throw new SQLException("commit called while in autoCommit mode");
    }
}
