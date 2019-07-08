package io.requery.android.sqlcipher;

import android.database.Cursor;
import io.requery.android.sqlite.BaseConnection;
import io.requery.android.sqlite.SqliteMetaData;
import io.requery.util.function.Function;
import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;

class SqlCipherMetaData extends SqliteMetaData {
    SqlCipherMetaData(BaseConnection baseConnection) {
        super(baseConnection);
    }

    /* access modifiers changed from: protected */
    public <R> R queryMemory(Function<Cursor, R> function, String str) throws SQLException {
        try {
            final SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(":memory:", "", null);
            return function.apply(closeWithCursor(new Closeable() {
                public void close() throws IOException {
                    openOrCreateDatabase.close();
                }
            }, openOrCreateDatabase.rawQuery(str, null)));
        } catch (SQLiteException e) {
            throw new SQLException(e);
        }
    }
}
