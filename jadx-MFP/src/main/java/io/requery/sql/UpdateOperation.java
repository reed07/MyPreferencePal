package io.requery.sql;

import io.requery.query.BaseScalar;
import io.requery.query.Scalar;
import io.requery.query.element.QueryElement;
import io.requery.query.element.QueryOperation;

class UpdateOperation extends PreparedQueryOperation implements QueryOperation<Scalar<Integer>> {
    UpdateOperation(RuntimeConfiguration runtimeConfiguration) {
        super(runtimeConfiguration, null);
    }

    UpdateOperation(RuntimeConfiguration runtimeConfiguration, GeneratedResultReader generatedResultReader) {
        super(runtimeConfiguration, generatedResultReader);
    }

    public Scalar<Integer> evaluate(final QueryElement<Scalar<Integer>> queryElement) {
        return new BaseScalar<Integer>(this.configuration.getWriteExecutor()) {
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x0081, code lost:
                r0 = th;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:38:0x0082, code lost:
                r5 = null;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:42:0x0086, code lost:
                r5 = move-exception;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:43:0x0087, code lost:
                r8 = r5;
                r5 = r0;
                r0 = r8;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Removed duplicated region for block: B:37:0x0081 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x002b] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Integer evaluate() {
                /*
                    r9 = this;
                    io.requery.sql.gen.DefaultOutput r0 = new io.requery.sql.gen.DefaultOutput
                    io.requery.sql.UpdateOperation r1 = io.requery.sql.UpdateOperation.this
                    io.requery.sql.RuntimeConfiguration r1 = r1.configuration
                    io.requery.query.element.QueryElement r2 = r3
                    r0.<init>(r1, r2)
                    java.lang.String r1 = r0.toSql()
                    io.requery.sql.UpdateOperation r2 = io.requery.sql.UpdateOperation.this
                    io.requery.sql.RuntimeConfiguration r2 = r2.configuration
                    io.requery.sql.TransactionProvider r2 = r2.getTransactionProvider()
                    io.requery.query.element.QueryElement r3 = r3
                    java.util.Set r3 = r3.entityTypes()
                    io.requery.sql.TransactionScope r4 = new io.requery.sql.TransactionScope     // Catch:{ SQLException -> 0x00af }
                    r4.<init>(r2, r3)     // Catch:{ SQLException -> 0x00af }
                    r2 = 0
                    io.requery.sql.UpdateOperation r3 = io.requery.sql.UpdateOperation.this     // Catch:{ Throwable -> 0x009d }
                    io.requery.sql.RuntimeConfiguration r3 = r3.configuration     // Catch:{ Throwable -> 0x009d }
                    java.sql.Connection r3 = r3.getConnection()     // Catch:{ Throwable -> 0x009d }
                    io.requery.sql.UpdateOperation r5 = io.requery.sql.UpdateOperation.this     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                    io.requery.sql.RuntimeConfiguration r5 = r5.configuration     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                    io.requery.sql.StatementListener r5 = r5.getStatementListener()     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                    io.requery.sql.UpdateOperation r6 = io.requery.sql.UpdateOperation.this     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                    java.sql.PreparedStatement r6 = r6.prepare(r1, r3)     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                    io.requery.sql.BoundParameters r0 = r0.parameters()     // Catch:{ Throwable -> 0x006a, all -> 0x0067 }
                    io.requery.sql.UpdateOperation r7 = io.requery.sql.UpdateOperation.this     // Catch:{ Throwable -> 0x006a, all -> 0x0067 }
                    r7.mapParameters(r6, r0)     // Catch:{ Throwable -> 0x006a, all -> 0x0067 }
                    r5.beforeExecuteUpdate(r6, r1, r0)     // Catch:{ Throwable -> 0x006a, all -> 0x0067 }
                    int r0 = r6.executeUpdate()     // Catch:{ Throwable -> 0x006a, all -> 0x0067 }
                    r5.afterExecuteUpdate(r6, r0)     // Catch:{ Throwable -> 0x006a, all -> 0x0067 }
                    io.requery.sql.UpdateOperation r5 = io.requery.sql.UpdateOperation.this     // Catch:{ Throwable -> 0x006a, all -> 0x0067 }
                    r7 = 0
                    r5.readGeneratedKeys(r7, r6)     // Catch:{ Throwable -> 0x006a, all -> 0x0067 }
                    if (r6 == 0) goto L_0x0057
                    r6.close()     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                L_0x0057:
                    r4.commit()     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                    if (r3 == 0) goto L_0x005f
                    r3.close()     // Catch:{ Throwable -> 0x009d }
                L_0x005f:
                    r4.close()     // Catch:{ SQLException -> 0x00af }
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                    return r0
                L_0x0067:
                    r0 = move-exception
                    r5 = r2
                    goto L_0x0070
                L_0x006a:
                    r0 = move-exception
                    throw r0     // Catch:{ all -> 0x006c }
                L_0x006c:
                    r5 = move-exception
                    r8 = r5
                    r5 = r0
                    r0 = r8
                L_0x0070:
                    if (r6 == 0) goto L_0x0080
                    if (r5 == 0) goto L_0x007d
                    r6.close()     // Catch:{ Throwable -> 0x0078, all -> 0x0081 }
                    goto L_0x0080
                L_0x0078:
                    r6 = move-exception
                    r5.addSuppressed(r6)     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                    goto L_0x0080
                L_0x007d:
                    r6.close()     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                L_0x0080:
                    throw r0     // Catch:{ Throwable -> 0x0084, all -> 0x0081 }
                L_0x0081:
                    r0 = move-exception
                    r5 = r2
                    goto L_0x008a
                L_0x0084:
                    r0 = move-exception
                    throw r0     // Catch:{ all -> 0x0086 }
                L_0x0086:
                    r5 = move-exception
                    r8 = r5
                    r5 = r0
                    r0 = r8
                L_0x008a:
                    if (r3 == 0) goto L_0x009a
                    if (r5 == 0) goto L_0x0097
                    r3.close()     // Catch:{ Throwable -> 0x0092 }
                    goto L_0x009a
                L_0x0092:
                    r3 = move-exception
                    r5.addSuppressed(r3)     // Catch:{ Throwable -> 0x009d }
                    goto L_0x009a
                L_0x0097:
                    r3.close()     // Catch:{ Throwable -> 0x009d }
                L_0x009a:
                    throw r0     // Catch:{ Throwable -> 0x009d }
                L_0x009b:
                    r0 = move-exception
                    goto L_0x00a0
                L_0x009d:
                    r0 = move-exception
                    r2 = r0
                    throw r2     // Catch:{ all -> 0x009b }
                L_0x00a0:
                    if (r2 == 0) goto L_0x00ab
                    r4.close()     // Catch:{ Throwable -> 0x00a6 }
                    goto L_0x00ae
                L_0x00a6:
                    r3 = move-exception
                    r2.addSuppressed(r3)     // Catch:{ SQLException -> 0x00af }
                    goto L_0x00ae
                L_0x00ab:
                    r4.close()     // Catch:{ SQLException -> 0x00af }
                L_0x00ae:
                    throw r0     // Catch:{ SQLException -> 0x00af }
                L_0x00af:
                    r0 = move-exception
                    io.requery.sql.StatementExecutionException r2 = new io.requery.sql.StatementExecutionException
                    r2.<init>(r0, r1)
                    throw r2
                */
                throw new UnsupportedOperationException("Method not decompiled: io.requery.sql.UpdateOperation.AnonymousClass1.evaluate():java.lang.Integer");
            }
        };
    }
}
