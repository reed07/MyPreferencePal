package io.requery.android.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;

class SqliteStatement extends BaseStatement {
    protected final SqliteConnection connection;

    SqliteStatement(SqliteConnection sqliteConnection) {
        super(sqliteConnection);
        this.connection = sqliteConnection;
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
            io.requery.android.sqlite.SqliteConnection r1 = r3.connection     // Catch:{ SQLException -> 0x0027 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getDatabase()     // Catch:{ SQLException -> 0x0027 }
            android.database.sqlite.SQLiteStatement r0 = r1.compileStatement(r4)     // Catch:{ SQLException -> 0x0027 }
            r4 = 1
            if (r5 != r4) goto L_0x001f
            long r1 = r0.executeInsert()     // Catch:{ SQLException -> 0x0027 }
            io.requery.android.sqlite.SingleResultSet r5 = new io.requery.android.sqlite.SingleResultSet     // Catch:{ SQLException -> 0x0027 }
            r5.<init>(r3, r1)     // Catch:{ SQLException -> 0x0027 }
            r3.insertResult = r5     // Catch:{ SQLException -> 0x0027 }
            if (r0 == 0) goto L_0x001e
            r0.close()
        L_0x001e:
            return r4
        L_0x001f:
            r0.execute()     // Catch:{ SQLException -> 0x0027 }
            if (r0 == 0) goto L_0x0030
            goto L_0x002d
        L_0x0025:
            r4 = move-exception
            goto L_0x0032
        L_0x0027:
            r4 = move-exception
            io.requery.android.sqlite.SqliteConnection.throwSQLException(r4)     // Catch:{ all -> 0x0025 }
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
        throw new UnsupportedOperationException("Method not decompiled: io.requery.android.sqlite.SqliteStatement.execute(java.lang.String, int):boolean");
    }

    public ResultSet executeQuery(String str) throws SQLException {
        throwIfClosed();
        try {
            CursorResultSet cursorResultSet = new CursorResultSet(this, this.connection.getDatabase().rawQuery(str, null), true);
            this.queryResult = cursorResultSet;
            return cursorResultSet;
        } catch (android.database.SQLException e) {
            SqliteConnection.throwSQLException(e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (r0 == null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002d, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        return r3.updateCount;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0022, code lost:
        if (r0 != null) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int executeUpdate(java.lang.String r4, int r5) throws java.sql.SQLException {
        /*
            r3 = this;
            r0 = 0
            io.requery.android.sqlite.SqliteConnection r1 = r3.connection     // Catch:{ SQLException -> 0x0027 }
            android.database.sqlite.SQLiteDatabase r1 = r1.getDatabase()     // Catch:{ SQLException -> 0x0027 }
            android.database.sqlite.SQLiteStatement r0 = r1.compileStatement(r4)     // Catch:{ SQLException -> 0x0027 }
            r4 = 1
            if (r5 != r4) goto L_0x001c
            long r1 = r0.executeInsert()     // Catch:{ SQLException -> 0x0027 }
            io.requery.android.sqlite.SingleResultSet r5 = new io.requery.android.sqlite.SingleResultSet     // Catch:{ SQLException -> 0x0027 }
            r5.<init>(r3, r1)     // Catch:{ SQLException -> 0x0027 }
            r3.insertResult = r5     // Catch:{ SQLException -> 0x0027 }
            r3.updateCount = r4     // Catch:{ SQLException -> 0x0027 }
            goto L_0x0022
        L_0x001c:
            int r4 = r0.executeUpdateDelete()     // Catch:{ SQLException -> 0x0027 }
            r3.updateCount = r4     // Catch:{ SQLException -> 0x0027 }
        L_0x0022:
            if (r0 == 0) goto L_0x0030
            goto L_0x002d
        L_0x0025:
            r4 = move-exception
            goto L_0x0033
        L_0x0027:
            r4 = move-exception
            io.requery.android.sqlite.SqliteConnection.throwSQLException(r4)     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0030
        L_0x002d:
            r0.close()
        L_0x0030:
            int r4 = r3.updateCount
            return r4
        L_0x0033:
            if (r0 == 0) goto L_0x0038
            r0.close()
        L_0x0038:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.requery.android.sqlite.SqliteStatement.executeUpdate(java.lang.String, int):int");
    }
}
