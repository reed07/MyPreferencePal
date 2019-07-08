package io.requery.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

class PreparedStatementCache implements AutoCloseable {
    private boolean closed;
    /* access modifiers changed from: private */
    public final LinkedHashMap<String, PreparedStatement> elements;

    private static class CachedStatement extends PreparedStatementDelegate {
        private final PreparedStatementCache cache;
        private final String sql;
        private final PreparedStatement statement;

        CachedStatement(PreparedStatementCache preparedStatementCache, String str, PreparedStatement preparedStatement) {
            super(preparedStatement);
            this.cache = preparedStatementCache;
            this.sql = str;
            this.statement = preparedStatement;
        }

        /* access modifiers changed from: 0000 */
        public void closeDelegate() throws SQLException {
            this.statement.close();
        }

        public void close() throws SQLException {
            this.cache.put(this.sql, this);
        }
    }

    PreparedStatementCache(int i) {
        final int i2 = i;
        AnonymousClass1 r0 = new LinkedHashMap<String, PreparedStatement>(i, 0.75f, true) {
            /* access modifiers changed from: protected */
            public boolean removeEldestEntry(Entry entry) {
                synchronized (PreparedStatementCache.this.elements) {
                    if (PreparedStatementCache.this.elements.size() <= i2) {
                        return false;
                    }
                    PreparedStatementCache.this.closeStatement((PreparedStatement) entry.getValue());
                    return true;
                }
            }
        };
        this.elements = r0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.sql.PreparedStatement get(java.lang.String r4) throws java.sql.SQLException {
        /*
            r3 = this;
            java.util.LinkedHashMap<java.lang.String, java.sql.PreparedStatement> r0 = r3.elements
            monitor-enter(r0)
            boolean r1 = r3.closed     // Catch:{ all -> 0x001e }
            r2 = 0
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return r2
        L_0x000a:
            java.util.LinkedHashMap<java.lang.String, java.sql.PreparedStatement> r1 = r3.elements     // Catch:{ all -> 0x001e }
            java.lang.Object r4 = r1.remove(r4)     // Catch:{ all -> 0x001e }
            java.sql.PreparedStatement r4 = (java.sql.PreparedStatement) r4     // Catch:{ all -> 0x001e }
            if (r4 == 0) goto L_0x001c
            boolean r1 = r4.isClosed()     // Catch:{ all -> 0x001e }
            if (r1 == 0) goto L_0x001c
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return r2
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            return r4
        L_0x001e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.requery.sql.PreparedStatementCache.get(java.lang.String):java.sql.PreparedStatement");
    }

    public PreparedStatement put(String str, PreparedStatement preparedStatement) {
        if (!(preparedStatement instanceof CachedStatement)) {
            preparedStatement = new CachedStatement(this, str, preparedStatement);
        }
        synchronized (this.elements) {
            if (this.closed) {
                return null;
            }
            this.elements.put(str, preparedStatement);
            return preparedStatement;
        }
    }

    public void close() {
        synchronized (this.elements) {
            if (!this.closed) {
                this.closed = true;
                for (PreparedStatement closeStatement : this.elements.values()) {
                    closeStatement(closeStatement);
                }
                this.elements.clear();
            }
        }
    }

    /* access modifiers changed from: private */
    public void closeStatement(PreparedStatement preparedStatement) {
        try {
            if (!preparedStatement.isClosed() && (preparedStatement instanceof CachedStatement)) {
                ((CachedStatement) preparedStatement).closeDelegate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
