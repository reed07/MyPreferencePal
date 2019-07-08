package io.requery.android.sqlcipher;

import io.requery.android.sqlite.BaseStatement;
import io.requery.android.sqlite.CursorResultSet;
import io.requery.android.sqlite.SingleResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.sqlcipher.database.SQLiteException;
import net.sqlcipher.database.SQLiteStatement;

class SqlCipherStatement extends BaseStatement {
    protected final SqlCipherConnection connection;

    SqlCipherStatement(SqlCipherConnection sqlCipherConnection) {
        super(sqlCipherConnection);
        this.connection = sqlCipherConnection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        if (r0 != null) goto L_0x002d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        if (r0 == null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean execute(java.lang.String r4, int r5) throws java.sql.SQLException {
        /*
            r3 = this;
            r0 = 0
            io.requery.android.sqlcipher.SqlCipherConnection r1 = r3.connection     // Catch:{ SQLiteException -> 0x0027 }
            net.sqlcipher.database.SQLiteDatabase r1 = r1.getDatabase()     // Catch:{ SQLiteException -> 0x0027 }
            net.sqlcipher.database.SQLiteStatement r0 = r1.compileStatement(r4)     // Catch:{ SQLiteException -> 0x0027 }
            r4 = 1
            if (r5 != r4) goto L_0x001f
            long r1 = r0.executeInsert()     // Catch:{ SQLiteException -> 0x0027 }
            io.requery.android.sqlite.SingleResultSet r5 = new io.requery.android.sqlite.SingleResultSet     // Catch:{ SQLiteException -> 0x0027 }
            r5.<init>(r3, r1)     // Catch:{ SQLiteException -> 0x0027 }
            r3.insertResult = r5     // Catch:{ SQLiteException -> 0x0027 }
            if (r0 == 0) goto L_0x001e
            r0.close()
        L_0x001e:
            return r4
        L_0x001f:
            r0.execute()     // Catch:{ SQLiteException -> 0x0027 }
            if (r0 == 0) goto L_0x0030
            goto L_0x002d
        L_0x0025:
            r4 = move-exception
            goto L_0x0032
        L_0x0027:
            r4 = move-exception
            io.requery.android.sqlcipher.SqlCipherConnection.throwSQLException(r4)     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0030
        L_0x002d:
            r0.close()
        L_0x0030:
            r4 = 0
            return r4
        L_0x0032:
            if (r0 == 0) goto L_0x0037
            r0.close()
        L_0x0037:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.requery.android.sqlcipher.SqlCipherStatement.execute(java.lang.String, int):boolean");
    }

    public ResultSet executeQuery(String str) throws SQLException {
        try {
            CursorResultSet cursorResultSet = new CursorResultSet(this, this.connection.getDatabase().rawQuery(str, null), true);
            this.queryResult = cursorResultSet;
            return cursorResultSet;
        } catch (SQLiteException e) {
            SqlCipherConnection.throwSQLException(e);
            return null;
        }
    }

    public int executeUpdate(String str, int i) throws SQLException {
        SQLiteStatement sQLiteStatement = null;
        try {
            sQLiteStatement = this.connection.getDatabase().compileStatement(str);
            if (i == 1) {
                this.insertResult = new SingleResultSet(this, sQLiteStatement.executeInsert());
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                return 1;
            }
            int executeUpdateDelete = sQLiteStatement.executeUpdateDelete();
            this.updateCount = executeUpdateDelete;
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            return executeUpdateDelete;
        } catch (SQLiteException e) {
            SqlCipherConnection.throwSQLException(e);
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            return 0;
        } catch (Throwable th) {
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
            throw th;
        }
    }
}
