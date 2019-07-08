package io.requery.sql;

import io.requery.query.Expression;
import io.requery.util.CloseableIterator;
import io.requery.util.IndexAccessible;
import io.requery.util.Objects;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Wrapper;
import java.util.NoSuchElementException;
import java.util.Set;

public class ResultSetIterator<E> implements CloseableIterator<E>, IndexAccessible<E>, Wrapper {
    private boolean advanced;
    private final boolean closeConnection;
    private final boolean closeStatement;
    private boolean closed;
    private int position;
    private final ResultReader<E> reader;
    private final ResultSet results;
    private final Set<? extends Expression<?>> selection;

    ResultSetIterator(ResultReader<E> resultReader, ResultSet resultSet, Set<? extends Expression<?>> set, boolean z, boolean z2) {
        this.reader = (ResultReader) Objects.requireNotNull(resultReader);
        this.results = (ResultSet) Objects.requireNotNull(resultSet);
        this.selection = set;
        this.closeStatement = z;
        this.closeConnection = z2;
    }

    public boolean hasNext() {
        if (this.closed) {
            return false;
        }
        if (this.advanced) {
            return true;
        }
        try {
            if (this.results.next()) {
                this.advanced = true;
                return true;
            }
            close();
            return false;
        } catch (SQLException unused) {
            return false;
        }
    }

    public E next() {
        if (!this.closed) {
            try {
                if (!this.advanced) {
                    if (!this.results.next()) {
                        this.advanced = false;
                        close();
                        throw new NoSuchElementException();
                    }
                }
                E read = this.reader.read(this.results, this.selection);
                this.position++;
                this.advanced = false;
                return read;
            } catch (SQLException e) {
                NoSuchElementException noSuchElementException = new NoSuchElementException();
                noSuchElementException.initCause(e);
                throw noSuchElementException;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public void remove() {
        try {
            if (this.results.isBeforeFirst()) {
                throw new IllegalStateException();
            } else if (this.results.getConcurrency() == 1008) {
                this.results.deleteRow();
            } else {
                throw new UnsupportedOperationException();
            }
        } catch (SQLFeatureNotSupportedException e) {
            throw new UnsupportedOperationException(e);
        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x001f */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x001b A[SYNTHETIC, Splitter:B:15:0x001b] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r4 = this;
            java.sql.ResultSet r0 = r4.results
            monitor-enter(r0)
            boolean r1 = r4.closed     // Catch:{ all -> 0x002e }
            if (r1 != 0) goto L_0x002c
            boolean r1 = r4.closeStatement     // Catch:{ all -> 0x002e }
            r2 = 0
            if (r1 == 0) goto L_0x0013
            java.sql.ResultSet r1 = r4.results     // Catch:{ SQLException -> 0x0013 }
            java.sql.Statement r1 = r1.getStatement()     // Catch:{ SQLException -> 0x0013 }
            goto L_0x0014
        L_0x0013:
            r1 = r2
        L_0x0014:
            java.sql.ResultSet r3 = r4.results     // Catch:{ all -> 0x002e }
            closeSuppressed(r3)     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0029
            java.sql.Connection r2 = r1.getConnection()     // Catch:{ SQLException -> 0x001f }
        L_0x001f:
            closeSuppressed(r1)     // Catch:{ all -> 0x002e }
            boolean r1 = r4.closeConnection     // Catch:{ all -> 0x002e }
            if (r1 == 0) goto L_0x0029
            closeSuppressed(r2)     // Catch:{ all -> 0x002e }
        L_0x0029:
            r1 = 1
            r4.closed = r1     // Catch:{ all -> 0x002e }
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return
        L_0x002e:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.requery.sql.ResultSetIterator.close():void");
    }

    private static void closeSuppressed(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception unused) {
            }
        }
    }

    public <T> T unwrap(Class<T> cls) throws SQLException {
        return this.results.unwrap(cls);
    }

    public boolean isWrapperFor(Class<?> cls) throws SQLException {
        return this.results.isWrapperFor(cls);
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof ResultSetIterator)) {
            return false;
        }
        if (((ResultSetIterator) obj).results == this.results) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hash(this.results);
    }
}
