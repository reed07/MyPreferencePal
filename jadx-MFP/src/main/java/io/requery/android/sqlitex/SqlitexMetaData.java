package io.requery.android.sqlitex;

import android.database.Cursor;
import io.requery.android.database.sqlite.SQLiteDatabase;
import io.requery.android.sqlite.BaseConnection;
import io.requery.android.sqlite.SqliteMetaData;
import io.requery.util.function.Function;
import java.sql.SQLException;

class SqlitexMetaData extends SqliteMetaData {
    SqlitexMetaData(BaseConnection baseConnection) {
        super(baseConnection);
    }

    /* access modifiers changed from: protected */
    public <R> R queryMemory(Function<Cursor, R> function, String str) throws SQLException {
        try {
            SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
            return function.apply(closeWithCursor(openOrCreateDatabase, openOrCreateDatabase.rawQuery(str, null)));
        } catch (android.database.SQLException e) {
            throw new SQLException(e);
        }
    }
}
